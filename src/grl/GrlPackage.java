/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;

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
     * The number of structural features of the '<em>GR Lspec</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GR_LSPEC_FEATURE_COUNT = 6;

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
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRL_NODE__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

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
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BELIEF__NAME = GRL_NODE__NAME;

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
     * The meta object id for the '{@link grl.impl.IntentionalElementImpl <em>Intentional Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see grl.impl.IntentionalElementImpl
     * @see grl.impl.GrlPackageImpl#getIntentionalElement()
     * @generated
     */
    int INTENTIONAL_ELEMENT = 2;

    /**
     * The feature id for the '<em><b>From Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__FROM_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__FROM_LINKS;

    /**
     * The feature id for the '<em><b>To Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__TO_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__TO_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__ID = UrncorePackage.GR_LMODEL_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__DESCRIPTION = UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int INTENTIONAL_ELEMENT__METADATA = UrncorePackage.GR_LMODEL_ELEMENT__METADATA;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__TYPE = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Decomposition Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Line Color</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__LINE_COLOR = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Fill Color</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__FILL_COLOR = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Filled</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__FILLED = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Grlspec</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__GRLSPEC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Refs</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__REFS = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Links Src</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__LINKS_SRC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Links Dest</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__LINKS_DEST = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The number of structural features of the '<em>Intentional Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT_FEATURE_COUNT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 9;

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
     * The feature id for the '<em><b>From Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR__FROM_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__FROM_LINKS;

    /**
     * The feature id for the '<em><b>To Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR__TO_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__TO_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR__ID = UrncorePackage.GR_LMODEL_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR__DESCRIPTION = UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTOR__METADATA = UrncorePackage.GR_LMODEL_ELEMENT__METADATA;

    /**
     * The feature id for the '<em><b>Line Color</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR__LINE_COLOR = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Fill Color</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR__FILL_COLOR = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Filled</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR__FILLED = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Cont Refs</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR__CONT_REFS = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Grlspec</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR__GRLSPEC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the '<em>Actor</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR_FEATURE_COUNT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 5;

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
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRL_GRAPH__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

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
     * The number of structural features of the '<em>GRL Graph</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRL_GRAPH_FEATURE_COUNT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 4;

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
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR_REF__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

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
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT_REF__NAME = GRL_NODE__NAME;

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
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ELEMENT_LINK__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

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
     * The feature id for the '<em><b>Src</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ELEMENT_LINK__SRC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Dest</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ELEMENT_LINK__DEST = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 3;

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
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTRIBUTION__NAME = ELEMENT_LINK__NAME;

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
     * The feature id for the '<em><b>Src</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTRIBUTION__SRC = ELEMENT_LINK__SRC;

    /**
     * The feature id for the '<em><b>Dest</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTRIBUTION__DEST = ELEMENT_LINK__DEST;

    /**
     * The feature id for the '<em><b>Contribution</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTRIBUTION__CONTRIBUTION = ELEMENT_LINK_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Correlation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTRIBUTION__CORRELATION = ELEMENT_LINK_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Contribution</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTRIBUTION_FEATURE_COUNT = ELEMENT_LINK_FEATURE_COUNT + 2;

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
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DECOMPOSITION__NAME = ELEMENT_LINK__NAME;

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
     * The feature id for the '<em><b>Src</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DECOMPOSITION__SRC = ELEMENT_LINK__SRC;

    /**
     * The feature id for the '<em><b>Dest</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DECOMPOSITION__DEST = ELEMENT_LINK__DEST;

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
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEPENDENCY__NAME = ELEMENT_LINK__NAME;

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
     * The feature id for the '<em><b>Src</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEPENDENCY__SRC = ELEMENT_LINK__SRC;

    /**
     * The feature id for the '<em><b>Dest</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEPENDENCY__DEST = ELEMENT_LINK__DEST;

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
     * The feature id for the '<em><b>Int Element</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVALUATION__INT_ELEMENT = 1;

    /**
     * The feature id for the '<em><b>Strategies</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVALUATION__STRATEGIES = 2;

    /**
     * The number of structural features of the '<em>Evaluation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVALUATION_FEATURE_COUNT = 3;

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
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVALUATION_STRATEGY__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

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
     * The number of structural features of the '<em>Evaluation Strategy</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVALUATION_STRATEGY_FEATURE_COUNT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 4;

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
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STRATEGIES_GROUP__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

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
     * The meta object id for the '{@link grl.Criticality <em>Criticality</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see grl.Criticality
     * @see grl.impl.GrlPackageImpl#getCriticality()
     * @generated
     */
    int CRITICALITY = 18;

    /**
     * The meta object id for the '{@link grl.IntentionalElementType <em>Intentional Element Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see grl.IntentionalElementType
     * @see grl.impl.GrlPackageImpl#getIntentionalElementType()
     * @generated
     */
    int INTENTIONAL_ELEMENT_TYPE = 19;

    /**
     * The meta object id for the '{@link grl.Priority <em>Priority</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see grl.Priority
     * @see grl.impl.GrlPackageImpl#getPriority()
     * @generated
     */
    int PRIORITY = 20;

    /**
     * The meta object id for the '{@link grl.ContributionType <em>Contribution Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see grl.ContributionType
     * @see grl.impl.GrlPackageImpl#getContributionType()
     * @generated
     */
    int CONTRIBUTION_TYPE = 21;

    /**
     * The meta object id for the '{@link grl.DecompositionType <em>Decomposition Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see grl.DecompositionType
     * @see grl.impl.GrlPackageImpl#getDecompositionType()
     * @generated
     */
    int DECOMPOSITION_TYPE = 22;


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
     * Returns the meta object for the reference list '{@link grl.IntentionalElement#getLinksSrc <em>Links Src</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Links Src</em>'.
     * @see grl.IntentionalElement#getLinksSrc()
     * @see #getIntentionalElement()
     * @generated
     */
    EReference getIntentionalElement_LinksSrc();

    /**
     * Returns the meta object for the reference list '{@link grl.IntentionalElement#getLinksDest <em>Links Dest</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Links Dest</em>'.
     * @see grl.IntentionalElement#getLinksDest()
     * @see #getIntentionalElement()
     * @generated
     */
    EReference getIntentionalElement_LinksDest();

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
         * The meta object literal for the '<em><b>Links Src</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference INTENTIONAL_ELEMENT__LINKS_SRC = eINSTANCE.getIntentionalElement_LinksSrc();

        /**
         * The meta object literal for the '<em><b>Links Dest</b></em>' reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference INTENTIONAL_ELEMENT__LINKS_DEST = eINSTANCE.getIntentionalElement_LinksDest();

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
         * The meta object literal for the '<em><b>Grlspec</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ACTOR__GRLSPEC = eINSTANCE.getActor_Grlspec();

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

	}

} //GrlPackage
