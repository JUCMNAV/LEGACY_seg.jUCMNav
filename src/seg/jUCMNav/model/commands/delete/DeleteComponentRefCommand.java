package seg.jUCMNav.model.commands.delete;

import java.util.Vector;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentRef;
import ucm.map.Map;
import urncore.ComponentElement;

/**
 * Command to delete a ComponentRef. (Remove it from the model).
 * 
 * @author jkealey
 *  
 */
public class DeleteComponentRefCommand extends Command implements JUCMNavCommand {

    private static final String DeleteCommand_Label = "DeleteComponentRefCommand"; //$NON-NLS-1$

    // the component reference to delete
    private ComponentRef compRef;

    // the map in which it is contained
    private Map map;

    // its relationships with the rest of the metamodel
    private Vector compRefChildren;

    private Vector pathNodeChildren;

    private ComponentRef parent;

    private ComponentElement compDef;

    public DeleteComponentRefCommand(ComponentRef cr) {
        setCompRef(cr);
        setLabel(DeleteCommand_Label);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return compRef != null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        // also set the relations
        map = compRef.getMap();
        parent = compRef.getParent();
        compDef = compRef.getCompDef();
        // we're copying the arrays
        compRefChildren = new Vector();
        compRefChildren.addAll(compRef.getChildren());
        pathNodeChildren = new Vector();
        pathNodeChildren.addAll(compRef.getPathNodes());

        redo();
    }

    /**
     * @return Returns the compRef.
     */
    public ComponentRef getCompRef() {
        return compRef;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        // break relations and remove compRef
        map.getCompRefs().remove(compRef);
        compRef.getChildren().clear();
        compRef.getPathNodes().clear();
        compRef.setParent(null);
        compRef.setCompDef(null);

        testPostConditions();
    }

    /**
     * @param compRef
     *            The compRef to set.
     */
    public void setCompRef(ComponentRef compRef) {
        this.compRef = compRef;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        // lists could be empty but not null
        assert compRef != null && map != null && compRefChildren != null && pathNodeChildren != null && compDef != null : "post something is null"; //$NON-NLS-1$

        assert compRef.getCompDef() == null : "post compRef-compDef"; //$NON-NLS-1$
        assert !map.getCompRefs().contains(compRef) : "post compRef in map"; //$NON-NLS-1$
        assert compRef.getParent() == null : "post parent"; //$NON-NLS-1$
        assert 0 == compRef.getChildren().size() && 0 == compRef.getPathNodes().size() : "post children size invalid"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {

        // lists could be empty but not null
        assert compRef != null && map != null && compRefChildren != null && pathNodeChildren != null && compDef != null : "pre something is null"; //$NON-NLS-1$

        assert compRef.getCompDef() == compDef : "pre compRef-compDef"; //$NON-NLS-1$
        assert map.getCompRefs().contains(compRef) : "pre compRef in map"; //$NON-NLS-1$
        assert compRef.getParent() == parent : "pre parent"; //$NON-NLS-1$
        assert compRefChildren.size() == compRef.getChildren().size() && pathNodeChildren.size() == compRef.getPathNodes().size() : "pre children size invalid"; //$NON-NLS-1$
        for (int i = 0; i < compRefChildren.size(); i++) {
            assert compRef.getChildren().contains(compRefChildren.get(i)) : "pre missing child compRef"; //$NON-NLS-1$
        }
        for (int i = 0; i < pathNodeChildren.size(); i++) {
            assert compRef.getPathNodes().contains(pathNodeChildren.get(i)) : "pre missing child PathNode"; //$NON-NLS-1$
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        // put back element in model and setup the relations.
        compRef.getChildren().addAll(compRefChildren);
        compRef.getPathNodes().addAll(pathNodeChildren);
        compRef.setParent(parent);
        compRef.setCompDef(compDef);
        // must be last so that the label has access to the component definition
        map.getCompRefs().add(compRef);

        testPreConditions();
    }
}