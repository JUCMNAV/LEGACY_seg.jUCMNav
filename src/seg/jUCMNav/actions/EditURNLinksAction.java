package seg.jUCMNav.actions;

import java.util.List;

import grl.Actor;
import grl.Contribution;
import grl.ElementLink;

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
    private EditPart editPart;

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
    		
    		if( parts.get(0) instanceof EditPart ){
    			editPart = ((EditPart) parts.get(0));
    		}
    		
    		System.out.println( "EditURNLinksAction: " + editPart.getModel().getClass().getName() );
    		
    		if (sel.getSelectionType() == SelectionHelper.INTENTIONALELEMENTREF) {
//    			element = sel.getIntentionalelementref().getDef();
    			element = sel.getIntentionalelementref();
    			return true;
    		} else if (sel.getSelectionType() == SelectionHelper.ACTORREF) {
    			element = (Actor) sel.getActorref().getContDef();
    			return true;
    		}
//    		} else if (sel.getSelectionType() == SelectionHelper.ACTOR) {
//    			element = sel.getActor();
//    			return true;
//    		} else if (sel.getSelectionType() == SelectionHelper.INTENTIONALELEMENT) {
//    			element = sel.getIntentionalElement();
//    			return true;
//    		} 
    		else if (sel.getSelectionType() == SelectionHelper.RESPONSIBILITYREF) {
//    			Responsibility resp = sel.getRespRef().getRespDef();
//    			element = resp;
    			element = sel.getRespRef();
    			return true; 		
    		} else if (sel.getSelectionType() == SelectionHelper.LINKREF) {
    			ElementLink el = sel.getLinkref().getLink();
    			element = el;
//    			element = sel.getLinkref();
    			return true;
    		} else if (sel.getSelectionType() == SelectionHelper.COMPONENTREF) {
//    			Component comp = (Component) sel.getComponentref().getContDef();
//    			element = comp;
    			element = sel.getComponentref();
    			return true;
    		} else if( editPart.getModel() instanceof URNmodelElement ) {
    			element = (URNmodelElement) editPart.getModel();
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
        ul.EditLink( getCommandStack(), element, editPart );
    }

}
