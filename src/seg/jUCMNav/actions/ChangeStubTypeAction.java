package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.transformations.ChangeStubTypeCommand;

/**
 * Changes the stub type.
 * 
 * @author jkealey
 */
public class ChangeStubTypeAction extends URNSelectionAction {
    public static final String CHANGE_STUB_TYPE = "seg.jUCMNav.CHANGESTUBTYPE"; //$NON-NLS-1$

    public static final String[] STUB_TYPES = new String[] { Messages.getString("ChangeStubTypeAction.Stub"), //$NON-NLS-1$
            Messages.getString("ChangeStubTypeAction.DynamicStub"), Messages.getString("ChangeStubTypeAction.PointcutStub") }; //$NON-NLS-1$ //$NON-NLS-2$

    protected int stubType;

    /**
     * @param part
     */
    public ChangeStubTypeAction(IWorkbenchPart part, int stubType) {
        super(part);
        setId(generateId(stubType));
        this.stubType = stubType;

        if (stubType == 1)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/DynStub16.gif")); //$NON-NLS-1$
        else if (stubType == 2)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/PointcutStub16.gif")); //$NON-NLS-1$
        else
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Stub16.gif")); //$NON-NLS-1$

        setText(Messages.getString("ChangeStubTypeAction.ConvertTo") + STUB_TYPES[stubType]); //$NON-NLS-1$
    }

    public static String generateId(int stubType) {
        return CHANGE_STUB_TYPE + stubType;
    }

    /**
     * True if we've selected an empty point or a node connection.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.STUB:
            if (sel.getStub().isPointcut() && stubType == 2) // already one
                return false;
            else if (sel.getStub().isDynamic() && !sel.getStub().isPointcut() && stubType == 1) // already one
                return false;
            else if (!sel.getStub().isDynamic() && !sel.getStub().isPointcut() && stubType == 0) // already one
                return false;
            else if (stubType == 0 && sel.getStub().getBindings().size() > 1) // not when have multiple plugins
                return false;
            else
                return true;
        }
        return false;
    }

    /**
     * Returns the appropriate direction arrow creation command, given the current selection.
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        Command comm;

        switch (sel.getSelectionType()) {

        case SelectionHelper.STUB:
            comm = new ChangeStubTypeCommand(sel.getStub(), stubType != 0, stubType == 2);
            return comm;
        default:
            return null;
        }

    }

}