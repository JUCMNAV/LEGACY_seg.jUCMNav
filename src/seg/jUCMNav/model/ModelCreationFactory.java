package seg.jUCMNav.model;

import java.text.DateFormat;
import java.util.Date;

import org.eclipse.gef.requests.CreationFactory;

import seg.jUCMNav.model.commands.changeConstraints.SetConstraintComponentRefCommand;
import seg.jUCMNav.model.util.URNNamingHelper;
import ucm.UCMspec;
import ucm.UcmFactory;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.ComponentRef;
import ucm.map.Connect;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.InBinding;
import ucm.map.Map;
import ucm.map.MapFactory;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.OutBinding;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.WaitingPlace;
import ucm.performance.PerformanceFactory;
import ucm.performance.Workload;
import urn.URNlink;
import urn.URNspec;
import urn.UrnFactory;
import urncore.Component;
import urncore.ComponentElement;
import urncore.ComponentKind;
import urncore.ComponentLabel;
import urncore.Condition;
import urncore.GRLmodelElement;
import urncore.NodeLabel;
import urncore.Responsibility;
import urncore.UCMmodelElement;
import urncore.URNdefinition;
import urncore.UrncoreFactory;

/**
 * This class implements the CreationFactory to be used as the central point to obtain new model elements. It sets up the default values for all new elements.
 * It in turn uses the EMF-generated factories to create the model instances
 * 
 * Our application will use the static getNewObject methods to access the factories. The palette needs to be passed a CreationFactory; that is the reason of the
 * non-static methods.
 * 
 * Since it would make no sense to provide a URNspec to be able to obtain one, an additional specific method was created for this task: getNewURNspec().
 *  
 * See DevDocModelCreationFactory
 *  
 * @author jkealey
 *  
 */
public class ModelCreationFactory implements CreationFactory {
    private Class targetClass;
    private int type;
    private URNspec urn;

    /**
     * @param urn
     *            The URNspec which contains information about the last ID created (for unique IDs). Use null if the class does not have an id/name.
     * @param targetClass
     *            The class we need to create from this factory.
     */
    public ModelCreationFactory(URNspec urn, Class targetClass) {
        this.urn = urn;
        this.targetClass = targetClass;
        this.type = 0;
    }

    /**
     * @param urn
     *            The URNspec which contains information about the last ID created (for unique IDs). Use null if the class does not have an id/name.
     * @param targetClass
     *            The class we need to create from this factory.
     * @param type
     *            If this is a ComponentRef, we can pass the ComponentKind.
     */
    public ModelCreationFactory(URNspec urn, Class targetClass, int type) {
        this.urn = urn;
        this.targetClass = targetClass;
        this.type = type;
    }

    /**
     * @return the target class.
     * 
     * @see org.eclipse.gef.requests.CreationFactory#getObjectType()
     */
    public Object getObjectType() {
        return targetClass;
    }

    /**
     * @return the object defined by the constructor parameters
     */
    public Object getNewObject() {
        return getNewObject(urn, targetClass, type);
    }

    /**
     * Equivalent to getNewObject(urn, targetClass, 0);
     * 
     * @param targetClass
     *            the class to obtain a new instance of
     * @return the class to obtain a new instance of
     */
    public static Object getNewObject(URNspec urn, Class targetClass) {
        return getNewObject(urn, targetClass, 0);
    }

