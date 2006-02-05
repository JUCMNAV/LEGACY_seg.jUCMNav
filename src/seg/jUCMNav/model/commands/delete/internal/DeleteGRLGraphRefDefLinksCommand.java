/**
 * 
 */
package seg.jUCMNav.model.commands.delete.internal;

import grl.Actor;
import grl.ActorRef;
import grl.ElementLink;
import grl.GRLGraph;
import grl.GRLNode;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.LinkRef;

import java.util.Hashtable;
import java.util.Iterator;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urncore.IURNConnection;

/**
 * This class unlink reference and definition of IntentionalElements, Actors and Links. 
 * It also delete the GRLGraph
 *  
 * @author Jean-François Roy
 *
 */
public class DeleteGRLGraphRefDefLinksCommand extends Command implements JUCMNavCommand {
    
    // its references to definitions.
    private Hashtable htReferences;

    // the graph to delete
    private GRLGraph graph;

    // the URNspec in which it is contained
    private URNspec urn;
    
    private int position;
   
    /**
     * @param diagram
     *            the graph to delete
     */
    public DeleteGRLGraphRefDefLinksCommand(GRLGraph diagram) {
        setGraph(diagram);
        setLabel("DeleteGRLGraphRefDefLinksCommand");
    }

    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return getGraph() != null;
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        urn = getGraph().getUrndefinition().getUrnspec();
        htReferences = new Hashtable();
        //Set the references of the Actors
        for (Iterator iter = graph.getContRefs().iterator(); iter.hasNext();) {
            ActorRef actor = (ActorRef) iter.next();
            htReferences.put(actor, actor.getContDef());
        }

        //Set the references of the IntentionalElements
        for (Iterator iter = graph.getNodes().iterator(); iter.hasNext();) {
            GRLNode node = (GRLNode) iter.next();
            if (node instanceof IntentionalElementRef)
                htReferences.put(node, ((IntentionalElementRef) node).getDef());
        }
        //Set the references of the Links
        for (Iterator iter = graph.getConnections().iterator(); iter.hasNext();) {
            IURNConnection link = (IURNConnection) iter.next();
            if (link instanceof LinkRef){
                htReferences.put((LinkRef)link, ((LinkRef) link).getLink());
            }
        }
        
        position = getGraph().getUrndefinition().getSpecDiagrams().indexOf(getGraph());
        redo();
    }
    
    public GRLGraph getGraph() {
        return graph;
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        
        // remove graph
        urn.getUrndef().getSpecDiagrams().remove(getGraph());

        // break relations
        for (Iterator iter = graph.getContRefs().iterator(); iter.hasNext();) {
            ActorRef actor = (ActorRef) iter.next();
            actor.setContDef(null);
        }

        for (Iterator iter = graph.getNodes().iterator(); iter.hasNext();) {
            GRLNode node = (GRLNode) iter.next();
            if (node instanceof IntentionalElementRef)
                ((IntentionalElementRef) node).setDef(null);
        }

        for (Iterator iter = graph.getConnections().iterator(); iter.hasNext();) {
            IURNConnection link = (IURNConnection) iter.next();
            if (link instanceof LinkRef){
                ((LinkRef)link).setLink(null);
            }
        }        
        testPostConditions();
    }
    
    public void setGraph(GRLGraph graph) {
        this.graph = graph;
    }
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        // lists could be empty but not null
        assert getGraph() != null && urn != null : "pre something is null"; //$NON-NLS-1$
        assert urn.getUrndef().getSpecDiagrams().contains(getGraph()) : "pre graph still in model"; //$NON-NLS-1$

        // verify references
        for (Iterator iter = graph.getContRefs().iterator(); iter.hasNext();) {
            ActorRef actor = (ActorRef) iter.next();
            assert actor.getContDef() != null : "pre actor still references definition"; //$NON-NLS-1$
        }

        for (Iterator iter = graph.getNodes().iterator(); iter.hasNext();) {
            GRLNode node = (GRLNode) iter.next();
            if (node instanceof IntentionalElementRef)
                assert ((IntentionalElementRef) node).getDef() != null : "pre node still references definition"; //$NON-NLS-1$
        }
        
        for (Iterator iter = graph.getConnections().iterator(); iter.hasNext();) {
            IURNConnection link = (IURNConnection) iter.next();
            if (link instanceof LinkRef){
                assert ((LinkRef)link).getLink() != null : "pre link still references definition"; //$NON-NLS-1$
            }
        }
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        // lists could be empty but not null
        assert getGraph() != null && urn != null : "post something is null"; //$NON-NLS-1$
        assert !urn.getUrndef().getSpecDiagrams().contains(getGraph()) : "post graph still in model"; //$NON-NLS-1$

        // verify no more references
        for (Iterator iter = graph.getContRefs().iterator(); iter.hasNext();) {
            ActorRef actor = (ActorRef) iter.next();
            assert actor.getContDef() == null : "post actor still references definition"; //$NON-NLS-1$
        }

        for (Iterator iter = graph.getNodes().iterator(); iter.hasNext();) {
            GRLNode node = (GRLNode) iter.next();
            if (node instanceof IntentionalElementRef)
                assert ((IntentionalElementRef) node).getDef() == null : "post node still references definition"; //$NON-NLS-1$
        }
        
        for (Iterator iter = graph.getConnections().iterator(); iter.hasNext();) {
            IURNConnection link = (IURNConnection) iter.next();
            if (link instanceof LinkRef){
                assert ((LinkRef)link).getLink() == null : "post link still references definition"; //$NON-NLS-1$
            }
        }
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        super.undo();

        // re-add map
        urn.getUrndef().getSpecDiagrams().add(position, getGraph());

        // re-add references
        for (Iterator iter = graph.getContRefs().iterator(); iter.hasNext();) {
            ActorRef actor = (ActorRef) iter.next();
            actor.setContDef((Actor) htReferences.get(actor));
        }

        for (Iterator iter = graph.getNodes().iterator(); iter.hasNext();) {
            GRLNode node = (GRLNode) iter.next();
            if (node instanceof IntentionalElementRef)
                ((IntentionalElementRef) node).setDef((IntentionalElement) htReferences.get(node));
        }
        for (Iterator iter = graph.getConnections().iterator(); iter.hasNext();) {
            IURNConnection link = (IURNConnection) iter.next();
            if (link instanceof LinkRef){
                ((LinkRef)link).setLink((ElementLink) htReferences.get(link));
            }
        }  
        testPreConditions();
    }
}
