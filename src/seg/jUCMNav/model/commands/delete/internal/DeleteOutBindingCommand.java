package seg.jUCMNav.model.commands.delete.internal;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OutBinding;
import ucm.map.PluginBinding;

/**
 * Deletes a plugin's outbinding.
 * 
 * @author TremblaE
 * @author gunterm (added support for aspect stub bindings)
 *  
 */
public class DeleteOutBindingCommand extends Command implements JUCMNavCommand {

    private PluginBinding plugin;
	// for AoUCM, an end point or a node connection may be specified as end
    private Object end;
    private OutBinding out;
    private NodeConnection stubExit;

    private int index;

    /**
     * @param out
     *            the outbinding to be deleted.
     */
    public DeleteOutBindingCommand(OutBinding out) {
        super();
        this.out = out;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        plugin = out.getBinding();
        end = out.getEndPoint();
        if (end == null)
        	end = out.getPointcutEntry();
        stubExit = out.getStubExit();

        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        index = plugin.getOut().indexOf(out);
        plugin.getOut().remove(out);
        if (end instanceof EndPoint)
        	((EndPoint) end).getOutBindings().remove(out);
        else
        	((NodeConnection) end).getOutBindingsPlugin().remove(out);
        stubExit.getOutBindings().remove(out);
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        plugin.getOut().add(index, out);
        if (end instanceof EndPoint)
        	((EndPoint) end).getOutBindings().add(out);
        else
        	((NodeConnection) end).getOutBindingsPlugin().add(out);
        stubExit.getOutBindings().add(out);
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert out != null : "Pre Outbinding is null"; //$NON-NLS-1$

        assert (out.getEndPoint() == end || out.getPointcutEntry() == end): "Pre End point/pointcut entry changed"; //$NON-NLS-1$
        assert out.getStubExit() == stubExit : "Pre stub exit changed"; //$NON-NLS-1$
        assert out.getBinding() == plugin : "Pre PluginBinding changed"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert out != null : "Post Outbinding is null"; //$NON-NLS-1$

        assert out.getEndPoint() == null : "Post end point changed"; //$NON-NLS-1$
        assert out.getPointcutEntry() == null : "Post pointcut entry changed"; //$NON-NLS-1$
        assert out.getStubExit() == null : "Post stub exit changed"; //$NON-NLS-1$
        assert out.getBinding() == null : "Post PluginBinding changed"; //$NON-NLS-1$
    }
}