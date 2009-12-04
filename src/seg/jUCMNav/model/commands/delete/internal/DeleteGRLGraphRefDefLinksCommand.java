/**
 * 
 */
package seg.jUCMNav.model.commands.delete.internal;

import grl.ActorRef;
import grl.GRLGraph;
import grl.GRLNode;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.DeleteActorRefCommand;
import seg.jUCMNav.model.commands.delete.DeleteGRLNodeCommand;

/**
 * This class unlink reference and definition of IntentionalElements, Actors and Links. It also delete the GRLGraph
 * 
 * @author Jean-François Roy
 * 
 */
public class DeleteGRLGraphRefDefLinksCommand extends CompoundCommand {

    // the graph to delete
    private GRLGraph graph;

    /**
     * @param diagram
     *            the graph to delete
     */
    public DeleteGRLGraphRefDefLinksCommand(GRLGraph diagram) {
        this.graph = diagram;
        setLabel("DeleteGRLGraphRefDefLinksCommand"); //$NON-NLS-1$
    }

    /**
     * Returns true even if no commands exist.
     */
    public boolean canExecute() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canExecute();
    }

    /**
     * Builds command as late as possible.
     */
    public void execute() {
        build();
        super.execute();
    }

    /**
     * Create the command to delete a GRLGraph
     */
    private void build() {
        for (Iterator iter = graph.getContRefs().iterator(); iter.hasNext();) {
            ActorRef actor = (ActorRef) iter.next();
            add(new DeleteActorRefCommand(actor));
        }

        for (Iterator iter = graph.getNodes().iterator(); iter.hasNext();) {
            GRLNode node = (GRLNode) iter.next();
            add(new DeleteGRLNodeCommand(node));
        }

        add(new RemoveGRLGraphCommand(graph));
    }

}
