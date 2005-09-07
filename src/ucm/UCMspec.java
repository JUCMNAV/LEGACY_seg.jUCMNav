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
 *   <li>{@link ucm.UCMspec#getResptimereq <em>Resptimereq</em>}</li>
 *   <li>{@link ucm.UCMspec#getPerfMeasures <em>Perf Measures</em>}</li>
 *   <li>{@link ucm.UCMspec#getResources <em>Resources</em>}</li>
 *   <li>{@link ucm.UCMspec#getMaps <em>Maps</em>}</li>
 *   <li>{@link ucm.UCMspec#getRoot <em>Root</em>}</li>
 *   <li>{@link ucm.UCMspec#getScenarioGroups <em>Scenario Groups</em>}</li>
 *   <li>{@link ucm.UCMspec#getVariables <em>Variables</em>}</li>
 *   <li>{@link ucm.UCMspec#getScenarioDefs <em>Scenario Defs</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.UcmPackage#getUCMspec()
 * @model
 * @generated
 */
public interface UCMspec extends EObject{
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
     * Returns the value of the '<em><b>Resptimereq</b></em>' containment reference list.
     * The list contents are of type {@link ucm.performance.ResponseTimeReq}.
     * It is bidirectional and its opposite is '{@link ucm.performance.ResponseTimeReq#getUcmspec <em>Ucmspec</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resptimereq</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Resptimereq</em>' containment reference list.
     * @see ucm.UcmPackage#getUCMspec_Resptimereq()
     * @see ucm.performance.ResponseTimeReq#getUcmspec
     * @model type="ucm.performance.ResponseTimeReq" opposite="ucmspec" containment="true"
     * @generated
     */
	EList getResptimereq();

    /**
     * Returns the value of the '<em><b>Perf Measures</b></em>' containment reference list.
     * The list contents are of type {@link ucm.performance.PerfMeasure}.
     * It is bidirectional and its opposite is '{@link ucm.performance.PerfMeasure#getUcmspec <em>Ucmspec</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Perf Measures</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Perf Measures</em>' containment reference list.
     * @see ucm.UcmPackage#getUCMspec_PerfMeasures()
     * @see ucm.performance.PerfMeasure#getUcmspec
     * @model type="ucm.performance.PerfMeasure" opposite="ucmspec" containment="true"
     * @generated
     */
	EList getPerfMeasures();

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
     * Returns the value of the '<em><b>Maps</b></em>' containment reference list.
     * The list contents are of type {@link ucm.map.Map}.
     * It is bidirectional and its opposite is '{@link ucm.map.Map#getUcmspec <em>Ucmspec</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maps</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Maps</em>' containment reference list.
     * @see ucm.UcmPackage#getUCMspec_Maps()
     * @see ucm.map.Map#getUcmspec
     * @model type="ucm.map.Map" opposite="ucmspec" containment="true" required="true"
     * @generated
     */
	EList getMaps();

    /**
     * Returns the value of the '<em><b>Root</b></em>' reference list.
     * The list contents are of type {@link ucm.map.Map}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Root</em>' reference list.
     * @see ucm.UcmPackage#getUCMspec_Root()
     * @model type="ucm.map.Map" required="true"
     * @generated
     */
	EList getRoot();

    /**
     * Returns the value of the '<em><b>Scenario Groups</b></em>' containment reference list.
     * The list contents are of type {@link ucm.scenario.ScenarioGroup}.
     * It is bidirectional and its opposite is '{@link ucm.scenario.ScenarioGroup#getUrnspec <em>Urnspec</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scenario Groups</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Scenario Groups</em>' containment reference list.
     * @see ucm.UcmPackage#getUCMspec_ScenarioGroups()
     * @see ucm.scenario.ScenarioGroup#getUrnspec
     * @model type="ucm.scenario.ScenarioGroup" opposite="urnspec" containment="true"
     * @generated
     */
	EList getScenarioGroups();

    /**
     * Returns the value of the '<em><b>Variables</b></em>' containment reference list.
     * The list contents are of type {@link ucm.scenario.Variable}.
     * It is bidirectional and its opposite is '{@link ucm.scenario.Variable#getUrnspec <em>Urnspec</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variables</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Variables</em>' containment reference list.
     * @see ucm.UcmPackage#getUCMspec_Variables()
     * @see ucm.scenario.Variable#getUrnspec
     * @model type="ucm.scenario.Variable" opposite="urnspec" containment="true"
     * @generated
     */
	EList getVariables();

    /**
     * Returns the value of the '<em><b>Scenario Defs</b></em>' containment reference list.
     * The list contents are of type {@link ucm.scenario.ScenarioDef}.
     * It is bidirectional and its opposite is '{@link ucm.scenario.ScenarioDef#getUrnspec <em>Urnspec</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scenario Defs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Scenario Defs</em>' containment reference list.
     * @see ucm.UcmPackage#getUCMspec_ScenarioDefs()
     * @see ucm.scenario.ScenarioDef#getUrnspec
     * @model type="ucm.scenario.ScenarioDef" opposite="urnspec" containment="true"
     * @generated
     */
	EList getScenarioDefs();

} // UCMspec
