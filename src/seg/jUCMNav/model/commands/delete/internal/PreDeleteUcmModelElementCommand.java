package seg.jUCMNav.model.commands.delete.internal;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.DisconnectCommand;
import ucm.map.ComponentRef;
import ucm.map.UCMmap;
import ucm.map.NodeConnection;
import ucm.map.PathNode;

/**
 * This command prepares an element for deletion. It gets rid of synch/asynch connections and invisible relationships.
 * 
 * This is a wrapper around CleanRelationshipsCommand which only adds DisconnectCommands to PathNodes but might have more future use.
 * 
 * @author jkealey
 * 
 */
public class PreDeleteUcmModelElementCommand extends CompoundCommand {
    private EObject element;

    /**
     * 
     * @param map
     *            the Map to be deleted.
     */
    public PreDeleteUcmModelElementCommand(UCMmap map) {
        this.element = map;
        add(new CleanRelationshipsCommand(map));
    }

    /**
     * 
     * @param nc
     *            the NodeConnection to be deleted.
     */
    public PreDeleteUcmModelElementCommand(NodeConnection nc) {
        this.element = nc;
        add(new CleanRelationshipsCommand(nc));
    }

    /**
     * 
     * @param pn
     *            the PathNode to be deleted.
     */
    public PreDeleteUcmModelElementCommand(PathNode pn) {
        this.element = pn;
        DisconnectCommand cmd = new DisconnectCommand(pn);
        if (cmd.canExecute())
            add(cmd);
        add(new CleanRelationshipsCommand(pn));

    }

    /**
     * 
     * @param cr
     *            the ComponentRef to be deleted.
     */
    public PreDeleteUcmModelElementCommand(ComponentRef cr) {
        this.element = cr;
        add(new CleanRelationshipsCommand(cr));
    }
}
