/*
 * Created on 2005-02-25
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.model.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ucm.Link;
import seg.jUCMNav.model.ucm.Node;
import seg.jUCMNav.model.ucm.UcmDiagram;
import seg.jUCMNav.model.ucm.UcmFactory;

/**
 * Created 2005-02-25
 * 
 * @author Etienne Tremblay
 */
public class SplitLinkCommand extends Command {
	
	private UcmDiagram diagram; // The UCM diagram
	
	private Link oldLink; // The old link where we are inserting
	
	private Node node; // The new node we are inserting
	private Node previousNode; // The node before the new node
	private Node nextNode; // The node following the new node
	private Link newLink1; // The two new links for the new node
	private Link newLink2;
	
	private Point location;

	/**
	 * 
	 */
	public SplitLinkCommand() {
		super();
		// TODO Auto-generated constructor stub
	}

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
		
		previousNode = oldLink.getSource();
		nextNode = oldLink.getTarget();
		
		diagram = previousNode.getDiagram();
		
		newLink1 = factory.createLink();
		newLink2 = factory.createLink();
		
		node.setX(location.x);
		node.setY(location.y);
		redo();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		oldLink.setSource(null);
		oldLink.setTarget(null);
		diagram.getLinks().remove(oldLink);
		
		previousNode.setDownLink(newLink1);
		node.setUpLink(newLink1);
		node.setDownLink(newLink2);
		nextNode.setUpLink(newLink2);
		
		diagram.getNodes().add(node);
		diagram.getLinks().add(newLink1);
		diagram.getLinks().add(newLink2);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		diagram.getLinks().remove(newLink1);
		diagram.getLinks().remove(newLink2);
		diagram.getNodes().remove(node);
		
		nextNode.setUpLink(oldLink);
		previousNode.setDownLink(oldLink);
		
		diagram.getLinks().add(oldLink);
	}
	/**
	 * @return Returns the oldLink.
	 */
	public Link getOldLink() {
		return oldLink;
	}
	/**
	 * @param oldLink The oldLink to set.
	 */
	public void setOldLink(Link oldLink) {
		this.oldLink = oldLink;
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
	 * @return Returns the node.
	 */
	public Node getNode() {
		return node;
	}
	/**
	 * @param node The node to set.
	 */
	public void setNode(Node node) {
		this.node = node;
	}
}
