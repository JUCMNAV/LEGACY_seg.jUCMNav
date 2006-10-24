package seg.jUCMNav.views;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.views.wizards.scenarios.CodeEditor;
import urncore.Condition;

/**
 * This class opens editors for conditions.  
 * 
 * @author jkealey
 * 
 */
public class OpenEditorQuickFix implements IMarkerResolution {
    private Condition cond;
    OpenEditorQuickFix(Condition cond) {
       this.cond=cond;
    }
    public Condition getCondition() {
       return cond;
    }
    public void run(IMarker marker) {
		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		CodeEditor wizard = new CodeEditor();

		StructuredSelection selection = new StructuredSelection(cond);
		wizard.init(PlatformUI.getWorkbench(), selection);
		WizardDialog dialog = new WizardDialog(shell, wizard);
		dialog.open();
	}
	public String getLabel() {
		return "Edit code";
	}
 }

