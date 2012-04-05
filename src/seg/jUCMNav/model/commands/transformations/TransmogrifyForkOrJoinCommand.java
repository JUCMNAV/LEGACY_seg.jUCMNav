package seg.jUCMNav.model.commands.transformations;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.URNNamingHelper;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.Condition;
import urncore.NodeLabel;

/**
 * 
 * Command to transform AND to OR or vice-versa. (forks/joins)
 * 
 * @author jpdaigle, jkealey
 * 
 */
public class TransmogrifyForkOrJoinCommand extends Command implements JUCMNavCommand {

    PathNode _newNode, _oldNode;
    UCMmap _pg;
    int _x, _y;
    Vector _inConn, _outConn;
    Vector _outConditions;
    NodeLabel _oldLabel;

    /**
     * Create a command to transform AND to OR or vice-versa.
     * 
     * @param oldNode
     *            Old Fork or Join to transmogrify.
     * @param pg
     *            the pathgraph containing the elements
     */
    public TransmogrifyForkOrJoinCommand(PathNode oldNode, UCMmap pg) {
        _oldNode = oldNode;
        _pg = pg;
        if (!(_oldNode instanceof OrFork || _oldNode instanceof AndFork || _oldNode instanceof OrJoin || _oldNode instanceof AndJoin)) {
            // check that the PathNode passed in is an Or or And Fork or Join.
            throw new IllegalArgumentException(Messages.getString("TransmogrifyForkOrJoinCommand.mustBeForkorJoin")); //$NON-NLS-1$
        }

        assert (_oldNode != null);

        this.setLabel(Messages.getString("TransmogrifyForkOrJoinCommand.convert")); //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        _x = _oldNode.getX();
        _y = _oldNode.getY();
        URNspec urn = _pg.getUrndefinition().getUrnspec();

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
                _outConditions.add(ModelCreationFactory.getNewObject(urn, Condition.class));
        }

        _oldLabel = _oldNode.getLabel();

        // transfer name/description
        // don't transfer name if wasn't default.
        if (!_oldNode.getName().startsWith(URNNamingHelper.getPrefix(_oldNode.getClass())))
            _newNode.setName(_oldNode.getName());

        _newNode.setDescription(_oldNode.getDescription());

        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
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
        _pg.getNodes().remove(_oldNode);

        // Add new node
        _pg.getNodes().add(_newNode);

        // reset parents
        _newNode.setContRef(_oldNode.getContRef());
        _oldNode.setContRef(null);

        // transfer label
        _newNode.setLabel(_oldLabel);

    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
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
        _pg.getNodes().remove(_newNode);

        // Add new node
        _pg.getNodes().add(_oldNode);

        // reset parents
        _oldNode.setContRef(_newNode.getContRef());
        _newNode.setContRef(null);

        // transfer label
        _oldNode.setLabel(_oldLabel);

    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert _newNode != null && _oldNode != null && _pg != null : "pre something is null"; //$NON-NLS-1$
        assert _pg.getNodes().contains(_oldNode) && !_pg.getNodes().contains(_newNode) : "pre node replacement problem"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert _newNode != null && _oldNode != null && _pg != null : "post something is null"; //$NON-NLS-1$
        assert !_pg.getNodes().contains(_oldNode) && _pg.getNodes().contains(_newNode) : "post node replacement problem"; //$NON-NLS-1$

    }

}