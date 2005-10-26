/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Decomposition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.Decomposition#getSrc <em>Src</em>}</li>
 *   <li>{@link grl.Decomposition#getDest <em>Dest</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getDecomposition()
 * @model 
 * @generated
 */
public interface Decomposition extends ElementLink {
    /**
     * Returns the value of the '<em><b>Src</b></em>' reference.
     * It is bidirectional and its opposite is '{@link grl.IntentionalElement#getDecompositionSrc <em>Decomposition Src</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Src</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Src</em>' reference.
     * @see #setSrc(IntentionalElement)
     * @see grl.GrlPackage#getDecomposition_Src()
     * @see grl.IntentionalElement#getDecompositionSrc
     * @model opposite="decompositionSrc" required="true"
     * @generated
     */
    IntentionalElement getSrc();

    /**
     * Sets the value of the '{@link grl.Decomposition#getSrc <em>Src</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Src</em>' reference.
     * @see #getSrc()
     * @generated
     */
    void setSrc(IntentionalElement value);

    /**
     * Returns the value of the '<em><b>Dest</b></em>' reference.
     * It is bidirectional and its opposite is '{@link grl.IntentionalElement#getDecompositionDest <em>Decomposition Dest</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Dest</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Dest</em>' reference.
     * @see #setDest(IntentionalElement)
     * @see grl.GrlPackage#getDecomposition_Dest()
     * @see grl.IntentionalElement#getDecompositionDest
     * @model opposite="decompositionDest" required="true"
     * @generated
     */
    IntentionalElement getDest();

    /**
     * Sets the value of the '{@link grl.Decomposition#getDest <em>Dest</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Dest</em>' reference.
     * @see #getDest()
     * @generated
     */
    void setDest(IntentionalElement value);

} // Decomposition
