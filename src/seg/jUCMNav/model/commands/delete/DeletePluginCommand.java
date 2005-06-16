package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.InBinding;
import ucm.map.Map;
import ucm.map.OutBinding;
import ucm.map.PluginBinding;
import ucm.map.Stub;
import urn.URNspec;

/**
 * Created 2005-06-06
 * 
 * This command delete a PluginBinding from a Stub.
 * 
 * @author Etienne Tremblay
 */
public class DeletePluginCommand extends CompoundCommand implements JUCMNavCommand {

    // The PluginBinding to delete.
    private PluginBinding oldPlugin;

    // The stub owner of the PluginBinding to delete.
    private Stub stub;

    // The map of the PluginBinding
    private Map oldMap;

    // The URNspec of the file
    private URNspec urnSpec;

    /**
     * This command delete a PluginBinding from a Stub.
     * 
     * @param oldPlugin
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

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	public boolean canUndo() {
		// Make sure we can undo even if we don't have any added commands
		if(getCommands().size() == 0)
			return true;
		return super.canUndo();
	}
    public void execute() {
        stub = oldPlugin.getStub();
        oldMap = oldPlugin.getPlugin();
        urnSpec = oldMap.getUcmspec().getUrnspec();

        for (Iterator i = oldPlugin.getIn().iterator(); i.hasNext();) {
            InBinding in = (InBinding) i.next();
            DeleteInBindingCommand cmd = new DeleteInBindingCommand(in);
            add(cmd);
        }

        for (Iterator i = oldPlugin.getOut().iterator(); i.hasNext();) {
            OutBinding out = (OutBinding) i.next();
            DeleteOutBindingCommand cmd = new DeleteOutBindingCommand(out);
            add(cmd);
        }
        
        doRedo();
        super.execute();
    }

    public void redo() {
        testPreConditions();

        doRedo();
        
        super.redo();

        testPostConditions();
    }

    /**
	 * 
	 */
	private void doRedo() {
        stub.getBindings().remove(oldPlugin);
        oldMap.getParentStub().remove(oldPlugin);
	}

	public void undo() {
        testPostConditions();
        
        super.undo();

        oldMap.getParentStub().add(oldPlugin);
        stub.getBindings().add(oldPlugin);

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

//        assert oldPlugin.getIn().size() == inBindings.size() : "Pre InBindings size changed";
//        assert oldPlugin.getOut().size() == outBindings.size() : "Pre OutBindings size changed";
//
//        for (Iterator i = oldPlugin.getIn().iterator(); i.hasNext();) {
//            InBinding in = (InBinding) i.next();
//            assert in.getStartPoint() != starts.get(in.getBinding()) : "Pre an InBinding start point is not equal to what it was before";
//            assert in.getStubEntry() != entry.get(in.getBinding()) : "Pre an InBinding node connection entry is not equals to what is was before";
//        }
//
//        for (Iterator i = oldPlugin.getOut().iterator(); i.hasNext();) {
//            OutBinding out = (OutBinding) i.next();
//            assert out.getEndPoint() != starts.get(out.getBinding()) : "Pre an OutBinding end point is not equal to what it was before";
//            assert out.getStubExit() != entry.get(out.getBinding()) : "Pre an OutBinding node connection exit is not equals to what is was before";
//        }
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

//        for (Iterator i = inBindings.iterator(); i.hasNext();) {
//            InBinding in = (InBinding) i.next();
//            assert in.getStartPoint() == null : "Post an InBinding start point is not null.";
//            assert in.getStubEntry() == null : "Post an InBinding node connection entry is not null";
//        }
//
//        for (Iterator i = outBindings.iterator(); i.hasNext();) {
//            OutBinding out = (OutBinding) i.next();
//            assert out.getEndPoint() == null : "Post an OutBinding end point is not null";
//            assert out.getStubExit() == null : "Post an OutBinding node connection exit is not null";
//        }
    }

}