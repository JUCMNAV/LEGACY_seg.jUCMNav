package seg.jUCMNav.model.commands.changeConstraints;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import urncore.UCMmodelElement;

/**
 * Created on 28-Apr-2005
 * Binds a child (PathNode or ComponentRef) to a parent ComponentRef. 
 * 
 * @author jkealey
 *  
 */
public class ComponentRefBindChildCommand extends Command implements JUCMNavCommand {

    private ComponentRef parent;
    private UCMmodelElement child;

    /**
     * 
     * @param parent The component to which the child is bound and will move with.  
     * @param child Either a PathNode or a ComponentRef.
     */
    public ComponentRefBindChildCommand(ComponentRef parent, UCMmodelElement child) {
        this.parent = parent;
        this.child = child;	
    }


    /**
     * Child must be a PathNode or ComponentRef
     */
    public boolean canExecute() {
        return child instanceof PathNode || child instanceof ComponentRef;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        
        if (child instanceof PathNode)
            parent.getPathNodes().add(child);
        else if (child instanceof ComponentRef)
            parent.getChildren().add(child);
        
        testPostConditions();

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        
        if (child instanceof PathNode)
            parent.getPathNodes().remove(child);
        else if (child instanceof ComponentRef)
            parent.getChildren().remove(child);
        
        testPreConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        
        assert canExecute() : "pre can execute";
        assert !parent.getChildren().contains(child) && !parent.getPathNodes().contains(child) : "pre is not bound";
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert canExecute() : "post can execute";
        assert (child instanceof ComponentRef && parent.getChildren().contains(child)) || (child instanceof PathNode && parent.getPathNodes().contains(child)) : "post is bound";
    }

    /**
     * @return Returns the child.
     */
    public UCMmodelElement getChild() {
        return child;
    }
    /**
     * @param child The child to set.
     */
    public void setChild(UCMmodelElement child) {
        this.child = child;
    }
    /**
     * @return Returns the parent.
     */
    public ComponentRef getParent() {
        return parent;
    }
    /**
     * @param parent The parent to set.
     */
    public void setParent(ComponentRef parent) {
        this.parent = parent;
    }
}