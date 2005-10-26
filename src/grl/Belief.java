/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;

import urncore.GRLmodelElement;
import urncore.SpecificationNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Belief</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.Belief#getConnection <em>Connection</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getBelief()
 * @model 
 * @generated
 */
public interface Belief extends GRLmodelElement, SpecificationNode {
    /**
     * Returns the value of the '<em><b>Connection</b></em>' reference.
     * It is bidirectional and its opposite is '{@link grl.LinkRef#getBeliefs <em>Beliefs</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Connection</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Connection</em>' reference.
     * @see #setConnection(LinkRef)
     * @see grl.GrlPackage#getBelief_Connection()
     * @see grl.LinkRef#getBeliefs
     * @model opposite="beliefs" required="true"
     * @generated
     */
    LinkRef getConnection();

    /**
     * Sets the value of the '{@link grl.Belief#getConnection <em>Connection</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Connection</em>' reference.
     * @see #getConnection()
     * @generated
     */
    void setConnection(LinkRef value);

} // Belief
