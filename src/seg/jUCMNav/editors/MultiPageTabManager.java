package seg.jUCMNav.editors;

import grl.GRLGraph;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;

import seg.jUCMNav.editparts.ModelElementEditPart;
import seg.jUCMNav.editparts.URNDiagramEditPart;
import seg.jUCMNav.editparts.treeEditparts.URNspecTreeEditPart;
import seg.jUCMNav.editparts.treeEditparts.UrnModelElementTreeEditPart;
import seg.jUCMNav.model.commands.transformations.ChangeUCMDiagramOrderCommand;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.IURNDiagram;
import urncore.URNmodelElement;

/**
 * Manages tab creation/addition/removal for the editor. Originally included in the UCMNavMultiPageEditor, this code was factored out.
 * 
 * @author jkealey
 * 
 */
public class MultiPageTabManager {

    private ListenerList pageListeners = new ListenerList(ListenerList.IDENTITY);

    /** the managed editor */
    private UCMNavMultiPageEditor editor;

    /**
     * The listener will get notifications when the current page is changed.
     * 
     * @param listener
     *            The listener to add
     */
    public void addPageChangeListener(IPageChangeListener listener) {
        pageListeners.add(listener);
    }

    /**
     * Remove a page change listener.
     * 
     * @param listener
     *            The listener to remove
     */
    public void removePageChangeListener(IPageChangeListener listener) {
        pageListeners.remove(listener);
    }

    /** the selection listener */
    private ISelectionListener selectionListener = new ISelectionListener() {
        public void selectionChanged(IWorkbenchPart part, ISelection selection) {

            if (selection instanceof StructuredSelection) {
                // a selection has been made in the outline, ensure that the containing page is visible
                if (((StructuredSelection) selection).getFirstElement() instanceof UrnModelElementTreeEditPart) {
                    UrnModelElementTreeEditPart selectedPart = (UrnModelElementTreeEditPart) ((StructuredSelection) selection).getFirstElement();

                    if (!(selectedPart instanceof URNspecTreeEditPart)) {
                        IURNDiagram selectedMap = selectedPart.getContainingMap();
                        if (getActivePage() != getDiagrams().indexOf(selectedMap) && getDiagrams().indexOf(selectedMap) >= 0) {
                            setActivePage(getDiagrams().indexOf(selectedMap));
                        }
                    }
                }
            }
        }
    };

    /**
     * This class is delegated some responsibilities by the editor.
     */
    public MultiPageTabManager(UCMNavMultiPageEditor editor) {
        this.editor = editor;
    }

    /** Delegates to UCMNavMultiPageEditor */
    private void addPage(IEditorPart part, IEditorInput input) {
        try {
            getEditor().addPage(part, input);
        } catch (PartInitException e) {
            e.printStackTrace();
        }
    }

    /**
     * Called when the editor is opened or when a save as is performed.
     * 
     * @see org.eclipse.ui.part.MultiPageEditorPart#createPages()
     */
    protected void createPages() {

        for (int i = 0; i < getDiagrams().size(); i++) {
            IURNDiagram g = getDiagram(i);
            if (g instanceof UCMmap) {
                UcmEditor u = new UcmEditor(getEditor());
                u.setModel(getDiagram(i));
                addPage(u, editor.getEditorInput());
                editor.getMultiPageCommandStackListener().addCommandStack(u.getCommandStack());
                setPageText(getPageCount() - 1, ((UCMmap) getDiagram(i)).getName());
            } else if (g instanceof GRLGraph) {
                GrlEditor grl = new GrlEditor(getEditor());
                grl.setModel(getDiagram(i));
                addPage(grl, editor.getEditorInput());
                editor.getMultiPageCommandStackListener().addCommandStack(grl.getCommandStack());
                setPageText(getPageCount() - 1, ((GRLGraph) getDiagram(i)).getName());
            }
        }
    }

