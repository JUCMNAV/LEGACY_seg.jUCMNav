package seg.jUCMNav.actions;

import fm.Feature;
import grl.IntentionalElementRef;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.actions.hyperlinks.HyperlinkUtils;
import seg.jUCMNav.model.commands.transformations.ChangeLinkCommand;
import ca.mcgill.sel.core.COREFeatureRelationshipType;

/**
 * Action for changing a Feature's link type with its parent.
 * 
 * @author Patrice Boulet
 * 
 */
public class ChangeLinkAction extends URNSelectionAction
{
    public static final String CHANGELINK = "seg.jUCMNav.ChangeLink"; //$NON-NLS-1$
  
    private IntentionalElementRef elementRef;
    protected String relationship = null;
    
    public ChangeLinkAction(IWorkbenchPart part)
    {
        super(part);        
        setId(CHANGELINK);
    }
    
    /**
     * True if we have selected a Feature that is connected to a parent Feature.
     */
    @SuppressWarnings("static-access")
	protected boolean calculateEnabled() 
    {
        @SuppressWarnings("unchecked")
		List<Object> objects = (List<Object>)getSelectedObjects();

        if (objects.size() != 1)
            return false;

        SelectionHelper sel = new SelectionHelper(objects);
        sel.getUrnspec();
        HyperlinkUtils.findURNmodelElement(sel);
        
        if (sel.getSelectionType() == sel.INTENTIONALELEMENTREF) {
            elementRef = sel.getIntentionalElementRef(); 
            if( elementRef.getDef() instanceof Feature){
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
