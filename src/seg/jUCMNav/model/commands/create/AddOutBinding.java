package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OutBinding;
import ucm.map.PluginBinding;
import urn.URNspec;

/**
 * Created 2005-06-06
 * 
 * @author Etienne Tremblay
 */
public class AddOutBinding extends Command implements JUCMNavCommand {

	private PluginBinding plugin;
	private EndPoint end;
	private NodeConnection exit;
	
	private OutBinding out;
	private URNspec urnSpec;

	/**
	 * @param stub
	 * @param plugin
	 * @param start
	 * @param entry
	 */
	public AddOutBinding(PluginBinding plugin, EndPoint end, NodeConnection entry) {
		super();
		this.plugin = plugin;
		this.end = end;
		this.exit = entry;
		setLabel(Messages.getString("AddOutBinding.addOutBinding")); //$NON-NLS-1$
	}

	public boolean canExecute() {
		if(plugin != null && end != null && exit != null)
			return true;
		else
			return false;
	}
	
	public void execute() {
		urnSpec = plugin.getPlugin().getUcmspec().getUrnspec();
		
		out = (OutBinding) ModelCreationFactory.getNewObject(urnSpec, OutBinding.class);
		
		redo();
	}
	
	public void redo() {
		testPreConditions();
		
		plugin.getOut().add(out);
		out.setEndPoint(end);
		out.setStubExit(exit);
		
		testPostConditions();
	}
	
	public void undo() {
		testPostConditions();
		
		plugin.getOut().remove(out);
		out.setEndPoint(null);
		out.setStubExit(null);
		
		testPreConditions();
	}
	/* (non-Javadoc)
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
	 */
	public void testPreConditions() {
		assert plugin != null : "Pre plugin null"; //$NON-NLS-1$
		assert end != null : "Pre end point null"; //$NON-NLS-1$
		assert exit != null : "Pre exit connection null"; //$NON-NLS-1$
		assert !plugin.getOut().contains(out) : "Pre plugin contains the out binding"; //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
	 */
	public void testPostConditions() {
		assert plugin != null : "Post plugin null"; //$NON-NLS-1$
		assert end != null : "Post end point null"; //$NON-NLS-1$
		assert exit != null : "Post exit connection null"; //$NON-NLS-1$
		assert plugin.getOut().contains(out) : "Post plugin doesn't contains the out binding"; //$NON-NLS-1$
	}
}