    /**
     * Indicates that the current page has changed.
     * <p>
     * We update the DelegatingCommandStack, OutlineViewer and other things here.
     */
    protected void currentPageChanged() {
        if (getCurrentPage() != null) {
            // update zoom actions
            editor.getDelegatingZoomManager().setCurrentZoomManager(editor.getZoomManager(getCurrentPage().getGraphicalViewer()));

            // update delegating command stack
            editor.getDelegatingCommandStack().setCurrentCommandStack(getCurrentPage().getCommandStack());

            IWorkbenchPage page = this.editor.getSite().getPage();
            page.getNavigationHistory().markLocation(this.editor);

            firePageChanged();
        }
    }

    /** Delegates to UCMNavMultiPageEditor */
    private int getActivePage() {
        return getEditor().getActivePage();
    }

    /** Delegates to UCMNavMultiPageEditor */
    private UrnEditor getCurrentPage() {
        return editor.getCurrentPage();
    }

    /** Delegates to UCMNavMultiPageEditor */
    private UCMNavMultiPageEditor getEditor() {
        return editor;
    }

    /** Delegates to UCMNavMultiPageEditor */
    private IURNDiagram getDiagram(int i) {
        return (IURNDiagram) getDiagrams().get(i);
    }

    /** Delegates to UCMNavMultiPageEditor */
    private EList getDiagrams() {
        return getModel().getUrndef().getSpecDiagrams();
    }

    /** Delegates to UCMNavMultiPageEditor */
    private URNspec getModel() {
        return getEditor().getModel();
    }

    /** Delegates to UCMNavMultiPageEditor */
    private int getPageCount() {
        return editor.getPageCount();
    }

    /**
     * 
     * @return the selection listener
     */
    protected ISelectionListener getSelectionListener() {
        return selectionListener;
    }

    /**
     * This method is called when the user clicks on a tab.
     * 
     * If we have selected another map, select it so that the outline view is refreshed.
     * 
     * @see org.eclipse.ui.part.MultiPageEditorPart#pageChange(int)
     */
    protected void pageChange(int newPageIndex) {
        ModelElementEditPart e;
        if (editor.getCurrentPage().getModel() instanceof UCMmap) {
            // we want the outline to know that we've selected another map.
            e = (URNDiagramEditPart) editor.getCurrentPage().getGraphicalViewer().getEditPartRegistry().get(((UcmEditor) editor.getCurrentPage()).getModel());
        } else {
            e = (URNDiagramEditPart) editor.getCurrentPage().getGraphicalViewer().getEditPartRegistry().get(((GrlEditor) editor.getCurrentPage()).getModel());
        }
        // If we don't flush, paint buffer not displayed on screen
        editor.getCurrentPage().getGraphicalViewer().flush();

        // select the background map
        try {
            if (e != null) {
                editor.getCurrentPage().getGraphicalViewer().select(e);
            }
        } catch (Exception ex) // defense in depth.
        {

        }

        // Tell to listeners that the current page changed
        firePageChanged();

        // refresh content depending on current page
        currentPageChanged();
    }

    /**
     * Fires that the current page in the editor changed.
     */
    private void firePageChanged() {
        Object[] listeners = pageListeners.getListeners();
        for (int i = 0; i < listeners.length; i++) {
            IPageChangeListener listener = (IPageChangeListener) listeners[i];
            listener.pageChanged();
        }
    }

    /** Refreshes the tab text for all pages */
    protected void refreshPageNames() {
        for (int i = 0; i < editor.getModel().getUrndef().getSpecDiagrams().size(); i++)
            editor.setPageText(i, ((URNmodelElement) editor.getModel().getUrndef().getSpecDiagrams().get(i)).getName());
    }

    /** Delegates to UCMNavMultiPageEditor */
    private void setActivePage(int pageIndex) {
        getEditor().setActivePage(pageIndex);
    }

    /** Delegates to UCMNavMultiPageEditor */
    private void setPageText(int pageIndex, String text) {
        editor.setPageText(pageIndex, text);
    }

    /** Delegates to UCMNavMultiPageEditor */
    private void removePage(int pageIndex) {
        editor.removePage(pageIndex);
    }

    /**
     * Removes all pages and calls createPages().
     */
    protected void recreatePages() {
        while (getPageCount() > 0)
            removePage(0);

        createPages();

        // set the active page (page 0 by default), unless it has already been done
        if (getActivePage() == -1)
            setActivePage(0);
    }

