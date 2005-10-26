/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.ElementLink#getRefs <em>Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getElementLink()
 * @model 
 * @generated
 */
public interface ElementLink extends EObject {
    /**
     * Returns the value of the '<em><b>Refs</b></em>' reference list.
     * The list contents are of type {@link grl.LinkRef}.
     * It is bidirectional and its opposite is '{@link grl.LinkRef#getLink <em>Link</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Refs</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Refs</em>' reference list.
     * @see grl.GrlPackage#getElementLink_Refs()
     * @see grl.LinkRef#getLink
     * @model type="grl.LinkRef" opposite="link" required="true"
     * @generated
     */
    EList getRefs();

} // ElementLink
