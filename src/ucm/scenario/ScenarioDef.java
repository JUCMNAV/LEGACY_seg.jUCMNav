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
 * Defines initial values for the variables, the expected postconditions, and the start points.
 * TODO!
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.scenario.ScenarioDef#getStartPoints <em>Start Points</em>}</li>
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
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Points</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Points</em>' reference list.
	 * @see ucm.scenario.ScenarioPackage#getScenarioDef_StartPoints()
	 * @model type="ucm.map.StartPoint" required="true"
	 * @generated
	 */
	EList getStartPoints();

} // ScenarioDef
