package seg.jUCMNav.editors;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.INavigationLocation;
import org.eclipse.ui.NavigationLocation;

import ucm.map.Map;

/**
 * Created on 30-May-2005
 * 
 * @author jkealey
 *  
 */
public class MultiPageEditorLocation extends NavigationLocation {

    private String currentMapID;
    private UCMNavMultiPageEditor editor;

    //    private int iPreviousPage;

    /**
     * @param editorPart
     */
    protected MultiPageEditorLocation(IEditorPart editorPart) {
        super(editorPart);
        this.editor = (UCMNavMultiPageEditor) editorPart;
        update();
    }

    public String getCurrentMapID() {
        return currentMapID;
    }

    public UCMNavMultiPageEditor getEditor() {
        return editor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.INavigationLocation#mergeInto(org.eclipse.ui.INavigationLocation)
     */
    public boolean mergeInto(INavigationLocation currentLocation) {
        if (((MultiPageEditorLocation) currentLocation).getCurrentMapID() == currentMapID)
            return true;
        else
            return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.INavigationLocation#restoreLocation()
     */
    public void restoreLocation() {
        //        IWorkbenchPage page = this.editor.getSite().getPage();
        //        page.getNavigationHistory().markLocation(this.editor);

        for (int i = 0; i < editor.getModel().getUcmspec().getMaps().size(); i++) {
            Map map = (Map) editor.getModel().getUcmspec().getMaps().get(i);
            if (map.getId().equals(currentMapID)) {
                ((UCMNavMultiPageEditor) getEditorPart()).setActivePage(i);
                break;
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.INavigationLocation#restoreState(org.eclipse.ui.IMemento)
     */
    public void restoreState(IMemento memento) {
        memento.getString("iCurrentPage");

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.INavigationLocation#saveState(org.eclipse.ui.IMemento)
     */
    public void saveState(IMemento memento) {
        memento.putString("iCurrentPage", currentMapID);

    }

    public void setCurrentMapID(String currentPage) {
        currentMapID = currentPage;
    }

    public void setEditor(UCMNavMultiPageEditor editor) {
        this.editor = editor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.INavigationLocation#update()
     */
    public void update() {
        if (editor.getCurrentPage() != null)
            currentMapID = editor.getCurrentPage().getModel().getId();
        else
            currentMapID = null;
    }
}