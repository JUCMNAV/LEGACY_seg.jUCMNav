/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.kpimodel;

import grl.GrlPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see grl.kpimodel.KpimodelFactory
 * @model kind="package"
 * @generated
 */
public interface KpimodelPackage extends EPackage {
    /**
	 * The package name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNAME = "kpimodel";

    /**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_URI = "http:///grl/kpimodel.ecore";

    /**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_PREFIX = "grl.kpimodel";

    /**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    KpimodelPackage eINSTANCE = grl.kpimodel.impl.KpimodelPackageImpl.init();

    /**
	 * The meta object id for the '{@link grl.kpimodel.impl.IndicatorGroupImpl <em>Indicator Group</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.kpimodel.impl.IndicatorGroupImpl
	 * @see grl.kpimodel.impl.KpimodelPackageImpl#getIndicatorGroup()
	 * @generated
	 */
    int INDICATOR_GROUP = 0;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR_GROUP__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR_GROUP__FROM_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR_GROUP__TO_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR_GROUP__ID = UrncorePackage.GR_LMODEL_ELEMENT__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR_GROUP__DESCRIPTION = UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR_GROUP__METADATA = UrncorePackage.GR_LMODEL_ELEMENT__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR_GROUP__INCONCERN = UrncorePackage.GR_LMODEL_ELEMENT__INCONCERN;

    /**
	 * The feature id for the '<em><b>Is Redesign Category</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR_GROUP__IS_REDESIGN_CATEGORY = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Grlspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR_GROUP__GRLSPEC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Indicators</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR_GROUP__INDICATORS = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
	 * The number of structural features of the '<em>Indicator Group</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR_GROUP_FEATURE_COUNT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
	 * The meta object id for the '{@link grl.kpimodel.impl.IndicatorImpl <em>Indicator</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.kpimodel.impl.IndicatorImpl
	 * @see grl.kpimodel.impl.KpimodelPackageImpl#getIndicator()
	 * @generated
	 */
    int INDICATOR = 1;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR__NAME = GrlPackage.INTENTIONAL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR__FROM_LINKS = GrlPackage.INTENTIONAL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR__TO_LINKS = GrlPackage.INTENTIONAL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR__ID = GrlPackage.INTENTIONAL_ELEMENT__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR__DESCRIPTION = GrlPackage.INTENTIONAL_ELEMENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR__METADATA = GrlPackage.INTENTIONAL_ELEMENT__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR__INCONCERN = GrlPackage.INTENTIONAL_ELEMENT__INCONCERN;

    /**
	 * The feature id for the '<em><b>Links Dest</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR__LINKS_DEST = GrlPackage.INTENTIONAL_ELEMENT__LINKS_DEST;

				/**
	 * The feature id for the '<em><b>Links Src</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR__LINKS_SRC = GrlPackage.INTENTIONAL_ELEMENT__LINKS_SRC;

				/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR__TYPE = GrlPackage.INTENTIONAL_ELEMENT__TYPE;

				/**
	 * The feature id for the '<em><b>Decomposition Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR__DECOMPOSITION_TYPE = GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE;

				/**
	 * The feature id for the '<em><b>Importance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDICATOR__IMPORTANCE = GrlPackage.INTENTIONAL_ELEMENT__IMPORTANCE;

				/**
	 * The feature id for the '<em><b>Importance Quantitative</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INDICATOR__IMPORTANCE_QUANTITATIVE = GrlPackage.INTENTIONAL_ELEMENT__IMPORTANCE_QUANTITATIVE;

				/**
	 * The feature id for the '<em><b>Line Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR__LINE_COLOR = GrlPackage.INTENTIONAL_ELEMENT__LINE_COLOR;

				/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR__FILL_COLOR = GrlPackage.INTENTIONAL_ELEMENT__FILL_COLOR;

				/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR__FILLED = GrlPackage.INTENTIONAL_ELEMENT__FILLED;

				/**
	 * The feature id for the '<em><b>Grlspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR__GRLSPEC = GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC;

				/**
	 * The feature id for the '<em><b>Refs</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR__REFS = GrlPackage.INTENTIONAL_ELEMENT__REFS;

    /**
	 * The feature id for the '<em><b>Kpi Model Links Dest</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR__KPI_MODEL_LINKS_DEST = GrlPackage.INTENTIONAL_ELEMENT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR__GROUPS = GrlPackage.INTENTIONAL_ELEMENT_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Indicator</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INDICATOR_FEATURE_COUNT = GrlPackage.INTENTIONAL_ELEMENT_FEATURE_COUNT + 2;

    /**
	 * The meta object id for the '{@link grl.kpimodel.impl.KPIInformationElementImpl <em>KPI Information Element</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.kpimodel.impl.KPIInformationElementImpl
	 * @see grl.kpimodel.impl.KpimodelPackageImpl#getKPIInformationElement()
	 * @generated
	 */
    int KPI_INFORMATION_ELEMENT = 2;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT__FROM_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT__TO_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT__ID = UrncorePackage.GR_LMODEL_ELEMENT__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT__DESCRIPTION = UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT__METADATA = UrncorePackage.GR_LMODEL_ELEMENT__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT__INCONCERN = UrncorePackage.GR_LMODEL_ELEMENT__INCONCERN;

    /**
	 * The feature id for the '<em><b>Refs</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT__REFS = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Grlspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT__GRLSPEC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Kpi Model Links Src</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT__KPI_MODEL_LINKS_SRC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
	 * The number of structural features of the '<em>KPI Information Element</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT_FEATURE_COUNT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
	 * The meta object id for the '{@link grl.kpimodel.impl.KPIInformationElementRefImpl <em>KPI Information Element Ref</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.kpimodel.impl.KPIInformationElementRefImpl
	 * @see grl.kpimodel.impl.KpimodelPackageImpl#getKPIInformationElementRef()
	 * @generated
	 */
    int KPI_INFORMATION_ELEMENT_REF = 3;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT_REF__NAME = GrlPackage.GRL_NODE__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT_REF__FROM_LINKS = GrlPackage.GRL_NODE__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT_REF__TO_LINKS = GrlPackage.GRL_NODE__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT_REF__ID = GrlPackage.GRL_NODE__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT_REF__DESCRIPTION = GrlPackage.GRL_NODE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT_REF__METADATA = GrlPackage.GRL_NODE__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT_REF__INCONCERN = GrlPackage.GRL_NODE__INCONCERN;

