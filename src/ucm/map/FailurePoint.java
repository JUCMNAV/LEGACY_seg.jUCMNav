/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Failure Point</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.FailurePoint#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getFailurePoint()
 * @model
 * @generated
 */
public interface FailurePoint extends PathNode {
    /**
	 * Returns the value of the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' attribute.
	 * @see #setExpression(String)
	 * @see ucm.map.MapPackage#getFailurePoint_Expression()
	 * @model
	 * @generated
	 */
    String getExpression();

    /**
	 * Sets the value of the '{@link ucm.map.FailurePoint#getExpression <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' attribute.
	 * @see #getExpression()
	 * @generated
	 */
    void setExpression(String value);

} // FailurePoint
