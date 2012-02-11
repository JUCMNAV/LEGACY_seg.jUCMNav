/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucmscenarios;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sequence Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucmscenarios.SequenceElement#getParentScenario <em>Parent Scenario</em>}</li>
 *   <li>{@link ucmscenarios.SequenceElement#getSequence <em>Sequence</em>}</li>
 *   <li>{@link ucmscenarios.SequenceElement#getInstance <em>Instance</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucmscenarios.UcmscenariosPackage#getSequenceElement()
 * @model
 * @generated
 */
public interface SequenceElement extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Parent Scenario</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucmscenarios.ScenarioDef#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Scenario</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Scenario</em>' container reference.
	 * @see #setParentScenario(ScenarioDef)
	 * @see ucmscenarios.UcmscenariosPackage#getSequenceElement_ParentScenario()
	 * @see ucmscenarios.ScenarioDef#getChildren
	 * @model opposite="children"
	 * @generated
	 */
	ScenarioDef getParentScenario();

	/**
	 * Sets the value of the '{@link ucmscenarios.SequenceElement#getParentScenario <em>Parent Scenario</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent Scenario</em>' container reference.
	 * @see #getParentScenario()
	 * @generated
	 */
	void setParentScenario(ScenarioDef value);

	/**
	 * Returns the value of the '<em><b>Sequence</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucmscenarios.Sequence#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sequence</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sequence</em>' container reference.
	 * @see #setSequence(Sequence)
	 * @see ucmscenarios.UcmscenariosPackage#getSequenceElement_Sequence()
	 * @see ucmscenarios.Sequence#getChildren
	 * @model opposite="children"
	 * @generated
	 */
	Sequence getSequence();

	/**
	 * Sets the value of the '{@link ucmscenarios.SequenceElement#getSequence <em>Sequence</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sequence</em>' container reference.
	 * @see #getSequence()
	 * @generated
	 */
	void setSequence(Sequence value);

	/**
	 * Returns the value of the '<em><b>Instance</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ucmscenarios.Instance#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instance</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instance</em>' reference.
	 * @see #setInstance(Instance)
	 * @see ucmscenarios.UcmscenariosPackage#getSequenceElement_Instance()
	 * @see ucmscenarios.Instance#getElements
	 * @model opposite="elements"
	 * @generated
	 */
	Instance getInstance();

	/**
	 * Sets the value of the '{@link ucmscenarios.SequenceElement#getInstance <em>Instance</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instance</em>' reference.
	 * @see #getInstance()
	 * @generated
	 */
	void setInstance(Instance value);

} // SequenceElement