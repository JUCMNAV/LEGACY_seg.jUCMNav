/**
 * 
 */
package seg.jUCMNav.strategies;

import grl.Actor;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GRLspec;
import grl.IntentionalElement;

import java.util.HashMap;
import java.util.Iterator;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.editparts.URNRootEditPart;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddEvaluationCommand;

/**
 * This class is a singleton responsible to manage the current strategy. 
 * It does the evaluation calculation for IntentionalElement, create the Evaluation
 * and return the value of the evaluation given an IntentionalElement for the current strategy.
 * 
 * @author Jean-François Roy
 *
 */
public class EvaluationStrategyManager {
    
    private class EvaluationCalculation{
        public IntentionalElement element;
        public int linkCalc;
        public int totalLinkDest;
        
        public EvaluationCalculation(IntentionalElement element, int totalLink){
            this.element = element;
            this.totalLinkDest = totalLink;
            linkCalc = 0;
        }
    }
    private UCMNavMultiPageEditor multieditor;
    
    private static EvaluationStrategyManager instance;
    private boolean canRefresh;
    public static synchronized EvaluationStrategyManager getInstance()
    {
        if (instance == null){
            instance = new EvaluationStrategyManager();
        }
        instance.canRefresh = true;
        return instance;
    }

    public static synchronized EvaluationStrategyManager getInstance(boolean canRefresh)
    {
        if (instance == null){
            instance = new EvaluationStrategyManager();
        }
        instance.canRefresh = canRefresh;
        return instance;
    }
    
    private HashMap evaluations; //HashMap to keep link between intentionalElement and the evaluation for a particular strategy
    private EvaluationStrategy strategy;
    private IGRLStrategyAlgorithm algo;
    /**
     * 
     */
    private EvaluationStrategyManager() {
        
    }
    
    public synchronized void calculateEvaluation(){
        if (strategy == null){
            return;
        }
        algo = new DefaultGRLStrategyAlgorithm();
        
        algo.init(strategy,evaluations);
        
        while(algo.hasNextNode()){
            IntentionalElement element = algo.nextNode();
            Evaluation eval = (Evaluation)evaluations.get(element);
            eval.setEvaluation(algo.getEvaluation(element));
        }

        //Refresh all the diagrams if canRefresh set to true
        if (canRefresh && multieditor!=null){
            for (int i=0; i< multieditor.getPageCount(); i++){
                UrnEditor u = (UrnEditor) multieditor.getEditor(i);
                ((URNRootEditPart) u.getGraphicalViewer().getRootEditPart()).refreshChildren();         
            }
        }
    }
    
    public synchronized int getActorEvaluation(Actor actor){
        return algo.getActorEvaluation(actor);
    }
    public synchronized int getEvaluation(IntentionalElement elem){
        Evaluation temp = (Evaluation)evaluations.get(elem);
        if (temp == null  && strategy!=null && strategy.getGrlspec()!=null && strategy.getGrlspec().getUrnspec()!=null){
            temp = (Evaluation)ModelCreationFactory.getNewObject(strategy.getGrlspec().getUrnspec(), Evaluation.class);
            evaluations.put(elem, temp);
            return temp.getEvaluation();
        } else if (temp != null)
        {
        	return temp.getEvaluation();
        }
        else return 0;
        
    }
    
    public synchronized Evaluation getEvaluationObject(IntentionalElement elem){
        Evaluation temp = (Evaluation)evaluations.get(elem);
        //if the evaluation is null, it is a new element and we need to create a new evaluation
        if (temp == null && strategy!=null && strategy.getGrlspec()!=null && strategy.getGrlspec().getUrnspec()!=null){
            temp = (Evaluation)ModelCreationFactory.getNewObject(strategy.getGrlspec().getUrnspec(), Evaluation.class);
            evaluations.put(elem, temp);
        }
        return temp;
    }
    
    public synchronized EvaluationStrategy getEvaluationStrategy(){
        return strategy;
    }
    
    public synchronized void setEvaluationForElement(IntentionalElement element, Evaluation eval){
        if (eval != null){
            evaluations.remove(element);
            evaluations.put(element,eval);
            calculateEvaluation();
        }
        
    }
    public synchronized void setStrategy(EvaluationStrategy strategy){
        this.strategy = strategy;

        //Create a new hash map for this strategy
        evaluations = new HashMap();
        if (strategy != null){
        //Go through all the intentionalElement and create a new Evaluation object if no one exist for this strategy
            GRLspec grl = strategy.getGrlspec();
            Iterator it = grl.getIntElements().iterator();
            while (it.hasNext()){
                IntentionalElement elem = (IntentionalElement)it.next();
                //Verify if an evaluation exist for this strategy. This could create performance problem!!!!
                Iterator sc = strategy.getEvaluations().iterator();
                Evaluation eval = null;
                while(sc.hasNext() && eval == null){
                    Evaluation temp = (Evaluation)sc.next();
                    if (temp.getIntElement() == elem){
                        eval = temp;
                    }
                }
                if (eval == null){
                    eval = (Evaluation)ModelCreationFactory.getNewObject(grl.getUrnspec(), Evaluation.class);
                }
                evaluations.put(elem,eval);
            }
            calculateEvaluation();
        }
    }
    
    public synchronized void setIntentionalElementEvaluation(IntentionalElement element, int value){
        //The evaluation could only be between 100 and -100. Do nothing if it is not the case
        if (value<=100 && value>=-100){
            Evaluation eval = (Evaluation)evaluations.get(element);
            //Change the value in the evaluation
            if (value != eval.getEvaluation()){
                eval.setEvaluation(value);
            }
            //If it is a new Evaluation enter by the user, link it with the strategy and intentionalElement
            AddEvaluationCommand cmd = new AddEvaluationCommand(eval, element, strategy);
            if (cmd.canExecute()){
                cmd.execute();
            }

            calculateEvaluation();
        }
    }

    public synchronized void setMultieditor(UCMNavMultiPageEditor multieditor) {
        this.multieditor = multieditor;
    }
}
