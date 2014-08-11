package seg.jUCMNav.actions;

import fm.Feature;
import grl.IntentionalElementRef;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import ca.mcgill.sel.core.COREFeatureRelationshipType;
import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.hyperlinks.HyperlinkUtils;
import seg.jUCMNav.model.commands.create.ShowLinkedElementCommand;
import seg.jUCMNav.model.commands.transformations.ChangeLinkCommand;
import urn.URNspec;
import urncore.URNmodelElement;

/**
 * Action for showing linked element(s).
 * 
 * @author rouzbahan
 * 
 */
public class ChangeLinkAction extends URNSelectionAction
{
    public static final String CHANGELINK = "seg.jUCMNav.ChangeLink"; //$NON-NLS-1$
  
    private URNmodelElement element;
    private IntentionalElementRef elementRef;
    private URNspec urnspec;
    protected COREFeatureRelationshipType relationship = null;
    private Feature feature;
    
    public ChangeLinkAction(IWorkbenchPart part)
    {
        super(part);        
        setId(CHANGELINK);
    }
    
    /**
     * True if we have selected a valid URNmodelElement.
     */
    @SuppressWarnings("static-access")
	protected boolean calculateEnabled() 
    {
        List objects = getSelectedObjects();

        if (objects.size() != 1)
            return false;

        SelectionHelper sel = new SelectionHelper(objects);
        urnspec = sel.getUrnspec();
        element = HyperlinkUtils.findURNmodelElement(sel);
        
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
     * Trying to Add linked element to environment.
     * 
     */
    protected Command getCommand() 
    {
        return new ChangeLinkCommand(relationship, elementRef);
    }
}
