/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;

import org.eclipse.emf.ecore.EObject;

import urn.URNspec;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>GR Lspec</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.GRLspec#getUrnspec <em>Urnspec</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getGRLspec()
 * @model
 * @generated
 */
public interface GRLspec extends EObject{
    /**
     * Returns the value of the '<em><b>Urnspec</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link urn.URNspec#getGrlspec <em>Grlspec</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Urnspec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Urnspec</em>' container reference.
     * @see #setUrnspec(URNspec)
     * @see grl.GrlPackage#getGRLspec_Urnspec()
     * @see urn.URNspec#getGrlspec
     * @model opposite="grlspec" required="true"
     * @generated
     */
	URNspec getUrnspec();

    /**
     * Sets the value of the '{@link grl.GRLspec#getUrnspec <em>Urnspec</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Urnspec</em>' container reference.
     * @see #getUrnspec()
     * @generated
     */
	void setUrnspec(URNspec value);

} // GRLspec
