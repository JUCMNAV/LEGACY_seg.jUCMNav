package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.transformations.ChangeStubTypeCommand;
import seg.jUCMNav.model.util.StubHelper;
import seg.jUCMNav.model.util.StubKind;
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
            Messages.getString("ChangeStubTypeAction.PointcutReplacementStub"), Messages.getString("ChangeStubTypeAction.SynchronizingStub"), Messages.getString("ChangeStubTypeAction.BlockingStub"), Messages.getString("ChangeStubTypeAction.AspectMarker"), Messages.getString("ChangeStubTypeAction.EntranceAspectMarker"), Messages.getString("ChangeStubTypeAction.ExitAspectMarker"), Messages.getString("ChangeStubTypeAction.ConditionalAspectMarker") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$

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
        
        setImageDescriptor(getImageDescriptorForStubType(stubType));

        setText(Messages.getString("ChangeStubTypeAction.ConvertTo") + STUB_TYPES[stubType]); //$NON-NLS-1$
    }
    
    public static ImageDescriptor getImageDescriptorForStubType(int stubType) {
        if (stubType == StubKind.DYNAMIC)
            return JUCMNavPlugin.getImageDescriptor("icons/DynStub16.gif"); //$NON-NLS-1$
        else if (stubType == StubKind.POINTCUT)
            return JUCMNavPlugin.getImageDescriptor("icons/PointcutStub16.gif"); //$NON-NLS-1$
        else if (stubType == StubKind.POINTCUT_REPLACEMENT)
            return JUCMNavPlugin.getImageDescriptor("icons/PointcutRepStub16.gif"); //$NON-NLS-1$
        else if (stubType == StubKind.SYNCHRONIZING)
            return JUCMNavPlugin.getImageDescriptor("icons/SyncStub16.gif"); //$NON-NLS-1$
        else if (stubType == StubKind.SYNCHRONIZING_BLOCKING)
            return JUCMNavPlugin.getImageDescriptor("icons/BlockStub16.gif"); //$NON-NLS-1$
        else if (stubType == StubKind.ASPECT_MARKER)
            return JUCMNavPlugin.getImageDescriptor("icons/aspectMarker16.gif"); //$NON-NLS-1$
        else if (stubType == StubKind.ASPECT_ENTRANCE)
            return JUCMNavPlugin.getImageDescriptor("icons/aspectMarkerEntrance16.gif"); //$NON-NLS-1$
        else if (stubType == StubKind.ASPECT_EXIT)
            return JUCMNavPlugin.getImageDescriptor("icons/aspectMarkerExit16.gif"); //$NON-NLS-1$
        else if (stubType == StubKind.ASPECT_CONDITIONNAL)
            return JUCMNavPlugin.getImageDescriptor("icons/aspectMarkerCond16.gif"); //$NON-NLS-1$
        else
            return JUCMNavPlugin.getImageDescriptor("icons/Stub16.gif"); //$NON-NLS-1$
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
            else if (stubType == StubKind.STATIC && s.getBindings().size() > 1) // not when have multiple plugins
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
                if(stubType == StubKind.POINTCUT)
                    p = PointcutKind.REGULAR_LITERAL;
                else if( stubType == StubKind.POINTCUT_REPLACEMENT)
                    p = PointcutKind.REPLACEMENT_LITERAL;

                AspectKind a = AspectKind.NONE_LITERAL;
                if(stubType == StubKind.ASPECT_MARKER)
                    a = AspectKind.REGULAR_LITERAL;
                if(stubType == StubKind.ASPECT_ENTRANCE)
                    a = AspectKind.ENTRANCE_LITERAL;
                else if (stubType == StubKind.ASPECT_EXIT)
                    a = AspectKind.EXIT_LITERAL;
                else if (stubType == StubKind.ASPECT_CONDITIONNAL)
                    a = AspectKind.CONDITIONAL_LITERAL;
                
            comm = new ChangeStubTypeCommand(   sel.getStub(),
                                                stubType != StubKind.STATIC,
                                                p,
                                                stubType == StubKind.SYNCHRONIZING || stubType == StubKind.SYNCHRONIZING_BLOCKING,
                                                stubType == StubKind.SYNCHRONIZING_BLOCKING,
                                                a);
            return comm;
            }
            catch(Exception ex) {
                
            }
        default:
            return null;
        }
    }
}