package seg.jUCMNav.actions;

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.model.commands.create.CreateLabelCommand;
import ucm.map.EndPoint;
import ucm.map.FailurePoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import ucm.map.WaitingPlace;
import urncore.UCMmodelElement;

/**
 * Adds a label to a PathNode or ComponentRef.
 * 
 * @author Jordan
 */
public class AddConditionLabelAction extends URNSelectionAction {
    public static final String ADDLABEL = "seg.jUCMNav.AddConditionLabel"; //$NON-NLS-1$

    /**
     * @param part
     */
    public AddConditionLabelAction(IWorkbenchPart part) {
        super(part);
        setId(ADDLABEL);
        setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/label.gif")); //$NON-NLS-1$
    }

    /**
     * can only add labels if none already exist.
     * 
     * @return true, if calculate enabled
     */
    protected boolean calculateEnabled() {
        List parts = getSelectedObjects();
        if (parts.size() == 1 && parts.get(0) instanceof EditPart) {
            EditPart part = (EditPart) parts.get(0);

            if ((part.getModel() instanceof NodeConnection)) {
                NodeConnection nc = (NodeConnection) part.getModel();
                return nc.getCondition() != null && (nc.getCondition().getLabel() == null || nc.getCondition().getLabel().length() == 0);
            } else if (part.getModel() instanceof StartPoint) {
                StartPoint point = (StartPoint) part.getModel();
                return point.getPrecondition() != null && (point.getPrecondition()).getLabel() == null || point.getPrecondition().getLabel().length() == 0;
            } else if (part.getModel() instanceof EndPoint) {
                EndPoint point = (EndPoint) part.getModel();
                return (point.getPostcondition() != null && (point.getPostcondition()).getLabel() == null || point.getPostcondition().getLabel().length() == 0);

            } else if ((part.getModel() instanceof OrFork) || (part.getModel() instanceof WaitingPlace) || (part.getModel() instanceof FailurePoint)) {
                for (Iterator iter = ((PathNode) part.getModel()).getSucc().iterator(); iter.hasNext();) {
                    NodeConnection nc = (NodeConnection) iter.next();
                    if (nc.getCondition() != null && (nc.getCondition().getLabel() == null || nc.getCondition().getLabel().length() == 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * @return a {@link CreateLabelCommand} adapted to the situation.
     */
    protected Command getCommand() {
        List parts = getSelectedObjects();
        EditPart part = (EditPart) parts.get(0);

        if ((part.getModel() instanceof NodeConnection)) {
            NodeConnection nc = (NodeConnection) part.getModel();
            if (nc.getCondition() != null && (nc.getCondition().getLabel() == null || nc.getCondition().getLabel().length() == 0))
                return new CreateLabelCommand(nc.getCondition());
        } else if (part.getModel() instanceof StartPoint) {
            StartPoint point = (StartPoint) part.getModel();
            if (point.getPrecondition() != null && (point.getPrecondition()).getLabel() == null || point.getPrecondition().getLabel().length() == 0) {
                return new CreateLabelCommand(point.getPrecondition());
            }
        } else if (part.getModel() instanceof EndPoint) {
            EndPoint point = (EndPoint) part.getModel();
            if (point.getPostcondition() != null && (point.getPostcondition()).getLabel() == null || point.getPostcondition().getLabel().length() == 0) {
                return new CreateLabelCommand(point.getPostcondition());
            }
        } else if ((part.getModel() instanceof OrFork) || (part.getModel() instanceof WaitingPlace) || (part.getModel() instanceof FailurePoint)) {
            UCMmodelElement modelElement = (UCMmodelElement) part.getModel();

            CompoundCommand cmd = new CompoundCommand();
            for (Iterator iter = ((PathNode) part.getModel()).getSucc().iterator(); iter.hasNext();) {
                NodeConnection nc = (NodeConnection) iter.next();
                if (nc.getCondition() != null && (nc.getCondition().getLabel() == null || nc.getCondition().getLabel().length() == 0)) {
                    cmd.add(new CreateLabelCommand(nc.getCondition()));
                }
            }
            return cmd;
        }

        return null;
    }

}