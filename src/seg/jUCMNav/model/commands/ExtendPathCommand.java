/*
 * Created on 2005-02-25
 *
 */
package seg.jUCMNav.model.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ucm.EndPoint;
import seg.jUCMNav.model.ucm.Link;
import seg.jUCMNav.model.ucm.Node;
import seg.jUCMNav.model.ucm.Path;
import seg.jUCMNav.model.ucm.UcmDiagram;
import seg.jUCMNav.model.ucm.UcmFactory;

/**
 * Created 2005-02-25
 * 
 * @author Etienne Tremblay
 */
public class ExtendPathCommand extends Command {
	
	private UcmDiagram diagram; // The UCM diagram
	private Path path; // The path to extend
	
	private Node lastNode; // The last node before the end node
	
	private EndPoint newEnd; // The new end node
	private EndPoint lastEnd; // The last end node before the extension
	
	private Node newNode; // The new node required to replace the last end node
	
	private Link lastLink; // The last link connection lastNode and lastEnd
	
	private Link newLink1; // The new link connecting the lastNode and the newNode
	private Link newLink2; // The new link connecting the newNode and the newEnd
	
	private Point location; // Location of the new end.

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		UcmFactory factory = UcmFactory.eINSTANCE;
		
		lastEnd = path.getEndpoint();
		lastLink = lastEnd.getUpLink();
		
		lastNode = lastLink.getSource();
		
		diagram = lastEnd.getDiagram();
		
		newLink1 = factory.createLink();
		newLink2 = factory.createLink();
		
		newNode = factory.createNode();
		newNode.setX(lastEnd.getX());
		newNode.setY(lastEnd.getY());
		
		newEnd.setX(location.x);
		newEnd.setY(location.y);
		redo();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		
		//Remove last link
		lastLink.setSource(null);
		lastLink.setTarget(null);
		diagram.getLinks().remove(lastLink);
		
		// Remove the unused end and add the new node + new end
		diagram.getNodes().remove(lastEnd);
		diagram.getNodes().add(newNode);
		diagram.getNodes().add(newEnd);
		
		// Update the path
		path.setEndpoint(newEnd);
		path.getNodes().add(newNode);

		// Setup the new two links connecting the new node + new end
		newLink1.setSource(lastNode);
		newLink1.setTarget(newNode);
		diagram.getLinks().add(newLink1);
		
		newLink2.setSource(newNode);
		newLink2.setTarget(newEnd);
		diagram.getLinks().add(newLink2);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		// Delete the new two links
		newLink2.setSource(null);
		newLink2.setTarget(null);
		diagram.getLinks().remove(newLink2);
		
		newLink1.setSource(null);
		newLink1.setTarget(null);
		diagram.getLinks().remove(newLink1);
		
		// Update the path
		path.getNodes().remove(newNode);
		path.setEndpoint(lastEnd);
		
		// Remove the added nodes and recover the lastEnd
		diagram.getNodes().remove(newEnd);
		diagram.getNodes().remove(newNode);
		diagram.getNodes().add(lastEnd);
		
		// Recover last link
		lastLink.setSource(lastNode);
		lastLink.setTarget(lastEnd);
		diagram.getLinks().add(lastLink);
	}
	
	/**
	 * @return Returns the diagram.
	 */
	public UcmDiagram getDiagram() {
		return diagram;
	}
	/**
	 * @param diagram The diagram to set.
	 */
	public void setDiagram(UcmDiagram diagram) {
		this.diagram = diagram;
	}
	/**
	 * @return Returns the path.
	 */
	public Path getPath() {
		return path;
	}
	/**
	 * @param path The path to set.
	 */
	public void setPath(Path path) {
		this.path = path;
	}
	/**
	 * @return Returns the location.
	 */
	public Point getLocation() {
		return location;
	}
	/**
	 * @param location The location to set.
	 */
	public void setLocation(Point location) {
		this.location = location;
	}
	/**
	 * @return Returns the newEnd.
	 */
	public EndPoint getNewEnd() {
		return newEnd;
	}
	/**
	 * @param newEnd The newEnd to set.
	 */
	public void setNewEnd(EndPoint newEnd) {
		this.newEnd = newEnd;
	}
}
