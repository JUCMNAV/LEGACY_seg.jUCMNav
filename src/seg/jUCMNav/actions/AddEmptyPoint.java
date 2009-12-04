package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.transformations.ReplaceEmptyPointCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import ucm.map.EmptyPoint;
import ucm.map.PathNode;
import urn.URNspec;

/**
 * Adds an Empty Point to a Node Connection or replaces a Direction Arrow.
 * 
 * @author Ali
 */
public class AddEmptyPoint extends URNSelectionAction {
    public static final String ADDEMPTYPOINT = "seg.jUCMNav.AddEmptyPoint"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddEmptyPoint(IWorkbenchPart part) {
        super(part);
        setId(ADDEMPTYPOINT);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Node16.gif")); //$NON-NLS-1$
    }

    /**
     * True if we select a direction arrow or a node connection.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.DIRECTIONARROW:
            return true;
        case SelectionHelper.NODECONNECTION:
            return true;
        }
        return false;
    }

    /**
     * Returns the appropriate Empty Point creation command, given the current selection.
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        PathNode newEmptyPoint = getNewEmptyPoint(sel.getUrnspec());
        Command comm;

        switch (sel.getSelectionType()) {

        case SelectionHelper.DIRECTIONARROW:
            comm = new ReplaceEmptyPointCommand(sel.getDirectionarrow(), newEmptyPoint);
            return comm;

        case SelectionHelper.NODECONNECTION:
            comm = new SplitLinkCommand(sel.getMap(), newEmptyPoint, sel.getNodeconnection(), sel.getNodeconnectionMiddle().x, sel.getNodeconnectionMiddle().y);
            return comm;

        default:
            return null;
        }

    }

    /**
     * Creates a new empty point.
     * 
     * @param urn
     * @return an Empty Point
     */
    protected PathNode getNewEmptyPoint(URNspec urn) {
        return (EmptyPoint) ModelCreationFactory.getNewObject(urn, EmptyPoint.class);
    }

}