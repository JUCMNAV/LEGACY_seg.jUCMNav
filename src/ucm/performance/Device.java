/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance;

import urncore.UCMmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Device</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.performance.Device#getDeviceKind <em>Device Kind</em>}</li>
 *   <li>{@link ucm.performance.Device#getOptime <em>Optime</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.performance.PerformancePackage#getDevice()
 * @model 
 * @generated
 */
public interface Device extends UCMmodelElement {
	/**
	 * Returns the value of the '<em><b>Device Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link ucm.performance.DeviceKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Device Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Device Kind</em>' attribute.
	 * @see ucm.performance.DeviceKind
	 * @see #setDeviceKind(DeviceKind)
	 * @see ucm.performance.PerformancePackage#getDevice_DeviceKind()
	 * @model 
	 * @generated
	 */
	DeviceKind getDeviceKind();

	/**
	 * Sets the value of the '{@link ucm.performance.Device#getDeviceKind <em>Device Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Device Kind</em>' attribute.
	 * @see ucm.performance.DeviceKind
	 * @see #getDeviceKind()
	 * @generated
	 */
	void setDeviceKind(DeviceKind value);

	/**
	 * Returns the value of the '<em><b>Optime</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Optime</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Optime</em>' attribute.
	 * @see #setOptime(String)
	 * @see ucm.performance.PerformancePackage#getDevice_Optime()
	 * @model 
	 * @generated
	 */
	String getOptime();

	/**
	 * Sets the value of the '{@link ucm.performance.Device#getOptime <em>Optime</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Optime</em>' attribute.
	 * @see #getOptime()
	 * @generated
	 */
	void setOptime(String value);

} // Device
