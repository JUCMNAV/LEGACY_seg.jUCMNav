package seg.jUCMNav.views.search;

import java.util.HashMap;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.search.internal.ui.Messages;
import org.eclipse.search.internal.ui.SearchMessages;
import org.eclipse.search.internal.ui.text.DecoratingFileSearchLabelProvider;
import org.eclipse.search.internal.ui.text.EditorOpener;
import org.eclipse.search.internal.ui.text.FileLabelProvider;
import org.eclipse.search.internal.ui.text.FileMatch;
import org.eclipse.search.internal.ui.text.FileSearchPage.DecoratorIgnoringViewerSorter;
import org.eclipse.search.internal.ui.text.IFileSearchContentProvider;
import org.eclipse.search.internal.ui.text.LineElement;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.search.ui.text.AbstractTextSearchResult;
import org.eclipse.search.ui.text.AbstractTextSearchViewPage;
import org.eclipse.search.ui.text.Match;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;

/**
 * Very similar to FileSearchPage
 * 
 * @author jkealey
 * 
 */
public class UrnTextSearchViewPage extends AbstractTextSearchViewPage {
    private static final int DEFAULT_ELEMENT_LIMIT = 1000;
    private EditorOpener fEditorOpener = new EditorOpener();

    public UrnTextSearchViewPage() {
        super(AbstractTextSearchViewPage.FLAG_LAYOUT_TREE);
        setElementLimit(new Integer(DEFAULT_ELEMENT_LIMIT));
    }

    private IFileSearchContentProvider fContentProvider;
    private int fCurrentSortOrder;

    protected void clear() {
        if (fContentProvider != null)
            fContentProvider.clear();
    }

    protected void configureTableViewer(TableViewer viewer) {
        viewer.setUseHashlookup(true);
        FileLabelProvider innerLabelProvider = new FileLabelProvider(this, FileLabelProvider.SHOW_LABEL_PATH);
        viewer.setLabelProvider(new DecoratingFileSearchLabelProvider(innerLabelProvider));
        viewer.setContentProvider(new UrnTableContentProvider(this));
        viewer.setComparator(new DecoratorIgnoringViewerSorter(innerLabelProvider));
        fContentProvider = (IFileSearchContentProvider) viewer.getContentProvider();
    }

    protected void configureTreeViewer(TreeViewer viewer) {
        viewer.setUseHashlookup(true);
        FileLabelProvider innerLabelProvider = new FileLabelProvider(this, FileLabelProvider.SHOW_LABEL_PATH);
        viewer.setLabelProvider(new DecoratingFileSearchLabelProvider(innerLabelProvider));
        viewer.setContentProvider(new UrnTreeContentProvider(this, viewer));
        viewer.setComparator(new DecoratorIgnoringViewerSorter(innerLabelProvider));
        fContentProvider = (IFileSearchContentProvider) viewer.getContentProvider();
    }

    protected void elementsChanged(Object[] objects) {
        if (fContentProvider != null)
            fContentProvider.elementsChanged(objects);
    }

    public StructuredViewer getViewer() {
        return super.getViewer();
    }

    protected void showMatch(Match match, int currentOffset, int currentLength, boolean activate) throws PartInitException {
        if (match instanceof UrnFileMatch) {
            showMatch((UrnFileMatch) match, activate);
        }
    }

