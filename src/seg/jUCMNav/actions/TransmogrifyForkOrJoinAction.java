package seg.jUCMNav.actions;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.model.commands.transformations.TransmogrifyForkOrJoinCommand;
import ucm.map.PathNode;
import ucm.map.UCMmap;

/**
 * This action exposes a "Convert" action in Fork contextual menus, allowing them to be changed to a Join / Fork. This action is now an abstract base class for
 * TransmogrifyAndForkOrJoinAction and TransmogrifyOrForkOrJoinAction.
 * 
 * @author jpdaigle
 */
public abstract class TransmogrifyForkOrJoinAction extends URNSelectionAction {
    /**
     * @param part
     */
    public TransmogrifyForkOrJoinAction(IWorkbenchPart part) {
        super(part);
    }

    /**
     * @return a {@link TransmogrifyForkOrJoinCommand}
     */
    protected Command getCommand() {
        List parts = getSelectedObjects();
        EditPart part = (EditPart) parts.get(0);
        UCMmap pg = (UCMmap) ((PathNode) part.getModel()).getDiagram();
        TransmogrifyForkOrJoinCommand comm = new TransmogrifyForkOrJoinCommand((PathNode) part.getModel(), pg);
        return comm;
    }

}