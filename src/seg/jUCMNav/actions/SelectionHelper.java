package seg.jUCMNav.actions;

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;

import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.figures.SplineConnection;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.ComponentRef;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.Map;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.WaitingPlace;
import urn.URNspec;
import urncore.ComponentLabel;
import urncore.NodeLabel;

/**
 * This class will help reduce redundant code in all action classes. When given a selection, it parses it and gives utility functions to return its type and
 * allows for easy access to the concerned model elements by encapsulating the casts and other management overhead.
 * 
 * Created on 28-May-2005
 * 
 * @author jkealey
 *  
 */
public class SelectionHelper {
    // SelectionTypes
    public static final int ANDFORK = 13;
    public static final int ANDJOIN = 14;
    public static final int COMPONENTLABEL = 10;
    public static final int COMPONENTREF = 11;
    public static final int EMPTYPOINT = 1;
    public static final int ENDPOINT = 2;
    public static final int ENDPOINT_ANDJOIN = 112;
    public static final int ENDPOINT_EMPTYPOINT = 101;
    public static final int ENDPOINT_NODECONNECTION = 102;
    public static final int ENDPOINT_ORJOIN = 111;
    public static final int ENDPOINT_STUB = 103;
    public static final int ENDPOINT_TIMER = 104;
    public static final int ENDPOINT_WAITINGPLACE = 105;
    public static final int NODECONNECTION = 3;
    public static final int NODELABEL = 9;
    public static final int ORFORK = 12;
    public static final int ORJOIN = 15;
    public static final int OTHER = -1;
    public static final int RESPONSIBILITY = 4;
    public static final int STARTPOINT = 5;
    public static final int STARTPOINT_ANDFORK = 114;
    public static final int STARTPOINT_EMPTYPOINT = 106;
    public static final int STARTPOINT_ENDPOINT = 107;
    public static final int STARTPOINT_NODECONNECTION = 108;
    public static final int STARTPOINT_ORFORK = 113;
    public static final int STARTPOINT_STUB = 109;
    public static final int STARTPOINT_TIMER = 110;
    public static final int STUB = 6;
    public static final int TIMER = 7;
    public static final int WAITINGPLACE = 8;
    public static final int MAP = 16;
    public static final int URNSPEC = 17;

    // internal variables; for quick reference.
    private AndFork andfork;
    private AndJoin andjoin;
    private ComponentLabel componentlabel;
    private ComponentRef componentref;
    private EmptyPoint emptypoint;
    private EndPoint endpoint;
    private Map map;
    private NodeConnection nodeconnection;
    private Point nodeconnectionmiddle;
    private NodeLabel nodelabel;
    private OrFork orfork;
    private OrJoin orjoin;
    private PathGraph pathgraph;
    private RespRef respref;
    private List selection;
    private int selectionType;
    private StartPoint startpoint;
    private Stub stub;
    private Timer timer;
    private URNspec urnspec;
    private WaitingPlace waitingplace;

    public SelectionHelper(List selection) {
        setSelection(selection);
    }

    public AndFork getAndfork() {
        return andfork;
    }

    public AndJoin getAndjoin() {
        return andjoin;
    }

    public ComponentLabel getComponentlabel() {
        return componentlabel;
    }

    public ComponentRef getComponentref() {
        return componentref;
    }

    public EmptyPoint getEmptypoint() {
        return emptypoint;
    }

    public EndPoint getEndpoint() {
        return endpoint;
    }

    public Map getMap() {
        return map;
    }

    public NodeConnection getNodeconnection() {
        return nodeconnection;
    }

    public Point getNodeconnectionMiddle() {
        return nodeconnectionmiddle;
    }

    public NodeLabel getNodelabel() {
        return nodelabel;
    }

    public OrFork getOrfork() {
        return orfork;
    }

    public OrJoin getOrjoin() {
        return orjoin;
    }

    public PathGraph getPathgraph() {
        return pathgraph;
    }

    public RespRef getRespref() {
        return respref;
    }

    public List getSelection() {
        return selection;
    }

    public int getSelectionType() {
        return selectionType;
    }

    public StartPoint getStartpoint() {
        return startpoint;
    }

    public Stub getStub() {
        return stub;
    }

    public Timer getTimer() {
        return timer;
    }

    public URNspec getUrnspec() {
        return urnspec;
    }

    public WaitingPlace getWaitingPlace() {
        return waitingplace;
    }

