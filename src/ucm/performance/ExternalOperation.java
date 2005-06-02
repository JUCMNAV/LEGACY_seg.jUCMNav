/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>External Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.performance.ExternalOperation#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.performance.PerformancePackage#getExternalOperation()
 * @model 
 * @generated
 */
public interface ExternalOperation extends ActiveResource{
    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see ucm.performance.PerformancePackage#getExternalOperation_Description()
     * @model 
     * @generated
     */
	String getDescription();

    /**
     * Sets the value of the '{@link ucm.performance.ExternalOperation#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
	void setDescription(String value);

} // ExternalOperation
