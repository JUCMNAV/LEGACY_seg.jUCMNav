package seg.jUCMNav.importexport.csm.duplicate;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Vector;

import org.eclipse.core.resources.IMarker;

import seg.jUCMNav.importexport.csm.Messages;
import seg.jUCMNav.importexport.csm.implicit.CSMResource;
import seg.jUCMNav.importexport.csm.implicit.CSMResourceSet;
import seg.jUCMNav.importexport.csm.one2one.AbstractConverter;
import seg.jUCMNav.importexport.csm.one2one.AndForkConverter;
import seg.jUCMNav.importexport.csm.one2one.AndJoinConverter;
import seg.jUCMNav.importexport.csm.one2one.ConnectConverter;
import seg.jUCMNav.importexport.csm.one2one.CsmExportWarning;
import seg.jUCMNav.importexport.csm.one2one.DirectionArrowConverter;
import seg.jUCMNav.importexport.csm.one2one.EmptyPointConverter;
import seg.jUCMNav.importexport.csm.one2one.EndPointConverter;
import seg.jUCMNav.importexport.csm.one2one.OrForkConverter;
import seg.jUCMNav.importexport.csm.one2one.OrJoinConverter;
import seg.jUCMNav.importexport.csm.one2one.ResponsibilityRefConverter;
import seg.jUCMNav.importexport.csm.one2one.StartPointConverter;
import seg.jUCMNav.importexport.csm.one2one.StubConverter;
import seg.jUCMNav.importexport.csm.one2one.TimerConverter;
import seg.jUCMNav.importexport.csm.one2one.WaitingPlaceConverter;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.Connect;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.WaitingPlace;

/**
 * A CSMDupNode is a reference to a node in the original UCMmap.
 * 
 */
public class CSMDupNode {

    // The various types of PathNode elements in jUCMNav
    static public final int RESPREF = 1;

    static public final int START = 2;

    static public final int END = 3;

    static public final int EMPTY = 4;

    static public final int TIMESTAMP = 5;

    static public final int FAILURE = 6;

    static public final int ARROW = 7;

    static public final int CONNECT = 8;

    static public final int STUB = 9;

    static public final int ABORT = 10;

    static public final int WAIT = 11;

    static public final int ORFORK = 12;

    static public final int ANDFORK = 13;

    static public final int ORJOIN = 14;

    static public final int ANDJOIN = 15;

    static public final int LOOP = 16;

    static public final int UNDEFINED = 0;

    // Types of new elements, necessary to produce CSM
    static public final int RA = 17; // Resource Allocate

    static public final int RR = 18; // Resource Release

    static public final int CSMEMPTY = 19; // new Empty Point

    static public final int CSMDUMMY = 20; // new Dummy Step

    static public final int CSMSTEP = 21; // EmptyPoint into DummyStep

    private int type = UNDEFINED;

    private CSMResourceSet resourcesDownstream = null;

    private CSMResourceSet resourcesUpstream = null;

    private CSMResource resourceToAcquire = null;

    private CSMResource resourceToRelease = null;

    private String res = null;

    /**
     * 
     * @param resource
     */
    public void setResToAcquire(String resource) {
        res = resource;
    }

    /**
     * 
     * @return the resource to be acquired
     */
    public String getResToAcquire() {
        return res;
    }

    /**
     * 
     * @param resAttribs
     */
    public void setResourceToAcquire(CSMResource resAttribs) {
        resourceToAcquire = resAttribs;
    }

    public CSMResource getResourceToAcquire() {
        return resourceToAcquire;
    }

    public void setResToRelease(String res) {
        this.res = res;
    }

    public String getResToRelease() {
        return res;
    }

    public void setResourceToRelease(CSMResource resAttribs) {
        resourceToRelease = resAttribs;
    }

    public CSMResource getResourceToRelease() {
        return resourceToRelease;
    }

