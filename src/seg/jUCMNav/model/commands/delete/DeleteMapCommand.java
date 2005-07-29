package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.internal.CleanRelationshipsCommand;
import seg.jUCMNav.model.commands.delete.internal.DeleteMapRefDefLinksCommand;
import ucm.map.Map;

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
        add(new CleanRelationshipsCommand(map));
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