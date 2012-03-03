/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Waiting Place</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 *  A waiting place has one source hyperedge and one target  hyperedge, or two target hyperedges when there is a timeout  path. This is not a strict constraint as other hyperedges (e.g.,  connect) might be linked to it. The triggering-event-list gives  the set of events that restart the sequence of actions in a  path. The precondition-list must be satisfied in order for the  sequence to restart.ATTRIBUTE wait-type: user given typeATTRIBUTE logical-condition: expression which is evaluated at run timeATTRIBUTE timeout-variable: reference to a timer's timeout variable
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.WaitingPlace#getWaitType <em>Wait Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getWaitingPlace()
 * @model
 * @generated
 */
public interface WaitingPlace extends PathNode {
    /**
	 * Returns the value of the '<em><b>Wait Type</b></em>' attribute.
	 * The literals are from the enumeration {@link ucm.map.WaitKind}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Wait Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Wait Type</em>' attribute.
	 * @see ucm.map.WaitKind
	 * @see #setWaitType(WaitKind)
	 * @see ucm.map.MapPackage#getWaitingPlace_WaitType()
	 * @model
	 * @generated
	 */
    WaitKind getWaitType();

    /**
	 * Sets the value of the '{@link ucm.map.WaitingPlace#getWaitType <em>Wait Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Wait Type</em>' attribute.
	 * @see ucm.map.WaitKind
	 * @see #getWaitType()
	 * @generated
	 */
	void setWaitType(WaitKind value);

} // WaitingPlace
