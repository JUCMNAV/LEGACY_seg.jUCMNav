package seg.jUCMNav.model.commands.delete;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.figures.SplineConnection;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.StartPoint;
import urn.URNspec;

/**
 * Created on 29-May-2005
 * 
 * This is the first implementation of deletion for a PathNode that has multiple inputs or outputs.
 * 
 * We create new start and end points to truncate the paths that enter or exit this PathNode.
 * 
 * @author jkealey
 *  
 */
public class DeleteMultiNodeCommand extends Command implements JUCMNavCommand {

    // needed to get access to the SplineConnection of incoming / outgoing node connections.
    private Map editpartregistry;

    // list of incoming and outgoing node connections
    private List ncIn, ncOut;

    // list of new start points and end points.
    private List newStart, newEnd;

    // if the node is bound to a parent
    private ComponentRef parent;

    // the pathgraph that contains the node.
    private PathGraph pg;

    // the pathnode to be deleted.
    private PathNode toDelete;

    // the URNspec which contains all the elements
    private URNspec urn;

    /**
     * Deletes a PathNode and replaces its incoming and outgoing nodes with start points and end points.
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
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return toDelete != null && toDelete.getPathGraph() != null;
        // At first, was only intended for the following elements but this is not necessary. Performs work similar to the CutPathCommand.
        //
        //&& (toDelete instanceof Stub || toDelete instanceof AndFork || toDelete instanceof AndJoin || toDelete instanceof OrFork || toDelete instanceof
        // OrJoin);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        // create new vectors.
        ncOut = new Vector();
        ncIn = new Vector();
        newStart = new Vector();
        newEnd = new Vector();

        // save current state.
        pg = toDelete.getPathGraph();
        urn = toDelete.getPathGraph().getMap().getUcmspec().getUrnspec();
        parent = toDelete.getCompRef();
        ncOut.addAll(toDelete.getSucc());
        ncIn.addAll(toDelete.getPred());

        // create and initialize all new start points.
        for (Iterator iter = ncOut.iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            Point midPoint;
            SplineConnection spline = (SplineConnection) ((NodeConnectionEditPart) editpartregistry.get(nc)).getFigure();
//          if we don't do this, when deleting multiple path elements, the spline might not have been refreshed and the mid point ends up being in the top left corner.
            spline.getConnectionRouter().route(spline);
            midPoint = spline.getPoints().getMidpoint();
            StartPoint sp = (StartPoint) ModelCreationFactory.getNewObject(urn, StartPoint.class);
            sp.setX(midPoint.x);
            sp.setY(midPoint.y);
            newStart.add(sp);
        }

        // create and initialize all new end points.
        for (Iterator iter = ncIn.iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            Point midPoint;
            SplineConnection spline = (SplineConnection) ((NodeConnectionEditPart) editpartregistry.get(nc)).getFigure();
            
            // if we don't do this, when deleting multiple path elements, the spline might not have been refreshed and the mid point ends up being in the top left corner. 
            spline.getConnectionRouter().route(spline);
            midPoint = spline.getPoints().getMidpoint();
            EndPoint ep = (EndPoint) ModelCreationFactory.getNewObject(urn, EndPoint.class);
            ep.setX(midPoint.x);
            ep.setY(midPoint.y);
            newEnd.add(ep);
        }

        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        // remove the current node from the map
        toDelete.setCompRef(null);
        pg.getPathNodes().remove(toDelete);

        // rewire incoming and outgoing links.
        for (int i = 0; i < ncIn.size(); i++) {
            NodeConnection nc = (NodeConnection) ncIn.get(i);
            nc.setTarget((PathNode) newEnd.get(i));
            pg.getPathNodes().add((PathNode) newEnd.get(i));
        }

        for (int i = 0; i < ncOut.size(); i++) {
            NodeConnection nc = (NodeConnection) ncOut.get(i);
            nc.setSource((PathNode) newStart.get(i));
            pg.getPathNodes().add((PathNode) newStart.get(i));
        }

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urn != null && pg != null && toDelete != null && ncIn != null && ncOut != null && newStart != null && newEnd != null : "post something is null";
        assert ncIn.size() == newEnd.size() && ncOut.size() == newStart.size() : "post invalid sizes";
        assert !pg.getPathNodes().contains(toDelete) : "post node not in model";
        for (int i = 0; i < ncIn.size(); i++) {
            assert !toDelete.getPred().contains(ncIn.get(i)) : "post pred nc not in pred";
            assert pg.getPathNodes().contains(newEnd.get(i)) : "post new end not in graph";
        }
        for (int i = 0; i < ncOut.size(); i++) {
            assert !toDelete.getSucc().contains(ncOut.get(i)) : "post succ nc not in pred";
            assert pg.getPathNodes().contains(newStart.get(i)) : "post new start not in graph";
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urn != null && pg != null && toDelete != null && ncIn != null && ncOut != null && newStart != null && newEnd != null : "pre something is null";
        assert ncIn.size() == newEnd.size() && ncOut.size() == newStart.size() : "pre invalid sizes";
        assert pg.getPathNodes().contains(toDelete) : "pre node not in model";
        for (int i = 0; i < ncIn.size(); i++) {
            assert toDelete.getPred().contains(ncIn.get(i)) : "pre pred nc not in pred";
            assert !pg.getPathNodes().contains(newEnd.get(i)) : "pre new end not in graph";
        }
        for (int i = 0; i < ncOut.size(); i++) {
            assert toDelete.getSucc().contains(ncOut.get(i)) : "pre succ nc not in pred";
            assert !pg.getPathNodes().contains(newStart.get(i)) : "pre new start not in graph";
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        // add the pathnode to the graph
        toDelete.setCompRef(parent);
        pg.getPathNodes().add(toDelete);

        // rewire the incoming and outgoing connections.
        for (int i = 0; i < ncIn.size(); i++) {
            NodeConnection nc = (NodeConnection) ncIn.get(i);
            nc.setTarget(toDelete);
            pg.getPathNodes().remove((PathNode) newEnd.get(i));
        }

        for (int i = 0; i < ncOut.size(); i++) {
            NodeConnection nc = (NodeConnection) ncOut.get(i);
            nc.setSource(toDelete);
            pg.getPathNodes().remove((PathNode) newStart.get(i));
        }

        testPreConditions();
    }

}