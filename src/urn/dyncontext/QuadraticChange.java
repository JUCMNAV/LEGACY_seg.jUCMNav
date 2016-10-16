/**
 */
package urn.dyncontext;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Quadratic Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link urn.dyncontext.QuadraticChange#getQuadraticCoefficient <em>Quadratic Coefficient</em>}</li>
 *   <li>{@link urn.dyncontext.QuadraticChange#getLinearCoefficient <em>Linear Coefficient</em>}</li>
 *   <li>{@link urn.dyncontext.QuadraticChange#getConstant <em>Constant</em>}</li>
 * </ul>
 *
 * @see urn.dyncontext.DyncontextPackage#getQuadraticChange()
 * @model
 * @generated
 */
public interface QuadraticChange extends NumericChange {
	/**
	 * Returns the value of the '<em><b>Quadratic Coefficient</b></em>' attribute.
	 * The default value is <code>"0.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quadratic Coefficient</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Quadratic Coefficient</em>' attribute.
	 * @see #setQuadraticCoefficient(float)
	 * @see urn.dyncontext.DyncontextPackage#getQuadraticChange_QuadraticCoefficient()
	 * @model default="0.0"
	 * @generated
	 */
	float getQuadraticCoefficient();

	/**
	 * Sets the value of the '{@link urn.dyncontext.QuadraticChange#getQuadraticCoefficient <em>Quadratic Coefficient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Quadratic Coefficient</em>' attribute.
	 * @see #getQuadraticCoefficient()
	 * @generated
	 */
	void setQuadraticCoefficient(float value);

	/**
	 * Returns the value of the '<em><b>Linear Coefficient</b></em>' attribute.
	 * The default value is <code>"0.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Linear Coefficient</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Linear Coefficient</em>' attribute.
	 * @see #setLinearCoefficient(float)
	 * @see urn.dyncontext.DyncontextPackage#getQuadraticChange_LinearCoefficient()
	 * @model default="0.0"
	 * @generated
	 */
	float getLinearCoefficient();

	/**
	 * Sets the value of the '{@link urn.dyncontext.QuadraticChange#getLinearCoefficient <em>Linear Coefficient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Linear Coefficient</em>' attribute.
	 * @see #getLinearCoefficient()
	 * @generated
	 */
	void setLinearCoefficient(float value);

	/**
	 * Returns the value of the '<em><b>Constant</b></em>' attribute.
	 * The default value is <code>"0.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constant</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constant</em>' attribute.
	 * @see #setConstant(float)
	 * @see urn.dyncontext.DyncontextPackage#getQuadraticChange_Constant()
	 * @model default="0.0"
	 * @generated
	 */
	float getConstant();

	/**
	 * Sets the value of the '{@link urn.dyncontext.QuadraticChange#getConstant <em>Constant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constant</em>' attribute.
	 * @see #getConstant()
	 * @generated
	 */
	void setConstant(float value);

} // QuadraticChange
