package seg.jUCMNav.actions;

import grl.Actor;
import grl.ActorRef;
import grl.Belief;
import grl.Contribution;
import grl.ContributionChange;
import grl.ContributionContext;
import grl.ContributionContextGroup;
import grl.Decomposition;
import grl.Dependency;
import grl.ElementLink;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.GRLNode;
import grl.GRLspec;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.LinkRef;
import grl.StrategiesGroup;
import grl.kpimodel.IndicatorGroup;
import grl.kpimodel.KPIConversion;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;
import grl.kpimodel.KPIModelLink;
import grl.kpimodel.KPIModelLinkRef;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;

import seg.jUCMNav.editparts.NodeConnectionEditPart;
import seg.jUCMNav.editparts.treeEditparts.LabelTreeEditPart;
import ucm.UCMspec;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.Anything;
import ucm.map.ComponentRef;
import ucm.map.Connect;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.FailurePoint;
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
import ucm.scenario.EnumerationType;
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import urn.URNspec;
import urncore.Comment;
import urncore.Component;
import urncore.ComponentLabel;
import urncore.Concern;
import urncore.Condition;
import urncore.GRLmodelElement;
import urncore.IURNConnection;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.NodeLabel;
import urncore.Responsibility;
import urncore.UCMmodelElement;
import urncore.URNmodelElement;

/**
 * This class will help reduce redundant code in all action classes. When given a selection, it parses it and gives utility functions to return its type and
 * allows for easy access to the concerned model elements by encapsulating the casts and other management overhead.
 * 
 * The general concept is to set the type to the exact selection and infer some variables (such as the URNspec) from the given selection.
 * 
 * Not the most elegant of classes, but helps simplify everything outside of it :)
 * 
 * @author jkealey, gunterm, pchen, damyot
 * 
 */
public class SelectionHelper {
    // SelectionTypes
    public static final int ANDFORK = 13;
    public static final int ANDJOIN = 14;
    public static final int ANYTHING = 21;
    public static final int COMPONENTLABEL = 10;
    public static final int COMPONENTREF = 11;
    public static final int COMPONENT = 125;
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
    public static final int RESPONSIBILITYREF = 4;
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
    public static final int SCENARIOGROUP = 119;
    public static final int SCENARIO = 120;
    public static final int RESPONSIBILITY = 121;
    public static final int CONDITION = 122;
    public static final int INITIALIZATION = 123;
    public static final int FAILUREPOINT = 20;

    // GRL constant
    public static final int GRLGRAPH = 200;
    public static final int ACTORREF = 201;
    public static final int BELIEF = 202;
    public static final int INTENTIONALELEMENTREF = 203;
    public static final int LINKREF = 204;
    public static final int EVALUATIONGROUP = 205;
    public static final int EVALUATIONSTRATEGY = 206;
    public static final int ACTOR = 207;
    public static final int INTENTIONALELEMENT = 208;
    public static final int KPIINFORMATIONELEMENT = 209;
    public static final int KPIINFORMATIONELEMENTREF = 210;
    public static final int KPIMODELLINK = 211;
    public static final int KPIMODELLINKREF = 212;
    public static final int KPIINFORMATIONCONFIG = 213;
    public static final int INDICATORGROUP = 214;
    public static final int CONTRIBUTION = 215;
    public static final int DECOMPOSITION = 216;
    public static final int DEPENDENCY = 217;
    public static final int CONTRIBUTIONCONTEXTGROUP = 218;
    public static final int CONTRIBUTIONCONTEXT = 219;
    public static final int CONTRIBUTIONCHANGE = 220;

    // Concerns
    public static final int CONCERN = 300;
    public static final int COMMENT = 400;

    // internal variables; for quick reference.
    private AndFork andfork;
    private AndJoin andjoin;
    private Anything anything;
    private ComponentLabel componentlabel;
    private Component component;
    private ComponentRef componentref;
    private Connect connect;
    private DirectionArrow directionarrow;
    private EmptyPoint emptypoint;
    private EndPoint endpoint;
    private FailurePoint failurepoint;
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
    private Comment comment;

