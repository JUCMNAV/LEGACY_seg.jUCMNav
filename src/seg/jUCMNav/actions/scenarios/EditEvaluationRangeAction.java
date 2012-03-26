package seg.jUCMNav.actions.scenarios;

import grl.Evaluation;
import grl.IntentionalElement;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.wizards.strategies.EditEvaluationRangeWizard;
import urn.URNspec;

/**
 * Opens the evaluation range editor. 
 * 
 * @author editor
 */
public class EditEvaluationRangeAction extends URNSelectionAction {

    public static final String EDITEVALUATIONRANGEACTION = "seg.jUCMNav.EditEvaluationRangeAction"; //$NON-NLS-1$

    private EObject obj;
    private URNspec urn;

    /**
     * Opens the evaluation range editor.
     * 
     * @param part
     *            the UCMNavMultiPageEditor
     */
    public EditEvaluationRangeAction(IWorkbenchPart part) {
        super(part);
        setId(EDITEVALUATIONRANGEACTION);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Metadata.gif")); //$NON-NLS-1$
    }

    /**
     * True if we've selected something with an evaluation.
     */
    protected boolean calculateEnabled() {
        return getSelectedEvaluation() != null;
    }

    private Evaluation getSelectedEvaluation() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        IntentionalElement ie = sel.getIntentionalElement();
        obj = ie;
        urn = sel.getUrnspec();
        if (urn!=null && ie!=null) {
            Evaluation ev  = EvaluationStrategyManager.getInstance().getEvaluationObject(ie);
            if (ev!=null && ev.getStrategies()!=null)
                return ev;

        }
        return null;
    }

    /**
     * Opens the evaluation range editor.
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        EditEvaluationRangeWizard wizard = new EditEvaluationRangeWizard(urn);

        wizard.init(PlatformUI.getWorkbench(), getSelectedEvaluation());
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();

    }

}
