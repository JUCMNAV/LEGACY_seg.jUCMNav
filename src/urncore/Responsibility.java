/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Responsibility</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.Responsibility#getExecSequence <em>Exec Sequence</em>}</li>
 *   <li>{@link urncore.Responsibility#getDevices <em>Devices</em>}</li>
 *   <li>{@link urncore.Responsibility#getRespRefs <em>Resp Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getResponsibility()
 * @model 
 * @generated
 */
public interface Responsibility extends UCMmodelElement {
	/**
	 * Returns the value of the '<em><b>Exec Sequence</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exec Sequence</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exec Sequence</em>' attribute.
	 * @see #setExecSequence(String)
	 * @see urncore.UrncorePackage#getResponsibility_ExecSequence()
	 * @model 
	 * @generated
	 */
	String getExecSequence();

	/**
	 * Sets the value of the '{@link urncore.Responsibility#getExecSequence <em>Exec Sequence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exec Sequence</em>' attribute.
	 * @see #getExecSequence()
	 * @generated
	 */
	void setExecSequence(String value);

	/**
	 * Returns the value of the '<em><b>Devices</b></em>' reference list.
	 * The list contents are of type {@link ucm.performance.Device}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Devices</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Devices</em>' reference list.
	 * @see urncore.UrncorePackage#getResponsibility_Devices()
	 * @model type="ucm.performance.Device"
	 * @generated
	 */
	EList getDevices();

	/**
	 * Returns the value of the '<em><b>Resp Refs</b></em>' reference list.
	 * The list contents are of type {@link ucm.map.RespRef}.
	 * It is bidirectional and its opposite is '{@link ucm.map.RespRef#getRespDef <em>Resp Def</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resp Refs</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resp Refs</em>' reference list.
	 * @see urncore.UrncorePackage#getResponsibility_RespRefs()
	 * @see ucm.map.RespRef#getRespDef
	 * @model type="ucm.map.RespRef" opposite="respDef" required="true"
	 * @generated
	 */
	EList getRespRefs();

} // Responsibility
