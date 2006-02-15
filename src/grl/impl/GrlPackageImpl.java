/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Actor;
import grl.ActorRef;
import grl.Belief;
import grl.BeliefLink;
import grl.Contribution;
import grl.ContributionType;
import grl.Criticality;
import grl.Decomposition;
import grl.DecompositionType;
import grl.Dependency;
import grl.ElementLink;
import grl.Evaluation;
import grl.EvaluationGroup;
import grl.EvaluationScenario;
import grl.GRLGraph;
import grl.GRLNode;
import grl.GRLspec;
import grl.GrlFactory;
import grl.GrlPackage;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.IntentionalElementType;
import grl.LinkRef;
import grl.LinkRefBendpoint;
import grl.Priority;

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
    private EClass evaluationScenarioEClass = null;

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
    private EClass evaluationGroupEClass = null;

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
    public static GrlPackage init() {
        if (isInited) return (GrlPackage)EPackage.Registry.INSTANCE.getEPackage(GrlPackage.eNS_URI);

        // Obtain or create and register package
        GrlPackageImpl theGrlPackage = (GrlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(eNS_URI) instanceof GrlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(eNS_URI) : new GrlPackageImpl());

        isInited = true;

        // Obtain or create and register interdependencies
        UrnPackageImpl theUrnPackage = (UrnPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI) instanceof UrnPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI) : UrnPackage.eINSTANCE);
        UrncorePackageImpl theUrncorePackage = (UrncorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI) instanceof UrncorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI) : UrncorePackage.eINSTANCE);
        UcmPackageImpl theUcmPackage = (UcmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI) instanceof UcmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI) : UcmPackage.eINSTANCE);
        PerformancePackageImpl thePerformancePackage = (PerformancePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI) instanceof PerformancePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI) : PerformancePackage.eINSTANCE);
        MapPackageImpl theMapPackage = (MapPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) instanceof MapPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) : MapPackage.eINSTANCE);
        ScenarioPackageImpl theScenarioPackage = (ScenarioPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) instanceof ScenarioPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) : ScenarioPackage.eINSTANCE);

        // Create package meta-data objects
        theGrlPackage.createPackageContents();
        theUrnPackage.createPackageContents();
        theUrncorePackage.createPackageContents();
        theUcmPackage.createPackageContents();
        thePerformancePackage.createPackageContents();
        theMapPackage.createPackageContents();
        theScenarioPackage.createPackageContents();

        // Initialize created meta-data
        theGrlPackage.initializePackageContents();
        theUrnPackage.initializePackageContents();
        theUrncorePackage.initializePackageContents();
        theUcmPackage.initializePackageContents();
        thePerformancePackage.initializePackageContents();
        theMapPackage.initializePackageContents();
        theScenarioPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theGrlPackage.freeze();

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
    public EReference getGRLspec_EvaluationGroups() {
        return (EReference)grLspecEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getGRLspec_Scenarios() {
        return (EReference)grLspecEClass.getEStructuralFeatures().get(5);
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
    public EAttribute getIntentionalElement_Criticality() {
        return (EAttribute)intentionalElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIntentionalElement_Priority() {
        return (EAttribute)intentionalElementEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getIntentionalElement_DecompositionType() {
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
    public EReference getIntentionalElement_Evals() {
        return (EReference)intentionalElementEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIntentionalElement_LinksSrc() {
        return (EReference)intentionalElementEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIntentionalElement_LinksDest() {
        return (EReference)intentionalElementEClass.getEStructuralFeatures().get(11);
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
    public EReference getActor_Grlspec() {
        return (EReference)actorEClass.getEStructuralFeatures().get(0);
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
    public EReference getIntentionalElementRef_Def() {
        return (EReference)intentionalElementRefEClass.getEStructuralFeatures().get(0);
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
    public EAttribute getContribution_Correlation() {
        return (EAttribute)contributionEClass.getEStructuralFeatures().get(1);
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
        return (EReference)elementLinkEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getElementLink_Dest() {
        return (EReference)elementLinkEClass.getEStructuralFeatures().get(3);
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
    public EReference getEvaluation_IntElement() {
        return (EReference)evaluationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEvaluation_Scenario() {
        return (EReference)evaluationEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEvaluationScenario() {
        return evaluationScenarioEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getEvaluationScenario_Author() {
        return (EAttribute)evaluationScenarioEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEvaluationScenario_Evaluations() {
        return (EReference)evaluationScenarioEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEvaluationScenario_Group() {
        return (EReference)evaluationScenarioEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEvaluationScenario_Grlspec() {
        return (EReference)evaluationScenarioEClass.getEStructuralFeatures().get(3);
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
    public EClass getEvaluationGroup() {
        return evaluationGroupEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEvaluationGroup_Scenarios() {
        return (EReference)evaluationGroupEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEvaluationGroup_Grlspec() {
        return (EReference)evaluationGroupEClass.getEStructuralFeatures().get(1);
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
        createEReference(grLspecEClass, GR_LSPEC__EVALUATION_GROUPS);
        createEReference(grLspecEClass, GR_LSPEC__SCENARIOS);

        beliefEClass = createEClass(BELIEF);
        createEAttribute(beliefEClass, BELIEF__AUTHOR);

        intentionalElementEClass = createEClass(INTENTIONAL_ELEMENT);
        createEAttribute(intentionalElementEClass, INTENTIONAL_ELEMENT__TYPE);
        createEAttribute(intentionalElementEClass, INTENTIONAL_ELEMENT__CRITICALITY);
        createEAttribute(intentionalElementEClass, INTENTIONAL_ELEMENT__PRIORITY);
        createEAttribute(intentionalElementEClass, INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE);
        createEAttribute(intentionalElementEClass, INTENTIONAL_ELEMENT__LINE_COLOR);
        createEAttribute(intentionalElementEClass, INTENTIONAL_ELEMENT__FILL_COLOR);
        createEAttribute(intentionalElementEClass, INTENTIONAL_ELEMENT__FILLED);
        createEReference(intentionalElementEClass, INTENTIONAL_ELEMENT__GRLSPEC);
        createEReference(intentionalElementEClass, INTENTIONAL_ELEMENT__REFS);
        createEReference(intentionalElementEClass, INTENTIONAL_ELEMENT__EVALS);
        createEReference(intentionalElementEClass, INTENTIONAL_ELEMENT__LINKS_SRC);
        createEReference(intentionalElementEClass, INTENTIONAL_ELEMENT__LINKS_DEST);

        actorEClass = createEClass(ACTOR);
        createEReference(actorEClass, ACTOR__GRLSPEC);

        grlGraphEClass = createEClass(GRL_GRAPH);

        actorRefEClass = createEClass(ACTOR_REF);

        intentionalElementRefEClass = createEClass(INTENTIONAL_ELEMENT_REF);
        createEReference(intentionalElementRefEClass, INTENTIONAL_ELEMENT_REF__DEF);

        contributionEClass = createEClass(CONTRIBUTION);
        createEAttribute(contributionEClass, CONTRIBUTION__CONTRIBUTION);
        createEAttribute(contributionEClass, CONTRIBUTION__CORRELATION);

        linkRefEClass = createEClass(LINK_REF);
        createEReference(linkRefEClass, LINK_REF__LINK);
        createEReference(linkRefEClass, LINK_REF__BENDPOINTS);

        elementLinkEClass = createEClass(ELEMENT_LINK);
        createEReference(elementLinkEClass, ELEMENT_LINK__REFS);
        createEReference(elementLinkEClass, ELEMENT_LINK__GRLSPEC);
        createEReference(elementLinkEClass, ELEMENT_LINK__SRC);
        createEReference(elementLinkEClass, ELEMENT_LINK__DEST);

        decompositionEClass = createEClass(DECOMPOSITION);

        dependencyEClass = createEClass(DEPENDENCY);

        evaluationEClass = createEClass(EVALUATION);
        createEAttribute(evaluationEClass, EVALUATION__EVALUATION);
        createEReference(evaluationEClass, EVALUATION__INT_ELEMENT);
        createEReference(evaluationEClass, EVALUATION__SCENARIO);

        evaluationScenarioEClass = createEClass(EVALUATION_SCENARIO);
        createEAttribute(evaluationScenarioEClass, EVALUATION_SCENARIO__AUTHOR);
        createEReference(evaluationScenarioEClass, EVALUATION_SCENARIO__EVALUATIONS);
        createEReference(evaluationScenarioEClass, EVALUATION_SCENARIO__GROUP);
        createEReference(evaluationScenarioEClass, EVALUATION_SCENARIO__GRLSPEC);

        grlNodeEClass = createEClass(GRL_NODE);

        linkRefBendpointEClass = createEClass(LINK_REF_BENDPOINT);
        createEAttribute(linkRefBendpointEClass, LINK_REF_BENDPOINT__X);
        createEAttribute(linkRefBendpointEClass, LINK_REF_BENDPOINT__Y);
        createEReference(linkRefBendpointEClass, LINK_REF_BENDPOINT__LINKREF);

        beliefLinkEClass = createEClass(BELIEF_LINK);

        evaluationGroupEClass = createEClass(EVALUATION_GROUP);
        createEReference(evaluationGroupEClass, EVALUATION_GROUP__SCENARIOS);
        createEReference(evaluationGroupEClass, EVALUATION_GROUP__GRLSPEC);

        // Create enums
        criticalityEEnum = createEEnum(CRITICALITY);
        intentionalElementTypeEEnum = createEEnum(INTENTIONAL_ELEMENT_TYPE);
        priorityEEnum = createEEnum(PRIORITY);
        contributionTypeEEnum = createEEnum(CONTRIBUTION_TYPE);
        decompositionTypeEEnum = createEEnum(DECOMPOSITION_TYPE);
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
        UrnPackageImpl theUrnPackage = (UrnPackageImpl)EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI);
        UrncorePackageImpl theUrncorePackage = (UrncorePackageImpl)EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI);

        // Add supertypes to classes
        beliefEClass.getESuperTypes().add(this.getGRLNode());
        intentionalElementEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());
        actorEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());
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
        evaluationScenarioEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());
        grlNodeEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());
        grlNodeEClass.getESuperTypes().add(theUrncorePackage.getIURNNode());
        beliefLinkEClass.getESuperTypes().add(theUrncorePackage.getIURNConnection());
        evaluationGroupEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());

        // Initialize classes and features; add operations and parameters
        initEClass(grLspecEClass, GRLspec.class, "GRLspec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getGRLspec_Urnspec(), theUrnPackage.getURNspec(), theUrnPackage.getURNspec_Grlspec(), "urnspec", null, 1, 1, GRLspec.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGRLspec_IntElements(), this.getIntentionalElement(), this.getIntentionalElement_Grlspec(), "intElements", null, 0, -1, GRLspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGRLspec_Actors(), this.getActor(), this.getActor_Grlspec(), "actors", null, 0, -1, GRLspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGRLspec_Links(), this.getElementLink(), this.getElementLink_Grlspec(), "links", null, 0, -1, GRLspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGRLspec_EvaluationGroups(), this.getEvaluationGroup(), this.getEvaluationGroup_Grlspec(), "evaluationGroups", null, 0, -1, GRLspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGRLspec_Scenarios(), this.getEvaluationScenario(), this.getEvaluationScenario_Grlspec(), "scenarios", null, 0, -1, GRLspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(beliefEClass, Belief.class, "Belief", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBelief_Author(), ecorePackage.getEString(), "author", null, 0, 1, Belief.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(intentionalElementEClass, IntentionalElement.class, "IntentionalElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIntentionalElement_Type(), this.getIntentionalElementType(), "type", null, 0, 1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIntentionalElement_Criticality(), this.getCriticality(), "criticality", "Medium", 0, 1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIntentionalElement_Priority(), this.getPriority(), "priority", "Medium", 0, 1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIntentionalElement_DecompositionType(), this.getDecompositionType(), "decompositionType", "And", 0, 1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIntentionalElement_LineColor(), ecorePackage.getEString(), "lineColor", null, 0, 1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIntentionalElement_FillColor(), ecorePackage.getEString(), "fillColor", null, 0, 1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIntentionalElement_Filled(), ecorePackage.getEBoolean(), "filled", "false", 0, 1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIntentionalElement_Grlspec(), this.getGRLspec(), this.getGRLspec_IntElements(), "grlspec", null, 1, 1, IntentionalElement.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIntentionalElement_Refs(), this.getIntentionalElementRef(), this.getIntentionalElementRef_Def(), "refs", null, 0, -1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIntentionalElement_Evals(), this.getEvaluation(), this.getEvaluation_IntElement(), "evals", null, 0, -1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIntentionalElement_LinksSrc(), this.getElementLink(), this.getElementLink_Src(), "linksSrc", null, 0, -1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIntentionalElement_LinksDest(), this.getElementLink(), this.getElementLink_Dest(), "linksDest", null, 0, -1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(actorEClass, Actor.class, "Actor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getActor_Grlspec(), this.getGRLspec(), this.getGRLspec_Actors(), "grlspec", null, 1, 1, Actor.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(grlGraphEClass, GRLGraph.class, "GRLGraph", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(actorRefEClass, ActorRef.class, "ActorRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(intentionalElementRefEClass, IntentionalElementRef.class, "IntentionalElementRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getIntentionalElementRef_Def(), this.getIntentionalElement(), this.getIntentionalElement_Refs(), "def", null, 1, 1, IntentionalElementRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(contributionEClass, Contribution.class, "Contribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getContribution_Contribution(), this.getContributionType(), "contribution", "Unknown", 0, 1, Contribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getContribution_Correlation(), ecorePackage.getEBoolean(), "correlation", "false", 0, 1, Contribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(linkRefEClass, LinkRef.class, "LinkRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLinkRef_Link(), this.getElementLink(), this.getElementLink_Refs(), "link", null, 1, 1, LinkRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLinkRef_Bendpoints(), this.getLinkRefBendpoint(), this.getLinkRefBendpoint_Linkref(), "bendpoints", null, 0, -1, LinkRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(elementLinkEClass, ElementLink.class, "ElementLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getElementLink_Refs(), this.getLinkRef(), this.getLinkRef_Link(), "refs", null, 0, -1, ElementLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getElementLink_Grlspec(), this.getGRLspec(), this.getGRLspec_Links(), "grlspec", null, 1, 1, ElementLink.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getElementLink_Src(), this.getIntentionalElement(), this.getIntentionalElement_LinksSrc(), "src", null, 1, 1, ElementLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getElementLink_Dest(), this.getIntentionalElement(), this.getIntentionalElement_LinksDest(), "dest", null, 1, 1, ElementLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(decompositionEClass, Decomposition.class, "Decomposition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(dependencyEClass, Dependency.class, "Dependency", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(evaluationEClass, Evaluation.class, "Evaluation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getEvaluation_Evaluation(), ecorePackage.getEInt(), "evaluation", "0", 0, 1, Evaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEvaluation_IntElement(), this.getIntentionalElement(), this.getIntentionalElement_Evals(), "intElement", null, 1, 1, Evaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEvaluation_Scenario(), this.getEvaluationScenario(), this.getEvaluationScenario_Evaluations(), "scenario", null, 1, 1, Evaluation.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(evaluationScenarioEClass, EvaluationScenario.class, "EvaluationScenario", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getEvaluationScenario_Author(), ecorePackage.getEString(), "author", null, 0, 1, EvaluationScenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEvaluationScenario_Evaluations(), this.getEvaluation(), this.getEvaluation_Scenario(), "evaluations", null, 0, -1, EvaluationScenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEvaluationScenario_Group(), this.getEvaluationGroup(), this.getEvaluationGroup_Scenarios(), "group", null, 1, 1, EvaluationScenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEvaluationScenario_Grlspec(), this.getGRLspec(), this.getGRLspec_Scenarios(), "grlspec", null, 1, 1, EvaluationScenario.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(grlNodeEClass, GRLNode.class, "GRLNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(linkRefBendpointEClass, LinkRefBendpoint.class, "LinkRefBendpoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLinkRefBendpoint_X(), ecorePackage.getEInt(), "x", null, 0, 1, LinkRefBendpoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLinkRefBendpoint_Y(), ecorePackage.getEInt(), "y", null, 0, 1, LinkRefBendpoint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLinkRefBendpoint_Linkref(), this.getLinkRef(), this.getLinkRef_Bendpoints(), "linkref", null, 1, 1, LinkRefBendpoint.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(beliefLinkEClass, BeliefLink.class, "BeliefLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(evaluationGroupEClass, EvaluationGroup.class, "EvaluationGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEvaluationGroup_Scenarios(), this.getEvaluationScenario(), this.getEvaluationScenario_Group(), "scenarios", null, 0, -1, EvaluationGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEvaluationGroup_Grlspec(), this.getGRLspec(), this.getGRLspec_EvaluationGroups(), "grlspec", null, 1, 1, EvaluationGroup.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(criticalityEEnum, Criticality.class, "Criticality");
        addEEnumLiteral(criticalityEEnum, Criticality.HIGH_LITERAL);
        addEEnumLiteral(criticalityEEnum, Criticality.MEDIUM_LITERAL);
        addEEnumLiteral(criticalityEEnum, Criticality.LOW_LITERAL);

        initEEnum(intentionalElementTypeEEnum, IntentionalElementType.class, "IntentionalElementType");
        addEEnumLiteral(intentionalElementTypeEEnum, IntentionalElementType.SOFTGOAL_LITERAL);
        addEEnumLiteral(intentionalElementTypeEEnum, IntentionalElementType.GOAL_LITERAL);
        addEEnumLiteral(intentionalElementTypeEEnum, IntentionalElementType.TASK_LITERAL);
        addEEnumLiteral(intentionalElementTypeEEnum, IntentionalElementType.RESSOURCE_LITERAL);

        initEEnum(priorityEEnum, Priority.class, "Priority");
        addEEnumLiteral(priorityEEnum, Priority.HIGH_LITERAL);
        addEEnumLiteral(priorityEEnum, Priority.MEDIUM_LITERAL);
        addEEnumLiteral(priorityEEnum, Priority.LOW_LITERAL);

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

        // Create resource
        createResource(eNS_URI);
    }

} //GrlPackageImpl
