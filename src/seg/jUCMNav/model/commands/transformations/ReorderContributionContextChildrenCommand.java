package seg.jUCMNav.model.commands.transformations;

import grl.ContributionContext;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * Changes the order of included contexts.
 * 
 * @author jkealey
 */
public class ReorderContributionContextChildrenCommand extends Command implements JUCMNavCommand {
    private EObject child;
    private ContributionContext parent;
    private boolean isMoveUp;
    private EList list;

    public ReorderContributionContextChildrenCommand(ContributionContext parent, EObject child, boolean isMoveUp) {
        this.parent = parent;
        this.child = child;
        this.isMoveUp = isMoveUp;

        if (isMoveUp)
            setLabel(Messages.getString("ReorderScenarioChildrenCommand.MoveUp")); //$NON-NLS-1$
        else
            setLabel(Messages.getString("ReorderScenarioChildrenCommand.MoveDown")); //$NON-NLS-1$

    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (child instanceof ContributionContext) {
            list = parent.getIncludedContexts();
        }

        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        int index = list.indexOf(child);
        if (isMoveUp)
            index--;
        else
            index++;

        list.remove(child);
        list.add(index, child);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert parent != null && child != null && list != null : "post something null"; //$NON-NLS-1$
        assert list.contains(child) : "post child no longer in list"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert parent != null && child != null && list != null : "pre something null"; //$NON-NLS-1$
        assert list.contains(child) : "pre child no longer in list"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        int index = list.indexOf(child);
        if (isMoveUp)
            index++;
        else
            index--;

        list.remove(child);
        list.add(index, child);

        testPreConditions();
    }

}