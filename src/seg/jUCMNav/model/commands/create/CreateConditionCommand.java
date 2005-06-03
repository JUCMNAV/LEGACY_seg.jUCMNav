/*
 * Created on Jun 1, 2005
 */
package seg.jUCMNav.model.commands.create;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.NodeConnection;
import urncore.Condition;

/**
 * @author Jordan
 */
public class CreateConditionCommand extends Command implements JUCMNavCommand {

	private Condition condition;
    private NodeConnection connection;
    private int deltaX;
    private int deltaY;
    
    public CreateConditionCommand(NodeConnection connection) {
    	this.connection = connection;
    }

    public boolean canExecute() {
    	if(connection == null) {
    		System.out.println("hello");
    	}
        return connection != null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        condition = (Condition) ModelCreationFactory.getNewObject(connection.getPathGraph().getMap().getUcmspec().getUrnspec(), Condition.class);

        condition.setDeltaX(deltaX);
        condition.setDeltaY(deltaY);

        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        
        connection.setCondition(condition);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        connection.setCondition(null);

        testPreConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert condition != null : "pre Condition";
        assert connection != null : "pre Connection";
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
    	assert condition != null : "pre Condition";
        assert connection != null : "pre Connection";
    }

    /**
     * @param deltaX
     *            The deltaX to set.
     */
    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    /**
     * @param deltaY
     *            The deltaY to set.
     */
    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }
}
