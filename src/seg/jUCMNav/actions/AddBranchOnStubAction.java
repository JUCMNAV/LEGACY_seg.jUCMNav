package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editparts.StubEditPart;
import seg.jUCMNav.model.commands.create.AddBranchOnStubCommand;
import ucm.map.Stub;

/**
 * Action to add an additional branch to an existing stub.
 * 
 * @author jkealey
 * 
 */
public class AddBranchOnStubAction extends URNSelectionAction {

    public static final String ADDBRANCH = "seg.jUCMNav.AddInBranchOnStub"; //$NON-NLS-1$
    public static final String ADDBRANCH2 = "seg.jUCMNav.AddOutBranchOnStub"; //$NON-NLS-1$
    private boolean isInBranch;

    /**
     * @param part
     */
    public AddBranchOnStubAction(IWorkbenchPart part, boolean isInBranch) {
        super(part);
        this.isInBranch = isInBranch;
        if (isInBranch)
            setId(ADDBRANCH);
        else
            setId(ADDBRANCH2);
    }

    /**
     * Returns true if the selection is a fork/join. Also sets the image dynamically.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());

        switch (sel.getSelectionType()) {
        case SelectionHelper.STUB:
            if (isInBranch)
                setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/inBinding16.gif")); //$NON-NLS-1$
            else
                setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/outBinding16.gif")); //$NON-NLS-1$
            break;
        default:
            return false;
        }
        return true;
    }

    protected Command getCommand() {
        return new AddBranchOnStubCommand((Stub) ((StubEditPart) getSelectedObjects().get(0)).getModel(), isInBranch);
    }
}