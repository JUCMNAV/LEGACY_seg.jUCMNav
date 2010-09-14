package seg.jUCMNav.actions;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.views.stub.StubBindingsDialog;
import ucm.map.AspectKind;
import ucm.map.Stub;

/**
 * Opens the stub plugin dialog.
 * 
 * @author Etienne Tremblay, gunterm
 */
public class EditStubPluginsAction extends URNSelectionAction {

    public static final String EDITSTUBPLUGINS = "seg.jUCMNav.EditStubPluginsAction"; //$NON-NLS-1$

    private Stub stub;

    /**
     * @param part
     */
    public EditStubPluginsAction(IWorkbenchPart part) {
        super(part);
        setId(EDITSTUBPLUGINS);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Binding16.gif")); //$NON-NLS-1$
    }

    /**
     * True if we've selected a stub that is not an aspect marker.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.STUB:
            stub = sel.getStub();
            if (stub.getAspect() == null || stub.getAspect().equals(AspectKind.NONE_LITERAL))
            	return true;
        }
        return false;
    }

    /**
     * Open the {@link StubBindingsDialog}
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        StubBindingsDialog d = new StubBindingsDialog(shell, getCommandStack());
        d.open(stub);
    }

}