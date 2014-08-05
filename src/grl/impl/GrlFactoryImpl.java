/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.*;
import grl.Actor;
import grl.ActorRef;
import grl.Belief;
import grl.BeliefLink;
import grl.CollapsedActorRef;
import grl.Contribution;
import grl.ContributionChange;
import grl.ContributionContext;
import grl.ContributionContextGroup;
import grl.ContributionType;
import grl.Criticality;
import grl.Decomposition;
import grl.DecompositionType;
import grl.Dependency;
import grl.ElementLink;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.GRLNode;
import grl.GRLspec;
import grl.GrlFactory;
import grl.GrlPackage;
import grl.ImportanceType;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.IntentionalElementType;
import grl.LinkRef;
import grl.LinkRefBendpoint;
import grl.Priority;
import grl.QualitativeLabel;
import grl.StrategiesGroup;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GrlFactoryImpl extends EFactoryImpl implements GrlFactory {
    /**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static GrlFactory init() {
		try {
			GrlFactory theGrlFactory = (GrlFactory)EPackage.Registry.INSTANCE.getEFactory(GrlPackage.eNS_URI);
			if (theGrlFactory != null) {
				return theGrlFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new GrlFactoryImpl();
	}

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
			case GrlPackage.EVALUATION_STRATEGY: return createEvaluationStrategy();
			case GrlPackage.GRL_NODE: return createGRLNode();
			case GrlPackage.LINK_REF_BENDPOINT: return createLinkRefBendpoint();
			case GrlPackage.BELIEF_LINK: return createBeliefLink();
			case GrlPackage.STRATEGIES_GROUP: return createStrategiesGroup();
			case GrlPackage.CONTRIBUTION_CONTEXT_GROUP: return createContributionContextGroup();
			case GrlPackage.CONTRIBUTION_CONTEXT: return createContributionContext();
			case GrlPackage.CONTRIBUTION_CHANGE: return createContributionChange();
			case GrlPackage.COLLAPSED_ACTOR_REF: return createCollapsedActorRef();
			case GrlPackage.EVALUATION_RANGE: return createEvaluationRange();
			case GrlPackage.CONTRIBUTION_RANGE: return createContributionRange();
			case GrlPackage.IMPACT_MODEL: return createImpactModel();
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
			case GrlPackage.CRITICALITY:
				return createCriticalityFromString(eDataType, initialValue);
			case GrlPackage.INTENTIONAL_ELEMENT_TYPE:
				return createIntentionalElementTypeFromString(eDataType, initialValue);
			case GrlPackage.PRIORITY:
				return createPriorityFromString(eDataType, initialValue);
			case GrlPackage.CONTRIBUTION_TYPE:
				return createContributionTypeFromString(eDataType, initialValue);
			case GrlPackage.DECOMPOSITION_TYPE:
				return createDecompositionTypeFromString(eDataType, initialValue);
			case GrlPackage.QUALITATIVE_LABEL:
				return createQualitativeLabelFromString(eDataType, initialValue);
			case GrlPackage.IMPORTANCE_TYPE:
				return createImportanceTypeFromString(eDataType, initialValue);
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
				return convertCriticalityToString(eDataType, instanceValue);
			case GrlPackage.INTENTIONAL_ELEMENT_TYPE:
				return convertIntentionalElementTypeToString(eDataType, instanceValue);
			case GrlPackage.PRIORITY:
				return convertPriorityToString(eDataType, instanceValue);
			case GrlPackage.CONTRIBUTION_TYPE:
				return convertContributionTypeToString(eDataType, instanceValue);
			case GrlPackage.DECOMPOSITION_TYPE:
				return convertDecompositionTypeToString(eDataType, instanceValue);
			case GrlPackage.QUALITATIVE_LABEL:
				return convertQualitativeLabelToString(eDataType, instanceValue);
			case GrlPackage.IMPORTANCE_TYPE:
				return convertImportanceTypeToString(eDataType, instanceValue);
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
    public EvaluationStrategy createEvaluationStrategy() {
		EvaluationStrategyImpl evaluationStrategy = new EvaluationStrategyImpl();
		return evaluationStrategy;
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
    public StrategiesGroup createStrategiesGroup() {
		StrategiesGroupImpl strategiesGroup = new StrategiesGroupImpl();
		return strategiesGroup;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContributionContextGroup createContributionContextGroup() {
		ContributionContextGroupImpl contributionContextGroup = new ContributionContextGroupImpl();
		return contributionContextGroup;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContributionContext createContributionContext() {
		ContributionContextImpl contributionContext = new ContributionContextImpl();
		return contributionContext;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContributionChange createContributionChange() {
		ContributionChangeImpl contributionChange = new ContributionChangeImpl();
		return contributionChange;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CollapsedActorRef createCollapsedActorRef() {
		CollapsedActorRefImpl collapsedActorRef = new CollapsedActorRefImpl();
		return collapsedActorRef;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EvaluationRange createEvaluationRange() {
		EvaluationRangeImpl evaluationRange = new EvaluationRangeImpl();
		return evaluationRange;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContributionRange createContributionRange() {
		ContributionRangeImpl contributionRange = new ContributionRangeImpl();
		return contributionRange;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImpactModel createImpactModel() {
		ImpactModelImpl impactModel = new ImpactModelImpl();
		return impactModel;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Criticality createCriticalityFromString(EDataType eDataType, String initialValue) {
		Criticality result = Criticality.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCriticalityToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IntentionalElementType createIntentionalElementTypeFromString(EDataType eDataType, String initialValue) {
		IntentionalElementType result = IntentionalElementType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIntentionalElementTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Priority createPriorityFromString(EDataType eDataType, String initialValue) {
		Priority result = Priority.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPriorityToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContributionType createContributionTypeFromString(EDataType eDataType, String initialValue) {
		ContributionType result = ContributionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertContributionTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecompositionType createDecompositionTypeFromString(EDataType eDataType, String initialValue) {
		DecompositionType result = DecompositionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDecompositionTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QualitativeLabel createQualitativeLabelFromString(EDataType eDataType, String initialValue) {
		QualitativeLabel result = QualitativeLabel.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertQualitativeLabelToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportanceType createImportanceTypeFromString(EDataType eDataType, String initialValue) {
		ImportanceType result = ImportanceType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertImportanceTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
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
