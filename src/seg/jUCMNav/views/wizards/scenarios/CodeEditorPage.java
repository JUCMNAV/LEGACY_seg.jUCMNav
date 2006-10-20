package seg.jUCMNav.views.wizards.scenarios;

import java.util.Collections;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.scenarios.parser.SimpleNode;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import ucm.scenario.Variable;
import urn.URNspec;
import urncore.Condition;
import urncore.Responsibility;

/**
 * The page actually containing the code editor for responsibilities and
 * conditions.
 * 
 */
public class CodeEditorPage extends WizardPage {
	private Text codeText;
	private ISelection selection;
	private Responsibility resp;
	private Condition cond;
	private URNspec urn;
	private List variables;

	/**
	 * The selection contains either a responsibility or a condition. Loaded in
	 * {@link #initialize()}.
	 * 
	 * @param selection
	 */
	public CodeEditorPage(ISelection selection) {
		super("wizardPage"); //$NON-NLS-1$

		this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$

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
		layout.numColumns = 2;
		layout.verticalSpacing = 5;

		// label over the code box.
		Label label = new Label(container, SWT.NULL);
		label.setText(Messages.getString("CodeEditorPage.EnterTheCode")); //$NON-NLS-1$

		
		// variable list
		variables = new List(container, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		variables.addSelectionListener(new SelectionListener() {
		
			public void widgetSelected(SelectionEvent e) {
				// single click. 
		
			}
		
			public void widgetDefaultSelected(SelectionEvent e) {
				// double click. 
				
				if (variables.getSelection().length>0) {
					codeText.insert(variables.getSelection()[0]);
					codeText.forceFocus();
				}
		
			}
		
		});
		
		GridData gd = new GridData(GridData.FILL_VERTICAL);
	
		gd.verticalSpan=3;
		gd.widthHint=100;
		gd.heightHint=200;
		variables.setLayoutData(gd);

		
		
		// simple multi-line scrollable text-box that grows with box.
		codeText = new Text(container, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);

		 gd = new GridData(GridData.FILL_BOTH);
		codeText.setLayoutData(gd);
		codeText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});


		// Button to open the new variable wizard.
		Button button = new Button(container, SWT.PUSH);
		button.setText(Messages.getString("CodeEditorPage.CreateNewVariable")); //$NON-NLS-1$
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
	    		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
	    		AddVariableWizard wizard = new AddVariableWizard();


	    		if (urn!=null) {
		    		wizard.init(PlatformUI.getWorkbench(), new StructuredSelection(urn));
		    		WizardDialog dialog = new WizardDialog(shell, wizard);
		    		dialog.open();
		    		initVariables();
		    		dialogChanged();
		    		
	    		}
			}
		});

		// 
		initialize();
		if (isResponsibility()) {
			setTitle(Messages.getString("CodeEditorPage.ResponsibilityEditor")); //$NON-NLS-1$
			setDescription(Messages.getString("CodeEditorPage.PleaseEnterResponsibility")); //$NON-NLS-1$
		} else {
			setDescription(Messages.getString("CodeEditorPage.PleaseEnterCondition")); //$NON-NLS-1$
			setTitle(Messages.getString("CodeEditorPage.ConditionEditor")); //$NON-NLS-1$
		}

		dialogChanged();
		setControl(container);

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
			if (obj instanceof Responsibility) {
				resp = (Responsibility) obj;
				if (resp.getExpression() == null)
					codeText.setText(""); //$NON-NLS-1$
				else
					codeText.setText(resp.getExpression());
			} else if (obj instanceof Condition) {
				cond = (Condition) obj;
				if (cond.getExpression() == null)
					codeText.setText("true"); //$NON-NLS-1$
				else
					codeText.setText(cond.getExpression());
			}
		}
		
		
		EObject o;
		if (resp!=null)
			urn = resp.getUrndefinition().getUrnspec();
		else {
			o = cond.eContainer();
			while (o!=null) {
				if (o instanceof URNspec) {
					urn = (URNspec)o;
				}
				o = o.eContainer();
			}
			
		}
		
		initVariables();
	}

	private void initVariables() 
	{
		Vector v = new Vector();

		for (int i=0;i<urn.getUcmspec().getVariables().size();i++) {
			v.add(((Variable)urn.getUcmspec().getVariables().get(i)).getName());
		}
		
		Collections.sort(v);
		String[] vars = new String[v.size()];

		for (int i=0;i<v.size();i++) {
			vars[i]=v.get(i).toString();
		}
		variables.setItems(vars);
		

	}
	/**
	 * Ensures that the pseudo-code is legal (syntax and type checking)
	 */
	private void dialogChanged() {
		if (getCode() == null || getCode().length() == 0)
			updateStatus(null);
		else {
			Object o;
			
			if (isResponsibility())
				o = ScenarioUtils.parse(getCode(), ScenarioUtils.getEnvironment(resp), true);
			else
				o = ScenarioUtils.parse(getCode(), ScenarioUtils.getEnvironment(cond), false);
			
			if (o instanceof SimpleNode)
				updateStatus(null);
			else
				updateStatus((String) o);
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
		
		if (GeneralPreferencePage.getStrictCodeEditor())
			setPageComplete(message == null);
		else
			setPageComplete(true);
			
	}

	/**
	 * Returns the responsibility for which the pseudo-code is being edited.
	 * 
	 * @return the responsibility being edited.
	 */
	public Responsibility getResponsibility() {
		return resp;
	}

	/**
	 * Returns the condition for which the pseudo-code is being edited.
	 * 
	 * @return the condition being edited.
	 */
	public Condition getCondition() {
		return cond;
	}

	/**
	 * Are we editing a responsibility or a condition?
	 * 
	 * @return true if responsibility, false otherwise.
	 */
	public boolean isResponsibility() {
		return resp != null;
	}

	/**
	 * The pseudo-code entered in the text box.
	 * 
	 * @return the pseudo-code
	 */
	public String getCode() {
		return codeText.getText();
	}
}
