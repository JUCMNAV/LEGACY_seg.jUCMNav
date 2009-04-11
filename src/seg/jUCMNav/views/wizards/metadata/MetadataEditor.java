package seg.jUCMNav.views.wizards.metadata;

import grl.Actor;
import grl.ActorRef;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
import ucm.map.ComponentRef;
import ucm.map.RespRef;
import urn.URNspec;
import urncore.Component;
import urncore.Metadata;
import urncore.Responsibility;
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
        for (Iterator iter = metadataMap.entrySet().iterator(); iter.hasNext();) {
            Map.Entry entry = (Map.Entry) iter.next();
            cmd.add(new ChangeMetadataCommand((EObject) entry.getKey(), (Metadata[]) entry.getValue()));
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

        // This code prevents the addition of metadata on *references* to intentional elements
        // and components. This was also prevented for responsibility references in the past
        // but this had to be allowed for CSM export.
        // CHANGED on April 10, 2009: No longer allowed for resp. references... Too annoying.
        if (defaultSelected instanceof IntentionalElementRef) {
            IntentionalElement intentionalElem = ((IntentionalElementRef) defaultSelected).getDef();
            this.defaultSelected = intentionalElem;
        } else if (defaultSelected instanceof RespRef) {
        	Responsibility respElem = ((RespRef) defaultSelected).getRespDef();
            this.defaultSelected = respElem;
        } else if (defaultSelected instanceof KPIInformationElementRef) {
            KPIInformationElement kpiInformationElem = ((KPIInformationElementRef) defaultSelected).getDef();
            this.defaultSelected = kpiInformationElem;
        } else if (defaultSelected instanceof ComponentRef) {
            Component compElem = (Component) ((ComponentRef) defaultSelected).getContDef();
            this.defaultSelected = compElem;
        } else if (defaultSelected instanceof ActorRef) {
            Actor actorElem = (Actor) ((ActorRef) defaultSelected).getContDef();
            this.defaultSelected = actorElem;
        } else {
            this.defaultSelected = defaultSelected;
        }

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
                    while (urnIter.hasNext()) {
                        Object obj = urnIter.next();

                        if (obj instanceof URNmodelElement) {
                            URNmodelElement urnelem = (URNmodelElement) obj;

                            // This code prevents the addition of metadata on *references* to intentional elements
                            // and components. This was also prevented for responsibility references in the past
                            // but this had to be allowed for CSM export.
                            // CHANGED on April 10, 2009: No longer allowed for resp. references... Too annoying.
                            if (urnelem instanceof IntentionalElementRef) {
                            } else if (urnelem instanceof KPIInformationElementRef) {
                            } else if (urnelem instanceof ActorRef) {
                            } else if (urnelem instanceof ComponentRef) {
                            } else if (urnelem instanceof RespRef) {
                            } else {
                                v.add(urnelem);
                            }
                        }
                    }
                }

            }

            this.selection = new StructuredSelection(v);
        }
    }

}
