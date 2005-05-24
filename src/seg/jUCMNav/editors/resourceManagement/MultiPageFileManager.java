package seg.jUCMNav.editors.resourceManagement;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialogWithToggle;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import urn.URNspec;

/**
 * Created on 24-May-2005
 * 
 * Originally included in the UCMNavMultiPageEditor, this code was factored out.
 * 
 * UCMNavMultiPageEditor delegates file management operations to this class. Saving / creating is handled here.
 * 
 * @author jkealey
 *  
 */
public class MultiPageFileManager {
    /** the managed editor */
    private UCMNavMultiPageEditor editor;

    /** the model manager handles serialization */
    private UrnModelManager modelManager = new UrnModelManager();

    /**
     * 
     * @param editor
     */
    public MultiPageFileManager(UCMNavMultiPageEditor editor) {
        this.editor = editor;
    }

    /**
     * Returns the URNspec object from the specified file.
     * 
     * @param file
     * @return the ucm object from the specified file
     */
    public URNspec create(IFile file) throws CoreException {
        URNspec urn = null;
        modelManager = new UrnModelManager();

        if (file.exists()) {
            try {
                modelManager.load(file.getFullPath());
            } catch (Exception e) {
                modelManager.createURNspec(file.getFullPath());
            }

            urn = modelManager.getModel();
            if (null == urn) {
                throw new CoreException(new Status(IStatus.ERROR, JUCMNavPlugin.PLUGIN_ID, 0, "Error loading the UCM.", null));
            }

        }

        return urn;
    }

    /**
     * Perform a save on the file we are editing.
     * 
     * @see org.eclipse.ui.ISaveablePart#doSave(org.eclipse.core.runtime.IProgressMonitor)
     */
    public void doSave(IProgressMonitor monitor) {
        try {
            IFile file = ((IFileEditorInput) getEditor().getEditorInput()).getFile();
            if (file.exists()
                    || MessageDialogWithToggle.openConfirm(getEditor().getSite().getShell(), "Create File", "The file '" + file.getName()
                            + "' doesn't exist. Click OK to create it.")) {
                save(file, monitor);
                getEditor().getMultiPageCommandStackListener().markSaveLocations();
            }
        } catch (CoreException e) {
            ErrorDialog.openError(getEditor().getSite().getShell(), "Error During Save", "The current UCM model could not be saved.", e.getStatus());
        }
    }

    /**
     * Perform a save as action on the file we are editing.
     * 
     * @see org.eclipse.ui.ISaveablePart#doSaveAs()
     */
    public void doSaveAs() {
        // get the new path
        SaveAsDialog dialog = new SaveAsDialog(getEditor().getSite().getShell());
        dialog.setOriginalFile(((IFileEditorInput) getEditor().getEditorInput()).getFile());
        dialog.open();
        IPath path = dialog.getResult();

        // if the user presses cancel or refuses to overwrite an existing file, null will be returned.
        if (path == null)
            return;
        else if (((IFileEditorInput) getEditor().getEditorInput()).getFile().getFullPath().equals(path)) {
            doSave(new NullProgressMonitor());
            return;
        }

        try {
            // because our modelmanager is tightly coupled with the resource.
            modelManager = new UrnModelManager();

            IWorkspace workspace = ResourcesPlugin.getWorkspace();
            IFile file = workspace.getRoot().getFile(path);

            // we're overwriting the file so get rid of it
            if (file.exists()) {
                file.delete(true, false, new NullProgressMonitor());
            }

            // creates a non existing resource and assigns it our model
            modelManager.createURNspec(path, getEditor().getModel());

            // save the new file
            modelManager.save(path);
            getEditor().getDelegatingCommandStack().markSaveLocation();

            // reinit everything
            getEditor().init(getEditor().getEditorSite(), new FileEditorInput(file));

            getEditor().setMultiPageCommandStackListener(null);

            getEditor().recreatePages();

        } catch (Exception e) {
            ErrorDialog.openError(getEditor().getSite().getShell(), "Error During Save", "The current UCM model could not be saved.", new Status(IStatus.ERROR,
                    "seg.jUCMNav", IStatus.ERROR, "", e));
        }

    }

    /**
     * Returns the editor that is worked on. 
     * @return
     */
    private UCMNavMultiPageEditor getEditor() {
        return editor;
    }

    /**
     * Saves the ucm under the specified file.
     * 
     * @param file
     *            is the file where we want to save this UCM.
     * @param progressMonitor
     *            is the object used to show the progress of the save.
     * @throws CoreException
     */
    private void save(IFile file, IProgressMonitor progressMonitor) throws CoreException {

        if (null == progressMonitor)
            progressMonitor = new NullProgressMonitor();

        progressMonitor.beginTask("Saving " + file, 2);

        if (null == modelManager) {
            IStatus status = new Status(IStatus.ERROR, JUCMNavPlugin.PLUGIN_ID, 0, "No model manager found for saving the file.", null);
            throw new CoreException(status);
        }

        // save URNspec to file
        try {
            modelManager.save(file.getFullPath());
            progressMonitor.worked(1);
            file.refreshLocal(IResource.DEPTH_ZERO, new SubProgressMonitor(progressMonitor, 1));
            progressMonitor.done();
        } catch (FileNotFoundException e) {
            IStatus status = new Status(IStatus.ERROR, JUCMNavPlugin.PLUGIN_ID, 0, "Error writing file.", e);
            throw new CoreException(status);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            IStatus status = new Status(IStatus.ERROR, JUCMNavPlugin.PLUGIN_ID, 0, "Error writing file.", e);
            throw new CoreException(status);
        }
    }
}