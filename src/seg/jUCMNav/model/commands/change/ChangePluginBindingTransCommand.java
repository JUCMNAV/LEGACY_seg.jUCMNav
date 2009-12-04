package seg.jUCMNav.model.commands.change;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
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
        setLabel(Messages.getString("ChangePluginBindingTransCommand.ChangePluginBindingTransaction")); //$NON-NLS-1$
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
        assert pluginBinding != null : "Post Plugin Binding is null"; //$NON-NLS-1$
        boolean newValue = pluginBinding.isTransaction();
        assert newValue == this.newTransaction : "Post Transaction changed"; //$NON-NLS-1$
    }

    public void testPreConditions() {
        assert pluginBinding != null : "Pre Plugin Binding is null"; //$NON-NLS-1$
        boolean oldValue = pluginBinding.isTransaction();
        assert oldValue == this.oldTransaction : "Pre Transaction changed"; //$NON-NLS-1$
    }

    public boolean getTransaction() {
        return newTransaction;
    }

    public void setTransaction(boolean transaction) {
        this.newTransaction = transaction;
    }

    public PluginBinding getPluginBinding() {
        return pluginBinding;
    }
}