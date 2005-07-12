package seg.jUCMNav.model.commands.transformations;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.URNNamingHelper;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import ucm.map.RespRef;
import urncore.ComponentLabel;
import urncore.Condition;
import urncore.Label;
import urncore.NodeLabel;
import urncore.UCMmodelElement;

/**
 * Renames a PathNode or ComponentRef. Will rename the definition if this is a reference.
 * 
 * @author jkealey
 */
public class ChangeLabelNameCommand extends Command implements JUCMNavCommand {
    private EObject elem;

    private String name, oldName;

    public ChangeLabelNameCommand(Label lbl, String name) {
        if (lbl instanceof ComponentLabel)
            this.elem = ((ComponentLabel) lbl).getCompRef();
        else if (lbl instanceof NodeLabel)
            this.elem = ((NodeLabel) lbl).getPathNode();
        else if (lbl instanceof Condition) {
            this.elem = lbl;
        }
        this.name = name;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (elem instanceof ComponentRef) {
            oldName = ((ComponentRef) elem).getCompDef().getName();
        } else if (elem instanceof RespRef) {
            oldName = ((RespRef) elem).getRespDef().getName();
        } else if (elem instanceof PathNode) {
            oldName = ((PathNode) elem).getName();
        } else if (elem instanceof Condition) {
            oldName = ((Condition) elem).getLabel();
        }
        redo();
    }

    /**
     * @return whether we can apply changes
     */
    public boolean canExecute() {
        if (elem instanceof ComponentRef || elem instanceof PathNode || elem instanceof Condition) {
            return verifyUniqueness(name);
        } else
            return false;
    }

    /**
     * @return true or false - uniqueness of name
     */
    private boolean verifyUniqueness(String name) {
        if (elem instanceof UCMmodelElement) {
            return URNNamingHelper.isNameValid((UCMmodelElement) elem, name).length() == 0;
        }
        return true;
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
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        if (elem instanceof ComponentRef) {
            ((ComponentRef) elem).getCompDef().setName(name);
        } else if (elem instanceof RespRef) {
            ((RespRef) elem).getRespDef().setName(name);
        } else if (elem instanceof PathNode) {
            ((PathNode) elem).setName(name);
        } else if (elem instanceof Condition) {
            ((Condition) elem).setLabel(name);
        }
        testPostConditions();
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        if (elem instanceof ComponentRef) {
            ((ComponentRef) elem).getCompDef().setName(oldName);
        } else if (elem instanceof RespRef) {
            ((RespRef) elem).getRespDef().setName(oldName);
        } else if (elem instanceof PathNode) {
            ((PathNode) elem).setName(oldName);
        } else if (elem instanceof Condition) {
            ((Condition) elem).setLabel(oldName);
        }
        testPreConditions();
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

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert verifyUniqueness(oldName) : "post problem; non unique name used."; //$NON-NLS-1$
        assert elem != null : "post no elemement to name!"; //$NON-NLS-1$
    }
}