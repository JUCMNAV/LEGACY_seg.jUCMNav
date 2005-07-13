package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.CompoundCommand;

import ucm.map.Map;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.Stub;
import urn.URNspec;

/**
 * CompoundCommand to delete a Map. (Remove it from the model).
 * 
 * Will delete any PluginBindings then remove the map itself by using DeleteRefDefLinksCommand, which is more efficient than deleting all contained elements.
 * 
 * @author jkealey
 *  
 */
public class DeleteMapCommand extends CompoundCommand {

    private Map map;

    /**
     * @param map
     *            the map to delete
     */
    public DeleteMapCommand(Map map) {
        setLabel("DeleteMapCommand");//$NON-NLS-1$

        this.map = map;
        URNspec urn = map.getUcmspec().getUrnspec();

        for (Iterator iter = map.getPathGraph().getPathNodes().iterator(); iter.hasNext();) {
            PathNode node = (PathNode) iter.next();
            if (node instanceof Stub) {
                Stub stub = (Stub) node;
                List plugins = stub.getBindings();
                // remove any plugins related to contained stubs.
                for (Iterator i = plugins.iterator(); i.hasNext();) {
                    PluginBinding plugin = (PluginBinding) i.next();
                    DeletePluginCommand cmd = new DeletePluginCommand(plugin, urn);
                    add(cmd);
                }
            }
        }

        // remove bindings that include this map.
        List plugins = map.getParentStub();
        for (Iterator i = plugins.iterator(); i.hasNext();) {
            PluginBinding plugin = (PluginBinding) i.next();
            DeletePluginCommand cmd = new DeletePluginCommand(plugin, urn);
            add(cmd);
        }

        // remove the map itself.
        add(new DeleteMapRefDefLinksCommand(map));
    }

    /**
     * 
     * @return the map being deleted.
     */
    public Map getMap() {
        return map;
    }
}