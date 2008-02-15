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
 * A representation of the model object '<em><b>KPI Information Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.kpimodel.KPIInformationElement#getRefs <em>Refs</em>}</li>
 *   <li>{@link grl.kpimodel.KPIInformationElement#getGrlspec <em>Grlspec</em>}</li>
 *   <li>{@link grl.kpimodel.KPIInformationElement#getKpiModelLinksSrc <em>Kpi Model Links Src</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.kpimodel.KpimodelPackage#getKPIInformationElement()
 * @model
 * @generated
 */
public interface KPIInformationElement extends GRLmodelElement {
    /**
	 * Returns the value of the '<em><b>Refs</b></em>' reference list.
	 * The list contents are of type {@link grl.kpimodel.KPIInformationElementRef}.
	 * It is bidirectional and its opposite is '{@link grl.kpimodel.KPIInformationElementRef#getDef <em>Def</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Refs</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Refs</em>' reference list.
	 * @see grl.kpimodel.KpimodelPackage#getKPIInformationElement_Refs()
	 * @see grl.kpimodel.KPIInformationElementRef#getDef
	 * @model type="grl.kpimodel.KPIInformationElementRef" opposite="def"
	 * @generated
	 */
    EList getRefs();

    /**
	 * Returns the value of the '<em><b>Grlspec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link grl.GRLspec#getKpiInformationElements <em>Kpi Information Elements</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Grlspec</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Grlspec</em>' container reference.
	 * @see #setGrlspec(GRLspec)
	 * @see grl.kpimodel.KpimodelPackage#getKPIInformationElement_Grlspec()
	 * @see grl.GRLspec#getKpiInformationElements
	 * @model opposite="kpiInformationElements" required="true"
	 * @generated
	 */
    GRLspec getGrlspec();

    /**
	 * Sets the value of the '{@link grl.kpimodel.KPIInformationElement#getGrlspec <em>Grlspec</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grlspec</em>' container reference.
	 * @see #getGrlspec()
	 * @generated
	 */
    void setGrlspec(GRLspec value);

    /**
	 * Returns the value of the '<em><b>Kpi Model Links Src</b></em>' reference list.
	 * The list contents are of type {@link grl.kpimodel.KPIModelLink}.
	 * It is bidirectional and its opposite is '{@link grl.kpimodel.KPIModelLink#getKpiInformationElementSrc <em>Kpi Information Element Src</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Kpi Model Links Src</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Kpi Model Links Src</em>' reference list.
	 * @see grl.kpimodel.KpimodelPackage#getKPIInformationElement_KpiModelLinksSrc()
	 * @see grl.kpimodel.KPIModelLink#getKpiInformationElementSrc
	 * @model type="grl.kpimodel.KPIModelLink" opposite="kpiInformationElementSrc"
	 * @generated
	 */
    EList getKpiModelLinksSrc();

} // KPIInformationElement