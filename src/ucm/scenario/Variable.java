/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.scenario;

import org.eclipse.emf.common.util.EList;

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
 *   <li>{@link ucm.scenario.Variable#getUsages <em>Usages</em>}</li>
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
     * Returns the value of the '<em><b>Usages</b></em>' reference list.
     * The list contents are of type {@link ucm.map.Condition}.
     * It is bidirectional and its opposite is '{@link ucm.map.Condition#getVariables <em>Variables</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Usages</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Usages</em>' reference list.
     * @see ucm.scenario.ScenarioPackage#getVariable_Usages()
     * @see ucm.map.Condition#getVariables
     * @model type="ucm.map.Condition" opposite="variables"
     * @generated
     */
    EList getUsages();

} // Variable
