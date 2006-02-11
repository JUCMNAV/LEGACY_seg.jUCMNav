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
 *   <li>{@link grl.EvaluationScenario#getGrlspec <em>Grlspec</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getEvaluationScenario()
 * @model
 * @generated
 */
public interface EvaluationScenario extends GRLmodelElement{
    /**
     * Returns the value of the '<em><b>Evaluations</b></em>' containment reference list.
     * The list contents are of type {@link grl.Evaluation}.
     * It is bidirectional and its opposite is '{@link grl.Evaluation#getScenario <em>Scenario</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Evaluations</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Evaluations</em>' containment reference list.
     * @see grl.GrlPackage#getEvaluationScenario_Evaluations()
     * @see grl.Evaluation#getScenario
     * @model type="grl.Evaluation" opposite="scenario" containment="true"
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

    /**
     * Returns the value of the '<em><b>Grlspec</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link grl.GRLspec#getScenarios <em>Scenarios</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Grlspec</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Grlspec</em>' container reference.
     * @see #setGrlspec(GRLspec)
     * @see grl.GrlPackage#getEvaluationScenario_Grlspec()
     * @see grl.GRLspec#getScenarios
     * @model opposite="scenarios" required="true"
     * @generated
     */
    GRLspec getGrlspec();

    /**
     * Sets the value of the '{@link grl.EvaluationScenario#getGrlspec <em>Grlspec</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Grlspec</em>' container reference.
     * @see #getGrlspec()
     * @generated
     */
    void setGrlspec(GRLspec value);

} // EvaluationScenario
