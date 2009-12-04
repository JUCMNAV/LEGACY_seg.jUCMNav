package seg.jUCMNav.editparts;

import org.eclipse.gef.Request;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.views.wizards.scenarios.CodeEditor;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.UCMmap;

/**
 * Editpart for Responsibilities. Adds double-click behaviour.
 * 
 * @author jkealey
 */
public class RespRefEditPart extends PathNodeEditPart {
    private CodeEditor wizard;

    /**
     * Creates a respref editpart.
     */
    public RespRefEditPart(PathNode model, UCMmap diagram) {
        super(model, diagram);

    }

    /**
     * What should be done when the stub is double clicked.
     * 
     * @see org.eclipse.gef.EditPart#performRequest(org.eclipse.gef.Request)
     */
    public void performRequest(Request req) {
        if (req.getType() == REQ_OPEN) {
            RespRef respRef = (RespRef) getModel();
            Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
            wizard = new CodeEditor();

            wizard.init(PlatformUI.getWorkbench(), null, respRef);
            WizardDialog dialog = new WizardDialog(shell, wizard);
            dialog.open();
        }

        super.performRequest(req);
    }
}