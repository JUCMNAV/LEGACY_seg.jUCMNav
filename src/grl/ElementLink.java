/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;

import org.eclipse.emf.common.util.EList;

import urncore.GRLmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.ElementLink#getRefs <em>Refs</em>}</li>
 *   <li>{@link grl.ElementLink#getGrlspec <em>Grlspec</em>}</li>
 *   <li>{@link grl.ElementLink#getDest <em>Dest</em>}</li>
 *   <li>{@link grl.ElementLink#getSrc <em>Src</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getElementLink()
 * @model
 * @generated
 */
public interface ElementLink extends GRLmodelElement {
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
	 * @model type="grl.LinkRef" opposite="link"
	 * @generated
	 */
    EList getRefs();

    /**
	 * Returns the value of the '<em><b>Grlspec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link grl.GRLspec#getLinks <em>Links</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Grlspec</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Grlspec</em>' container reference.
	 * @see #setGrlspec(GRLspec)
	 * @see grl.GrlPackage#getElementLink_Grlspec()
	 * @see grl.GRLspec#getLinks
	 * @model opposite="links" required="true"
	 * @generated
	 */
    GRLspec getGrlspec();

    /**
	 * Sets the value of the '{@link grl.ElementLink#getGrlspec <em>Grlspec</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grlspec</em>' container reference.
	 * @see #getGrlspec()
	 * @generated
	 */
    void setGrlspec(GRLspec value);

    /**
	 * Returns the value of the '<em><b>Src</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link grl.GRLLinkableElement#getLinksSrc <em>Links Src</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Src</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Src</em>' reference.
	 * @see #setSrc(GRLLinkableElement)
	 * @see grl.GrlPackage#getElementLink_Src()
	 * @see grl.GRLLinkableElement#getLinksSrc
	 * @model opposite="linksSrc" required="true"
	 * @generated
	 */
    GRLLinkableElement getSrc();

    /**
	 * Sets the value of the '{@link grl.ElementLink#getSrc <em>Src</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Src</em>' reference.
	 * @see #getSrc()
	 * @generated
	 */
	void setSrc(GRLLinkableElement value);

				/**
	 * Returns the value of the '<em><b>Dest</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link grl.GRLLinkableElement#getLinksDest <em>Links Dest</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Dest</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Dest</em>' reference.
	 * @see #setDest(GRLLinkableElement)
	 * @see grl.GrlPackage#getElementLink_Dest()
	 * @see grl.GRLLinkableElement#getLinksDest
	 * @model opposite="linksDest" required="true"
	 * @generated
	 */
    GRLLinkableElement getDest();

    /**
	 * Sets the value of the '{@link grl.ElementLink#getDest <em>Dest</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dest</em>' reference.
	 * @see #getDest()
	 * @generated
	 */
	void setDest(GRLLinkableElement value);

} // ElementLink
