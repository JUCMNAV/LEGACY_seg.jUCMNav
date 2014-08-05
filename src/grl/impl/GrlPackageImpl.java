/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import asd.AsdPackage;
import asd.impl.AsdPackageImpl;
import ca.mcgill.sel.core.CorePackage;
import fm.FmPackage;
import fm.impl.FmPackageImpl;
import grl.Actor;
import grl.ActorRef;
import grl.Belief;
import grl.BeliefLink;
import grl.CollapsedActorRef;
import grl.Contribution;
import grl.ContributionChange;
import grl.ContributionContext;
import grl.ContributionContextGroup;
import grl.ContributionRange;
import grl.ContributionType;
import grl.Criticality;
import grl.Decomposition;
import grl.DecompositionType;
import grl.Dependency;
import grl.ElementLink;
import grl.Evaluation;
import grl.EvaluationRange;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.GRLLinkableElement;
import grl.GRLNode;
import grl.GRLspec;
import grl.GrlFactory;
import grl.GrlPackage;
import grl.ImpactModel;
import grl.ImportanceType;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.IntentionalElementType;
import grl.LinkRef;
import grl.LinkRefBendpoint;
import grl.Priority;
import grl.QualitativeLabel;
import grl.StrategiesGroup;
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
public class GrlPackageImpl extends EPackageImpl implements GrlPackage {
    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass grLspecEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass beliefEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass intentionalElementEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass actorEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass grlGraphEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass actorRefEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass intentionalElementRefEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass contributionEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass linkRefEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass elementLinkEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass decompositionEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass dependencyEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass evaluationEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass evaluationStrategyEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass grlNodeEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass linkRefBendpointEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass beliefLinkEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass strategiesGroupEClass = null;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contributionContextGroupEClass = null;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contributionContextEClass = null;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contributionChangeEClass = null;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass grlLinkableElementEClass = null;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass collapsedActorRefEClass = null;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass evaluationRangeEClass = null;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contributionRangeEClass = null;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass impactModelEClass = null;

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum criticalityEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum intentionalElementTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum priorityEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum contributionTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum decompositionTypeEEnum = null;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum qualitativeLabelEEnum = null;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum importanceTypeEEnum = null;

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
	 * @see grl.GrlPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
    private GrlPackageImpl() {
		super(eNS_URI, GrlFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link GrlPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
    public static GrlPackage init() {
		if (isInited) return (GrlPackage)EPackage.Registry.INSTANCE.getEPackage(GrlPackage.eNS_URI);

		// Obtain or create and register package
		GrlPackageImpl theGrlPackage = (GrlPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof GrlPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new GrlPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		FmPackageImpl theFmPackage = (FmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(FmPackage.eNS_URI) instanceof FmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(FmPackage.eNS_URI) : FmPackage.eINSTANCE);
		KpimodelPackageImpl theKpimodelPackage = (KpimodelPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(KpimodelPackage.eNS_URI) instanceof KpimodelPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(KpimodelPackage.eNS_URI) : KpimodelPackage.eINSTANCE);
		UrncorePackageImpl theUrncorePackage = (UrncorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI) instanceof UrncorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI) : UrncorePackage.eINSTANCE);
		UrnPackageImpl theUrnPackage = (UrnPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI) instanceof UrnPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI) : UrnPackage.eINSTANCE);
		UcmPackageImpl theUcmPackage = (UcmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI) instanceof UcmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI) : UcmPackage.eINSTANCE);
		PerformancePackageImpl thePerformancePackage = (PerformancePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI) instanceof PerformancePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI) : PerformancePackage.eINSTANCE);
		MapPackageImpl theMapPackage = (MapPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) instanceof MapPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) : MapPackage.eINSTANCE);
		ScenarioPackageImpl theScenarioPackage = (ScenarioPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) instanceof ScenarioPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) : ScenarioPackage.eINSTANCE);
		AsdPackageImpl theAsdPackage = (AsdPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AsdPackage.eNS_URI) instanceof AsdPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(AsdPackage.eNS_URI) : AsdPackage.eINSTANCE);

		// Create package meta-data objects
		theGrlPackage.createPackageContents();
		theFmPackage.createPackageContents();
		theKpimodelPackage.createPackageContents();
		theUrncorePackage.createPackageContents();
		theUrnPackage.createPackageContents();
		theUcmPackage.createPackageContents();
		thePerformancePackage.createPackageContents();
		theMapPackage.createPackageContents();
		theScenarioPackage.createPackageContents();
		theAsdPackage.createPackageContents();

		// Initialize created meta-data
		theGrlPackage.initializePackageContents();
		theFmPackage.initializePackageContents();
		theKpimodelPackage.initializePackageContents();
		theUrncorePackage.initializePackageContents();
		theUrnPackage.initializePackageContents();
		theUcmPackage.initializePackageContents();
		thePerformancePackage.initializePackageContents();
		theMapPackage.initializePackageContents();
		theScenarioPackage.initializePackageContents();
		theAsdPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theGrlPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(GrlPackage.eNS_URI, theGrlPackage);
		return theGrlPackage;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getGRLspec() {
		return grLspecEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getGRLspec_Urnspec() {
		return (EReference)grLspecEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getGRLspec_IntElements() {
		return (EReference)grLspecEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getGRLspec_Actors() {
		return (EReference)grLspecEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getGRLspec_Links() {
		return (EReference)grLspecEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getGRLspec_Groups() {
		return (EReference)grLspecEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getGRLspec_Strategies() {
		return (EReference)grLspecEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGRLspec_ContributionGroups() {
		return (EReference)grLspecEClass.getEStructuralFeatures().get(6);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGRLspec_ContributionContexts() {
		return (EReference)grLspecEClass.getEStructuralFeatures().get(7);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGRLspec_ImpactModel() {
		return (EReference)grLspecEClass.getEStructuralFeatures().get(8);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getGRLspec_KpiInformationElements() {
		return (EReference)grLspecEClass.getEStructuralFeatures().get(9);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getGRLspec_KpiModelLinks() {
		return (EReference)grLspecEClass.getEStructuralFeatures().get(10);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getGRLspec_IndicatorGroup() {
		return (EReference)grLspecEClass.getEStructuralFeatures().get(11);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGRLspec_KPIConversion() {
		return (EReference)grLspecEClass.getEStructuralFeatures().get(12);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGRLspec_FeatureModel() {
		return (EReference)grLspecEClass.getEStructuralFeatures().get(13);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getBelief() {
		return beliefEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getBelief_Author() {
		return (EAttribute)beliefEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getIntentionalElement() {
		return intentionalElementEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getIntentionalElement_Type() {
		return (EAttribute)intentionalElementEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getIntentionalElement_DecompositionType() {
		return (EAttribute)intentionalElementEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntentionalElement_Importance() {
		return (EAttribute)intentionalElementEClass.getEStructuralFeatures().get(2);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntentionalElement_ImportanceQuantitative() {
		return (EAttribute)intentionalElementEClass.getEStructuralFeatures().get(3);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getIntentionalElement_LineColor() {
		return (EAttribute)intentionalElementEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getIntentionalElement_FillColor() {
		return (EAttribute)intentionalElementEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getIntentionalElement_Filled() {
		return (EAttribute)intentionalElementEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIntentionalElement_Grlspec() {
		return (EReference)intentionalElementEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIntentionalElement_Refs() {
		return (EReference)intentionalElementEClass.getEStructuralFeatures().get(8);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getActor() {
		return actorEClass;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActor_Importance() {
		return (EAttribute)actorEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getActor_ImportanceQuantitative() {
		return (EAttribute)actorEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getActor_Grlspec() {
		return (EReference)actorEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_IncludedActors() {
		return (EReference)actorEClass.getEStructuralFeatures().get(3);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_IncludingActor() {
		return (EReference)actorEClass.getEStructuralFeatures().get(4);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActor_CollapsedRefs() {
		return (EReference)actorEClass.getEStructuralFeatures().get(5);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getGRLGraph() {
		return grlGraphEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getActorRef() {
		return actorRefEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getIntentionalElementRef() {
		return intentionalElementRefEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getIntentionalElementRef_Criticality() {
		return (EAttribute)intentionalElementRefEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getIntentionalElementRef_Priority() {
		return (EAttribute)intentionalElementRefEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getIntentionalElementRef_Def() {
		return (EReference)intentionalElementRefEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getContribution() {
		return contributionEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getContribution_Contribution() {
		return (EAttribute)contributionEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContribution_QuantitativeContribution() {
		return (EAttribute)contributionEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getContribution_Correlation() {
		return (EAttribute)contributionEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getLinkRef() {
		return linkRefEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getLinkRef_Link() {
		return (EReference)linkRefEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getLinkRef_Bendpoints() {
		return (EReference)linkRefEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getElementLink() {
		return elementLinkEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getElementLink_Refs() {
		return (EReference)elementLinkEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getElementLink_Grlspec() {
		return (EReference)elementLinkEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getElementLink_Src() {
		return (EReference)elementLinkEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getElementLink_Dest() {
		return (EReference)elementLinkEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getDecomposition() {
		return decompositionEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getDependency() {
		return dependencyEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getEvaluation() {
		return evaluationEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getEvaluation_Evaluation() {
		return (EAttribute)evaluationEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEvaluation_QualitativeEvaluation() {
		return (EAttribute)evaluationEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEvaluation_Exceeds() {
		return (EAttribute)evaluationEClass.getEStructuralFeatures().get(2);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getEvaluation_IntElement() {
		return (EReference)evaluationEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getEvaluation_Strategies() {
		return (EReference)evaluationEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getEvaluation_KpiEvalValueSet() {
		return (EReference)evaluationEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEvaluation_EvalRange() {
		return (EReference)evaluationEClass.getEStructuralFeatures().get(6);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEvaluation_KpiNewEvalValue() {
		return (EReference)evaluationEClass.getEStructuralFeatures().get(7);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getEvaluationStrategy() {
		return evaluationStrategyEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getEvaluationStrategy_Author() {
		return (EAttribute)evaluationStrategyEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getEvaluationStrategy_Evaluations() {
		return (EReference)evaluationStrategyEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getEvaluationStrategy_Group() {
		return (EReference)evaluationStrategyEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getEvaluationStrategy_Grlspec() {
		return (EReference)evaluationStrategyEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEvaluationStrategy_IncludedStrategies() {
		return (EReference)evaluationStrategyEClass.getEStructuralFeatures().get(4);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEvaluationStrategy_ParentStrategies() {
		return (EReference)evaluationStrategyEClass.getEStructuralFeatures().get(5);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getEvaluationStrategy_KpiInfoConfig() {
		return (EReference)evaluationStrategyEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getGRLNode() {
		return grlNodeEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getLinkRefBendpoint() {
		return linkRefBendpointEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLinkRefBendpoint_X() {
		return (EAttribute)linkRefBendpointEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getLinkRefBendpoint_Y() {
		return (EAttribute)linkRefBendpointEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getLinkRefBendpoint_Linkref() {
		return (EReference)linkRefBendpointEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getBeliefLink() {
		return beliefLinkEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getStrategiesGroup() {
		return strategiesGroupEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getStrategiesGroup_Strategies() {
		return (EReference)strategiesGroupEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getStrategiesGroup_Grlspec() {
		return (EReference)strategiesGroupEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContributionContextGroup() {
		return contributionContextGroupEClass;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContributionContextGroup_Grlspec() {
		return (EReference)contributionContextGroupEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContributionContextGroup_Contribs() {
		return (EReference)contributionContextGroupEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContributionContext() {
		return contributionContextEClass;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContributionContext_Grlspec() {
		return (EReference)contributionContextEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContributionContext_Groups() {
		return (EReference)contributionContextEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContributionContext_Changes() {
		return (EReference)contributionContextEClass.getEStructuralFeatures().get(2);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContributionContext_ParentContexts() {
		return (EReference)contributionContextEClass.getEStructuralFeatures().get(3);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContributionContext_IncludedContexts() {
		return (EReference)contributionContextEClass.getEStructuralFeatures().get(4);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContributionChange() {
		return contributionChangeEClass;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContributionChange_NewContribution() {
		return (EAttribute)contributionChangeEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContributionChange_NewQuantitativeContribution() {
		return (EAttribute)contributionChangeEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContributionChange_Context() {
		return (EReference)contributionChangeEClass.getEStructuralFeatures().get(2);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContributionChange_Contribution() {
		return (EReference)contributionChangeEClass.getEStructuralFeatures().get(3);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContributionChange_ContribRange() {
		return (EReference)contributionChangeEClass.getEStructuralFeatures().get(4);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGRLLinkableElement() {
		return grlLinkableElementEClass;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGRLLinkableElement_LinksDest() {
		return (EReference)grlLinkableElementEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGRLLinkableElement_LinksSrc() {
		return (EReference)grlLinkableElementEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCollapsedActorRef() {
		return collapsedActorRefEClass;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCollapsedActorRef_Actor() {
		return (EReference)collapsedActorRefEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEvaluationRange() {
		return evaluationRangeEClass;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEvaluationRange_Start() {
		return (EAttribute)evaluationRangeEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEvaluationRange_End() {
		return (EAttribute)evaluationRangeEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getEvaluationRange_Step() {
		return (EAttribute)evaluationRangeEClass.getEStructuralFeatures().get(2);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEvaluationRange_Eval() {
		return (EReference)evaluationRangeEClass.getEStructuralFeatures().get(3);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getContributionRange() {
		return contributionRangeEClass;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContributionRange_Start() {
		return (EAttribute)contributionRangeEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContributionRange_End() {
		return (EAttribute)contributionRangeEClass.getEStructuralFeatures().get(1);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getContributionRange_Step() {
		return (EAttribute)contributionRangeEClass.getEStructuralFeatures().get(2);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getContributionRange_Change() {
		return (EReference)contributionRangeEClass.getEStructuralFeatures().get(3);
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getImpactModel() {
		return impactModelEClass;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getImpactModel_Grlspec() {
		return (EReference)impactModelEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getCriticality() {
		return criticalityEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getIntentionalElementType() {
		return intentionalElementTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getPriority() {
		return priorityEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getContributionType() {
		return contributionTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getDecompositionType() {
		return decompositionTypeEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getQualitativeLabel() {
		return qualitativeLabelEEnum;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getImportanceType() {
		return importanceTypeEEnum;
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public GrlFactory getGrlFactory() {
		return (GrlFactory)getEFactoryInstance();
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
		grLspecEClass = createEClass(GR_LSPEC);
		createEReference(grLspecEClass, GR_LSPEC__URNSPEC);
		createEReference(grLspecEClass, GR_LSPEC__INT_ELEMENTS);
		createEReference(grLspecEClass, GR_LSPEC__ACTORS);
		createEReference(grLspecEClass, GR_LSPEC__LINKS);
		createEReference(grLspecEClass, GR_LSPEC__GROUPS);
		createEReference(grLspecEClass, GR_LSPEC__STRATEGIES);
		createEReference(grLspecEClass, GR_LSPEC__CONTRIBUTION_GROUPS);
		createEReference(grLspecEClass, GR_LSPEC__CONTRIBUTION_CONTEXTS);
		createEReference(grLspecEClass, GR_LSPEC__IMPACT_MODEL);
		createEReference(grLspecEClass, GR_LSPEC__KPI_INFORMATION_ELEMENTS);
		createEReference(grLspecEClass, GR_LSPEC__KPI_MODEL_LINKS);
		createEReference(grLspecEClass, GR_LSPEC__INDICATOR_GROUP);
		createEReference(grLspecEClass, GR_LSPEC__KPI_CONVERSION);
		createEReference(grLspecEClass, GR_LSPEC__FEATURE_MODEL);

		beliefEClass = createEClass(BELIEF);
		createEAttribute(beliefEClass, BELIEF__AUTHOR);

		intentionalElementEClass = createEClass(INTENTIONAL_ELEMENT);
		createEAttribute(intentionalElementEClass, INTENTIONAL_ELEMENT__TYPE);
		createEAttribute(intentionalElementEClass, INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE);
		createEAttribute(intentionalElementEClass, INTENTIONAL_ELEMENT__IMPORTANCE);
		createEAttribute(intentionalElementEClass, INTENTIONAL_ELEMENT__IMPORTANCE_QUANTITATIVE);
		createEAttribute(intentionalElementEClass, INTENTIONAL_ELEMENT__LINE_COLOR);
		createEAttribute(intentionalElementEClass, INTENTIONAL_ELEMENT__FILL_COLOR);
		createEAttribute(intentionalElementEClass, INTENTIONAL_ELEMENT__FILLED);
		createEReference(intentionalElementEClass, INTENTIONAL_ELEMENT__GRLSPEC);
		createEReference(intentionalElementEClass, INTENTIONAL_ELEMENT__REFS);

		actorEClass = createEClass(ACTOR);
		createEAttribute(actorEClass, ACTOR__IMPORTANCE);
		createEAttribute(actorEClass, ACTOR__IMPORTANCE_QUANTITATIVE);
		createEReference(actorEClass, ACTOR__GRLSPEC);
		createEReference(actorEClass, ACTOR__INCLUDED_ACTORS);
		createEReference(actorEClass, ACTOR__INCLUDING_ACTOR);
		createEReference(actorEClass, ACTOR__COLLAPSED_REFS);

		grlGraphEClass = createEClass(GRL_GRAPH);

		actorRefEClass = createEClass(ACTOR_REF);

		intentionalElementRefEClass = createEClass(INTENTIONAL_ELEMENT_REF);
		createEAttribute(intentionalElementRefEClass, INTENTIONAL_ELEMENT_REF__CRITICALITY);
		createEAttribute(intentionalElementRefEClass, INTENTIONAL_ELEMENT_REF__PRIORITY);
		createEReference(intentionalElementRefEClass, INTENTIONAL_ELEMENT_REF__DEF);

		contributionEClass = createEClass(CONTRIBUTION);
		createEAttribute(contributionEClass, CONTRIBUTION__CONTRIBUTION);
		createEAttribute(contributionEClass, CONTRIBUTION__QUANTITATIVE_CONTRIBUTION);
		createEAttribute(contributionEClass, CONTRIBUTION__CORRELATION);

		linkRefEClass = createEClass(LINK_REF);
		createEReference(linkRefEClass, LINK_REF__LINK);
		createEReference(linkRefEClass, LINK_REF__BENDPOINTS);

		elementLinkEClass = createEClass(ELEMENT_LINK);
		createEReference(elementLinkEClass, ELEMENT_LINK__REFS);
		createEReference(elementLinkEClass, ELEMENT_LINK__GRLSPEC);
		createEReference(elementLinkEClass, ELEMENT_LINK__DEST);
		createEReference(elementLinkEClass, ELEMENT_LINK__SRC);

		decompositionEClass = createEClass(DECOMPOSITION);

		dependencyEClass = createEClass(DEPENDENCY);

		evaluationEClass = createEClass(EVALUATION);
		createEAttribute(evaluationEClass, EVALUATION__EVALUATION);
		createEAttribute(evaluationEClass, EVALUATION__QUALITATIVE_EVALUATION);
		createEAttribute(evaluationEClass, EVALUATION__EXCEEDS);
		createEReference(evaluationEClass, EVALUATION__INT_ELEMENT);
		createEReference(evaluationEClass, EVALUATION__STRATEGIES);
		createEReference(evaluationEClass, EVALUATION__KPI_EVAL_VALUE_SET);
		createEReference(evaluationEClass, EVALUATION__EVAL_RANGE);
		createEReference(evaluationEClass, EVALUATION__KPI_NEW_EVAL_VALUE);

		evaluationStrategyEClass = createEClass(EVALUATION_STRATEGY);
		createEAttribute(evaluationStrategyEClass, EVALUATION_STRATEGY__AUTHOR);
		createEReference(evaluationStrategyEClass, EVALUATION_STRATEGY__EVALUATIONS);
		createEReference(evaluationStrategyEClass, EVALUATION_STRATEGY__GROUP);
		createEReference(evaluationStrategyEClass, EVALUATION_STRATEGY__GRLSPEC);
		createEReference(evaluationStrategyEClass, EVALUATION_STRATEGY__INCLUDED_STRATEGIES);
		createEReference(evaluationStrategyEClass, EVALUATION_STRATEGY__PARENT_STRATEGIES);
		createEReference(evaluationStrategyEClass, EVALUATION_STRATEGY__KPI_INFO_CONFIG);

		grlNodeEClass = createEClass(GRL_NODE);

		linkRefBendpointEClass = createEClass(LINK_REF_BENDPOINT);
		createEAttribute(linkRefBendpointEClass, LINK_REF_BENDPOINT__X);
		createEAttribute(linkRefBendpointEClass, LINK_REF_BENDPOINT__Y);
		createEReference(linkRefBendpointEClass, LINK_REF_BENDPOINT__LINKREF);

		beliefLinkEClass = createEClass(BELIEF_LINK);

		strategiesGroupEClass = createEClass(STRATEGIES_GROUP);
		createEReference(strategiesGroupEClass, STRATEGIES_GROUP__STRATEGIES);
		createEReference(strategiesGroupEClass, STRATEGIES_GROUP__GRLSPEC);

		contributionContextGroupEClass = createEClass(CONTRIBUTION_CONTEXT_GROUP);
		createEReference(contributionContextGroupEClass, CONTRIBUTION_CONTEXT_GROUP__GRLSPEC);
		createEReference(contributionContextGroupEClass, CONTRIBUTION_CONTEXT_GROUP__CONTRIBS);

		contributionContextEClass = createEClass(CONTRIBUTION_CONTEXT);
		createEReference(contributionContextEClass, CONTRIBUTION_CONTEXT__GRLSPEC);
		createEReference(contributionContextEClass, CONTRIBUTION_CONTEXT__GROUPS);
		createEReference(contributionContextEClass, CONTRIBUTION_CONTEXT__CHANGES);
		createEReference(contributionContextEClass, CONTRIBUTION_CONTEXT__PARENT_CONTEXTS);
		createEReference(contributionContextEClass, CONTRIBUTION_CONTEXT__INCLUDED_CONTEXTS);

		contributionChangeEClass = createEClass(CONTRIBUTION_CHANGE);
		createEAttribute(contributionChangeEClass, CONTRIBUTION_CHANGE__NEW_CONTRIBUTION);
		createEAttribute(contributionChangeEClass, CONTRIBUTION_CHANGE__NEW_QUANTITATIVE_CONTRIBUTION);
		createEReference(contributionChangeEClass, CONTRIBUTION_CHANGE__CONTEXT);
		createEReference(contributionChangeEClass, CONTRIBUTION_CHANGE__CONTRIBUTION);
		createEReference(contributionChangeEClass, CONTRIBUTION_CHANGE__CONTRIB_RANGE);

		grlLinkableElementEClass = createEClass(GRL_LINKABLE_ELEMENT);
		createEReference(grlLinkableElementEClass, GRL_LINKABLE_ELEMENT__LINKS_DEST);
		createEReference(grlLinkableElementEClass, GRL_LINKABLE_ELEMENT__LINKS_SRC);

		collapsedActorRefEClass = createEClass(COLLAPSED_ACTOR_REF);
		createEReference(collapsedActorRefEClass, COLLAPSED_ACTOR_REF__ACTOR);

		evaluationRangeEClass = createEClass(EVALUATION_RANGE);
		createEAttribute(evaluationRangeEClass, EVALUATION_RANGE__START);
		createEAttribute(evaluationRangeEClass, EVALUATION_RANGE__END);
		createEAttribute(evaluationRangeEClass, EVALUATION_RANGE__STEP);
		createEReference(evaluationRangeEClass, EVALUATION_RANGE__EVAL);

		contributionRangeEClass = createEClass(CONTRIBUTION_RANGE);
		createEAttribute(contributionRangeEClass, CONTRIBUTION_RANGE__START);
		createEAttribute(contributionRangeEClass, CONTRIBUTION_RANGE__END);
		createEAttribute(contributionRangeEClass, CONTRIBUTION_RANGE__STEP);
		createEReference(contributionRangeEClass, CONTRIBUTION_RANGE__CHANGE);

		impactModelEClass = createEClass(IMPACT_MODEL);
		createEReference(impactModelEClass, IMPACT_MODEL__GRLSPEC);

		// Create enums
		criticalityEEnum = createEEnum(CRITICALITY);
		intentionalElementTypeEEnum = createEEnum(INTENTIONAL_ELEMENT_TYPE);
		priorityEEnum = createEEnum(PRIORITY);
		contributionTypeEEnum = createEEnum(CONTRIBUTION_TYPE);
		decompositionTypeEEnum = createEEnum(DECOMPOSITION_TYPE);
		qualitativeLabelEEnum = createEEnum(QUALITATIVE_LABEL);
		importanceTypeEEnum = createEEnum(IMPORTANCE_TYPE);
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
		KpimodelPackage theKpimodelPackage = (KpimodelPackage)EPackage.Registry.INSTANCE.getEPackage(KpimodelPackage.eNS_URI);
		UrnPackage theUrnPackage = (UrnPackage)EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI);
		FmPackage theFmPackage = (FmPackage)EPackage.Registry.INSTANCE.getEPackage(FmPackage.eNS_URI);
		UrncorePackage theUrncorePackage = (UrncorePackage)EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI);
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theKpimodelPackage);

		// Add supertypes to classes
		beliefEClass.getESuperTypes().add(this.getGRLNode());
		intentionalElementEClass.getESuperTypes().add(this.getGRLLinkableElement());
		actorEClass.getESuperTypes().add(this.getGRLLinkableElement());
		actorEClass.getESuperTypes().add(theUrncorePackage.getIURNContainer());
		grlGraphEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());
		grlGraphEClass.getESuperTypes().add(theUrncorePackage.getIURNDiagram());
		actorRefEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());
		actorRefEClass.getESuperTypes().add(theUrncorePackage.getIURNContainerRef());
		intentionalElementRefEClass.getESuperTypes().add(this.getGRLNode());
		contributionEClass.getESuperTypes().add(this.getElementLink());
		linkRefEClass.getESuperTypes().add(theUrncorePackage.getIURNConnection());
		elementLinkEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());
		decompositionEClass.getESuperTypes().add(this.getElementLink());
		dependencyEClass.getESuperTypes().add(this.getElementLink());
		evaluationStrategyEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());
		grlNodeEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());
		grlNodeEClass.getESuperTypes().add(theUrncorePackage.getIURNNode());
		beliefLinkEClass.getESuperTypes().add(theUrncorePackage.getIURNConnection());
		strategiesGroupEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());
		contributionContextGroupEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());
		contributionContextEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());
		grlLinkableElementEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());
		collapsedActorRefEClass.getESuperTypes().add(this.getGRLNode());
		impactModelEClass.getESuperTypes().add(theCorePackage.getCOREImpactModel());

		// Initialize classes and features; add operations and parameters
		initEClass(grLspecEClass, GRLspec.class, "GRLspec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGRLspec_Urnspec(), theUrnPackage.getURNspec(), theUrnPackage.getURNspec_Grlspec(), "urnspec", null, 1, 1, GRLspec.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGRLspec_IntElements(), this.getIntentionalElement(), this.getIntentionalElement_Grlspec(), "intElements", null, 0, -1, GRLspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGRLspec_Actors(), this.getActor(), this.getActor_Grlspec(), "actors", null, 0, -1, GRLspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGRLspec_Links(), this.getElementLink(), this.getElementLink_Grlspec(), "links", null, 0, -1, GRLspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGRLspec_Groups(), this.getStrategiesGroup(), this.getStrategiesGroup_Grlspec(), "groups", null, 0, -1, GRLspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGRLspec_Strategies(), this.getEvaluationStrategy(), this.getEvaluationStrategy_Grlspec(), "strategies", null, 0, -1, GRLspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGRLspec_ContributionGroups(), this.getContributionContextGroup(), this.getContributionContextGroup_Grlspec(), "contributionGroups", null, 0, -1, GRLspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGRLspec_ContributionContexts(), this.getContributionContext(), this.getContributionContext_Grlspec(), "contributionContexts", null, 0, -1, GRLspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGRLspec_ImpactModel(), this.getImpactModel(), this.getImpactModel_Grlspec(), "impactModel", null, 0, 1, GRLspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGRLspec_KpiInformationElements(), theKpimodelPackage.getKPIInformationElement(), theKpimodelPackage.getKPIInformationElement_Grlspec(), "kpiInformationElements", null, 0, -1, GRLspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGRLspec_KpiModelLinks(), theKpimodelPackage.getKPIModelLink(), theKpimodelPackage.getKPIModelLink_Grlspec(), "kpiModelLinks", null, 0, -1, GRLspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGRLspec_IndicatorGroup(), theKpimodelPackage.getIndicatorGroup(), theKpimodelPackage.getIndicatorGroup_Grlspec(), "indicatorGroup", null, 0, -1, GRLspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGRLspec_KPIConversion(), theKpimodelPackage.getKPIConversion(), theKpimodelPackage.getKPIConversion_Grlspec(), "KPIConversion", null, 0, -1, GRLspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGRLspec_FeatureModel(), theFmPackage.getFeatureModel(), theFmPackage.getFeatureModel_Grlspec(), "featureModel", null, 0, 1, GRLspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(beliefEClass, Belief.class, "Belief", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBelief_Author(), ecorePackage.getEString(), "author", null, 0, 1, Belief.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(intentionalElementEClass, IntentionalElement.class, "IntentionalElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntentionalElement_Type(), this.getIntentionalElementType(), "type", null, 0, 1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntentionalElement_DecompositionType(), this.getDecompositionType(), "decompositionType", "And", 0, 1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntentionalElement_Importance(), this.getImportanceType(), "importance", "None", 0, 1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntentionalElement_ImportanceQuantitative(), ecorePackage.getEInt(), "importanceQuantitative", "0", 0, 1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntentionalElement_LineColor(), ecorePackage.getEString(), "lineColor", null, 0, 1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntentionalElement_FillColor(), ecorePackage.getEString(), "fillColor", null, 0, 1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntentionalElement_Filled(), ecorePackage.getEBoolean(), "filled", "false", 0, 1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIntentionalElement_Grlspec(), this.getGRLspec(), this.getGRLspec_IntElements(), "grlspec", null, 1, 1, IntentionalElement.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIntentionalElement_Refs(), this.getIntentionalElementRef(), this.getIntentionalElementRef_Def(), "refs", null, 0, -1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actorEClass, Actor.class, "Actor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActor_Importance(), this.getImportanceType(), "importance", "None", 0, 1, Actor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getActor_ImportanceQuantitative(), ecorePackage.getEInt(), "importanceQuantitative", "0", 0, 1, Actor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActor_Grlspec(), this.getGRLspec(), this.getGRLspec_Actors(), "grlspec", null, 1, 1, Actor.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActor_IncludedActors(), this.getActor(), this.getActor_IncludingActor(), "includedActors", null, 0, -1, Actor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActor_IncludingActor(), this.getActor(), this.getActor_IncludedActors(), "includingActor", null, 0, 1, Actor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getActor_CollapsedRefs(), this.getCollapsedActorRef(), this.getCollapsedActorRef_Actor(), "collapsedRefs", null, 0, -1, Actor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(grlGraphEClass, GRLGraph.class, "GRLGraph", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(actorRefEClass, ActorRef.class, "ActorRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(intentionalElementRefEClass, IntentionalElementRef.class, "IntentionalElementRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntentionalElementRef_Criticality(), this.getCriticality(), "criticality", "None", 0, 1, IntentionalElementRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIntentionalElementRef_Priority(), this.getPriority(), "priority", "None", 0, 1, IntentionalElementRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIntentionalElementRef_Def(), this.getIntentionalElement(), this.getIntentionalElement_Refs(), "def", null, 1, 1, IntentionalElementRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(contributionEClass, Contribution.class, "Contribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getContribution_Contribution(), this.getContributionType(), "contribution", "Help", 0, 1, Contribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContribution_QuantitativeContribution(), ecorePackage.getEInt(), "quantitativeContribution", "25", 0, 1, Contribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContribution_Correlation(), ecorePackage.getEBoolean(), "correlation", "false", 0, 1, Contribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(linkRefEClass, LinkRef.class, "LinkRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLinkRef_Link(), this.getElementLink(), this.getElementLink_Refs(), "link", null, 1, 1, LinkRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLinkRef_Bendpoints(), this.getLinkRefBendpoint(), this.getLinkRefBendpoint_Linkref(), "bendpoints", null, 0, -1, LinkRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(elementLinkEClass, ElementLink.class, "ElementLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getElementLink_Refs(), this.getLinkRef(), this.getLinkRef_Link(), "refs", null, 0, -1, ElementLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElementLink_Grlspec(), this.getGRLspec(), this.getGRLspec_Links(), "grlspec", null, 1, 1, ElementLink.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElementLink_Dest(), this.getGRLLinkableElement(), this.getGRLLinkableElement_LinksDest(), "dest", null, 1, 1, ElementLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getElementLink_Src(), this.getGRLLinkableElement(), this.getGRLLinkableElement_LinksSrc(), "src", null, 1, 1, ElementLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(decompositionEClass, Decomposition.class, "Decomposition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dependencyEClass, Dependency.class, "Dependency", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(evaluationEClass, Evaluation.class, "Evaluation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEvaluation_Evaluation(), ecorePackage.getEInt(), "evaluation", "0", 0, 1, Evaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEvaluation_QualitativeEvaluation(), this.getQualitativeLabel(), "qualitativeEvaluation", "None", 0, 1, Evaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEvaluation_Exceeds(), ecorePackage.getEBoolean(), "exceeds", "false", 0, 1, Evaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEvaluation_IntElement(), this.getIntentionalElement(), null, "intElement", null, 1, 1, Evaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEvaluation_Strategies(), this.getEvaluationStrategy(), this.getEvaluationStrategy_Evaluations(), "strategies", null, 1, 1, Evaluation.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEvaluation_KpiEvalValueSet(), theKpimodelPackage.getKPIEvalValueSet(), theKpimodelPackage.getKPIEvalValueSet_Eval(), "kpiEvalValueSet", null, 0, 1, Evaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEvaluation_EvalRange(), this.getEvaluationRange(), this.getEvaluationRange_Eval(), "evalRange", null, 0, 1, Evaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEvaluation_KpiNewEvalValue(), theKpimodelPackage.getKPINewEvalValue(), theKpimodelPackage.getKPINewEvalValue_Eval(), "kpiNewEvalValue", null, 0, 1, Evaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(evaluationStrategyEClass, EvaluationStrategy.class, "EvaluationStrategy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEvaluationStrategy_Author(), ecorePackage.getEString(), "author", null, 0, 1, EvaluationStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEvaluationStrategy_Evaluations(), this.getEvaluation(), this.getEvaluation_Strategies(), "evaluations", null, 0, -1, EvaluationStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEvaluationStrategy_Group(), this.getStrategiesGroup(), this.getStrategiesGroup_Strategies(), "group", null, 1, 1, EvaluationStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEvaluationStrategy_Grlspec(), this.getGRLspec(), this.getGRLspec_Strategies(), "grlspec", null, 1, 1, EvaluationStrategy.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEvaluationStrategy_IncludedStrategies(), this.getEvaluationStrategy(), this.getEvaluationStrategy_ParentStrategies(), "includedStrategies", null, 0, -1, EvaluationStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEvaluationStrategy_ParentStrategies(), this.getEvaluationStrategy(), this.getEvaluationStrategy_IncludedStrategies(), "parentStrategies", null, 0, -1, EvaluationStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEvaluationStrategy_KpiInfoConfig(), theKpimodelPackage.getKPIInformationConfig(), theKpimodelPackage.getKPIInformationConfig_Strategies(), "kpiInfoConfig", null, 0, -1, EvaluationStrategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(grlNodeEClass, GRLNode.class, "GRLNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(linkRefBendpointEClass, LinkRefBendpoint.class, "LinkRefBendpoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLinkRefBendpoint_X(), ecorePackage.getEInt(), "x", null, 0, 1, LinkRefBendpoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLinkRefBendpoint_Y(), ecorePackage.getEInt(), "y", null, 0, 1, LinkRefBendpoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLinkRefBendpoint_Linkref(), this.getLinkRef(), this.getLinkRef_Bendpoints(), "linkref", null, 1, 1, LinkRefBendpoint.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(beliefLinkEClass, BeliefLink.class, "BeliefLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(strategiesGroupEClass, StrategiesGroup.class, "StrategiesGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStrategiesGroup_Strategies(), this.getEvaluationStrategy(), this.getEvaluationStrategy_Group(), "strategies", null, 0, -1, StrategiesGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStrategiesGroup_Grlspec(), this.getGRLspec(), this.getGRLspec_Groups(), "grlspec", null, 1, 1, StrategiesGroup.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(contributionContextGroupEClass, ContributionContextGroup.class, "ContributionContextGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContributionContextGroup_Grlspec(), this.getGRLspec(), this.getGRLspec_ContributionGroups(), "grlspec", null, 1, 1, ContributionContextGroup.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContributionContextGroup_Contribs(), this.getContributionContext(), this.getContributionContext_Groups(), "contribs", null, 0, -1, ContributionContextGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(contributionContextEClass, ContributionContext.class, "ContributionContext", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContributionContext_Grlspec(), this.getGRLspec(), this.getGRLspec_ContributionContexts(), "grlspec", null, 1, 1, ContributionContext.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContributionContext_Groups(), this.getContributionContextGroup(), this.getContributionContextGroup_Contribs(), "groups", null, 0, -1, ContributionContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContributionContext_Changes(), this.getContributionChange(), this.getContributionChange_Context(), "changes", null, 0, -1, ContributionContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContributionContext_ParentContexts(), this.getContributionContext(), this.getContributionContext_IncludedContexts(), "parentContexts", null, 0, -1, ContributionContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContributionContext_IncludedContexts(), this.getContributionContext(), this.getContributionContext_ParentContexts(), "includedContexts", null, 0, -1, ContributionContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(contributionChangeEClass, ContributionChange.class, "ContributionChange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getContributionChange_NewContribution(), this.getContributionType(), "newContribution", "Unknown", 0, 1, ContributionChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContributionChange_NewQuantitativeContribution(), ecorePackage.getEInt(), "newQuantitativeContribution", "0", 0, 1, ContributionChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContributionChange_Context(), this.getContributionContext(), this.getContributionContext_Changes(), "context", null, 1, 1, ContributionChange.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContributionChange_Contribution(), this.getContribution(), null, "contribution", null, 1, 1, ContributionChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContributionChange_ContribRange(), this.getContributionRange(), this.getContributionRange_Change(), "contribRange", null, 0, 1, ContributionChange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(grlLinkableElementEClass, GRLLinkableElement.class, "GRLLinkableElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGRLLinkableElement_LinksDest(), this.getElementLink(), this.getElementLink_Dest(), "linksDest", null, 0, -1, GRLLinkableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGRLLinkableElement_LinksSrc(), this.getElementLink(), this.getElementLink_Src(), "linksSrc", null, 0, -1, GRLLinkableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(collapsedActorRefEClass, CollapsedActorRef.class, "CollapsedActorRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCollapsedActorRef_Actor(), this.getActor(), this.getActor_CollapsedRefs(), "actor", null, 1, 1, CollapsedActorRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(evaluationRangeEClass, EvaluationRange.class, "EvaluationRange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEvaluationRange_Start(), ecorePackage.getEInt(), "start", null, 0, 1, EvaluationRange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEvaluationRange_End(), ecorePackage.getEInt(), "end", null, 0, 1, EvaluationRange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getEvaluationRange_Step(), ecorePackage.getEInt(), "step", "1", 0, 1, EvaluationRange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEvaluationRange_Eval(), this.getEvaluation(), this.getEvaluation_EvalRange(), "eval", null, 1, 1, EvaluationRange.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(contributionRangeEClass, ContributionRange.class, "ContributionRange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getContributionRange_Start(), ecorePackage.getEInt(), "start", null, 0, 1, ContributionRange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContributionRange_End(), ecorePackage.getEInt(), "end", null, 0, 1, ContributionRange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContributionRange_Step(), ecorePackage.getEInt(), "step", "1", 0, 1, ContributionRange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContributionRange_Change(), this.getContributionChange(), this.getContributionChange_ContribRange(), "change", null, 1, 1, ContributionRange.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(impactModelEClass, ImpactModel.class, "ImpactModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getImpactModel_Grlspec(), this.getGRLspec(), this.getGRLspec_ImpactModel(), "grlspec", null, 1, 1, ImpactModel.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(criticalityEEnum, Criticality.class, "Criticality");
		addEEnumLiteral(criticalityEEnum, Criticality.HIGH_LITERAL);
		addEEnumLiteral(criticalityEEnum, Criticality.MEDIUM_LITERAL);
		addEEnumLiteral(criticalityEEnum, Criticality.LOW_LITERAL);
		addEEnumLiteral(criticalityEEnum, Criticality.NONE_LITERAL);

		initEEnum(intentionalElementTypeEEnum, IntentionalElementType.class, "IntentionalElementType");
		addEEnumLiteral(intentionalElementTypeEEnum, IntentionalElementType.SOFTGOAL_LITERAL);
		addEEnumLiteral(intentionalElementTypeEEnum, IntentionalElementType.GOAL_LITERAL);
		addEEnumLiteral(intentionalElementTypeEEnum, IntentionalElementType.TASK_LITERAL);
		addEEnumLiteral(intentionalElementTypeEEnum, IntentionalElementType.RESSOURCE_LITERAL);
		addEEnumLiteral(intentionalElementTypeEEnum, IntentionalElementType.INDICATOR_LITERAL);

		initEEnum(priorityEEnum, Priority.class, "Priority");
		addEEnumLiteral(priorityEEnum, Priority.HIGH_LITERAL);
		addEEnumLiteral(priorityEEnum, Priority.MEDIUM_LITERAL);
		addEEnumLiteral(priorityEEnum, Priority.LOW_LITERAL);
		addEEnumLiteral(priorityEEnum, Priority.NONE_LITERAL);

		initEEnum(contributionTypeEEnum, ContributionType.class, "ContributionType");
		addEEnumLiteral(contributionTypeEEnum, ContributionType.MAKE_LITERAL);
		addEEnumLiteral(contributionTypeEEnum, ContributionType.HELP_LITERAL);
		addEEnumLiteral(contributionTypeEEnum, ContributionType.SOME_POSITIVE_LITERAL);
		addEEnumLiteral(contributionTypeEEnum, ContributionType.UNKNOWN_LITERAL);
		addEEnumLiteral(contributionTypeEEnum, ContributionType.SOME_NEGATIVE_LITERAL);
		addEEnumLiteral(contributionTypeEEnum, ContributionType.HURT_LITERAL);
		addEEnumLiteral(contributionTypeEEnum, ContributionType.BREAK_LITERAL);

		initEEnum(decompositionTypeEEnum, DecompositionType.class, "DecompositionType");
		addEEnumLiteral(decompositionTypeEEnum, DecompositionType.AND_LITERAL);
		addEEnumLiteral(decompositionTypeEEnum, DecompositionType.OR_LITERAL);
		addEEnumLiteral(decompositionTypeEEnum, DecompositionType.XOR_LITERAL);

		initEEnum(qualitativeLabelEEnum, QualitativeLabel.class, "QualitativeLabel");
		addEEnumLiteral(qualitativeLabelEEnum, QualitativeLabel.DENIED_LITERAL);
		addEEnumLiteral(qualitativeLabelEEnum, QualitativeLabel.WEAKLY_DENIED_LITERAL);
		addEEnumLiteral(qualitativeLabelEEnum, QualitativeLabel.WEAKLY_SATISFIED_LITERAL);
		addEEnumLiteral(qualitativeLabelEEnum, QualitativeLabel.SATISFIED_LITERAL);
		addEEnumLiteral(qualitativeLabelEEnum, QualitativeLabel.CONFLICT_LITERAL);
		addEEnumLiteral(qualitativeLabelEEnum, QualitativeLabel.UNKNOWN_LITERAL);
		addEEnumLiteral(qualitativeLabelEEnum, QualitativeLabel.NONE_LITERAL);

		initEEnum(importanceTypeEEnum, ImportanceType.class, "ImportanceType");
		addEEnumLiteral(importanceTypeEEnum, ImportanceType.HIGH_LITERAL);
		addEEnumLiteral(importanceTypeEEnum, ImportanceType.MEDIUM_LITERAL);
		addEEnumLiteral(importanceTypeEEnum, ImportanceType.LOW_LITERAL);
		addEEnumLiteral(importanceTypeEEnum, ImportanceType.NONE_LITERAL);

		// Create resource
		createResource(eNS_URI);
	}

} //GrlPackageImpl
