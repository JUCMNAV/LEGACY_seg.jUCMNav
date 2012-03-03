/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import ucm.map.EndPoint;
import ucm.map.NodeConnection;
import ucm.map.PluginBinding;
import ucm.map.StartPoint;
import ucm.scenario.ScenarioDef;

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
 *   <li>{@link urncore.Condition#getConcern <em>Concern</em>}</li>
 *   <li>{@link urncore.Condition#getScenarioDefPre <em>Scenario Def Pre</em>}</li>
 *   <li>{@link urncore.Condition#getScenarioDefPost <em>Scenario Def Post</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getCondition()
 * @model
 * @generated
 */
public interface Condition extends Label {
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
	 * Returns the value of the '<em><b>Concern</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link urncore.Concern#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Concern</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Concern</em>' container reference.
	 * @see #setConcern(Concern)
	 * @see urncore.UrncorePackage#getCondition_Concern()
	 * @see urncore.Concern#getCondition
	 * @model opposite="condition"
	 * @generated
	 */
    Concern getConcern();

    /**
	 * Sets the value of the '{@link urncore.Condition#getConcern <em>Concern</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Concern</em>' container reference.
	 * @see #getConcern()
	 * @generated
	 */
    void setConcern(Concern value);

    /**
	 * Returns the value of the '<em><b>Scenario Def Pre</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucm.scenario.ScenarioDef#getPreconditions <em>Preconditions</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Scenario Def Pre</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenario Def Pre</em>' container reference.
	 * @see #setScenarioDefPre(ScenarioDef)
	 * @see urncore.UrncorePackage#getCondition_ScenarioDefPre()
	 * @see ucm.scenario.ScenarioDef#getPreconditions
	 * @model opposite="preconditions"
	 * @generated
	 */
    ScenarioDef getScenarioDefPre();

    /**
	 * Sets the value of the '{@link urncore.Condition#getScenarioDefPre <em>Scenario Def Pre</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scenario Def Pre</em>' container reference.
	 * @see #getScenarioDefPre()
	 * @generated
	 */
    void setScenarioDefPre(ScenarioDef value);

    /**
	 * Returns the value of the '<em><b>Scenario Def Post</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucm.scenario.ScenarioDef#getPostconditions <em>Postconditions</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Scenario Def Post</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenario Def Post</em>' container reference.
	 * @see #setScenarioDefPost(ScenarioDef)
	 * @see urncore.UrncorePackage#getCondition_ScenarioDefPost()
	 * @see ucm.scenario.ScenarioDef#getPostconditions
	 * @model opposite="postconditions"
	 * @generated
	 */
    ScenarioDef getScenarioDefPost();

    /**
	 * Sets the value of the '{@link urncore.Condition#getScenarioDefPost <em>Scenario Def Post</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scenario Def Post</em>' container reference.
	 * @see #getScenarioDefPost()
	 * @generated
	 */
    void setScenarioDefPost(ScenarioDef value);

} // Condition
