/**
 */
package urn.dyncontext;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import urn.URNspec;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Timepoint Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link urn.dyncontext.TimepointGroup#getTimepoints <em>Timepoints</em>}</li>
 *   <li>{@link urn.dyncontext.TimepointGroup#getUrnspec <em>Urnspec</em>}</li>
 * </ul>
 *
 * @see urn.dyncontext.DyncontextPackage#getTimepointGroup()
 * @model
 * @generated
 */
public interface TimepointGroup extends EObject {
	/**
	 * Returns the value of the '<em><b>Timepoints</b></em>' containment reference list.
	 * The list contents are of type {@link urn.dyncontext.Timepoint}.
	 * It is bidirectional and its opposite is '{@link urn.dyncontext.Timepoint#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timepoints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timepoints</em>' containment reference list.
	 * @see urn.dyncontext.DyncontextPackage#getTimepointGroup_Timepoints()
	 * @see urn.dyncontext.Timepoint#getGroup
	 * @model type="urn.dyncontext.Timepoint" opposite="group" containment="true"
	 * @generated
	 */
	EList getTimepoints();

	/**
	 * Returns the value of the '<em><b>Urnspec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link urn.URNspec#getTimepointGroups <em>Timepoint Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Urnspec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Urnspec</em>' container reference.
	 * @see #setUrnspec(URNspec)
	 * @see urn.dyncontext.DyncontextPackage#getTimepointGroup_Urnspec()
	 * @see urn.URNspec#getTimepointGroups
	 * @model opposite="timepointGroups" required="true"
	 * @generated
	 */
	URNspec getUrnspec();

	/**
	 * Sets the value of the '{@link urn.dyncontext.TimepointGroup#getUrnspec <em>Urnspec</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Urnspec</em>' container reference.
	 * @see #getUrnspec()
	 * @generated
	 */
	void setUrnspec(URNspec value);

} // TimepointGroup
