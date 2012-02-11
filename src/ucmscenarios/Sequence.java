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
 * A representation of the model object '<em><b>Sequence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucmscenarios.Sequence#getParent <em>Parent</em>}</li>
 *   <li>{@link ucmscenarios.Sequence#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucmscenarios.UcmscenariosPackage#getSequence()
 * @model
 * @generated
 */
public interface Sequence extends SequenceElement {
	/**
	 * Returns the value of the '<em><b>Parent</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucmscenarios.Parallel#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' container reference.
	 * @see #setParent(Parallel)
	 * @see ucmscenarios.UcmscenariosPackage#getSequence_Parent()
	 * @see ucmscenarios.Parallel#getChildren
	 * @model opposite="children"
	 * @generated
	 */
	Parallel getParent();

	/**
	 * Sets the value of the '{@link ucmscenarios.Sequence#getParent <em>Parent</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' container reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(Parallel value);

	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link ucmscenarios.SequenceElement}.
	 * It is bidirectional and its opposite is '{@link ucmscenarios.SequenceElement#getSequence <em>Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see ucmscenarios.UcmscenariosPackage#getSequence_Children()
	 * @see ucmscenarios.SequenceElement#getSequence
	 * @model type="ucmscenarios.SequenceElement" opposite="sequence" containment="true" required="true"
	 * @generated
	 */
	EList getChildren();

} // Sequence