/*
 * Created on Apr 28, 2005
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.PathGraph;
import ucm.map.PathNode;

/**
 * This command adds an OR-Fork to a path at a specified EmptyPoint. The EmptyPoint is replaced by an OrFork and a new
 * branch of the path with corresponding EndPoint is created.
 * 
 * @author jpdaigle
 *  
 */
public class AddOrForkCommand extends Command implements JUCMNavCommand {

    // Where to create the Fork
    PathGraph _pg;

    EmptyPoint _originEp;

    // New items
    OrFork _newOrFork;

    EmptyPoint _newEmptyPoint;

    EndPoint _newEndPoint;

    NodeConnection _newLink1, _newLink2, _ncTarg;

    NodeConnection _originNc;
    
    int _posX, _posY;

    public AddOrForkCommand() {
        super();
        this.setLabel("Add OR-Fork");
    }

    public AddOrForkCommand(PathGraph pg, EmptyPoint ep) {
        _pg = pg;
        _originEp = ep;
        this.setLabel("Add OR-Fork");
    }

    public AddOrForkCommand(PathGraph pg, NodeConnection nc, int x, int y) {
        _pg = pg;
        _originNc = nc;
        _posX = x;
        _posY = y;
        this.setLabel("Add OR-Fork");
    }

    public boolean canExecute() {
        return super.canExecute();
    }
    
    public void execute() {
        if (_originEp != null)
            executeFromEmptyPoint();
        else if (_originNc != null) {
            executeFromNodeConnection();
        }
    }

    protected void executeFromEmptyPoint() {
        if (_originEp != null && _pg != null) {
            int x, y;
            x = _originEp.getX();
            y = _originEp.getY();

            assert (_originEp.getPred().size() > 0);
            assert (_originEp.getSucc().size() > 0);

            NodeConnection ncPred, ncTarg;
            ncPred = (NodeConnection) _originEp.getPred().get(0);
            ncTarg = (NodeConnection) _originEp.getSucc().get(0);

            // OrFork -- EmptyPoint -- EndPoint
            _newOrFork = (OrFork) ModelCreationFactory.getNewObject(OrFork.class);
            _newOrFork.setX(x);
            _newOrFork.setY(y);

            ncPred.setTarget(_newOrFork);
            ncTarg.setSource(_newOrFork);

            _newEmptyPoint = (EmptyPoint) ModelCreationFactory.getNewObject(EmptyPoint.class);
            _newEmptyPoint.setX(x + 25);
            _newEmptyPoint.setY(y - 20);

            _newLink1 = (NodeConnection) ModelCreationFactory.getNewObject(NodeConnection.class);
            _newLink1.setSource(_newOrFork);
            _newLink1.setTarget(_newEmptyPoint);

            _newEndPoint = (EndPoint) ModelCreationFactory.getNewObject(EndPoint.class);
            _newEndPoint.setX(x + 100);
            _newEndPoint.setY(y - 50);

            _newLink2 = (NodeConnection) ModelCreationFactory.getNewObject(NodeConnection.class);
            _newLink2.setSource(_newEmptyPoint);
            _newLink2.setTarget(_newEndPoint);

            // TODO Add an empty point *ON* the connection going towards the
            // EndPoint

            redo();
        }
    }

    protected void executeFromNodeConnection() {
        // Well, this isn't very clean :(
        
        // Split existing connection
        NodeConnection ncPred;
        PathNode previousNode, nextNode;
        previousNode = _originNc.getSource();
        nextNode = _originNc.getTarget();
        _ncTarg = (NodeConnection) ModelCreationFactory.getNewObject(NodeConnection.class);
        ncPred = _originNc;

        // OrFork -- EmptyPoint -- EndPoint
        _newOrFork = (OrFork) ModelCreationFactory.getNewObject(OrFork.class);
        _newOrFork.setX(_posX);
        _newOrFork.setY(_posY);

        ncPred.setTarget(_newOrFork);
        _ncTarg.setSource(_newOrFork);
        _ncTarg.setTarget(nextNode);
        
        _newEmptyPoint = (EmptyPoint) ModelCreationFactory.getNewObject(EmptyPoint.class);
        _newEmptyPoint.setX(_posX + 25);
        _newEmptyPoint.setY(_posY - 20);

        _newLink1 = (NodeConnection) ModelCreationFactory.getNewObject(NodeConnection.class);
        _newLink1.setSource(_newOrFork);
        _newLink1.setTarget(_newEmptyPoint);

        _newEndPoint = (EndPoint) ModelCreationFactory.getNewObject(EndPoint.class);
        _newEndPoint.setX(_posX + 100);
        _newEndPoint.setY(_posY - 50);

        _newLink2 = (NodeConnection) ModelCreationFactory.getNewObject(NodeConnection.class);
        _newLink2.setSource(_newEmptyPoint);
        _newLink2.setTarget(_newEndPoint);

        // TODO Add an empty point *ON* the connection going towards the
        // EndPoint

        redo();
    }

    public void redo() {
        // Add the new objects to the graph
        testPreConditions();
        if (_originEp != null)
        	redo_fromEmptyPoint();
        else if (_originNc != null)
            redo_fromNodeConnection();
        testPostConditions();
    }

    protected void redo_fromEmptyPoint() {
        _pg.getNodeConnections().add(_newLink1);
        _pg.getNodeConnections().add(_newLink2);

        _pg.getPathNodes().add(_newOrFork);
        _pg.getPathNodes().add(_newEmptyPoint);
        _pg.getPathNodes().add(_newEndPoint);

        // Delete old node
        _pg.getPathNodes().remove(_originEp);
        //_originEp = null;
    }
    
    protected void redo_fromNodeConnection() {
        _pg.getNodeConnections().add(_newLink1);
        _pg.getNodeConnections().add(_newLink2);
        _pg.getNodeConnections().add(_ncTarg);
        
        _pg.getPathNodes().add(_newOrFork);
        _pg.getPathNodes().add(_newEmptyPoint);
        _pg.getPathNodes().add(_newEndPoint);

    }
    
    public void undo() {
        testPreConditions();
        testPostConditions();
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