package seg.jUCMNav.editors.actionContributors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.DeleteAction;
import org.eclipse.gef.ui.actions.DirectEditAction;
import org.eclipse.gef.ui.actions.EditorPartAction;
import org.eclipse.gef.ui.actions.RedoAction;
import org.eclipse.gef.ui.actions.SelectAllAction;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.gef.ui.actions.StackAction;
import org.eclipse.gef.ui.actions.UndoAction;
import org.eclipse.gef.ui.actions.UpdateAction;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IKeyBindingService;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.actions.AddAndForkAction;
import seg.jUCMNav.actions.AddAndJoinAction;
import seg.jUCMNav.actions.AddLabelAction;
import seg.jUCMNav.actions.AddMapAction;
import seg.jUCMNav.actions.AddOrForkAction;
import seg.jUCMNav.actions.AddOrJoinAction;
import seg.jUCMNav.actions.BindChildren;
import seg.jUCMNav.actions.BindWithParent;
import seg.jUCMNav.actions.CutPathAction;
import seg.jUCMNav.actions.TransmogrifyForkOrJoinAction;
import seg.jUCMNav.actions.UnbindChildren;
import seg.jUCMNav.actions.UnbindFromParent;

/**
 * Created on 24-May-2005
 * 
 * Originally included in the UCMNavMultiPageEditor, this code was factored out.
 * 
 * @author jkealey
 *  
 */
public class ActionRegistryManager {

    /** the action registry that is managed */
    private ActionRegistry actionRegistry;

    /** the list of action ids that are editor actions */
    private List editorActionIDs = new ArrayList();

    /** the list of action ids that are to EditPart actions */
    private List editPartActionIDs = new ArrayList();

    /** the list of action ids that are to CommandStack actions */
    private List stackActionIDs = new ArrayList();

    public ActionRegistryManager(ActionRegistry reg) {
        this.actionRegistry = reg;
    }

    /**
     * Adds a general action to the action registry.
     * 
     * @param action
     */
    public void addAction(IAction action) {
        getActionRegistry().registerAction(action);
    }

    /**
     * Adds an editor action to this editor.
     * 
     * <p>
     * Editor actions are actions that depend and work on the editor.
     * 
     * @param action
     *            the editor action
     */
    public void addEditorAction(EditorPartAction action) {
        getActionRegistry().registerAction(action);
        editorActionIDs.add(action.getId());
    }

    /**
     * Adds an <code>EditPart</code> action to this editor.
     * 
     * <p>
     * <code>EditPart</code> actions are actions that depend and work on the selected <code>EditPart</code>s.
     * 
     * @param action
     *            the <code>EditPart</code> action
     */
    public void addEditPartAction(SelectionAction action) {
        getActionRegistry().registerAction(action);
        editPartActionIDs.add(action.getId());
    }

    /**
     * Adds a <code>CommandStack</code> action to this editor.
     * 
     * <p>
     * <code>CommandStack</code> actions are actions that depend and work on the <code>CommandStack</code>.
     * 
     * @param action
     *            the <code>CommandStack</code> action
     */
    public void addStackAction(StackAction action) {
        getActionRegistry().registerAction(action);
        stackActionIDs.add(action.getId());
    }

