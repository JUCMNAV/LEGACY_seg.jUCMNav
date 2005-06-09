package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.Map;
import ucm.map.PluginBinding;
import ucm.map.Stub;
import urn.URNspec;

/**
 * Created 2005-06-06
 * 
 * @author Etienne Tremblay
 */
public class AddPluginCommand extends Command implements JUCMNavCommand {
	
	private Stub stub;
	private Map map;
	private PluginBinding plugin;
	
	private URNspec urnSpec;

	/**
	 * @param stub
	 * @param map
	 */
	public AddPluginCommand(Stub stub, Map map) {
		super();
		this.stub = stub;
		this.map = map;
		setLabel(Messages.getString("AddPlugin.addPlugin")); //$NON-NLS-1$
	}

	public boolean canExecute() {
		if(stub != null && map != null)
			return true;
		else
			return false;
	}
	
	public void execute() {
		urnSpec = map.getUcmspec().getUrnspec();
		
		plugin = (PluginBinding)ModelCreationFactory.getNewObject(urnSpec, PluginBinding.class);
		
		redo();
	}
	
	public void redo() {
		testPreConditions();
		
		stub.getBindings().add(plugin);
		map.getParentStub().add(plugin);
		
		testPostConditions();
	}
	
	public void undo() {
		testPostConditions();
		
		stub.getBindings().remove(plugin);
		map.getParentStub().remove(plugin);
		
		testPreConditions();
	}
	
	/* (non-Javadoc)
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
	 */
	public void testPreConditions() {
		assert stub != null : "Pre stub null"; //$NON-NLS-1$
		assert plugin != null : "Pre plugin null"; //$NON-NLS-1$
		assert !stub.getBindings().contains(plugin) : "Pre plugin contained in stub plugins"; //$NON-NLS-1$
		assert !map.getParentStub().contains(plugin) : "Pre plugin contained in map parent stub"; //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
	 */
	public void testPostConditions() {
		assert stub != null : "Post stub null"; //$NON-NLS-1$
		assert plugin != null : "Post plugin null"; //$NON-NLS-1$
		assert stub.getBindings().contains(plugin) : "Post plugin not contained in stub plugins"; //$NON-NLS-1$
		assert map.getParentStub().contains(plugin) : "Post plugin not contained in map parent stub"; //$NON-NLS-1$
	}
}
