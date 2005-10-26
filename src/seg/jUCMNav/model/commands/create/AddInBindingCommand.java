package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.InBinding;
import ucm.map.NodeConnection;
import ucm.map.PluginBinding;
import ucm.map.StartPoint;
import urn.URNspec;

/**
 * Adds an in-binding between a stub and one of its plugin's start points.
 * 
 * @author Etienne Tremblay
 */
public class AddInBindingCommand extends Command implements JUCMNavCommand {

    private PluginBinding plugin;

    private StartPoint start;

    private NodeConnection entry;

    private InBinding in;

    private URNspec urnSpec;

    /**
     * @param plugin
     *            the concerned plugin binding
     * @param start
     *            one of the plugin's start points
     * @param entry
     *            the stub's entry connection
     */
    public AddInBindingCommand(PluginBinding plugin, StartPoint start, NodeConnection entry) {
        super();
        this.plugin = plugin;
        this.start = start;
        this.entry = entry;
        setLabel(Messages.getString("AddInBinding.addnBinding")); //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (plugin != null && start != null && entry != null)
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

        in = (InBinding) ModelCreationFactory.getNewObject(urnSpec, InBinding.class);

        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        plugin.getIn().add(in);
        in.setStartPoint(start);
        in.setStubEntry(entry);

        testPostConditions();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        plugin.getIn().remove(in);
        in.setStartPoint(null);
        in.setStubEntry(null);

        testPreConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert plugin != null : "Pre plugin null"; //$NON-NLS-1$
        assert start != null : "Pre start point null"; //$NON-NLS-1$
        assert entry != null : "Pre entry connection null"; //$NON-NLS-1$
        assert !plugin.getIn().contains(in) : "Pre plugin contains the in binding"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert plugin != null : "Post plugin null"; //$NON-NLS-1$
        assert start != null : "Post start point null"; //$NON-NLS-1$
        assert entry != null : "Post entry connection null"; //$NON-NLS-1$
        assert plugin.getIn().contains(in) : "Post plugin doesn't contains the in binding"; //$NON-NLS-1$
    }
}