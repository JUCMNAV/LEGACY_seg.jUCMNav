/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Belief</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.Belief#getAuthor <em>Author</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getBelief()
 * @model
 * @generated
 */
public interface Belief extends GRLNode {
    /**
	 * Returns the value of the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Author</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Author</em>' attribute.
	 * @see #setAuthor(String)
	 * @see grl.GrlPackage#getBelief_Author()
	 * @model
	 * @generated
	 */
    String getAuthor();

    /**
	 * Sets the value of the '{@link grl.Belief#getAuthor <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Author</em>' attribute.
	 * @see #getAuthor()
	 * @generated
	 */
    void setAuthor(String value);

} // Belief
