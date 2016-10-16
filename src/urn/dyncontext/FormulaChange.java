/**
 */
package urn.dyncontext;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Formula Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link urn.dyncontext.FormulaChange#getFormula <em>Formula</em>}</li>
 * </ul>
 *
 * @see urn.dyncontext.DyncontextPackage#getFormulaChange()
 * @model
 * @generated
 */
public interface FormulaChange extends NumericChange {
	/**
	 * Returns the value of the '<em><b>Formula</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Formula</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Formula</em>' attribute.
	 * @see #setFormula(String)
	 * @see urn.dyncontext.DyncontextPackage#getFormulaChange_Formula()
	 * @model
	 * @generated
	 */
	String getFormula();

	/**
	 * Sets the value of the '{@link urn.dyncontext.FormulaChange#getFormula <em>Formula</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Formula</em>' attribute.
	 * @see #getFormula()
	 * @generated
	 */
	void setFormula(String value);

} // FormulaChange
