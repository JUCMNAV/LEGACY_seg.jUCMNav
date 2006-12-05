package seg.jUCMNav.views.wizards.metadata;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.metadata.ChangeMetadataCommand;
import urn.URNspec;
import urncore.Metadata;
import urncore.URNmodelElement;

/**
 * Wizard for editing a responsibility's code or a condition.
 * 
 * @author pchen
 */
public class MetadataEditor extends Wizard {

    private MetadataEditorPage page;
    private ISelection selection;
    private EObject defaultSelected;

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
        page = new MetadataEditorPage(selection, defaultSelected);
        addPage(page);
    }

    /**
     * This method is called when 'Finish' button is pressed in the wizard. We will create an operation and run it using wizard as execution context.
     */
    public boolean performFinish() {
        final HashMap metadataMap = page.getAllMetadata();

        CommandStack cs = ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();

        CompoundCommand cmd = new CompoundCommand();
        for (Iterator iter = metadataMap.keySet().iterator(); iter.hasNext();) {
            EObject obj = (EObject) iter.next();
            cmd.add(new ChangeMetadataCommand(obj, (Metadata[]) metadataMap.get(obj)));
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
        this.workbenchPage = workbench.getActiveWorkbenchWindow().getActivePage();
        this.defaultSelected = defaultSelected;

        if (selection == null) {
            Vector v = new Vector();

            // choose only urn model elements
            if (defaultSelected instanceof URNmodelElement) {
                URNmodelElement defaultUrnelem = (URNmodelElement) defaultSelected;

                EObject o = defaultUrnelem.eContainer();
                URNspec urn = null;
                while (o != null) {
                    if (o instanceof URNspec) {
                        urn = (URNspec) o;
                    }

                    o = o.eContainer();
                }

                if (urn != null) {
                    TreeIterator urnIter = urn.eAllContents();
                    int i = 0;
                    while (urnIter.hasNext()) {
                        Object obj = urnIter.next();

                        if (obj instanceof URNmodelElement) {
                            i++;
                            URNmodelElement urnelem = (URNmodelElement) obj;
                            v.add(urnelem);
                        }
                    }
                }

            }

            this.selection = new StructuredSelection(v);
        }
    }

}