    private ScenarioGroup scenariogroup;
    private ScenarioDef scenario;
    private Responsibility respdef;
    private Condition condition;
    private Initialization initialization;

    // internal variables for GRL
    private ActorRef actorref;
    private Actor actor;
    private Belief belief;
    private GRLGraph grlgraph;
    private IntentionalElementRef intentionalelementref;
    private IntentionalElement intentionalelement;
    private KPIInformationElementRef kpiInformationElementRef;
    private KPIInformationElement kpiInformationElement;
    private KPIModelLink kpiModelLink;
    private KPIModelLinkRef kpiModelLinkRef;
    private LinkRef linkref;
    private StrategiesGroup group;
    private EvaluationStrategy strategy;
    private IndicatorGroup indicatorGroup;
    private Contribution contribution;
    private Decomposition decomposition;
    private Dependency dependency;
    private ContributionContextGroup contributionContextGroup;
    private ContributionContext contributionContext;
    private ContributionChange contributionChange;

    // internal variables for Concern and others
    private Concern concern;
    private UCMspec ucmspec;
    private GRLspec grlspec;
    private URNmodelElement urnelem;

    private Vector allModel = new Vector();

    public SelectionHelper(List selection) {
        setSelection(selection);
    }

    /**
     * Given an EditPart, set the appropriate internal variable.
     */
    private EObject setInternals(EditPart part) {
        EObject model = (EObject) part.getModel();

        // Separate UCM from GRL and others... Cuts search time by half!
        // Also tries to sort them by likelihood of being found.

        // *** UCM Elements ***
        if (model instanceof UCMmodelElement) {

            // Do the same, but with Path Nodes
            if (model instanceof PathNode) {
                if (model instanceof EmptyPoint) {
                    emptypoint = (EmptyPoint) model;
                    if (emptypoint.getSucc().size() == 2) {
                        // if this throws an exception, a previous command broke the link order.
                        // don't try-catch this
                        connect = (Connect) ((NodeConnection) emptypoint.getSucc().get(1)).getTarget();
                    }
                } else if (model instanceof RespRef) {
                    respref = (RespRef) model;
                    respdef = respref.getRespDef();
                } else if (model instanceof StartPoint) {
                    startpoint = (StartPoint) model;
                    if (startpoint.getPred().size() == 1) {
                        // if this throws an exception, a previous command broke the link order.
                        // don't try-catch this
                        connect = (Connect) ((NodeConnection) startpoint.getPred().get(0)).getSource();
                    }
                } else if (model instanceof EndPoint) {
                    endpoint = (EndPoint) model;
                    if (endpoint.getSucc().size() == 1) {
                        // if this throws an exception, a previous command broke the link order.
                        // don't try-catch this
                        connect = (Connect) ((NodeConnection) endpoint.getSucc().get(0)).getTarget();
                    }
                } else if (model instanceof Stub) {
                    stub = (Stub) model;
                } else if (model instanceof DirectionArrow)
                    directionarrow = (DirectionArrow) model;
                else if (model instanceof OrFork)
                    orfork = (OrFork) model;
                else if (model instanceof FailurePoint)
                    failurepoint = (FailurePoint) model;
                else if (model instanceof Anything)
                    anything = (Anything) anything;
                else if (model instanceof AndFork)
                    andfork = (AndFork) model;
                else if (model instanceof OrJoin)
                    orjoin = (OrJoin) model;
                else if (model instanceof AndJoin)
                    andjoin = (AndJoin) model;
                else if (model instanceof Timer) {
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
                }
            } else if (model instanceof ComponentRef) {
                componentref = (ComponentRef) model;
                component = (Component) componentref.getContDef();
            } else if (model instanceof Responsibility)
                respdef = (Responsibility) model;
            else if (model instanceof Component)
                component = (Component) model;
            else if (model instanceof UCMmap && ((UCMmap) model).getUrndefinition() != null) {
                map = (UCMmap) model;
                urnspec = map.getUrndefinition().getUrnspec();
            } else if (model instanceof ScenarioGroup) {
                scenariogroup = (ScenarioGroup) model;
                ucmspec = scenariogroup.getUcmspec();
                urnspec = scenariogroup.getUcmspec().getUrnspec();
            } else if (model instanceof ScenarioDef) {
                scenario = (ScenarioDef) model;
                scenariogroup = scenario.getGroup();
                if (scenariogroup != null) {
                    ucmspec = scenariogroup.getUcmspec();
                    urnspec = scenariogroup.getUcmspec().getUrnspec();
                }
            } else if (model instanceof Initialization) {
                initialization = (Initialization) model;
                scenario = initialization.getScenarioDef();
                scenariogroup = scenario.getGroup();
                if (scenariogroup != null) {
                    ucmspec = scenariogroup.getUcmspec();
                    urnspec = scenariogroup.getUcmspec().getUrnspec();
                }
            } else if (model instanceof EnumerationType) {
                EnumerationType et = (EnumerationType) model;
                ucmspec = et.getUcmspec();
                urnspec = et.getUcmspec().getUrnspec();
            }

        }

        // *** GRL Elements ***
        else if (model instanceof GRLmodelElement) {
            if (model instanceof IntentionalElementRef) {
                intentionalelementref = (IntentionalElementRef) model;
                intentionalelement = (IntentionalElement) intentionalelementref.getDef();
            } else if (model instanceof ActorRef) {
                actorref = (ActorRef) model;
                actor = (Actor) actorref.getContDef();
            } else if (model instanceof IntentionalElement) {
                intentionalelement = (IntentionalElement) model;
            } else if (model instanceof Actor) {
                actor = (Actor) model;
            } else if (model instanceof KPIInformationElement) {
                kpiInformationElement = (KPIInformationElement) model;
            } else if (model instanceof KPIInformationElementRef) {
                kpiInformationElementRef = (KPIInformationElementRef) model;
            } else if (model instanceof Belief) {
                belief = (Belief) model;
            } else if (model instanceof Contribution) {
                contribution = (Contribution) model;
            } else if (model instanceof Decomposition) {
                decomposition = (Decomposition) model;
            } else if (model instanceof Dependency) {
                dependency = (Dependency) model;
            } else if (model instanceof GRLGraph && ((GRLGraph) model).getUrndefinition() != null) {
                grlgraph = (GRLGraph) model;
                urnspec = grlgraph.getUrndefinition().getUrnspec();
            } else if (model instanceof StrategiesGroup) {
                group = (StrategiesGroup) model;
                grlspec = group.getGrlspec();
                urnspec = group.getGrlspec().getUrnspec();
            } else if (model instanceof EvaluationStrategy) {
                // if( JUCMNavPlugin.isInDebug() ) System.out.println( "EvaluationStrategy found in SelectionHelper." );
                strategy = (EvaluationStrategy) model;
                group = strategy.getGroup();
                if (group != null) {
                    grlspec = group.getGrlspec();
                    urnspec = group.getGrlspec().getUrnspec();
                }
            } else if (model instanceof IndicatorGroup) {
                indicatorGroup = (IndicatorGroup) model;
                grlspec = indicatorGroup.getGrlspec();
                urnspec = indicatorGroup.getGrlspec().getUrnspec();
            } else if (model instanceof ContributionContextGroup) {
                contributionContextGroup = (ContributionContextGroup) model;
                grlspec = contributionContextGroup.getGrlspec();
                urnspec = grlspec.getUrnspec();
            } else if (model instanceof ContributionContext) {
                contributionContext = (ContributionContext) model;
                if (contributionContext.getGroups().size() > 0) {
                    contributionContextGroup = (ContributionContextGroup) contributionContext.getGroups().get(0); // TODO: only one?
                    if (contributionContextGroup != null) {
                        grlspec = contributionContextGroup.getGrlspec();
                        urnspec = grlspec.getUrnspec();
                    }
                }
            } else if (model instanceof ContributionChange) {
                contributionChange = (ContributionChange) model;
                if (contributionChange.getContext() != null) {
                    contributionContext = contributionChange.getContext();
                    if (contributionContext.getGroups().size() > 0) {
                        contributionContextGroup = (ContributionContextGroup) contributionContext.getGroups().get(0); // TODO: only one?
                        if (contributionContextGroup != null) {
                            grlspec = contributionContextGroup.getGrlspec();
                            urnspec = grlspec.getUrnspec();
                        }
                    }
                }
            } else if (model instanceof KPIConversion) {
                KPIConversion kpi = (KPIConversion) model;
                grlspec = kpi.getGrlspec();
                if (grlspec != null)
                    urnspec = grlspec.getUrnspec();
            }
        }

        // Connections
        else if (model instanceof IURNConnection) {
            if (model instanceof NodeConnection) {
                nodeconnection = (NodeConnection) model;
                nodeconnectionmiddle = ((NodeConnectionEditPart) part).getMiddlePoint();
            } else if (model instanceof LinkRef) {
                linkref = (LinkRef) model;
                ElementLink elementLink = (ElementLink) linkref.getLink();
                if (elementLink instanceof Contribution) {
                    contribution = (Contribution) elementLink;
                    urnelem = (URNmodelElement) contribution;
                } else if (elementLink instanceof Decomposition) {
                    decomposition = (Decomposition) elementLink;
                    urnelem = (URNmodelElement) decomposition;
                } else if (elementLink instanceof Dependency) {
                    dependency = (Dependency) elementLink;
                    urnelem = (URNmodelElement) dependency;
                }
            } else if (model instanceof KPIModelLinkRef) {
                kpiModelLinkRef = (KPIModelLinkRef) model;
                kpiModelLink = (KPIModelLink) kpiModelLinkRef.getLink();
                urnelem = (URNmodelElement) kpiModelLink;
            }
        }

        // *** OTHERS ***

        else if (model instanceof NodeLabel) {
            nodelabel = (NodeLabel) model;
        } else if (model instanceof ComponentLabel) {
            componentlabel = (ComponentLabel) model;
        } else if (model instanceof Condition) {
            condition = (Condition) model;
        } else if (model instanceof Comment) {
            comment = (Comment) model;
            if (comment.getDiagram() instanceof UCMmap) {
                map = (UCMmap) comment.getDiagram();
                urnspec = map.getUrndefinition().getUrnspec();
            } else {
                grlgraph = (GRLGraph) comment.getDiagram();
                urnspec = grlgraph.getUrndefinition().getUrnspec();
            }
        } else if (model instanceof Concern) {
            concern = (Concern) model;
            urnspec = concern.getUrndefinition().getUrnspec();

        } else if (model instanceof GRLspec) {
            grlspec = ((GRLspec) model);
            urnspec = ((GRLspec) model).getUrnspec();
        } else if (model instanceof UCMspec) {
            ucmspec = (UCMspec) model;
            urnspec = ((UCMspec) model).getUrnspec();
        } else if (model instanceof URNspec) {
            urnspec = (URNspec) model;
        }

        // Update related variables

        if (model instanceof NodeConnection || model instanceof PathNode || model instanceof ComponentRef) {
            if (model instanceof NodeConnection)
                map = (UCMmap) nodeconnection.getDiagram();
            else if (model instanceof PathNode)
                map = (UCMmap) ((PathNode) model).getDiagram();
            else if (model instanceof ComponentRef)
                map = (UCMmap) ((ComponentRef) model).getDiagram();

            if (map != null && map.getUrndefinition() != null)
                urnspec = map.getUrndefinition().getUrnspec();
        } else if (model instanceof LinkRef || model instanceof GRLNode || model instanceof ActorRef) {
            if (model instanceof LinkRef) {
                grlgraph = (GRLGraph) ((LinkRef) model).getDiagram();
            } else if (model instanceof GRLNode) {
                grlgraph = (GRLGraph) ((GRLNode) model).getDiagram();
            } else if (model instanceof ActorRef) {
                grlgraph = (GRLGraph) ((ActorRef) model).getDiagram();
            }

            if (grlgraph != null && grlgraph.getUrndefinition() != null)
                urnspec = grlgraph.getUrndefinition().getUrnspec();
        }

        if (urnspec == null) {
            if (model instanceof Component) {
                Component element = (Component) model;
                urnspec = element.getUrndefinition().getUrnspec();
            } else if (model instanceof Actor) {
                Actor actor = (Actor) model;
                urnspec = actor.getGrlspec().getUrnspec();
            } else if (model instanceof IntentionalElement) {
                IntentionalElement element = (IntentionalElement) model;
                urnspec = element.getGrlspec().getUrnspec();
            } else if (model instanceof KPIInformationElement) {
                KPIInformationElement element = (KPIInformationElement) model;
                urnspec = element.getGrlspec().getUrnspec();
            } else if (model instanceof ElementLink) {
                ElementLink element = (ElementLink) model;
                urnspec = element.getGrlspec().getUrnspec();
            } else if (model instanceof KPIModelLink) {
                KPIModelLink element = (KPIModelLink) model;
                urnspec = element.getGrlspec().getUrnspec();
            }
        }

        if (model instanceof URNmodelElement) {
            urnelem = (URNmodelElement) model;
        }

        return model;
    }

