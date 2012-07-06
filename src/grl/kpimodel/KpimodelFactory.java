/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.kpimodel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see grl.kpimodel.KpimodelPackage
 * @generated
 */
public interface KpimodelFactory extends EFactory {
    /**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    KpimodelFactory eINSTANCE = grl.kpimodel.impl.KpimodelFactoryImpl.init();

    /**
	 * Returns a new object of class '<em>Indicator Group</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Indicator Group</em>'.
	 * @generated
	 */
    IndicatorGroup createIndicatorGroup();

    /**
	 * Returns a new object of class '<em>Indicator</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Indicator</em>'.
	 * @generated
	 */
    Indicator createIndicator();

    /**
	 * Returns a new object of class '<em>KPI Information Element</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>KPI Information Element</em>'.
	 * @generated
	 */
    KPIInformationElement createKPIInformationElement();

    /**
	 * Returns a new object of class '<em>KPI Information Element Ref</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>KPI Information Element Ref</em>'.
	 * @generated
	 */
    KPIInformationElementRef createKPIInformationElementRef();

    /**
	 * Returns a new object of class '<em>KPI Model Link</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>KPI Model Link</em>'.
	 * @generated
	 */
    KPIModelLink createKPIModelLink();

    /**
	 * Returns a new object of class '<em>KPI Model Link Ref</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>KPI Model Link Ref</em>'.
	 * @generated
	 */
    KPIModelLinkRef createKPIModelLinkRef();

    /**
	 * Returns a new object of class '<em>KPI Eval Value Set</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>KPI Eval Value Set</em>'.
	 * @generated
	 */
    KPIEvalValueSet createKPIEvalValueSet();

    /**
	 * Returns a new object of class '<em>KPI Information Config</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>KPI Information Config</em>'.
	 * @generated
	 */
    KPIInformationConfig createKPIInformationConfig();

    /**
	 * Returns a new object of class '<em>KPI New Eval Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>KPI New Eval Value</em>'.
	 * @generated
	 */
	KPINewEvalValue createKPINewEvalValue();

				/**
	 * Returns a new object of class '<em>Qualitative Mappings</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Qualitative Mappings</em>'.
	 * @generated
	 */
	QualitativeMappings createQualitativeMappings();

				/**
	 * Returns a new object of class '<em>Qualitative Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Qualitative Mapping</em>'.
	 * @generated
	 */
	QualitativeMapping createQualitativeMapping();

				/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
    KpimodelPackage getKpimodelPackage();

} //KpimodelFactory
