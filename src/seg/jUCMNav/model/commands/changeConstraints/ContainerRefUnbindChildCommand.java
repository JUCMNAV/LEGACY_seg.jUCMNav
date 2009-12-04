package seg.jUCMNav.model.commands.changeConstraints;

import java.util.List;
import java.util.Vector;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urncore.IURNContainerRef;
import urncore.IURNNode;
import urncore.URNmodelElement;

/**
 * Unbinds a child/children from a parent ContainerRef.
 * 
 * @author jkealey
 * 
 */
public class ContainerRefUnbindChildCommand extends Command implements JUCMNavCommand {

    private IURNContainerRef parent;

    private Vector children;

    /**
     * 
     * @param parent
     *            The component to which the child is bound and will move with.
     * @param child
     *            Either a PathNode or a ComponentRef.
     */
    public ContainerRefUnbindChildCommand(IURNContainerRef parent, URNmodelElement child) {
        this.parent = parent;
        this.children = new Vector();
        this.children.add(child);
        setLabel(Messages.getString("ContainerRefUnbindChildCommand.unbindContainerReference")); //$NON-NLS-1$
    }

    /**
     * 
     * @param parent
     *            The component to which the child is bound and will move with.
     * @param children
     *            A list of either PathNodes or ComponentRefs.
     */
    public ContainerRefUnbindChildCommand(IURNContainerRef parent, List children) {
        this.parent = parent;
        this.children = new Vector();
        this.children.addAll(children);
    }

    /**
     * Children must be a PathNode or ComponentRef
     */
    public boolean canExecute() {
        boolean b = children.size() > 0;
        for (int i = 0; i < children.size(); i++) {
            b = b && (children.get(i) instanceof IURNNode || children.get(i) instanceof IURNContainerRef);
        }

        return b;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        for (int i = 0; i < children.size(); i++) {
            Object child = children.get(i);
            if (child instanceof IURNNode)
                parent.getNodes().remove(child);
            else if (child instanceof IURNContainerRef)
                parent.getChildren().remove(child);
        }
        testPostConditions();

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        for (int i = 0; i < children.size(); i++) {
            Object child = children.get(i);
            if (child instanceof IURNNode)
                parent.getNodes().add(child);
            else if (child instanceof IURNContainerRef)
                parent.getChildren().add(child);
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
            URNmodelElement child = (URNmodelElement) children.get(i);
            assert (child instanceof IURNContainerRef && parent.getChildren().contains(child))
                    || (child instanceof IURNNode && parent.getNodes().contains(child)) : "pre is bound: " + i; //$NON-NLS-1$
        }
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert canExecute() : "post can execute"; //$NON-NLS-1$
        assert parent != null : "post parent null"; //$NON-NLS-1$
        for (int i = 0; i < children.size(); i++) {
            URNmodelElement child = (URNmodelElement) children.get(i);
            assert !parent.getChildren().contains(child) && !parent.getNodes().contains(child) : "post is not bound: " + i; //$NON-NLS-1$
        }
    }

    /**
     * @return Returns the parent.
     */
    public IURNContainerRef getParent() {
        return parent;
    }

    /**
     * @param parent
     *            The parent to set.
     */
    public void setParent(IURNContainerRef parent) {
        this.parent = parent;
    }
}