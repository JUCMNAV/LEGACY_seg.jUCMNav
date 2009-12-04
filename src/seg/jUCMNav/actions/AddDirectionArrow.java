package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.transformations.ReplaceEmptyPointCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import ucm.map.DirectionArrow;
import ucm.map.PathNode;
import urn.URNspec;

/**
 * Adds a Direction arrow to Node Connection or replace an empty point by a direction arrow
 * 
 * @author Ali
 */
public class AddDirectionArrow extends URNSelectionAction {
    public static final String ADDDIRECTIONARROW = "seg.jUCMNav.CovertToArrow"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddDirectionArrow(IWorkbenchPart part) {
        super(part);
        setId(ADDDIRECTIONARROW);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/DirectionArrow16.gif")); //$NON-NLS-1$
    }

    /**
     * True if we've selected an empty point or a node connection.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.EMPTYPOINT:
            return true;
        case SelectionHelper.NODECONNECTION:
            return true;
        }
        return false;
    }

    /**
     * Returns the appropriate direction arrow creation command, given the current selection.
     */
    protected Command getCommand() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        PathNode newDirectionArrow = getNewDirectionArrow(sel.getUrnspec());
        Command comm;

        switch (sel.getSelectionType()) {

        case SelectionHelper.EMPTYPOINT:
            comm = new ReplaceEmptyPointCommand(sel.getEmptypoint(), newDirectionArrow);
            return comm;

        case SelectionHelper.NODECONNECTION:
            comm = new SplitLinkCommand(sel.getMap(), newDirectionArrow, sel.getNodeconnection(), sel.getNodeconnectionMiddle().x, sel
                    .getNodeconnectionMiddle().y);
            return comm;

        default:
            return null;
        }

    }

    /**
     * Creates a new direction arrow.
     * 
     * @param urn
     *            the URNspec in which to create it
     * @return a direction arrow
     */
    protected PathNode getNewDirectionArrow(URNspec urn) {
        return (DirectionArrow) ModelCreationFactory.getNewObject(urn, DirectionArrow.class);
    }

}