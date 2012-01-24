package seg.jUCMNav.actions;

import java.util.List;

import grl.Actor;

import org.eclipse.gef.EditPart;
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

    protected boolean calculateEnabled() {
    	List parts = getSelectedObjects();
    	SelectionHelper sel = new SelectionHelper( parts );

    	if( parts.size() == 1 ){
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
    		} else if( parts.get(0) instanceof EditPart ){
    			EditPart ep = ((EditPart) parts.get(0));
    			if( ep.getModel() instanceof URNmodelElement )
    			element = (URNmodelElement) ep.getModel();
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
