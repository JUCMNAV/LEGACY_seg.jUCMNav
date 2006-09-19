/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.scenario;

import org.eclipse.emf.common.util.EList;

import ucm.UCMspec;
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
 *   <li>{@link ucm.scenario.ScenarioDef#getStartPoints <em>Start Points</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioDef#getUcmspec <em>Ucmspec</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioDef#getGroups <em>Groups</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioDef#getParentScenarios <em>Parent Scenarios</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioDef#getIncludedScenarios <em>Included Scenarios</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioDef#getEndPoints <em>End Points</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioDef#getPreconditions <em>Preconditions</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioDef#getPostconditions <em>Postconditions</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioDef#getVariable <em>Variable</em>}</li>
 *   <li>{@link ucm.scenario.ScenarioDef#getInitializations <em>Initializations</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.scenario.ScenarioPackage#getScenarioDef()
 * @model
 * @generated
 */
public interface ScenarioDef extends UCMmodelElement {
	/**
	 * Returns the value of the '<em><b>Start Points</b></em>' reference list.
	 * The list contents are of type {@link ucm.map.StartPoint}.
	 * It is bidirectional and its opposite is '{@link ucm.map.StartPoint#getScenarioDefs <em>Scenario Defs</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Start Points</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Points</em>' reference list.
	 * @see ucm.scenario.ScenarioPackage#getScenarioDef_StartPoints()
	 * @see ucm.map.StartPoint#getScenarioDefs
	 * @model type="ucm.map.StartPoint" opposite="scenarioDefs" required="true"
	 * @generated
	 */
    EList getStartPoints();

	/**
	 * Returns the value of the '<em><b>Ucmspec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucm.UCMspec#getScenarioDefs <em>Scenario Defs</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Ucmspec</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Ucmspec</em>' container reference.
	 * @see #setUcmspec(UCMspec)
	 * @see ucm.scenario.ScenarioPackage#getScenarioDef_Ucmspec()
	 * @see ucm.UCMspec#getScenarioDefs
	 * @model opposite="scenarioDefs" required="true"
	 * @generated
	 */
    UCMspec getUcmspec();

	/**
	 * Sets the value of the '{@link ucm.scenario.ScenarioDef#getUcmspec <em>Ucmspec</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ucmspec</em>' container reference.
	 * @see #getUcmspec()
	 * @generated
	 */
    void setUcmspec(UCMspec value);

	/**
	 * Returns the value of the '<em><b>Groups</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucm.scenario.ScenarioGroup#getScenarios <em>Scenarios</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Groups</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Groups</em>' container reference.
	 * @see #setGroups(ScenarioGroup)
	 * @see ucm.scenario.ScenarioPackage#getScenarioDef_Groups()
	 * @see ucm.scenario.ScenarioGroup#getScenarios
	 * @model opposite="scenarios" required="true"
	 * @generated
	 */
    ScenarioGroup getGroups();

	/**
	 * Sets the value of the '{@link ucm.scenario.ScenarioDef#getGroups <em>Groups</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Groups</em>' container reference.
	 * @see #getGroups()
	 * @generated
	 */
	void setGroups(ScenarioGroup value);

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
	 * Returns the value of the '<em><b>End Points</b></em>' reference list.
	 * The list contents are of type {@link ucm.map.EndPoint}.
	 * It is bidirectional and its opposite is '{@link ucm.map.EndPoint#getScenarioDefs <em>Scenario Defs</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End Points</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End Points</em>' reference list.
	 * @see ucm.scenario.ScenarioPackage#getScenarioDef_EndPoints()
	 * @see ucm.map.EndPoint#getScenarioDefs
	 * @model type="ucm.map.EndPoint" opposite="scenarioDefs"
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
	 * Returns the value of the '<em><b>Variable</b></em>' reference list.
	 * The list contents are of type {@link ucm.scenario.Variable}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable</em>' reference list.
	 * @see ucm.scenario.ScenarioPackage#getScenarioDef_Variable()
	 * @model type="ucm.scenario.Variable"
	 * @generated
	 */
	EList getVariable();

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
