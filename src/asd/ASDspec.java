/**
 */
package asd;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AS Dspec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link asd.ASDspec#getASNetwork <em>AS Network</em>}</li>
 *   <li>{@link asd.ASDspec#getMotivation <em>Motivation</em>}</li>
 *   <li>{@link asd.ASDspec#getOutcome <em>Outcome</em>}</li>
 *   <li>{@link asd.ASDspec#getDols <em>Dols</em>}</li>
 *   <li>{@link asd.ASDspec#getSubjects <em>Subjects</em>}</li>
 *   <li>{@link asd.ASDspec#getCommunities <em>Communities</em>}</li>
 *   <li>{@link asd.ASDspec#getObjects <em>Objects</em>}</li>
 *   <li>{@link asd.ASDspec#getOutcomes <em>Outcomes</em>}</li>
 *   <li>{@link asd.ASDspec#getMotivations <em>Motivations</em>}</li>
 *   <li>{@link asd.ASDspec#getTools <em>Tools</em>}</li>
 *   <li>{@link asd.ASDspec#getRules <em>Rules</em>}</li>
 * </ul>
 * </p>
 *
 * @see asd.AsdPackage#getASDspec()
 * @model
 * @generated
 */
public interface ASDspec extends EObject {
	/**
	 * Returns the value of the '<em><b>AS Network</b></em>' reference list.
	 * The list contents are of type {@link asd.ASNetwork}.
	 * It is bidirectional and its opposite is '{@link asd.ASNetwork#getASDspec <em>AS Dspec</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>AS Network</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>AS Network</em>' reference list.
	 * @see asd.AsdPackage#getASDspec_ASNetwork()
	 * @see asd.ASNetwork#getASDspec
	 * @model type="asd.ASNetwork" opposite="ASDspec"
	 * @generated
	 */
	EList getASNetwork();

	/**
	 * Returns the value of the '<em><b>Motivation</b></em>' reference list.
	 * The list contents are of type {@link asd.Motivation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Motivation</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Motivation</em>' reference list.
	 * @see asd.AsdPackage#getASDspec_Motivation()
	 * @model type="asd.Motivation"
	 * @generated
	 */
	EList getMotivation();

	/**
	 * Returns the value of the '<em><b>Outcome</b></em>' reference list.
	 * The list contents are of type {@link asd.Outcome}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outcome</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outcome</em>' reference list.
	 * @see asd.AsdPackage#getASDspec_Outcome()
	 * @model type="asd.Outcome"
	 * @generated
	 */
	EList getOutcome();

	/**
	 * Returns the value of the '<em><b>Dols</b></em>' containment reference list.
	 * The list contents are of type {@link asd.DivisionOfLabour}.
	 * It is bidirectional and its opposite is '{@link asd.DivisionOfLabour#getAsdSpec <em>Asd Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dols</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dols</em>' containment reference list.
	 * @see asd.AsdPackage#getASDspec_Dols()
	 * @see asd.DivisionOfLabour#getAsdSpec
	 * @model type="asd.DivisionOfLabour" opposite="asdSpec" containment="true"
	 * @generated
	 */
	EList getDols();

	/**
	 * Returns the value of the '<em><b>Subjects</b></em>' containment reference list.
	 * The list contents are of type {@link asd.Subject}.
	 * It is bidirectional and its opposite is '{@link asd.Subject#getAsdSpec <em>Asd Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subjects</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subjects</em>' containment reference list.
	 * @see asd.AsdPackage#getASDspec_Subjects()
	 * @see asd.Subject#getAsdSpec
	 * @model type="asd.Subject" opposite="asdSpec" containment="true"
	 * @generated
	 */
	EList getSubjects();

	/**
	 * Returns the value of the '<em><b>Communities</b></em>' containment reference list.
	 * The list contents are of type {@link asd.Community}.
	 * It is bidirectional and its opposite is '{@link asd.Community#getAsdSpec <em>Asd Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Communities</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Communities</em>' containment reference list.
	 * @see asd.AsdPackage#getASDspec_Communities()
	 * @see asd.Community#getAsdSpec
	 * @model type="asd.Community" opposite="asdSpec" containment="true"
	 * @generated
	 */
	EList getCommunities();

	/**
	 * Returns the value of the '<em><b>Objects</b></em>' containment reference list.
	 * The list contents are of type {@link asd.Object}.
	 * It is bidirectional and its opposite is '{@link asd.Object#getAsdSpec <em>Asd Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Objects</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Objects</em>' containment reference list.
	 * @see asd.AsdPackage#getASDspec_Objects()
	 * @see asd.Object#getAsdSpec
	 * @model type="asd.Object" opposite="asdSpec" containment="true"
	 * @generated
	 */
	EList getObjects();

	/**
	 * Returns the value of the '<em><b>Outcomes</b></em>' containment reference list.
	 * The list contents are of type {@link asd.Outcome}.
	 * It is bidirectional and its opposite is '{@link asd.Outcome#getAsdSpec <em>Asd Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outcomes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outcomes</em>' containment reference list.
	 * @see asd.AsdPackage#getASDspec_Outcomes()
	 * @see asd.Outcome#getAsdSpec
	 * @model type="asd.Outcome" opposite="asdSpec" containment="true"
	 * @generated
	 */
	EList getOutcomes();

	/**
	 * Returns the value of the '<em><b>Motivations</b></em>' containment reference list.
	 * The list contents are of type {@link asd.Motivation}.
	 * It is bidirectional and its opposite is '{@link asd.Motivation#getAsdSpec <em>Asd Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Motivations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Motivations</em>' containment reference list.
	 * @see asd.AsdPackage#getASDspec_Motivations()
	 * @see asd.Motivation#getAsdSpec
	 * @model type="asd.Motivation" opposite="asdSpec" containment="true"
	 * @generated
	 */
	EList getMotivations();

	/**
	 * Returns the value of the '<em><b>Tools</b></em>' containment reference list.
	 * The list contents are of type {@link asd.Tool}.
	 * It is bidirectional and its opposite is '{@link asd.Tool#getAsdSpec <em>Asd Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tools</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tools</em>' containment reference list.
	 * @see asd.AsdPackage#getASDspec_Tools()
	 * @see asd.Tool#getAsdSpec
	 * @model type="asd.Tool" opposite="asdSpec" containment="true"
	 * @generated
	 */
	EList getTools();

	/**
	 * Returns the value of the '<em><b>Rules</b></em>' containment reference list.
	 * The list contents are of type {@link asd.Rule}.
	 * It is bidirectional and its opposite is '{@link asd.Rule#getAsdSpec <em>Asd Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rules</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rules</em>' containment reference list.
	 * @see asd.AsdPackage#getASDspec_Rules()
	 * @see asd.Rule#getAsdSpec
	 * @model type="asd.Rule" opposite="asdSpec" containment="true"
	 * @generated
	 */
	EList getRules();

} // ASDspec
