package seg.jUCMNav.views.wizards.scenarios;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.scenarios.ScenarioUtils;
import urn.URNspec;

/**
 * New variable name / type selection page.  
 * 
 */
public class AddVariableWizardPage extends WizardPage {
	URNspec urn;
	
	private Text variableName;
	private String sVariableName;
	
	private String sType;

	
	/**
	 * Initializes the wizard page with the URNspec. 
	 * 
	 * @param urn
	 */
	public AddVariableWizardPage(URNspec urn) {
		super("wizardPage"); //$NON-NLS-1$

		this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$

		setTitle("New Variable Wizard");
		setDescription("Please name your variable and select its type");
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
		label.setText("Variable name: ");


		variableName = new Text(container, SWT.BORDER);
		variableName.setText("Variable");

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		variableName.setLayoutData(gd);
		variableName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		label = new Label(container, SWT.NULL);
		label.setText("Variable type: ");
		
		Composite composite = new Composite(container, SWT.NULL);
		composite.setLayout(new RowLayout(SWT.VERTICAL));
		 
		Button btnType = new Button(composite, SWT.RADIO);
		btnType.setText("Boolean");
		btnType.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (!ScenarioUtils.sTypeBoolean.equals(sType)) {
					((AddVariableWizardInitsPage)getWizard().getPages()[2]).setupInitializations();
					sType=ScenarioUtils.sTypeBoolean;
				}
				dialogChanged();
			}
		});

		btnType.setSelection(true);
		sType=ScenarioUtils.sTypeBoolean;
		
		btnType = new Button(composite, SWT.RADIO);
		btnType.setText("Integer");
		btnType.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (!ScenarioUtils.sTypeInteger.equals(sType)) {
					((AddVariableWizardInitsPage)getWizard().getPages()[2]).setupInitializations();

					sType=ScenarioUtils.sTypeInteger;
				}
				dialogChanged();
			}
		});
		
		btnType = new Button(composite, SWT.RADIO);
		btnType.setText("Enumeration");
		// TODO: enable enumerations
		btnType.setEnabled(false);
		btnType.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (!ScenarioUtils.sTypeEnumeration.equals(sType)) {
					((AddVariableWizardInitsPage)getWizard().getPages()[2]).setupInitializations();

					sType=ScenarioUtils.sTypeEnumeration;
				}
				dialogChanged();
			}
		});


		dialogChanged();
		setControl(container);

	}


	/**
	 * Ensures that the selection is legal
	 */
	private void dialogChanged() {
		sVariableName = URNNamingHelper.cleanVariableName(variableName.getText());
		
		
		if (URNNamingHelper.doesVariableNameExist(urn, sVariableName))
			updateStatus("Variable name already in use.");
		else if (sType==null)
			updateStatus("Please select a type.");
		else
			updateStatus(null);

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
	 * Returns the variable name chosen by the user. 
	 * 
	 * @return the variable name. 
	 */
	public String getVariableName() {
		return sVariableName;
	}

	/**
	 * Returns the variable type (constant from ScenarioUtils)
	 * 
	 * @return variable type. 
	 */
	public String getVariableType() {
		return sType;
	}

}
