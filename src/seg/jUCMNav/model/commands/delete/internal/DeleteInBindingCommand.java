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
 * @author gunterm (added support for aspect stub bindings)
 * 
 */
public class DeleteInBindingCommand extends Command implements JUCMNavCommand {

    private PluginBinding plugin;
	// for AoUCM, a start point or a node connection may be specified as start
    private Object start;
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
        if (start == null)
        	start = in.getPointcutExit();
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
        if (start instanceof StartPoint)
        	((StartPoint) start).getInBindings().remove(in);
        else
        	((NodeConnection) start).getInBindingsPlugin().remove(in);
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
        if (start instanceof StartPoint)
        	((StartPoint) start).getInBindings().add(in);
        else
        	((NodeConnection) start).getInBindingsPlugin().add(in);
        stubEntry.getInBindings().add(in);

        testPreConditions();
    }

    /**
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert in != null : "Pre Inbindin is null"; //$NON-NLS-1$

        assert (in.getStartPoint() == start || in.getPointcutExit() == start): "Pre Start point/pointcut exit changed"; //$NON-NLS-1$
        assert in.getStubEntry() == stubEntry : "Pre stub entry changed"; //$NON-NLS-1$
        assert in.getBinding() == plugin : "Pre PluginBinding changed"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert in != null : "Post Inbinding is null"; //$NON-NLS-1$

        assert in.getStartPoint() == null : "Post Start point changed"; //$NON-NLS-1$
        assert in.getPointcutExit() == null : "Post pointcut exit changed"; //$NON-NLS-1$
        assert in.getStubEntry() == null : "Post stub entry changed"; //$NON-NLS-1$
        assert in.getBinding() == null : "Post PluginBinding changed"; //$NON-NLS-1$
    }
}