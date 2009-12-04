package seg.jUCMNav.model.commands.create;

import grl.Belief;
import grl.BeliefLink;
import grl.GRLGraph;
import grl.IntentionalElementRef;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * This command create a link between a belief and an intentionalElementRef or between a belief and an LinkRef
 * 
 * @author Jean-François Roy
 * 
 */
public class AddBeliefLinkCommand extends Command implements JUCMNavCommand {

    GRLGraph graph;
    BeliefLink link;
    Belief src;
    IntentionalElementRef elementDest;

    /**
     * @param src
     * @param link
     */
    public AddBeliefLinkCommand(GRLGraph diagram, Belief src, BeliefLink link) {
        this.graph = diagram;
        this.src = src;
        this.link = link;

        setLabel(Messages.getString("AddBeliefLinkCommand.addBeliefLink")); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (src == null || elementDest == null || src.getSucc().size() > 0) {
            return false;
        }
        return super.canExecute();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        link.setSource(src);
        link.setTarget(elementDest);
        link.setDiagram(graph);

        testPostConditions();
    }

    public void setTarget(IntentionalElementRef target) {
        this.elementDest = target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert graph != null : "pre graph"; //$NON-NLS-1$
        assert link != null : "pre Link"; //$NON-NLS-1$
        assert src != null : "pre src"; //$NON-NLS-1$
        assert elementDest != null : "pre elementDest"; //$NON-NLS-1$

        assert link.getSource() != src : "pre belieflink source"; //$NON-NLS-1$
        assert link.getTarget() != elementDest : "pre belieflink element destination"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert graph != null : "post graph"; //$NON-NLS-1$
        assert link != null : "post Link"; //$NON-NLS-1$
        assert src != null : "post src"; //$NON-NLS-1$
        assert elementDest != null : "post elementDest"; //$NON-NLS-1$

        assert link.getSource() == src : "post belieflink source"; //$NON-NLS-1$
        assert link.getTarget() == elementDest : "post belieflink element destination"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        link.setSource(null);
        link.setTarget(null);
        link.setDiagram(null);

        testPreConditions();
    }
}
