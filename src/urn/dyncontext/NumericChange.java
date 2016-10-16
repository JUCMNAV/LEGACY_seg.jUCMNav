/**
 */
package urn.dyncontext;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Numeric Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link urn.dyncontext.NumericChange#isSufficientOnceAchieved <em>Sufficient Once Achieved</em>}</li>
 * </ul>
 *
 * @see urn.dyncontext.DyncontextPackage#getNumericChange()
 * @model abstract="true"
 * @generated
 */
public interface NumericChange extends PropertyChange {
	/**
	 * Returns the value of the '<em><b>Sufficient Once Achieved</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sufficient Once Achieved</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sufficient Once Achieved</em>' attribute.
	 * @see #setSufficientOnceAchieved(boolean)
	 * @see urn.dyncontext.DyncontextPackage#getNumericChange_SufficientOnceAchieved()
	 * @model default="false"
	 * @generated
	 */
	boolean isSufficientOnceAchieved();

	/**
	 * Sets the value of the '{@link urn.dyncontext.NumericChange#isSufficientOnceAchieved <em>Sufficient Once Achieved</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sufficient Once Achieved</em>' attribute.
	 * @see #isSufficientOnceAchieved()
	 * @generated
	 */
	void setSufficientOnceAchieved(boolean value);

} // NumericChange
