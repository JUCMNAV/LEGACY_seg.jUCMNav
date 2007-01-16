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
 * A representation of the model object '<em><b>Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucmscenarios.Instance#getScenario <em>Scenario</em>}</li>
 *   <li>{@link ucmscenarios.Instance#getDefinition <em>Definition</em>}</li>
 *   <li>{@link ucmscenarios.Instance#getElements <em>Elements</em>}</li>
 *   <li>{@link ucmscenarios.Instance#getSent <em>Sent</em>}</li>
 *   <li>{@link ucmscenarios.Instance#getReceived <em>Received</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucmscenarios.UcmscenariosPackage#getInstance()
 * @model
 * @generated
 */
public interface Instance extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Scenario</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucmscenarios.ScenarioDef#getInstances <em>Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scenario</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenario</em>' container reference.
	 * @see #setScenario(ScenarioDef)
	 * @see ucmscenarios.UcmscenariosPackage#getInstance_Scenario()
	 * @see ucmscenarios.ScenarioDef#getInstances
	 * @model opposite="instances" required="true"
	 * @generated
	 */
	ScenarioDef getScenario();

	/**
	 * Sets the value of the '{@link ucmscenarios.Instance#getScenario <em>Scenario</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scenario</em>' container reference.
	 * @see #getScenario()
	 * @generated
	 */
	void setScenario(ScenarioDef value);

	/**
	 * Returns the value of the '<em><b>Definition</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ucmscenarios.Component#getInstances <em>Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definition</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Definition</em>' reference.
	 * @see #setDefinition(Component)
	 * @see ucmscenarios.UcmscenariosPackage#getInstance_Definition()
	 * @see ucmscenarios.Component#getInstances
	 * @model opposite="instances" required="true"
	 * @generated
	 */
	Component getDefinition();

	/**
	 * Sets the value of the '{@link ucmscenarios.Instance#getDefinition <em>Definition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Definition</em>' reference.
	 * @see #getDefinition()
	 * @generated
	 */
	void setDefinition(Component value);

	/**
	 * Returns the value of the '<em><b>Elements</b></em>' reference list.
	 * The list contents are of type {@link ucmscenarios.SequenceElement}.
	 * It is bidirectional and its opposite is '{@link ucmscenarios.SequenceElement#getInstance <em>Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' reference list.
	 * @see ucmscenarios.UcmscenariosPackage#getInstance_Elements()
	 * @see ucmscenarios.SequenceElement#getInstance
	 * @model type="ucmscenarios.SequenceElement" opposite="instance"
	 * @generated
	 */
	EList getElements();

	/**
	 * Returns the value of the '<em><b>Sent</b></em>' reference list.
	 * The list contents are of type {@link ucmscenarios.Message}.
	 * It is bidirectional and its opposite is '{@link ucmscenarios.Message#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sent</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sent</em>' reference list.
	 * @see ucmscenarios.UcmscenariosPackage#getInstance_Sent()
	 * @see ucmscenarios.Message#getSource
	 * @model type="ucmscenarios.Message" opposite="source"
	 * @generated
	 */
	EList getSent();

	/**
	 * Returns the value of the '<em><b>Received</b></em>' reference list.
	 * The list contents are of type {@link ucmscenarios.Message}.
	 * It is bidirectional and its opposite is '{@link ucmscenarios.Message#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Received</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Received</em>' reference list.
	 * @see ucmscenarios.UcmscenariosPackage#getInstance_Received()
	 * @see ucmscenarios.Message#getTarget
	 * @model type="ucmscenarios.Message" opposite="target"
	 * @generated
	 */
	EList getReceived();

} // Instance