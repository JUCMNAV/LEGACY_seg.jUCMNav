/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.scenario;

import org.eclipse.emf.common.util.EList;

import ucm.UCMspec;

import urncore.UCMmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Defines initial values for the variables, the expected postconditions, and the start points.
 * TODO!
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.scenario.ScenarioDef#getStartPoints <em>Start Points</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioDef#getUcmspec <em>Ucmspec</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioDef#getGroups <em>Groups</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.scenario.ScenarioPackage#getScenarioDef()
 * @model abstract="true"
 * @generated
 */
public interface ScenarioDef extends UCMmodelElement{
    /**
     * Returns the value of the '<em><b>Start Points</b></em>' reference list.
     * The list contents are of type {@link ucm.map.StartPoint}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Start Points</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Start Points</em>' reference list.
     * @see ucm.scenario.ScenarioPackage#getScenarioDef_StartPoints()
     * @model type="ucm.map.StartPoint" required="true"
     * @generated
     */
    EList getStartPoints();

    /**
     * Returns the value of the '<em><b>Ucmspec</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link ucm.UCMspec#getScenarioDefs <em>Scenario Defs</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ucmspec</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ucmspec</em>' container reference.
     * @see #setUcmspec(UCMspec)
     * @see ucm.scenario.ScenarioPackage#getScenarioDef_Ucmspec()
     * @see ucm.UCMspec#getScenarioDefs
     * @model opposite="scenarioDefs" required="true"
     * @generated
     */
    UCMspec getUcmspec();

    /**
     * Sets the value of the '{@link ucm.scenario.ScenarioDef#getUcmspec <em>Ucmspec</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ucmspec</em>' container reference.
     * @see #getUcmspec()
     * @generated
     */
    void setUcmspec(UCMspec value);

    /**
     * Returns the value of the '<em><b>Groups</b></em>' reference list.
     * The list contents are of type {@link ucm.scenario.ScenarioGroup}.
     * It is bidirectional and its opposite is '{@link ucm.scenario.ScenarioGroup#getScenarios <em>Scenarios</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Groups</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Groups</em>' reference list.
     * @see ucm.scenario.ScenarioPackage#getScenarioDef_Groups()
     * @see ucm.scenario.ScenarioGroup#getScenarios
     * @model type="ucm.scenario.ScenarioGroup" opposite="scenarios" required="true"
     * @generated
     */
    EList getGroups();

} // ScenarioDef
