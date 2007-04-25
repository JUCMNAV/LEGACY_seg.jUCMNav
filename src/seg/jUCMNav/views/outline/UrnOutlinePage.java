package seg.jUCMNav.views.outline;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.draw2d.parts.Thumbnail;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.PageBook;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.IPageChangeListener;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.actionContributors.UrnContextMenuProvider;
import seg.jUCMNav.editparts.concernsTreeEditparts.ConcernsTreeEditPartFactory;
import seg.jUCMNav.editparts.treeEditparts.TreeEditPartFactory;
import seg.jUCMNav.views.dnd.UrnTemplateTransferDragSourceListener;

/**
 * Creates an outline pagebook for both UCMNavMultiPageEditor and UcmEditor. Supports three views: 
 *    hierarchical model element tree view
 *    hierarchical concern view
 *    graphical overview
 * @author jkealey, etremblay, gunterm
 */
public class UrnOutlinePage extends ContentOutlinePage implements IAdaptable, IPageChangeListener {
    static final int ID_OUTLINE = 0;
    static final int ID_OVERVIEW = 1;
    static final int ID_CONCERNS = 2;

    private DisposeListener disposeListener;
    private UCMNavMultiPageEditor multieditor;
    private Control outline;
    private Canvas overview;
    private ContentOutlinePage concernsPage;
    private EditPartViewer concernsViewer;
    private Control concerns;
    private PageBook pageBook;
    private IAction showOutlineAction, showOverviewAction, showConcernsAction;
    private Thumbnail thumbnail;

    /**
     * Create a new outline page for the shapes editor.
     * 
     * @param editor
     *            The UCMNavMultiPageEditor to be used as the root model element
     * @param viewer
     *            a viewer (TreeViewer instance) used for this outline page
     */
    public UrnOutlinePage(UCMNavMultiPageEditor editor, EditPartViewer viewer) {
        super(viewer);
        this.multieditor = editor;
        // also initializes the second outline (the concern outline)
        this.concernsViewer = new TreeViewer();
        this.concernsPage = new ContentOutlinePage(concernsViewer);
    }

    /**
     * Adds buttons and button handlers, initializes drag and drop support.
     * Creates all three views and performs appropriate connections.
     */
    protected void configureOutlineViewer() {
        configureOutlineViewerDetails(getViewer(), new TreeEditPartFactory(multieditor.getModel()));
        configureOutlineViewerDetails(getConcernsViewer(), new ConcernsTreeEditPartFactory(multieditor.getModel()));
        multieditor.addPageChangeListener(this);

        IToolBarManager tbm = getSite().getActionBars().getToolBarManager();
        showOutlineAction = new Action() {
            public void run() {
                // tree view
                showPage(ID_OUTLINE);
            }
        };
        showOutlineAction.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/outline16.gif")); //$NON-NLS-1$
        showOutlineAction.setToolTipText(Messages.getString("UrnOutlinePage.HierarchicalOutline")); //$NON-NLS-1$
        showOutlineAction.setText(Messages.getString("UrnOutlinePage.HierarchicalOutline")); //$NON-NLS-1$
        tbm.add(showOutlineAction);
        showConcernsAction = new Action() {
            public void run() {
                // concern tree view
                showPage(ID_CONCERNS);
            }
        };
        showConcernsAction.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/concernsOutline16.gif")); //$NON-NLS-1$
        showConcernsAction.setToolTipText(Messages.getString("UrnOutlinePage.ConcernOutline")); //$NON-NLS-1$
        showConcernsAction.setText(Messages.getString("UrnOutlinePage.ConcernOutline")); //$NON-NLS-1$
        tbm.add(showConcernsAction);
        showOverviewAction = new Action() {
            public void run() {
                // outline view
                showPage(ID_OVERVIEW);
            }
        };
        showOverviewAction.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/icon16.gif")); //$NON-NLS-1$
        showOverviewAction.setToolTipText(Messages.getString("UrnOutlinePage.GraphicalOverview")); //$NON-NLS-1$
        showOverviewAction.setText(Messages.getString("UrnOutlinePage.GraphicalOverview")); //$NON-NLS-1$
        tbm.add(showOverviewAction);
        
        IMenuManager mm = getSite().getActionBars().getMenuManager();
        mm.add(showOutlineAction);
        mm.add(new Separator());
        mm.add(showConcernsAction);
        mm.add(new Separator());
        mm.add(showOverviewAction);
        
        showPage(ID_OUTLINE);
    }

