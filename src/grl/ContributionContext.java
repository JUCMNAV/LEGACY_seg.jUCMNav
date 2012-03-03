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
 * A representation of the model object '<em><b>Contribution Context</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.ContributionContext#getGrlspec <em>Grlspec</em>}</li>
 *   <li>{@link grl.ContributionContext#getGroups <em>Groups</em>}</li>
 *   <li>{@link grl.ContributionContext#getChanges <em>Changes</em>}</li>
 *   <li>{@link grl.ContributionContext#getParentContexts <em>Parent Contexts</em>}</li>
 *   <li>{@link grl.ContributionContext#getIncludedContexts <em>Included Contexts</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getContributionContext()
 * @model
 * @generated
 */
public interface ContributionContext extends GRLmodelElement {
	/**
	 * Returns the value of the '<em><b>Grlspec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link grl.GRLspec#getContributionContexts <em>Contribution Contexts</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grlspec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grlspec</em>' container reference.
	 * @see #setGrlspec(GRLspec)
	 * @see grl.GrlPackage#getContributionContext_Grlspec()
	 * @see grl.GRLspec#getContributionContexts
	 * @model opposite="contributionContexts" required="true"
	 * @generated
	 */
	GRLspec getGrlspec();

	/**
	 * Sets the value of the '{@link grl.ContributionContext#getGrlspec <em>Grlspec</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grlspec</em>' container reference.
	 * @see #getGrlspec()
	 * @generated
	 */
	void setGrlspec(GRLspec value);

	/**
	 * Returns the value of the '<em><b>Groups</b></em>' reference list.
	 * The list contents are of type {@link grl.ContributionContextGroup}.
	 * It is bidirectional and its opposite is '{@link grl.ContributionContextGroup#getContribs <em>Contribs</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Groups</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Groups</em>' reference list.
	 * @see grl.GrlPackage#getContributionContext_Groups()
	 * @see grl.ContributionContextGroup#getContribs
	 * @model type="grl.ContributionContextGroup" opposite="contribs"
	 * @generated
	 */
	EList getGroups();

	/**
	 * Returns the value of the '<em><b>Changes</b></em>' containment reference list.
	 * The list contents are of type {@link grl.ContributionChange}.
	 * It is bidirectional and its opposite is '{@link grl.ContributionChange#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Changes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Changes</em>' containment reference list.
	 * @see grl.GrlPackage#getContributionContext_Changes()
	 * @see grl.ContributionChange#getContext
	 * @model type="grl.ContributionChange" opposite="context" containment="true"
	 * @generated
	 */
	EList getChanges();

	/**
	 * Returns the value of the '<em><b>Parent Contexts</b></em>' reference list.
	 * The list contents are of type {@link grl.ContributionContext}.
	 * It is bidirectional and its opposite is '{@link grl.ContributionContext#getIncludedContexts <em>Included Contexts</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Contexts</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Contexts</em>' reference list.
	 * @see grl.GrlPackage#getContributionContext_ParentContexts()
	 * @see grl.ContributionContext#getIncludedContexts
	 * @model type="grl.ContributionContext" opposite="includedContexts"
	 * @generated
	 */
	EList getParentContexts();

	/**
	 * Returns the value of the '<em><b>Included Contexts</b></em>' reference list.
	 * The list contents are of type {@link grl.ContributionContext}.
	 * It is bidirectional and its opposite is '{@link grl.ContributionContext#getParentContexts <em>Parent Contexts</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Included Contexts</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Included Contexts</em>' reference list.
	 * @see grl.GrlPackage#getContributionContext_IncludedContexts()
	 * @see grl.ContributionContext#getParentContexts
	 * @model type="grl.ContributionContext" opposite="parentContexts"
	 * @generated
	 */
	EList getIncludedContexts();

} // ContributionContext
