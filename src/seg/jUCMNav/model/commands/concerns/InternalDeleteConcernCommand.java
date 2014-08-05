package seg.jUCMNav.model.commands.concerns;

import org.eclipse.gef.commands.Command;

import ca.mcgill.sel.core.COREConcern;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urncore.Concern;
import urncore.URNdefinition;

/**
 * Command to delete a Concern (remove it from the model), can only be done if no associations remain.
 * 
 * @author gunterm
 */
public class InternalDeleteConcernCommand extends Command implements JUCMNavCommand {

    // the concern to delete
    private Concern concern;
    // the URNdefinition in which it is contained (undo information)
    private URNdefinition oldUrnDefinition;
    private COREConcern	oldCoreConcern;

    /**
     * @param concern
     *            to be deleted
     */
    public InternalDeleteConcernCommand(Concern concern) {
        this.concern = concern;
        setLabel("InternalDeleteConcernCommand"); //$NON-NLS-1$
    }

    /**
     * checks all conditions of testPreConditions that can be checked before execute()
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return testConditionNotNull();
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        // remember for undo
        oldUrnDefinition = concern.getUrndefinition();
        oldCoreConcern = concern.getCoreConcern();
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        // break remaining association and thus remove concern since all other associations have already been deleted
        concern.setUrndefinition(null);
        concern.setCoreConcern(null);
        testPostConditions();
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        // re-add concern to the model
        concern.setUrndefinition(oldUrnDefinition);
        concern.setCoreConcern(oldCoreConcern);
        testPreConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert testConditionNotNull() && testConditionOldNotNull() : "post not null"; //$NON-NLS-1$
        assert testConditionNoAssociations() : "post no associations"; //$NON-NLS-1$
        assert !testConditionInModel() : "post concern not in model"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert testConditionNotNull() && testConditionOldNotNull() : "pre not null"; //$NON-NLS-1$
        assert testConditionNoAssociations() : "pre no associations"; //$NON-NLS-1$
        assert testConditionInModel() : "pre concern in model"; //$NON-NLS-1$
    }

    /**
     * @return true if condition is met, false otherwise
     */
    private boolean testConditionNotNull() {
        return concern != null;
    }

    /**
     * @return true if condition is met, false otherwise
     */
    private boolean testConditionOldNotNull() {
        return oldUrnDefinition != null;
    }

    /**
     * @return true if condition is met, false otherwise
     */
    private boolean testConditionNoAssociations() {
        return concern.getSpecDiagrams().size() == 0 && concern.getToLinks().size() == 0 && concern.getFromLinks().size() == 0;
    }

    /**
     * @return true if condition is met, false otherwise
     */
    private boolean testConditionInModel() {
        return oldUrnDefinition.getConcerns().contains(concern);
    }

}