package seg.jUCMNav.views.property.tabbed.sections;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.Messages;
import seg.jUCMNav.views.wizards.scenarios.EditEnumerationsWizard;
import ucm.scenario.EnumerationType;
import ucm.scenario.ScenarioPackage;

public class EnumerationValuesPropertySection extends AbstractDialogPropertySection {

    protected EAttribute getFeature() {
        return ScenarioPackage.eINSTANCE.getEnumerationType_Values();
    }

    public String getLabelText() {
        return Messages.getString("EnumerationValuesPropertySection_Vals"); //$NON-NLS-1$
    }

    protected Text createText(Composite composite) {
        Text result = getWidgetFactory().createText(composite, "", SWT.MULTI | SWT.WRAP); //$NON-NLS-1$

        result.setEnabled(false);

        GridData gridData = new GridData();
        gridData.heightHint = 75;
        gridData.grabExcessHorizontalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;
        gridData.verticalAlignment = GridData.CENTER;
        gridData.horizontalIndent = 2;

        result.setLayoutData(gridData);

        return result;
    }

    protected String getText() {
        String values = ((EnumerationType) eObject).getValues();

        if (values != null)
            values = values.replace(",", System.getProperty("line.separator", "\n")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        return values;
    }

    protected void openDialog() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

        Wizard _wizard;

        _wizard = new EditEnumerationsWizard();
        ((EditEnumerationsWizard) _wizard).init(PlatformUI.getWorkbench(), (StructuredSelection) selection);

        WizardDialog dialog = new WizardDialog(shell, _wizard);
        dialog.open();
    }

    protected Object resolve(Object obj) {
        return obj;
    }
}
