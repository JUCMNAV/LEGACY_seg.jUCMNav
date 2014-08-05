package seg.jUCMNav.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.views.wizards.AutoLayoutWizard;
import urncore.IURNDiagram;

/**
 * Opens the AutoLayout Wizard.
 * 
 * @author jkealey
 * 
 */
public class AutoLayoutActionDelegate implements IEditorActionDelegate {

	private UCMNavMultiPageEditor editor;

	/**
	 * 
	 * Sets the {@link UCMNavMultiPageEditor}.
	 * 
	 */
	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		// DB: avoid class cast exception with e4
		if ( targetEditor instanceof UCMNavMultiPageEditor ) {
			editor = (UCMNavMultiPageEditor) targetEditor;
		}
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		// we dont' depend on the selection.

	}

	/**
	 * Launches the {@link AutoLayoutWizard}
	 */
	public void run(IAction action) {
		if (editor.getCurrentPage() != null) {
			if (editor.getCurrentPage() instanceof UrnEditor) {
				IURNDiagram diagram = ((editor.getCurrentPage()).getModel());

				AutoLayoutWizard wizard = new AutoLayoutWizard(editor.getCurrentPage(), diagram);

				WizardDialog dialog = new WizardDialog(editor.getSite().getShell(), wizard);

				dialog.open();
			} else {
				System.out.println(Messages.getString("AutoLayoutActionDelegate.AutolayoutNotAvailable")); //$NON-NLS-1$
			}
		}
	}

}