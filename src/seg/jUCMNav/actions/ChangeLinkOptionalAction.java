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
public class ChangeLinkOptionalAction extends ChangeLinkAction
{
    public static final String CHANGELINKOPTIONAL = "seg.jUCMNav.ChangeLinkOptional"; //$NON-NLS-1$
  
    private URNmodelElement element;
    private IntentionalElementRef elementRef;
    private URNspec urnspec;
    
    public ChangeLinkOptionalAction(IWorkbenchPart part)
    {
        super(part);        
        setId(CHANGELINKOPTIONAL);
    	relationship = COREFeatureRelationshipType.OPTIONAL;
    }

}
