/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.scenario;

import org.eclipse.emf.common.util.EList;

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
