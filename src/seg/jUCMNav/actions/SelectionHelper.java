package seg.jUCMNav.actions;

import grl.ActorRef;
import grl.Belief;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.GRLNode;
import grl.GRLspec;
import grl.IntentionalElementRef;
import grl.LinkRef;
import grl.StrategiesGroup;

import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;

import seg.jUCMNav.editparts.NodeConnectionEditPart;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.ComponentRef;
import ucm.map.Connect;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.UCMmap;
import ucm.map.WaitingPlace;
import urn.URNspec;
import urncore.ComponentLabel;
import urncore.IURNConnection;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;
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
    public static final int CONNECT = 18;
    public static final int DIRECTIONARROW = 19;
    public static final int EMPTYPOINT = 1;
    public static final int EMPTYPOINT_TIMER = 116;
    public static final int EMPTYPOINT_WAITINGPLACE = 115;
    public static final int ENDPOINT = 2;
    public static final int ENDPOINT_ANDJOIN = 112;
    public static final int ENDPOINT_DIRECTIONARROW = 117;
    public static final int ENDPOINT_EMPTYPOINT = 101;
    public static final int ENDPOINT_NODECONNECTION = 102;
    public static final int ENDPOINT_ORJOIN = 111;
    public static final int ENDPOINT_STUB = 103;
    public static final int ENDPOINT_TIMER = 104;
    public static final int ENDPOINT_WAITINGPLACE = 105;
    public static final int MAP = 16;
    public static final int NODECONNECTION = 3;
    public static final int NODELABEL = 9;
    public static final int ORFORK = 12;
    public static final int ORJOIN = 15;
    public static final int OTHER = -1;
    public static final int RESPONSIBILITY = 4;
    public static final int STARTPOINT = 5;
    public static final int STARTPOINT_ANDFORK = 114;
    public static final int STARTPOINT_DIRECTIONARROW = 118;
    public static final int STARTPOINT_EMPTYPOINT = 106;
    public static final int STARTPOINT_ENDPOINT = 107;
    public static final int STARTPOINT_NODECONNECTION = 108;
    public static final int STARTPOINT_ORFORK = 113;
    public static final int STARTPOINT_STUB = 109;
    public static final int STARTPOINT_TIMER = 110;
    public static final int STUB = 6;
    public static final int TIMER = 7;
    public static final int URNSPEC = 17;
    public static final int WAITINGPLACE = 8;

    //GRL constant
    public static final int GRLGRAPH = 200;
    public static final int ACTORREF = 201;
    public static final int BELIEF = 202;
    public static final int INTENTIONALELEMENTREF = 203;
    public static final int LINKREF = 204;
    public static final int EVALUATIONGROUP = 205;
    public static final int EVALUATIONSTRATEGY = 206;
    
    // internal variables; for quick reference.
    private AndFork andfork;
    private AndJoin andjoin;
    private ComponentLabel componentlabel;
    private ComponentRef componentref;
    private Connect connect;
    private DirectionArrow directionarrow;
    private EmptyPoint emptypoint;
    private EndPoint endpoint;
    private UCMmap map;
    private NodeConnection nodeconnection;
    private Point nodeconnectionmiddle;
    private NodeLabel nodelabel;
    private OrFork orfork;
    private OrJoin orjoin;
    private RespRef respref;
    private List selection;
    private int selectionType;
    private StartPoint startpoint;
    private Stub stub;
    private Timer timer;
    private URNspec urnspec;
    private WaitingPlace waitingplace;

    //internal variable for GRL
    private ActorRef actorref;
    private Belief belief;
    private GRLGraph grlgraph;
    private IntentionalElementRef intentionalelementref;
    private LinkRef linkref;
    private StrategiesGroup group;
    private EvaluationStrategy strategy;
    
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

    public Connect getConnect() {
        return connect;
    }

    public EmptyPoint getEmptypoint() {
        return emptypoint;
    }

    public EndPoint getEndpoint() {
        return endpoint;
    }

    public UCMmap getMap() {
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

        if (model instanceof EmptyPoint) {
            emptypoint = (EmptyPoint) model;
            if (emptypoint.getSucc().size() == 2) {
                // if this throws an exception, a previous command broke the link order.
                // don't try-catch this
                connect = (Connect) ((NodeConnection) emptypoint.getSucc().get(1)).getTarget();
            }
        } else if (model instanceof EndPoint) {
            endpoint = (EndPoint) model;
            if (endpoint.getSucc().size() == 1) {
                // if this throws an exception, a previous command broke the link order.
                // don't try-catch this
                connect = (Connect) ((NodeConnection) endpoint.getSucc().get(0)).getTarget();
            }
        } else if (model instanceof NodeConnection) {
            nodeconnection = (NodeConnection) model;
            nodeconnectionmiddle = ((NodeConnectionEditPart) part).getMiddlePoint();
        } else if (model instanceof RespRef) {
            respref = (RespRef) model;
        } else if (model instanceof IntentionalElementRef) {
            intentionalelementref = (IntentionalElementRef) model;
        } else if (model instanceof Belief) {
            belief = (Belief) model;
        } else if (model instanceof LinkRef) {
            linkref = (LinkRef) model;
        } else if (model instanceof StartPoint) {
            startpoint = (StartPoint) model;
            if (startpoint.getPred().size() == 1) {
                // if this throws an exception, a previous command broke the link order.
                // don't try-catch this
                connect = (Connect) ((NodeConnection) startpoint.getPred().get(0)).getSource();
            }
        } else if (model instanceof Stub) {
            stub = (Stub) model;
        } else if (model instanceof Timer) {
            timer = (Timer) model;
            if (timer.getPred().size() == 2) {
                // if this throws an exception, a previous command broke the link order.
                // don't try-catch this
                connect = (Connect) ((NodeConnection) timer.getPred().get(1)).getSource();
            }
        } else if (model instanceof WaitingPlace) {
            waitingplace = (WaitingPlace) model;
            if (waitingplace.getPred().size() == 2) {
                // if this throws an exception, a previous command broke the link order.
                // don't try-catch this
                connect = (Connect) ((NodeConnection) waitingplace.getPred().get(1)).getSource();
            }
        } else if (model instanceof NodeLabel)
            nodelabel = (NodeLabel) model;
        else if (model instanceof ComponentLabel)
            componentlabel = (ComponentLabel) model;
        else if (model instanceof ComponentRef)
            componentref = (ComponentRef) model;
        else if (model instanceof ActorRef)
            actorref = (ActorRef) model;
        else if (model instanceof OrFork)
            orfork = (OrFork) model;
        else if (model instanceof AndFork)
            andfork = (AndFork) model;
        else if (model instanceof OrJoin)
            orjoin = (OrJoin) model;
        else if (model instanceof AndJoin)
            andjoin = (AndJoin) model;
        else if (model instanceof DirectionArrow)
            directionarrow = (DirectionArrow) model;
        else if (model instanceof UCMmap && ((UCMmap) model).getUrndefinition() != null) {
            map = (UCMmap) model;
            urnspec = map.getUrndefinition().getUrnspec();
        } else if (model instanceof GRLGraph && ((GRLGraph) model).getUrndefinition() != null) {
            grlgraph = (GRLGraph) model;
            urnspec = grlgraph.getUrndefinition().getUrnspec();
        } else if (model instanceof StrategiesGroup) {
            group = (StrategiesGroup)model;
            urnspec = group.getGrlspec().getUrnspec();
        } else if (model instanceof GRLspec) {
            urnspec = ((GRLspec)model).getUrnspec();
        } else if (model instanceof URNspec) {
            urnspec = (URNspec) model;
        }

        if (model instanceof NodeConnection || model instanceof PathNode || model instanceof ComponentRef) {
            if (model instanceof NodeConnection)
                map = (UCMmap)nodeconnection.getDiagram();
            else if (model instanceof PathNode)
                map = (UCMmap)((PathNode) model).getDiagram();

            if (map != null && map.getUrndefinition()!=null)
                urnspec = map.getUrndefinition().getUrnspec();
        } else if (model instanceof LinkRef || model instanceof GRLNode || model instanceof ActorRef) {
            if (model instanceof LinkRef){
                grlgraph = (GRLGraph) ((LinkRef)model).getDiagram();
            } else if (model instanceof GRLNode){
                grlgraph = (GRLGraph) ((GRLNode)model).getDiagram();
            } else if (model instanceof ActorRef){
                grlgraph = (GRLGraph) ((ActorRef)model).getDiagram();
            }
            
            if (grlgraph != null && grlgraph.getUrndefinition()!=null)
                urnspec = grlgraph.getUrndefinition().getUrnspec();
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

        // just set the URNspec.
        if (selection.size() > 2) {
            for (Iterator iter = selection.iterator(); iter.hasNext();) {
                Object element = (Object) iter.next();
                if (element instanceof EditPart && ((EditPart) element).getModel() != null) {
                    Object model = ((EditPart) element).getModel();
                    if (model instanceof URNspec)
                        urnspec = (URNspec) model;
                    else if (model instanceof IURNDiagram && ((IURNDiagram) model).getUrndefinition() != null)
                        urnspec = ((IURNDiagram) model).getUrndefinition().getUrnspec();
                    else if (model instanceof IURNNode && ((IURNNode) model).getDiagram()!=null && ((IURNNode) model).getDiagram().getUrndefinition()!=null)
                        urnspec = ((IURNNode) model).getDiagram().getUrndefinition().getUrnspec();
                    else if (model instanceof IURNConnection && ((IURNConnection) model).getDiagram() != null && ((IURNConnection) model).getDiagram().getUrndefinition()!=null)
                        urnspec = ((IURNConnection) model).getDiagram().getUrndefinition().getUrnspec();
                    else if (model instanceof IURNContainerRef && ((IURNContainerRef) model).getDiagram() != null && ((IURNContainerRef) model).getDiagram().getUrndefinition()!=null)
                        urnspec = ((IURNContainerRef) model).getDiagram().getUrndefinition().getUrnspec();

                }
                if (urnspec != null)
                    break;
            }
        }

        setType();
    }

    /**
     * Given the internal variable state, set the selection type.
     * 
     */
    private void setType() {
        if (connect != null)
            selectionType = CONNECT;
        else if (startpoint != null && directionarrow != null)
            selectionType = STARTPOINT_DIRECTIONARROW;
        else if (startpoint != null && emptypoint != null)
            selectionType = STARTPOINT_EMPTYPOINT;
        else if (startpoint != null && endpoint != null)
            selectionType = STARTPOINT_ENDPOINT;
        else if (startpoint != null && nodeconnection != null)
            selectionType = STARTPOINT_NODECONNECTION;
        else if (startpoint != null && stub != null)
            selectionType = STARTPOINT_STUB;
        else if (startpoint != null && timer != null)
            selectionType = STARTPOINT_TIMER;
        else if (endpoint != null && directionarrow != null)
            selectionType = ENDPOINT_DIRECTIONARROW;
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
        else if (emptypoint != null && waitingplace != null)
            selectionType = EMPTYPOINT_WAITINGPLACE;
        else if (emptypoint != null && timer != null)
            selectionType = EMPTYPOINT_TIMER;
        else if (emptypoint != null)
            selectionType = EMPTYPOINT;
        else if (directionarrow != null)
            selectionType = DIRECTIONARROW;
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
        else if (intentionalelementref != null)
            selectionType = INTENTIONALELEMENTREF;
        else if (belief != null)
            selectionType = BELIEF;
        else if (actorref != null)
            selectionType = ACTORREF;
        else if (linkref != null)
            selectionType = LINKREF;
        else if (group != null)
            selectionType = EVALUATIONGROUP;
        else if (strategy != null)
            selectionType = EVALUATIONSTRATEGY;
        else if (grlgraph != null)
            selectionType = GRLGRAPH;        
        else if (urnspec != null)
            selectionType = URNSPEC;
        else
            selectionType = OTHER;

    }

    public DirectionArrow getDirectionarrow() {
        return directionarrow;
    }

    public ActorRef getActorref() {
        return actorref;
    }

    public Belief getBelief() {
        return belief;
    }

    public GRLGraph getGrlgraph() {
        return grlgraph;
    }

    public IntentionalElementRef getIntentionalelementref() {
        return intentionalelementref;
    }

    public LinkRef getLinkref() {
        return linkref;
    }
    
    public StrategiesGroup getStrategiesGroup(){
        return group;
    }
    
    public EvaluationStrategy getEvaluationStrategy(){
        return strategy;
    }
}