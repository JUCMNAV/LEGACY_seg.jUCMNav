package seg.jUCMNav.model.commands.helpers;

import grl.GRLNode;
import grl.IntentionalElementRef;
import grl.kpimodel.KPIInformationElementRef;

import org.eclipse.emf.common.command.CompoundCommand;

import seg.jUCMNav.views.preferences.DeletePreferences;

public class DeleteGRLNodeCommandHelper extends CompoundCommand {
    GRLNode ref;

    @Override
    public boolean canExecute() {
        return true;
    
    }
    /**
     * 
     */
    public DeleteGRLNodeCommandHelper(GRLNode ref) {
        this.ref = ref;
     //   setLabel(Messages.getString("DeleteGRLNodeCommand.deleteGrlNode")); //$NON-NLS-1$
        append(new PreDeleteUrnModelElementCommandHelper(ref));
        append(new RemoveURNmodelElementCommandHelper(ref));
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
     /*       
      * && DeletePreferences.getDeleteDefinition(ref)
      * The above code has been commented out for specific reasons. 
       */
            if (intElement.getDef().getRefs().size() == 1 ) {
                // Add a command to delete the intentional element before execution
                append(new DeleteIntentionalElementCommandHelper(intElement.getDef()));
            }
        } else if (ref instanceof KPIInformationElementRef) {
            KPIInformationElementRef element = (KPIInformationElementRef) ref;
            // Verify if this reference is the only one
            if (element.getDef().getRefs().size() == 1 && DeletePreferences.getDeleteDefinition(element)) {
           //     append(new DeleteKPIInformationElementCommandHelper(element.getDef()));
            }
        }
        super.execute();

    }

}