    /**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT_REF__X = GrlPackage.GRL_NODE__X;

    /**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT_REF__Y = GrlPackage.GRL_NODE__Y;

    /**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT_REF__DIAGRAM = GrlPackage.GRL_NODE__DIAGRAM;

    /**
	 * The feature id for the '<em><b>Cont Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT_REF__CONT_REF = GrlPackage.GRL_NODE__CONT_REF;

    /**
	 * The feature id for the '<em><b>Succ</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT_REF__SUCC = GrlPackage.GRL_NODE__SUCC;

    /**
	 * The feature id for the '<em><b>Pred</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT_REF__PRED = GrlPackage.GRL_NODE__PRED;

    /**
	 * The feature id for the '<em><b>Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT_REF__LABEL = GrlPackage.GRL_NODE__LABEL;

    /**
	 * The feature id for the '<em><b>Def</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT_REF__DEF = GrlPackage.GRL_NODE_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>KPI Information Element Ref</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_ELEMENT_REF_FEATURE_COUNT = GrlPackage.GRL_NODE_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link grl.kpimodel.impl.KPIModelLinkImpl <em>KPI Model Link</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.kpimodel.impl.KPIModelLinkImpl
	 * @see grl.kpimodel.impl.KpimodelPackageImpl#getKPIModelLink()
	 * @generated
	 */
    int KPI_MODEL_LINK = 4;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_MODEL_LINK__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_MODEL_LINK__FROM_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_MODEL_LINK__TO_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_MODEL_LINK__ID = UrncorePackage.GR_LMODEL_ELEMENT__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_MODEL_LINK__DESCRIPTION = UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_MODEL_LINK__METADATA = UrncorePackage.GR_LMODEL_ELEMENT__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_MODEL_LINK__INCONCERN = UrncorePackage.GR_LMODEL_ELEMENT__INCONCERN;

    /**
	 * The feature id for the '<em><b>Kpi Information Element Src</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_MODEL_LINK__KPI_INFORMATION_ELEMENT_SRC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Refs</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_MODEL_LINK__REFS = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Grlspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_MODEL_LINK__GRLSPEC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Ind Dest</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_MODEL_LINK__IND_DEST = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
	 * The number of structural features of the '<em>KPI Model Link</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_MODEL_LINK_FEATURE_COUNT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
	 * The meta object id for the '{@link grl.kpimodel.impl.KPIModelLinkRefImpl <em>KPI Model Link Ref</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.kpimodel.impl.KPIModelLinkRefImpl
	 * @see grl.kpimodel.impl.KpimodelPackageImpl#getKPIModelLinkRef()
	 * @generated
	 */
    int KPI_MODEL_LINK_REF = 5;

    /**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_MODEL_LINK_REF__SOURCE = UrncorePackage.IURN_CONNECTION__SOURCE;

    /**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_MODEL_LINK_REF__TARGET = UrncorePackage.IURN_CONNECTION__TARGET;

    /**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_MODEL_LINK_REF__DIAGRAM = UrncorePackage.IURN_CONNECTION__DIAGRAM;

    /**
	 * The feature id for the '<em><b>Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KPI_MODEL_LINK_REF__LABEL = UrncorePackage.IURN_CONNECTION__LABEL;

				/**
	 * The feature id for the '<em><b>Link</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_MODEL_LINK_REF__LINK = UrncorePackage.IURN_CONNECTION_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>KPI Model Link Ref</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_MODEL_LINK_REF_FEATURE_COUNT = UrncorePackage.IURN_CONNECTION_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link grl.kpimodel.impl.KPIEvalValueSetImpl <em>KPI Eval Value Set</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.kpimodel.impl.KPIEvalValueSetImpl
	 * @see grl.kpimodel.impl.KpimodelPackageImpl#getKPIEvalValueSet()
	 * @generated
	 */
    int KPI_EVAL_VALUE_SET = 6;

    /**
	 * The feature id for the '<em><b>Target Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_EVAL_VALUE_SET__TARGET_VALUE = 0;

    /**
	 * The feature id for the '<em><b>Threshold Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_EVAL_VALUE_SET__THRESHOLD_VALUE = 1;

    /**
	 * The feature id for the '<em><b>Worst Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_EVAL_VALUE_SET__WORST_VALUE = 2;

    /**
	 * The feature id for the '<em><b>Evaluation Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_EVAL_VALUE_SET__EVALUATION_VALUE = 3;

    /**
	 * The feature id for the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_EVAL_VALUE_SET__UNIT = 4;

    /**
	 * The feature id for the '<em><b>Qualitative Evaluation Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KPI_EVAL_VALUE_SET__QUALITATIVE_EVALUATION_VALUE = 5;

				/**
	 * The feature id for the '<em><b>Eval</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_EVAL_VALUE_SET__EVAL = 6;

    /**
	 * The feature id for the '<em><b>Kpi Conv</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KPI_EVAL_VALUE_SET__KPI_CONV = 7;

				/**
	 * The number of structural features of the '<em>KPI Eval Value Set</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_EVAL_VALUE_SET_FEATURE_COUNT = 8;

    /**
	 * The meta object id for the '{@link grl.kpimodel.impl.KPIInformationConfigImpl <em>KPI Information Config</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.kpimodel.impl.KPIInformationConfigImpl
	 * @see grl.kpimodel.impl.KpimodelPackageImpl#getKPIInformationConfig()
	 * @generated
	 */
    int KPI_INFORMATION_CONFIG = 7;

