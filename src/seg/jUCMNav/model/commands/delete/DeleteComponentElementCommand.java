package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urncore.ComponentElement;

/**
 * Command to delete a ComponentElement. (Remove it from the model).
 * 
 * @author jkealey
 *  
 */
public class DeleteComponentElementCommand extends Command implements JUCMNavCommand {

    private static final String DeleteCommand_Label = "DeleteComponentElementCommand";

    // the component definition to delete
    private ComponentElement compDef;

    // the URNspec in which it is contained
    private URNspec urn;

    public DeleteComponentElementCommand(ComponentElement cd) {
        setCompDef(cd);
        setLabel(DeleteCommand_Label);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return getCompDef() != null && getCompDef().getCompRefs().size() == 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        // also set the relations
        urn = (URNspec) getCompDef().eContainer().eContainer();

        redo();
    }

    public ComponentElement getCompDef() {
        return compDef;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        // break relations and remove compDef
        urn.getUrndef().getComponents().remove(getCompDef());

        testPostConditions();
    }

    public void setCompDef(ComponentElement compDef) {
        this.compDef = compDef;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        // lists could be empty but not null
        assert getCompDef() != null && urn != null : "post something is null";
        assert getCompDef().getCompRefs().size() == 0 : "post there are still children";
        assert !urn.getUrndef().getComponents().contains(getCompDef()) : "post component element still in model";
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {

        // lists could be empty but not null
        assert getCompDef() != null && urn != null : "pre something is null";
        assert getCompDef().getCompRefs().size() == 0 : "pre can't delete if still referenced.";
        assert urn.getUrndef().getComponents().contains(getCompDef()) : "pre component element in model";

    }

    /*
     * (non-Javadoc)
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