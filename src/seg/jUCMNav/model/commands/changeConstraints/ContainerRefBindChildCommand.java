package seg.jUCMNav.model.commands.changeConstraints;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import urncore.IURNContainerRef;
import urncore.IURNNode;
import urncore.URNmodelElement;

/**
 * Binds a child/children to a parent ContainerRef.
 * 
 * Doesn't bind Connects. See DevDocSpecialCaseConnects
 * 
 * @author jkealey
 * 
 */
public class ContainerRefBindChildCommand extends Command implements JUCMNavCommand {

    private IURNContainerRef parent;

    private Vector children;

    private boolean delayed=false;
    
    public ContainerRefBindChildCommand(IURNContainerRef parent)
    {
        this.delayed=true;
        this.parent=parent;
        children = new Vector();
        setLabel(Messages.getString("ContainerRefBindChildCommand.bindContainerReference")); //$NON-NLS-1$
    }
    
    /**
     * 
     * @param parent
     *            The component to which the child is bound and will move with.
     * @param children
     *            A list of either PathNodes or ComponentRef.
     */
    public ContainerRefBindChildCommand(IURNContainerRef parent, List children) {
        this.parent = parent;
        this.children = new Vector();

        // don't bind Connects
        for (Iterator iter = children.iterator(); iter.hasNext();) {
            Object o = iter.next();
            // bug 721 - we bind them now.  
            //if (!(o instanceof Connect))
                this.children.add(o);
        }
        setLabel(Messages.getString("ContainerRefBindChildCommand.bindContainerReference")); //$NON-NLS-1$

    }

    /**
     * 
     * @param parent
     *            The component to which the child is bound and will move with.
     * @param child
     *            Either a PathNode or a ComponentRef.
     */
    public ContainerRefBindChildCommand(IURNContainerRef parent, URNmodelElement child) {
        this.parent = parent;
        this.children = new Vector();

        // don't bind Connects
        // bug 721 - we bind them now.  

        //if (!(child instanceof Connect))
            this.children.add(child);

    }

    /**
     * Children must be IURNNodes or IURNContainerRefs
     */
    public boolean canExecute() {
        for (int i = 0; i < children.size(); i++) {
            if (!(children.get(i) instanceof IURNNode || children.get(i) instanceof IURNContainerRef))
                return false;
        }

        return true;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (delayed && parent.getDiagram()!=null)
        {
            children = ParentFinder.findNewChildren(parent.getDiagram(), parent);
        }
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        for (int i = 0; i < children.size(); i++) {
            URNmodelElement child = (URNmodelElement) children.get(i);
            if (child instanceof IURNNode)
                parent.getNodes().add(child);
            else if (child instanceof IURNContainerRef)
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
            URNmodelElement child = (URNmodelElement) children.get(i);
            if (child instanceof IURNNode)
                parent.getNodes().remove(child);
            else if (child instanceof IURNContainerRef)
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
            URNmodelElement child = (URNmodelElement) children.get(i);
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
            URNmodelElement child = (URNmodelElement) children.get(i);
            assert (child instanceof IURNContainerRef && parent.getChildren().contains(child))
                    || (child instanceof IURNNode && parent.getNodes().contains(child)) : "post is bound: " + i; //$NON-NLS-1$
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