    /**
	 * The feature id for the '<em><b>Level Of Dimension</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_CONFIG__LEVEL_OF_DIMENSION = 0;

    /**
	 * The feature id for the '<em><b>Value Of Dimension</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_CONFIG__VALUE_OF_DIMENSION = 1;

    /**
	 * The feature id for the '<em><b>Strategies</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_CONFIG__STRATEGIES = 2;

    /**
	 * The feature id for the '<em><b>Kpi Info Element</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_CONFIG__KPI_INFO_ELEMENT = 3;

    /**
	 * The number of structural features of the '<em>KPI Information Config</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int KPI_INFORMATION_CONFIG_FEATURE_COUNT = 4;


    /**
	 * The meta object id for the '{@link grl.kpimodel.impl.KPINewEvalValueImpl <em>KPI New Eval Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see grl.kpimodel.impl.KPINewEvalValueImpl
	 * @see grl.kpimodel.impl.KpimodelPackageImpl#getKPINewEvalValue()
	 * @generated
	 */
	int KPI_NEW_EVAL_VALUE = 8;

				/**
	 * The feature id for the '<em><b>Evaluation Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KPI_NEW_EVAL_VALUE__EVALUATION_VALUE = 0;

				/**
	 * The feature id for the '<em><b>Eval</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KPI_NEW_EVAL_VALUE__EVAL = 1;

				/**
	 * The number of structural features of the '<em>KPI New Eval Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KPI_NEW_EVAL_VALUE_FEATURE_COUNT = 2;


				/**
	 * The meta object id for the '{@link grl.kpimodel.impl.KPIConversionImpl <em>KPI Conversion</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see grl.kpimodel.impl.KPIConversionImpl
	 * @see grl.kpimodel.impl.KpimodelPackageImpl#getKPIConversion()
	 * @generated
	 */
	int KPI_CONVERSION = 9;

				/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KPI_CONVERSION__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KPI_CONVERSION__FROM_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KPI_CONVERSION__TO_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KPI_CONVERSION__ID = UrncorePackage.GR_LMODEL_ELEMENT__ID;

				/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KPI_CONVERSION__DESCRIPTION = UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION;

				/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KPI_CONVERSION__METADATA = UrncorePackage.GR_LMODEL_ELEMENT__METADATA;

				/**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KPI_CONVERSION__INCONCERN = UrncorePackage.GR_LMODEL_ELEMENT__INCONCERN;

				/**
	 * The feature id for the '<em><b>Kpi Eval Value Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KPI_CONVERSION__KPI_EVAL_VALUE_SET = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Grlspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KPI_CONVERSION__GRLSPEC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 1;

				/**
	 * The number of structural features of the '<em>KPI Conversion</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KPI_CONVERSION_FEATURE_COUNT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 2;

				/**
	 * The meta object id for the '{@link grl.kpimodel.impl.QualitativeMappingsImpl <em>Qualitative Mappings</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see grl.kpimodel.impl.QualitativeMappingsImpl
	 * @see grl.kpimodel.impl.KpimodelPackageImpl#getQualitativeMappings()
	 * @generated
	 */
	int QUALITATIVE_MAPPINGS = 10;

				/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITATIVE_MAPPINGS__NAME = KPI_CONVERSION__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITATIVE_MAPPINGS__FROM_LINKS = KPI_CONVERSION__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITATIVE_MAPPINGS__TO_LINKS = KPI_CONVERSION__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITATIVE_MAPPINGS__ID = KPI_CONVERSION__ID;

				/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITATIVE_MAPPINGS__DESCRIPTION = KPI_CONVERSION__DESCRIPTION;

				/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITATIVE_MAPPINGS__METADATA = KPI_CONVERSION__METADATA;

				/**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITATIVE_MAPPINGS__INCONCERN = KPI_CONVERSION__INCONCERN;

				/**
	 * The feature id for the '<em><b>Kpi Eval Value Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITATIVE_MAPPINGS__KPI_EVAL_VALUE_SET = KPI_CONVERSION__KPI_EVAL_VALUE_SET;

				/**
	 * The feature id for the '<em><b>Grlspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITATIVE_MAPPINGS__GRLSPEC = KPI_CONVERSION__GRLSPEC;

				/**
	 * The feature id for the '<em><b>Mapping</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITATIVE_MAPPINGS__MAPPING = KPI_CONVERSION_FEATURE_COUNT + 0;

				/**
	 * The number of structural features of the '<em>Qualitative Mappings</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITATIVE_MAPPINGS_FEATURE_COUNT = KPI_CONVERSION_FEATURE_COUNT + 1;

				/**
	 * The meta object id for the '{@link grl.kpimodel.impl.QualitativeMappingImpl <em>Qualitative Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see grl.kpimodel.impl.QualitativeMappingImpl
	 * @see grl.kpimodel.impl.KpimodelPackageImpl#getQualitativeMapping()
	 * @generated
	 */
	int QUALITATIVE_MAPPING = 11;

				/**
	 * The feature id for the '<em><b>Real World Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITATIVE_MAPPING__REAL_WORLD_LABEL = 0;

				/**
	 * The feature id for the '<em><b>Evaluation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITATIVE_MAPPING__EVALUATION = 1;

				/**
	 * The feature id for the '<em><b>Qualitative Evaluation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITATIVE_MAPPING__QUALITATIVE_EVALUATION = 2;

				/**
	 * The feature id for the '<em><b>Exceeds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITATIVE_MAPPING__EXCEEDS = 3;

				/**
	 * The number of structural features of the '<em>Qualitative Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUALITATIVE_MAPPING_FEATURE_COUNT = 4;

				/**
	 * Returns the meta object for class '{@link grl.kpimodel.IndicatorGroup <em>Indicator Group</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Indicator Group</em>'.
	 * @see grl.kpimodel.IndicatorGroup
	 * @generated
	 */
    EClass getIndicatorGroup();

