package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddForkOrJoinCompoundCommand;
import seg.jUCMNav.model.commands.transformations.DividePathOnNodeConnectionCompoundCommand;
import seg.jUCMNav.model.commands.transformations.ForkPathsCommand;
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
public class AddOrForkAction extends UCMSelectionAction {
    public static final String ADDORFORK = "seg.jUCMNav.AddOrFork"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddOrForkAction(IWorkbenchPart part) {
        super(part);
        setId(ADDORFORK);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/OrFork16.gif")); //$NON-NLS-1$
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
        PathNode newOrFork = getNewFork(sel.getUrnspec());
        Command comm;

        switch (sel.getSelectionType()) {
        case SelectionHelper.STARTPOINT_EMPTYPOINT:
            comm = new ForkPathsCommand(sel.getEmptypoint(), sel.getStartpoint(), newOrFork);
            return comm;
        case SelectionHelper.STARTPOINT_NODECONNECTION:
            return new DividePathOnNodeConnectionCompoundCommand(sel.getStartpoint(), sel.getNodeconnection(), sel.getNodeconnectionMiddle().x, sel
                    .getNodeconnectionMiddle().y, true);
        case SelectionHelper.EMPTYPOINT:
            comm = new AddForkOrJoinCompoundCommand(newOrFork, sel.getPathgraph(), sel.getEmptypoint());
            return comm;
        case SelectionHelper.NODECONNECTION:
            comm = new AddForkOrJoinCompoundCommand(newOrFork, sel.getPathgraph(), sel.getNodeconnection(), sel.getNodeconnectionMiddle().x, sel
                    .getNodeconnectionMiddle().y);
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

}