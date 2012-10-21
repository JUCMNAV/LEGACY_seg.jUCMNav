/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.GRLGraph;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.IGlobalStackCommand;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urncore.IURNDiagram;

/**
 * This command add a new GrlGraph to the model
 * 
 * @author Jean-François Roy
 * 
 */
public class CreateGrlGraphCommand extends Command implements JUCMNavCommand, IGlobalStackCommand {

    private URNspec urn;
    private GRLGraph graph;
    private int oldCount;
    private int index=-1;

    public CreateGrlGraphCommand(URNspec urn) {
        this.urn = urn;

        // must be created here for getMap() to work properly.
        graph = (GRLGraph) ModelCreationFactory.getNewObject(urn, GRLGraph.class);
        setLabel(Messages.getString("CreateGrlGraphCommand.createGrlGraph")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return urn != null;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        oldCount = urn.getUrndef().getSpecDiagrams().size();
        redo();
    }

    public GRLGraph getDiagram() {
        return graph;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        if (getIndex() >= 0 && getIndex() <= urn.getUrndef().getSpecDiagrams().size())
            urn.getUrndef().getSpecDiagrams().add(index, graph);
        else
            urn.getUrndef().getSpecDiagrams().add(graph);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert urn != null && urn.getUrndef() != null && graph != null : "post not null"; //$NON-NLS-1$
        assert urn.getUrndef().getSpecDiagrams().contains(graph) : "post graph not in model"; //$NON-NLS-1$
        assert oldCount + 1 == urn.getUrndef().getSpecDiagrams().size() : "post should have only one graph added"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPreConditions() {
        assert urn != null && urn.getUrndef() != null && graph != null : "pre not null"; //$NON-NLS-1$
        assert !urn.getUrndef().getSpecDiagrams().contains(graph) : "pre map not in model"; //$NON-NLS-1$
        assert oldCount == urn.getUrndef().getSpecDiagrams().size() : "pre map count wrong"; //$NON-NLS-1$   

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        urn.getUrndef().getSpecDiagrams().remove(graph);
        testPreConditions();
    }

    public IURNDiagram getAffectedDiagram() {
        return getDiagram();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