    /**
	 * Returns the meta object for the attribute '{@link grl.kpimodel.IndicatorGroup#isIsRedesignCategory <em>Is Redesign Category</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Redesign Category</em>'.
	 * @see grl.kpimodel.IndicatorGroup#isIsRedesignCategory()
	 * @see #getIndicatorGroup()
	 * @generated
	 */
    EAttribute getIndicatorGroup_IsRedesignCategory();

    /**
	 * Returns the meta object for the container reference '{@link grl.kpimodel.IndicatorGroup#getGrlspec <em>Grlspec</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Grlspec</em>'.
	 * @see grl.kpimodel.IndicatorGroup#getGrlspec()
	 * @see #getIndicatorGroup()
	 * @generated
	 */
    EReference getIndicatorGroup_Grlspec();

    /**
	 * Returns the meta object for the reference list '{@link grl.kpimodel.IndicatorGroup#getIndicators <em>Indicators</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Indicators</em>'.
	 * @see grl.kpimodel.IndicatorGroup#getIndicators()
	 * @see #getIndicatorGroup()
	 * @generated
	 */
    EReference getIndicatorGroup_Indicators();

    /**
	 * Returns the meta object for class '{@link grl.kpimodel.Indicator <em>Indicator</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Indicator</em>'.
	 * @see grl.kpimodel.Indicator
	 * @generated
	 */
    EClass getIndicator();

    /**
	 * Returns the meta object for the reference list '{@link grl.kpimodel.Indicator#getKpiModelLinksDest <em>Kpi Model Links Dest</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Kpi Model Links Dest</em>'.
	 * @see grl.kpimodel.Indicator#getKpiModelLinksDest()
	 * @see #getIndicator()
	 * @generated
	 */
    EReference getIndicator_KpiModelLinksDest();

    /**
	 * Returns the meta object for the reference list '{@link grl.kpimodel.Indicator#getGroups <em>Groups</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Groups</em>'.
	 * @see grl.kpimodel.Indicator#getGroups()
	 * @see #getIndicator()
	 * @generated
	 */
    EReference getIndicator_Groups();

    /**
	 * Returns the meta object for class '{@link grl.kpimodel.KPIInformationElement <em>KPI Information Element</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>KPI Information Element</em>'.
	 * @see grl.kpimodel.KPIInformationElement
	 * @generated
	 */
    EClass getKPIInformationElement();

    /**
	 * Returns the meta object for the reference list '{@link grl.kpimodel.KPIInformationElement#getRefs <em>Refs</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Refs</em>'.
	 * @see grl.kpimodel.KPIInformationElement#getRefs()
	 * @see #getKPIInformationElement()
	 * @generated
	 */
    EReference getKPIInformationElement_Refs();

    /**
	 * Returns the meta object for the container reference '{@link grl.kpimodel.KPIInformationElement#getGrlspec <em>Grlspec</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Grlspec</em>'.
	 * @see grl.kpimodel.KPIInformationElement#getGrlspec()
	 * @see #getKPIInformationElement()
	 * @generated
	 */
    EReference getKPIInformationElement_Grlspec();

    /**
	 * Returns the meta object for the reference list '{@link grl.kpimodel.KPIInformationElement#getKpiModelLinksSrc <em>Kpi Model Links Src</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Kpi Model Links Src</em>'.
	 * @see grl.kpimodel.KPIInformationElement#getKpiModelLinksSrc()
	 * @see #getKPIInformationElement()
	 * @generated
	 */
    EReference getKPIInformationElement_KpiModelLinksSrc();

    /**
	 * Returns the meta object for class '{@link grl.kpimodel.KPIInformationElementRef <em>KPI Information Element Ref</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>KPI Information Element Ref</em>'.
	 * @see grl.kpimodel.KPIInformationElementRef
	 * @generated
	 */
    EClass getKPIInformationElementRef();

    /**
	 * Returns the meta object for the reference '{@link grl.kpimodel.KPIInformationElementRef#getDef <em>Def</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Def</em>'.
	 * @see grl.kpimodel.KPIInformationElementRef#getDef()
	 * @see #getKPIInformationElementRef()
	 * @generated
	 */
    EReference getKPIInformationElementRef_Def();

    /**
	 * Returns the meta object for class '{@link grl.kpimodel.KPIModelLink <em>KPI Model Link</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>KPI Model Link</em>'.
	 * @see grl.kpimodel.KPIModelLink
	 * @generated
	 */
    EClass getKPIModelLink();

    /**
	 * Returns the meta object for the reference '{@link grl.kpimodel.KPIModelLink#getKpiInformationElementSrc <em>Kpi Information Element Src</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Kpi Information Element Src</em>'.
	 * @see grl.kpimodel.KPIModelLink#getKpiInformationElementSrc()
	 * @see #getKPIModelLink()
	 * @generated
	 */
    EReference getKPIModelLink_KpiInformationElementSrc();

    /**
	 * Returns the meta object for the reference list '{@link grl.kpimodel.KPIModelLink#getRefs <em>Refs</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Refs</em>'.
	 * @see grl.kpimodel.KPIModelLink#getRefs()
	 * @see #getKPIModelLink()
	 * @generated
	 */
    EReference getKPIModelLink_Refs();

    /**
	 * Returns the meta object for the container reference '{@link grl.kpimodel.KPIModelLink#getGrlspec <em>Grlspec</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Grlspec</em>'.
	 * @see grl.kpimodel.KPIModelLink#getGrlspec()
	 * @see #getKPIModelLink()
	 * @generated
	 */
    EReference getKPIModelLink_Grlspec();

