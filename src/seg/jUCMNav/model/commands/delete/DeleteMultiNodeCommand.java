package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.CompoundCommand;

import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.figures.SplineConnection;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.ParentFinder;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.ComponentRef;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import urn.URNspec;
import urncore.Condition;

/**
 * Created on 29-May-2005
 * 
 * This is the first implementation of deletion for a PathNode that has multiple inputs or outputs.
 * 
 * We create new start and end points to truncate the paths that enter or exit this PathNode.
 * 
 * This command handles the deletion of PluginBindings, InBindings and OutBindings from Stubs.
 * 
 * @author jkealey
 *  
 */
public class DeleteMultiNodeCommand extends CompoundCommand implements JUCMNavCommand {

    // needed to get access to the SplineConnection of incoming / outgoing node
    // connections.
    private Map editpartregistry;

    // list of incoming and outgoing node connections
    private List ncIn, ncOut;

    // when deleting an orfork, remember its out conditions.
    private List outConditions;

    // list of new start points and end points.
    private List newStart, newEnd;

    // if the node is bound to a parent
    private ComponentRef parent;

    // the pathgraph that contains the node.
    private PathGraph pg;

    // the pathnode to be deleted.
    private PathNode toDelete;

    // if the pathnode was downgraded to an empty point, this is it.
    private EmptyPoint empty;

    // if we are downgrading an orfork to an empty point, we need to get rid of
    // the condition.
    //    private Condition condition;

    // the URNspec which contains all the elements
    private URNspec urn;

    // if this is false, we're only removing branches.
    private boolean shouldDeleteNode = true;

    // if the node has already been deleted, this will prevent the command from
    // being done/undone without breaking the stack.
    private boolean aborted = false;

    /**
     * Deletes a PathNode and replaces its incoming and outgoing node connections with start points and end points.
     * 
     * @param toDelete
     *            The PathNode to be deleted.
     * @param editpartregistry
     *            The Edit Part Viewer's edit part registry. It is sufficient to pass a map containing only the mapping between NodeConnections and
     *            NodeConnectionEditParts.
     *  
     */
    public DeleteMultiNodeCommand(PathNode toDelete, Map editpartregistry) {
        this.toDelete = toDelete;
        this.editpartregistry = editpartregistry;
        this.shouldDeleteNode = true;
    }

    /**
     * Deletes a PathNode and replaces the specified incoming and outgoing node connections with start points and end points.
     * 
     * The node connection lists will not be respected if they imply the node must be completely removed.
     * 
     * @param toDelete
     *            The PathNode to be deleted.
     * @param ncIn
     *            The node connections that enter this PathNode. Pass an empty list if none should be disconnected or null if all should be disconnected.
     * @param ncOut
     *            The node connections that exist this PathNode. Pass an empty list if none should be disconnected or null if all should be disconnected.
     * 
     * @param editpartregistry
     *            The Edit Part Viewer's edit part registry. It is sufficient to pass a map containing only the mapping between NodeConnections and
     *            NodeConnectionEditParts.
     *  
     */
    public DeleteMultiNodeCommand(PathNode toDelete, List ncIn, List ncOut, Map editpartregistry) {
        this.toDelete = toDelete;
        this.ncIn = ncIn;
        this.ncOut = ncOut;
        this.editpartregistry = editpartregistry;
        this.shouldDeleteNode = false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return toDelete != null && toDelete.getPathGraph() != null;
        // At first, was only intended for the following elements but this is
        // not necessary. Performs work similar to the CutPathCommand.
        //
        //&& (toDelete instanceof Stub || toDelete instanceof AndFork ||
        // toDelete instanceof AndJoin || toDelete instanceof OrFork || toDelete
        // instanceof
        // OrJoin);
    }

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	public boolean canUndo() {
		// Make sure we can undo even if we don't have any added commands
		if(getCommands().size() == 0)
			return true;
		return super.canUndo();
	}
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
    	if(toDelete instanceof Stub){
        	Stub stub = (Stub)toDelete;
        	for (Iterator i = stub.getBindings().iterator(); i.hasNext();) {
				PluginBinding plugin = (PluginBinding) i.next();
				DeletePluginCommand del = new DeletePluginCommand(plugin);
				add(del);
			}
        }
    	
        // this could happen when multiple nodes are selected for deletion and
        // this one has already been deleted.
        if (!canExecute()) {
            aborted = true;
            return;
        }
        // create new vectors.
        newStart = new Vector();
        newEnd = new Vector();

