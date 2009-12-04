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
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.views.wizards.HyperlinkDialog;
import urn.URNspec;
import urncore.Metadata;
import urncore.URNmodelElement;

/**
 * Action for modifying a hyperlink
 * 
 * @author damyot
 * 
 */
public class ChangeHyperlinkAction extends URNSelectionAction {

    public static final String CHANGEHYPERLINK = "seg.jUCMNav.ChangeHyperlinkAction"; //$NON-NLS-1$

    private URNmodelElement element;
    private Metadata hyperlink;
    private URNspec urnspec;

    public ChangeHyperlinkAction(IWorkbenchPart part) {
        super(part);
        setId(CHANGEHYPERLINK);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/modify16.gif")); //$NON-NLS-1$
    }

    /**
     * True if we have selected a valid URNmodelElement with hyperlink. Also uses definitions when references are selected.
     */
    protected boolean calculateEnabled() {
        List objects = getSelectedObjects();

        if (objects.size() != 1)
            return false;

        SelectionHelper sel = new SelectionHelper(objects);
        urnspec = sel.getUrnspec();
        element = HyperlinkUtils.findURNmodelElement(sel);

        if (element != null) {
            // True when there is a hyperlink metadata for this URN model element
            hyperlink = MetadataHelper.getMetaDataObj(element, HyperlinkUtils.HYPERLINK);
            return (hyperlink != null);
        } else
            return false;
    }

    /**
     * Change the hyperlink metadata
     * 
     */
    protected Command getCommand() {
        Command comm;
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        HyperlinkDialog dialog = new HyperlinkDialog(shell);

        String url = dialog.open(Messages.getString("Hyperlink.WindowModify"), //$NON-NLS-1$ 
                Messages.getString("Hyperlink.TextEnterNew"), //$NON-NLS-1$  
                hyperlink.getValue()); //$NON-NLS-1$ 

        if (url != null && url.length() > 0) {
            comm = new ChangeHyperlinkCommand(urnspec, element, url);
            return comm;
        } else
            return null;
    }

}
