package seg.jUCMNav.editpolicies.element;

import grl.kpimodel.KPIConversion;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteKPIConversionCommand;

/**
 * ComponentEditPolicy for KPIConversions. Return the delete command
 * 
 * @author jkealey
 * 
 */
public class KPIConversionComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Return a DeleteKPIConversionCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object obj = getHost().getModel();
        if (obj instanceof KPIConversion) {
            KPIConversion conv = (KPIConversion) obj;
            DeleteKPIConversionCommand deleteCommand = new DeleteKPIConversionCommand(conv);
            return deleteCommand;
        }
        return null;
    }
}
