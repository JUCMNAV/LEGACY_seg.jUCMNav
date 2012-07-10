package seg.jUCMNav.strategies;

import grl.Actor;
import grl.Contribution;
import grl.DecompositionType;
import grl.ElementLink;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElement;
import grl.IntentionalElementType;
import grl.impl.ContributionImpl;
import grl.impl.DecompositionImpl;
import grl.impl.DependencyImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import JaCoP.constraints.Constraint;
import JaCoP.constraints.Max;
import JaCoP.constraints.Min;
import JaCoP.constraints.Or;
import JaCoP.constraints.SumWeight;
import JaCoP.constraints.XeqC;
import JaCoP.constraints.XeqY;
import JaCoP.constraints.XlteqY;
import JaCoP.constraints.XmodYeqZ;
import JaCoP.constraints.XmulCeqZ;
import JaCoP.constraints.XplusYeqZ;
import JaCoP.core.IntVar;
import JaCoP.core.Store;
import JaCoP.search.DepthFirstSearch;
import JaCoP.search.IndomainMin;
import JaCoP.search.MostConstrainedStatic;
import JaCoP.search.Search;
import JaCoP.search.SelectChoicePoint;
import JaCoP.search.SimpleSelect;

/**
 * This class implement the default GRL evaluation algorithm and using a 
 * constraint solver to calculate evaluation values.
 * 
 * @author Hao
 * 
 */
public class Hao2011Algorithm implements IGRLStrategyAlgorithm {

    private List<IntentionalElement> allElements; 
    private List<IntentionalElement> strategyElements;     
    private Store store;
    private HashMap<String, IntVar> variables;
    private HashMap<IntentionalElement, Evaluation> evaluations;
    private boolean foundSolution;
    private static IntVar oneHundred;
    private static IntVar minusOneHundred; 
    private List<Constraint> constraints;
    private static final String VAR_PREFIX = "Node"; //$NON-NLS-1$

    public void init(EvaluationStrategy strategy, HashMap evaluations) {
        setAllElements(new ArrayList<IntentionalElement>());
        setStrategyElements(new ArrayList<IntentionalElement>());
        setStore(new Store());
        setEvaluations(evaluations);
        setConstraints(new ArrayList<Constraint>());
        setVariables(new HashMap<String, IntVar>());
        reInitializeEvaluations();
        oneHundred = new IntVar(getStore(), "oneHundred", 100, 100); //$NON-NLS-1$
        minusOneHundred = new IntVar(getStore(), "minusOneHundred", -100, -100); //$NON-NLS-1$
        initializeStrategyElements(strategy);
        initializeConstraintVariables(strategy);
        analyseElementAndLinks(strategy);
    }

    private void reInitializeEvaluations() {
        for (Entry<IntentionalElement, Evaluation> anEntry : getEvaluations().entrySet()) {
            Evaluation evaluation = anEntry.getValue();
            if(evaluation.getStrategies() == null) {
                evaluation.setEvaluation(0);
            }
        }
    }

    private void analyseElementAndLinks(EvaluationStrategy strategy) {
        Iterator elementIterator = strategy.getGrlspec().getIntElements().iterator();
        while (elementIterator.hasNext()) {
            IntentionalElement element = (IntentionalElement) elementIterator.next();
            List<IntentionalElement> andDecompositionElements = new ArrayList<IntentionalElement>(); 
            List<IntentionalElement> orDecompositionElements = new ArrayList<IntentionalElement>(); 
            List<IntentionalElement> dependencyElements = new ArrayList<IntentionalElement>(); 
            List<IntentionalElement> contributionElements = new ArrayList<IntentionalElement>(); 
            categorizeElements(element, andDecompositionElements, orDecompositionElements, dependencyElements, contributionElements);
            generateConstraints(element, andDecompositionElements, orDecompositionElements, dependencyElements, contributionElements);
        }
    }

