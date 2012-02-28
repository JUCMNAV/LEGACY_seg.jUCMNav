package seg.jUCMNav.actions;

import grl.ElementLink;
import grl.EvaluationStrategy;
import grl.StrategiesGroup;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import urn.util.EditURNLink;
import urncore.Responsibility;
import urncore.URNmodelElement;

/**
 * This action open the URNLink dialog for the selected element
 * 
 * @author Jean-Francois Roy
 * 
 */
public class EditURNLinksAction extends URNSelectionAction {

    public static final String EDITURNLINKS = "seg.jUCMNav.EditURNLinksAction"; //$NON-NLS-1$

    private URNmodelElement element, parentElement;
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

    	if( (parts.size() > 0) && (parts.get(0) instanceof EditPart) )
    		editPart = ((EditPart) parts.get(0));
    	else
    		return false;

    	if( JUCMNavPlugin.isInDebug() ){
    		System.out.println( "EditURNLinksAction: " + editPart.getModel().getClass().getName() ); //$NON-NLS-1$
    	}

    	if (sel.getSelectionType() == SelectionHelper.INTENTIONALELEMENTREF) {
    		parentElement = sel.getIntentionalelementref().getDef();
    		element = sel.getIntentionalelementref();
    		return this.verifySelection();
    	} else if (sel.getSelectionType() == SelectionHelper.ACTOR) {
    		parentElement = sel.getActor();
    		element = sel.getActorref();
    		return this.verifySelection();
    	} else if (sel.getSelectionType() == SelectionHelper.RESPONSIBILITYREF) {
    		Responsibility resp = sel.getRespRef().getRespDef();
    		parentElement = resp;
    		element = sel.getRespRef();
    		return this.verifySelection();
    	} else if (sel.getSelectionType() == SelectionHelper.LINKREF) {
    		ElementLink el = sel.getLinkref().getLink();
    		parentElement = null;
    		element = el;
    		return this.verifySelection();
    	} else if (sel.getSelectionType() == SelectionHelper.COMPONENTREF) {
    		parentElement = sel.getComponent();
    		element = sel.getComponentref();
    		return this.verifySelection();
    	} else if (sel.getSelectionType() == SelectionHelper.EVALUATIONSTRATEGY) {
    		EvaluationStrategy strategy = sel.getEvaluationStrategy();
    		StrategiesGroup group = strategy.getGroup();
    		element = strategy;
    		parentElement = group;
    		if( JUCMNavPlugin.isInDebug() ) System.out.println( "strategy selected name: " + element.getName()); //$NON-NLS-1$
    		return this.verifySelection();
    	} else if ( (element = sel.getURNmodelElement()) != null ) {
    		parentElement = null;
    		if( JUCMNavPlugin.isInDebug() ) {
    			System.out.println( "element = sel.getURNmodelElement()) != null type: " + element.getClass().getSimpleName() + " \"" + element.getName() + "\""  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    		+ " sel.getSelectionType() = " + sel.getSelectionType() ); //$NON-NLS-1$
    		}
    		return true;
    	} else
    		return false;
    }

    private boolean verifySelection()
    {
		if( element == null && parentElement == null )
			return false;
		else
			return true;
    }
    
    public void run() {
    	if( element == null && parentElement == null )
    		return; // sanity check
    	EditURNLink ul = new EditURNLink();
        ul.editLink( getCommandStack(), element, parentElement, editPart );
    }
}
