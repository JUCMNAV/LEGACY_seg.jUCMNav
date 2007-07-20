/**
 * 
 */
package seg.jUCMNav.model.commands.create;

import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.kpimodel.Indicator;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;
import grl.kpimodel.KPIModelLink;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import urn.URNspec;

/**
 * This command create the kpiModelLink and all the KPIModelLinkRef in the graph where the source and destination element ref are.
 * 
 * @author pchen
 * 
 */
public class CreateKPIModelLinkCommand extends CompoundCommand {

    KPIInformationElement src;
    IntentionalElement dest;
    KPIModelLink link;

    /**
     * 
     */
    public CreateKPIModelLinkCommand(URNspec urn, KPIInformationElement source, KPIModelLink link) {
        this.src = source;
        this.link = link;

        if (link instanceof KPIModelLink) {
            add(new AddStandardKPIModelLinkCommand(urn, source, link));
        }
        setLabel(Messages.getString("CreateKPIModelLinkCommand.createKPIModelLink")); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (connectionExist()) {
            return false;
        }
        return super.canExecute();
    }

    /**
     * Verify if a connection already exist between the source kpiinformationelement and destination intentional element
     * 
     * @return true if a connection exist, false if not
     */
    private boolean connectionExist() {
        Iterator iter;
        EList list;

        if (!(dest instanceof Indicator)) {
            return false;
        }

        if (src != null && dest != null && !src.equals(dest)) {
            list = src.getKpiModelLinksSrc();
            for (iter = list.iterator(); iter.hasNext();) {
                KPIModelLink cont = (KPIModelLink) iter.next();
                if (cont.getIndDest() == (Indicator) dest) {
                    return true;
                }
            }
            list = ((Indicator) dest).getKpiModelLinksDest();
            for (iter = list.iterator(); iter.hasNext();) {
                KPIModelLink cont = (KPIModelLink) iter.next();
                if (cont.getKpiInformationElementSrc() == src) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * When ready to execute the command, add the addKPIModelLinkRefCommand to the CompoundCommand
     */
    public void execute() {
        addKPIModelLinkRefCommand();
        super.execute();
    }

    /**
     * Set the target endpoint for the connection.
     * 
     * @param target
     *            that target endpoint (a non-null IntentionalElement instance)
     */
    public void setTarget(IntentionalElement target) {
        this.dest = target;
        for (Iterator iter = getCommands().iterator(); iter.hasNext();) {
            Command cmd = (Command) iter.next();
            if (cmd instanceof AddStandardKPIModelLinkCommand) {
                ((AddStandardKPIModelLinkCommand) cmd).setTarget(target);
            }
        }
    }

    /*
     * Find the pair of kpiInformationElementRef and intentionalElementRef in the same GRLGraph corresponding to the source and destination elements and add
     * command to add KPIModelLinkRef
     */
    private void addKPIModelLinkRefCommand() {
        if (canExecute()) {
            for (Iterator iter = src.getRefs().iterator(); iter.hasNext();) {
                KPIInformationElementRef srcRef = (KPIInformationElementRef) iter.next();
                for (Iterator destiter = dest.getRefs().iterator(); destiter.hasNext();) {
                    IntentionalElementRef destRef = (IntentionalElementRef) destiter.next();
                    if (srcRef.getDiagram().equals(destRef.getDiagram())) {
                        add(new AddKPIModelLinkRefCommand(destRef.getDiagram(), srcRef, destRef, link));
                    }
                }
            }
        }
    }

}
