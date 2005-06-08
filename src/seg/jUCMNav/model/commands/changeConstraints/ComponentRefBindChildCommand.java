package seg.jUCMNav.model.commands.changeConstraints;

import java.util.List;
import java.util.Vector;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import urncore.UCMmodelElement;

/**
 * Created on 28-Apr-2005
 * 
 * Binds a child/children (PathNode or ComponentRef) to a parent ComponentRef.
 * 
 * @author jkealey
 *  
 */
public class ComponentRefBindChildCommand extends Command implements JUCMNavCommand {

    private ComponentRef parent;
    private Vector children;

    /**
     * 
     * @param parent
     *            The component to which the child is bound and will move with.
     * @param children
     *            A list of either PathNodes or ComponentRef.
     */
    public ComponentRefBindChildCommand(ComponentRef parent, List children) {
        this.parent = parent;
        this.children = new Vector();
        this.children.addAll(children);

    }

    /**
     * 
     * @param parent
     *            The component to which the child is bound and will move with.
     * @param child
     *            Either a PathNode or a ComponentRef.
     */
    public ComponentRefBindChildCommand(ComponentRef parent, UCMmodelElement child) {
        this.parent = parent;
        this.children = new Vector();
        this.children.add(child);

    }

    /**
     * Children must be PathNodes or ComponentRefs
     */
    public boolean canExecute() {
        for (int i = 0; i < children.size(); i++) {
            if (!(children.get(i) instanceof PathNode || children.get(i) instanceof ComponentRef))
                return false;
        }

        return true;
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

        for (int i = 0; i < children.size(); i++) {
            UCMmodelElement child = (UCMmodelElement) children.get(i);
            if (child instanceof PathNode)
                parent.getPathNodes().add(child);
            else if (child instanceof ComponentRef)
                parent.getChildren().add(child);
        }

        testPostConditions();

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        for (int i = 0; i < children.size(); i++) {
            UCMmodelElement child = (UCMmodelElement) children.get(i);
            if (child instanceof PathNode)
                parent.getPathNodes().remove(child);
            else if (child instanceof ComponentRef)
                parent.getChildren().remove(child);
        }
        testPreConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {

        assert canExecute() : "pre can execute"; //$NON-NLS-1$
        assert parent != null : "pre parent null"; //$NON-NLS-1$
        for (int i = 0; i < children.size(); i++) {
            UCMmodelElement child = (UCMmodelElement) children.get(i);
            assert !parent.getChildren().contains(child) && !parent.getPathNodes().contains(child) : "pre is not bound: " + i; //$NON-NLS-1$
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert canExecute() : "post can execute"; //$NON-NLS-1$
        assert parent != null : "post parent null"; //$NON-NLS-1$
        for (int i = 0; i < children.size(); i++) {
            UCMmodelElement child = (UCMmodelElement) children.get(i);
            assert (child instanceof ComponentRef && parent.getChildren().contains(child))
                    || (child instanceof PathNode && parent.getPathNodes().contains(child)) : "post is bound: " + i; //$NON-NLS-1$
        }
    }

    /**
     * @return Returns the parent.
     */
    public ComponentRef getParent() {
        return parent;
    }

    /**
     * @param parent
     *            The parent to set.
     */
    public void setParent(ComponentRef parent) {
        this.parent = parent;
    }
}