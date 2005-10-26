/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;

import org.eclipse.emf.common.util.EList;

import urncore.SpecificationConnection;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.LinkRef#getBeliefs <em>Beliefs</em>}</li>
 *   <li>{@link grl.LinkRef#getLink <em>Link</em>}</li>
 *   <li>{@link grl.LinkRef#getDependency <em>Dependency</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getLinkRef()
 * @model 
 * @generated
 */
public interface LinkRef extends SpecificationConnection {
    /**
     * Returns the value of the '<em><b>Beliefs</b></em>' reference list.
     * The list contents are of type {@link grl.Belief}.
     * It is bidirectional and its opposite is '{@link grl.Belief#getConnection <em>Connection</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Beliefs</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Beliefs</em>' reference list.
     * @see grl.GrlPackage#getLinkRef_Beliefs()
     * @see grl.Belief#getConnection
     * @model type="grl.Belief" opposite="connection"
     * @generated
     */
    EList getBeliefs();

    /**
     * Returns the value of the '<em><b>Link</b></em>' reference.
     * It is bidirectional and its opposite is '{@link grl.ElementLink#getRefs <em>Refs</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Link</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Link</em>' reference.
     * @see #setLink(ElementLink)
     * @see grl.GrlPackage#getLinkRef_Link()
     * @see grl.ElementLink#getRefs
     * @model opposite="refs" required="true"
     * @generated
     */
    ElementLink getLink();

    /**
     * Sets the value of the '{@link grl.LinkRef#getLink <em>Link</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Link</em>' reference.
     * @see #getLink()
     * @generated
     */
    void setLink(ElementLink value);

    /**
     * Returns the value of the '<em><b>Dependency</b></em>' reference.
     * It is bidirectional and its opposite is '{@link grl.Dependency#getSecondRefs <em>Second Refs</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Dependency</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Dependency</em>' reference.
     * @see #setDependency(Dependency)
     * @see grl.GrlPackage#getLinkRef_Dependency()
     * @see grl.Dependency#getSecondRefs
     * @model opposite="secondRefs" required="true"
     * @generated
     */
    Dependency getDependency();

    /**
     * Sets the value of the '{@link grl.LinkRef#getDependency <em>Dependency</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Dependency</em>' reference.
     * @see #getDependency()
     * @generated
     */
    void setDependency(Dependency value);

} // LinkRef
