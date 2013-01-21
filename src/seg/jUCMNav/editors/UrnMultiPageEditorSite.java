package seg.jUCMNav.editors;

import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.part.MultiPageEditorSite;

public class UrnMultiPageEditorSite extends MultiPageEditorSite {

    public UrnMultiPageEditorSite(MultiPageEditorPart multiPageEditor, IEditorPart editor) {
        super(multiPageEditor, editor);
    }

    protected static StructuredSelection globalSelection = null;
    
    protected void handleSelectionChanged(SelectionChangedEvent event) {
        // performance fix for large maps. 
        if (((UCMNavMultiPageEditor)getMultiPageEditor()).getActiveEditor()==getEditor())
            super.handleSelectionChanged(event);
    }
    
    protected void handlePostSelectionChanged(SelectionChangedEvent event) {
        // performance fix for large maps.
        if (((UCMNavMultiPageEditor)getMultiPageEditor()).getActiveEditor()==getEditor())
            super.handlePostSelectionChanged(event);
    }
}
