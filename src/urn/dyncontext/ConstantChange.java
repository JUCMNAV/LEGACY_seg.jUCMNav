/**
 */
package urn.dyncontext;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constant Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link urn.dyncontext.ConstantChange#getNewValue <em>New Value</em>}</li>
 * </ul>
 *
 * @see urn.dyncontext.DyncontextPackage#getConstantChange()
 * @model
 * @generated
 */
public interface ConstantChange extends NumericChange {
	/**
	 * Returns the value of the '<em><b>New Value</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>New Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Value</em>' attribute.
	 * @see #setNewValue(int)
	 * @see urn.dyncontext.DyncontextPackage#getConstantChange_NewValue()
	 * @model default="0"
	 * @generated
	 */
	int getNewValue();

	/**
	 * Sets the value of the '{@link urn.dyncontext.ConstantChange#getNewValue <em>New Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Value</em>' attribute.
	 * @see #getNewValue()
	 * @generated
	 */
	void setNewValue(int value);

} // ConstantChange
