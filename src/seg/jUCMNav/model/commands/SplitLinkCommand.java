/*
 * Created on 2005-02-25
 *
 */
package seg.jUCMNav.model.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import ucm.map.MapFactory;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;

/**
 * Created 2005-02-25
 * 
 * @author Etienne Tremblay
 */
public class SplitLinkCommand extends Command {
	
	private PathGraph diagram; // The UCM diagram
	
	private NodeConnection oldLink; // The old link where we are inserting
	
	private PathNode node; // The new node we are inserting
	private PathNode previousNode; // The node before the new node
	private PathNode nextNode; // The node following the new node
	private NodeConnection newLink1; // The two new links for the new node
	private NodeConnection newLink2;
	
	private Point location;

	/**
	 * 
	 */
	public SplitLinkCommand() {
		super();
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
		MapFactory factory = MapFactory.eINSTANCE;
		
		previousNode = oldLink.getSource();
		nextNode = oldLink.getTarget();
		
		newLink1 = factory.createNodeConnection();
		newLink2 = factory.createNodeConnection();
		
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
		diagram.getNodeConnections().remove(oldLink);
		
		previousNode.getSucc().add(newLink1);
		node.getPred().add(newLink1);
		node.getSucc().add(newLink2);
		nextNode.getPred().add(newLink2);
		
		diagram.getPathNodes().add(node);
		diagram.getNodeConnections().add(newLink1);
		diagram.getNodeConnections().add(newLink2);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		diagram.getNodeConnections().remove(newLink1);
		diagram.getNodeConnections().remove(newLink2);
		diagram.getPathNodes().remove(node);

		nextNode.getPred().remove(newLink2);
		previousNode.getSucc().remove(newLink1);
		nextNode.getPred().add(oldLink);
		previousNode.getSucc().add(oldLink);
		
		
		diagram.getNodeConnections().add(oldLink);
	}
	/**
	 * @return Returns the oldLink.
	 */
	public NodeConnection getOldLink() {
		return oldLink;
	}
	/**
	 * @param oldLink The oldLink to set.
	 */
	public void setOldLink(NodeConnection oldLink) {
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
	public PathGraph getDiagram() {
		return diagram;
	}
	/**
	 * @param diagram The diagram to set.
	 */
	public void setDiagram(PathGraph diagram) {
		this.diagram = diagram;
	}
	/**
	 * @return Returns the node.
	 */
	public PathNode getNode() {
		return node;
	}
	/**
	 * @param node The node to set.
	 */
	public void setNode(PathNode node) {
		this.node = node;
	}
}
