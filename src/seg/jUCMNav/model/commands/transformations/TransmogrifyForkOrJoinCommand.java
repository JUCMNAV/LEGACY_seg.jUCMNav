/*
 * Created on 26-May-2005
 */
package seg.jUCMNav.model.commands.transformations;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import urn.URNspec;
import urncore.Condition;

/**
 * @author jpdaigle
 *  
 */
public class TransmogrifyForkOrJoinCommand extends Command implements JUCMNavCommand {

    PathNode _newNode, _oldNode;

    PathGraph _pg;

    int _x, _y;

    Vector _inConn, _outConn;

    Vector _outConditions;

    public TransmogrifyForkOrJoinCommand() {
        super();
    }

    /**
     * Create a command to transform AND to OR or vice-versa.
     * 
     * @param oldNode
     *            Old Fork or Join to transmogrify.
     */
    public TransmogrifyForkOrJoinCommand(PathNode oldNode, PathGraph pg) {
        _oldNode = oldNode;
        _pg = pg;
        if (!(_oldNode instanceof OrFork || _oldNode instanceof AndFork || _oldNode instanceof OrJoin || _oldNode instanceof AndJoin)) {
            // check that the PathNode passed in is an Or or And Fork or Join.
            throw new IllegalArgumentException(Messages.getString("TransmogrifyForkOrJoinCommand.mustBeForkorJoin")); //$NON-NLS-1$
        }

        assert (_oldNode != null);

        this.setLabel(Messages.getString("TransmogrifyForkOrJoinCommand.convert")); //$NON-NLS-1$
    }

    public boolean canExecute() {
        return super.canExecute();
    }

    public void execute() {
        _x = _oldNode.getX();
        _y = _oldNode.getY();
        URNspec urn = _pg.getMap().getUcmspec().getUrnspec();

        if (_oldNode instanceof AndFork) {
            _newNode = (OrFork) ModelCreationFactory.getNewObject(urn, OrFork.class);
        } else if (_oldNode instanceof OrFork) {
            _newNode = (AndFork) ModelCreationFactory.getNewObject(urn, AndFork.class);
        } else if (_oldNode instanceof AndJoin) {
            _newNode = (OrJoin) ModelCreationFactory.getNewObject(urn, OrJoin.class);
        } else if (_oldNode instanceof OrJoin) {
            _newNode = (AndJoin) ModelCreationFactory.getNewObject(urn, AndJoin.class);
        } else
            throw new IllegalArgumentException(Messages.getString("TransmogrifyForkOrJoinCommand.mustBeForkorJoin")); //$NON-NLS-1$

        _newNode.setX(_x);
        _newNode.setY(_y);

        _inConn = new Vector();
        _inConn.addAll(_oldNode.getPred());
        _outConn = new Vector();
        _outConn.addAll(_oldNode.getSucc());

        _outConditions = new Vector();
        for (Iterator iter = _outConn.iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            if (_oldNode instanceof OrFork)
                _outConditions.add(nc.getCondition());
            else if (_newNode instanceof OrFork)
                _outConditions.add((Condition) ModelCreationFactory.getNewObject(urn, Condition.class));
        }
        redo();
    }

    public void redo() {
        // Transfer connections
        int i;
        NodeConnection nc;

        for (i = 0; i < _inConn.size(); i++) {
            nc = (NodeConnection) _inConn.get(i);
            nc.setTarget(_newNode);
        }
        for (i = 0; i < _outConn.size(); i++) {
            nc = (NodeConnection) _outConn.get(i);
            nc.setSource(_newNode);
            if (_oldNode instanceof OrFork)
                nc.setCondition(null);
            else if (_newNode instanceof OrFork)
                nc.setCondition((Condition) _outConditions.get(i));
        }

        // Remove old node
        _pg.getPathNodes().remove(_oldNode);
        _oldNode.setCompRef(null);

        // Add new node
        _newNode.setCompRef(ParentFinder.findParent(_pg.getMap(), _newNode.getX(), _newNode.getY()));
        _pg.getPathNodes().add(_newNode);

    }

    public void undo() {
        // Transfer connections
        int i;
        NodeConnection nc;

        for (i = 0; i < _inConn.size(); i++) {
            nc = (NodeConnection) _inConn.get(i);
            nc.setTarget(_oldNode);
        }
        for (i = 0; i < _outConn.size(); i++) {
            nc = (NodeConnection) _outConn.get(i);
            nc.setSource(_oldNode);
            if (_oldNode instanceof OrFork)
                nc.setCondition((Condition) _outConditions.get(i));
            else if (_newNode instanceof OrFork)
                nc.setCondition(null);            
        }

        // Remove old node
        _pg.getPathNodes().remove(_newNode);
        _newNode.setCompRef(null);

        // Add new node
        _oldNode.setCompRef(ParentFinder.findParent(_pg.getMap(), _oldNode.getX(), _oldNode.getY()));
        _pg.getPathNodes().add(_oldNode);
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