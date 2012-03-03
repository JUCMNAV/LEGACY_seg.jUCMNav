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
 * A representation of the model object '<em><b>GRL Linkable Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.GRLLinkableElement#getLinksDest <em>Links Dest</em>}</li>
 *   <li>{@link grl.GRLLinkableElement#getLinksSrc <em>Links Src</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getGRLLinkableElement()
 * @model abstract="true"
 * @generated
 */
public interface GRLLinkableElement extends GRLmodelElement {
	/**
	 * Returns the value of the '<em><b>Links Dest</b></em>' reference list.
	 * The list contents are of type {@link grl.ElementLink}.
	 * It is bidirectional and its opposite is '{@link grl.ElementLink#getDest <em>Dest</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Links Dest</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Links Dest</em>' reference list.
	 * @see grl.GrlPackage#getGRLLinkableElement_LinksDest()
	 * @see grl.ElementLink#getDest
	 * @model type="grl.ElementLink" opposite="dest"
	 * @generated
	 */
	EList getLinksDest();

	/**
	 * Returns the value of the '<em><b>Links Src</b></em>' reference list.
	 * The list contents are of type {@link grl.ElementLink}.
	 * It is bidirectional and its opposite is '{@link grl.ElementLink#getSrc <em>Src</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Links Src</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Links Src</em>' reference list.
	 * @see grl.GrlPackage#getGRLLinkableElement_LinksSrc()
	 * @see grl.ElementLink#getSrc
	 * @model type="grl.ElementLink" opposite="src"
	 * @generated
	 */
	EList getLinksSrc();

} // GRLLinkableElement
