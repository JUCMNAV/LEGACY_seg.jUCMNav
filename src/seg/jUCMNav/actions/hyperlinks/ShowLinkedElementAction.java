package seg.jUCMNav.actions.hyperlinks;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.model.commands.metadata.ChangeHyperlinkCommand;
//import seg.jUCMNav.model.commands.metadata.ShowLinkedElementCommand;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.views.wizards.HyperlinkDialog;
import urn.URNspec;
import urncore.URNmodelElement;

/**
 * Action for showing linked element(s)
 * 
 * @author rouzbahan
 * 
 */
public class ShowLinkedElementAction extends URNSelectionAction
{
    public static final String SHOWLINKEDELEMENT = "seg.jUCMNav.ShowLinkedElementAction"; //$NON-NLS-1$
  
    private URNmodelElement element;
    private URNspec urnspec;
    
    public ShowLinkedElementAction(IWorkbenchPart part)
    {
        super(part);
        
        setId(SHOWLINKEDELEMENT);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/ShowLinkedElement.gif")); //$NON-NLS-1$
    }
    
    /**
     * True if we have selected a valid URNmodelElement without any hyperlink. Also uses definitions when references are selected.
     */
    protected boolean calculateEnabled() 
    {
        List objects = getSelectedObjects();

        if (objects.size() != 1)
            return false;

        SelectionHelper sel = new SelectionHelper(objects);
        urnspec = sel.getUrnspec();
        element = HyperlinkUtils.findURNmodelElement(sel);

        if (element != null) 
        {
            // True when there is no hyperlink metadata for this URN model element
            return (MetadataHelper.getMetaDataObj(element, HyperlinkUtils.HYPERLINK) == null);
        } 
        else
            return false;
    }
    
    /**
     * Trying to Add linked element to environment
     * 
     */
    protected Command getCommand() {
        Command comm;
        //Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        //HyperlinkDialog dialog = new HyperlinkDialog(shell);

        //String url = dialog.open(Messages.getString("Hyperlink.WindowEnterNew"), //$NON-NLS-1$ 
        //        Messages.getString("Hyperlink.TextEnterNew"), //$NON-NLS-1$  
        //        ""); //$NON-NLS-1$ 

        //if (url != null && url.length() > 0) {
        //    comm = new ChangeHyperlinkCommand(urnspec, element, url);
//        comm = new ShowLinkedElementCommand(urnspec, element);
        //    return comm;
        //} else
            return null;
    }
}
