package seg.jUCMNav.actions;

import fm.Feature;
import grl.Decomposition;
import grl.ElementLink;
import grl.IntentionalElement;
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
public class ChangeLinkXORAction extends ChangeLinkAction
{
    public static final String CHANGELINKXOR = "seg.jUCMNav.ChangeLinkXOR"; //$NON-NLS-1$
  
    private URNmodelElement element;
    private IntentionalElementRef elementRef;
    private URNspec urnspec;
    
    public ChangeLinkXORAction(IWorkbenchPart part)
    {
        super(part);        
        setId(CHANGELINKXOR);
    	relationship = COREFeatureRelationshipType.XOR;
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
            	
            	Feature feature = (Feature)elementRef.getDef();
            	if ( feature.getLinksSrc().get(0) instanceof Decomposition ){
            		IntentionalElement intElem = (IntentionalElement)((ElementLink)feature.getLinksSrc().get(0)).getDest();
            		int decompType = intElem.getDecompositionType().getValue();
            	
            		if ( decompType == 2 ) /* XOR */ {
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
     * Trying to Add linked element to environment.
     * 
     */
    protected Command getCommand() 
    {
        return new ChangeLinkCommand(relationship, elementRef);
    }

}
