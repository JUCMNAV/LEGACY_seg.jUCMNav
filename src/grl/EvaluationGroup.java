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
 *   <li>{@link grl.EvaluationGroup#getStrategies <em>Strategies</em>}</li>
 *   <li>{@link grl.EvaluationGroup#getGrlspec <em>Grlspec</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getEvaluationGroup()
 * @model
 * @generated
 */
public interface EvaluationGroup extends GRLmodelElement{
    /**
     * Returns the value of the '<em><b>Strategies</b></em>' reference list.
     * The list contents are of type {@link grl.EvaluationStrategy}.
     * It is bidirectional and its opposite is '{@link grl.EvaluationStrategy#getGroup <em>Group</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Strategies</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Strategies</em>' reference list.
     * @see grl.GrlPackage#getEvaluationGroup_Strategies()
     * @see grl.EvaluationStrategy#getGroup
     * @model type="grl.EvaluationStrategy" opposite="group"
     * @generated
     */
    EList getStrategies();

    /**
     * Returns the value of the '<em><b>Grlspec</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link grl.GRLspec#getEvaluationGroups <em>Evaluation Groups</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Grlspec</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Grlspec</em>' container reference.
     * @see #setGrlspec(GRLspec)
     * @see grl.GrlPackage#getEvaluationGroup_Grlspec()
     * @see grl.GRLspec#getEvaluationGroups
     * @model opposite="evaluationGroups" required="true"
     * @generated
     */
    GRLspec getGrlspec();

    /**
     * Sets the value of the '{@link grl.EvaluationGroup#getGrlspec <em>Grlspec</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Grlspec</em>' container reference.
     * @see #getGrlspec()
     * @generated
     */
    void setGrlspec(GRLspec value);

} // EvaluationGroup
