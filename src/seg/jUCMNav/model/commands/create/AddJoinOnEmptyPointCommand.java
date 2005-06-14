/*
 * Created on 25 May 2005
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.EmptyPoint;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import urn.URNspec;

/**
 * @author jpdaigle
 *  
 */
public class AddJoinOnEmptyPointCommand extends Command implements JUCMNavCommand {
    // Where to create the Join
    PathGraph _pg;

    EmptyPoint _originEp;

    // New items
    PathNode _newJoin;

    EmptyPoint _newEmptyPoint;

    StartPoint _newStartPoint;

    NodeConnection _newLink1, _newLink2, _ncPred, _ncTarg;

    NodeConnection _originNc;

    PathNode _prevNode, _nextNode;

    int _posX, _posY;

    /**
     *  
     */
    public AddJoinOnEmptyPointCommand() {
        super();
        this.setLabel(Messages.getString("AddJoinOnEmptyPointCommand.addJoin")); //$NON-NLS-1$
    }

    public AddJoinOnEmptyPointCommand(PathNode newJoin, PathGraph pg, EmptyPoint ep) {
        _newJoin = newJoin;
        _pg = pg;
        _originEp = ep;
        this.setLabel(Messages.getString("AddJoinOnEmptyPointCommand.addJoin")); //$NON-NLS-1$
    }

    /**
     * @param label
     */
    public AddJoinOnEmptyPointCommand(String label) {
        super(label);
        // TODO Auto-generated constructor stub
    }

    public boolean canExecute() {
        return super.canExecute();
    }

    public void execute() {
        if (_originEp != null && _pg != null) {
            int x, y;
            x = _originEp.getX();
            y = _originEp.getY();

            assert (_originEp.getPred().size() > 0);
            assert (_originEp.getSucc().size() > 0);

            // Fork -- EmptyPoint -- EndPoint
            _newJoin.setX(x);
            _newJoin.setY(y);

            URNspec urn = _pg.getMap().getUcmspec().getUrnspec();

            _newEmptyPoint = (EmptyPoint) ModelCreationFactory.getNewObject(urn, EmptyPoint.class);
            _newEmptyPoint.setX(x - 25);
            _newEmptyPoint.setY(y - 25);

            _newLink1 = (NodeConnection) ModelCreationFactory.getNewObject(urn, NodeConnection.class);

            _newStartPoint = (StartPoint) ModelCreationFactory.getNewObject(urn, StartPoint.class);
            _newStartPoint.setX(x - 75);
            _newStartPoint.setY(y - 30);

            _newLink2 = (NodeConnection) ModelCreationFactory.getNewObject(urn, NodeConnection.class);

            redo();
        }
    }

    public void redo() {
        // Path modifications
        _ncPred = (NodeConnection) _originEp.getPred().get(0);
        _ncTarg = (NodeConnection) _originEp.getSucc().get(0);
        _ncPred.setTarget(_newJoin);
        _ncTarg.setSource(_newJoin);
        _newLink1.setSource(_newEmptyPoint);
        _newLink1.setTarget(_newJoin);
        _newLink2.setSource(_newStartPoint);
        _newLink2.setTarget(_newEmptyPoint);

        // Add to model
        _pg.getNodeConnections().add(_newLink1);
        _pg.getNodeConnections().add(_newLink2);
        _pg.getPathNodes().add(_newJoin);
        _pg.getPathNodes().add(_newEmptyPoint);
        _pg.getPathNodes().add(_newStartPoint);

        // Delete old node
        _pg.getPathNodes().remove(_originEp);
        _originEp.setCompRef(null);
        //_originEp = null;

        // bind to parent
        _newJoin.setCompRef(ParentFinder.findParent(_pg.getMap(), _newJoin.getX(), _newJoin.getY()));
        _newEmptyPoint.setCompRef(ParentFinder.findParent(_pg.getMap(), _newEmptyPoint.getX(), _newEmptyPoint.getY()));
        _newStartPoint.setCompRef(ParentFinder.findParent(_pg.getMap(), _newStartPoint.getX(), _newStartPoint.getY()));
    }

    public void undo() {
        // Path modifications
        _ncPred.setTarget(_originEp);
        _ncTarg.setSource(_originEp);

        _newLink1.setSource(null);
        _newLink1.setTarget(null);
        _newLink2.setSource(null);
        _newLink2.setTarget(null);

        // Remove from model
        _pg.getNodeConnections().remove(_newLink1);
        _pg.getNodeConnections().remove(_newLink2);
        _pg.getPathNodes().remove(_newJoin);
        _pg.getPathNodes().remove(_newEmptyPoint);
        _pg.getPathNodes().remove(_newStartPoint);

        // Re-add old node
        _pg.getPathNodes().add(_originEp);
        _originEp.setCompRef(ParentFinder.findParent(_pg.getMap(), _originEp.getX(), _originEp.getY()));

        // unbind from parent
        _newJoin.setCompRef(null);
        _newEmptyPoint.setCompRef(null);
        _newStartPoint.setCompRef(null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert (_newEmptyPoint != null) : "pre newEmptyPoint"; //$NON-NLS-1$
        assert (_newJoin != null) : "pre newJoin"; //$NON-NLS-1$
        assert (_newStartPoint != null) : "pre newStartPoint"; //$NON-NLS-1$

        assert (!_pg.getPathNodes().contains(_newEmptyPoint) && !_pg.getPathNodes().contains(_newJoin) && !_pg.getPathNodes().contains(
                _newStartPoint)) : "pre PathGraph doesn't contain new nodes"; //$NON-NLS-1$

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert (_newEmptyPoint != null) : "pre newEmptyPoint"; //$NON-NLS-1$
        assert (_newJoin != null) : "pre newJoin"; //$NON-NLS-1$
        assert (_newStartPoint != null) : "pre newStartPoint"; //$NON-NLS-1$

        assert (_pg.getPathNodes().contains(_newEmptyPoint) && _pg.getPathNodes().contains(_newJoin) && _pg.getPathNodes().contains(
                _newStartPoint)) : "pre PathGraph doesn't contain new nodes"; //$NON-NLS-1$

    }

}