package seg.jUCMNav.model.commands.transformations;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urncore.Condition;
import urncore.Responsibility;

/**
 * Changes the pseudo-code associated with a Responsibility or a Condition. 
 * 
 * @author jkealey
 */
public class ChangeCodeCommand extends Command implements JUCMNavCommand {
    private EObject elem;
    private String code, oldCode;

    public ChangeCodeCommand(EObject obj, String newcode) {
    	this.elem = obj;
    	this.code = newcode;
    	
        if (elem instanceof Responsibility )
            setLabel(Messages.getString("ChangeCodeCommand.ChangeResponsibilityCode")); //$NON-NLS-1$
        else
        	setLabel(Messages.getString("ChangeCodeCommand.ChangeCondition")); //$NON-NLS-1$
    }


    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (elem instanceof Responsibility) {
            oldCode = ((Responsibility)elem).getDescription();
        } else if (elem instanceof Condition) {
        	oldCode = ((Condition) elem).getExpression();
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

        if (elem instanceof Responsibility) {
            ((Responsibility)elem).setDescription(code);
        } else if (elem instanceof Condition) {
        	((Condition) elem).setExpression(code);
        }

        testPostConditions();
    }


    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert elem != null : "post no elemement to name!"; //$NON-NLS-1$
    }

	/*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert elem != null : "pre no elemement to name!"; //$NON-NLS-1$

    }

	/**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        if (elem instanceof Responsibility) {
            ((Responsibility)elem).setDescription(oldCode);
        } else if (elem instanceof Condition) {
        	((Condition) elem).setExpression(oldCode);
        }
        
        testPreConditions();
    }

}