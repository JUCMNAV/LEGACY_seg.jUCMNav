package seg.jUCMNav.model.commands.helpers;

import java.util.Collection;

import grl.GRLGraph;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

import org.eclipse.emf.common.command.Command;

import seg.jUCMNav.Messages;
import urn.URNspec;

public class AddIntentionalElementRefCommandHelper implements Command {
    private IntentionalElementRef elementRef;

    // Graph where the element has been added.
    private GRLGraph graph;

    private boolean bDefAlreadyExists;
    private IntentionalElement existingDef;

    /**
     * 
     * @param graph
     *            The graph to which to add the IntentionalElementRef
     * @param ir
     *            The IntentioanalElementRef
     */
    public AddIntentionalElementRefCommandHelper(GRLGraph graph, IntentionalElementRef ir) {
        this.graph = graph;
        this.elementRef = ir;
    //    setLabel(Messages.getString("AddIntentionalElementRefCommand.CreateIntentionalElements")); //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        existingDef = elementRef.getDef();
        bDefAlreadyExists = graph.getUrndefinition().getUrnspec().getGrlspec().getIntElements().contains(existingDef);
        
        if ( !bDefAlreadyExists && existingDef != null &&
                existingDef.getGrlspec() != null){
            if( existingDef.getGrlspec().getIntElements().contains(existingDef))
                bDefAlreadyExists = true;
        }
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        URNspec urnspec = graph.getUrndefinition().getUrnspec();
        if (!bDefAlreadyExists)
            urnspec.getGrlspec().getIntElements().add(elementRef.getDef());
        else
            elementRef.setDef(existingDef);
        graph.getNodes().add(elementRef);
        elementRef.setDiagram(graph);
        
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert elementRef != null : "pre elementRef"; //$NON-NLS-1$
        assert bDefAlreadyExists || elementRef.getDef() != null : "pre elementDef"; //$NON-NLS-1$
        assert graph != null : "pre graph"; //$NON-NLS-1$

        assert !graph.getNodes().contains(elementRef) : "pre elementref in graph"; //$NON-NLS-1$
        assert bDefAlreadyExists || !graph.getUrndefinition().getUrnspec().getGrlspec().getIntElements().contains(elementRef.getDef()) : "pre elementDef in model"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
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
        if (!bDefAlreadyExists)
            urnspec.getGrlspec().getIntElements().remove(elementRef.getDef());
        else
            elementRef.setDef(null);

        graph.getNodes().remove(elementRef);

        testPreConditions();
    }

    @Override
    public boolean canExecute() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean canUndo() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Collection<?> getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<?> getAffectedObjects() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Command chain(Command command) {
        // TODO Auto-generated method stub
        return null;
    }
}
