package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.WaitKind;
import ucm.map.WaitingPlace;

/**
 * Changes an element's kind
 * 
 * @author jkealey
 */
public class ChangeWaitPlaceTypeCommand extends Command implements JUCMNavCommand {
    private WaitingPlace element;
    private WaitKind oldKind, newKind;

    public ChangeWaitPlaceTypeCommand(WaitingPlace obj, WaitKind kind) {
        this.element = obj;
        this.newKind = kind;

        setLabel(Messages.getString("ChangeWaitPlaceTypeCommand.ChangeWaitPlaceType")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        oldKind = element.getWaitType();

        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        element.setWaitType(newKind);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert element != null : "post no elem to change!"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert element != null : "pre no elem to change"; //$NON-NLS-1$

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        element.setWaitType(oldKind);

        testPreConditions();
    }

    public WaitingPlace getElement() {
        return element;
    }

    public void setElement(WaitingPlace element) {
        this.element = element;
    }

    public WaitKind getOldKind() {
        return oldKind;
    }

    public void setOldKind(WaitKind oldKind) {
        this.oldKind = oldKind;
    }

    public WaitKind getNewKind() {
        return newKind;
    }

    public void setNewKind(WaitKind newKind) {
        this.newKind = newKind;
    }

}