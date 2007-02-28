package seg.jUCMNav.views.wizards.performance;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import seg.jUCMNav.JUCMNavPlugin;
import ucm.map.impl.RespRefImpl;

/**
 * Creating an Active Resouce
 * 
 * @author jack
 */

public class CreateResourceActive extends WizardPage {
	private Shell shell;

	private Composite container;

	private ISelection selection;

	private Label typeLabel;

	private EObject defaultSelected;

	private double opTime;

	private boolean opTimeValid;

	private Text opTimeText;

	/**
	 * The selection contains urn model elements. Loaded in
	 * 
	 * @param selection
	 * @param defaultSelected
	 */
	public CreateResourceActive(ISelection selection, EObject defaultSelected) {
		super("wizardPage"); //$NON-NLS-1$
		this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$
		this.selection = selection;
		this.defaultSelected = defaultSelected;

		setTitle("Create a Resource");
		setDescription("Select the type of resource to be created.");
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

		Label opTimeLabel = new Label(container, SWT.NULL);
		opTimeLabel.setText("opTime");

		opTimeText = new Text(container, SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 1;
		opTimeText.setLayoutData(gd);
		opTimeText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				valueChanged();
			}
		});

		typeLabel = new Label(container, SWT.NONE);
		typeLabel.setText("Active");

		Composite composite = new Composite(container, SWT.NULL);
		composite.setLayout(new RowLayout(SWT.VERTICAL));

		Button btnType = new Button(composite, SWT.RADIO);

		sType = null;

		btnType.setText(CreateResourceQueryType.sActiveProcessing);
		btnType.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (!CreateResourceQueryType.sActiveProcessing.equals(sType)) {
					sType = CreateResourceQueryType.sActiveProcessing;
				}
				dialogChanged();
			}
		});

		btnType = new Button(composite, SWT.RADIO);
		btnType.setText(CreateResourceQueryType.sActiveExternal);
		btnType.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (!CreateResourceQueryType.sActiveExternal.equals(sType)) {
					sType = CreateResourceQueryType.sActiveExternal;
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
		return (sType != null) && opTimeValid;
	}

	/**
	 * 
	 */
	private void dialogChanged() {
		if (sType == null) {
			updateStatus("Please Select The Kind of Active Resource");
		} else if (!opTimeValid) {
			updateStatus("Please Enter a Valid opTime ([0-9]+.[0-9]+)");
		} else {
			setErrorMessage(null);
			setPageComplete(true);
		}
	}

	private void valueChanged() {
		String s;
		if (opTimeText.getText() != "") {
			if (opTimeText.getText().matches("[0-9]+.[0-9]+")) {
				opTime = StringConverter.asDouble(opTimeText.getText().toString());
				opTimeValid = true;
			} else {
				opTimeValid = false;
			}
		}
		dialogChanged();
	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */
	private void initialize() {
		opTimeValid = false;
		if (selection != null && selection.isEmpty() == false && selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			// TODO: anything else? _js_
		}
		if (defaultSelected != null && defaultSelected instanceof RespRefImpl) {
			// TODO: _js_ ???
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

	public double getOpTime() {
		return opTime;
	}
}
