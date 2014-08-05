/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see grl.GrlPackage
 * @generated
 */
public interface GrlFactory extends EFactory {
    /**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    GrlFactory eINSTANCE = grl.impl.GrlFactoryImpl.init();

    /**
	 * Returns a new object of class '<em>GR Lspec</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>GR Lspec</em>'.
	 * @generated
	 */
    GRLspec createGRLspec();

    /**
	 * Returns a new object of class '<em>Belief</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Belief</em>'.
	 * @generated
	 */
    Belief createBelief();

    /**
	 * Returns a new object of class '<em>Intentional Element</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Intentional Element</em>'.
	 * @generated
	 */
    IntentionalElement createIntentionalElement();

    /**
	 * Returns a new object of class '<em>Actor</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Actor</em>'.
	 * @generated
	 */
    Actor createActor();

    /**
	 * Returns a new object of class '<em>GRL Graph</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>GRL Graph</em>'.
	 * @generated
	 */
    GRLGraph createGRLGraph();

    /**
	 * Returns a new object of class '<em>Actor Ref</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Actor Ref</em>'.
	 * @generated
	 */
    ActorRef createActorRef();

    /**
	 * Returns a new object of class '<em>Intentional Element Ref</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Intentional Element Ref</em>'.
	 * @generated
	 */
    IntentionalElementRef createIntentionalElementRef();

    /**
	 * Returns a new object of class '<em>Contribution</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Contribution</em>'.
	 * @generated
	 */
    Contribution createContribution();

    /**
	 * Returns a new object of class '<em>Link Ref</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Link Ref</em>'.
	 * @generated
	 */
    LinkRef createLinkRef();

    /**
	 * Returns a new object of class '<em>Element Link</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Element Link</em>'.
	 * @generated
	 */
    ElementLink createElementLink();

    /**
	 * Returns a new object of class '<em>Decomposition</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Decomposition</em>'.
	 * @generated
	 */
    Decomposition createDecomposition();

    /**
	 * Returns a new object of class '<em>Dependency</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dependency</em>'.
	 * @generated
	 */
    Dependency createDependency();

    /**
	 * Returns a new object of class '<em>Evaluation</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Evaluation</em>'.
	 * @generated
	 */
    Evaluation createEvaluation();

    /**
	 * Returns a new object of class '<em>Evaluation Strategy</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Evaluation Strategy</em>'.
	 * @generated
	 */
    EvaluationStrategy createEvaluationStrategy();

    /**
	 * Returns a new object of class '<em>GRL Node</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>GRL Node</em>'.
	 * @generated
	 */
    GRLNode createGRLNode();

    /**
	 * Returns a new object of class '<em>Link Ref Bendpoint</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Link Ref Bendpoint</em>'.
	 * @generated
	 */
    LinkRefBendpoint createLinkRefBendpoint();

    /**
	 * Returns a new object of class '<em>Belief Link</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Belief Link</em>'.
	 * @generated
	 */
    BeliefLink createBeliefLink();

    /**
	 * Returns a new object of class '<em>Strategies Group</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Strategies Group</em>'.
	 * @generated
	 */
    StrategiesGroup createStrategiesGroup();

    /**
	 * Returns a new object of class '<em>Contribution Context Group</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Contribution Context Group</em>'.
	 * @generated
	 */
	ContributionContextGroup createContributionContextGroup();

				/**
	 * Returns a new object of class '<em>Contribution Context</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Contribution Context</em>'.
	 * @generated
	 */
	ContributionContext createContributionContext();

				/**
	 * Returns a new object of class '<em>Contribution Change</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Contribution Change</em>'.
	 * @generated
	 */
	ContributionChange createContributionChange();

				/**
	 * Returns a new object of class '<em>Collapsed Actor Ref</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Collapsed Actor Ref</em>'.
	 * @generated
	 */
	CollapsedActorRef createCollapsedActorRef();

				/**
	 * Returns a new object of class '<em>Evaluation Range</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Evaluation Range</em>'.
	 * @generated
	 */
	EvaluationRange createEvaluationRange();

				/**
	 * Returns a new object of class '<em>Contribution Range</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Contribution Range</em>'.
	 * @generated
	 */
	ContributionRange createContributionRange();

				/**
	 * Returns a new object of class '<em>Impact Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Impact Model</em>'.
	 * @generated
	 */
	ImpactModel createImpactModel();

				/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
    GrlPackage getGrlPackage();

} //GrlFactory
