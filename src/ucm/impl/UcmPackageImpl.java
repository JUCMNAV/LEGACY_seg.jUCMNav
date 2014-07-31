/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.impl;

import asd.AsdPackage;
import asd.impl.AsdPackageImpl;
import ca.mcgill.sel.core.CorePackage;
import fm.FmPackage;
import fm.impl.FmPackageImpl;
import grl.GrlPackage;
import grl.impl.GrlPackageImpl;
import grl.kpimodel.KpimodelPackage;
import grl.kpimodel.impl.KpimodelPackageImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import ucm.UCMspec;
import ucm.UcmFactory;
import ucm.UcmPackage;
import ucm.map.MapPackage;
import ucm.map.impl.MapPackageImpl;
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
public class UcmPackageImpl extends EPackageImpl implements UcmPackage {
    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass ucMspecEClass = null;

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
	 * @see ucm.UcmPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
    private UcmPackageImpl() {
		super(eNS_URI, UcmFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link UcmPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
    public static UcmPackage init() {
		if (isInited) return (UcmPackage)EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI);

		// Obtain or create and register package
		UcmPackageImpl theUcmPackage = (UcmPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof UcmPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new UcmPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		FmPackageImpl theFmPackage = (FmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(FmPackage.eNS_URI) instanceof FmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(FmPackage.eNS_URI) : FmPackage.eINSTANCE);
		GrlPackageImpl theGrlPackage = (GrlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GrlPackage.eNS_URI) instanceof GrlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GrlPackage.eNS_URI) : GrlPackage.eINSTANCE);
		KpimodelPackageImpl theKpimodelPackage = (KpimodelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(KpimodelPackage.eNS_URI) instanceof KpimodelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(KpimodelPackage.eNS_URI) : KpimodelPackage.eINSTANCE);
		UrncorePackageImpl theUrncorePackage = (UrncorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI) instanceof UrncorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI) : UrncorePackage.eINSTANCE);
		UrnPackageImpl theUrnPackage = (UrnPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI) instanceof UrnPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI) : UrnPackage.eINSTANCE);
		PerformancePackageImpl thePerformancePackage = (PerformancePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI) instanceof PerformancePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI) : PerformancePackage.eINSTANCE);
		MapPackageImpl theMapPackage = (MapPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) instanceof MapPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) : MapPackage.eINSTANCE);
		ScenarioPackageImpl theScenarioPackage = (ScenarioPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) instanceof ScenarioPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) : ScenarioPackage.eINSTANCE);
		AsdPackageImpl theAsdPackage = (AsdPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AsdPackage.eNS_URI) instanceof AsdPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AsdPackage.eNS_URI) : AsdPackage.eINSTANCE);

		// Create package meta-data objects
		theUcmPackage.createPackageContents();
		theFmPackage.createPackageContents();
		theGrlPackage.createPackageContents();
		theKpimodelPackage.createPackageContents();
		theUrncorePackage.createPackageContents();
		theUrnPackage.createPackageContents();
		thePerformancePackage.createPackageContents();
		theMapPackage.createPackageContents();
		theScenarioPackage.createPackageContents();
		theAsdPackage.createPackageContents();

		// Initialize created meta-data
		theUcmPackage.initializePackageContents();
		theFmPackage.initializePackageContents();
		theGrlPackage.initializePackageContents();
		theKpimodelPackage.initializePackageContents();
		theUrncorePackage.initializePackageContents();
		theUrnPackage.initializePackageContents();
		thePerformancePackage.initializePackageContents();
		theMapPackage.initializePackageContents();
		theScenarioPackage.initializePackageContents();
		theAsdPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theUcmPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(UcmPackage.eNS_URI, theUcmPackage);
		return theUcmPackage;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getUCMspec() {
		return ucMspecEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getUCMspec_Urnspec() {
		return (EReference)ucMspecEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getUCMspec_Resources() {
		return (EReference)ucMspecEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getUCMspec_ScenarioGroups() {
		return (EReference)ucMspecEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getUCMspec_Variables() {
		return (EReference)ucMspecEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUCMspec_EnumerationTypes() {
		return (EReference)ucMspecEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public UcmFactory getUcmFactory() {
		return (UcmFactory)getEFactoryInstance();
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
		ucMspecEClass = createEClass(UC_MSPEC);
		createEReference(ucMspecEClass, UC_MSPEC__URNSPEC);
		createEReference(ucMspecEClass, UC_MSPEC__RESOURCES);
		createEReference(ucMspecEClass, UC_MSPEC__SCENARIO_GROUPS);
		createEReference(ucMspecEClass, UC_MSPEC__VARIABLES);
		createEReference(ucMspecEClass, UC_MSPEC__ENUMERATION_TYPES);
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
		PerformancePackage thePerformancePackage = (PerformancePackage)EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI);
		MapPackage theMapPackage = (MapPackage)EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI);
		ScenarioPackage theScenarioPackage = (ScenarioPackage)EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI);
		UrnPackage theUrnPackage = (UrnPackage)EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(thePerformancePackage);
		getESubpackages().add(theMapPackage);
		getESubpackages().add(theScenarioPackage);

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(ucMspecEClass, UCMspec.class, "UCMspec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUCMspec_Urnspec(), theUrnPackage.getURNspec(), theUrnPackage.getURNspec_Ucmspec(), "urnspec", null, 1, 1, UCMspec.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUCMspec_Resources(), thePerformancePackage.getGeneralResource(), thePerformancePackage.getGeneralResource_Ucmspec(), "resources", null, 0, -1, UCMspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUCMspec_ScenarioGroups(), theScenarioPackage.getScenarioGroup(), theScenarioPackage.getScenarioGroup_Ucmspec(), "scenarioGroups", null, 0, -1, UCMspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUCMspec_Variables(), theScenarioPackage.getVariable(), theScenarioPackage.getVariable_Ucmspec(), "variables", null, 0, -1, UCMspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUCMspec_EnumerationTypes(), theScenarioPackage.getEnumerationType(), theScenarioPackage.getEnumerationType_Ucmspec(), "enumerationTypes", null, 0, -1, UCMspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //UcmPackageImpl
