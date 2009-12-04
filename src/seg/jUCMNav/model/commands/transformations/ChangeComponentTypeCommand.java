package seg.jUCMNav.model.commands.transformations;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentRef;
import urncore.Component;
import urncore.ComponentKind;

/**
 * Changes an element's kind
 * 
 * @author jkealey
 */
public class ChangeComponentTypeCommand extends Command implements JUCMNavCommand {
    private ComponentRef element;
    private ComponentKind oldKind, newKind;

    public ChangeComponentTypeCommand(ComponentRef obj, ComponentKind kind) {
        this.element = obj;
        this.newKind = kind;

        setLabel(Messages.getString("ChangeComponentTypeCommand.ChangeComponentType")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (element.getContDef() instanceof Component) {
            Component c = (Component) element.getContDef();
            oldKind = c.getKind();
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

        if (element.getContDef() instanceof Component) {
            Component c = (Component) element.getContDef();
            c.setKind(newKind);
        }

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

        if (element.getContDef() instanceof Component) {
            Component c = (Component) element.getContDef();
            c.setKind(oldKind);
        }
        testPreConditions();
    }

    public ComponentRef getElement() {
        return element;
    }

    public void setElement(ComponentRef element) {
        this.element = element;
    }

    public ComponentKind getOldKind() {
        return oldKind;
    }

    public void setOldKind(ComponentKind oldKind) {
        this.oldKind = oldKind;
    }

    public ComponentKind getNewKind() {
        return newKind;
    }

    public void setNewKind(ComponentKind newKind) {
        this.newKind = newKind;
    }

}