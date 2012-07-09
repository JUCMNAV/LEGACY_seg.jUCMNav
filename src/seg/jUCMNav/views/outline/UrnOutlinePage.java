package seg.jUCMNav.views.outline;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.draw2d.parts.Thumbnail;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.preference.PreferenceDialog;
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
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.PageBook;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.IPageChangeListener;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditDomain;
import seg.jUCMNav.editors.actionContributors.UrnContextMenuProvider;
import seg.jUCMNav.editparts.concernsTreeEditparts.ConcernsTreeEditPartFactory;
import seg.jUCMNav.editparts.treeEditparts.OutlineRootEditPart;
import seg.jUCMNav.editparts.treeEditparts.TreeEditPartFactory;
import seg.jUCMNav.views.JUCMNavRefreshableView;
import seg.jUCMNav.views.dnd.UrnTemplateTransferDragSourceListener;
import seg.jUCMNav.views.preferences.DisplayPreferences;
import ucm.map.UCMmap;

/**
 * Creates an outline pagebook for both UCMNavMultiPageEditor and UcmEditor. Supports three views: hierarchical model element tree view hierarchical concern
 * view graphical overview
 * 
 * @author jkealey, etremblay, gunterm
 */
public class UrnOutlinePage extends ContentOutlinePage implements IAdaptable, IPageChangeListener, JUCMNavRefreshableView {
    static final int ID_OUTLINE = 0;
    static final int ID_OVERVIEW = 1;
    static final int ID_CONCERNS = 2;
    static final int ID_DEFINITIONS = 3;

    private DisposeListener disposeListener;
    private UCMNavMultiPageEditor multieditor;
    private Control outline;
    private Canvas overview;
    private ContentOutlinePage concernsPage;
    private ContentOutlinePage definitionPage;

    private EditPartViewer concernsViewer;
    private EditPartViewer definitionsViewer;
    private Control concerns;
    private Control definitions;

    private PageBook pageBook;
    private IAction showOutlineAction, showOverviewAction, showConcernsAction, showDefinitionsAction;
    private ActionContributionItem showConcernsActionItem;
    // Preference action
    private IAction enableGlobalFilter, showNodeNumberAction;

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
        this.concernsViewer = new UrnTreeViewer();
        this.concernsPage = new ContentOutlinePage(concernsViewer);

