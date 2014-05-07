/**
 */
package org.etsi.mts.tdl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Multiple Combined Behaviour</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.MultipleCombinedBehaviour#getBlocks <em>Block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.etsi.mts.tdl.TdlPackage#getMultipleCombinedBehaviour()
 * @model abstract="true"
 * @generated
 */
public interface MultipleCombinedBehaviour extends CombinedBehaviour {
	/**
	 * Returns the value of the '<em><b>Block</b></em>' containment reference list.
	 * The list contents are of type {@link org.etsi.mts.tdl.Block}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Block</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Block</em>' containment reference list.
	 * @see org.etsi.mts.tdl.TdlPackage#getMultipleCombinedBehaviour_Block()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Block> getBlocks();

	/**
	 * Creates a new {@link org.etsi.mts.tdl.Block} and appends it to the '<em><b>Block</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return The new {@link org.etsi.mts.tdl.Block}.
	 * @see #getBlocks()
	 * @generated
	 */
	Block createBlock();

} // MultipleCombinedBehaviour
