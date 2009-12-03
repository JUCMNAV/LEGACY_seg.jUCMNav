/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import urncore.Condition;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Failure Start Point</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.FailureStartPoint#getKind <em>Kind</em>}</li>
 *   <li>{@link ucm.map.FailureStartPoint#getCondition <em>Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getFailureStartPoint()
 * @model
 * @generated
 */
public interface FailureStartPoint extends PathNode {
    /**
     * Returns the value of the '<em><b>Kind</b></em>' attribute.
     * The default value is <code>"Failure"</code>.
     * The literals are from the enumeration {@link ucm.map.FailureKind}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Kind</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Kind</em>' attribute.
     * @see ucm.map.FailureKind
     * @see #setKind(FailureKind)
     * @see ucm.map.MapPackage#getFailureStartPoint_Kind()
     * @model default="Failure"
     * @generated
     */
    FailureKind getKind();

    /**
     * Sets the value of the '{@link ucm.map.FailureStartPoint#getKind <em>Kind</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Kind</em>' attribute.
     * @see ucm.map.FailureKind
     * @see #getKind()
     * @generated
     */
    void setKind(FailureKind value);

    /**
     * Returns the value of the '<em><b>Condition</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link urncore.Condition#getFailureStartPoint <em>Failure Start Point</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Condition</em>' containment reference.
     * @see #setCondition(Condition)
     * @see ucm.map.MapPackage#getFailureStartPoint_Condition()
     * @see urncore.Condition#getFailureStartPoint
     * @model opposite="failureStartPoint" containment="true"
     * @generated
     */
    Condition getCondition();

    /**
     * Sets the value of the '{@link ucm.map.FailureStartPoint#getCondition <em>Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Condition</em>' containment reference.
     * @see #getCondition()
     * @generated
     */
    void setCondition(Condition value);

} // FailureStartPoint
