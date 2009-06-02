package seg.jUCMNav.views.preferences.metrics;

import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import seg.jUCMNav.Messages;
import seg.jUCMNav.rulemanagement.RuleManagementDefinitionManager;
import seg.jUCMNav.views.preferences.rulemanagement.RuleEditDialog;

public class MetricsRuleEditDialog extends RuleEditDialog {

	public MetricsRuleEditDialog(IShellProvider parentShell,
			RuleManagementDefinitionManager defferManager) {
		super(parentShell, defferManager);
		// TODO Auto-generated constructor stub
	}

	public MetricsRuleEditDialog(Shell parentShell,
			RuleManagementDefinitionManager defferManager) {
		super(parentShell, defferManager);
		// TODO Auto-generated constructor stub
	}


	protected void createConstraintGUI(Composite c1){
		/*
		Label lblCheck = new Label(c1, SWT.LEFT);
		lblCheck.setText(Messages.getString("RuleEditDialog.ConstraintExpression")); //$NON-NLS-1$
		txtCheck = new Text(c1, SWT.MULTI | SWT.BORDER);
		txtCheck.setLayoutData(new GridData(GridData.FILL_BOTH));
		txtCheck.setSize(600, 600);
		*/
	}
	
    
    protected String getQueryLabel(){
    	return Messages.getString("MetricsRuleEditDialog.QueryExpression");
    }
    
    protected GridData getQueryGridData(){
   	 GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
        gd.heightHint = 80;
        return gd;
   	
   }
    

}
