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
	private int deltaX;
	private int deltaY;
	
	public boolean canExecute() {
		return node != null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		if(node.getName() == null) {
			String[] fullClassName = node.getClass().getName().split("\\.");
            String className = fullClassName[fullClassName.length-1];
            className = className.substring(0, className.length()-4);
            node.setName(className);
		}

        UrncoreFactory factory = UrncoreFactory.eINSTANCE;
	    label = factory.createNodeLabel();

	    label.setPathNode(node);
	    
	    label.setDeltaX(deltaX);
	    label.setDeltaY(deltaY);
		
		redo();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		testPreConditions();
		
		node.setLabel(label);
		
        testPostConditions();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		testPostConditions();

		node.setLabel(null);

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
        //assert label.getPathNode() == null : "pre NodeLabel not connected to a PathNode";
        //assert node.getLabel() == null : "pre PathNode not connected to a NodeLabel";
        
        assert label.getDeltaX() == deltaX : "pre NodeLabel deltaX";
        assert label.getDeltaY() == deltaY : "pre NodeLabel deltaY";
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
    	assert label != null : "pre NodeLabel";
        assert node != null : "pre PathNode";
        assert label.getPathNode().equals(node) : "pre NodeLabel connected to correct PathNode";
        assert node.getLabel().equals(label) : "pre PathNode connected to correct NodeLabel";
        
        assert label.getDeltaX() == deltaX : "pre NodeLabel deltaX";
        assert label.getDeltaY() == deltaY : "pre NodeLabel deltaY";
    }
	
	
    /**
     * @param node The node to set.
     */
    public void setNode(PathNode node) {
        this.node = node;
    }

	/**
	 * @param deltaX The deltaX to set.
	 */
	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}
	
	/**
	 * @param deltaY The deltaY to set.
	 */
	public void setDeltaY(int deltaY) {
		this.deltaY = deltaY;
	}
}

