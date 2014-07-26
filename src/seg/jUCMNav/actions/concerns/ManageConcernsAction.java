package seg.jUCMNav.actions.concerns;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.actions.SelectionHelper;
import seg.jUCMNav.actions.URNSelectionAction;
import seg.jUCMNav.views.preferences.DisplayPreferences;
import seg.jUCMNav.views.wizards.concerns.ConcernsManager;
import urn.URNspec;
import urncore.URNmodelElement;

/**
 * Action related to managing concerns (adding, deleting, and assignment to a diagram)
 * 
 * @author gunterm
 * 
 */
public class ManageConcernsAction extends URNSelectionAction {

    public static final String MANAGECONCERNS = "seg.jUCMNav.actions.concerns.ManageConcernsAction"; //$NON-NLS-1$
    private URNmodelElement element;
    private URNspec urn;

    /**
     * @param part
     *            the workbench part we are working with
     */
    public ManageConcernsAction(IWorkbenchPart part) {
        super(part);
        setId(MANAGECONCERNS);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Concern16.gif")); //$NON-NLS-1$
    }

    /**
     * Returns true if a map, a grl graph, or a concern in selected; false otherwise
     * 
     * @see seg.jUCMNav.actions.URNSelectionAction#calculateEnabled()
     */
    protected boolean calculateEnabled() {
        if(!DisplayPreferences.getInstance().isAdvancedControlEnabled() || !DisplayPreferences.getInstance().isShowAspect())
            return false;
        
        SelectionHelper sel = new SelectionHelper(getSelectedObjects());
        element = sel.getURNmodelElement();
        switch (sel.getSelectionType()) {
        case SelectionHelper.URNSPEC:
        case SelectionHelper.MAP:
        case SelectionHelper.GRLGRAPH:
        case SelectionHelper.CONCERN:
            urn = sel.getUrnspec();
            return true;
        default:
            return false;
        }
    }

    /**
     * starts the Concern Manager wizard
     * 
     * @see seg.jUCMNav.actions.URNSelectionAction#run()
     */
    public void run() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        ConcernsManager wizard = new ConcernsManager();
        wizard.initialize(PlatformUI.getWorkbench(), urn, element);
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();
    }

    
}