package seg.jUCMNav.views.preferences.metrics;

import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import seg.jUCMNav.Messages;
import seg.jUCMNav.rulemanagement.RuleManagementDefinitionManager;
import seg.jUCMNav.views.preferences.rulemanagement.RuleEditDialog;

public class MetricsRuleEditDialog extends RuleEditDialog {

    public MetricsRuleEditDialog(IShellProvider parentShell, RuleManagementDefinitionManager defferManager) {
        super(parentShell, defferManager);
    }

    public MetricsRuleEditDialog(Shell parentShell, RuleManagementDefinitionManager defferManager) {
        super(parentShell, defferManager);
    }

    // Override. Remove this part.
    protected void createConstraintGUI(Composite c1) {
    }

    protected String getQueryLabel() {
        return Messages.getString("MetricsRuleEditDialog.QueryExpression"); //$NON-NLS-1$
    }

    protected GridData getQueryGridData() {
        GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
        gd.heightHint = 80;
        return gd;

    }

}
