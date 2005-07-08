package seg.jUCMNav.views.outline;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.IPageSite;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.actionContributors.UcmContextMenuProvider;
import seg.jUCMNav.editparts.treeEditparts.TreeEditPartFactory;

/**
 * Creates an outline pagebook for both UCMNavMultiPageEditor and UcmEditor.
 *  
 */
public class UcmOutlinePage extends ContentOutlinePage {
    //    private final UcmEditor editor;
    private final UCMNavMultiPageEditor multieditor;

    /**
     * Create a new outline page for the shapes editor.
     * 
     * @param viewer
     *            a viewer (TreeViewer instance) used for this outline page
     * @param editor
     *            TODO
     * @throws IllegalArgumentException
     *             if editor is null
     */
    public UcmOutlinePage(UCMNavMultiPageEditor editor, EditPartViewer viewer) {
        super(viewer);
        this.multieditor = editor;
    }

    /**
     * Creates the tree view and performs appropriate connections.
     * 
     * @see org.eclipse.ui.part.IPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        // create outline viewer page
        getViewer().createControl(parent);
        // configure outline viewer
        //        if (editor != null) {
        //            getViewer().setEditDomain(editor.getEditDomain());
        //        } else {
        getViewer().setEditDomain(new DefaultEditDomain(multieditor));
        //}
        getViewer().setEditPartFactory(new TreeEditPartFactory(multieditor.getModel()));

        // configure & add context menu to viewer
        ContextMenuProvider cmProvider = new UcmContextMenuProvider(getViewer(), multieditor.getActionRegistry());
        getViewer().setContextMenu(cmProvider);
        getSite().registerContextMenu("seg.jUCMNav.editors.actionContributors.UcmContextMenuProvider", cmProvider, getSite().getSelectionProvider()); //$NON-NLS-1$
        // hook outline viewer
        multieditor.getSelectionSynchronizer().addViewer(getViewer());

        // initialize outline viewer with the URNspec

        getViewer().setContents(multieditor);
        // show outline viewer

        Tree tree = (Tree) getControl();
		if (tree.getTopItem() != null) {	//fix for crash on linux!
			Object[] items = tree.getTopItem().getItems();
			for (int i = 0; i < items.length; i++) {
				((TreeItem) items[i]).setExpanded(true);
			}
			tree.getTopItem().setExpanded(true);
		}
    }

    /**
	 * Removes listeners
	 * 
	 * @see org.eclipse.ui.part.IPage#dispose()
	 */
    public void dispose() {
        // unhook outline viewer
        multieditor.getSelectionSynchronizer().removeViewer(getViewer());
        // dispose
        super.dispose();
    }

    /**
     * Returns the control
     * 
     * @see org.eclipse.ui.part.IPage#getControl()
     */
    public Control getControl() {
        return getViewer().getControl();
    }

    /**
     * To allow outside access (tests)
     */
    public EditPartViewer getViewer() {
        return super.getViewer();
    }

    /**
     * Initialize the action registry so that undo/redo keyboard shortcuts work here.
     * 
     * @see org.eclipse.ui.part.IPageBookViewPage#init(org.eclipse.ui.part.IPageSite)
     */
    public void init(IPageSite pageSite) {
        super.init(pageSite);
        ActionRegistry registry = multieditor.getActionRegistry();
        IActionBars bars = pageSite.getActionBars();
        String id = ActionFactory.UNDO.getId();
        bars.setGlobalActionHandler(id, registry.getAction(id));
        id = ActionFactory.REDO.getId();
        bars.setGlobalActionHandler(id, registry.getAction(id));
        id = ActionFactory.DELETE.getId();
        bars.setGlobalActionHandler(id, registry.getAction(id));
    }
}