package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.AndJoin;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OrJoin;
import ucm.map.PathGraph;
import ucm.map.PathNode;

/**
 * @author jpdaigle
 * 
 *  
 */
public class JoinPathsCommand extends Command implements JUCMNavCommand {

    EmptyPoint _oldEmptyPoint;

    EndPoint _oldEndPoint;

    PathNode _newJoin;

    int _x, _y;

    NodeConnection _ncOldEnd, _ncA, _ncB;

    PathGraph _pg;

    public JoinPathsCommand() {
        super();
    }

    /**
     * Create a command to join 2 paths, pass in an already created non-null Join.
     * 
     * @param emPoint
     *            EmptyPoint on target path to join to.
     * @param endPoint
     *            EndPoint on source path to join.
     * @param newJoin
     *            OrJoin or AndJoin to unify paths.
     */
    public JoinPathsCommand(EmptyPoint emPoint, EndPoint endPoint, PathNode newJoin) {
        _oldEmptyPoint = emPoint;
        _oldEndPoint = endPoint;
        _newJoin = newJoin;

        if (!(_newJoin instanceof OrJoin || _newJoin instanceof AndJoin)) {
            // check that the PathNode passed in is an Or or And Join.
            throw new IllegalArgumentException(Messages.getString("JoinPathsCommand.mustBeOrorAndJoin")); //$NON-NLS-1$
        }

        assert (_newJoin != null);

        this.setLabel(Messages.getString("JoinPathsCommand.joinPaths")); //$NON-NLS-1$
    }

    public boolean canExecute() {
        return super.canExecute();
    }

    public void execute() {
        _x = _oldEmptyPoint.getX();
        _y = _oldEmptyPoint.getY();

        _newJoin.setX(_x);
        _newJoin.setY(_y);

        _pg = _oldEmptyPoint.getPathGraph();
        _ncOldEnd = (NodeConnection) _oldEndPoint.getPred().get(0);
        _ncA = (NodeConnection) _oldEmptyPoint.getPred().get(0);
        _ncB = (NodeConnection) _oldEmptyPoint.getSucc().get(0);

        redo();
    }

    public void redo() {
        // Set the end of the link going to _oldEndPoint to point to the new join.
        _ncOldEnd.setTarget(_newJoin);

        // Move the old empty point's connections to the new join.
        _ncA.setTarget(_newJoin);
        _ncB.setSource(_newJoin);

        // Remove old end point from model
        // Remove old empty point from model
        _pg.getPathNodes().remove(_oldEmptyPoint);
        _pg.getPathNodes().remove(_oldEndPoint);
        _oldEmptyPoint.setCompRef(null);
        _oldEndPoint.setCompRef(null);

        // Add new join PathNode to model
        _newJoin.setCompRef(ParentFinder.findParent(_pg.getMap(), _newJoin.getX(), _newJoin.getY()));
        _pg.getPathNodes().add(_newJoin);
    }

    public void undo() {
        _ncOldEnd.setTarget(_oldEndPoint);

        _ncA.setTarget(_oldEmptyPoint);
        _ncB.setSource(_oldEmptyPoint);

        _pg.getPathNodes().add(_oldEmptyPoint);
        _pg.getPathNodes().add(_oldEndPoint);
        _oldEmptyPoint.setCompRef(ParentFinder.findParent(_pg.getMap(), _oldEmptyPoint.getX(), _oldEmptyPoint
                .getY()));
        _oldEndPoint.setCompRef(ParentFinder.findParent(_pg.getMap(), _oldEndPoint.getX(), _oldEndPoint
                .getY()));

        _newJoin.setCompRef(null);
        _pg.getPathNodes().remove(_newJoin);
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        // TODO Auto-generated method stub

    }

}