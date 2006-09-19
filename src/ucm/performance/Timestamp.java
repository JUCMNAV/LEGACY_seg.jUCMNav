/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance;

import org.eclipse.emf.common.util.EList;

import ucm.map.PathNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Timestamp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.performance.Timestamp#getOrientation <em>Orientation</em>}</li>
 *   <li>{@link ucm.performance.Timestamp#getTargets <em>Targets</em>}</li>
 *   <li>{@link ucm.performance.Timestamp#getSources <em>Sources</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.performance.PerformancePackage#getTimestamp()
 * @model
 * @generated
 */
public interface Timestamp extends PathNode {
	/**
	 * Returns the value of the '<em><b>Orientation</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Orientation</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Orientation</em>' attribute.
	 * @see #setOrientation(String)
	 * @see ucm.performance.PerformancePackage#getTimestamp_Orientation()
	 * @model
	 * @generated
	 */
    String getOrientation();

	/**
	 * Sets the value of the '{@link ucm.performance.Timestamp#getOrientation <em>Orientation</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Orientation</em>' attribute.
	 * @see #getOrientation()
	 * @generated
	 */
    void setOrientation(String value);

	/**
	 * Returns the value of the '<em><b>Targets</b></em>' reference list.
	 * The list contents are of type {@link ucm.performance.ResponseTimeReq}.
	 * It is bidirectional and its opposite is '{@link ucm.performance.ResponseTimeReq#getTs1 <em>Ts1</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Targets</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Targets</em>' reference list.
	 * @see ucm.performance.PerformancePackage#getTimestamp_Targets()
	 * @see ucm.performance.ResponseTimeReq#getTs1
	 * @model type="ucm.performance.ResponseTimeReq" opposite="ts1"
	 * @generated
	 */
    EList getTargets();

	/**
	 * Returns the value of the '<em><b>Sources</b></em>' reference list.
	 * The list contents are of type {@link ucm.performance.ResponseTimeReq}.
	 * It is bidirectional and its opposite is '{@link ucm.performance.ResponseTimeReq#getTs2 <em>Ts2</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Sources</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Sources</em>' reference list.
	 * @see ucm.performance.PerformancePackage#getTimestamp_Sources()
	 * @see ucm.performance.ResponseTimeReq#getTs2
	 * @model type="ucm.performance.ResponseTimeReq" opposite="ts2"
	 * @generated
	 */
    EList getSources();

} // Timestamp
