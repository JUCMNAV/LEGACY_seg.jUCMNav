/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import java.util.Iterator;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.performance.Demand;
import ucm.performance.ExternalOperation;
import urn.URNspec;
import urncore.Responsibility;

/**
 * This command creates a demand (responsibility)
 * 
 * @author jack
 * 
 */
public class CreateDemandCommand extends Command implements JUCMNavCommand {

    private URNspec urn;

    private ExternalOperation externalOperation;

    private String quantity;

    private Responsibility responsibility;

    private Demand demand;

    /**
     * @param urn
     *            containing URN specification
     * @param extOp
     *            to be associated with the demand
     * @param quantity
     *            quantity of the externalOperation
     * @param responsibility
     *            requesting the externalOperation
     * 
     */
    public CreateDemandCommand(URNspec urn, ExternalOperation extOp, String quantity, Responsibility responsibility) {
        this.urn = urn;
        this.externalOperation = extOp;
        this.quantity = quantity;
        this.responsibility = responsibility;
        // setLabel(Messages.getString("CreateDemandCommand.CreateDemand"));
        // //$NON-NLS-1$
        setLabel(Messages.getString("CreateDemandCommand.CreateDemandCommand")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return (urn != null) && (responsibility != null) && (externalOperation != null);
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        demand = (Demand) ModelCreationFactory.getNewObject(urn, Demand.class);
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        demand.setResource(externalOperation);
        demand.setQuantity(quantity);
        demand.setResponsibility(responsibility);
        responsibility.getDemands().add(demand);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert (urn != null) && (externalOperation != null) && (responsibility != null) : "post not null"; //$NON-NLS-1$
        assert urn.getUcmspec().getResources().contains(externalOperation) : "post Resource not in model"; //$NON-NLS-1$
        // assert responsibility.getDemands().contains(externalOperation) : "post demand
        // not in Responsibility"; //$NON-NLS-1$
        assert isResourceInDemands(responsibility, externalOperation) : "post demand not in Responsibility"; //$NON-NLS-1$
    }

    public boolean isResourceInDemands(Responsibility responsibility, ExternalOperation extOp) {
        boolean found;
        ExternalOperation externalOperation;
        found = false;
        if ((extOp != null) || (responsibility != null)) {
            for (Iterator demand = responsibility.getDemands().iterator(); demand.hasNext();) {
                externalOperation = (ExternalOperation) ((Demand) demand.next()).getResource();
                found = found || externalOperation.equals(extOp);
            }
        }
        return found;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert (urn != null) && (externalOperation != null) && (responsibility != null) : "pre not null"; //$NON-NLS-1$
        assert urn.getUcmspec().getResources().contains(externalOperation) : "pre Resource not in model"; //$NON-NLS-1$
        assert !responsibility.getDemands().contains(externalOperation) : "pre Demand already in Responsibility"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        responsibility.getDemands().remove(demand);
        demand.setResponsibility(null);
        demand.setQuantity("0.0"); //$NON-NLS-1$
        demand.setResource(null);
        testPreConditions();
    }

    public Demand getDemand() {
        return demand;
    }
}
