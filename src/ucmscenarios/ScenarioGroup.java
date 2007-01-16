/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucmscenarios;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scenario Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucmscenarios.ScenarioGroup#getScenarioSpec <em>Scenario Spec</em>}</li>
 *   <li>{@link ucmscenarios.ScenarioGroup#getScenarios <em>Scenarios</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucmscenarios.UcmscenariosPackage#getScenarioGroup()
 * @model
 * @generated
 */
public interface ScenarioGroup extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Scenario Spec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucmscenarios.ScenarioSpec#getGroups <em>Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scenario Spec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenario Spec</em>' container reference.
	 * @see #setScenarioSpec(ScenarioSpec)
	 * @see ucmscenarios.UcmscenariosPackage#getScenarioGroup_ScenarioSpec()
	 * @see ucmscenarios.ScenarioSpec#getGroups
	 * @model opposite="groups" required="true"
	 * @generated
	 */
	ScenarioSpec getScenarioSpec();

	/**
	 * Sets the value of the '{@link ucmscenarios.ScenarioGroup#getScenarioSpec <em>Scenario Spec</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scenario Spec</em>' container reference.
	 * @see #getScenarioSpec()
	 * @generated
	 */
	void setScenarioSpec(ScenarioSpec value);

	/**
	 * Returns the value of the '<em><b>Scenarios</b></em>' containment reference list.
	 * The list contents are of type {@link ucmscenarios.ScenarioDef}.
	 * It is bidirectional and its opposite is '{@link ucmscenarios.ScenarioDef#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scenarios</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenarios</em>' containment reference list.
	 * @see ucmscenarios.UcmscenariosPackage#getScenarioGroup_Scenarios()
	 * @see ucmscenarios.ScenarioDef#getGroup
	 * @model type="ucmscenarios.ScenarioDef" opposite="group" containment="true"
	 * @generated
	 */
	EList getScenarios();

} // ScenarioGroup