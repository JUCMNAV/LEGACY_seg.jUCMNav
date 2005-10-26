/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Evaluation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.Evaluation#getEvaluation <em>Evaluation</em>}</li>
 *   <li>{@link grl.Evaluation#getIntElement <em>Int Element</em>}</li>
 *   <li>{@link grl.Evaluation#getSet <em>Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getEvaluation()
 * @model 
 * @generated
 */
public interface Evaluation extends EObject {
    /**
     * Returns the value of the '<em><b>Evaluation</b></em>' attribute.
     * The literals are from the enumeration {@link grl.EvaluationLevel}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Evaluation</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Evaluation</em>' attribute.
     * @see grl.EvaluationLevel
     * @see #setEvaluation(EvaluationLevel)
     * @see grl.GrlPackage#getEvaluation_Evaluation()
     * @model 
     * @generated
     */
    EvaluationLevel getEvaluation();

    /**
     * Sets the value of the '{@link grl.Evaluation#getEvaluation <em>Evaluation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Evaluation</em>' attribute.
     * @see grl.EvaluationLevel
     * @see #getEvaluation()
     * @generated
     */
    void setEvaluation(EvaluationLevel value);

    /**
     * Returns the value of the '<em><b>Int Element</b></em>' reference.
     * It is bidirectional and its opposite is '{@link grl.IntentionalElement#getEvals <em>Evals</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Int Element</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Int Element</em>' reference.
     * @see #setIntElement(IntentionalElement)
     * @see grl.GrlPackage#getEvaluation_IntElement()
     * @see grl.IntentionalElement#getEvals
     * @model opposite="evals" required="true"
     * @generated
     */
    IntentionalElement getIntElement();

    /**
     * Sets the value of the '{@link grl.Evaluation#getIntElement <em>Int Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Int Element</em>' reference.
     * @see #getIntElement()
     * @generated
     */
    void setIntElement(IntentionalElement value);

    /**
     * Returns the value of the '<em><b>Set</b></em>' reference.
     * It is bidirectional and its opposite is '{@link grl.EvaluationSet#getEvaluations <em>Evaluations</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Set</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Set</em>' reference.
     * @see #setSet(EvaluationSet)
     * @see grl.GrlPackage#getEvaluation_Set()
     * @see grl.EvaluationSet#getEvaluations
     * @model opposite="evaluations" required="true"
     * @generated
     */
    EvaluationSet getSet();

    /**
     * Sets the value of the '{@link grl.Evaluation#getSet <em>Set</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Set</em>' reference.
     * @see #getSet()
     * @generated
     */
    void setSet(EvaluationSet value);

} // Evaluation
