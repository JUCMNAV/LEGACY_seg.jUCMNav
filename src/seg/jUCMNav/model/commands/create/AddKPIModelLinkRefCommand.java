/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.IntentionalElementRef;
import grl.kpimodel.KPIInformationElementRef;
import grl.kpimodel.KPIModelLink;
import grl.kpimodel.KPIModelLinkRef;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urncore.IURNDiagram;

/**
 * Command to create KPIModelLinkRef in a GRLDiagram
 * 
 * @author pchen
 * 
 */
public class AddKPIModelLinkRefCommand extends Command implements JUCMNavCommand {

    IURNDiagram graph;

    KPIInformationElementRef source;
    IntentionalElementRef destination;

    KPIModelLink link;
    KPIModelLinkRef kpiModelLinkRef;

    /**
     * 
     */
    public AddKPIModelLinkRefCommand(IURNDiagram graph, KPIInformationElementRef source, IntentionalElementRef destination, KPIModelLink link) {
        this.graph = graph;
        this.source = source;
        this.destination = destination;
        this.link = link;

        setLabel(Messages.getString("AddKPIModelLinkRefCommand.addKPIModelLinkRef")); //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return (source != null && destination != null);
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        kpiModelLinkRef = (KPIModelLinkRef) ModelCreationFactory.getNewObject(graph.getUrndefinition().getUrnspec(), KPIModelLinkRef.class);
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        kpiModelLinkRef.setLink(link);
        kpiModelLinkRef.setDiagram(graph);

        kpiModelLinkRef.setSource(source);
        kpiModelLinkRef.setTarget(destination);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert link != null : "pre KPIModelLink"; //$NON-NLS-1$
        assert kpiModelLinkRef != null : "pre KPIModellinkref"; //$NON-NLS-1$
        assert graph != null : "pre Graph"; //$NON-NLS-1$
        assert source != null : "pre source"; //$NON-NLS-1$
        assert destination != null : "pre destination"; //$NON-NLS-1$

        assert kpiModelLinkRef.getSource() != source : "pre KPIModellinkref source"; //$NON-NLS-1$
        assert kpiModelLinkRef.getTarget() != destination : "pre KPIModellinkref destination"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert link != null : "post KPIModelLink"; //$NON-NLS-1$
        assert kpiModelLinkRef != null : "post KPIModellinkref"; //$NON-NLS-1$
        assert graph != null : "post Graph"; //$NON-NLS-1$
        assert source != null : "post source"; //$NON-NLS-1$
        assert destination != null : "post destination"; //$NON-NLS-1$

        assert kpiModelLinkRef.getSource() == source : "post KPIModellinkref source"; //$NON-NLS-1$
        assert kpiModelLinkRef.getTarget() == destination : "post KPIModellinkref destination"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        kpiModelLinkRef.setSource(null);
        kpiModelLinkRef.setTarget(null);
        kpiModelLinkRef.setLink(null);
        kpiModelLinkRef.setDiagram(null);

        testPreConditions();
    }
}
