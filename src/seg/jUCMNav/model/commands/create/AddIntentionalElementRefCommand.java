/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.GRLGraph;
import grl.IntentionalElementRef;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;

/**
 * This command create a IntentionalElementRef and a IntentionalElement object in the model
 * 
 * @author Jean-Fran�ois Roy
 *
 */
public class AddIntentionalElementRefCommand extends Command implements JUCMNavCommand {

    private IntentionalElementRef elementRef;
    
    //Graph where the element has been added.
    private GRLGraph graph;
    
    /**
     * 
     * @param graph
     *            The graph to which to add the IntentionalElementRef
     * @param ir
     *            The IntentioanalElementRef
     */
    public AddIntentionalElementRefCommand(GRLGraph graph, IntentionalElementRef ir) {
        this.graph = graph;
        this.elementRef = ir;
        setLabel("Create Intentional Elements"); 
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

        URNspec urnspec = graph.getUrndefinition().getUrnspec();
        urnspec.getGrlspec().getIntElements().add(elementRef.getDef());
        
        graph.getNodes().add(elementRef);
        
        testPostConditions();
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert elementRef != null : "pre elementRef"; //$NON-NLS-1$
        assert elementRef.getDef() != null : "pre elementDef"; //$NON-NLS-1$
        assert graph != null : "pre graph"; //$NON-NLS-1$

        assert !graph.getNodes().contains(elementRef) : "pre elementref in graph"; //$NON-NLS-1$
        assert !graph.getUrndefinition().getUrnspec().getGrlspec().getIntElements().contains(elementRef.getDef()) : "pre elementDef in model"; //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert elementRef != null : "post elementRef"; //$NON-NLS-1$
        assert elementRef.getDef() != null : "post elementDef"; //$NON-NLS-1$
        assert graph != null : "post graph"; //$NON-NLS-1$

        assert graph.getNodes().contains(elementRef) : "post elementref in graph"; //$NON-NLS-1$
        assert graph.getUrndefinition().getUrnspec().getGrlspec().getIntElements().contains(elementRef.getDef()) : "post elementDef in model"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        URNspec urnspec = graph.getUrndefinition().getUrnspec();
        urnspec.getGrlspec().getIntElements().remove(elementRef.getDef());
        
        graph.getNodes().remove(elementRef);

        testPreConditions();
    }
}