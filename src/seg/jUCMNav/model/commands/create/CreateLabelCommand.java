/*
 * Created on Mar 30, 2005
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import ucm.map.PathNode;
import urncore.NodeLabel;
import urncore.UrncoreFactory;

/**
 * @author Jordan
 */
public class CreateLabelCommand extends Command {
    private static final String	CreateCommand_Label = "CreateLabelCommand";
	private NodeLabel label;
	private PathNode node;
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		if(node != null){
	        String[] fullClassName = node.getClass().getName().split("\\.");
            String className = fullClassName[fullClassName.length-1];
            className = className.substring(0, className.length()-4);
            node.setName(className);
            
	        UrncoreFactory factory = UrncoreFactory.eINSTANCE;
		    label = factory.createNodeLabel();

		    label.setPathNode(node);
		    node.setLabel(label);
		    label.setDeltaX(50);
		    label.setDeltaY(50);
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
     * @param node The node to set.
     */
    public void setNode(PathNode node) {
        this.node = node;
    }
}