        this.definitionsViewer = new UrnTreeViewer();
        this.definitionPage = new ContentOutlinePage(definitionsViewer);
    }

    /**
     * Adds buttons and button handlers, initializes drag and drop support. Creates all three views and performs appropriate connections.
     */
    protected void configureOutlineViewer() {
        configureOutlineViewerDetails(getViewer(), new TreeEditPartFactory(multieditor.getModel(), false));
        configureOutlineViewerDetails(getConcernsViewer(), new ConcernsTreeEditPartFactory(multieditor.getModel()));
        configureOutlineViewerDetails(getDefinitionsViewer(), new TreeEditPartFactory(multieditor.getModel(), true));
        multieditor.addPageChangeListener(this);

        addMenuItems();

        showPage(ID_OUTLINE);
    }

    private void addMenuItems() {
        IToolBarManager tbm = getSite().getActionBars().getToolBarManager();
        showOutlineAction = new Action() {
            public void run() {
                // tree view
                showPage(ID_OUTLINE);
            }
        };
        showOutlineAction.setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/outline16.gif")); //$NON-NLS-1$
        showOutlineAction.setToolTipText(Messages.getString("UrnOutlinePage.HierarchicalOutline")); //$NON-NLS-1$
        showOutlineAction.setText(Messages.getString("UrnOutlinePage.HierarchicalOutline")); //$NON-NLS-1$
        tbm.add(showOutlineAction);

        showDefinitionsAction = new Action() {
            public void run() {
                // concern tree view
                showPage(ID_DEFINITIONS);
            }
        };
        showDefinitionsAction.setId("Definitions"); //$NON-NLS-1$
        showDefinitionsAction.setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/definitionsOutline16.gif")); //$NON-NLS-1$
        showDefinitionsAction.setToolTipText(Messages.getString("UrnOutlinePage.Definitions")); //$NON-NLS-1$
        showDefinitionsAction.setText(Messages.getString("UrnOutlinePage.Definitions")); //$NON-NLS-1$
        tbm.add(showDefinitionsAction);

        showConcernsAction = new Action() {
            public void run() {
                // concern tree view
                showPage(ID_CONCERNS);
            }
        };
        showConcernsAction.setId("UrnOutlinePage.ConcernOutline"); //$NON-NLS-1$
        showConcernsAction.setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/concernsOutline16.gif")); //$NON-NLS-1$
        showConcernsAction.setToolTipText(Messages.getString("UrnOutlinePage.ConcernOutline")); //$NON-NLS-1$
        showConcernsAction.setText(Messages.getString("UrnOutlinePage.ConcernOutline")); //$NON-NLS-1$

        showConcernsActionItem = new ActionContributionItem(showConcernsAction);

        if (DisplayPreferences.getInstance().isAdvancedControlEnabled() && DisplayPreferences.getInstance().isShowAspect())
            tbm.add(showConcernsActionItem);

        showOverviewAction = new Action() {
            public void run() {
                // outline view
                showPage(ID_OVERVIEW);
            }
        };
        showOverviewAction.setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/urnstratscenoff16.gif")); //$NON-NLS-1$
        showOverviewAction.setToolTipText(Messages.getString("UrnOutlinePage.GraphicalOverview")); //$NON-NLS-1$
        showOverviewAction.setText(Messages.getString("UrnOutlinePage.GraphicalOverview")); //$NON-NLS-1$
        tbm.add(showOverviewAction);

        tbm.add(new Separator());

        // Set this view as a listener of the display preferences
        DisplayPreferences.getInstance().registerListener(this);

        // Preferences action
        enableGlobalFilter = new Action() {
            public void run() {
                DisplayPreferences.getInstance().setGlobalFilterEnabled(enableGlobalFilter.isChecked());
                if (enableGlobalFilter.isChecked()) {
                    PreferenceDialog pref = PreferencesUtil.createPreferenceDialogOn(getSite().getShell(),
                            "seg.jUCMNav.views.preferences.DisplayPreferencesPage", new String[] { "seg.jUCMNav.views.preferences.DisplayPreferencesPage" }, //$NON-NLS-1$ //$NON-NLS-2$
                            null);
                    if (pref != null)
                        pref.open();
                }
            }
        };

        enableGlobalFilter.setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/filter16.gif")); //$NON-NLS-1$
        enableGlobalFilter.setToolTipText(Messages.getString("UrnOutlinePage.FilterCertainElements")); //$NON-NLS-1$
        enableGlobalFilter.setText(Messages.getString("UrnOutlinePage.FilterOutline")); //$NON-NLS-1$
        enableGlobalFilter.setChecked(DisplayPreferences.getInstance().isGlobalFilterEnabled());
        tbm.add(enableGlobalFilter);

        showNodeNumberAction = new Action() {
            public void run() {
                DisplayPreferences.getInstance().setShowNodeNumber(showNodeNumberAction.isChecked());
            }
        };

        showNodeNumberAction.setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/identifiers.png")); //$NON-NLS-1$
        showNodeNumberAction.setToolTipText(Messages.getString("UrnOutlinePage.ShowElementsIds")); //$NON-NLS-1$ 
        showNodeNumberAction.setText(Messages.getString("UrnOutlinePage.ShowElementsIds")); //$NON-NLS-1$ 
        showNodeNumberAction.setChecked(DisplayPreferences.getInstance().getShowNodeNumber());

        // disabled.
        // tbm.add(showNodeNumberAction);

        IMenuManager mm = getSite().getActionBars().getMenuManager();
        mm.add(showOutlineAction);
        mm.add(showDefinitionsAction);
        if (DisplayPreferences.getInstance().isAdvancedControlEnabled() && DisplayPreferences.getInstance().isShowAspect())
            mm.add(showConcernsActionItem);
        mm.add(showOverviewAction);
    }

    /**
     * @param viewer
     *            for the outline
     * @param treeEditPartFactory
     *            is the factory for the outline
     */
    private void configureOutlineViewerDetails(EditPartViewer viewer, TreeEditPartFactory treeEditPartFactory) {
        if (viewer.getEditDomain() instanceof UrnEditDomain) {
            ((UrnEditDomain) viewer.getEditDomain()).dispose();
        }

        viewer.setEditDomain(new UrnEditDomain(multieditor));
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
        definitions = getDefinitionsViewer().createControl(pageBook);
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

        EditPartViewer[] viewers = getViewers();

        // doing this one twice just in case.
        if (multieditor != null && multieditor.getSelectionSynchronizer() != null)
            multieditor.getSelectionSynchronizer().removeViewer(getViewer());

        for (int i = 0; i < viewers.length; i++) {
            if (viewers[i] != null) {
                if (viewers[i].getContextMenu() != null) {
                    viewers[i].getContextMenu().dispose();
                    viewers[i].setContextMenu(null);
                }
                if (viewers[i].getEditDomain() instanceof UrnEditDomain) {
                    UrnEditDomain domain = (UrnEditDomain) viewers[i].getEditDomain();
                    domain.dispose();
                }

                viewers[i].setEditPartFactory(null);
            }
            if (multieditor != null && multieditor.getSelectionSynchronizer() != null)
                multieditor.getSelectionSynchronizer().removeViewer(viewers[i]);
        }

        unhookOutlineViewer();
        if (thumbnail != null) {
            thumbnail.deactivate();
            thumbnail = null;
        }

        multieditor.removePageChangeListener(this);
        DisplayPreferences.getInstance().unregisterListener(this);

        super.dispose();

        for (int i = 0; i < viewers.length; i++) {
            EditPartViewer editPartViewer = viewers[i];
            if (editPartViewer != null) {
                if (editPartViewer.getRootEditPart() != null) {
                    if (editPartViewer.getRootEditPart().getContents() != null) {
                        editPartViewer.getRootEditPart().getContents().setModel(null);
                        editPartViewer.getRootEditPart().setContents(null);
                    }
                    editPartViewer.getRootEditPart().setModel(null);
                }

                editPartViewer.getEditPartRegistry().clear();
            }
        }
        // bug 531
        multieditor = null;
        disposeListener = null;
        outline = null;
        overview = null;
        concernsPage = null;
        definitionPage = null;
        concernsViewer = null;
        concerns = null;
        definitions = null;
        pageBook = null;
        showOutlineAction = null;
        showOverviewAction = null;
        showConcernsAction = null;
        enableGlobalFilter = null;
        showNodeNumberAction = null;
        thumbnail = null;
        definitionsViewer = null;

    }

    public EditPartViewer[] getViewers() {
        EditPartViewer[] viewers = new EditPartViewer[3];
        viewers[0] = getViewer();
        viewers[1] = definitionsViewer;
        viewers[2] = concernsViewer;
        return viewers;
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
    public void hookOutlineViewer() {
        multieditor.getSelectionSynchronizer().addViewer(getViewer());
        multieditor.getSelectionSynchronizer().addViewer(getConcernsViewer());
        multieditor.getSelectionSynchronizer().addViewer(getDefinitionsViewer());
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
        id = ActionFactory.PASTE.getId();
        bars.setGlobalActionHandler(id, registry.getAction(id));
        id = ActionFactory.COPY.getId();
        bars.setGlobalActionHandler(id, registry.getAction(id));
        id = ActionFactory.CUT.getId();
        bars.setGlobalActionHandler(id, registry.getAction(id));

    }

    /**
     * Passes the model from parser to the viewers
     */
    protected void initializeOutlineViewer() {
        setContents(multieditor);
        expandOutline();

    }

    private void expandOutline() {
        // show outline viewer
        expandOutline((Tree) ((PageBook) getControl()).getChildren()[0], false);
        // show the concern outline viewer
        expandOutline((Tree) ((PageBook) getControl()).getChildren()[1], false);
        // show the definitions outline viewer
        // expandOutline((Tree) ((PageBook) getControl()).getChildren()[2], true);
        refreshDefinitions(true);
    }

    /**
     * @param tree
     *            to be expanded
     * @param expandSecondLevel
     *            defines up to what level the tree should be expanded (true for first and second, false for only first level)
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
            thumbnail = null;
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
            thumbnail = null;
            return;
        }

        RootEditPart rep = multieditor.getCurrentPage().getGraphicalViewer().getRootEditPart();
        if (rep instanceof ScalableFreeformRootEditPart) {
            refreshThumbnail((ScalableFreeformRootEditPart) rep);
        }

        refreshDefinitions(false);

    }

    private void refreshDefinitions(boolean forceRefresh) {
        if (multieditor.getCurrentPage() != null) {
            boolean expand = false;
            if (multieditor.getCurrentPage().getModel() instanceof UCMmap) {
                String root = Messages.getString("LabelTreeEditPart.ucmDefs"); //$NON-NLS-1$
                if (forceRefresh || definitionsViewer.getContents() == null || definitionsViewer.getContents().getModel() == null
                        || !definitionsViewer.getContents().getModel().equals(root)) {
                    definitionsViewer.setContents(root);
                    expand = true;
                }
            } else {
                String root = Messages.getString("LabelTreeEditPart.grlDefs"); //$NON-NLS-1$
                if (forceRefresh || definitionsViewer.getContents() == null || definitionsViewer.getContents().getModel() == null
                        || !definitionsViewer.getContents().getModel().equals(root)) {
                    definitionsViewer.setContents(root);
                    expand = true;
                }
            }
            if (expand)
                expandOutline((Tree) ((PageBook) getControl()).getChildren()[2], false);
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
        getDefinitionsViewer().setContents(contents);

    }

    /**
     * Checks which view to display - standard outline, concern outline, or tree, and displays it.
     * 
     * @param id
     *            parameter indicating which view to display, passed when an appropriate button is pressed in the workbench
     */
    protected void showPage(int id) {
        if (showOutlineAction==null) return; // defense in depth. 
        showOutlineAction.setChecked(id == ID_OUTLINE);
        showOverviewAction.setChecked(id == ID_OVERVIEW);
        showConcernsAction.setChecked(id == ID_CONCERNS);
        showDefinitionsAction.setChecked(id == ID_DEFINITIONS);

        if (id == ID_OUTLINE) {
            enableGlobalFilter.setEnabled(true);
            showNodeNumberAction.setEnabled(true);
            pageBook.showPage(outline);
            if (thumbnail != null)
                thumbnail.setVisible(false);
        } else if (id == ID_OVERVIEW) {
            if (thumbnail == null)
                initializeOverview();
            enableGlobalFilter.setEnabled(true);
            showNodeNumberAction.setEnabled(false);
            pageBook.showPage(overview);
            if (thumbnail != null && multieditor.getCurrentPage() != null)
                thumbnail.setVisible(true);
            else
                overview.setVisible(false);
        } else if (id == ID_CONCERNS) {
            enableGlobalFilter.setEnabled(true);
            showNodeNumberAction.setEnabled(true);
            pageBook.showPage(concerns);
            if (thumbnail != null)
                thumbnail.setVisible(false);
        } else if (id == ID_DEFINITIONS) {
            enableGlobalFilter.setEnabled(true);
            showNodeNumberAction.setEnabled(true);
            pageBook.showPage(definitions);
            if (thumbnail != null)
                thumbnail.setVisible(false);
        }
    }

    /**
     * Removes selection synchronization, removes listeners. Usually called when outline page is being closed.
     */
    public void unhookOutlineViewer() {
        if (multieditor != null) {
            multieditor.getSelectionSynchronizer().removeViewer(getViewer());
            multieditor.getSelectionSynchronizer().removeViewer(getConcernsViewer());
            multieditor.getSelectionSynchronizer().removeViewer(getDefinitionsViewer());
            if (disposeListener != null && multieditor.getCurrentPage() != null
                    && ((FigureCanvas) multieditor.getCurrentPage().getGraphicalViewer().getControl()) != null
                    && !((FigureCanvas) multieditor.getCurrentPage().getGraphicalViewer().getControl()).isDisposed())
                ((FigureCanvas) multieditor.getCurrentPage().getGraphicalViewer().getControl()).removeDisposeListener(disposeListener);
        }
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

    /**
     * @return the secondary viewer of this page (for definitions outline)
     */
    private EditPartViewer getDefinitionsViewer() {
        return definitionsViewer;
    }

    public void refreshView() {
        // To refresh the viewer

        if (getViewer().getContents() instanceof OutlineRootEditPart) {
            OutlineRootEditPart outlineRootEditPart = (OutlineRootEditPart) getViewer().getContents();
            // causes various exceptions otherwise.
            if (outlineRootEditPart.getWidget() != null && !outlineRootEditPart.getWidget().isDisposed()) {
                getViewer().setContents(getViewer().getContents());
                concernsViewer.setContents(concernsViewer.getContents());
                definitionsViewer.setContents(definitionsViewer.getContents());

                expandOutline();
            }
        }

        if (enableGlobalFilter != null)
            enableGlobalFilter.setChecked(DisplayPreferences.getInstance().isGlobalFilterEnabled());
        if (showNodeNumberAction != null)
            showNodeNumberAction.setChecked(DisplayPreferences.getInstance().getShowNodeNumber());

        IMenuManager mm = getSite().getActionBars().getMenuManager();
        IToolBarManager tbm = getSite().getActionBars().getToolBarManager();
        if (DisplayPreferences.getInstance().isAdvancedControlEnabled() && DisplayPreferences.getInstance().isShowAspect()) {
//            IContributionItem item = tbm.find(showConcernsAction.getActionDefinitionId());
            
            boolean found=false;
            for (int i = 0; i < tbm.getItems().length; i++) {
                if (tbm.getItems()[i].getId()!=null && tbm.getItems()[i].getId().equals(showConcernsAction.getId()))
                    found=true;
            }
            if (!found) {
                tbm.removeAll();
                mm.removeAll();
                
                addMenuItems();

                tbm.update(true);
                mm.updateAll(true);
            }
        } else {
            mm.remove(showConcernsActionItem);
            tbm.remove(showConcernsActionItem);
            
            tbm.markDirty();

            tbm.update(true);
            mm.updateAll(true);
        }
    }
}