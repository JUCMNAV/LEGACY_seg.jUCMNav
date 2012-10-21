package seg.jUCMNav.actions;

import java.util.List;

import grl.IntentionalElementRef;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.hyperlinks.HyperlinkUtils;
import seg.jUCMNav.model.commands.create.ShowLinkedElementLevelThreeCommand;
import urn.URNspec;
import urncore.URNmodelElement;

/**
 * @author Rouzbahan
 *
 */
public class ShowLinkedElementLevelThreeAction extends URNSelectionAction
{
    public static final String SHOWLINKEDELEMENTLEVELTHREE = "seg.jUCMNav.ShowLinkedElementActionLevelThree"; //$NON-NLS-1$
  
    private URNmodelElement element;
    private IntentionalElementRef elementRef;
    private URNspec urnspec;

    public ShowLinkedElementLevelThreeAction(IWorkbenchPart part)
    {
        super(part);
    
        setId(SHOWLINKEDELEMENTLEVELTHREE);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/ShowLinkedElement3.gif")); //$NON-NLS-1$
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
        return new ShowLinkedElementLevelThreeCommand(urnspec, element, elementRef);
    }
}
