package seg.jUCMNav.views.property.tabbed.sections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.Messages;
import seg.jUCMNav.views.property.tabbed.mapper.ConditionDataResolver;
import seg.jUCMNav.views.property.tabbed.mapper.IPropertyDataResolver;
import seg.jUCMNav.views.wizards.scenarios.CodeEditor;
import ucm.map.FailurePoint;
import urncore.Condition;
import urncore.Responsibility;

public class ConditionExpressionPropertySection extends AbstractDialogPropertySection {

    protected EObject current;

    protected static IPropertyDataResolver resolver = new ConditionDataResolver();

    protected String getText() {
        String result = ""; //$NON-NLS-1$
        if (current instanceof Condition)
            result = ((Condition) current).getExpression();
        else if (current instanceof FailurePoint)
            result = ((FailurePoint) current).getExpression();
        else if (current instanceof Responsibility)
            result = ((Responsibility) current).getExpression();

        if (result == null)
            result = ""; //$NON-NLS-1$

        return result;
    }

    public String getLabelText() {
        return Messages.getString("ConditionExpressionPropertySection_Condition"); //$NON-NLS-1$
    }

    protected Object resolve(Object obj) {
        current = (EObject) resolver.getData(eObject);

        return current;
    }

    protected void openDialog() {
        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        CodeEditor wizard = new CodeEditor();

        // initialize it
        wizard.init(PlatformUI.getWorkbench(), null, current);

        // open it.
        WizardDialog dialog = new WizardDialog(shell, wizard);
        dialog.open();
    }
}
