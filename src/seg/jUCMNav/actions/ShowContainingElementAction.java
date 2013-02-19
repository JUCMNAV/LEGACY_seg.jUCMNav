package seg.jUCMNav.actions;

import grl.ActorRef;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.create.ShowContainingElementCommand;
import urn.URNspec;
import urncore.URNmodelElement;

/**
 * Action for showing containing element(s)
 * 
 * @author rouzbahan
 *
 */
public class ShowContainingElementAction extends URNSelectionAction
{
    public static final String SHOWCONTAININGELEMENT = "seg.jUCMNav.ShowContainingElementAction"; //$NON-NLS-1$
  
    private URNmodelElement element;
    private ActorRef elementRef;
    private URNspec urnspec;
    
    public ShowContainingElementAction(IWorkbenchPart part)
    {
        super(part);      
        setId(SHOWCONTAININGELEMENT);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/ShowContainingElement.gif")); //$NON-NLS-1$
    }
    
    /**
     * True if we have selected a valid URNmodelElement.
     */
    protected boolean calculateEnabled() 
    {
        List objects = getSelectedObjects();

        if (objects.size() != 1)
            return false;

        SelectionHelper sel = new SelectionHelper(objects);
        urnspec = sel.getUrnspec();
        
        if (sel.getSelectionType() == SelectionHelper.ACTORREF) {
            element = sel.getActor();
            elementRef = (ActorRef) sel.getActorref();
            return true;
        } else
            return false;
    }
    
    /**
     * Trying to Add linked element to environment.
     * 
     */
    protected Command getCommand() 
    {
        return new ShowContainingElementCommand(urnspec, element, elementRef);            
    }
}
