package seg.jUCMNav.views.wizards.performance;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import seg.jUCMNav.JUCMNavPlugin;

/**
 * @author jack
 */

public class CreateResourceActiveExternal extends WizardPage {
	private Shell shell;

	private Composite container;

	private ISelection selection;

	private EObject defaultSelected;

	private Text descriptionText;

	private String descrip;

	/**
	 * The selection contains urn model elements. Loaded in
	 * 
	 * @param selection
	 * @param defaultSelected
	 */
	public CreateResourceActiveExternal(ISelection selection, EObject defaultSelected) {
		super("wizardPage"); //$NON-NLS-1$
		this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$
		this.selection = selection;
		this.defaultSelected = defaultSelected;
		this.descrip = null;
		// setTitle("Active Resource : External Operation");
		// setDescription("Specify the External Operation to be created.");
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
		layout.numColumns = 2;
		layout.verticalSpacing = 5;

		Label descriptionLabel = new Label(container, SWT.NULL);
		descriptionLabel.setText("Description");

		descriptionText = new Text(container, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 1;
		descriptionText.setLayoutData(gd);
		descriptionText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				valueChanged();
			}
		});

		initialize();
		// setTitle("External Operation");
		setTitle("Active Resource : External Operation");
		setDescription("Specify the External Operation to be created.");

		// dialogChanged();
		setControl(container);
	}

	/**
	 * Disabling/Enabling the NEXT button by override of canFlipToNextPage. _js_
	 */
	/*
	 * public boolean canFlipToNextPage() { return false; }
	 */
	/**
	 * 
	 */
	private void dialogChanged() {
		String msg = null;
		if (descriptionText.getText().toString().length() == 0) {
			msg = "Please Enter a Description";
			descrip = null;
		} else {
			if ((descrip.length() == 0) && (descriptionText.getText().toString().length() != 0)) {
				msg = null;
			}
			descrip = descriptionText.getText().toString();
		}
		updateStatus(msg);
	}

	private void valueChanged() {
		// descrip = descriptionText.getText().toString();
		dialogChanged();
	}

	/**
	 * 
	 */
	private void initialize() {
		descrip = null;
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

	public String getDescription() {
		return descrip;
	}

}
