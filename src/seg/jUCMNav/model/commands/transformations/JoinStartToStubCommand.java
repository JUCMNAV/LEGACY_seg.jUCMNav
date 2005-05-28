package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.StartPoint;
import ucm.map.Stub;

/**
 * Created 27-05-2005
 * 
 * This command represents the action of a user dragging a StartPoint in a Stub. This action will erase the StartPoint and add the path as a successor of the
 * stub.
 * 
 * @author Etienne Tremblay
 */
public class JoinStartToStubCommand extends Command implements JUCMNavCommand {

	/**
	 * <code>oldStartPoint</code>: The start point beeing dragged to the stub.
	 */
	private StartPoint oldStartPoint;

	/**
	 * <code>stub</code>: The stub where the start point will get merged.
	 */
	private Stub stub;

	/**
	 * <code>oldX, oldY</code>: The old coordinates of the start point.
	 */
	private int oldX, oldY;

	/**
	 * <code>ncOldStart</code>: The connection going from the start point initialy.
	 */
	private NodeConnection ncOldStart;

	private PathGraph pg;

	/**
	 * @param oldStartPoint
	 *            The start point beeing dragged to the stub.
	 * @param stub
	 *            The stub where the start point will get merged.
	 */
	public JoinStartToStubCommand(StartPoint oldStartPoint, Stub stub) {
		super();
		this.oldStartPoint = oldStartPoint;
		this.stub = stub;
	}

	/**
	 * Disable the default constructor.
	 */
	private JoinStartToStubCommand() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		if (stub != null && oldStartPoint != null)
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
		// Remember the last position of the startpoint.
		oldX = oldStartPoint.getX();
		oldY = oldStartPoint.getY();

		pg = oldStartPoint.getPathGraph();

		ncOldStart = (NodeConnection) oldStartPoint.getSucc().get(0);

		redo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		testPreConditions();

		ncOldStart.setSource(stub);
		pg.getPathNodes().remove(oldStartPoint);

		testPostConditions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		testPostConditions();

		pg.getPathNodes().add(oldStartPoint);
		ncOldStart.setSource(oldStartPoint);

		oldStartPoint.setX(oldX);
		oldStartPoint.setY(oldY);

		testPreConditions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
	 */
	public void testPreConditions() {
		assert oldStartPoint != null : "pre old start point";
		assert stub != null : "pre stub";
		assert ncOldStart != null : "pre old node connection";
		assert pg != null : "pre pathgraph";

		assert oldStartPoint.getX() == oldX && oldStartPoint.getY() == oldY : "pre old start position";
		assert ncOldStart.getSource() == oldStartPoint : "pre connection source is the start point";
		assert pg.getPathNodes().contains(oldStartPoint) : "pre pathgraph contains the start point";
		assert pg.getNodeConnections().contains(ncOldStart) : "pre pathgraph contains the connection";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
	 */
	public void testPostConditions() {
		assert oldStartPoint != null : "post old start point";
		assert stub != null : "post stub";
		assert ncOldStart != null : "post old node connection";
		assert pg != null : "post pathgraph";

		assert ncOldStart.getSource() == stub : "post connection source is the stub";
		assert !pg.getPathNodes().contains(oldStartPoint) : "post pathgraph doesn't contain the start point";
		assert pg.getNodeConnections().contains(ncOldStart) : "post pathgraph contains the connection";
	}

}