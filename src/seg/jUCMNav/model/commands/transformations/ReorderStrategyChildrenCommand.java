package seg.jUCMNav.model.commands.transformations;

import grl.EvaluationStrategy;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;

/**
 * Changes the order included strategies.
 * 
 * @author jkealey
 */
public class ReorderStrategyChildrenCommand extends Command implements JUCMNavCommand {
    private EObject child;
    private EvaluationStrategy parent;
    private boolean isMoveUp;
    private EList list;

    public ReorderStrategyChildrenCommand(EvaluationStrategy parent, EObject child, boolean isMoveUp) {
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
        if (child instanceof EvaluationStrategy) {
            list = parent.getIncludedStrategies();
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