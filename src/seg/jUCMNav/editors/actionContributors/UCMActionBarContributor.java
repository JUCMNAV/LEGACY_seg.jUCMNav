package seg.jUCMNav.editors.actionContributors;

import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.RedoRetargetAction;
import org.eclipse.gef.ui.actions.UndoRetargetAction;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.RetargetAction;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.ui.IEditorPart;


/**
 * This class define all the modification we should make to the workbench when we are editing an UCM.
 * 
 * @author Etienne Tremblay
 * 
 */
public class UCMActionBarContributor extends ActionBarContributor {
    /**
     * Create actions managed by this contributor.
     * 
     * @see org.eclipse.gef.ui.actions.ActionBarContributor#buildActions()
     */
    protected void buildActions() {
        addRetargetAction(new UndoRetargetAction());
        addRetargetAction(new RedoRetargetAction());
        addRetargetAction(new ZoomInRetargetAction());
        addRetargetAction(new ZoomOutRetargetAction());
        addRetargetAction((RetargetAction) ActionFactory.COPY.create(getPage().getWorkbenchWindow()));
        addRetargetAction((RetargetAction) ActionFactory.CUT.create(getPage().getWorkbenchWindow()));
        addRetargetAction((RetargetAction) ActionFactory.PASTE.create(getPage().getWorkbenchWindow()));

    }

    /**
     * Add actions to the given toolbar.
     * 
     * @see org.eclipse.ui.part.EditorActionBarContributor#contributeToToolBar(org.eclipse.jface.action.IToolBarManager)
     */
    public void contributeToToolBar(IToolBarManager toolBarManager) {
        super.contributeToToolBar(toolBarManager);
        toolBarManager.add(getAction(ActionFactory.UNDO.getId()));
        toolBarManager.add(getAction(ActionFactory.REDO.getId()));
        toolBarManager.add(new Separator());
        toolBarManager.add(getAction(ActionFactory.COPY.getId()));
        toolBarManager.add(getAction(ActionFactory.CUT.getId()));
        toolBarManager.add(getAction(ActionFactory.PASTE.getId()));
        toolBarManager.add(new Separator());
        String[] zoomStrings = new String[] { ZoomManager.FIT_ALL, ZoomManager.FIT_HEIGHT, ZoomManager.FIT_WIDTH };
        toolBarManager.add(new ZoomComboContributionItem(getPage(), zoomStrings));
        
        /*
        String[] modeStrings = new String[] {
                Messages.getString("UCMActionBarContributor.viewAllElements"), Messages.getString("UCMActionBarContributor.hideEmptyPoints"), Messages.getString("UCMActionBarContributor.hideEmptyPointsAndStubLabels") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        toolBarManager.add(new ModeComboContributionItem(getPage(), modeStrings));
        */
    }

    /**
     * Registers actions that are global to the UCMNavMultiPageEditor, not specific to the UcmEditor.
     */
    protected void declareGlobalActionKeys() {
        // currently none
    }
    
    
    /* DB: Overrides this to avoid null pointer exception with ActionRegistry adapter.
     * (non-Javadoc)
     * @see org.eclipse.gef.ui.actions.ActionBarContributor#setActiveEditor(org.eclipse.ui.IEditorPart)
     */
    @Override
	public void setActiveEditor(IEditorPart editor) {
    	final ActionRegistry registry = (ActionRegistry) editor.getAdapter(ActionRegistry.class);
    	
    	if ( registry != null ) {
    		super.setActiveEditor( editor );
    	}
	}


}