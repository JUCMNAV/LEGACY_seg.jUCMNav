package seg.jUCMNav.model.commands.change;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;

public class SetCommand extends Command implements JUCMNavCommand {

    EObject obj;
    EAttribute attr;
    Object value;
    Object before;

    public SetCommand(EObject obj, EAttribute attr, Object value) {
        super();

        this.obj = obj;
        this.attr = attr;
        this.value = value;

        this.before = obj.eGet(attr);
    }

    public void execute() {
        redo();
    }

    public void redo() {
        obj.eSet(attr, value);
    }

    public void undo() {
        obj.eSet(attr, before);
    }

    public void testPostConditions() {

    }

    public void testPreConditions() {

    }

    public boolean canExecute() {
        return obj != null && attr != null && value != null;
    }
}
