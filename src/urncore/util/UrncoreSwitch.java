/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.util;

import ca.mcgill.sel.core.CORENamedElement;
import java.util.List;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import urncore.*;

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
 * @see urncore.UrncorePackage
 * @generated
 */
public class UrncoreSwitch {
    /**
	 * The cached model package
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected static UrncorePackage modelPackage;

    /**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public UrncoreSwitch() {
		if (modelPackage == null) {
			modelPackage = UrncorePackage.eINSTANCE;
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
			case UrncorePackage.UR_NDEFINITION: {
				URNdefinition urNdefinition = (URNdefinition)theEObject;
				Object result = caseURNdefinition(urNdefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UrncorePackage.RESPONSIBILITY: {
				Responsibility responsibility = (Responsibility)theEObject;
				Object result = caseResponsibility(responsibility);
				if (result == null) result = caseUCMmodelElement(responsibility);
				if (result == null) result = caseURNmodelElement(responsibility);
				if (result == null) result = caseCORENamedElement(responsibility);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UrncorePackage.COMPONENT: {
				Component component = (Component)theEObject;
				Object result = caseComponent(component);
				if (result == null) result = caseUCMmodelElement(component);
				if (result == null) result = caseIURNContainer(component);
				if (result == null) result = caseURNmodelElement(component);
				if (result == null) result = caseCORENamedElement(component);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UrncorePackage.COMPONENT_TYPE: {
				ComponentType componentType = (ComponentType)theEObject;
				Object result = caseComponentType(componentType);
				if (result == null) result = caseUCMmodelElement(componentType);
				if (result == null) result = caseURNmodelElement(componentType);
				if (result == null) result = caseCORENamedElement(componentType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UrncorePackage.UC_MMODEL_ELEMENT: {
				UCMmodelElement ucMmodelElement = (UCMmodelElement)theEObject;
				Object result = caseUCMmodelElement(ucMmodelElement);
				if (result == null) result = caseURNmodelElement(ucMmodelElement);
				if (result == null) result = caseCORENamedElement(ucMmodelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UrncorePackage.GR_LMODEL_ELEMENT: {
				GRLmodelElement grLmodelElement = (GRLmodelElement)theEObject;
				Object result = caseGRLmodelElement(grLmodelElement);
				if (result == null) result = caseURNmodelElement(grLmodelElement);
				if (result == null) result = caseCORENamedElement(grLmodelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UrncorePackage.NODE_LABEL: {
				NodeLabel nodeLabel = (NodeLabel)theEObject;
				Object result = caseNodeLabel(nodeLabel);
				if (result == null) result = caseLabel(nodeLabel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UrncorePackage.LABEL: {
				Label label = (Label)theEObject;
				Object result = caseLabel(label);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UrncorePackage.COMPONENT_LABEL: {
				ComponentLabel componentLabel = (ComponentLabel)theEObject;
				Object result = caseComponentLabel(componentLabel);
				if (result == null) result = caseLabel(componentLabel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UrncorePackage.CONDITION: {
				Condition condition = (Condition)theEObject;
				Object result = caseCondition(condition);
				if (result == null) result = caseLabel(condition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UrncorePackage.IURN_DIAGRAM: {
				IURNDiagram iurnDiagram = (IURNDiagram)theEObject;
				Object result = caseIURNDiagram(iurnDiagram);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UrncorePackage.UR_NMODEL_ELEMENT: {
				URNmodelElement urNmodelElement = (URNmodelElement)theEObject;
				Object result = caseURNmodelElement(urNmodelElement);
				if (result == null) result = caseCORENamedElement(urNmodelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UrncorePackage.IURN_NODE: {
				IURNNode iurnNode = (IURNNode)theEObject;
				Object result = caseIURNNode(iurnNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UrncorePackage.IURN_CONTAINER_REF: {
				IURNContainerRef iurnContainerRef = (IURNContainerRef)theEObject;
				Object result = caseIURNContainerRef(iurnContainerRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UrncorePackage.IURN_CONTAINER: {
				IURNContainer iurnContainer = (IURNContainer)theEObject;
				Object result = caseIURNContainer(iurnContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UrncorePackage.IURN_CONNECTION: {
				IURNConnection iurnConnection = (IURNConnection)theEObject;
				Object result = caseIURNConnection(iurnConnection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UrncorePackage.METADATA: {
				Metadata metadata = (Metadata)theEObject;
				Object result = caseMetadata(metadata);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UrncorePackage.CONCERN: {
				Concern concern = (Concern)theEObject;
				Object result = caseConcern(concern);
				if (result == null) result = caseURNmodelElement(concern);
				if (result == null) result = caseCORENamedElement(concern);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UrncorePackage.CONNECTION_LABEL: {
				ConnectionLabel connectionLabel = (ConnectionLabel)theEObject;
				Object result = caseConnectionLabel(connectionLabel);
				if (result == null) result = caseLabel(connectionLabel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case UrncorePackage.COMMENT: {
				Comment comment = (Comment)theEObject;
				Object result = caseComment(comment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>UR Ndefinition</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>UR Ndefinition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseURNdefinition(URNdefinition object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Responsibility</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Responsibility</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseResponsibility(Responsibility object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Component</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseComponent(Component object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Component Type</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseComponentType(ComponentType object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>UC Mmodel Element</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>UC Mmodel Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseUCMmodelElement(UCMmodelElement object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>GR Lmodel Element</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>GR Lmodel Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseGRLmodelElement(GRLmodelElement object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Node Label</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Node Label</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseNodeLabel(NodeLabel object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Label</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Label</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseLabel(Label object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Component Label</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Label</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseComponentLabel(ComponentLabel object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Condition</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Condition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseCondition(Condition object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>IURN Diagram</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IURN Diagram</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseIURNDiagram(IURNDiagram object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>UR Nmodel Element</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>UR Nmodel Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseURNmodelElement(URNmodelElement object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>IURN Node</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IURN Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseIURNNode(IURNNode object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>IURN Container Ref</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IURN Container Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseIURNContainerRef(IURNContainerRef object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>IURN Container</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IURN Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseIURNContainer(IURNContainer object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>IURN Connection</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IURN Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseIURNConnection(IURNConnection object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Metadata</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Metadata</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMetadata(Metadata object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Concern</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Concern</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseConcern(Concern object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Connection Label</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connection Label</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseConnectionLabel(ConnectionLabel object) {
		return null;
	}

				/**
	 * Returns the result of interpreting the object as an instance of '<em>Comment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Comment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseComment(Comment object) {
		return null;
	}

				/**
	 * Returns the result of interpreting the object as an instance of '<em>CORE Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CORE Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCORENamedElement(CORENamedElement object) {
		return null;
	}

				/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
    public Object defaultCase(EObject object) {
		return null;
	}

} //UrncoreSwitch