    /**
	 * Returns the meta object for the reference '{@link grl.kpimodel.KPIModelLink#getIndDest <em>Ind Dest</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ind Dest</em>'.
	 * @see grl.kpimodel.KPIModelLink#getIndDest()
	 * @see #getKPIModelLink()
	 * @generated
	 */
    EReference getKPIModelLink_IndDest();

    /**
	 * Returns the meta object for class '{@link grl.kpimodel.KPIModelLinkRef <em>KPI Model Link Ref</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>KPI Model Link Ref</em>'.
	 * @see grl.kpimodel.KPIModelLinkRef
	 * @generated
	 */
    EClass getKPIModelLinkRef();

    /**
	 * Returns the meta object for the reference '{@link grl.kpimodel.KPIModelLinkRef#getLink <em>Link</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Link</em>'.
	 * @see grl.kpimodel.KPIModelLinkRef#getLink()
	 * @see #getKPIModelLinkRef()
	 * @generated
	 */
    EReference getKPIModelLinkRef_Link();

    /**
	 * Returns the meta object for class '{@link grl.kpimodel.KPIEvalValueSet <em>KPI Eval Value Set</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>KPI Eval Value Set</em>'.
	 * @see grl.kpimodel.KPIEvalValueSet
	 * @generated
	 */
    EClass getKPIEvalValueSet();

    /**
	 * Returns the meta object for the attribute '{@link grl.kpimodel.KPIEvalValueSet#getTargetValue <em>Target Value</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Value</em>'.
	 * @see grl.kpimodel.KPIEvalValueSet#getTargetValue()
	 * @see #getKPIEvalValueSet()
	 * @generated
	 */
    EAttribute getKPIEvalValueSet_TargetValue();

    /**
	 * Returns the meta object for the attribute '{@link grl.kpimodel.KPIEvalValueSet#getThresholdValue <em>Threshold Value</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Threshold Value</em>'.
	 * @see grl.kpimodel.KPIEvalValueSet#getThresholdValue()
	 * @see #getKPIEvalValueSet()
	 * @generated
	 */
    EAttribute getKPIEvalValueSet_ThresholdValue();

    /**
	 * Returns the meta object for the attribute '{@link grl.kpimodel.KPIEvalValueSet#getWorstValue <em>Worst Value</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Worst Value</em>'.
	 * @see grl.kpimodel.KPIEvalValueSet#getWorstValue()
	 * @see #getKPIEvalValueSet()
	 * @generated
	 */
    EAttribute getKPIEvalValueSet_WorstValue();

    /**
	 * Returns the meta object for the attribute '{@link grl.kpimodel.KPIEvalValueSet#getEvaluationValue <em>Evaluation Value</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Evaluation Value</em>'.
	 * @see grl.kpimodel.KPIEvalValueSet#getEvaluationValue()
	 * @see #getKPIEvalValueSet()
	 * @generated
	 */
    EAttribute getKPIEvalValueSet_EvaluationValue();

    /**
	 * Returns the meta object for the attribute '{@link grl.kpimodel.KPIEvalValueSet#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unit</em>'.
	 * @see grl.kpimodel.KPIEvalValueSet#getUnit()
	 * @see #getKPIEvalValueSet()
	 * @generated
	 */
    EAttribute getKPIEvalValueSet_Unit();

    /**
	 * Returns the meta object for the attribute '{@link grl.kpimodel.KPIEvalValueSet#getQualitativeEvaluationValue <em>Qualitative Evaluation Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Qualitative Evaluation Value</em>'.
	 * @see grl.kpimodel.KPIEvalValueSet#getQualitativeEvaluationValue()
	 * @see #getKPIEvalValueSet()
	 * @generated
	 */
	EAttribute getKPIEvalValueSet_QualitativeEvaluationValue();

				/**
	 * Returns the meta object for the container reference '{@link grl.kpimodel.KPIEvalValueSet#getEval <em>Eval</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Eval</em>'.
	 * @see grl.kpimodel.KPIEvalValueSet#getEval()
	 * @see #getKPIEvalValueSet()
	 * @generated
	 */
    EReference getKPIEvalValueSet_Eval();

    /**
	 * Returns the meta object for the reference '{@link grl.kpimodel.KPIEvalValueSet#getKpiConv <em>Kpi Conv</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Kpi Conv</em>'.
	 * @see grl.kpimodel.KPIEvalValueSet#getKpiConv()
	 * @see #getKPIEvalValueSet()
	 * @generated
	 */
	EReference getKPIEvalValueSet_KpiConv();

				/**
	 * Returns the meta object for class '{@link grl.kpimodel.KPIInformationConfig <em>KPI Information Config</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>KPI Information Config</em>'.
	 * @see grl.kpimodel.KPIInformationConfig
	 * @generated
	 */
    EClass getKPIInformationConfig();

    /**
	 * Returns the meta object for the attribute '{@link grl.kpimodel.KPIInformationConfig#getLevelOfDimension <em>Level Of Dimension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Level Of Dimension</em>'.
	 * @see grl.kpimodel.KPIInformationConfig#getLevelOfDimension()
	 * @see #getKPIInformationConfig()
	 * @generated
	 */
    EAttribute getKPIInformationConfig_LevelOfDimension();

    /**
	 * Returns the meta object for the attribute '{@link grl.kpimodel.KPIInformationConfig#getValueOfDimension <em>Value Of Dimension</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value Of Dimension</em>'.
	 * @see grl.kpimodel.KPIInformationConfig#getValueOfDimension()
	 * @see #getKPIInformationConfig()
	 * @generated
	 */
    EAttribute getKPIInformationConfig_ValueOfDimension();

    /**
	 * Returns the meta object for the container reference '{@link grl.kpimodel.KPIInformationConfig#getStrategies <em>Strategies</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Strategies</em>'.
	 * @see grl.kpimodel.KPIInformationConfig#getStrategies()
	 * @see #getKPIInformationConfig()
	 * @generated
	 */
    EReference getKPIInformationConfig_Strategies();

