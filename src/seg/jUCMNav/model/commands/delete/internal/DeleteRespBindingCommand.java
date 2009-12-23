package seg.jUCMNav.model.commands.delete.internal;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.ResponsibilityBinding;
import urncore.Responsibility;

/**
 * Deletes a plugin's component binding.
 * 
 * @author jkealey
 * 
 */
public class DeleteRespBindingCommand extends Command implements JUCMNavCommand {

    private PluginBinding plugin;
    private Responsibility parent;
    private RespRef child;
    private ResponsibilityBinding binding;

    private int index;
    private boolean aborted=false;

    /**
     * @param binding
     *            the binding to be deleted.
     */
    public DeleteRespBindingCommand(ResponsibilityBinding binding) {
        super();
        this.binding = binding;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        plugin = binding.getBinding();
        parent = binding.getParentResp();
        child = binding.getPluginResp();

        if (plugin==null || parent==null || child==null)
            aborted=true;
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (aborted) return;
        index = plugin.getResponsibilities().indexOf(binding);
        plugin.getResponsibilities().remove(binding);
        binding.setParentResp(null);
        binding.setPluginResp(null);
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted) return;
        plugin.getResponsibilities().add(index, binding);
        binding.setParentResp(parent);
        binding.setPluginResp(child);
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert binding != null : "Pre binding is null"; //$NON-NLS-1$

        assert binding.getParentResp() == parent : "Pre parent changed"; //$NON-NLS-1$
        assert binding.getPluginResp() == child : "Pre child changed"; //$NON-NLS-1$
        assert binding.getBinding() == plugin : "Pre PluginBinding changed"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert binding != null : "Post binding is null"; //$NON-NLS-1$

        assert binding.getParentResp() == null : "Post parent changed"; //$NON-NLS-1$
        assert binding.getPluginResp() == null : "Post plugin changed"; //$NON-NLS-1$
        assert binding.getBinding() == null : "Post PluginBinding changed"; //$NON-NLS-1$
    }
}