package seg.jUCMNav.actions.scenarios;

import grl.Contribution;
import grl.ContributionChange;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.wizards.strategies.EditContributionRangeWizard;
import urn.URNspec;

/**
 * Opens the contribution range editor. 
 * 
 * @author editor
 */
public class EditContributionRangeAction extends URNSelectionAction {

    public static final String EDITCONTRIBUTIONRANGEACTION = "seg.jUCMNav.EditContributionRangeAction"; //$NON-NLS-1$

    private EObject obj;
    private URNspec urn;

    /**
     * Opens the evaluation range editor.
     * 
     * @param part
     *            the UCMNavMultiPageEditor
     */
    public EditContributionRangeAction(IWorkbenchPart part) {
        super(part);
        setId(EDITCONTRIBUTIONRANGEACTION);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/ContributionRange16.gif")); //$NON-NLS-1$
    }

    /**
     * True if we've selected something with an evaluation.
     */
    protected boolean calculateEnabled() {
        return getSelectedContributionChange() != null;
    }

    private ContributionChange getSelectedContributionChange() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        Contribution contrib = sel.getContribution();
        obj = null;
        urn = sel.getUrnspec();
        if (urn!=null && contrib!=null) {
            ContributionChange c  = EvaluationStrategyManager.getInstance().findApplicableContributionChange(contrib, true);
            if (c!=null) {
                obj = c;
                return c;
            }

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
        EditContributionRangeWizard wizard = new EditContributionRangeWizard(urn);

        wizard.init(PlatformUI.getWorkbench(), getSelectedContributionChange());
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();

    }

}
