/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import urn.URNspec;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>UC Mspec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.UCMspec#getUrnspec <em>Urnspec</em>}</li>
 *   <li>{@link ucm.UCMspec#getResources <em>Resources</em>}</li>
 *   <li>{@link ucm.UCMspec#getScenarioGroups <em>Scenario Groups</em>}</li>
 *   <li>{@link ucm.UCMspec#getVariables <em>Variables</em>}</li>
 *   <li>{@link ucm.UCMspec#getEnumerationTypes <em>Enumeration Types</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.UcmPackage#getUCMspec()
 * @model
 * @generated
 */
public interface UCMspec extends EObject {
    /**
	 * Returns the value of the '<em><b>Urnspec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link urn.URNspec#getUcmspec <em>Ucmspec</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Urnspec</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Urnspec</em>' container reference.
	 * @see #setUrnspec(URNspec)
	 * @see ucm.UcmPackage#getUCMspec_Urnspec()
	 * @see urn.URNspec#getUcmspec
	 * @model opposite="ucmspec" required="true"
	 * @generated
	 */
    URNspec getUrnspec();

    /**
	 * Sets the value of the '{@link ucm.UCMspec#getUrnspec <em>Urnspec</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Urnspec</em>' container reference.
	 * @see #getUrnspec()
	 * @generated
	 */
    void setUrnspec(URNspec value);

    /**
	 * Returns the value of the '<em><b>Resources</b></em>' containment reference list.
	 * The list contents are of type {@link ucm.performance.GeneralResource}.
	 * It is bidirectional and its opposite is '{@link ucm.performance.GeneralResource#getUcmspec <em>Ucmspec</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resources</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Resources</em>' containment reference list.
	 * @see ucm.UcmPackage#getUCMspec_Resources()
	 * @see ucm.performance.GeneralResource#getUcmspec
	 * @model type="ucm.performance.GeneralResource" opposite="ucmspec" containment="true"
	 * @generated
	 */
    EList getResources();

    /**
	 * Returns the value of the '<em><b>Scenario Groups</b></em>' containment reference list.
	 * The list contents are of type {@link ucm.scenario.ScenarioGroup}.
	 * It is bidirectional and its opposite is '{@link ucm.scenario.ScenarioGroup#getUcmspec <em>Ucmspec</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Scenario Groups</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenario Groups</em>' containment reference list.
	 * @see ucm.UcmPackage#getUCMspec_ScenarioGroups()
	 * @see ucm.scenario.ScenarioGroup#getUcmspec
	 * @model type="ucm.scenario.ScenarioGroup" opposite="ucmspec" containment="true"
	 * @generated
	 */
    EList getScenarioGroups();

    /**
	 * Returns the value of the '<em><b>Variables</b></em>' containment reference list.
	 * The list contents are of type {@link ucm.scenario.Variable}.
	 * It is bidirectional and its opposite is '{@link ucm.scenario.Variable#getUcmspec <em>Ucmspec</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variables</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Variables</em>' containment reference list.
	 * @see ucm.UcmPackage#getUCMspec_Variables()
	 * @see ucm.scenario.Variable#getUcmspec
	 * @model type="ucm.scenario.Variable" opposite="ucmspec" containment="true"
	 * @generated
	 */
    EList getVariables();

    /**
	 * Returns the value of the '<em><b>Enumeration Types</b></em>' containment reference list.
	 * The list contents are of type {@link ucm.scenario.EnumerationType}.
	 * It is bidirectional and its opposite is '{@link ucm.scenario.EnumerationType#getUcmspec <em>Ucmspec</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enumeration Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enumeration Types</em>' containment reference list.
	 * @see ucm.UcmPackage#getUCMspec_EnumerationTypes()
	 * @see ucm.scenario.EnumerationType#getUcmspec
	 * @model type="ucm.scenario.EnumerationType" opposite="ucmspec" containment="true"
	 * @generated
	 */
	EList getEnumerationTypes();

} // UCMspec