    /**
     * Given an EditPart, set the appropriate internal variable.
     */
    private void setInternals(EditPart part) {
        EObject model = (EObject) part.getModel();

        if (model instanceof EmptyPoint)
            emptypoint = (EmptyPoint) model;
        else if (model instanceof EndPoint)
            endpoint = (EndPoint) model;
        else if (model instanceof NodeConnection) {
            nodeconnection = (NodeConnection) model;
            SplineConnection conn = (SplineConnection) ((NodeConnectionEditPart) part).getFigure();
            nodeconnectionmiddle = conn.getPoints().getMidpoint();
        } else if (model instanceof RespRef)
            respref = (RespRef) model;
        else if (model instanceof StartPoint)
            startpoint = (StartPoint) model;
        else if (model instanceof Stub)
            stub = (Stub) model;
        else if (model instanceof Timer)
            timer = (Timer) model;
        else if (model instanceof WaitingPlace)
            waitingplace = (WaitingPlace) model;
        else if (model instanceof NodeLabel)
            nodelabel = (NodeLabel) model;
        else if (model instanceof ComponentLabel)
            componentlabel = (ComponentLabel) model;
        else if (model instanceof ComponentRef)
            componentref = (ComponentRef) model;
        else if (model instanceof OrFork)
            orfork = (OrFork) model;
        else if (model instanceof AndFork)
            andfork = (AndFork) model;
        else if (model instanceof OrJoin)
            orjoin = (OrJoin) model;
        else if (model instanceof AndJoin)
            andjoin = (AndJoin) model;
        else if (model instanceof Map) {
            map = (Map) model;
            urnspec = map.getUcmspec().getUrnspec();
        } else if (model instanceof URNspec) {
            urnspec = (URNspec) model;
        }

        if (model instanceof NodeConnection || model instanceof PathNode || model instanceof ComponentRef) {
            if (model instanceof NodeConnection)
                pathgraph = nodeconnection.getPathGraph();
            else if (model instanceof PathNode)
                pathgraph = ((PathNode) model).getPathGraph();

            if (model instanceof ComponentRef)
                map = componentref.getMap();
            else
                map = pathgraph.getMap();

            urnspec = map.getUcmspec().getUrnspec();
        }
    }

    /**
     * Given a list of edit parts of eobjects, set the internal variables and internal type.
     * 
     * @param selection
     */
    private void setSelection(List selection) {
        this.selection = selection;
        if ((selection.size() == 1 || selection.size() == 2) && selection.get(0) instanceof EditPart) {
            EditPart ep = (EditPart) selection.get(0);
            if (ep.getModel() instanceof EObject) {
                setInternals(ep);
            }
        }

        if (selection.size() == 2 && selection.get(1) instanceof EditPart) {
            EditPart ep = (EditPart) selection.get(1);
            if (ep.getModel() instanceof EObject) {
                setInternals(ep);
            }

        }

        setType();
    }

    /**
     * Given the internal variable state, set the selection type.
     *  
     */
    private void setType() {
        if (startpoint != null && emptypoint != null)
            selectionType = STARTPOINT_EMPTYPOINT;
        else if (startpoint != null && endpoint != null)
            selectionType = STARTPOINT_ENDPOINT;
        else if (startpoint != null && nodeconnection != null)
            selectionType = STARTPOINT_NODECONNECTION;
        else if (startpoint != null && stub != null)
            selectionType = STARTPOINT_STUB;
        else if (startpoint != null && timer != null)
            selectionType = STARTPOINT_TIMER;
        else if (endpoint != null && emptypoint != null)
            selectionType = ENDPOINT_EMPTYPOINT;
        else if (endpoint != null && nodeconnection != null)
            selectionType = ENDPOINT_NODECONNECTION;
        else if (endpoint != null && stub != null)
            selectionType = ENDPOINT_STUB;
        else if (endpoint != null && timer != null)
            selectionType = ENDPOINT_TIMER;
        else if (endpoint != null && waitingplace != null)
            selectionType = ENDPOINT_WAITINGPLACE;
        else if (startpoint != null && orfork != null)
            selectionType = STARTPOINT_ORFORK;
        else if (startpoint != null && andfork != null)
            selectionType = STARTPOINT_ANDFORK;
        else if (endpoint != null && orjoin != null)
            selectionType = ENDPOINT_ORJOIN;
        else if (endpoint != null && andjoin != null)
            selectionType = ENDPOINT_ANDJOIN;
        else if (emptypoint != null)
            selectionType = EMPTYPOINT;
        else if (endpoint != null)
            selectionType = ENDPOINT;
        else if (nodeconnection != null)
            selectionType = NODECONNECTION;
        else if (respref != null)
            selectionType = RESPONSIBILITY;
        else if (startpoint != null)
            selectionType = STARTPOINT;
        else if (stub != null)
            selectionType = STUB;
        else if (timer != null)
            selectionType = TIMER;
        else if (waitingplace != null)
            selectionType = WAITINGPLACE;
        else if (componentlabel != null)
            selectionType = COMPONENTLABEL;
        else if (componentref != null)
            selectionType = COMPONENTREF;
        else if (nodelabel != null)
            selectionType = NODELABEL;
        else if (orfork != null)
            selectionType = ORFORK;
        else if (andfork != null)
            selectionType = ANDFORK;
        else if (orjoin != null)
            selectionType = ORJOIN;
        else if (andjoin != null)
            selectionType = ANDJOIN;
        else if (map != null)
            selectionType = MAP;
        else if (urnspec != null)
            selectionType = URNSPEC;
        else
            selectionType = OTHER;

    }
}