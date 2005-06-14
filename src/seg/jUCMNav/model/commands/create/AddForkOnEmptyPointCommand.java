package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import urn.URNspec;
import urncore.Condition;

/**
 * @author jpdaigle
 *  
 */
public class AddForkOnEmptyPointCommand extends Command implements JUCMNavCommand {
    // Where to create the Fork
    PathGraph _pg;

    EmptyPoint _originEp;

    // New items
    PathNode _newFork;

    EmptyPoint _newEmptyPoint;

    EndPoint _newEndPoint;

    NodeConnection _newLink1, _newLink2, _ncPred, _ncTarg;

    NodeConnection _originNc;

    Condition _orForkCondition1, _orForkCondition2;

    PathNode _prevNode, _nextNode;

    int _posX, _posY;

    /**
     *  
     */
    public AddForkOnEmptyPointCommand() {
        super();
        this.setLabel(Messages.getString("AddForkOnEmptyPointCommand.addFork")); //$NON-NLS-1$
    }

    public AddForkOnEmptyPointCommand(PathNode newFork, PathGraph pg, EmptyPoint ep) {
        _newFork = newFork;
        _pg = pg;
        _originEp = ep;
        this.setLabel(Messages.getString("AddForkOnEmptyPointCommand.addFork")); //$NON-NLS-1$
    }

    /**
     * @param label
     */
    public AddForkOnEmptyPointCommand(String label) {
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
            _newFork.setX(x);
            _newFork.setY(y);

            URNspec urn = _pg.getMap().getUcmspec().getUrnspec();
            _newEmptyPoint = (EmptyPoint) ModelCreationFactory.getNewObject(urn, EmptyPoint.class);
            _newEmptyPoint.setX(x + 25);
            _newEmptyPoint.setY(y - 25);

            _newLink1 = (NodeConnection) ModelCreationFactory.getNewObject(urn, NodeConnection.class);

            _newEndPoint = (EndPoint) ModelCreationFactory.getNewObject(urn, EndPoint.class);
            _newEndPoint.setX(x + 75);
            _newEndPoint.setY(y - 30);

            _newLink2 = (NodeConnection) ModelCreationFactory.getNewObject(urn, NodeConnection.class);

            if (_newFork instanceof OrFork) {
                _orForkCondition1 = (Condition) ModelCreationFactory.getNewObject(urn, Condition.class);
                _orForkCondition2 = (Condition) ModelCreationFactory.getNewObject(urn, Condition.class);
            }

            // TODO Add an empty point *ON* the connection going towards the
            // EndPoint

            redo();
        }
    }

    public void redo() {
        // Path modifications
        _ncPred = (NodeConnection) _originEp.getPred().get(0);
        _ncTarg = (NodeConnection) _originEp.getSucc().get(0);
        _ncPred.setTarget(_newFork);
        _ncTarg.setSource(_newFork);
        _newLink1.setSource(_newFork);
        _newLink1.setTarget(_newEmptyPoint);
        _newLink2.setSource(_newEmptyPoint);
        _newLink2.setTarget(_newEndPoint);

        if (_newFork instanceof OrFork) {
            _newLink1.setCondition(_orForkCondition1);
            _ncTarg.setCondition(_orForkCondition2);
        }

        // Add to model
        _pg.getNodeConnections().add(_newLink1);
        _pg.getNodeConnections().add(_newLink2);
        _pg.getPathNodes().add(_newFork);
        _pg.getPathNodes().add(_newEmptyPoint);
        _pg.getPathNodes().add(_newEndPoint);

        // Delete old node
        _pg.getPathNodes().remove(_originEp);
        _originEp.setCompRef(null);
        //_originEp = null;

        // bind to parent
        _newFork.setCompRef(ParentFinder.findParent(_pg.getMap(), _newFork.getX(), _newFork.getY()));
        _newEmptyPoint.setCompRef(ParentFinder.findParent(_pg.getMap(), _newEmptyPoint.getX(), _newEmptyPoint.getY()));
        _newEndPoint.setCompRef(ParentFinder.findParent(_pg.getMap(), _newEndPoint.getX(), _newEndPoint.getY()));
    }

    public void undo() {
        // Path modifications
        _ncPred.setTarget(_originEp);
        _ncTarg.setSource(_originEp);

        _newLink1.setSource(null);
        _newLink1.setTarget(null);
        _newLink2.setSource(null);
        _newLink2.setTarget(null);

        if (_newFork instanceof OrFork) {
            _newLink1.setCondition(null);
            _ncTarg.setCondition(null);
        }

        // Remove from model
        _pg.getNodeConnections().remove(_newLink1);
        _pg.getNodeConnections().remove(_newLink2);
        _pg.getPathNodes().remove(_newFork);
        _pg.getPathNodes().remove(_newEmptyPoint);
        _pg.getPathNodes().remove(_newEndPoint);

        // Re-add old node
        _pg.getPathNodes().add(_originEp);
        _originEp.setCompRef(ParentFinder.findParent(_pg.getMap(), _originEp.getX(), _originEp.getY()));

        // unbind from parent
        _newFork.setCompRef(null);
        _newEmptyPoint.setCompRef(null);
        _newEndPoint.setCompRef(null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert (_newEmptyPoint != null) : "pre newEmptyPoint"; //$NON-NLS-1$
        assert (_newFork != null) : "pre newFork"; //$NON-NLS-1$
        assert (_newEndPoint != null) : "pre newEndPoint"; //$NON-NLS-1$

        assert (!_pg.getPathNodes().contains(_newEmptyPoint) && !_pg.getPathNodes().contains(_newFork) && !_pg.getPathNodes().contains(
                _newEndPoint)) : "pre PathGraph doesn't contain new nodes"; //$NON-NLS-1$

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert (_newEmptyPoint != null) : "pre newEmptyPoint"; //$NON-NLS-1$
        assert (_newFork != null) : "pre newFork"; //$NON-NLS-1$
        assert (_newEndPoint != null) : "pre newEndPoint"; //$NON-NLS-1$

        assert (_pg.getPathNodes().contains(_newEmptyPoint) && _pg.getPathNodes().contains(_newFork) && _pg.getPathNodes().contains(
                _newEndPoint)) : "pre PathGraph doesn't contain new nodes"; //$NON-NLS-1$

    }

}