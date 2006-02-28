/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urn;

import org.eclipse.emf.ecore.EObject;

import urncore.GRLmodelElement;
import urncore.UCMmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>UR Nlink</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urn.URNlink#getUrnspec <em>Urnspec</em>}</li>
 *   <li>{@link urn.URNlink#getGrlModelElements <em>Grl Model Elements</em>}</li>
 *   <li>{@link urn.URNlink#getUcmModelElements <em>Ucm Model Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see urn.UrnPackage#getURNlink()
 * @model
 * @generated
 */
public interface URNlink extends EObject{
    /**
     * Returns the value of the '<em><b>Urnspec</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link urn.URNspec#getUrnLinks <em>Urn Links</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Urnspec</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Urnspec</em>' container reference.
     * @see #setUrnspec(URNspec)
     * @see urn.UrnPackage#getURNlink_Urnspec()
     * @see urn.URNspec#getUrnLinks
     * @model opposite="urnLinks" required="true"
     * @generated
     */
    URNspec getUrnspec();

    /**
     * Sets the value of the '{@link urn.URNlink#getUrnspec <em>Urnspec</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Urnspec</em>' container reference.
     * @see #getUrnspec()
     * @generated
     */
    void setUrnspec(URNspec value);

    /**
     * Returns the value of the '<em><b>Grl Model Elements</b></em>' reference.
     * It is bidirectional and its opposite is '{@link urncore.GRLmodelElement#getUrnlinks <em>Urnlinks</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Grl Model Elements</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Grl Model Elements</em>' reference.
     * @see #setGrlModelElements(GRLmodelElement)
     * @see urn.UrnPackage#getURNlink_GrlModelElements()
     * @see urncore.GRLmodelElement#getUrnlinks
     * @model opposite="urnlinks" required="true"
     * @generated
     */
    GRLmodelElement getGrlModelElements();

    /**
     * Sets the value of the '{@link urn.URNlink#getGrlModelElements <em>Grl Model Elements</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Grl Model Elements</em>' reference.
     * @see #getGrlModelElements()
     * @generated
     */
    void setGrlModelElements(GRLmodelElement value);

    /**
     * Returns the value of the '<em><b>Ucm Model Elements</b></em>' reference.
     * It is bidirectional and its opposite is '{@link urncore.UCMmodelElement#getUrnlinks <em>Urnlinks</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ucm Model Elements</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Ucm Model Elements</em>' reference.
     * @see #setUcmModelElements(UCMmodelElement)
     * @see urn.UrnPackage#getURNlink_UcmModelElements()
     * @see urncore.UCMmodelElement#getUrnlinks
     * @model opposite="urnlinks" required="true"
     * @generated
     */
    UCMmodelElement getUcmModelElements();

    /**
     * Sets the value of the '{@link urn.URNlink#getUcmModelElements <em>Ucm Model Elements</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ucm Model Elements</em>' reference.
     * @see #getUcmModelElements()
     * @generated
     */
    void setUcmModelElements(UCMmodelElement value);

} // URNlink
