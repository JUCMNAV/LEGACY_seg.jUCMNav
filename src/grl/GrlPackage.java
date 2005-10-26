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
     * The number of structural features of the the '<em>GR Lspec</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GR_LSPEC_FEATURE_COUNT = 3;

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
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BELIEF__ID = UrncorePackage.GR_LMODEL_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BELIEF__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BELIEF__DESCRIPTION = UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BELIEF__URN_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__URN_LINKS;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BELIEF__X = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BELIEF__Y = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Spec Diagram</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BELIEF__SPEC_DIAGRAM = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Comp Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BELIEF__COMP_REF = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Succ</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BELIEF__SUCC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Pred</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BELIEF__PRED = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BELIEF__LABEL = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Connection</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BELIEF__CONNECTION = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the the '<em>Belief</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int BELIEF_FEATURE_COUNT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 8;

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
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__URN_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__URN_LINKS;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__TYPE = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Criticality</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__CRITICALITY = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Priority</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__PRIORITY = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Decomposition Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Grlspec</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__GRLSPEC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Refs</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__REFS = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Is Depender</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__IS_DEPENDER = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Decomposition Src</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__DECOMPOSITION_SRC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Decomposition Dest</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__DECOMPOSITION_DEST = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Contribution Src</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__CONTRIBUTION_SRC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Contribution Dest</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__CONTRIBUTION_DEST = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Is Dependum</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__IS_DEPENDUM = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Is Dependee</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__IS_DEPENDEE = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 12;

    /**
     * The feature id for the '<em><b>Evals</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT__EVALS = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 13;

    /**
     * The number of structural features of the the '<em>Intentional Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT_FEATURE_COUNT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 14;

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
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR__URN_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__URN_LINKS;

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
     * The feature id for the '<em><b>Comp Refs</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR__COMP_REFS = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Grlspec</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR__GRLSPEC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the the '<em>Actor</em>' class.
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
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRL_GRAPH__URN_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__URN_LINKS;

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
     * The feature id for the '<em><b>Comp Refs</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRL_GRAPH__COMP_REFS = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Connections</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int GRL_GRAPH__CONNECTIONS = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the the '<em>GRL Graph</em>' class.
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
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR_REF__X = UrncorePackage.SPECIFICATION_COMPONENT_REF__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR_REF__Y = UrncorePackage.SPECIFICATION_COMPONENT_REF__Y;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR_REF__WIDTH = UrncorePackage.SPECIFICATION_COMPONENT_REF__WIDTH;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR_REF__HEIGHT = UrncorePackage.SPECIFICATION_COMPONENT_REF__HEIGHT;

    /**
     * The feature id for the '<em><b>Fixed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR_REF__FIXED = UrncorePackage.SPECIFICATION_COMPONENT_REF__FIXED;

    /**
     * The feature id for the '<em><b>Spec Diagram</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR_REF__SPEC_DIAGRAM = UrncorePackage.SPECIFICATION_COMPONENT_REF__SPEC_DIAGRAM;

    /**
     * The feature id for the '<em><b>Comp Def</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR_REF__COMP_DEF = UrncorePackage.SPECIFICATION_COMPONENT_REF__COMP_DEF;

    /**
     * The feature id for the '<em><b>Nodes</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR_REF__NODES = UrncorePackage.SPECIFICATION_COMPONENT_REF__NODES;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR_REF__LABEL = UrncorePackage.SPECIFICATION_COMPONENT_REF__LABEL;

    /**
     * The feature id for the '<em><b>Parent</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR_REF__PARENT = UrncorePackage.SPECIFICATION_COMPONENT_REF__PARENT;

    /**
     * The feature id for the '<em><b>Children</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR_REF__CHILDREN = UrncorePackage.SPECIFICATION_COMPONENT_REF__CHILDREN;

    /**
     * The number of structural features of the the '<em>Actor Ref</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ACTOR_REF_FEATURE_COUNT = UrncorePackage.SPECIFICATION_COMPONENT_REF_FEATURE_COUNT + 0;

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
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT_REF__ID = UrncorePackage.GR_LMODEL_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT_REF__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT_REF__DESCRIPTION = UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT_REF__URN_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__URN_LINKS;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT_REF__X = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT_REF__Y = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Spec Diagram</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT_REF__SPEC_DIAGRAM = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Comp Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT_REF__COMP_REF = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Succ</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT_REF__SUCC = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Pred</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT_REF__PRED = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT_REF__LABEL = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Def</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT_REF__DEF = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the the '<em>Intentional Element Ref</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int INTENTIONAL_ELEMENT_REF_FEATURE_COUNT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 8;

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
     * The feature id for the '<em><b>Refs</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ELEMENT_LINK__REFS = 0;

    /**
     * The number of structural features of the the '<em>Element Link</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ELEMENT_LINK_FEATURE_COUNT = 1;

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
     * The feature id for the '<em><b>Refs</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTRIBUTION__REFS = ELEMENT_LINK__REFS;

    /**
     * The feature id for the '<em><b>Contibution</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTRIBUTION__CONTIBUTION = ELEMENT_LINK_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Correlation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTRIBUTION__CORRELATION = ELEMENT_LINK_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Src</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTRIBUTION__SRC = ELEMENT_LINK_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Dest</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTRIBUTION__DEST = ELEMENT_LINK_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the the '<em>Contribution</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONTRIBUTION_FEATURE_COUNT = ELEMENT_LINK_FEATURE_COUNT + 4;

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
    int LINK_REF__SOURCE = UrncorePackage.SPECIFICATION_CONNECTION__SOURCE;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK_REF__TARGET = UrncorePackage.SPECIFICATION_CONNECTION__TARGET;

    /**
     * The feature id for the '<em><b>Spec Diagram</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK_REF__SPEC_DIAGRAM = UrncorePackage.SPECIFICATION_CONNECTION__SPEC_DIAGRAM;

    /**
     * The feature id for the '<em><b>Beliefs</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK_REF__BELIEFS = UrncorePackage.SPECIFICATION_CONNECTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Link</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK_REF__LINK = UrncorePackage.SPECIFICATION_CONNECTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Dependency</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK_REF__DEPENDENCY = UrncorePackage.SPECIFICATION_CONNECTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the the '<em>Link Ref</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINK_REF_FEATURE_COUNT = UrncorePackage.SPECIFICATION_CONNECTION_FEATURE_COUNT + 3;

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
     * The feature id for the '<em><b>Refs</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DECOMPOSITION__REFS = ELEMENT_LINK__REFS;

    /**
     * The feature id for the '<em><b>Src</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DECOMPOSITION__SRC = ELEMENT_LINK_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Dest</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DECOMPOSITION__DEST = ELEMENT_LINK_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the the '<em>Decomposition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DECOMPOSITION_FEATURE_COUNT = ELEMENT_LINK_FEATURE_COUNT + 2;

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
     * The feature id for the '<em><b>Refs</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEPENDENCY__REFS = ELEMENT_LINK__REFS;

    /**
     * The feature id for the '<em><b>Depender</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEPENDENCY__DEPENDER = ELEMENT_LINK_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Dependum</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEPENDENCY__DEPENDUM = ELEMENT_LINK_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Dependee</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEPENDENCY__DEPENDEE = ELEMENT_LINK_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Second Refs</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEPENDENCY__SECOND_REFS = ELEMENT_LINK_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the the '<em>Dependency</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DEPENDENCY_FEATURE_COUNT = ELEMENT_LINK_FEATURE_COUNT + 4;

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
     * The feature id for the '<em><b>Set</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVALUATION__SET = 2;

    /**
     * The number of structural features of the the '<em>Evaluation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVALUATION_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link grl.impl.EvaluationSetImpl <em>Evaluation Set</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see grl.impl.EvaluationSetImpl
     * @see grl.impl.GrlPackageImpl#getEvaluationSet()
     * @generated
     */
    int EVALUATION_SET = 13;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVALUATION_SET__ID = UrncorePackage.GR_LMODEL_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVALUATION_SET__NAME = UrncorePackage.GR_LMODEL_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVALUATION_SET__DESCRIPTION = UrncorePackage.GR_LMODEL_ELEMENT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVALUATION_SET__URN_LINKS = UrncorePackage.GR_LMODEL_ELEMENT__URN_LINKS;

    /**
     * The feature id for the '<em><b>Evaluations</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVALUATION_SET__EVALUATIONS = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the the '<em>Evaluation Set</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EVALUATION_SET_FEATURE_COUNT = UrncorePackage.GR_LMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link grl.EvaluationLevel <em>Evaluation Level</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see grl.EvaluationLevel
     * @see grl.impl.GrlPackageImpl#getEvaluationLevel()
     * @generated
     */
    int EVALUATION_LEVEL = 14;

    /**
     * The meta object id for the '{@link grl.Criticality <em>Criticality</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see grl.Criticality
     * @see grl.impl.GrlPackageImpl#getCriticality()
     * @generated
     */
    int CRITICALITY = 15;

    /**
     * The meta object id for the '{@link grl.IntentionalElementType <em>Intentional Element Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see grl.IntentionalElementType
     * @see grl.impl.GrlPackageImpl#getIntentionalElementType()
     * @generated
     */
    int INTENTIONAL_ELEMENT_TYPE = 16;

    /**
     * The meta object id for the '{@link grl.Priority <em>Priority</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see grl.Priority
     * @see grl.impl.GrlPackageImpl#getPriority()
     * @generated
     */
    int PRIORITY = 17;

    /**
     * The meta object id for the '{@link grl.ContributionType <em>Contribution Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see grl.ContributionType
     * @see grl.impl.GrlPackageImpl#getContributionType()
     * @generated
     */
    int CONTRIBUTION_TYPE = 18;

    /**
     * The meta object id for the '{@link grl.DecompositionType <em>Decomposition Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see grl.DecompositionType
     * @see grl.impl.GrlPackageImpl#getDecompositionType()
     * @generated
     */
    int DECOMPOSITION_TYPE = 19;


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
     * Returns the meta object for class '{@link grl.Belief <em>Belief</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Belief</em>'.
     * @see grl.Belief
     * @generated
     */
    EClass getBelief();

    /**
     * Returns the meta object for the reference '{@link grl.Belief#getConnection <em>Connection</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Connection</em>'.
     * @see grl.Belief#getConnection()
     * @see #getBelief()
     * @generated
     */
    EReference getBelief_Connection();

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
     * Returns the meta object for the attribute '{@link grl.IntentionalElement#getCriticality <em>Criticality</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Criticality</em>'.
     * @see grl.IntentionalElement#getCriticality()
     * @see #getIntentionalElement()
     * @generated
     */
    EAttribute getIntentionalElement_Criticality();

    /**
     * Returns the meta object for the attribute '{@link grl.IntentionalElement#getPriority <em>Priority</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Priority</em>'.
     * @see grl.IntentionalElement#getPriority()
     * @see #getIntentionalElement()
     * @generated
     */
    EAttribute getIntentionalElement_Priority();

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
     * Returns the meta object for the reference list '{@link grl.IntentionalElement#getIsDepender <em>Is Depender</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Is Depender</em>'.
     * @see grl.IntentionalElement#getIsDepender()
     * @see #getIntentionalElement()
     * @generated
     */
    EReference getIntentionalElement_IsDepender();

    /**
     * Returns the meta object for the reference list '{@link grl.IntentionalElement#getDecompositionSrc <em>Decomposition Src</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Decomposition Src</em>'.
     * @see grl.IntentionalElement#getDecompositionSrc()
     * @see #getIntentionalElement()
     * @generated
     */
    EReference getIntentionalElement_DecompositionSrc();

    /**
     * Returns the meta object for the reference list '{@link grl.IntentionalElement#getDecompositionDest <em>Decomposition Dest</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Decomposition Dest</em>'.
     * @see grl.IntentionalElement#getDecompositionDest()
     * @see #getIntentionalElement()
     * @generated
     */
    EReference getIntentionalElement_DecompositionDest();

    /**
     * Returns the meta object for the reference list '{@link grl.IntentionalElement#getContributionSrc <em>Contribution Src</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Contribution Src</em>'.
     * @see grl.IntentionalElement#getContributionSrc()
     * @see #getIntentionalElement()
     * @generated
     */
    EReference getIntentionalElement_ContributionSrc();

    /**
     * Returns the meta object for the reference list '{@link grl.IntentionalElement#getContributionDest <em>Contribution Dest</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Contribution Dest</em>'.
     * @see grl.IntentionalElement#getContributionDest()
     * @see #getIntentionalElement()
     * @generated
     */
    EReference getIntentionalElement_ContributionDest();

    /**
     * Returns the meta object for the reference list '{@link grl.IntentionalElement#getIsDependum <em>Is Dependum</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Is Dependum</em>'.
     * @see grl.IntentionalElement#getIsDependum()
     * @see #getIntentionalElement()
     * @generated
     */
    EReference getIntentionalElement_IsDependum();

    /**
     * Returns the meta object for the reference list '{@link grl.IntentionalElement#getIsDependee <em>Is Dependee</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Is Dependee</em>'.
     * @see grl.IntentionalElement#getIsDependee()
     * @see #getIntentionalElement()
     * @generated
     */
    EReference getIntentionalElement_IsDependee();

    /**
     * Returns the meta object for the reference list '{@link grl.IntentionalElement#getEvals <em>Evals</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Evals</em>'.
     * @see grl.IntentionalElement#getEvals()
     * @see #getIntentionalElement()
     * @generated
     */
    EReference getIntentionalElement_Evals();

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
     * Returns the meta object for the attribute '{@link grl.Contribution#getContibution <em>Contibution</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Contibution</em>'.
     * @see grl.Contribution#getContibution()
     * @see #getContribution()
     * @generated
     */
    EAttribute getContribution_Contibution();

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
     * Returns the meta object for the reference '{@link grl.Contribution#getSrc <em>Src</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Src</em>'.
     * @see grl.Contribution#getSrc()
     * @see #getContribution()
     * @generated
     */
    EReference getContribution_Src();

    /**
     * Returns the meta object for the reference '{@link grl.Contribution#getDest <em>Dest</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Dest</em>'.
     * @see grl.Contribution#getDest()
     * @see #getContribution()
     * @generated
     */
    EReference getContribution_Dest();

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
     * Returns the meta object for the reference list '{@link grl.LinkRef#getBeliefs <em>Beliefs</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Beliefs</em>'.
     * @see grl.LinkRef#getBeliefs()
     * @see #getLinkRef()
     * @generated
     */
    EReference getLinkRef_Beliefs();

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
     * Returns the meta object for the reference '{@link grl.LinkRef#getDependency <em>Dependency</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Dependency</em>'.
     * @see grl.LinkRef#getDependency()
     * @see #getLinkRef()
     * @generated
     */
    EReference getLinkRef_Dependency();

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
     * Returns the meta object for class '{@link grl.Decomposition <em>Decomposition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Decomposition</em>'.
     * @see grl.Decomposition
     * @generated
     */
    EClass getDecomposition();

    /**
     * Returns the meta object for the reference '{@link grl.Decomposition#getSrc <em>Src</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Src</em>'.
     * @see grl.Decomposition#getSrc()
     * @see #getDecomposition()
     * @generated
     */
    EReference getDecomposition_Src();

    /**
     * Returns the meta object for the reference '{@link grl.Decomposition#getDest <em>Dest</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Dest</em>'.
     * @see grl.Decomposition#getDest()
     * @see #getDecomposition()
     * @generated
     */
    EReference getDecomposition_Dest();

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
     * Returns the meta object for the reference '{@link grl.Dependency#getDepender <em>Depender</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Depender</em>'.
     * @see grl.Dependency#getDepender()
     * @see #getDependency()
     * @generated
     */
    EReference getDependency_Depender();

    /**
     * Returns the meta object for the reference '{@link grl.Dependency#getDependum <em>Dependum</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Dependum</em>'.
     * @see grl.Dependency#getDependum()
     * @see #getDependency()
     * @generated
     */
    EReference getDependency_Dependum();

    /**
     * Returns the meta object for the reference '{@link grl.Dependency#getDependee <em>Dependee</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Dependee</em>'.
     * @see grl.Dependency#getDependee()
     * @see #getDependency()
     * @generated
     */
    EReference getDependency_Dependee();

    /**
     * Returns the meta object for the reference list '{@link grl.Dependency#getSecondRefs <em>Second Refs</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Second Refs</em>'.
     * @see grl.Dependency#getSecondRefs()
     * @see #getDependency()
     * @generated
     */
    EReference getDependency_SecondRefs();

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
     * Returns the meta object for the reference '{@link grl.Evaluation#getSet <em>Set</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Set</em>'.
     * @see grl.Evaluation#getSet()
     * @see #getEvaluation()
     * @generated
     */
    EReference getEvaluation_Set();

    /**
     * Returns the meta object for class '{@link grl.EvaluationSet <em>Evaluation Set</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Evaluation Set</em>'.
     * @see grl.EvaluationSet
     * @generated
     */
    EClass getEvaluationSet();

    /**
     * Returns the meta object for the reference list '{@link grl.EvaluationSet#getEvaluations <em>Evaluations</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Evaluations</em>'.
     * @see grl.EvaluationSet#getEvaluations()
     * @see #getEvaluationSet()
     * @generated
     */
    EReference getEvaluationSet_Evaluations();

    /**
     * Returns the meta object for enum '{@link grl.EvaluationLevel <em>Evaluation Level</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Evaluation Level</em>'.
     * @see grl.EvaluationLevel
     * @generated
     */
    EEnum getEvaluationLevel();

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

} //GrlPackage
