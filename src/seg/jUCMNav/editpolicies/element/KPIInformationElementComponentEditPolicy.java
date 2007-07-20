package seg.jUCMNav.editpolicies.element;

import grl.kpimodel.KPIInformationElement;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteKPIInformationElementCommand;

/**
 * ComponentEditPolicy for KPIInformationElement. return the delete command
 * 
 * @author pchen
 * 
 */
public class KPIInformationElementComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Return a DeleteKPIInformationElementCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object elem = getHost().getModel();
        if (elem instanceof KPIInformationElement) {

            KPIInformationElement kpiInformationElem = (KPIInformationElement) elem;
            if (kpiInformationElem.getRefs().size() == 0) {
                DeleteKPIInformationElementCommand deleteCommand = new DeleteKPIInformationElementCommand(kpiInformationElem);
                return deleteCommand;
            } else
                return null;
        }

        return null;
    }

}
