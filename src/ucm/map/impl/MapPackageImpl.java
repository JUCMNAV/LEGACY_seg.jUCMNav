/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map.impl;

import asd.AsdPackage;
import asd.impl.AsdPackageImpl;
import ca.mcgill.sel.core.CorePackage;
import fm.FmPackage;
import fm.impl.FmPackageImpl;
import grl.GrlPackage;
import grl.impl.GrlPackageImpl;
import grl.kpimodel.KpimodelPackage;
import grl.kpimodel.impl.KpimodelPackageImpl;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import ucm.UcmPackage;
import ucm.impl.UcmPackageImpl;
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
import ucm.map.PathNode;
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
import ucm.performance.PerformancePackage;
import ucm.performance.impl.PerformancePackageImpl;
import ucm.scenario.ScenarioPackage;
import ucm.scenario.impl.ScenarioPackageImpl;
import urn.UrnPackage;
import urn.impl.UrnPackageImpl;
import urncore.UrncorePackage;
import urncore.impl.UrncorePackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MapPackageImpl extends EPackageImpl implements MapPackage {
    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass andJoinEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass outBindingEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass inBindingEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass respRefEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass orJoinEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass orForkEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass connectEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass nodeConnectionEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass waitingPlaceEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass stubEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass pathNodeEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass endPointEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass startPointEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass ucMmapEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass pluginBindingEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass componentRefEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass timerEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass andForkEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass emptyPointEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass directionArrowEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass componentBindingEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass anythingEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass failurePointEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass responsibilityBindingEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum waitKindEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum aspectKindEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum pointcutKindEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum failureKindEEnum = null;

    /**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see ucm.map.MapPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
    private MapPackageImpl() {
		super(eNS_URI, MapFactory.eINSTANCE);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static boolean isInited = false;

    /**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link MapPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
    public static MapPackage init() {
		if (isInited) return (MapPackage)EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI);

		// Obtain or create and register package
		MapPackageImpl theMapPackage = (MapPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MapPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new MapPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		FmPackageImpl theFmPackage = (FmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(FmPackage.eNS_URI) instanceof FmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(FmPackage.eNS_URI) : FmPackage.eINSTANCE);
		GrlPackageImpl theGrlPackage = (GrlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GrlPackage.eNS_URI) instanceof GrlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GrlPackage.eNS_URI) : GrlPackage.eINSTANCE);
		KpimodelPackageImpl theKpimodelPackage = (KpimodelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(KpimodelPackage.eNS_URI) instanceof KpimodelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(KpimodelPackage.eNS_URI) : KpimodelPackage.eINSTANCE);
		UrncorePackageImpl theUrncorePackage = (UrncorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI) instanceof UrncorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI) : UrncorePackage.eINSTANCE);
		UrnPackageImpl theUrnPackage = (UrnPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI) instanceof UrnPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI) : UrnPackage.eINSTANCE);
		UcmPackageImpl theUcmPackage = (UcmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI) instanceof UcmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI) : UcmPackage.eINSTANCE);
		PerformancePackageImpl thePerformancePackage = (PerformancePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI) instanceof PerformancePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI) : PerformancePackage.eINSTANCE);
		ScenarioPackageImpl theScenarioPackage = (ScenarioPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) instanceof ScenarioPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) : ScenarioPackage.eINSTANCE);
		AsdPackageImpl theAsdPackage = (AsdPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AsdPackage.eNS_URI) instanceof AsdPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AsdPackage.eNS_URI) : AsdPackage.eINSTANCE);

		// Create package meta-data objects
		theMapPackage.createPackageContents();
		theFmPackage.createPackageContents();
		theGrlPackage.createPackageContents();
		theKpimodelPackage.createPackageContents();
		theUrncorePackage.createPackageContents();
		theUrnPackage.createPackageContents();
		theUcmPackage.createPackageContents();
		thePerformancePackage.createPackageContents();
		theScenarioPackage.createPackageContents();
		theAsdPackage.createPackageContents();

		// Initialize created meta-data
		theMapPackage.initializePackageContents();
		theFmPackage.initializePackageContents();
		theGrlPackage.initializePackageContents();
		theKpimodelPackage.initializePackageContents();
		theUrncorePackage.initializePackageContents();
		theUrnPackage.initializePackageContents();
		theUcmPackage.initializePackageContents();
		thePerformancePackage.initializePackageContents();
		theScenarioPackage.initializePackageContents();
		theAsdPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theMapPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(MapPackage.eNS_URI, theMapPackage);
		return theMapPackage;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getAndJoin() {
		return andJoinEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getAndJoin_Orientation() {
		return (EAttribute)andJoinEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getOutBinding() {
		return outBindingEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getOutBinding_Binding() {
		return (EReference)outBindingEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getOutBinding_EndPoint() {
		return (EReference)outBindingEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getOutBinding_StubExit() {
		return (EReference)outBindingEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getOutBinding_PointcutEntry() {
		return (EReference)outBindingEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getInBinding() {
		return inBindingEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getInBinding_Binding() {
		return (EReference)inBindingEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getInBinding_StartPoint() {
		return (EReference)inBindingEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getInBinding_StubEntry() {
		return (EReference)inBindingEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getInBinding_PointcutExit() {
		return (EReference)inBindingEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getRespRef() {
		return respRefEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRespRef_RepetitionCount() {
		return (EAttribute)respRefEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRespRef_HostDemand() {
		return (EAttribute)respRefEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getRespRef_RespDef() {
		return (EReference)respRefEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getRespRef_PluginBindings() {
		return (EReference)respRefEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getOrJoin() {
		return orJoinEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getOrJoin_Orientation() {
		return (EAttribute)orJoinEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getOrFork() {
		return orForkEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getOrFork_Orientation() {
		return (EAttribute)orForkEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getConnect() {
		return connectEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getNodeConnection() {
		return nodeConnectionEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getNodeConnection_Probability() {
		return (EAttribute)nodeConnectionEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getNodeConnection_Threshold() {
		return (EAttribute)nodeConnectionEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getNodeConnection_InBindings() {
		return (EReference)nodeConnectionEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getNodeConnection_OutBindings() {
		return (EReference)nodeConnectionEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getNodeConnection_Condition() {
		return (EReference)nodeConnectionEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getNodeConnection_OutBindingsPlugin() {
		return (EReference)nodeConnectionEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getNodeConnection_InBindingsPlugin() {
		return (EReference)nodeConnectionEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getWaitingPlace() {
		return waitingPlaceEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getWaitingPlace_WaitType() {
		return (EAttribute)waitingPlaceEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getStub() {
		return stubEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getStub_Dynamic() {
		return (EAttribute)stubEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getStub_Shared() {
		return (EAttribute)stubEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getStub_RepetitionCount() {
		return (EAttribute)stubEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getStub_Pointcut() {
		return (EAttribute)stubEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getStub_Synchronization() {
		return (EAttribute)stubEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getStub_Blocking() {
		return (EAttribute)stubEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getStub_Aopointcut() {
		return (EAttribute)stubEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getStub_Aspect() {
		return (EAttribute)stubEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getStub_Bindings() {
		return (EReference)stubEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getPathNode() {
		return pathNodeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getEndPoint() {
		return endPointEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getEndPoint_Local() {
		return (EAttribute)endPointEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getEndPoint_OutBindings() {
		return (EReference)endPointEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getEndPoint_Postcondition() {
		return (EReference)endPointEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getEndPoint_ScenarioEndPoints() {
		return (EReference)endPointEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getStartPoint() {
		return startPointEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getStartPoint_Workload() {
		return (EReference)startPointEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getStartPoint_Local() {
		return (EAttribute)startPointEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getStartPoint_FailureKind() {
		return (EAttribute)startPointEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getStartPoint_InBindings() {
		return (EReference)startPointEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getStartPoint_Precondition() {
		return (EReference)startPointEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getStartPoint_ScenarioStartPoints() {
		return (EReference)startPointEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getUCMmap() {
		return ucMmapEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getUCMmap_Singleton() {
		return (EAttribute)ucMmapEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getUCMmap_ParentStub() {
		return (EReference)ucMmapEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getPluginBinding() {
		return pluginBindingEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getPluginBinding_Id() {
		return (EAttribute)pluginBindingEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getPluginBinding_Probability() {
		return (EAttribute)pluginBindingEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getPluginBinding_Transaction() {
		return (EAttribute)pluginBindingEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getPluginBinding_ReplicationFactor() {
		return (EAttribute)pluginBindingEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getPluginBinding_In() {
		return (EReference)pluginBindingEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getPluginBinding_Out() {
		return (EReference)pluginBindingEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getPluginBinding_Stub() {
		return (EReference)pluginBindingEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getPluginBinding_Plugin() {
		return (EReference)pluginBindingEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getPluginBinding_Precondition() {
		return (EReference)pluginBindingEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getPluginBinding_Components() {
		return (EReference)pluginBindingEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getPluginBinding_Responsibilities() {
		return (EReference)pluginBindingEClass.getEStructuralFeatures().get(10);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getComponentRef() {
		return componentRefEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getComponentRef_Role() {
		return (EAttribute)componentRefEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getComponentRef_ReplicationFactor() {
		return (EAttribute)componentRefEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getComponentRef_Anchored() {
		return (EAttribute)componentRefEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getComponentRef_ParentBindings() {
		return (EReference)componentRefEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getComponentRef_PluginBindings() {
		return (EReference)componentRefEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getTimer() {
		return timerEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getTimer_TimeoutPath() {
		return (EReference)timerEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getAndFork() {
		return andForkEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getAndFork_Orientation() {
		return (EAttribute)andForkEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getEmptyPoint() {
		return emptyPointEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getDirectionArrow() {
		return directionArrowEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getComponentBinding() {
		return componentBindingEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getComponentBinding_Binding() {
		return (EReference)componentBindingEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getComponentBinding_ParentComponent() {
		return (EReference)componentBindingEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getComponentBinding_PluginComponent() {
		return (EReference)componentBindingEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getAnything() {
		return anythingEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getFailurePoint() {
		return failurePointEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getFailurePoint_Expression() {
		return (EAttribute)failurePointEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getResponsibilityBinding() {
		return responsibilityBindingEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getResponsibilityBinding_Binding() {
		return (EReference)responsibilityBindingEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getResponsibilityBinding_ParentResp() {
		return (EReference)responsibilityBindingEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getResponsibilityBinding_PluginResp() {
		return (EReference)responsibilityBindingEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getWaitKind() {
		return waitKindEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getAspectKind() {
		return aspectKindEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getPointcutKind() {
		return pointcutKindEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getFailureKind() {
		return failureKindEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public MapFactory getMapFactory() {
		return (MapFactory)getEFactoryInstance();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private boolean isCreated = false;

    /**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		andJoinEClass = createEClass(AND_JOIN);
		createEAttribute(andJoinEClass, AND_JOIN__ORIENTATION);

		outBindingEClass = createEClass(OUT_BINDING);
		createEReference(outBindingEClass, OUT_BINDING__BINDING);
		createEReference(outBindingEClass, OUT_BINDING__END_POINT);
		createEReference(outBindingEClass, OUT_BINDING__STUB_EXIT);
		createEReference(outBindingEClass, OUT_BINDING__POINTCUT_ENTRY);

		inBindingEClass = createEClass(IN_BINDING);
		createEReference(inBindingEClass, IN_BINDING__BINDING);
		createEReference(inBindingEClass, IN_BINDING__START_POINT);
		createEReference(inBindingEClass, IN_BINDING__STUB_ENTRY);
		createEReference(inBindingEClass, IN_BINDING__POINTCUT_EXIT);

		respRefEClass = createEClass(RESP_REF);
		createEAttribute(respRefEClass, RESP_REF__REPETITION_COUNT);
		createEAttribute(respRefEClass, RESP_REF__HOST_DEMAND);
		createEReference(respRefEClass, RESP_REF__RESP_DEF);
		createEReference(respRefEClass, RESP_REF__PLUGIN_BINDINGS);

		orJoinEClass = createEClass(OR_JOIN);
		createEAttribute(orJoinEClass, OR_JOIN__ORIENTATION);

		orForkEClass = createEClass(OR_FORK);
		createEAttribute(orForkEClass, OR_FORK__ORIENTATION);

		connectEClass = createEClass(CONNECT);

		nodeConnectionEClass = createEClass(NODE_CONNECTION);
		createEAttribute(nodeConnectionEClass, NODE_CONNECTION__PROBABILITY);
		createEAttribute(nodeConnectionEClass, NODE_CONNECTION__THRESHOLD);
		createEReference(nodeConnectionEClass, NODE_CONNECTION__IN_BINDINGS);
		createEReference(nodeConnectionEClass, NODE_CONNECTION__OUT_BINDINGS);
		createEReference(nodeConnectionEClass, NODE_CONNECTION__CONDITION);
		createEReference(nodeConnectionEClass, NODE_CONNECTION__OUT_BINDINGS_PLUGIN);
		createEReference(nodeConnectionEClass, NODE_CONNECTION__IN_BINDINGS_PLUGIN);

		waitingPlaceEClass = createEClass(WAITING_PLACE);
		createEAttribute(waitingPlaceEClass, WAITING_PLACE__WAIT_TYPE);

		stubEClass = createEClass(STUB);
		createEAttribute(stubEClass, STUB__DYNAMIC);
		createEAttribute(stubEClass, STUB__SHARED);
		createEAttribute(stubEClass, STUB__REPETITION_COUNT);
		createEAttribute(stubEClass, STUB__POINTCUT);
		createEAttribute(stubEClass, STUB__SYNCHRONIZATION);
		createEAttribute(stubEClass, STUB__BLOCKING);
		createEAttribute(stubEClass, STUB__AOPOINTCUT);
		createEAttribute(stubEClass, STUB__ASPECT);
		createEReference(stubEClass, STUB__BINDINGS);

		pathNodeEClass = createEClass(PATH_NODE);

		endPointEClass = createEClass(END_POINT);
		createEAttribute(endPointEClass, END_POINT__LOCAL);
		createEReference(endPointEClass, END_POINT__OUT_BINDINGS);
		createEReference(endPointEClass, END_POINT__POSTCONDITION);
		createEReference(endPointEClass, END_POINT__SCENARIO_END_POINTS);

		startPointEClass = createEClass(START_POINT);
		createEReference(startPointEClass, START_POINT__WORKLOAD);
		createEAttribute(startPointEClass, START_POINT__LOCAL);
		createEAttribute(startPointEClass, START_POINT__FAILURE_KIND);
		createEReference(startPointEClass, START_POINT__IN_BINDINGS);
		createEReference(startPointEClass, START_POINT__PRECONDITION);
		createEReference(startPointEClass, START_POINT__SCENARIO_START_POINTS);

		ucMmapEClass = createEClass(UC_MMAP);
		createEAttribute(ucMmapEClass, UC_MMAP__SINGLETON);
		createEReference(ucMmapEClass, UC_MMAP__PARENT_STUB);

		pluginBindingEClass = createEClass(PLUGIN_BINDING);
		createEAttribute(pluginBindingEClass, PLUGIN_BINDING__ID);
		createEAttribute(pluginBindingEClass, PLUGIN_BINDING__PROBABILITY);
		createEAttribute(pluginBindingEClass, PLUGIN_BINDING__TRANSACTION);
		createEAttribute(pluginBindingEClass, PLUGIN_BINDING__REPLICATION_FACTOR);
		createEReference(pluginBindingEClass, PLUGIN_BINDING__IN);
		createEReference(pluginBindingEClass, PLUGIN_BINDING__OUT);
		createEReference(pluginBindingEClass, PLUGIN_BINDING__STUB);
		createEReference(pluginBindingEClass, PLUGIN_BINDING__PLUGIN);
		createEReference(pluginBindingEClass, PLUGIN_BINDING__PRECONDITION);
		createEReference(pluginBindingEClass, PLUGIN_BINDING__COMPONENTS);
		createEReference(pluginBindingEClass, PLUGIN_BINDING__RESPONSIBILITIES);

		componentRefEClass = createEClass(COMPONENT_REF);
		createEAttribute(componentRefEClass, COMPONENT_REF__ROLE);
		createEAttribute(componentRefEClass, COMPONENT_REF__REPLICATION_FACTOR);
		createEAttribute(componentRefEClass, COMPONENT_REF__ANCHORED);
		createEReference(componentRefEClass, COMPONENT_REF__PARENT_BINDINGS);
		createEReference(componentRefEClass, COMPONENT_REF__PLUGIN_BINDINGS);

		timerEClass = createEClass(TIMER);
		createEReference(timerEClass, TIMER__TIMEOUT_PATH);

		andForkEClass = createEClass(AND_FORK);
		createEAttribute(andForkEClass, AND_FORK__ORIENTATION);

		emptyPointEClass = createEClass(EMPTY_POINT);

		directionArrowEClass = createEClass(DIRECTION_ARROW);

		componentBindingEClass = createEClass(COMPONENT_BINDING);
		createEReference(componentBindingEClass, COMPONENT_BINDING__BINDING);
		createEReference(componentBindingEClass, COMPONENT_BINDING__PARENT_COMPONENT);
		createEReference(componentBindingEClass, COMPONENT_BINDING__PLUGIN_COMPONENT);

		anythingEClass = createEClass(ANYTHING);

		failurePointEClass = createEClass(FAILURE_POINT);
		createEAttribute(failurePointEClass, FAILURE_POINT__EXPRESSION);

		responsibilityBindingEClass = createEClass(RESPONSIBILITY_BINDING);
		createEReference(responsibilityBindingEClass, RESPONSIBILITY_BINDING__BINDING);
		createEReference(responsibilityBindingEClass, RESPONSIBILITY_BINDING__PLUGIN_RESP);
		createEReference(responsibilityBindingEClass, RESPONSIBILITY_BINDING__PARENT_RESP);

		// Create enums
		waitKindEEnum = createEEnum(WAIT_KIND);
		aspectKindEEnum = createEEnum(ASPECT_KIND);
		pointcutKindEEnum = createEEnum(POINTCUT_KIND);
		failureKindEEnum = createEEnum(FAILURE_KIND);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private boolean isInitialized = false;

    /**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		UrncorePackage theUrncorePackage = (UrncorePackage)EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI);
		ScenarioPackage theScenarioPackage = (ScenarioPackage)EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI);
		PerformancePackage thePerformancePackage = (PerformancePackage)EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI);
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);

		// Add supertypes to classes
		andJoinEClass.getESuperTypes().add(this.getPathNode());
		respRefEClass.getESuperTypes().add(this.getPathNode());
		orJoinEClass.getESuperTypes().add(this.getPathNode());
		orForkEClass.getESuperTypes().add(this.getPathNode());
		connectEClass.getESuperTypes().add(this.getPathNode());
		nodeConnectionEClass.getESuperTypes().add(theUrncorePackage.getIURNConnection());
		waitingPlaceEClass.getESuperTypes().add(this.getPathNode());
		stubEClass.getESuperTypes().add(this.getPathNode());
		pathNodeEClass.getESuperTypes().add(theUrncorePackage.getUCMmodelElement());
		pathNodeEClass.getESuperTypes().add(theUrncorePackage.getIURNNode());
		endPointEClass.getESuperTypes().add(this.getPathNode());
		startPointEClass.getESuperTypes().add(this.getPathNode());
		ucMmapEClass.getESuperTypes().add(theUrncorePackage.getUCMmodelElement());
		ucMmapEClass.getESuperTypes().add(theUrncorePackage.getIURNDiagram());
		ucMmapEClass.getESuperTypes().add(theCorePackage.getCOREModel());
		componentRefEClass.getESuperTypes().add(theUrncorePackage.getUCMmodelElement());
		componentRefEClass.getESuperTypes().add(theUrncorePackage.getIURNContainerRef());
		timerEClass.getESuperTypes().add(this.getWaitingPlace());
		andForkEClass.getESuperTypes().add(this.getPathNode());
		emptyPointEClass.getESuperTypes().add(this.getPathNode());
		directionArrowEClass.getESuperTypes().add(this.getPathNode());
		anythingEClass.getESuperTypes().add(this.getPathNode());
		failurePointEClass.getESuperTypes().add(this.getPathNode());

		// Initialize classes and features; add operations and parameters
		initEClass(andJoinEClass, AndJoin.class, "AndJoin", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAndJoin_Orientation(), ecorePackage.getEString(), "orientation", null, 0, 1, AndJoin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(outBindingEClass, OutBinding.class, "OutBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOutBinding_Binding(), this.getPluginBinding(), this.getPluginBinding_Out(), "binding", null, 1, 1, OutBinding.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOutBinding_EndPoint(), this.getEndPoint(), this.getEndPoint_OutBindings(), "endPoint", null, 0, 1, OutBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOutBinding_StubExit(), this.getNodeConnection(), this.getNodeConnection_OutBindings(), "stubExit", null, 1, 1, OutBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getOutBinding_PointcutEntry(), this.getNodeConnection(), this.getNodeConnection_OutBindingsPlugin(), "pointcutEntry", null, 0, 1, OutBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inBindingEClass, InBinding.class, "InBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInBinding_Binding(), this.getPluginBinding(), this.getPluginBinding_In(), "binding", null, 1, 1, InBinding.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInBinding_StartPoint(), this.getStartPoint(), this.getStartPoint_InBindings(), "startPoint", null, 0, 1, InBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInBinding_StubEntry(), this.getNodeConnection(), this.getNodeConnection_InBindings(), "stubEntry", null, 1, 1, InBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInBinding_PointcutExit(), this.getNodeConnection(), this.getNodeConnection_InBindingsPlugin(), "pointcutExit", null, 0, 1, InBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(respRefEClass, RespRef.class, "RespRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRespRef_RepetitionCount(), ecorePackage.getEString(), "repetitionCount", "1", 0, 1, RespRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRespRef_HostDemand(), ecorePackage.getEString(), "hostDemand", null, 0, 1, RespRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRespRef_RespDef(), theUrncorePackage.getResponsibility(), theUrncorePackage.getResponsibility_RespRefs(), "respDef", null, 1, 1, RespRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRespRef_PluginBindings(), this.getResponsibilityBinding(), this.getResponsibilityBinding_PluginResp(), "pluginBindings", null, 0, -1, RespRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(orJoinEClass, OrJoin.class, "OrJoin", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOrJoin_Orientation(), ecorePackage.getEString(), "orientation", null, 0, 1, OrJoin.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(orForkEClass, OrFork.class, "OrFork", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOrFork_Orientation(), ecorePackage.getEString(), "orientation", null, 0, 1, OrFork.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(connectEClass, Connect.class, "Connect", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(nodeConnectionEClass, NodeConnection.class, "NodeConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNodeConnection_Probability(), ecorePackage.getEDouble(), "probability", "1.0", 0, 1, NodeConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNodeConnection_Threshold(), ecorePackage.getEString(), "threshold", null, 0, 1, NodeConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNodeConnection_InBindings(), this.getInBinding(), this.getInBinding_StubEntry(), "inBindings", null, 0, -1, NodeConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNodeConnection_OutBindings(), this.getOutBinding(), this.getOutBinding_StubExit(), "outBindings", null, 0, -1, NodeConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNodeConnection_Condition(), theUrncorePackage.getCondition(), theUrncorePackage.getCondition_NodeConnection(), "condition", null, 0, 1, NodeConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNodeConnection_OutBindingsPlugin(), this.getOutBinding(), this.getOutBinding_PointcutEntry(), "outBindingsPlugin", null, 0, -1, NodeConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNodeConnection_InBindingsPlugin(), this.getInBinding(), this.getInBinding_PointcutExit(), "inBindingsPlugin", null, 0, -1, NodeConnection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(waitingPlaceEClass, WaitingPlace.class, "WaitingPlace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWaitingPlace_WaitType(), this.getWaitKind(), "waitType", null, 0, 1, WaitingPlace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stubEClass, Stub.class, "Stub", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStub_Dynamic(), ecorePackage.getEBoolean(), "dynamic", "false", 0, 1, Stub.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStub_Shared(), ecorePackage.getEBoolean(), "shared", "false", 0, 1, Stub.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStub_RepetitionCount(), ecorePackage.getEString(), "repetitionCount", "1", 0, 1, Stub.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStub_Pointcut(), ecorePackage.getEBoolean(), "pointcut", "false", 0, 1, Stub.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStub_Synchronization(), ecorePackage.getEBoolean(), "synchronization", "false", 0, 1, Stub.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStub_Blocking(), ecorePackage.getEBoolean(), "blocking", "false", 0, 1, Stub.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStub_Aopointcut(), this.getPointcutKind(), "aopointcut", "None", 0, 1, Stub.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStub_Aspect(), this.getAspectKind(), "aspect", "None", 0, 1, Stub.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStub_Bindings(), this.getPluginBinding(), this.getPluginBinding_Stub(), "bindings", null, 0, -1, Stub.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pathNodeEClass, PathNode.class, "PathNode", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(endPointEClass, EndPoint.class, "EndPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEndPoint_Local(), ecorePackage.getEBoolean(), "local", "false", 0, 1, EndPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEndPoint_OutBindings(), this.getOutBinding(), this.getOutBinding_EndPoint(), "outBindings", null, 0, -1, EndPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEndPoint_Postcondition(), theUrncorePackage.getCondition(), theUrncorePackage.getCondition_EndPoint(), "postcondition", null, 0, 1, EndPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEndPoint_ScenarioEndPoints(), theScenarioPackage.getScenarioEndPoint(), theScenarioPackage.getScenarioEndPoint_EndPoint(), "scenarioEndPoints", null, 0, -1, EndPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(startPointEClass, StartPoint.class, "StartPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStartPoint_Workload(), thePerformancePackage.getWorkload(), thePerformancePackage.getWorkload_StartPoint(), "workload", null, 0, 1, StartPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStartPoint_Local(), ecorePackage.getEBoolean(), "local", "false", 0, 1, StartPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStartPoint_FailureKind(), this.getFailureKind(), "failureKind", "None", 0, 1, StartPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStartPoint_InBindings(), this.getInBinding(), this.getInBinding_StartPoint(), "inBindings", null, 0, -1, StartPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStartPoint_Precondition(), theUrncorePackage.getCondition(), theUrncorePackage.getCondition_StartPoint(), "precondition", null, 0, 1, StartPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStartPoint_ScenarioStartPoints(), theScenarioPackage.getScenarioStartPoint(), theScenarioPackage.getScenarioStartPoint_StartPoint(), "scenarioStartPoints", null, 0, -1, StartPoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(ucMmapEClass, UCMmap.class, "UCMmap", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUCMmap_Singleton(), ecorePackage.getEBoolean(), "singleton", "true", 0, 1, UCMmap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUCMmap_ParentStub(), this.getPluginBinding(), this.getPluginBinding_Plugin(), "parentStub", null, 0, -1, UCMmap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pluginBindingEClass, PluginBinding.class, "PluginBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPluginBinding_Id(), ecorePackage.getEString(), "id", null, 0, 1, PluginBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPluginBinding_Probability(), ecorePackage.getEDouble(), "probability", "1.0", 0, 1, PluginBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPluginBinding_Transaction(), ecorePackage.getEBoolean(), "transaction", "false", 0, 1, PluginBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPluginBinding_ReplicationFactor(), ecorePackage.getEInt(), "replicationFactor", "1", 0, 1, PluginBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPluginBinding_In(), this.getInBinding(), this.getInBinding_Binding(), "in", null, 1, -1, PluginBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPluginBinding_Out(), this.getOutBinding(), this.getOutBinding_Binding(), "out", null, 1, -1, PluginBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPluginBinding_Stub(), this.getStub(), this.getStub_Bindings(), "stub", null, 0, 1, PluginBinding.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPluginBinding_Plugin(), this.getUCMmap(), this.getUCMmap_ParentStub(), "plugin", null, 1, 1, PluginBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPluginBinding_Precondition(), theUrncorePackage.getCondition(), theUrncorePackage.getCondition_PluginBinding(), "precondition", null, 0, 1, PluginBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPluginBinding_Components(), this.getComponentBinding(), this.getComponentBinding_Binding(), "components", null, 0, -1, PluginBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPluginBinding_Responsibilities(), this.getResponsibilityBinding(), this.getResponsibilityBinding_Binding(), "responsibilities", null, 0, -1, PluginBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(componentRefEClass, ComponentRef.class, "ComponentRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComponentRef_Role(), ecorePackage.getEString(), "role", null, 0, 1, ComponentRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentRef_ReplicationFactor(), ecorePackage.getEInt(), "replicationFactor", "1", 0, 1, ComponentRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentRef_Anchored(), ecorePackage.getEBoolean(), "anchored", "false", 0, 1, ComponentRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponentRef_ParentBindings(), this.getComponentBinding(), this.getComponentBinding_ParentComponent(), "parentBindings", null, 0, -1, ComponentRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponentRef_PluginBindings(), this.getComponentBinding(), this.getComponentBinding_PluginComponent(), "pluginBindings", null, 0, -1, ComponentRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(timerEClass, Timer.class, "Timer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTimer_TimeoutPath(), this.getNodeConnection(), null, "timeoutPath", null, 0, 1, Timer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(andForkEClass, AndFork.class, "AndFork", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAndFork_Orientation(), ecorePackage.getEString(), "orientation", null, 0, 1, AndFork.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(emptyPointEClass, EmptyPoint.class, "EmptyPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(directionArrowEClass, DirectionArrow.class, "DirectionArrow", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(componentBindingEClass, ComponentBinding.class, "ComponentBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponentBinding_Binding(), this.getPluginBinding(), this.getPluginBinding_Components(), "binding", null, 1, 1, ComponentBinding.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponentBinding_ParentComponent(), this.getComponentRef(), this.getComponentRef_ParentBindings(), "parentComponent", null, 1, 1, ComponentBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponentBinding_PluginComponent(), this.getComponentRef(), this.getComponentRef_PluginBindings(), "pluginComponent", null, 1, 1, ComponentBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(anythingEClass, Anything.class, "Anything", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(failurePointEClass, FailurePoint.class, "FailurePoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFailurePoint_Expression(), ecorePackage.getEString(), "expression", null, 0, 1, FailurePoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(responsibilityBindingEClass, ResponsibilityBinding.class, "ResponsibilityBinding", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResponsibilityBinding_Binding(), this.getPluginBinding(), this.getPluginBinding_Responsibilities(), "binding", null, 1, 1, ResponsibilityBinding.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResponsibilityBinding_PluginResp(), this.getRespRef(), this.getRespRef_PluginBindings(), "pluginResp", null, 1, 1, ResponsibilityBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResponsibilityBinding_ParentResp(), theUrncorePackage.getResponsibility(), theUrncorePackage.getResponsibility_ParentBindings(), "parentResp", null, 1, 1, ResponsibilityBinding.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(waitKindEEnum, WaitKind.class, "WaitKind");
		addEEnumLiteral(waitKindEEnum, WaitKind.TRANSIENT_LITERAL);
		addEEnumLiteral(waitKindEEnum, WaitKind.PERSISTENT_LITERAL);

		initEEnum(aspectKindEEnum, AspectKind.class, "AspectKind");
		addEEnumLiteral(aspectKindEEnum, AspectKind.NONE_LITERAL);
		addEEnumLiteral(aspectKindEEnum, AspectKind.REGULAR_LITERAL);
		addEEnumLiteral(aspectKindEEnum, AspectKind.ENTRANCE_LITERAL);
		addEEnumLiteral(aspectKindEEnum, AspectKind.EXIT_LITERAL);
		addEEnumLiteral(aspectKindEEnum, AspectKind.CONDITIONAL_LITERAL);

		initEEnum(pointcutKindEEnum, PointcutKind.class, "PointcutKind");
		addEEnumLiteral(pointcutKindEEnum, PointcutKind.NONE_LITERAL);
		addEEnumLiteral(pointcutKindEEnum, PointcutKind.REGULAR_LITERAL);
		addEEnumLiteral(pointcutKindEEnum, PointcutKind.REPLACEMENT_LITERAL);

		initEEnum(failureKindEEnum, FailureKind.class, "FailureKind");
		addEEnumLiteral(failureKindEEnum, FailureKind.FAILURE_LITERAL);
		addEEnumLiteral(failureKindEEnum, FailureKind.ABORT_LITERAL);
		addEEnumLiteral(failureKindEEnum, FailureKind.NONE_LITERAL);
	}

} //MapPackageImpl
