package seg.jUCMNav.views.search;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.search.internal.ui.text.IFileSearchContentProvider;
import org.eclipse.search.ui.text.AbstractTextSearchResult;

/***
 * Copy of org.eclipse.search.internal.ui.text.FileTableContentProvider
 * 
 * @author jkealey
 * 
 */
public class UrnTableContentProvider implements IStructuredContentProvider, IFileSearchContentProvider {
    private final Object[] EMPTY_ARR = new Object[0];

    private UrnTextSearchViewPage fPage;
    private AbstractTextSearchResult fResult;

    public UrnTableContentProvider(UrnTextSearchViewPage page) {
        fPage = page;
    }

    public void clear() {
        getViewer().refresh();
    }

    public void dispose() {
    }

    public void elementsChanged(Object[] updatedElements) {
        TableViewer viewer = getViewer();
        int elementLimit = getElementLimit();
        boolean tableLimited = elementLimit != -1;
        for (int i = 0; i < updatedElements.length; i++) {
            if (fResult.getMatchCount(updatedElements[i]) > 0) {
                if (viewer.testFindItem(updatedElements[i]) != null)
                    viewer.update(updatedElements[i], null);
                else {
                    if (!tableLimited || viewer.getTable().getItemCount() < elementLimit)
                        viewer.add(updatedElements[i]);
                }
            } else
                viewer.remove(updatedElements[i]);
        }
    }

    private int getElementLimit() {
        return fPage.getElementLimit().intValue();
    }

    public Object[] getElements(Object inputElement) {
        if (inputElement instanceof UrnSearchResult) {
            int elementLimit = getElementLimit();
            Object[] elements = ((UrnSearchResult) inputElement).getElements();
            if (elementLimit != -1 && elements.length > elementLimit) {
                Object[] shownElements = new Object[elementLimit];
                System.arraycopy(elements, 0, shownElements, 0, elementLimit);
                return shownElements;
            }
            return elements;
        }
        return EMPTY_ARR;
    }

    private TableViewer getViewer() {
        return (TableViewer) fPage.getViewer();
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        if (newInput instanceof UrnSearchResult) {
            fResult = (UrnSearchResult) newInput;
        }
    }

}
