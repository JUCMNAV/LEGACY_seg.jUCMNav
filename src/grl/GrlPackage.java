/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;

import ca.mcgill.sel.core.CorePackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see grl.GrlFactory
 * @model kind="package"
 * @generated
 */
public interface GrlPackage extends EPackage {
    /**
	 * The package name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNAME = "grl";

    /**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_URI = "http:///grl.ecore";

    /**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_PREFIX = "grl";

    /**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    GrlPackage eINSTANCE = grl.impl.GrlPackageImpl.init();

    /**
	 * The meta object id for the '{@link grl.impl.GRLspecImpl <em>GR Lspec</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.impl.GRLspecImpl
	 * @see grl.impl.GrlPackageImpl#getGRLspec()
	 * @generated
	 */
    int GR_LSPEC = 0;

    /**
	 * The feature id for the '<em><b>Urnspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GR_LSPEC__URNSPEC = 0;

    /**
	 * The feature id for the '<em><b>Int Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GR_LSPEC__INT_ELEMENTS = 1;

    /**
	 * The feature id for the '<em><b>Actors</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GR_LSPEC__ACTORS = 2;

    /**
	 * The feature id for the '<em><b>Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GR_LSPEC__LINKS = 3;

    /**
	 * The feature id for the '<em><b>Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GR_LSPEC__GROUPS = 4;

    /**
	 * The feature id for the '<em><b>Strategies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GR_LSPEC__STRATEGIES = 5;

    /**
	 * The feature id for the '<em><b>Contribution Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GR_LSPEC__CONTRIBUTION_GROUPS = 6;

				/**
	 * The feature id for the '<em><b>Contribution Contexts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GR_LSPEC__CONTRIBUTION_CONTEXTS = 7;

				/**
	 * The feature id for the '<em><b>Impact Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GR_LSPEC__IMPACT_MODEL = 8;

				/**
	 * The feature id for the '<em><b>Kpi Information Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GR_LSPEC__KPI_INFORMATION_ELEMENTS = 9;

    /**
	 * The feature id for the '<em><b>Kpi Model Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GR_LSPEC__KPI_MODEL_LINKS = 10;

    /**
	 * The feature id for the '<em><b>Indicator Group</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GR_LSPEC__INDICATOR_GROUP = 11;

    /**
	 * The feature id for the '<em><b>KPI Conversion</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GR_LSPEC__KPI_CONVERSION = 12;

				/**
	 * The feature id for the '<em><b>Feature Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GR_LSPEC__FEATURE_MODEL = 13;

				/**
	 * The number of structural features of the '<em>GR Lspec</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GR_LSPEC_FEATURE_COUNT = 14;

    /**
	 * The meta object id for the '{@link grl.impl.GRLNodeImpl <em>GRL Node</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.impl.GRLNodeImpl
	 * @see grl.impl.GrlPackageImpl#getGRLNode()
	 * @generated
	 */
    int GRL_NODE = 14;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_NODE__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_NODE__FROM_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_NODE__TO_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_NODE__ID = UrncorePackage.GR_LMODEL_ELEMENT__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_NODE__DESCRIPTION = UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRL_NODE__METADATA = UrncorePackage.GR_LMODEL_ELEMENT__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_NODE__INCONCERN = UrncorePackage.GR_LMODEL_ELEMENT__INCONCERN;

    /**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_NODE__X = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_NODE__Y = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_NODE__DIAGRAM = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Cont Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_NODE__CONT_REF = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Succ</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_NODE__SUCC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>Pred</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_NODE__PRED = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 5;

    /**
	 * The feature id for the '<em><b>Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_NODE__LABEL = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 6;

    /**
	 * The number of structural features of the '<em>GRL Node</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_NODE_FEATURE_COUNT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 7;

    /**
	 * The meta object id for the '{@link grl.impl.BeliefImpl <em>Belief</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.impl.BeliefImpl
	 * @see grl.impl.GrlPackageImpl#getBelief()
	 * @generated
	 */
    int BELIEF = 1;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BELIEF__NAME = GRL_NODE__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BELIEF__FROM_LINKS = GRL_NODE__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BELIEF__TO_LINKS = GRL_NODE__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BELIEF__ID = GRL_NODE__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BELIEF__DESCRIPTION = GRL_NODE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF__METADATA = GRL_NODE__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BELIEF__INCONCERN = GRL_NODE__INCONCERN;

    /**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BELIEF__X = GRL_NODE__X;

    /**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BELIEF__Y = GRL_NODE__Y;

    /**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BELIEF__DIAGRAM = GRL_NODE__DIAGRAM;

    /**
	 * The feature id for the '<em><b>Cont Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BELIEF__CONT_REF = GRL_NODE__CONT_REF;

    /**
	 * The feature id for the '<em><b>Succ</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BELIEF__SUCC = GRL_NODE__SUCC;

    /**
	 * The feature id for the '<em><b>Pred</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BELIEF__PRED = GRL_NODE__PRED;

    /**
	 * The feature id for the '<em><b>Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BELIEF__LABEL = GRL_NODE__LABEL;

    /**
	 * The feature id for the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BELIEF__AUTHOR = GRL_NODE_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Belief</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BELIEF_FEATURE_COUNT = GRL_NODE_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link grl.impl.GRLLinkableElementImpl <em>GRL Linkable Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see grl.impl.GRLLinkableElementImpl
	 * @see grl.impl.GrlPackageImpl#getGRLLinkableElement()
	 * @generated
	 */
	int GRL_LINKABLE_ELEMENT = 21;

				/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRL_LINKABLE_ELEMENT__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRL_LINKABLE_ELEMENT__FROM_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRL_LINKABLE_ELEMENT__TO_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRL_LINKABLE_ELEMENT__ID = UrncorePackage.GR_LMODEL_ELEMENT__ID;

				/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRL_LINKABLE_ELEMENT__DESCRIPTION = UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION;

				/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRL_LINKABLE_ELEMENT__METADATA = UrncorePackage.GR_LMODEL_ELEMENT__METADATA;

				/**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_LINKABLE_ELEMENT__INCONCERN = UrncorePackage.GR_LMODEL_ELEMENT__INCONCERN;

                /**
	 * The feature id for the '<em><b>Links Dest</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRL_LINKABLE_ELEMENT__LINKS_DEST = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Links Src</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRL_LINKABLE_ELEMENT__LINKS_SRC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 1;

				/**
	 * The number of structural features of the '<em>GRL Linkable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRL_LINKABLE_ELEMENT_FEATURE_COUNT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 2;

				/**
	 * The meta object id for the '{@link grl.impl.IntentionalElementImpl <em>Intentional Element</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.impl.IntentionalElementImpl
	 * @see grl.impl.GrlPackageImpl#getIntentionalElement()
	 * @generated
	 */
    int INTENTIONAL_ELEMENT = 2;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT__NAME = GRL_LINKABLE_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT__FROM_LINKS = GRL_LINKABLE_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT__TO_LINKS = GRL_LINKABLE_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT__ID = GRL_LINKABLE_ELEMENT__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT__DESCRIPTION = GRL_LINKABLE_ELEMENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTIONAL_ELEMENT__METADATA = GRL_LINKABLE_ELEMENT__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT__INCONCERN = GRL_LINKABLE_ELEMENT__INCONCERN;

    /**
	 * The feature id for the '<em><b>Links Dest</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT__LINKS_DEST = GRL_LINKABLE_ELEMENT__LINKS_DEST;

				/**
	 * The feature id for the '<em><b>Links Src</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT__LINKS_SRC = GRL_LINKABLE_ELEMENT__LINKS_SRC;

				/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT__TYPE = GRL_LINKABLE_ELEMENT_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Decomposition Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE = GRL_LINKABLE_ELEMENT_FEATURE_COUNT + 1;

				/**
	 * The feature id for the '<em><b>Importance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTIONAL_ELEMENT__IMPORTANCE = GRL_LINKABLE_ELEMENT_FEATURE_COUNT + 2;

				/**
	 * The feature id for the '<em><b>Importance Quantitative</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTIONAL_ELEMENT__IMPORTANCE_QUANTITATIVE = GRL_LINKABLE_ELEMENT_FEATURE_COUNT + 3;

				/**
	 * The feature id for the '<em><b>Line Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT__LINE_COLOR = GRL_LINKABLE_ELEMENT_FEATURE_COUNT + 4;

				/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT__FILL_COLOR = GRL_LINKABLE_ELEMENT_FEATURE_COUNT + 5;

				/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT__FILLED = GRL_LINKABLE_ELEMENT_FEATURE_COUNT + 6;

				/**
	 * The feature id for the '<em><b>Grlspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT__GRLSPEC = GRL_LINKABLE_ELEMENT_FEATURE_COUNT + 7;

				/**
	 * The feature id for the '<em><b>Refs</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT__REFS = GRL_LINKABLE_ELEMENT_FEATURE_COUNT + 8;

    /**
	 * The number of structural features of the '<em>Intentional Element</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT_FEATURE_COUNT = GRL_LINKABLE_ELEMENT_FEATURE_COUNT + 9;

    /**
	 * The meta object id for the '{@link grl.impl.ActorImpl <em>Actor</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.impl.ActorImpl
	 * @see grl.impl.GrlPackageImpl#getActor()
	 * @generated
	 */
    int ACTOR = 3;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR__NAME = GRL_LINKABLE_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR__FROM_LINKS = GRL_LINKABLE_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR__TO_LINKS = GRL_LINKABLE_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR__ID = GRL_LINKABLE_ELEMENT__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR__DESCRIPTION = GRL_LINKABLE_ELEMENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__METADATA = GRL_LINKABLE_ELEMENT__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR__INCONCERN = GRL_LINKABLE_ELEMENT__INCONCERN;

    /**
	 * The feature id for the '<em><b>Links Dest</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__LINKS_DEST = GRL_LINKABLE_ELEMENT__LINKS_DEST;

				/**
	 * The feature id for the '<em><b>Links Src</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__LINKS_SRC = GRL_LINKABLE_ELEMENT__LINKS_SRC;

				/**
	 * The feature id for the '<em><b>Line Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR__LINE_COLOR = GRL_LINKABLE_ELEMENT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR__FILL_COLOR = GRL_LINKABLE_ELEMENT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR__FILLED = GRL_LINKABLE_ELEMENT_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Cont Refs</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR__CONT_REFS = GRL_LINKABLE_ELEMENT_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Importance</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__IMPORTANCE = GRL_LINKABLE_ELEMENT_FEATURE_COUNT + 4;

				/**
	 * The feature id for the '<em><b>Importance Quantitative</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__IMPORTANCE_QUANTITATIVE = GRL_LINKABLE_ELEMENT_FEATURE_COUNT + 5;

				/**
	 * The feature id for the '<em><b>Grlspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR__GRLSPEC = GRL_LINKABLE_ELEMENT_FEATURE_COUNT + 6;

    /**
	 * The feature id for the '<em><b>Included Actors</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__INCLUDED_ACTORS = GRL_LINKABLE_ELEMENT_FEATURE_COUNT + 7;

				/**
	 * The feature id for the '<em><b>Including Actor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__INCLUDING_ACTOR = GRL_LINKABLE_ELEMENT_FEATURE_COUNT + 8;

				/**
	 * The feature id for the '<em><b>Collapsed Refs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR__COLLAPSED_REFS = GRL_LINKABLE_ELEMENT_FEATURE_COUNT + 9;

				/**
	 * The number of structural features of the '<em>Actor</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR_FEATURE_COUNT = GRL_LINKABLE_ELEMENT_FEATURE_COUNT + 10;

    /**
	 * The meta object id for the '{@link grl.impl.GRLGraphImpl <em>GRL Graph</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.impl.GRLGraphImpl
	 * @see grl.impl.GrlPackageImpl#getGRLGraph()
	 * @generated
	 */
    int GRL_GRAPH = 4;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_GRAPH__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_GRAPH__FROM_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_GRAPH__TO_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_GRAPH__ID = UrncorePackage.GR_LMODEL_ELEMENT__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_GRAPH__DESCRIPTION = UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRL_GRAPH__METADATA = UrncorePackage.GR_LMODEL_ELEMENT__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_GRAPH__INCONCERN = UrncorePackage.GR_LMODEL_ELEMENT__INCONCERN;

