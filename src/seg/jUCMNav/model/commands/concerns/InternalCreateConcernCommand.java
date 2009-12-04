package seg.jUCMNav.model.commands.concerns;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urncore.Concern;

/**
 * This command adds a new concern to the URNspec, to be followed by {@link AssignConcernDiagramCommand} in order to assign the concern to all of its diagrams
 * 
 * @author gunterm
 */
public class InternalCreateConcernCommand extends Command implements JUCMNavCommand {

    // new concern
    private Concern concern;
    // URNspec to which the concern is added
    private URNspec urn;
    // number of concerns in the model
    private int oldCount;

    /**
     * @param urn
     *            to which to add the concern
     * @param name
     *            of the concern
     * @param description
     *            of the concern
     */
    public InternalCreateConcernCommand(URNspec urn, String name, String description) {
        this.urn = urn;
        // must be created here for getConcern() to work properly.
        concern = (Concern) ModelCreationFactory.getNewObject(urn, Concern.class);
        concern.setName(name);
        concern.setDescription(description);
        setLabel("InternalCreateConcern"); //$NON-NLS-1$
    }

    /**
     * checks all conditions of testPreConditions that can be checked before execute()
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return testConditionNotNull() && !testConditionInModel();
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        // remember for undo
        oldCount = urn.getUrndef().getConcerns().size();
        redo();
    }

    /**
     * @return the newly created concern
     */
    public Concern getConcern() {
        return concern;
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        // add the concern to the model
        urn.getUrndef().getConcerns().add(concern);
        testPostConditions();
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        // remove the concern from the model
        urn.getUrndef().getConcerns().remove(concern);
        testPreConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert testConditionNotNull() : "post not null"; //$NON-NLS-1$
        assert testConditionInModel() : "post concern in model"; //$NON-NLS-1$
        assert oldCount + 1 == urn.getUrndef().getConcerns().size() : "post number of concerns increased by one"; //$NON-NLS-1$

    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert testConditionNotNull() : "pre not null"; //$NON-NLS-1$
        assert !testConditionInModel() : "pre concern not in model"; //$NON-NLS-1$
        assert oldCount == urn.getUrndef().getConcerns().size() : "pre number of concerns is original"; //$NON-NLS-1$
    }

    /**
     * @return true if condition is met, false otherwise
     */
    private boolean testConditionNotNull() {
        return urn != null && urn.getUrndef() != null && concern != null && concern.getName() != null && concern.getDescription() != null;
    }

    /**
     * @return true if condition is met, false otherwise
     */
    private boolean testConditionInModel() {
        return urn.getUrndef().getConcerns().contains(concern);
    }

}