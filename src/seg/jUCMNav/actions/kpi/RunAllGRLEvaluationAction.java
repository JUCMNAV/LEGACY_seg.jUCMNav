package seg.jUCMNav.actions.kpi;

import grl.Actor;
import grl.EvaluationStrategy;
import grl.GRLLinkableElement;
import grl.GRLspec;
import grl.IntentionalElement;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

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
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.preferences.ReportGeneratorPreferences;
import seg.jUCMNav.views.strategies.StrategiesView;

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
                && (sel.getGroup() != null || (sel.getGroup() == null && getSelectedObjects().size() > 1
                        && !(getSelectedObjects().get(0) instanceof VariableListTreeEditPart) && !(getSelectedObjects().get(0) instanceof EnumerationTypeTreeEditPart)));
        if (!b)
            return false;

        if (sel.getStrategiesGroup() != null)
            return sel.getStrategiesGroup().getStrategies().size() > 0;
        else {
            // We are selecting multiple strategies
            if(getSelectedObjects().size() > 1)
            {
                List objects = sel.getAllModel();
                for (Object obj : objects) {
                    if(!(obj instanceof EvaluationStrategy))
                        return false;
                }
                return true; // All selected objects are EvaluationStrategy
            }
            
            return false;
        }
    }

    public void run() {
        UCMNavMultiPageEditor multieditor = (UCMNavMultiPageEditor) getWorkbenchPart();
        int prefTrend = Integer.parseInt(ReportGeneratorPreferences.getGRLEvalStrategyTrend());

        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        if (sel.getGroup() != null || sel.getAllModel().size() > 1) {
            Vector strategies = new Vector();
            GRLspec grlSpec = null;
            
            if(sel.getGroup() != null)
                strategies.addAll(sel.getGroup().getStrategies());
            else // Multiple evaluation strategy are selected
                strategies.addAll(sel.getAllModel());
            
            grlSpec = ((EvaluationStrategy)strategies.get(0)).getGrlspec();
            
            HashMap<Integer, EvaluationStrategy> stratIndexes = new HashMap<Integer, EvaluationStrategy>();
            
            // Sort strategies by name
            Collections.sort(strategies, new Comparator(){
                public int compare(Object arg0, Object arg1) {
                    EvaluationStrategy strat0 = (EvaluationStrategy)arg0;
                    EvaluationStrategy strat1 = (EvaluationStrategy)arg1;
                    
                    return strat0.getName().compareToIgnoreCase(strat1.getName());
                }
            });

            evalTable = BatchEvaluationUtil.calculateAllEvaluations(strategies, grlSpec);
            
            int j = 0;
            for (Iterator i = strategies.iterator(); i.hasNext();) {
                EvaluationStrategy strat = (EvaluationStrategy) i.next();
                
                stratIndexes.put(j++, strat);
            }
            
            if(evalTable.size() > 0) {
                Set<GRLLinkableElement> elements = evalTable.get(strategies.get(0)).keySet();
                
                for (Iterator iter = grlSpec.getActors().iterator(); iter.hasNext();) {
                    Actor actor = (Actor) iter.next();
                    int trend = BatchEvaluationUtil.calculateTrend(stratIndexes, actor, evalTable, prefTrend);
                    
                    MetadataHelper.addMetaData(grlSpec.getUrnspec(), actor, "_trend", new Integer(trend).toString()); //$NON-NLS-1$
                }
                
                for (Iterator i = grlSpec.getIntElements().iterator(); i.hasNext();) {
                    IntentionalElement element = (IntentionalElement) i.next();
                    int trend = BatchEvaluationUtil.calculateTrend(stratIndexes, element, evalTable, prefTrend);
                    
                    MetadataHelper.addMetaData(grlSpec.getUrnspec(), element, "_trend", new Integer(trend).toString()); //$NON-NLS-1$
                }
            }
            
            StrategiesView sv = null;
            
            if( (sv = EvaluationStrategyManager.getInstance(false).getStrategiesView()) != null ) {
                if( !sv.isStrategyView() ) {
                    sv.showPage(StrategiesView.ID_STRATEGY);
                    sv.refreshScenarioIfNeeded();
                }   
            }
        }
    }
}
