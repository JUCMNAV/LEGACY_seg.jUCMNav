/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.scenario;

import org.eclipse.emf.ecore.EObject;

import ucm.map.StartPoint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Start Point</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.scenario.ScenarioStartPoint#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioStartPoint#getScenarioDef <em>Scenario Def</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioStartPoint#getStartPoint <em>Start Point</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.scenario.ScenarioPackage#getScenarioStartPoint()
 * @model
 * @generated
 */
public interface ScenarioStartPoint extends EObject {
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
	 * @see ucm.scenario.ScenarioPackage#getScenarioStartPoint_Enabled()
	 * @model
	 * @generated
	 */
	boolean isEnabled();

    /**
	 * Sets the value of the '{@link ucm.scenario.ScenarioStartPoint#isEnabled <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enabled</em>' attribute.
	 * @see #isEnabled()
	 * @generated
	 */
	void setEnabled(boolean value);

    /**
	 * Returns the value of the '<em><b>Scenario Def</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucm.scenario.ScenarioDef#getStartPoints <em>Start Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scenario Def</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenario Def</em>' container reference.
	 * @see #setScenarioDef(ScenarioDef)
	 * @see ucm.scenario.ScenarioPackage#getScenarioStartPoint_ScenarioDef()
	 * @see ucm.scenario.ScenarioDef#getStartPoints
	 * @model opposite="startPoints" required="true"
	 * @generated
	 */
	ScenarioDef getScenarioDef();

    /**
	 * Sets the value of the '{@link ucm.scenario.ScenarioStartPoint#getScenarioDef <em>Scenario Def</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scenario Def</em>' container reference.
	 * @see #getScenarioDef()
	 * @generated
	 */
	void setScenarioDef(ScenarioDef value);

    /**
	 * Returns the value of the '<em><b>Start Point</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ucm.map.StartPoint#getScenarioStartPoints <em>Scenario Start Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Point</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Point</em>' reference.
	 * @see #setStartPoint(StartPoint)
	 * @see ucm.scenario.ScenarioPackage#getScenarioStartPoint_StartPoint()
	 * @see ucm.map.StartPoint#getScenarioStartPoints
	 * @model opposite="scenarioStartPoints" required="true"
	 * @generated
	 */
	StartPoint getStartPoint();

    /**
	 * Sets the value of the '{@link ucm.scenario.ScenarioStartPoint#getStartPoint <em>Start Point</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Point</em>' reference.
	 * @see #getStartPoint()
	 * @generated
	 */
	void setStartPoint(StartPoint value);

} // ScenarioStartPoint