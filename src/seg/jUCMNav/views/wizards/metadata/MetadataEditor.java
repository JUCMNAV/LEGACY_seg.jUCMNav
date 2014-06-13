package seg.jUCMNav.views.wizards.metadata;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.metadata.ChangeMetadataCommand;
import seg.jUCMNav.views.property.MetadataRefResolver;
import urncore.Metadata;

/**
 * Wizard for editing a responsibility's code or a condition.
 * 
 * @author pchen
 */
public class MetadataEditor extends Wizard {

    private MetadataEditorPage page;
    private ISelection selection;
    private EObject defaultSelected;
    private MetadataRefResolver resolver = new MetadataRefResolver();
    private EObject eObject;

    /**
     * The workbench page in which we are working
     */
    protected IWorkbenchPage workbenchPage;

    /**
     * Creates the editor
     */
    public MetadataEditor() {
        super();
        setNeedsProgressMonitor(true);
        this.setWindowTitle(Messages.getString("MetadataEditor.MetadataEditor")); //$NON-NLS-1$
    }

    /**
     * Adding the page to the wizard.
     */
    public void addPages() {
        page = new MetadataEditorPage(selection, defaultSelected, eObject != defaultSelected ? eObject : null);
        addPage(page);
    }

    /**
     * This method is called when 'Finish' button is pressed in the wizard. We will create an operation and run it using wizard as execution context.
     */
    public boolean performFinish() {
        final HashMap metadataMap = page.getAllMetadata();

        CommandStack cs = ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();

        CompoundCommand cmd = new CompoundCommand();
        for (Iterator iter = metadataMap.entrySet().iterator(); iter.hasNext();) {
            Map.Entry entry = (Map.Entry) iter.next();
            cmd.add(new ChangeMetadataCommand((EObject) entry.getKey(), (Metadata[]) entry.getValue(), null));
        }

        if (cmd.canExecute()) {
            cs.execute(cmd);
        }

        return true;
    }

    /**
     * Throws an error using the message.
     * 
     * @param message
     *            the error message.
     * @throws CoreException
     *             the generated exception.
     */
    private void throwCoreException(String message) throws CoreException {
        IStatus status = new Status(IStatus.ERROR, "seg.jUCMNav", IStatus.OK, message, null); //$NON-NLS-1$
        throw new CoreException(status);
    }

    /**
     * We will accept the selection in the workbench to see if we can initialize from it.
     * 
     * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
     */
    public void init(IWorkbench workbench, IStructuredSelection selection, EObject defaultSelected) {

        this.selection = selection;
        this.eObject = defaultSelected;
        
        
        this.workbenchPage = workbench.getActiveWorkbenchWindow().getActivePage();

        this.defaultSelected = resolver.getRealObject(defaultSelected);

        this.selection = resolver.adjustSelection(selection, defaultSelected);
    }
}
