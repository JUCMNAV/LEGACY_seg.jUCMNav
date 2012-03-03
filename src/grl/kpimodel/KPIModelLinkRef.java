/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.kpimodel;

import urncore.IURNConnection;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KPI Model Link Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.kpimodel.KPIModelLinkRef#getLink <em>Link</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.kpimodel.KpimodelPackage#getKPIModelLinkRef()
 * @model
 * @generated
 */
public interface KPIModelLinkRef extends IURNConnection {
    /**
	 * Returns the value of the '<em><b>Link</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link grl.kpimodel.KPIModelLink#getRefs <em>Refs</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Link</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Link</em>' reference.
	 * @see #setLink(KPIModelLink)
	 * @see grl.kpimodel.KpimodelPackage#getKPIModelLinkRef_Link()
	 * @see grl.kpimodel.KPIModelLink#getRefs
	 * @model opposite="refs" required="true"
	 * @generated
	 */
    KPIModelLink getLink();

    /**
	 * Sets the value of the '{@link grl.kpimodel.KPIModelLinkRef#getLink <em>Link</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Link</em>' reference.
	 * @see #getLink()
	 * @generated
	 */
    void setLink(KPIModelLink value);

} // KPIModelLinkRef