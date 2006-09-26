package seg.jUCMNav.views.wizards.scenarios;

import java.util.ArrayList;
import java.util.Iterator;

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

import seg.jUCMNav.JUCMNavPlugin;
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
	private PathNode child;
	private boolean bStartPoint;
	private List points;

	/**
	 * The selection contains a scenario definition to wihch we want to add a start/end point. Loaded in
	 * {@link #initialize()}.
	 * 
	 * @param selection
	 */
	public AddStartEndPointWizardPage(ISelection selection, boolean bIsStartPoint) {
		super("wizardPage"); //$NON-NLS-1$

		this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$
		this.bStartPoint = bIsStartPoint;

		if (bIsStartPoint) {
			setTitle("Add Start Point to Scenario");
			setDescription("Please choose the start point to be added.");
		} else {
			setTitle("Add End Point to Scenario");
			setDescription("Please choose the end point to be added.");
		}

		// loaded in initialize()
		this.selection = selection;
	}

	/**
	 * Creates the page.
	 */
	public void createControl(Composite parent) {

		Composite container = new Composite(parent, SWT.NULL);

		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 1;
		layout.verticalSpacing = 5;

		// label over the dropdown list box.
		Label label = new Label(container, SWT.NULL);
		label.setText("Please choose the scenario to be included.");

		initialize();

		points = new List(container, SWT.SINGLE | SWT.BORDER | SWT.SCROLL_LINE);
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
			pointStrings.add(URNNamingHelper.getName((UCMmap)node.getDiagram()) + "\\" + URNNamingHelper.getName(node));
			
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
			updateStatus("Please select a point");
		else {
			child = (PathNode) getNodes().get(points.getSelectionIndex());
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
	public PathNode getSelectedNode() {
		return child;
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
