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
 *   <li>{@link grl.Evaluation#getStrategies <em>Strategies</em>}</li>
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
     * The default value is <code>"0"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Evaluation</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Evaluation</em>' attribute.
     * @see #setEvaluation(int)
     * @see grl.GrlPackage#getEvaluation_Evaluation()
     * @model default="0"
     * @generated
     */
    int getEvaluation();

    /**
     * Sets the value of the '{@link grl.Evaluation#getEvaluation <em>Evaluation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Evaluation</em>' attribute.
     * @see #getEvaluation()
     * @generated
     */
    void setEvaluation(int value);

    /**
     * Returns the value of the '<em><b>Int Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Int Element</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Int Element</em>' reference.
     * @see #setIntElement(IntentionalElement)
     * @see grl.GrlPackage#getEvaluation_IntElement()
     * @model required="true"
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
     * Returns the value of the '<em><b>Strategies</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link grl.EvaluationStrategy#getEvaluations <em>Evaluations</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Strategies</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Strategies</em>' container reference.
     * @see #setStrategies(EvaluationStrategy)
     * @see grl.GrlPackage#getEvaluation_Strategies()
     * @see grl.EvaluationStrategy#getEvaluations
     * @model opposite="evaluations" required="true"
     * @generated
     */
    EvaluationStrategy getStrategies();

    /**
     * Sets the value of the '{@link grl.Evaluation#getStrategies <em>Strategies</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Strategies</em>' container reference.
     * @see #getStrategies()
     * @generated
     */
    void setStrategies(EvaluationStrategy value);

} // Evaluation
