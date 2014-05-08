/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.kpimodel.impl;

import grl.kpimodel.*;
import grl.kpimodel.Indicator;
import grl.kpimodel.IndicatorGroup;
import grl.kpimodel.KPIEvalValueSet;
import grl.kpimodel.KPIInformationConfig;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;
import grl.kpimodel.KPIModelLink;
import grl.kpimodel.KPIModelLinkRef;
import grl.kpimodel.KpimodelFactory;
import grl.kpimodel.KpimodelPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class KpimodelFactoryImpl extends EFactoryImpl implements KpimodelFactory {
    /**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static KpimodelFactory init() {
		try {
			KpimodelFactory theKpimodelFactory = (KpimodelFactory)EPackage.Registry.INSTANCE.getEFactory(KpimodelPackage.eNS_URI);
			if (theKpimodelFactory != null) {
				return theKpimodelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new KpimodelFactoryImpl();
	}

    /**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public KpimodelFactoryImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case KpimodelPackage.INDICATOR_GROUP: return createIndicatorGroup();
			case KpimodelPackage.INDICATOR: return createIndicator();
			case KpimodelPackage.KPI_INFORMATION_ELEMENT: return createKPIInformationElement();
			case KpimodelPackage.KPI_INFORMATION_ELEMENT_REF: return createKPIInformationElementRef();
			case KpimodelPackage.KPI_MODEL_LINK: return createKPIModelLink();
			case KpimodelPackage.KPI_MODEL_LINK_REF: return createKPIModelLinkRef();
			case KpimodelPackage.KPI_EVAL_VALUE_SET: return createKPIEvalValueSet();
			case KpimodelPackage.KPI_INFORMATION_CONFIG: return createKPIInformationConfig();
			case KpimodelPackage.KPI_NEW_EVAL_VALUE: return createKPINewEvalValue();
			case KpimodelPackage.QUALITATIVE_MAPPINGS: return createQualitativeMappings();
			case KpimodelPackage.QUALITATIVE_MAPPING: return createQualitativeMapping();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public IndicatorGroup createIndicatorGroup() {
		IndicatorGroupImpl indicatorGroup = new IndicatorGroupImpl();
		return indicatorGroup;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Indicator createIndicator() {
		IndicatorImpl indicator = new IndicatorImpl();
		return indicator;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public KPIInformationElement createKPIInformationElement() {
		KPIInformationElementImpl kpiInformationElement = new KPIInformationElementImpl();
		return kpiInformationElement;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public KPIInformationElementRef createKPIInformationElementRef() {
		KPIInformationElementRefImpl kpiInformationElementRef = new KPIInformationElementRefImpl();
		return kpiInformationElementRef;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public KPIModelLink createKPIModelLink() {
		KPIModelLinkImpl kpiModelLink = new KPIModelLinkImpl();
		return kpiModelLink;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public KPIModelLinkRef createKPIModelLinkRef() {
		KPIModelLinkRefImpl kpiModelLinkRef = new KPIModelLinkRefImpl();
		return kpiModelLinkRef;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public KPIEvalValueSet createKPIEvalValueSet() {
		KPIEvalValueSetImpl kpiEvalValueSet = new KPIEvalValueSetImpl();
		return kpiEvalValueSet;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public KPIInformationConfig createKPIInformationConfig() {
		KPIInformationConfigImpl kpiInformationConfig = new KPIInformationConfigImpl();
		return kpiInformationConfig;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KPINewEvalValue createKPINewEvalValue() {
		KPINewEvalValueImpl kpiNewEvalValue = new KPINewEvalValueImpl();
		return kpiNewEvalValue;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QualitativeMappings createQualitativeMappings() {
		QualitativeMappingsImpl qualitativeMappings = new QualitativeMappingsImpl();
		return qualitativeMappings;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QualitativeMapping createQualitativeMapping() {
		QualitativeMappingImpl qualitativeMapping = new QualitativeMappingImpl();
		return qualitativeMapping;
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public KpimodelPackage getKpimodelPackage() {
		return (KpimodelPackage)getEPackage();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
    public static KpimodelPackage getPackage() {
		return KpimodelPackage.eINSTANCE;
	}

} //KpimodelFactoryImpl
