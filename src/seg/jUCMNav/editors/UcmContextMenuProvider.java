/*
 * Created on 2005-03-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package seg.jUCMNav.editors;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionFactory;

import seg.jUCMNav.actions.AddLabelAction;
import seg.jUCMNav.actions.CutPathAction;

/**
 * Created 2005-03-21
 * 
 * @author Etienne Tremblay
 */
public class UcmContextMenuProvider extends ContextMenuProvider {
	
	private ActionRegistry actionRegistry;

	/* (non-Javadoc)
	 * @see org.eclipse.gef.ContextMenuProvider#buildContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void buildContextMenu(IMenuManager manager) {
		GEFActionConstants.addStandardActionGroups(manager);

		IAction action;

		action = getActionRegistry().getAction(ActionFactory.UNDO.getId());
		manager.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

		action = getActionRegistry().getAction(ActionFactory.REDO.getId());
		manager.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

		action = getActionRegistry().getAction(ActionFactory.DELETE.getId());
		if (action.isEnabled())
			manager.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		
		action = getActionRegistry().getAction(CutPathAction.CUTPATH);
		if (action.isEnabled())
			manager.appendToGroup(GEFActionConstants.GROUP_REST, action);
		
		action = getActionRegistry().getAction(AddLabelAction.ADDLABEL);
		if (action.isEnabled())
			manager.appendToGroup(GEFActionConstants.GROUP_REST, action);
	}
	/**
	 * @param viewer
	 */
	public UcmContextMenuProvider(EditPartViewer viewer, ActionRegistry registry) {
		super(viewer);
		setActionRegistry(registry);
	}
	
	private ActionRegistry getActionRegistry() {
		return actionRegistry;
	}

	private void setActionRegistry(ActionRegistry registry) {
		actionRegistry = registry;
	}
}
