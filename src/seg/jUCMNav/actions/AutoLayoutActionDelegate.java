package seg.jUCMNav.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UcmEditor;
import seg.jUCMNav.views.wizards.AutoLayoutWizard;
import ucm.map.UCMmap;

/**
 * Opens the AutoLayout Wizard.
 * 
 * @author jkealey
 *  
 */
public class AutoLayoutActionDelegate implements IEditorActionDelegate {

    private UCMNavMultiPageEditor editor;

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IEditorActionDelegate#setActiveEditor(org.eclipse.jface.action.IAction, org.eclipse.ui.IEditorPart)
     */
    public void setActiveEditor(IAction action, IEditorPart targetEditor) {
        editor = (UCMNavMultiPageEditor) targetEditor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
        // we dont' depend on the selection.

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {
        if (editor.getCurrentPage() != null) {
            if (editor.getCurrentPage() instanceof UcmEditor)
            {
                UCMmap map = ((UCMmap)((UcmEditor) editor.getCurrentPage()).getModel());
    
                AutoLayoutWizard wizard = new AutoLayoutWizard((UcmEditor)editor.getCurrentPage(), map);
    
                WizardDialog dialog = new WizardDialog(editor.getSite().getShell(), wizard);
    
                dialog.open();
            }
            else
            {
                System.out.println(Messages.getString("AutoLayoutActionDelegate.AutolayoutNotAvailable")); //$NON-NLS-1$
            }
        }
    }

}