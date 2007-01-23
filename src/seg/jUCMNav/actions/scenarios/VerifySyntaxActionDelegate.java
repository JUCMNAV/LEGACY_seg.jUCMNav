package seg.jUCMNav.actions.scenarios;

import java.util.Vector;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.scenarios.SyntaxChecker;

/**
 * Verifies the syntax of all conditions and responsibilities in jUCMNav. 
 * 
 * @author jkealey
 *  
 */
public class VerifySyntaxActionDelegate implements IEditorActionDelegate {

    private UCMNavMultiPageEditor editor;

    /**
     * Set the active editor to a new instance of a {@link seg.jUCMNav.editors.UCMNavMultiPageEditor}. 
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

    /**
     * Runs {@link seg.jUCMNav.scenarios.SyntaxChecker#verifySyntax(urn.URNspec)} and updates the problem view. 
     * 
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {
    	if (editor!=null) {
    		Vector errors = SyntaxChecker.verifySyntax(editor.getModel());
    		SyntaxChecker.refreshProblemsView(errors);
    	}

    }

}