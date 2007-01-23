package seg.jUCMNav.actions.metadata;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.views.wizards.metadata.MetadataEditor;

/**
 * Opens the metadata editor
 * 
 * @author pchen
 */
public class EditMetadataAction extends URNSelectionAction {

    public static final String EDITMETADATAACTION = "seg.jUCMNav.EditMetadataAction"; //$NON-NLS-1$

    private EObject obj;

    /**
     * Opens the Metadata editor.
     * 
     * @param part
     *            the UCMNavMultiPageEditor
     */
    public EditMetadataAction(IWorkbenchPart part) {
        super(part);
        setId(EDITMETADATAACTION);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Metadata.gif")); //$NON-NLS-1$
    }

    /**
     * True if we've selected something with metadata.
     */
    protected boolean calculateEnabled() {
        boolean enable = false;

        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        obj = sel.getURNmodelElement();
        if (obj != null) {
            enable = true;
        }

        return enable;
    }

    /**
     * Opens the Metadata editor.
     * 
     * @see seg.jUCMNav.views.wizards.metadata.MetadataEditor
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        MetadataEditor wizard = new MetadataEditor();

        wizard.init(PlatformUI.getWorkbench(), null, obj);
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();

    }

}
