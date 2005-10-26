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
import grl.Contribution;
import grl.ContributionType;
import grl.Criticality;
import grl.Decomposition;
import grl.DecompositionType;
import grl.Dependency;
import grl.ElementLink;
import grl.Evaluation;
import grl.EvaluationLevel;
import grl.EvaluationSet;
import grl.GRLGraph;
import grl.GRLspec;
import grl.GrlFactory;
import grl.GrlPackage;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.IntentionalElementType;
import grl.LinkRef;
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
    private EClass evaluationSetEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum evaluationLevelEEnum = null;

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
        UrnPackageImpl theUrnPackage = (UrnPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI) instanceof UrnPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UrnPackage.eNS_URI) : UrnPackageImpl.eINSTANCE);
        UrncorePackageImpl theUrncorePackage = (UrncorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI) instanceof UrncorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UrncorePackage.eNS_URI) : UrncorePackageImpl.eINSTANCE);
        UcmPackageImpl theUcmPackage = (UcmPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI) instanceof UcmPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(UcmPackage.eNS_URI) : UcmPackageImpl.eINSTANCE);
        PerformancePackageImpl thePerformancePackage = (PerformancePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI) instanceof PerformancePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(PerformancePackage.eNS_URI) : PerformancePackageImpl.eINSTANCE);
        MapPackageImpl theMapPackage = (MapPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) instanceof MapPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MapPackage.eNS_URI) : MapPackageImpl.eINSTANCE);
        ScenarioPackageImpl theScenarioPackage = (ScenarioPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) instanceof ScenarioPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ScenarioPackage.eNS_URI) : ScenarioPackageImpl.eINSTANCE);

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
    public EClass getBelief() {
        return beliefEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getBelief_Connection() {
        return (EReference)beliefEClass.getEStructuralFeatures().get(0);
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
    public EReference getIntentionalElement_Grlspec() {
        return (EReference)intentionalElementEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIntentionalElement_Refs() {
        return (EReference)intentionalElementEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIntentionalElement_IsDepender() {
        return (EReference)intentionalElementEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIntentionalElement_DecompositionSrc() {
        return (EReference)intentionalElementEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIntentionalElement_DecompositionDest() {
        return (EReference)intentionalElementEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIntentionalElement_ContributionSrc() {
        return (EReference)intentionalElementEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIntentionalElement_ContributionDest() {
        return (EReference)intentionalElementEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIntentionalElement_IsDependum() {
        return (EReference)intentionalElementEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIntentionalElement_IsDependee() {
        return (EReference)intentionalElementEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getIntentionalElement_Evals() {
        return (EReference)intentionalElementEClass.getEStructuralFeatures().get(13);
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
    public EAttribute getContribution_Contibution() {
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
    public EReference getContribution_Src() {
        return (EReference)contributionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getContribution_Dest() {
        return (EReference)contributionEClass.getEStructuralFeatures().get(3);
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
    public EReference getLinkRef_Beliefs() {
        return (EReference)linkRefEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLinkRef_Link() {
        return (EReference)linkRefEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getLinkRef_Dependency() {
        return (EReference)linkRefEClass.getEStructuralFeatures().get(2);
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
    public EClass getDecomposition() {
        return decompositionEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDecomposition_Src() {
        return (EReference)decompositionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDecomposition_Dest() {
        return (EReference)decompositionEClass.getEStructuralFeatures().get(1);
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
    public EReference getDependency_Depender() {
        return (EReference)dependencyEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDependency_Dependum() {
        return (EReference)dependencyEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDependency_Dependee() {
        return (EReference)dependencyEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getDependency_SecondRefs() {
        return (EReference)dependencyEClass.getEStructuralFeatures().get(3);
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
    public EReference getEvaluation_Set() {
        return (EReference)evaluationEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getEvaluationSet() {
        return evaluationSetEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getEvaluationSet_Evaluations() {
        return (EReference)evaluationSetEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getEvaluationLevel() {
        return evaluationLevelEEnum;
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

        beliefEClass = createEClass(BELIEF);
        createEReference(beliefEClass, BELIEF__CONNECTION);

        intentionalElementEClass = createEClass(INTENTIONAL_ELEMENT);
        createEAttribute(intentionalElementEClass, INTENTIONAL_ELEMENT__TYPE);
        createEAttribute(intentionalElementEClass, INTENTIONAL_ELEMENT__CRITICALITY);
        createEAttribute(intentionalElementEClass, INTENTIONAL_ELEMENT__PRIORITY);
        createEAttribute(intentionalElementEClass, INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE);
        createEReference(intentionalElementEClass, INTENTIONAL_ELEMENT__GRLSPEC);
        createEReference(intentionalElementEClass, INTENTIONAL_ELEMENT__REFS);
        createEReference(intentionalElementEClass, INTENTIONAL_ELEMENT__IS_DEPENDER);
        createEReference(intentionalElementEClass, INTENTIONAL_ELEMENT__DECOMPOSITION_SRC);
        createEReference(intentionalElementEClass, INTENTIONAL_ELEMENT__DECOMPOSITION_DEST);
        createEReference(intentionalElementEClass, INTENTIONAL_ELEMENT__CONTRIBUTION_SRC);
        createEReference(intentionalElementEClass, INTENTIONAL_ELEMENT__CONTRIBUTION_DEST);
        createEReference(intentionalElementEClass, INTENTIONAL_ELEMENT__IS_DEPENDUM);
        createEReference(intentionalElementEClass, INTENTIONAL_ELEMENT__IS_DEPENDEE);
        createEReference(intentionalElementEClass, INTENTIONAL_ELEMENT__EVALS);

        actorEClass = createEClass(ACTOR);
        createEReference(actorEClass, ACTOR__GRLSPEC);

        grlGraphEClass = createEClass(GRL_GRAPH);

        actorRefEClass = createEClass(ACTOR_REF);

        intentionalElementRefEClass = createEClass(INTENTIONAL_ELEMENT_REF);
        createEReference(intentionalElementRefEClass, INTENTIONAL_ELEMENT_REF__DEF);

        contributionEClass = createEClass(CONTRIBUTION);
        createEAttribute(contributionEClass, CONTRIBUTION__CONTIBUTION);
        createEAttribute(contributionEClass, CONTRIBUTION__CORRELATION);
        createEReference(contributionEClass, CONTRIBUTION__SRC);
        createEReference(contributionEClass, CONTRIBUTION__DEST);

        linkRefEClass = createEClass(LINK_REF);
        createEReference(linkRefEClass, LINK_REF__BELIEFS);
        createEReference(linkRefEClass, LINK_REF__LINK);
        createEReference(linkRefEClass, LINK_REF__DEPENDENCY);

        elementLinkEClass = createEClass(ELEMENT_LINK);
        createEReference(elementLinkEClass, ELEMENT_LINK__REFS);

        decompositionEClass = createEClass(DECOMPOSITION);
        createEReference(decompositionEClass, DECOMPOSITION__SRC);
        createEReference(decompositionEClass, DECOMPOSITION__DEST);

        dependencyEClass = createEClass(DEPENDENCY);
        createEReference(dependencyEClass, DEPENDENCY__DEPENDER);
        createEReference(dependencyEClass, DEPENDENCY__DEPENDUM);
        createEReference(dependencyEClass, DEPENDENCY__DEPENDEE);
        createEReference(dependencyEClass, DEPENDENCY__SECOND_REFS);

        evaluationEClass = createEClass(EVALUATION);
        createEAttribute(evaluationEClass, EVALUATION__EVALUATION);
        createEReference(evaluationEClass, EVALUATION__INT_ELEMENT);
        createEReference(evaluationEClass, EVALUATION__SET);

        evaluationSetEClass = createEClass(EVALUATION_SET);
        createEReference(evaluationSetEClass, EVALUATION_SET__EVALUATIONS);

        // Create enums
        evaluationLevelEEnum = createEEnum(EVALUATION_LEVEL);
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
        beliefEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());
        beliefEClass.getESuperTypes().add(theUrncorePackage.getSpecificationNode());
        intentionalElementEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());
        actorEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());
        actorEClass.getESuperTypes().add(theUrncorePackage.getSpecificationComponent());
        grlGraphEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());
        grlGraphEClass.getESuperTypes().add(theUrncorePackage.getSpecificationDiagram());
        actorRefEClass.getESuperTypes().add(theUrncorePackage.getSpecificationComponentRef());
        intentionalElementRefEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());
        intentionalElementRefEClass.getESuperTypes().add(theUrncorePackage.getSpecificationNode());
        contributionEClass.getESuperTypes().add(this.getElementLink());
        linkRefEClass.getESuperTypes().add(theUrncorePackage.getSpecificationConnection());
        decompositionEClass.getESuperTypes().add(this.getElementLink());
        dependencyEClass.getESuperTypes().add(this.getElementLink());
        evaluationSetEClass.getESuperTypes().add(theUrncorePackage.getGRLmodelElement());

        // Initialize classes and features; add operations and parameters
        initEClass(grLspecEClass, GRLspec.class, "GRLspec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getGRLspec_Urnspec(), theUrnPackage.getURNspec(), theUrnPackage.getURNspec_Grlspec(), "urnspec", null, 1, 1, GRLspec.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGRLspec_IntElements(), this.getIntentionalElement(), this.getIntentionalElement_Grlspec(), "intElements", null, 0, -1, GRLspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGRLspec_Actors(), this.getActor(), this.getActor_Grlspec(), "actors", null, 0, -1, GRLspec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(beliefEClass, Belief.class, "Belief", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getBelief_Connection(), this.getLinkRef(), this.getLinkRef_Beliefs(), "connection", null, 1, 1, Belief.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(intentionalElementEClass, IntentionalElement.class, "IntentionalElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getIntentionalElement_Type(), this.getIntentionalElementType(), "type", null, 0, 1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIntentionalElement_Criticality(), this.getCriticality(), "criticality", null, 0, 1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIntentionalElement_Priority(), this.getPriority(), "priority", null, 0, 1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getIntentionalElement_DecompositionType(), this.getDecompositionType(), "decompositionType", "AND", 0, 1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIntentionalElement_Grlspec(), this.getGRLspec(), this.getGRLspec_IntElements(), "grlspec", null, 1, 1, IntentionalElement.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIntentionalElement_Refs(), this.getIntentionalElementRef(), this.getIntentionalElementRef_Def(), "refs", null, 1, -1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIntentionalElement_IsDepender(), this.getDependency(), this.getDependency_Depender(), "isDepender", null, 0, -1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIntentionalElement_DecompositionSrc(), this.getDecomposition(), this.getDecomposition_Src(), "decompositionSrc", null, 0, -1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIntentionalElement_DecompositionDest(), this.getDecomposition(), this.getDecomposition_Dest(), "decompositionDest", null, 0, -1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIntentionalElement_ContributionSrc(), this.getContribution(), this.getContribution_Src(), "contributionSrc", null, 0, -1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIntentionalElement_ContributionDest(), this.getContribution(), this.getContribution_Dest(), "contributionDest", null, 0, -1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIntentionalElement_IsDependum(), this.getDependency(), this.getDependency_Dependum(), "isDependum", null, 0, -1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIntentionalElement_IsDependee(), this.getDependency(), this.getDependency_Dependee(), "isDependee", null, 0, -1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getIntentionalElement_Evals(), this.getEvaluation(), this.getEvaluation_IntElement(), "evals", null, 0, -1, IntentionalElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(actorEClass, Actor.class, "Actor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getActor_Grlspec(), this.getGRLspec(), this.getGRLspec_Actors(), "grlspec", null, 1, 1, Actor.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(grlGraphEClass, GRLGraph.class, "GRLGraph", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(actorRefEClass, ActorRef.class, "ActorRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(intentionalElementRefEClass, IntentionalElementRef.class, "IntentionalElementRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getIntentionalElementRef_Def(), this.getIntentionalElement(), this.getIntentionalElement_Refs(), "def", null, 1, 1, IntentionalElementRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(contributionEClass, Contribution.class, "Contribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getContribution_Contibution(), this.getContributionType(), "contibution", null, 0, 1, Contribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getContribution_Correlation(), ecorePackage.getEBoolean(), "correlation", "false", 0, 1, Contribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getContribution_Src(), this.getIntentionalElement(), this.getIntentionalElement_ContributionSrc(), "src", null, 1, 1, Contribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getContribution_Dest(), this.getIntentionalElement(), this.getIntentionalElement_ContributionDest(), "dest", null, 1, 1, Contribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(linkRefEClass, LinkRef.class, "LinkRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLinkRef_Beliefs(), this.getBelief(), this.getBelief_Connection(), "beliefs", null, 0, -1, LinkRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLinkRef_Link(), this.getElementLink(), this.getElementLink_Refs(), "link", null, 1, 1, LinkRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLinkRef_Dependency(), this.getDependency(), this.getDependency_SecondRefs(), "dependency", null, 1, 1, LinkRef.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(elementLinkEClass, ElementLink.class, "ElementLink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getElementLink_Refs(), this.getLinkRef(), this.getLinkRef_Link(), "refs", null, 1, -1, ElementLink.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(decompositionEClass, Decomposition.class, "Decomposition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDecomposition_Src(), this.getIntentionalElement(), this.getIntentionalElement_DecompositionSrc(), "src", null, 1, 1, Decomposition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDecomposition_Dest(), this.getIntentionalElement(), this.getIntentionalElement_DecompositionDest(), "dest", null, 1, 1, Decomposition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dependencyEClass, Dependency.class, "Dependency", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDependency_Depender(), this.getIntentionalElement(), this.getIntentionalElement_IsDepender(), "depender", null, 1, 1, Dependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDependency_Dependum(), this.getIntentionalElement(), this.getIntentionalElement_IsDependum(), "dependum", null, 1, 1, Dependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDependency_Dependee(), this.getIntentionalElement(), this.getIntentionalElement_IsDependee(), "dependee", null, 1, 1, Dependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getDependency_SecondRefs(), this.getLinkRef(), this.getLinkRef_Dependency(), "secondRefs", null, 0, -1, Dependency.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(evaluationEClass, Evaluation.class, "Evaluation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getEvaluation_Evaluation(), this.getEvaluationLevel(), "evaluation", null, 0, 1, Evaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEvaluation_IntElement(), this.getIntentionalElement(), this.getIntentionalElement_Evals(), "intElement", null, 1, 1, Evaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEvaluation_Set(), this.getEvaluationSet(), this.getEvaluationSet_Evaluations(), "set", null, 1, 1, Evaluation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(evaluationSetEClass, EvaluationSet.class, "EvaluationSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEvaluationSet_Evaluations(), this.getEvaluation(), this.getEvaluation_Set(), "evaluations", null, 0, -1, EvaluationSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(evaluationLevelEEnum, EvaluationLevel.class, "EvaluationLevel");
        addEEnumLiteral(evaluationLevelEEnum, EvaluationLevel.SATISFICED_LITERAL);
        addEEnumLiteral(evaluationLevelEEnum, EvaluationLevel.WEAKLY_SATISFICED_LITERAL);
        addEEnumLiteral(evaluationLevelEEnum, EvaluationLevel.UNDECIDED_LITERAL);
        addEEnumLiteral(evaluationLevelEEnum, EvaluationLevel.WEAKLY_DENIED_LITERAL);
        addEEnumLiteral(evaluationLevelEEnum, EvaluationLevel.DENIED_LITERAL);
        addEEnumLiteral(evaluationLevelEEnum, EvaluationLevel.CONFLICT_LITERAL);

        initEEnum(criticalityEEnum, Criticality.class, "Criticality");
        addEEnumLiteral(criticalityEEnum, Criticality.HIGH_LITERAL);
        addEEnumLiteral(criticalityEEnum, Criticality.MEDIUM_LITERAL);
        addEEnumLiteral(criticalityEEnum, Criticality.LOW_LITERAL);

        initEEnum(intentionalElementTypeEEnum, IntentionalElementType.class, "IntentionalElementType");
        addEEnumLiteral(intentionalElementTypeEEnum, IntentionalElementType.GOAL_LITERAL);
        addEEnumLiteral(intentionalElementTypeEEnum, IntentionalElementType.SOFTGOAL_LITERAL);
        addEEnumLiteral(intentionalElementTypeEEnum, IntentionalElementType.TASK_LITERAL);
        addEEnumLiteral(intentionalElementTypeEEnum, IntentionalElementType.RESSOURCE_LITERAL);

        initEEnum(priorityEEnum, Priority.class, "Priority");
        addEEnumLiteral(priorityEEnum, Priority.HIGH_LITERAL);
        addEEnumLiteral(priorityEEnum, Priority.MEDIUM_LITERAL);
        addEEnumLiteral(priorityEEnum, Priority.LOW_LITERAL);

        initEEnum(contributionTypeEEnum, ContributionType.class, "ContributionType");
        addEEnumLiteral(contributionTypeEEnum, ContributionType.BREAK_LITERAL);
        addEEnumLiteral(contributionTypeEEnum, ContributionType.HURT_LITERAL);
        addEEnumLiteral(contributionTypeEEnum, ContributionType.SOME_NEGATIVE_LITERAL);
        addEEnumLiteral(contributionTypeEEnum, ContributionType.UNKNOWN_LITERAL);
        addEEnumLiteral(contributionTypeEEnum, ContributionType.EQUAL_LITERAL);
        addEEnumLiteral(contributionTypeEEnum, ContributionType.SOME_POSITIVE_LITERAL);
        addEEnumLiteral(contributionTypeEEnum, ContributionType.HELP_LITERAL);
        addEEnumLiteral(contributionTypeEEnum, ContributionType.MAKE_LITERAL);

        initEEnum(decompositionTypeEEnum, DecompositionType.class, "DecompositionType");
        addEEnumLiteral(decompositionTypeEEnum, DecompositionType.AND_LITERAL);
        addEEnumLiteral(decompositionTypeEEnum, DecompositionType.OR_LITERAL);

        // Create resource
        createResource(eNS_URI);
    }

} //GrlPackageImpl
