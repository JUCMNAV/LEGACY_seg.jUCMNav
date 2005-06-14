package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urncore.Responsibility;

/**
 * Command to delete a Responsibility. (Remove it from the model).
 * 
 * @author jkealey
 *  
 */
public class DeleteResponsibilityCommand extends Command implements JUCMNavCommand {

    private static final String DeleteCommand_Label = "DeleteResponsibilityCommand"; //$NON-NLS-1$

    // the responsibility definition to delete
    private Responsibility respDef;

    // the URNspec in which it is contained
    private URNspec urn;

    public DeleteResponsibilityCommand(Responsibility resp) {
        setRespDef(resp);
        setLabel(DeleteCommand_Label);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return getRespDef() != null && getRespDef().getRespRefs().size() == 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        // also set the relations
        urn = getRespDef().getUrndefinition().getUrnspec();

        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        // break relations and remove respDef
        urn.getUrndef().getResponsibilities().remove(getRespDef());

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        // lists could be empty but not null
        assert getRespDef() != null && urn != null : "post something is null"; //$NON-NLS-1$
        assert getRespDef().getRespRefs().size() == 0 : "post there are still children"; //$NON-NLS-1$
        assert !urn.getUrndef().getResponsibilities().contains(getRespDef()) : "post responsibility still in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {

        // lists could be empty but not null
        assert getRespDef() != null && urn != null : "pre something is null"; //$NON-NLS-1$
        assert getRespDef().getRespRefs().size() == 0 : "pre can't delete if still referenced."; //$NON-NLS-1$
        assert urn.getUrndef().getResponsibilities().contains(getRespDef()) : "pre responsibility in model"; //$NON-NLS-1$

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        // re-add compDef
        urn.getUrndef().getResponsibilities().add(getRespDef());

        testPreConditions();
    }

    public Responsibility getRespDef() {
        return respDef;
    }

    public void setRespDef(Responsibility respDef) {
        this.respDef = respDef;
    }
}