    /**
     * Converts TypeOfNode(int) to String (for debugging purposes)
     * 
     * @return string corresponding to the node type
     */
    public String getTypeString() {
        String textual;
        if (type == RESPREF) {
            textual = "RESPREF"; //$NON-NLS-1$
        } else if (type == START) {
            textual = "START"; //$NON-NLS-1$
        } else if (type == END) {
            textual = "END"; //$NON-NLS-1$
        } else if (type == EMPTY) {
            textual = "EMPTY"; //$NON-NLS-1$
        } else if (type == TIMESTAMP) {
            textual = "TIMESTAMP"; //$NON-NLS-1$
        } else if (type == FAILURE) {
            textual = "FAILURE"; //$NON-NLS-1$
        } else if (type == ARROW) {
            textual = "ARROW"; //$NON-NLS-1$
        } else if (type == CONNECT) {
            textual = "CONNECT"; //$NON-NLS-1$
        } else if (type == STUB) {
            textual = "STUB"; //$NON-NLS-1$
        } else if (type == ABORT) {
            textual = "ABORT"; //$NON-NLS-1$
        } else if (type == WAIT) {
            textual = "WAIT"; //$NON-NLS-1$
        } else if (type == ORFORK) {
            textual = "ORFORK"; //$NON-NLS-1$
        } else if (type == ANDFORK) {
            textual = "ANDFORK"; //$NON-NLS-1$
        } else if (type == ORJOIN) {
            textual = "ORJOIN"; //$NON-NLS-1$
        } else if (type == ANDJOIN) {
            textual = "ANDJOIN"; //$NON-NLS-1$
        } else if (type == LOOP) {
            textual = "LOOP"; //$NON-NLS-1$
        } else if (type == UNDEFINED) {
            textual = "UNDEFINED"; //$NON-NLS-1$
        } else if (type == RA) {
            textual = "RA"; //$NON-NLS-1$
        } else if (type == RR) {
            textual = "RR"; //$NON-NLS-1$
        } else if (type == CSMEMPTY) {
            textual = "CSMEMPTY"; //$NON-NLS-1$
        } else if (type == CSMDUMMY) {
            textual = "CSMDUMMY"; //$NON-NLS-1$
        } else if (type == CSMSTEP) {
            textual = "CSMSTEP"; //$NON-NLS-1$
        } else {
            textual = "NOT DEFINED IN SYSTEM"; //$NON-NLS-1$
        }
        return textual;
    }

    // Reference to the PathNode in jUCMNav's UCM model
    private PathNode node;

    // id for ra, rr or sequence
    private String node_id;

    /**
     * Constructor
     * 
     * @param node
     * @param warnings
     */
    public CSMDupNode(PathNode node, Vector warnings) {
        this.node = node;
        // Set the node type
        if (node instanceof OrJoin) {
            type = ORJOIN;
        } else if (node instanceof AndJoin) {
            type = ANDJOIN;
        } else if (node instanceof OrFork) {
            type = ORFORK;
        } else if (node instanceof AndFork) {
            type = ANDFORK;
        } else if (node instanceof StartPoint) {
            type = START;
            // resourcesDownstream = new CSMResourceSet(node);
            // resourcesUpstream = resourcesDownstream;
        } else if (node instanceof EndPoint) {
            type = END;
            // resourcesDownstream = new CSMResourceSet(node);
            // resourcesUpstream = resourcesDownstream;
        } else if (node instanceof EmptyPoint) {
            type = EMPTY;
        } else if (node instanceof Stub) {
            type = STUB;
            resourcesDownstream = new CSMResourceSet(node, warnings);
            resourcesUpstream = resourcesDownstream;
        } else if (node instanceof RespRef) {
            type = RESPREF;
            resourcesDownstream = new CSMResourceSet(node, warnings);
            resourcesUpstream = resourcesDownstream;
        } else if (node instanceof OrJoin) {
            type = ORJOIN;
        } else if (node instanceof DirectionArrow) {
            type = ARROW;
        } else if (node instanceof Connect) {
            type = CONNECT;
        } else if (node instanceof WaitingPlace) {
            type = WAIT;
        } else {
            type = UNDEFINED;
        }
    }

    public CSMResourceSet getResourcesDownstream() {
        return resourcesDownstream;
    }

    public void setResourcesDownstream(CSMResourceSet usedResources) {
        resourcesDownstream = usedResources;
    }

    public CSMResourceSet getResourcesUpstream() {
        return resourcesUpstream;
    }

    public void setResourcesUpstream(CSMResourceSet usedResources) {
        resourcesUpstream = usedResources;
    }

    /**
     * 
     * @return the type of node
     */
    public int getType() {
        return type;
    }

    /**
     * Set the type of node.
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Set the nodeId
     * 
     * @param nodeId
     */
    public void setID(String nodeId) {
        node_id = nodeId;
    }

