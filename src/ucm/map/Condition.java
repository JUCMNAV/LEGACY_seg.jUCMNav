/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.Condition#getLabel <em>Label</em>}</li>
 *   <li>{@link ucm.map.Condition#getExpression <em>Expression</em>}</li>
 *   <li>{@link ucm.map.Condition#getDescription <em>Description</em>}</li>
 *   <li>{@link ucm.map.Condition#getLabelX <em>Label X</em>}</li>
 *   <li>{@link ucm.map.Condition#getLabelY <em>Label Y</em>}</li>
 *   <li>{@link ucm.map.Condition#getVariables <em>Variables</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getCondition()
 * @model 
 * @generated
 */
public interface Condition extends EObject {
    /**
     * Returns the value of the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label</em>' attribute.
     * @see #setLabel(String)
     * @see ucm.map.MapPackage#getCondition_Label()
     * @model 
     * @generated
     */
    String getLabel();

    /**
     * Sets the value of the '{@link ucm.map.Condition#getLabel <em>Label</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label</em>' attribute.
     * @see #getLabel()
     * @generated
     */
    void setLabel(String value);

    /**
     * Returns the value of the '<em><b>Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Expression</em>' attribute.
     * @see #setExpression(String)
     * @see ucm.map.MapPackage#getCondition_Expression()
     * @model 
     * @generated
     */
    String getExpression();

    /**
     * Sets the value of the '{@link ucm.map.Condition#getExpression <em>Expression</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression</em>' attribute.
     * @see #getExpression()
     * @generated
     */
    void setExpression(String value);

    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see ucm.map.MapPackage#getCondition_Description()
     * @model 
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '{@link ucm.map.Condition#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Label X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label X</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label X</em>' attribute.
     * @see #setLabelX(int)
     * @see ucm.map.MapPackage#getCondition_LabelX()
     * @model 
     * @generated
     */
    int getLabelX();

    /**
     * Sets the value of the '{@link ucm.map.Condition#getLabelX <em>Label X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label X</em>' attribute.
     * @see #getLabelX()
     * @generated
     */
    void setLabelX(int value);

    /**
     * Returns the value of the '<em><b>Label Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Y</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label Y</em>' attribute.
     * @see #setLabelY(int)
     * @see ucm.map.MapPackage#getCondition_LabelY()
     * @model 
     * @generated
     */
    int getLabelY();

    /**
     * Sets the value of the '{@link ucm.map.Condition#getLabelY <em>Label Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label Y</em>' attribute.
     * @see #getLabelY()
     * @generated
     */
    void setLabelY(int value);

    /**
     * Returns the value of the '<em><b>Variables</b></em>' reference list.
     * The list contents are of type {@link ucm.scenario.Variable}.
     * It is bidirectional and its opposite is '{@link ucm.scenario.Variable#getUsages <em>Usages</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Variables</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Variables</em>' reference list.
     * @see ucm.map.MapPackage#getCondition_Variables()
     * @see ucm.scenario.Variable#getUsages
     * @model type="ucm.scenario.Variable" opposite="usages"
     * @generated
     */
    EList getVariables();

} // Condition
