/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.kpimodel;

import grl.Evaluation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KPI New Eval Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.kpimodel.KPINewEvalValue#getEvaluationValue <em>Evaluation Value</em>}</li>
 *   <li>{@link grl.kpimodel.KPINewEvalValue#getEval <em>Eval</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.kpimodel.KpimodelPackage#getKPINewEvalValue()
 * @model
 * @generated
 */
public interface KPINewEvalValue extends EObject {
	/**
	 * Returns the value of the '<em><b>Evaluation Value</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Evaluation Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Evaluation Value</em>' attribute.
	 * @see #setEvaluationValue(double)
	 * @see grl.kpimodel.KpimodelPackage#getKPINewEvalValue_EvaluationValue()
	 * @model default="0"
	 * @generated
	 */
	double getEvaluationValue();

	/**
	 * Sets the value of the '{@link grl.kpimodel.KPINewEvalValue#getEvaluationValue <em>Evaluation Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Evaluation Value</em>' attribute.
	 * @see #getEvaluationValue()
	 * @generated
	 */
	void setEvaluationValue(double value);

	/**
	 * Returns the value of the '<em><b>Eval</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link grl.Evaluation#getKpiNewEvalValue <em>Kpi New Eval Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Eval</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Eval</em>' container reference.
	 * @see #setEval(Evaluation)
	 * @see grl.kpimodel.KpimodelPackage#getKPINewEvalValue_Eval()
	 * @see grl.Evaluation#getKpiNewEvalValue
	 * @model opposite="kpiNewEvalValue" required="true"
	 * @generated
	 */
	Evaluation getEval();

	/**
	 * Sets the value of the '{@link grl.kpimodel.KPINewEvalValue#getEval <em>Eval</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Eval</em>' container reference.
	 * @see #getEval()
	 * @generated
	 */
	void setEval(Evaluation value);

} // KPINewEvalValue
