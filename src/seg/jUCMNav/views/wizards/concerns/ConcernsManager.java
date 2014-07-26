package seg.jUCMNav.views.wizards.concerns;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.concerns.AssignConcernDiagramCommand;
import seg.jUCMNav.model.commands.concerns.CreateConcernCommand;
import seg.jUCMNav.model.commands.concerns.DeleteConcernCommand;
import seg.jUCMNav.model.commands.concerns.UpdateConcernCommand;
import urn.URNspec;
import urncore.Concern;
import urncore.IURNDiagram;
import urncore.URNdefinition;
import urncore.URNmodelElement;

/**
 * Wizard for managing concerns
 * 
 * @author gunterm
 */
public class ConcernsManager extends Wizard {
    // the actual UI
    private ConcernsManagerPage page;
    // the following constitute the portion of the URN model which is passed to the ConcernsManagerPage
    private List concerns;
    private List diagrams;
    private URNmodelElement defaultSelected;
    // required to add new concerns
    private URNspec urn;
    // The workbench page in which we are working
    protected IWorkbenchPage workbenchPage;

    /**
     * Creates the editor
     */
    public ConcernsManager() {
        super();
        setNeedsProgressMonitor(true);
        this.setWindowTitle(Messages.getString("ConcernsManager.ConcernManager")); //$NON-NLS-1$
    }

    /**
     * Adding the page to the wizard.
     */
    public void addPages() {
        page = new ConcernsManagerPage(concerns, diagrams, defaultSelected);
        addPage(page);
    }

    /**
     * This method is called when the 'Finish' button is pressed in the wizard. Gets the updates from UpdatedConcern/UpdatedDiagram and creates the commands to
     * change the URN model according to the updates.
     */
    public boolean performFinish() {
        CommandStack cs = ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();
        CompoundCommand cmd = new CompoundCommand();
        cmd.setLabel(Messages.getString("ConcernsManager.ManageConcerns")); //$NON-NLS-1$
        // first, update already existing concerns - does not include new and deleted concerns,
        // only name/description but not diagrams are changed
        for (Iterator iter = UpdatedConcern.getChangedConcerns().iterator(); iter.hasNext();) {
            UpdatedConcern uConcern = (UpdatedConcern) iter.next();
            cmd.add(new UpdateConcernCommand(uConcern.getOriginal(), uConcern.getName(), uConcern.getDescription()));
        }
        // then assign concerns to diagrams (affects already existing and deleted concerns) - therefore
        // has to be done before deleting concerns (and also before creating new concerns - see below)
        for (Iterator iter = UpdatedDiagram.getChangedDiagrams().iterator(); iter.hasNext();) {
            UpdatedDiagram uDiagram = (UpdatedDiagram) iter.next();
            // catch the case when a concern is removed
            if (uDiagram.getUpdatedConcern() == null)
                cmd.add(new AssignConcernDiagramCommand(uDiagram.getOriginal(), null));
            // only update existing concerns, new concerns will be dealt with later
            else if (uDiagram.getUpdatedConcern().getOriginal() != null)
                cmd.add(new AssignConcernDiagramCommand(uDiagram.getOriginal(), uDiagram.getUpdatedConcern().getOriginal()));
        }
        // then create any new concerns (sets name, description, and diagrams) - this has to be done
        // after assigning concerns to ensure that a diagram of the new concern is not assigned null
        for (Iterator iter = UpdatedConcern.getNewConcerns().iterator(); iter.hasNext();) {
            UpdatedConcern uConcern = (UpdatedConcern) iter.next();
            cmd.add(new CreateConcernCommand(urn, uConcern.getName(), uConcern.getDescription(), uConcern.getSpecDiagrams()));
        }
        // then delete any concerns - this has to be done after assigning concerns to diagrams as
        // assigning concerns may affect deleted concerns
        for (Iterator iter = UpdatedConcern.getDeletedConcerns().iterator(); iter.hasNext();) {
            UpdatedConcern uConcern = (UpdatedConcern) iter.next();
            cmd.add(new DeleteConcernCommand(uConcern.getOriginal()));
        }
        if (cmd.canExecute())
            cs.execute(cmd);
        // dispose of the the temporary updated model
        disposeUpdatedModel();
        return true;
    }

    /**
     * This method is called when the 'Cancel' button is pressed in the wizard. Ensures that the temporary UpdatedConcerns/UpdatedDiagrams are disposed.
     */
    public boolean performCancel() {
        disposeUpdatedModel();
        return true;
    }

    /**
     * disposes of all temporary wrapper objects that were used as the MODEL for the ConcernManagerPage
     */
    private void disposeUpdatedModel() {
        UpdatedConcern.disposeConcerns();
        UpdatedDiagram.disposeDiagrams();
    }

    /**
     * Accept the selection in the workbench and initialize the manager from it.
     * 
     * @param workbench
     *            the current workbench we are working with
     * @param urn
     *            the URNspec
     * @param defaultSelected
     *            the URNmodelElement currently selected
     */
    public void initialize(IWorkbench workbench, URNspec urn, URNmodelElement defaultSelected) {
        this.workbenchPage = workbench.getActiveWorkbenchWindow().getActivePage();
        this.urn = urn;
        this.defaultSelected = defaultSelected;
        this.concerns = new ArrayList();
        this.diagrams = new ArrayList();
        // populate the concerns and diagrams lists
        URNdefinition urnDef = urn.getUrndef();
        if (urnDef != null) {
            this.concerns = (List) urnDef.getConcerns();
            this.diagrams = (List) urnDef.getSpecDiagrams();
        }
    }
}