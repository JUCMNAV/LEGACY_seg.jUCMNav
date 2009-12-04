package seg.jUCMNav.model.commands.create;

import grl.Belief;
import grl.GRLGraph;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * 
 * Adds a belief to a {@link GRLGraph}
 * 
 * @author Jean-François Roy
 * 
 */
public class AddBeliefCommand extends Command implements JUCMNavCommand {

    private Belief belief;

    // Graph where the element has been added.
    private GRLGraph graph;

    /**
     * @param graph
     *            graph where to add the belief
     * @param belief
     *            belief to add
     */
    public AddBeliefCommand(GRLGraph graph, Belief belief) {
        super();
        this.graph = graph;
        this.belief = belief;
        setLabel(Messages.getString("AddBeliefCommand.createBelief")); //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        graph.getNodes().add(belief);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert belief != null : "pre belief"; //$NON-NLS-1$
        assert graph != null : "pre graph"; //$NON-NLS-1$

        assert !graph.getNodes().contains(belief) : "pre belief in graph"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert belief != null : "post belief"; //$NON-NLS-1$
        assert graph != null : "post graph"; //$NON-NLS-1$

        assert graph.getNodes().contains(belief) : "post belief in graph"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        graph.getNodes().remove(belief);

        testPreConditions();
    }
}
