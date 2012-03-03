/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import org.eclipse.emf.common.util.EList;

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
 *   <li>{@link ucm.map.RespRef#getHostDemand <em>Host Demand</em>}</li>
 *   <li>{@link ucm.map.RespRef#getRespDef <em>Resp Def</em>}</li>
 *   <li>{@link ucm.map.RespRef#getPluginBindings <em>Plugin Bindings</em>}</li>
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
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Repetition Count</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Repetition Count</em>' attribute.
	 * @see #setRepetitionCount(String)
	 * @see ucm.map.MapPackage#getRespRef_RepetitionCount()
	 * @model default="1"
	 * @generated
	 */
    String getRepetitionCount();

    /**
	 * Sets the value of the '{@link ucm.map.RespRef#getRepetitionCount <em>Repetition Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Repetition Count</em>' attribute.
	 * @see #getRepetitionCount()
	 * @generated
	 */
	void setRepetitionCount(String value);

    /**
	 * Returns the value of the '<em><b>Host Demand</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Host Demand</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Host Demand</em>' attribute.
	 * @see #setHostDemand(String)
	 * @see ucm.map.MapPackage#getRespRef_HostDemand()
	 * @model
	 * @generated
	 */
	String getHostDemand();

    /**
	 * Sets the value of the '{@link ucm.map.RespRef#getHostDemand <em>Host Demand</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Host Demand</em>' attribute.
	 * @see #getHostDemand()
	 * @generated
	 */
	void setHostDemand(String value);

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

    /**
	 * Returns the value of the '<em><b>Plugin Bindings</b></em>' reference list.
	 * The list contents are of type {@link ucm.map.ResponsibilityBinding}.
	 * It is bidirectional and its opposite is '{@link ucm.map.ResponsibilityBinding#getPluginResp <em>Plugin Resp</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Plugin Bindings</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Plugin Bindings</em>' reference list.
	 * @see ucm.map.MapPackage#getRespRef_PluginBindings()
	 * @see ucm.map.ResponsibilityBinding#getPluginResp
	 * @model type="ucm.map.ResponsibilityBinding" opposite="pluginResp"
	 * @generated
	 */
    EList getPluginBindings();

} // RespRef
