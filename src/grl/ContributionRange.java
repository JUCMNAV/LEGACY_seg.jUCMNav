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
 * A representation of the model object '<em><b>Contribution Range</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.ContributionRange#getStart <em>Start</em>}</li>
 *   <li>{@link grl.ContributionRange#getEnd <em>End</em>}</li>
 *   <li>{@link grl.ContributionRange#getStep <em>Step</em>}</li>
 *   <li>{@link grl.ContributionRange#getChange <em>Change</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getContributionRange()
 * @model
 * @generated
 */
public interface ContributionRange extends EObject {
	/**
	 * Returns the value of the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start</em>' attribute.
	 * @see #setStart(int)
	 * @see grl.GrlPackage#getContributionRange_Start()
	 * @model
	 * @generated
	 */
	int getStart();

	/**
	 * Sets the value of the '{@link grl.ContributionRange#getStart <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' attribute.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(int value);

	/**
	 * Returns the value of the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End</em>' attribute.
	 * @see #setEnd(int)
	 * @see grl.GrlPackage#getContributionRange_End()
	 * @model
	 * @generated
	 */
	int getEnd();

	/**
	 * Sets the value of the '{@link grl.ContributionRange#getEnd <em>End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End</em>' attribute.
	 * @see #getEnd()
	 * @generated
	 */
	void setEnd(int value);

	/**
	 * Returns the value of the '<em><b>Step</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Step</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Step</em>' attribute.
	 * @see #setStep(int)
	 * @see grl.GrlPackage#getContributionRange_Step()
	 * @model default="1"
	 * @generated
	 */
	int getStep();

	/**
	 * Sets the value of the '{@link grl.ContributionRange#getStep <em>Step</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Step</em>' attribute.
	 * @see #getStep()
	 * @generated
	 */
	void setStep(int value);

	/**
	 * Returns the value of the '<em><b>Change</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link grl.ContributionChange#getContribRange <em>Contrib Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Change</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Change</em>' container reference.
	 * @see #setChange(ContributionChange)
	 * @see grl.GrlPackage#getContributionRange_Change()
	 * @see grl.ContributionChange#getContribRange
	 * @model opposite="contribRange" required="true"
	 * @generated
	 */
	ContributionChange getChange();

	/**
	 * Sets the value of the '{@link grl.ContributionRange#getChange <em>Change</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Change</em>' container reference.
	 * @see #getChange()
	 * @generated
	 */
	void setChange(ContributionChange value);

} // ContributionRange
