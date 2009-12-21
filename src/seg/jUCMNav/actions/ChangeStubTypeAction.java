package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.transformations.ChangeStubTypeCommand;
import seg.jUCMNav.model.util.StubHelper;
import ucm.map.AspectKind;
import ucm.map.PointcutKind;
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
            "Pointcut Replacement Stub", "Synchronizing Stub", "Blocking Stub", "Aspect Marker", "Entrance Aspect Marker", "Exit Aspect Marker", "Conditional Aspect Marker" };

    protected int stubType;
    // 0: Static
    // 1: Dynamic
    // 2: Pointcut Regular
    // 3: Pointcut Replacement
    // 4: Synchronizing
    // 5: Blocking Stub
    // 6: Aspect Marker
    // 7: Aspect Marker Entrance
    // 8: Aspect Marker Exit
    // 9: Aspect Marker Conditional

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
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/PointcutRepStub16.gif")); //$NON-NLS-1$
        else if (stubType == 4)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/SyncStub16.gif")); //$NON-NLS-1$
        else if (stubType == 5)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/BlockStub16.gif")); //$NON-NLS-1$
        else if (stubType == 6)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/aspectMarker16.gif")); //$NON-NLS-1$
        else if (stubType == 7)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/aspectMarkerEntrance16.gif")); //$NON-NLS-1$
        else if (stubType == 8)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/aspectMarkerExit16.gif")); //$NON-NLS-1$
        else if (stubType == 9)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/aspectMarkerCond16.gif")); //$NON-NLS-1$
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
            
            int kind = StubHelper.getStubKind(s);
            if (stubType == kind) // already same stub type
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
                PointcutKind p = PointcutKind.NONE_LITERAL;
                if(stubType == 2)
                    p = PointcutKind.REGULAR_LITERAL;
                else if( stubType == 3)
                    p = PointcutKind.REPLACEMENT_LITERAL;

                AspectKind a = AspectKind.NONE_LITERAL;
                if(stubType == 6)
                    a = AspectKind.REGULAR_LITERAL;
                if(stubType == 7)
                    a = AspectKind.ENTRANCE_LITERAL;
                else if (stubType == 8)
                    a = AspectKind.EXIT_LITERAL;
                else if (stubType == 9)
                    a = AspectKind.CONDITIONAL_LITERAL;
                
            comm = new ChangeStubTypeCommand(sel.getStub(), stubType != 0, p, stubType == 4 || stubType == 5, stubType == 5, a);
            return comm;
            }
            catch(Exception ex) {
                
            }
        default:
            return null;
        }

    }

}