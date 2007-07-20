/**
 * 
 */
package seg.jUCMNav.model.commands.delete.internal;

import grl.kpimodel.KPIModelLink;
import grl.kpimodel.KPIModelLinkRef;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urncore.IURNDiagram;
import urncore.IURNNode;

/**
 * Delete a LinkRef in a GRLGraph
 * 
 * @author pchen
 * 
 */
public class RemoveKPIModelLinkRefCommand extends Command implements JUCMNavCommand {

    KPIModelLinkRef linkref;
    KPIModelLink link;
    IURNDiagram graph;
    IURNNode source, target;

    /**
     * 
     */
    public RemoveKPIModelLinkRefCommand(KPIModelLinkRef linkref) {
        this.linkref = linkref;
        setLabel(Messages.getString("RemoveKPIModelLinkRefCommand.removeKPIModelLinkRef")); // //$NON-NLS-1$
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
    public void setKPIModelLink(KPIModelLink link) {
        this.link = link;
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
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
        assert linkref != null : "Pre KPIModelLinkRef is null"; //$NON-NLS-1$
        assert link != null : "Pre KPIModelLink is null"; //$NON-NLS-1$
        assert graph != null : "Pre graph is null"; //$NON-NLS-1$
        assert source != null && target != null : "Pre source-target is null"; //$NON-NLS-1$

        assert link.getRefs().contains(linkref) : "Pre KPIModelLinkRef-link"; //$NON-NLS-1$
        assert graph.getConnections().contains(linkref) : "Pre graph-KPIModelLinkRef"; //$NON-NLS-1$
        assert source.getSucc().contains(linkref) && target.getPred().contains(linkref) : "Pre KPIInformationElementRef"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert linkref != null && link != null : "Post KPIModelLink is null"; //$NON-NLS-1$
        assert graph != null : "Post graph is null"; //$NON-NLS-1$
        assert source != null && target != null : "Post source-target is null"; //$NON-NLS-1$

        assert !link.getRefs().contains(linkref) : "Post KPIModelLinkRef-link"; //$NON-NLS-1$
        assert !graph.getConnections().contains(linkref) : "Post graph-KPIModelLinkRef"; //$NON-NLS-1$
        assert !source.getSucc().contains(linkref) && !target.getPred().contains(linkref) : "Post KPIInformationElementRef"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        graph.getConnections().add(linkref);
        link.getRefs().add(linkref);
        linkref.setSource(source);
        linkref.setTarget(target);
        testPreConditions();
    }
}
