package seg.jUCMNav.model.commands.delete.internal;

import grl.GRLGraph;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;

/**
 * This class delete the GRLGraph from the model (should be empty of actor and element)
 * 
 * @author Jean-François Roy
 * 
 */
public class RemoveGRLGraphCommand extends Command implements JUCMNavCommand {

    private GRLGraph diagram;
    private URNspec urn;
    private int position;

    public RemoveGRLGraphCommand(GRLGraph graph) {
        setLabel("Remove GRLGraph"); //$NON-NLS-1$
        diagram = graph;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        urn = diagram.getUrndefinition().getUrnspec();

        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        position = urn.getUrndef().getSpecDiagrams().indexOf(diagram);
        urn.getUrndef().getSpecDiagrams().remove(diagram);

        testPostConditions();
    }

    public void testPreConditions() {
        assert diagram != null && urn != null : "pre something is null"; //$NON-NLS-1$
        assert urn.getUrndef().getSpecDiagrams().contains(diagram) : "pre diagram in model"; //$NON-NLS-1$
    }

    public void testPostConditions() {
        assert diagram != null && urn != null : "post something is null"; //$NON-NLS-1$
        assert !urn.getUrndef().getSpecDiagrams().contains(diagram) : "post diagram in model"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        urn.getUrndef().getSpecDiagrams().add(position, diagram);

        testPreConditions();
    }
}
