/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.Contribution;
import grl.ElementLink;
import grl.IntentionalElementRef;
import grl.LinkRef;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urncore.ConnectionLabel;
import urncore.IURNDiagram;

/**
 * Command to create LinkRef in a GRLDiagram
 * 
 * @author Jean-François Roy
 * 
 */
public class AddLinkRefCommand extends Command implements JUCMNavCommand {

    IURNDiagram graph;
    IntentionalElementRef source, destination;
    ElementLink link;

    LinkRef linkref;
    
    ConnectionLabel labelTarget;

    /**
     * 
     */
    public AddLinkRefCommand(IURNDiagram graph, IntentionalElementRef source, IntentionalElementRef destination, ElementLink link) {
        this.graph = graph;
        this.source = source;
        this.destination = destination;
        this.link = link;

        setLabel(Messages.getString("AddLinkRefCommand.addLinkRef")); //$NON-NLS-1$
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
        linkref = (LinkRef) ModelCreationFactory.getNewObject(graph.getUrndefinition().getUrnspec(), LinkRef.class);
        if(link instanceof Contribution) {
            labelTarget = (ConnectionLabel)ModelCreationFactory.getNewObject(graph.getUrndefinition().getUrnspec(), ConnectionLabel.class);
            labelTarget.setDeltaX(30);
            labelTarget.setDeltaY(-30);
        }
        
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        linkref.setLink(link);
        linkref.setDiagram(graph);

        linkref.setSource(source);
        linkref.setTarget(destination);
        
        if(link instanceof Contribution)
            linkref.setLabel(labelTarget);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert link != null : "pre Link"; //$NON-NLS-1$
        assert linkref != null : "pre linkref"; //$NON-NLS-1$
        assert graph != null : "pre Graph"; //$NON-NLS-1$
        assert source != null : "pre source"; //$NON-NLS-1$
        assert destination != null : "pre destination"; //$NON-NLS-1$

        assert linkref.getSource() != source : "pre linkref source"; //$NON-NLS-1$
        assert linkref.getTarget() != destination : "pre linkref destination"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert link != null : "post Link"; //$NON-NLS-1$
        assert linkref != null : "post linkref"; //$NON-NLS-1$
        assert graph != null : "post Graph"; //$NON-NLS-1$
        assert source != null : "post source"; //$NON-NLS-1$
        assert destination != null : "post destination"; //$NON-NLS-1$

        assert linkref.getSource() == source : "post linkref source"; //$NON-NLS-1$
        assert linkref.getTarget() == destination : "post linkref destination"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        linkref.setSource(null);
        linkref.setTarget(null);
        linkref.setLink(null);
        linkref.setDiagram(null);
        
        if(link instanceof Contribution)
            linkref.setLabel(null);

        testPreConditions();
    }
}
