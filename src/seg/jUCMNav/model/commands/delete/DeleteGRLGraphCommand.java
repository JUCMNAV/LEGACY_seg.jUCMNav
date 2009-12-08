/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.GRLGraph;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.IGlobalStackCommand;
import seg.jUCMNav.model.commands.delete.internal.CleanRelationshipsCommand;
import seg.jUCMNav.model.commands.delete.internal.DeleteGRLGraphRefDefLinksCommand;
import urncore.IURNDiagram;

/**
 * Command to delete a GRLGraph.
 * 
 * @author Jean-François Roy
 * 
 */
public class DeleteGRLGraphCommand extends CompoundCommand implements IGlobalStackCommand {

    private IURNDiagram diagram;

    /**
     * 
     */
    public DeleteGRLGraphCommand(GRLGraph diagram) {
        setLabel(Messages.getString("DeleteGRLGraphCommand.deleteGrlGraph")); //$NON-NLS-1$
        setDiagram(diagram);

        add(new CleanRelationshipsCommand(diagram));
        // Command to delete information about link between GRL and UCM should be add in the CleanRelationshipCommand
        add(new DeleteGRLGraphRefDefLinksCommand(diagram));
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
