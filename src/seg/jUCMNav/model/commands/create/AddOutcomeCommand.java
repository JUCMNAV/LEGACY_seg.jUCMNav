package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import asd.ASDiagram;
import asd.Outcome;
import grl.GRLGraph;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
/**
 * 
 * Adds a belief to a {@link GRLGraph}
 * 
 * @author Jean-François Roy
 * 
 */
public class AddOutcomeCommand extends Command implements JUCMNavCommand {

    private Outcome comm1;

    // Graph where the element has been added.
    private URNspec spec;
    
    private ASDiagram asDiagram;

    /**
     * @param asDiagram 
     * @param graph
     *            graph where to add the belief
     * @param belief
     *            belief to add
     */
    public AddOutcomeCommand(URNspec spec, Outcome comm, ASDiagram asDiagram) {
        super();
        this.spec = spec;
        this.comm1 = comm;
        this.asDiagram = asDiagram;
        setLabel(Messages.getString("AddOutcomeCommand.createOutcome")); //$NON-NLS-1$
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

     //   graph.getNodes().add(outcome);
   
        spec.getAsdspec().getOutcomes().add(comm1);
        comm1.getDiagrams().add(asDiagram);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert comm1 != null : "pre belief"; //$NON-NLS-1$
        assert spec != null : "pre spec"; //$NON-NLS-1$

       assert !spec.getAsdspec().getOutcomes().contains(comm1) : "pre comm in spec"; //$NON-NLS-1$
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert comm1 != null : "post outcome"; //$NON-NLS-1$
        assert spec != null : "post spec"; //$NON-NLS-1$

        assert spec.getAsdspec().getOutcomes().contains(comm1) : "pre outcome in spec"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        spec.getAsdspec().getOutcomes().remove(comm1);
        comm1.getDiagrams().remove(asDiagram);
        

        testPreConditions();
    }
}
