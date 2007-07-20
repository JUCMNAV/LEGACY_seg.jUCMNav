/**
 * 
 */
package seg.jUCMNav.model.commands.delete.internal;

import grl.BeliefLink;
import grl.GRLNode;
import grl.LinkRef;
import grl.kpimodel.KPIModelLinkRef;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.delete.DeleteKPIModelLinkRefCommand;
import seg.jUCMNav.model.commands.delete.DeleteLinkRefCommand;
import urncore.IURNConnection;

/**
 * Disconnect a GRLNode from all the connection associate to it. Used in delete of IntentionalElementRef and Belief and KPIInformationElementRef. Add command to
 * delete all the linkref and kpimodellinkref
 * 
 * @author Jean-François Roy, pchen
 * 
 */
public class DisconnectGRLNodeCommand extends CompoundCommand {

    GRLNode grlnode;

    /**
     * @param element
     *            IntentionalElementRef to disconnect from LinkRef KPIInformationElementRef to disconnect from kpimodellinkref
     */
    public DisconnectGRLNodeCommand(GRLNode element) {
        this.grlnode = element;
    }

    /**
     * Returns true even if no commands exist.
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (getCommands().size() == 0) {
            return true;
        }
        return super.canExecute();
    }

    /**
     * Returns true even if no commands exist.
     */
    public boolean canUndo() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canUndo();
    }

    /**
     * Late building
     */
    public void execute() {
        build();
        super.execute();
    }

    /**
     * Builds a sequence of DeleteGRLNodeCommands
     * 
     */
    private void build() {
        for (int i = 0; i < grlnode.getSucc().size(); i++) {
            IURNConnection ref = (IURNConnection) grlnode.getSucc().get(i);
            if (ref instanceof LinkRef) {
                add(new DeleteLinkRefCommand((LinkRef) ref));
            } else if (ref instanceof BeliefLink) {
                add(new RemoveBeliefLinkCommand((BeliefLink) ref));
            } else if (ref instanceof KPIModelLinkRef) {
                add(new DeleteKPIModelLinkRefCommand((KPIModelLinkRef) ref));
            }
        }
        for (int j = 0; j < grlnode.getPred().size(); j++) {
            IURNConnection ref = (IURNConnection) grlnode.getPred().get(j);
            if (ref instanceof LinkRef) {
                add(new DeleteLinkRefCommand((LinkRef) ref));
            } else if (ref instanceof BeliefLink) {
                add(new RemoveBeliefLinkCommand((BeliefLink) ref));
            } else if (ref instanceof KPIModelLinkRef) {
                add(new DeleteKPIModelLinkRefCommand((KPIModelLinkRef) ref));
            }
        }
    }
}
