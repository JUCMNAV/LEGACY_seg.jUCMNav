/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import ucm.map.AndFork;
import ucm.map.AndJoin;
import ucm.map.Anything;
import ucm.map.AspectKind;
import ucm.map.ComponentBinding;
import ucm.map.ComponentRef;
import ucm.map.Connect;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.FailureKind;
import ucm.map.FailurePoint;
import ucm.map.InBinding;
import ucm.map.MapFactory;
import ucm.map.MapPackage;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.OrJoin;
import ucm.map.OutBinding;
import ucm.map.PluginBinding;
import ucm.map.PointcutKind;
import ucm.map.RespRef;
import ucm.map.ResponsibilityBinding;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.UCMmap;
import ucm.map.WaitKind;
import ucm.map.WaitingPlace;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MapFactoryImpl extends EFactoryImpl implements MapFactory {
    /**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MapFactory init() {
		try {
			MapFactory theMapFactory = (MapFactory)EPackage.Registry.INSTANCE.getEFactory(MapPackage.eNS_URI);
			if (theMapFactory != null) {
				return theMapFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new MapFactoryImpl();
	}

    /**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public MapFactoryImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case MapPackage.AND_JOIN: return createAndJoin();
			case MapPackage.OUT_BINDING: return createOutBinding();
			case MapPackage.IN_BINDING: return createInBinding();
			case MapPackage.RESP_REF: return createRespRef();
			case MapPackage.OR_JOIN: return createOrJoin();
			case MapPackage.OR_FORK: return createOrFork();
			case MapPackage.CONNECT: return createConnect();
			case MapPackage.NODE_CONNECTION: return createNodeConnection();
			case MapPackage.WAITING_PLACE: return createWaitingPlace();
			case MapPackage.STUB: return createStub();
			case MapPackage.END_POINT: return createEndPoint();
			case MapPackage.START_POINT: return createStartPoint();
			case MapPackage.UC_MMAP: return createUCMmap();
			case MapPackage.PLUGIN_BINDING: return createPluginBinding();
			case MapPackage.COMPONENT_REF: return createComponentRef();
			case MapPackage.TIMER: return createTimer();
			case MapPackage.AND_FORK: return createAndFork();
			case MapPackage.EMPTY_POINT: return createEmptyPoint();
			case MapPackage.DIRECTION_ARROW: return createDirectionArrow();
			case MapPackage.COMPONENT_BINDING: return createComponentBinding();
			case MapPackage.ANYTHING: return createAnything();
			case MapPackage.FAILURE_POINT: return createFailurePoint();
			case MapPackage.RESPONSIBILITY_BINDING: return createResponsibilityBinding();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case MapPackage.WAIT_KIND:
				return createWaitKindFromString(eDataType, initialValue);
			case MapPackage.ASPECT_KIND:
				return createAspectKindFromString(eDataType, initialValue);
			case MapPackage.POINTCUT_KIND:
				return createPointcutKindFromString(eDataType, initialValue);
			case MapPackage.FAILURE_KIND:
				return createFailureKindFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case MapPackage.WAIT_KIND:
				return convertWaitKindToString(eDataType, instanceValue);
			case MapPackage.ASPECT_KIND:
				return convertAspectKindToString(eDataType, instanceValue);
			case MapPackage.POINTCUT_KIND:
				return convertPointcutKindToString(eDataType, instanceValue);
			case MapPackage.FAILURE_KIND:
				return convertFailureKindToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public AndJoin createAndJoin() {
		AndJoinImpl andJoin = new AndJoinImpl();
		return andJoin;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public OutBinding createOutBinding() {
		OutBindingImpl outBinding = new OutBindingImpl();
		return outBinding;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public InBinding createInBinding() {
		InBindingImpl inBinding = new InBindingImpl();
		return inBinding;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public RespRef createRespRef() {
		RespRefImpl respRef = new RespRefImpl();
		return respRef;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public OrJoin createOrJoin() {
		OrJoinImpl orJoin = new OrJoinImpl();
		return orJoin;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public OrFork createOrFork() {
		OrForkImpl orFork = new OrForkImpl();
		return orFork;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Connect createConnect() {
		ConnectImpl connect = new ConnectImpl();
		return connect;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NodeConnection createNodeConnection() {
		NodeConnectionImpl nodeConnection = new NodeConnectionImpl();
		return nodeConnection;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public WaitingPlace createWaitingPlace() {
		WaitingPlaceImpl waitingPlace = new WaitingPlaceImpl();
		return waitingPlace;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Stub createStub() {
		StubImpl stub = new StubImpl();
		return stub;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EndPoint createEndPoint() {
		EndPointImpl endPoint = new EndPointImpl();
		return endPoint;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public StartPoint createStartPoint() {
		StartPointImpl startPoint = new StartPointImpl();
		return startPoint;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public UCMmap createUCMmap() {
		UCMmapImpl ucMmap = new UCMmapImpl();
		return ucMmap;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PluginBinding createPluginBinding() {
		PluginBindingImpl pluginBinding = new PluginBindingImpl();
		return pluginBinding;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ComponentRef createComponentRef() {
		ComponentRefImpl componentRef = new ComponentRefImpl();
		return componentRef;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Timer createTimer() {
		TimerImpl timer = new TimerImpl();
		return timer;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public AndFork createAndFork() {
		AndForkImpl andFork = new AndForkImpl();
		return andFork;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EmptyPoint createEmptyPoint() {
		EmptyPointImpl emptyPoint = new EmptyPointImpl();
		return emptyPoint;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DirectionArrow createDirectionArrow() {
		DirectionArrowImpl directionArrow = new DirectionArrowImpl();
		return directionArrow;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentBinding createComponentBinding() {
		ComponentBindingImpl componentBinding = new ComponentBindingImpl();
		return componentBinding;
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Anything createAnything() {
		AnythingImpl anything = new AnythingImpl();
		return anything;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FailurePoint createFailurePoint() {
		FailurePointImpl failurePoint = new FailurePointImpl();
		return failurePoint;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ResponsibilityBinding createResponsibilityBinding() {
		ResponsibilityBindingImpl responsibilityBinding = new ResponsibilityBindingImpl();
		return responsibilityBinding;
	}

                /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WaitKind createWaitKindFromString(EDataType eDataType, String initialValue) {
		WaitKind result = WaitKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertWaitKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public AspectKind createAspectKindFromString(EDataType eDataType, String initialValue) {
		AspectKind result = AspectKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertAspectKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PointcutKind createPointcutKindFromString(EDataType eDataType, String initialValue) {
		PointcutKind result = PointcutKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertPointcutKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public FailureKind createFailureKindFromString(EDataType eDataType, String initialValue) {
		FailureKind result = FailureKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertFailureKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public MapPackage getMapPackage() {
		return (MapPackage)getEPackage();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
    public static MapPackage getPackage() {
		return MapPackage.eINSTANCE;
	}

} //MapFactoryImpl
