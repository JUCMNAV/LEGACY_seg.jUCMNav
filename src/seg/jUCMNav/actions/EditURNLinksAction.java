package seg.jUCMNav.actions;

import grl.ElementLink;
import grl.EvaluationStrategy;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.views.urnlinks.URNLinksDialog;
import urn.util.EditURNLink;
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

    private URNmodelElement element, parentElement;
    private EditPart editPart;

    private final boolean DEBUG = false;
    
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
  			if( DEBUG) System.out.println( "EditURNLinksAction: " + editPart.getModel().getClass().getName() );

    			if( editPart.getModel() instanceof EvaluationStrategy ){
    				parentElement = null;
    				element = (EvaluationStrategy) editPart.getModel();
    				if( DEBUG) System.out.println( "strategy selected 1 name: " + element.getName());
    				return true;
    			}
    		}

    		if (sel.getSelectionType() == SelectionHelper.INTENTIONALELEMENTREF) {
    			parentElement = sel.getIntentionalelementref().getDef();
    			element = sel.getIntentionalelementref();
    			if( element == null && parentElement == null )
    				return false; // sanity check
    			else
    				return true;
    		} else if (sel.getSelectionType() == SelectionHelper.ACTOR) {
    			parentElement = sel.getActor();
    			element = sel.getActorref();
    			if( element == null && parentElement == null )
    				return false; // sanity check
    			else
    				return true;
    		} else if (sel.getSelectionType() == SelectionHelper.RESPONSIBILITYREF) {
    			Responsibility resp = sel.getRespRef().getRespDef();
    			parentElement = resp;
    			element = sel.getRespRef();
    			if( element == null && parentElement == null )
    				return false; // sanity check
    			else
    				return true; 		
    		} else if (sel.getSelectionType() == SelectionHelper.LINKREF) {
    			ElementLink el = sel.getLinkref().getLink();
    			parentElement = null;
    			element = el;
    			if( element == null && parentElement == null )
    				return false; // sanity check
    			else
    				return true;
    		} else if (sel.getSelectionType() == SelectionHelper.COMPONENTREF) {
    			parentElement = sel.getComponent();
    			element = sel.getComponentref();
    			if( element == null && parentElement == null )
    				return false; // sanity check
    			else
    				return true;
    		} else if (sel.getSelectionType() == SelectionHelper.EVALUATIONSTRATEGY) {
    			parentElement = null;
    			element = sel.getEvaluationStrategy();
    			if( DEBUG) System.out.println( "strategy selected 2 name: " + element.getName());
    			if( element == null && parentElement == null )
    				return false; // sanity check
    			else
    				return true;
    		} else if( editPart.getModel() instanceof URNmodelElement ) {
    			element = (URNmodelElement) editPart.getModel();
    			parentElement = null;
    			if( element == null && parentElement == null )
    				return false; // sanity check
    			else
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
    	if( element == null && parentElement == null )
    		return; // sanity check
    	EditURNLink ul = new EditURNLink();
        ul.editLink( getCommandStack(), element, parentElement, editPart );
    }

}
