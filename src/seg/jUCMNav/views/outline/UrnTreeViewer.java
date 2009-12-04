package seg.jUCMNav.views.outline;

import org.eclipse.gef.ui.parts.TreeViewer;

public class UrnTreeViewer extends TreeViewer {

    public UrnTreeViewer() {

    }

    // bug 531: causes mucho memory leaks

    /*
     * protected UCMNavMultiPageEditor editor; public UrnTreeViewer(UCMNavMultiPageEditor editor) { this.editor=editor; } public UCMNavMultiPageEditor
     * getEditor() { return editor; } public void setEditor(UCMNavMultiPageEditor editor) { this.editor = editor; }
     */
}
