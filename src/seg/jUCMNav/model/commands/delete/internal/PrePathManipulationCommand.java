package seg.jUCMNav.model.commands.delete.internal;

import java.util.List;
import java.util.Map;

import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.model.commands.IDelayedBuildCompoundCommand;
import ucm.map.PathNode;

/**
 * Manipulates the path to prepare to remove this PathNode. Disconnects relevant branches and rewires the underlying path if necessary.
 * 
 * @author jkealey
 * 
 */
public class PrePathManipulationCommand extends CompoundCommand implements IDelayedBuildCompoundCommand {
    private boolean rewire = false;
    private List ncIn, ncOut;
    private PathNode pn;
    private Map editpartregistry;
    private boolean built=false;

    /**
     * @param pn
     *            The PathNode to be deleted.
     * @param ncIn
     *            The node connections that enter this PathNode. Pass an empty list if none should be disconnected or null if all should be disconnected.
     * @param ncOut
     *            The node connections that exist this PathNode. Pass an empty list if none should be disconnected or null if all should be disconnected.
     * 
     * @param editpartregistry
     *            The Edit Part Viewer's edit part registry. It is sufficient to pass a map containing only the mapping between NodeConnections and
     *            NodeConnectionEditParts.
     */
    public PrePathManipulationCommand(PathNode pn, List ncIn, List ncOut, Map editpartregistry, boolean rewire) {
        this.pn = pn;
        this.ncIn = ncIn;
        this.ncOut = ncOut;
        this.editpartregistry = editpartregistry;
        this.rewire = rewire;
    }

    /**
     * @param pn
     *            The PathNode to be deleted.
     * @param editpartregistry
     *            The Edit Part Viewer's edit part registry. It is sufficient to pass a map containing only the mapping between NodeConnections and
     *            NodeConnectionEditParts.
     */
    public PrePathManipulationCommand(PathNode pn, Map editpartregistry) {
        this.pn = pn;
        this.editpartregistry = editpartregistry;
        this.rewire = true;
    }

    /**
     * Disconnect the branches and rewire if necessary.
     * 
     */
    public void build() {
        if (built)return;
        built=true;
        
        DisconnectBranchesCommand cmd;
        if (ncIn == null || ncOut == null) {
            cmd = new DisconnectBranchesCommand(pn, editpartregistry);
        } else {
            cmd = new DisconnectBranchesCommand(pn, ncIn, ncOut, editpartregistry);

        }
        add(cmd);

        // if (cmd.getNcInBefore().size() == 1 && cmd.getNcOutBefore().size() == 1 || mustReplaceWithEmpty) {
        // we are rewiring the paths underneath the element
        if (rewire && pn.getSucc().size() > 0) {
            // add(new PreDeleteUrnModelElementCommand((NodeConnection) pn.getSucc().get(0)));
            add(new RewirePathCommand(pn));

        }

    }

    /**
     * Returns true even if no commands exist.
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canExecute();
    }

    /**
     * Returns true even if no commands exist.
     * 
     * @see org.eclipse.gef.commands.Command#canUndo()
     */
    public boolean canUndo() {
        if (getCommands().size() == 0)
            return true;
        else
            return super.canUndo();
    }

    /**
     * Builds the command at execution time, if it hasn't alreayd been deleted.
     */
    public void execute() {
        if (pn.getDiagram() != null) {
            build();
            super.execute();
        }
    }

}
