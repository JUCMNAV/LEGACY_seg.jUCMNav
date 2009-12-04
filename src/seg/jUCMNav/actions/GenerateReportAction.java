package seg.jUCMNav.actions;

import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.views.wizards.importexport.ReportWizard;
import urn.URNspec;

/**
 * Opens the {@link ReportWizard} with the current selection (diagram or {@link URNspec})
 * 
 * Could allow exporting when anything in a map is selected, not just the background.
 * 
 * @author damyot
 * 
 */
public class GenerateReportAction extends URNSelectionAction {

    public static final String GENERATEREPORT = "seg.jUCMNav.GENERATEREPORT"; //$NON-NLS-1$

    /**
     * @param part
     */
    public GenerateReportAction(IWorkbenchPart part) {
        super(part);
        setId(GENERATEREPORT);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/report16.gif")); //$NON-NLS-1$
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
     * Opens the {@link ReportWizard}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {
        // using the selection helper will filter out the selection
        // if many maps are selected in the outline, for example.
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        ReportWizard wiz = new ReportWizard();
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