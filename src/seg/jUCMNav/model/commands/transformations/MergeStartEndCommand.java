package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.actions.SafePathChecker;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.ComponentRef;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.StartPoint;

/**
 * Created on 26-May-2005
 * 
 * Will merge a start point and end point into an empty point located at x,y;
 * 
 * @author jkealey
 *  
 */
public class MergeStartEndCommand extends Command implements JUCMNavCommand {

    private StartPoint startPoint;

    private EndPoint endPoint;

    private EmptyPoint newEmptyPoint;
    
    private ComponentRef parentStart, parentEnd;

    private Map map;

    private int x, y;

    private NodeConnection prevConn, nextConn;

    /**
     * 
     *  
     */
    public MergeStartEndCommand(Map map, StartPoint sp, EndPoint ep, int x, int y) {
        this.startPoint = sp;
        this.endPoint = ep;
        this.x = x;
        this.y = y;
        this.map = map;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        this.newEmptyPoint = (EmptyPoint) ModelCreationFactory.getNewObject(map.getUcmspec().getUrnspec(), EmptyPoint.class);
        newEmptyPoint.setX(x);
        newEmptyPoint.setY(y);
        this.prevConn = (NodeConnection) endPoint.getPred().get(0);
        this.nextConn = (NodeConnection) startPoint.getSucc().get(0);

        parentStart = startPoint.getCompRef();
        parentEnd = endPoint.getCompRef();
        
        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return SafePathChecker.isSafeJoin(this.startPoint, this.endPoint);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        prevConn.setTarget(newEmptyPoint);
        nextConn.setSource(newEmptyPoint);
        map.getPathGraph().getPathNodes().remove(startPoint);
        map.getPathGraph().getPathNodes().remove(endPoint);
        map.getPathGraph().getPathNodes().add(newEmptyPoint);
        
        startPoint.setCompRef(null);
        endPoint.setCompRef(null);
        newEmptyPoint.setCompRef(ParentFinder.getPossibleParent(newEmptyPoint));

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        prevConn.setTarget(endPoint);
        nextConn.setSource(startPoint);
        map.getPathGraph().getPathNodes().add(startPoint);
        map.getPathGraph().getPathNodes().add(endPoint);
        map.getPathGraph().getPathNodes().remove(newEmptyPoint);

        startPoint.setCompRef(parentStart);
        endPoint.setCompRef(parentEnd);
        newEmptyPoint.setCompRef(null);
        
        testPreConditions();
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