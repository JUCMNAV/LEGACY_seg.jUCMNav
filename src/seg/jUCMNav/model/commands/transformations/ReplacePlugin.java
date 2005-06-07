package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

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
public class ReplacePlugin extends Command implements JUCMNavCommand {
	
	private Stub stub;
	private Map map;
	private PluginBinding oldPlugin;
	private PluginBinding newPlugin;
	
	private URNspec urnSpec;
	
	/**
	 * @param stub
	 * @param plugin
	 */
	public ReplacePlugin(PluginBinding oldPlugin) {
		super();
		this.oldPlugin = oldPlugin;
	}

	public boolean canExecute() {
		if(oldPlugin != null)
			return true;
		else
			return false;
	}
	
	public void execute() {
		stub = oldPlugin.getStub();
		
		urnSpec = stub.getPathGraph().getMap().getUcmspec().getUrnspec();
		
		map = oldPlugin.getPlugin();
		
		newPlugin = (PluginBinding)ModelCreationFactory.getNewObject(urnSpec, PluginBinding.class);
		
		redo();
	}
	
	public void redo() {
		testPreConditions();
		
		oldPlugin.setPlugin(null);
		oldPlugin.setStub(null);
		
		map.getParentStub().add(newPlugin);
		stub.getBindings().add(newPlugin);
		
		testPostConditions();
	}
	
	public void undo() {
		testPostConditions();
		
		map.getParentStub().add(oldPlugin);
		stub.getBindings().add(oldPlugin);
		
		newPlugin.setPlugin(null);
		newPlugin.setStub(null);
		
		testPreConditions();
	}
	/* (non-Javadoc)
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
	 */
	public void testPreConditions() {
		assert oldPlugin != null : "Pre oldPlugin is null";
		assert stub.getBindings().contains(oldPlugin) : "Pre oldPlugin not contained in stub bindings";
		assert map.getParentStub().contains(oldPlugin) : "Pre oldPlugin not contained in the map parent stubs";
		assert !stub.getBindings().contains(newPlugin) : "Pre newPlugin contained in the stub bindings";
		assert !map.getParentStub().contains(newPlugin) : "Pre newPlugin contained in the map parent stubs";
	}

	/* (non-Javadoc)
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
	 */
	public void testPostConditions() {
		assert oldPlugin != null : "Post oldPlugin is null";
		assert !stub.getBindings().contains(oldPlugin) : "Post oldPlugin contained in stub bindings";
		assert !map.getParentStub().contains(oldPlugin) : "Post oldPlugin contained in the map parent stubs";
		assert stub.getBindings().contains(newPlugin) : "Post newPlugin not contained in the stub bindings";
		assert map.getParentStub().contains(newPlugin) : "Post newPlugin noy contained in the map parent stubs";
	}

}
