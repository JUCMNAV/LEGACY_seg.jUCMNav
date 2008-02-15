/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.scenario;

import org.eclipse.emf.ecore.EObject;

import ucm.map.EndPoint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>End Point</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.scenario.ScenarioEndPoint#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioEndPoint#isMandatory <em>Mandatory</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioEndPoint#getScenarioDef <em>Scenario Def</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioEndPoint#getEndPoint <em>End Point</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.scenario.ScenarioPackage#getScenarioEndPoint()
 * @model
 * @generated
 */
public interface ScenarioEndPoint extends EObject {
    /**
	 * Returns the value of the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enabled</em>' attribute.
	 * @see #setEnabled(boolean)
	 * @see ucm.scenario.ScenarioPackage#getScenarioEndPoint_Enabled()
	 * @model
	 * @generated
	 */
	boolean isEnabled();

    /**
	 * Sets the value of the '{@link ucm.scenario.ScenarioEndPoint#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

    /**
	 * Returns the value of the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mandatory</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mandatory</em>' attribute.
	 * @see #setMandatory(boolean)
	 * @see ucm.scenario.ScenarioPackage#getScenarioEndPoint_Mandatory()
	 * @model
	 * @generated
	 */
	boolean isMandatory();

    /**
	 * Sets the value of the '{@link ucm.scenario.ScenarioEndPoint#isMandatory <em>Mandatory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mandatory</em>' attribute.
	 * @see #isMandatory()
	 * @generated
	 */
	void setMandatory(boolean value);

    /**
	 * Returns the value of the '<em><b>Scenario Def</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucm.scenario.ScenarioDef#getEndPoints <em>End Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scenario Def</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenario Def</em>' container reference.
	 * @see #setScenarioDef(ScenarioDef)
	 * @see ucm.scenario.ScenarioPackage#getScenarioEndPoint_ScenarioDef()
	 * @see ucm.scenario.ScenarioDef#getEndPoints
	 * @model opposite="endPoints" required="true"
	 * @generated
	 */
	ScenarioDef getScenarioDef();

    /**
	 * Sets the value of the '{@link ucm.scenario.ScenarioEndPoint#getScenarioDef <em>Scenario Def</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scenario Def</em>' container reference.
	 * @see #getScenarioDef()
	 * @generated
	 */
	void setScenarioDef(ScenarioDef value);

    /**
	 * Returns the value of the '<em><b>End Point</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ucm.map.EndPoint#getScenarioEndPoints <em>Scenario End Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End Point</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End Point</em>' reference.
	 * @see #setEndPoint(EndPoint)
	 * @see ucm.scenario.ScenarioPackage#getScenarioEndPoint_EndPoint()
	 * @see ucm.map.EndPoint#getScenarioEndPoints
	 * @model opposite="scenarioEndPoints" required="true"
	 * @generated
	 */
	EndPoint getEndPoint();

    /**
	 * Sets the value of the '{@link ucm.scenario.ScenarioEndPoint#getEndPoint <em>End Point</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End Point</em>' reference.
	 * @see #getEndPoint()
	 * @generated
	 */
	void setEndPoint(EndPoint value);

} // ScenarioEndPoint