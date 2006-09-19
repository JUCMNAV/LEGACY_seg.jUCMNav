/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;

import urncore.GRLmodelElement;
import urncore.IURNContainer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Actor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.Actor#getGrlspec <em>Grlspec</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getActor()
 * @model
 * @generated
 */
public interface Actor extends GRLmodelElement, IURNContainer {
	/**
	 * Returns the value of the '<em><b>Grlspec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link grl.GRLspec#getActors <em>Actors</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Grlspec</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Grlspec</em>' container reference.
	 * @see #setGrlspec(GRLspec)
	 * @see grl.GrlPackage#getActor_Grlspec()
	 * @see grl.GRLspec#getActors
	 * @model opposite="actors" required="true"
	 * @generated
	 */
    GRLspec getGrlspec();

	/**
	 * Sets the value of the '{@link grl.Actor#getGrlspec <em>Grlspec</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grlspec</em>' container reference.
	 * @see #getGrlspec()
	 * @generated
	 */
    void setGrlspec(GRLspec value);

} // Actor
