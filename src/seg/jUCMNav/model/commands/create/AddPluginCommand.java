package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.PluginBinding;
import ucm.map.Stub;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.Condition;

/**
 * Creates a new plugin binding between a stub and a map. Doesn't check to see if should be valid.
 * 
 * @author Etienne Tremblay, gunterm
 */
public class AddPluginCommand extends Command implements JUCMNavCommand {

    private Stub stub;
    private UCMmap map;
    private PluginBinding plugin;
    private URNspec urnSpec;
    private Condition condition;

    /**
     * @param stub
     *            the stub
     * @param map
     *            its new plugin
     */
    public AddPluginCommand(Stub stub, UCMmap map) {
        super();
        this.stub = stub;
        this.map = map;
        setLabel(Messages.getString("AddPlugin.addPlugin")); //$NON-NLS-1$
    }

    /**
     * @param stub
     *            the stub
     * @param map
     *            its new plugin
     */
    public AddPluginCommand(Stub stub, UCMmap map, Condition condition) {
        super();
        this.stub = stub;
        this.map = map;
        this.condition = condition;
        setLabel(Messages.getString("AddPlugin.addPlugin")); //$NON-NLS-1$
    }

    /**
     * Should check to see if doesn't cause circular relationship.
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (stub != null && map != null)
            return true;
        else
            return false;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (getPlugin()==null)
            build();

        redo();
    }

    public void build() {
        urnSpec = map.getUrndefinition().getUrnspec();

        setPlugin((PluginBinding) ModelCreationFactory.getNewObject(urnSpec, PluginBinding.class));
        if (condition != null)
            getPlugin().setPrecondition(condition);
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        // first assign the pluginbinding to the map and then to the stub (this ensures that when
        // the stub is notified about a change in its plugins, the map is already associated with
        // the plugin) - this is important for the Concern outline!
        map.getParentStub().add(getPlugin());
        stub.getBindings().add(getPlugin());

        testPostConditions();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        stub.getBindings().remove(getPlugin());
        map.getParentStub().remove(getPlugin());

        testPreConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert stub != null : "Pre stub null"; //$NON-NLS-1$
        assert getPlugin() != null : "Pre plugin null"; //$NON-NLS-1$
        assert !stub.getBindings().contains(getPlugin()) : "Pre plugin contained in stub plugins"; //$NON-NLS-1$
        assert !map.getParentStub().contains(getPlugin()) : "Pre plugin contained in map parent stub"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert stub != null : "Post stub null"; //$NON-NLS-1$
        assert getPlugin() != null : "Post plugin null"; //$NON-NLS-1$
        assert stub.getBindings().contains(getPlugin()) : "Post plugin not contained in stub plugins"; //$NON-NLS-1$
        assert map.getParentStub().contains(getPlugin()) : "Post plugin not contained in map parent stub"; //$NON-NLS-1$
    }

    public void setPlugin(PluginBinding plugin) {
        this.plugin = plugin;
    }

    public PluginBinding getPlugin() {
        return plugin;
    }
}