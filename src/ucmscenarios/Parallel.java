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
 * A representation of the model object '<em><b>Parallel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucmscenarios.Parallel#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucmscenarios.UcmscenariosPackage#getParallel()
 * @model
 * @generated
 */
public interface Parallel extends SequenceElement {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link ucmscenarios.Sequence}.
	 * It is bidirectional and its opposite is '{@link ucmscenarios.Sequence#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see ucmscenarios.UcmscenariosPackage#getParallel_Children()
	 * @see ucmscenarios.Sequence#getParent
	 * @model type="ucmscenarios.Sequence" opposite="parent" containment="true" lower="2"
	 * @generated
	 */
	EList getChildren();

} // Parallel