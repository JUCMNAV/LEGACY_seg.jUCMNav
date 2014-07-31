/**
 */
package asd.util;

import asd.ASDelement;
import asd.ASDiagram;
import asd.ASDlayout;
import asd.ASDmodelElement;
import asd.ASDspec;
import asd.ASNetwork;
import asd.AsdPackage;
import asd.Community;
import asd.DivisionOfLabour;
import asd.MediatedElement;
import asd.MediatingElement;
import asd.Mediation;
import asd.Motivation;
import asd.Outcome;
import asd.Rule;
import asd.Subject;
import asd.Tool;
import ca.mcgill.sel.core.CORENamedElement;
import java.util.List;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import urncore.IURNDiagram;
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
 * @see asd.AsdPackage
 * @generated
 */
public class AsdSwitch {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static AsdPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AsdSwitch() {
		if (modelPackage == null) {
			modelPackage = AsdPackage.eINSTANCE;
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
			case AsdPackage.AS_DSPEC: {
				ASDspec asDspec = (ASDspec)theEObject;
				Object result = caseASDspec(asDspec);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsdPackage.AS_DELEMENT: {
				ASDelement asDelement = (ASDelement)theEObject;
				Object result = caseASDelement(asDelement);
				if (result == null) result = caseASDmodelElement(asDelement);
				if (result == null) result = caseURNmodelElement(asDelement);
				if (result == null) result = caseCORENamedElement(asDelement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsdPackage.AS_DIAGRAM: {
				ASDiagram asDiagram = (ASDiagram)theEObject;
				Object result = caseASDiagram(asDiagram);
				if (result == null) result = caseASDmodelElement(asDiagram);
				if (result == null) result = caseIURNDiagram(asDiagram);
				if (result == null) result = caseURNmodelElement(asDiagram);
				if (result == null) result = caseCORENamedElement(asDiagram);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsdPackage.MEDIATING_ELEMENT: {
				MediatingElement mediatingElement = (MediatingElement)theEObject;
				Object result = caseMediatingElement(mediatingElement);
				if (result == null) result = caseASDelement(mediatingElement);
				if (result == null) result = caseASDmodelElement(mediatingElement);
				if (result == null) result = caseURNmodelElement(mediatingElement);
				if (result == null) result = caseCORENamedElement(mediatingElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsdPackage.MEDIATION: {
				Mediation mediation = (Mediation)theEObject;
				Object result = caseMediation(mediation);
				if (result == null) result = caseASDmodelElement(mediation);
				if (result == null) result = caseURNmodelElement(mediation);
				if (result == null) result = caseCORENamedElement(mediation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsdPackage.MEDIATED_ELEMENT: {
				MediatedElement mediatedElement = (MediatedElement)theEObject;
				Object result = caseMediatedElement(mediatedElement);
				if (result == null) result = caseASDelement(mediatedElement);
				if (result == null) result = caseASDmodelElement(mediatedElement);
				if (result == null) result = caseURNmodelElement(mediatedElement);
				if (result == null) result = caseCORENamedElement(mediatedElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsdPackage.AS_DMODEL_ELEMENT: {
				ASDmodelElement asDmodelElement = (ASDmodelElement)theEObject;
				Object result = caseASDmodelElement(asDmodelElement);
				if (result == null) result = caseURNmodelElement(asDmodelElement);
				if (result == null) result = caseCORENamedElement(asDmodelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsdPackage.AS_NETWORK: {
				ASNetwork asNetwork = (ASNetwork)theEObject;
				Object result = caseASNetwork(asNetwork);
				if (result == null) result = caseIURNDiagram(asNetwork);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsdPackage.AS_DLAYOUT: {
				ASDlayout asDlayout = (ASDlayout)theEObject;
				Object result = caseASDlayout(asDlayout);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsdPackage.TOOL: {
				Tool tool = (Tool)theEObject;
				Object result = caseTool(tool);
				if (result == null) result = caseMediatingElement(tool);
				if (result == null) result = caseASDelement(tool);
				if (result == null) result = caseASDmodelElement(tool);
				if (result == null) result = caseURNmodelElement(tool);
				if (result == null) result = caseCORENamedElement(tool);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsdPackage.RULE: {
				Rule rule = (Rule)theEObject;
				Object result = caseRule(rule);
				if (result == null) result = caseMediatingElement(rule);
				if (result == null) result = caseASDelement(rule);
				if (result == null) result = caseASDmodelElement(rule);
				if (result == null) result = caseURNmodelElement(rule);
				if (result == null) result = caseCORENamedElement(rule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsdPackage.DIVISION_OF_LABOUR: {
				DivisionOfLabour divisionOfLabour = (DivisionOfLabour)theEObject;
				Object result = caseDivisionOfLabour(divisionOfLabour);
				if (result == null) result = caseMediatingElement(divisionOfLabour);
				if (result == null) result = caseASDelement(divisionOfLabour);
				if (result == null) result = caseASDmodelElement(divisionOfLabour);
				if (result == null) result = caseURNmodelElement(divisionOfLabour);
				if (result == null) result = caseCORENamedElement(divisionOfLabour);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsdPackage.COMMUNITY: {
				Community community = (Community)theEObject;
				Object result = caseCommunity(community);
				if (result == null) result = caseMediatedElement(community);
				if (result == null) result = caseASDelement(community);
				if (result == null) result = caseASDmodelElement(community);
				if (result == null) result = caseURNmodelElement(community);
				if (result == null) result = caseCORENamedElement(community);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsdPackage.SUBJECT: {
				Subject subject = (Subject)theEObject;
				Object result = caseSubject(subject);
				if (result == null) result = caseMediatedElement(subject);
				if (result == null) result = caseASDelement(subject);
				if (result == null) result = caseASDmodelElement(subject);
				if (result == null) result = caseURNmodelElement(subject);
				if (result == null) result = caseCORENamedElement(subject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsdPackage.OBJECT: {
				asd.Object object = (asd.Object)theEObject;
				Object result = caseObject(object);
				if (result == null) result = caseMediatedElement(object);
				if (result == null) result = caseASDelement(object);
				if (result == null) result = caseASDmodelElement(object);
				if (result == null) result = caseURNmodelElement(object);
				if (result == null) result = caseCORENamedElement(object);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsdPackage.MOTIVATION: {
				Motivation motivation = (Motivation)theEObject;
				Object result = caseMotivation(motivation);
				if (result == null) result = caseASDelement(motivation);
				if (result == null) result = caseASDmodelElement(motivation);
				if (result == null) result = caseURNmodelElement(motivation);
				if (result == null) result = caseCORENamedElement(motivation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case AsdPackage.OUTCOME: {
				Outcome outcome = (Outcome)theEObject;
				Object result = caseOutcome(outcome);
				if (result == null) result = caseASDelement(outcome);
				if (result == null) result = caseASDmodelElement(outcome);
				if (result == null) result = caseURNmodelElement(outcome);
				if (result == null) result = caseCORENamedElement(outcome);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AS Dspec</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AS Dspec</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseASDspec(ASDspec object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AS Delement</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AS Delement</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseASDelement(ASDelement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AS Diagram</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AS Diagram</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseASDiagram(ASDiagram object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mediating Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mediating Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMediatingElement(MediatingElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mediation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mediation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMediation(Mediation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mediated Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mediated Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMediatedElement(MediatedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AS Dmodel Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AS Dmodel Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseASDmodelElement(ASDmodelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AS Network</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AS Network</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseASNetwork(ASNetwork object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>AS Dlayout</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>AS Dlayout</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseASDlayout(ASDlayout object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tool</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tool</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseTool(Tool object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Rule</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Rule</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseRule(Rule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Division Of Labour</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Division Of Labour</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseDivisionOfLabour(DivisionOfLabour object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Community</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Community</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCommunity(Community object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Subject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Subject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseSubject(Subject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseObject(asd.Object object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Motivation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Motivation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseMotivation(Motivation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Outcome</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Outcome</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseOutcome(Outcome object) {
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

} //AsdSwitch