    /**
	 * The feature id for the '<em><b>Urndefinition</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_GRAPH__URNDEFINITION = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_GRAPH__NODES = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Cont Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_GRAPH__CONT_REFS = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_GRAPH__CONNECTIONS = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Concern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRL_GRAPH__CONCERN = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRL_GRAPH__COMMENTS = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 5;

				/**
	 * The number of structural features of the '<em>GRL Graph</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GRL_GRAPH_FEATURE_COUNT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 6;

    /**
	 * The meta object id for the '{@link grl.impl.ActorRefImpl <em>Actor Ref</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.impl.ActorRefImpl
	 * @see grl.impl.GrlPackageImpl#getActorRef()
	 * @generated
	 */
    int ACTOR_REF = 5;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR_REF__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR_REF__FROM_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR_REF__TO_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR_REF__ID = UrncorePackage.GR_LMODEL_ELEMENT__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR_REF__DESCRIPTION = UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTOR_REF__METADATA = UrncorePackage.GR_LMODEL_ELEMENT__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR_REF__INCONCERN = UrncorePackage.GR_LMODEL_ELEMENT__INCONCERN;

    /**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR_REF__X = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR_REF__Y = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR_REF__WIDTH = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR_REF__HEIGHT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Fixed</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR_REF__FIXED = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR_REF__DIAGRAM = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 5;

    /**
	 * The feature id for the '<em><b>Cont Def</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR_REF__CONT_DEF = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 6;

    /**
	 * The feature id for the '<em><b>Nodes</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR_REF__NODES = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 7;

    /**
	 * The feature id for the '<em><b>Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR_REF__LABEL = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 8;

    /**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR_REF__PARENT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 9;

    /**
	 * The feature id for the '<em><b>Children</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR_REF__CHILDREN = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 10;

    /**
	 * The number of structural features of the '<em>Actor Ref</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ACTOR_REF_FEATURE_COUNT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 11;

    /**
	 * The meta object id for the '{@link grl.impl.IntentionalElementRefImpl <em>Intentional Element Ref</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.impl.IntentionalElementRefImpl
	 * @see grl.impl.GrlPackageImpl#getIntentionalElementRef()
	 * @generated
	 */
    int INTENTIONAL_ELEMENT_REF = 6;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT_REF__NAME = GRL_NODE__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT_REF__FROM_LINKS = GRL_NODE__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT_REF__TO_LINKS = GRL_NODE__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT_REF__ID = GRL_NODE__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT_REF__DESCRIPTION = GRL_NODE__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTENTIONAL_ELEMENT_REF__METADATA = GRL_NODE__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT_REF__INCONCERN = GRL_NODE__INCONCERN;

    /**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT_REF__X = GRL_NODE__X;

    /**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT_REF__Y = GRL_NODE__Y;

    /**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT_REF__DIAGRAM = GRL_NODE__DIAGRAM;

    /**
	 * The feature id for the '<em><b>Cont Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT_REF__CONT_REF = GRL_NODE__CONT_REF;

    /**
	 * The feature id for the '<em><b>Succ</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT_REF__SUCC = GRL_NODE__SUCC;

    /**
	 * The feature id for the '<em><b>Pred</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT_REF__PRED = GRL_NODE__PRED;

    /**
	 * The feature id for the '<em><b>Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT_REF__LABEL = GRL_NODE__LABEL;

    /**
	 * The feature id for the '<em><b>Criticality</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT_REF__CRITICALITY = GRL_NODE_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT_REF__PRIORITY = GRL_NODE_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Def</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT_REF__DEF = GRL_NODE_FEATURE_COUNT + 2;

    /**
	 * The number of structural features of the '<em>Intentional Element Ref</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INTENTIONAL_ELEMENT_REF_FEATURE_COUNT = GRL_NODE_FEATURE_COUNT + 3;

    /**
	 * The meta object id for the '{@link grl.impl.ElementLinkImpl <em>Element Link</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.impl.ElementLinkImpl
	 * @see grl.impl.GrlPackageImpl#getElementLink()
	 * @generated
	 */
    int ELEMENT_LINK = 9;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ELEMENT_LINK__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ELEMENT_LINK__FROM_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ELEMENT_LINK__TO_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ELEMENT_LINK__ID = UrncorePackage.GR_LMODEL_ELEMENT__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ELEMENT_LINK__DESCRIPTION = UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_LINK__METADATA = UrncorePackage.GR_LMODEL_ELEMENT__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ELEMENT_LINK__INCONCERN = UrncorePackage.GR_LMODEL_ELEMENT__INCONCERN;

    /**
	 * The feature id for the '<em><b>Refs</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ELEMENT_LINK__REFS = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Grlspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ELEMENT_LINK__GRLSPEC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Dest</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ELEMENT_LINK__DEST = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 2;

				/**
	 * The feature id for the '<em><b>Src</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ELEMENT_LINK__SRC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
	 * The number of structural features of the '<em>Element Link</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ELEMENT_LINK_FEATURE_COUNT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
	 * The meta object id for the '{@link grl.impl.ContributionImpl <em>Contribution</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.impl.ContributionImpl
	 * @see grl.impl.GrlPackageImpl#getContribution()
	 * @generated
	 */
    int CONTRIBUTION = 7;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONTRIBUTION__NAME = ELEMENT_LINK__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONTRIBUTION__FROM_LINKS = ELEMENT_LINK__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONTRIBUTION__TO_LINKS = ELEMENT_LINK__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONTRIBUTION__ID = ELEMENT_LINK__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONTRIBUTION__DESCRIPTION = ELEMENT_LINK__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__METADATA = ELEMENT_LINK__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONTRIBUTION__INCONCERN = ELEMENT_LINK__INCONCERN;

    /**
	 * The feature id for the '<em><b>Refs</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONTRIBUTION__REFS = ELEMENT_LINK__REFS;

    /**
	 * The feature id for the '<em><b>Grlspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONTRIBUTION__GRLSPEC = ELEMENT_LINK__GRLSPEC;

    /**
	 * The feature id for the '<em><b>Dest</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONTRIBUTION__DEST = ELEMENT_LINK__DEST;

				/**
	 * The feature id for the '<em><b>Src</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONTRIBUTION__SRC = ELEMENT_LINK__SRC;

    /**
	 * The feature id for the '<em><b>Contribution</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONTRIBUTION__CONTRIBUTION = ELEMENT_LINK_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Quantitative Contribution</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION__QUANTITATIVE_CONTRIBUTION = ELEMENT_LINK_FEATURE_COUNT + 1;

				/**
	 * The feature id for the '<em><b>Correlation</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONTRIBUTION__CORRELATION = ELEMENT_LINK_FEATURE_COUNT + 2;

    /**
	 * The number of structural features of the '<em>Contribution</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONTRIBUTION_FEATURE_COUNT = ELEMENT_LINK_FEATURE_COUNT + 3;

    /**
	 * The meta object id for the '{@link grl.impl.LinkRefImpl <em>Link Ref</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.impl.LinkRefImpl
	 * @see grl.impl.GrlPackageImpl#getLinkRef()
	 * @generated
	 */
    int LINK_REF = 8;

    /**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LINK_REF__SOURCE = UrncorePackage.IURN_CONNECTION__SOURCE;

    /**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LINK_REF__TARGET = UrncorePackage.IURN_CONNECTION__TARGET;

    /**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LINK_REF__DIAGRAM = UrncorePackage.IURN_CONNECTION__DIAGRAM;

    /**
	 * The feature id for the '<em><b>Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_REF__LABEL = UrncorePackage.IURN_CONNECTION__LABEL;

				/**
	 * The feature id for the '<em><b>Link</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LINK_REF__LINK = UrncorePackage.IURN_CONNECTION_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Bendpoints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LINK_REF__BENDPOINTS = UrncorePackage.IURN_CONNECTION_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Link Ref</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LINK_REF_FEATURE_COUNT = UrncorePackage.IURN_CONNECTION_FEATURE_COUNT + 2;

    /**
	 * The meta object id for the '{@link grl.impl.DecompositionImpl <em>Decomposition</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.impl.DecompositionImpl
	 * @see grl.impl.GrlPackageImpl#getDecomposition()
	 * @generated
	 */
    int DECOMPOSITION = 10;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DECOMPOSITION__NAME = ELEMENT_LINK__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DECOMPOSITION__FROM_LINKS = ELEMENT_LINK__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DECOMPOSITION__TO_LINKS = ELEMENT_LINK__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DECOMPOSITION__ID = ELEMENT_LINK__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DECOMPOSITION__DESCRIPTION = ELEMENT_LINK__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECOMPOSITION__METADATA = ELEMENT_LINK__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DECOMPOSITION__INCONCERN = ELEMENT_LINK__INCONCERN;

    /**
	 * The feature id for the '<em><b>Refs</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DECOMPOSITION__REFS = ELEMENT_LINK__REFS;

    /**
	 * The feature id for the '<em><b>Grlspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DECOMPOSITION__GRLSPEC = ELEMENT_LINK__GRLSPEC;

    /**
	 * The feature id for the '<em><b>Dest</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DECOMPOSITION__DEST = ELEMENT_LINK__DEST;

				/**
	 * The feature id for the '<em><b>Src</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DECOMPOSITION__SRC = ELEMENT_LINK__SRC;

    /**
	 * The number of structural features of the '<em>Decomposition</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DECOMPOSITION_FEATURE_COUNT = ELEMENT_LINK_FEATURE_COUNT + 0;

    /**
	 * The meta object id for the '{@link grl.impl.DependencyImpl <em>Dependency</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.impl.DependencyImpl
	 * @see grl.impl.GrlPackageImpl#getDependency()
	 * @generated
	 */
    int DEPENDENCY = 11;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DEPENDENCY__NAME = ELEMENT_LINK__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DEPENDENCY__FROM_LINKS = ELEMENT_LINK__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DEPENDENCY__TO_LINKS = ELEMENT_LINK__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DEPENDENCY__ID = ELEMENT_LINK__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DEPENDENCY__DESCRIPTION = ELEMENT_LINK__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPENDENCY__METADATA = ELEMENT_LINK__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DEPENDENCY__INCONCERN = ELEMENT_LINK__INCONCERN;

    /**
	 * The feature id for the '<em><b>Refs</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DEPENDENCY__REFS = ELEMENT_LINK__REFS;

    /**
	 * The feature id for the '<em><b>Grlspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DEPENDENCY__GRLSPEC = ELEMENT_LINK__GRLSPEC;

    /**
	 * The feature id for the '<em><b>Dest</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DEPENDENCY__DEST = ELEMENT_LINK__DEST;

				/**
	 * The feature id for the '<em><b>Src</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DEPENDENCY__SRC = ELEMENT_LINK__SRC;

    /**
	 * The number of structural features of the '<em>Dependency</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DEPENDENCY_FEATURE_COUNT = ELEMENT_LINK_FEATURE_COUNT + 0;

    /**
	 * The meta object id for the '{@link grl.impl.EvaluationImpl <em>Evaluation</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.impl.EvaluationImpl
	 * @see grl.impl.GrlPackageImpl#getEvaluation()
	 * @generated
	 */
    int EVALUATION = 12;

    /**
	 * The feature id for the '<em><b>Evaluation</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVALUATION__EVALUATION = 0;

    /**
	 * The feature id for the '<em><b>Qualitative Evaluation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION__QUALITATIVE_EVALUATION = 1;

				/**
	 * The feature id for the '<em><b>Exceeds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION__EXCEEDS = 2;

				/**
	 * The feature id for the '<em><b>Int Element</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVALUATION__INT_ELEMENT = 3;

    /**
	 * The feature id for the '<em><b>Strategies</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVALUATION__STRATEGIES = 4;

    /**
	 * The feature id for the '<em><b>Kpi Eval Value Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVALUATION__KPI_EVAL_VALUE_SET = 5;

    /**
	 * The feature id for the '<em><b>Eval Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION__EVAL_RANGE = 6;

				/**
	 * The feature id for the '<em><b>Kpi New Eval Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION__KPI_NEW_EVAL_VALUE = 7;

				/**
	 * The number of structural features of the '<em>Evaluation</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVALUATION_FEATURE_COUNT = 8;

    /**
	 * The meta object id for the '{@link grl.impl.EvaluationStrategyImpl <em>Evaluation Strategy</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.impl.EvaluationStrategyImpl
	 * @see grl.impl.GrlPackageImpl#getEvaluationStrategy()
	 * @generated
	 */
    int EVALUATION_STRATEGY = 13;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVALUATION_STRATEGY__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVALUATION_STRATEGY__FROM_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVALUATION_STRATEGY__TO_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVALUATION_STRATEGY__ID = UrncorePackage.GR_LMODEL_ELEMENT__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVALUATION_STRATEGY__DESCRIPTION = UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_STRATEGY__METADATA = UrncorePackage.GR_LMODEL_ELEMENT__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVALUATION_STRATEGY__INCONCERN = UrncorePackage.GR_LMODEL_ELEMENT__INCONCERN;

