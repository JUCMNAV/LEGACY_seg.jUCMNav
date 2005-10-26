package seg.jUCMNav.model.commands.changeConstraints;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentRef;
import ucm.map.Connect;
import ucm.map.PathNode;
import urncore.UCMmodelElement;

/**
 * Binds a child/children (PathNode or ComponentRef) to a parent ComponentRef.
 * 
 * Doesn't bind Connects. See DevDocSpecialCaseConnects
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

        // don't bind Connects
        for (Iterator iter = children.iterator(); iter.hasNext();) {
            Object o = iter.next();
            if (!(o instanceof Connect))
                this.children.add(o);
        }

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

        // don't bind Connects        
        if (!(child instanceof Connect))
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

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        for (int i = 0; i < children.size(); i++) {
            UCMmodelElement child = (UCMmodelElement) children.get(i);
            if (child instanceof PathNode)
                parent.getNodes().add(child);
            else if (child instanceof ComponentRef)
                parent.getChildren().add(child);
        }

        testPostConditions();

    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        for (int i = 0; i < children.size(); i++) {
            UCMmodelElement child = (UCMmodelElement) children.get(i);
            if (child instanceof PathNode)
                parent.getNodes().remove(child);
            else if (child instanceof ComponentRef)
                parent.getChildren().remove(child);
        }
        testPreConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {

        assert canExecute() : "pre can execute"; //$NON-NLS-1$
        assert parent != null : "pre parent null"; //$NON-NLS-1$
        for (int i = 0; i < children.size(); i++) {
            UCMmodelElement child = (UCMmodelElement) children.get(i);
            assert !parent.getChildren().contains(child) && !parent.getNodes().contains(child) : "pre is not bound: " + i; //$NON-NLS-1$
        }

    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert canExecute() : "post can execute"; //$NON-NLS-1$
        assert parent != null : "post parent null"; //$NON-NLS-1$
        for (int i = 0; i < children.size(); i++) {
            UCMmodelElement child = (UCMmodelElement) children.get(i);
            assert (child instanceof ComponentRef && parent.getChildren().contains(child))
                    || (child instanceof PathNode && parent.getNodes().contains(child)) : "post is bound: " + i; //$NON-NLS-1$
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