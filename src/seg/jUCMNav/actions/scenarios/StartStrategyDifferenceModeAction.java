package seg.jUCMNav.actions.scenarios;

import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import grl.EvaluationStrategy;

import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.strategies.StrategiesView;

/**
 * This action starts the Strategy Difference Mode
 * 
 * @author amiga
 * 
 */
public class StartStrategyDifferenceModeAction extends URNSelectionAction {

    public static final String START_STRATEGY_DIFF_MODE = "seg.jUCMNav.StartStrategyDifferenceModeAction"; //$NON-NLS-1$

	private EvaluationStrategy strategy1 = null;
	
	public StartStrategyDifferenceModeAction(IWorkbenchPart part) {
		super(part);
		setId(START_STRATEGY_DIFF_MODE);
	}

    protected boolean calculateEnabled()
    {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        
        if( sel.getSelectionType() == SelectionHelper.EVALUATIONSTRATEGY ) {
        	
        	if( EvaluationStrategyManager.getInstance().isDifferenceMode() ) {
        		return false;
        	}

        	if( ((StrategiesView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView( "seg.jUCMNav.views.StrategiesView" )).isStrategyView()) {
        		if( (strategy1 = sel.getStrategy()) != null ) {
        			return true;
        		}
        	}
        }
        
        return false;
    }

    public void run()
    {
    	if( strategy1 != null ) {
    		EvaluationStrategyManager.getInstance().startDifferenceMode( strategy1 );
    	}
    }
}
