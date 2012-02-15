/**
 * 
 */
package seg.jUCMNav.model.commands.delete;

import java.util.Map;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.IDelayedBuildCompoundCommand;
import seg.jUCMNav.model.commands.delete.internal.RemovePathNodeCommand;
import seg.jUCMNav.views.preferences.DeletePreferences;
import ucm.map.PathNode;
import ucm.map.RespRef;
import urncore.Responsibility;

/**
 * This command is only be called from the edit part.
 * 
 * If the path node is a responsibility, and there is no more references, depending of the preferences, it might also delete the definition.
 * 
 * @author jfroy
 * 
 */
public class DeletePathNodeCommand extends CompoundCommand implements IDelayedBuildCompoundCommand {

    private PathNode pathNode;
    private Map editPartRegistry;
    private boolean built = false;
    private boolean replaceWithEmptyPoint = false;

    public DeletePathNodeCommand(PathNode pn, java.util.Map editpartregistry) {
        this.pathNode = pn;
        this.editPartRegistry = editpartregistry;
        this.replaceWithEmptyPoint = false;
        setLabel(Messages.getString("DeletePathNodeCommand.deletePathNode")); //$NON-NLS-1$
    }

    public DeletePathNodeCommand(PathNode pn, java.util.Map editpartregistry, boolean replaceWithEmptyPoint) {
        this.pathNode = pn;
        this.editPartRegistry = editpartregistry;
        this.replaceWithEmptyPoint = replaceWithEmptyPoint;
        setLabel(Messages.getString("DeletePathNodeCommand.deletePathNode")); //$NON-NLS-1$
    }

    /**
     * Returns true even if no commands exist.
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (getCommands().size() == 0) {
            return true;
        }
        return super.canExecute();
    }

    /**
     * Returns true even if no commands exist.
     */
    public boolean canUndo() {
        if (getCommands().size() == 0)
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
    public void build() {
        if (built)
            return;
        built = true;

        add(new RemovePathNodeCommand(pathNode, editPartRegistry, replaceWithEmptyPoint));

        // Verify if it is a responsibility and if the definition can be delete.
        if (pathNode instanceof RespRef && ((RespRef) pathNode).getRespDef() != null)
        {
            Responsibility respDef = ((RespRef) pathNode).getRespDef();
            if (respDef.getRespRefs().size() <= 1 && respDef.getParentBindings().size()==0 /* bug 764*/ && DeletePreferences.getDeleteDefinition(pathNode)) {
                add(new DeleteResponsibilityCommand(respDef));
            }
        }

    }
}
