package seg.jUCMNav.views.wizards;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.views.UCMPerspectiveFactory;
import seg.jUCMNav.views.wizards.importexport.jUCMNavLoader;

/**
 * This wizard will create a new .jUCM file and open the UCMNavMultiPageEditor.
 * 
 * @author etremblay
 */
public class NewUcmFileWizard extends Wizard implements INewWizard {
    private NewUcmFileWizardPage page;
    private ISelection selection;

    /**
     * The workbench page in which we are working
     */
    protected IWorkbenchPage workbenchPage;

    /**
     * Constructor for NewUcmFileWizard.
     */
    public NewUcmFileWizard() {
        super();
        setNeedsProgressMonitor(true);
        this.setWindowTitle(Messages.getString("NewUcmFileWizard.newJUCMFile")); //$NON-NLS-1$
    }

    /**
     * Adding the page to the wizard.
     */

    public void addPages() {
        page = new NewUcmFileWizardPage(selection);
        addPage(page);
    }

    /**
     * This method is called when 'Finish' button is pressed in the wizard. We will create an operation and run it using wizard as execution context.
     */
    public boolean performFinish() {
        page.preFinish();
        final String containerName = page.getContainerName();
        final String fileName = page.getFileName();
        IRunnableWithProgress op = new IRunnableWithProgress() {
            public void run(IProgressMonitor monitor) throws InvocationTargetException {
                try {
                    doFinish(containerName, fileName, monitor);
                } catch (CoreException e) {
                    throw new InvocationTargetException(e);
                } finally {
                    monitor.done();
                }
            }
        };
        try {
            getContainer().run(true, false, op);
        } catch (InterruptedException e) {
            return false;
        } catch (InvocationTargetException e) {
            Throwable realException = e.getTargetException();
            MessageDialog.openError(getShell(), Messages.getString("NewUcmFileWizard.error"), realException.getMessage()); //$NON-NLS-1$
            return false;
        }
        return true;
    }

    /**
     * The worker method. It will find the container, create the file if missing or just replace its contents, and open the editor on the newly created file.
     */

    private void doFinish(String containerName, String fileName, IProgressMonitor monitor) throws CoreException {
        // create a sample file
        monitor.beginTask(Messages.getString("NewUcmFileWizard.creating") + fileName, 2); //$NON-NLS-1$
        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IResource resource = root.findMember(new Path(containerName));
        if (resource == null || !resource.exists() || !(resource instanceof IContainer)) {
            throwCoreException(Messages.getString("NewUcmFileWizard.container") + containerName + Messages.getString("NewUcmFileWizard.doesNotExist")); //$NON-NLS-1$ //$NON-NLS-2$
        }
        // IContainer container = (IContainer) resource;

        // final IFile file = container.getFile(new Path(fileName));
        try {
            jUCMNavLoader loader = new jUCMNavLoader(workbenchPage, getShell());
            loader.createAndOpenFile(fileName, containerName, ModelCreationFactory.getNewURNspec(), false, page.overwrite);

            // InputStream stream = openContentStream();
            // if (file.exists()) {
            // file.setContents(stream, true, true, monitor);
            // } else {
            // file.create(stream, true, monitor);
            // }
            // stream.close();
            // } catch (IOException e) {
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        monitor.worked(1);
        monitor.setTaskName(Messages.getString("NewUcmFileWizard.openingForEditing")); //$NON-NLS-1$
        getShell().getDisplay().asyncExec(new Runnable() {
            public void run() {
                // IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                try {
                    // IDE.openEditor(page, file, true);
                    PlatformUI.getWorkbench().showPerspective(UCMPerspectiveFactory.JUCMNAV_PERSPECTIVE_ID,
                            PlatformUI.getWorkbench().getActiveWorkbenchWindow());

                } catch (PartInitException e) {
                    e.printStackTrace();
                } catch (WorkbenchException e) {
                }
            }
        });
        monitor.worked(1);
    }

    /**
     * We will initialize file contents with a sample text.
     */

    // private InputStream openContentStream() {
    //		String contents = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><urn:URNspec xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:urn=\"http:///urn.ecore\" xmlns:ucm.map=\"http:///ucm/map.ecore\" xmlns:urncore=\"http:///urncore.ecore\">\n<urndef>\n<specDiagrams xsi:type=\"ucm.map:UCMmap\" id=\"2\" name=\"UCMmap\"/>\n</urndef>\n</urn:URNspec>"; //$NON-NLS-1$
    // return new ByteArrayInputStream(contents.getBytes());
    // }

    private void throwCoreException(String message) throws CoreException {
        IStatus status = new Status(IStatus.ERROR, "seg.jUCMNav", IStatus.OK, message, null); //$NON-NLS-1$
        throw new CoreException(status);
    }

    /**
     * We will accept the selection in the workbench to see if we can initialize from it.
     * 
     * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
     */
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.selection = selection;
        this.workbenchPage = workbench.getActiveWorkbenchWindow().getActivePage();
    }
}