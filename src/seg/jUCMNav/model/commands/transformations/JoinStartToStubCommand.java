/*
 * Created on May 27, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.StartPoint;
import ucm.map.Stub;

/**
 * @author TremblaE
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JoinStartToStubCommand extends Command implements JUCMNavCommand {

    private StartPoint oldStartPoint;

    private Stub stub;

    private int oldX, oldY;

    private NodeConnection ncOldStart;

    private PathGraph pg;
    
	/**
	 * @param oldEndPoint
	 * @param stub
	 * @param ncOldEnd
	 */
	public JoinStartToStubCommand(StartPoint oldEndPoint, Stub stub) {
		super();
		this.oldStartPoint = oldEndPoint;
		this.stub = stub;
	}
	/**
	 * 
	 */
	private JoinStartToStubCommand() {
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
		oldX = oldStartPoint.getX();
		oldY = oldStartPoint.getY();
		
		pg = oldStartPoint.getPathGraph();
		ncOldStart = (NodeConnection)oldStartPoint.getSucc().get(0);
		
		redo();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		ncOldStart.setSource(stub);
		pg.getPathNodes().remove(oldStartPoint);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		pg.getPathNodes().add(oldStartPoint);
		ncOldStart.setSource(oldStartPoint);
		oldStartPoint.setX(oldX);
		oldStartPoint.setY(oldY);
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
