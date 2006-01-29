/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;

import org.eclipse.emf.common.util.EList;

import urncore.GRLmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Evaluation Scenario</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.EvaluationScenario#getEvaluations <em>Evaluations</em>}</li>
 *   <li>{@link grl.EvaluationScenario#getGroups <em>Groups</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getEvaluationScenario()
 * @model
 * @generated
 */
public interface EvaluationScenario extends GRLmodelElement {
    /**
     * Returns the value of the '<em><b>Evaluations</b></em>' reference list.
     * The list contents are of type {@link grl.Evaluation}.
     * It is bidirectional and its opposite is '{@link grl.Evaluation#getScenario <em>Scenario</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Evaluations</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Evaluations</em>' reference list.
     * @see grl.GrlPackage#getEvaluationScenario_Evaluations()
     * @see grl.Evaluation#getScenario
     * @model type="grl.Evaluation" opposite="scenario"
     * @generated
     */
    EList getEvaluations();

    /**
     * Returns the value of the '<em><b>Groups</b></em>' reference list.
     * The list contents are of type {@link grl.EvaluationGroup}.
     * It is bidirectional and its opposite is '{@link grl.EvaluationGroup#getScenarios <em>Scenarios</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Groups</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Groups</em>' reference list.
     * @see grl.GrlPackage#getEvaluationScenario_Groups()
     * @see grl.EvaluationGroup#getScenarios
     * @model type="grl.EvaluationGroup" opposite="scenarios" required="true"
     * @generated
     */
    EList getGroups();

} // EvaluationScenario
