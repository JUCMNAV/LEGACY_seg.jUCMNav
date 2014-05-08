/**
 */
package core.util;

import core.*;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

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
 * @see core.CorePackage
 * @generated
 */
public class CoreSwitch {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static CorePackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreSwitch() {
		if (modelPackage == null) {
			modelPackage = CorePackage.eINSTANCE;
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
			case CorePackage.CORE_MODEL: {
				COREModel coreModel = (COREModel)theEObject;
				Object result = caseCOREModel(coreModel);
				if (result == null) result = caseCORENamedElement(coreModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.CORE_IMPACT_MODEL: {
				COREImpactModel coreImpactModel = (COREImpactModel)theEObject;
				Object result = caseCOREImpactModel(coreImpactModel);
				if (result == null) result = caseCOREModel(coreImpactModel);
				if (result == null) result = caseCORENamedElement(coreImpactModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.CORE_CONCERN: {
				COREConcern coreConcern = (COREConcern)theEObject;
				Object result = caseCOREConcern(coreConcern);
				if (result == null) result = caseCORENamedElement(coreConcern);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.CORE_FEATURE: {
				COREFeature coreFeature = (COREFeature)theEObject;
				Object result = caseCOREFeature(coreFeature);
				if (result == null) result = caseCOREModelElement(coreFeature);
				if (result == null) result = caseCORENamedElement(coreFeature);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.CORE_BINDING: {
				COREBinding coreBinding = (COREBinding)theEObject;
				Object result = caseCOREBinding(coreBinding);
				if (result == null) result = caseCORECompositionSpecification(coreBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.CORE_MODEL_ELEMENT: {
				COREModelElement coreModelElement = (COREModelElement)theEObject;
				Object result = caseCOREModelElement(coreModelElement);
				if (result == null) result = caseCORENamedElement(coreModelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.CORE_COMPOSITION_SPECIFICATION: {
				CORECompositionSpecification coreCompositionSpecification = (CORECompositionSpecification)theEObject;
				Object result = caseCORECompositionSpecification(coreCompositionSpecification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.CORE_MAPPING: {
				COREMapping coreMapping = (COREMapping)theEObject;
				Object result = caseCOREMapping(coreMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.CORE_NAMED_ELEMENT: {
				CORENamedElement coreNamedElement = (CORENamedElement)theEObject;
				Object result = caseCORENamedElement(coreNamedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.CORE_STRATEGY: {
				COREStrategy coreStrategy = (COREStrategy)theEObject;
				Object result = caseCOREStrategy(coreStrategy);
				if (result == null) result = caseCORENamedElement(coreStrategy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.CORE_INTERFACE: {
				COREInterface coreInterface = (COREInterface)theEObject;
				Object result = caseCOREInterface(coreInterface);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.CORE_REUSE: {
				COREReuse coreReuse = (COREReuse)theEObject;
				Object result = caseCOREReuse(coreReuse);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.CORE_PATTERN: {
				COREPattern corePattern = (COREPattern)theEObject;
				Object result = caseCOREPattern(corePattern);
				if (result == null) result = caseCORECompositionSpecification(corePattern);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.CORE_IMPACT_MODEL_ELEMENT: {
				COREImpactModelElement coreImpactModelElement = (COREImpactModelElement)theEObject;
				Object result = caseCOREImpactModelElement(coreImpactModelElement);
				if (result == null) result = caseCOREModelElement(coreImpactModelElement);
				if (result == null) result = caseCORENamedElement(coreImpactModelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.CORE_CONFIGURATION: {
				COREConfiguration coreConfiguration = (COREConfiguration)theEObject;
				Object result = caseCOREConfiguration(coreConfiguration);
				if (result == null) result = caseCORENamedElement(coreConfiguration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CorePackage.CORE_FEATURE_MODEL: {
				COREFeatureModel coreFeatureModel = (COREFeatureModel)theEObject;
				Object result = caseCOREFeatureModel(coreFeatureModel);
				if (result == null) result = caseCOREModel(coreFeatureModel);
				if (result == null) result = caseCORENamedElement(coreFeatureModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CORE Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CORE Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCOREModel(COREModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CORE Impact Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CORE Impact Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCOREImpactModel(COREImpactModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CORE Concern</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CORE Concern</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCOREConcern(COREConcern object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CORE Feature</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CORE Feature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCOREFeature(COREFeature object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CORE Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CORE Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCOREBinding(COREBinding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CORE Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CORE Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCOREModelElement(COREModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CORE Composition Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CORE Composition Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCORECompositionSpecification(CORECompositionSpecification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CORE Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CORE Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCOREMapping(COREMapping object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>CORE Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CORE Strategy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCOREStrategy(COREStrategy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CORE Interface</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CORE Interface</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCOREInterface(COREInterface object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CORE Reuse</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CORE Reuse</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCOREReuse(COREReuse object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CORE Pattern</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CORE Pattern</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCOREPattern(COREPattern object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CORE Impact Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CORE Impact Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCOREImpactModelElement(COREImpactModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CORE Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CORE Configuration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCOREConfiguration(COREConfiguration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>CORE Feature Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CORE Feature Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCOREFeatureModel(COREFeatureModel object) {
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

} //CoreSwitch
