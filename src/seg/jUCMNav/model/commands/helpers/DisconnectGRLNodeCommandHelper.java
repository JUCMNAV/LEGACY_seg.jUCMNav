package seg.jUCMNav.model.commands.helpers;

import grl.GRLNode;
import grl.LinkRef;
import grl.kpimodel.KPIModelLinkRef;

import org.eclipse.emf.common.command.CompoundCommand;

import urncore.IURNConnection;

public class DisconnectGRLNodeCommandHelper extends CompoundCommand {

    
    GRLNode grlnode;

    /**
     * @param element
     *            IntentionalElementRef to disconnect from LinkRef KPIInformationElementRef to disconnect from kpimodellinkref
     */
    public DisconnectGRLNodeCommandHelper(GRLNode element) {
        this.grlnode = element;
    }

    /**
     * Returns true even if no commands exist.
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (getCommandList().size() == 0) {
            return true;
        }
        return super.canExecute();
    }

    /**
     * Returns true even if no commands exist.
     */
    public boolean canUndo() {
        if (getCommandList().size() == 0)
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
                append(new DeleteLinkRefCommandHelper((LinkRef) ref));
            }else if (ref instanceof KPIModelLinkRef) {
                append(new DeleteKPIModelLinkRefCommandHelper((KPIModelLinkRef) ref));
            }
        }
        for (int j = 0; j < grlnode.getPred().size(); j++) {
            IURNConnection ref = (IURNConnection) grlnode.getPred().get(j);
            if (ref instanceof LinkRef) {
                append(new DeleteLinkRefCommandHelper((LinkRef) ref));
            }else if (ref instanceof KPIModelLinkRef) {
                append(new DeleteKPIModelLinkRefCommandHelper((KPIModelLinkRef) ref));
            }
        }
    }
    
}
