/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.scenario;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Initialization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.scenario.Initialization#getValue <em>Value</em>}</li>
 *   <li>{@link ucm.scenario.Initialization#getScenarioDef <em>Scenario Def</em>}</li>
 *   <li>{@link ucm.scenario.Initialization#getVariable <em>Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.scenario.ScenarioPackage#getInitialization()
 * @model
 * @generated
 */
public interface Initialization extends EObject {
    /**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see ucm.scenario.ScenarioPackage#getInitialization_Value()
	 * @model
	 * @generated
	 */
	String getValue();

    /**
	 * Sets the value of the '{@link ucm.scenario.Initialization#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

    /**
	 * Returns the value of the '<em><b>Scenario Def</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucm.scenario.ScenarioDef#getInitializations <em>Initializations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scenario Def</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenario Def</em>' container reference.
	 * @see #setScenarioDef(ScenarioDef)
	 * @see ucm.scenario.ScenarioPackage#getInitialization_ScenarioDef()
	 * @see ucm.scenario.ScenarioDef#getInitializations
	 * @model opposite="initializations" required="true"
	 * @generated
	 */
	ScenarioDef getScenarioDef();

    /**
	 * Sets the value of the '{@link ucm.scenario.Initialization#getScenarioDef <em>Scenario Def</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scenario Def</em>' container reference.
	 * @see #getScenarioDef()
	 * @generated
	 */
	void setScenarioDef(ScenarioDef value);

    /**
	 * Returns the value of the '<em><b>Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable</em>' reference.
	 * @see #setVariable(Variable)
	 * @see ucm.scenario.ScenarioPackage#getInitialization_Variable()
	 * @model required="true"
	 * @generated
	 */
	Variable getVariable();

    /**
	 * Sets the value of the '{@link ucm.scenario.Initialization#getVariable <em>Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable</em>' reference.
	 * @see #getVariable()
	 * @generated
	 */
	void setVariable(Variable value);

} // Initialization