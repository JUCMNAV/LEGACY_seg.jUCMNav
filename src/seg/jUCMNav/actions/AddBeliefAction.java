/**
 * 
 */
package seg.jUCMNav.actions;

import grl.IntentionalElementRef;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.transformations.AddBeliefToIntentionalElementRefCommand;

/**
 * This action is used to create a Belief associate to the selected IntentionalElementRef
 * 
 * @author Jean-François Roy
 * 
 */
public class AddBeliefAction extends URNSelectionAction {

    public static final String ADDBELIEF = Messages.getString("AddBeliefAction.AddBelief"); //$NON-NLS-1$
    private IntentionalElementRef selection;

    /**
     * @param part
     */
    public AddBeliefAction(IWorkbenchPart part) {
        super(part);
        setId(ADDBELIEF);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Belief16.gif")); //$NON-NLS-1$
    }

    /**
     * We need to have an intentional element reference selected.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.INTENTIONALELEMENTREF:
            selection = sel.getIntentionalElementRef();
            return true;
        }
        return false;
    }

    /**
     * @return Builds a chained command to create a belief associate with the selected intentional element reference.
     */
    protected Command getCommand() {
        return new AddBeliefToIntentionalElementRefCommand(selection);
    }

}
