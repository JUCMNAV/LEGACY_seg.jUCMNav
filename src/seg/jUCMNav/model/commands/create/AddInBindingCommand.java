package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.InBinding;
import ucm.map.NodeConnection;
import ucm.map.PluginBinding;
import ucm.map.StartPoint;
import ucm.map.Stub;
import urn.URNspec;

/**
 * Adds an in-binding between a stub and one of its plugin's start points.
 * 
 * @author Etienne Tremblay
 * @author gunterm (added support for aspect stub bindings)
 * 
 */
public class AddInBindingCommand extends Command implements JUCMNavCommand {

	// there are two options now
	// 1) UCM: plugin and start are specified
	// 2) AoUCM: a stub is specified (because the PluginBinding has not yet been created, there will be 
	//           only one pluginBinding for the stub which is retrieved during execution); in this case,
	//           a start point (start) or a node connection (start2 = exit of pointcut stub) may be specified
    private PluginBinding plugin;
    private Stub stub;
    private StartPoint start;
    private NodeConnection start2;

    private NodeConnection entry;

    private InBinding in;

    private URNspec urnSpec;

    private boolean delayedExecution = false;
    private boolean aborted = false;
    private int delayedIndex = 0;

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
        this.stub = null;
        this.start = (StartPoint) start;
        this.start2 = null;
        this.entry = entry;
        this.delayedExecution = false;
        setLabel(Messages.getString("AddInBinding.addnBinding")); //$NON-NLS-1$
    }

    /**
     * @param stub
     *            the concerned stub (static, i.e., only one PluginBinding)
     * @param start
     *            one of the plugin's start points or out-path of pointcut stub
     * @param entry
     *            the stub's entry connection
     */
    public AddInBindingCommand(Stub stub, Object start) {
        super();
        this.plugin = null;
        this.stub = stub;
        assert (start instanceof StartPoint || start instanceof NodeConnection) : "Start parameter must be StartPoint or NodeConnection"; //$NON-NLS-1$
        if (start instanceof StartPoint) {
            this.start = (StartPoint) start;
            this.start2 = null;
        } else {
            this.start = null;
            this.start2 = (NodeConnection) start;
        }
        // don't set entry because it's delayed; index is 0 because there is only one in-path for the aspect stub
        this.delayedIndex = 0;
        this.delayedExecution = true;
        setLabel(Messages.getString("AddInBinding.addnBinding")); //$NON-NLS-1$
    }
    
    /**
     * @param plugin
     *            the concerned plugin binding
     * @param start
     *            one of the plugin's start points
     * @param index
     *            the stub's entry connection index. will be checked at execution time.
     */
    public AddInBindingCommand(PluginBinding plugin, StartPoint start, int index) {
        super();
        this.plugin = plugin;
        this.stub = null;
        this.start = start;
        this.start2 = null;
        // this.entry = entry;
        this.delayedIndex = index;
        this.delayedExecution = true;
        setLabel(Messages.getString("AddInBinding.addnBinding")); //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if ((plugin != null && start != null) || (stub != null && (start != null || start2 != null)) && (entry != null || delayedExecution))
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

    	in = (InBinding) ModelCreationFactory.getNewObject(urnSpec, InBinding.class);

        if (delayedExecution) {
            if (plugin != null && plugin.getStub().getPred().size() > delayedIndex) {
            	entry = (NodeConnection) plugin.getStub().getPred().get(delayedIndex);
            } else if (stub != null && stub.getPred().size() > delayedIndex) {
               	entry = (NodeConnection) stub.getPred().get(delayedIndex);
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
            plugin.getIn().add(in);        	
        } else {
        	((PluginBinding) stub.getBindings().get(0)).getIn().add(in);
        }
        in.setStartPoint(start);
        in.setPointcutExit(start2);
        in.setStubEntry(entry);

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
            plugin.getIn().remove(in);        	
        } else {
        	((PluginBinding) stub.getBindings().get(0)).getIn().remove(in);
        }
        in.setStartPoint(null);
        in.setPointcutExit(null);
        in.setStubEntry(null);

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
        assert (start != null || start2 != null) : "Pre start null"; //$NON-NLS-1$
        assert entry != null : "Pre entry connection null"; //$NON-NLS-1$
        if (plugin != null) {
            assert !plugin.getIn().contains(in) : "Pre plugin contains the in binding"; //$NON-NLS-1$        	
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
        assert (start != null || start2 !=null) : "Post start null"; //$NON-NLS-1$
        assert entry != null : "Post entry connection null"; //$NON-NLS-1$
        if (plugin != null) {
            assert plugin.getIn().contains(in) : "Post plugin doesn't contains the in binding"; //$NON-NLS-1$        	
        }
    }
}