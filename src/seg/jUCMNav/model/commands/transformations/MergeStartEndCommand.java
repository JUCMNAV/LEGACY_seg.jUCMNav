package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
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

        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        // TODO Should check if not on same path.
        return super.canExecute();
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