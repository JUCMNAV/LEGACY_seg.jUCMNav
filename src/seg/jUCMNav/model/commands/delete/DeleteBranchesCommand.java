package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.internal.PostPathManipulationCommand;
import seg.jUCMNav.model.commands.delete.internal.PreDeleteUcmModelElementCommand;
import seg.jUCMNav.model.commands.delete.internal.PrePathManipulationCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveUCMmodelElementCommand;
import seg.jUCMNav.model.util.DoesDisconnectImplyDelete;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.Stub;

/**
 * This command will delete the branches from a PathNode. It not only disconnects them, but also cleans any properties relating to these deleted branches.
 * 
 * @author jkealey
 * 
 */
public class DeleteBranchesCommand extends CompoundCommand {

    public DeleteBranchesCommand(PathNode pn, DoesDisconnectImplyDelete verification, Map editpartregistry) {
        if (verification.shouldDeleteNode() || verification.shouldReplaceWithEmpty()) {
            add(new PreDeleteUcmModelElementCommand(pn));
            add(new PrePathManipulationCommand(pn, verification.getNcInToRemove(), verification.getNcOutToRemove(), editpartregistry, true));
            add(new RemoveUCMmodelElementCommand(pn));
            if (verification.shouldReplaceWithEmpty())
                add(new PostPathManipulationCommand(pn, verification.getInsertionConnection()));
            else
                add(new PostPathManipulationCommand(pn));
        } else {
            boolean rewire = false;
            if (!(pn instanceof Stub) && verification.getNcInBefore().size() == 1 && verification.getNcOutBefore().size() == 1)
                rewire = true;

            for (Iterator iter = verification.getNcInToRemove().iterator(); iter.hasNext();) {
                NodeConnection element = (NodeConnection) iter.next();
                add(new PreDeleteUcmModelElementCommand(element));
            }
            for (Iterator iter = verification.getNcOutToRemove().iterator(); iter.hasNext();) {
                NodeConnection element = (NodeConnection) iter.next();
                add(new PreDeleteUcmModelElementCommand(element));
            }

            add(new PrePathManipulationCommand(pn, verification.getNcInToRemove(), verification.getNcOutToRemove(), editpartregistry, rewire));
        }
    }

}
