/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance;

import org.eclipse.emf.common.util.EList;

import ucm.UCMspec;
import urncore.UCMmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>General Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.performance.GeneralResource#getMultiplicity <em>Multiplicity</em>}</li>
 *   <li>{@link ucm.performance.GeneralResource#getSchedPolicy <em>Sched Policy</em>}</li>
 *   <li>{@link ucm.performance.GeneralResource#getUcmspec <em>Ucmspec</em>}</li>
 *   <li>{@link ucm.performance.GeneralResource#getPerfMeasures <em>Perf Measures</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.performance.PerformancePackage#getGeneralResource()
 * @model abstract="true"
 * @generated
 */
public interface GeneralResource extends UCMmodelElement {
	/**
	 * Returns the value of the '<em><b>Multiplicity</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multiplicity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multiplicity</em>' attribute.
	 * @see #setMultiplicity(String)
	 * @see ucm.performance.PerformancePackage#getGeneralResource_Multiplicity()
	 * @model
	 * @generated
	 */
	String getMultiplicity();

	/**
	 * Sets the value of the '{@link ucm.performance.GeneralResource#getMultiplicity <em>Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multiplicity</em>' attribute.
	 * @see #getMultiplicity()
	 * @generated
	 */
	void setMultiplicity(String value);

	/**
	 * Returns the value of the '<em><b>Sched Policy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sched Policy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sched Policy</em>' attribute.
	 * @see #setSchedPolicy(String)
	 * @see ucm.performance.PerformancePackage#getGeneralResource_SchedPolicy()
	 * @model
	 * @generated
	 */
	String getSchedPolicy();

	/**
	 * Sets the value of the '{@link ucm.performance.GeneralResource#getSchedPolicy <em>Sched Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sched Policy</em>' attribute.
	 * @see #getSchedPolicy()
	 * @generated
	 */
	void setSchedPolicy(String value);

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
	 * Returns the value of the '<em><b>Perf Measures</b></em>' reference list.
	 * The list contents are of type {@link ucm.performance.PerfMeasure}.
	 * It is bidirectional and its opposite is '{@link ucm.performance.PerfMeasure#getResource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Perf Measures</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Perf Measures</em>' reference list.
	 * @see ucm.performance.PerformancePackage#getGeneralResource_PerfMeasures()
	 * @see ucm.performance.PerfMeasure#getResource
	 * @model type="ucm.performance.PerfMeasure" opposite="resource"
	 * @generated
	 */
	EList getPerfMeasures();

} // GeneralResource
