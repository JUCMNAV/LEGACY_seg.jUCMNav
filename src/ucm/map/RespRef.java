/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import urncore.Responsibility;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resp Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.RespRef#getRepetitionCount <em>Repetition Count</em>}</li>
 *   <li>{@link ucm.map.RespRef#getRespDef <em>Resp Def</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getRespRef()
 * @model
 * @generated
 */
public interface RespRef extends PathNode {
    /**
     * Returns the value of the '<em><b>Repetition Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Repetition Count</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Repetition Count</em>' attribute.
     * @see #setRepetitionCount(int)
     * @see ucm.map.MapPackage#getRespRef_RepetitionCount()
     * @model
     * @generated
     */
    int getRepetitionCount();

    /**
     * Sets the value of the '{@link ucm.map.RespRef#getRepetitionCount <em>Repetition Count</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Repetition Count</em>' attribute.
     * @see #getRepetitionCount()
     * @generated
     */
    void setRepetitionCount(int value);

    /**
     * Returns the value of the '<em><b>Resp Def</b></em>' reference.
     * It is bidirectional and its opposite is '{@link urncore.Responsibility#getRespRefs <em>Resp Refs</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resp Def</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Resp Def</em>' reference.
     * @see #setRespDef(Responsibility)
     * @see ucm.map.MapPackage#getRespRef_RespDef()
     * @see urncore.Responsibility#getRespRefs
     * @model opposite="respRefs" required="true"
     * @generated
     */
    Responsibility getRespDef();

    /**
     * Sets the value of the '{@link ucm.map.RespRef#getRespDef <em>Resp Def</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Resp Def</em>' reference.
     * @see #getRespDef()
     * @generated
     */
    void setRespDef(Responsibility value);

} // RespRef
