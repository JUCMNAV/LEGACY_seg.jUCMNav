/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.scenario;

import ucm.UCMspec;
import urncore.UCMmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Variable definition
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.scenario.Variable#getType <em>Type</em>}</li>
 *   <li>{@link ucm.scenario.Variable#getUcmspec <em>Ucmspec</em>}</li>
 *   <li>{@link ucm.scenario.Variable#getEnumerationType <em>Enumeration Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.scenario.ScenarioPackage#getVariable()
 * @model
 * @generated
 */
public interface Variable extends UCMmodelElement {
    /**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The default value is <code>"boolean"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see ucm.scenario.ScenarioPackage#getVariable_Type()
	 * @model default="boolean"
	 * @generated
	 */
    String getType();

    /**
	 * Sets the value of the '{@link ucm.scenario.Variable#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
    void setType(String value);

    /**
	 * Returns the value of the '<em><b>Ucmspec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucm.UCMspec#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ucmspec</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Ucmspec</em>' container reference.
	 * @see #setUcmspec(UCMspec)
	 * @see ucm.scenario.ScenarioPackage#getVariable_Ucmspec()
	 * @see ucm.UCMspec#getVariables
	 * @model opposite="variables" required="true"
	 * @generated
	 */
    UCMspec getUcmspec();

    /**
	 * Sets the value of the '{@link ucm.scenario.Variable#getUcmspec <em>Ucmspec</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ucmspec</em>' container reference.
	 * @see #getUcmspec()
	 * @generated
	 */
    void setUcmspec(UCMspec value);

    /**
	 * Returns the value of the '<em><b>Enumeration Type</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ucm.scenario.EnumerationType#getInstances <em>Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enumeration Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enumeration Type</em>' reference.
	 * @see #setEnumerationType(EnumerationType)
	 * @see ucm.scenario.ScenarioPackage#getVariable_EnumerationType()
	 * @see ucm.scenario.EnumerationType#getInstances
	 * @model opposite="instances"
	 * @generated
	 */
	EnumerationType getEnumerationType();

    /**
	 * Sets the value of the '{@link ucm.scenario.Variable#getEnumerationType <em>Enumeration Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enumeration Type</em>' reference.
	 * @see #getEnumerationType()
	 * @generated
	 */
	void setEnumerationType(EnumerationType value);

} // Variable
