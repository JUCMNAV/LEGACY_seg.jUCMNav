package seg.jUCMNav.editpolicies.element;

import grl.kpimodel.QualitativeMapping;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import seg.jUCMNav.model.commands.delete.DeleteQualitativeMappingCommand;

/**
 * ComponentEditPolicy for QualitativeMapping. Return the delete command
 * 
 * @author jkealey
 * 
 */
public class QualitativeMappingComponentEditPolicy extends ComponentEditPolicy {

    /**
     * Return a DeleteQualitativeMappingCommand
     */
    protected Command getDeleteCommand(GroupRequest request) {
        Object obj = getHost().getModel();
        if (obj instanceof QualitativeMapping) {
            QualitativeMapping conv = (QualitativeMapping) obj;
            DeleteQualitativeMappingCommand deleteCommand = new DeleteQualitativeMappingCommand(conv);
            return deleteCommand;
        }
        return null;
    }
}
