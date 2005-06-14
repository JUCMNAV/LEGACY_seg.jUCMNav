/*
 * Created on Jun 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.NodeConnection;
import urncore.Condition;

/**
 * @author Jordan
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java - Code Style - Code Templates
 */
public class DeleteConditionCommand extends Command implements JUCMNavCommand {
    private Condition condition;

    private NodeConnection connection;

    public DeleteConditionCommand(Condition condition) {
        this.condition = condition;
        connection = condition.getNodeConnection();
    }

    public boolean canExecute() {
        return condition != null && connection != null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        connection.setCondition(null);

        testPostConditions();
    }

    public boolean canUndo() {
        return condition != null && connection.getCondition() == null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        connection.setCondition(condition);

        testPreConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert condition != null : "pre Condition"; //$NON-NLS-1$
        assert connection != null : "pre Connection"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPostConditions() {
        assert condition != null : "pre Condition"; //$NON-NLS-1$
        assert connection != null : "pre Connection"; //$NON-NLS-1$
    }
}
