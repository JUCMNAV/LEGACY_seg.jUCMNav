package seg.jUCMNav.actions.scenarios;

import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.strategies.EvaluationStrategyManager;

public class StopStrategyDifferenceModeAction extends URNSelectionAction {

    public static final String STOP_STRATEGY_DIFF_MODE = "seg.jUCMNav.StopStrategyDifferenceModeAction"; //$NON-NLS-1$

    public StopStrategyDifferenceModeAction(IWorkbenchPart part) {
		super(part);
		setId(STOP_STRATEGY_DIFF_MODE);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/StrategyDifferenceMode.gif")); //$NON-NLS-1$
	}

	protected boolean calculateEnabled()
    {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        
        if( sel.getSelectionType() == SelectionHelper.EVALUATIONSTRATEGY ) {
        	if( EvaluationStrategyManager.getInstance().isDifferenceMode() ) {
        		return true;
        	}
        }
        
        return false;
    }

    public void run()
    {
    	EvaluationStrategyManager.getInstance().stopDifferenceMode();
    }

}
