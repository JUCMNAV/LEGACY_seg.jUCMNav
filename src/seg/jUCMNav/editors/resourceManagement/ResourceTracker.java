package seg.jUCMNav.editors.resourceManagement;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import urn.URNspec;

/**
 * This class listens to changes to the file system in the workspace, and makes changes accordingly. 1) An open, saved file gets deleted -> close the editor 2)
 * An open file gets renamed or moved -> change the editor's input accordingly
 * 
 * @author Gunnar Wagenknecht, Jean-François Roy
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

    /**
     * Closes the editor for deleted files.
     * 
     * If a file is moved, updates the editors.
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
                        // editor.doSave(null);
                        URNspec spec = editor.getModel();
                        editor.setInput(newFile, spec);
                    }
                });
            }
        } else if ((delta.getKind() == IResourceDelta.CHANGED) && ((delta.getFlags() & IResourceDelta.CONTENT) != 0)) {
            // Content changed
            QualifiedName timestamp = new QualifiedName(null, "ModificationDate"); //$NON-NLS-1$
            try {
                editor.setInput(editor.getEditorInput());
                IResource res = delta.getResource();
                Long modificationTime = (Long) res.getSessionProperty(timestamp);
                // Verify that the refresh on the editor have not been done before
                if ((modificationTime == null) || (res.getModificationStamp() != modificationTime.longValue())) {
                    if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() == null)
                        return false;
                    IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                    IEditorReference[] edref = page.getEditorReferences();
                    String filename = delta.getResource().getName();

                    boolean useranswer = true;
                    int numbereditor = 0;
                    int i = 0;
                    // Verify if there are more than 1 editor who edit the same file
                    while ((i < edref.length) && (numbereditor < 2)) {
                        if (filename.equals(edref[i].getName())) {
                            numbereditor++;
                            if (numbereditor == 2) {
                                if (MessageDialog
                                        .openQuestion(editor.getSite().getShell(),
                                                Messages.getString("ResourceTracker.MultipleEditorsTitle") + filename, //$NON-NLS-1$
                                                Messages.getString("ResourceTracker.MultipleEditorsText") + filename + Messages.getString("ResourceTracker.MultipleEditorsText2"))) { //$NON-NLS-1$ //$NON-NLS-2$
                                    // For each editor bind to the changed file, refresh the model and recreates the pages
                                    for (int j = 0; j < edref.length; j++) {
                                        if (filename.equals(edref[j].getName())) {
                                            UCMNavMultiPageEditor multieditor = (UCMNavMultiPageEditor) edref[j].getEditor(false);
                                            
                                            if (multieditor!=null) {
                                                multieditor.setModel(multieditor.getFileManager()
                                                        .create(((FileEditorInput) multieditor.getEditorInput()).getFile()));
                                                multieditor.recreatePages();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        i++;
                    }

                    res.setSessionProperty(timestamp, new Long(res.getModificationStamp()));
                }
            } catch (CoreException e) {
                JUCMNavPlugin.getDefault().getLog().log(e.getStatus());
                e.printStackTrace();
            }

        }
        return false;
    }
}