    /**
	 * The feature id for the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVALUATION_STRATEGY__AUTHOR = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Evaluations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVALUATION_STRATEGY__EVALUATIONS = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Group</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVALUATION_STRATEGY__GROUP = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Grlspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVALUATION_STRATEGY__GRLSPEC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Included Strategies</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_STRATEGY__INCLUDED_STRATEGIES = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 4;

				/**
	 * The feature id for the '<em><b>Parent Strategies</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_STRATEGY__PARENT_STRATEGIES = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 5;

				/**
	 * The feature id for the '<em><b>Kpi Info Config</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVALUATION_STRATEGY__KPI_INFO_CONFIG = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 6;

    /**
	 * The number of structural features of the '<em>Evaluation Strategy</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int EVALUATION_STRATEGY_FEATURE_COUNT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 7;

    /**
	 * The meta object id for the '{@link grl.impl.LinkRefBendpointImpl <em>Link Ref Bendpoint</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.impl.LinkRefBendpointImpl
	 * @see grl.impl.GrlPackageImpl#getLinkRefBendpoint()
	 * @generated
	 */
    int LINK_REF_BENDPOINT = 15;

    /**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LINK_REF_BENDPOINT__X = 0;

    /**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LINK_REF_BENDPOINT__Y = 1;

    /**
	 * The feature id for the '<em><b>Linkref</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LINK_REF_BENDPOINT__LINKREF = 2;

    /**
	 * The number of structural features of the '<em>Link Ref Bendpoint</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LINK_REF_BENDPOINT_FEATURE_COUNT = 3;

    /**
	 * The meta object id for the '{@link grl.impl.BeliefLinkImpl <em>Belief Link</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.impl.BeliefLinkImpl
	 * @see grl.impl.GrlPackageImpl#getBeliefLink()
	 * @generated
	 */
    int BELIEF_LINK = 16;

    /**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BELIEF_LINK__SOURCE = UrncorePackage.IURN_CONNECTION__SOURCE;

    /**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BELIEF_LINK__TARGET = UrncorePackage.IURN_CONNECTION__TARGET;

    /**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BELIEF_LINK__DIAGRAM = UrncorePackage.IURN_CONNECTION__DIAGRAM;

    /**
	 * The feature id for the '<em><b>Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BELIEF_LINK__LABEL = UrncorePackage.IURN_CONNECTION__LABEL;

				/**
	 * The number of structural features of the '<em>Belief Link</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int BELIEF_LINK_FEATURE_COUNT = UrncorePackage.IURN_CONNECTION_FEATURE_COUNT + 0;

    /**
	 * The meta object id for the '{@link grl.impl.StrategiesGroupImpl <em>Strategies Group</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.impl.StrategiesGroupImpl
	 * @see grl.impl.GrlPackageImpl#getStrategiesGroup()
	 * @generated
	 */
    int STRATEGIES_GROUP = 17;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int STRATEGIES_GROUP__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int STRATEGIES_GROUP__FROM_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int STRATEGIES_GROUP__TO_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int STRATEGIES_GROUP__ID = UrncorePackage.GR_LMODEL_ELEMENT__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int STRATEGIES_GROUP__DESCRIPTION = UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGIES_GROUP__METADATA = UrncorePackage.GR_LMODEL_ELEMENT__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int STRATEGIES_GROUP__INCONCERN = UrncorePackage.GR_LMODEL_ELEMENT__INCONCERN;

    /**
	 * The feature id for the '<em><b>Strategies</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int STRATEGIES_GROUP__STRATEGIES = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Grlspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int STRATEGIES_GROUP__GRLSPEC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Strategies Group</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int STRATEGIES_GROUP_FEATURE_COUNT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
	 * The meta object id for the '{@link grl.impl.ContributionContextGroupImpl <em>Contribution Context Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see grl.impl.ContributionContextGroupImpl
	 * @see grl.impl.GrlPackageImpl#getContributionContextGroup()
	 * @generated
	 */
	int CONTRIBUTION_CONTEXT_GROUP = 18;

				/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CONTEXT_GROUP__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CONTEXT_GROUP__FROM_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CONTEXT_GROUP__TO_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CONTEXT_GROUP__ID = UrncorePackage.GR_LMODEL_ELEMENT__ID;

				/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CONTEXT_GROUP__DESCRIPTION = UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION;

				/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CONTEXT_GROUP__METADATA = UrncorePackage.GR_LMODEL_ELEMENT__METADATA;

				/**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONTRIBUTION_CONTEXT_GROUP__INCONCERN = UrncorePackage.GR_LMODEL_ELEMENT__INCONCERN;

                /**
	 * The feature id for the '<em><b>Grlspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CONTEXT_GROUP__GRLSPEC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Contribs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CONTEXT_GROUP__CONTRIBS = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 1;

				/**
	 * The number of structural features of the '<em>Contribution Context Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CONTEXT_GROUP_FEATURE_COUNT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 2;

				/**
	 * The meta object id for the '{@link grl.impl.ContributionContextImpl <em>Contribution Context</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see grl.impl.ContributionContextImpl
	 * @see grl.impl.GrlPackageImpl#getContributionContext()
	 * @generated
	 */
	int CONTRIBUTION_CONTEXT = 19;

				/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CONTEXT__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CONTEXT__FROM_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CONTEXT__TO_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CONTEXT__ID = UrncorePackage.GR_LMODEL_ELEMENT__ID;

				/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CONTEXT__DESCRIPTION = UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION;

				/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CONTEXT__METADATA = UrncorePackage.GR_LMODEL_ELEMENT__METADATA;

				/**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONTRIBUTION_CONTEXT__INCONCERN = UrncorePackage.GR_LMODEL_ELEMENT__INCONCERN;

                /**
	 * The feature id for the '<em><b>Grlspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CONTEXT__GRLSPEC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CONTEXT__GROUPS = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 1;

				/**
	 * The feature id for the '<em><b>Changes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CONTEXT__CHANGES = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 2;

				/**
	 * The feature id for the '<em><b>Parent Contexts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CONTEXT__PARENT_CONTEXTS = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 3;

				/**
	 * The feature id for the '<em><b>Included Contexts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CONTEXT__INCLUDED_CONTEXTS = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 4;

				/**
	 * The number of structural features of the '<em>Contribution Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CONTEXT_FEATURE_COUNT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 5;

				/**
	 * The meta object id for the '{@link grl.impl.ContributionChangeImpl <em>Contribution Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see grl.impl.ContributionChangeImpl
	 * @see grl.impl.GrlPackageImpl#getContributionChange()
	 * @generated
	 */
	int CONTRIBUTION_CHANGE = 20;

				/**
	 * The feature id for the '<em><b>New Contribution</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CHANGE__NEW_CONTRIBUTION = 0;

				/**
	 * The feature id for the '<em><b>New Quantitative Contribution</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CHANGE__NEW_QUANTITATIVE_CONTRIBUTION = 1;

				/**
	 * The feature id for the '<em><b>Context</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CHANGE__CONTEXT = 2;

				/**
	 * The feature id for the '<em><b>Contribution</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CHANGE__CONTRIBUTION = 3;

				/**
	 * The feature id for the '<em><b>Contrib Range</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CHANGE__CONTRIB_RANGE = 4;

				/**
	 * The number of structural features of the '<em>Contribution Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_CHANGE_FEATURE_COUNT = 5;

				/**
	 * The meta object id for the '{@link grl.impl.CollapsedActorRefImpl <em>Collapsed Actor Ref</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see grl.impl.CollapsedActorRefImpl
	 * @see grl.impl.GrlPackageImpl#getCollapsedActorRef()
	 * @generated
	 */
	int COLLAPSED_ACTOR_REF = 22;

				/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSED_ACTOR_REF__NAME = GRL_NODE__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSED_ACTOR_REF__FROM_LINKS = GRL_NODE__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSED_ACTOR_REF__TO_LINKS = GRL_NODE__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSED_ACTOR_REF__ID = GRL_NODE__ID;

				/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSED_ACTOR_REF__DESCRIPTION = GRL_NODE__DESCRIPTION;

				/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSED_ACTOR_REF__METADATA = GRL_NODE__METADATA;

				/**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COLLAPSED_ACTOR_REF__INCONCERN = GRL_NODE__INCONCERN;

                /**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSED_ACTOR_REF__X = GRL_NODE__X;

				/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSED_ACTOR_REF__Y = GRL_NODE__Y;

				/**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSED_ACTOR_REF__DIAGRAM = GRL_NODE__DIAGRAM;

				/**
	 * The feature id for the '<em><b>Cont Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSED_ACTOR_REF__CONT_REF = GRL_NODE__CONT_REF;

				/**
	 * The feature id for the '<em><b>Succ</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSED_ACTOR_REF__SUCC = GRL_NODE__SUCC;

				/**
	 * The feature id for the '<em><b>Pred</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSED_ACTOR_REF__PRED = GRL_NODE__PRED;

				/**
	 * The feature id for the '<em><b>Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSED_ACTOR_REF__LABEL = GRL_NODE__LABEL;

				/**
	 * The feature id for the '<em><b>Actor</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSED_ACTOR_REF__ACTOR = GRL_NODE_FEATURE_COUNT + 0;

				/**
	 * The number of structural features of the '<em>Collapsed Actor Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLLAPSED_ACTOR_REF_FEATURE_COUNT = GRL_NODE_FEATURE_COUNT + 1;

				/**
	 * The meta object id for the '{@link grl.impl.EvaluationRangeImpl <em>Evaluation Range</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see grl.impl.EvaluationRangeImpl
	 * @see grl.impl.GrlPackageImpl#getEvaluationRange()
	 * @generated
	 */
	int EVALUATION_RANGE = 23;

				/**
	 * The feature id for the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_RANGE__START = 0;

				/**
	 * The feature id for the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_RANGE__END = 1;

				/**
	 * The feature id for the '<em><b>Step</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_RANGE__STEP = 2;

				/**
	 * The feature id for the '<em><b>Eval</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_RANGE__EVAL = 3;

				/**
	 * The number of structural features of the '<em>Evaluation Range</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVALUATION_RANGE_FEATURE_COUNT = 4;

				/**
	 * The meta object id for the '{@link grl.impl.ContributionRangeImpl <em>Contribution Range</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see grl.impl.ContributionRangeImpl
	 * @see grl.impl.GrlPackageImpl#getContributionRange()
	 * @generated
	 */
	int CONTRIBUTION_RANGE = 24;

				/**
	 * The feature id for the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_RANGE__START = 0;

				/**
	 * The feature id for the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_RANGE__END = 1;

				/**
	 * The feature id for the '<em><b>Step</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_RANGE__STEP = 2;

				/**
	 * The feature id for the '<em><b>Change</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_RANGE__CHANGE = 3;

				/**
	 * The number of structural features of the '<em>Contribution Range</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTRIBUTION_RANGE_FEATURE_COUNT = 4;

				/**
	 * The meta object id for the '{@link grl.impl.ImpactModelImpl <em>Impact Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see grl.impl.ImpactModelImpl
	 * @see grl.impl.GrlPackageImpl#getImpactModel()
	 * @generated
	 */
	int IMPACT_MODEL = 25;

				/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPACT_MODEL__NAME = CorePackage.CORE_IMPACT_MODEL__NAME;

