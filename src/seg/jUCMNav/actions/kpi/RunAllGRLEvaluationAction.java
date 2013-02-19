package seg.jUCMNav.actions.kpi;

import grl.Actor;
import grl.EvaluationStrategy;
import grl.GRLLinkableElement;
import grl.GRLspec;
import grl.IntentionalElement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editparts.strategyTreeEditparts.EnumerationTypeTreeEditPart;
import seg.jUCMNav.editparts.strategyTreeEditparts.VariableListTreeEditPart;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.strategies.BatchEvaluationUtil;
import seg.jUCMNav.views.preferences.ReportGeneratorPreferences;

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
        int prefTrend = Integer.parseInt(ReportGeneratorPreferences.getGRLEvalStrategyTrend());

        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        if (sel.getGroup() != null) {
            EList strategies = sel.getGroup().getStrategies();
            HashMap<Integer, EvaluationStrategy> stratIndexes = new HashMap<Integer, EvaluationStrategy>();

            evalTable = BatchEvaluationUtil.calculateAllEvaluations(strategies, sel.getGroup().getGrlspec());
            
            int j = 0;
            for (Iterator i = strategies.iterator(); i.hasNext();) {
                EvaluationStrategy strat = (EvaluationStrategy) i.next();
                
                stratIndexes.put(j++, strat);
            }
            
            if(evalTable.size() > 0) {
                Set<GRLLinkableElement> elements = evalTable.get(strategies.get(0)).keySet();
                GRLspec grlspec = sel.getGroup().getGrlspec();
                
                for (Iterator iter = grlspec.getActors().iterator(); iter.hasNext();) {
                    Actor actor = (Actor) iter.next();
                    int trend = BatchEvaluationUtil.calculateTrend(stratIndexes, actor, evalTable, prefTrend);
                    
                    MetadataHelper.addMetaData(sel.getGroup().getGrlspec().getUrnspec(), actor, "_trend", new Integer(trend).toString());
                }
                
                for (Iterator i = grlspec.getIntElements().iterator(); i.hasNext();) {
                    IntentionalElement element = (IntentionalElement) i.next();
                    int trend = BatchEvaluationUtil.calculateTrend(stratIndexes, element, evalTable, prefTrend);
                    
                    MetadataHelper.addMetaData(sel.getGroup().getGrlspec().getUrnspec(), element, "_trend", new Integer(trend).toString());
                }
            }
        }
    }
}
