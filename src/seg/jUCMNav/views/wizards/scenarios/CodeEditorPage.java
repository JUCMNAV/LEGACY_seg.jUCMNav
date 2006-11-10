package seg.jUCMNav.views.wizards.scenarios;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.scenarios.parser.SimpleNode;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import ucm.scenario.Variable;
import urn.URNspec;
import urncore.Condition;
import urncore.Responsibility;
import urncore.URNmodelElement;

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
	private Combo possibilities;
	
	private Vector allPossibilities;
	private EObject defaultSelected;
	private HashMap code;

	/**
	 * The selection contains either a responsibility or a condition. Loaded in
	 * {@link #initialize()}.
	 * 
	 * @param selection
	 */
	public CodeEditorPage(ISelection selection, EObject defaultSelected) {
		super("wizardPage"); //$NON-NLS-1$

		this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$

		// loaded in initialize()
		this.selection = selection;
		this.defaultSelected = defaultSelected;
		this.code = new HashMap();
		this.allPossibilities = new Vector();
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

		
		possibilities = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);

		
		possibilities.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				// single click. 
		
				if (possibilities.getSelectionIndex()>=0) {
					EObject o = (EObject) allPossibilities.get(possibilities.getSelectionIndex());
					if (o!=defaultSelected) {
						defaultSelected=o;
						setupText(o);

						
						if (defaultSelected instanceof Responsibility)
							resp = (Responsibility) defaultSelected;
						else
							cond = (Condition) defaultSelected;						
					}
				}
			}
		
			public void widgetDefaultSelected(SelectionEvent e) {
				// double click. 
	
			}
		
		});
		
		
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan=2;
		possibilities.setLayoutData(gd);
		
		// label over the code box.
		Label label = new Label(container, SWT.NULL);
		label.setText(Messages.getString("CodeEditorPage.EnterTheCode")); //$NON-NLS-1$

		// label over the variable box
		label = new Label(container, SWT.NULL);
		label.setText(Messages.getString("CodeEditorPage.DoubleClickOnVariable")); //$NON-NLS-1$
		

		
		
		// simple multi-line scrollable text-box that grows with box.
		codeText = new Text(container, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);

		gd = new GridData(GridData.FILL_BOTH);
		codeText.setLayoutData(gd);
		codeText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
		
		
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
		
		gd = new GridData(GridData.FILL_VERTICAL);
	
		gd.verticalSpan=2;
		gd.widthHint=100;
		gd.heightHint=200;
		variables.setLayoutData(gd);
		


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
		
		
		refreshPossibilityLabels();
		
		possibilities.select(allPossibilities.indexOf(defaultSelected));
		
		if (isResponsibility()) {
			setTitle(Messages.getString("CodeEditorPage.ResponsibilityEditor")); //$NON-NLS-1$
			setDescription(Messages.getString("CodeEditorPage.PleaseEnterResponsibility")); //$NON-NLS-1$
		} else {
			setDescription(Messages.getString("CodeEditorPage.PleaseEnterCondition")); //$NON-NLS-1$
			setTitle(Messages.getString("CodeEditorPage.ConditionEditor")); //$NON-NLS-1$
		}

		dialogChanged();
		setControl(container);
		codeText.forceFocus();

	}

	private void refreshPossibilityLabels() {
		boolean add=possibilities.getItemCount()==0;
		
		for (int i=0;i<allPossibilities.size();i++) { 
			EObject element = (EObject) allPossibilities.get(i);
			
			if (element instanceof URNmodelElement)
			{
				if (add)
					possibilities.add(URNNamingHelper.getName((URNmodelElement)element));
				else {
					possibilities.setItem(i, URNNamingHelper.getName((URNmodelElement)element));
				}
					
			} else if (element instanceof urncore.Condition)
			{
				if (add)
					possibilities.add(URNNamingHelper.getName((urncore.Condition)element));
				else {
					possibilities.setItem(i,URNNamingHelper.getName((urncore.Condition)element, code.get(element).toString()));
				}
					
			}
			
}
	}

	/**
	 * Tests if the current workbench selection is a suitable container to use.
	 */
	private void initialize() {
		if (selection != null && selection.isEmpty() == false && selection instanceof IStructuredSelection) {
			IStructuredSelection ssel = (IStructuredSelection) selection;
			
			
			initPossibilities(ssel);
			
			if (defaultSelected==null) 
				defaultSelected = (EObject) ssel.getFirstElement();
			
			setupText(defaultSelected);
		}
		
		if (defaultSelected instanceof Responsibility)
			resp = (Responsibility) defaultSelected;
		else
			cond = (Condition) defaultSelected;
		
		// assuming same of all items in list. 
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

	private void setupText(Object obj) {
		if (obj instanceof Responsibility) {
			resp = (Responsibility) obj;
			if (code.get(resp) == null)
				codeText.setText(""); //$NON-NLS-1$
			else
				codeText.setText(code.get(resp).toString());
		} else if (obj instanceof Condition) {
			cond = (Condition) obj;
			if (code.get(cond)== null)
				codeText.setText("true"); //$NON-NLS-1$
			else
				codeText.setText(code.get(cond).toString());
		}
	}

	private void initPossibilities(IStructuredSelection ssel) {
		boolean found = false;
		for (Iterator iter = ssel.iterator(); iter.hasNext();) {
			EObject element = (EObject) iter.next();
			allPossibilities.add(element);

			if (element == defaultSelected)
				found=true;
			
			if (element instanceof Responsibility) {
				Responsibility r = (Responsibility) element;
				if (r.getExpression() == null)
					code.put(element, ""); //$NON-NLS-1$
				else
					code.put(element, r.getExpression());
			} else if (element instanceof Condition) {
				Condition c = (Condition) element;
				if (c.getExpression() == null)
					code.put(element, "true"); //$NON-NLS-1$
				else
					code.put(element, c.getExpression());
			}
			
		}
		
		// ignore it if it wasn't in the list. 
		if (!found)defaultSelected=null;
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
		if (resp!=null && ScenarioUtils.isEmptyResponsibility(getCode())) {
			code.put(defaultSelected, ""); //$NON-NLS-1$
			updateStatus(null);
		}
		else {
			Object o;
			
			if (isResponsibility())
				o = ScenarioUtils.parse(getCode(), ScenarioUtils.getEnvironment(resp), true);
			else
				o = ScenarioUtils.parse(getCode(), ScenarioUtils.getEnvironment(cond), false);
			
			if (o instanceof SimpleNode) {
				code.put(defaultSelected, getCode());
				updateStatus(null);
			}
			else {
				if (!GeneralPreferencePage.getStrictCodeEditor())
				{
					code.put(defaultSelected, getCode());
				}
				updateStatus((String) o);
			}
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
		
		refreshPossibilityLabels();
		possibilities.setEnabled(isPageComplete());
			
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
	
	/**
	 * Code for all objects that were passed. Assumed to be always valid.  
	 * 
	 * @return
	 */
	public HashMap getAllCode() {
		return code;
	}
}
