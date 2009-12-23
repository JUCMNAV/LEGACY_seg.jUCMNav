/**
 * 
 */
package seg.jUCMNav.actions;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.delete.DeleteUnreferencedDefinitionCommand;
import urn.URNspec;

/**
 * This action delete all the unreferenced definitions in the URNSpec (Components, responsibilities, intentional elementsand actors).
 * 
 * @author jfroy
 * 
 */
public class DeleteUnreferencedDefinitionAction extends URNSelectionAction {
    public static final String DELETEDEF = "seg.jUCMNav.DeleteUnreferencedDefinition"; //$NON-NLS-1$

    public DeleteUnreferencedDefinitionAction(IWorkbenchPart part) {
        super(part);
        setId(DELETEDEF);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Resp16.gif")); //$NON-NLS-1$
    }

    /**
     * True if we select the URNSpec, the UCM definitions or the GRL definitions
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        if (sel.getSelectionType() == SelectionHelper.URNSPEC) {
            return true;
        }
        return false;
    }

    protected Command getCommand() {
        SelectionHelper selection = new SelectionHelper(getSelectedObjects());
        if (selection.getSelectionType() == SelectionHelper.URNSPEC) {
            URNspec urn = selection.getUrnspec();
            return new DeleteUnreferencedDefinitionCommand(urn);
        } else {
            return null;
        }

    }
}
