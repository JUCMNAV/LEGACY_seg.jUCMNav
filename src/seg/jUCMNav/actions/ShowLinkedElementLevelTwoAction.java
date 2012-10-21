package seg.jUCMNav.actions;

import grl.IntentionalElementRef;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.hyperlinks.HyperlinkUtils;
import seg.jUCMNav.model.commands.create.ShowLinkedElementLevelTwoCommand;
import urn.URNspec;
import urncore.URNmodelElement;


/**
 * Action for showing linked element(s) in level two.
 * 
 * @author rouzbahan
 *
 */
public class ShowLinkedElementLevelTwoAction extends URNSelectionAction
{
    public static final String SHOWLINKEDELEMENTLEVELTWO = "seg.jUCMNav.ShowLinkedElementActionLevelTwo"; //$NON-NLS-1$
  
    private URNmodelElement element;
    private IntentionalElementRef elementRef;
    private URNspec urnspec;
  
    public ShowLinkedElementLevelTwoAction(IWorkbenchPart part)
    {
        super(part);
      
        setId(SHOWLINKEDELEMENTLEVELTWO);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/ShowLinkedElement2.gif")); //$NON-NLS-1$
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
            return true;
        } else
            return false;
    }
  
    /**
     * Trying to Add linked element(s) to environment.
     * 
     */
    protected Command getCommand() 
    {
        return new ShowLinkedElementLevelTwoCommand(urnspec, element, elementRef);
    }
}
