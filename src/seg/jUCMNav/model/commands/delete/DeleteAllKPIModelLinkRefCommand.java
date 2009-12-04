/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.IntentionalElementRef;
import grl.kpimodel.KPIInformationElementRef;
import grl.kpimodel.KPIModelLinkRef;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;

/**
 * This compound command delete all the KPIModelLinkRef associate with the specified KPIInformationElementRef
 * 
 * @author pchen
 * 
 */
public class DeleteAllKPIModelLinkRefCommand extends CompoundCommand {

    /**
     * 
     */
    public DeleteAllKPIModelLinkRefCommand(KPIInformationElementRef element) {
        setLabel(Messages.getString("DeleteAllKPIModelLinkRefCommand.deleteAllKPIModelLinkRefs")); //$NON-NLS-1$

        for (Iterator iter = element.getSucc().iterator(); iter.hasNext();) {
            KPIModelLinkRef linkref = (KPIModelLinkRef) iter.next();
            add(new DeleteKPIModelLinkRefCommand(linkref));
        }
    }

    /**
     * 
     */
    public DeleteAllKPIModelLinkRefCommand(IntentionalElementRef element) {
        setLabel(Messages.getString("DeleteAllKPIModelLinkRefCommand.deleteAllKPIModelLinkRefs")); //$NON-NLS-1$

        for (Iterator iter = element.getPred().iterator(); iter.hasNext();) {
            KPIModelLinkRef linkref = (KPIModelLinkRef) iter.next();
            add(new DeleteKPIModelLinkRefCommand(linkref));
        }
    }
}
