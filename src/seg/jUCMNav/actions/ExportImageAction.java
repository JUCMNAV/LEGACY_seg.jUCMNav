package seg.jUCMNav.actions;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.views.wizards.ExportImageWizard;

/**
 * Created on 2-Jun-2005
 * 
 * Opens the ExportImageWizard with the current selection (map or URNspec)
 * 
 * @author jkealey
 *  
 */
public class ExportImageAction extends SelectionAction {

    public static final String EXPORTBITMAP = "EXPORTBITMAP";

    /**
     * @param part
     */
    public ExportImageAction(IWorkbenchPart part) {
        super(part);
        setId(EXPORTBITMAP);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#calculateEnabled()
     */
    protected boolean calculateEnabled() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        switch (sel.getSelectionType()) {
        case SelectionHelper.MAP:
        case SelectionHelper.URNSPEC:
            return true;
        default:
            return false;

        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    public void run() {
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        ExportImageWizard wiz = new ExportImageWizard();
        StructuredSelection selection = null;
        switch (sel.getSelectionType()) {
        case SelectionHelper.MAP:
            selection = new StructuredSelection(sel.getMap());
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