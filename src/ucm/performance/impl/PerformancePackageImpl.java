/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance.impl;

import grl.GrlPackage;
import grl.impl.GrlPackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import ucm.UcmPackage;
import ucm.impl.UcmPackageImpl;
import ucm.map.MapPackage;
import ucm.map.impl.MapPackageImpl;
import ucm.performance.ActiveResource;
import ucm.performance.ArrivalProcess;
import ucm.performance.Demand;
import ucm.performance.DeviceKind;
import ucm.performance.ExternalOperation;
import ucm.performance.GeneralResource;
import ucm.performance.PassiveResource;
import ucm.performance.PerfAttribute;
import ucm.performance.PerfMeasure;
import ucm.performance.PerfValue;
import ucm.performance.PerfValueKind;
import ucm.performance.PerfValueSource;
import ucm.performance.PerformanceFactory;
import ucm.performance.PerformancePackage;
import ucm.performance.ProcessingResource;
import ucm.performance.ResponseTimeReq;
import ucm.performance.Timestamp;
import ucm.performance.Workload;
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
public class PerformancePackageImpl extends EPackageImpl implements PerformancePackage {
	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass timestampEClass = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass responseTimeReqEClass = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass workloadEClass = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass generalResourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass perfMeasureEClass = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass perfValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass activeResourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass passiveResourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass externalOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass processingResourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass demandEClass = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum deviceKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum perfValueKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum perfValueSourceEEnum = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum arrivalProcessEEnum = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum perfAttributeEEnum = null;

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
	 * @see ucm.performance.PerformancePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
    private PerformancePackageImpl() {
		super(eNS_URI, PerformanceFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
    public static PerformancePackage init() {
		if (isInited) return (PerformancePackage)EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI);

		// Obtain or create and register package
		PerformancePackageImpl thePerformancePackage = (PerformancePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof PerformancePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new PerformancePackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		UrnPackageImpl theUrnPackage = (UrnPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI) instanceof UrnPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI) : UrnPackage.eINSTANCE);
		UrncorePackageImpl theUrncorePackage = (UrncorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI) instanceof UrncorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI) : UrncorePackage.eINSTANCE);
		GrlPackageImpl theGrlPackage = (GrlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GrlPackage.eNS_URI) instanceof GrlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GrlPackage.eNS_URI) : GrlPackage.eINSTANCE);
		UcmPackageImpl theUcmPackage = (UcmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI) instanceof UcmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI) : UcmPackage.eINSTANCE);
		MapPackageImpl theMapPackage = (MapPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) instanceof MapPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) : MapPackage.eINSTANCE);
		ScenarioPackageImpl theScenarioPackage = (ScenarioPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) instanceof ScenarioPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) : ScenarioPackage.eINSTANCE);

		// Create package meta-data objects
		thePerformancePackage.createPackageContents();
		theUrnPackage.createPackageContents();
		theUrncorePackage.createPackageContents();
		theGrlPackage.createPackageContents();
		theUcmPackage.createPackageContents();
		theMapPackage.createPackageContents();
		theScenarioPackage.createPackageContents();

		// Initialize created meta-data
		thePerformancePackage.initializePackageContents();
		theUrnPackage.initializePackageContents();
		theUrncorePackage.initializePackageContents();
		theGrlPackage.initializePackageContents();
		theUcmPackage.initializePackageContents();
		theMapPackage.initializePackageContents();
		theScenarioPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thePerformancePackage.freeze();

		return thePerformancePackage;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getTimestamp() {
		return timestampEClass;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getTimestamp_Orientation() {
		return (EAttribute)timestampEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getTimestamp_Targets() {
		return (EReference)timestampEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getTimestamp_Sources() {
		return (EReference)timestampEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getResponseTimeReq() {
		return responseTimeReqEClass;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getResponseTimeReq_ResponseTime() {
		return (EAttribute)responseTimeReqEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getResponseTimeReq_Percentage() {
		return (EAttribute)responseTimeReqEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getResponseTimeReq_Ucmspec() {
		return (EReference)responseTimeReqEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getResponseTimeReq_Ts1() {
		return (EReference)responseTimeReqEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getResponseTimeReq_Ts2() {
		return (EReference)responseTimeReqEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getWorkload() {
		return workloadEClass;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getWorkload_Closed() {
		return (EAttribute)workloadEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getWorkload_ArrivalPattern() {
		return (EAttribute)workloadEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getWorkload_ArrivalParam1() {
		return (EAttribute)workloadEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getWorkload_ArrivalParam2() {
		return (EAttribute)workloadEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getWorkload_ExternalDelay() {
		return (EAttribute)workloadEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getWorkload_Value() {
		return (EAttribute)workloadEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getWorkload_CoeffVarSeq() {
		return (EAttribute)workloadEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getWorkload_Population() {
		return (EAttribute)workloadEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getWorkload_StartPoint() {
		return (EReference)workloadEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWorkload_ResponseTime() {
		return (EReference)workloadEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getGeneralResource() {
		return generalResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeneralResource_Multiplicity() {
		return (EAttribute)generalResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGeneralResource_SchedPolicy() {
		return (EAttribute)generalResourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getGeneralResource_Ucmspec() {
		return (EReference)generalResourceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGeneralResource_PerfMeasures() {
		return (EReference)generalResourceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getPerfMeasure() {
		return perfMeasureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getPerfMeasure_Measure() {
		return (EAttribute)perfMeasureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getPerfMeasure_Ucmspec() {
		return (EReference)perfMeasureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getPerfMeasure_PerfValues() {
		return (EReference)perfMeasureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getPerfMeasure_Duration() {
		return (EReference)perfMeasureEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPerfMeasure_Resource() {
		return (EReference)perfMeasureEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPerfMeasure_Trigger() {
		return (EReference)perfMeasureEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPerfMeasure_End() {
		return (EReference)perfMeasureEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getPerfValue() {
		return perfValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getPerfValue_Value() {
		return (EAttribute)perfValueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getPerfValue_Kind() {
		return (EAttribute)perfValueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getPerfValue_Source() {
		return (EAttribute)perfValueEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getPerfValue_Percentile() {
		return (EAttribute)perfValueEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getPerfValue_KthMoment() {
		return (EAttribute)perfValueEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getPerfValue_PerfMeasure() {
		return (EReference)perfValueEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getActiveResource() {
		return activeResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getActiveResource_OpTime() {
		return (EAttribute)activeResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getPassiveResource() {
		return passiveResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getPassiveResource_Component() {
		return (EReference)passiveResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getExternalOperation() {
		return externalOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExternalOperation_Demands() {
		return (EReference)externalOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getProcessingResource() {
		return processingResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getProcessingResource_Kind() {
		return (EAttribute)processingResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getProcessingResource_Components() {
		return (EReference)processingResourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getDemand() {
		return demandEClass;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getDemand_Quantity() {
		return (EAttribute)demandEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getDemand_Responsibility() {
		return (EReference)demandEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getDemand_Resource() {
		return (EReference)demandEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getDeviceKind() {
		return deviceKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getPerfValueKind() {
		return perfValueKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getPerfValueSource() {
		return perfValueSourceEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getArrivalProcess() {
		return arrivalProcessEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getPerfAttribute() {
		return perfAttributeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PerformanceFactory getPerformanceFactory() {
		return (PerformanceFactory)getEFactoryInstance();
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
		timestampEClass = createEClass(TIMESTAMP);
		createEAttribute(timestampEClass, TIMESTAMP__ORIENTATION);
		createEReference(timestampEClass, TIMESTAMP__TARGETS);
		createEReference(timestampEClass, TIMESTAMP__SOURCES);

		responseTimeReqEClass = createEClass(RESPONSE_TIME_REQ);
		createEAttribute(responseTimeReqEClass, RESPONSE_TIME_REQ__RESPONSE_TIME);
		createEAttribute(responseTimeReqEClass, RESPONSE_TIME_REQ__PERCENTAGE);
		createEReference(responseTimeReqEClass, RESPONSE_TIME_REQ__UCMSPEC);
		createEReference(responseTimeReqEClass, RESPONSE_TIME_REQ__TS1);
		createEReference(responseTimeReqEClass, RESPONSE_TIME_REQ__TS2);

		workloadEClass = createEClass(WORKLOAD);
		createEAttribute(workloadEClass, WORKLOAD__CLOSED);
		createEAttribute(workloadEClass, WORKLOAD__ARRIVAL_PATTERN);
		createEAttribute(workloadEClass, WORKLOAD__ARRIVAL_PARAM1);
		createEAttribute(workloadEClass, WORKLOAD__ARRIVAL_PARAM2);
		createEAttribute(workloadEClass, WORKLOAD__EXTERNAL_DELAY);
		createEAttribute(workloadEClass, WORKLOAD__VALUE);
		createEAttribute(workloadEClass, WORKLOAD__COEFF_VAR_SEQ);
		createEAttribute(workloadEClass, WORKLOAD__POPULATION);
		createEReference(workloadEClass, WORKLOAD__START_POINT);
		createEReference(workloadEClass, WORKLOAD__RESPONSE_TIME);

		generalResourceEClass = createEClass(GENERAL_RESOURCE);
		createEAttribute(generalResourceEClass, GENERAL_RESOURCE__MULTIPLICITY);
		createEAttribute(generalResourceEClass, GENERAL_RESOURCE__SCHED_POLICY);
		createEReference(generalResourceEClass, GENERAL_RESOURCE__UCMSPEC);
		createEReference(generalResourceEClass, GENERAL_RESOURCE__PERF_MEASURES);

		perfMeasureEClass = createEClass(PERF_MEASURE);
		createEAttribute(perfMeasureEClass, PERF_MEASURE__MEASURE);
		createEReference(perfMeasureEClass, PERF_MEASURE__UCMSPEC);
		createEReference(perfMeasureEClass, PERF_MEASURE__PERF_VALUES);
		createEReference(perfMeasureEClass, PERF_MEASURE__DURATION);
		createEReference(perfMeasureEClass, PERF_MEASURE__RESOURCE);
		createEReference(perfMeasureEClass, PERF_MEASURE__TRIGGER);
		createEReference(perfMeasureEClass, PERF_MEASURE__END);

		perfValueEClass = createEClass(PERF_VALUE);
		createEAttribute(perfValueEClass, PERF_VALUE__VALUE);
		createEAttribute(perfValueEClass, PERF_VALUE__KIND);
		createEAttribute(perfValueEClass, PERF_VALUE__SOURCE);
		createEAttribute(perfValueEClass, PERF_VALUE__PERCENTILE);
		createEAttribute(perfValueEClass, PERF_VALUE__KTH_MOMENT);
		createEReference(perfValueEClass, PERF_VALUE__PERF_MEASURE);

		activeResourceEClass = createEClass(ACTIVE_RESOURCE);
		createEAttribute(activeResourceEClass, ACTIVE_RESOURCE__OP_TIME);

		passiveResourceEClass = createEClass(PASSIVE_RESOURCE);
		createEReference(passiveResourceEClass, PASSIVE_RESOURCE__COMPONENT);

		externalOperationEClass = createEClass(EXTERNAL_OPERATION);
		createEReference(externalOperationEClass, EXTERNAL_OPERATION__DEMANDS);

		processingResourceEClass = createEClass(PROCESSING_RESOURCE);
		createEAttribute(processingResourceEClass, PROCESSING_RESOURCE__KIND);
		createEReference(processingResourceEClass, PROCESSING_RESOURCE__COMPONENTS);

		demandEClass = createEClass(DEMAND);
		createEAttribute(demandEClass, DEMAND__QUANTITY);
		createEReference(demandEClass, DEMAND__RESPONSIBILITY);
		createEReference(demandEClass, DEMAND__RESOURCE);

		// Create enums
		deviceKindEEnum = createEEnum(DEVICE_KIND);
		perfValueKindEEnum = createEEnum(PERF_VALUE_KIND);
		perfValueSourceEEnum = createEEnum(PERF_VALUE_SOURCE);
		arrivalProcessEEnum = createEEnum(ARRIVAL_PROCESS);
		perfAttributeEEnum = createEEnum(PERF_ATTRIBUTE);
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
		MapPackage theMapPackage = (MapPackage)EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI);
		UrncorePackage theUrncorePackage = (UrncorePackage)EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI);
		UcmPackage theUcmPackage = (UcmPackage)EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI);

		// Add supertypes to classes
		timestampEClass.getESuperTypes().add(theMapPackage.getPathNode());
		responseTimeReqEClass.getESuperTypes().add(theUrncorePackage.getUCMmodelElement());
		workloadEClass.getESuperTypes().add(theUrncorePackage.getUCMmodelElement());
		generalResourceEClass.getESuperTypes().add(theUrncorePackage.getUCMmodelElement());
		perfMeasureEClass.getESuperTypes().add(theUrncorePackage.getUCMmodelElement());
		activeResourceEClass.getESuperTypes().add(this.getGeneralResource());
		passiveResourceEClass.getESuperTypes().add(this.getGeneralResource());
		externalOperationEClass.getESuperTypes().add(this.getActiveResource());
		processingResourceEClass.getESuperTypes().add(this.getActiveResource());

		// Initialize classes and features; add operations and parameters
		initEClass(timestampEClass, Timestamp.class, "Timestamp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTimestamp_Orientation(), ecorePackage.getEString(), "orientation", null, 0, 1, Timestamp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTimestamp_Targets(), this.getResponseTimeReq(), this.getResponseTimeReq_Ts1(), "targets", null, 0, -1, Timestamp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTimestamp_Sources(), this.getResponseTimeReq(), this.getResponseTimeReq_Ts2(), "sources", null, 0, -1, Timestamp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(responseTimeReqEClass, ResponseTimeReq.class, "ResponseTimeReq", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getResponseTimeReq_ResponseTime(), ecorePackage.getEString(), "responseTime", null, 0, 1, ResponseTimeReq.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResponseTimeReq_Percentage(), ecorePackage.getEString(), "percentage", null, 0, 1, ResponseTimeReq.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResponseTimeReq_Ucmspec(), theUcmPackage.getUCMspec(), theUcmPackage.getUCMspec_Resptimereq(), "ucmspec", null, 1, 1, ResponseTimeReq.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResponseTimeReq_Ts1(), this.getTimestamp(), this.getTimestamp_Targets(), "ts1", null, 1, 1, ResponseTimeReq.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResponseTimeReq_Ts2(), this.getTimestamp(), this.getTimestamp_Sources(), "ts2", null, 1, 1, ResponseTimeReq.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workloadEClass, Workload.class, "Workload", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkload_Closed(), ecorePackage.getEBoolean(), "closed", "false", 0, 1, Workload.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkload_ArrivalPattern(), this.getArrivalProcess(), "arrivalPattern", null, 0, 1, Workload.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkload_ArrivalParam1(), ecorePackage.getEString(), "arrivalParam1", null, 0, 1, Workload.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkload_ArrivalParam2(), ecorePackage.getEString(), "arrivalParam2", null, 0, 1, Workload.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkload_ExternalDelay(), ecorePackage.getEString(), "externalDelay", null, 0, 1, Workload.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkload_Value(), ecorePackage.getEString(), "value", null, 0, 1, Workload.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkload_CoeffVarSeq(), ecorePackage.getEString(), "coeffVarSeq", null, 0, 1, Workload.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkload_Population(), ecorePackage.getEString(), "population", null, 0, 1, Workload.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkload_StartPoint(), theMapPackage.getStartPoint(), theMapPackage.getStartPoint_Workload(), "startPoint", null, 1, 1, Workload.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkload_ResponseTime(), this.getPerfMeasure(), this.getPerfMeasure_Duration(), "responseTime", null, 0, -1, Workload.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(generalResourceEClass, GeneralResource.class, "GeneralResource", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGeneralResource_Multiplicity(), ecorePackage.getEString(), "multiplicity", null, 0, 1, GeneralResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeneralResource_SchedPolicy(), ecorePackage.getEString(), "schedPolicy", null, 0, 1, GeneralResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGeneralResource_Ucmspec(), theUcmPackage.getUCMspec(), theUcmPackage.getUCMspec_Resources(), "ucmspec", null, 1, 1, GeneralResource.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGeneralResource_PerfMeasures(), this.getPerfMeasure(), this.getPerfMeasure_Resource(), "perfMeasures", null, 0, -1, GeneralResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(perfMeasureEClass, PerfMeasure.class, "PerfMeasure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPerfMeasure_Measure(), this.getPerfAttribute(), "measure", "Delay", 0, 1, PerfMeasure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPerfMeasure_Ucmspec(), theUcmPackage.getUCMspec(), theUcmPackage.getUCMspec_PerfMeasures(), "ucmspec", null, 1, 1, PerfMeasure.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPerfMeasure_PerfValues(), this.getPerfValue(), this.getPerfValue_PerfMeasure(), "perfValues", null, 0, -1, PerfMeasure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPerfMeasure_Duration(), this.getWorkload(), this.getWorkload_ResponseTime(), "duration", null, 0, 1, PerfMeasure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPerfMeasure_Resource(), this.getGeneralResource(), this.getGeneralResource_PerfMeasures(), "resource", null, 0, 1, PerfMeasure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPerfMeasure_Trigger(), theMapPackage.getPathNode(), theMapPackage.getPathNode_PerfMTrig(), "trigger", null, 0, 1, PerfMeasure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPerfMeasure_End(), theMapPackage.getPathNode(), theMapPackage.getPathNode_PerfMEnd(), "end", null, 0, 1, PerfMeasure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(perfValueEClass, PerfValue.class, "PerfValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPerfValue_Value(), ecorePackage.getEString(), "value", null, 0, 1, PerfValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPerfValue_Kind(), this.getPerfValueKind(), "kind", "Unknown", 0, 1, PerfValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPerfValue_Source(), this.getPerfValueSource(), "source", "Unknown", 0, 1, PerfValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPerfValue_Percentile(), ecorePackage.getEString(), "percentile", null, 0, 1, PerfValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPerfValue_KthMoment(), ecorePackage.getEString(), "kthMoment", null, 0, 1, PerfValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPerfValue_PerfMeasure(), this.getPerfMeasure(), this.getPerfMeasure_PerfValues(), "perfMeasure", null, 1, 1, PerfValue.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(activeResourceEClass, ActiveResource.class, "ActiveResource", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActiveResource_OpTime(), ecorePackage.getEString(), "opTime", null, 0, 1, ActiveResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(passiveResourceEClass, PassiveResource.class, "PassiveResource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPassiveResource_Component(), theUrncorePackage.getComponentElement(), theUrncorePackage.getComponentElement_Resource(), "component", null, 0, 1, PassiveResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(externalOperationEClass, ExternalOperation.class, "ExternalOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExternalOperation_Demands(), this.getDemand(), this.getDemand_Resource(), "demands", null, 0, -1, ExternalOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(processingResourceEClass, ProcessingResource.class, "ProcessingResource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProcessingResource_Kind(), this.getDeviceKind(), "kind", null, 0, 1, ProcessingResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcessingResource_Components(), theUrncorePackage.getComponentRegular(), theUrncorePackage.getComponentRegular_Host(), "components", null, 0, -1, ProcessingResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(demandEClass, Demand.class, "Demand", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDemand_Quantity(), ecorePackage.getEString(), "quantity", null, 0, 1, Demand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDemand_Responsibility(), theUrncorePackage.getResponsibility(), theUrncorePackage.getResponsibility_Demands(), "responsibility", null, 1, 1, Demand.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDemand_Resource(), this.getExternalOperation(), this.getExternalOperation_Demands(), "resource", null, 1, 1, Demand.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(deviceKindEEnum, DeviceKind.class, "DeviceKind");
		addEEnumLiteral(deviceKindEEnum, DeviceKind.PROCESSOR_LITERAL);
		addEEnumLiteral(deviceKindEEnum, DeviceKind.DISK_LITERAL);
		addEEnumLiteral(deviceKindEEnum, DeviceKind.DSP_LITERAL);
		addEEnumLiteral(deviceKindEEnum, DeviceKind.OTHER_LITERAL);

		initEEnum(perfValueKindEEnum, PerfValueKind.class, "PerfValueKind");
		addEEnumLiteral(perfValueKindEEnum, PerfValueKind.MEAN_LITERAL);
		addEEnumLiteral(perfValueKindEEnum, PerfValueKind.VARIANCE_LITERAL);
		addEEnumLiteral(perfValueKindEEnum, PerfValueKind.PERCENTILE_LITERAL);
		addEEnumLiteral(perfValueKindEEnum, PerfValueKind.MOMENT_LITERAL);
		addEEnumLiteral(perfValueKindEEnum, PerfValueKind.MIN_LITERAL);
		addEEnumLiteral(perfValueKindEEnum, PerfValueKind.MAX_LITERAL);
		addEEnumLiteral(perfValueKindEEnum, PerfValueKind.DISTRIBUTION_LITERAL);
		addEEnumLiteral(perfValueKindEEnum, PerfValueKind.UNKNOWN_LITERAL);

		initEEnum(perfValueSourceEEnum, PerfValueSource.class, "PerfValueSource");
		addEEnumLiteral(perfValueSourceEEnum, PerfValueSource.REQUIRED_LITERAL);
		addEEnumLiteral(perfValueSourceEEnum, PerfValueSource.ASSUMED_LITERAL);
		addEEnumLiteral(perfValueSourceEEnum, PerfValueSource.PREDICTED_LITERAL);
		addEEnumLiteral(perfValueSourceEEnum, PerfValueSource.MEASURED_LITERAL);
		addEEnumLiteral(perfValueSourceEEnum, PerfValueSource.UNKNOWN_LITERAL);

		initEEnum(arrivalProcessEEnum, ArrivalProcess.class, "ArrivalProcess");
		addEEnumLiteral(arrivalProcessEEnum, ArrivalProcess.POISSON_PDF_LITERAL);
		addEEnumLiteral(arrivalProcessEEnum, ArrivalProcess.PERIODIC_LITERAL);
		addEEnumLiteral(arrivalProcessEEnum, ArrivalProcess.UNIFORM_LITERAL);
		addEEnumLiteral(arrivalProcessEEnum, ArrivalProcess.PHASE_TYPE_LITERAL);

		initEEnum(perfAttributeEEnum, PerfAttribute.class, "PerfAttribute");
		addEEnumLiteral(perfAttributeEEnum, PerfAttribute.DELAY_LITERAL);
		addEEnumLiteral(perfAttributeEEnum, PerfAttribute.THROUGHPUT_LITERAL);
		addEEnumLiteral(perfAttributeEEnum, PerfAttribute.UTILIZATION_LITERAL);
		addEEnumLiteral(perfAttributeEEnum, PerfAttribute.INTERVAL_LITERAL);
		addEEnumLiteral(perfAttributeEEnum, PerfAttribute.WAIT_LITERAL);
	}

} //PerformancePackageImpl
