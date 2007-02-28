package seg.jUCMNav.views.wizards.performance;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import seg.jUCMNav.JUCMNavPlugin;
import ucm.map.ComponentRef;
import urn.URNspec;
import urncore.Component;
import urncore.ComponentElement;

/**
 * Creating a Passive Resource
 * 
 * @author jack
 */
public class CreateResourcePassive extends WizardPage {
	private Shell shell;

	private Composite container;

	private ISelection selection;

	private Label typeLabel;

	private EObject defaultSelected;

	private URNspec spec;

	private Component[] components;

	private Combo allcomponents;

	private IStructuredSelection ssel;

	/**
	 * The selection contains ...
	 * 
	 * @param selection
	 * @param defaultSelected
	 */
	public CreateResourcePassive(ISelection selection, EObject defaultSelected) {
		super("wizardPage"); //$NON-NLS-1$
		this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$
		this.selection = selection;
		this.defaultSelected = defaultSelected;
		// this.allPossibilities = new Vector();

		setTitle("Create a Passive Resource");
		setDescription("Select the component to bind it to.");

	}

	/**
	 * Creates the page.
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);
		shell = container.getShell();

		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		// layout.numColumns = 5;
		layout.numColumns = 1;
		layout.verticalSpacing = 5;

		typeLabel = new Label(container, SWT.NONE);
		typeLabel.setText("Define a resource to be associated to a component");

		allcomponents = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);

		initialize();
		setTitle("Resource Type");
		setControl(container);
	}

	/**
	 * Ensures that the selection is legal
	 */
	private void dialogChanged() {
		// if (invalid choices made and/or values entered)
		// updateStatus("Please Select The Type of Resource");
		// else
		// updateStatus(null);
		// TODO: or check: _js_
	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */
	private void initialize() {
		if (selection != null && selection.isEmpty() == false && selection instanceof IStructuredSelection) {
			ssel = (IStructuredSelection) selection;
			// _js_ initialize values from selection...
			boolean found = false;

			EObject[] eobjs = new EObject[ssel.size()];
			Iterator iter = ssel.iterator();
			int index = 0;
			while (iter.hasNext()) {
				EObject element = (EObject) iter.next();
				eobjs[index++] = element;

				if (element == defaultSelected) {
					found = true;
				}
			}

			// ignore it if it wasn't in the list.
			if (!found) {
				defaultSelected = null;
			}

		}
		if (selection == null) {
			if (defaultSelected != null) {
				if (defaultSelected instanceof ComponentRef) {
					spec = ((ComponentRef) defaultSelected).getDiagram().getUrndefinition().getUrnspec();
				} else if (defaultSelected instanceof ComponentElement) {
					spec = ((ComponentElement) defaultSelected).getUrndefinition().getUrnspec();
				}

			}

		}
		if (spec != null) {
			components = new Component[spec.getUrndef().getComponents().size()];
		}
		int i = 0;
		for (Iterator component = spec.getUrndef().getComponents().iterator(); component.hasNext();) {
			components[i] = (Component) component.next();
			allcomponents.add(components[i].getName() + "(" + components[i].getId() + ")");
			i++;
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

}
