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
 * A representation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucmscenarios.Component#getScenarioSpec <em>Scenario Spec</em>}</li>
 *   <li>{@link ucmscenarios.Component#getInstances <em>Instances</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucmscenarios.UcmscenariosPackage#getComponent()
 * @model
 * @generated
 */
public interface Component extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Scenario Spec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucmscenarios.ScenarioSpec#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scenario Spec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenario Spec</em>' container reference.
	 * @see #setScenarioSpec(ScenarioSpec)
	 * @see ucmscenarios.UcmscenariosPackage#getComponent_ScenarioSpec()
	 * @see ucmscenarios.ScenarioSpec#getComponents
	 * @model opposite="components" required="true"
	 * @generated
	 */
	ScenarioSpec getScenarioSpec();

	/**
	 * Sets the value of the '{@link ucmscenarios.Component#getScenarioSpec <em>Scenario Spec</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scenario Spec</em>' container reference.
	 * @see #getScenarioSpec()
	 * @generated
	 */
	void setScenarioSpec(ScenarioSpec value);

	/**
	 * Returns the value of the '<em><b>Instances</b></em>' reference list.
	 * The list contents are of type {@link ucmscenarios.Instance}.
	 * It is bidirectional and its opposite is '{@link ucmscenarios.Instance#getDefinition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instances</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instances</em>' reference list.
	 * @see ucmscenarios.UcmscenariosPackage#getComponent_Instances()
	 * @see ucmscenarios.Instance#getDefinition
	 * @model type="ucmscenarios.Instance" opposite="definition"
	 * @generated
	 */
	EList getInstances();

} // Component