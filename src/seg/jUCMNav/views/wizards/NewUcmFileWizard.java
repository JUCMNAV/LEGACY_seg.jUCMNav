package seg.jUCMNav.views.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.core.runtime.*;
import org.eclipse.jface.operation.*;
import java.lang.reflect.InvocationTargetException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;
import java.io.*;
import org.eclipse.ui.*;
import org.eclipse.ui.ide.IDE;

import seg.jUCMNav.Messages;

/**
 * This wizard will create a new .jUCM file and open the UCMNavMultiPageEditor.
 * 
 * @author etremblay
 */
public class NewUcmFileWizard extends Wizard implements INewWizard {
    private NewUcmFileWizardPage page;
    private ISelection selection;

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
        if (!resource.exists() || !(resource instanceof IContainer)) {
            throwCoreException(Messages.getString("NewUcmFileWizard.container") + containerName + Messages.getString("NewUcmFileWizard.doesNotExist")); //$NON-NLS-1$ //$NON-NLS-2$
        }
        IContainer container = (IContainer) resource;
        final IFile file = container.getFile(new Path(fileName));
        try {
            InputStream stream = openContentStream();
            if (file.exists()) {
                file.setContents(stream, true, true, monitor);
            } else {
                file.create(stream, true, monitor);
            }
            stream.close();
        } catch (IOException e) {
        }
        monitor.worked(1);
        monitor.setTaskName(Messages.getString("NewUcmFileWizard.openingForEditing")); //$NON-NLS-1$
        getShell().getDisplay().asyncExec(new Runnable() {
            public void run() {
                IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                try {
                    IDE.openEditor(page, file, true);
                } catch (PartInitException e) {
                }
            }
        });
        monitor.worked(1);
    }

    /**
     * We will initialize file contents with a sample text.
     */

    private InputStream openContentStream() {
        String contents = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><urn:URNspec xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:urn=\"http:///urn.ecore\" xmlns:ucm.map=\"http:///ucm/map.ecore\" xmlns:urncore=\"http:///urncore.ecore\">\n<urndef>\n<specDiagrams xsi:type=\"ucm.map:UCMmap\" id=\"2\" name=\"UCMmap\"/>\n</urndef>\n</urn:URNspec>"; //$NON-NLS-1$
        return new ByteArrayInputStream(contents.getBytes());
    }

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
    }
}