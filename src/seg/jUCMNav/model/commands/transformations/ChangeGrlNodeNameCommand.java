/**
 * 
 */
package seg.jUCMNav.model.commands.transformations;

import grl.Belief;
import grl.GRLNode;
import grl.IntentionalElementRef;
import grl.kpimodel.KPIInformationElementRef;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.URNNamingHelper;

/**
 * Rename the reference of the grl node. If it is a belief, modify the description instead
 * 
 * @author Jean-François Roy, pchen
 * 
 */
public class ChangeGrlNodeNameCommand extends Command implements JUCMNavCommand {

    private GRLNode elem;

    private String name, oldName;

    /**
     * Constructor
     */
    public ChangeGrlNodeNameCommand(GRLNode node, String name) {
        this.elem = node;
        this.name = name;
        setLabel(Messages.getString("ChangeGrlNodeNameCommand.changeGrlNodeName")); //$NON-NLS-1$
    }

    public GRLNode getElement() {
        return elem;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (elem instanceof IntentionalElementRef) {
            oldName = (((IntentionalElementRef) elem).getDef()).getName();
        } else if (elem instanceof KPIInformationElementRef) {
            oldName = (((KPIInformationElementRef) elem).getDef()).getName();
        } else if (elem instanceof Belief) {
            oldName = ((Belief) elem).getDescription();
        }
        redo();
    }

    /**
     * @return whether we can apply changes
     */
    public boolean canExecute() {
        if (elem instanceof IntentionalElementRef) {
            return verifyUniqueness(name);
        } else if (elem instanceof KPIInformationElementRef) {
            return verifyUniqueness(name);
        } else if (elem instanceof Belief) {
            return true;
        } else
            return false;
    }

    /**
     * @return true or false - uniqueness of name
     */
    private boolean verifyUniqueness(String name) {
        return URNNamingHelper.isNameValid(elem, name).length() == 0;
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

    public String getName() {
        return this.name;
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

    public String getOldName() {
        return this.oldName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        if (elem instanceof IntentionalElementRef) {
            (((IntentionalElementRef) elem).getDef()).setName(name);
        } else if (elem instanceof KPIInformationElementRef) {
            (((KPIInformationElementRef) elem).getDef()).setName(name);
        } else if (elem instanceof Belief) {
            ((Belief) elem).setDescription(name);
        }
        testPostConditions();
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        if (elem instanceof IntentionalElementRef) {
            (((IntentionalElementRef) elem).getDef()).setName(oldName);
        } else if (elem instanceof KPIInformationElementRef) {
            (((KPIInformationElementRef) elem).getDef()).setName(oldName);
        } else if (elem instanceof Belief) {
            ((Belief) elem).setDescription(oldName);
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
