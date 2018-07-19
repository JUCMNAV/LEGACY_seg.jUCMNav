package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import asd.ASDiagram;
import asd.Tool;
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
public class AddToolCommand extends Command implements JUCMNavCommand {

    private Tool tool;

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
    public AddToolCommand(URNspec spec, Tool tool, ASDiagram asDiagram) {
        super();
        this.spec = spec;
        this.tool = tool;
        this.asDiagram = asDiagram;
        setLabel(Messages.getString("AddToolCommand.createTool")); //$NON-NLS-1$
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

     //   graph.getNodes().add(tool);
        spec.getAsdspec().getTools().add(tool);
        tool.getDiagrams().add(asDiagram);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert tool != null : "pre belief"; //$NON-NLS-1$
        assert spec != null : "pre spec"; //$NON-NLS-1$

       assert !spec.getAsdspec().getTools().contains(tool) : "pre tool in spec"; //$NON-NLS-1$
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert tool != null : "post tool"; //$NON-NLS-1$
        assert spec != null : "post spec"; //$NON-NLS-1$

        assert spec.getAsdspec().getTools().contains(tool) : "pre tool in spec"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        
        tool.getDiagrams().remove(asDiagram);
        spec.getAsdspec().getTools().remove(tool);

        testPreConditions();
    }
}
