/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>UC Mspec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
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
public interface UCMspec extends EObject {
    /**
     * Returns the value of the '<em><b>Resptimereq</b></em>' containment reference list.
     * The list contents are of type {@link ucm.performance.ResponseTimeReq}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resptimereq</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resptimereq</em>' containment reference list.
     * @see ucm.UcmPackage#getUCMspec_Resptimereq()
     * @model type="ucm.performance.ResponseTimeReq" containment="true"
     * @generated
     */
    EList getResptimereq();

    /**
     * Returns the value of the '<em><b>Perf Measures</b></em>' containment reference list.
     * The list contents are of type {@link ucm.performance.PerfMeasure}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Perf Measures</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Perf Measures</em>' containment reference list.
     * @see ucm.UcmPackage#getUCMspec_PerfMeasures()
     * @model type="ucm.performance.PerfMeasure" containment="true"
     * @generated
     */
    EList getPerfMeasures();

    /**
     * Returns the value of the '<em><b>Resources</b></em>' containment reference list.
     * The list contents are of type {@link ucm.performance.GeneralResource}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resources</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resources</em>' containment reference list.
     * @see ucm.UcmPackage#getUCMspec_Resources()
     * @model type="ucm.performance.GeneralResource" containment="true"
     * @generated
     */
    EList getResources();

    /**
     * Returns the value of the '<em><b>Maps</b></em>' containment reference list.
     * The list contents are of type {@link ucm.map.Map}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Maps</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Maps</em>' containment reference list.
     * @see ucm.UcmPackage#getUCMspec_Maps()
     * @model type="ucm.map.Map" containment="true" required="true"
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
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Scenario Groups</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Scenario Groups</em>' containment reference list.
     * @see ucm.UcmPackage#getUCMspec_ScenarioGroups()
     * @model type="ucm.scenario.ScenarioGroup" containment="true"
     * @generated
     */
    EList getScenarioGroups();

    /**
     * Returns the value of the '<em><b>Variables</b></em>' containment reference list.
     * The list contents are of type {@link ucm.scenario.Variable}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variables</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Variables</em>' containment reference list.
     * @see ucm.UcmPackage#getUCMspec_Variables()
     * @model type="ucm.scenario.Variable" containment="true"
     * @generated
     */
    EList getVariables();

    /**
     * Returns the value of the '<em><b>Scenario Defs</b></em>' containment reference list.
     * The list contents are of type {@link ucm.scenario.ScenarioDef}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Scenario Defs</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Scenario Defs</em>' containment reference list.
     * @see ucm.UcmPackage#getUCMspec_ScenarioDefs()
     * @model type="ucm.scenario.ScenarioDef" containment="true"
     * @generated
     */
    EList getScenarioDefs();

} // UCMspec
