package seg.jUCMNav.model.commands.transformations;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.FailurePoint;
import ucm.map.NodeConnection;
import ucm.scenario.Initialization;
import urncore.Condition;
import urncore.Responsibility;

/**
 * Changes the pseudo-code associated with a Responsibility or a Condition or the Initialization value of a variable.
 * 
 * @author jkealey
 */
public class ChangeCodeCommand extends Command implements JUCMNavCommand {
    private EObject elem;
    private String code, oldCode;
    private String name, oldName;
    private String description, oldDescription;
    private boolean extendedFeatures;

    public ChangeCodeCommand(EObject obj, String newcode) {
        this.elem = obj;
        this.code = newcode;
        this.extendedFeatures = false;

        setLabel();
    }

    private void setLabel() {
        if (elem instanceof Responsibility)
            setLabel(Messages.getString("ChangeCodeCommand.ChangeResponsibilityCode")); //$NON-NLS-1$
        else if (elem instanceof Condition)
            setLabel(Messages.getString("ChangeCodeCommand.ChangeCondition")); //$NON-NLS-1$
        else if (elem instanceof Initialization)
            setLabel(Messages.getString("ChangeCodeCommand.ChangeInitialization")); //$NON-NLS-1$
        else if (elem instanceof NodeConnection)
            setLabel(Messages.getString("ChangeCodeCommand.ChangeThreshold")); //$NON-NLS-1$
        else if (elem instanceof FailurePoint)
            setLabel(Messages.getString("ChangeCodeCommand.ChangeFailureExpression")); //$NON-NLS-1$
    }

    public ChangeCodeCommand(EObject obj, String newcode, String newlabel, String newDescription) {
        this.elem = obj;
        this.code = newcode;
        this.extendedFeatures = true;
        this.name = newlabel;
        this.description = newDescription;

        setLabel();
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (elem instanceof Responsibility) {
            oldCode = ((Responsibility) elem).getExpression();
            oldName = ((Responsibility) elem).getName();
            oldDescription = ((Responsibility) elem).getDescription();
        } else if (elem instanceof FailurePoint) {
            oldCode = ((FailurePoint) elem).getExpression();
            oldName = ((FailurePoint) elem).getName();
            oldDescription = ((FailurePoint) elem).getDescription();
        } else if (elem instanceof Condition) {
            oldCode = ((Condition) elem).getExpression();
            oldName = ((Condition) elem).getLabel();
            oldDescription = ((Condition) elem).getDescription();
        } else if (elem instanceof Initialization) {
            oldCode = ((Initialization) elem).getValue();
        } else if (elem instanceof NodeConnection)
        {
            oldCode = ((NodeConnection)elem).getThreshold();
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
            ((Responsibility) elem).setExpression(code);
            if (extendedFeatures) {
                ((Responsibility) elem).setName(name);
                ((Responsibility) elem).setDescription(description);
            }

        } else if (elem instanceof FailurePoint) {
            ((FailurePoint) elem).setExpression(code);
            if (extendedFeatures) {
                ((FailurePoint) elem).setName(name);
                ((FailurePoint) elem).setDescription(description);
            }

        } else if (elem instanceof Condition) {
            ((Condition) elem).setExpression(code);
            if (extendedFeatures) {
                ((Condition) elem).setLabel(name);
                ((Condition) elem).setDescription(description);
            }
        } else if (elem instanceof Initialization) {
            ((Initialization) elem).setValue(code);
        }
        else if (elem instanceof NodeConnection) {
            ((NodeConnection) elem).setThreshold(code);
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
            ((Responsibility) elem).setExpression(oldCode);
            if (extendedFeatures) {
                ((Responsibility) elem).setName(oldName);
                ((Responsibility) elem).setDescription(oldDescription);
            }
        } else if (elem instanceof FailurePoint) {
            ((FailurePoint) elem).setExpression(oldCode);
            if (extendedFeatures) {
                ((FailurePoint) elem).setName(oldName);
                ((FailurePoint) elem).setDescription(oldDescription);
            }
        } else if (elem instanceof Condition) {
            ((Condition) elem).setExpression(oldCode);
            if (extendedFeatures) {
                ((Condition) elem).setLabel(oldName);
                ((Condition) elem).setDescription(oldDescription);
            }
        } else if (elem instanceof Initialization) {
            ((Initialization) elem).setValue(oldCode);
        } else if (elem instanceof NodeConnection) {
            ((NodeConnection) elem).setThreshold(oldCode);
        }        

        testPreConditions();
    }

}