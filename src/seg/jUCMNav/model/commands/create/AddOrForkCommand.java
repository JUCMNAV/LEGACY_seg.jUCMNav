/*
 * Created on Apr 28, 2005
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import urn.URNspec;

/**
 * This command adds an OR-Fork to a path at a specified EmptyPoint. The EmptyPoint is replaced by an OrFork and a new branch of the path with corresponding
 * EndPoint is created.
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

    NodeConnection _newLink1, _newLink2, _ncPred, _ncTarg;

    NodeConnection _originNc;

    PathNode _prevNode, _nextNode;

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

            // OrFork -- EmptyPoint -- EndPoint
            _newOrFork = (OrFork) ModelCreationFactory.getNewObject((URNspec) _pg.eContainer().eContainer().eContainer(), OrFork.class);
            _newOrFork.setX(x);
            _newOrFork.setY(y);

            _newEmptyPoint = (EmptyPoint) ModelCreationFactory.getNewObject((URNspec) _pg.eContainer().eContainer().eContainer(), EmptyPoint.class);
            _newEmptyPoint.setX(x + 25);
            _newEmptyPoint.setY(y - 25);

            _newLink1 = (NodeConnection) ModelCreationFactory.getNewObject((URNspec) _pg.eContainer().eContainer().eContainer(), NodeConnection.class);

            _newEndPoint = (EndPoint) ModelCreationFactory.getNewObject((URNspec) _pg.eContainer().eContainer().eContainer(), EndPoint.class);
            _newEndPoint.setX(x + 75);
            _newEndPoint.setY(y - 30);

            _newLink2 = (NodeConnection) ModelCreationFactory.getNewObject((URNspec) _pg.eContainer().eContainer().eContainer(), NodeConnection.class);

            // TODO Add an empty point *ON* the connection going towards the
            // EndPoint

            redo();
        }
    }

protected void executeFromNodeConnection() {
        // Well, this isn't very clean :(
        // HACK HACK HACK HACK HACK HACK HACK HACK

        // TODO factor out model-impacting code to the redo method

        // Split existing connection
        _prevNode = _originNc.getSource();
        _nextNode = _originNc.getTarget();
        _ncTarg = (NodeConnection) ModelCreationFactory.getNewObject((URNspec) _pg.eContainer().eContainer().eContainer(),NodeConnection.class);
        _ncPred = _originNc;

        // OrFork -- EmptyPoint -- EndPoint
        _newOrFork = (OrFork) ModelCreationFactory.getNewObject((URNspec) _pg.eContainer().eContainer().eContainer(),OrFork.class);
        _newOrFork.setX(_posX);
        _newOrFork.setY(_posY);

        _newEmptyPoint = (EmptyPoint) ModelCreationFactory.getNewObject((URNspec) _pg.eContainer().eContainer().eContainer(),EmptyPoint.class);
        _newEmptyPoint.setX(_posX + 25);
        _newEmptyPoint.setY(_posY - 25);

        _newLink1 = (NodeConnection) ModelCreationFactory.getNewObject((URNspec) _pg.eContainer().eContainer().eContainer(),NodeConnection.class);


        _newEndPoint = (EndPoint) ModelCreationFactory.getNewObject((URNspec) _pg.eContainer().eContainer().eContainer(),EndPoint.class);
        _newEndPoint.setX(_posX + 75);
        _newEndPoint.setY(_posY - 30);

        _newLink2 = (NodeConnection) ModelCreationFactory.getNewObject((URNspec) _pg.eContainer().eContainer().eContainer(),NodeConnection.class);

        // TODO Add an empty point *ON* the connection going towards the
        // EndPoint

        redo();
    }    public void redo() {
        // Add the new objects to the graph
        testPreConditions();
        if (_originEp != null)
            redo_fromEmptyPoint();
        else if (_originNc != null)
            redo_fromNodeConnection();
        testPostConditions();
    }

    protected void redo_fromEmptyPoint() {
        // Path modifications
        _ncPred = (NodeConnection) _originEp.getPred().get(0);
        _ncTarg = (NodeConnection) _originEp.getSucc().get(0);
        _ncPred.setTarget(_newOrFork);
        _ncTarg.setSource(_newOrFork);
        _newLink1.setSource(_newOrFork);
        _newLink1.setTarget(_newEmptyPoint);
        _newLink2.setSource(_newEmptyPoint);
        _newLink2.setTarget(_newEndPoint);

        // Add to model
        _pg.getNodeConnections().add(_newLink1);
        _pg.getNodeConnections().add(_newLink2);
        _pg.getPathNodes().add(_newOrFork);
        _pg.getPathNodes().add(_newEmptyPoint);
        _pg.getPathNodes().add(_newEndPoint);

        // Delete old node
        _pg.getPathNodes().remove(_originEp);
        _originEp.setCompRef(null);
        //_originEp = null;

        // bind to parent
        _newOrFork.setCompRef(ParentFinder.findParent((Map) _pg.eContainer(), _newOrFork.getX(), _newOrFork.getY()));
        _newEmptyPoint.setCompRef(ParentFinder.findParent((Map) _pg.eContainer(), _newEmptyPoint.getX(), _newEmptyPoint.getY()));
        _newEndPoint.setCompRef(ParentFinder.findParent((Map) _pg.eContainer(), _newEndPoint.getX(), _newEndPoint.getY()));
    }

    protected void redo_fromNodeConnection() {

        // _ncPred is now the connection the user clicked on
        _ncTarg.setTarget(_nextNode);
        _ncTarg.setSource(_newOrFork);
        _ncPred.setTarget(_newOrFork);

        _newLink1.setSource(_newOrFork);
        _newLink1.setTarget(_newEmptyPoint);

        _newLink2.setSource(_newEmptyPoint);
        _newLink2.setTarget(_newEndPoint);

        _pg.getNodeConnections().add(_newLink1);
        _pg.getNodeConnections().add(_newLink2);
        _pg.getNodeConnections().add(_ncTarg);

        _pg.getPathNodes().add(_newOrFork);
        _pg.getPathNodes().add(_newEmptyPoint);
        _pg.getPathNodes().add(_newEndPoint);

        _newOrFork.setCompRef(ParentFinder.findParent((Map) _pg.eContainer(), _newOrFork.getX(), _newOrFork.getY()));
        _newEmptyPoint.setCompRef(ParentFinder.findParent((Map) _pg.eContainer(), _newEmptyPoint.getX(), _newEmptyPoint.getY()));
        _newEndPoint.setCompRef(ParentFinder.findParent((Map) _pg.eContainer(), _newEndPoint.getX(), _newEndPoint.getY()));
    }

    public void undo() {
        testPostConditions();
        if (_originEp != null)
            undo_fromEmptyPoint();
        else if (_originNc != null)
            undo_fromNodeConnection();
        testPreConditions();
    }

    protected void undo_fromEmptyPoint() {
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
        _pg.getPathNodes().remove(_newOrFork);
        _pg.getPathNodes().remove(_newEmptyPoint);
        _pg.getPathNodes().remove(_newEndPoint);

        // Re-add old node
        _pg.getPathNodes().add(_originEp);
        _originEp.setCompRef(ParentFinder.findParent((Map) _pg.eContainer(), _originEp.getX(), _originEp.getY()));

        // unbind from parent
        _newOrFork.setCompRef(null);
        _newEmptyPoint.setCompRef(null);
        _newEndPoint.setCompRef(null);
    }

    protected void undo_fromNodeConnection() {
        _ncPred.setTarget(_nextNode);

        _ncTarg.setTarget(null);
        _ncTarg.setSource(null);

        _newLink1.setSource(null);
        _newLink1.setTarget(null);

        _newLink2.setSource(null);
        _newLink2.setTarget(null);

        _pg.getNodeConnections().remove(_newLink1);
        _pg.getNodeConnections().remove(_newLink2);
        _pg.getNodeConnections().remove(_ncTarg);

        _pg.getPathNodes().remove(_newOrFork);
        _pg.getPathNodes().remove(_newEmptyPoint);
        _pg.getPathNodes().remove(_newEndPoint);

        _newOrFork.setCompRef(null);
        _newEmptyPoint.setCompRef(null);
        _newEndPoint.setCompRef(null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        // TODO Must implement (high-priority)

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        // TODO Must implement (high-priority)

    }

}