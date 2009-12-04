package seg.jUCMNav.actions.performance;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.views.wizards.performance.ManageResources;
import ucm.map.ComponentRef;
import ucm.map.RespRef;
import urn.URNspec;
import urncore.Component;
import urncore.Responsibility;

/**
 * Opens the resource editor
 * 
 * @author jack
 */
public class ManageResourcesAction extends URNSelectionAction {

    public static final String MANAGERESOURCESACTION = "seg.jUCMNav.actions.performance.ManageResourcesAction"; //$NON-NLS-1$

    private EObject obj;
    private URNspec urn;

    /**
     * Opens the resource editor.
     * 
     * @param part
     *            the UCMNavMultiPageEditor
     */
    public ManageResourcesAction(IWorkbenchPart part) {
        super(part);
        setId(MANAGERESOURCESACTION);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Resource.gif")); //$NON-NLS-1$
    }

    /**
     * True if we've selected something with resource.
     */
    protected boolean calculateEnabled() {
        boolean enable = false;

        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        obj = sel.getURNmodelElement();

        if (obj instanceof ComponentRef) {
            this.urn = ((Component) ((ComponentRef) obj).getContDef()).getUrndefinition().getUrnspec();
            enable = true;
        }
        if (obj instanceof RespRef) {
            this.urn = ((Responsibility) ((RespRef) obj).getRespDef()).getUrndefinition().getUrnspec();
            enable = true;
        }

        return enable;
    }

    /**
     * Opens the resource editor.
     * 
     * @see seg.jUCMNav.views.wizards.performance.ManageResources
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        ManageResources wizard = new ManageResources(urn);
        wizard.init(PlatformUI.getWorkbench(), obj);
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();

    }

}
