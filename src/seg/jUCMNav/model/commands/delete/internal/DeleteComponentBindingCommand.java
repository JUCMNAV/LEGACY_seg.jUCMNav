package seg.jUCMNav.model.commands.delete.internal;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentBinding;
import ucm.map.ComponentRef;
import ucm.map.PluginBinding;

/**
 * Deletes a plugin's component binding.
 * 
 * @author jkealey
 * 
 */
public class DeleteComponentBindingCommand extends Command implements JUCMNavCommand {

    private PluginBinding plugin;
    private ComponentRef parent;
    private ComponentRef child;
    private ComponentBinding binding;

    private int index;

    /**
     * @param binding
     *            the binding to be deleted.
     */
    public DeleteComponentBindingCommand(ComponentBinding binding) {
        super();
        this.binding = binding;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        plugin = binding.getBinding();
        parent = binding.getParentComponent();
        child = binding.getPluginComponent();

        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        index = plugin.getComponents().indexOf(binding);
        plugin.getComponents().remove(binding);
        binding.setParentComponent(null);
        binding.setPluginComponent(null);
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        plugin.getComponents().add(index, binding);
        binding.setParentComponent(parent);
        binding.setPluginComponent(child);
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert binding != null : "Pre binding is null"; //$NON-NLS-1$

        assert binding.getParentComponent() == parent : "Pre parent changed"; //$NON-NLS-1$
        assert binding.getPluginComponent() == child : "Pre child changed"; //$NON-NLS-1$
        assert binding.getBinding() == plugin : "Pre PluginBinding changed"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert binding != null : "Post binding is null"; //$NON-NLS-1$

        assert binding.getParentComponent() == null : "Post parent changed"; //$NON-NLS-1$
        assert binding.getPluginComponent() == null : "Post plugin changed"; //$NON-NLS-1$
        assert binding.getBinding() == null : "Post PluginBinding changed"; //$NON-NLS-1$
    }
}