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
 *  
 */
public class DeleteOutBindingCommand extends Command implements JUCMNavCommand {

    private PluginBinding plugin;
    private EndPoint end;
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
        stubExit = out.getStubExit();

        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
    	index = plugin.getOut().indexOf(out);
        plugin.getOut().remove(out);
        end.getOutBindings().remove(out);
        stubExit.getOutBindings().remove(out);
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        plugin.getOut().add(index, out);
        end.getOutBindings().add(out);
        stubExit.getOutBindings().add(out);
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert out != null : "Pre Outbinding is null"; //$NON-NLS-1$

        assert out.getEndPoint() == end : "Pre End point changed"; //$NON-NLS-1$
        assert out.getStubExit() == stubExit : "Pre stub exit changed"; //$NON-NLS-1$
        assert out.getBinding() == plugin : "Pre PluginBinding changed"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert out != null : "Post Outbinding is null"; //$NON-NLS-1$

        assert out.getEndPoint() == null : "Post end point changed"; //$NON-NLS-1$
        assert out.getStubExit() == null : "Post stub exit changed"; //$NON-NLS-1$
        assert out.getBinding() == null : "Post PluginBinding changed"; //$NON-NLS-1$
    }
}