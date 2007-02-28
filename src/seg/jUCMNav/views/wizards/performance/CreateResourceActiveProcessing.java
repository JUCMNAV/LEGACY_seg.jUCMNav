package seg.jUCMNav.views.wizards.performance;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import seg.jUCMNav.JUCMNavPlugin;
import ucm.map.ComponentRef;
import ucm.performance.DeviceKind;
import urn.URNspec;
import urncore.ComponentElement;

/**
 * The page actually containing the metadata editor for urn model elements.
 * 
 * @author jack
 */
public class CreateResourceActiveProcessing extends WizardPage {
	private Shell shell;

	private Composite container;

	private ISelection selection;

	private URNspec spec;

	private Label typeLabel;

	private Combo allDeviceKindsNames;

	private DeviceKind[] deviceKinds;

	private DeviceKind selectedDeviceKind;

	private EObject defaultSelected;

	/**
	 * The selection contains urn model elements. Loaded in
	 * 
	 * @param selection
	 * @param defaultSelected
	 */
	public CreateResourceActiveProcessing(ISelection selection, EObject defaultSelected) {
		super("wizardPage"); //$NON-NLS-1$
		this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$
		this.selection = selection;
		this.defaultSelected = defaultSelected;

		setTitle("Create an Active Processing Resource");
		setDescription("Select the Processing Resource kind.");
	}

	private String sType = null;

	/**
	 * Creates the page.
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);
		shell = container.getShell();

		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		// layout.numColumns = 5;
		layout.numColumns = 2;
		layout.verticalSpacing = 5;

		typeLabel = new Label(container, SWT.NONE);
		typeLabel.setText("Active Processing Kind");

		allDeviceKindsNames = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		allDeviceKindsNames.addSelectionListener(allDeviceKindsNamesSelectionListener);

		initialize();
		setTitle("Active Processing spec");
		// dialogChanged();
		setControl(container);
	}

	private SelectionListener allDeviceKindsNamesSelectionListener = new SelectionAdapter() {
		public void widgetDefaultSelected(SelectionEvent e) {
			// double-click???
			dialogChanged();
		}

		public void widgetSelected(SelectionEvent e) {
			// single-click???
			dialogChanged();
		}
	};

	/**
	 * Disabling/Enabling the NEXT button by override of canFlipToNextPage. _js_
	 */
	public boolean canFlipToNextPage() {
		return (sType != null);
	}

	/**
	 * 
	 */
	private void dialogChanged() {
		int i = 0;
		if (allDeviceKindsNames.getSelection() == null) {
			updateStatus("Please Select The Kind of Active Resource");
		} else {
			i = allDeviceKindsNames.getSelectionIndex();
			selectedDeviceKind = deviceKinds[i];
			setErrorMessage(null);
			setPageComplete(true);
		}
	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */
	private void initialize() {
		selectedDeviceKind = null;
		if (defaultSelected != null) {
			if (defaultSelected instanceof ComponentRef) {
				spec = ((ComponentRef) defaultSelected).getDiagram().getUrndefinition().getUrnspec();
			} else if (defaultSelected instanceof ComponentElement) {
				spec = ((ComponentElement) defaultSelected).getUrndefinition().getUrnspec();
			}
		}
		// prepare a list of available DeviceKind values
		deviceKinds = new DeviceKind[DeviceKind.VALUES.size()];
		int i = 0;
		for (Iterator kind = DeviceKind.VALUES.iterator(); kind.hasNext();) {
			deviceKinds[i] = (DeviceKind) kind.next();
			allDeviceKindsNames.add(((DeviceKind) deviceKinds[i]).getLiteral());
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

	public String getType() {
		return sType;
	}

	public DeviceKind getDeviceKind() {
		return selectedDeviceKind;
	}
}
