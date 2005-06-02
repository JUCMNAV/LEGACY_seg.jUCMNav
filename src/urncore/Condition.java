/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import org.eclipse.emf.common.util.EList;

import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PluginBinding;
import ucm.map.StartPoint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.Condition#getLabel <em>Label</em>}</li>
 *   <li>{@link urncore.Condition#getExpression <em>Expression</em>}</li>
 *   <li>{@link urncore.Condition#getDescription <em>Description</em>}</li>
 *   <li>{@link urncore.Condition#getStartPoint <em>Start Point</em>}</li>
 *   <li>{@link urncore.Condition#getEndPoint <em>End Point</em>}</li>
 *   <li>{@link urncore.Condition#getPluginBinding <em>Plugin Binding</em>}</li>
 *   <li>{@link urncore.Condition#getNodeConnection <em>Node Connection</em>}</li>
 *   <li>{@link urncore.Condition#getVariables <em>Variables</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getCondition()
 * @model 
 * @generated
 */
public interface Condition extends Label{
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
     * @see urncore.UrncorePackage#getCondition_Label()
     * @model 
     * @generated
     */
	String getLabel();

    /**
     * Sets the value of the '{@link urncore.Condition#getLabel <em>Label</em>}' attribute.
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
     * @see urncore.UrncorePackage#getCondition_Expression()
     * @model 
     * @generated
     */
	String getExpression();

    /**
     * Sets the value of the '{@link urncore.Condition#getExpression <em>Expression</em>}' attribute.
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
     * @see urncore.UrncorePackage#getCondition_Description()
     * @model 
     * @generated
     */
	String getDescription();

    /**
     * Sets the value of the '{@link urncore.Condition#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
	void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Start Point</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link ucm.map.StartPoint#getPrecondition <em>Precondition</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Point</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Start Point</em>' container reference.
     * @see #setStartPoint(StartPoint)
     * @see urncore.UrncorePackage#getCondition_StartPoint()
     * @see ucm.map.StartPoint#getPrecondition
     * @model opposite="precondition"
     * @generated
     */
	StartPoint getStartPoint();

    /**
     * Sets the value of the '{@link urncore.Condition#getStartPoint <em>Start Point</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Start Point</em>' container reference.
     * @see #getStartPoint()
     * @generated
     */
	void setStartPoint(StartPoint value);

    /**
     * Returns the value of the '<em><b>End Point</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link ucm.map.EndPoint#getPostcondition <em>Postcondition</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End Point</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>End Point</em>' container reference.
     * @see #setEndPoint(EndPoint)
     * @see urncore.UrncorePackage#getCondition_EndPoint()
     * @see ucm.map.EndPoint#getPostcondition
     * @model opposite="postcondition"
     * @generated
     */
	EndPoint getEndPoint();

    /**
     * Sets the value of the '{@link urncore.Condition#getEndPoint <em>End Point</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>End Point</em>' container reference.
     * @see #getEndPoint()
     * @generated
     */
	void setEndPoint(EndPoint value);

    /**
     * Returns the value of the '<em><b>Plugin Binding</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link ucm.map.PluginBinding#getPrecondition <em>Precondition</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Plugin Binding</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Plugin Binding</em>' container reference.
     * @see #setPluginBinding(PluginBinding)
     * @see urncore.UrncorePackage#getCondition_PluginBinding()
     * @see ucm.map.PluginBinding#getPrecondition
     * @model opposite="precondition"
     * @generated
     */
	PluginBinding getPluginBinding();

    /**
     * Sets the value of the '{@link urncore.Condition#getPluginBinding <em>Plugin Binding</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Plugin Binding</em>' container reference.
     * @see #getPluginBinding()
     * @generated
     */
	void setPluginBinding(PluginBinding value);

    /**
     * Returns the value of the '<em><b>Node Connection</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link ucm.map.NodeConnection#getCondition <em>Condition</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node Connection</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Node Connection</em>' container reference.
     * @see #setNodeConnection(NodeConnection)
     * @see urncore.UrncorePackage#getCondition_NodeConnection()
     * @see ucm.map.NodeConnection#getCondition
     * @model opposite="condition"
     * @generated
     */
	NodeConnection getNodeConnection();

    /**
     * Sets the value of the '{@link urncore.Condition#getNodeConnection <em>Node Connection</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Node Connection</em>' container reference.
     * @see #getNodeConnection()
     * @generated
     */
	void setNodeConnection(NodeConnection value);

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
     * @see urncore.UrncorePackage#getCondition_Variables()
     * @see ucm.scenario.Variable#getUsages
     * @model type="ucm.scenario.Variable" opposite="usages"
     * @generated
     */
	EList getVariables();

} // Condition