        // save current state.
        if (ncOut == null) {
            ncOut = new Vector();
            ncOut.addAll(toDelete.getSucc());
        }

        if (ncIn == null) {
            ncIn = new Vector();
            ncIn.addAll(toDelete.getPred());
        }

        // make sure it is valid to simply break the connection.
        if (!shouldDeleteNode) {
            // if one side or the other is left without any connection.
            if ((toDelete.getSucc().size() - ncOut.size() == 0) || (toDelete.getPred().size() - ncIn.size() == 0)) {
                if (!(toDelete instanceof Stub) || ((toDelete.getSucc().size() - ncOut.size() == 0) && (toDelete.getPred().size() - ncIn.size() == 0))) {
                    markNodeForDeletion();
                }
            } else if (toDelete instanceof Timer && ncOut.size() == 1 && toDelete.getSucc().indexOf(ncOut.get(0)) == 0) {
                // if trying to delete the normal path of a timer, must disconnect its timeout path as well.
                markNodeForDeletion();
            } else if ((toDelete.getSucc().size() - ncOut.size() == 1) && (toDelete.getPred().size() - ncIn.size() == 1)) {

                if (toDelete instanceof AndFork || toDelete instanceof OrFork || toDelete instanceof AndJoin || toDelete instanceof OrJoin) {
                    // need to downgrade pathnode to empty point.
                    empty = (EmptyPoint) ModelCreationFactory.getNewObject(urn, EmptyPoint.class);
                    empty.setX(toDelete.getX());
                    empty.setY(toDelete.getY());

                    if (ncOut.size() > 0) {
                        // index of connection to be deleted.
                        int index = toDelete.getSucc().indexOf(ncOut.get(0));
                        // index of remaining node connection
                        index = (++index % 2);
                        // we need to get rid of this condition.
                        if (((NodeConnection) toDelete.getSucc().get(index)).getCondition() != null) {
                            outConditions = new Vector();
                            outConditions.add(((NodeConnection) toDelete.getSucc().get(index)).getCondition());
                        }
                    }
                }

            }

        }

        pg = toDelete.getPathGraph();
        urn = toDelete.getPathGraph().getMap().getUcmspec().getUrnspec();
        parent = toDelete.getCompRef();

        // create and initialize all new start points.
        for (Iterator iter = ncOut.iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            Point midPoint;
            StartPoint sp = (StartPoint) ModelCreationFactory.getNewObject(urn, StartPoint.class);

            NodeConnectionEditPart nodePart = (NodeConnectionEditPart) editpartregistry.get(nc);
            if (nodePart != null) {
                SplineConnection spline = (SplineConnection) nodePart.getFigure();
                //          if we don't do this, when deleting multiple path elements,
                // the spline might not have been refreshed and the mid point
                // ends up being in the
                // top
                // left corner.
                spline.getConnectionRouter().route(spline);
                if (spline.getPoints().size() > 0) {
                    midPoint = spline.getPoints().getMidpoint();
                    sp.setX(midPoint.x);
                    sp.setY(midPoint.y);
                }
            }
            newStart.add(sp);
        }

        // create and initialize all new end points.
        for (Iterator iter = ncIn.iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            Point midPoint;
            EndPoint ep = (EndPoint) ModelCreationFactory.getNewObject(urn, EndPoint.class);
            NodeConnectionEditPart nodePart = (NodeConnectionEditPart) editpartregistry.get(nc);
            if (nodePart != null) {
                SplineConnection spline = (SplineConnection) nodePart.getFigure();

                // if we don't do this, when deleting multiple path elements,
                // the spline might not have been refreshed and the mid point
                // ends up being in the
                // top
                // left corner.
                spline.getConnectionRouter().route(spline);
                if (spline.getPoints().size() > 0) {
                    midPoint = spline.getPoints().getMidpoint();
                    ep.setX(midPoint.x);
                    ep.setY(midPoint.y);
                } else {
                    if (nc.getSource() != null && nc.getSource() != toDelete) {
                        ep.setX(nc.getSource().getX());
                        ep.setY(nc.getSource().getY());
                    }
                    if (nc.getTarget() != null && nc.getTarget() != toDelete) {
                        ep.setX(nc.getTarget().getX());
                        ep.setY(nc.getTarget().getY());
                    }
                }
            }
            newEnd.add(ep);
        }