				/**
	 * The feature id for the '<em><b>Model Reuse</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPACT_MODEL__MODEL_REUSE = CorePackage.CORE_IMPACT_MODEL__MODEL_REUSE;

				/**
	 * The feature id for the '<em><b>Model Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPACT_MODEL__MODEL_ELEMENTS = CorePackage.CORE_IMPACT_MODEL__MODEL_ELEMENTS;

				/**
	 * The feature id for the '<em><b>Realizes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPACT_MODEL__REALIZES = CorePackage.CORE_IMPACT_MODEL__REALIZES;

				/**
	 * The feature id for the '<em><b>Grlspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPACT_MODEL__GRLSPEC = CorePackage.CORE_IMPACT_MODEL_FEATURE_COUNT + 0;

				/**
	 * The number of structural features of the '<em>Impact Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMPACT_MODEL_FEATURE_COUNT = CorePackage.CORE_IMPACT_MODEL_FEATURE_COUNT + 1;

				/**
	 * The meta object id for the '{@link grl.Criticality <em>Criticality</em>}' enum.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.Criticality
	 * @see grl.impl.GrlPackageImpl#getCriticality()
	 * @generated
	 */
    int CRITICALITY = 26;

    /**
	 * The meta object id for the '{@link grl.IntentionalElementType <em>Intentional Element Type</em>}' enum.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.IntentionalElementType
	 * @see grl.impl.GrlPackageImpl#getIntentionalElementType()
	 * @generated
	 */
    int INTENTIONAL_ELEMENT_TYPE = 27;

    /**
	 * The meta object id for the '{@link grl.Priority <em>Priority</em>}' enum.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.Priority
	 * @see grl.impl.GrlPackageImpl#getPriority()
	 * @generated
	 */
    int PRIORITY = 28;

    /**
	 * The meta object id for the '{@link grl.ContributionType <em>Contribution Type</em>}' enum.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.ContributionType
	 * @see grl.impl.GrlPackageImpl#getContributionType()
	 * @generated
	 */
    int CONTRIBUTION_TYPE = 29;

    /**
	 * The meta object id for the '{@link grl.DecompositionType <em>Decomposition Type</em>}' enum.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see grl.DecompositionType
	 * @see grl.impl.GrlPackageImpl#getDecompositionType()
	 * @generated
	 */
    int DECOMPOSITION_TYPE = 30;


    /**
	 * The meta object id for the '{@link grl.QualitativeLabel <em>Qualitative Label</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see grl.QualitativeLabel
	 * @see grl.impl.GrlPackageImpl#getQualitativeLabel()
	 * @generated
	 */
	int QUALITATIVE_LABEL = 31;


				/**
	 * The meta object id for the '{@link grl.ImportanceType <em>Importance Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see grl.ImportanceType
	 * @see grl.impl.GrlPackageImpl#getImportanceType()
	 * @generated
	 */
	int IMPORTANCE_TYPE = 32;


				/**
	 * Returns the meta object for class '{@link grl.GRLspec <em>GR Lspec</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>GR Lspec</em>'.
	 * @see grl.GRLspec
	 * @generated
	 */
    EClass getGRLspec();

    /**
	 * Returns the meta object for the container reference '{@link grl.GRLspec#getUrnspec <em>Urnspec</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Urnspec</em>'.
	 * @see grl.GRLspec#getUrnspec()
	 * @see #getGRLspec()
	 * @generated
	 */
    EReference getGRLspec_Urnspec();

    /**
	 * Returns the meta object for the containment reference list '{@link grl.GRLspec#getIntElements <em>Int Elements</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Int Elements</em>'.
	 * @see grl.GRLspec#getIntElements()
	 * @see #getGRLspec()
	 * @generated
	 */
    EReference getGRLspec_IntElements();

    /**
	 * Returns the meta object for the containment reference list '{@link grl.GRLspec#getActors <em>Actors</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Actors</em>'.
	 * @see grl.GRLspec#getActors()
	 * @see #getGRLspec()
	 * @generated
	 */
    EReference getGRLspec_Actors();

    /**
	 * Returns the meta object for the containment reference list '{@link grl.GRLspec#getLinks <em>Links</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Links</em>'.
	 * @see grl.GRLspec#getLinks()
	 * @see #getGRLspec()
	 * @generated
	 */
    EReference getGRLspec_Links();

    /**
	 * Returns the meta object for the containment reference list '{@link grl.GRLspec#getGroups <em>Groups</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Groups</em>'.
	 * @see grl.GRLspec#getGroups()
	 * @see #getGRLspec()
	 * @generated
	 */
    EReference getGRLspec_Groups();

    /**
	 * Returns the meta object for the containment reference list '{@link grl.GRLspec#getStrategies <em>Strategies</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Strategies</em>'.
	 * @see grl.GRLspec#getStrategies()
	 * @see #getGRLspec()
	 * @generated
	 */
    EReference getGRLspec_Strategies();

    /**
	 * Returns the meta object for the containment reference list '{@link grl.GRLspec#getContributionGroups <em>Contribution Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contribution Groups</em>'.
	 * @see grl.GRLspec#getContributionGroups()
	 * @see #getGRLspec()
	 * @generated
	 */
	EReference getGRLspec_ContributionGroups();

				/**
	 * Returns the meta object for the containment reference list '{@link grl.GRLspec#getContributionContexts <em>Contribution Contexts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contribution Contexts</em>'.
	 * @see grl.GRLspec#getContributionContexts()
	 * @see #getGRLspec()
	 * @generated
	 */
	EReference getGRLspec_ContributionContexts();

				/**
	 * Returns the meta object for the containment reference '{@link grl.GRLspec#getImpactModel <em>Impact Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Impact Model</em>'.
	 * @see grl.GRLspec#getImpactModel()
	 * @see #getGRLspec()
	 * @generated
	 */
	EReference getGRLspec_ImpactModel();

				/**
	 * Returns the meta object for the containment reference list '{@link grl.GRLspec#getKpiInformationElements <em>Kpi Information Elements</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Kpi Information Elements</em>'.
	 * @see grl.GRLspec#getKpiInformationElements()
	 * @see #getGRLspec()
	 * @generated
	 */
    EReference getGRLspec_KpiInformationElements();

    /**
	 * Returns the meta object for the containment reference list '{@link grl.GRLspec#getKpiModelLinks <em>Kpi Model Links</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Kpi Model Links</em>'.
	 * @see grl.GRLspec#getKpiModelLinks()
	 * @see #getGRLspec()
	 * @generated
	 */
    EReference getGRLspec_KpiModelLinks();

    /**
	 * Returns the meta object for the containment reference list '{@link grl.GRLspec#getIndicatorGroup <em>Indicator Group</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Indicator Group</em>'.
	 * @see grl.GRLspec#getIndicatorGroup()
	 * @see #getGRLspec()
	 * @generated
	 */
    EReference getGRLspec_IndicatorGroup();

    /**
	 * Returns the meta object for the containment reference list '{@link grl.GRLspec#getKPIConversion <em>KPI Conversion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>KPI Conversion</em>'.
	 * @see grl.GRLspec#getKPIConversion()
	 * @see #getGRLspec()
	 * @generated
	 */
	EReference getGRLspec_KPIConversion();

				/**
	 * Returns the meta object for the containment reference '{@link grl.GRLspec#getFeatureModel <em>Feature Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Feature Model</em>'.
	 * @see grl.GRLspec#getFeatureModel()
	 * @see #getGRLspec()
	 * @generated
	 */
	EReference getGRLspec_FeatureModel();

				/**
	 * Returns the meta object for class '{@link grl.Belief <em>Belief</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Belief</em>'.
	 * @see grl.Belief
	 * @generated
	 */
    EClass getBelief();

    /**
	 * Returns the meta object for the attribute '{@link grl.Belief#getAuthor <em>Author</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Author</em>'.
	 * @see grl.Belief#getAuthor()
	 * @see #getBelief()
	 * @generated
	 */
    EAttribute getBelief_Author();

    /**
	 * Returns the meta object for class '{@link grl.IntentionalElement <em>Intentional Element</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Intentional Element</em>'.
	 * @see grl.IntentionalElement
	 * @generated
	 */
    EClass getIntentionalElement();

    /**
	 * Returns the meta object for the attribute '{@link grl.IntentionalElement#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see grl.IntentionalElement#getType()
	 * @see #getIntentionalElement()
	 * @generated
	 */
    EAttribute getIntentionalElement_Type();

    /**
	 * Returns the meta object for the attribute '{@link grl.IntentionalElement#getDecompositionType <em>Decomposition Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Decomposition Type</em>'.
	 * @see grl.IntentionalElement#getDecompositionType()
	 * @see #getIntentionalElement()
	 * @generated
	 */
    EAttribute getIntentionalElement_DecompositionType();

    /**
	 * Returns the meta object for the attribute '{@link grl.IntentionalElement#getImportance <em>Importance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Importance</em>'.
	 * @see grl.IntentionalElement#getImportance()
	 * @see #getIntentionalElement()
	 * @generated
	 */
	EAttribute getIntentionalElement_Importance();

				/**
	 * Returns the meta object for the attribute '{@link grl.IntentionalElement#getImportanceQuantitative <em>Importance Quantitative</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Importance Quantitative</em>'.
	 * @see grl.IntentionalElement#getImportanceQuantitative()
	 * @see #getIntentionalElement()
	 * @generated
	 */
	EAttribute getIntentionalElement_ImportanceQuantitative();

				/**
	 * Returns the meta object for the attribute '{@link grl.IntentionalElement#getLineColor <em>Line Color</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Color</em>'.
	 * @see grl.IntentionalElement#getLineColor()
	 * @see #getIntentionalElement()
	 * @generated
	 */
    EAttribute getIntentionalElement_LineColor();

    /**
	 * Returns the meta object for the attribute '{@link grl.IntentionalElement#getFillColor <em>Fill Color</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fill Color</em>'.
	 * @see grl.IntentionalElement#getFillColor()
	 * @see #getIntentionalElement()
	 * @generated
	 */
    EAttribute getIntentionalElement_FillColor();

    /**
	 * Returns the meta object for the attribute '{@link grl.IntentionalElement#isFilled <em>Filled</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filled</em>'.
	 * @see grl.IntentionalElement#isFilled()
	 * @see #getIntentionalElement()
	 * @generated
	 */
    EAttribute getIntentionalElement_Filled();

    /**
	 * Returns the meta object for the container reference '{@link grl.IntentionalElement#getGrlspec <em>Grlspec</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Grlspec</em>'.
	 * @see grl.IntentionalElement#getGrlspec()
	 * @see #getIntentionalElement()
	 * @generated
	 */
    EReference getIntentionalElement_Grlspec();

    /**
	 * Returns the meta object for the reference list '{@link grl.IntentionalElement#getRefs <em>Refs</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Refs</em>'.
	 * @see grl.IntentionalElement#getRefs()
	 * @see #getIntentionalElement()
	 * @generated
	 */
    EReference getIntentionalElement_Refs();

    /**
	 * Returns the meta object for class '{@link grl.Actor <em>Actor</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Actor</em>'.
	 * @see grl.Actor
	 * @generated
	 */
    EClass getActor();

    /**
	 * Returns the meta object for the attribute '{@link grl.Actor#getImportance <em>Importance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Importance</em>'.
	 * @see grl.Actor#getImportance()
	 * @see #getActor()
	 * @generated
	 */
	EAttribute getActor_Importance();

				/**
	 * Returns the meta object for the attribute '{@link grl.Actor#getImportanceQuantitative <em>Importance Quantitative</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Importance Quantitative</em>'.
	 * @see grl.Actor#getImportanceQuantitative()
	 * @see #getActor()
	 * @generated
	 */
	EAttribute getActor_ImportanceQuantitative();

				/**
	 * Returns the meta object for the container reference '{@link grl.Actor#getGrlspec <em>Grlspec</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Grlspec</em>'.
	 * @see grl.Actor#getGrlspec()
	 * @see #getActor()
	 * @generated
	 */
    EReference getActor_Grlspec();

    /**
	 * Returns the meta object for the reference list '{@link grl.Actor#getIncludedActors <em>Included Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Included Actors</em>'.
	 * @see grl.Actor#getIncludedActors()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_IncludedActors();

				/**
	 * Returns the meta object for the reference '{@link grl.Actor#getIncludingActor <em>Including Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Including Actor</em>'.
	 * @see grl.Actor#getIncludingActor()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_IncludingActor();

				/**
	 * Returns the meta object for the reference list '{@link grl.Actor#getCollapsedRefs <em>Collapsed Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Collapsed Refs</em>'.
	 * @see grl.Actor#getCollapsedRefs()
	 * @see #getActor()
	 * @generated
	 */
	EReference getActor_CollapsedRefs();

