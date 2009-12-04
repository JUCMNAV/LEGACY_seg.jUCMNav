package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urncore.URNmodelElement;

/**
 * Changes a URN model element's description.
 * 
 * @author jkealey
 */
public class ChangeDescriptionCommand extends Command implements JUCMNavCommand {
    private URNmodelElement element;
    private String newDescription, oldDescription;

    public ChangeDescriptionCommand(URNmodelElement obj, String newdesc) {
        this.element = obj;
        this.newDescription = newdesc;

        setLabel(Messages.getString("ChangeDescriptionCommand.ChangeDescription")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        oldDescription = element.getDescription();
        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        element.setDescription(newDescription);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert element != null : "post no elem to change description!"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert element != null : "pre no elem to change description"; //$NON-NLS-1$

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        element.setDescription(oldDescription);

        testPreConditions();
    }

    public URNmodelElement getElement() {
        return element;
    }

    public void setElement(URNmodelElement element) {
        this.element = element;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public void setNewDescription(String newDescription) {
        this.newDescription = newDescription;
    }

    public String getOldDescription() {
        return oldDescription;
    }

    public void setOldDescription(String oldDescription) {
        this.oldDescription = oldDescription;
    }

}