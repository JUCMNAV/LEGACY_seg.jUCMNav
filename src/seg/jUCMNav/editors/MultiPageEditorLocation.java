package seg.jUCMNav.editors;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.INavigationLocation;
import org.eclipse.ui.NavigationLocation;

import ucm.map.Map;

/**
 * A NavigationLocation for jUCMNav. Memorizes the currently opened page.
 * 
 * Maybe should start memorizing URNspec/IFile path instead of the actual editor.
 * 
 * @author jkealey
 *  
 */
public class MultiPageEditorLocation extends NavigationLocation {

    private String currentMapID;
    private UCMNavMultiPageEditor editor;

    /**
     * @param editorPart
     */
    protected MultiPageEditorLocation(IEditorPart editorPart) {
        super(editorPart);
        this.editor = (UCMNavMultiPageEditor) editorPart;
        update();
    }

    /**
     * 
     * @return returns the map ID for this location. (not relative position)
     */
    public String getCurrentMapID() {
        return currentMapID;
    }

    /**
     * 
     * @return the UCMNavMultiPageEditor that is associated with this location.
     */
    public UCMNavMultiPageEditor getEditor() {
        return editor;
    }

    /**
     * The navigation location stack tries to minimize the number of entries by merging duplicates/similar entries together.
     */
    public boolean mergeInto(INavigationLocation currentLocation) {
        if (currentLocation instanceof MultiPageEditorLocation) {
            if (((MultiPageEditorLocation) currentLocation).getCurrentMapID() == currentMapID)
                return true;
        }
        return false;
    }

    /**
     * Loads the appropriate tab.
     */
    public void restoreLocation() {

        for (int i = 0; i < editor.getModel().getUcmspec().getMaps().size(); i++) {
            Map map = (Map) editor.getModel().getUcmspec().getMaps().get(i);
            if (map.getId().equals(currentMapID)) {
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
        memento.getString("iCurrentPage"); //$NON-NLS-1$

    }

    /**
     * Saves the state in the memento.
     */
    public void saveState(IMemento memento) {
        // can only save page because reference to editor is useless.
        memento.putString("iCurrentPage", currentMapID); //$NON-NLS-1$

    }

    /**
     * @param currentPage
     *            the page that is currently opened
     */
    public void setCurrentMapID(String currentPage) {
        currentMapID = currentPage;
    }

    /**
     * @param editor
     *            the editor in which the page is opened.
     */
    public void setEditor(UCMNavMultiPageEditor editor) {
        this.editor = editor;
    }

    /**
     * update this location by querying the active page
     */
    public void update() {
        if (editor.getCurrentPage() != null)
            currentMapID = ((UcmEditor)editor.getCurrentPage()).getModel().getId();
        else
            currentMapID = null;
    }
}