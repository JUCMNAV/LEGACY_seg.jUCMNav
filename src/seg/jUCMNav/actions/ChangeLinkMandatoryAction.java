package seg.jUCMNav.actions;

import fm.Feature;
import fm.MandatoryFMLink;
import grl.IntentionalElementRef;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.commands.transformations.ChangeLinkCommand;

/**
 * Action for changing a Feature's link type with its parent to Mandatory.
 * 
 * @author Patrice Boulet
 * 
 */
public class ChangeLinkMandatoryAction extends ChangeLinkAction
{
    public static final String CHANGELINKMANDATORY = "seg.jUCMNav.ChangeLinkMandatory"; //$NON-NLS-1$
  
    private IntentionalElementRef elementRef;
    
    public ChangeLinkMandatoryAction(IWorkbenchPart part)
    {
        super(part);        
        setId(CHANGELINKMANDATORY);
    	relationship = ChangeLinkCommand.FEATURE_MANDATORY_RELATIONSHIP;
    }
    
    /**
     * True if we have selected a Feature and it's link type is not Mandatory already.
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
            	  	if ( feature.getLinksSrc().get(0) instanceof MandatoryFMLink){
                		return false;
                	}
            	}
            	return true;
            }else
            	return false;
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
