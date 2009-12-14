package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.transformations.ChangeStubTypeCommand;
import ucm.map.Stub;

/**
 * Changes the stub type.
 * 
 * @author jkealey
 */
public class ChangeStubTypeAction extends URNSelectionAction {
    public static final String CHANGE_STUB_TYPE = "seg.jUCMNav.CHANGESTUBTYPE"; //$NON-NLS-1$

    public static final String[] STUB_TYPES = new String[] { Messages.getString("ChangeStubTypeAction.Stub"), //$NON-NLS-1$
            Messages.getString("ChangeStubTypeAction.DynamicStub"), Messages.getString("ChangeStubTypeAction.PointcutStub"),  //$NON-NLS-1$ //$NON-NLS-2$
            "Synchronizing Stub", "Blocking Stub" };

    protected int stubType;
    // 0: Static
    // 1: Dynamic
    // 2: Pointcut
    // 3: Synchronizing
    // 4: Blocking Stub

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
        else if (stubType == 3)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/SyncStub16.gif")); //$NON-NLS-1$
        else if (stubType == 4)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/BlockStub16.gif")); //$NON-NLS-1$
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
            Stub s = sel.getStub();
            if (stubType == 0 && !s.isDynamic() && !s.isPointcut() && !s.isSynchronization() && !s.isBlocking()) // already static
                return false;
            else if (stubType == 1 && s.isDynamic() && !s.isPointcut() && !s.isSynchronization() && !s.isBlocking()) // already dynamic
                return false;
            else if (stubType == 2 && s.isDynamic() && s.isPointcut() && !s.isSynchronization() && !s.isBlocking()) // already pointcut
                return false;
            else if (stubType == 3 && s.isDynamic() && !s.isPointcut() && s.isSynchronization() && !s.isBlocking()) // already synchro
                return false;
            else if(stubType == 4 && s.isDynamic() && !s.isPointcut() && s.isSynchronization() && s.isBlocking()) // already blocking
                return false;
            else if (stubType == 0 && s.getBindings().size() > 1) // not when have multiple plugins
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
            try {
            comm = new ChangeStubTypeCommand(sel.getStub(), stubType != 0, stubType == 2, stubType == 3 || stubType == 4, stubType == 4);
            return comm;
            }
            catch(Exception ex) {
                
            }
        default:
            return null;
        }

    }

}