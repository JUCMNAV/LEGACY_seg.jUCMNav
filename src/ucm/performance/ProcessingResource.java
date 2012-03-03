/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Processing Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.performance.ProcessingResource#getKind <em>Kind</em>}</li>
 *   <li>{@link ucm.performance.ProcessingResource#getComponents <em>Components</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.performance.PerformancePackage#getProcessingResource()
 * @model
 * @generated
 */
public interface ProcessingResource extends ActiveResource {
    /**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link ucm.performance.DeviceKind}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Kind</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see ucm.performance.DeviceKind
	 * @see #setKind(DeviceKind)
	 * @see ucm.performance.PerformancePackage#getProcessingResource_Kind()
	 * @model
	 * @generated
	 */
    DeviceKind getKind();

    /**
	 * Sets the value of the '{@link ucm.performance.ProcessingResource#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see ucm.performance.DeviceKind
	 * @see #getKind()
	 * @generated
	 */
    void setKind(DeviceKind value);

    /**
	 * Returns the value of the '<em><b>Components</b></em>' reference list.
	 * The list contents are of type {@link urncore.Component}.
	 * It is bidirectional and its opposite is '{@link urncore.Component#getHost <em>Host</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Components</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Components</em>' reference list.
	 * @see ucm.performance.PerformancePackage#getProcessingResource_Components()
	 * @see urncore.Component#getHost
	 * @model type="urncore.Component" opposite="host"
	 * @generated
	 */
    EList getComponents();

} // ProcessingResource