				/**
	 * Returns the meta object for class '{@link grl.GRLGraph <em>GRL Graph</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>GRL Graph</em>'.
	 * @see grl.GRLGraph
	 * @generated
	 */
    EClass getGRLGraph();

    /**
	 * Returns the meta object for class '{@link grl.ActorRef <em>Actor Ref</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Actor Ref</em>'.
	 * @see grl.ActorRef
	 * @generated
	 */
    EClass getActorRef();

    /**
	 * Returns the meta object for class '{@link grl.IntentionalElementRef <em>Intentional Element Ref</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Intentional Element Ref</em>'.
	 * @see grl.IntentionalElementRef
	 * @generated
	 */
    EClass getIntentionalElementRef();

    /**
	 * Returns the meta object for the attribute '{@link grl.IntentionalElementRef#getCriticality <em>Criticality</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Criticality</em>'.
	 * @see grl.IntentionalElementRef#getCriticality()
	 * @see #getIntentionalElementRef()
	 * @generated
	 */
    EAttribute getIntentionalElementRef_Criticality();

    /**
	 * Returns the meta object for the attribute '{@link grl.IntentionalElementRef#getPriority <em>Priority</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Priority</em>'.
	 * @see grl.IntentionalElementRef#getPriority()
	 * @see #getIntentionalElementRef()
	 * @generated
	 */
    EAttribute getIntentionalElementRef_Priority();

    /**
	 * Returns the meta object for the reference '{@link grl.IntentionalElementRef#getDef <em>Def</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Def</em>'.
	 * @see grl.IntentionalElementRef#getDef()
	 * @see #getIntentionalElementRef()
	 * @generated
	 */
    EReference getIntentionalElementRef_Def();

    /**
	 * Returns the meta object for class '{@link grl.Contribution <em>Contribution</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contribution</em>'.
	 * @see grl.Contribution
	 * @generated
	 */
    EClass getContribution();

    /**
	 * Returns the meta object for the attribute '{@link grl.Contribution#getContribution <em>Contribution</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Contribution</em>'.
	 * @see grl.Contribution#getContribution()
	 * @see #getContribution()
	 * @generated
	 */
    EAttribute getContribution_Contribution();

    /**
	 * Returns the meta object for the attribute '{@link grl.Contribution#getQuantitativeContribution <em>Quantitative Contribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Quantitative Contribution</em>'.
	 * @see grl.Contribution#getQuantitativeContribution()
	 * @see #getContribution()
	 * @generated
	 */
	EAttribute getContribution_QuantitativeContribution();

				/**
	 * Returns the meta object for the attribute '{@link grl.Contribution#isCorrelation <em>Correlation</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Correlation</em>'.
	 * @see grl.Contribution#isCorrelation()
	 * @see #getContribution()
	 * @generated
	 */
    EAttribute getContribution_Correlation();

    /**
	 * Returns the meta object for class '{@link grl.LinkRef <em>Link Ref</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Link Ref</em>'.
	 * @see grl.LinkRef
	 * @generated
	 */
    EClass getLinkRef();

    /**
	 * Returns the meta object for the reference '{@link grl.LinkRef#getLink <em>Link</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Link</em>'.
	 * @see grl.LinkRef#getLink()
	 * @see #getLinkRef()
	 * @generated
	 */
    EReference getLinkRef_Link();

    /**
	 * Returns the meta object for the containment reference list '{@link grl.LinkRef#getBendpoints <em>Bendpoints</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Bendpoints</em>'.
	 * @see grl.LinkRef#getBendpoints()
	 * @see #getLinkRef()
	 * @generated
	 */
    EReference getLinkRef_Bendpoints();

    /**
	 * Returns the meta object for class '{@link grl.ElementLink <em>Element Link</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element Link</em>'.
	 * @see grl.ElementLink
	 * @generated
	 */
    EClass getElementLink();

    /**
	 * Returns the meta object for the reference list '{@link grl.ElementLink#getRefs <em>Refs</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Refs</em>'.
	 * @see grl.ElementLink#getRefs()
	 * @see #getElementLink()
	 * @generated
	 */
    EReference getElementLink_Refs();

    /**
	 * Returns the meta object for the container reference '{@link grl.ElementLink#getGrlspec <em>Grlspec</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Grlspec</em>'.
	 * @see grl.ElementLink#getGrlspec()
	 * @see #getElementLink()
	 * @generated
	 */
    EReference getElementLink_Grlspec();

    /**
	 * Returns the meta object for the reference '{@link grl.ElementLink#getSrc <em>Src</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Src</em>'.
	 * @see grl.ElementLink#getSrc()
	 * @see #getElementLink()
	 * @generated
	 */
    EReference getElementLink_Src();

    /**
	 * Returns the meta object for the reference '{@link grl.ElementLink#getDest <em>Dest</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Dest</em>'.
	 * @see grl.ElementLink#getDest()
	 * @see #getElementLink()
	 * @generated
	 */
    EReference getElementLink_Dest();

    /**
	 * Returns the meta object for class '{@link grl.Decomposition <em>Decomposition</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Decomposition</em>'.
	 * @see grl.Decomposition
	 * @generated
	 */
    EClass getDecomposition();

    /**
	 * Returns the meta object for class '{@link grl.Dependency <em>Dependency</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dependency</em>'.
	 * @see grl.Dependency
	 * @generated
	 */
    EClass getDependency();

    /**
	 * Returns the meta object for class '{@link grl.Evaluation <em>Evaluation</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Evaluation</em>'.
	 * @see grl.Evaluation
	 * @generated
	 */
    EClass getEvaluation();

    /**
	 * Returns the meta object for the attribute '{@link grl.Evaluation#getEvaluation <em>Evaluation</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Evaluation</em>'.
	 * @see grl.Evaluation#getEvaluation()
	 * @see #getEvaluation()
	 * @generated
	 */
    EAttribute getEvaluation_Evaluation();

    /**
	 * Returns the meta object for the attribute '{@link grl.Evaluation#getQualitativeEvaluation <em>Qualitative Evaluation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Qualitative Evaluation</em>'.
	 * @see grl.Evaluation#getQualitativeEvaluation()
	 * @see #getEvaluation()
	 * @generated
	 */
	EAttribute getEvaluation_QualitativeEvaluation();

				/**
	 * Returns the meta object for the attribute '{@link grl.Evaluation#isExceeds <em>Exceeds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Exceeds</em>'.
	 * @see grl.Evaluation#isExceeds()
	 * @see #getEvaluation()
	 * @generated
	 */
	EAttribute getEvaluation_Exceeds();

				/**
	 * Returns the meta object for the reference '{@link grl.Evaluation#getIntElement <em>Int Element</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Int Element</em>'.
	 * @see grl.Evaluation#getIntElement()
	 * @see #getEvaluation()
	 * @generated
	 */
    EReference getEvaluation_IntElement();

    /**
	 * Returns the meta object for the container reference '{@link grl.Evaluation#getStrategies <em>Strategies</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Strategies</em>'.
	 * @see grl.Evaluation#getStrategies()
	 * @see #getEvaluation()
	 * @generated
	 */
    EReference getEvaluation_Strategies();

    /**
	 * Returns the meta object for the containment reference '{@link grl.Evaluation#getKpiEvalValueSet <em>Kpi Eval Value Set</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Kpi Eval Value Set</em>'.
	 * @see grl.Evaluation#getKpiEvalValueSet()
	 * @see #getEvaluation()
	 * @generated
	 */
    EReference getEvaluation_KpiEvalValueSet();

    /**
	 * Returns the meta object for the containment reference '{@link grl.Evaluation#getEvalRange <em>Eval Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Eval Range</em>'.
	 * @see grl.Evaluation#getEvalRange()
	 * @see #getEvaluation()
	 * @generated
	 */
	EReference getEvaluation_EvalRange();

				/**
	 * Returns the meta object for the containment reference '{@link grl.Evaluation#getKpiNewEvalValue <em>Kpi New Eval Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Kpi New Eval Value</em>'.
	 * @see grl.Evaluation#getKpiNewEvalValue()
	 * @see #getEvaluation()
	 * @generated
	 */
	EReference getEvaluation_KpiNewEvalValue();

				/**
	 * Returns the meta object for class '{@link grl.EvaluationStrategy <em>Evaluation Strategy</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Evaluation Strategy</em>'.
	 * @see grl.EvaluationStrategy
	 * @generated
	 */
    EClass getEvaluationStrategy();

    /**
	 * Returns the meta object for the attribute '{@link grl.EvaluationStrategy#getAuthor <em>Author</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Author</em>'.
	 * @see grl.EvaluationStrategy#getAuthor()
	 * @see #getEvaluationStrategy()
	 * @generated
	 */
    EAttribute getEvaluationStrategy_Author();

    /**
	 * Returns the meta object for the containment reference list '{@link grl.EvaluationStrategy#getEvaluations <em>Evaluations</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Evaluations</em>'.
	 * @see grl.EvaluationStrategy#getEvaluations()
	 * @see #getEvaluationStrategy()
	 * @generated
	 */
    EReference getEvaluationStrategy_Evaluations();

    /**
	 * Returns the meta object for the reference '{@link grl.EvaluationStrategy#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Group</em>'.
	 * @see grl.EvaluationStrategy#getGroup()
	 * @see #getEvaluationStrategy()
	 * @generated
	 */
    EReference getEvaluationStrategy_Group();

    /**
	 * Returns the meta object for the container reference '{@link grl.EvaluationStrategy#getGrlspec <em>Grlspec</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Grlspec</em>'.
	 * @see grl.EvaluationStrategy#getGrlspec()
	 * @see #getEvaluationStrategy()
	 * @generated
	 */
    EReference getEvaluationStrategy_Grlspec();

    /**
	 * Returns the meta object for the reference list '{@link grl.EvaluationStrategy#getIncludedStrategies <em>Included Strategies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Included Strategies</em>'.
	 * @see grl.EvaluationStrategy#getIncludedStrategies()
	 * @see #getEvaluationStrategy()
	 * @generated
	 */
	EReference getEvaluationStrategy_IncludedStrategies();

				/**
	 * Returns the meta object for the reference list '{@link grl.EvaluationStrategy#getParentStrategies <em>Parent Strategies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parent Strategies</em>'.
	 * @see grl.EvaluationStrategy#getParentStrategies()
	 * @see #getEvaluationStrategy()
	 * @generated
	 */
	EReference getEvaluationStrategy_ParentStrategies();

				/**
	 * Returns the meta object for the containment reference list '{@link grl.EvaluationStrategy#getKpiInfoConfig <em>Kpi Info Config</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Kpi Info Config</em>'.
	 * @see grl.EvaluationStrategy#getKpiInfoConfig()
	 * @see #getEvaluationStrategy()
	 * @generated
	 */
    EReference getEvaluationStrategy_KpiInfoConfig();

    /**
	 * Returns the meta object for class '{@link grl.GRLNode <em>GRL Node</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>GRL Node</em>'.
	 * @see grl.GRLNode
	 * @generated
	 */
    EClass getGRLNode();

    /**
	 * Returns the meta object for class '{@link grl.LinkRefBendpoint <em>Link Ref Bendpoint</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Link Ref Bendpoint</em>'.
	 * @see grl.LinkRefBendpoint
	 * @generated
	 */
    EClass getLinkRefBendpoint();

    /**
	 * Returns the meta object for the attribute '{@link grl.LinkRefBendpoint#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see grl.LinkRefBendpoint#getX()
	 * @see #getLinkRefBendpoint()
	 * @generated
	 */
    EAttribute getLinkRefBendpoint_X();

    /**
	 * Returns the meta object for the attribute '{@link grl.LinkRefBendpoint#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see grl.LinkRefBendpoint#getY()
	 * @see #getLinkRefBendpoint()
	 * @generated
	 */
    EAttribute getLinkRefBendpoint_Y();

    /**
	 * Returns the meta object for the container reference '{@link grl.LinkRefBendpoint#getLinkref <em>Linkref</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Linkref</em>'.
	 * @see grl.LinkRefBendpoint#getLinkref()
	 * @see #getLinkRefBendpoint()
	 * @generated
	 */
    EReference getLinkRefBendpoint_Linkref();

