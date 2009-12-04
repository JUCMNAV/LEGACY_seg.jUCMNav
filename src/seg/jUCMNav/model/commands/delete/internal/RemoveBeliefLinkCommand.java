/**
 * 
 */
package seg.jUCMNav.model.commands.delete.internal;

import grl.BeliefLink;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import urncore.IURNDiagram;
import urncore.IURNNode;

/**
 * Delete a BeliefLink from a GRLGraph
 * 
 * @author Jean-François Roy
 * 
 */
public class RemoveBeliefLinkCommand extends Command implements JUCMNavCommand {

    BeliefLink link;
    IURNDiagram graph;
    IURNNode source, target;

    /**
     * 
     */
    public RemoveBeliefLinkCommand(BeliefLink link) {
        this.link = link;
        setLabel("RemoveBeliefLinkCommand"); ////$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        this.graph = link.getDiagram();
        this.source = link.getSource();
        this.target = link.getTarget();
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        graph.getConnections().remove(link);
        link.setSource(null);
        link.setTarget(null);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert link != null : "Pre link is null"; //$NON-NLS-1$
        assert graph != null : "Pre graph is null"; //$NON-NLS-1$
        assert source != null && target != null : "Pre source-target is null"; //$NON-NLS-1$

        assert graph.getConnections().contains(link) : "Pre graph-linkref"; //$NON-NLS-1$
        assert source.getSucc().contains(link) && target.getPred().contains(link) : "Pre link"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert link != null : "post link is null"; //$NON-NLS-1$
        assert graph != null : "post graph is null"; //$NON-NLS-1$
        assert source != null && target != null : "post source-target is null"; //$NON-NLS-1$

        assert !graph.getConnections().contains(link) : "post graph-linkref"; //$NON-NLS-1$
        assert !source.getSucc().contains(link) && !target.getPred().contains(link) : "post link"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        graph.getConnections().add(link);
        link.setSource(source);
        link.setTarget(target);
        testPreConditions();
    }
}
