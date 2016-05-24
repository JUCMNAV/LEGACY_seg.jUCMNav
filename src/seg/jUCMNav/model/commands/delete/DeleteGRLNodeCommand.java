/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import fm.Feature;
import grl.GRLNode;
import grl.IntentionalElementRef;
import grl.kpimodel.KPIInformationElementRef;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.delete.internal.PreDeleteUrnModelElementCommand;
import seg.jUCMNav.model.commands.delete.internal.RemoveURNmodelElementCommand;
import seg.jUCMNav.model.util.MetadataHelper;
import seg.jUCMNav.strategies.util.ReusedElementUtil;
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
        // wonder if the code of disabling the deletion of the last reference to the
        // last root feature should display here
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.CompoundCommand#execute()
     */
    public void execute() {
        if (ref instanceof IntentionalElementRef) {

            IntentionalElementRef intElement = (IntentionalElementRef) ref;

            // Verify if this reference is the only one and it is not a reused element since the
            String value= MetadataHelper.getMetaData( (intElement).getDef(), "CoURN");  //$NON-NLS-1$
            
            boolean rootFeature= (value != null && intElement.getDef() instanceof Feature &&
         		   value.equalsIgnoreCase("root feature"));  //$NON-NLS-1$
           
             if (!ReusedElementUtil.isReusedElement(intElement.getDiagram().getUrndefinition().getUrnspec()
             		.getGrlspec(), intElement.getDef()) && intElement.getDef().getRefs().size() == 1 
             		&& DeletePreferences.getDeleteDefinition(ref) && !rootFeature) {
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

    public boolean canExecute(){
    	if (ref instanceof IntentionalElementRef){
    		 IntentionalElementRef intElement = (IntentionalElementRef) ref;
    		 String value= MetadataHelper.getMetaData( (intElement).getDef(), "CoURN");
    		 boolean rootFeature= (value != null && intElement.getDef() instanceof Feature &&
    	     		   value.equalsIgnoreCase("root feature"));
    		 if (rootFeature==true && intElement.getDef().getRefs().size()==1)
    			 return false;
    	}
    	return true;
     }
}
