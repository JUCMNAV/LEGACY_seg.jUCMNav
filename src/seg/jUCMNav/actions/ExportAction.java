package seg.jUCMNav.actions;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.views.wizards.importexport.ExportWizard;
import urn.URNspec;

/**
 * Opens the {@link ExportWizard} with the current selection (diagram or {@link URNspec})
 * 
 * Could allow exporting when anything in a map is selected, not just the background.
 * 
 * @author jkealey
 * 
 */
public class ExportAction extends URNSelectionAction {

    public static final String EXPORT = "seg.jUCMNav.EXPORT"; //$NON-NLS-1$

    /**
     * @param part
     */
    public ExportAction(IWorkbenchPart part) {
        super(part);
        setId(EXPORT);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/export.gif")); //$NON-NLS-1$
    }

    /**
     * True if we've selected a Map or URNSpec
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.MAP:
        case SelectionHelper.GRLGRAPH:
        case SelectionHelper.URNSPEC:
            return true;
        default:
            return false;

        }
    }

    /**
     * Opens the {@link ExportWizard}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {
        // using the selection helper will filter out the selection
        // if many maps are selected in the outline, for example.
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        ExportWizard wiz = new ExportWizard();
        StructuredSelection selection = null;
        switch (sel.getSelectionType()) {
        case SelectionHelper.MAP:
            selection = new StructuredSelection(sel.getMap());
            break;
        case SelectionHelper.GRLGRAPH:
            selection = new StructuredSelection(sel.getGrlgraph());
            break;
        case SelectionHelper.URNSPEC:
            selection = new StructuredSelection(sel.getUrnspec());
            break;
        }
        wiz.init(getWorkbenchPart().getSite().getWorkbenchWindow().getWorkbench(), selection);
        WizardDialog wd = new WizardDialog(getWorkbenchPart().getSite().getShell(), wiz);
        wd.open();

    }
}