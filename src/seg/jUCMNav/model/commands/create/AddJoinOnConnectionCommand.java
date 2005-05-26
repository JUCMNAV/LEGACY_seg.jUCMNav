/*
 * Created on 25 May 2005
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.EmptyPoint;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import urn.URNspec;

/**
 * @author jpdaigle
 *  
 */
public class AddJoinOnConnectionCommand extends Command implements JUCMNavCommand {
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
    public AddJoinOnConnectionCommand() {
        super();
        this.setLabel("Add Join");
    }

    public AddJoinOnConnectionCommand(PathNode newJoin, PathGraph pg, NodeConnection nc, int x, int y) {
        _pg = pg;
        _originNc = nc;
        _posX = x;
        _posY = y;
        _newJoin = newJoin;
        this.setLabel("Add Join");
    }

    public boolean canExecute() {
        return super.canExecute();
    }

    public void execute() {
        // Split existing connection
        _prevNode = _originNc.getSource();
        _nextNode = _originNc.getTarget();
        _ncTarg = (NodeConnection) ModelCreationFactory.getNewObject((URNspec) _pg.eContainer().eContainer()
                .eContainer(), NodeConnection.class);
        _ncPred = _originNc;

        // Startpoint -- EmptyPoint -- Join
        _newJoin.setX(_posX);
        _newJoin.setY(_posY);

        _newEmptyPoint = (EmptyPoint) ModelCreationFactory.getNewObject((URNspec) _pg.eContainer().eContainer()
                .eContainer(), EmptyPoint.class);
        _newEmptyPoint.setX(_posX - 25);
        _newEmptyPoint.setY(_posY - 25);

        _newLink1 = (NodeConnection) ModelCreationFactory.getNewObject((URNspec) _pg.eContainer().eContainer()
                .eContainer(), NodeConnection.class);

        _newStartPoint = (StartPoint) ModelCreationFactory.getNewObject((URNspec) _pg.eContainer().eContainer()
                .eContainer(), StartPoint.class);
        _newStartPoint.setX(_posX - 75);
        _newStartPoint.setY(_posY - 30);

        _newLink2 = (NodeConnection) ModelCreationFactory.getNewObject((URNspec) _pg.eContainer().eContainer()
                .eContainer(), NodeConnection.class);


        redo();
    }

    public void redo() {
        testPreConditions();
        
        // _ncPred is now the connection the user clicked on
        _ncTarg.setTarget(_nextNode);
        _ncTarg.setSource(_newJoin);
        _ncPred.setTarget(_newJoin);

        _newLink1.setSource(_newEmptyPoint);
        _newLink1.setTarget(_newJoin);

        _newLink2.setSource(_newStartPoint);
        _newLink2.setTarget(_newEmptyPoint);

        _pg.getNodeConnections().add(_newLink1);
        _pg.getNodeConnections().add(_newLink2);
        _pg.getNodeConnections().add(_ncTarg);

        _pg.getPathNodes().add(_newJoin);
        _pg.getPathNodes().add(_newEmptyPoint);
        _pg.getPathNodes().add(_newStartPoint);

        _newJoin.setCompRef(ParentFinder.findParent((Map) _pg.eContainer(), _newJoin.getX(), _newJoin.getY()));
        _newEmptyPoint.setCompRef(ParentFinder.findParent((Map) _pg.eContainer(), _newEmptyPoint.getX(), _newEmptyPoint.getY()));
        _newStartPoint.setCompRef(ParentFinder.findParent((Map) _pg.eContainer(), _newStartPoint.getX(), _newStartPoint.getY()));

        testPostConditions();
    }
    
    public void undo() {
        testPostConditions();
        
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

        _pg.getPathNodes().remove(_newJoin);
        _pg.getPathNodes().remove(_newEmptyPoint);
        _pg.getPathNodes().remove(_newStartPoint);

        _newJoin.setCompRef(null);
        _newEmptyPoint.setCompRef(null);
        _newStartPoint.setCompRef(null);
        
        testPreConditions();
    }
    
    /**
     * @param label
     */
    public AddJoinOnConnectionCommand(String label) {
        super(label);
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert (_newEmptyPoint != null) : "pre newEmptyPoint";
        assert (_newJoin != null) : "pre newJoin";
        assert (_newStartPoint != null) : "pre newStartPoint";

        assert (!_pg.getPathNodes().contains(_newEmptyPoint) && !_pg.getPathNodes().contains(_newJoin) && !_pg
                .getPathNodes().contains(_newStartPoint)) : "pre PathGraph doesn't contain new nodes";

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert (_newEmptyPoint != null) : "post newEmptyPoint";
        assert (_newJoin != null) : "post newJoin";
        assert (_newStartPoint != null) : "post newEndPoint";

        assert (_pg.getPathNodes().contains(_newEmptyPoint) && _pg.getPathNodes().contains(_newJoin) && _pg
                .getPathNodes().contains(_newStartPoint)) : "post PathGraph contains new nodes";

    }

}