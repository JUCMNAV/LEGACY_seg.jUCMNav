package seg.jUCMNav.model.commands.delete;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.PreDeleteUrnModelElementCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;
import seg.jUCMNav.views.preferences.DeletePreferences;
import ucm.map.ComponentRef;
import urncore.Component;

/**
 * Command to delete a ComponentRef. (Remove it from the model).
 * 
 * @author jkealey, jfroy
 * 
 */
public class DeleteComponentRefCommand extends CompoundCommand {

    private ComponentRef cr;

    /**
     * 
     * @param cr
     *            the component reference to delete.
     */
    public DeleteComponentRefCommand(ComponentRef cr) {
        this.cr = cr;
        setLabel(Messages.getString("DeleteComponentRefCommand.deleteComponentRef")); //$NON-NLS-1$
        add(new PreDeleteUrnModelElementCommand(cr));
        add(new RemoveURNmodelElementCommand(cr));

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CompoundCommand#execute()
     */
    public void execute() {
        // Verify if this reference is the only on the component definition
        if (cr.getContDef().getContRefs().size() <= 1 && DeletePreferences.getDeleteDefinition(cr)) {
            add(new DeleteComponentCommand((Component) cr.getContDef()));
        }
        super.execute();
    }
}