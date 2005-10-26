/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>UC Mmodel Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.UCMmodelElement#getUrnLinks <em>Urn Links</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getUCMmodelElement()
 * @model abstract="true"
 * @generated
 */
public interface UCMmodelElement extends URNmodelElement {
    /**
     * Returns the value of the '<em><b>Urn Links</b></em>' reference list.
     * The list contents are of type {@link urn.URNlink}.
     * It is bidirectional and its opposite is '{@link urn.URNlink#getUcmElems <em>Ucm Elems</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Urn Links</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Urn Links</em>' reference list.
     * @see urncore.UrncorePackage#getUCMmodelElement_UrnLinks()
     * @see urn.URNlink#getUcmElems
     * @model type="urn.URNlink" opposite="ucmElems"
     * @generated
     */
    EList getUrnLinks();

} // UCMmodelElement
