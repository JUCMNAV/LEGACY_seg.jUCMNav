/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.util;

import ca.mcgill.sel.core.CORENamedElement;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import urncore.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see urncore.UrncorePackage
 * @generated
 */
public class UrncoreAdapterFactory extends AdapterFactoryImpl {
    /**
	 * The cached model package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected static UrncorePackage modelPackage;

    /**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public UrncoreAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = UrncorePackage.eINSTANCE;
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
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected UrncoreSwitch modelSwitch =
        new UrncoreSwitch() {
			public Object caseURNdefinition(URNdefinition object) {
				return createURNdefinitionAdapter();
			}
			public Object caseResponsibility(Responsibility object) {
				return createResponsibilityAdapter();
			}
			public Object caseComponent(Component object) {
				return createComponentAdapter();
			}
			public Object caseComponentType(ComponentType object) {
				return createComponentTypeAdapter();
			}
			public Object caseUCMmodelElement(UCMmodelElement object) {
				return createUCMmodelElementAdapter();
			}
			public Object caseGRLmodelElement(GRLmodelElement object) {
				return createGRLmodelElementAdapter();
			}
			public Object caseNodeLabel(NodeLabel object) {
				return createNodeLabelAdapter();
			}
			public Object caseLabel(Label object) {
				return createLabelAdapter();
			}
			public Object caseComponentLabel(ComponentLabel object) {
				return createComponentLabelAdapter();
			}
			public Object caseCondition(Condition object) {
				return createConditionAdapter();
			}
			public Object caseIURNDiagram(IURNDiagram object) {
				return createIURNDiagramAdapter();
			}
			public Object caseURNmodelElement(URNmodelElement object) {
				return createURNmodelElementAdapter();
			}
			public Object caseIURNNode(IURNNode object) {
				return createIURNNodeAdapter();
			}
			public Object caseIURNContainerRef(IURNContainerRef object) {
				return createIURNContainerRefAdapter();
			}
			public Object caseIURNContainer(IURNContainer object) {
				return createIURNContainerAdapter();
			}
			public Object caseIURNConnection(IURNConnection object) {
				return createIURNConnectionAdapter();
			}
			public Object caseMetadata(Metadata object) {
				return createMetadataAdapter();
			}
			public Object caseConcern(Concern object) {
				return createConcernAdapter();
			}
			public Object caseConnectionLabel(ConnectionLabel object) {
				return createConnectionLabelAdapter();
			}
			public Object caseComment(Comment object) {
				return createCommentAdapter();
			}
			public Object caseCORENamedElement(CORENamedElement object) {
				return createCORENamedElementAdapter();
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
	 * Creates a new adapter for an object of class '{@link urncore.URNdefinition <em>UR Ndefinition</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urncore.URNdefinition
	 * @generated
	 */
    public Adapter createURNdefinitionAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link urncore.Responsibility <em>Responsibility</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urncore.Responsibility
	 * @generated
	 */
    public Adapter createResponsibilityAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link urncore.Component <em>Component</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urncore.Component
	 * @generated
	 */
    public Adapter createComponentAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link urncore.ComponentType <em>Component Type</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urncore.ComponentType
	 * @generated
	 */
    public Adapter createComponentTypeAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link urncore.UCMmodelElement <em>UC Mmodel Element</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urncore.UCMmodelElement
	 * @generated
	 */
    public Adapter createUCMmodelElementAdapter() {
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
	 * Creates a new adapter for an object of class '{@link urncore.NodeLabel <em>Node Label</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urncore.NodeLabel
	 * @generated
	 */
    public Adapter createNodeLabelAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link urncore.Label <em>Label</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urncore.Label
	 * @generated
	 */
    public Adapter createLabelAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link urncore.ComponentLabel <em>Component Label</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urncore.ComponentLabel
	 * @generated
	 */
    public Adapter createComponentLabelAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link urncore.Condition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urncore.Condition
	 * @generated
	 */
    public Adapter createConditionAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link urncore.IURNDiagram <em>IURN Diagram</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urncore.IURNDiagram
	 * @generated
	 */
    public Adapter createIURNDiagramAdapter() {
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
	 * Creates a new adapter for an object of class '{@link urncore.IURNNode <em>IURN Node</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urncore.IURNNode
	 * @generated
	 */
    public Adapter createIURNNodeAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link urncore.IURNContainerRef <em>IURN Container Ref</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urncore.IURNContainerRef
	 * @generated
	 */
    public Adapter createIURNContainerRefAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link urncore.IURNContainer <em>IURN Container</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urncore.IURNContainer
	 * @generated
	 */
    public Adapter createIURNContainerAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link urncore.IURNConnection <em>IURN Connection</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urncore.IURNConnection
	 * @generated
	 */
    public Adapter createIURNConnectionAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link urncore.Metadata <em>Metadata</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urncore.Metadata
	 * @generated
	 */
	public Adapter createMetadataAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link urncore.Concern <em>Concern</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urncore.Concern
	 * @generated
	 */
	public Adapter createConcernAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link urncore.ConnectionLabel <em>Connection Label</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urncore.ConnectionLabel
	 * @generated
	 */
	public Adapter createConnectionLabelAdapter() {
		return null;
	}

				/**
	 * Creates a new adapter for an object of class '{@link urncore.Comment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see urncore.Comment
	 * @generated
	 */
	public Adapter createCommentAdapter() {
		return null;
	}

				/**
	 * Creates a new adapter for an object of class '{@link ca.mcgill.sel.core.CORENamedElement <em>CORE Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see ca.mcgill.sel.core.CORENamedElement
	 * @generated
	 */
	public Adapter createCORENamedElementAdapter() {
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

} //UrncoreAdapterFactory
