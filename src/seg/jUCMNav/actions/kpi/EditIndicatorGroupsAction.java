package seg.jUCMNav.actions.kpi;

import grl.IntentionalElementRef;
import grl.kpimodel.Indicator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.views.wizards.kpi.IndicatorGroupDialog;

/**
 * Opens the Indicator Group editor
 * 
 * @author pchen
 */
public class EditIndicatorGroupsAction extends URNSelectionAction {

    public static final String EDITINDICATORGROUPSACTION = "seg.jUCMNav.EditIndicatorGroupsAction"; //$NON-NLS-1$

    private EObject obj;

    /**
     * Opens the Indicator Group editor.
     * 
     * @param part
     *            the UCMNavMultiPageEditor
     */
    public EditIndicatorGroupsAction(IWorkbenchPart part) {
        super(part);
        setId(EDITINDICATORGROUPSACTION);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/indicator.gif")); //$NON-NLS-1$
    }

    /**
     * True if we've selected something with Indicator Group.
     */
    protected boolean calculateEnabled() {
        boolean enable = false;

        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        obj = sel.getURNmodelElement();
        if (obj != null && (obj instanceof IntentionalElementRef && ((IntentionalElementRef) obj).getDef() instanceof Indicator) || (obj instanceof Indicator)) {
            enable = true;
        }

        return enable;
    }

    /**
     * Opens the Metadata editor.
     * 
     * @see seg.jUCMNav.views.wizards.metadata.MetadataEditor
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

        Indicator ind = null;
        if (obj instanceof Indicator) {
            ind = (Indicator) obj;
        } else if (obj instanceof IntentionalElementRef && ((IntentionalElementRef) obj).getDef() instanceof Indicator) {
            ind = (Indicator) ((IntentionalElementRef) obj).getDef();
        }
        IndicatorGroupDialog indDialog = new IndicatorGroupDialog(((UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .getActiveEditor()).getDelegatingCommandStack(), ind);

    }

}
