package seg.jUCMNav.model.commands.delete.internal;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import ucm.map.EmptyPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.UCMmap;

/**
 * Intented to be used after the deletion of a PathNode which must be downgraded to an EmptyPoint. Will insert a new EmptyPoint on such a connection using
 * SplitLinkCommand.
 * 
 * PathNode must still be in map during construction, but not necessarily during execution.
 * 
 * @author jkealey
 * 
 */
public class PostPathManipulationCommand extends CompoundCommand {
    private EmptyPoint empty;

    public PostPathManipulationCommand(PathNode pn) {
        // Nothing to do.
        // System.out.println("Deleted PathNode" + pn);
    }

    /**
     * 
     * @param pn
     *            the PathNode that is to be deleted
     * @param link
     *            where to insert a new EmptyPoint
     */
    public PostPathManipulationCommand(PathNode pn, NodeConnection link) {
        if (pn.getDiagram() != null && pn.getDiagram().getUrndefinition() != null) {
            empty = (EmptyPoint) ModelCreationFactory.getNewObject(pn.getDiagram().getUrndefinition().getUrnspec(), EmptyPoint.class);
            add(new SplitLinkCommand((UCMmap) pn.getDiagram(), empty, link, pn.getX(), pn.getY()));
        }
    }

    /**
     * Return true even if no commands.
     */
    public boolean canExecute() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canExecute();
    }

    /**
     * Returns true even if no commands exist.
     */
    public boolean canUndo() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canUndo();
    }

    /**
     * 
     * @return The empty point that was created.
     */
    public EmptyPoint getEmpty() {
        return empty;
    }
}
