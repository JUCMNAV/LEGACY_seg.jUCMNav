/**
 */
package urn.dyncontext;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Text Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link urn.dyncontext.TextChange#getNewValue <em>New Value</em>}</li>
 * </ul>
 *
 * @see urn.dyncontext.DyncontextPackage#getTextChange()
 * @model
 * @generated
 */
public interface TextChange extends PropertyChange {
	/**
	 * Returns the value of the '<em><b>New Value</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>New Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Value</em>' attribute.
	 * @see #setNewValue(String)
	 * @see urn.dyncontext.DyncontextPackage#getTextChange_NewValue()
	 * @model default=""
	 * @generated
	 */
	String getNewValue();

	/**
	 * Sets the value of the '{@link urn.dyncontext.TextChange#getNewValue <em>New Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Value</em>' attribute.
	 * @see #getNewValue()
	 * @generated
	 */
	void setNewValue(String value);

} // TextChange
