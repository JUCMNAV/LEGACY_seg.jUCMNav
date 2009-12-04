package seg.jUCMNav.actions.hyperlinks;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.model.commands.metadata.ChangeHyperlinkCommand;
import seg.jUCMNav.model.util.MetadataHelper;
import urn.URNspec;
import urncore.Metadata;
import urncore.URNmodelElement;

/**
 * Action for deleting a hyperlink
 * 
 * @author damyot
 * 
 */
public class DeleteHyperlinkAction extends URNSelectionAction {

    public static final String DELETEHYPERLINK = "seg.jUCMNav.DeleteHyperlinkAction"; //$NON-NLS-1$

    private URNmodelElement element;
    private Metadata hyperlink;
    private URNspec urnspec;

    public DeleteHyperlinkAction(IWorkbenchPart part) {
        super(part);
        setId(DELETEHYPERLINK);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/delete16.gif")); //$NON-NLS-1$
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
     * Delete the hyperlink metadata
     * 
     */
    protected Command getCommand() {
        Command comm;
        comm = new ChangeHyperlinkCommand(urnspec, element, null);
        return comm;
    }

}
