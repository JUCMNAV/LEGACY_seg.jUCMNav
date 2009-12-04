/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.performance.ExternalOperation;
import urn.URNspec;

/**
 * This command adds an external operation
 * 
 * @author jack
 * 
 */
public class CreateExternalOperationCommand extends Command implements JUCMNavCommand {

    private URNspec urn;
    private ExternalOperation externalOperation;
    private String opTime;
    private String description;
    private String name;
    private String multiplicity;
    private String schedPolicy;

    /**
     * @param urn
     *            containing URN specification
     * @param name
     * @param opTime
     *            to be associated with the external resource
     * @param description
     *            of the external resource
     * @param schedPolicyStr
     * @param multiplicityStr
     * 
     */
    public CreateExternalOperationCommand(URNspec urn, String name, String opTime, String description, String multiplicityStr, String schedPolicyStr) {
        this.urn = urn;
        this.opTime = opTime;
        this.description = description;
        this.name = name;
        this.multiplicity = multiplicityStr;
        this.schedPolicy = schedPolicyStr;
        setLabel(Messages.getString("CreateExternalOperationCommand.CreateActiveProcessingResource")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        // this resource should not be there already _js_
        return (urn != null);
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        externalOperation = (ExternalOperation) ModelCreationFactory.getNewObject(urn, ExternalOperation.class, 0, null);
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        urn.getUcmspec().getResources().add(externalOperation);
        externalOperation.setName(name);
        externalOperation.setMultiplicity(multiplicity);
        externalOperation.setSchedPolicy(schedPolicy);
        externalOperation.setDescription(description);
        externalOperation.setOpTime(opTime);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert (urn != null) && (urn.getUcmspec() != null) && (urn.getUcmspec().getResources() != null) : "post not null"; //$NON-NLS-1$
        assert urn.getUcmspec().getResources().contains(externalOperation) : "post externalOperation in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert (urn != null) && (urn.getUcmspec() != null) : "pre not null"; //$NON-NLS-1$
        assert !urn.getUcmspec().getResources().contains(externalOperation) : "pre externalOperation not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        externalOperation.setOpTime("0.0"); //$NON-NLS-1$
        externalOperation.setDescription(null);
        externalOperation.setName(null);
        externalOperation.setMultiplicity(null);
        externalOperation.setSchedPolicy(null);
        urn.getUcmspec().getResources().remove(externalOperation);
        testPreConditions();
    }

    public ExternalOperation getExternalOperation() {
        return externalOperation;
    }
}
