package seg.jUCMNav.actions;

import grl.IntentionalElementRef;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.actions.hyperlinks.HyperlinkUtils;
import seg.jUCMNav.model.commands.create.ShowLinkedElementAlternativeSubNodesCommand;
import seg.jUCMNav.views.wizards.ShowLinkedElementDialog;
import urn.URNspec;
import urncore.URNmodelElement;

/**
 * Action for showing linked element(s).
 * 
 * @author rouzbahan
 * 
 */
public class ShowLinkedElementAlternativeSubNodesAction extends URNSelectionAction 
{
    public static final String SHOWLINKEDELEMENTALTERNATIVESUBNODES = "seg.jUCMNav.ShowLinkedElementAlternativeSubNodesAction"; //$NON-NLS-1$
  
    private URNmodelElement element;
    private IntentionalElementRef elementRef;
    private URNspec urnspec;
    
    public ShowLinkedElementAlternativeSubNodesAction(IWorkbenchPart part) {
        super(part);        
        setId(SHOWLINKEDELEMENTALTERNATIVESUBNODES);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/ShowLinkedElementAlternative.gif")); //$NON-NLS-1$
    }
    
    /**
     * True if we have selected a valid URNmodelElement.
     */
    @SuppressWarnings("static-access")
	protected boolean calculateEnabled() {
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
     * Trying to Add linked element to environment.
     * 
     */
    protected Command getCommand() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        ShowLinkedElementDialog dialog = new ShowLinkedElementDialog(shell);
        String value = dialog.open(Messages.getString("ShowLinkedElementalternative.WindowEnterNew"), //$NON-NLS-1$ 
            Messages.getString("ShowLinkedElementalternative.TextEnterNew"), //$NON-NLS-1$  
            ""); //$NON-NLS-1$
        
        if (value != null && value.length() > 0)
            return new ShowLinkedElementAlternativeSubNodesCommand(urnspec, element, elementRef, value);
        else
            return null;        
    }
}
