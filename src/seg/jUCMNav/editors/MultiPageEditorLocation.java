package seg.jUCMNav.editors;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.INavigationLocation;
import org.eclipse.ui.NavigationLocation;

import urncore.URNmodelElement;

/**
 * A NavigationLocation for jUCMNav. Memorizes the currently opened page.
 * 
 * Maybe should start memorizing URNspec/IFile path instead of the actual
 * editor.
 * 
 * @author jkealey
 * 
 */
public class MultiPageEditorLocation extends NavigationLocation {

	private String currentGraphID;
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
		return editor;
	}

	public String getText() {
		if (editor == null)
			return ""; //$NON-NLS-1$
		return editor.getTitle();
	}

	/**
	 * The navigation location stack tries to minimize the number of entries by
	 * merging duplicates/similar entries together.
	 */
	public boolean mergeInto(INavigationLocation currentLocation) {
		if (currentLocation instanceof MultiPageEditorLocation) {
			if (((MultiPageEditorLocation) currentLocation).getCurrentGraphID() == currentGraphID)
				return true;
		}
		return false;
	}

	/**
	 * Loads the appropriate tab.
	 */
	public void restoreLocation() {

		for (int i = 0; i < editor.getModel().getUrndef().getSpecDiagrams().size(); i++) {
			URNmodelElement graph = (URNmodelElement) editor.getModel().getUrndef().getSpecDiagrams().get(i);
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
			currentGraphID = ((URNmodelElement) editor.getCurrentPage().getModel()).getId();
		else
			currentGraphID = null;
	}
}