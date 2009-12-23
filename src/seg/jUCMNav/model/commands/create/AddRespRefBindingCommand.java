package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.ResponsibilityBinding;
import urn.URNspec;
import urncore.Responsibility;

/**
 * Adds a component binding between a parent and a plug-in map.
 * 
 * @author jkealey
 */
public class AddRespRefBindingCommand extends Command implements JUCMNavCommand {

    private PluginBinding plugin;

    private Responsibility parent;

    private RespRef child;

    private ResponsibilityBinding binding;

    private boolean isContext;
//    private ComponentKind kind;

    private URNspec urnSpec;

    /**
     * @param plugin
     *            the concerned plugin binding
     * @param parent
     *            the parent component
     * @param child
     *            the plug-in component
     */
    public AddRespRefBindingCommand(PluginBinding plugin, Responsibility parent, RespRef child) {
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

    @Override
    public boolean canUndo() {
        return plugin.getResponsibilities().contains(binding);
    }
    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        urnSpec = plugin.getPlugin().getUrndefinition().getUrnspec();

        binding = (ResponsibilityBinding) ModelCreationFactory.getNewObject(urnSpec, ResponsibilityBinding.class);
        isContext = ((Responsibility) child.getRespDef()).isContext();

        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        plugin.getResponsibilities().add(binding);
        binding.setParentResp(parent);
        binding.setPluginResp(child);
        ((Responsibility) child.getRespDef()).setContext(true);

        testPostConditions();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        plugin.getResponsibilities().remove(binding);
        binding.setParentResp(null);
        binding.setPluginResp(null);

        ((Responsibility) child.getRespDef()).setContext(isContext);

        testPreConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert plugin != null : "Pre plugin null"; //$NON-NLS-1$
        assert parent != null : "Pre parent null"; //$NON-NLS-1$
        assert child != null : "Pre child null"; //$NON-NLS-1$
        assert !plugin.getResponsibilities().contains(binding) : "Pre plugin contains the binding"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert plugin != null : "Post plugin null"; //$NON-NLS-1$
        assert parent != null : "Post parent null"; //$NON-NLS-1$
        assert child != null : "Post child null"; //$NON-NLS-1$
        assert plugin.getResponsibilities().contains(binding) : "Post plugin doesn't contain the binding"; //$NON-NLS-1$
    }
}