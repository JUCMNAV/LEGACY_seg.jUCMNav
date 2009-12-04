package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentBinding;
import ucm.map.ComponentRef;
import ucm.map.PluginBinding;
import urn.URNspec;
import urncore.Component;
import urncore.ComponentKind;

/**
 * Adds a component binding between a parent and a plug-in map.
 * 
 * @author jkealey
 */
public class AddComponentBindingCommand extends Command implements JUCMNavCommand {

    private PluginBinding plugin;

    private ComponentRef parent;

    private ComponentRef child;

    private ComponentBinding binding;

    private boolean isContext;
    private ComponentKind kind;

    private URNspec urnSpec;

    /**
     * @param plugin
     *            the concerned plugin binding
     * @param parent
     *            the parent component
     * @param child
     *            the plug-in component
     */
    public AddComponentBindingCommand(PluginBinding plugin, ComponentRef parent, ComponentRef child) {
        super();
        this.plugin = plugin;
        this.parent = parent;
        this.child = child;
        setLabel(Messages.getString("AddComponentBindingCommand.AddComponentBinding")); //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (plugin != null && parent != null && child != null)
            return true;
        else
            return false;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        urnSpec = plugin.getPlugin().getUrndefinition().getUrnspec();

        binding = (ComponentBinding) ModelCreationFactory.getNewObject(urnSpec, ComponentBinding.class);
        isContext = ((Component) child.getContDef()).isContext();
        kind = ((Component) child.getContDef()).getKind();

        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        plugin.getComponents().add(binding);
        binding.setParentComponent(parent);
        binding.setPluginComponent(child);
        ((Component) child.getContDef()).setContext(true);
        ((Component) child.getContDef()).setKind(ComponentKind.TEAM_LITERAL);

        testPostConditions();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        plugin.getComponents().remove(binding);
        binding.setParentComponent(null);
        binding.setPluginComponent(null);

        ((Component) child.getContDef()).setContext(isContext);
        ((Component) child.getContDef()).setKind(kind);

        testPreConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert plugin != null : "Pre plugin null"; //$NON-NLS-1$
        assert parent != null : "Pre parent null"; //$NON-NLS-1$
        assert child != null : "Pre child null"; //$NON-NLS-1$
        assert !plugin.getComponents().contains(binding) : "Pre plugin contains the binding"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert plugin != null : "Post plugin null"; //$NON-NLS-1$
        assert parent != null : "Post parent null"; //$NON-NLS-1$
        assert child != null : "Post child null"; //$NON-NLS-1$
        assert plugin.getComponents().contains(binding) : "Post plugin doesn't contain the binding"; //$NON-NLS-1$
    }
}