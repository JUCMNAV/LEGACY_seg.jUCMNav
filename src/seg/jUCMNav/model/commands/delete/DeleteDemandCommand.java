/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.performance.Demand;
import ucm.performance.ExternalOperation;
import urn.URNspec;
import urncore.Responsibility;

/**
 * This command deletes a demand (for a responsibility)
 * 
 * @author jack
 * 
 */
public class DeleteDemandCommand extends Command implements JUCMNavCommand {

    private URNspec urn;

    private ExternalOperation externalOpn;

    private Responsibility responsibility;

    private Demand demand;
    private boolean aborted;

    /**
     * @param urn
     *            containing URN specification
     * @param extOp
     *            to be associated with the demand
     * @param demand
     * @param responsibility
     *            requesting the externalOpn
     */
    public DeleteDemandCommand(URNspec urn, ExternalOperation extOp, Demand demand, Responsibility responsibility) {
        this.urn = urn;
        this.externalOpn = extOp;
        this.demand = demand;
        this.responsibility = responsibility;
        setLabel(Messages.getString("DeleteDemandCommand.DeleteDemand")); //$NON-NLS-1$
    }

    public DeleteDemandCommand(Demand demand) {
        this.demand = demand;
        this.externalOpn = (ExternalOperation) demand.getResource();
        this.responsibility = demand.getResponsibility();

        if (demand.getResource() != null && demand.getResource().getUcmspec() != null)
            this.urn = demand.getResource().getUcmspec().getUrnspec();
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return (urn != null) && (responsibility != null) && (externalOpn != null && demand != null);
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        aborted = !urn.getUcmspec().getResources().contains(externalOpn);

        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (aborted)
            return;
        testPreConditions();
        responsibility.getDemands().remove(demand);
        externalOpn.getDemands().remove(demand);
        demand.setResource(null);
        demand.setResponsibility(null);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert (urn != null) && (externalOpn != null) && (responsibility != null) : "post null"; //$NON-NLS-1$
        assert urn.getUcmspec().getResources().contains(externalOpn) : "post ExternalOperation not in model"; //$NON-NLS-1$
        assert !responsibility.getDemands().contains(demand) : "post Demand still in Responsibility"; //$NON-NLS-1$
        assert !isResourceInDemands(responsibility, externalOpn) : "pre Resource still in Demand"; //$NON-NLS-1$
    }

    public boolean isResourceInDemands(Responsibility responsibility, ExternalOperation extOp) {
        boolean found;
        ExternalOperation externOpn;
        found = false;
        if ((extOp != null) || (responsibility != null)) {
            for (Iterator demand = responsibility.getDemands().iterator(); demand.hasNext();) {
                externOpn = (ExternalOperation) ((Demand) demand.next()).getResource();
                found = found || externOpn.equals(extOp);
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
        assert (urn != null) && (externalOpn != null) && (responsibility != null) && (demand != null) : "pre null"; //$NON-NLS-1$
        assert urn.getUcmspec().getResources().contains(externalOpn) : "pre ExternalOperation not in model"; //$NON-NLS-1$
        assert responsibility.getDemands().contains(demand) : "pre Demand not in Responsibility"; //$NON-NLS-1$
        assert isResourceInDemands(responsibility, externalOpn) : "pre Resource not in Demand"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();
        responsibility.getDemands().remove(demand);
        demand.setResponsibility(responsibility);
        demand.setResource(externalOpn);
        externalOpn.getDemands().add(demand);
        responsibility.getDemands().add(demand);
        testPreConditions();
    }

    public Demand getDemand() {
        return demand;
    }
}
