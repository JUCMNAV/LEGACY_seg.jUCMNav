package seg.jUCMNav.model.commands.delete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.EndPoint;
import ucm.map.InBinding;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.OutBinding;
import ucm.map.PluginBinding;
import ucm.map.StartPoint;
import ucm.map.Stub;
import urn.URNspec;

/**
 * Created 2005-06-06
 * 
 * This command delete a PluginBinding from a Stub.
 * 
 * @author Etienne Tremblay
 */
public class DeletePluginCommand extends Command implements JUCMNavCommand {

	// The PluginBinding to delete.
	private PluginBinding oldPlugin;
	// The stub owner of the PluginBinding to delete.
	private Stub stub;
	// The map of the PluginBinding
	private Map oldMap;
	// The URNspec of the file
	private URNspec urnSpec;

	// Two arrays containing the In and Out Bindings of the PluginBinding.
	private ArrayList inBindings = new ArrayList();
	private ArrayList outBindings = new ArrayList();

	// The HashMaps containing the (key,value) pair (InBinding | OutBinding, StartPoint | EndPoint | NodeConnection)
	private HashMap starts = new HashMap();
	private HashMap ends = new HashMap();
	private HashMap entry = new HashMap();
	private HashMap exit = new HashMap();

	/**
	 * This command delete a PluginBinding from a Stub.
	 * 
	 * @param plugin
	 *            The PluginBindng to delete from the Stub.
	 */
	public DeletePluginCommand(PluginBinding oldPlugin) {
		super();
		this.oldPlugin = oldPlugin;
	}

	public boolean canExecute() {
		// This command can execute only if oldPlugin is not null and that this plugin is contained in a stub.
		if (oldPlugin != null)
			if (oldPlugin.getStub() != null)
				return true;
			else
				return false;
		else
			return false;
	}

	public void execute() {
		stub = oldPlugin.getStub();
		oldMap = oldPlugin.getPlugin();
		urnSpec = stub.getPathGraph().getMap().getUcmspec().getUrnspec();

		inBindings.addAll(oldPlugin.getIn());
		outBindings.addAll(oldPlugin.getOut());

		for (Iterator i = oldPlugin.getIn().iterator(); i.hasNext();) {
			InBinding in = (InBinding) i.next();
			starts.put(in, in.getStartPoint());
			entry.put(in, in.getStubEntry());
		}

		for (Iterator i = oldPlugin.getOut().iterator(); i.hasNext();) {
			OutBinding out = (OutBinding) i.next();
			ends.put(out, out.getEndPoint());
			exit.put(out, out.getStubExit());
		}

		redo();
	}

	public void redo() {
		testPreConditions();

		for (Iterator i = oldPlugin.getIn().iterator(); i.hasNext();) {
			InBinding in = (InBinding) i.next();
			in.setStartPoint(null);
			in.setStubEntry(null);
		}

		for (Iterator i = oldPlugin.getOut().iterator(); i.hasNext();) {
			OutBinding out = (OutBinding) i.next();
			out.setEndPoint(null);
			out.setStubExit(null);
		}

		oldPlugin.getIn().clear();
		oldPlugin.getOut().clear();

		stub.getBindings().remove(oldPlugin);
		oldMap.getParentStub().remove(oldPlugin);

		testPostConditions();
	}

	public void undo() {
		testPostConditions();

		oldMap.getParentStub().add(oldPlugin);
		stub.getBindings().add(oldPlugin);

		oldPlugin.getIn().addAll(inBindings);
		oldPlugin.getOut().addAll(outBindings);

		for (Iterator i = oldPlugin.getIn().iterator(); i.hasNext();) {
			InBinding in = (InBinding) i.next();
			in.setStartPoint((StartPoint) starts.get(in));
			in.setStubEntry((NodeConnection) entry.get(in));
		}

		for (Iterator i = oldPlugin.getOut().iterator(); i.hasNext();) {
			OutBinding out = (OutBinding) i.next();
			out.setEndPoint((EndPoint) ends.get(out));
			out.setStubExit((NodeConnection) exit.get(out));
		}

		testPreConditions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
	 */
	public void testPreConditions() {
		assert oldPlugin != null : "Pre oldPlugin is null";
		assert stub != null : "Pre stub is null";
		assert oldMap != null : "Pre the associated map is null";

		assert stub.getBindings().contains(oldPlugin) : "Pre oldPlugin not contained in stub bindings";
		assert oldMap.getParentStub().contains(oldPlugin) : "Pre oldPlugin not contained in the map parent stubs";

		assert oldPlugin.getIn().size() == inBindings.size() : "Pre InBindings size changed";
		assert oldPlugin.getOut().size() == outBindings.size() : "Pre OutBindings size changed";

		for (Iterator i = oldPlugin.getIn().iterator(); i.hasNext();) {
			InBinding in = (InBinding) i.next();
			assert in.getStartPoint() != starts.get(in.getBinding()) : "Pre an InBinding start point is not equal to what it was before";
			assert in.getStubEntry() != entry.get(in.getBinding()) : "Pre an InBinding node connection entry is not equals to what is was before";
		}

		for (Iterator i = oldPlugin.getOut().iterator(); i.hasNext();) {
			OutBinding out = (OutBinding) i.next();
			assert out.getEndPoint() != starts.get(out.getBinding()) : "Pre an OutBinding end point is not equal to what it was before";
			assert out.getStubExit() != entry.get(out.getBinding()) : "Pre an OutBinding node connection exit is not equals to what is was before";
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
	 */
	public void testPostConditions() {
		assert oldPlugin != null : "Post oldPlugin is null";
		assert oldPlugin != null : "Post oldPlugin is null";
		assert stub != null : "Post stub is null";
		assert oldMap != null : "Post the associated map is null";

		assert !stub.getBindings().contains(oldPlugin) : "Post oldPlugin contained in stub bindings";
		assert !oldMap.getParentStub().contains(oldPlugin) : "Post oldPlugin contained in the map parent stubs";

		assert oldPlugin.getIn().size() == 0 : "Post InBindings size is not zero";
		assert oldPlugin.getOut().size() == 0 : "Post OutBindings size is not zero";

		for (Iterator i = inBindings.iterator(); i.hasNext();) {
			InBinding in = (InBinding) i.next();
			assert in.getStartPoint() == null : "Post an InBinding start point is not null.";
			assert in.getStubEntry() == null : "Post an InBinding node connection entry is not null";
		}

		for (Iterator i = outBindings.iterator(); i.hasNext();) {
			OutBinding out = (OutBinding) i.next();
			assert out.getEndPoint() == null : "Post an OutBinding end point is not null";
			assert out.getStubExit() == null : "Post an OutBinding node connection exit is not null";
		}
	}

}