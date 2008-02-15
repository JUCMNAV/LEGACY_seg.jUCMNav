/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Timer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.Timer#getTimeoutPath <em>Timeout Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getTimer()
 * @model
 * @generated
 */
public interface Timer extends WaitingPlace {
    /**
	 * Returns the value of the '<em><b>Timeout Path</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Timeout Path</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Timeout Path</em>' reference.
	 * @see #setTimeoutPath(NodeConnection)
	 * @see ucm.map.MapPackage#getTimer_TimeoutPath()
	 * @model
	 * @generated
	 */
    NodeConnection getTimeoutPath();

    /**
	 * Sets the value of the '{@link ucm.map.Timer#getTimeoutPath <em>Timeout Path</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timeout Path</em>' reference.
	 * @see #getTimeoutPath()
	 * @generated
	 */
    void setTimeoutPath(NodeConnection value);

} // Timer
