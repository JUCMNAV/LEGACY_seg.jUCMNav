package seg.jUCMNav.actions.scenarios;

import java.util.Iterator;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.views.wizards.scenarios.AddStartEndPointWizard;
import ucm.map.EndPoint;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.UCMmap;
import urncore.IURNDiagram;

/**
 * Opens the add start/end point wizard
 * 
 * @author jkealey
 */
public class AddStartEndPointAction extends IncludeScenarioAction {

    public static final String ADDSTARTPOINT = "seg.jUCMNav.AddStartPoint"; //$NON-NLS-1$
    public static final String ADDENDPOINT = "seg.jUCMNav.AddEndPoint"; //$NON-NLS-1$
    private boolean bIsAddStartPoint;

    /**
     * @param part
     */
    public AddStartEndPointAction(IWorkbenchPart part, boolean bIsAddStartPoint) {
        super(part);
        this.bIsAddStartPoint = bIsAddStartPoint;

        if (bIsAddStartPoint) {
            setId(ADDSTARTPOINT);
            setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Start16.gif")); //$NON-NLS-1$
        } else {
            setId(ADDENDPOINT);
            setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/End16.gif")); //$NON-NLS-1$
        }

    }

    /**
     * Will be enabled if there are start/end points that can be added to this scenario.
     */
    protected boolean calculateEnabled() {
        initScenario();
        if (scenario != null && scenario.getGroup() != null) {

            for (Iterator iter = scenario.getGroup().getUcmspec().getUrnspec().getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
                IURNDiagram diag = (IURNDiagram) iter.next();
                if (diag instanceof UCMmap) {

                    for (Iterator iterator = ((UCMmap) diag).getNodes().iterator(); iterator.hasNext();) {
                        PathNode node = (PathNode) iterator.next();
                        if ((bIsAddStartPoint && node instanceof StartPoint) || (!bIsAddStartPoint && node instanceof EndPoint))
                            return true;
                    }

                }

            }
        }

        return false;
    }

    /**
     * Opens the {@link AddStartEndPointWizard}
     * 
     */
    public void run() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        AddStartEndPointWizard wizard = new AddStartEndPointWizard(bIsAddStartPoint);

        StructuredSelection selection = new StructuredSelection(scenario);
        wizard.init(PlatformUI.getWorkbench(), selection);
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();

    }

}