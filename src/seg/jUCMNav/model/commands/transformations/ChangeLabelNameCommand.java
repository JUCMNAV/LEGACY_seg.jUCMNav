package seg.jUCMNav.model.commands.transformations;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.URNNamingHelper;
import ucm.map.PathNode;
import ucm.map.RespRef;
import urncore.ComponentLabel;
import urncore.Condition;
import urncore.IURNContainerRef;
import urncore.Label;
import urncore.NodeLabel;
import urncore.URNmodelElement;

/**
 * Renames a PathNode or ComponentRef. Will rename the definition if this is a reference.
 * 
 * @author jkealey
 */
public class ChangeLabelNameCommand extends Command implements JUCMNavCommand {
    // PluginBinding dependant.
    private String description = "", oldDesc = ""; //$NON-NLS-1$ //$NON-NLS-2$
    private EObject elem;

    private String expression = "", oldExp = ""; //$NON-NLS-1$ //$NON-NLS-2$

    private Label lbl;
    private String name, oldName;

    public ChangeLabelNameCommand(Label lbl, String name) {
        this.lbl = lbl;
        if (lbl instanceof ComponentLabel)
            this.elem = ((ComponentLabel) lbl).getContRef();
        else if (lbl instanceof NodeLabel)
            this.elem = ((NodeLabel) lbl).getNode();
        else if (lbl instanceof Condition) {
            this.elem = lbl;
        }
        this.name = name;
        setLabel(Messages.getString("ChangeLabelNameCommand.changeLabelName")); //$NON-NLS-1$
    }

    /**
     * @return whether we can apply changes
     */
    public boolean canExecute() {
        if (elem instanceof IURNContainerRef || elem instanceof PathNode || elem instanceof Condition) {
            return verifyUniqueness(name);
        } else
            return false;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (elem instanceof IURNContainerRef) {
            oldName = ((URNmodelElement) ((IURNContainerRef) elem).getContDef()).getName();
        } else if (elem instanceof RespRef) {
            oldName = ((RespRef) elem).getRespDef().getName();
        } else if (elem instanceof PathNode) {
            oldName = ((PathNode) elem).getName();
        } else if (elem instanceof Condition) {
            oldName = ((Condition) elem).getLabel();
            oldDesc = ((Condition) elem).getDescription();
            oldExp = ((Condition) elem).getExpression();
        }
        redo();
    }

    public String getDescription() {
        return description;
    }

    public String getExpression() {
        return expression;
    }

    public String getName() {
        return name;
    }

    public String getOldName() {
        return oldName;
    }

    public Label getRenamedLabel() {
        return this.lbl;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        if (elem instanceof IURNContainerRef) {
            ((URNmodelElement) ((IURNContainerRef) elem).getContDef()).setName(name);
        } else if (elem instanceof RespRef) {
            ((RespRef) elem).getRespDef().setName(name);
        } else if (elem instanceof PathNode) {
            ((PathNode) elem).setName(name);
        } else if (elem instanceof Condition) {
            ((Condition) elem).setLabel(name);
            ((Condition) elem).setExpression(expression);
            ((Condition) elem).setDescription(description);
        }
        testPostConditions();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * Sets the new Column name
     * 
     * @param string
     *            the new name
     */
    public void setName(String string) {
        this.name = string;
    }

    /**
     * Sets the old Column name
     * 
     * @param string
     *            the old name
     */
    public void setOldName(String string) {
        oldName = string;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert verifyUniqueness(oldName) : "post problem; non unique name used."; //$NON-NLS-1$
        assert elem != null : "post no elemement to name!"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert verifyUniqueness(name) : "pre problem; non unique name used."; //$NON-NLS-1$
        assert elem != null : "pre no elemement to name!"; //$NON-NLS-1$

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        if (elem instanceof IURNContainerRef) {
            ((URNmodelElement) ((IURNContainerRef) elem).getContDef()).setName(oldName);
        } else if (elem instanceof RespRef) {
            ((RespRef) elem).getRespDef().setName(oldName);
        } else if (elem instanceof PathNode) {
            ((PathNode) elem).setName(oldName);
        } else if (elem instanceof Condition) {
            ((Condition) elem).setLabel(oldName);
            ((Condition) elem).setExpression(oldExp);
            ((Condition) elem).setDescription(oldDesc);
        }
        testPreConditions();
    }

    /**
     * @return true or false - uniqueness of name
     */
    private boolean verifyUniqueness(String name) {
        if (elem instanceof URNmodelElement) {
            return URNNamingHelper.isNameValid((URNmodelElement) elem, name).length() == 0;
        }
        return true;
    }
}