    /**
     * The new node is entirely defined by its ID; this is typically a node added to obtain a CSM-compliant structure or to add RA/RR nodes. <BR>
     * TODO: wave limitations
     * 
     * @param raORrrORseq
     *            node ID, of an RA, RR, Dummy Sequence or Dummy Step
     */
    public CSMDupNode(int raORrrORseq) {
        // RA,RR/Seq/Dummy Step to be inserted
        if (raORrrORseq >= 1000 && raORrrORseq < 2000) {
            type = RA;
        } else if ((raORrrORseq >= 2000 && raORrrORseq < 3000) || (raORrrORseq >= 4000 && raORrrORseq < 5000)) {
            type = CSMEMPTY;
        } else if (raORrrORseq >= 3000 && raORrrORseq < 4000) {
            type = RR;
        } else if (raORrrORseq >= 5000 && raORrrORseq < 6000) {
            type = CSMDUMMY;
        }
        node_id = "G" + raORrrORseq; //$NON-NLS-1$
    }

    // return the id of the node
    public String getId() {
        String id;
        if (node == null) {
            id = node_id;
        } else {
            id = node.getId();
        }
        return id;
    }

    // return the id of the node if node is a Pathnode, else return null
    public PathNode getNode() {
        PathNode pn;
        if (type == RA || type == RR || type == CSMEMPTY) {
            pn = null;
        } else {
            pn = node;
        }
        return pn;
    }

    public boolean isPathNode() {
        boolean notPathnodeKind = (type == RA || type == RR || type == CSMEMPTY);
        return !notPathnodeKind;
    }

    // Converts object through polymorphism (dynamic binding)
    public void doConvert(AbstractConverter pn, PrintStream ps, ArrayList source, ArrayList target, Vector warnings) {
        pn.Convert(ps, source, target, warnings);
    }

    /**
     * Prints CSM representation for attribute node
     * 
     * @param ps
     * @param source
     * @param target
     * @param warnings
     */
    public void printPathNode(PrintStream ps, ArrayList source, ArrayList target, Vector warnings) {
        // guard against non-path node elements (RA/RR)
        if (node == null)
            return;
        // if UCM object is found, generate CSM representation
        if (node instanceof OrJoin) {
            OrJoinConverter obj = new OrJoinConverter((OrJoin) node);
            doConvert(obj, ps, source, target, warnings);
        } else if (node instanceof AndJoin) {
            AndJoinConverter obj = new AndJoinConverter((AndJoin) node);
            doConvert(obj, ps, source, target, warnings);
        } else if (node instanceof OrFork) {
            OrForkConverter obj = new OrForkConverter((OrFork) node);
            doConvert(obj, ps, source, target, warnings);
        } else if (node instanceof AndFork) {
            AndForkConverter obj = new AndForkConverter((AndFork) node);
            doConvert(obj, ps, source, target, warnings);
        } else if (node instanceof StartPoint) {
            StartPointConverter obj = new StartPointConverter((StartPoint) node);
            doConvert(obj, ps, source, target, warnings);
        } else if (node instanceof EndPoint) {
            EndPointConverter obj = new EndPointConverter((EndPoint) node);
            doConvert(obj, ps, source, target, warnings);
        } else if (node instanceof EmptyPoint) {
            EmptyPointConverter obj = new EmptyPointConverter((EmptyPoint) node);
            doConvert(obj, ps, source, target, warnings);
        } else if (node instanceof Stub) {
            StubConverter obj = new StubConverter((Stub) node);
            doConvert(obj, ps, source, target, warnings);
        } else if (node instanceof RespRef) {
            ResponsibilityRefConverter obj = new ResponsibilityRefConverter((RespRef) node);
            doConvert(obj, ps, source, target, warnings);
        } else if (node instanceof DirectionArrow) {
            DirectionArrowConverter obj = new DirectionArrowConverter((DirectionArrow) node);
            doConvert(obj, ps, source, target, warnings);
        } else if (node instanceof Timer) {
            TimerConverter obj = new TimerConverter((Timer) node);
            doConvert(obj, ps, source, target, warnings);
        } else if (node instanceof Connect) {
            ConnectConverter obj = new ConnectConverter((Connect) node);
            doConvert(obj, ps, source, target, warnings);
        } else if (node instanceof WaitingPlace) {
            WaitingPlaceConverter obj = new WaitingPlaceConverter((WaitingPlace) node);
            doConvert(obj, ps, source, target, warnings);
        } else {
            // Reaching this part would indicate that some major processing is missing
            warnings.add(new CsmExportWarning(Messages.getString("CSMDupNode.NoTypeNotImplemented") + node.getClass().getName(), node, IMarker.SEVERITY_ERROR)); //$NON-NLS-1$
        }
    }
}