        if (outConditions == null) {
            outConditions = new Vector();
            for (Iterator iter = ncOut.iterator(); iter.hasNext();) {
                NodeConnection nc = (NodeConnection) iter.next();
                outConditions.add(nc.getCondition());
            }
        }
        doRedo();
        super.execute();
    }

    /**
     * 
     */
    private void markNodeForDeletion() {
        ncOut = new Vector();
        ncIn = new Vector();
        ncOut.addAll(toDelete.getSucc());
        ncIn.addAll(toDelete.getPred());

        shouldDeleteNode = true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        if (aborted)
            return;
        testPreConditions();

        doRedo();
        
        super.redo();
        
        testPostConditions();
    }

    /**
	 * 
	 */
	private void doRedo() {
		if (shouldDeleteNode) {

            // remove the current node from the map
            toDelete.setCompRef(null);
            pg.getPathNodes().remove(toDelete);
        }

        // rewire incoming and outgoing links.
        for (int i = 0; i < ncIn.size(); i++) {
            NodeConnection nc = (NodeConnection) ncIn.get(i);
            PathNode pn = (PathNode) newEnd.get(i);
            nc.setTarget(pn);
            pg.getPathNodes().add(pn);
            pn.setCompRef(ParentFinder.getPossibleParent(pn));
        }

        for (int i = 0; i < ncOut.size(); i++) {
            NodeConnection nc = (NodeConnection) ncOut.get(i);
            PathNode pn = (PathNode) newStart.get(i);
            nc.setSource(pn);
            pg.getPathNodes().add(pn);
            pn.setCompRef(ParentFinder.getPossibleParent(pn));
            nc.setCondition(null);
        }

        if (!shouldDeleteNode && empty != null) {
            // must downgrade to empty point.
            ((NodeConnection) toDelete.getPred().get(0)).setTarget(empty);
            ((NodeConnection) toDelete.getSucc().get(0)).setSource(empty);
            empty.setCompRef(toDelete.getCompRef());
            toDelete.setCompRef(null);
            pg.getPathNodes().remove(toDelete);
            pg.getPathNodes().add(empty);

        }
	}

	/*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null && pg != null && toDelete != null && ncIn != null && ncOut != null && newStart != null && newEnd != null : "post something is null"; //$NON-NLS-1$
        assert ncIn.size() == newEnd.size() && ncOut.size() == newStart.size() : "post invalid sizes"; //$NON-NLS-1$
        if (shouldDeleteNode)
            assert !pg.getPathNodes().contains(toDelete) : "post node not in model"; //$NON-NLS-1$

        for (int i = 0; i < ncIn.size(); i++) {
            assert !toDelete.getPred().contains(ncIn.get(i)) : "post pred nc not in pred " + i; //$NON-NLS-1$
            assert pg.getPathNodes().contains(newEnd.get(i)) : "post new end not in graph " + i; //$NON-NLS-1$
        }
        for (int i = 0; i < ncOut.size(); i++) {
            assert !toDelete.getSucc().contains(ncOut.get(i)) : "post succ nc not in succ " + i; //$NON-NLS-1$
            assert pg.getPathNodes().contains(newStart.get(i)) : "post new start not in graph " + i; //$NON-NLS-1$
        }

        /*
         * if (toDelete instanceof Stub) { Stub stub = (Stub) toDelete;
         * 
         * assert stub.getBindings().size() == 0 : "Post Stub PluginBindings count is not 0."; //$NON-NLS-1$ for (Iterator i = plugins.iterator(); i.hasNext();) {
         * PluginBinding plugin = (PluginBinding) i.next();
         * 
         * assert plugin.getPlugin() == null : "Post the map associated with the PluginBinding is not null."; //$NON-NLS-1$
         * 
         * assert plugin.getIn().size() == 0 : "Post number of InBinding is not 0."; //$NON-NLS-1$ assert plugin.getOut().size() == 0 : "Post number of
         * OutBinding is not 0."; //$NON-NLS-1$
         * 
         * for (Iterator j = plugin.getIn().iterator(); j.hasNext();) { InBinding in = (InBinding) j.next(); assert in.getStartPoint() == null : "Post the
         * StartPoint for this InBinding is not null."; //$NON-NLS-1$ assert in.getStubEntry() == null : "Post the entry for this InBinding is not null.";
         * //$NON-NLS-1$ }
         * 
         * for (Iterator j = plugin.getOut().iterator(); j.hasNext();) { OutBinding out = (OutBinding) j.next(); assert out.getEndPoint() == null : "Post the
         * EndPoint for this OutBinding is not null."; //$NON-NLS-1$ assert out.getStubExit() == null : "Post the exit for this OutBinding is not null.";
         * //$NON-NLS-1$ } } }
         */
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && pg != null && toDelete != null && ncIn != null && ncOut != null && newStart != null && newEnd != null : "pre something is null"; //$NON-NLS-1$
        assert ncIn.size() == newEnd.size() && ncOut.size() == newStart.size() : "pre invalid sizes"; //$NON-NLS-1$
        if (shouldDeleteNode)
            assert pg.getPathNodes().contains(toDelete) : "pre node not in model"; //$NON-NLS-1$
        for (int i = 0; i < ncIn.size(); i++) {
            assert toDelete.getPred().contains(ncIn.get(i)) : "pre pred nc not in pred " + i; //$NON-NLS-1$
            assert !pg.getPathNodes().contains(newEnd.get(i)) : "pre new end not in graph " + i; //$NON-NLS-1$
        }
        for (int i = 0; i < ncOut.size(); i++) {
            assert toDelete.getSucc().contains(ncOut.get(i)) : "pre succ nc not in succ " + i; //$NON-NLS-1$
            assert !pg.getPathNodes().contains(newStart.get(i)) : "pre new start not in graph " + i; //$NON-NLS-1$
        }
        /*
         * if (toDelete instanceof Stub) { Stub stub = (Stub) toDelete;
         * 
         * assert stub.getBindings().size() == plugins.size() : "Pre Stub PluginBindings count is different."; //$NON-NLS-1$ for (Iterator i =
         * stub.getBindings().iterator(); i.hasNext();) { PluginBinding plugin = (PluginBinding) i.next();
         * 
         * assert plugin.getPlugin() == maps.get(plugin) : "Pre the map associated with the PluginBinding is not the same as before."; //$NON-NLS-1$
         * 
         * assert plugin.getIn().size() == ((ArrayList) inBindings.get(plugin)).size() : "Pre number of InBinding is not the same for this PluginBinding";
         * //$NON-NLS-1$ assert plugin.getOut().size() == ((ArrayList) outBindings.get(plugin)).size() : "Pre number of OutBinding is not the same for this
         * PluginBinding"; //$NON-NLS-1$
         * 
         * for (Iterator j = plugin.getIn().iterator(); j.hasNext();) { InBinding in = (InBinding) j.next(); assert in.getStartPoint() == starts.get(in) : "Pre
         * the StartPoint for this InBinding is not the same as before."; //$NON-NLS-1$ assert in.getStubEntry() == entry.get(in) : "Pre the entry for this
         * InBinding is not the same as before."; //$NON-NLS-1$ }
         * 
         * for (Iterator j = plugin.getOut().iterator(); j.hasNext();) { OutBinding out = (OutBinding) j.next(); assert out.getEndPoint() == starts.get(out) :
         * "Pre the EndPoint for this OutBinding is not the same as before."; //$NON-NLS-1$ assert out.getStubExit() == entry.get(out) : "Pre the exit for this
         * OutBinding is not the same as before."; //$NON-NLS-1$ } } }
         */

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        if (aborted)
            return;
        testPostConditions();
        
        super.undo();

        if (!shouldDeleteNode && empty != null) {
            // must upgrade back to non empty point.
            if (outConditions != null && outConditions.size() > 0) {
                ((NodeConnection) empty.getSucc().get(0)).setCondition((Condition) outConditions.get(0));
            }

            ((NodeConnection) empty.getPred().get(0)).setTarget(toDelete);
            ((NodeConnection) empty.getSucc().get(0)).setSource(toDelete);
            toDelete.setCompRef(empty.getCompRef());
            empty.setCompRef(null);
            pg.getPathNodes().remove(empty);
            pg.getPathNodes().add(toDelete);
        }

        // rewire the incoming and outgoing connections.
        for (int i = 0; i < ncIn.size(); i++) {
            NodeConnection nc = (NodeConnection) ncIn.get(i);
            nc.setTarget(toDelete);
            PathNode pn = (PathNode) newEnd.get(i);
            pg.getPathNodes().remove(pn);
            pn.setCompRef(null);
        }

        for (int i = 0; i < ncOut.size(); i++) {
            NodeConnection nc = (NodeConnection) ncOut.get(i);
            nc.setSource(toDelete);
            PathNode pn = (PathNode) newStart.get(i);
            pg.getPathNodes().remove(pn);
            pn.setCompRef(null);
            if (shouldDeleteNode || empty == null)
                nc.setCondition((Condition) outConditions.get(i));

        }

        if (shouldDeleteNode) {
            // add the pathnode to the graph
            toDelete.setCompRef(parent);
            pg.getPathNodes().add(toDelete);
        }

        testPreConditions();
    }
}