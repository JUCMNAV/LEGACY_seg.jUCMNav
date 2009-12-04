package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.transformations.DividePathCommand;
import seg.jUCMNav.model.util.SafePathChecker;
import ucm.map.OrFork;
import ucm.map.PathNode;
import urn.URNspec;

/**
 * Adds an or-fork. Given selection, determines which command to invoke. Might create new small path or might replace elements.
 * 
 * @see SafePathChecker
 * @author jpdaigle, jkealey
 */
public class AddOrForkAction extends URNSelectionAction {
    public static final String ADDORFORK = "seg.jUCMNav.AddOrFork"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddOrForkAction(IWorkbenchPart part) {
        super(part);
        setId(ADDORFORK);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/OrFork16.gif")); //$NON-NLS-1$
    }

    /**
     * @return true if have selected elements that can be forked, and when forked will not cause illegal loops.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.NODECONNECTION:
            return true;
        case SelectionHelper.EMPTYPOINT:
            return true;
        case SelectionHelper.STARTPOINT_EMPTYPOINT:
            return SafePathChecker.isSafeFusion(sel.getStartpoint(), sel.getEmptypoint());
        case SelectionHelper.STARTPOINT_DIRECTIONARROW:
            return SafePathChecker.isSafeFusion(sel.getStartpoint(), sel.getDirectionarrow());
        case SelectionHelper.STARTPOINT_NODECONNECTION:
            return SafePathChecker.isSafeFusion(sel.getStartpoint(), sel.getNodeconnection());
        default:
            return false;
        }
    }

    /**
     * Returns the appropriate fork creation command, given the current selection.
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        Command comm;
        PathNode newOrFork = getNewFork(sel.getUrnspec());

        switch (sel.getSelectionType()) {
        case SelectionHelper.STARTPOINT_EMPTYPOINT:
            comm = new DividePathCommand(sel.getStartpoint(), sel.getEmptypoint(), isOrElement());
            return comm;
        case SelectionHelper.STARTPOINT_DIRECTIONARROW:
            comm = new DividePathCommand(sel.getStartpoint(), sel.getDirectionarrow(), isOrElement());
            return comm;
        case SelectionHelper.STARTPOINT_NODECONNECTION:
            return new DividePathCommand(sel.getStartpoint(), sel.getNodeconnection(), sel.getNodeconnectionMiddle().x, sel.getNodeconnectionMiddle().y,
                    isOrElement());
        case SelectionHelper.EMPTYPOINT:
            comm = new DividePathCommand(newOrFork, sel.getEmptypoint());
            return comm;
        case SelectionHelper.NODECONNECTION:
            comm = new DividePathCommand(newOrFork, sel.getNodeconnection(), sel.getNodeconnectionMiddle().x, sel.getNodeconnectionMiddle().y);
            return comm;
        default:
            return null;
        }
    }

    /**
     * @param urn
     * @return an or-fork
     */
    protected PathNode getNewFork(URNspec urn) {
        return (OrFork) ModelCreationFactory.getNewObject(urn, OrFork.class);
    }

    /**
     * 
     * @return true if or fork/join, false if and fork/join
     */
    protected boolean isOrElement() {
        return true;
    }
}