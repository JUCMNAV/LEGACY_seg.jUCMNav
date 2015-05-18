package seg.jUCMNav.actions;

import fm.Feature;
import fm.MandatoryFMLink;
import fm.OptionalFMLink;
import grl.IntentionalElementRef;
import grl.LinkRef;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.commands.transformations.ChangeLinkCommand;

/**
 * Action for changing a Feature's link type with its parent to Optional.
 * 
 * @author Patrice Boulet
 * 
 */
public class ChangeLinkOptionalAction extends ChangeLinkAction
{
    public static final String CHANGELINKOPTIONAL = "seg.jUCMNav.ChangeLinkOptional"; //$NON-NLS-1$
  
    private IntentionalElementRef elementRef;
    
    public ChangeLinkOptionalAction(IWorkbenchPart part)
    {
        super(part);        
        setId(CHANGELINKOPTIONAL);
    	relationship = ChangeLinkCommand.FEATURE_OPTIONAL_RELATIONSHIP;
    }
    
    /**
     * True if we have selected a Feature and it's link type is not Optional already.
     */
    @SuppressWarnings("static-access")
	protected boolean calculateEnabled() 
    {
    	@SuppressWarnings("unchecked")
		List<Object> objects = (List<Object>)getSelectedObjects();


        if (objects.size() != 1)
            return false;

        SelectionHelper sel = new SelectionHelper(objects);
        
        if (sel.getSelectionType() == sel.INTENTIONALELEMENTREF) {
            elementRef = sel.getIntentionalElementRef(); 
            if( elementRef.getDef() instanceof Feature){
            	
            	Feature feature = (Feature)elementRef.getDef();
            	if( feature.getLinksSrc().size() != 0){
            	  	if ( feature.getLinksSrc().get(0) instanceof OptionalFMLink){
                		return false;
                	}
            	}
            	return true;
            }else
            	return false;
        } else if (sel.getSelectionType() == sel.LINKREF) {
        	LinkRef linkRef = sel.getLinkref();
        	elementRef = (IntentionalElementRef) linkRef.getSource();
        	
        	if (elementRef.getDef() instanceof Feature && linkRef.getLink() instanceof MandatoryFMLink) {
        		return true;
        	} else {
        		return false;
        	}
        } else
            return false;
  
    }
    
    /**
     * Trying to change the link type.
     * 
     */
    protected Command getCommand() 
    {
        return new ChangeLinkCommand(relationship, elementRef);
    }

}