    /**
	 * Returns the meta object for the reference '{@link grl.kpimodel.KPIInformationConfig#getKpiInfoElement <em>Kpi Info Element</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Kpi Info Element</em>'.
	 * @see grl.kpimodel.KPIInformationConfig#getKpiInfoElement()
	 * @see #getKPIInformationConfig()
	 * @generated
	 */
    EReference getKPIInformationConfig_KpiInfoElement();

    /**
	 * Returns the meta object for class '{@link grl.kpimodel.KPINewEvalValue <em>KPI New Eval Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>KPI New Eval Value</em>'.
	 * @see grl.kpimodel.KPINewEvalValue
	 * @generated
	 */
	EClass getKPINewEvalValue();

				/**
	 * Returns the meta object for the attribute '{@link grl.kpimodel.KPINewEvalValue#getEvaluationValue <em>Evaluation Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Evaluation Value</em>'.
	 * @see grl.kpimodel.KPINewEvalValue#getEvaluationValue()
	 * @see #getKPINewEvalValue()
	 * @generated
	 */
	EAttribute getKPINewEvalValue_EvaluationValue();

				/**
	 * Returns the meta object for the container reference '{@link grl.kpimodel.KPINewEvalValue#getEval <em>Eval</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Eval</em>'.
	 * @see grl.kpimodel.KPINewEvalValue#getEval()
	 * @see #getKPINewEvalValue()
	 * @generated
	 */
	EReference getKPINewEvalValue_Eval();

				/**
	 * Returns the meta object for class '{@link grl.kpimodel.KPIConversion <em>KPI Conversion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>KPI Conversion</em>'.
	 * @see grl.kpimodel.KPIConversion
	 * @generated
	 */
	EClass getKPIConversion();

				/**
	 * Returns the meta object for the reference list '{@link grl.kpimodel.KPIConversion#getKpiEvalValueSet <em>Kpi Eval Value Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Kpi Eval Value Set</em>'.
	 * @see grl.kpimodel.KPIConversion#getKpiEvalValueSet()
	 * @see #getKPIConversion()
	 * @generated
	 */
	EReference getKPIConversion_KpiEvalValueSet();

				/**
	 * Returns the meta object for the container reference '{@link grl.kpimodel.KPIConversion#getGrlspec <em>Grlspec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Grlspec</em>'.
	 * @see grl.kpimodel.KPIConversion#getGrlspec()
	 * @see #getKPIConversion()
	 * @generated
	 */
	EReference getKPIConversion_Grlspec();

				/**
	 * Returns the meta object for class '{@link grl.kpimodel.QualitativeMappings <em>Qualitative Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Qualitative Mappings</em>'.
	 * @see grl.kpimodel.QualitativeMappings
	 * @generated
	 */
	EClass getQualitativeMappings();

				/**
	 * Returns the meta object for the containment reference list '{@link grl.kpimodel.QualitativeMappings#getMapping <em>Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mapping</em>'.
	 * @see grl.kpimodel.QualitativeMappings#getMapping()
	 * @see #getQualitativeMappings()
	 * @generated
	 */
	EReference getQualitativeMappings_Mapping();

				/**
	 * Returns the meta object for class '{@link grl.kpimodel.QualitativeMapping <em>Qualitative Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Qualitative Mapping</em>'.
	 * @see grl.kpimodel.QualitativeMapping
	 * @generated
	 */
	EClass getQualitativeMapping();

				/**
	 * Returns the meta object for the attribute '{@link grl.kpimodel.QualitativeMapping#getRealWorldLabel <em>Real World Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Real World Label</em>'.
	 * @see grl.kpimodel.QualitativeMapping#getRealWorldLabel()
	 * @see #getQualitativeMapping()
	 * @generated
	 */
	EAttribute getQualitativeMapping_RealWorldLabel();

				/**
	 * Returns the meta object for the attribute '{@link grl.kpimodel.QualitativeMapping#getEvaluation <em>Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Evaluation</em>'.
	 * @see grl.kpimodel.QualitativeMapping#getEvaluation()
	 * @see #getQualitativeMapping()
	 * @generated
	 */
	EAttribute getQualitativeMapping_Evaluation();

				/**
	 * Returns the meta object for the attribute '{@link grl.kpimodel.QualitativeMapping#getQualitativeEvaluation <em>Qualitative Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Qualitative Evaluation</em>'.
	 * @see grl.kpimodel.QualitativeMapping#getQualitativeEvaluation()
	 * @see #getQualitativeMapping()
	 * @generated
	 */
	EAttribute getQualitativeMapping_QualitativeEvaluation();

				/**
	 * Returns the meta object for the attribute '{@link grl.kpimodel.QualitativeMapping#isExceeds <em>Exceeds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Exceeds</em>'.
	 * @see grl.kpimodel.QualitativeMapping#isExceeds()
	 * @see #getQualitativeMapping()
	 * @generated
	 */
	EAttribute getQualitativeMapping_Exceeds();

				/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
    KpimodelFactory getKpimodelFactory();

    /**
	 * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     *   <li>each class,</li>
     *   <li>each feature of each class,</li>
     *   <li>each enum,</li>
     *   <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
	 * @generated
	 */
    interface Literals {
        /**
		 * The meta object literal for the '{@link grl.kpimodel.impl.IndicatorGroupImpl <em>Indicator Group</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see grl.kpimodel.impl.IndicatorGroupImpl
		 * @see grl.kpimodel.impl.KpimodelPackageImpl#getIndicatorGroup()
		 * @generated
		 */
        EClass INDICATOR_GROUP = eINSTANCE.getIndicatorGroup();

