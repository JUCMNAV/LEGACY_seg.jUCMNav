/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.kpimodel;

import grl.EvaluationStrategy;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KPI Information Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.kpimodel.KPIInformationConfig#getLevelOfDimension <em>Level Of Dimension</em>}</li>
 *   <li>{@link grl.kpimodel.KPIInformationConfig#getValueOfDimension <em>Value Of Dimension</em>}</li>
 *   <li>{@link grl.kpimodel.KPIInformationConfig#getStrategies <em>Strategies</em>}</li>
 *   <li>{@link grl.kpimodel.KPIInformationConfig#getKpiInfoElement <em>Kpi Info Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.kpimodel.KpimodelPackage#getKPIInformationConfig()
 * @model
 * @generated
 */
public interface KPIInformationConfig extends EObject {
    /**
	 * Returns the value of the '<em><b>Level Of Dimension</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Level Of Dimension</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Level Of Dimension</em>' attribute.
	 * @see #setLevelOfDimension(String)
	 * @see grl.kpimodel.KpimodelPackage#getKPIInformationConfig_LevelOfDimension()
	 * @model
	 * @generated
	 */
    String getLevelOfDimension();

    /**
	 * Sets the value of the '{@link grl.kpimodel.KPIInformationConfig#getLevelOfDimension <em>Level Of Dimension</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Level Of Dimension</em>' attribute.
	 * @see #getLevelOfDimension()
	 * @generated
	 */
    void setLevelOfDimension(String value);

    /**
	 * Returns the value of the '<em><b>Value Of Dimension</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Of Dimension</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Of Dimension</em>' attribute.
	 * @see #setValueOfDimension(String)
	 * @see grl.kpimodel.KpimodelPackage#getKPIInformationConfig_ValueOfDimension()
	 * @model
	 * @generated
	 */
    String getValueOfDimension();

    /**
	 * Sets the value of the '{@link grl.kpimodel.KPIInformationConfig#getValueOfDimension <em>Value Of Dimension</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Of Dimension</em>' attribute.
	 * @see #getValueOfDimension()
	 * @generated
	 */
    void setValueOfDimension(String value);

    /**
	 * Returns the value of the '<em><b>Strategies</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link grl.EvaluationStrategy#getKpiInfoConfig <em>Kpi Info Config</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Strategies</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Strategies</em>' container reference.
	 * @see #setStrategies(EvaluationStrategy)
	 * @see grl.kpimodel.KpimodelPackage#getKPIInformationConfig_Strategies()
	 * @see grl.EvaluationStrategy#getKpiInfoConfig
	 * @model opposite="kpiInfoConfig" required="true"
	 * @generated
	 */
    EvaluationStrategy getStrategies();

    /**
	 * Sets the value of the '{@link grl.kpimodel.KPIInformationConfig#getStrategies <em>Strategies</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Strategies</em>' container reference.
	 * @see #getStrategies()
	 * @generated
	 */
    void setStrategies(EvaluationStrategy value);

    /**
	 * Returns the value of the '<em><b>Kpi Info Element</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Kpi Info Element</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Kpi Info Element</em>' reference.
	 * @see #setKpiInfoElement(KPIInformationElement)
	 * @see grl.kpimodel.KpimodelPackage#getKPIInformationConfig_KpiInfoElement()
	 * @model required="true"
	 * @generated
	 */
    KPIInformationElement getKpiInfoElement();

    /**
	 * Sets the value of the '{@link grl.kpimodel.KPIInformationConfig#getKpiInfoElement <em>Kpi Info Element</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kpi Info Element</em>' reference.
	 * @see #getKpiInfoElement()
	 * @generated
	 */
    void setKpiInfoElement(KPIInformationElement value);

} // KPIInformationConfig