/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.IntentionalElementRef;
import grl.LinkRef;
import grl.kpimodel.KPIModelLinkRef;

import java.util.Iterator;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import urncore.IURNConnection;

/**
 * This compound command delete all the LinkRef associate with the specified IntentionalElementRef
 * 
 * @author Jean-François Roy, pchen
 * 
 */
public class DeleteAllLinkRefCommand extends CompoundCommand {

    /**
     * 
     */
    public DeleteAllLinkRefCommand(IntentionalElementRef element) {
        setLabel(Messages.getString("DeleteAllLinkRefCommand.deleteAllLinkRefs")); //$NON-NLS-1$
        for (Iterator iter = element.getPred().iterator(); iter.hasNext();) {
            IURNConnection linkref = (IURNConnection) iter.next();

            if (linkref instanceof KPIModelLinkRef) {
                add(new DeleteKPIModelLinkRefCommand((KPIModelLinkRef) linkref));
            } else if (linkref instanceof LinkRef) {
                add(new DeleteLinkRefCommand((LinkRef) linkref));
            }
        }

        for (Iterator iter = element.getSucc().iterator(); iter.hasNext();) {
            LinkRef linkref = (LinkRef) iter.next();
            add(new DeleteLinkRefCommand(linkref));
        }
    }
}
