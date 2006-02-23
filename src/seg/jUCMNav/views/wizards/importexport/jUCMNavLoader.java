package seg.jUCMNav.views.wizards.importexport;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.editors.resourceManagement.UrnModelManager;
import seg.jUCMNav.views.wizards.AutoLayoutWizard;
import urn.URNspec;
import urncore.IURNDiagram;

/**
 * This class is intended to be used to create new .jucm files and open them. If
 * necessary, perform autolayout on them.
 * 
 * @author jkealey, Jean-François Roy
 * 
 */
public class jUCMNavLoader {
	protected Shell shell;
	protected IWorkbenchPage page;

	/**
	 * jUCMNavLoader can be reused many times
	 * 
	 * @param page
	 *            in what workbench page to open the editor
	 * @param shell
	 *            required for asynchronous execution
	 * 
	 */
	public jUCMNavLoader(IWorkbenchPage page, Shell shell) {
		setShell(shell);
		setPage(page);
	}

	/**
	 * Given a filename or path, get the name without the extension.
	 * 
	 * @param originalFileName
	 *            filename or path. doesn't need to exist.
	 * @return the name without the extension.
	 */
	protected static String getBaseNameFromFilename(String originalFileName) {
		File f = new File(originalFileName);
		return f.getName().substring(0, f.getName().indexOf('.'));
	}

	/**
	 * Gets the complete filename of the a new resource to be created.
	 * 
	 * @param originalFileName
	 *            the original file name
	 * @param newProject
	 *            the project in which the jucm file should be created
	 * @param overwrite
	 *            should we overwrite a file if it already exists?
	 * @return the full filename to be used as a target
	 */
	public String getTargetFilename(String originalFileName, String newProject, boolean overwrite) {
		//TODO: Test under non-Windows. I think the backslash will cause problems. 
		String base = newProject + "\\" + getBaseNameFromFilename(originalFileName);//$NON-NLS-1$
		String mid = ""; //$NON-NLS-1$
		String extension = ".jucm"; //$NON-NLS-1$
		String filename = base + mid + extension;

		int index = 0;
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(filename));

		// loop until we find an file that is not in use, if we aren't
		// overwriting.
		while (!overwrite && resource != null) {
			mid = "-" + (++index); //$NON-NLS-1$
			filename = base + mid + extension;
			resource = root.findMember(new Path(filename));
		}

		return filename;
	}

	/**
	 * Will create a new file in newProject with the name baseFilename.jucm (or
	 * baseFilename-#.jucm where the # is the first integer for which the
	 * filename doesn't already exist) using the given URNspec.
	 * 
	 * Will not overwrite any existing files.
	 * 
	 * @param originalFileName
	 *            the original file name
	 * @param newProject
	 *            the project in which the jucm file should be created
	 * @param newurn
	 *            the contents of this new file
	 * @throws InvocationTargetException
	 */
	public void createAndOpenFile(String originalFileName, String newProject, URNspec newurn) throws InvocationTargetException {
		createAndOpenFile(originalFileName, newProject, newurn, false, false, false);
	}

	/**
	 * Will create a new file in newProject with the name baseFilename.jucm (or
	 * baseFilename-#.jucm where the # is the first integer for which the
	 * filename doesn't already exist, if overwrite is set to false) using the
	 * given URNspec.
	 * 
	 * Will perform autolayout on all maps if doAutoLayout is true.
	 * 
	 * @param originalFileName
	 *            the original file name
	 * @param newProject
	 *            the project in which the jucm file should be created
	 * @param newurn
	 *            the contents of this new file
	 * @param doAutoLayout
	 *            should the maps be layed out automatically on load? useful
	 *            with reverse engineering
     * @param onlyLastEditor
     *            for the autolayout. If it is true, will autolayout the last editor in the list
	 * @param overwrite
	 *            should we overwrite a file if it already exists?
	 * @throws InvocationTargetException
	 */
	public void createAndOpenFile(String originalFileName, String newProject, URNspec newurn, boolean doAutoLayout, boolean onlyLastEditor, boolean overwrite)
			throws InvocationTargetException {
		// final for asynch thread.
		final boolean autolayout = doAutoLayout;
        
        final boolean lastEditor = onlyLastEditor;

		String filename = getTargetFilename(originalFileName, newProject, overwrite);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();

		// create the file and save.
		UrnModelManager umm = new UrnModelManager();
		umm.createURNspec(new Path(filename), newurn);
		try {
			umm.save(new Path(filename));
		} catch (IOException e) {
			throw new InvocationTargetException(e, Messages.getString("jUCMNavLoader.UnableCreateFile")); //$NON-NLS-1$
		}

		// final for asynch thread. should not be null because we just created
		// it.
		final IResource resource2 = root.findMember(new Path(filename));

		// other thread.
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {

				try {
					// find a pointer to jUCMNav.
					IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().findEditor("seg.jUCMNav.MainEditor"); //$NON-NLS-1$

					// open the newfile using jucmnav.
					UCMNavMultiPageEditor editor = (UCMNavMultiPageEditor) getPage().openEditor(new FileEditorInput((IFile) resource2), desc.getId());                    
                             
					// autolayout
					if (autolayout) {
                        if (lastEditor){
                            doAutolayout(editor, editor.getPageCount()-1);
                        } else{
    						for (int i = 0; i < editor.getPageCount(); i++) {
    							doAutolayout(editor, i);
    						}
                        }

						// save the file after autolayout.
						editor.getFileManager().doSave(new NullProgressMonitor());
					}

				} catch (Exception e) {
					// can't because the asynch thread doesn't throw errors.
					// throw new InvocationTargetException(e, "Error opening
					// file.");
				}
			}

            /**
             * @param editor
             * @param i
             * @throws InvocationTargetException
             */
            private void doAutolayout(UCMNavMultiPageEditor editor, int i) throws InvocationTargetException {
                UrnEditor urneditor = (UrnEditor) editor.getEditor(i);
                AutoLayoutWizard wizard = new AutoLayoutWizard(urneditor, (IURNDiagram) urneditor.getModel());

                // use settings saved in preferences.
                if (wizard.canFinish()) {
                	try {
                		wizard.performFinish();
                	} catch (Exception ex) {
                		throw new InvocationTargetException(ex, Messages.getString("jUCMNavLoader.UnablePerformAutolayout")); //$NON-NLS-1$
                	}
                } else {
                	throw new InvocationTargetException(new Exception(Messages.getString("jUCMNavLoader.UnablePerformAutolayout"))); //$NON-NLS-1$
                }
            }
		});

	}

	/**
	 * 
	 * @return the shell
	 */
	public Shell getShell() {
		return shell;
	}

	/**
	 * 
	 * @param shell
	 *            the shell
	 */
	public void setShell(Shell shell) {
		this.shell = shell;
	}

	/**
	 * 
	 * @return the workbench page in which to open the editor.
	 */
	public IWorkbenchPage getPage() {
		return page;
	}

	/**
	 * 
	 * @param page
	 *            the workbench page in which to open the editor.
	 */
	public void setPage(IWorkbenchPage page) {
		this.page = page;
	}

}