    /**
     * Generate constraints based on the elements collected
     * @param element
     * @param andDecompositionElements
     * @param orDecompositionElements
     * @param dependencyElements
     * @param contributionElements
     */
    private void generateConstraints(IntentionalElement element, List<IntentionalElement> andDecompositionElements, List<IntentionalElement> orDecompositionElements,
            List<IntentionalElement> dependencyElements, List<IntentionalElement> contributionElements) {
        IntVar minMaxResult = null;
        // dependency can be considered independently of other type of links, so we do it first
        if(dependencyElements.size() != 0) {
            for (int i = 0; i < dependencyElements.size(); i++) {
                IntVar aVar = getConstraintVariable(dependencyElements.get(i));
                XlteqY xlteqYConstraint = new XlteqY(getConstraintVariable(element), aVar);
                getConstraints().add(xlteqYConstraint);
            }
        }

        if(andDecompositionElements.size() != 0) {
            IntVar[] andList = new IntVar[andDecompositionElements.size()];
            for (int i = 0; i < andDecompositionElements.size(); i++) {
                andList[i] = getConstraintVariable(andDecompositionElements.get(i));
            }
            minMaxResult = new IntVar(getStore(), "minMaxResult" + element.getId(), -100, 100); //$NON-NLS-1$
            Constraint minConstraint = new Min(andList, minMaxResult); 
            getConstraints().add(minConstraint);
        }
        if(orDecompositionElements.size() != 0) {
            IntVar[] orList = new IntVar[orDecompositionElements.size()];
            for (int i = 0; i < orDecompositionElements.size(); i++) {
                orList[i] = getConstraintVariable(orDecompositionElements.get(i));
            }
            minMaxResult = new IntVar(getStore(), "minMaxResult" + element.getId(), -100, 100); //$NON-NLS-1$
            Constraint maxConstraint = new Max(orList, minMaxResult); 
            getConstraints().add(maxConstraint);
        }
        if(contributionElements.size() != 0) {
            IntVar[] contributionVariableList = null;
            int[] contributionConstantList = null;
            int numberOfNodesFromContributionLinks = contributionElements.size();
            contributionVariableList = new IntVar[numberOfNodesFromContributionLinks];
            contributionConstantList = new int[numberOfNodesFromContributionLinks];
            for (int i = 0; i < numberOfNodesFromContributionLinks; i++) {
                contributionVariableList[i] = getConstraintVariable(contributionElements.get(i));
                Iterator linksIterator = contributionElements.get(i).getLinksSrc().iterator();
                while (linksIterator.hasNext()) {
                    ElementLink aLink = (ElementLink) linksIterator.next();
                    if(isContribution(aLink) && element.equals(aLink.getDest())){
                        //contributionConstantList[i] = ((Contribution) aLink).getQuantitativeContribution();
                        contributionConstantList[i] = EvaluationStrategyManager.getInstance().getActiveQuantitativeContribution((Contribution) aLink);
                    }
                }
            }

            IntVar sum = new IntVar(store, "sum" + element.getId() , -10000*numberOfNodesFromContributionLinks, 10000*numberOfNodesFromContributionLinks); //$NON-NLS-1$ 
            //MAX (-100, MIN(100, (60 * A + B * -60 + c*-70) / 100 ))
            //use collection size
            IntVar sumOver100 = new IntVar(store, "sum" + element.getId() + "Over100", -100*numberOfNodesFromContributionLinks, 100*numberOfNodesFromContributionLinks);  //$NON-NLS-1$  //$NON-NLS-2$
            IntVar remainder = new IntVar(store, "remainder" + element.getId(), -100, 100); //$NON-NLS-1$
            IntVar quotient = new IntVar(store, "quotient" + element.getId(), -10000*numberOfNodesFromContributionLinks, 10000*numberOfNodesFromContributionLinks); //$NON-NLS-1$
            IntVar minCompareTo100 = new IntVar(store, "min" + element.getId() + "CompareTo100", -100*numberOfNodesFromContributionLinks, 100);  //$NON-NLS-1$ //$NON-NLS-2$
            IntVar[] minList = {oneHundred, sumOver100};
            IntVar[] maxList = {minusOneHundred, minCompareTo100};
            getConstraints().add(new SumWeight(contributionVariableList, contributionConstantList, sum));
            getConstraints().add(new XmodYeqZ(sum, oneHundred, remainder));
            getConstraints().add(new XplusYeqZ(remainder, quotient, sum));
            getConstraints().add(new XmulCeqZ(sumOver100, 100, quotient));
            getConstraints().add(new Min(minList, minCompareTo100));
            IntVar contributionResult = new IntVar(getStore(), "contributionResult" + element.getId(), -100, 100); //$NON-NLS-1$
            getConstraints().add(new Max(maxList, contributionResult));
            // we need to consider the decomposition result, then combine the result with contribution
            if(andDecompositionElements.size() != 0 || orDecompositionElements.size() != 0) {
                getConstraints().add(new XplusYeqZ(minMaxResult, contributionResult, getConstraintVariable(element)));
            }else {
                getConstraints().add(new XeqY(contributionResult, getConstraintVariable(element)));
            }
        }else {
            if(andDecompositionElements.size() != 0 || orDecompositionElements.size() != 0)
                getConstraints().add(new XeqY(minMaxResult, getConstraintVariable(element)));
        }
    }

