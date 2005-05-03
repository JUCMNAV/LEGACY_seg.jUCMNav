/*
 * Created on May 3, 2005
 */
package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.Command;

import ucm.map.PathNode;
import urncore.NodeLabel;

/**
 * @author Jordan
 */
public class DeleteLabelCommand extends Command {
	private static final String	CreateCommand_Label = "DeleteLabelCommand";
	private NodeLabel label;
	private PathNode node;
	
	public boolean canExecute() {
		return label != null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		node = label.getPathNode();
		
		redo();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		testPreConditions();
		
		node.setLabel(null);
		
        testPostConditions();
	}
	
	public boolean canUndo() {
		return label != null && node.getLabel() == null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		testPostConditions();

		node.setLabel(label);

        testPreConditions();
	}
	
	/*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert label != null : "pre NodeLabel";
        assert node != null : "pre PathNode";
        //assert label.getPathNode() != null : "pre NodeLabel not connected to a PathNode";
        //assert node.getLabel() != null : "pre PathNode not connected to a NodeLabel";
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
    	assert label != null : "pre NodeLabel";
        assert node != null : "pre PathNode";
        //assert label.getPathNode().equals(node) : "pre NodeLabel connected to correct PathNode";
        //assert node.getLabel().equals(label) : "pre PathNode connected to correct NodeLabel";
    }
    
    
	/**
	 * @param label The label to set.
	 */
	public void setNodeLabel(NodeLabel label) {
		this.label = label;
	}
}