    /**
	 * Returns the meta object for class '{@link grl.BeliefLink <em>Belief Link</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Belief Link</em>'.
	 * @see grl.BeliefLink
	 * @generated
	 */
    EClass getBeliefLink();

    /**
	 * Returns the meta object for class '{@link grl.StrategiesGroup <em>Strategies Group</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Strategies Group</em>'.
	 * @see grl.StrategiesGroup
	 * @generated
	 */
    EClass getStrategiesGroup();

    /**
	 * Returns the meta object for the reference list '{@link grl.StrategiesGroup#getStrategies <em>Strategies</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Strategies</em>'.
	 * @see grl.StrategiesGroup#getStrategies()
	 * @see #getStrategiesGroup()
	 * @generated
	 */
    EReference getStrategiesGroup_Strategies();

    /**
	 * Returns the meta object for the container reference '{@link grl.StrategiesGroup#getGrlspec <em>Grlspec</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Grlspec</em>'.
	 * @see grl.StrategiesGroup#getGrlspec()
	 * @see #getStrategiesGroup()
	 * @generated
	 */
    EReference getStrategiesGroup_Grlspec();

    /**
	 * Returns the meta object for class '{@link grl.ContributionContextGroup <em>Contribution Context Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contribution Context Group</em>'.
	 * @see grl.ContributionContextGroup
	 * @generated
	 */
	EClass getContributionContextGroup();

				/**
	 * Returns the meta object for the container reference '{@link grl.ContributionContextGroup#getGrlspec <em>Grlspec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Grlspec</em>'.
	 * @see grl.ContributionContextGroup#getGrlspec()
	 * @see #getContributionContextGroup()
	 * @generated
	 */
	EReference getContributionContextGroup_Grlspec();

				/**
	 * Returns the meta object for the reference list '{@link grl.ContributionContextGroup#getContribs <em>Contribs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Contribs</em>'.
	 * @see grl.ContributionContextGroup#getContribs()
	 * @see #getContributionContextGroup()
	 * @generated
	 */
	EReference getContributionContextGroup_Contribs();

				/**
	 * Returns the meta object for class '{@link grl.ContributionContext <em>Contribution Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contribution Context</em>'.
	 * @see grl.ContributionContext
	 * @generated
	 */
	EClass getContributionContext();

				/**
	 * Returns the meta object for the container reference '{@link grl.ContributionContext#getGrlspec <em>Grlspec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Grlspec</em>'.
	 * @see grl.ContributionContext#getGrlspec()
	 * @see #getContributionContext()
	 * @generated
	 */
	EReference getContributionContext_Grlspec();

				/**
	 * Returns the meta object for the reference list '{@link grl.ContributionContext#getGroups <em>Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Groups</em>'.
	 * @see grl.ContributionContext#getGroups()
	 * @see #getContributionContext()
	 * @generated
	 */
	EReference getContributionContext_Groups();

				/**
	 * Returns the meta object for the containment reference list '{@link grl.ContributionContext#getChanges <em>Changes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Changes</em>'.
	 * @see grl.ContributionContext#getChanges()
	 * @see #getContributionContext()
	 * @generated
	 */
	EReference getContributionContext_Changes();

				/**
	 * Returns the meta object for the reference list '{@link grl.ContributionContext#getParentContexts <em>Parent Contexts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parent Contexts</em>'.
	 * @see grl.ContributionContext#getParentContexts()
	 * @see #getContributionContext()
	 * @generated
	 */
	EReference getContributionContext_ParentContexts();

				/**
	 * Returns the meta object for the reference list '{@link grl.ContributionContext#getIncludedContexts <em>Included Contexts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Included Contexts</em>'.
	 * @see grl.ContributionContext#getIncludedContexts()
	 * @see #getContributionContext()
	 * @generated
	 */
	EReference getContributionContext_IncludedContexts();

				/**
	 * Returns the meta object for class '{@link grl.ContributionChange <em>Contribution Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contribution Change</em>'.
	 * @see grl.ContributionChange
	 * @generated
	 */
	EClass getContributionChange();

				/**
	 * Returns the meta object for the attribute '{@link grl.ContributionChange#getNewContribution <em>New Contribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>New Contribution</em>'.
	 * @see grl.ContributionChange#getNewContribution()
	 * @see #getContributionChange()
	 * @generated
	 */
	EAttribute getContributionChange_NewContribution();

				/**
	 * Returns the meta object for the attribute '{@link grl.ContributionChange#getNewQuantitativeContribution <em>New Quantitative Contribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>New Quantitative Contribution</em>'.
	 * @see grl.ContributionChange#getNewQuantitativeContribution()
	 * @see #getContributionChange()
	 * @generated
	 */
	EAttribute getContributionChange_NewQuantitativeContribution();

				/**
	 * Returns the meta object for the container reference '{@link grl.ContributionChange#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Context</em>'.
	 * @see grl.ContributionChange#getContext()
	 * @see #getContributionChange()
	 * @generated
	 */
	EReference getContributionChange_Context();

				/**
	 * Returns the meta object for the reference '{@link grl.ContributionChange#getContribution <em>Contribution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Contribution</em>'.
	 * @see grl.ContributionChange#getContribution()
	 * @see #getContributionChange()
	 * @generated
	 */
	EReference getContributionChange_Contribution();

				/**
	 * Returns the meta object for the containment reference '{@link grl.ContributionChange#getContribRange <em>Contrib Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Contrib Range</em>'.
	 * @see grl.ContributionChange#getContribRange()
	 * @see #getContributionChange()
	 * @generated
	 */
	EReference getContributionChange_ContribRange();

				/**
	 * Returns the meta object for class '{@link grl.GRLLinkableElement <em>GRL Linkable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>GRL Linkable Element</em>'.
	 * @see grl.GRLLinkableElement
	 * @generated
	 */
	EClass getGRLLinkableElement();

				/**
	 * Returns the meta object for the reference list '{@link grl.GRLLinkableElement#getLinksDest <em>Links Dest</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Links Dest</em>'.
	 * @see grl.GRLLinkableElement#getLinksDest()
	 * @see #getGRLLinkableElement()
	 * @generated
	 */
	EReference getGRLLinkableElement_LinksDest();

				/**
	 * Returns the meta object for the reference list '{@link grl.GRLLinkableElement#getLinksSrc <em>Links Src</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Links Src</em>'.
	 * @see grl.GRLLinkableElement#getLinksSrc()
	 * @see #getGRLLinkableElement()
	 * @generated
	 */
	EReference getGRLLinkableElement_LinksSrc();

				/**
	 * Returns the meta object for class '{@link grl.CollapsedActorRef <em>Collapsed Actor Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collapsed Actor Ref</em>'.
	 * @see grl.CollapsedActorRef
	 * @generated
	 */
	EClass getCollapsedActorRef();

				/**
	 * Returns the meta object for the reference '{@link grl.CollapsedActorRef#getActor <em>Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Actor</em>'.
	 * @see grl.CollapsedActorRef#getActor()
	 * @see #getCollapsedActorRef()
	 * @generated
	 */
	EReference getCollapsedActorRef_Actor();

				/**
	 * Returns the meta object for class '{@link grl.EvaluationRange <em>Evaluation Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Evaluation Range</em>'.
	 * @see grl.EvaluationRange
	 * @generated
	 */
	EClass getEvaluationRange();

				/**
	 * Returns the meta object for the attribute '{@link grl.EvaluationRange#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start</em>'.
	 * @see grl.EvaluationRange#getStart()
	 * @see #getEvaluationRange()
	 * @generated
	 */
	EAttribute getEvaluationRange_Start();

				/**
	 * Returns the meta object for the attribute '{@link grl.EvaluationRange#getEnd <em>End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End</em>'.
	 * @see grl.EvaluationRange#getEnd()
	 * @see #getEvaluationRange()
	 * @generated
	 */
	EAttribute getEvaluationRange_End();

				/**
	 * Returns the meta object for the attribute '{@link grl.EvaluationRange#getStep <em>Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Step</em>'.
	 * @see grl.EvaluationRange#getStep()
	 * @see #getEvaluationRange()
	 * @generated
	 */
	EAttribute getEvaluationRange_Step();

				/**
	 * Returns the meta object for the container reference '{@link grl.EvaluationRange#getEval <em>Eval</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Eval</em>'.
	 * @see grl.EvaluationRange#getEval()
	 * @see #getEvaluationRange()
	 * @generated
	 */
	EReference getEvaluationRange_Eval();

				/**
	 * Returns the meta object for class '{@link grl.ContributionRange <em>Contribution Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contribution Range</em>'.
	 * @see grl.ContributionRange
	 * @generated
	 */
	EClass getContributionRange();

				/**
	 * Returns the meta object for the attribute '{@link grl.ContributionRange#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start</em>'.
	 * @see grl.ContributionRange#getStart()
	 * @see #getContributionRange()
	 * @generated
	 */
	EAttribute getContributionRange_Start();

				/**
	 * Returns the meta object for the attribute '{@link grl.ContributionRange#getEnd <em>End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End</em>'.
	 * @see grl.ContributionRange#getEnd()
	 * @see #getContributionRange()
	 * @generated
	 */
	EAttribute getContributionRange_End();

				/**
	 * Returns the meta object for the attribute '{@link grl.ContributionRange#getStep <em>Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Step</em>'.
	 * @see grl.ContributionRange#getStep()
	 * @see #getContributionRange()
	 * @generated
	 */
	EAttribute getContributionRange_Step();

				/**
	 * Returns the meta object for the container reference '{@link grl.ContributionRange#getChange <em>Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Change</em>'.
	 * @see grl.ContributionRange#getChange()
	 * @see #getContributionRange()
	 * @generated
	 */
	EReference getContributionRange_Change();

				/**
	 * Returns the meta object for class '{@link grl.ImpactModel <em>Impact Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Impact Model</em>'.
	 * @see grl.ImpactModel
	 * @generated
	 */
	EClass getImpactModel();

				/**
	 * Returns the meta object for the container reference '{@link grl.ImpactModel#getGrlspec <em>Grlspec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Grlspec</em>'.
	 * @see grl.ImpactModel#getGrlspec()
	 * @see #getImpactModel()
	 * @generated
	 */
	EReference getImpactModel_Grlspec();

				/**
	 * Returns the meta object for enum '{@link grl.Criticality <em>Criticality</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Criticality</em>'.
	 * @see grl.Criticality
	 * @generated
	 */
    EEnum getCriticality();

    /**
	 * Returns the meta object for enum '{@link grl.IntentionalElementType <em>Intentional Element Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Intentional Element Type</em>'.
	 * @see grl.IntentionalElementType
	 * @generated
	 */
    EEnum getIntentionalElementType();

    /**
	 * Returns the meta object for enum '{@link grl.Priority <em>Priority</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Priority</em>'.
	 * @see grl.Priority
	 * @generated
	 */
    EEnum getPriority();

    /**
	 * Returns the meta object for enum '{@link grl.ContributionType <em>Contribution Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Contribution Type</em>'.
	 * @see grl.ContributionType
	 * @generated
	 */
    EEnum getContributionType();

    /**
	 * Returns the meta object for enum '{@link grl.DecompositionType <em>Decomposition Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Decomposition Type</em>'.
	 * @see grl.DecompositionType
	 * @generated
	 */
    EEnum getDecompositionType();

    /**
	 * Returns the meta object for enum '{@link grl.QualitativeLabel <em>Qualitative Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Qualitative Label</em>'.
	 * @see grl.QualitativeLabel
	 * @generated
	 */
	EEnum getQualitativeLabel();

				/**
	 * Returns the meta object for enum '{@link grl.ImportanceType <em>Importance Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Importance Type</em>'.
	 * @see grl.ImportanceType
	 * @generated
	 */
	EEnum getImportanceType();

				/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
    GrlFactory getGrlFactory();

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
	interface Literals  {
        /**
		 * The meta object literal for the '{@link grl.impl.GRLspecImpl <em>GR Lspec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.GRLspecImpl
		 * @see grl.impl.GrlPackageImpl#getGRLspec()
		 * @generated
		 */
		EClass GR_LSPEC = eINSTANCE.getGRLspec();

