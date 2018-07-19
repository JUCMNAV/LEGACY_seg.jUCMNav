package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import asd.ASDiagram;
import asd.Subject;
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
public class AddSubjectCommand extends Command implements JUCMNavCommand {

    private Subject comm1;

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
    public AddSubjectCommand(URNspec spec, Subject comm, ASDiagram asDiagram) {
        super();
        this.spec = spec;
        this.comm1 = comm;
        this.asDiagram = asDiagram;
        setLabel(Messages.getString("AddSubjectCommand.createSubject")); //$NON-NLS-1$
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

     //   graph.getNodes().add(subject);
   
        spec.getAsdspec().getSubjects().add(comm1);
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

       assert !spec.getAsdspec().getSubjects().contains(comm1) : "pre comm in spec"; //$NON-NLS-1$
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert comm1 != null : "post subject"; //$NON-NLS-1$
        assert spec != null : "post spec"; //$NON-NLS-1$

        assert spec.getAsdspec().getSubjects().contains(comm1) : "pre subject in spec"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        spec.getAsdspec().getSubjects().remove(comm1);
        comm1.getDiagrams().remove(asDiagram);
        

        testPreConditions();
    }
}
