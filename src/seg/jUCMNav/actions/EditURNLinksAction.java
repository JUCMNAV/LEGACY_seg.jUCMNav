/**
 * 
 */
package seg.jUCMNav.actions;

import grl.Actor;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.views.urnlinks.URNLinksDialog;
import urncore.GRLmodelElement;

/**
 * This action open the URNLink dialog for the selected element
 * 
 * @author Jean-François Roy
 *
 */
public class EditURNLinksAction extends URNSelectionAction {

    public static final String EDITURNLINKS = "seg.jUCMNav.EditURNLinksAction"; //$NON-NLS-1$

    private GRLmodelElement element;
    
    /**
     * @param part
     */
    public EditURNLinksAction(IWorkbenchPart part) {
        super(part);
        setId(EDITURNLINKS);
    }
    
    /**
     * True if we've selected an intentional element
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        if (sel.getSelectionType() == SelectionHelper.INTENTIONALELEMENTREF) {
            element = sel.getIntentionalelementref().getDef();
            return true;
        } else if (sel.getSelectionType() == SelectionHelper.ACTORREF){
            element = (Actor)sel.getActorref().getContDef();
            return true;
        } else if (sel.getSelectionType() == SelectionHelper.ACTOR){
            element = sel.getActor();
            return true;
        } else if (sel.getSelectionType() == SelectionHelper.INTENTIONALELEMENT){
            element = sel.getIntentionalElement();
            return true;
        }
        return false;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        URNLinksDialog d = new URNLinksDialog(getCommandStack(), element);
    }

}
