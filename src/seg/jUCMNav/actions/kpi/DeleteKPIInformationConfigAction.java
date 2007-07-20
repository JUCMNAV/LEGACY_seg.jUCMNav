package seg.jUCMNav.actions.kpi;

import grl.EvaluationStrategy;
import grl.kpimodel.KPIInformationConfig;
import grl.kpimodel.KPIInformationElementRef;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.model.commands.delete.DeleteKPIInformationConfigCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;

/**
 * Deletes a user KPIInformationConfig.
 * 
 * @author pchen
 * 
 */
public class DeleteKPIInformationConfigAction extends URNSelectionAction {

    public static final String DELETEKPIINFORMATIONCONFIG = Messages.getString("DeleteKPIInformationConfigAction.DeleteUserEvaluation"); //$NON-NLS-1$
    private KPIInformationConfig kpiInformationConfig;

    /**
     * @param part
     */
    public DeleteKPIInformationConfigAction(IWorkbenchPart part) {
        super(part);
        setId(DELETEKPIINFORMATIONCONFIG);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/StrategyView16.gif")); //$NON-NLS-1$
    }

    /**
     * We need an evaluation set.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        if (sel.getSelectionType() == SelectionHelper.KPIINFORMATIONELEMENTREF) {
            KPIInformationElementRef selection = sel.getKPIInformationElementref();
            EvaluationStrategy strategy = EvaluationStrategyManager.getInstance().getEvaluationStrategy();
            if (strategy != null) {
                kpiInformationConfig = EvaluationStrategyManager.getInstance().getKPIInformationConfigObject(selection.getDef());
                if (kpiInformationConfig.getStrategies() == strategy) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @return Builds the command to delete the evaluation
     */
    protected Command getCommand() {
        return new DeleteKPIInformationConfigCommand(kpiInformationConfig);
    }
}
