/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.util;

import grl.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import urncore.GRLmodelElement;
import urncore.SpecificationComponent;
import urncore.SpecificationComponentRef;
import urncore.SpecificationConnection;
import urncore.SpecificationDiagram;
import urncore.SpecificationNode;
import urncore.URNmodelElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see grl.GrlPackage
 * @generated
 */
public class GrlAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static GrlPackage modelPackage;

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GrlAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = GrlPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch the delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected GrlSwitch modelSwitch =
        new GrlSwitch() {
            public Object caseGRLspec(GRLspec object) {
                return createGRLspecAdapter();
            }
            public Object caseBelief(Belief object) {
                return createBeliefAdapter();
            }
            public Object caseIntentionalElement(IntentionalElement object) {
                return createIntentionalElementAdapter();
            }
            public Object caseActor(Actor object) {
                return createActorAdapter();
            }
            public Object caseGRLGraph(GRLGraph object) {
                return createGRLGraphAdapter();
            }
            public Object caseActorRef(ActorRef object) {
                return createActorRefAdapter();
            }
            public Object caseIntentionalElementRef(IntentionalElementRef object) {
                return createIntentionalElementRefAdapter();
            }
            public Object caseContribution(Contribution object) {
                return createContributionAdapter();
            }
            public Object caseLinkRef(LinkRef object) {
                return createLinkRefAdapter();
            }
            public Object caseElementLink(ElementLink object) {
                return createElementLinkAdapter();
            }
            public Object caseDecomposition(Decomposition object) {
                return createDecompositionAdapter();
            }
            public Object caseDependency(Dependency object) {
                return createDependencyAdapter();
            }
            public Object caseEvaluation(Evaluation object) {
                return createEvaluationAdapter();
            }
            public Object caseEvaluationSet(EvaluationSet object) {
                return createEvaluationSetAdapter();
            }
            public Object caseURNmodelElement(URNmodelElement object) {
                return createURNmodelElementAdapter();
            }
            public Object caseGRLmodelElement(GRLmodelElement object) {
                return createGRLmodelElementAdapter();
            }
            public Object caseSpecificationNode(SpecificationNode object) {
                return createSpecificationNodeAdapter();
            }
            public Object caseSpecificationComponent(SpecificationComponent object) {
                return createSpecificationComponentAdapter();
            }
            public Object caseSpecificationDiagram(SpecificationDiagram object) {
                return createSpecificationDiagramAdapter();
            }
            public Object caseSpecificationComponentRef(SpecificationComponentRef object) {
                return createSpecificationComponentRefAdapter();
            }
            public Object caseSpecificationConnection(SpecificationConnection object) {
                return createSpecificationConnectionAdapter();
            }
            public Object defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    public Adapter createAdapter(Notifier target) {
        return (Adapter)modelSwitch.doSwitch((EObject)target);
    }


    /**
     * Creates a new adapter for an object of class '{@link grl.GRLspec <em>GR Lspec</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see grl.GRLspec
     * @generated
     */
    public Adapter createGRLspecAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link grl.Belief <em>Belief</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see grl.Belief
     * @generated
     */
    public Adapter createBeliefAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link grl.IntentionalElement <em>Intentional Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see grl.IntentionalElement
     * @generated
     */
    public Adapter createIntentionalElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link grl.Actor <em>Actor</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see grl.Actor
     * @generated
     */
    public Adapter createActorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link grl.GRLGraph <em>GRL Graph</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see grl.GRLGraph
     * @generated
     */
    public Adapter createGRLGraphAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link grl.ActorRef <em>Actor Ref</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see grl.ActorRef
     * @generated
     */
    public Adapter createActorRefAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link grl.IntentionalElementRef <em>Intentional Element Ref</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see grl.IntentionalElementRef
     * @generated
     */
    public Adapter createIntentionalElementRefAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link grl.Contribution <em>Contribution</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see grl.Contribution
     * @generated
     */
    public Adapter createContributionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link grl.LinkRef <em>Link Ref</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see grl.LinkRef
     * @generated
     */
    public Adapter createLinkRefAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link grl.ElementLink <em>Element Link</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see grl.ElementLink
     * @generated
     */
    public Adapter createElementLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link grl.Decomposition <em>Decomposition</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see grl.Decomposition
     * @generated
     */
    public Adapter createDecompositionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link grl.Dependency <em>Dependency</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see grl.Dependency
     * @generated
     */
    public Adapter createDependencyAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link grl.Evaluation <em>Evaluation</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see grl.Evaluation
     * @generated
     */
    public Adapter createEvaluationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link grl.EvaluationSet <em>Evaluation Set</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see grl.EvaluationSet
     * @generated
     */
    public Adapter createEvaluationSetAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link urncore.URNmodelElement <em>UR Nmodel Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see urncore.URNmodelElement
     * @generated
     */
    public Adapter createURNmodelElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link urncore.GRLmodelElement <em>GR Lmodel Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see urncore.GRLmodelElement
     * @generated
     */
    public Adapter createGRLmodelElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link urncore.SpecificationNode <em>Specification Node</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see urncore.SpecificationNode
     * @generated
     */
    public Adapter createSpecificationNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link urncore.SpecificationComponent <em>Specification Component</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see urncore.SpecificationComponent
     * @generated
     */
    public Adapter createSpecificationComponentAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link urncore.SpecificationDiagram <em>Specification Diagram</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see urncore.SpecificationDiagram
     * @generated
     */
    public Adapter createSpecificationDiagramAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link urncore.SpecificationComponentRef <em>Specification Component Ref</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see urncore.SpecificationComponentRef
     * @generated
     */
    public Adapter createSpecificationComponentRefAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link urncore.SpecificationConnection <em>Specification Connection</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @see urncore.SpecificationConnection
     * @generated
     */
    public Adapter createSpecificationConnectionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} //GrlAdapterFactory
