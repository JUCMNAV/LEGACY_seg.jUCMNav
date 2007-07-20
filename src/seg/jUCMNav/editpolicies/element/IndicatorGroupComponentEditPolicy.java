/**
 * 
 */
package seg.jUCMNav.editpolicies.element;

import grl.kpimodel.IndicatorGroup;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteIndicatorGroupCommand;

/**
 * ComponentEditPolicy for IndicatorGroup. Return the delete command
 * 
 * @author pchen
 * 
 */
public class IndicatorGroupComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Return a DeleteIndicatorGroupCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object obj = getHost().getModel();
        if (obj instanceof IndicatorGroup) {

            IndicatorGroup group = (IndicatorGroup) obj;
            if (group.getIndicators().size() == 0) {
                DeleteIndicatorGroupCommand deleteCommand = new DeleteIndicatorGroupCommand(group);
                return deleteCommand;
            } else
                return null;
        }

        return null;
    }
}
