package seg.jUCMNav.views.property.tabbed.sections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import seg.jUCMNav.views.property.tabbed.mapper.ConditionDataResolver;
import seg.jUCMNav.views.property.tabbed.mapper.IPropertyDataResolver;
import seg.jUCMNav.views.wizards.scenarios.CodeEditor;
import urncore.Condition;
import urncore.Responsibility;

public class ConditionExpressionPropertySection extends AbstractGEFPropertySection {
	
	protected EObject current;
	
	protected Text text;
	protected static IPropertyDataResolver resolver = new ConditionDataResolver();

	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		Composite parentComposite = getWidgetFactory().createFlatFormComposite(parent);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		
		parentComposite.setLayout(gridLayout);
	
		CLabel nameLabel = getWidgetFactory().createCLabel(parentComposite,
			getLabelText());
		
		GridData gridData = new GridData();
		gridData.widthHint = getStandardLabelWidth(parentComposite, new String[] { getLabelText() });
		nameLabel.setLayoutData(gridData);
	
		text = createText(parentComposite);
		
		Button b = new Button(parentComposite, SWT.NULL);
		b.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent arg0) {
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				CodeEditor wizard = new CodeEditor();
				
				// initialize it
				wizard.init(PlatformUI.getWorkbench(), null, current);

				// open it.
				WizardDialog dialog = new WizardDialog(shell, wizard);
				dialog.open();
			}
		});
		b.setText("...");
	}

	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		
		current = (EObject)resolver.getData(eObject);
		
		if(!text.isDisposed())
			text.setText(getExpression());
	}

	public void refresh() {
		super.refresh();

		if(!text.isDisposed())
			text.setText(getExpression());
	}
	
	protected String getExpression()
	{
		String result = "";
		if(current instanceof Condition)
			result = ((Condition)current).getExpression();
		else if(current instanceof Responsibility)
			result = ((Responsibility)current).getExpression();
		
		if(result == null)
			result = "";
		
		return result;
	}
	
	protected Text createText(Composite parent)
	{
		Text result = getWidgetFactory().createText(parent, ""); //$NON-NLS-1$
		result.setEnabled(false);

		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.CENTER;
		
		result.setLayoutData(gridData);
		
		return result;
	}

	protected String getLabelText() {
		return "Condition:";
	}
}
