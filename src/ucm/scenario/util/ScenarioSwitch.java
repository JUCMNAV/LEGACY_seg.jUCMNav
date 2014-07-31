/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.scenario.util;

import ca.mcgill.sel.core.CORENamedElement;
import java.util.List;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import ucm.scenario.*;
import urncore.UCMmodelElement;
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
 * @see ucm.scenario.ScenarioPackage
 * @generated
 */
public class ScenarioSwitch {
    /**
	 * The cached model package
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected static ScenarioPackage modelPackage;

    /**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ScenarioSwitch() {
		if (modelPackage == null) {
			modelPackage = ScenarioPackage.eINSTANCE;
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
			case ScenarioPackage.VARIABLE: {
				Variable variable = (Variable)theEObject;
				Object result = caseVariable(variable);
				if (result == null) result = caseUCMmodelElement(variable);
				if (result == null) result = caseURNmodelElement(variable);
				if (result == null) result = caseCORENamedElement(variable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScenarioPackage.SCENARIO_DEF: {
				ScenarioDef scenarioDef = (ScenarioDef)theEObject;
				Object result = caseScenarioDef(scenarioDef);
				if (result == null) result = caseUCMmodelElement(scenarioDef);
				if (result == null) result = caseURNmodelElement(scenarioDef);
				if (result == null) result = caseCORENamedElement(scenarioDef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScenarioPackage.SCENARIO_GROUP: {
				ScenarioGroup scenarioGroup = (ScenarioGroup)theEObject;
				Object result = caseScenarioGroup(scenarioGroup);
				if (result == null) result = caseUCMmodelElement(scenarioGroup);
				if (result == null) result = caseURNmodelElement(scenarioGroup);
				if (result == null) result = caseCORENamedElement(scenarioGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScenarioPackage.ENUMERATION_TYPE: {
				EnumerationType enumerationType = (EnumerationType)theEObject;
				Object result = caseEnumerationType(enumerationType);
				if (result == null) result = caseUCMmodelElement(enumerationType);
				if (result == null) result = caseURNmodelElement(enumerationType);
				if (result == null) result = caseCORENamedElement(enumerationType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScenarioPackage.INITIALIZATION: {
				Initialization initialization = (Initialization)theEObject;
				Object result = caseInitialization(initialization);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScenarioPackage.SCENARIO_START_POINT: {
				ScenarioStartPoint scenarioStartPoint = (ScenarioStartPoint)theEObject;
				Object result = caseScenarioStartPoint(scenarioStartPoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ScenarioPackage.SCENARIO_END_POINT: {
				ScenarioEndPoint scenarioEndPoint = (ScenarioEndPoint)theEObject;
				Object result = caseScenarioEndPoint(scenarioEndPoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Group</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseScenarioGroup(ScenarioGroup object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Enumeration Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enumeration Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEnumerationType(EnumerationType object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Initialization</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Initialization</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseInitialization(Initialization object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Start Point</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Start Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseScenarioStartPoint(ScenarioStartPoint object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>End Point</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>End Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseScenarioEndPoint(ScenarioEndPoint object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Def</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Def</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseScenarioDef(ScenarioDef object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Variable</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Variable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseVariable(Variable object) {
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

} //ScenarioSwitch
