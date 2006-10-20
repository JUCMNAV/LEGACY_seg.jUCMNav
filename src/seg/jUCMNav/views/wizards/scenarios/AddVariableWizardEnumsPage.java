package seg.jUCMNav.views.wizards.scenarios;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import seg.jUCMNav.JUCMNavPlugin;
import urn.URNspec;

/**
 * The page to select an enumeration, create new ones, modify their values.  
 * 
 */
public class AddVariableWizardEnumsPage extends WizardPage {
	private URNspec urn;
	
	
	/**
	 * Initializes the wizard page with the URNspec. 
	 * 
	 * @param urn
	 */
	public AddVariableWizardEnumsPage(URNspec urn) {
		super("wizardPage"); //$NON-NLS-1$

		this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$

		setTitle("New Variable Wizard");
		setDescription("Choose an enumeration");
		this.urn=urn;
	}

	/**
	 * Creates the page.
	 */
	public void createControl(Composite parent) {

		Composite container = new Composite(parent, SWT.NULL);

		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		layout.verticalSpacing = 5;

		Label label = new Label(container, SWT.NULL);
		label.setText("Enumeration name: ");


		// TODO: Create enumeration radio button list.
		// TODO: Create new enumeration button
		// TODO: Modify enumeration values button. 
		 

		dialogChanged();
		setControl(container);

	}


	/**
	 * Ensures that the selection is legal
	 */
	private void dialogChanged() {
		// TODO: verify validity of selection. 
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
