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
    private double opTime;
    private String description;
    
    /**
     * @param component 
     * 
     */
    public CreateExternalOperationCommand(URNspec urn, double opTime, String description) {
        this.urn = urn;
        this.opTime = opTime;
        this.description = description;
        setLabel(Messages.getString("CreateExternalOperationCommand.CreateActiveProcessingResource")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
    	// this resource should not be there already _js_
        return (urn != null) ;
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
    	externalOperation.setDescription(description);
    	externalOperation.setOpTime(opTime);
        testPostConditions();
    }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert (urn != null) && (urn.getUcmspec() != null) && (urn.getUcmspec().getResources() != null): "post not null"; //$NON-NLS-1$
        assert urn.getUcmspec().getResources().contains(externalOperation): "post externalOperation in model"; //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert (urn != null) && (urn.getUcmspec() != null) && (urn.getUcmspec().getResources() != null): "pre not null"; //$NON-NLS-1$
        assert !urn.getUcmspec().getResources().contains(externalOperation) : "pre passiveResource not in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
    	externalOperation.setOpTime(0.0);
    	externalOperation.setDescription(null);
        urn.getUcmspec().getResources().remove(externalOperation);
        testPreConditions();
    }

    public ExternalOperation getExternalOperation() {
        return externalOperation;
    }
}