	/**
	 * @param viewer for the outline
	 * @param treeEditPartFactory is the factory for the outline 
	 */
	private void configureOutlineViewerDetails(EditPartViewer viewer, TreeEditPartFactory treeEditPartFactory) {
		viewer.setEditDomain(new DefaultEditDomain(multieditor));
        viewer.setEditPartFactory(treeEditPartFactory);

        // configure & add context menu to viewer
        ContextMenuProvider cmProvider = new UrnContextMenuProvider(viewer, multieditor.getActionRegistry());
        viewer.setContextMenu(cmProvider);
        getSite().registerContextMenu("seg.jUCMNav.editors.actionContributors.UrnContextMenuProvider", cmProvider, getSite().getSelectionProvider()); //$NON-NLS-1$

        // hook outline viewer
        multieditor.getSelectionSynchronizer().addViewer(viewer);

        // initialize outline viewer with the URNspec
        viewer.setContents(multieditor);

        // currently not implemented
        // ((TreeViewer) getViewer())
        // .addDropTargetListener(new MSCTransferDropTargetListener(
        // getViewer()));
        
        viewer.addDragSourceListener(new UrnTemplateTransferDragSourceListener(viewer));
	}

    /**
     * Creates all pages in a page book and shows the hierarchical outline
     * 
     * @see org.eclipse.ui.part.IPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        pageBook = new PageBook(parent, SWT.NONE);
        outline = getViewer().createControl(pageBook);
        concerns = getConcernsViewer().createControl(pageBook);
        overview = new Canvas(pageBook, SWT.NONE);
        pageBook.showPage(outline);
        configureOutlineViewer();
        hookOutlineViewer();
        initializeOutlineViewer();
    }

    /**
     * Cleans up when disposing
     * 
     * @see org.eclipse.ui.part.IPage#dispose()
     */
    public void dispose() {
        unhookOutlineViewer();
        if (thumbnail != null) {
            thumbnail.deactivate();
            thumbnail = null;
        }
        super.dispose();
    }

    /**
     * 
     * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
     */
    public Object getAdapter(Class type) {
        if (type == ZoomManager.class && multieditor.getCurrentPage() != null)
            return multieditor.getCurrentPage().getGraphicalViewer().getProperty(ZoomManager.class.toString());
        return null;
    }

    /**
     * returns the PageBook
     * 
     * @see org.eclipse.ui.part.IPage#getControl()
     */
    public Control getControl() {
        return pageBook;
    }

    /**
     * Synchronizes the selection between the main viewer and the outline views.
     */
    protected void hookOutlineViewer() {
        multieditor.getSelectionSynchronizer().addViewer(getViewer());
        multieditor.getSelectionSynchronizer().addViewer(getConcernsViewer());
    }

    /**
     * Initializes the outline; addes undo/redo/delete keyhandlers.
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

    /**
     * Passes the model from parser to the viewers
     */
    protected void initializeOutlineViewer() {
        setContents(multieditor);
        // show outline viewer
        expandOutline((Tree) ((PageBook) getControl()).getChildren()[0], true);
        // show the concern outline viewer
        expandOutline((Tree) ((PageBook) getControl()).getChildren()[1], false);
    }

    /**
     * @param tree to be expanded 
     * @param expandSecondLevel defines up to what level the tree should be expanded (true for first and second, false for only first level)
     */
    private void expandOutline(Tree tree, boolean expandSecondLevel) {
    	// can't use tree.getTopItem() because it only returns one item even if there are more than 
    	// one top level item: use tree.getItems() instead
    	if (tree.getItems() != null) {
    		TreeItem[] topItems = tree.getItems();
    		for (int i = 0; i < topItems.length; i++) {
    			if (topItems[i] != null) { // fix for crash on linux!
    				TreeItem[] items = topItems[i].getItems();
    				for (int j = 0; j < items.length; j++) {
    					((TreeItem) items[j]).setExpanded(expandSecondLevel);
    				}
    				topItems[i].setExpanded(true);
    			}
    		}
    	}
    }

