package seg.jUCMNav.model.commands.change;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.PluginBinding;

public class ChangePluginBindingProbCommand extends Command implements JUCMNavCommand {

    private PluginBinding pluginBinding;
    private double oldProbability;
    private double newProbability;

    public ChangePluginBindingProbCommand(PluginBinding pb, double prob) {
        super();
        this.pluginBinding = pb;
        this.newProbability = prob;
        this.oldProbability = pluginBinding.getProbability();
        setLabel(Messages.getString("ChangePluginBindingProbCommand.ChangePluginBindingProbability")); //$NON-NLS-1$
    }

    public void execute() {
        redo();
    }

    public boolean canExecute() {
        return pluginBinding != null;
    }

    public void redo() {
        testPreConditions();
        pluginBinding.setProbability(newProbability);
        testPostConditions();
    }

    public void undo() {
        testPostConditions();
        pluginBinding.setProbability(oldProbability);
        testPreConditions();
    }

    public void testPostConditions() {
        assert pluginBinding != null : "Post Plugin Binding is null"; //$NON-NLS-1$
        assert pluginBinding.getProbability() == newProbability : "Post newProbability changed"; //$NON-NLS-1$
    }

    public void testPreConditions() {
        assert pluginBinding != null : "Pre Plugin Binding is null"; //$NON-NLS-1$
        assert pluginBinding.getProbability() == oldProbability : "Pre newProbability changed"; //$NON-NLS-1$
    }

    public double getProbability() {
        return newProbability;
    }

    public void setProbability(double probability) {
        this.newProbability = probability;
    }

    public PluginBinding getPluginBinding() {
        return pluginBinding;
    }
}