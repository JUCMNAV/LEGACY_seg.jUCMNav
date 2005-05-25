/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abort</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.Abort#getCondition <em>Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getAbort()
 * @model 
 * @generated
 */
public interface Abort extends PathNode {
	/**
	 * Returns the value of the '<em><b>Condition</b></em>' reference list.
	 * The list contents are of type {@link urncore.Condition}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' reference list.
	 * @see ucm.map.MapPackage#getAbort_Condition()
	 * @model type="urncore.Condition"
	 * @generated
	 */
	EList getCondition();

} // Abort
