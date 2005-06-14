package seg.jUCMNav.model.commands.transformations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.EndPoint;
import ucm.map.InBinding;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.OutBinding;
import ucm.map.PluginBinding;
import ucm.map.StartPoint;
import ucm.map.Stub;
import urn.URNspec;

/**
 * Created 2005-06-06
 * 
 * @author Etienne Tremblay
 */
public class ReplacePluginCommand extends Command implements JUCMNavCommand {

    private Stub stub;

    private Map map;

    private Map oldMap;

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
     * @param stub
     * @param plugin
     */
    public ReplacePluginCommand(PluginBinding oldPlugin, Map map) {
        super();
        this.oldPlugin = oldPlugin;
        this.map = map;
    }

    public boolean canExecute() {
        if (oldPlugin != null && map != null)
            return true;
        else
            return false;
    }

    public void execute() {
        stub = oldPlugin.getStub();
        oldMap = oldPlugin.getPlugin();
        urnSpec = stub.getPathGraph().getMap().getUcmspec().getUrnspec();

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

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        //		assert oldPlugin != null : "Pre oldPlugin is null";
        //		assert stub.getBindings().contains(oldPlugin) : "Pre oldPlugin not contained in stub bindings";
        //		assert oldMap.getParentStub().contains(oldPlugin) : "Pre oldPlugin not contained in the map parent stubs";
        //		assert !stub.getBindings().contains(newPlugin) : "Pre newPlugin contained in the stub bindings";
        //		assert !oldMap.getParentStub().contains(newPlugin) : "Pre newPlugin contained in the map parent stubs";
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        //		assert oldPlugin != null : "Post oldPlugin is null";
        //		assert !stub.getBindings().contains(oldPlugin) : "Post oldPlugin contained in stub bindings";
        //		assert !oldMap.getParentStub().contains(oldPlugin) : "Post oldPlugin contained in the map parent stubs";
        //		assert stub.getBindings().contains(newPlugin) : "Post newPlugin not contained in the stub bindings";
        //		assert oldMap.getParentStub().contains(newPlugin) : "Post newPlugin noy contained in the map parent stubs";
    }

}