        /**
		 * The meta object literal for the '<em><b>Urnspec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GR_LSPEC__URNSPEC = eINSTANCE.getGRLspec_Urnspec();

        /**
		 * The meta object literal for the '<em><b>Int Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GR_LSPEC__INT_ELEMENTS = eINSTANCE.getGRLspec_IntElements();

        /**
		 * The meta object literal for the '<em><b>Actors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GR_LSPEC__ACTORS = eINSTANCE.getGRLspec_Actors();

        /**
		 * The meta object literal for the '<em><b>Links</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GR_LSPEC__LINKS = eINSTANCE.getGRLspec_Links();

        /**
		 * The meta object literal for the '<em><b>Groups</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GR_LSPEC__GROUPS = eINSTANCE.getGRLspec_Groups();

        /**
		 * The meta object literal for the '<em><b>Strategies</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GR_LSPEC__STRATEGIES = eINSTANCE.getGRLspec_Strategies();

        /**
		 * The meta object literal for the '<em><b>Contribution Groups</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GR_LSPEC__CONTRIBUTION_GROUPS = eINSTANCE.getGRLspec_ContributionGroups();

								/**
		 * The meta object literal for the '<em><b>Contribution Contexts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GR_LSPEC__CONTRIBUTION_CONTEXTS = eINSTANCE.getGRLspec_ContributionContexts();

								/**
		 * The meta object literal for the '<em><b>Impact Model</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GR_LSPEC__IMPACT_MODEL = eINSTANCE.getGRLspec_ImpactModel();

								/**
		 * The meta object literal for the '<em><b>Kpi Information Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference GR_LSPEC__KPI_INFORMATION_ELEMENTS = eINSTANCE.getGRLspec_KpiInformationElements();

        /**
		 * The meta object literal for the '<em><b>Kpi Model Links</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference GR_LSPEC__KPI_MODEL_LINKS = eINSTANCE.getGRLspec_KpiModelLinks();

        /**
		 * The meta object literal for the '<em><b>Indicator Group</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference GR_LSPEC__INDICATOR_GROUP = eINSTANCE.getGRLspec_IndicatorGroup();

        /**
		 * The meta object literal for the '<em><b>KPI Conversion</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GR_LSPEC__KPI_CONVERSION = eINSTANCE.getGRLspec_KPIConversion();

								/**
		 * The meta object literal for the '<em><b>Feature Model</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GR_LSPEC__FEATURE_MODEL = eINSTANCE.getGRLspec_FeatureModel();

								/**
		 * The meta object literal for the '{@link grl.impl.BeliefImpl <em>Belief</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.BeliefImpl
		 * @see grl.impl.GrlPackageImpl#getBelief()
		 * @generated
		 */
		EClass BELIEF = eINSTANCE.getBelief();

        /**
		 * The meta object literal for the '<em><b>Author</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BELIEF__AUTHOR = eINSTANCE.getBelief_Author();

        /**
		 * The meta object literal for the '{@link grl.impl.IntentionalElementImpl <em>Intentional Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.IntentionalElementImpl
		 * @see grl.impl.GrlPackageImpl#getIntentionalElement()
		 * @generated
		 */
		EClass INTENTIONAL_ELEMENT = eINSTANCE.getIntentionalElement();

        /**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTIONAL_ELEMENT__TYPE = eINSTANCE.getIntentionalElement_Type();

        /**
		 * The meta object literal for the '<em><b>Decomposition Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE = eINSTANCE.getIntentionalElement_DecompositionType();

        /**
		 * The meta object literal for the '<em><b>Importance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTIONAL_ELEMENT__IMPORTANCE = eINSTANCE.getIntentionalElement_Importance();

								/**
		 * The meta object literal for the '<em><b>Importance Quantitative</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTIONAL_ELEMENT__IMPORTANCE_QUANTITATIVE = eINSTANCE.getIntentionalElement_ImportanceQuantitative();

								/**
		 * The meta object literal for the '<em><b>Line Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTIONAL_ELEMENT__LINE_COLOR = eINSTANCE.getIntentionalElement_LineColor();

        /**
		 * The meta object literal for the '<em><b>Fill Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTIONAL_ELEMENT__FILL_COLOR = eINSTANCE.getIntentionalElement_FillColor();

        /**
		 * The meta object literal for the '<em><b>Filled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTIONAL_ELEMENT__FILLED = eINSTANCE.getIntentionalElement_Filled();

        /**
		 * The meta object literal for the '<em><b>Grlspec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENTIONAL_ELEMENT__GRLSPEC = eINSTANCE.getIntentionalElement_Grlspec();

        /**
		 * The meta object literal for the '<em><b>Refs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENTIONAL_ELEMENT__REFS = eINSTANCE.getIntentionalElement_Refs();

        /**
		 * The meta object literal for the '{@link grl.impl.ActorImpl <em>Actor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.ActorImpl
		 * @see grl.impl.GrlPackageImpl#getActor()
		 * @generated
		 */
		EClass ACTOR = eINSTANCE.getActor();

        /**
		 * The meta object literal for the '<em><b>Importance</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTOR__IMPORTANCE = eINSTANCE.getActor_Importance();

								/**
		 * The meta object literal for the '<em><b>Importance Quantitative</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTOR__IMPORTANCE_QUANTITATIVE = eINSTANCE.getActor_ImportanceQuantitative();

								/**
		 * The meta object literal for the '<em><b>Grlspec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__GRLSPEC = eINSTANCE.getActor_Grlspec();

        /**
		 * The meta object literal for the '<em><b>Included Actors</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__INCLUDED_ACTORS = eINSTANCE.getActor_IncludedActors();

								/**
		 * The meta object literal for the '<em><b>Including Actor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__INCLUDING_ACTOR = eINSTANCE.getActor_IncludingActor();

								/**
		 * The meta object literal for the '<em><b>Collapsed Refs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTOR__COLLAPSED_REFS = eINSTANCE.getActor_CollapsedRefs();

								/**
		 * The meta object literal for the '{@link grl.impl.GRLGraphImpl <em>GRL Graph</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.GRLGraphImpl
		 * @see grl.impl.GrlPackageImpl#getGRLGraph()
		 * @generated
		 */
		EClass GRL_GRAPH = eINSTANCE.getGRLGraph();

        /**
		 * The meta object literal for the '{@link grl.impl.ActorRefImpl <em>Actor Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.ActorRefImpl
		 * @see grl.impl.GrlPackageImpl#getActorRef()
		 * @generated
		 */
		EClass ACTOR_REF = eINSTANCE.getActorRef();

        /**
		 * The meta object literal for the '{@link grl.impl.IntentionalElementRefImpl <em>Intentional Element Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.IntentionalElementRefImpl
		 * @see grl.impl.GrlPackageImpl#getIntentionalElementRef()
		 * @generated
		 */
		EClass INTENTIONAL_ELEMENT_REF = eINSTANCE.getIntentionalElementRef();

        /**
		 * The meta object literal for the '<em><b>Criticality</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTIONAL_ELEMENT_REF__CRITICALITY = eINSTANCE.getIntentionalElementRef_Criticality();

        /**
		 * The meta object literal for the '<em><b>Priority</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTENTIONAL_ELEMENT_REF__PRIORITY = eINSTANCE.getIntentionalElementRef_Priority();

        /**
		 * The meta object literal for the '<em><b>Def</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTENTIONAL_ELEMENT_REF__DEF = eINSTANCE.getIntentionalElementRef_Def();

        /**
		 * The meta object literal for the '{@link grl.impl.ContributionImpl <em>Contribution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.ContributionImpl
		 * @see grl.impl.GrlPackageImpl#getContribution()
		 * @generated
		 */
		EClass CONTRIBUTION = eINSTANCE.getContribution();

        /**
		 * The meta object literal for the '<em><b>Contribution</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTRIBUTION__CONTRIBUTION = eINSTANCE.getContribution_Contribution();

        /**
		 * The meta object literal for the '<em><b>Quantitative Contribution</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTRIBUTION__QUANTITATIVE_CONTRIBUTION = eINSTANCE.getContribution_QuantitativeContribution();

								/**
		 * The meta object literal for the '<em><b>Correlation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTRIBUTION__CORRELATION = eINSTANCE.getContribution_Correlation();

        /**
		 * The meta object literal for the '{@link grl.impl.LinkRefImpl <em>Link Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.LinkRefImpl
		 * @see grl.impl.GrlPackageImpl#getLinkRef()
		 * @generated
		 */
		EClass LINK_REF = eINSTANCE.getLinkRef();

        /**
		 * The meta object literal for the '<em><b>Link</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK_REF__LINK = eINSTANCE.getLinkRef_Link();

        /**
		 * The meta object literal for the '<em><b>Bendpoints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK_REF__BENDPOINTS = eINSTANCE.getLinkRef_Bendpoints();

        /**
		 * The meta object literal for the '{@link grl.impl.ElementLinkImpl <em>Element Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.ElementLinkImpl
		 * @see grl.impl.GrlPackageImpl#getElementLink()
		 * @generated
		 */
		EClass ELEMENT_LINK = eINSTANCE.getElementLink();

        /**
		 * The meta object literal for the '<em><b>Refs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT_LINK__REFS = eINSTANCE.getElementLink_Refs();

        /**
		 * The meta object literal for the '<em><b>Grlspec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT_LINK__GRLSPEC = eINSTANCE.getElementLink_Grlspec();

        /**
		 * The meta object literal for the '<em><b>Src</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT_LINK__SRC = eINSTANCE.getElementLink_Src();

        /**
		 * The meta object literal for the '<em><b>Dest</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT_LINK__DEST = eINSTANCE.getElementLink_Dest();

        /**
		 * The meta object literal for the '{@link grl.impl.DecompositionImpl <em>Decomposition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.DecompositionImpl
		 * @see grl.impl.GrlPackageImpl#getDecomposition()
		 * @generated
		 */
		EClass DECOMPOSITION = eINSTANCE.getDecomposition();

        /**
		 * The meta object literal for the '{@link grl.impl.DependencyImpl <em>Dependency</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.DependencyImpl
		 * @see grl.impl.GrlPackageImpl#getDependency()
		 * @generated
		 */
		EClass DEPENDENCY = eINSTANCE.getDependency();

        /**
		 * The meta object literal for the '{@link grl.impl.EvaluationImpl <em>Evaluation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.EvaluationImpl
		 * @see grl.impl.GrlPackageImpl#getEvaluation()
		 * @generated
		 */
		EClass EVALUATION = eINSTANCE.getEvaluation();

        /**
		 * The meta object literal for the '<em><b>Evaluation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVALUATION__EVALUATION = eINSTANCE.getEvaluation_Evaluation();

        /**
		 * The meta object literal for the '<em><b>Qualitative Evaluation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVALUATION__QUALITATIVE_EVALUATION = eINSTANCE.getEvaluation_QualitativeEvaluation();

								/**
		 * The meta object literal for the '<em><b>Exceeds</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVALUATION__EXCEEDS = eINSTANCE.getEvaluation_Exceeds();

								/**
		 * The meta object literal for the '<em><b>Int Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVALUATION__INT_ELEMENT = eINSTANCE.getEvaluation_IntElement();

        /**
		 * The meta object literal for the '<em><b>Strategies</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVALUATION__STRATEGIES = eINSTANCE.getEvaluation_Strategies();

        /**
		 * The meta object literal for the '<em><b>Kpi Eval Value Set</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference EVALUATION__KPI_EVAL_VALUE_SET = eINSTANCE.getEvaluation_KpiEvalValueSet();

        /**
		 * The meta object literal for the '<em><b>Eval Range</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVALUATION__EVAL_RANGE = eINSTANCE.getEvaluation_EvalRange();

								/**
		 * The meta object literal for the '<em><b>Kpi New Eval Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVALUATION__KPI_NEW_EVAL_VALUE = eINSTANCE.getEvaluation_KpiNewEvalValue();

								/**
		 * The meta object literal for the '{@link grl.impl.EvaluationStrategyImpl <em>Evaluation Strategy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.EvaluationStrategyImpl
		 * @see grl.impl.GrlPackageImpl#getEvaluationStrategy()
		 * @generated
		 */
		EClass EVALUATION_STRATEGY = eINSTANCE.getEvaluationStrategy();

