package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urncore.ComponentElement;

/**
 * Command to delete a ComponentElement. (Remove it from the model). Can only do it if it has no references.
 * 
 * @author jkealey
 *  
 */
public class DeleteComponentElementCommand extends Command implements JUCMNavCommand {

    // the component definition to delete
    private ComponentElement compDef;

    // the URNspec in which it is contained
    private URNspec urn;

    public DeleteComponentElementCommand(ComponentElement cd) {
        setCompDef(cd);
        setLabel("DeleteComponentElementCommand");//$NON-NLS-1$
    }

    /**
     * Only if not referenced.
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return getCompDef() != null && getCompDef().getContRefs().size() == 0;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        // also set the relations
        urn = getCompDef().getUrndefinition().getUrnspec();

        redo();
    }

    /**
     * @return the component definition to delete
     */
    public ComponentElement getCompDef() {
        return compDef;
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        // break relations and remove compDef
        urn.getUrndef().getComponents().remove(getCompDef());

        testPostConditions();
    }

    /**
     * 
     * @param compDef
     *            the component definition to delete.
     */
    public void setCompDef(ComponentElement compDef) {
        this.compDef = compDef;
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        // lists could be empty but not null
        assert getCompDef() != null && urn != null : "post something is null"; //$NON-NLS-1$
        assert getCompDef().getContRefs().size() == 0 : "post there are still children"; //$NON-NLS-1$
        assert !urn.getUrndef().getComponents().contains(getCompDef()) : "post component element still in model"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {

        // lists could be empty but not null
        assert getCompDef() != null && urn != null : "pre something is null"; //$NON-NLS-1$
        assert getCompDef().getContRefs().size() == 0 : "pre can't delete if still referenced."; //$NON-NLS-1$
        assert urn.getUrndef().getComponents().contains(getCompDef()) : "pre component element in model"; //$NON-NLS-1$

    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        // re-add compDef
        urn.getUrndef().getComponents().add(getCompDef());

        testPreConditions();
    }
}