    /**
     * Returns a new model element preset with its default values. Note that no exception will be thrown for unknown classes but there will be a message printed
     * on the standard output to facilitate debugging for new developers.
     * 
     * @param urn
     *            The URNspec containing the ID seed. Use null if the targetClass does not have an id/name.
     * 
     * @see org.eclipse.gef.requests.CreationFactory#getNewObject()
     */
    public static Object getNewObject(URNspec urn, Class targetClass, int type) {
        MapFactory mapfactory = MapFactory.eINSTANCE;
        UcmFactory ucmfactory = UcmFactory.eINSTANCE;
        UrncoreFactory urncorefactory = UrncoreFactory.eINSTANCE;
        PerformanceFactory performancefactory = PerformanceFactory.eINSTANCE;

        Object result = null;

        if (targetClass != null) {

            // Simple creations
            if (targetClass.equals(URNspec.class)) {
                result = getNewURNspec();
            } else if (targetClass.equals(UCMspec.class)) {
                result = ucmfactory.createUCMspec();
            } else if (targetClass.equals(URNdefinition.class)) {
                result = urncorefactory.createURNdefinition();
            } else if (targetClass.equals(PathGraph.class)) {
                return mapfactory.createPathGraph();
            } else if (targetClass.equals(EmptyPoint.class)) {
                result = mapfactory.createEmptyPoint();
            } else if (targetClass.equals(NodeConnection.class)) {
                result = mapfactory.createNodeConnection();
            } else if (targetClass.equals(DirectionArrow.class)) {
                result = mapfactory.createDirectionArrow();
            } else if (targetClass.equals(Responsibility.class)) {
                result = urncorefactory.createResponsibility();
            } else if (targetClass.equals(NodeLabel.class)) {
                result = urncorefactory.createNodeLabel();
            } else if (targetClass.equals(ComponentLabel.class)) {
                result = urncorefactory.createComponentLabel();
            } else if (targetClass.equals(OrFork.class)) {
                result = mapfactory.createOrFork();
            } else if (targetClass.equals(AndFork.class)) {
                result = mapfactory.createAndFork();
            } else if (targetClass.equals(OrJoin.class)) {
                result = mapfactory.createOrJoin();
            } else if (targetClass.equals(AndJoin.class)) {
                result = mapfactory.createAndJoin();
            } else if (targetClass.equals(WaitingPlace.class)) {
                result = mapfactory.createWaitingPlace();
            } else if (targetClass.equals(Timer.class)) {
                result = mapfactory.createTimer();
            } else if (targetClass.equals(PluginBinding.class)) {
                result = mapfactory.createPluginBinding();
            } else if (targetClass.equals(InBinding.class)) {
                result = mapfactory.createInBinding();
            } else if (targetClass.equals(OutBinding.class)) {
                result = mapfactory.createOutBinding();
            } else if (targetClass.equals(Workload.class)) {
                result = performancefactory.createWorkload();
            } else if (targetClass.equals(Connect.class)) {
                result = mapfactory.createConnect();
            } else {
                // complex creations
                if (targetClass.equals(Map.class)) {
                    // create a map
                    result = mapfactory.createMap();
                    URNNamingHelper.setElementNameAndID(urn, (Map) result);

                    // add an empty pathgraph to this map
                    ((Map) result).setPathGraph(mapfactory.createPathGraph());
                } else if (targetClass.equals(ComponentRef.class)) {
                    // create the component ref
                    result = mapfactory.createComponentRef();

                    // new component refs must have a component definition
                    Component compdef = urncorefactory.createComponent();
                    ((ComponentRef) result).setCompDef(compdef);

                    // define the ComponentKind according to what was set in the construction
                    compdef.setKind(ComponentKind.get(type));

                    URNNamingHelper.setElementNameAndID(urn, compdef);
                    URNNamingHelper.resolveNamingConflict(urn, compdef);

                    ((ComponentRef) result).setHeight(SetConstraintComponentRefCommand.DEFAULT_HEIGHT);
                    ((ComponentRef) result).setWidth(SetConstraintComponentRefCommand.DEFAULT_WIDTH);

                    ((ComponentRef) result).setLabel(urncorefactory.createComponentLabel());
                } else if (targetClass.equals(Component.class)) {
                    result = urncorefactory.createComponent();
                    ((Component) result).setKind(ComponentKind.get(type));
                } else if (targetClass.equals(Stub.class)) {
                    if (type == 0)
                        result = mapfactory.createStub();
                    else {
                        result = mapfactory.createStub();
                        Stub dyn = (Stub) result;
                        dyn.setDynamic(true);
                    }
                } else if (targetClass.equals(RespRef.class)) {
                    // should create responsibility definition
                    result = mapfactory.createRespRef();

                    // new component refs must have a component definition
                    Responsibility respdef = urncorefactory.createResponsibility();
                    ((RespRef) result).setRespDef(respdef);

                    URNNamingHelper.setElementNameAndID(urn, respdef);
                    URNNamingHelper.resolveNamingConflict(urn, respdef);
                } else if (targetClass.equals(Condition.class)) {
                    Condition cond = urncorefactory.createCondition();
                    cond.setExpression("true"); //$NON-NLS-1$
                    cond.setLabel(""); //$NON-NLS-1$
                    result = cond;
                } else if (targetClass.equals(StartPoint.class)) {
                    StartPoint sp = mapfactory.createStartPoint();
                    sp.setPrecondition((Condition) getNewObject(urn, Condition.class));
                    sp.getPrecondition().setDeltaX(40);
                    sp.getPrecondition().setDeltaY(-17);
                    result = sp;
                } else if (targetClass.equals(EndPoint.class)) {
                    EndPoint ep = mapfactory.createEndPoint();
                    ep.setPostcondition((Condition) getNewObject(urn, Condition.class));
                    ep.getPostcondition().setDeltaX(-40);
                    ep.getPostcondition().setDeltaY(-20);
                    result = ep;
                } else {
                    System.out.println("Unknown class passed to ModelCreationFactory"); //$NON-NLS-1$
                }
            }
        }

        // add labels automatically to the required pathnodes.
        if (result instanceof StartPoint || result instanceof EndPoint || result instanceof Stub || result instanceof RespRef || result instanceof WaitingPlace
                || result instanceof Timer) {
            ((PathNode) result).setLabel(urncorefactory.createNodeLabel());
        }

        // set the name and id of model elements
        // doesn't verify unique names.
        if (result instanceof UCMmodelElement || result instanceof GRLmodelElement || result instanceof URNlink) {
            URNNamingHelper.setElementNameAndID(urn, result);
        }

        // verify unique names
        if (result instanceof Responsibility || result instanceof ComponentElement) {
            URNNamingHelper.resolveNamingConflict(urn, (UCMmodelElement) result);
        }

        return result;

    }

    /**
     * @return a new URN spec
     */
    public static URNspec getNewURNspec() {
        MapFactory factory = MapFactory.eINSTANCE;

        URNspec result = null;

        // create the URN spec
        URNspec urnspec = UrnFactory.eINSTANCE.createURNspec();

        // name the URNspec
        urnspec.setName(URNNamingHelper.getPrefix(URNspec.class));

        // seed the global id
        urnspec.setNextGlobalID("1"); //$NON-NLS-1$

        String sDate;
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        sDate = df.format(new Date());
        urnspec.setCreated(sDate);
        urnspec.setModified(sDate);

        urnspec.setUrnVersion("0.9"); //$NON-NLS-1$
        urnspec.setSpecVersion("0"); //$NON-NLS-1$

        // add its URN definition
        urnspec.setUrndef(UrncoreFactory.eINSTANCE.createURNdefinition());

        // add its UCMspec
        urnspec.setUcmspec(UcmFactory.eINSTANCE.createUCMspec());

        // add the new mapp to the UCMspec
        urnspec.getUcmspec().getMaps().add((Map) getNewObject(urnspec, Map.class));

        result = urnspec;
        return result;
    }

}