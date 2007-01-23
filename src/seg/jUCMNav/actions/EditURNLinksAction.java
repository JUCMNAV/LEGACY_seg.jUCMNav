package seg.jUCMNav.actions;

import grl.Actor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.views.urnlinks.URNLinksDialog;
import urn.URNlink;
import urncore.ComponentElement;
import urncore.Responsibility;
import urncore.URNmodelElement;

/**
 * This action open the URNLink dialog for the selected element
 * 
 * @author Jean-François Roy
 *
 */
public class EditURNLinksAction extends URNSelectionAction {

    public static final String EDITURNLINKS = "seg.jUCMNav.EditURNLinksAction"; //$NON-NLS-1$

    private URNmodelElement element;
    
    /**
     * @param part
     */
    public EditURNLinksAction(IWorkbenchPart part) {
        super(part);
        setId(EDITURNLINKS);
        setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/urnlink.gif")); //$NON-NLS-1$
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
        } else if (sel.getSelectionType() == SelectionHelper.RESPONSIBILITYREF){
            Responsibility resp = sel.getRespRef().getRespDef();
            if (resp.getToLinks().size() > 0){
                element = ((URNlink)resp.getToLinks().get(0)).getFromElem();
                return true;
            } else{
                return false;
            }
        } else if (sel.getSelectionType() == SelectionHelper.COMPONENTREF){
            ComponentElement comp = (ComponentElement)sel.getComponentref().getContDef();
            if (comp.getFromLinks().size() > 0){
                element = ((URNlink)comp.getFromLinks().get(0)).getToElem();
                return true;
            } else{
                return false;
            }
        }
        return false;
    }
    
    /**
     * Launches a {@link URNLinksDialog}
     * 
     */
    public void run() {
        // TODO: should be launched by something else than constructor. 
        new URNLinksDialog(getCommandStack(), element);
    }

}
