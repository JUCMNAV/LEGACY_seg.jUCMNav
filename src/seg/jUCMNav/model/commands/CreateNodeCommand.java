/*
 * Created on 2005-01-30
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.model.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ucm.Path;
import seg.jUCMNav.model.ucm.XYElement;

/**
 * @author Etienne Tremblay
 *
 */
public class CreateNodeCommand extends Command {
	
	private static final String	CreateCommand_Label = "CreateNodeCommand";
	private XYElement node;
	private Point location;
	private Path path;
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		path.getNodes().add(node);
		if(location != null){
			node.setX(location.x);
			node.setY(location.y);
		}
	}
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		path.getNodes().add(node);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		path.getNodes().remove(node);
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
	 * @return Returns the node.
	 */
	public XYElement getNode() {
		return node;
	}
	/**
	 * @param node The node to set.
	 */
	public void setNode(XYElement node) {
		this.node = node;
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
}
