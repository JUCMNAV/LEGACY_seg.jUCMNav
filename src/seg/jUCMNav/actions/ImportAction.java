package seg.jUCMNav.actions;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.views.wizards.importexport.ImportWizard;
import urn.URNspec;

/**
 * Opens the {@link ImportWizard} with the current selection (diagram or {@link URNspec})
 * 
 * @author Jean-Francois Roy
 * 
 */
public class ImportAction extends URNSelectionAction {

    public static final String IMPORT = "seg.jUCMNav.IMPORT"; //$NON-NLS-1$

    /**
     * @param part
     */
    public ImportAction(IWorkbenchPart part) {
        super(part);
        setId(IMPORT);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/import.gif")); //$NON-NLS-1$
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
     * Launches the {@link ImportWizard}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {
        // using the selection helper will filter out the selection
        // if many maps are selected in the outline, for example.
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        ImportWizard wiz = new ImportWizard();
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