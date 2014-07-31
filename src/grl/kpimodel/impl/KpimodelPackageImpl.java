/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.kpimodel.impl;

import asd.AsdPackage;
import asd.impl.AsdPackageImpl;
import ca.mcgill.sel.core.CorePackage;
import fm.FmPackage;
import fm.impl.FmPackageImpl;
import grl.GrlPackage;
import grl.impl.GrlPackageImpl;
import grl.kpimodel.Indicator;
import grl.kpimodel.IndicatorGroup;
import grl.kpimodel.KPIConversion;
import grl.kpimodel.KPIEvalValueSet;
import grl.kpimodel.KPIInformationConfig;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;
import grl.kpimodel.KPIModelLink;
import grl.kpimodel.KPIModelLinkRef;
import grl.kpimodel.KPINewEvalValue;
import grl.kpimodel.KpimodelFactory;
import grl.kpimodel.KpimodelPackage;
import grl.kpimodel.QualitativeMapping;
import grl.kpimodel.QualitativeMappings;
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
import urn.impl.UrnPackageImpl;
import urncore.UrncorePackage;
import urncore.impl.UrncorePackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class KpimodelPackageImpl extends EPackageImpl implements KpimodelPackage {
    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass indicatorGroupEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass indicatorEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass kpiInformationElementEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass kpiInformationElementRefEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass kpiModelLinkEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass kpiModelLinkRefEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass kpiEvalValueSetEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass kpiInformationConfigEClass = null;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass kpiNewEvalValueEClass = null;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass kpiConversionEClass = null;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass qualitativeMappingsEClass = null;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass qualitativeMappingEClass = null;

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
	 * @see grl.kpimodel.KpimodelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
    private KpimodelPackageImpl() {
		super(eNS_URI, KpimodelFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link KpimodelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
    public static KpimodelPackage init() {
		if (isInited) return (KpimodelPackage)EPackage.Registry.INSTANCE.getEPackage(KpimodelPackage.eNS_URI);

		// Obtain or create and register package
		KpimodelPackageImpl theKpimodelPackage = (KpimodelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof KpimodelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new KpimodelPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		FmPackageImpl theFmPackage = (FmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(FmPackage.eNS_URI) instanceof FmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(FmPackage.eNS_URI) : FmPackage.eINSTANCE);
		GrlPackageImpl theGrlPackage = (GrlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(GrlPackage.eNS_URI) instanceof GrlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(GrlPackage.eNS_URI) : GrlPackage.eINSTANCE);
		UrncorePackageImpl theUrncorePackage = (UrncorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI) instanceof UrncorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI) : UrncorePackage.eINSTANCE);
		UrnPackageImpl theUrnPackage = (UrnPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI) instanceof UrnPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI) : UrnPackage.eINSTANCE);
		UcmPackageImpl theUcmPackage = (UcmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI) instanceof UcmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI) : UcmPackage.eINSTANCE);
		PerformancePackageImpl thePerformancePackage = (PerformancePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI) instanceof PerformancePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI) : PerformancePackage.eINSTANCE);
		MapPackageImpl theMapPackage = (MapPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) instanceof MapPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) : MapPackage.eINSTANCE);
		ScenarioPackageImpl theScenarioPackage = (ScenarioPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) instanceof ScenarioPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) : ScenarioPackage.eINSTANCE);
		AsdPackageImpl theAsdPackage = (AsdPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AsdPackage.eNS_URI) instanceof AsdPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AsdPackage.eNS_URI) : AsdPackage.eINSTANCE);

		// Create package meta-data objects
		theKpimodelPackage.createPackageContents();
		theFmPackage.createPackageContents();
		theGrlPackage.createPackageContents();
		theUrncorePackage.createPackageContents();
		theUrnPackage.createPackageContents();
		theUcmPackage.createPackageContents();
		thePerformancePackage.createPackageContents();
		theMapPackage.createPackageContents();
		theScenarioPackage.createPackageContents();
		theAsdPackage.createPackageContents();

		// Initialize created meta-data
		theKpimodelPackage.initializePackageContents();
		theFmPackage.initializePackageContents();
		theGrlPackage.initializePackageContents();
		theUrncorePackage.initializePackageContents();
		theUrnPackage.initializePackageContents();
		theUcmPackage.initializePackageContents();
		thePerformancePackage.initializePackageContents();
		theMapPackage.initializePackageContents();
		theScenarioPackage.initializePackageContents();
		theAsdPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theKpimodelPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(KpimodelPackage.eNS_URI, theKpimodelPackage);
		return theKpimodelPackage;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getIndicatorGroup() {
		return indicatorGroupEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getIndicatorGroup_IsRedesignCategory() {
		return (EAttribute)indicatorGroupEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIndicatorGroup_Grlspec() {
		return (EReference)indicatorGroupEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIndicatorGroup_Indicators() {
		return (EReference)indicatorGroupEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getIndicator() {
		return indicatorEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIndicator_KpiModelLinksDest() {
		return (EReference)indicatorEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIndicator_Groups() {
		return (EReference)indicatorEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getKPIInformationElement() {
		return kpiInformationElementEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getKPIInformationElement_Refs() {
		return (EReference)kpiInformationElementEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getKPIInformationElement_Grlspec() {
		return (EReference)kpiInformationElementEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getKPIInformationElement_KpiModelLinksSrc() {
		return (EReference)kpiInformationElementEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getKPIInformationElementRef() {
		return kpiInformationElementRefEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getKPIInformationElementRef_Def() {
		return (EReference)kpiInformationElementRefEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getKPIModelLink() {
		return kpiModelLinkEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getKPIModelLink_KpiInformationElementSrc() {
		return (EReference)kpiModelLinkEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getKPIModelLink_Refs() {
		return (EReference)kpiModelLinkEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getKPIModelLink_Grlspec() {
		return (EReference)kpiModelLinkEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getKPIModelLink_IndDest() {
		return (EReference)kpiModelLinkEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getKPIModelLinkRef() {
		return kpiModelLinkRefEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getKPIModelLinkRef_Link() {
		return (EReference)kpiModelLinkRefEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getKPIEvalValueSet() {
		return kpiEvalValueSetEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getKPIEvalValueSet_TargetValue() {
		return (EAttribute)kpiEvalValueSetEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getKPIEvalValueSet_ThresholdValue() {
		return (EAttribute)kpiEvalValueSetEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getKPIEvalValueSet_WorstValue() {
		return (EAttribute)kpiEvalValueSetEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getKPIEvalValueSet_EvaluationValue() {
		return (EAttribute)kpiEvalValueSetEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getKPIEvalValueSet_Unit() {
		return (EAttribute)kpiEvalValueSetEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getKPIEvalValueSet_QualitativeEvaluationValue() {
		return (EAttribute)kpiEvalValueSetEClass.getEStructuralFeatures().get(5);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getKPIEvalValueSet_Eval() {
		return (EReference)kpiEvalValueSetEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKPIEvalValueSet_KpiConv() {
		return (EReference)kpiEvalValueSetEClass.getEStructuralFeatures().get(7);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getKPIInformationConfig() {
		return kpiInformationConfigEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getKPIInformationConfig_LevelOfDimension() {
		return (EAttribute)kpiInformationConfigEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getKPIInformationConfig_ValueOfDimension() {
		return (EAttribute)kpiInformationConfigEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getKPIInformationConfig_Strategies() {
		return (EReference)kpiInformationConfigEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getKPIInformationConfig_KpiInfoElement() {
		return (EReference)kpiInformationConfigEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getKPINewEvalValue() {
		return kpiNewEvalValueEClass;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getKPINewEvalValue_EvaluationValue() {
		return (EAttribute)kpiNewEvalValueEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKPINewEvalValue_Eval() {
		return (EReference)kpiNewEvalValueEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getKPIConversion() {
		return kpiConversionEClass;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKPIConversion_KpiEvalValueSet() {
		return (EReference)kpiConversionEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getKPIConversion_Grlspec() {
		return (EReference)kpiConversionEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQualitativeMappings() {
		return qualitativeMappingsEClass;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQualitativeMappings_Mapping() {
		return (EReference)qualitativeMappingsEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQualitativeMapping() {
		return qualitativeMappingEClass;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQualitativeMapping_RealWorldLabel() {
		return (EAttribute)qualitativeMappingEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQualitativeMapping_Evaluation() {
		return (EAttribute)qualitativeMappingEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQualitativeMapping_QualitativeEvaluation() {
		return (EAttribute)qualitativeMappingEClass.getEStructuralFeatures().get(2);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getQualitativeMapping_Exceeds() {
		return (EAttribute)qualitativeMappingEClass.getEStructuralFeatures().get(3);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public KpimodelFactory getKpimodelFactory() {
		return (KpimodelFactory)getEFactoryInstance();
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
		indicatorGroupEClass = createEClass(INDICATOR_GROUP);
		createEAttribute(indicatorGroupEClass, INDICATOR_GROUP__IS_REDESIGN_CATEGORY);
		createEReference(indicatorGroupEClass, INDICATOR_GROUP__GRLSPEC);
		createEReference(indicatorGroupEClass, INDICATOR_GROUP__INDICATORS);

		indicatorEClass = createEClass(INDICATOR);
		createEReference(indicatorEClass, INDICATOR__KPI_MODEL_LINKS_DEST);
		createEReference(indicatorEClass, INDICATOR__GROUPS);

		kpiInformationElementEClass = createEClass(KPI_INFORMATION_ELEMENT);
		createEReference(kpiInformationElementEClass, KPI_INFORMATION_ELEMENT__REFS);
		createEReference(kpiInformationElementEClass, KPI_INFORMATION_ELEMENT__GRLSPEC);
		createEReference(kpiInformationElementEClass, KPI_INFORMATION_ELEMENT__KPI_MODEL_LINKS_SRC);

		kpiInformationElementRefEClass = createEClass(KPI_INFORMATION_ELEMENT_REF);
		createEReference(kpiInformationElementRefEClass, KPI_INFORMATION_ELEMENT_REF__DEF);

		kpiModelLinkEClass = createEClass(KPI_MODEL_LINK);
		createEReference(kpiModelLinkEClass, KPI_MODEL_LINK__KPI_INFORMATION_ELEMENT_SRC);
		createEReference(kpiModelLinkEClass, KPI_MODEL_LINK__REFS);
		createEReference(kpiModelLinkEClass, KPI_MODEL_LINK__GRLSPEC);
		createEReference(kpiModelLinkEClass, KPI_MODEL_LINK__IND_DEST);

		kpiModelLinkRefEClass = createEClass(KPI_MODEL_LINK_REF);
		createEReference(kpiModelLinkRefEClass, KPI_MODEL_LINK_REF__LINK);

		kpiEvalValueSetEClass = createEClass(KPI_EVAL_VALUE_SET);
		createEAttribute(kpiEvalValueSetEClass, KPI_EVAL_VALUE_SET__TARGET_VALUE);
		createEAttribute(kpiEvalValueSetEClass, KPI_EVAL_VALUE_SET__THRESHOLD_VALUE);
		createEAttribute(kpiEvalValueSetEClass, KPI_EVAL_VALUE_SET__WORST_VALUE);
		createEAttribute(kpiEvalValueSetEClass, KPI_EVAL_VALUE_SET__EVALUATION_VALUE);
		createEAttribute(kpiEvalValueSetEClass, KPI_EVAL_VALUE_SET__UNIT);
		createEAttribute(kpiEvalValueSetEClass, KPI_EVAL_VALUE_SET__QUALITATIVE_EVALUATION_VALUE);
		createEReference(kpiEvalValueSetEClass, KPI_EVAL_VALUE_SET__EVAL);
		createEReference(kpiEvalValueSetEClass, KPI_EVAL_VALUE_SET__KPI_CONV);

		kpiInformationConfigEClass = createEClass(KPI_INFORMATION_CONFIG);
		createEAttribute(kpiInformationConfigEClass, KPI_INFORMATION_CONFIG__LEVEL_OF_DIMENSION);
		createEAttribute(kpiInformationConfigEClass, KPI_INFORMATION_CONFIG__VALUE_OF_DIMENSION);
		createEReference(kpiInformationConfigEClass, KPI_INFORMATION_CONFIG__STRATEGIES);
		createEReference(kpiInformationConfigEClass, KPI_INFORMATION_CONFIG__KPI_INFO_ELEMENT);

		kpiNewEvalValueEClass = createEClass(KPI_NEW_EVAL_VALUE);
		createEAttribute(kpiNewEvalValueEClass, KPI_NEW_EVAL_VALUE__EVALUATION_VALUE);
		createEReference(kpiNewEvalValueEClass, KPI_NEW_EVAL_VALUE__EVAL);

		kpiConversionEClass = createEClass(KPI_CONVERSION);
		createEReference(kpiConversionEClass, KPI_CONVERSION__KPI_EVAL_VALUE_SET);
		createEReference(kpiConversionEClass, KPI_CONVERSION__GRLSPEC);

		qualitativeMappingsEClass = createEClass(QUALITATIVE_MAPPINGS);
		createEReference(qualitativeMappingsEClass, QUALITATIVE_MAPPINGS__MAPPING);

		qualitativeMappingEClass = createEClass(QUALITATIVE_MAPPING);
		createEAttribute(qualitativeMappingEClass, QUALITATIVE_MAPPING__REAL_WORLD_LABEL);
		createEAttribute(qualitativeMappingEClass, QUALITATIVE_MAPPING__EVALUATION);
		createEAttribute(qualitativeMappingEClass, QUALITATIVE_MAPPING__QUALITATIVE_EVALUATION);
		createEAttribute(qualitativeMappingEClass, QUALITATIVE_MAPPING__EXCEEDS);
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
		GrlPackage theGrlPackage = (GrlPackage)EPackage.Registry.INSTANCE.getEPackage(GrlPackage.eNS_URI);

		// Add supertypes to classes
		indicatorGroupEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());
		indicatorEClass.getESuperTypes().add(theGrlPackage.getIntentionalElement());
		kpiInformationElementEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());
		kpiInformationElementRefEClass.getESuperTypes().add(theGrlPackage.getGRLNode());
		kpiModelLinkEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());
		kpiModelLinkRefEClass.getESuperTypes().add(theUrncorePackage.getIURNConnection());
		kpiConversionEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());
		qualitativeMappingsEClass.getESuperTypes().add(this.getKPIConversion());

		// Initialize classes and features; add operations and parameters
		initEClass(indicatorGroupEClass, IndicatorGroup.class, "IndicatorGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIndicatorGroup_IsRedesignCategory(), ecorePackage.getEBoolean(), "isRedesignCategory", null, 0, 1, IndicatorGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIndicatorGroup_Grlspec(), theGrlPackage.getGRLspec(), theGrlPackage.getGRLspec_IndicatorGroup(), "grlspec", null, 1, 1, IndicatorGroup.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIndicatorGroup_Indicators(), this.getIndicator(), this.getIndicator_Groups(), "indicators", null, 0, -1, IndicatorGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(indicatorEClass, Indicator.class, "Indicator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIndicator_KpiModelLinksDest(), this.getKPIModelLink(), this.getKPIModelLink_IndDest(), "kpiModelLinksDest", null, 0, -1, Indicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIndicator_Groups(), this.getIndicatorGroup(), this.getIndicatorGroup_Indicators(), "groups", null, 0, -1, Indicator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(kpiInformationElementEClass, KPIInformationElement.class, "KPIInformationElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getKPIInformationElement_Refs(), this.getKPIInformationElementRef(), this.getKPIInformationElementRef_Def(), "refs", null, 0, -1, KPIInformationElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKPIInformationElement_Grlspec(), theGrlPackage.getGRLspec(), theGrlPackage.getGRLspec_KpiInformationElements(), "grlspec", null, 1, 1, KPIInformationElement.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKPIInformationElement_KpiModelLinksSrc(), this.getKPIModelLink(), this.getKPIModelLink_KpiInformationElementSrc(), "kpiModelLinksSrc", null, 0, -1, KPIInformationElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(kpiInformationElementRefEClass, KPIInformationElementRef.class, "KPIInformationElementRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getKPIInformationElementRef_Def(), this.getKPIInformationElement(), this.getKPIInformationElement_Refs(), "def", null, 1, 1, KPIInformationElementRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(kpiModelLinkEClass, KPIModelLink.class, "KPIModelLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getKPIModelLink_KpiInformationElementSrc(), this.getKPIInformationElement(), this.getKPIInformationElement_KpiModelLinksSrc(), "kpiInformationElementSrc", null, 1, 1, KPIModelLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKPIModelLink_Refs(), this.getKPIModelLinkRef(), this.getKPIModelLinkRef_Link(), "refs", null, 0, -1, KPIModelLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKPIModelLink_Grlspec(), theGrlPackage.getGRLspec(), theGrlPackage.getGRLspec_KpiModelLinks(), "grlspec", null, 1, 1, KPIModelLink.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKPIModelLink_IndDest(), this.getIndicator(), this.getIndicator_KpiModelLinksDest(), "indDest", null, 1, 1, KPIModelLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(kpiModelLinkRefEClass, KPIModelLinkRef.class, "KPIModelLinkRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getKPIModelLinkRef_Link(), this.getKPIModelLink(), this.getKPIModelLink_Refs(), "link", null, 1, 1, KPIModelLinkRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(kpiEvalValueSetEClass, KPIEvalValueSet.class, "KPIEvalValueSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getKPIEvalValueSet_TargetValue(), ecorePackage.getEDouble(), "targetValue", "0", 0, 1, KPIEvalValueSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKPIEvalValueSet_ThresholdValue(), ecorePackage.getEDouble(), "thresholdValue", "0", 0, 1, KPIEvalValueSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKPIEvalValueSet_WorstValue(), ecorePackage.getEDouble(), "worstValue", "0", 0, 1, KPIEvalValueSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKPIEvalValueSet_EvaluationValue(), ecorePackage.getEDouble(), "evaluationValue", "0", 0, 1, KPIEvalValueSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKPIEvalValueSet_Unit(), ecorePackage.getEString(), "unit", "", 0, 1, KPIEvalValueSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKPIEvalValueSet_QualitativeEvaluationValue(), ecorePackage.getEString(), "qualitativeEvaluationValue", "", 0, 1, KPIEvalValueSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKPIEvalValueSet_Eval(), theGrlPackage.getEvaluation(), theGrlPackage.getEvaluation_KpiEvalValueSet(), "eval", null, 1, 1, KPIEvalValueSet.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKPIEvalValueSet_KpiConv(), this.getKPIConversion(), this.getKPIConversion_KpiEvalValueSet(), "kpiConv", null, 0, 1, KPIEvalValueSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(kpiInformationConfigEClass, KPIInformationConfig.class, "KPIInformationConfig", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getKPIInformationConfig_LevelOfDimension(), ecorePackage.getEString(), "levelOfDimension", null, 0, 1, KPIInformationConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKPIInformationConfig_ValueOfDimension(), ecorePackage.getEString(), "valueOfDimension", null, 0, 1, KPIInformationConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKPIInformationConfig_Strategies(), theGrlPackage.getEvaluationStrategy(), theGrlPackage.getEvaluationStrategy_KpiInfoConfig(), "strategies", null, 1, 1, KPIInformationConfig.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKPIInformationConfig_KpiInfoElement(), this.getKPIInformationElement(), null, "kpiInfoElement", null, 1, 1, KPIInformationConfig.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(kpiNewEvalValueEClass, KPINewEvalValue.class, "KPINewEvalValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getKPINewEvalValue_EvaluationValue(), ecorePackage.getEDouble(), "evaluationValue", "0", 0, 1, KPINewEvalValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKPINewEvalValue_Eval(), theGrlPackage.getEvaluation(), theGrlPackage.getEvaluation_KpiNewEvalValue(), "eval", null, 1, 1, KPINewEvalValue.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(kpiConversionEClass, KPIConversion.class, "KPIConversion", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getKPIConversion_KpiEvalValueSet(), this.getKPIEvalValueSet(), this.getKPIEvalValueSet_KpiConv(), "kpiEvalValueSet", null, 0, -1, KPIConversion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKPIConversion_Grlspec(), theGrlPackage.getGRLspec(), theGrlPackage.getGRLspec_KPIConversion(), "grlspec", null, 1, 1, KPIConversion.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(qualitativeMappingsEClass, QualitativeMappings.class, "QualitativeMappings", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getQualitativeMappings_Mapping(), this.getQualitativeMapping(), null, "mapping", null, 0, -1, QualitativeMappings.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(qualitativeMappingEClass, QualitativeMapping.class, "QualitativeMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getQualitativeMapping_RealWorldLabel(), ecorePackage.getEString(), "realWorldLabel", null, 0, 1, QualitativeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getQualitativeMapping_Evaluation(), ecorePackage.getEInt(), "evaluation", null, 0, 1, QualitativeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getQualitativeMapping_QualitativeEvaluation(), theGrlPackage.getQualitativeLabel(), "qualitativeEvaluation", null, 0, 1, QualitativeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getQualitativeMapping_Exceeds(), ecorePackage.getEBoolean(), "exceeds", null, 0, 1, QualitativeMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
	}

} //KpimodelPackageImpl
