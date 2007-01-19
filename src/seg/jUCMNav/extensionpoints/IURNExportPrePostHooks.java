package seg.jUCMNav.extensionpoints;

import org.eclipse.ui.IWorkbenchPage;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;

/**
 * Allows an exporter to do stuff in the UI before/after the export. Because the export is done in another thread, you can't open another editor, for example. 
 * @author jkealey
 * 
 */
public interface IURNExportPrePostHooks {

	void preHook(UCMNavMultiPageEditor editor);
	void postHook(IWorkbenchPage page);
	
}
