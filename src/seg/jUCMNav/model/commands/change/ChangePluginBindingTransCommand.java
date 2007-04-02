package seg.jUCMNav.model.commands.change;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.PluginBinding;

public class ChangePluginBindingTransCommand extends Command implements JUCMNavCommand {

    private PluginBinding pluginBinding;
    private boolean oldTransaction;
    private boolean newTransaction;

    public ChangePluginBindingTransCommand(PluginBinding pb, boolean transaction) {
	super();
	this.pluginBinding = pb;
	this.newTransaction = transaction;
	this.oldTransaction = pluginBinding.isTransaction();
	setLabel("Change PluginBinding Transaction");
    }

    public void execute() {
	redo();
    }

    public boolean canExecute() {
	return pluginBinding != null;
    }

    public void redo() {
	testPreConditions();
	pluginBinding.setTransaction(newTransaction);
	testPostConditions();
    }

    public void undo() {
	testPostConditions();
	pluginBinding.setTransaction(oldTransaction);
	testPreConditions();
    }

    public void testPostConditions() {
	assert pluginBinding != null : "Post Plugin Binding is null";
	boolean newValue = pluginBinding.isTransaction(); 
	assert newValue == this.newTransaction : "Post Transaction changed";
    }

    public void testPreConditions() {
	assert pluginBinding != null : "Pre Plugin Binding is null";
	boolean oldValue = pluginBinding.isTransaction(); 
	assert oldValue == this.oldTransaction : "Pre Transaction changed";
    }
}