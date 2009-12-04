package seg.jUCMNav.model.commands.transformations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.EndPoint;
import ucm.map.InBinding;
import ucm.map.NodeConnection;
import ucm.map.OutBinding;
import ucm.map.PluginBinding;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.UCMmap;
import urn.URNspec;

/**
 * Command that removes a binding and replaces it with a new one, without any In/Out Bindings.
 * 
 * @author Etienne Tremblay
 */
public class ReplacePluginCommand extends Command implements JUCMNavCommand {

    private Stub stub;
    private UCMmap map;
    private UCMmap oldMap;
    private PluginBinding oldPlugin;
    private PluginBinding newPlugin;
    private ArrayList inBindings = new ArrayList();
    private ArrayList outBindings = new ArrayList();
    private HashMap starts = new HashMap();
    private HashMap ends = new HashMap();
    private HashMap entry = new HashMap();
    private HashMap exit = new HashMap();
    private URNspec urnSpec;

    /**
     * @param oldPlugin
     *            the plugin to replace
     * @param map
     *            the concerned map
     */
    public ReplacePluginCommand(PluginBinding oldPlugin, UCMmap map) {
        super();
        this.oldPlugin = oldPlugin;
        this.map = map;
        setLabel(Messages.getString("ReplacePluginCommand.replacePlugin")); //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (oldPlugin != null && map != null)
            return true;
        else
            return false;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        stub = oldPlugin.getStub();
        oldMap = oldPlugin.getPlugin();
        urnSpec = stub.getDiagram().getUrndefinition().getUrnspec();

        inBindings.addAll(oldPlugin.getIn());
        outBindings.addAll(oldPlugin.getOut());

        for (Iterator i = oldPlugin.getIn().iterator(); i.hasNext();) {
            InBinding in = (InBinding) i.next();
            starts.put(in, in.getStartPoint());
            entry.put(in, in.getStubEntry());
        }

        for (Iterator i = oldPlugin.getOut().iterator(); i.hasNext();) {
            OutBinding out = (OutBinding) i.next();
            ends.put(out, out.getEndPoint());
            exit.put(out, out.getStubExit());
        }

        newPlugin = (PluginBinding) ModelCreationFactory.getNewObject(urnSpec, PluginBinding.class);

        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        for (Iterator i = oldPlugin.getIn().iterator(); i.hasNext();) {
            InBinding in = (InBinding) i.next();
            in.setStartPoint(null);
            in.setStubEntry(null);
        }

        for (Iterator i = oldPlugin.getOut().iterator(); i.hasNext();) {
            OutBinding out = (OutBinding) i.next();
            out.setEndPoint(null);
            out.setStubExit(null);
        }

        oldPlugin.getIn().clear();
        oldPlugin.getOut().clear();

        stub.getBindings().remove(oldPlugin);
        oldMap.getParentStub().remove(oldPlugin);

        map.getParentStub().add(newPlugin);
        stub.getBindings().add(newPlugin);

        testPostConditions();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        stub.getBindings().remove(newPlugin);
        map.getParentStub().remove(newPlugin);

        oldMap.getParentStub().add(oldPlugin);
        stub.getBindings().add(oldPlugin);

        oldPlugin.getIn().addAll(inBindings);
        oldPlugin.getOut().addAll(outBindings);

        for (Iterator i = oldPlugin.getIn().iterator(); i.hasNext();) {
            InBinding in = (InBinding) i.next();
            in.setStartPoint((StartPoint) starts.get(in));
            in.setStubEntry((NodeConnection) entry.get(in));
        }

        for (Iterator i = oldPlugin.getOut().iterator(); i.hasNext();) {
            OutBinding out = (OutBinding) i.next();
            out.setEndPoint((EndPoint) ends.get(out));
            out.setStubExit((NodeConnection) exit.get(out));
        }

        testPreConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        // assert oldPlugin != null : "Pre oldPlugin is null";
        // assert stub.getBindings().contains(oldPlugin) : "Pre oldPlugin not contained in stub bindings";
        // assert oldMap.getParentStub().contains(oldPlugin) : "Pre oldPlugin not contained in the map parent stubs";
        // assert !stub.getBindings().contains(newPlugin) : "Pre newPlugin contained in the stub bindings";
        // assert !oldMap.getParentStub().contains(newPlugin) : "Pre newPlugin contained in the map parent stubs";
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        // assert oldPlugin != null : "Post oldPlugin is null";
        // assert !stub.getBindings().contains(oldPlugin) : "Post oldPlugin contained in stub bindings";
        // assert !oldMap.getParentStub().contains(oldPlugin) : "Post oldPlugin contained in the map parent stubs";
        // assert stub.getBindings().contains(newPlugin) : "Post newPlugin not contained in the stub bindings";
        // assert oldMap.getParentStub().contains(newPlugin) : "Post newPlugin noy contained in the map parent stubs";
    }

}