/**
 * 
 */
package seg.jUCMNav.model.commands.delete.internal;

import grl.IntentionalElement;
import grl.kpimodel.Indicator;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIModelLink;

import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;

/**
 * Removes an KPIModelLink from a GRLGraph (it has at most one ref of the link)
 * 
 * @author pchen
 * 
 */
public class RemoveKPIModelLinkCommand extends Command implements JUCMNavCommand {

    KPIModelLink link;
    URNspec urn;
    KPIInformationElement src;
    IntentionalElement dest;
    boolean aborted = false;

    /**
     * 
     */
    public RemoveKPIModelLinkCommand(KPIModelLink link) {
        this.link = link;
        urn = link.getGrlspec().getUrnspec();
        setLabel(Messages.getString("RemoveKPIModelLinkCommand.removeKPIModelLink")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        dest = link.getIndDest();
        src = link.getKpiInformationElementSrc();
        redo();
    }

    public boolean canExecute() {
        return urn != null && urn.getGrlspec() != null && urn.getGrlspec().getKpiModelLinks().contains(link);
    }

    public KPIModelLink getKPIModelLink() {
        return link;
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        aborted = !canExecute();
        if (aborted)
            return;
        testPreConditions();
        urn.getGrlspec().getKpiModelLinks().remove(link);

        link.setIndDest(null);
        link.setKpiInformationElementSrc(null);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert link != null && urn != null : "Pre something is null"; //$NON-NLS-1$

        assert urn.getGrlspec().getKpiModelLinks().contains(link) : "Pre urn contain KPIModel link"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert link != null && urn != null : "Post something is null"; //$NON-NLS-1$

        assert !urn.getGrlspec().getKpiModelLinks().contains(link) : "Post urn contain KPIModel link"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();
        urn.getGrlspec().getKpiModelLinks().add(link);

        link.setIndDest((Indicator) dest);
        link.setKpiInformationElementSrc(src);
        testPreConditions();
    }
}
