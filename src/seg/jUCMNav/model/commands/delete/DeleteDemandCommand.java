/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.performance.Demand;
import ucm.performance.GeneralResource;
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

	private GeneralResource resource;

	private Responsibility responsibility;

	private Demand demand;

	/**
	 * @param urn
	 *            containing URN specification
	 * @param resource
	 *            to be associated with the demand
	 * @param responsibility
	 *            requesting the resource
	 * 
	 */
	public DeleteDemandCommand(URNspec urn, GeneralResource resource, Demand demand, Responsibility responsibility) {
		this.urn = urn;
		this.resource = resource;
		this.demand = demand;
		this.responsibility = responsibility;
		setLabel("Delete Demand");
	}

	/**
	 * @see org.eclipse.gef.commands.Command#canExecute()
	 */
	public boolean canExecute() {
		return (urn != null) && (responsibility != null) && (resource != null && demand != null);
	}

	/**
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		redo();
	}

	/**
	 * 
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		testPreConditions();
		responsibility.getDemands().remove(demand);
		resource.getDemands().remove(demand);
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
		assert (urn != null) && (resource != null) && (responsibility != null) : "post not null";
		assert urn.getUcmspec().getResources().contains(resource) : "post Resource disappeared from model";
		assert !responsibility.getDemands().contains(demand) : "post Demand still in Responsibility";
		assert !isResourceInDemands(responsibility, resource) : "pre Resource still in Demand";
	}

	public boolean isResourceInDemands(Responsibility responsibility, GeneralResource res) {
		boolean found;
		GeneralResource resource;
		found = false;
		if ((res != null) || (responsibility != null)) {
			for (Iterator demand = responsibility.getDemands().iterator(); demand.hasNext();) {
				resource = (GeneralResource) ((Demand) demand.next()).getResource();
				found = found || resource.equals(res);
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
		assert (urn != null) && (resource != null) && (responsibility != null) && (demand != null) : "pre not null";
		assert urn.getUcmspec().getResources().contains(resource) : "pre Resource not in model";
		assert responsibility.getDemands().contains(demand) : "pre Demand not in Responsibility";
		assert isResourceInDemands(responsibility, resource) : "pre Resource not in Demand";
	}

	/**
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		testPostConditions();
		responsibility.getDemands().remove(demand);
		demand.setResponsibility(responsibility);
		demand.setResource(resource);
		resource.getDemands().add(demand);
		responsibility.getDemands().add(demand);
		testPreConditions();
	}

	public Demand getDemand() {
		return demand;
	}
}
