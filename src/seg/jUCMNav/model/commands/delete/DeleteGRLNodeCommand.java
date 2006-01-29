/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.GRLNode;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.internal.PreDeleteUrnModelElementCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;

/**
 * Delete a GRLNode from a GRLGraph
 * 
 * @author Jean-François Roy
 *
 */
public class DeleteGRLNodeCommand extends CompoundCommand {

    /**
     * 
     */
    public DeleteGRLNodeCommand(GRLNode ref) {
        setLabel("DeleteGrlNodeCommand"); //$NON-NLS-1$
        add(new PreDeleteUrnModelElementCommand(ref));
        add(new RemoveURNmodelElementCommand(ref));
    }


}
