package seg.jUCMNav.model.commands.transformations;

import java.util.Vector;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.AndFork;
import ucm.map.EmptyPoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import urncore.Condition;

/**
 * @author jpdaigle, jkealey
 * 
 *  
 */
public class ForkPathsCommand extends Command implements JUCMNavCommand {

    EmptyPoint _oldEmptyPoint;

    StartPoint _oldStartPoint;

    PathNode _newFork;

    int _x, _y;

    NodeConnection _ncOldStart, _ncA, _ncB;

    PathGraph _pg;

    Vector _newConditions;

    public ForkPathsCommand() {
        super();
    }

    /**
     * Create a command to fork 2 paths, pass in an already created non-null Fork.
     * 
     * @param emPoint
     *            EmptyPoint on target path to fork.
     * @param startPoint
     *            StartPoint on source path to join with the other path (via a fork).
     * @param newFork
     *            OrFork or AndFork to split paths.
     */
    public ForkPathsCommand(EmptyPoint emPoint, StartPoint startPoint, PathNode newFork) {
        _oldEmptyPoint = emPoint;
        _oldStartPoint = startPoint;
        _newFork = newFork;

        if (!(_newFork instanceof OrFork || _newFork instanceof AndFork)) {
            // check that the PathNode passed in is an Or or And Join.
            throw new IllegalArgumentException(Messages.getString("ForkPathsCommand.mustBeOrorAndFork")); //$NON-NLS-1$
        }

        assert (_newFork != null);

        this.setLabel(Messages.getString("ForkPathsCommand.forkPaths")); //$NON-NLS-1$
    }

    public boolean canExecute() {
        return super.canExecute();
    }

    public void execute() {
        _x = _oldEmptyPoint.getX();
        _y = _oldEmptyPoint.getY();

        _newFork.setX(_x);
        _newFork.setY(_y);

        _pg = _oldEmptyPoint.getPathGraph();
        _ncOldStart = (NodeConnection) _oldStartPoint.getSucc().get(0);
        _ncA = (NodeConnection) _oldEmptyPoint.getPred().get(0);
        _ncB = (NodeConnection) _oldEmptyPoint.getSucc().get(0);

        if (_newFork instanceof OrFork) {
            _newConditions = new Vector();
            //need two conditions, one for each branch.
            _newConditions.add((Condition) ModelCreationFactory.getNewObject(_pg.getMap().getUcmspec().getUrnspec(), Condition.class));
            _newConditions.add((Condition) ModelCreationFactory.getNewObject(_pg.getMap().getUcmspec().getUrnspec(), Condition.class));
        }

        redo();
    }

    public void redo() {
        // Set the start of the link going to _oldStartPoint to point to the new fork.
        _ncOldStart.setSource(_newFork);

        // Move the old empty point's connections to the new fork.
        _ncA.setTarget(_newFork);
        _ncB.setSource(_newFork);

        // Remove old end point from model
        // Remove old empty point from model
        _pg.getPathNodes().remove(_oldEmptyPoint);
        _pg.getPathNodes().remove(_oldStartPoint);
        _oldEmptyPoint.setCompRef(null);
        _oldStartPoint.setCompRef(null);

        // Add new fork PathNode to model
        _newFork.setCompRef(ParentFinder.findParent(_pg.getMap(), _newFork.getX(), _newFork.getY()));
        _pg.getPathNodes().add(_newFork);

        if (_newConditions != null) {
            _ncOldStart.setCondition((Condition) _newConditions.get(0));
            _ncB.setCondition((Condition) _newConditions.get(1));
        }
    }

    public void undo() {
        _ncOldStart.setSource(_oldStartPoint);

        _ncA.setTarget(_oldEmptyPoint);
        _ncB.setSource(_oldEmptyPoint);

        _pg.getPathNodes().add(_oldEmptyPoint);
        _pg.getPathNodes().add(_oldStartPoint);
        _oldEmptyPoint.setCompRef(ParentFinder.findParent(_pg.getMap(), _oldEmptyPoint.getX(), _oldEmptyPoint.getY()));
        _oldStartPoint.setCompRef(ParentFinder.findParent(_pg.getMap(), _oldStartPoint.getX(), _oldStartPoint.getY()));

        _newFork.setCompRef(null);
        _pg.getPathNodes().remove(_newFork);

        if (_newConditions != null) {
            _ncOldStart.setCondition(null);
            _ncB.setCondition(null);
        }
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