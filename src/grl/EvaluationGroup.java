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
 * A representation of the model object '<em><b>Evaluation Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.EvaluationGroup#getScenarios <em>Scenarios</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getEvaluationGroup()
 * @model
 * @generated
 */
public interface EvaluationGroup extends GRLmodelElement {
    /**
     * Returns the value of the '<em><b>Scenarios</b></em>' reference list.
     * The list contents are of type {@link grl.EvaluationScenario}.
     * It is bidirectional and its opposite is '{@link grl.EvaluationScenario#getGroups <em>Groups</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Scenarios</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Scenarios</em>' reference list.
     * @see grl.GrlPackage#getEvaluationGroup_Scenarios()
     * @see grl.EvaluationScenario#getGroups
     * @model type="grl.EvaluationScenario" opposite="groups"
     * @generated
     */
    EList getScenarios();

} // EvaluationGroup
