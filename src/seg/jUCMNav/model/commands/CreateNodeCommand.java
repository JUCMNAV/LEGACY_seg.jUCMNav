/*
 * Created on 2005-01-30
 *
 */
package seg.jUCMNav.model.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ucm.UcmDiagram;
import seg.jUCMNav.model.ucm.XYElement;

/**
 * @author Etienne Tremblay
 *
 */
public class CreateNodeCommand extends Command {
	
	private static final String	CreateCommand_Label = "CreateNodeCommand";
	private XYElement node;
	private Point location;
	private UcmDiagram diagram;
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		if(location != null){
			node.setX(location.x);
			node.setY(location.y);
		}
	}
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
	}
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
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
}
