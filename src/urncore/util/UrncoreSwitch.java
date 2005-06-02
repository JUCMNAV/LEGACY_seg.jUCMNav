/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.util;

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
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case UrncorePackage.POOL: {
                Pool pool = (Pool)theEObject;
                Object result = casePool(pool);
                if (result == null) result = caseComponentElement(pool);
                if (result == null) result = caseUCMmodelElement(pool);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case UrncorePackage.COMPONENT: {
                Component component = (Component)theEObject;
                Object result = caseComponent(component);
                if (result == null) result = caseComponentRegular(component);
                if (result == null) result = caseComponentElement(component);
                if (result == null) result = caseUCMmodelElement(component);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case UrncorePackage.COMPONENT_TYPE: {
                ComponentType componentType = (ComponentType)theEObject;
                Object result = caseComponentType(componentType);
                if (result == null) result = caseComponentRegular(componentType);
                if (result == null) result = caseComponentElement(componentType);
                if (result == null) result = caseUCMmodelElement(componentType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case UrncorePackage.DYNAMIC_RESPONSIBILITY: {
                DynamicResponsibility dynamicResponsibility = (DynamicResponsibility)theEObject;
                Object result = caseDynamicResponsibility(dynamicResponsibility);
                if (result == null) result = caseResponsibility(dynamicResponsibility);
                if (result == null) result = caseUCMmodelElement(dynamicResponsibility);
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
            case UrncorePackage.COMPONENT_LABEL: {
                ComponentLabel componentLabel = (ComponentLabel)theEObject;
                Object result = caseComponentLabel(componentLabel);
                if (result == null) result = caseLabel(componentLabel);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case UrncorePackage.NEW_CLASS: {
                NewClass newClass = (NewClass)theEObject;
                Object result = caseNewClass(newClass);
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
            default: return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>UR Ndefinition</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>UR Ndefinition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseURNdefinition(URNdefinition object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Responsibility</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Responsibility</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseResponsibility(Responsibility object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Component Regular</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Component Regular</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseComponentRegular(ComponentRegular object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Component Element</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Component Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseComponentElement(ComponentElement object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Pool</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Pool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object casePool(Pool object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Component</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Component</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseComponent(Component object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Component Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Component Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseComponentType(ComponentType object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Dynamic Responsibility</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Dynamic Responsibility</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseDynamicResponsibility(DynamicResponsibility object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>UC Mmodel Element</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>UC Mmodel Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseUCMmodelElement(UCMmodelElement object) {
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
     * Returns the result of interpretting the object as an instance of '<em>Node Label</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Node Label</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseNodeLabel(NodeLabel object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Label</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Label</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseLabel(Label object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Component Label</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Component Label</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseComponentLabel(ComponentLabel object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>New Class</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>New Class</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseNewClass(NewClass object) {
        return null;
    }

    /**
     * Returns the result of interpretting the object as an instance of '<em>Condition</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpretting the object as an instance of '<em>Condition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public Object caseCondition(Condition object) {
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

} //UrncoreSwitch
