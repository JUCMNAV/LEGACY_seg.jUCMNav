package seg.jUCMNav.editors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.util.ListenerList;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;

import seg.jUCMNav.editparts.MapAndPathGraphEditPart;
import seg.jUCMNav.editparts.treeEditparts.URNspecTreeEditPart;
import seg.jUCMNav.editparts.treeEditparts.UcmModelElementTreeEditPart;
import ucm.map.Map;
import urn.URNspec;

/**
 * Manages tab creation/addition/removal for the editor. Originally included in the UCMNavMultiPageEditor, this code was factored out.
 * 
 * @author jkealey
 *  
 */
public class MultiPageTabManager {

    private ListenerList pageListeners = new ListenerList(3);

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
                if (((StructuredSelection) selection).getFirstElement() instanceof UcmModelElementTreeEditPart) {
                    UcmModelElementTreeEditPart selectedPart = (UcmModelElementTreeEditPart) ((StructuredSelection) selection).getFirstElement();

                    if (!(selectedPart instanceof URNspecTreeEditPart)) {
                        Map selectedMap = selectedPart.getContainingMap();
                        if (getActivePage() != getMaps().indexOf(selectedMap) && getMaps().indexOf(selectedMap) >= 0) {
                            setActivePage(getMaps().indexOf(selectedMap));
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

        for (int i = 0; i < getMaps().size(); i++) {

            UcmEditor u = new UcmEditor(getEditor());
            u.setModel(getMap(i));

            addPage(u, editor.getEditorInput());

            // add command stacks
            editor.getMultiPageCommandStackListener().addCommandStack(u.getCommandStack());

            setPageText(getPageCount() - 1, getMap(i).getName());

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
    private Map getMap(int i) {
        return (Map) getMaps().get(i);
    }

    /** Delegates to UCMNavMultiPageEditor */
    private EList getMaps() {
        return getModel().getUcmspec().getMaps();
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
        // we want the outline to know that we've selected another map.
        MapAndPathGraphEditPart mappart = (MapAndPathGraphEditPart) editor.getCurrentPage().getGraphicalViewer().getEditPartRegistry().get(
                ((UcmEditor)editor.getCurrentPage()).getModel());

        // I don't know why we flush() but etremblay did it in his code
        editor.getCurrentPage().getGraphicalViewer().flush();

        // select the background map
        editor.getCurrentPage().getGraphicalViewer().select(mappart);

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
        for (int i = 0; i < editor.getModel().getUcmspec().getMaps().size(); i++)
            editor.setPageText(i, ((Map) editor.getModel().getUcmspec().getMaps().get(i)).getName());

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
}