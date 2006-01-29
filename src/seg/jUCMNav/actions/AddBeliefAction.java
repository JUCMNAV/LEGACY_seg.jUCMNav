/**
 * 
 */
package seg.jUCMNav.actions;

import grl.IntentionalElementRef;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.transformations.AddBeliefToIntentionalElementRefCommand;

/**
 * This action is used to create a Belief associate to the selected IntentionalElementRef
 * 
 * @author Jean-Fran�ois Roy
 *
 */
public class AddBeliefAction extends URNSelectionAction {

    public static final String ADDBELIEF = "Add Belief";
    private IntentionalElementRef selection;
    
    /**
     * @param part
     */
    public AddBeliefAction(IWorkbenchPart part) {
        super(part);
        setId(ADDBELIEF);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Belief16.gif")); //$NON-NLS-1$
    }

    /**
     * We need to have a URNspec.
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.INTENTIONALELEMENTREF:
            selection = sel.getIntentionalelementref();
            return true;
        }
        return false;
    }
    
    /**
     * @return Builds a chained command to create a belief associate with the selected intentional element reference. 
     */
    protected Command getCommand() {
        return  new AddBeliefToIntentionalElementRefCommand(selection);
    }

}
