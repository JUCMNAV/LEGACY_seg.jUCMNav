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
 * A representation of the model object '<em><b>Evaluation Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.EvaluationSet#getEvaluations <em>Evaluations</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getEvaluationSet()
 * @model 
 * @generated
 */
public interface EvaluationSet extends GRLmodelElement {
    /**
     * Returns the value of the '<em><b>Evaluations</b></em>' reference list.
     * The list contents are of type {@link grl.Evaluation}.
     * It is bidirectional and its opposite is '{@link grl.Evaluation#getSet <em>Set</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Evaluations</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Evaluations</em>' reference list.
     * @see grl.GrlPackage#getEvaluationSet_Evaluations()
     * @see grl.Evaluation#getSet
     * @model type="grl.Evaluation" opposite="set"
     * @generated
     */
    EList getEvaluations();

} // EvaluationSet
