/**
 */
package urn.dyncontext;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link urn.dyncontext.BooleanChange#isNewValue <em>New Value</em>}</li>
 * </ul>
 *
 * @see urn.dyncontext.DyncontextPackage#getBooleanChange()
 * @model
 * @generated
 */
public interface BooleanChange extends PropertyChange {
	/**
	 * Returns the value of the '<em><b>New Value</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>New Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Value</em>' attribute.
	 * @see #setNewValue(boolean)
	 * @see urn.dyncontext.DyncontextPackage#getBooleanChange_NewValue()
	 * @model default="false"
	 * @generated
	 */
	boolean isNewValue();

	/**
	 * Sets the value of the '{@link urn.dyncontext.BooleanChange#isNewValue <em>New Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Value</em>' attribute.
	 * @see #isNewValue()
	 * @generated
	 */
	void setNewValue(boolean value);

} // BooleanChange
