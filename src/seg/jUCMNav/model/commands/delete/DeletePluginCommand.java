package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import ucm.map.InBinding;
import ucm.map.Map;
import ucm.map.OutBinding;
import ucm.map.PluginBinding;
import ucm.map.Stub;
import urn.URNspec;

/**
 * This command delete a PluginBinding from a Stub.
 * 
 * @author Etienne Tremblay
 */
public class DeletePluginCommand extends CompoundCommand {

    // The PluginBinding to delete.
    private PluginBinding oldPlugin;

    // The stub owner of the PluginBinding to delete.
    private Stub stub;

    // The map of the PluginBinding
    private Map oldMap;

    // The URNspec of the file
    private URNspec urnSpec;

    /**
     * This command delete a PluginBinding from a Stub. Infers URNspec from stub.
     * 
     * @param oldPlugin
     *            The PluginBinding to delete from the Stub.
     */
    public DeletePluginCommand(PluginBinding oldPlugin) {
        super();
        this.oldPlugin = oldPlugin;
        build();
    }

    /**
     * This command delete a PluginBinding from a Stub.
     * 
     * @param oldPlugin
     *            The PluginBinding to delete from the Stub.
     * 
     * @param urnSpec
     *            the URNspec containing the plugin
     */
    public DeletePluginCommand(PluginBinding oldPlugin, URNspec urnSpec) {
        super();
        this.oldPlugin = oldPlugin;
        this.urnSpec = urnSpec;
        build();
    }

    /**
     * Builds the CompoundCommand
     *  
     */
    private void build() {
        stub = oldPlugin.getStub();
        oldMap = oldPlugin.getPlugin();
        if (urnSpec == null)
            urnSpec = oldMap.getUcmspec().getUrnspec();

        for (Iterator i = oldPlugin.getIn().iterator(); i.hasNext();) {
            InBinding in = (InBinding) i.next();
            DeleteInBindingCommand cmd = new DeleteInBindingCommand(in);
            add(cmd);
        }

        for (Iterator i = oldPlugin.getOut().iterator(); i.hasNext();) {
            OutBinding out = (OutBinding) i.next();
            DeleteOutBindingCommand cmd = new DeleteOutBindingCommand(out);
            add(cmd);
        }

        add(new RemoveBindingCommand(oldPlugin, urnSpec));
    }

}