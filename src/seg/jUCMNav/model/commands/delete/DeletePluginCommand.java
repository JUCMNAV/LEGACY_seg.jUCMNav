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
    
    public DeletePluginCommand(PluginBinding oldPlugin, URNspec urnSpec){
    	super();
        this.oldPlugin = oldPlugin;
        this.urnSpec = urnSpec;
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
        if(urnSpec == null)
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
        testPreConditions();
        
        doRedo();
        super.execute();
        
        testPostConditions();
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
        assert oldPlugin != null : "Pre oldPlugin is null"; //$NON-NLS-1$
        assert stub != null : "Pre stub is null"; //$NON-NLS-1$
        assert oldMap != null : "Pre the associated map is null"; //$NON-NLS-1$

        assert stub.getBindings().contains(oldPlugin) : "Pre oldPlugin not contained in stub bindings"; //$NON-NLS-1$
        assert oldMap.getParentStub().contains(oldPlugin) : "Pre oldPlugin not contained in the map parent stubs"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert oldPlugin != null : "Post oldPlugin is null"; //$NON-NLS-1$
        assert stub != null : "Post stub is null"; //$NON-NLS-1$
        assert oldMap != null : "Post the associated map is null"; //$NON-NLS-1$

        assert !stub.getBindings().contains(oldPlugin) : "Post oldPlugin contained in stub bindings"; //$NON-NLS-1$
        assert !oldMap.getParentStub().contains(oldPlugin) : "Post oldPlugin contained in the map parent stubs"; //$NON-NLS-1$

        assert oldPlugin.getIn().size() == 0 : "Post InBindings size is not zero"; //$NON-NLS-1$
        assert oldPlugin.getOut().size() == 0 : "Post OutBindings size is not zero"; //$NON-NLS-1$
    }

}