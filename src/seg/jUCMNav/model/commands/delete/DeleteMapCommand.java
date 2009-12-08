package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.IGlobalStackCommand;
import seg.jUCMNav.model.commands.delete.internal.CleanRelationshipsCommand;
import seg.jUCMNav.model.commands.delete.internal.DeleteMapRefDefLinksCommand;
import ucm.map.UCMmap;
import urncore.IURNDiagram;

/**
 * CompoundCommand to delete a Map. (Remove it from the model).
 * 
 * Will delete any PluginBindings then remove the map itself by using DeleteRefDefLinksCommand, which is more efficient than deleting all contained elements.
 * 
 * @author jkealey
 * 
 */
public class DeleteMapCommand extends CompoundCommand implements IGlobalStackCommand {

    private IURNDiagram diagram;

    /**
     * @param map
     *            the map to delete
     */
    public DeleteMapCommand(UCMmap map) {
        setLabel("DeleteMapCommand");//$NON-NLS-1$

        setDiagram(map);
        add(new CleanRelationshipsCommand(map));
        // remove the map itself.
        add(new DeleteMapRefDefLinksCommand(map));
    }

    public IURNDiagram getDiagram() {
        return diagram;
    }

    public void setDiagram(IURNDiagram diagram) {
        this.diagram = diagram;
    }

    public IURNDiagram getAffectedDiagram() {
        return getDiagram();
    }

}