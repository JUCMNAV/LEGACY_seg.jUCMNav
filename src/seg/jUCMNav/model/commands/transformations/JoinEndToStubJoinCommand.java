package seg.jUCMNav.model.commands.transformations;

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.commands.delete.DeleteOutBindingCommand;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OutBinding;
import ucm.map.PathGraph;
import ucm.map.PathNode;

/**
 * Created 27-05-2005
 * 
 * This command represents the action of a user dragging an EndPoint in a Stub/Join. This action will erase the EndPoint and add the path as
 * a predecessor of the Stub/Join.
 * 
 * @author Etienne Tremblay
 */
public class JoinEndToStubJoinCommand extends CompoundCommand implements JUCMNavCommand {

    /**
     * <code>oldEndPoint</code>: The end point beeing dragged to the stub.
     */
    private EndPoint oldEndPoint;

    /**
     * <code>stubOrJoin</code>: The stub/join where the end point will get merged.
     */
    private PathNode stubOrJoin;

    /**
     * <code>oldX</code>: The old coordinates of the end point.
     */
    private int oldX, oldY;

    /**
     * end point's parent componentref. 
     */
    private ComponentRef oldParent;
    
    /**
     * <code>ncOldEnd</code>: The connection going from the end point initialy.
     */
    private NodeConnection ncOldEnd;

    /**
     * The pathgraph containing the nodes and connections
     */
    private PathGraph pg;

    /**
     * @param oldEndPoint
     *            The end point beeing dragged to the stub/join.
     * @param stubOrJoin
     *            The stub/join where the end point will get merged.
     */
    public JoinEndToStubJoinCommand(EndPoint oldEndPoint, PathNode stubOrJoin) {
        super();
        this.oldEndPoint = oldEndPoint;
        this.stubOrJoin = stubOrJoin;
    }

    /**
     * Disable the default constructor.
     */
    private JoinEndToStubJoinCommand() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (oldEndPoint != null && stubOrJoin != null)
            return true;
        else
            return false;
    }
    
    /* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	public boolean canUndo() {
		// Make sure we can undo even if we don't have any added commands
		if(getCommands().size() == 0)
			return true;
		return super.canUndo();
	}

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        oldX = oldEndPoint.getX();
        oldY = oldEndPoint.getY();

        pg = oldEndPoint.getPathGraph();
        ncOldEnd = (NodeConnection) oldEndPoint.getPred().get(0);
        oldParent = oldEndPoint.getCompRef();
        
        List outs = oldEndPoint.getOutBindings();
        for (Iterator i = outs.iterator(); i.hasNext();) {
			OutBinding out = (OutBinding) i.next();
			Command cmd = new DeleteOutBindingCommand(out);
			add(cmd);
		}
        
        testPreConditions();
        
        doRedo();
        super.execute();
        
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
    	testPreConditions();
    	
        doRedo();
        
        super.redo();
        
        testPostConditions();
    }

    /**
	 * 
	 */
	private void doRedo() {
		ncOldEnd.setTarget(stubOrJoin);
        pg.getPathNodes().remove(oldEndPoint);
        oldEndPoint.setCompRef(null);
	}

	/*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
    	testPostConditions();
    	
    	super.undo();
    	
        pg.getPathNodes().add(oldEndPoint);

        ncOldEnd.setTarget(oldEndPoint);

        oldEndPoint.setX(oldX);
        oldEndPoint.setY(oldY);
        oldEndPoint.setCompRef(oldParent);
        
        testPreConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert oldEndPoint != null : "pre old end point"; //$NON-NLS-1$
        assert stubOrJoin != null : "pre stub"; //$NON-NLS-1$
        assert ncOldEnd != null : "pre old node connection"; //$NON-NLS-1$
        assert pg != null : "pre pathgraph"; //$NON-NLS-1$

        assert oldEndPoint.getX() == oldX && oldEndPoint.getY() == oldY : "pre old end position"; //$NON-NLS-1$
        assert ncOldEnd.getTarget() == oldEndPoint : "pre connection source is the end point"; //$NON-NLS-1$
        assert pg.getPathNodes().contains(oldEndPoint) : "pre pathgraph contains the end point"; //$NON-NLS-1$
        assert pg.getNodeConnections().contains(ncOldEnd) : "pre pathgraph contains the connection"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert oldEndPoint != null : "post old end point"; //$NON-NLS-1$
        assert stubOrJoin != null : "post stub"; //$NON-NLS-1$
        assert ncOldEnd != null : "post old node connection"; //$NON-NLS-1$
        assert pg != null : "post pathgraph"; //$NON-NLS-1$

        assert ncOldEnd.getTarget() == stubOrJoin : "post connection source is the stub"; //$NON-NLS-1$
        assert !pg.getPathNodes().contains(oldEndPoint) : "post pathgraph doesn't contain the end point"; //$NON-NLS-1$
        assert pg.getNodeConnections().contains(ncOldEnd) : "post pathgraph contains the connection"; //$NON-NLS-1$
    }

}