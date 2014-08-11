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
import urn.URNspec;
import urncore.URNmodelElement;

/**
 * Action for showing linked element(s).
 * 
 * @author rouzbahan
 * 
 */
public class ChangeLinkMandatoryAction extends ChangeLinkAction
{
    public static final String CHANGELINKMANDATORY = "seg.jUCMNav.ChangeLinkMandatory"; //$NON-NLS-1$
  
    private URNmodelElement element;
    private IntentionalElementRef elementRef;
    private URNspec urnspec;
    
    public ChangeLinkMandatoryAction(IWorkbenchPart part)
    {
        super(part);        
        setId(CHANGELINKMANDATORY);
    	relationship = COREFeatureRelationshipType.MANDATORY;
    }
}
