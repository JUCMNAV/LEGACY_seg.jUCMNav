package seg.jUCMNav.editors.resourceManagement;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;

/**
 * This class listens to changes to the file system in the workspace, and makes changes accordingly. 1) An open, saved file gets deleted -> close the editor 2)
 * An open file gets renamed or moved -> change the editor's input accordingly
 * 
 * @author Gunnar Wagenknecht
 */
public class ResourceTracker implements IResourceChangeListener, IResourceDeltaVisitor {

    UCMNavMultiPageEditor editor;

    public ResourceTracker(UCMNavMultiPageEditor editor) {
        this.editor = editor;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.resources.IResourceChangeListener#resourceChanged(org.eclipse.core.resources.IResourceChangeEvent)
     */
    public void resourceChanged(IResourceChangeEvent event) {
        IResourceDelta delta = event.getDelta();
        try {
            if (delta != null)
                delta.accept(this);
        } catch (CoreException exception) {
            JUCMNavPlugin.getDefault().getLog().log(exception.getStatus());
            exception.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
     */
    public boolean visit(IResourceDelta delta) {
        if (delta == null || !delta.getResource().equals(((IFileEditorInput) editor.getEditorInput()).getFile()))
            return true;

        if (delta.getKind() == IResourceDelta.REMOVED) {
            if ((IResourceDelta.MOVED_TO & delta.getFlags()) == 0) {
                // if the file was deleted
                // NOTE: The case where an open, unsaved file is deleted is being handled by the
                // PartListener added to the Workbench in the initialize() method.
                if (!editor.isDirty())
                    editor.closeEditor(false);
            } else {
                // else if it was moved or renamed
                final IFile newFile = ResourcesPlugin.getWorkspace().getRoot().getFile(delta.getMovedToPath());
                Display display = editor.getSite().getShell().getDisplay();
                display.asyncExec(new Runnable() {
                    public void run() {

                        // added for bug 303
                        try {
                            editor.closeEditor(true);
                            IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

                            IDE.openEditor(page, newFile, true);
                        } catch (PartInitException e) {
                        }
                        // end of addition
                        //editor.setInput(new FileEditorInput(newFile));
                    }
                });
            }
        }
        return false;
    }
}