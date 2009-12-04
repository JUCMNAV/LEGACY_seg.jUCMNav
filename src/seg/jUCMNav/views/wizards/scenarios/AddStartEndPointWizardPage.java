package seg.jUCMNav.views.wizards.scenarios;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.URNNamingHelper;
import ucm.map.EndPoint;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.UCMmap;
import ucm.scenario.ScenarioDef;
import urncore.IURNDiagram;

/**
 * The page actually containing the code for adding a start/end point into a scenario.
 * 
 * Not robust enough to support loading when none are available.
 * 
 */
public class AddStartEndPointWizardPage extends WizardPage {
    private ISelection selection;
    private ScenarioDef parent;
    private Vector children;
    private boolean bStartPoint;
    private List points;

    /**
     * The selection contains a scenario definition to wihch we want to add a start/end point. Loaded in {@link #initialize()}.
     * 
     * @param selection
     */
    public AddStartEndPointWizardPage(ISelection selection, boolean bIsStartPoint) {
        super("wizardPage"); //$NON-NLS-1$

        this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$
        this.bStartPoint = bIsStartPoint;

        if (bIsStartPoint) {
            setTitle(Messages.getString("AddStartEndPointWizardPage.AddStartToScenario")); //$NON-NLS-1$
            setDescription(Messages.getString("AddStartEndPointWizardPage.ChooseStartPoints")); //$NON-NLS-1$
        } else {
            setTitle(Messages.getString("AddStartEndPointWizardPage.AddEndPoints")); //$NON-NLS-1$
            setDescription(Messages.getString("AddStartEndPointWizardPage.ChooseEndPoints")); //$NON-NLS-1$
        }

        // loaded in initialize()
        this.selection = selection;
    }

    /**
     * Creates the page.
     */
    public void createControl(Composite parent) {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "seg.jUCMNav.scenario_addstartend"); //$NON-NLS-1$
        children = new Vector();
        Composite container = new Composite(parent, SWT.NULL);

        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 1;
        layout.verticalSpacing = 5;

        // label over the dropdown list box.
        Label label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("AddStartEndPointWizardPage.ChooseScenarioToBeIncluded")); //$NON-NLS-1$

        initialize();

        points = new List(container, SWT.MULTI | SWT.BORDER | SWT.SCROLL_LINE);
        points.setItems(getPossibleChildren());

        GridData gd = new GridData(GridData.FILL_BOTH);
        points.setLayoutData(gd);
        points.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                dialogChanged();
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                // double-click does nothing.

            }
        });

        dialogChanged();
        setControl(container);

    }

    /**
     * The possible start/end points as a string array.
     */
    private String[] getPossibleChildren() {
        ArrayList points = getNodes();

        ArrayList pointStrings = new ArrayList();
        for (Iterator iter = points.iterator(); iter.hasNext();) {
            PathNode node = (PathNode) iter.next();
            pointStrings.add(URNNamingHelper.getName((UCMmap) node.getDiagram()) + ": " + URNNamingHelper.getName(node)); //$NON-NLS-1$

        }
        Object[] o = pointStrings.toArray();
        String[] strings = new String[o.length];
        for (int i = 0; i < o.length; i++) {
            strings[i] = o[i].toString();
        }
        return strings;
    }

    /**
     * 
     * 
     * @return The possible start/end points as an arraylist of PathNodes.
     */
    private ArrayList getNodes() {
        ArrayList points = new ArrayList();
        for (Iterator iter = parent.getGroup().getUcmspec().getUrnspec().getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
            IURNDiagram element = (IURNDiagram) iter.next();
            if (element instanceof UCMmap) {
                for (Iterator iterator = ((UCMmap) element).getNodes().iterator(); iterator.hasNext();) {
                    PathNode node = (PathNode) iterator.next();
                    if ((node instanceof StartPoint && bStartPoint) || (node instanceof EndPoint && !bStartPoint))
                        points.add(node);
                }
            }

        }
        return points;
    }

    /**
     * Tests if the current workbench selection is a suitable container to use.
     */
    private void initialize() {
        if (selection != null && selection.isEmpty() == false && selection instanceof IStructuredSelection) {
            IStructuredSelection ssel = (IStructuredSelection) selection;
            if (ssel.size() > 1)
                return;
            Object obj = ssel.getFirstElement();
            if (obj instanceof ScenarioDef) {
                parent = (ScenarioDef) obj;
            }
        }
    }

    /**
     * Ensures that the selection is legal
     */
    private void dialogChanged() {

        if (points.getSelectionIndex() < 0)
            updateStatus(Messages.getString("AddStartEndPointWizardPage.SelectPoint")); //$NON-NLS-1$
        else {
            children.clear();
            for (int i = 0; i < points.getSelectionIndices().length; i++) {
                int index = points.getSelectionIndices()[i];
                children.add(getNodes().get(index));
            }
            // child = (PathNode) getNodes().get(points.getSelectionIndex());
            updateStatus(null);
        }
    }

    /**
     * Updates the status of the window
     * 
     * @param message
     *            the error message or null if no error message.
     */
    private void updateStatus(String message) {
        setErrorMessage(message);
        setPageComplete(message == null);

    }

    /**
     * Returns the child start/end point that has been selected.
     * 
     * @return the child
     */
    public Vector getSelectedNodes() {
        return children;
    }

    /**
     * Returns the parent scenario that was initially given
     * 
     * @return the child
     */
    public ScenarioDef getParentScenario() {
        return parent;
    }

}
