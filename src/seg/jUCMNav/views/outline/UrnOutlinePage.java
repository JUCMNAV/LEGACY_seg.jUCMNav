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
import seg.jUCMNav.editparts.treeEditparts.TreeEditPartFactory;
import seg.jUCMNav.views.dnd.UrnTemplateTransferDragSourceListener;

/**
 * Creates an outline pagebook for both UCMNavMultiPageEditor and UcmEditor. Supports two views: hierarchical tree view and graphical overview.
 * 
 * @author jkealey, etremblay
 * 
 */
public class UrnOutlinePage extends ContentOutlinePage implements IAdaptable, IPageChangeListener {
    static final int ID_OUTLINE = 0;
    static final int ID_OVERVIEW = 1;

    private DisposeListener disposeListener;
    private UCMNavMultiPageEditor multieditor;
    private Control outline;
    private Canvas overview;
    private PageBook pageBook;
    private IAction showOutlineAction, showOverviewAction;
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
    }

    /**
     * Adds buttons and button handlers, initializes drag and drop support.
     * 
     * Creates the tree view and performs appropriate connections.
     */
    protected void configureOutlineViewer() {
        getViewer().setEditDomain(new DefaultEditDomain(multieditor));
        getViewer().setEditPartFactory(new TreeEditPartFactory(multieditor.getModel()));

        // configure & add context menu to viewer
        ContextMenuProvider cmProvider = new UrnContextMenuProvider(getViewer(), multieditor.getActionRegistry());
        getViewer().setContextMenu(cmProvider);
        getSite().registerContextMenu("seg.jUCMNav.editors.actionContributors.UrnContextMenuProvider", cmProvider, getSite().getSelectionProvider()); //$NON-NLS-1$

        // hook outline viewer
        multieditor.getSelectionSynchronizer().addViewer(getViewer());
        multieditor.addPageChangeListener(this);

        // initialize outline viewer with the URNspec
        getViewer().setContents(multieditor);

        // currently not implemented
        // ((TreeViewer) getViewer())
        // .addDropTargetListener(new MSCTransferDropTargetListener(
        // getViewer()));
        
        getViewer().addDragSourceListener(new UrnTemplateTransferDragSourceListener(getViewer()));        


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
        mm.add(showOverviewAction);

        showPage(ID_OUTLINE);
    }

    /**
     * Creates both pages in a page book and shows the hierarchical outline
     * 
     * @see org.eclipse.ui.part.IPage#createControl(org.eclipse.swt.widgets.Composite)
     */
    public void createControl(Composite parent) {
        pageBook = new PageBook(parent, SWT.NONE);
        outline = getViewer().createControl(pageBook);
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
     * Passes the model from parser to the viewer
     */
    protected void initializeOutlineViewer() {
        setContents(multieditor);
        // show outline viewer
        Tree tree = (Tree) ((PageBook) getControl()).getChildren()[0];

        if (tree.getTopItem() != null) { // fix for crash on linux!
            Object[] items = tree.getTopItem().getItems();
            for (int i = 0; i < items.length; i++) {
                ((TreeItem) items[i]).setExpanded(true);
            }
            tree.getTopItem().setExpanded(true);
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
     * Sets the model for the viewer.
     * 
     * @param contents
     */
    public void setContents(Object contents) {
        getViewer().setContents(contents);
    }

    /**
     * Checks which view to display - outline or tree, and displays it.
     * 
     * @param id
     *            parameter indicating which view to display, passed when an appropriate button is pressed in the workbench
     */
    protected void showPage(int id) {
        if (id == ID_OUTLINE) {
            showOutlineAction.setChecked(true);
            showOverviewAction.setChecked(false);
            pageBook.showPage(outline);
            if (thumbnail != null)
                thumbnail.setVisible(false);
        } else if (id == ID_OVERVIEW) {
            if (thumbnail == null)
                initializeOverview();
            showOutlineAction.setChecked(false);
            showOverviewAction.setChecked(true);
            pageBook.showPage(overview);
            if (thumbnail != null && multieditor.getCurrentPage()!=null)
                thumbnail.setVisible(true);
            else
                overview.setVisible(false);
                
        }
    }

    /**
     * Removes selection synchronization, removes listeners. Usually called when outline page is being closed.
     */
    protected void unhookOutlineViewer() {
        multieditor.getSelectionSynchronizer().removeViewer(getViewer());
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
}