    /**
     * Initialize the graphical overview.
     * 
     */
    protected void initializeOverview() {
        if (multieditor.getCurrentPage() == null) {
            overview.setVisible(false);
            thumbnail.deactivate();
            thumbnail=null;
            return;
        }

        RootEditPart rep = multieditor.getCurrentPage().getGraphicalViewer().getRootEditPart();
        if (rep instanceof ScalableFreeformRootEditPart) {
            refreshThumbnail((ScalableFreeformRootEditPart) rep);

            disposeListener = new DisposeListener() {
                public void widgetDisposed(DisposeEvent e) {
                    if (thumbnail != null) {
                        thumbnail.deactivate();
                        thumbnail = null;
                    }
                }
            };
            ((FigureCanvas) multieditor.getCurrentPage().getGraphicalViewer().getControl()).addDisposeListener(disposeListener);
        }
    }

    /**
     * Refresh the overview thumbnail when the map changes.
     */
    public void pageChanged() {
        if (multieditor.getCurrentPage() == null) {
            overview.setVisible(false);
            thumbnail.deactivate();
            thumbnail=null;
            return;
        }
        RootEditPart rep = multieditor.getCurrentPage().getGraphicalViewer().getRootEditPart();
        if (rep instanceof ScalableFreeformRootEditPart) {
            refreshThumbnail((ScalableFreeformRootEditPart) rep);
        }

    }

    /**
     * Reload the thumbnail from the root
     * 
     * @param root
     *            the root edit part
     */
    private void refreshThumbnail(ScalableFreeformRootEditPart root) {
        if (thumbnail != null)
            thumbnail.deactivate();
        LightweightSystem lws = new LightweightSystem(overview);
        thumbnail = new ScrollableThumbnail((Viewport) root.getFigure());
        thumbnail.setBorder(new MarginBorder(3));
        thumbnail.setSource(root.getLayer(LayerConstants.PRINTABLE_LAYERS));
        lws.setContents(thumbnail);
    }

    /**
     * Sets the model for the viewers.
     * 
     * @param contents
     */
    public void setContents(Object contents) {
        getViewer().setContents(contents);
        getConcernsViewer().setContents(contents);
    }

    /**
     * Checks which view to display - standard outline, concern outline, or tree, and displays it.
     * 
     * @param id
     *            parameter indicating which view to display, passed when an appropriate button is pressed in the workbench
     */
    protected void showPage(int id) {
        if (id == ID_OUTLINE) {
            showOutlineAction.setChecked(true);
            showOverviewAction.setChecked(false);
            showConcernsAction.setChecked(false);
            pageBook.showPage(outline);
            if (thumbnail != null)
                thumbnail.setVisible(false);
        } else if (id == ID_OVERVIEW) {
            if (thumbnail == null)
                initializeOverview();
            showOutlineAction.setChecked(false);
            showOverviewAction.setChecked(true);
            showConcernsAction.setChecked(false);
            pageBook.showPage(overview);
            if (thumbnail != null && multieditor.getCurrentPage()!=null)
                thumbnail.setVisible(true);
            else
                overview.setVisible(false);
        }
        else if (id == ID_CONCERNS) {
            showOutlineAction.setChecked(false);
            showOverviewAction.setChecked(false);
            showConcernsAction.setChecked(true);
            pageBook.showPage(concerns);
            if (thumbnail != null)
                thumbnail.setVisible(false);
        } 
    }

    /**
     * Removes selection synchronization, removes listeners. Usually called when outline page is being closed.
     */
    protected void unhookOutlineViewer() {
        multieditor.getSelectionSynchronizer().removeViewer(getViewer());
        multieditor.getSelectionSynchronizer().removeViewer(getConcernsViewer());
        if (disposeListener != null && multieditor.getCurrentPage() != null
                && ((FigureCanvas) multieditor.getCurrentPage().getGraphicalViewer().getControl()) != null
                && !((FigureCanvas) multieditor.getCurrentPage().getGraphicalViewer().getControl()).isDisposed())
            ((FigureCanvas) multieditor.getCurrentPage().getGraphicalViewer().getControl()).removeDisposeListener(disposeListener);
    }

    /**
     * Made public so that it is visible from the tests
     */
    public EditPartViewer getViewer() {
        return super.getViewer();
    }
    
    /**
     * @return the secondary viewer of this page (for concern outline)
     */
    private EditPartViewer getConcernsViewer() {
    	return concernsViewer;
    }
}