package seg.jUCMNav.actions;

import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.internal.EditorPluginAction;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;

/**
 * This is an action delegate that can be used in plugin.xml to run commands that are in the action registry.
 * 
 * @author jkealey
 * 
 */
public class UCMActionDelegate implements IEditorActionDelegate {

    private UCMNavMultiPageEditor target;

    /**
     * Memorize the active editor if it is a {@link UCMNavMultiPageEditor}
     * 
     * @see org.eclipse.ui.IEditorActionDelegate#setActiveEditor(org.eclipse.jface.action.IAction, org.eclipse.ui.IEditorPart)
     */
    public void setActiveEditor(IAction action, IEditorPart targetEditor) {
        if (targetEditor instanceof UCMNavMultiPageEditor)
            target = (UCMNavMultiPageEditor) targetEditor;
        else
            target = null;

    }

    /**
     * Run the action that we are given in the current editor.
     * 
     * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
     */
    public void run(IAction action) {
        if (action instanceof EditorPluginAction) {
            EditorPluginAction pluginaction = (EditorPluginAction) action;
            ActionRegistry registry = (ActionRegistry) target.getAdapter(ActionRegistry.class);
            IAction torun = registry.getAction(pluginaction.getId());
            torun.run();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
        // does not depend on selection.
    }

}