/**
 * 
 */
package seg.jUCMNav.model.commands.transformations;

import grl.Belief;
import grl.GRLNode;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.URNNamingHelper;
import urncore.URNmodelElement;


/**
 * Rename the reference of the grl node. If it is a belief, modify the description instead
 * @author Jean-François Roy
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
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (elem instanceof IntentionalElementRef) {
            oldName = ((IntentionalElement)((IntentionalElementRef) elem).getDef()).getName();
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
        } else if (elem instanceof Belief){
            return true;
        }
            else
            return false;
    }

    /**
     * @return true or false - uniqueness of name
     */
    private boolean verifyUniqueness(String name) {
        return URNNamingHelper.isNameValid((URNmodelElement) elem, name).length() == 0;
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

        if (elem instanceof IntentionalElementRef) {
            ((IntentionalElement)((IntentionalElementRef) elem).getDef()).setName(name);
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
            ((IntentionalElement)((IntentionalElementRef) elem).getDef()).setName(oldName);
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
