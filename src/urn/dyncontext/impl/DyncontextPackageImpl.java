/**
 */
package urn.dyncontext.impl;

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
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import ucm.UcmPackage;

import ucm.impl.UcmPackageImpl;

import ucm.map.MapPackage;

import ucm.map.impl.MapPackageImpl;

import ucm.performance.PerformancePackage;

import ucm.performance.impl.PerformancePackageImpl;

import ucm.scenario.ScenarioPackage;

import ucm.scenario.impl.ScenarioPackageImpl;

import urn.UrnPackage;

import urn.dyncontext.Change;
import urn.dyncontext.ConstantChange;
import urn.dyncontext.DeactivationChange;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.DynamicContextGroup;
import urn.dyncontext.DyncontextFactory;
import urn.dyncontext.DyncontextPackage;
import urn.dyncontext.EnumChange;
import urn.dyncontext.FormulaChange;
import urn.dyncontext.LinearChange;
import urn.dyncontext.NumericChange;
import urn.dyncontext.PropertyChange;
import urn.dyncontext.QuadraticChange;
import urn.dyncontext.Timepoint;
import urn.dyncontext.TimepointGroup;

import urn.impl.UrnPackageImpl;

import urncore.UrncorePackage;

import urncore.impl.UrncorePackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DyncontextPackageImpl extends EPackageImpl implements DyncontextPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass quadraticChangeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timepointGroupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timepointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyChangeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass numericChangeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formulaChangeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass linearChangeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enumChangeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dynamicContextEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dynamicContextGroupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass deactivationChangeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constantChangeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass changeEClass = null;

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
	 * @see urn.dyncontext.DyncontextPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DyncontextPackageImpl() {
		super(eNS_URI, DyncontextFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link DyncontextPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DyncontextPackage init() {
		if (isInited) return (DyncontextPackage)EPackage.Registry.INSTANCE.getEPackage(DyncontextPackage.eNS_URI);

		// Obtain or create and register package
		DyncontextPackageImpl theDyncontextPackage = (DyncontextPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof DyncontextPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new DyncontextPackageImpl());

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
		MapPackageImpl theMapPackage = (MapPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) instanceof MapPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) : MapPackage.eINSTANCE);
		ScenarioPackageImpl theScenarioPackage = (ScenarioPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) instanceof ScenarioPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) : ScenarioPackage.eINSTANCE);
		AsdPackageImpl theAsdPackage = (AsdPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AsdPackage.eNS_URI) instanceof AsdPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AsdPackage.eNS_URI) : AsdPackage.eINSTANCE);

		// Create package meta-data objects
		theDyncontextPackage.createPackageContents();
		theFmPackage.createPackageContents();
		theGrlPackage.createPackageContents();
		theKpimodelPackage.createPackageContents();
		theUrncorePackage.createPackageContents();
		theUrnPackage.createPackageContents();
		theUcmPackage.createPackageContents();
		thePerformancePackage.createPackageContents();
		theMapPackage.createPackageContents();
		theScenarioPackage.createPackageContents();
		theAsdPackage.createPackageContents();

		// Initialize created meta-data
		theDyncontextPackage.initializePackageContents();
		theFmPackage.initializePackageContents();
		theGrlPackage.initializePackageContents();
		theKpimodelPackage.initializePackageContents();
		theUrncorePackage.initializePackageContents();
		theUrnPackage.initializePackageContents();
		theUcmPackage.initializePackageContents();
		thePerformancePackage.initializePackageContents();
		theMapPackage.initializePackageContents();
		theScenarioPackage.initializePackageContents();
		theAsdPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDyncontextPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DyncontextPackage.eNS_URI, theDyncontextPackage);
		return theDyncontextPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQuadraticChange() {
		return quadraticChangeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQuadraticChange_QuadraticCoefficient() {
		return (EAttribute)quadraticChangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQuadraticChange_LinearCoefficient() {
		return (EAttribute)quadraticChangeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQuadraticChange_Constant() {
		return (EAttribute)quadraticChangeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimepointGroup() {
		return timepointGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimepointGroup_Timepoints() {
		return (EReference)timepointGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimepointGroup_Urnspec() {
		return (EReference)timepointGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimepoint() {
		return timepointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimepoint_Timepoint() {
		return (EAttribute)timepointEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimepoint_Group() {
		return (EReference)timepointEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPropertyChange() {
		return propertyChangeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPropertyChange_AffectedProperty() {
		return (EAttribute)propertyChangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNumericChange() {
		return numericChangeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNumericChange_SufficientOnceAchieved() {
		return (EAttribute)numericChangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFormulaChange() {
		return formulaChangeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFormulaChange_Formula() {
		return (EAttribute)formulaChangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLinearChange() {
		return linearChangeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLinearChange_NewValue() {
		return (EAttribute)linearChangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEnumChange() {
		return enumChangeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEnumChange_NewValue() {
		return (EAttribute)enumChangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDynamicContext() {
		return dynamicContextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicContext_ParentContexts() {
		return (EReference)dynamicContextEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicContext_IncludedContexts() {
		return (EReference)dynamicContextEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicContext_Strategy() {
		return (EReference)dynamicContextEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicContext_ContributionContext() {
		return (EReference)dynamicContextEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicContext_Scenario() {
		return (EReference)dynamicContextEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicContext_Urnspec() {
		return (EReference)dynamicContextEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicContext_Groups() {
		return (EReference)dynamicContextEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicContext_Changes() {
		return (EReference)dynamicContextEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDynamicContextGroup() {
		return dynamicContextGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicContextGroup_Urnspec() {
		return (EReference)dynamicContextGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDynamicContextGroup_Contexts() {
		return (EReference)dynamicContextGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDeactivationChange() {
		return deactivationChangeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConstantChange() {
		return constantChangeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConstantChange_NewValue() {
		return (EAttribute)constantChangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getChange() {
		return changeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getChange_Start() {
		return (EAttribute)changeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getChange_End() {
		return (EAttribute)changeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getChange_Context() {
		return (EReference)changeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getChange_Element() {
		return (EReference)changeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DyncontextFactory getDyncontextFactory() {
		return (DyncontextFactory)getEFactoryInstance();
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
		quadraticChangeEClass = createEClass(QUADRATIC_CHANGE);
		createEAttribute(quadraticChangeEClass, QUADRATIC_CHANGE__QUADRATIC_COEFFICIENT);
		createEAttribute(quadraticChangeEClass, QUADRATIC_CHANGE__LINEAR_COEFFICIENT);
		createEAttribute(quadraticChangeEClass, QUADRATIC_CHANGE__CONSTANT);

		timepointGroupEClass = createEClass(TIMEPOINT_GROUP);
		createEReference(timepointGroupEClass, TIMEPOINT_GROUP__TIMEPOINTS);
		createEReference(timepointGroupEClass, TIMEPOINT_GROUP__URNSPEC);

		timepointEClass = createEClass(TIMEPOINT);
		createEAttribute(timepointEClass, TIMEPOINT__TIMEPOINT);
		createEReference(timepointEClass, TIMEPOINT__GROUP);

		propertyChangeEClass = createEClass(PROPERTY_CHANGE);
		createEAttribute(propertyChangeEClass, PROPERTY_CHANGE__AFFECTED_PROPERTY);

		numericChangeEClass = createEClass(NUMERIC_CHANGE);
		createEAttribute(numericChangeEClass, NUMERIC_CHANGE__SUFFICIENT_ONCE_ACHIEVED);

		formulaChangeEClass = createEClass(FORMULA_CHANGE);
		createEAttribute(formulaChangeEClass, FORMULA_CHANGE__FORMULA);

		linearChangeEClass = createEClass(LINEAR_CHANGE);
		createEAttribute(linearChangeEClass, LINEAR_CHANGE__NEW_VALUE);

		enumChangeEClass = createEClass(ENUM_CHANGE);
		createEAttribute(enumChangeEClass, ENUM_CHANGE__NEW_VALUE);

		dynamicContextEClass = createEClass(DYNAMIC_CONTEXT);
		createEReference(dynamicContextEClass, DYNAMIC_CONTEXT__PARENT_CONTEXTS);
		createEReference(dynamicContextEClass, DYNAMIC_CONTEXT__INCLUDED_CONTEXTS);
		createEReference(dynamicContextEClass, DYNAMIC_CONTEXT__STRATEGY);
		createEReference(dynamicContextEClass, DYNAMIC_CONTEXT__CONTRIBUTION_CONTEXT);
		createEReference(dynamicContextEClass, DYNAMIC_CONTEXT__SCENARIO);
		createEReference(dynamicContextEClass, DYNAMIC_CONTEXT__URNSPEC);
		createEReference(dynamicContextEClass, DYNAMIC_CONTEXT__GROUPS);
		createEReference(dynamicContextEClass, DYNAMIC_CONTEXT__CHANGES);

		dynamicContextGroupEClass = createEClass(DYNAMIC_CONTEXT_GROUP);
		createEReference(dynamicContextGroupEClass, DYNAMIC_CONTEXT_GROUP__URNSPEC);
		createEReference(dynamicContextGroupEClass, DYNAMIC_CONTEXT_GROUP__CONTEXTS);

		deactivationChangeEClass = createEClass(DEACTIVATION_CHANGE);

		constantChangeEClass = createEClass(CONSTANT_CHANGE);
		createEAttribute(constantChangeEClass, CONSTANT_CHANGE__NEW_VALUE);

		changeEClass = createEClass(CHANGE);
		createEAttribute(changeEClass, CHANGE__START);
		createEAttribute(changeEClass, CHANGE__END);
		createEReference(changeEClass, CHANGE__CONTEXT);
		createEReference(changeEClass, CHANGE__ELEMENT);
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
		UrnPackage theUrnPackage = (UrnPackage)EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI);
		UrncorePackage theUrncorePackage = (UrncorePackage)EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI);
		GrlPackage theGrlPackage = (GrlPackage)EPackage.Registry.INSTANCE.getEPackage(GrlPackage.eNS_URI);
		ScenarioPackage theScenarioPackage = (ScenarioPackage)EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI);

		// Add supertypes to classes
		quadraticChangeEClass.getESuperTypes().add(this.getNumericChange());
		propertyChangeEClass.getESuperTypes().add(this.getChange());
		numericChangeEClass.getESuperTypes().add(this.getPropertyChange());
		formulaChangeEClass.getESuperTypes().add(this.getNumericChange());
		linearChangeEClass.getESuperTypes().add(this.getNumericChange());
		enumChangeEClass.getESuperTypes().add(this.getPropertyChange());
		dynamicContextEClass.getESuperTypes().add(theUrncorePackage.getURNmodelElement());
		dynamicContextGroupEClass.getESuperTypes().add(theUrncorePackage.getURNmodelElement());
		deactivationChangeEClass.getESuperTypes().add(this.getChange());
		constantChangeEClass.getESuperTypes().add(this.getNumericChange());

		// Initialize classes and features; add operations and parameters
		initEClass(quadraticChangeEClass, QuadraticChange.class, "QuadraticChange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getQuadraticChange_QuadraticCoefficient(), ecorePackage.getEFloat(), "quadraticCoefficient", "0.0", 0, 1, QuadraticChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getQuadraticChange_LinearCoefficient(), ecorePackage.getEFloat(), "linearCoefficient", "0.0", 0, 1, QuadraticChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getQuadraticChange_Constant(), ecorePackage.getEFloat(), "constant", "0.0", 0, 1, QuadraticChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(timepointGroupEClass, TimepointGroup.class, "TimepointGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTimepointGroup_Timepoints(), this.getTimepoint(), this.getTimepoint_Group(), "timepoints", null, 0, -1, TimepointGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTimepointGroup_Urnspec(), theUrnPackage.getURNspec(), theUrnPackage.getURNspec_TimepointGroups(), "urnspec", null, 1, 1, TimepointGroup.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(timepointEClass, Timepoint.class, "Timepoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTimepoint_Timepoint(), ecorePackage.getEDate(), "timepoint", null, 0, 1, Timepoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTimepoint_Group(), this.getTimepointGroup(), this.getTimepointGroup_Timepoints(), "group", null, 1, 1, Timepoint.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyChangeEClass, PropertyChange.class, "PropertyChange", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPropertyChange_AffectedProperty(), ecorePackage.getEString(), "affectedProperty", null, 0, 1, PropertyChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(numericChangeEClass, NumericChange.class, "NumericChange", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNumericChange_SufficientOnceAchieved(), ecorePackage.getEBoolean(), "sufficientOnceAchieved", "false", 0, 1, NumericChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(formulaChangeEClass, FormulaChange.class, "FormulaChange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFormulaChange_Formula(), ecorePackage.getEString(), "formula", null, 0, 1, FormulaChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(linearChangeEClass, LinearChange.class, "LinearChange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLinearChange_NewValue(), ecorePackage.getEInt(), "newValue", "0", 0, 1, LinearChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(enumChangeEClass, EnumChange.class, "EnumChange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEnumChange_NewValue(), ecorePackage.getEString(), "newValue", null, 0, 1, EnumChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dynamicContextEClass, DynamicContext.class, "DynamicContext", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDynamicContext_ParentContexts(), this.getDynamicContext(), this.getDynamicContext_IncludedContexts(), "parentContexts", null, 0, -1, DynamicContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicContext_IncludedContexts(), this.getDynamicContext(), this.getDynamicContext_ParentContexts(), "includedContexts", null, 0, -1, DynamicContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicContext_Strategy(), theGrlPackage.getEvaluationStrategy(), null, "strategy", null, 0, 1, DynamicContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicContext_ContributionContext(), theGrlPackage.getContributionContext(), null, "contributionContext", null, 0, 1, DynamicContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicContext_Scenario(), theScenarioPackage.getScenarioDef(), null, "scenario", null, 0, 1, DynamicContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicContext_Urnspec(), theUrnPackage.getURNspec(), theUrnPackage.getURNspec_DynamicContexts(), "urnspec", null, 1, 1, DynamicContext.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicContext_Groups(), this.getDynamicContextGroup(), this.getDynamicContextGroup_Contexts(), "groups", null, 1, -1, DynamicContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicContext_Changes(), this.getChange(), this.getChange_Context(), "changes", null, 0, -1, DynamicContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dynamicContextGroupEClass, DynamicContextGroup.class, "DynamicContextGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDynamicContextGroup_Urnspec(), theUrnPackage.getURNspec(), theUrnPackage.getURNspec_DynamicContextGroups(), "urnspec", null, 1, 1, DynamicContextGroup.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDynamicContextGroup_Contexts(), this.getDynamicContext(), this.getDynamicContext_Groups(), "contexts", null, 0, -1, DynamicContextGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(deactivationChangeEClass, DeactivationChange.class, "DeactivationChange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(constantChangeEClass, ConstantChange.class, "ConstantChange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConstantChange_NewValue(), ecorePackage.getEInt(), "newValue", "0", 0, 1, ConstantChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(changeEClass, Change.class, "Change", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getChange_Start(), ecorePackage.getEDate(), "start", null, 0, 1, Change.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getChange_End(), ecorePackage.getEDate(), "end", null, 0, 1, Change.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getChange_Context(), this.getDynamicContext(), this.getDynamicContext_Changes(), "context", null, 1, 1, Change.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getChange_Element(), theUrncorePackage.getURNmodelElement(), null, "element", null, 1, 1, Change.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
	}

} //DyncontextPackageImpl
