package seg.jUCMNav.views.search;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.search.internal.ui.text.FileMatch;
import org.eclipse.search.ui.ISearchQuery;
import org.eclipse.search.ui.ISearchResult;
import org.eclipse.search.ui.text.AbstractTextSearchResult;
import org.eclipse.search.ui.text.IEditorMatchAdapter;
import org.eclipse.search.ui.text.IFileMatchAdapter;
import org.eclipse.search.ui.text.Match;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;

import urncore.URNmodelElement;

/***
 * Container for the matches that are built while running UrnSearchQuery
 * 
 * @author jkealey
 * 
 */
public class UrnSearchResult extends AbstractTextSearchResult implements ISearchResult, IEditorMatchAdapter, IFileMatchAdapter {
    private final Match[] EMPTY_ARR = new Match[0];

    protected String label;
    protected UrnSearchQuery query;

    public UrnSearchResult(UrnSearchQuery query) {
        this.query = query;
    }

    public UrnSearchResult(UrnSearchQuery query, String label) {
        this.query = query;
        this.label = label;
    }

    public Match addEntry(IFile file, String searchPattern, URNmodelElement modelElement) {
        Match fm;
        if (modelElement != null) // create a specific type of match.
        {
            fm = new UrnFileMatch(file, searchPattern, modelElement);
        } else
            fm = new FileMatch(file);
        addMatch(fm);

        return fm;
    }

    public Match[] computeContainedMatches(AbstractTextSearchResult result, IEditorPart editor) {
        IEditorInput ei = editor.getEditorInput();
        if (ei instanceof IFileEditorInput) {
            IFileEditorInput fi = (IFileEditorInput) ei;
            return getMatches(fi.getFile());
        }
        return EMPTY_ARR;
    }

    public Match[] computeContainedMatches(AbstractTextSearchResult result, IFile file) {
        return getMatches(file);
    }

    public IEditorMatchAdapter getEditorMatchAdapter() {
        return this;
    }

    public IFile getFile(Object element) {
        if (element instanceof IFile)
            return (IFile) element;
        return null;
    }

    public IFileMatchAdapter getFileMatchAdapter() {
        return this;
    }

    public ImageDescriptor getImageDescriptor() {
        return null;
    }

    public String getLabel() {
        return label;
    }

    public ISearchQuery getQuery() {
        return query;
    }

    public String getTooltip() {
        return getLabel();
    }

    public boolean isShownInEditor(Match match, IEditorPart editor) {
        IEditorInput ei = editor.getEditorInput();
        if (ei instanceof IFileEditorInput) {
            IFileEditorInput fi = (IFileEditorInput) ei;
            return match.getElement().equals(fi.getFile());
        }
        return false;
    }

    public void setLabel(String value) {
        label = value;
    }

}
