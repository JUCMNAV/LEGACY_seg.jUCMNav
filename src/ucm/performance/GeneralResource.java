/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import ucm.UCMspec;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>General Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.performance.GeneralResource#getUcmspec <em>Ucmspec</em>}</li>
 *   <li>{@link ucm.performance.GeneralResource#getDemands <em>Demands</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.performance.PerformancePackage#getGeneralResource()
 * @model 
 * @generated
 */
public interface GeneralResource extends EObject{
    /**
     * Returns the value of the '<em><b>Ucmspec</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link ucm.UCMspec#getResources <em>Resources</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ucmspec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Ucmspec</em>' container reference.
     * @see #setUcmspec(UCMspec)
     * @see ucm.performance.PerformancePackage#getGeneralResource_Ucmspec()
     * @see ucm.UCMspec#getResources
     * @model opposite="resources" required="true"
     * @generated
     */
	UCMspec getUcmspec();

    /**
     * Sets the value of the '{@link ucm.performance.GeneralResource#getUcmspec <em>Ucmspec</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ucmspec</em>' container reference.
     * @see #getUcmspec()
     * @generated
     */
	void setUcmspec(UCMspec value);

    /**
     * Returns the value of the '<em><b>Demands</b></em>' reference list.
     * The list contents are of type {@link ucm.performance.Demand}.
     * It is bidirectional and its opposite is '{@link ucm.performance.Demand#getResource <em>Resource</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Demands</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Demands</em>' reference list.
     * @see ucm.performance.PerformancePackage#getGeneralResource_Demands()
     * @see ucm.performance.Demand#getResource
     * @model type="ucm.performance.Demand" opposite="resource"
     * @generated
     */
	EList getDemands();

} // GeneralResource
