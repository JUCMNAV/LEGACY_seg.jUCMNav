package seg.jUCMNav.model.commands.delete.internal;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import ucm.map.ComponentBinding;
import ucm.map.InBinding;
import ucm.map.OutBinding;
import ucm.map.PluginBinding;
import ucm.map.ResponsibilityBinding;
import ucm.map.UCMmap;
import urn.URNspec;

/**
 * This command delete a PluginBinding from a Stub.
 * 
 * @author Etienne Tremblay
 */
public class DeletePluginCommand extends CompoundCommand {

    // The PluginBinding to delete.
    private PluginBinding oldPlugin;

    // The map of the PluginBinding
    private UCMmap oldMap;

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
        oldMap = oldPlugin.getPlugin();
        if (urnSpec == null)
            urnSpec = oldMap.getUrndefinition().getUrnspec();

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
        
        for (Iterator i = oldPlugin.getComponents().iterator(); i.hasNext();) {
            ComponentBinding out = (ComponentBinding) i.next();
            DeleteComponentBindingCommand cmd = new DeleteComponentBindingCommand(out);
            add(cmd);
        }
        
        for (Iterator i = oldPlugin.getResponsibilities().iterator(); i.hasNext();) {
            ResponsibilityBinding out = (ResponsibilityBinding) i.next();
            DeleteRespBindingCommand cmd = new DeleteRespBindingCommand(out);
            add(cmd);
        }

        add(new RemoveBindingCommand(oldPlugin, urnSpec));
    }

}