    protected void showMatch(UrnFileMatch match, boolean activate) throws PartInitException {
        IFile file = (IFile) match.getFileElement();
        IEditorPart editor = fEditorOpener.open(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), file, activate);
        if (match != null) {
            if (editor instanceof UCMNavMultiPageEditor) {
                showWithMarker((UCMNavMultiPageEditor) editor, match);
            }
        }
    }

    private void showWithMarker(UCMNavMultiPageEditor editor, UrnFileMatch match) throws PartInitException {
        IMarker marker = null;
        try {
            marker = ((IFile) match.getFileElement()).createMarker(NewSearchUI.SEARCH_MARKER);
            HashMap attributes = new HashMap(2);
            // attributes.put(IMarker.CHAR_START, new Integer (offset));
            // attributes.put(IMarker.CHAR_END, new Integer (offset + length));
            attributes.put("EObject", match.getId()); //$NON-NLS-1$
            marker.setAttributes(attributes);
            IDE.gotoMarker(editor, marker);
        } catch (CoreException e) {
            throw new PartInitException("Error", e); //$NON-NLS-1$
        } finally {
            if (marker != null)
                try {
                    marker.delete();
                } catch (CoreException e) {
                    // ignore
                }
        }
    }

    public int getDisplayedMatchCount(Object element) {
        if (showLineMatches()) {
            if (element instanceof LineElement) {
                LineElement lineEntry = (LineElement) element;
                return lineEntry.getNumberOfMatches(getInput());
            }
            return 0;
        }
        return super.getDisplayedMatchCount(element);
    }

    public Match[] getDisplayedMatches(Object element) {
        if (showLineMatches()) {
            if (element instanceof LineElement) {

                LineElement lineEntry = (LineElement) element;
                getDisplayedMatches(lineEntry.getParent());
                return lineEntry.getMatches(getInput());
            }
            return new Match[0];
        }
        return super.getDisplayedMatches(element);
    }

    protected void evaluateChangedElements(Match[] matches, Set changedElements) {
        if (showLineMatches()) {
            for (int i = 0; i < matches.length; i++) {
                changedElements.add(((FileMatch) matches[i]).getLineElement());
            }
        } else {
            super.evaluateChangedElements(matches, changedElements);
        }
    }

    private boolean showLineMatches() {
        AbstractTextSearchResult input = getInput();
        return getLayout() == FLAG_LAYOUT_TREE && input != null && !((UrnSearchQuery) input.getQuery()).isFileNameSearch();
    }

    protected void handleOpen(OpenEvent event) {
        if (showLineMatches()) {
            Object firstElement = ((IStructuredSelection) event.getSelection()).getFirstElement();

            try {
                if (firstElement instanceof LineElement) {
                    LineElement lineElement = (LineElement) firstElement;
                    showMatch(getCurrentMatch(lineElement), true);
                    return;
                } else if (firstElement instanceof IFile) {
                    if (getDisplayedMatchCount(firstElement) == 0) {

                        fEditorOpener.open(getSite().getPage(), (IFile) firstElement, false);

                        return;
                    }
                }
            } catch (PartInitException e) {
                ErrorDialog.openError(getSite().getShell(), SearchMessages.FileSearchPage_open_file_dialog_title,
                        SearchMessages.FileSearchPage_open_file_failed, e.getStatus());
            }
        }
        super.handleOpen(event);
    }

    private UrnFileMatch getCurrentMatch(LineElement lineElement) {
        // getCurrentMatch stopped working (returned null) when we started using getElement as instanceof LineElement.
        // this helps resolve the situation.
        Match[] matches = getInput().getMatches(lineElement);

        for (int i = 0; i < matches.length; i++) {
            Match match = matches[i];
            if (match instanceof UrnFileMatch) {
                UrnFileMatch urnFileMatch = (UrnFileMatch) match;
                if (urnFileMatch.getElement() instanceof LineElement) {
                    if (urnFileMatch.getElement().equals(lineElement)) {
                        return urnFileMatch;
                    }
                }
            }
        }
        return null;
    }

    public String getLabel() {
        String label = super.getLabel();
        StructuredViewer viewer = getViewer();
        if (viewer instanceof TableViewer) {
            TableViewer tv = (TableViewer) viewer;

            AbstractTextSearchResult result = getInput();
            if (result != null) {
                int itemCount = ((IStructuredContentProvider) tv.getContentProvider()).getElements(getInput()).length;
                if (showLineMatches()) {
                    int matchCount = getInput().getMatchCount();
                    if (itemCount < matchCount) {
                        return Messages.format(SearchMessages.FileSearchPage_limited_format_matches, new Object[] { label, new Integer(itemCount),
                                new Integer(matchCount) });
                    }
                } else {
                    int fileCount = getInput().getElements().length;
                    if (itemCount < fileCount) {
                        return Messages.format(SearchMessages.FileSearchPage_limited_format_files, new Object[] { label, new Integer(itemCount),
                                new Integer(fileCount) });
                    }
                }
            }
        }
        return label;
    }
}