    /**
     * Collect elements by type of links into different collections(And, Or, Dependency, Contribution).
     *	This will be the place to add more type of links we need to support in the future!
     * @param element
     * @param andDecompositionElements 
     * @param orDecompositionElements
     * @param dependencyElements
     * @param contributionElements
     */
    private void categorizeElements(IntentionalElement element, List<IntentionalElement> andDecompositionElements, 
            List<IntentionalElement> orDecompositionElements, List<IntentionalElement> dependencyElements, List<IntentionalElement> contributionElements) {
        Iterator linksIterator = element.getLinksDest().iterator(); //Return the list of elementlink
        List<IntentionalElement> dependencyRealSrc = new ArrayList<IntentionalElement>(); // List of real sources of dependency links

        while (linksIterator.hasNext()){
            ElementLink link = (ElementLink) linksIterator.next();
            if (isDecomposition(link)){
                if (isAndDecomposition(element.getDecompositionType())){
                    andDecompositionElements.add((IntentionalElement) link.getSrc());
                } else if (isOrDecomposition(element.getDecompositionType())){
                    orDecompositionElements.add((IntentionalElement) link.getSrc());
                }
            } else if (isDependency(link)) {
                dependencyElements.add((IntentionalElement) link.getSrc()); 
                // For historical reasons, Dependency links have their source and dest inverted
                // This is needed to detect real leaf nodes in a GRL model.
                dependencyRealSrc.add((IntentionalElement) link.getDest());
            } else if (isContribution(link)) {
                contributionElements.add((IntentionalElement) link.getSrc());
            }
        }
        IntVar variable = getConstraintVariable(element);
        int evaluation = getEvaluations().get(element).getEvaluation();
        if(!getStrategyElements().contains(element)) {
            // Leaf nodes are handled here
            if(andDecompositionElements.size() == 0 && orDecompositionElements.size() == 0 && dependencyRealSrc.size() == 0  && contributionElements.size() == 0) {
                // only Task type of intentional elements shall be enforced to 0 or 100
                if(IntentionalElementType.TASK == element.getType().getValue()) {
                    getStore().impose(new Or(new XeqC(variable,0), new XeqC(variable,100)));
                }
            }
        } else {
            getStore().impose(new XeqC(variable, evaluation));
        }
    }

    public void calculate() {
        findSolution();
        if(isFoundSolution())
            populateSolution();  
        else
            populateConflicts();
    }

