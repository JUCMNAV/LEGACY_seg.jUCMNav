/*
 * Created on 2005-03-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.model.commands;

import org.eclipse.gef.commands.Command;

import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.MapFactory;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.StartPoint;

/**
 * Created 2005-03-21
 * 
 * @author Etienne Tremblay
 */
public class CutPathCommand extends Command {
	
	private PathGraph diagram;
	private EmptyPoint emptyPoint;
	
	private PathNode nextPoint;
	private PathNode previousPoint;
	
	private StartPoint newStart;
	private EndPoint newEnd;
	
	private NodeConnection previousToNode;
	private NodeConnection nodeToNext;
	
	private NodeConnection toPrevious;
	private NodeConnection fromNext;

	/**
	 * 
	 */
	public CutPathCommand() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		MapFactory factory = MapFactory.eINSTANCE;
		
		newStart = factory.createStartPoint();
		newEnd = factory.createEndPoint();
		
		nodeToNext = (NodeConnection)emptyPoint.getSucc().get(0);
		nextPoint = nodeToNext.getTarget();
		
		previousToNode = (NodeConnection)emptyPoint.getPred().get(0);
		previousPoint = previousToNode.getSource();
		
		toPrevious = (NodeConnection)previousPoint.getPred().get(0);
		fromNext = (NodeConnection)nextPoint.getSucc().get(0);
		
		newStart.setX(nextPoint.getX());
		newStart.setY(nextPoint.getY());
		newEnd.setX(previousPoint.getX());
		newEnd.setY(previousPoint.getY());
		
		redo();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		diagram.getPathNodes().remove(emptyPoint);
		diagram.getPathNodes().remove(previousPoint);
		diagram.getPathNodes().remove(nextPoint);
		
		diagram.getNodeConnections().remove(previousToNode);
		diagram.getNodeConnections().remove(nodeToNext);
		
		diagram.getPathNodes().add(newStart);
		diagram.getPathNodes().add(newEnd);
		
		toPrevious.setTarget(newEnd);
		fromNext.setSource(newStart);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {		
		toPrevious.setTarget(previousPoint);
		fromNext.setSource(nextPoint);
		
		diagram.getPathNodes().remove(newStart);
		diagram.getPathNodes().remove(newEnd);
		
		diagram.getNodeConnections().add(previousToNode);
		diagram.getNodeConnections().add(nodeToNext);
		
		diagram.getPathNodes().add(emptyPoint);
		diagram.getPathNodes().add(previousPoint);
		diagram.getPathNodes().add(nextPoint);
	}
	
	/**
	 * @return Returns the emptyPoint.
	 */
	public EmptyPoint getEmptyPoint() {
		return emptyPoint;
	}
	/**
	 * @param emptyPoint The emptyPoint to set.
	 */
	public void setEmptyPoint(EmptyPoint emptyPoint) {
		this.emptyPoint = emptyPoint;
	}
	/**
	 * @return Returns the diagram.
	 */
	public PathGraph getDiagram() {
		return diagram;
	}
	/**
	 * @param diagram The diagram to set.
	 */
	public void setDiagram(PathGraph diagram) {
		this.diagram = diagram;
	}
}
