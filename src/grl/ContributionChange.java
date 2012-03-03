/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contribution Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.ContributionChange#getNewContribution <em>New Contribution</em>}</li>
 *   <li>{@link grl.ContributionChange#getNewQuantitativeContribution <em>New Quantitative Contribution</em>}</li>
 *   <li>{@link grl.ContributionChange#getContext <em>Context</em>}</li>
 *   <li>{@link grl.ContributionChange#getContribution <em>Contribution</em>}</li>
 *   <li>{@link grl.ContributionChange#getContribRange <em>Contrib Range</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getContributionChange()
 * @model
 * @generated
 */
public interface ContributionChange extends EObject {
	/**
	 * Returns the value of the '<em><b>New Contribution</b></em>' attribute.
	 * The default value is <code>"Unknown"</code>.
	 * The literals are from the enumeration {@link grl.ContributionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>New Contribution</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Contribution</em>' attribute.
	 * @see grl.ContributionType
	 * @see #setNewContribution(ContributionType)
	 * @see grl.GrlPackage#getContributionChange_NewContribution()
	 * @model default="Unknown"
	 * @generated
	 */
	ContributionType getNewContribution();

	/**
	 * Sets the value of the '{@link grl.ContributionChange#getNewContribution <em>New Contribution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Contribution</em>' attribute.
	 * @see grl.ContributionType
	 * @see #getNewContribution()
	 * @generated
	 */
	void setNewContribution(ContributionType value);

	/**
	 * Returns the value of the '<em><b>New Quantitative Contribution</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>New Quantitative Contribution</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>New Quantitative Contribution</em>' attribute.
	 * @see #setNewQuantitativeContribution(int)
	 * @see grl.GrlPackage#getContributionChange_NewQuantitativeContribution()
	 * @model default="0"
	 * @generated
	 */
	int getNewQuantitativeContribution();

	/**
	 * Sets the value of the '{@link grl.ContributionChange#getNewQuantitativeContribution <em>New Quantitative Contribution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>New Quantitative Contribution</em>' attribute.
	 * @see #getNewQuantitativeContribution()
	 * @generated
	 */
	void setNewQuantitativeContribution(int value);

	/**
	 * Returns the value of the '<em><b>Context</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link grl.ContributionContext#getChanges <em>Changes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context</em>' container reference.
	 * @see #setContext(ContributionContext)
	 * @see grl.GrlPackage#getContributionChange_Context()
	 * @see grl.ContributionContext#getChanges
	 * @model opposite="changes" required="true"
	 * @generated
	 */
	ContributionContext getContext();

	/**
	 * Sets the value of the '{@link grl.ContributionChange#getContext <em>Context</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context</em>' container reference.
	 * @see #getContext()
	 * @generated
	 */
	void setContext(ContributionContext value);

	/**
	 * Returns the value of the '<em><b>Contribution</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contribution</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contribution</em>' reference.
	 * @see #setContribution(Contribution)
	 * @see grl.GrlPackage#getContributionChange_Contribution()
	 * @model required="true"
	 * @generated
	 */
	Contribution getContribution();

	/**
	 * Sets the value of the '{@link grl.ContributionChange#getContribution <em>Contribution</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contribution</em>' reference.
	 * @see #getContribution()
	 * @generated
	 */
	void setContribution(Contribution value);

	/**
	 * Returns the value of the '<em><b>Contrib Range</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link grl.ContributionRange#getChange <em>Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contrib Range</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contrib Range</em>' containment reference.
	 * @see #setContribRange(ContributionRange)
	 * @see grl.GrlPackage#getContributionChange_ContribRange()
	 * @see grl.ContributionRange#getChange
	 * @model opposite="change" containment="true"
	 * @generated
	 */
	ContributionRange getContribRange();

	/**
	 * Sets the value of the '{@link grl.ContributionChange#getContribRange <em>Contrib Range</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contrib Range</em>' containment reference.
	 * @see #getContribRange()
	 * @generated
	 */
	void setContribRange(ContributionRange value);

} // ContributionChange
