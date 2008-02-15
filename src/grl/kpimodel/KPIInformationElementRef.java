/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.kpimodel;

import grl.GRLNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KPI Information Element Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.kpimodel.KPIInformationElementRef#getDef <em>Def</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.kpimodel.KpimodelPackage#getKPIInformationElementRef()
 * @model
 * @generated
 */
public interface KPIInformationElementRef extends GRLNode {
    /**
	 * Returns the value of the '<em><b>Def</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link grl.kpimodel.KPIInformationElement#getRefs <em>Refs</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Def</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Def</em>' reference.
	 * @see #setDef(KPIInformationElement)
	 * @see grl.kpimodel.KpimodelPackage#getKPIInformationElementRef_Def()
	 * @see grl.kpimodel.KPIInformationElement#getRefs
	 * @model opposite="refs" required="true"
	 * @generated
	 */
    KPIInformationElement getDef();

    /**
	 * Sets the value of the '{@link grl.kpimodel.KPIInformationElementRef#getDef <em>Def</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Def</em>' reference.
	 * @see #getDef()
	 * @generated
	 */
    void setDef(KPIInformationElement value);

} // KPIInformationElementRef