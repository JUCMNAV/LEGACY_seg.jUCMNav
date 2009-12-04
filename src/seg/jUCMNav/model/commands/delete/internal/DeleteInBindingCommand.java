package seg.jUCMNav.model.commands.delete.internal;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.InBinding;
import ucm.map.NodeConnection;
import ucm.map.PluginBinding;
import ucm.map.StartPoint;

/**
 * Removes pluginbinding's inbindings.
 * 
 * @author TremblaE
 * 
 */
public class DeleteInBindingCommand extends Command implements JUCMNavCommand {

    private PluginBinding plugin;
    private StartPoint start;
    private InBinding in;
    private NodeConnection stubEntry;
    private int index;

    /**
     * @param in
     *            the InBinding to be deleted.
     */
    public DeleteInBindingCommand(InBinding in) {
        super();
        this.in = in;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        plugin = in.getBinding();
        start = in.getStartPoint();
        stubEntry = in.getStubEntry();

        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        index = plugin.getIn().indexOf(in);
        plugin.getIn().remove(in);
        start.getInBindings().remove(in);
        stubEntry.getInBindings().remove(in);

        testPostConditions();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        plugin.getIn().add(index, in);
        start.getInBindings().add(in);
        stubEntry.getInBindings().add(in);

        testPreConditions();
    }

    /**
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert in != null : "Pre Inbindin is null"; //$NON-NLS-1$

        assert in.getStartPoint() == start : "Pre Start point changed"; //$NON-NLS-1$
        assert in.getStubEntry() == stubEntry : "Pre stub entry changed"; //$NON-NLS-1$
        assert in.getBinding() == plugin : "Pre PluginBinding changed"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert in != null : "Post Inbinding is null"; //$NON-NLS-1$

        assert in.getStartPoint() == null : "Post Start point changed"; //$NON-NLS-1$
        assert in.getStubEntry() == null : "Post stub entry changed"; //$NON-NLS-1$
        assert in.getBinding() == null : "Post PluginBinding changed"; //$NON-NLS-1$
    }
}