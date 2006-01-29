/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GrlFactoryImpl extends EFactoryImpl implements GrlFactory {
    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GrlFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case GrlPackage.GR_LSPEC: return createGRLspec();
            case GrlPackage.BELIEF: return createBelief();
            case GrlPackage.INTENTIONAL_ELEMENT: return createIntentionalElement();
            case GrlPackage.ACTOR: return createActor();
            case GrlPackage.GRL_GRAPH: return createGRLGraph();
            case GrlPackage.ACTOR_REF: return createActorRef();
            case GrlPackage.INTENTIONAL_ELEMENT_REF: return createIntentionalElementRef();
            case GrlPackage.CONTRIBUTION: return createContribution();
            case GrlPackage.LINK_REF: return createLinkRef();
            case GrlPackage.ELEMENT_LINK: return createElementLink();
            case GrlPackage.DECOMPOSITION: return createDecomposition();
            case GrlPackage.DEPENDENCY: return createDependency();
            case GrlPackage.EVALUATION: return createEvaluation();
            case GrlPackage.EVALUATION_SCENARIO: return createEvaluationScenario();
            case GrlPackage.GRL_NODE: return createGRLNode();
            case GrlPackage.LINK_REF_BENDPOINT: return createLinkRefBendpoint();
            case GrlPackage.BELIEF_LINK: return createBeliefLink();
            case GrlPackage.EVALUATION_GROUP: return createEvaluationGroup();
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
            case GrlPackage.CRITICALITY: {
                Criticality result = Criticality.get(initialValue);
                if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
                return result;
            }
            case GrlPackage.INTENTIONAL_ELEMENT_TYPE: {
                IntentionalElementType result = IntentionalElementType.get(initialValue);
                if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
                return result;
            }
            case GrlPackage.PRIORITY: {
                Priority result = Priority.get(initialValue);
                if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
                return result;
            }
            case GrlPackage.CONTRIBUTION_TYPE: {
                ContributionType result = ContributionType.get(initialValue);
                if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
                return result;
            }
            case GrlPackage.DECOMPOSITION_TYPE: {
                DecompositionType result = DecompositionType.get(initialValue);
                if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
                return result;
            }
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
            case GrlPackage.CRITICALITY:
                return instanceValue == null ? null : instanceValue.toString();
            case GrlPackage.INTENTIONAL_ELEMENT_TYPE:
                return instanceValue == null ? null : instanceValue.toString();
            case GrlPackage.PRIORITY:
                return instanceValue == null ? null : instanceValue.toString();
            case GrlPackage.CONTRIBUTION_TYPE:
                return instanceValue == null ? null : instanceValue.toString();
            case GrlPackage.DECOMPOSITION_TYPE:
                return instanceValue == null ? null : instanceValue.toString();
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GRLspec createGRLspec() {
        GRLspecImpl grLspec = new GRLspecImpl();
        return grLspec;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Belief createBelief() {
        BeliefImpl belief = new BeliefImpl();
        return belief;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElement createIntentionalElement() {
        IntentionalElementImpl intentionalElement = new IntentionalElementImpl();
        return intentionalElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Actor createActor() {
        ActorImpl actor = new ActorImpl();
        return actor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GRLGraph createGRLGraph() {
        GRLGraphImpl grlGraph = new GRLGraphImpl();
        return grlGraph;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ActorRef createActorRef() {
        ActorRefImpl actorRef = new ActorRefImpl();
        return actorRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElementRef createIntentionalElementRef() {
        IntentionalElementRefImpl intentionalElementRef = new IntentionalElementRefImpl();
        return intentionalElementRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Contribution createContribution() {
        ContributionImpl contribution = new ContributionImpl();
        return contribution;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LinkRef createLinkRef() {
        LinkRefImpl linkRef = new LinkRefImpl();
        return linkRef;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ElementLink createElementLink() {
        ElementLinkImpl elementLink = new ElementLinkImpl();
        return elementLink;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Decomposition createDecomposition() {
        DecompositionImpl decomposition = new DecompositionImpl();
        return decomposition;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Dependency createDependency() {
        DependencyImpl dependency = new DependencyImpl();
        return dependency;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Evaluation createEvaluation() {
        EvaluationImpl evaluation = new EvaluationImpl();
        return evaluation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EvaluationScenario createEvaluationScenario() {
        EvaluationScenarioImpl evaluationScenario = new EvaluationScenarioImpl();
        return evaluationScenario;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GRLNode createGRLNode() {
        GRLNodeImpl grlNode = new GRLNodeImpl();
        return grlNode;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public LinkRefBendpoint createLinkRefBendpoint() {
        LinkRefBendpointImpl linkRefBendpoint = new LinkRefBendpointImpl();
        return linkRefBendpoint;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BeliefLink createBeliefLink() {
        BeliefLinkImpl beliefLink = new BeliefLinkImpl();
        return beliefLink;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EvaluationGroup createEvaluationGroup() {
        EvaluationGroupImpl evaluationGroup = new EvaluationGroupImpl();
        return evaluationGroup;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GrlPackage getGrlPackage() {
        return (GrlPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    public static GrlPackage getPackage() {
        return GrlPackage.eINSTANCE;
    }

} //GrlFactoryImpl
