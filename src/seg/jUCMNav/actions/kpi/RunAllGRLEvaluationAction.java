package seg.jUCMNav.actions.kpi;

import grl.EvaluationStrategy;
import grl.GRLLinkableElement;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editparts.strategyTreeEditparts.EnumerationTypeTreeEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.VariableListTreeEditPart;
import seg.jUCMNav.strategies.EvaluationStrategyManager;

public class RunAllGRLEvaluationAction extends SelectionAction {

    public static final String RUNALLGRLEVALUATIONS = "Run All GRL Evaluations"; //$NON-NLS-1$
    
    private HashMap<EvaluationStrategy, HashMap<GRLLinkableElement, Integer>> evalTable = new HashMap<EvaluationStrategy, HashMap<GRLLinkableElement, Integer>>();

    public RunAllGRLEvaluationAction(IWorkbenchPart part) {
        super(part);

        setId(RUNALLGRLEVALUATIONS);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/refresh.gif")); //$NON-NLS-1$
    }

    @Override
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());

        boolean b = sel.getUrnspec() != null
                && sel.getStrategy() == null
                && (sel.getGroup() != null || (sel.getGroup() == null && getSelectedObjects().size() == 1
                        && !(getSelectedObjects().get(0) instanceof VariableListTreeEditPart) && !(getSelectedObjects().get(0) instanceof EnumerationTypeTreeEditPart)));
        if (!b)
            return false;

        if (sel.getStrategiesGroup() != null)
            return sel.getStrategiesGroup().getStrategies().size() > 0;
        else {
            // for (Iterator iter = sel.getUCMspec().get.iterator(); iter.hasNext();) {
            // ScenarioGroup group = (ScenarioGroup) iter.next();
            // if (group.getScenarios().size() > 0)
            // return true;
            // }

            return false;
        }
    }

    public void run() {
        UCMNavMultiPageEditor multieditor = (UCMNavMultiPageEditor) getWorkbenchPart();

        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        if (sel.getGroup() != null) {
            EList strategies = sel.getGroup().getStrategies();
            
            EvaluationStrategyManager manager = EvaluationStrategyManager.getInstance();
            
            int j = 0;
            for (Iterator i = strategies.iterator(); i.hasNext();) {
                EvaluationStrategy strat = (EvaluationStrategy) i.next();

                manager.setStrategy(strat);
                manager.calculateEvaluation();
                
                evalTable.put(strat, manager.getEvaluations());
            }
            
            if(evalTable.size() > 0) {
//                calculateTrend(strategies, )
            }
        }
    }
    
    private int calculateTrend(HashMap<Integer, EvaluationStrategy> strategies, GRLLinkableElement element, int numStrat) {
        int trend = -2; 
            //no trend = -2
            //varying trend = -3
            //no change = 0
            //negative trend = -1
            //positive trend = 1
        int lastValue;
        int currentValue;
        int prefTrend = 3;
        
        EvaluationStrategy currentStrategy;
        
        if (numStrat >= prefTrend && prefTrend > 1){//else not enough data to calculate trend
  
            currentStrategy = strategies.get(numStrat - prefTrend+1); 
            
            lastValue = evalTable.get(currentStrategy).get(element);
            
            for (int i = (int)numStrat - prefTrend + 2; i<=numStrat; i++){
                currentStrategy = strategies.get(i); 
                currentValue = evalTable.get(currentStrategy).get(element);
                 
                
                if (trend == -2){ //no trend calculated yet (first element)
                    if (currentValue > lastValue){
                        trend = 1;
                    }else if(currentValue < lastValue){
                        trend = -1;
                    }else{
                        trend = 0;
                    }
                }
                else{
                    //if (!((currentValue > lastValue && trend == 1) || (currentValue < lastValue && trend == -1) || (currentValue == lastValue && trend == 0))){
                        //trend = -2;
                    //  trend = -3;
                    //  break;
                    //}
                    if (trend == 0 && currentValue > lastValue){ //neutral trend changed to positive
                        trend = 1;  
                    }else if(trend == 0 && currentValue < lastValue){//neutral trend changed to negative
                        trend = -1;
                    }else if(!((currentValue >= lastValue && trend == 1) || (currentValue <= lastValue && trend == -1) || (currentValue == lastValue && trend == 0))){ //trend changed
                        trend = -3;
                        break;
                    }
                }
                
                lastValue = currentValue;
            }
        }
        
        return trend;   
    }
}
