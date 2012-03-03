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
 * A representation of the model object '<em><b>Evaluation Range</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.EvaluationRange#getStart <em>Start</em>}</li>
 *   <li>{@link grl.EvaluationRange#getEnd <em>End</em>}</li>
 *   <li>{@link grl.EvaluationRange#getStep <em>Step</em>}</li>
 *   <li>{@link grl.EvaluationRange#getEval <em>Eval</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getEvaluationRange()
 * @model
 * @generated
 */
public interface EvaluationRange extends EObject {
	/**
	 * Returns the value of the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start</em>' attribute.
	 * @see #setStart(int)
	 * @see grl.GrlPackage#getEvaluationRange_Start()
	 * @model
	 * @generated
	 */
	int getStart();

	/**
	 * Sets the value of the '{@link grl.EvaluationRange#getStart <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' attribute.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(int value);

	/**
	 * Returns the value of the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End</em>' attribute.
	 * @see #setEnd(int)
	 * @see grl.GrlPackage#getEvaluationRange_End()
	 * @model
	 * @generated
	 */
	int getEnd();

	/**
	 * Sets the value of the '{@link grl.EvaluationRange#getEnd <em>End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End</em>' attribute.
	 * @see #getEnd()
	 * @generated
	 */
	void setEnd(int value);

	/**
	 * Returns the value of the '<em><b>Step</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Step</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Step</em>' attribute.
	 * @see #setStep(int)
	 * @see grl.GrlPackage#getEvaluationRange_Step()
	 * @model default="1"
	 * @generated
	 */
	int getStep();

	/**
	 * Sets the value of the '{@link grl.EvaluationRange#getStep <em>Step</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Step</em>' attribute.
	 * @see #getStep()
	 * @generated
	 */
	void setStep(int value);

	/**
	 * Returns the value of the '<em><b>Eval</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link grl.Evaluation#getEvalRange <em>Eval Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Eval</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Eval</em>' container reference.
	 * @see #setEval(Evaluation)
	 * @see grl.GrlPackage#getEvaluationRange_Eval()
	 * @see grl.Evaluation#getEvalRange
	 * @model opposite="evalRange" required="true"
	 * @generated
	 */
	Evaluation getEval();

	/**
	 * Sets the value of the '{@link grl.EvaluationRange#getEval <em>Eval</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Eval</em>' container reference.
	 * @see #getEval()
	 * @generated
	 */
	void setEval(Evaluation value);

} // EvaluationRange
