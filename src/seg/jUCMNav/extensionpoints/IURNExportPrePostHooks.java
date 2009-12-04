package seg.jUCMNav.extensionpoints;

import org.eclipse.ui.IWorkbenchPage;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;

/**
 * Allows an exporter to do stuff in the UI before/after the export. Because the export is done in another thread, you can't open another editor, for example.
 * 
 * @author jkealey
 * 
 */
public interface IURNExportPrePostHooks {

    /**
     * Before the actual export, give us a reference to the current editor.
     * 
     * @param editor
     *            jUCMNav
     */
    void preHook(UCMNavMultiPageEditor editor);

    /**
     * After the export, give us a reference to the workbench page.
     * 
     * @param page
     *            the workbench page.
     */
    void postHook(IWorkbenchPage page);

}
