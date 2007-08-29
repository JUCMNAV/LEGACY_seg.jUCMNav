/**
 * 
 */
package seg.jUCMNav.model.commands.delete.internal;

import grl.kpimodel.KPIInformationElement;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;

/**
 * Delete an KPIInformationElement definition. The definition should have no references
 * 
 * This command should be used in a compound command that also delete all the KPIModelLink associate to the element.
 * 
 * @author pchen
 * 
 */
public class RemoveKPIInformationElementCommand extends Command implements JUCMNavCommand {

    // the KPIInformationElement to delete
    private KPIInformationElement element;

    // the URNspec in which it is contained
    private URNspec urn;

    /**
     * 
     */
    public RemoveKPIInformationElementCommand(KPIInformationElement kpiInformationElement) {
        this.element = kpiInformationElement;
        setLabel(Messages.getString("RemoveKPIInformationElementCommand.removeKPIInformationElement")); //$NON-NLS-1$
    }

    /**
     * Only if not referenced.
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return element != null && element.getRefs().size() == 0;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        // also set the relations
        urn = element.getGrlspec().getUrnspec();

        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        // remove the KPIInformationElement from the urnspec
        urn.getGrlspec().getKpiInformationElements().remove(element);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert element != null && urn != null : "pre something is null"; //$NON-NLS-1$
        assert element.getRefs().size() == 0 : "pre can't delete if still referenced."; //$NON-NLS-1$
        assert urn.getGrlspec().getKpiInformationElements().contains(element) : "pre element in model"; //$NON-NLS-1$

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert element != null && urn != null : "post something is null"; //$NON-NLS-1$
        assert element.getRefs().size() == 0 : "post can't delete if still referenced."; //$NON-NLS-1$
        assert !urn.getGrlspec().getKpiInformationElements().contains(element) : "post element in model"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        // re-add intentionalelement
        urn.getGrlspec().getKpiInformationElements().add(element);

        testPreConditions();
    }
}
