package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.InBinding;
import ucm.map.NodeConnection;
import ucm.map.PluginBinding;
import ucm.map.StartPoint;

/**
 * @author TremblaE
 *
 */
public class DeleteInBindingCommand extends Command implements JUCMNavCommand {

	private PluginBinding plugin;
	private StartPoint start;
	private InBinding in;
	private NodeConnection stubEntry;
	
	/**
	 * 
	 */
	public DeleteInBindingCommand(InBinding in) {
		super();
		this.in = in;
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
		plugin = in.getBinding();
		start = in.getStartPoint();
		stubEntry = in.getStubEntry();
		
		redo();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		plugin.getIn().remove(in);
		start.getInBindings().remove(in);
		stubEntry.getInBindings().remove(in);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		plugin.getIn().add(in);
		start.getInBindings().add(in);
		stubEntry.getInBindings().add(in);
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
