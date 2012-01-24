package seg.jUCMNav.actions;

import grl.Actor;

import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.views.urnlinks.URNLinksDialog;
import urn.util.EditURNLink;
import urncore.Component;
import urncore.Responsibility;
import urncore.URNmodelElement;

/**
 * This action open the URNLink dialog for the selected element
 * 
 * @author Jean-Fran�ois Roy
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
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/urnlink.gif")); //$NON-NLS-1$
    }

    /**
     * True if we've selected an intentional element
     */
    protected boolean calculateEnabled() {
    	SelectionHelper sel = new SelectionHelper(getSelectedObjects());

    	if( sel.getSelection().size() == 1 ){
    		if (sel.getSelectionType() == SelectionHelper.INTENTIONALELEMENTREF) {
    			element = sel.getIntentionalelementref().getDef();
    			return true;
    		} else if (sel.getSelectionType() == SelectionHelper.ACTORREF) {
    			element = (Actor) sel.getActorref().getContDef();
    			return true;
    		} else if (sel.getSelectionType() == SelectionHelper.ACTOR) {
    			element = sel.getActor();
    			return true;
    		} else if (sel.getSelectionType() == SelectionHelper.INTENTIONALELEMENT) {
    			element = sel.getIntentionalElement();
    			return true;
    		} else if (sel.getSelectionType() == SelectionHelper.RESPONSIBILITYREF) {
    			Responsibility resp = sel.getRespRef().getRespDef();
    			element = resp;
    			return true;
    		} else if (sel.getSelectionType() == SelectionHelper.COMPONENTREF) {
    			Component comp = (Component) sel.getComponentref().getContDef();
    			element = comp;
    			return true;
    		}
    		else if( sel.getSelection().get(0) instanceof URNmodelElement ){
    			element = (URNmodelElement) sel.getSelection().get(0);
    			return true;
    		}
    		else
    			return false;
    	}
    	else
    		return false;
    }

    /**
     * Launches a {@link URNLinksDialog}
     * 
     */
    public void run() {
    	EditURNLink ul = new EditURNLink();
        ul.EditLink( getCommandStack(), element );
    }

}
