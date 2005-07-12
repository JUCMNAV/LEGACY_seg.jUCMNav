package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OutBinding;
import ucm.map.PluginBinding;
import urn.URNspec;

/**
 * Adds an out-binding between a stub and one of its plugin's end points.
 * 
 * @author Etienne Tremblay
 */
public class AddOutBindingCommand extends Command implements JUCMNavCommand {

    private PluginBinding plugin;
    private EndPoint end;
    private NodeConnection exit;
    private OutBinding out;
    private URNspec urnSpec;

    /**
     * @param plugin
     *            the concerned plugin binding
     * @param end
     *            one of the plugin's end points
     * @param exit
     *            the stub's exit connection
     */
    public AddOutBindingCommand(PluginBinding plugin, EndPoint end, NodeConnection exit) {
        super();
        this.plugin = plugin;
        this.end = end;
        this.exit = exit;
        setLabel(Messages.getString("AddOutBinding.addOutBinding")); //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (plugin != null && end != null && exit != null)
            return true;
        else
            return false;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        urnSpec = plugin.getPlugin().getUcmspec().getUrnspec();

        out = (OutBinding) ModelCreationFactory.getNewObject(urnSpec, OutBinding.class);

        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        plugin.getOut().add(out);
        out.setEndPoint(end);
        out.setStubExit(exit);

        testPostConditions();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        plugin.getOut().remove(out);
        out.setEndPoint(null);
        out.setStubExit(null);

        testPreConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert plugin != null : "Pre plugin null"; //$NON-NLS-1$
        assert end != null : "Pre end point null"; //$NON-NLS-1$
        assert exit != null : "Pre exit connection null"; //$NON-NLS-1$
        assert !plugin.getOut().contains(out) : "Pre plugin contains the out binding"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert plugin != null : "Post plugin null"; //$NON-NLS-1$
        assert end != null : "Post end point null"; //$NON-NLS-1$
        assert exit != null : "Post exit connection null"; //$NON-NLS-1$
        assert plugin.getOut().contains(out) : "Post plugin doesn't contains the out binding"; //$NON-NLS-1$
    }
}