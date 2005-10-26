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
 *   <li>{@link ucm.scenario.ScenarioDef#getUrnspec <em>Urnspec</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.scenario.ScenarioPackage#getScenarioDef()
 * @model 
 * @generated
 */
public interface ScenarioDef extends UCMmodelElement {
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
     * Returns the value of the '<em><b>Urnspec</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link ucm.UCMspec#getScenarioDefs <em>Scenario Defs</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Urnspec</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Urnspec</em>' container reference.
     * @see #setUrnspec(UCMspec)
     * @see ucm.scenario.ScenarioPackage#getScenarioDef_Urnspec()
     * @see ucm.UCMspec#getScenarioDefs
     * @model opposite="scenarioDefs" required="true"
     * @generated
     */
    UCMspec getUrnspec();

    /**
     * Sets the value of the '{@link ucm.scenario.ScenarioDef#getUrnspec <em>Urnspec</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Urnspec</em>' container reference.
     * @see #getUrnspec()
     * @generated
     */
    void setUrnspec(UCMspec value);

} // ScenarioDef
