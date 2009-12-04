package seg.jUCMNav.model.commands.delete.internal;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.PluginBinding;
import ucm.map.Stub;
import ucm.map.UCMmap;
import urn.URNspec;

/**
 * This command deletes a PluginBinding from a Stub, but doesn't remove the in/out bindings first. See DeletePluginCommand for such behaviour.
 * 
 * Not intended to be called by something else that DeletePluginCommand
 * 
 * @author Etienne Tremblay
 */
public class RemoveBindingCommand extends Command implements JUCMNavCommand {

    // The PluginBinding to delete.
    private PluginBinding oldPlugin;

    // The stub owner of the PluginBinding to delete.
    private Stub stub;

    // The map of the PluginBinding
    private UCMmap oldMap;

    // The URNspec of the file
    private URNspec urnSpec;

    private int index;

    /**
     * This command delete a PluginBinding from a Stub. Infers URNspec from stub.
     * 
     * @param oldPlugin
     *            The PluginBinding to delete from the Stub.
     */
    public RemoveBindingCommand(PluginBinding oldPlugin) {
        super();
        this.oldPlugin = oldPlugin;
    }

    /**
     * This command delete a PluginBinding from a Stub.
     * 
     * @param oldPlugin
     *            The PluginBinding to delete from the Stub.
     * 
     * @param urnSpec
     *            the URNspec containing the plugin
     */
    public RemoveBindingCommand(PluginBinding oldPlugin, URNspec urnSpec) {
        super();
        this.oldPlugin = oldPlugin;
        this.urnSpec = urnSpec;
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
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

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        stub = oldPlugin.getStub();
        oldMap = oldPlugin.getPlugin();
        if (urnSpec == null)
            urnSpec = oldMap.getUrndefinition().getUrnspec();

        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        index = stub.getBindings().indexOf(oldPlugin);
        stub.getBindings().remove(oldPlugin);

        oldMap.getParentStub().remove(oldPlugin);
        testPostConditions();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        oldMap.getParentStub().add(oldPlugin);
        stub.getBindings().add(index, oldPlugin);

        testPreConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert oldPlugin != null : "Pre oldPlugin is null"; //$NON-NLS-1$
        assert stub != null : "Pre stub is null"; //$NON-NLS-1$
        assert oldMap != null : "Pre the associated map is null"; //$NON-NLS-1$

        assert stub.getBindings().contains(oldPlugin) : "Pre oldPlugin not contained in stub bindings"; //$NON-NLS-1$
        assert oldMap.getParentStub().contains(oldPlugin) : "Pre oldPlugin not contained in the map parent stubs"; //$NON-NLS-1$
    }

    /**
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