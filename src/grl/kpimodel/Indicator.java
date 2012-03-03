/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.kpimodel;

import grl.IntentionalElement;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Indicator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.kpimodel.Indicator#getKpiModelLinksDest <em>Kpi Model Links Dest</em>}</li>
 *   <li>{@link grl.kpimodel.Indicator#getGroups <em>Groups</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.kpimodel.KpimodelPackage#getIndicator()
 * @model
 * @generated
 */
public interface Indicator extends IntentionalElement {
    /**
	 * Returns the value of the '<em><b>Kpi Model Links Dest</b></em>' reference list.
	 * The list contents are of type {@link grl.kpimodel.KPIModelLink}.
	 * It is bidirectional and its opposite is '{@link grl.kpimodel.KPIModelLink#getIndDest <em>Ind Dest</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Kpi Model Links Dest</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Kpi Model Links Dest</em>' reference list.
	 * @see grl.kpimodel.KpimodelPackage#getIndicator_KpiModelLinksDest()
	 * @see grl.kpimodel.KPIModelLink#getIndDest
	 * @model type="grl.kpimodel.KPIModelLink" opposite="indDest"
	 * @generated
	 */
    EList getKpiModelLinksDest();

    /**
	 * Returns the value of the '<em><b>Groups</b></em>' reference list.
	 * The list contents are of type {@link grl.kpimodel.IndicatorGroup}.
	 * It is bidirectional and its opposite is '{@link grl.kpimodel.IndicatorGroup#getIndicators <em>Indicators</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Groups</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Groups</em>' reference list.
	 * @see grl.kpimodel.KpimodelPackage#getIndicator_Groups()
	 * @see grl.kpimodel.IndicatorGroup#getIndicators
	 * @model type="grl.kpimodel.IndicatorGroup" opposite="indicators"
	 * @generated
	 */
    EList getGroups();

} // Indicator