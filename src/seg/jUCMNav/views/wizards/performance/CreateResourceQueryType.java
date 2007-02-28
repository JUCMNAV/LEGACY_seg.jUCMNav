package seg.jUCMNav.views.wizards.performance;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import seg.jUCMNav.JUCMNavPlugin;
import ucm.map.impl.RespRefImpl;

/**
 * The page actually containing the metadata editor for urn model elements.
 * 
 * @author jack
 */

public class CreateResourceQueryType extends WizardPage {
	private Shell shell;

	private Composite container;

	private ISelection selection;

	private Label typeLabel;

	private EObject defaultSelected;

	/**
	 * The selection contains urn model elements. Loaded in
	 * 
	 * @param selection
	 * @param defaultSelected
	 */
	public CreateResourceQueryType(ISelection selection, EObject defaultSelected) {
		super("wizardPage"); //$NON-NLS-1$
		this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$
		this.selection = selection;
		this.defaultSelected = defaultSelected;

		setTitle("Create a Resource");
		setDescription("Select the type of resource to be created.");

	}

	public static final String sPassive = "passive";

	public static final String sActive = "active";

	public static final String sActiveProcessing = "processing";

	public static final String sActiveExternal = "external";

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
		typeLabel.setText("Type of Resource");

		Composite composite = new Composite(container, SWT.NULL);
		composite.setLayout(new RowLayout(SWT.VERTICAL));

		Button btnType = new Button(composite, SWT.RADIO);
		btnType.setText(sPassive);
		btnType.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (!sPassive.equals(sType)) {
					sType = sPassive;
				}
				dialogChanged();
			}
		});

		sType = null;

		btnType = new Button(composite, SWT.RADIO);
		btnType.setText(sActive);
		btnType.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (!sActive.equals(sType)) {
					sType = sActive;
				}
				dialogChanged();
			}
		});

		initialize();
		setTitle("Resource Type");
		dialogChanged();
		setControl(container);
	}

	/**
	 * Disabling/Enabling the NEXT button by override of canFlipToNextPage. _js_
	 */
	public boolean canFlipToNextPage() {
		return (sType != null) && (sType != sPassive);
	}

	/**
	 * 
	 */
	private void dialogChanged() {
		if (sType == null) {
			updateStatus("Please Select The Type of Resource");
		} else {
			setErrorMessage(null);
			setPageComplete(sType.equals(sPassive));
		}
	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */
	private void initialize() {
		if (selection != null && selection.isEmpty() == false && selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			// _js_ initialize values from selection...

		}
		if (defaultSelected != null && defaultSelected instanceof RespRefImpl) {
			// _js_ ???
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
}
