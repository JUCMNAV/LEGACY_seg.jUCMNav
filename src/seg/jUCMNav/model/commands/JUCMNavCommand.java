package seg.jUCMNav.model.commands;

import org.eclipse.gef.commands.Command;

/**
 * This is the parent class of all of our commands. Basically, it is here to inforce the redefinition of testPreConditions() and testPostConditions()
 * 
 * 
 * @author jkealey
 *  
 */
public abstract class JUCMNavCommand extends Command {

    /**
     * Should be the first instruction in redo() and the last instruction in undo(). Should verify that all needed parameters are present and that the model is
     * is in the appropriate state. Its real value is to verify that after a redo() - undo() sequence has been performed, the model is back in its original
     * state.
     *  
     */
    public abstract void testPreConditions();

    /**
     * Should be the last instruction in redo() and the first instruction in undo(). Should verify that all needed parameters are present and that the model is
     * is in the appropriate state. Its real value is to verify that the redo() command does what it is supposed to. 
     *  
     */
    public abstract void testPostConditions();
}