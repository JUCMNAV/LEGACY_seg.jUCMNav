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
 * A representation of the model object '<em><b>Scenario Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucmscenarios.ScenarioDef#getGroup <em>Group</em>}</li>
 *   <li>{@link ucmscenarios.ScenarioDef#getInstances <em>Instances</em>}</li>
 *   <li>{@link ucmscenarios.ScenarioDef#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucmscenarios.UcmscenariosPackage#getScenarioDef()
 * @model
 * @generated
 */
public interface ScenarioDef extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Group</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucmscenarios.ScenarioGroup#getScenarios <em>Scenarios</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' container reference.
	 * @see #setGroup(ScenarioGroup)
	 * @see ucmscenarios.UcmscenariosPackage#getScenarioDef_Group()
	 * @see ucmscenarios.ScenarioGroup#getScenarios
	 * @model opposite="scenarios" required="true"
	 * @generated
	 */
	ScenarioGroup getGroup();

	/**
	 * Sets the value of the '{@link ucmscenarios.ScenarioDef#getGroup <em>Group</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group</em>' container reference.
	 * @see #getGroup()
	 * @generated
	 */
	void setGroup(ScenarioGroup value);

	/**
	 * Returns the value of the '<em><b>Instances</b></em>' containment reference list.
	 * The list contents are of type {@link ucmscenarios.Instance}.
	 * It is bidirectional and its opposite is '{@link ucmscenarios.Instance#getScenario <em>Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instances</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instances</em>' containment reference list.
	 * @see ucmscenarios.UcmscenariosPackage#getScenarioDef_Instances()
	 * @see ucmscenarios.Instance#getScenario
	 * @model type="ucmscenarios.Instance" opposite="scenario" containment="true"
	 * @generated
	 */
	EList getInstances();

	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link ucmscenarios.SequenceElement}.
	 * It is bidirectional and its opposite is '{@link ucmscenarios.SequenceElement#getParentScenario <em>Parent Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see ucmscenarios.UcmscenariosPackage#getScenarioDef_Children()
	 * @see ucmscenarios.SequenceElement#getParentScenario
	 * @model type="ucmscenarios.SequenceElement" opposite="parentScenario" containment="true" required="true"
	 * @generated
	 */
	EList getChildren();

} // ScenarioDef