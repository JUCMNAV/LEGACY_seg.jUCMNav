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
 *   <li>{@link ucm.scenario.ScenarioGroup#getUrnspec <em>Urnspec</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioGroup#getScenarioDef <em>Scenario Def</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.scenario.ScenarioPackage#getScenarioGroup()
 * @model 
 * @generated
 */
public interface ScenarioGroup extends UCMmodelElement {
	/**
	 * Returns the value of the '<em><b>Urnspec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucm.UCMspec#getScenarioGroups <em>Scenario Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Urnspec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Urnspec</em>' container reference.
	 * @see #setUrnspec(UCMspec)
	 * @see ucm.scenario.ScenarioPackage#getScenarioGroup_Urnspec()
	 * @see ucm.UCMspec#getScenarioGroups
	 * @model opposite="scenarioGroups" required="true"
	 * @generated
	 */
	UCMspec getUrnspec();

	/**
	 * Sets the value of the '{@link ucm.scenario.ScenarioGroup#getUrnspec <em>Urnspec</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Urnspec</em>' container reference.
	 * @see #getUrnspec()
	 * @generated
	 */
	void setUrnspec(UCMspec value);

	/**
	 * Returns the value of the '<em><b>Scenario Def</b></em>' containment reference list.
	 * The list contents are of type {@link ucm.scenario.ScenarioDef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scenario Def</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenario Def</em>' containment reference list.
	 * @see ucm.scenario.ScenarioPackage#getScenarioGroup_ScenarioDef()
	 * @model type="ucm.scenario.ScenarioDef" containment="true"
	 * @generated
	 */
	EList getScenarioDef();

} // ScenarioGroup
