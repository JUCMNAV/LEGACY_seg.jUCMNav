package seg.jUCMNav.editors;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.INavigationLocation;
import org.eclipse.ui.NavigationLocation;

import urncore.URNmodelElement;

/**
 * A NavigationLocation for jUCMNav. Memorizes the currently opened page.
 * 
 * Maybe should start memorizing URNspec/IFile path instead of the actual editor.
 * 
 * @author jkealey
 * 
 */
public class MultiPageEditorLocation extends NavigationLocation {

    private String currentGraphID;
    private String text;

    // private UCMNavMultiPageEditor editor;

    /**
     * @param editorPart
     */
    protected MultiPageEditorLocation(IEditorPart editorPart) {
        super(editorPart);
        UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) editorPart;
        this.text = editor != null ? editor.getTitle() : ""; //$NON-NLS-1$

        update(editor);
    }

    /**
     * 
     * @return returns the graph ID for this location. (not relative position)
     */
    public String getCurrentGraphID() {
        return currentGraphID;
    }

    /**
     * 
     * @return the UCMNavMultiPageEditor that is associated with this location.
     */
    public UCMNavMultiPageEditor getEditor() {
        return (UCMNavMultiPageEditor) getEditorPart();
    }

    public String getText() {
        // if (getEditorPart() == null)
        //	return ""; //$NON-NLS-1$
        // return getEditorPart().getTitle();
        return text;
    }

    /**
     * The navigation location stack tries to minimize the number of entries by merging duplicates/similar entries together.
     */
    public boolean mergeInto(INavigationLocation currentLocation) {
        if (currentLocation instanceof MultiPageEditorLocation) {
            if (((MultiPageEditorLocation) currentLocation).getCurrentGraphID() != null
                    && ((MultiPageEditorLocation) currentLocation).getCurrentGraphID().equals(currentGraphID))
                return true;
        }
        return false;
    }

    /**
     * Loads the appropriate tab.
     */
    public void restoreLocation() {

        for (int i = 0; i < getEditor().getModel().getUrndef().getSpecDiagrams().size(); i++) {
            URNmodelElement graph = (URNmodelElement) getEditor().getModel().getUrndef().getSpecDiagrams().get(i);
            if (graph.getId().equals(currentGraphID)) {
                ((UCMNavMultiPageEditor) getEditorPart()).setActivePage(i);
                break;
            }
        }

    }

    /**
     * Loads its state from the memento
     * 
     */
    public void restoreState(IMemento memento) {
        // can only load page because reference to editor is useless.
        currentGraphID = memento.getString("iCurrentPage"); //$NON-NLS-1$

    }

    /**
     * Saves the state in the memento.
     */
    public void saveState(IMemento memento) {
        // can only save page because reference to editor is useless.
        memento.putString("iCurrentPage", currentGraphID); //$NON-NLS-1$

    }

    /**
     * @param currentPage
     *            the page that is currently opened
     */
    public void setCurrentGraphID(String currentPage) {
        currentGraphID = currentPage;
    }

    /**
     * update this location by querying the active page
     */
    public void update(UCMNavMultiPageEditor editor) {
        if (editor != null && editor.getCurrentPage() != null && editor.getCurrentPage().getModel() !=null)
            currentGraphID = ((URNmodelElement) editor.getCurrentPage().getModel()).getId();
        else
            currentGraphID = null;
    }

    public void update() {
        update(getEditor());
    }
}