package seg.jUCMNav.model.commands.delete.internal;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.IDelayedBuildCompoundCommand;
import seg.jUCMNav.model.commands.transformations.CutPathCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.UCMmap;

/**
 * Deletes a PathNode from the model. Uses many commands from the internal package because some deletions are very complex and impact multiple other UCM
 * elements.
 * 
 * @author jkealey
 * 
 */
public class RemovePathNodeCommand extends CompoundCommand implements IDelayedBuildCompoundCommand {

    private PathNode pn;
    private java.util.Map editpartregistry;
    private boolean built;

    private boolean replaceWithEmptyPoint = false;

    /**
     * @param pn
     *            The PathNode to be deleted.
     * @param editpartregistry
     *            The Edit Part Viewer's edit part registry. It is sufficient to pass a map containing only the mapping between NodeConnections and
     *            NodeConnectionEditParts.
     */
    public RemovePathNodeCommand(PathNode pn, java.util.Map editpartregistry) {
        this.pn = pn;
        this.editpartregistry = editpartregistry;
        this.replaceWithEmptyPoint = false;
        setLabel(Messages.getString("RemovePathNodeCommand_RemovePathNode")); //$NON-NLS-1$
    }

    /**
     * @param pn
     *            The PathNode to be deleted.
     * @param editpartregistry
     *            The Edit Part Viewer's edit part registry. It is sufficient to pass a map containing only the mapping between NodeConnections and
     *            NodeConnectionEditParts.
     * @param replaceWithEmptyPoint
     *            if this is a simple node, should it be replaced with an empty point?
     */
    public RemovePathNodeCommand(PathNode pn, java.util.Map editpartregistry, boolean replaceWithEmptyPoint) {
        this.pn = pn;
        this.editpartregistry = editpartregistry;
        this.replaceWithEmptyPoint = replaceWithEmptyPoint;
        setLabel(Messages.getString("RemovePathNodeCommand_RemovePathNode")); //$NON-NLS-1$
    }

    /**
     * Builds a command to delete the passed element.
     * 
     */
    public void build() {
        if (built)
            return;
        built = true;

        UCMmap map = (UCMmap) pn.getDiagram();

        if (pn instanceof StartPoint) {
            add(new DeletePathCommand((StartPoint) pn, editpartregistry));
        } else if (pn instanceof EndPoint) {
            add(new DeletePathCommand((EndPoint) pn, editpartregistry));
        } else {
            // when deleting a regular pathnode, assuming never has to be replaced by empty point
            // stubs must not be replaced with empty points even if have 1in/1out because could cause illegal loops.
            add(new PreDeleteUrnModelElementCommand(pn));
            add(new PrePathManipulationCommand(pn, editpartregistry));
            add(new RemoveURNmodelElementCommand(pn));
            
            if (replaceWithEmptyPoint && pn.getPred().size()>0 && !(pn instanceof Stub) && !isDeletingLastNodeOnAStubLoop())
                add(new PostPathManipulationCommand(pn, (NodeConnection)pn.getPred().get(0)));
            else 
                add(new PostPathManipulationCommand(pn));

            if (isDeletingLastNodeOnAStubLoop()) {
                // deleting last pathnode on a stub's loop.
                NodeConnection link = (NodeConnection) pn.getPred().get(0);
                PathNode target = (PathNode) ((NodeConnection) pn.getSucc().get(0)).getTarget();

                // because of the lack of a better CutPathCommand, have to enter a bunch of empty points.
                EmptyPoint empty = (EmptyPoint) ModelCreationFactory.getNewObject(map.getUrndefinition().getUrnspec(), EmptyPoint.class);
                add(new SplitLinkCommand(map, empty, link, (target.getX() - pn.getX()) / 2 - 25 + pn.getX(), (target.getY() - pn.getY()) / 2 + pn.getY()));

                EmptyPoint middle = (EmptyPoint) ModelCreationFactory.getNewObject(map.getUrndefinition().getUrnspec(), EmptyPoint.class);
                add(new SplitLinkCommand(map, middle, link, (target.getX() - pn.getX()) / 2 + pn.getX(), (target.getY() - pn.getY()) / 2 + pn.getY()));

                empty = (EmptyPoint) ModelCreationFactory.getNewObject(map.getUrndefinition().getUrnspec(), EmptyPoint.class);
                add(new SplitLinkCommand(map, empty, link, (target.getX() - pn.getX()) / 2 + 25 + pn.getX(), (target.getY() - pn.getY()) / 2 + pn.getY()));
                // should be able to break the link.
                add(new CutPathCommand(map, middle));
            }
        }

    }

    private boolean isDeletingLastNodeOnAStubLoop() {
        return pn.getSucc().size() == 1 && pn.getPred().size() == 1
                && ((NodeConnection) pn.getSucc().get(0)).getTarget() == ((NodeConnection) pn.getPred().get(0)).getSource();
    }

    /**
     * Builds commands as late as possible
     */
    public void execute() {
        if (pn.getDiagram() != null) {
            build();
            super.execute();
        }
    }

    /**
     * Execute even if no commands exist.
     */
    public boolean canExecute() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canExecute();
    }

    /**
     * Execute even if no commands exist.
     */
    public boolean canUndo() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canUndo();
    }
}
