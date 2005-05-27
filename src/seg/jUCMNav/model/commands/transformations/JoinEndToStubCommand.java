/*
 * Created on May 27, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.Stub;

/**
 * @author TremblaE
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JoinEndToStubCommand extends Command implements JUCMNavCommand {

    private EndPoint oldEndPoint;

    private Stub stub;

    private int oldX, oldY;

    private NodeConnection ncOldEnd;

    private PathGraph pg;

	/**
	 * @param oldEndPoint
	 * @param stub
	 * @param ncOldEnd
	 */
	public JoinEndToStubCommand(EndPoint oldEndPoint, Stub stub) {
		super();
		this.oldEndPoint = oldEndPoint;
		this.stub = stub;
	}
	/**
	 * 
	 */
	private JoinEndToStubCommand() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		return super.canExecute();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		oldX = oldEndPoint.getX();
		oldY = oldEndPoint.getY();
		
		pg = oldEndPoint.getPathGraph();
		ncOldEnd = (NodeConnection)oldEndPoint.getPred().get(0);
		
		redo();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		ncOldEnd.setTarget(stub);
		pg.getPathNodes().remove(oldEndPoint);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		pg.getPathNodes().add(oldEndPoint);
		ncOldEnd.setTarget(oldEndPoint);
		oldEndPoint.setX(oldX);
		oldEndPoint.setY(oldY);
	}
	
	/* (non-Javadoc)
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
	 */
	public void testPreConditions() {

	}

	/* (non-Javadoc)
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
	 */
	public void testPostConditions() {

	}

}
