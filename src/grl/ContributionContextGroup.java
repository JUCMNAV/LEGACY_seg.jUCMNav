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
 * A representation of the model object '<em><b>Contribution Context Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.ContributionContextGroup#getGrlspec <em>Grlspec</em>}</li>
 *   <li>{@link grl.ContributionContextGroup#getContribs <em>Contribs</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getContributionContextGroup()
 * @model
 * @generated
 */
public interface ContributionContextGroup extends GRLmodelElement {
	/**
	 * Returns the value of the '<em><b>Grlspec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link grl.GRLspec#getContributionGroups <em>Contribution Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grlspec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grlspec</em>' container reference.
	 * @see #setGrlspec(GRLspec)
	 * @see grl.GrlPackage#getContributionContextGroup_Grlspec()
	 * @see grl.GRLspec#getContributionGroups
	 * @model opposite="contributionGroups" required="true"
	 * @generated
	 */
	GRLspec getGrlspec();

	/**
	 * Sets the value of the '{@link grl.ContributionContextGroup#getGrlspec <em>Grlspec</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grlspec</em>' container reference.
	 * @see #getGrlspec()
	 * @generated
	 */
	void setGrlspec(GRLspec value);

	/**
	 * Returns the value of the '<em><b>Contribs</b></em>' reference list.
	 * The list contents are of type {@link grl.ContributionContext}.
	 * It is bidirectional and its opposite is '{@link grl.ContributionContext#getGroups <em>Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contribs</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contribs</em>' reference list.
	 * @see grl.GrlPackage#getContributionContextGroup_Contribs()
	 * @see grl.ContributionContext#getGroups
	 * @model type="grl.ContributionContext" opposite="groups"
	 * @generated
	 */
	EList getContribs();

} // ContributionContextGroup
