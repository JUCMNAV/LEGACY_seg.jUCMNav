/**
 * 
 */
package seg.jUCMNav.model.commands.delete.internal;

import grl.ElementLink;
import grl.LinkRef;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import urncore.IURNDiagram;
import urncore.IURNNode;

/**
 * Delete a LinkRef in a GRLGraph
 * 
 * @author Jean-François Roy
 * 
 */
public class RemoveLinkRefCommand extends Command implements JUCMNavCommand {

    LinkRef linkref;
    ElementLink link;
    IURNDiagram graph;
    IURNNode source, target;

    boolean aborted=false;
    /**
     * 
     */
    public RemoveLinkRefCommand(LinkRef linkref) {
        this.linkref = linkref;
        setLabel("RemoveLinkRefCommand"); ////$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        this.link = linkref.getLink();
        this.graph = linkref.getDiagram();
        this.source = linkref.getSource();
        this.target = linkref.getTarget();
        
        redo();
    }

    /**
     * @param link
     */
    public void setElementLink(ElementLink link) {
        this.link = link;
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (link==null || graph==null || source==null || target==null) {
            aborted=true;
            return;
        }

        testPreConditions();
        graph.getConnections().remove(linkref);
        link.getRefs().remove(linkref);
        linkref.setSource(null);
        linkref.setTarget(null);
        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert linkref != null : "Pre linkref is null"; //$NON-NLS-1$
        assert link != null : "Pre link is null"; //$NON-NLS-1$
        assert graph != null : "Pre graph is null"; //$NON-NLS-1$
        assert source != null && target != null : "Pre source-target is null"; //$NON-NLS-1$

        assert link.getRefs().contains(linkref) : "Pre linkref-link"; //$NON-NLS-1$
        assert graph.getConnections().contains(linkref) : "Pre graph-linkref"; //$NON-NLS-1$
        assert source.getSucc().contains(linkref) && target.getPred().contains(linkref) : "Pre IntentionalElementRef"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert linkref != null && link != null : "Post link is null"; //$NON-NLS-1$
        assert graph != null : "Post graph is null"; //$NON-NLS-1$
        assert source != null && target != null : "Post source-target is null"; //$NON-NLS-1$

        assert !link.getRefs().contains(linkref) : "Post linkref-link"; //$NON-NLS-1$
        assert !graph.getConnections().contains(linkref) : "Post graph-linkref"; //$NON-NLS-1$
        assert !source.getSucc().contains(linkref) && !target.getPred().contains(linkref) : "Post IntentionalElementRef"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (link==null || graph==null || source==null || target==null) {
            aborted=true;
            return;
        }
        
        testPostConditions();
        link.getRefs().add(linkref);
        linkref.setSource(source);
        linkref.setTarget(target);
        graph.getConnections().add(linkref);
        testPreConditions();
    }
}
