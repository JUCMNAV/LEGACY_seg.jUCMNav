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
 * A representation of the model object '<em><b>Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Group of related scenario definitions.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.scenario.ScenarioGroup#getUcmspec <em>Ucmspec</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioGroup#getScenarios <em>Scenarios</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.scenario.ScenarioPackage#getScenarioGroup()
 * @model
 * @generated
 */
public interface ScenarioGroup extends UCMmodelElement {
    /**
	 * Returns the value of the '<em><b>Ucmspec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucm.UCMspec#getScenarioGroups <em>Scenario Groups</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ucmspec</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Ucmspec</em>' container reference.
	 * @see #setUcmspec(UCMspec)
	 * @see ucm.scenario.ScenarioPackage#getScenarioGroup_Ucmspec()
	 * @see ucm.UCMspec#getScenarioGroups
	 * @model opposite="scenarioGroups" required="true"
	 * @generated
	 */
    UCMspec getUcmspec();

    /**
	 * Sets the value of the '{@link ucm.scenario.ScenarioGroup#getUcmspec <em>Ucmspec</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ucmspec</em>' container reference.
	 * @see #getUcmspec()
	 * @generated
	 */
    void setUcmspec(UCMspec value);

    /**
	 * Returns the value of the '<em><b>Scenarios</b></em>' containment reference list.
	 * The list contents are of type {@link ucm.scenario.ScenarioDef}.
	 * It is bidirectional and its opposite is '{@link ucm.scenario.ScenarioDef#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Scenarios</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenarios</em>' containment reference list.
	 * @see ucm.scenario.ScenarioPackage#getScenarioGroup_Scenarios()
	 * @see ucm.scenario.ScenarioDef#getGroup
	 * @model type="ucm.scenario.ScenarioDef" opposite="group" containment="true"
	 * @generated
	 */
    EList getScenarios();

} // ScenarioGroup
