/*
 * Created on Mar 29, 2005
 */
package seg.jUCMNav.model.commands;

import org.eclipse.gef.commands.Command;

import ucm.map.PathGraph;
import ucm.map.PathNodeProxy;


/**
 * @author Jordan
 */
public class CreateLabelCommand extends Command {
    private static final String	CreateCommand_Label = "CreateLabelCommand";
	private PathNodeProxy node;
	private PathGraph diagram;
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		if(node != null){
			node.setLabelX(node.getNodeX() - 50);
			node.setLabelY(node.getNodeY() - 50);
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
    public PathNodeProxy getNode() {
        return node;
    }
    /**
     * @param node The node to set.
     */
    public void setNode(PathNodeProxy proxy) {
        this.node = proxy;
    }
}

