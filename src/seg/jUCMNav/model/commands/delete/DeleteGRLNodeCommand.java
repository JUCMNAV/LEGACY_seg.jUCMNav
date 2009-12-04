/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import grl.GRLNode;
import grl.IntentionalElementRef;
import grl.kpimodel.KPIInformationElementRef;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.PreDeleteUrnModelElementCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;
import seg.jUCMNav.views.preferences.DeletePreferences;

/**
 * Delete a GRLNode from a GRLGraph
 * 
 * @author Jean-François Roy
 * 
 */
public class DeleteGRLNodeCommand extends CompoundCommand {

    GRLNode ref;

    /**
     * 
     */
    public DeleteGRLNodeCommand(GRLNode ref) {
        this.ref = ref;
        setLabel(Messages.getString("DeleteGRLNodeCommand.deleteGrlNode")); //$NON-NLS-1$
        add(new PreDeleteUrnModelElementCommand(ref));
        add(new RemoveURNmodelElementCommand(ref));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CompoundCommand#execute()
     */
    public void execute() {
        if (ref instanceof IntentionalElementRef) {

            IntentionalElementRef intElement = (IntentionalElementRef) ref;

            // Verify if this reference is the only one
            if (intElement.getDef().getRefs().size() == 1 && DeletePreferences.getDeleteDefinition(ref)) {
                // Add a command to delete the intentional element before execution
                add(new DeleteIntentionalElementCommand(intElement.getDef()));
            }
        } else if (ref instanceof KPIInformationElementRef) {
            KPIInformationElementRef element = (KPIInformationElementRef) ref;
            // Verify if this reference is the only one
            if (element.getDef().getRefs().size() == 1 && DeletePreferences.getDeleteDefinition(element)) {
                add(new DeleteKPIInformationElementCommand(element.getDef()));
            }
        }
        super.execute();

    }

}