        /**
		 * The meta object literal for the '<em><b>Is Redesign Category</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute INDICATOR_GROUP__IS_REDESIGN_CATEGORY = eINSTANCE.getIndicatorGroup_IsRedesignCategory();

        /**
		 * The meta object literal for the '<em><b>Grlspec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference INDICATOR_GROUP__GRLSPEC = eINSTANCE.getIndicatorGroup_Grlspec();

        /**
		 * The meta object literal for the '<em><b>Indicators</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference INDICATOR_GROUP__INDICATORS = eINSTANCE.getIndicatorGroup_Indicators();

        /**
		 * The meta object literal for the '{@link grl.kpimodel.impl.IndicatorImpl <em>Indicator</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see grl.kpimodel.impl.IndicatorImpl
		 * @see grl.kpimodel.impl.KpimodelPackageImpl#getIndicator()
		 * @generated
		 */
        EClass INDICATOR = eINSTANCE.getIndicator();

        /**
		 * The meta object literal for the '<em><b>Kpi Model Links Dest</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference INDICATOR__KPI_MODEL_LINKS_DEST = eINSTANCE.getIndicator_KpiModelLinksDest();

        /**
		 * The meta object literal for the '<em><b>Groups</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference INDICATOR__GROUPS = eINSTANCE.getIndicator_Groups();

        /**
		 * The meta object literal for the '{@link grl.kpimodel.impl.KPIInformationElementImpl <em>KPI Information Element</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see grl.kpimodel.impl.KPIInformationElementImpl
		 * @see grl.kpimodel.impl.KpimodelPackageImpl#getKPIInformationElement()
		 * @generated
		 */
        EClass KPI_INFORMATION_ELEMENT = eINSTANCE.getKPIInformationElement();

        /**
		 * The meta object literal for the '<em><b>Refs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference KPI_INFORMATION_ELEMENT__REFS = eINSTANCE.getKPIInformationElement_Refs();

        /**
		 * The meta object literal for the '<em><b>Grlspec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference KPI_INFORMATION_ELEMENT__GRLSPEC = eINSTANCE.getKPIInformationElement_Grlspec();

        /**
		 * The meta object literal for the '<em><b>Kpi Model Links Src</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference KPI_INFORMATION_ELEMENT__KPI_MODEL_LINKS_SRC = eINSTANCE.getKPIInformationElement_KpiModelLinksSrc();

        /**
		 * The meta object literal for the '{@link grl.kpimodel.impl.KPIInformationElementRefImpl <em>KPI Information Element Ref</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see grl.kpimodel.impl.KPIInformationElementRefImpl
		 * @see grl.kpimodel.impl.KpimodelPackageImpl#getKPIInformationElementRef()
		 * @generated
		 */
        EClass KPI_INFORMATION_ELEMENT_REF = eINSTANCE.getKPIInformationElementRef();

        /**
		 * The meta object literal for the '<em><b>Def</b></em>' reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference KPI_INFORMATION_ELEMENT_REF__DEF = eINSTANCE.getKPIInformationElementRef_Def();

        /**
		 * The meta object literal for the '{@link grl.kpimodel.impl.KPIModelLinkImpl <em>KPI Model Link</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see grl.kpimodel.impl.KPIModelLinkImpl
		 * @see grl.kpimodel.impl.KpimodelPackageImpl#getKPIModelLink()
		 * @generated
		 */
        EClass KPI_MODEL_LINK = eINSTANCE.getKPIModelLink();

        /**
		 * The meta object literal for the '<em><b>Kpi Information Element Src</b></em>' reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference KPI_MODEL_LINK__KPI_INFORMATION_ELEMENT_SRC = eINSTANCE.getKPIModelLink_KpiInformationElementSrc();

        /**
		 * The meta object literal for the '<em><b>Refs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference KPI_MODEL_LINK__REFS = eINSTANCE.getKPIModelLink_Refs();

        /**
		 * The meta object literal for the '<em><b>Grlspec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference KPI_MODEL_LINK__GRLSPEC = eINSTANCE.getKPIModelLink_Grlspec();

        /**
		 * The meta object literal for the '<em><b>Ind Dest</b></em>' reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference KPI_MODEL_LINK__IND_DEST = eINSTANCE.getKPIModelLink_IndDest();

        /**
		 * The meta object literal for the '{@link grl.kpimodel.impl.KPIModelLinkRefImpl <em>KPI Model Link Ref</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see grl.kpimodel.impl.KPIModelLinkRefImpl
		 * @see grl.kpimodel.impl.KpimodelPackageImpl#getKPIModelLinkRef()
		 * @generated
		 */
        EClass KPI_MODEL_LINK_REF = eINSTANCE.getKPIModelLinkRef();

        /**
		 * The meta object literal for the '<em><b>Link</b></em>' reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference KPI_MODEL_LINK_REF__LINK = eINSTANCE.getKPIModelLinkRef_Link();

        /**
		 * The meta object literal for the '{@link grl.kpimodel.impl.KPIEvalValueSetImpl <em>KPI Eval Value Set</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see grl.kpimodel.impl.KPIEvalValueSetImpl
		 * @see grl.kpimodel.impl.KpimodelPackageImpl#getKPIEvalValueSet()
		 * @generated
		 */
        EClass KPI_EVAL_VALUE_SET = eINSTANCE.getKPIEvalValueSet();