    /**
     * Initializes the action registry to what is available to jUCMNav.
     * 
     * @param editor
     * @param keyBindingService
     * @param zm
     */
    public void createActions(IEditorPart editor, IKeyBindingService keyBindingService, ZoomManager zm) {
        addStackAction(new UndoAction(editor));
        addStackAction(new RedoAction(editor));

        IAction zoomIn = new ZoomInAction(zm);
        IAction zoomOut = new ZoomOutAction(zm);
        addAction(zoomIn);
        addAction(zoomOut);
        keyBindingService.registerAction(zoomIn);
        keyBindingService.registerAction(zoomOut);

        IAction action;

        action = new SelectAllAction(editor);
        addAction(action);
        action.setAccelerator(SWT.CTRL | 'A');
        keyBindingService.registerAction(action);

        // Notice the following are calls to addEditPartAction().
        // They need to know the current selection to work.
        // If you write addAction instead, you'll get empty selections
        action = new DeleteAction((IWorkbenchPart) editor);
        addEditPartAction((SelectionAction) action);

        action = new CutPathAction(editor);
        action.setText("Cut path");
        addEditPartAction((SelectionAction) action);

        action = new AddLabelAction(editor);
        action.setText("Add label");
        addEditPartAction((SelectionAction) action);

        action = new AddOrForkAction(editor);
        action.setText("Add OR-Fork");
        addEditPartAction((SelectionAction) action);

        action = new AddAndForkAction(editor);
        action.setText("Add AND-Fork");
        addEditPartAction((SelectionAction) action);

        action = new TransmogrifyForkOrJoinAction(editor);
        action.setText("Transmogrify");
        addEditPartAction((SelectionAction) action);

        action = new AddOrJoinAction(editor);
        action.setText("Add OR-Join");
        addEditPartAction((SelectionAction) action);

        action = new AddAndJoinAction(editor);
        action.setText("Add AND-Join");
        addEditPartAction((SelectionAction) action);

        action = new BindWithParent(editor);
        action.setText("Bind with parent component");
        addEditPartAction((SelectionAction) action);

        action = new UnbindFromParent(editor);
        action.setText("Unbind from parent component");
        addEditPartAction((SelectionAction) action);

        action = new UnbindChildren(editor);
        action.setText("Unbind all enclosed elements");
        addEditPartAction((SelectionAction) action);

        action = new BindChildren(editor);
        action.setText("Bind all enclosed elements");
        addEditPartAction((SelectionAction) action);

        action = new AddMapAction(editor);
        action.setText("Add Use Case Map");
        addEditPartAction((SelectionAction) action);

        action = new DirectEditAction((IWorkbenchPart) editor);
        action.setText("edit");
        addEditPartAction((SelectionAction) action);
    }

    /**
     * @return The managed action registry.
     */
    public ActionRegistry getActionRegistry() {
        return actionRegistry;
    }

    /**
     * @return The list of all actions added by addEditorAction()
     */
    public List getEditorActionIDs() {
        return editorActionIDs;
    }

    /**
     * @return The list of all actions added by addEditorAction()
     */
    public List getEditPartActionIDs() {
        return editPartActionIDs;
    }

    /**
     * @return The list of all actions added by addEditorAction()
     */
    public List getStackActionIDs() {
        return stackActionIDs;
    }

    /**
     * Shorthand for updateActions(editorActionIDs); updateActions(editPartActionIDs); updateActions(stackActionIDs);
     *  
     */
    public void updateActions() {
        updateActions(editorActionIDs);
        updateActions(editPartActionIDs);
        updateActions(stackActionIDs);
    }

    /**
     * Updates the specified actions, if they are UpdateActions
     * 
     * @param actionIds
     *            the list of ids of actions to update
     */
    public void updateActions(List actionIds) {
        // jkealey: this method comes from copy paste; I don't think we have any UpdateActions.
        // leaving here just in case, for future use.

        for (Iterator ids = actionIds.iterator(); ids.hasNext();) {
            IAction action = getActionRegistry().getAction(ids.next());
            if (null != action && action instanceof UpdateAction)
                ((UpdateAction) action).update();

        }
    }

    /**
     * Shorthand for updateActions(editorActionIDs);
     *  
     */
    public void updateEditorActions() {
        updateActions(editorActionIDs);
    }

    /**
     * Shorthand forupdateActions(editPartActionIDs);
     *  
     */
    public void updateEditPartActions() {
        updateActions(editPartActionIDs);
    }

    /**
     * Shorthand for updateActions(stackActionIDs);
     *  
     */
    public void updateStackActions() {
        updateActions(stackActionIDs);
    }

}