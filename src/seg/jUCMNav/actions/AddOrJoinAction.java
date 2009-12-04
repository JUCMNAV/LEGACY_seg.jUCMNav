package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.transformations.DividePathCommand;
import seg.jUCMNav.model.util.SafePathChecker;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import urn.URNspec;

/**
 * Adds an or-join. Given selection, determines which command to invoke. Might create new small path or might replace elements.
 * 
 * @author jpdaigle, jkealey
 * @see SafePathChecker
 */
public class AddOrJoinAction extends URNSelectionAction {
    public static final String ADDORJOIN = "seg.jUCMNav.AddOrJoin"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddOrJoinAction(IWorkbenchPart part) {
        super(part);
        setId(ADDORJOIN);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/OrJoin16.gif")); //$NON-NLS-1$
    }

    /**
     * @return true if have selected elements that can be joined, and when joined will not cause illegal loops.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.ENDPOINT_EMPTYPOINT:
            return SafePathChecker.isSafeFusion(sel.getEndpoint(), sel.getEmptypoint());
        case SelectionHelper.ENDPOINT_DIRECTIONARROW:
            return SafePathChecker.isSafeFusion(sel.getEndpoint(), sel.getDirectionarrow());
        case SelectionHelper.NODECONNECTION:
            return true;
        case SelectionHelper.EMPTYPOINT:
            return true;
        case SelectionHelper.ENDPOINT_NODECONNECTION:
            return SafePathChecker.isSafeFusion(sel.getEndpoint(), sel.getNodeconnection());
        default:
            return false;
        }
    }

    /**
     * Returns the appropriate join creation command, given the current selection.
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        PathNode newOrJoin = getNewJoin(sel.getUrnspec());
        Command comm;

        switch (sel.getSelectionType()) {
        case SelectionHelper.ENDPOINT_EMPTYPOINT:
            comm = new DividePathCommand(sel.getEndpoint(), sel.getEmptypoint(), isOrElement());
            return comm;
        case SelectionHelper.ENDPOINT_DIRECTIONARROW:
            comm = new DividePathCommand(sel.getEndpoint(), sel.getDirectionarrow(), isOrElement());
            return comm;
        case SelectionHelper.ENDPOINT_NODECONNECTION:
            return new DividePathCommand(sel.getEndpoint(), sel.getNodeconnection(), sel.getNodeconnectionMiddle().x, sel.getNodeconnectionMiddle().y,
                    isOrElement());
        case SelectionHelper.EMPTYPOINT:
            comm = new DividePathCommand(newOrJoin, sel.getEmptypoint());
            return comm;
        case SelectionHelper.NODECONNECTION:
            comm = new DividePathCommand(newOrJoin, sel.getNodeconnection(), sel.getNodeconnectionMiddle().x, sel.getNodeconnectionMiddle().y);
            return comm;

        default:
            return null;
        }

    }

    /**
     * @param urn
     * @return an or-join
     */
    protected PathNode getNewJoin(URNspec urn) {
        return (OrJoin) ModelCreationFactory.getNewObject(urn, OrJoin.class);
    }

    /**
     * 
     * @return true if or fork/join, false if and fork/join
     */
    protected boolean isOrElement() {
        return true;
    }
}