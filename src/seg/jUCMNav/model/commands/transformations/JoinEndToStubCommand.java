package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.Stub;

/**
 * Created 27-05-2005
 * 
 * This command represents the action of a user dragging an EndPoint in a Stub. This action will erase the EndPoint and add the path as a predecessor of the
 * Stub.
 * 
 * @author Etienne Tremblay
 */
public class JoinEndToStubCommand extends Command implements JUCMNavCommand {

	/**
	 * <code>oldEndPoint</code>: The end point beeing dragged to the stub.
	 */
	private EndPoint oldEndPoint;

	/**
	 * <code>stub</code>: The stub where the end point will get merged.
	 */
	private Stub stub;

	/**
	 * <code>oldX</code>: The old coordinates of the end point.
	 */
	private int oldX, oldY;

	/**
	 * <code>ncOldEnd</code>: The connection going from the end point initialy.
	 */
	private NodeConnection ncOldEnd;

	private PathGraph pg;

	/**
	 * @param oldEndPoint
	 *            The end point beeing dragged to the stub.
	 * @param stub
	 *            The stub where the end point will get merged.
	 */
	public JoinEndToStubCommand(EndPoint oldEndPoint, Stub stub) {
		super();
		this.oldEndPoint = oldEndPoint;
		this.stub = stub;
	}

	/**
	 * Disable the default constructor.
	 */
	private JoinEndToStubCommand() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		if (oldEndPoint != null && stub != null)
			return true;
		else
			return false;
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

		redo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		ncOldEnd.setTarget(stub);
		pg.getPathNodes().remove(oldEndPoint);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		pg.getPathNodes().add(oldEndPoint);

		ncOldEnd.setTarget(oldEndPoint);

		oldEndPoint.setX(oldX);
		oldEndPoint.setY(oldY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
	 */
	public void testPreConditions() {
		assert oldEndPoint != null : "pre old end point";
		assert stub != null : "pre stub";
		assert ncOldEnd != null : "pre old node connection";
		assert pg != null : "pre pathgraph";

		assert oldEndPoint.getX() == oldX && oldEndPoint.getY() == oldY : "pre old end position";
		assert ncOldEnd.getSource() == oldEndPoint : "pre connection source is the end point";
		assert pg.getPathNodes().contains(oldEndPoint) : "pre pathgraph contains the end point";
		assert pg.getNodeConnections().contains(ncOldEnd) : "pre pathgraph contains the connection";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
	 */
	public void testPostConditions() {
		assert oldEndPoint != null : "post old end point";
		assert stub != null : "post stub";
		assert ncOldEnd != null : "post old node connection";
		assert pg != null : "post pathgraph";

		assert ncOldEnd.getSource() == stub : "post connection source is the stub";
		assert !pg.getPathNodes().contains(oldEndPoint) : "post pathgraph doesn't contain the end point";
		assert pg.getNodeConnections().contains(ncOldEnd) : "post pathgraph contains the connection";
	}

}