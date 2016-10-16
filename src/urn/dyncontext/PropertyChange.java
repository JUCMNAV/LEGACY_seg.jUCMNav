/**
 */
package urn.dyncontext;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link urn.dyncontext.PropertyChange#getAffectedProperty <em>Affected Property</em>}</li>
 * </ul>
 *
 * @see urn.dyncontext.DyncontextPackage#getPropertyChange()
 * @model abstract="true"
 * @generated
 */
public interface PropertyChange extends Change {
	/**
	 * Returns the value of the '<em><b>Affected Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Affected Property</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Affected Property</em>' attribute.
	 * @see #setAffectedProperty(String)
	 * @see urn.dyncontext.DyncontextPackage#getPropertyChange_AffectedProperty()
	 * @model
	 * @generated
	 */
	String getAffectedProperty();

	/**
	 * Sets the value of the '{@link urn.dyncontext.PropertyChange#getAffectedProperty <em>Affected Property</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Affected Property</em>' attribute.
	 * @see #getAffectedProperty()
	 * @generated
	 */
	void setAffectedProperty(String value);

} // PropertyChange
