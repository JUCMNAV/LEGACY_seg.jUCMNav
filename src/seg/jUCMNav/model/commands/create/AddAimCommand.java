package seg.jUCMNav.model.commands.create;

import java.util.ArrayList;

import org.eclipse.gef.commands.Command;

import asd.ASDiagram;
import asd.Aim;
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
public class AddAimCommand extends Command implements JUCMNavCommand {

    private Aim comm1;

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
    public AddAimCommand(URNspec spec, Aim comm, ASDiagram asDiagram) {
        super();
        this.spec = spec;
        this.comm1 = comm;
        this.asDiagram = asDiagram;
        setLabel(Messages.getString("AddAimCommand.createAim")); //$NON-NLS-1$
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

     //   graph.getNodes().add(community);
   
        spec.getAsdspec().getObjects().add(comm1);
        comm1.getDiagrams().add(asDiagram);
        
        ArrayList al = new ArrayList(); 
    	
    	for(Object el1: asDiagram.getElements())
    	{
			if(el1 instanceof Outcome && el1!=null && !asDiagram.getElements().contains(el1)) //always true
    		{
				
				
			
				((Aim) comm1).getOutcomes().add(el1);
    		}
			
    	}

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

       assert !spec.getAsdspec().getObjects().contains(comm1) : "pre comm in spec"; //$NON-NLS-1$
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert comm1 != null : "post aim"; //$NON-NLS-1$
        assert spec != null : "post spec"; //$NON-NLS-1$

        assert spec.getAsdspec().getObjects().contains(comm1) : "pre aim in spec"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        spec.getAsdspec().getObjects().remove(comm1);
        comm1.getDiagrams().remove(asDiagram);
        

    	for(Object el1: asDiagram.getElements())
    	{
			if(el1 instanceof Outcome && el1!=null && asDiagram.getElements().contains(el1)) //always true
    		{
				
				
			
				((Aim) comm1).getOutcomes().remove(el1);
    		}
			
    	}
    	
       testPreConditions();
    }
}
