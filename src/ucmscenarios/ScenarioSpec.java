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
 * A representation of the model object '<em><b>Scenario Spec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucmscenarios.ScenarioSpec#getFilename <em>Filename</em>}</li>
 *   <li>{@link ucmscenarios.ScenarioSpec#getCreated <em>Created</em>}</li>
 *   <li>{@link ucmscenarios.ScenarioSpec#getModified <em>Modified</em>}</li>
 *   <li>{@link ucmscenarios.ScenarioSpec#getSpecVersion <em>Spec Version</em>}</li>
 *   <li>{@link ucmscenarios.ScenarioSpec#getComponents <em>Components</em>}</li>
 *   <li>{@link ucmscenarios.ScenarioSpec#getGroups <em>Groups</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucmscenarios.UcmscenariosPackage#getScenarioSpec()
 * @model
 * @generated
 */
public interface ScenarioSpec extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Filename</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filename</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filename</em>' attribute.
	 * @see #setFilename(String)
	 * @see ucmscenarios.UcmscenariosPackage#getScenarioSpec_Filename()
	 * @model
	 * @generated
	 */
	String getFilename();

	/**
	 * Sets the value of the '{@link ucmscenarios.ScenarioSpec#getFilename <em>Filename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filename</em>' attribute.
	 * @see #getFilename()
	 * @generated
	 */
	void setFilename(String value);

	/**
	 * Returns the value of the '<em><b>Created</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Created</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Created</em>' attribute.
	 * @see #setCreated(String)
	 * @see ucmscenarios.UcmscenariosPackage#getScenarioSpec_Created()
	 * @model
	 * @generated
	 */
	String getCreated();

	/**
	 * Sets the value of the '{@link ucmscenarios.ScenarioSpec#getCreated <em>Created</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Created</em>' attribute.
	 * @see #getCreated()
	 * @generated
	 */
	void setCreated(String value);

	/**
	 * Returns the value of the '<em><b>Modified</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modified</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modified</em>' attribute.
	 * @see #setModified(String)
	 * @see ucmscenarios.UcmscenariosPackage#getScenarioSpec_Modified()
	 * @model
	 * @generated
	 */
	String getModified();

	/**
	 * Sets the value of the '{@link ucmscenarios.ScenarioSpec#getModified <em>Modified</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modified</em>' attribute.
	 * @see #getModified()
	 * @generated
	 */
	void setModified(String value);

	/**
	 * Returns the value of the '<em><b>Spec Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Spec Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Spec Version</em>' attribute.
	 * @see #setSpecVersion(String)
	 * @see ucmscenarios.UcmscenariosPackage#getScenarioSpec_SpecVersion()
	 * @model
	 * @generated
	 */
	String getSpecVersion();

	/**
	 * Sets the value of the '{@link ucmscenarios.ScenarioSpec#getSpecVersion <em>Spec Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Spec Version</em>' attribute.
	 * @see #getSpecVersion()
	 * @generated
	 */
	void setSpecVersion(String value);

	/**
	 * Returns the value of the '<em><b>Components</b></em>' containment reference list.
	 * The list contents are of type {@link ucmscenarios.Component}.
	 * It is bidirectional and its opposite is '{@link ucmscenarios.Component#getScenarioSpec <em>Scenario Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Components</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Components</em>' containment reference list.
	 * @see ucmscenarios.UcmscenariosPackage#getScenarioSpec_Components()
	 * @see ucmscenarios.Component#getScenarioSpec
	 * @model type="ucmscenarios.Component" opposite="scenarioSpec" containment="true"
	 * @generated
	 */
	EList getComponents();

	/**
	 * Returns the value of the '<em><b>Groups</b></em>' containment reference list.
	 * The list contents are of type {@link ucmscenarios.ScenarioGroup}.
	 * It is bidirectional and its opposite is '{@link ucmscenarios.ScenarioGroup#getScenarioSpec <em>Scenario Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Groups</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Groups</em>' containment reference list.
	 * @see ucmscenarios.UcmscenariosPackage#getScenarioSpec_Groups()
	 * @see ucmscenarios.ScenarioGroup#getScenarioSpec
	 * @model type="ucmscenarios.ScenarioGroup" opposite="scenarioSpec" containment="true"
	 * @generated
	 */
	EList getGroups();

} // ScenarioSpec