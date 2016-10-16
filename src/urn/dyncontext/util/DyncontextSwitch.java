/**
 */
package urn.dyncontext.util;

import ca.mcgill.sel.core.CORENamedElement;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import urn.dyncontext.*;

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
 * @see urn.dyncontext.DyncontextPackage
 * @generated
 */
public class DyncontextSwitch {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static DyncontextPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DyncontextSwitch() {
		if (modelPackage == null) {
			modelPackage = DyncontextPackage.eINSTANCE;
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
			case DyncontextPackage.QUADRATIC_CHANGE: {
				QuadraticChange quadraticChange = (QuadraticChange)theEObject;
				Object result = caseQuadraticChange(quadraticChange);
				if (result == null) result = caseNumericChange(quadraticChange);
				if (result == null) result = casePropertyChange(quadraticChange);
				if (result == null) result = caseChange(quadraticChange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DyncontextPackage.TIMEPOINT_GROUP: {
				TimepointGroup timepointGroup = (TimepointGroup)theEObject;
				Object result = caseTimepointGroup(timepointGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DyncontextPackage.TIMEPOINT: {
				Timepoint timepoint = (Timepoint)theEObject;
				Object result = caseTimepoint(timepoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DyncontextPackage.PROPERTY_CHANGE: {
				PropertyChange propertyChange = (PropertyChange)theEObject;
				Object result = casePropertyChange(propertyChange);
				if (result == null) result = caseChange(propertyChange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DyncontextPackage.NUMERIC_CHANGE: {
				NumericChange numericChange = (NumericChange)theEObject;
				Object result = caseNumericChange(numericChange);
				if (result == null) result = casePropertyChange(numericChange);
				if (result == null) result = caseChange(numericChange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DyncontextPackage.FORMULA_CHANGE: {
				FormulaChange formulaChange = (FormulaChange)theEObject;
				Object result = caseFormulaChange(formulaChange);
				if (result == null) result = caseNumericChange(formulaChange);
				if (result == null) result = casePropertyChange(formulaChange);
				if (result == null) result = caseChange(formulaChange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DyncontextPackage.LINEAR_CHANGE: {
				LinearChange linearChange = (LinearChange)theEObject;
				Object result = caseLinearChange(linearChange);
				if (result == null) result = caseNumericChange(linearChange);
				if (result == null) result = casePropertyChange(linearChange);
				if (result == null) result = caseChange(linearChange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DyncontextPackage.ENUM_CHANGE: {
				EnumChange enumChange = (EnumChange)theEObject;
				Object result = caseEnumChange(enumChange);
				if (result == null) result = casePropertyChange(enumChange);
				if (result == null) result = caseChange(enumChange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DyncontextPackage.DYNAMIC_CONTEXT: {
				DynamicContext dynamicContext = (DynamicContext)theEObject;
				Object result = caseDynamicContext(dynamicContext);
				if (result == null) result = caseURNmodelElement(dynamicContext);
				if (result == null) result = caseCORENamedElement(dynamicContext);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DyncontextPackage.DYNAMIC_CONTEXT_GROUP: {
				DynamicContextGroup dynamicContextGroup = (DynamicContextGroup)theEObject;
				Object result = caseDynamicContextGroup(dynamicContextGroup);
				if (result == null) result = caseURNmodelElement(dynamicContextGroup);
				if (result == null) result = caseCORENamedElement(dynamicContextGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DyncontextPackage.DEACTIVATION_CHANGE: {
				DeactivationChange deactivationChange = (DeactivationChange)theEObject;
				Object result = caseDeactivationChange(deactivationChange);
				if (result == null) result = caseChange(deactivationChange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DyncontextPackage.CONSTANT_CHANGE: {
				ConstantChange constantChange = (ConstantChange)theEObject;
				Object result = caseConstantChange(constantChange);
				if (result == null) result = caseNumericChange(constantChange);
				if (result == null) result = casePropertyChange(constantChange);
				if (result == null) result = caseChange(constantChange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case DyncontextPackage.CHANGE: {
				Change change = (Change)theEObject;
				Object result = caseChange(change);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Quadratic Change</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Quadratic Change</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseQuadraticChange(QuadraticChange object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Timepoint Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Timepoint Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTimepointGroup(TimepointGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Timepoint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Timepoint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTimepoint(Timepoint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Property Change</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Property Change</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object casePropertyChange(PropertyChange object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Numeric Change</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Numeric Change</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseNumericChange(NumericChange object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Formula Change</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Formula Change</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseFormulaChange(FormulaChange object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Linear Change</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Linear Change</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseLinearChange(LinearChange object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enum Change</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enum Change</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseEnumChange(EnumChange object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dynamic Context</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dynamic Context</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDynamicContext(DynamicContext object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dynamic Context Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dynamic Context Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDynamicContextGroup(DynamicContextGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Deactivation Change</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Deactivation Change</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDeactivationChange(DeactivationChange object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constant Change</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constant Change</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseConstantChange(ConstantChange object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Change</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Change</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseChange(Change object) {
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

} //DyncontextSwitch
