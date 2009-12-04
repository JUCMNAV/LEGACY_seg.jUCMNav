package seg.jUCMNav.model.commands.delete.internal;

import org.eclipse.emf.common.util.EList;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.UCMmap;

/**
 * Given a PathNode with one input and output, removes the outgoing node connection and rewires the incoming node connection onto the successor.
 * 
 * Transfers any binding information from the deleted node connection to the remaining one (InBindings on ncSucc is transfered to ncPred). Since the incoming
 * one is retained and since NodeConnection conditions always directly follow Or-Forks, Timers or WaitingPlaces, the condition is retained implicitely. Assumes
 * that if the passed PathNode will be deleted and that if it is a stub, someone else will get rid of the InBindings on ncPred and OutBindings on ncSucc.
 * 
 * Does not remove the PathNode from the PathGraph.
 * 
 * @author jkealey
 * 
 */
public class RewirePathCommand extends Command implements JUCMNavCommand {
    private PathNode element;
    private NodeConnection ncPred, ncSucc;
    private EList inBindings;
    private boolean aborted;
    private UCMmap pg;
    private int iTargetPosition;

    /**
     * 
     * @param pn
     *            the PathNode to be removed.
     */
    public RewirePathCommand(PathNode pn) {
        this.element = pn;

    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        // all branches were removed on one side; no transfer needed.
        aborted = !(element.getPred().size() >= 1 && element.getSucc().size() >= 1);

        if (aborted)
            return;
        if (element.getPred().size() >= 1)
            ncPred = (NodeConnection) element.getPred().get(0);
        if (element.getSucc().size() >= 1) {
            ncSucc = (NodeConnection) element.getSucc().get(0);
            inBindings = ncSucc.getInBindings();
        }

        pg = (UCMmap) element.getDiagram();
        aborted = pg == null || element.getDiagram().getUrndefinition() == null;
        if (aborted)
            return;
        iTargetPosition = ncSucc.getTarget().getPred().indexOf(ncSucc);
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (aborted)
            return;
        testPreConditions();

        ncSucc.getTarget().getPred().add(iTargetPosition, ncPred);
        ncSucc.setTarget(null);
        ncSucc.setSource(null);
        ncPred.getInBindings().addAll(ncSucc.getInBindings());
        ncSucc.getInBindings().clear();
        pg.getConnections().remove(ncSucc);

        testPostConditions();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();

        ncPred.getTarget().getPred().add(iTargetPosition, ncSucc);
        ncSucc.setSource(element);
        ncPred.setTarget(element);

        ncSucc.getInBindings().addAll(ncPred.getInBindings());
        ncPred.getInBindings().clear();
        pg.getConnections().add(ncSucc);

        testPreConditions();
    }

    /**
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert pg != null && element != null && ncPred != null && ncSucc != null : "pre something is null"; //$NON-NLS-1$
        assert element.getSucc().size() == 1 && element.getPred().size() == 1 : "pre something wrong with connections"; //$NON-NLS-1$
        assert aborted == false : "pre aborted"; //$NON-NLS-1$
        assert pg.getNodes().contains(element) && pg.getConnections().contains(ncPred) && pg.getConnections().contains(ncSucc) : "pre pg problem"; //$NON-NLS-1$
        assert ncSucc.getInBindings().containsAll(inBindings) && ncPred.getInBindings().size() == 0 : "pre inbinding problem"; //$NON-NLS-1$

    }

    /**
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert pg != null && element != null && ncPred != null && ncSucc != null : "post something is null"; //$NON-NLS-1$
        assert element.getSucc().size() == 0 && element.getPred().size() == 0 : "post something wrong with connections"; //$NON-NLS-1$
        assert aborted == false : "post aborted"; //$NON-NLS-1$
        assert pg.getNodes().contains(element) && pg.getConnections().contains(ncPred) && !pg.getConnections().contains(ncSucc) : "post pg problem"; //$NON-NLS-1$
        assert ncSucc.getInBindings().size() == 0 && ncPred.getInBindings().containsAll(inBindings) : "post inbinding problem"; //$NON-NLS-1$

    }

}