    /**
     * Given a list of edit parts of eobjects, set the internal variables and internal type.
     * 
     * @param selection
     */
    private void setSelection(List selection) {
        this.selection = selection;
        allModel.clear();
        if ((selection.size() == 1) && selection.get(0) instanceof URNspec) {
            urnspec = (URNspec) selection.get(0);
        }

        if ((selection.size() == 1 || selection.size() == 2) && selection.get(0) instanceof EditPart) {
            EditPart ep = (EditPart) selection.get(0);
            if (ep.getModel() instanceof EObject) {
                allModel.add(setInternals(ep));
            }
        }

        if (selection.size() == 2 && selection.get(1) instanceof EditPart) {
            EditPart ep = (EditPart) selection.get(1);
            if (ep.getModel() instanceof EObject) {
                allModel.add(setInternals(ep));

                if (selection.get(0) instanceof EditPart)
                    allModel.add(((EditPart) selection.get(0)).getModel());
            }

        }

        // just set the URNspec.
        if (selection.size() > 2 || urnspec == null) {
            for (Iterator iter = selection.iterator(); iter.hasNext();) {
                Object element = iter.next();
                if (element instanceof EditPart && ((EditPart) element).getModel() != null) {
                    Object model = ((EditPart) element).getModel();
                    allModel.add(model);

                    if (urnspec == null) {
                        if (model instanceof URNspec)
                            urnspec = (URNspec) model;
                        else if (model instanceof IURNDiagram && ((IURNDiagram) model).getUrndefinition() != null)
                            urnspec = ((IURNDiagram) model).getUrndefinition().getUrnspec();
                        else if (model instanceof IURNNode && ((IURNNode) model).getDiagram() != null
                                && ((IURNNode) model).getDiagram().getUrndefinition() != null)
                            urnspec = ((IURNNode) model).getDiagram().getUrndefinition().getUrnspec();
                        else if (model instanceof IURNConnection && ((IURNConnection) model).getDiagram() != null
                                && ((IURNConnection) model).getDiagram().getUrndefinition() != null)
                            urnspec = ((IURNConnection) model).getDiagram().getUrndefinition().getUrnspec();
                        else if (model instanceof IURNContainerRef && ((IURNContainerRef) model).getDiagram() != null
                                && ((IURNContainerRef) model).getDiagram().getUrndefinition() != null)
                            urnspec = ((IURNContainerRef) model).getDiagram().getUrndefinition().getUrnspec();
                        else if (model instanceof Comment)
                            urnspec = ((Comment) model).getDiagram().getUrndefinition().getUrnspec();
                        else if (model instanceof Responsibility)
                            urnspec = ((Responsibility) model).getUrndefinition().getUrnspec();
                        else if (model instanceof Component)
                            urnspec = ((Component) model).getUrndefinition().getUrnspec();
                        else if (model instanceof ScenarioDef)
                            urnspec = ((ScenarioDef) model).getGroup().getUcmspec().getUrnspec();
                        else if (model instanceof ScenarioGroup)
                            urnspec = ((ScenarioGroup) model).getUcmspec().getUrnspec();
                        else if (model instanceof EvaluationStrategy)
                            urnspec = ((EvaluationStrategy) model).getGroup().getGrlspec().getUrnspec();
                        else if (model instanceof StrategiesGroup)
                            urnspec = ((StrategiesGroup) model).getGrlspec().getUrnspec();
                    }

                    if (urnspec == null && element instanceof LabelTreeEditPart) {
                        urnspec = ((LabelTreeEditPart) element).getURNSpec();
                    }
                }
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
            selectionType = RESPONSIBILITYREF;
        else if (respdef != null)
            selectionType = RESPONSIBILITY;
        else if (condition != null)
            selectionType = CONDITION;
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
        else if (component != null)
            selectionType = COMPONENT;
        else if (comment != null)
            selectionType = COMMENT;
        else if (nodelabel != null)
            selectionType = NODELABEL;
        else if (orfork != null)
            selectionType = ORFORK;
        else if (andfork != null)
            selectionType = ANDFORK;
        else if (anything != null)
            selectionType = ANYTHING;
        else if (failurepoint != null)
            selectionType = FAILUREPOINT;
        else if (orjoin != null)
            selectionType = ORJOIN;
        else if (andjoin != null)
            selectionType = ANDJOIN;
        else if (map != null)
            selectionType = MAP;
        else if (intentionalelementref != null)
            selectionType = INTENTIONALELEMENTREF;
        else if (intentionalelement != null)
            selectionType = INTENTIONALELEMENT;
        else if (kpiInformationElement != null)
            selectionType = KPIINFORMATIONELEMENT;
        else if (kpiInformationElementRef != null)
            selectionType = KPIINFORMATIONELEMENTREF;
        else if (belief != null)
            selectionType = BELIEF;
        else if (actorref != null)
            selectionType = ACTORREF;
        else if (actor != null)
            selectionType = ACTOR;
        else if (linkref != null)
            selectionType = LINKREF;
        else if (kpiModelLinkRef != null)
            selectionType = KPIMODELLINKREF;
        else if (strategy != null)
            selectionType = EVALUATIONSTRATEGY;
        else if (group != null)
            selectionType = EVALUATIONGROUP;
        else if (scenario != null)
            selectionType = SCENARIO;
        else if (scenariogroup != null)
            selectionType = SCENARIOGROUP;
        else if (initialization != null)
            selectionType = INITIALIZATION;
        else if (contributionChange != null)
            selectionType = CONTRIBUTIONCHANGE;
        else if (contributionContext != null)
            selectionType = CONTRIBUTIONCONTEXT;
        else if (contributionContextGroup != null)
            selectionType = CONTRIBUTIONCONTEXTGROUP;
        else if (grlgraph != null)
            selectionType = GRLGRAPH;
        else if (concern != null)
            selectionType = CONCERN;
        else if (urnspec != null)
            selectionType = URNSPEC;
        else
            selectionType = OTHER;

    }

    // GETTERS

    public AndFork getAndfork() {
        return andfork;
    }

    public AndJoin getAndjoin() {
        return andjoin;
    }

    public Anything getAnything() {
        return anything;
    }

    public Component getComponent() {
        return component;
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

    public FailurePoint getFailurePoint() {
        return failurepoint;
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

    public RespRef getRespRef() {
        return respref;
    }

    public Responsibility getRespDef() {
        return respdef;
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

    public Initialization getInitialization() {
        return initialization;
    }

    public Comment getComment() {
        return comment;
    }

    public DirectionArrow getDirectionarrow() {
        return directionarrow;
    }

    public Actor getActor() {
        return actor;
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

    public IntentionalElement getIntentionalElement() {
        return intentionalelement;
    }

    public IntentionalElementRef getIntentionalElementRef() {
        return intentionalelementref;
    }

    public KPIInformationElement getKPIInformationElement() {
        return kpiInformationElement;
    }

    public KPIInformationElementRef getKPIInformationElementref() {
        return kpiInformationElementRef;
    }

    public LinkRef getLinkref() {
        return linkref;
    }

    public KPIModelLinkRef getKPIModelLinkRef() {
        return kpiModelLinkRef;
    }

    public StrategiesGroup getStrategiesGroup() {
        return group;
    }

    public IndicatorGroup getIndicatorGroup() {
        return indicatorGroup;
    }

    public EvaluationStrategy getEvaluationStrategy() {
        return strategy;
    }

    public Concern getConcern() {
        return concern;
    }

    public ScenarioGroup getScenarioGroup() {
        return scenariogroup;
    }

    public ScenarioDef getScenario() {
        return scenario;
    }

    public GRLspec getGRLspec() {
        return grlspec;
    }

    public UCMspec getUCMspec() {
        return ucmspec;
    }

    public Condition getCondition() {
        return condition;
    }

    public URNmodelElement getURNmodelElement() {
        return urnelem;
    }

    public Point getNodeconnectionmiddle() {
        return nodeconnectionmiddle;
    }

    public RespRef getRespref() {
        return respref;
    }

    public WaitingPlace getWaitingplace() {
        return waitingplace;
    }

    public ScenarioGroup getScenariogroup() {
        return scenariogroup;
    }

    public Responsibility getRespdef() {
        return respdef;
    }

    public KPIInformationElementRef getKpiInformationElementRef() {
        return kpiInformationElementRef;
    }

    public KPIInformationElement getKpiInformationElement() {
        return kpiInformationElement;
    }

    public KPIModelLink getKpiModelLink() {
        return kpiModelLink;
    }

    public KPIModelLinkRef getKpiModelLinkRef() {
        return kpiModelLinkRef;
    }

    public StrategiesGroup getGroup() {
        return group;
    }

    public Vector getAllModel() {
        return allModel;
    }

    public EvaluationStrategy getStrategy() {
        return strategy;
    }

    public Contribution getContribution() {
        return contribution;
    }

    public Decomposition getDecomposition() {
        return decomposition;
    }

    public Dependency getDependency() {
        return dependency;
    }

    public UCMspec getUcmspec() {
        return ucmspec;
    }

    public GRLspec getGrlspec() {
        return grlspec;
    }

    public ContributionContextGroup getContributionContextGroup() {
        return contributionContextGroup;
    }

    public void setContributionContextGroup(ContributionContextGroup contributionContextGroup) {
        this.contributionContextGroup = contributionContextGroup;
    }

    public ContributionContext getContributionContext() {
        return contributionContext;
    }

    public void setContributionContext(ContributionContext contributionContext) {
        this.contributionContext = contributionContext;
    }

    public ContributionChange getContributionChange() {
        return contributionChange;
    }

    public void setContributionChange(ContributionChange contributionChange) {
        this.contributionChange = contributionChange;
    }
}