    private void populateSolution() {
        for (int i = 0; i < getAllElements().size(); i++) {
            IntentionalElement anElement = getAllElements().get(i);
            if(!getStrategyElements().contains(anElement)) {
                IntVar anVar = getConstraintVariable(anElement);
                getEvaluations().get(anElement).setEvaluation(anVar.value());
            }
            System.out.println("after: [" + anElement.getName() + "] with id ["+anElement.getId() +"] has evaluation ["+ getEvaluations().get(anElement).getEvaluation() +"]");  //$NON-NLS-1$  //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        }
        System.out.println();
    }

    private void populateConflicts() {
        // Show conflicts on uninitialized values.
        for (int i = 0; i < getAllElements().size(); i++) {
            IntentionalElement anElement = getAllElements().get(i);
            if(!getStrategyElements().contains(anElement)) {
                getEvaluations().get(anElement).setEvaluation(-101);
            }
        }
    }

    private void findSolution() {
        Search label = new DepthFirstSearch(); 
        label.getSolutionListener().searchAll(false); 
        label.getSolutionListener().recordSolutions(true);
        int numberOfElements = getAllElements().size();
        List<IntVar> initializedElements = new ArrayList<IntVar>();
        List<IntVar> notInitializedElements = new ArrayList<IntVar>();

        IntVar[] fullList = new IntVar[numberOfElements];
        // put all the non-zero initial values into consideration
        for (int i = 0; i < numberOfElements; i++) {
            IntentionalElement anElement = getAllElements().get(i);
            if(getStrategyElements().contains(anElement)) {
                initializedElements.add(getConstraintVariable(anElement));
            } else {
                notInitializedElements.add(getConstraintVariable(anElement));
            }
        }
        // build the full list of constraint variables below, include both initialized and uninitialized ones
        for (int i = 0; i < initializedElements.size(); i++) {
            fullList[i] = initializedElements.get(i);
        }
        for (int i = 0; i < notInitializedElements.size(); i++) {
            fullList[initializedElements.size() + i] = notInitializedElements.get(i);
        }
        for (int i = 0; i < fullList.length; i++) {
            System.out.println("before: [" + fullList[i] +"]");  //$NON-NLS-1$  //$NON-NLS-2$
        }

        for (int i = 0; i < getConstraints().size(); i++) {
            getStore().impose(getConstraints().get(i));
        }

        // have conflicting constraints, not solvable 
        if(!getStore().consistency()) {
            System.out.println("have conflicting constraints, not solvable ");  //$NON-NLS-1$
            setFoundSolution(false);
            return;
        }
        // TODO IndomainMin can be replaced with IndomainMax/IndomainMedian/etc......
        SelectChoicePoint select = new SimpleSelect<IntVar>(fullList, new MostConstrainedStatic<IntVar>(), new IndomainMin()); 

        // set the flag that indicates whether we found solution(s)
        setFoundSolution(label.labeling(store, select));
        if(isFoundSolution()) {
            for (int i=1; i<=label.getSolutionListener().solutionsNo(); i++){ 
                System.out.print("Solution " + i + ": ");  //$NON-NLS-1$ //$NON-NLS-2$
                for (int j=0; j<label.getSolution(i).length; j++) 
                    System.out.print(fullList[j].id + "=["+ label.getSolution(i)[j] +"] ");  //$NON-NLS-1$ //$NON-NLS-2$
                System.out.println(); 
            }
        }
    }

    public boolean hasNextNode() {
        if (getAllElements().size() > 0) {
            return true;
        }
        return false;
    }

    public IntentionalElement nextNode() {
        IntentionalElement intElem = (IntentionalElement) getAllElements().remove(0);
        return intElem;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#getEvaluationType()
     */
    public int getEvaluationType() {
        return IGRLStrategyAlgorithm.EVAL_CONSTRAINT_SOLVER;
    }

    /*
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm#getActorEvaluation(grl.Actor)
     */
    public int getActorEvaluation(Actor actor) {
        return StrategyAlgorithmImplementationHelper.defaultActorEvaluation(actor);
    }

    public List<IntentionalElement> getAllElements() {
        return allElements;
    }

    public void setAllElements(List<IntentionalElement> allElements) {
        this.allElements = allElements;
    }

    public List<IntentionalElement> getStrategyElements() {
        return strategyElements;
    }

    public void setStrategyElements(List<IntentionalElement> strategyElements) {
        this.strategyElements = strategyElements;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public boolean isConstraintSolverAlgorithm() {
        return true;
    }

    public HashMap<IntentionalElement, Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(HashMap<IntentionalElement, Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    /**
     * NOT USED! Should NOT be called
     */
    @Override
    public int getEvaluation(IntentionalElement element) {
        throw new RuntimeException("Not implemented, shouldn't be called."); //$NON-NLS-1$
    }

    public boolean isFoundSolution() {
        return foundSolution;
    }

    public void setFoundSolution(boolean foundSolution) {
        this.foundSolution = foundSolution;
    }

    public List<Constraint> getConstraints() {
        return constraints;
    }

    public void setConstraints(List<Constraint> constraints) {
        this.constraints = constraints;
    }

    /*
     * The section below are functions that DO NOT really belong here.
     * Following good OOD practice will make it harder to 
     * maintain whenever models get changed. They have been refactored to 
     * here simply because the grl model classes are tool generated.  
     */

    protected boolean isAndDecomposition(DecompositionType decompositionType) {
        return DecompositionType.AND == decompositionType.getValue();
    }

    protected boolean isOrDecomposition(DecompositionType decompositionType) {
        return DecompositionType.OR == decompositionType.getValue();
    }

    protected boolean isXorDecomposition(DecompositionType decompositionType) {
        return DecompositionType.XOR == decompositionType.getValue();
    }

    protected boolean isDecomposition(ElementLink link) {
        return link instanceof DecompositionImpl;
    }

    protected boolean isContribution(ElementLink link) {
        return link instanceof ContributionImpl;
    }

    protected boolean isDependency(ElementLink link) {
        return link instanceof DependencyImpl;
    }

    public HashMap<String, IntVar> getVariables() {
        return variables;
    }

    public void setVariables(HashMap<String, IntVar> variables) {
        this.variables = variables;
    }

    public IntVar getConstraintVariable(IntentionalElement element) {
        String varId = VAR_PREFIX + element.getId();
        return getVariables().get(varId);
    }

    private void initializeConstraintVariables(EvaluationStrategy strategy) {
        Iterator elementIterator = strategy.getGrlspec().getIntElements().iterator();
        while (elementIterator.hasNext()) {
            IntentionalElement element = (IntentionalElement) elementIterator.next();
            getAllElements().add(element);
            String varId = VAR_PREFIX + element.getId();
            getVariables().put(varId, new IntVar(store, varId, -100, 100));
        }
    }

    private void initializeStrategyElements(EvaluationStrategy strategy) {
        Iterator elementIterator = strategy.getEvaluations().iterator();
        while (elementIterator.hasNext()) {
            Evaluation eval = (Evaluation) elementIterator.next();
            getStrategyElements().add(eval.getIntElement());
        }
    }

}
