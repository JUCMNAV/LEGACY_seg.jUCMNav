package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.delete.internal.DeletePathCommand;
import seg.jUCMNav.model.commands.delete.internal.PostPathManipulationCommand;
import seg.jUCMNav.model.commands.delete.internal.PreDeleteUcmModelElementCommand;
import seg.jUCMNav.model.commands.delete.internal.PrePathManipulationCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveUCMmodelElementCommand;
import seg.jUCMNav.model.commands.transformations.CutPathCommand;
import seg.jUCMNav.model.commands.transformations.SplitLinkCommand;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.UCMmap;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.StartPoint;

/**
 * Deletes a PathNode from the model. Uses many commands from the internal package because some deletions are very complex and impact multiple other UCM
 * elements.
 * 
 * @author jkealey
 * 
 */
public class DeletePathNodeCommand extends CompoundCommand {

    private PathNode pn;
    private java.util.Map editpartregistry;

    /**
     * @param pn
     *            The PathNode to be deleted.
     * @param editpartregistry
     *            The Edit Part Viewer's edit part registry. It is sufficient to pass a map containing only the mapping between NodeConnections and
     *            NodeConnectionEditParts.
     */
    public DeletePathNodeCommand(PathNode pn, java.util.Map editpartregistry) {
        this.pn = pn;
        this.editpartregistry = editpartregistry;

    }

    /**
     * Builds a command to delete the passed element.
     * 
     */
    private void build() {
        UCMmap map = (UCMmap)pn.getSpecDiagram();

        if (pn instanceof StartPoint) {
            add(new DeletePathCommand((StartPoint) pn, editpartregistry));
        } else if (pn instanceof EndPoint) {
            add(new DeletePathCommand((EndPoint) pn, editpartregistry));
        } else {
            // when deleting a regular pathnode, assuming never has to be replaced by empty point
            // stubs must not be replaced with empty points even if have 1in/1out because could cause illegal loops.
            add(new PreDeleteUcmModelElementCommand(pn));
            add(new PrePathManipulationCommand(pn, editpartregistry));
            add(new RemoveUCMmodelElementCommand(pn));
            add(new PostPathManipulationCommand(pn));

            if (pn.getSucc().size() == 1 && pn.getPred().size() == 1
                    && ((NodeConnection) pn.getSucc().get(0)).getTarget() == ((NodeConnection) pn.getPred().get(0)).getSource()) {
                // deleting last pathnode on a stub's loop.
                NodeConnection link = (NodeConnection) pn.getPred().get(0);
                PathNode target = (PathNode)((NodeConnection) pn.getSucc().get(0)).getTarget();

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

    /**
     * Builds commands as late as possible
     */
    public void execute() {
        if (pn.getSpecDiagram() != null) {
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
