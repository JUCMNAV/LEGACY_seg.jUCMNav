/*
 * Created on 2005-02-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
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
	
	private UcmDiagram diagram;
	private Path path;
	
	private Node lastLastNode;
	private Node lastNode;
	
	private EndPoint newEnd;
	private EndPoint lastEnd;
	
	private Node newNode;
	
	private Link lastLastLink;
	private Link lastLink;
	
	private Link newLink1;
	private Link newLink2;
	private Link newLink3;
	
	private Point location;

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
		
		diagram.getNodes().remove(lastEnd);
		diagram.getNodes().add(newNode);
		diagram.getNodes().add(newEnd);
		
		path.setEndpoint(newEnd);

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
