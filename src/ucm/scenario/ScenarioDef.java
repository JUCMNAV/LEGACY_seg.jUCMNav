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
 * A representation of the model object '<em><b>Def</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Defines initial values for the variables, the expected postconditions, the end points, and the start points.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.scenario.ScenarioDef#getGroup <em>Group</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioDef#getParentScenarios <em>Parent Scenarios</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioDef#getIncludedScenarios <em>Included Scenarios</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioDef#getPreconditions <em>Preconditions</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioDef#getPostconditions <em>Postconditions</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioDef#getInitializations <em>Initializations</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioDef#getStartPoints <em>Start Points</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioDef#getEndPoints <em>End Points</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.scenario.ScenarioPackage#getScenarioDef()
 * @model
 * @generated
 */
public interface ScenarioDef extends UCMmodelElement {
    /**
	 * Returns the value of the '<em><b>Start Points</b></em>' containment reference list.
	 * The list contents are of type {@link ucm.scenario.ScenarioStartPoint}.
	 * It is bidirectional and its opposite is '{@link ucm.scenario.ScenarioStartPoint#getScenarioDef <em>Scenario Def</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Start Points</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Points</em>' containment reference list.
	 * @see ucm.scenario.ScenarioPackage#getScenarioDef_StartPoints()
	 * @see ucm.scenario.ScenarioStartPoint#getScenarioDef
	 * @model type="ucm.scenario.ScenarioStartPoint" opposite="scenarioDef" containment="true"
	 * @generated
	 */
    EList getStartPoints();

    /**
	 * Returns the value of the '<em><b>Group</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucm.scenario.ScenarioGroup#getScenarios <em>Scenarios</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' container reference.
	 * @see #setGroup(ScenarioGroup)
	 * @see ucm.scenario.ScenarioPackage#getScenarioDef_Group()
	 * @see ucm.scenario.ScenarioGroup#getScenarios
	 * @model opposite="scenarios" required="true"
	 * @generated
	 */
	ScenarioGroup getGroup();

    /**
	 * Sets the value of the '{@link ucm.scenario.ScenarioDef#getGroup <em>Group</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group</em>' container reference.
	 * @see #getGroup()
	 * @generated
	 */
	void setGroup(ScenarioGroup value);

    /**
	 * Returns the value of the '<em><b>Parent Scenarios</b></em>' reference list.
	 * The list contents are of type {@link ucm.scenario.ScenarioDef}.
	 * It is bidirectional and its opposite is '{@link ucm.scenario.ScenarioDef#getIncludedScenarios <em>Included Scenarios</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Scenarios</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Scenarios</em>' reference list.
	 * @see ucm.scenario.ScenarioPackage#getScenarioDef_ParentScenarios()
	 * @see ucm.scenario.ScenarioDef#getIncludedScenarios
	 * @model type="ucm.scenario.ScenarioDef" opposite="includedScenarios"
	 * @generated
	 */
	EList getParentScenarios();

    /**
	 * Returns the value of the '<em><b>Included Scenarios</b></em>' reference list.
	 * The list contents are of type {@link ucm.scenario.ScenarioDef}.
	 * It is bidirectional and its opposite is '{@link ucm.scenario.ScenarioDef#getParentScenarios <em>Parent Scenarios</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Included Scenarios</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Included Scenarios</em>' reference list.
	 * @see ucm.scenario.ScenarioPackage#getScenarioDef_IncludedScenarios()
	 * @see ucm.scenario.ScenarioDef#getParentScenarios
	 * @model type="ucm.scenario.ScenarioDef" opposite="parentScenarios"
	 * @generated
	 */
	EList getIncludedScenarios();

    /**
	 * Returns the value of the '<em><b>End Points</b></em>' containment reference list.
	 * The list contents are of type {@link ucm.scenario.ScenarioEndPoint}.
	 * It is bidirectional and its opposite is '{@link ucm.scenario.ScenarioEndPoint#getScenarioDef <em>Scenario Def</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End Points</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End Points</em>' containment reference list.
	 * @see ucm.scenario.ScenarioPackage#getScenarioDef_EndPoints()
	 * @see ucm.scenario.ScenarioEndPoint#getScenarioDef
	 * @model type="ucm.scenario.ScenarioEndPoint" opposite="scenarioDef" containment="true"
	 * @generated
	 */
	EList getEndPoints();

    /**
	 * Returns the value of the '<em><b>Preconditions</b></em>' containment reference list.
	 * The list contents are of type {@link urncore.Condition}.
	 * It is bidirectional and its opposite is '{@link urncore.Condition#getScenarioDefPre <em>Scenario Def Pre</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Preconditions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Preconditions</em>' containment reference list.
	 * @see ucm.scenario.ScenarioPackage#getScenarioDef_Preconditions()
	 * @see urncore.Condition#getScenarioDefPre
	 * @model type="urncore.Condition" opposite="scenarioDefPre" containment="true"
	 * @generated
	 */
	EList getPreconditions();

    /**
	 * Returns the value of the '<em><b>Postconditions</b></em>' containment reference list.
	 * The list contents are of type {@link urncore.Condition}.
	 * It is bidirectional and its opposite is '{@link urncore.Condition#getScenarioDefPost <em>Scenario Def Post</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Postconditions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Postconditions</em>' containment reference list.
	 * @see ucm.scenario.ScenarioPackage#getScenarioDef_Postconditions()
	 * @see urncore.Condition#getScenarioDefPost
	 * @model type="urncore.Condition" opposite="scenarioDefPost" containment="true"
	 * @generated
	 */
	EList getPostconditions();

    /**
	 * Returns the value of the '<em><b>Initializations</b></em>' containment reference list.
	 * The list contents are of type {@link ucm.scenario.Initialization}.
	 * It is bidirectional and its opposite is '{@link ucm.scenario.Initialization#getScenarioDef <em>Scenario Def</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initializations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initializations</em>' containment reference list.
	 * @see ucm.scenario.ScenarioPackage#getScenarioDef_Initializations()
	 * @see ucm.scenario.Initialization#getScenarioDef
	 * @model type="ucm.scenario.Initialization" opposite="scenarioDef" containment="true"
	 * @generated
	 */
	EList getInitializations();

} // ScenarioDef
