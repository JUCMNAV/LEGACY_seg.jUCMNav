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
 * A representation of the model object '<em><b>KPI Eval Value Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.kpimodel.KPIEvalValueSet#getTargetValue <em>Target Value</em>}</li>
 *   <li>{@link grl.kpimodel.KPIEvalValueSet#getThresholdValue <em>Threshold Value</em>}</li>
 *   <li>{@link grl.kpimodel.KPIEvalValueSet#getWorstValue <em>Worst Value</em>}</li>
 *   <li>{@link grl.kpimodel.KPIEvalValueSet#getEvaluationValue <em>Evaluation Value</em>}</li>
 *   <li>{@link grl.kpimodel.KPIEvalValueSet#getUnit <em>Unit</em>}</li>
 *   <li>{@link grl.kpimodel.KPIEvalValueSet#getQualitativeEvaluationValue <em>Qualitative Evaluation Value</em>}</li>
 *   <li>{@link grl.kpimodel.KPIEvalValueSet#getEval <em>Eval</em>}</li>
 *   <li>{@link grl.kpimodel.KPIEvalValueSet#getKpiConv <em>Kpi Conv</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.kpimodel.KpimodelPackage#getKPIEvalValueSet()
 * @model
 * @generated
 */
public interface KPIEvalValueSet extends EObject {
    /**
	 * Returns the value of the '<em><b>Target Value</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Value</em>' attribute.
	 * @see #setTargetValue(double)
	 * @see grl.kpimodel.KpimodelPackage#getKPIEvalValueSet_TargetValue()
	 * @model default="0"
	 * @generated
	 */
    double getTargetValue();

    /**
	 * Sets the value of the '{@link grl.kpimodel.KPIEvalValueSet#getTargetValue <em>Target Value</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Value</em>' attribute.
	 * @see #getTargetValue()
	 * @generated
	 */
    void setTargetValue(double value);

    /**
	 * Returns the value of the '<em><b>Threshold Value</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Threshold Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Threshold Value</em>' attribute.
	 * @see #setThresholdValue(double)
	 * @see grl.kpimodel.KpimodelPackage#getKPIEvalValueSet_ThresholdValue()
	 * @model default="0"
	 * @generated
	 */
    double getThresholdValue();

    /**
	 * Sets the value of the '{@link grl.kpimodel.KPIEvalValueSet#getThresholdValue <em>Threshold Value</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Threshold Value</em>' attribute.
	 * @see #getThresholdValue()
	 * @generated
	 */
    void setThresholdValue(double value);

    /**
	 * Returns the value of the '<em><b>Worst Value</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Worst Value</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Worst Value</em>' attribute.
	 * @see #setWorstValue(double)
	 * @see grl.kpimodel.KpimodelPackage#getKPIEvalValueSet_WorstValue()
	 * @model default="0"
	 * @generated
	 */
    double getWorstValue();

    /**
	 * Sets the value of the '{@link grl.kpimodel.KPIEvalValueSet#getWorstValue <em>Worst Value</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Worst Value</em>' attribute.
	 * @see #getWorstValue()
	 * @generated
	 */
    void setWorstValue(double value);

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
	 * @see grl.kpimodel.KpimodelPackage#getKPIEvalValueSet_EvaluationValue()
	 * @model default="0"
	 * @generated
	 */
    double getEvaluationValue();

    /**
	 * Sets the value of the '{@link grl.kpimodel.KPIEvalValueSet#getEvaluationValue <em>Evaluation Value</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Evaluation Value</em>' attribute.
	 * @see #getEvaluationValue()
	 * @generated
	 */
    void setEvaluationValue(double value);

    /**
	 * Returns the value of the '<em><b>Unit</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Unit</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Unit</em>' attribute.
	 * @see #setUnit(String)
	 * @see grl.kpimodel.KpimodelPackage#getKPIEvalValueSet_Unit()
	 * @model default=""
	 * @generated
	 */
    String getUnit();

    /**
	 * Sets the value of the '{@link grl.kpimodel.KPIEvalValueSet#getUnit <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit</em>' attribute.
	 * @see #getUnit()
	 * @generated
	 */
    void setUnit(String value);

    /**
	 * Returns the value of the '<em><b>Qualitative Evaluation Value</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualitative Evaluation Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qualitative Evaluation Value</em>' attribute.
	 * @see #setQualitativeEvaluationValue(String)
	 * @see grl.kpimodel.KpimodelPackage#getKPIEvalValueSet_QualitativeEvaluationValue()
	 * @model default=""
	 * @generated
	 */
	String getQualitativeEvaluationValue();

				/**
	 * Sets the value of the '{@link grl.kpimodel.KPIEvalValueSet#getQualitativeEvaluationValue <em>Qualitative Evaluation Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qualitative Evaluation Value</em>' attribute.
	 * @see #getQualitativeEvaluationValue()
	 * @generated
	 */
	void setQualitativeEvaluationValue(String value);

				/**
	 * Returns the value of the '<em><b>Eval</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link grl.Evaluation#getKpiEvalValueSet <em>Kpi Eval Value Set</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Eval</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Eval</em>' container reference.
	 * @see #setEval(Evaluation)
	 * @see grl.kpimodel.KpimodelPackage#getKPIEvalValueSet_Eval()
	 * @see grl.Evaluation#getKpiEvalValueSet
	 * @model opposite="kpiEvalValueSet" required="true"
	 * @generated
	 */
    Evaluation getEval();

    /**
	 * Sets the value of the '{@link grl.kpimodel.KPIEvalValueSet#getEval <em>Eval</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Eval</em>' container reference.
	 * @see #getEval()
	 * @generated
	 */
    void setEval(Evaluation value);

				/**
	 * Returns the value of the '<em><b>Kpi Conv</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link grl.kpimodel.KPIConversion#getKpiEvalValueSet <em>Kpi Eval Value Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kpi Conv</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kpi Conv</em>' reference.
	 * @see #setKpiConv(KPIConversion)
	 * @see grl.kpimodel.KpimodelPackage#getKPIEvalValueSet_KpiConv()
	 * @see grl.kpimodel.KPIConversion#getKpiEvalValueSet
	 * @model opposite="kpiEvalValueSet"
	 * @generated
	 */
	KPIConversion getKpiConv();

				/**
	 * Sets the value of the '{@link grl.kpimodel.KPIEvalValueSet#getKpiConv <em>Kpi Conv</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kpi Conv</em>' reference.
	 * @see #getKpiConv()
	 * @generated
	 */
	void setKpiConv(KPIConversion value);

} // KPIEvalValueSet