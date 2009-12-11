package seg.jUCMNav.model.commands.change;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.PluginBinding;

public class ChangePluginBindingRepFactorCommand extends Command implements JUCMNavCommand {

    private PluginBinding pluginBinding;
    private int oldRepFactor;
    private int newRepFactor;

    public ChangePluginBindingRepFactorCommand(PluginBinding pb, int repFactor) {
        super();
        this.pluginBinding = pb;
        this.newRepFactor = repFactor;
        this.oldRepFactor = pluginBinding.getReplicationFactor();
        setLabel(Messages.getString("ChangePluginBindingRepFactorCommand_RepFactorLabel")); //$NON-NLS-1$
    }

    public void execute() {
        redo();
    }

    public boolean canExecute() {
        return pluginBinding != null;
    }

    public void redo() {
        testPreConditions();
        pluginBinding.setReplicationFactor(newRepFactor);
        testPostConditions();
    }

    public void undo() {
        testPostConditions();
        pluginBinding.setReplicationFactor(oldRepFactor);
        testPreConditions();
    }

    public void testPostConditions() {
        assert pluginBinding != null : "Post Plugin Binding is null"; //$NON-NLS-1$
        assert pluginBinding.getReplicationFactor() == newRepFactor : "Post newRepFactor changed"; //$NON-NLS-1$
    }

    public void testPreConditions() {
        assert pluginBinding != null : "Pre Plugin Binding is null"; //$NON-NLS-1$
        assert pluginBinding.getReplicationFactor() == oldRepFactor : "Pre newRepFactor changed"; //$NON-NLS-1$
    }

    public double getRepFactor() {
        return newRepFactor;
    }

    public void setRepFactor(int repFactor) {
        this.newRepFactor = repFactor;
    }

    public PluginBinding getPluginBinding() {
        return pluginBinding;
    }
}