package seg.jUCMNav.model.commands.change;

import org.eclipse.gef.commands.Command;

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
	setLabel("Change PluginBinding Probability");
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
	assert pluginBinding != null : "Post Plugin Binding is null";
	assert pluginBinding.getProbability() == newProbability : "Post newProbability changed";
    }

    public void testPreConditions() {
	assert pluginBinding != null : "Pre Plugin Binding is null";
	assert pluginBinding.getProbability() == oldProbability : "Pre newProbability changed";
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