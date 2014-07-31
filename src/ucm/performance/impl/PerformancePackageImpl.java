/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance.impl;

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
import ucm.map.MapPackage;
import ucm.map.impl.MapPackageImpl;
import ucm.performance.ActiveResource;
import ucm.performance.ArrivalProcess;
import ucm.performance.Demand;
import ucm.performance.DeviceKind;
import ucm.performance.ExternalOperation;
import ucm.performance.GeneralResource;
import ucm.performance.PassiveResource;
import ucm.performance.PerformanceFactory;
import ucm.performance.PerformancePackage;
import ucm.performance.ProcessingResource;
import ucm.performance.TimeUnit;
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
    private EEnum arrivalProcessEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum timeUnitEEnum = null;

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
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link PerformancePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
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
		PerformancePackageImpl thePerformancePackage = (PerformancePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PerformancePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new PerformancePackageImpl());

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
		MapPackageImpl theMapPackage = (MapPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) instanceof MapPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) : MapPackage.eINSTANCE);
		ScenarioPackageImpl theScenarioPackage = (ScenarioPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) instanceof ScenarioPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) : ScenarioPackage.eINSTANCE);
		AsdPackageImpl theAsdPackage = (AsdPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AsdPackage.eNS_URI) instanceof AsdPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AsdPackage.eNS_URI) : AsdPackage.eINSTANCE);

		// Create package meta-data objects
		thePerformancePackage.createPackageContents();
		theFmPackage.createPackageContents();
		theGrlPackage.createPackageContents();
		theKpimodelPackage.createPackageContents();
		theUrncorePackage.createPackageContents();
		theUrnPackage.createPackageContents();
		theUcmPackage.createPackageContents();
		theMapPackage.createPackageContents();
		theScenarioPackage.createPackageContents();
		theAsdPackage.createPackageContents();

		// Initialize created meta-data
		thePerformancePackage.initializePackageContents();
		theFmPackage.initializePackageContents();
		theGrlPackage.initializePackageContents();
		theKpimodelPackage.initializePackageContents();
		theUrncorePackage.initializePackageContents();
		theUrnPackage.initializePackageContents();
		theUcmPackage.initializePackageContents();
		theMapPackage.initializePackageContents();
		theScenarioPackage.initializePackageContents();
		theAsdPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thePerformancePackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(PerformancePackage.eNS_URI, thePerformancePackage);
		return thePerformancePackage;
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
    public EAttribute getWorkload_Unit() {
		return (EAttribute)workloadEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getWorkload_StartPoint() {
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
    public EAttribute getActiveResource_Unit() {
		return (EAttribute)activeResourceEClass.getEStructuralFeatures().get(1);
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
    public EEnum getArrivalProcess() {
		return arrivalProcessEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getTimeUnit() {
		return timeUnitEEnum;
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
		workloadEClass = createEClass(WORKLOAD);
		createEAttribute(workloadEClass, WORKLOAD__CLOSED);
		createEAttribute(workloadEClass, WORKLOAD__ARRIVAL_PATTERN);
		createEAttribute(workloadEClass, WORKLOAD__ARRIVAL_PARAM1);
		createEAttribute(workloadEClass, WORKLOAD__ARRIVAL_PARAM2);
		createEAttribute(workloadEClass, WORKLOAD__EXTERNAL_DELAY);
		createEAttribute(workloadEClass, WORKLOAD__VALUE);
		createEAttribute(workloadEClass, WORKLOAD__COEFF_VAR_SEQ);
		createEAttribute(workloadEClass, WORKLOAD__POPULATION);
		createEAttribute(workloadEClass, WORKLOAD__UNIT);
		createEReference(workloadEClass, WORKLOAD__START_POINT);

		generalResourceEClass = createEClass(GENERAL_RESOURCE);
		createEAttribute(generalResourceEClass, GENERAL_RESOURCE__MULTIPLICITY);
		createEAttribute(generalResourceEClass, GENERAL_RESOURCE__SCHED_POLICY);
		createEReference(generalResourceEClass, GENERAL_RESOURCE__UCMSPEC);

		activeResourceEClass = createEClass(ACTIVE_RESOURCE);
		createEAttribute(activeResourceEClass, ACTIVE_RESOURCE__OP_TIME);
		createEAttribute(activeResourceEClass, ACTIVE_RESOURCE__UNIT);

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
		arrivalProcessEEnum = createEEnum(ARRIVAL_PROCESS);
		timeUnitEEnum = createEEnum(TIME_UNIT);
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
		MapPackage theMapPackage = (MapPackage)EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI);
		UcmPackage theUcmPackage = (UcmPackage)EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI);

		// Add supertypes to classes
		workloadEClass.getESuperTypes().add(theUrncorePackage.getUCMmodelElement());
		generalResourceEClass.getESuperTypes().add(theUrncorePackage.getUCMmodelElement());
		activeResourceEClass.getESuperTypes().add(this.getGeneralResource());
		passiveResourceEClass.getESuperTypes().add(this.getGeneralResource());
		externalOperationEClass.getESuperTypes().add(this.getActiveResource());
		processingResourceEClass.getESuperTypes().add(this.getActiveResource());

		// Initialize classes and features; add operations and parameters
		initEClass(workloadEClass, Workload.class, "Workload", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkload_Closed(), ecorePackage.getEBoolean(), "closed", "false", 0, 1, Workload.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkload_ArrivalPattern(), this.getArrivalProcess(), "arrivalPattern", null, 0, 1, Workload.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkload_ArrivalParam1(), ecorePackage.getEString(), "arrivalParam1", null, 0, 1, Workload.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkload_ArrivalParam2(), ecorePackage.getEString(), "arrivalParam2", null, 0, 1, Workload.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkload_ExternalDelay(), ecorePackage.getEString(), "externalDelay", null, 0, 1, Workload.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkload_Value(), ecorePackage.getEString(), "value", null, 0, 1, Workload.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkload_CoeffVarSeq(), ecorePackage.getEString(), "coeffVarSeq", null, 0, 1, Workload.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkload_Population(), ecorePackage.getEString(), "population", null, 0, 1, Workload.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkload_Unit(), this.getTimeUnit(), "unit", "ms", 0, 1, Workload.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkload_StartPoint(), theMapPackage.getStartPoint(), theMapPackage.getStartPoint_Workload(), "startPoint", null, 1, 1, Workload.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(generalResourceEClass, GeneralResource.class, "GeneralResource", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGeneralResource_Multiplicity(), ecorePackage.getEString(), "multiplicity", null, 0, 1, GeneralResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGeneralResource_SchedPolicy(), ecorePackage.getEString(), "schedPolicy", null, 0, 1, GeneralResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGeneralResource_Ucmspec(), theUcmPackage.getUCMspec(), theUcmPackage.getUCMspec_Resources(), "ucmspec", null, 1, 1, GeneralResource.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(activeResourceEClass, ActiveResource.class, "ActiveResource", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActiveResource_OpTime(), ecorePackage.getEString(), "opTime", null, 0, 1, ActiveResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActiveResource_Unit(), this.getTimeUnit(), "unit", "ms", 0, 1, ActiveResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(passiveResourceEClass, PassiveResource.class, "PassiveResource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPassiveResource_Component(), theUrncorePackage.getComponent(), theUrncorePackage.getComponent_Resource(), "component", null, 0, 1, PassiveResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(externalOperationEClass, ExternalOperation.class, "ExternalOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExternalOperation_Demands(), this.getDemand(), this.getDemand_Resource(), "demands", null, 0, -1, ExternalOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(processingResourceEClass, ProcessingResource.class, "ProcessingResource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProcessingResource_Kind(), this.getDeviceKind(), "kind", null, 0, 1, ProcessingResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProcessingResource_Components(), theUrncorePackage.getComponent(), theUrncorePackage.getComponent_Host(), "components", null, 0, -1, ProcessingResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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

		initEEnum(arrivalProcessEEnum, ArrivalProcess.class, "ArrivalProcess");
		addEEnumLiteral(arrivalProcessEEnum, ArrivalProcess.POISSON_PDF_LITERAL);
		addEEnumLiteral(arrivalProcessEEnum, ArrivalProcess.PERIODIC_LITERAL);
		addEEnumLiteral(arrivalProcessEEnum, ArrivalProcess.UNIFORM_LITERAL);
		addEEnumLiteral(arrivalProcessEEnum, ArrivalProcess.PHASE_TYPE_LITERAL);

		initEEnum(timeUnitEEnum, TimeUnit.class, "TimeUnit");
		addEEnumLiteral(timeUnitEEnum, TimeUnit.YEAR_LITERAL);
		addEEnumLiteral(timeUnitEEnum, TimeUnit.DAY_LITERAL);
		addEEnumLiteral(timeUnitEEnum, TimeUnit.H_LITERAL);
		addEEnumLiteral(timeUnitEEnum, TimeUnit.S_LITERAL);
		addEEnumLiteral(timeUnitEEnum, TimeUnit.MS_LITERAL);
		addEEnumLiteral(timeUnitEEnum, TimeUnit.US_LITERAL);
		addEEnumLiteral(timeUnitEEnum, TimeUnit.NS_LITERAL);
	}

} //PerformancePackageImpl
