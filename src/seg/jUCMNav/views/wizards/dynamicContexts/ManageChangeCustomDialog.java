package seg.jUCMNav.views.wizards.dynamicContexts;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

/**
 * Custom Dialog for managing changes. 
 * 
 * @author aprajita
 */
public class ManageChangeCustomDialog extends WizardDialog {
	
	public ManageChangeCustomDialog(Shell parentShell, IWizard newWizard) {
	    super(parentShell, newWizard);
	}

	@Override
	public void createButtonsForButtonBar(Composite parent){
	    super.createButtonsForButtonBar(parent);
	    Button finishButton = getButton(IDialogConstants.FINISH_ID);
	    finishButton.setText("Reset");
	    Button cancelButton = getButton(IDialogConstants.CANCEL_ID);
	    cancelButton.setText("Close");
	}

}
