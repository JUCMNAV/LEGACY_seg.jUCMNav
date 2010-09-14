package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OutBinding;
import ucm.map.PluginBinding;
import ucm.map.Stub;
import urn.URNspec;

/**
 * Adds an out-binding between a stub and one of its plugin's end points.
 * 
 * @author Etienne Tremblay
 * @author gunterm (added support for aspect stub bindings)
 * 
 */
public class AddOutBindingCommand extends Command implements JUCMNavCommand {

	// there are two options now
	// 1) UCM: plugin and end are specified
	// 2) AoUCM: a stub is specified (because the PluginBinding has not yet been created, there will be 
	//           only one pluginBinding for the stub which is retrieved during execution); in this case,
	//           an end point (end) or a node connection (end2 = entry of pointcut stub) may be specified
    private PluginBinding plugin;
    private Stub stub;
    private EndPoint end;
    private NodeConnection end2;
    
    private NodeConnection exit;
    private OutBinding out;
    private URNspec urnSpec;

    private boolean delayedExecution = false;
    private boolean aborted = false;
    private int delayedIndex = 0;

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
        this.stub = null;
        this.end = end;
        this.end2 = null;
        this.exit = exit;
        this.delayedExecution = false;
        setLabel(Messages.getString("AddOutBinding.addOutBinding")); //$NON-NLS-1$
    }

    /**
     * @param stub
     *            the concerned stub (static, i.e., only one PluginBinding)
     * @param end
     *            one of the plugin's end points or in-path of pointcut stub
     * @param exit
     *            the stub's exit connection
     */
    public AddOutBindingCommand(Stub stub, Object end) {
        super();
        this.plugin = null;
        this.stub = stub;
        assert (end instanceof EndPoint || end instanceof NodeConnection) : "end parameter must be EndPoint or NodeConnection"; //$NON-NLS-1$
        if (end instanceof EndPoint) {
            this.end = (EndPoint) end;
            this.end2 = null;
        } else {
            this.end = null;
            this.end2 = (NodeConnection) end;
        }
        // don't set exit because it's delayed; index is 0 because there is only one out-path for the aspect stub
        this.delayedIndex = 0;
        this.delayedExecution = true;
        setLabel(Messages.getString("AddOutBinding.addOutBinding")); //$NON-NLS-1$
    }
    
    /**
     * @param plugin
     *            the concerned plugin binding
     * @param end
     *            one of the plugin's end points
     * @param index
     *            the stub's exit connection index
     */
    public AddOutBindingCommand(PluginBinding plugin, EndPoint end, int index) {
        super();
        this.plugin = plugin;
        this.stub = null;
        this.end = end;
        this.end2 = null;
        this.delayedIndex = index;
        this.delayedExecution = true;
        setLabel(Messages.getString("AddOutBinding.addOutBinding")); //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
    	if ((plugin != null && end != null) || (stub != null && (end != null || end2 != null)) && (exit != null || delayedExecution))
            return true;
        else
            return false;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
    	if (plugin != null) {
            urnSpec = plugin.getPlugin().getUrndefinition().getUrnspec();    		
    	} else {
    		urnSpec = stub.getDiagram().getUrndefinition().getUrnspec();
    	}
    	
        out = (OutBinding) ModelCreationFactory.getNewObject(urnSpec, OutBinding.class);

        if (delayedExecution) {
        	
            if (plugin != null && plugin.getStub().getSucc().size() > delayedIndex) {
            	exit = (NodeConnection) plugin.getStub().getSucc().get(delayedIndex);
            } else if (stub != null && stub.getSucc().size() > delayedIndex) {
               	exit = (NodeConnection) stub.getSucc().get(delayedIndex);
            } else {
                aborted = true;            	
            }
        }
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (aborted)
            return;
        testPreConditions();

        if (plugin != null) {
            plugin.getOut().add(out);        	
        } else {
        	((PluginBinding) stub.getBindings().get(0)).getOut().add(out);
        }
        out.setEndPoint(end);
        out.setPointcutEntry(end2);
        out.setStubExit(exit);

        testPostConditions();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();

        if (plugin != null) {
            plugin.getOut().remove(out);        	
        } else {
        	((PluginBinding) stub.getBindings().get(0)).getOut().remove(out);
        }
        out.setEndPoint(null);
        out.setPointcutEntry(null);
        out.setStubExit(null);

        testPreConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert (plugin != null || stub != null) : "Pre plugin or stub null"; //$NON-NLS-1$
        if (stub != null) {
        	assert (stub.getBindings().size() == 1) : "Pre stub must have one plugin"; //$NON-NLS-1$
        }
        assert (end != null || end2 != null) : "Pre end null"; //$NON-NLS-1$
        assert exit != null : "Pre exit connection null"; //$NON-NLS-1$
        if (plugin != null) {
        	assert !plugin.getOut().contains(out) : "Pre plugin contains the out binding"; //$NON-NLS-1$	
        }
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert (plugin != null || stub != null) : "Post plugin or stub null"; //$NON-NLS-1$
        if (stub != null) {
        	assert (stub.getBindings().size() == 1) : "Post stub must have one plugin"; //$NON-NLS-1$
        }
        assert (end != null || end2 != null) : "Post end null"; //$NON-NLS-1$
        assert exit != null : "Post exit connection null"; //$NON-NLS-1$
        if (plugin != null) {
            assert plugin.getOut().contains(out) : "Post plugin doesn't contains the out binding"; //$NON-NLS-1$        	
        }
    }
}