        /**
		 * The meta object literal for the '<em><b>Target Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute KPI_EVAL_VALUE_SET__TARGET_VALUE = eINSTANCE.getKPIEvalValueSet_TargetValue();

        /**
		 * The meta object literal for the '<em><b>Threshold Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute KPI_EVAL_VALUE_SET__THRESHOLD_VALUE = eINSTANCE.getKPIEvalValueSet_ThresholdValue();

        /**
		 * The meta object literal for the '<em><b>Worst Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute KPI_EVAL_VALUE_SET__WORST_VALUE = eINSTANCE.getKPIEvalValueSet_WorstValue();

        /**
		 * The meta object literal for the '<em><b>Evaluation Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute KPI_EVAL_VALUE_SET__EVALUATION_VALUE = eINSTANCE.getKPIEvalValueSet_EvaluationValue();

        /**
		 * The meta object literal for the '<em><b>Unit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute KPI_EVAL_VALUE_SET__UNIT = eINSTANCE.getKPIEvalValueSet_Unit();

        /**
		 * The meta object literal for the '<em><b>Qualitative Evaluation Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute KPI_EVAL_VALUE_SET__QUALITATIVE_EVALUATION_VALUE = eINSTANCE.getKPIEvalValueSet_QualitativeEvaluationValue();

								/**
		 * The meta object literal for the '<em><b>Eval</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference KPI_EVAL_VALUE_SET__EVAL = eINSTANCE.getKPIEvalValueSet_Eval();

        /**
		 * The meta object literal for the '<em><b>Kpi Conv</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference KPI_EVAL_VALUE_SET__KPI_CONV = eINSTANCE.getKPIEvalValueSet_KpiConv();

								/**
		 * The meta object literal for the '{@link grl.kpimodel.impl.KPIInformationConfigImpl <em>KPI Information Config</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see grl.kpimodel.impl.KPIInformationConfigImpl
		 * @see grl.kpimodel.impl.KpimodelPackageImpl#getKPIInformationConfig()
		 * @generated
		 */
        EClass KPI_INFORMATION_CONFIG = eINSTANCE.getKPIInformationConfig();

        /**
		 * The meta object literal for the '<em><b>Level Of Dimension</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute KPI_INFORMATION_CONFIG__LEVEL_OF_DIMENSION = eINSTANCE.getKPIInformationConfig_LevelOfDimension();

        /**
		 * The meta object literal for the '<em><b>Value Of Dimension</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute KPI_INFORMATION_CONFIG__VALUE_OF_DIMENSION = eINSTANCE.getKPIInformationConfig_ValueOfDimension();

        /**
		 * The meta object literal for the '<em><b>Strategies</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference KPI_INFORMATION_CONFIG__STRATEGIES = eINSTANCE.getKPIInformationConfig_Strategies();

        /**
		 * The meta object literal for the '<em><b>Kpi Info Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference KPI_INFORMATION_CONFIG__KPI_INFO_ELEMENT = eINSTANCE.getKPIInformationConfig_KpiInfoElement();

								/**
		 * The meta object literal for the '{@link grl.kpimodel.impl.KPINewEvalValueImpl <em>KPI New Eval Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.kpimodel.impl.KPINewEvalValueImpl
		 * @see grl.kpimodel.impl.KpimodelPackageImpl#getKPINewEvalValue()
		 * @generated
		 */
		EClass KPI_NEW_EVAL_VALUE = eINSTANCE.getKPINewEvalValue();

								/**
		 * The meta object literal for the '<em><b>Evaluation Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute KPI_NEW_EVAL_VALUE__EVALUATION_VALUE = eINSTANCE.getKPINewEvalValue_EvaluationValue();

								/**
		 * The meta object literal for the '<em><b>Eval</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference KPI_NEW_EVAL_VALUE__EVAL = eINSTANCE.getKPINewEvalValue_Eval();

								/**
		 * The meta object literal for the '{@link grl.kpimodel.impl.KPIConversionImpl <em>KPI Conversion</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.kpimodel.impl.KPIConversionImpl
		 * @see grl.kpimodel.impl.KpimodelPackageImpl#getKPIConversion()
		 * @generated
		 */
		EClass KPI_CONVERSION = eINSTANCE.getKPIConversion();

								/**
		 * The meta object literal for the '<em><b>Kpi Eval Value Set</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference KPI_CONVERSION__KPI_EVAL_VALUE_SET = eINSTANCE.getKPIConversion_KpiEvalValueSet();

								/**
		 * The meta object literal for the '<em><b>Grlspec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference KPI_CONVERSION__GRLSPEC = eINSTANCE.getKPIConversion_Grlspec();

								/**
		 * The meta object literal for the '{@link grl.kpimodel.impl.QualitativeMappingsImpl <em>Qualitative Mappings</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.kpimodel.impl.QualitativeMappingsImpl
		 * @see grl.kpimodel.impl.KpimodelPackageImpl#getQualitativeMappings()
		 * @generated
		 */
		EClass QUALITATIVE_MAPPINGS = eINSTANCE.getQualitativeMappings();

								/**
		 * The meta object literal for the '<em><b>Mapping</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference QUALITATIVE_MAPPINGS__MAPPING = eINSTANCE.getQualitativeMappings_Mapping();

								/**
		 * The meta object literal for the '{@link grl.kpimodel.impl.QualitativeMappingImpl <em>Qualitative Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.kpimodel.impl.QualitativeMappingImpl
		 * @see grl.kpimodel.impl.KpimodelPackageImpl#getQualitativeMapping()
		 * @generated
		 */
		EClass QUALITATIVE_MAPPING = eINSTANCE.getQualitativeMapping();

								/**
		 * The meta object literal for the '<em><b>Real World Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUALITATIVE_MAPPING__REAL_WORLD_LABEL = eINSTANCE.getQualitativeMapping_RealWorldLabel();

								/**
		 * The meta object literal for the '<em><b>Evaluation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUALITATIVE_MAPPING__EVALUATION = eINSTANCE.getQualitativeMapping_Evaluation();

								/**
		 * The meta object literal for the '<em><b>Qualitative Evaluation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUALITATIVE_MAPPING__QUALITATIVE_EVALUATION = eINSTANCE.getQualitativeMapping_QualitativeEvaluation();

								/**
		 * The meta object literal for the '<em><b>Exceeds</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUALITATIVE_MAPPING__EXCEEDS = eINSTANCE.getQualitativeMapping_Exceeds();

    }

} //KpimodelPackage