    public void setupDragDropPage() {
        final CTabFolder folder = (CTabFolder) editor.getContainer();
        final Display display = folder.getDisplay();
        final UCMNavMultiPageEditor ed = editor;

        Listener listener = new Listener() {
            boolean drag = false;
            boolean exitDrag = false;
            CTabItem dragItem;

            public void handleEvent(Event e) {
                Point p = new Point(e.x, e.y);
                if (e.type == SWT.DragDetect) {
                    p = folder.toControl(display.getCursorLocation()); // see bug 43251
                }
                switch (e.type) {
                case SWT.DragDetect: {
                    CTabItem item = folder.getItem(p);
                    if (item == null)
                        return;
                    drag = true;
                    exitDrag = false;
                    dragItem = item;
                    break;
                }
                case SWT.MouseEnter:
                    if (exitDrag) {
                        exitDrag = false;
                        drag = e.button != 0;
                    }
                    break;
                case SWT.MouseExit:
                    if (drag) {
                        refreshPageNames();
                        exitDrag = true;
                        drag = false;
                    }
                    break;
                case SWT.MouseUp: {
                    if (!drag)
                        return;
                    handleMouseMove(p);

                    CTabItem item = folder.getItem(p);

                    if (item != null) {
                        // Determine if the user is trying to insert it before or after
                        // the pointed tab.
                        Rectangle rect = item.getBounds();
                        boolean after = p.x > rect.x + rect.width / 2;
                        int index = folder.indexOf(item);

                        int from = folder.indexOf(dragItem);
                        int to = index;
                        if (from < to && !after)
                            index--;
                        else if (from > to && after)
                            index++;
                        // index = after ? index : index - 1;
                        index = Math.min(Math.max(0, index), folder.getItemCount() - 1);

                        to = index;

                        ed.getDelegatingCommandStack().execute(new ChangeUCMDiagramOrderCommand(getModel().getUrndef(), from, to));
                    }
                    // If item is null, then if the mouse is pointing inside folder, but after the last
                    // tab item, then insert the item to the last position.
                    else if (isAtTheEnd(p)) {
                        int from = folder.indexOf(dragItem);
                        int to = folder.getItemCount() - 1;

                        ed.getDelegatingCommandStack().execute(new ChangeUCMDiagramOrderCommand(getModel().getUrndef(), from, to));
                    }
                    refreshPageNames();
                    drag = false;
                    exitDrag = false;
                    dragItem = null;
                    break;
                }
                case SWT.MouseMove: {
                    if (!drag)
                        return;
                    handleMouseMove(p);
                }
                }
            }

            void handleMouseMove(Point p) {
                CTabItem item = folder.getItem(p);
                if (item == null) {
                    refreshPageNames();

                    if (isAtTheEnd(p))
                        item = folder.getItem(folder.getItemCount() - 1);
                    else
                        return;
                }
                Rectangle rect = item.getBounds();
                boolean after = p.x > rect.x + rect.width / 2;

                refreshPageNames();
                if (item != dragItem) {
                    if (after)
                        item.setText(item.getText().replace("-->", "").replace("<--", "") + "-->"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
                    else
                        item.setText("<--" + item.getText().replace("-->", "").replace("<--", "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
                }
            }

            boolean isAtTheEnd(Point p) {
                CTabItem item = folder.getItem(p);
                int maxHeight = folder.getItem(0).getBounds().height;
                int relativeY = p.y - folder.getItem(0).getBounds().y;
                int relativeX = p.x - folder.getBounds().x;

                CTabItem lastItem = folder.getItem(folder.getItemCount() - 1);

                return relativeY >= 0 && relativeY <= maxHeight && p.x > (lastItem.getBounds().x + lastItem.getBounds().width);
            }
        };

        folder.addListener(SWT.DragDetect, listener);
        folder.addListener(SWT.MouseUp, listener);
        folder.addListener(SWT.MouseMove, listener);
        folder.addListener(SWT.MouseExit, listener);
        folder.addListener(SWT.MouseEnter, listener);
    }

    public void setEditor(UCMNavMultiPageEditor editor) {
        this.editor = editor;
    }
}