/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.kpimodel;

import grl.GRLspec;

import org.eclipse.emf.common.util.EList;

import urncore.GRLmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KPI Conversion</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.kpimodel.KPIConversion#getKpiEvalValueSet <em>Kpi Eval Value Set</em>}</li>
 *   <li>{@link grl.kpimodel.KPIConversion#getGrlspec <em>Grlspec</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.kpimodel.KpimodelPackage#getKPIConversion()
 * @model abstract="true"
 * @generated
 */
public interface KPIConversion extends GRLmodelElement {
	/**
	 * Returns the value of the '<em><b>Kpi Eval Value Set</b></em>' reference list.
	 * The list contents are of type {@link grl.kpimodel.KPIEvalValueSet}.
	 * It is bidirectional and its opposite is '{@link grl.kpimodel.KPIEvalValueSet#getKpiConv <em>Kpi Conv</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kpi Eval Value Set</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kpi Eval Value Set</em>' reference list.
	 * @see grl.kpimodel.KpimodelPackage#getKPIConversion_KpiEvalValueSet()
	 * @see grl.kpimodel.KPIEvalValueSet#getKpiConv
	 * @model type="grl.kpimodel.KPIEvalValueSet" opposite="kpiConv"
	 * @generated
	 */
	EList getKpiEvalValueSet();

	/**
	 * Returns the value of the '<em><b>Grlspec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link grl.GRLspec#getKPIConversion <em>KPI Conversion</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grlspec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grlspec</em>' container reference.
	 * @see #setGrlspec(GRLspec)
	 * @see grl.kpimodel.KpimodelPackage#getKPIConversion_Grlspec()
	 * @see grl.GRLspec#getKPIConversion
	 * @model opposite="KPIConversion" required="true"
	 * @generated
	 */
	GRLspec getGrlspec();

	/**
	 * Sets the value of the '{@link grl.kpimodel.KPIConversion#getGrlspec <em>Grlspec</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grlspec</em>' container reference.
	 * @see #getGrlspec()
	 * @generated
	 */
	void setGrlspec(GRLspec value);

} // KPIConversion
