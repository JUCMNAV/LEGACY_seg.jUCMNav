package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OutBinding;
import ucm.map.PluginBinding;

/**
 * @author TremblaE
 *
 */
public class DeleteOutBindingCommand extends Command implements JUCMNavCommand {
	
	private PluginBinding plugin;
	private EndPoint end;
	private OutBinding out;
	private NodeConnection stubExit;
	
	/**
	 * 
	 */
	public DeleteOutBindingCommand(OutBinding out) {
		super();
		this.out = out;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	public boolean canUndo() {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		plugin = out.getBinding();
		end = out.getEndPoint();
		stubExit = out.getStubExit();
		
		redo();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		plugin.getOut().remove(out);
		end.getOutBindings().remove(out);
		stubExit.getOutBindings().remove(out);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		plugin.getOut().add(out);
		end.getOutBindings().add(out);
		stubExit.getOutBindings().add(out);
	}
	
	/* (non-Javadoc)
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
	 */
	public void testPreConditions() {

	}

	/* (non-Javadoc)
	 * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
	 */
	public void testPostConditions() {

	}
}