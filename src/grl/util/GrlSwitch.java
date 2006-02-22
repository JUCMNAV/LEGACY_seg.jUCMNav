/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.util;

import grl.Actor;
import grl.ActorRef;
import grl.Belief;
import grl.BeliefLink;
import grl.Contribution;
import grl.Decomposition;
import grl.Dependency;
import grl.ElementLink;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.GRLNode;
import grl.GRLspec;
import grl.GrlPackage;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.LinkRef;
import grl.LinkRefBendpoint;
import grl.StrategiesGroup;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import urncore.GRLmodelElement;
import urncore.IURNConnection;
import urncore.IURNContainer;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.URNmodelElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see grl.GrlPackage
 * @generated
 */
public class GrlSwitch {
    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static GrlPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GrlSwitch() {
        if (modelPackage == null) {
            modelPackage = GrlPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public Object doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected Object doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        }
        else {
            List eSuperTypes = theEClass.getESuperTypes();
            return
                eSuperTypes.isEmpty() ?
                    defaultCase(theEObject) :
                    doSwitch((EClass)eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected Object doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case GrlPackage.GR_LSPEC: {
                GRLspec grLspec = (GRLspec)theEObject;
                Object result = caseGRLspec(grLspec);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case GrlPackage.BELIEF: {
                Belief belief = (Belief)theEObject;
                Object result = caseBelief(belief);
                if (result == null) result = caseGRLNode(belief);
                if (result == null) result = caseGRLmodelElement(belief);
                if (result == null) result = caseIURNNode(belief);
                if (result == null) result = caseURNmodelElement(belief);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case GrlPackage.INTENTIONAL_ELEMENT: {
                IntentionalElement intentionalElement = (IntentionalElement)theEObject;
                Object result = caseIntentionalElement(intentionalElement);
                if (result == null) result = caseGRLmodelElement(intentionalElement);
                if (result == null) result = caseURNmodelElement(intentionalElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case GrlPackage.ACTOR: {
                Actor actor = (Actor)theEObject;
                Object result = caseActor(actor);
                if (result == null) result = caseGRLmodelElement(actor);
                if (result == null) result = caseIURNContainer(actor);
                if (result == null) result = caseURNmodelElement(actor);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case GrlPackage.GRL_GRAPH: {
                GRLGraph grlGraph = (GRLGraph)theEObject;
                Object result = caseGRLGraph(grlGraph);
                if (result == null) result = caseGRLmodelElement(grlGraph);
                if (result == null) result = caseIURNDiagram(grlGraph);
                if (result == null) result = caseURNmodelElement(grlGraph);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case GrlPackage.ACTOR_REF: {
                ActorRef actorRef = (ActorRef)theEObject;
                Object result = caseActorRef(actorRef);
                if (result == null) result = caseGRLmodelElement(actorRef);
                if (result == null) result = caseIURNContainerRef(actorRef);
                if (result == null) result = caseURNmodelElement(actorRef);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case GrlPackage.INTENTIONAL_ELEMENT_REF: {
                IntentionalElementRef intentionalElementRef = (IntentionalElementRef)theEObject;
                Object result = caseIntentionalElementRef(intentionalElementRef);
                if (result == null) result = caseGRLNode(intentionalElementRef);
                if (result == null) result = caseGRLmodelElement(intentionalElementRef);
                if (result == null) result = caseIURNNode(intentionalElementRef);
                if (result == null) result = caseURNmodelElement(intentionalElementRef);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case GrlPackage.CONTRIBUTION: {
                Contribution contribution = (Contribution)theEObject;
                Object result = caseContribution(contribution);
                if (result == null) result = caseElementLink(contribution);
                if (result == null) result = caseGRLmodelElement(contribution);
                if (result == null) result = caseURNmodelElement(contribution);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case GrlPackage.LINK_REF: {
                LinkRef linkRef = (LinkRef)theEObject;
                Object result = caseLinkRef(linkRef);
                if (result == null) result = caseIURNConnection(linkRef);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case GrlPackage.ELEMENT_LINK: {
                ElementLink elementLink = (ElementLink)theEObject;
                Object result = caseElementLink(elementLink);
                if (result == null) result = caseGRLmodelElement(elementLink);
                if (result == null) result = caseURNmodelElement(elementLink);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case GrlPackage.DECOMPOSITION: {
                Decomposition decomposition = (Decomposition)theEObject;
                Object result = caseDecomposition(decomposition);
                if (result == null) result = caseElementLink(decomposition);
                if (result == null) result = caseGRLmodelElement(decomposition);
                if (result == null) result = caseURNmodelElement(decomposition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case GrlPackage.DEPENDENCY: {
                Dependency dependency = (Dependency)theEObject;
                Object result = caseDependency(dependency);
                if (result == null) result = caseElementLink(dependency);
                if (result == null) result = caseGRLmodelElement(dependency);
                if (result == null) result = caseURNmodelElement(dependency);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case GrlPackage.EVALUATION: {
                Evaluation evaluation = (Evaluation)theEObject;
                Object result = caseEvaluation(evaluation);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case GrlPackage.EVALUATION_STRATEGY: {
                EvaluationStrategy evaluationStrategy = (EvaluationStrategy)theEObject;
                Object result = caseEvaluationStrategy(evaluationStrategy);
                if (result == null) result = caseGRLmodelElement(evaluationStrategy);
                if (result == null) result = caseURNmodelElement(evaluationStrategy);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case GrlPackage.GRL_NODE: {
                GRLNode grlNode = (GRLNode)theEObject;
                Object result = caseGRLNode(grlNode);
                if (result == null) result = caseGRLmodelElement(grlNode);
                if (result == null) result = caseIURNNode(grlNode);
                if (result == null) result = caseURNmodelElement(grlNode);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case GrlPackage.LINK_REF_BENDPOINT: {
                LinkRefBendpoint linkRefBendpoint = (LinkRefBendpoint)theEObject;
                Object result = caseLinkRefBendpoint(linkRefBendpoint);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case GrlPackage.BELIEF_LINK: {
                BeliefLink beliefLink = (BeliefLink)theEObject;
                Object result = caseBeliefLink(beliefLink);
                if (result == null) result = caseIURNConnection(beliefLink);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case GrlPackage.STRATEGIES_GROUP: {
                StrategiesGroup strategiesGroup = (StrategiesGroup)theEObject;
                Object result = caseStrategiesGroup(strategiesGroup);
                if (result == null) result = caseGRLmodelElement(strategiesGroup);
                if (result == null) result = caseURNmodelElement(strategiesGroup);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>GR Lspec</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>GR Lspec</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseGRLspec(GRLspec object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Belief</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Belief</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseBelief(Belief object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Intentional Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Intentional Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseIntentionalElement(IntentionalElement object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Actor</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Actor</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseActor(Actor object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>GRL Graph</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>GRL Graph</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseGRLGraph(GRLGraph object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Actor Ref</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Actor Ref</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseActorRef(ActorRef object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Intentional Element Ref</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Intentional Element Ref</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseIntentionalElementRef(IntentionalElementRef object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Contribution</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Contribution</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseContribution(Contribution object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Link Ref</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Link Ref</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseLinkRef(LinkRef object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Element Link</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Element Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseElementLink(ElementLink object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Decomposition</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Decomposition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDecomposition(Decomposition object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Dependency</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Dependency</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseDependency(Dependency object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Evaluation</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Evaluation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseEvaluation(Evaluation object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Evaluation Strategy</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Evaluation Strategy</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseEvaluationStrategy(EvaluationStrategy object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>GRL Node</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>GRL Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseGRLNode(GRLNode object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Link Ref Bendpoint</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Link Ref Bendpoint</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseLinkRefBendpoint(LinkRefBendpoint object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Belief Link</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Belief Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseBeliefLink(BeliefLink object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Strategies Group</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Strategies Group</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseStrategiesGroup(StrategiesGroup object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>UR Nmodel Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>UR Nmodel Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseURNmodelElement(URNmodelElement object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>GR Lmodel Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>GR Lmodel Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseGRLmodelElement(GRLmodelElement object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IURN Node</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IURN Node</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseIURNNode(IURNNode object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IURN Container</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IURN Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseIURNContainer(IURNContainer object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IURN Diagram</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IURN Diagram</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseIURNDiagram(IURNDiagram object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IURN Container Ref</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IURN Container Ref</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseIURNContainerRef(IURNContainerRef object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>IURN Connection</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>IURN Connection</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public Object caseIURNConnection(IURNConnection object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public Object defaultCase(EObject object) {
        return null;
    }

} //GrlSwitch