        /**
		 * The meta object literal for the '<em><b>Author</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVALUATION_STRATEGY__AUTHOR = eINSTANCE.getEvaluationStrategy_Author();

        /**
		 * The meta object literal for the '<em><b>Evaluations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVALUATION_STRATEGY__EVALUATIONS = eINSTANCE.getEvaluationStrategy_Evaluations();

        /**
		 * The meta object literal for the '<em><b>Group</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVALUATION_STRATEGY__GROUP = eINSTANCE.getEvaluationStrategy_Group();

        /**
		 * The meta object literal for the '<em><b>Grlspec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVALUATION_STRATEGY__GRLSPEC = eINSTANCE.getEvaluationStrategy_Grlspec();

        /**
		 * The meta object literal for the '<em><b>Included Strategies</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVALUATION_STRATEGY__INCLUDED_STRATEGIES = eINSTANCE.getEvaluationStrategy_IncludedStrategies();

								/**
		 * The meta object literal for the '<em><b>Parent Strategies</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVALUATION_STRATEGY__PARENT_STRATEGIES = eINSTANCE.getEvaluationStrategy_ParentStrategies();

								/**
		 * The meta object literal for the '<em><b>Kpi Info Config</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference EVALUATION_STRATEGY__KPI_INFO_CONFIG = eINSTANCE.getEvaluationStrategy_KpiInfoConfig();

        /**
		 * The meta object literal for the '{@link grl.impl.GRLNodeImpl <em>GRL Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.GRLNodeImpl
		 * @see grl.impl.GrlPackageImpl#getGRLNode()
		 * @generated
		 */
		EClass GRL_NODE = eINSTANCE.getGRLNode();

        /**
		 * The meta object literal for the '{@link grl.impl.LinkRefBendpointImpl <em>Link Ref Bendpoint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.LinkRefBendpointImpl
		 * @see grl.impl.GrlPackageImpl#getLinkRefBendpoint()
		 * @generated
		 */
		EClass LINK_REF_BENDPOINT = eINSTANCE.getLinkRefBendpoint();

        /**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK_REF_BENDPOINT__X = eINSTANCE.getLinkRefBendpoint_X();

        /**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK_REF_BENDPOINT__Y = eINSTANCE.getLinkRefBendpoint_Y();

        /**
		 * The meta object literal for the '<em><b>Linkref</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK_REF_BENDPOINT__LINKREF = eINSTANCE.getLinkRefBendpoint_Linkref();

        /**
		 * The meta object literal for the '{@link grl.impl.BeliefLinkImpl <em>Belief Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.BeliefLinkImpl
		 * @see grl.impl.GrlPackageImpl#getBeliefLink()
		 * @generated
		 */
		EClass BELIEF_LINK = eINSTANCE.getBeliefLink();

        /**
		 * The meta object literal for the '{@link grl.impl.StrategiesGroupImpl <em>Strategies Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.StrategiesGroupImpl
		 * @see grl.impl.GrlPackageImpl#getStrategiesGroup()
		 * @generated
		 */
		EClass STRATEGIES_GROUP = eINSTANCE.getStrategiesGroup();

        /**
		 * The meta object literal for the '<em><b>Strategies</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRATEGIES_GROUP__STRATEGIES = eINSTANCE.getStrategiesGroup_Strategies();

        /**
		 * The meta object literal for the '<em><b>Grlspec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRATEGIES_GROUP__GRLSPEC = eINSTANCE.getStrategiesGroup_Grlspec();

        /**
		 * The meta object literal for the '{@link grl.impl.ContributionContextGroupImpl <em>Contribution Context Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.ContributionContextGroupImpl
		 * @see grl.impl.GrlPackageImpl#getContributionContextGroup()
		 * @generated
		 */
		EClass CONTRIBUTION_CONTEXT_GROUP = eINSTANCE.getContributionContextGroup();

								/**
		 * The meta object literal for the '<em><b>Grlspec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION_CONTEXT_GROUP__GRLSPEC = eINSTANCE.getContributionContextGroup_Grlspec();

								/**
		 * The meta object literal for the '<em><b>Contribs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION_CONTEXT_GROUP__CONTRIBS = eINSTANCE.getContributionContextGroup_Contribs();

								/**
		 * The meta object literal for the '{@link grl.impl.ContributionContextImpl <em>Contribution Context</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.ContributionContextImpl
		 * @see grl.impl.GrlPackageImpl#getContributionContext()
		 * @generated
		 */
		EClass CONTRIBUTION_CONTEXT = eINSTANCE.getContributionContext();

								/**
		 * The meta object literal for the '<em><b>Grlspec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION_CONTEXT__GRLSPEC = eINSTANCE.getContributionContext_Grlspec();

								/**
		 * The meta object literal for the '<em><b>Groups</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION_CONTEXT__GROUPS = eINSTANCE.getContributionContext_Groups();

								/**
		 * The meta object literal for the '<em><b>Changes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION_CONTEXT__CHANGES = eINSTANCE.getContributionContext_Changes();

								/**
		 * The meta object literal for the '<em><b>Parent Contexts</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION_CONTEXT__PARENT_CONTEXTS = eINSTANCE.getContributionContext_ParentContexts();

								/**
		 * The meta object literal for the '<em><b>Included Contexts</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION_CONTEXT__INCLUDED_CONTEXTS = eINSTANCE.getContributionContext_IncludedContexts();

								/**
		 * The meta object literal for the '{@link grl.impl.ContributionChangeImpl <em>Contribution Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.ContributionChangeImpl
		 * @see grl.impl.GrlPackageImpl#getContributionChange()
		 * @generated
		 */
		EClass CONTRIBUTION_CHANGE = eINSTANCE.getContributionChange();

								/**
		 * The meta object literal for the '<em><b>New Contribution</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTRIBUTION_CHANGE__NEW_CONTRIBUTION = eINSTANCE.getContributionChange_NewContribution();

								/**
		 * The meta object literal for the '<em><b>New Quantitative Contribution</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTRIBUTION_CHANGE__NEW_QUANTITATIVE_CONTRIBUTION = eINSTANCE.getContributionChange_NewQuantitativeContribution();

								/**
		 * The meta object literal for the '<em><b>Context</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION_CHANGE__CONTEXT = eINSTANCE.getContributionChange_Context();

								/**
		 * The meta object literal for the '<em><b>Contribution</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION_CHANGE__CONTRIBUTION = eINSTANCE.getContributionChange_Contribution();

								/**
		 * The meta object literal for the '<em><b>Contrib Range</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION_CHANGE__CONTRIB_RANGE = eINSTANCE.getContributionChange_ContribRange();

								/**
		 * The meta object literal for the '{@link grl.impl.GRLLinkableElementImpl <em>GRL Linkable Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.GRLLinkableElementImpl
		 * @see grl.impl.GrlPackageImpl#getGRLLinkableElement()
		 * @generated
		 */
		EClass GRL_LINKABLE_ELEMENT = eINSTANCE.getGRLLinkableElement();

								/**
		 * The meta object literal for the '<em><b>Links Dest</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRL_LINKABLE_ELEMENT__LINKS_DEST = eINSTANCE.getGRLLinkableElement_LinksDest();

								/**
		 * The meta object literal for the '<em><b>Links Src</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRL_LINKABLE_ELEMENT__LINKS_SRC = eINSTANCE.getGRLLinkableElement_LinksSrc();

								/**
		 * The meta object literal for the '{@link grl.impl.CollapsedActorRefImpl <em>Collapsed Actor Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.CollapsedActorRefImpl
		 * @see grl.impl.GrlPackageImpl#getCollapsedActorRef()
		 * @generated
		 */
		EClass COLLAPSED_ACTOR_REF = eINSTANCE.getCollapsedActorRef();

								/**
		 * The meta object literal for the '<em><b>Actor</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLLAPSED_ACTOR_REF__ACTOR = eINSTANCE.getCollapsedActorRef_Actor();

								/**
		 * The meta object literal for the '{@link grl.impl.EvaluationRangeImpl <em>Evaluation Range</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.EvaluationRangeImpl
		 * @see grl.impl.GrlPackageImpl#getEvaluationRange()
		 * @generated
		 */
		EClass EVALUATION_RANGE = eINSTANCE.getEvaluationRange();

								/**
		 * The meta object literal for the '<em><b>Start</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVALUATION_RANGE__START = eINSTANCE.getEvaluationRange_Start();

								/**
		 * The meta object literal for the '<em><b>End</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVALUATION_RANGE__END = eINSTANCE.getEvaluationRange_End();

								/**
		 * The meta object literal for the '<em><b>Step</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVALUATION_RANGE__STEP = eINSTANCE.getEvaluationRange_Step();

								/**
		 * The meta object literal for the '<em><b>Eval</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVALUATION_RANGE__EVAL = eINSTANCE.getEvaluationRange_Eval();

								/**
		 * The meta object literal for the '{@link grl.impl.ContributionRangeImpl <em>Contribution Range</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.ContributionRangeImpl
		 * @see grl.impl.GrlPackageImpl#getContributionRange()
		 * @generated
		 */
		EClass CONTRIBUTION_RANGE = eINSTANCE.getContributionRange();

								/**
		 * The meta object literal for the '<em><b>Start</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTRIBUTION_RANGE__START = eINSTANCE.getContributionRange_Start();

								/**
		 * The meta object literal for the '<em><b>End</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTRIBUTION_RANGE__END = eINSTANCE.getContributionRange_End();

								/**
		 * The meta object literal for the '<em><b>Step</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTRIBUTION_RANGE__STEP = eINSTANCE.getContributionRange_Step();

								/**
		 * The meta object literal for the '<em><b>Change</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTRIBUTION_RANGE__CHANGE = eINSTANCE.getContributionRange_Change();

								/**
		 * The meta object literal for the '{@link grl.impl.ImpactModelImpl <em>Impact Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.impl.ImpactModelImpl
		 * @see grl.impl.GrlPackageImpl#getImpactModel()
		 * @generated
		 */
		EClass IMPACT_MODEL = eINSTANCE.getImpactModel();

								/**
		 * The meta object literal for the '<em><b>Grlspec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPACT_MODEL__GRLSPEC = eINSTANCE.getImpactModel_Grlspec();

								/**
		 * The meta object literal for the '{@link grl.Criticality <em>Criticality</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.Criticality
		 * @see grl.impl.GrlPackageImpl#getCriticality()
		 * @generated
		 */
		EEnum CRITICALITY = eINSTANCE.getCriticality();

        /**
		 * The meta object literal for the '{@link grl.IntentionalElementType <em>Intentional Element Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.IntentionalElementType
		 * @see grl.impl.GrlPackageImpl#getIntentionalElementType()
		 * @generated
		 */
		EEnum INTENTIONAL_ELEMENT_TYPE = eINSTANCE.getIntentionalElementType();

        /**
		 * The meta object literal for the '{@link grl.Priority <em>Priority</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.Priority
		 * @see grl.impl.GrlPackageImpl#getPriority()
		 * @generated
		 */
		EEnum PRIORITY = eINSTANCE.getPriority();

        /**
		 * The meta object literal for the '{@link grl.ContributionType <em>Contribution Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.ContributionType
		 * @see grl.impl.GrlPackageImpl#getContributionType()
		 * @generated
		 */
		EEnum CONTRIBUTION_TYPE = eINSTANCE.getContributionType();

        /**
		 * The meta object literal for the '{@link grl.DecompositionType <em>Decomposition Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.DecompositionType
		 * @see grl.impl.GrlPackageImpl#getDecompositionType()
		 * @generated
		 */
		EEnum DECOMPOSITION_TYPE = eINSTANCE.getDecompositionType();

								/**
		 * The meta object literal for the '{@link grl.QualitativeLabel <em>Qualitative Label</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.QualitativeLabel
		 * @see grl.impl.GrlPackageImpl#getQualitativeLabel()
		 * @generated
		 */
		EEnum QUALITATIVE_LABEL = eINSTANCE.getQualitativeLabel();

								/**
		 * The meta object literal for the '{@link grl.ImportanceType <em>Importance Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see grl.ImportanceType
		 * @see grl.impl.GrlPackageImpl#getImportanceType()
		 * @generated
		 */
		EEnum IMPORTANCE_TYPE = eINSTANCE.getImportanceType();

	}

} //GrlPackage
