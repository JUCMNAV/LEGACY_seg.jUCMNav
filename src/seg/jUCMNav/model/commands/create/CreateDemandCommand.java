/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.performance.Demand;
import ucm.performance.GeneralResource;
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
    private GeneralResource resource;
    private double quantity;
    private Responsibility responsibility;
    private Demand demand;
    
    /**
     * @param urn
     * 		containing URN specification
     * @param resource
     * 		to be associated with the demand
     * @param quantity
     * 		quantity of the resource
     * @param responsibility
     * 		requesting the resource
     * 
     */
    public CreateDemandCommand(URNspec urn, GeneralResource resource, double quantity, Responsibility responsibility) {
        this.urn = urn;
        this.resource = resource;
        this.quantity = quantity;
        this.responsibility = responsibility;
//        setLabel(Messages.getString("CreateDemandCommand.CreateDemand")); //$NON-NLS-1$
        setLabel("Create Demand command");
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return (urn != null) && (responsibility != null) && (resource != null);
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
        demand.setResource(resource);
        demand.setQuantity(quantity);
        demand.setResponsibility(responsibility);
        responsibility.getDemands().add(demand);
        testPostConditions();
    }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert (urn != null) && (resource != null) && (responsibility != null): "post not null"; //$NON-NLS-1$
        assert urn.getUcmspec().getResources().contains(resource) : "post Resource not in model"; //$NON-NLS-1$
        assert responsibility.getDemands().contains(resource) : "post demand not in Responsibility"; //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert (urn != null) && (resource != null) && (responsibility != null): "pre not null"; //$NON-NLS-1$
        assert urn.getUcmspec().getResources().contains(resource) : "pre Resource not in model"; //$NON-NLS-1$
        assert !responsibility.getDemands().contains(resource) : "pre Demand already in Responsibility"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        responsibility.getDemands().remove(demand);
        demand.setResponsibility(null);
        demand.setQuantity(0.0);
        demand.setResource(null);
        testPreConditions();
    }

    public Demand getDemand() {
        return demand;
    }
}
