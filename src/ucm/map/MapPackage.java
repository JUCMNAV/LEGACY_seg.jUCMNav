/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

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
 * @see ucm.map.MapFactory
 * @generated
 */
public interface MapPackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "map";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http:///ucm/map.ecore";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "ucm.map";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    MapPackage eINSTANCE = ucm.map.impl.MapPackageImpl.init();

    /**
     * The meta object id for the '{@link ucm.map.impl.PathNodeImpl <em>Path Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.PathNodeImpl
     * @see ucm.map.impl.MapPackageImpl#getPathNode()
     * @generated
     */
    int PATH_NODE = 11;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATH_NODE__URN_LINKS = UrncorePackage.UC_MMODEL_ELEMENT__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATH_NODE__ID = UrncorePackage.UC_MMODEL_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATH_NODE__NAME = UrncorePackage.UC_MMODEL_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATH_NODE__DESCRIPTION = UrncorePackage.UC_MMODEL_ELEMENT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATH_NODE__X = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATH_NODE__Y = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Succ</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATH_NODE__SUCC = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Pred</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATH_NODE__PRED = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Comp Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATH_NODE__COMP_REF = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATH_NODE__LABEL = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The number of structural features of the the '<em>Path Node</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATH_NODE_FEATURE_COUNT = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The meta object id for the '{@link ucm.map.impl.AndJoinImpl <em>And Join</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.AndJoinImpl
     * @see ucm.map.impl.MapPackageImpl#getAndJoin()
     * @generated
     */
    int AND_JOIN = 0;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_JOIN__URN_LINKS = PATH_NODE__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_JOIN__ID = PATH_NODE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_JOIN__NAME = PATH_NODE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_JOIN__DESCRIPTION = PATH_NODE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_JOIN__X = PATH_NODE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_JOIN__Y = PATH_NODE__Y;

    /**
     * The feature id for the '<em><b>Succ</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_JOIN__SUCC = PATH_NODE__SUCC;

    /**
     * The feature id for the '<em><b>Pred</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_JOIN__PRED = PATH_NODE__PRED;

    /**
     * The feature id for the '<em><b>Comp Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_JOIN__COMP_REF = PATH_NODE__COMP_REF;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_JOIN__LABEL = PATH_NODE__LABEL;

    /**
     * The feature id for the '<em><b>Orientation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_JOIN__ORIENTATION = PATH_NODE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the the '<em>And Join</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_JOIN_FEATURE_COUNT = PATH_NODE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ucm.map.impl.OutBindingImpl <em>Out Binding</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.OutBindingImpl
     * @see ucm.map.impl.MapPackageImpl#getOutBinding()
     * @generated
     */
    int OUT_BINDING = 1;

    /**
     * The feature id for the '<em><b>End Point</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OUT_BINDING__END_POINT = 0;

    /**
     * The feature id for the '<em><b>Stub Exit</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OUT_BINDING__STUB_EXIT = 1;

    /**
     * The number of structural features of the the '<em>Out Binding</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OUT_BINDING_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link ucm.map.impl.InBindingImpl <em>In Binding</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.InBindingImpl
     * @see ucm.map.impl.MapPackageImpl#getInBinding()
     * @generated
     */
    int IN_BINDING = 2;

    /**
     * The feature id for the '<em><b>Start Point</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IN_BINDING__START_POINT = 0;

    /**
     * The feature id for the '<em><b>Stub Entry</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IN_BINDING__STUB_ENTRY = 1;

    /**
     * The number of structural features of the the '<em>In Binding</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IN_BINDING_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link ucm.map.impl.RespRefImpl <em>Resp Ref</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.RespRefImpl
     * @see ucm.map.impl.MapPackageImpl#getRespRef()
     * @generated
     */
    int RESP_REF = 3;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RESP_REF__URN_LINKS = PATH_NODE__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RESP_REF__ID = PATH_NODE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RESP_REF__NAME = PATH_NODE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RESP_REF__DESCRIPTION = PATH_NODE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RESP_REF__X = PATH_NODE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RESP_REF__Y = PATH_NODE__Y;

    /**
     * The feature id for the '<em><b>Succ</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RESP_REF__SUCC = PATH_NODE__SUCC;

    /**
     * The feature id for the '<em><b>Pred</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RESP_REF__PRED = PATH_NODE__PRED;

    /**
     * The feature id for the '<em><b>Comp Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RESP_REF__COMP_REF = PATH_NODE__COMP_REF;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RESP_REF__LABEL = PATH_NODE__LABEL;

    /**
     * The feature id for the '<em><b>Repetition Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RESP_REF__REPETITION_COUNT = PATH_NODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Resp Def</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RESP_REF__RESP_DEF = PATH_NODE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the the '<em>Resp Ref</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RESP_REF_FEATURE_COUNT = PATH_NODE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link ucm.map.impl.LoopImpl <em>Loop</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.LoopImpl
     * @see ucm.map.impl.MapPackageImpl#getLoop()
     * @generated
     */
    int LOOP = 4;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LOOP__URN_LINKS = PATH_NODE__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LOOP__ID = PATH_NODE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LOOP__NAME = PATH_NODE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LOOP__DESCRIPTION = PATH_NODE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LOOP__X = PATH_NODE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LOOP__Y = PATH_NODE__Y;

    /**
     * The feature id for the '<em><b>Succ</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LOOP__SUCC = PATH_NODE__SUCC;

    /**
     * The feature id for the '<em><b>Pred</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LOOP__PRED = PATH_NODE__PRED;

    /**
     * The feature id for the '<em><b>Comp Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LOOP__COMP_REF = PATH_NODE__COMP_REF;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LOOP__LABEL = PATH_NODE__LABEL;

    /**
     * The feature id for the '<em><b>Orientation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LOOP__ORIENTATION = PATH_NODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Exit Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LOOP__EXIT_CONDITION = PATH_NODE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the the '<em>Loop</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LOOP_FEATURE_COUNT = PATH_NODE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link ucm.map.impl.OrJoinImpl <em>Or Join</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.OrJoinImpl
     * @see ucm.map.impl.MapPackageImpl#getOrJoin()
     * @generated
     */
    int OR_JOIN = 5;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_JOIN__URN_LINKS = PATH_NODE__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_JOIN__ID = PATH_NODE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_JOIN__NAME = PATH_NODE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_JOIN__DESCRIPTION = PATH_NODE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_JOIN__X = PATH_NODE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_JOIN__Y = PATH_NODE__Y;

    /**
     * The feature id for the '<em><b>Succ</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_JOIN__SUCC = PATH_NODE__SUCC;

    /**
     * The feature id for the '<em><b>Pred</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_JOIN__PRED = PATH_NODE__PRED;

    /**
     * The feature id for the '<em><b>Comp Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_JOIN__COMP_REF = PATH_NODE__COMP_REF;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_JOIN__LABEL = PATH_NODE__LABEL;

    /**
     * The feature id for the '<em><b>Orientation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_JOIN__ORIENTATION = PATH_NODE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the the '<em>Or Join</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_JOIN_FEATURE_COUNT = PATH_NODE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ucm.map.impl.OrForkImpl <em>Or Fork</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.OrForkImpl
     * @see ucm.map.impl.MapPackageImpl#getOrFork()
     * @generated
     */
    int OR_FORK = 6;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_FORK__URN_LINKS = PATH_NODE__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_FORK__ID = PATH_NODE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_FORK__NAME = PATH_NODE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_FORK__DESCRIPTION = PATH_NODE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_FORK__X = PATH_NODE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_FORK__Y = PATH_NODE__Y;

    /**
     * The feature id for the '<em><b>Succ</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_FORK__SUCC = PATH_NODE__SUCC;

    /**
     * The feature id for the '<em><b>Pred</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_FORK__PRED = PATH_NODE__PRED;

    /**
     * The feature id for the '<em><b>Comp Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_FORK__COMP_REF = PATH_NODE__COMP_REF;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_FORK__LABEL = PATH_NODE__LABEL;

    /**
     * The feature id for the '<em><b>Orientation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_FORK__ORIENTATION = PATH_NODE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the the '<em>Or Fork</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int OR_FORK_FEATURE_COUNT = PATH_NODE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ucm.map.impl.ConnectImpl <em>Connect</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.ConnectImpl
     * @see ucm.map.impl.MapPackageImpl#getConnect()
     * @generated
     */
    int CONNECT = 7;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECT__URN_LINKS = PATH_NODE__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECT__ID = PATH_NODE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECT__NAME = PATH_NODE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECT__DESCRIPTION = PATH_NODE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECT__X = PATH_NODE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECT__Y = PATH_NODE__Y;

    /**
     * The feature id for the '<em><b>Succ</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECT__SUCC = PATH_NODE__SUCC;

    /**
     * The feature id for the '<em><b>Pred</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECT__PRED = PATH_NODE__PRED;

    /**
     * The feature id for the '<em><b>Comp Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECT__COMP_REF = PATH_NODE__COMP_REF;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECT__LABEL = PATH_NODE__LABEL;

    /**
     * The number of structural features of the the '<em>Connect</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONNECT_FEATURE_COUNT = PATH_NODE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ucm.map.impl.NodeConnectionImpl <em>Node Connection</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.NodeConnectionImpl
     * @see ucm.map.impl.MapPackageImpl#getNodeConnection()
     * @generated
     */
    int NODE_CONNECTION = 8;

    /**
     * The feature id for the '<em><b>Probability</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_CONNECTION__PROBABILITY = 0;

    /**
     * The feature id for the '<em><b>Source</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_CONNECTION__SOURCE = 1;

    /**
     * The feature id for the '<em><b>Target</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_CONNECTION__TARGET = 2;

    /**
     * The feature id for the '<em><b>In Bindings</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_CONNECTION__IN_BINDINGS = 3;

    /**
     * The feature id for the '<em><b>Out Bindings</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_CONNECTION__OUT_BINDINGS = 4;

    /**
     * The feature id for the '<em><b>Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_CONNECTION__CONDITION = 5;

    /**
     * The number of structural features of the the '<em>Node Connection</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NODE_CONNECTION_FEATURE_COUNT = 6;

    /**
     * The meta object id for the '{@link ucm.map.impl.WaitingPlaceImpl <em>Waiting Place</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.WaitingPlaceImpl
     * @see ucm.map.impl.MapPackageImpl#getWaitingPlace()
     * @generated
     */
    int WAITING_PLACE = 9;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WAITING_PLACE__URN_LINKS = PATH_NODE__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WAITING_PLACE__ID = PATH_NODE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WAITING_PLACE__NAME = PATH_NODE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WAITING_PLACE__DESCRIPTION = PATH_NODE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WAITING_PLACE__X = PATH_NODE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WAITING_PLACE__Y = PATH_NODE__Y;

    /**
     * The feature id for the '<em><b>Succ</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WAITING_PLACE__SUCC = PATH_NODE__SUCC;

    /**
     * The feature id for the '<em><b>Pred</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WAITING_PLACE__PRED = PATH_NODE__PRED;

    /**
     * The feature id for the '<em><b>Comp Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WAITING_PLACE__COMP_REF = PATH_NODE__COMP_REF;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WAITING_PLACE__LABEL = PATH_NODE__LABEL;

    /**
     * The feature id for the '<em><b>Wait Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WAITING_PLACE__WAIT_TYPE = PATH_NODE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the the '<em>Waiting Place</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int WAITING_PLACE_FEATURE_COUNT = PATH_NODE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ucm.map.impl.StubImpl <em>Stub</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.StubImpl
     * @see ucm.map.impl.MapPackageImpl#getStub()
     * @generated
     */
    int STUB = 10;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STUB__URN_LINKS = PATH_NODE__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STUB__ID = PATH_NODE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STUB__NAME = PATH_NODE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STUB__DESCRIPTION = PATH_NODE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STUB__X = PATH_NODE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STUB__Y = PATH_NODE__Y;

    /**
     * The feature id for the '<em><b>Succ</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STUB__SUCC = PATH_NODE__SUCC;

    /**
     * The feature id for the '<em><b>Pred</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STUB__PRED = PATH_NODE__PRED;

    /**
     * The feature id for the '<em><b>Comp Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STUB__COMP_REF = PATH_NODE__COMP_REF;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STUB__LABEL = PATH_NODE__LABEL;

    /**
     * The feature id for the '<em><b>Dynamic</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STUB__DYNAMIC = PATH_NODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Shared</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STUB__SHARED = PATH_NODE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Bindings</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STUB__BINDINGS = PATH_NODE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the the '<em>Stub</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int STUB_FEATURE_COUNT = PATH_NODE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link ucm.map.impl.EndPointImpl <em>End Point</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.EndPointImpl
     * @see ucm.map.impl.MapPackageImpl#getEndPoint()
     * @generated
     */
    int END_POINT = 12;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int END_POINT__URN_LINKS = PATH_NODE__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int END_POINT__ID = PATH_NODE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int END_POINT__NAME = PATH_NODE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int END_POINT__DESCRIPTION = PATH_NODE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int END_POINT__X = PATH_NODE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int END_POINT__Y = PATH_NODE__Y;

    /**
     * The feature id for the '<em><b>Succ</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int END_POINT__SUCC = PATH_NODE__SUCC;

    /**
     * The feature id for the '<em><b>Pred</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int END_POINT__PRED = PATH_NODE__PRED;

    /**
     * The feature id for the '<em><b>Comp Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int END_POINT__COMP_REF = PATH_NODE__COMP_REF;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int END_POINT__LABEL = PATH_NODE__LABEL;

    /**
     * The feature id for the '<em><b>Out Bindings</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int END_POINT__OUT_BINDINGS = PATH_NODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Postcondition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int END_POINT__POSTCONDITION = PATH_NODE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the the '<em>End Point</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int END_POINT_FEATURE_COUNT = PATH_NODE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link ucm.map.impl.StartPointImpl <em>Start Point</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.StartPointImpl
     * @see ucm.map.impl.MapPackageImpl#getStartPoint()
     * @generated
     */
    int START_POINT = 13;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int START_POINT__URN_LINKS = PATH_NODE__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int START_POINT__ID = PATH_NODE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int START_POINT__NAME = PATH_NODE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int START_POINT__DESCRIPTION = PATH_NODE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int START_POINT__X = PATH_NODE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int START_POINT__Y = PATH_NODE__Y;

    /**
     * The feature id for the '<em><b>Succ</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int START_POINT__SUCC = PATH_NODE__SUCC;

    /**
     * The feature id for the '<em><b>Pred</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int START_POINT__PRED = PATH_NODE__PRED;

    /**
     * The feature id for the '<em><b>Comp Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int START_POINT__COMP_REF = PATH_NODE__COMP_REF;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int START_POINT__LABEL = PATH_NODE__LABEL;

    /**
     * The feature id for the '<em><b>Workload</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int START_POINT__WORKLOAD = PATH_NODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>In Bindings</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int START_POINT__IN_BINDINGS = PATH_NODE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int START_POINT__PRECONDITION = PATH_NODE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the the '<em>Start Point</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int START_POINT_FEATURE_COUNT = PATH_NODE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link ucm.map.impl.MapImpl <em>Map</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.MapImpl
     * @see ucm.map.impl.MapPackageImpl#getMap()
     * @generated
     */
    int MAP = 14;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MAP__URN_LINKS = UrncorePackage.UC_MMODEL_ELEMENT__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MAP__ID = UrncorePackage.UC_MMODEL_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MAP__NAME = UrncorePackage.UC_MMODEL_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MAP__DESCRIPTION = UrncorePackage.UC_MMODEL_ELEMENT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Path Graph</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MAP__PATH_GRAPH = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Comp Refs</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MAP__COMP_REFS = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Parent Stub</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MAP__PARENT_STUB = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the the '<em>Map</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int MAP_FEATURE_COUNT = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link ucm.map.impl.PathGraphImpl <em>Path Graph</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.PathGraphImpl
     * @see ucm.map.impl.MapPackageImpl#getPathGraph()
     * @generated
     */
    int PATH_GRAPH = 15;

    /**
     * The feature id for the '<em><b>Path Nodes</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATH_GRAPH__PATH_NODES = 0;

    /**
     * The feature id for the '<em><b>Node Connections</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATH_GRAPH__NODE_CONNECTIONS = 1;

    /**
     * The number of structural features of the the '<em>Path Graph</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PATH_GRAPH_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link ucm.map.impl.AbortImpl <em>Abort</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.AbortImpl
     * @see ucm.map.impl.MapPackageImpl#getAbort()
     * @generated
     */
    int ABORT = 16;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABORT__URN_LINKS = PATH_NODE__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABORT__ID = PATH_NODE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABORT__NAME = PATH_NODE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABORT__DESCRIPTION = PATH_NODE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABORT__X = PATH_NODE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABORT__Y = PATH_NODE__Y;

    /**
     * The feature id for the '<em><b>Succ</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABORT__SUCC = PATH_NODE__SUCC;

    /**
     * The feature id for the '<em><b>Pred</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABORT__PRED = PATH_NODE__PRED;

    /**
     * The feature id for the '<em><b>Comp Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABORT__COMP_REF = PATH_NODE__COMP_REF;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABORT__LABEL = PATH_NODE__LABEL;

    /**
     * The feature id for the '<em><b>Condition</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABORT__CONDITION = PATH_NODE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the the '<em>Abort</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int ABORT_FEATURE_COUNT = PATH_NODE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ucm.map.impl.PluginBindingImpl <em>Plugin Binding</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.PluginBindingImpl
     * @see ucm.map.impl.MapPackageImpl#getPluginBinding()
     * @generated
     */
    int PLUGIN_BINDING = 17;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLUGIN_BINDING__ID = 0;

    /**
     * The feature id for the '<em><b>Repetition Count</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLUGIN_BINDING__REPETITION_COUNT = 1;

    /**
     * The feature id for the '<em><b>Probability</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLUGIN_BINDING__PROBABILITY = 2;

    /**
     * The feature id for the '<em><b>In</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLUGIN_BINDING__IN = 3;

    /**
     * The feature id for the '<em><b>Out</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLUGIN_BINDING__OUT = 4;

    /**
     * The feature id for the '<em><b>Plugin</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLUGIN_BINDING__PLUGIN = 5;

    /**
     * The feature id for the '<em><b>Precondition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLUGIN_BINDING__PRECONDITION = 6;

    /**
     * The number of structural features of the the '<em>Plugin Binding</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int PLUGIN_BINDING_FEATURE_COUNT = 7;

    /**
     * The meta object id for the '{@link ucm.map.impl.ComponentRefImpl <em>Component Ref</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.ComponentRefImpl
     * @see ucm.map.impl.MapPackageImpl#getComponentRef()
     * @generated
     */
    int COMPONENT_REF = 18;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_REF__URN_LINKS = UrncorePackage.UC_MMODEL_ELEMENT__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_REF__ID = UrncorePackage.UC_MMODEL_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_REF__NAME = UrncorePackage.UC_MMODEL_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_REF__DESCRIPTION = UrncorePackage.UC_MMODEL_ELEMENT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Role</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_REF__ROLE = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Replication Factor</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_REF__REPLICATION_FACTOR = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Anchored</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_REF__ANCHORED = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Fixed</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_REF__FIXED = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_REF__X = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_REF__Y = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Width</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_REF__WIDTH = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Height</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_REF__HEIGHT = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Comp Def</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_REF__COMP_DEF = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Children</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_REF__CHILDREN = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Parent</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_REF__PARENT = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Path Nodes</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_REF__PATH_NODES = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 11;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_REF__LABEL = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 12;

    /**
     * The number of structural features of the the '<em>Component Ref</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int COMPONENT_REF_FEATURE_COUNT = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 13;

    /**
     * The meta object id for the '{@link ucm.map.impl.TimerImpl <em>Timer</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.TimerImpl
     * @see ucm.map.impl.MapPackageImpl#getTimer()
     * @generated
     */
    int TIMER = 19;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TIMER__URN_LINKS = WAITING_PLACE__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TIMER__ID = WAITING_PLACE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TIMER__NAME = WAITING_PLACE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TIMER__DESCRIPTION = WAITING_PLACE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TIMER__X = WAITING_PLACE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TIMER__Y = WAITING_PLACE__Y;

    /**
     * The feature id for the '<em><b>Succ</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TIMER__SUCC = WAITING_PLACE__SUCC;

    /**
     * The feature id for the '<em><b>Pred</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TIMER__PRED = WAITING_PLACE__PRED;

    /**
     * The feature id for the '<em><b>Comp Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TIMER__COMP_REF = WAITING_PLACE__COMP_REF;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TIMER__LABEL = WAITING_PLACE__LABEL;

    /**
     * The feature id for the '<em><b>Wait Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TIMER__WAIT_TYPE = WAITING_PLACE__WAIT_TYPE;

    /**
     * The feature id for the '<em><b>Timeout Condition</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TIMER__TIMEOUT_CONDITION = WAITING_PLACE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Timeout Path</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TIMER__TIMEOUT_PATH = WAITING_PLACE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Timer Var</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TIMER__TIMER_VAR = WAITING_PLACE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the the '<em>Timer</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int TIMER_FEATURE_COUNT = WAITING_PLACE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link ucm.map.impl.AndForkImpl <em>And Fork</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.AndForkImpl
     * @see ucm.map.impl.MapPackageImpl#getAndFork()
     * @generated
     */
    int AND_FORK = 20;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_FORK__URN_LINKS = PATH_NODE__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_FORK__ID = PATH_NODE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_FORK__NAME = PATH_NODE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_FORK__DESCRIPTION = PATH_NODE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_FORK__X = PATH_NODE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_FORK__Y = PATH_NODE__Y;

    /**
     * The feature id for the '<em><b>Succ</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_FORK__SUCC = PATH_NODE__SUCC;

    /**
     * The feature id for the '<em><b>Pred</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_FORK__PRED = PATH_NODE__PRED;

    /**
     * The feature id for the '<em><b>Comp Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_FORK__COMP_REF = PATH_NODE__COMP_REF;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_FORK__LABEL = PATH_NODE__LABEL;

    /**
     * The feature id for the '<em><b>Orientation</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_FORK__ORIENTATION = PATH_NODE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the the '<em>And Fork</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int AND_FORK_FEATURE_COUNT = PATH_NODE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ucm.map.impl.EmptyPointImpl <em>Empty Point</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.EmptyPointImpl
     * @see ucm.map.impl.MapPackageImpl#getEmptyPoint()
     * @generated
     */
    int EMPTY_POINT = 21;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EMPTY_POINT__URN_LINKS = PATH_NODE__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EMPTY_POINT__ID = PATH_NODE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EMPTY_POINT__NAME = PATH_NODE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EMPTY_POINT__DESCRIPTION = PATH_NODE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EMPTY_POINT__X = PATH_NODE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EMPTY_POINT__Y = PATH_NODE__Y;

    /**
     * The feature id for the '<em><b>Succ</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EMPTY_POINT__SUCC = PATH_NODE__SUCC;

    /**
     * The feature id for the '<em><b>Pred</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EMPTY_POINT__PRED = PATH_NODE__PRED;

    /**
     * The feature id for the '<em><b>Comp Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EMPTY_POINT__COMP_REF = PATH_NODE__COMP_REF;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EMPTY_POINT__LABEL = PATH_NODE__LABEL;

    /**
     * The number of structural features of the the '<em>Empty Point</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EMPTY_POINT_FEATURE_COUNT = PATH_NODE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ucm.map.impl.FailurePointImpl <em>Failure Point</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.FailurePointImpl
     * @see ucm.map.impl.MapPackageImpl#getFailurePoint()
     * @generated
     */
    int FAILURE_POINT = 22;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FAILURE_POINT__URN_LINKS = PATH_NODE__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FAILURE_POINT__ID = PATH_NODE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FAILURE_POINT__NAME = PATH_NODE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FAILURE_POINT__DESCRIPTION = PATH_NODE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FAILURE_POINT__X = PATH_NODE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FAILURE_POINT__Y = PATH_NODE__Y;

    /**
     * The feature id for the '<em><b>Succ</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FAILURE_POINT__SUCC = PATH_NODE__SUCC;

    /**
     * The feature id for the '<em><b>Pred</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FAILURE_POINT__PRED = PATH_NODE__PRED;

    /**
     * The feature id for the '<em><b>Comp Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FAILURE_POINT__COMP_REF = PATH_NODE__COMP_REF;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FAILURE_POINT__LABEL = PATH_NODE__LABEL;

    /**
     * The number of structural features of the the '<em>Failure Point</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FAILURE_POINT_FEATURE_COUNT = PATH_NODE_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '{@link ucm.map.impl.ConditionImpl <em>Condition</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.ConditionImpl
     * @see ucm.map.impl.MapPackageImpl#getCondition()
     * @generated
     */
    int CONDITION = 23;

    /**
     * The feature id for the '<em><b>Label</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONDITION__LABEL = 0;

    /**
     * The feature id for the '<em><b>Expression</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONDITION__EXPRESSION = 1;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONDITION__DESCRIPTION = 2;

    /**
     * The feature id for the '<em><b>Label X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONDITION__LABEL_X = 3;

    /**
     * The feature id for the '<em><b>Label Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONDITION__LABEL_Y = 4;

    /**
     * The feature id for the '<em><b>Variables</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONDITION__VARIABLES = 5;

    /**
     * The number of structural features of the the '<em>Condition</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int CONDITION_FEATURE_COUNT = 6;

    /**
     * The meta object id for the '{@link ucm.map.impl.DirectionArrowImpl <em>Direction Arrow</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see ucm.map.impl.DirectionArrowImpl
     * @see ucm.map.impl.MapPackageImpl#getDirectionArrow()
     * @generated
     */
    int DIRECTION_ARROW = 24;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIRECTION_ARROW__URN_LINKS = PATH_NODE__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIRECTION_ARROW__ID = PATH_NODE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIRECTION_ARROW__NAME = PATH_NODE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIRECTION_ARROW__DESCRIPTION = PATH_NODE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIRECTION_ARROW__X = PATH_NODE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIRECTION_ARROW__Y = PATH_NODE__Y;

    /**
     * The feature id for the '<em><b>Succ</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIRECTION_ARROW__SUCC = PATH_NODE__SUCC;

    /**
     * The feature id for the '<em><b>Pred</b></em>' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIRECTION_ARROW__PRED = PATH_NODE__PRED;

    /**
     * The feature id for the '<em><b>Comp Ref</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIRECTION_ARROW__COMP_REF = PATH_NODE__COMP_REF;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIRECTION_ARROW__LABEL = PATH_NODE__LABEL;

    /**
     * The number of structural features of the the '<em>Direction Arrow</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int DIRECTION_ARROW_FEATURE_COUNT = PATH_NODE_FEATURE_COUNT + 0;


    /**
     * Returns the meta object for class '{@link ucm.map.AndJoin <em>And Join</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>And Join</em>'.
     * @see ucm.map.AndJoin
     * @generated
     */
    EClass getAndJoin();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.AndJoin#getOrientation <em>Orientation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Orientation</em>'.
     * @see ucm.map.AndJoin#getOrientation()
     * @see #getAndJoin()
     * @generated
     */
    EAttribute getAndJoin_Orientation();

    /**
     * Returns the meta object for class '{@link ucm.map.OutBinding <em>Out Binding</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Out Binding</em>'.
     * @see ucm.map.OutBinding
     * @generated
     */
    EClass getOutBinding();

    /**
     * Returns the meta object for the reference '{@link ucm.map.OutBinding#getEndPoint <em>End Point</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>End Point</em>'.
     * @see ucm.map.OutBinding#getEndPoint()
     * @see #getOutBinding()
     * @generated
     */
    EReference getOutBinding_EndPoint();

    /**
     * Returns the meta object for the reference '{@link ucm.map.OutBinding#getStubExit <em>Stub Exit</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Stub Exit</em>'.
     * @see ucm.map.OutBinding#getStubExit()
     * @see #getOutBinding()
     * @generated
     */
    EReference getOutBinding_StubExit();

    /**
     * Returns the meta object for class '{@link ucm.map.InBinding <em>In Binding</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>In Binding</em>'.
     * @see ucm.map.InBinding
     * @generated
     */
    EClass getInBinding();

    /**
     * Returns the meta object for the reference '{@link ucm.map.InBinding#getStartPoint <em>Start Point</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Start Point</em>'.
     * @see ucm.map.InBinding#getStartPoint()
     * @see #getInBinding()
     * @generated
     */
    EReference getInBinding_StartPoint();

    /**
     * Returns the meta object for the reference '{@link ucm.map.InBinding#getStubEntry <em>Stub Entry</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Stub Entry</em>'.
     * @see ucm.map.InBinding#getStubEntry()
     * @see #getInBinding()
     * @generated
     */
    EReference getInBinding_StubEntry();

    /**
     * Returns the meta object for class '{@link ucm.map.RespRef <em>Resp Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Resp Ref</em>'.
     * @see ucm.map.RespRef
     * @generated
     */
    EClass getRespRef();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.RespRef#getRepetitionCount <em>Repetition Count</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Repetition Count</em>'.
     * @see ucm.map.RespRef#getRepetitionCount()
     * @see #getRespRef()
     * @generated
     */
    EAttribute getRespRef_RepetitionCount();

    /**
     * Returns the meta object for the reference '{@link ucm.map.RespRef#getRespDef <em>Resp Def</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Resp Def</em>'.
     * @see ucm.map.RespRef#getRespDef()
     * @see #getRespRef()
     * @generated
     */
    EReference getRespRef_RespDef();

    /**
     * Returns the meta object for class '{@link ucm.map.Loop <em>Loop</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Loop</em>'.
     * @see ucm.map.Loop
     * @generated
     */
    EClass getLoop();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.Loop#getOrientation <em>Orientation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Orientation</em>'.
     * @see ucm.map.Loop#getOrientation()
     * @see #getLoop()
     * @generated
     */
    EAttribute getLoop_Orientation();

    /**
     * Returns the meta object for the containment reference '{@link ucm.map.Loop#getExitCondition <em>Exit Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Exit Condition</em>'.
     * @see ucm.map.Loop#getExitCondition()
     * @see #getLoop()
     * @generated
     */
    EReference getLoop_ExitCondition();

    /**
     * Returns the meta object for class '{@link ucm.map.OrJoin <em>Or Join</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Or Join</em>'.
     * @see ucm.map.OrJoin
     * @generated
     */
    EClass getOrJoin();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.OrJoin#getOrientation <em>Orientation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Orientation</em>'.
     * @see ucm.map.OrJoin#getOrientation()
     * @see #getOrJoin()
     * @generated
     */
    EAttribute getOrJoin_Orientation();

    /**
     * Returns the meta object for class '{@link ucm.map.OrFork <em>Or Fork</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Or Fork</em>'.
     * @see ucm.map.OrFork
     * @generated
     */
    EClass getOrFork();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.OrFork#getOrientation <em>Orientation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Orientation</em>'.
     * @see ucm.map.OrFork#getOrientation()
     * @see #getOrFork()
     * @generated
     */
    EAttribute getOrFork_Orientation();

    /**
     * Returns the meta object for class '{@link ucm.map.Connect <em>Connect</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Connect</em>'.
     * @see ucm.map.Connect
     * @generated
     */
    EClass getConnect();

    /**
     * Returns the meta object for class '{@link ucm.map.NodeConnection <em>Node Connection</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Node Connection</em>'.
     * @see ucm.map.NodeConnection
     * @generated
     */
    EClass getNodeConnection();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.NodeConnection#getProbability <em>Probability</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Probability</em>'.
     * @see ucm.map.NodeConnection#getProbability()
     * @see #getNodeConnection()
     * @generated
     */
    EAttribute getNodeConnection_Probability();

    /**
     * Returns the meta object for the reference '{@link ucm.map.NodeConnection#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Source</em>'.
     * @see ucm.map.NodeConnection#getSource()
     * @see #getNodeConnection()
     * @generated
     */
    EReference getNodeConnection_Source();

    /**
     * Returns the meta object for the reference '{@link ucm.map.NodeConnection#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Target</em>'.
     * @see ucm.map.NodeConnection#getTarget()
     * @see #getNodeConnection()
     * @generated
     */
    EReference getNodeConnection_Target();

    /**
     * Returns the meta object for the reference list '{@link ucm.map.NodeConnection#getInBindings <em>In Bindings</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>In Bindings</em>'.
     * @see ucm.map.NodeConnection#getInBindings()
     * @see #getNodeConnection()
     * @generated
     */
    EReference getNodeConnection_InBindings();

    /**
     * Returns the meta object for the reference list '{@link ucm.map.NodeConnection#getOutBindings <em>Out Bindings</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Out Bindings</em>'.
     * @see ucm.map.NodeConnection#getOutBindings()
     * @see #getNodeConnection()
     * @generated
     */
    EReference getNodeConnection_OutBindings();

    /**
     * Returns the meta object for the containment reference '{@link ucm.map.NodeConnection#getCondition <em>Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Condition</em>'.
     * @see ucm.map.NodeConnection#getCondition()
     * @see #getNodeConnection()
     * @generated
     */
    EReference getNodeConnection_Condition();

    /**
     * Returns the meta object for class '{@link ucm.map.WaitingPlace <em>Waiting Place</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Waiting Place</em>'.
     * @see ucm.map.WaitingPlace
     * @generated
     */
    EClass getWaitingPlace();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.WaitingPlace#getWaitType <em>Wait Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Wait Type</em>'.
     * @see ucm.map.WaitingPlace#getWaitType()
     * @see #getWaitingPlace()
     * @generated
     */
    EAttribute getWaitingPlace_WaitType();

    /**
     * Returns the meta object for class '{@link ucm.map.Stub <em>Stub</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Stub</em>'.
     * @see ucm.map.Stub
     * @generated
     */
    EClass getStub();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.Stub#isDynamic <em>Dynamic</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Dynamic</em>'.
     * @see ucm.map.Stub#isDynamic()
     * @see #getStub()
     * @generated
     */
    EAttribute getStub_Dynamic();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.Stub#isShared <em>Shared</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Shared</em>'.
     * @see ucm.map.Stub#isShared()
     * @see #getStub()
     * @generated
     */
    EAttribute getStub_Shared();

    /**
     * Returns the meta object for the containment reference list '{@link ucm.map.Stub#getBindings <em>Bindings</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Bindings</em>'.
     * @see ucm.map.Stub#getBindings()
     * @see #getStub()
     * @generated
     */
    EReference getStub_Bindings();

    /**
     * Returns the meta object for class '{@link ucm.map.PathNode <em>Path Node</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Path Node</em>'.
     * @see ucm.map.PathNode
     * @generated
     */
    EClass getPathNode();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.PathNode#getX <em>X</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>X</em>'.
     * @see ucm.map.PathNode#getX()
     * @see #getPathNode()
     * @generated
     */
    EAttribute getPathNode_X();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.PathNode#getY <em>Y</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see ucm.map.PathNode#getY()
     * @see #getPathNode()
     * @generated
     */
    EAttribute getPathNode_Y();

    /**
     * Returns the meta object for the reference list '{@link ucm.map.PathNode#getSucc <em>Succ</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Succ</em>'.
     * @see ucm.map.PathNode#getSucc()
     * @see #getPathNode()
     * @generated
     */
    EReference getPathNode_Succ();

    /**
     * Returns the meta object for the reference list '{@link ucm.map.PathNode#getPred <em>Pred</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Pred</em>'.
     * @see ucm.map.PathNode#getPred()
     * @see #getPathNode()
     * @generated
     */
    EReference getPathNode_Pred();

    /**
     * Returns the meta object for the reference '{@link ucm.map.PathNode#getCompRef <em>Comp Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Comp Ref</em>'.
     * @see ucm.map.PathNode#getCompRef()
     * @see #getPathNode()
     * @generated
     */
    EReference getPathNode_CompRef();

    /**
     * Returns the meta object for the containment reference '{@link ucm.map.PathNode#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Label</em>'.
     * @see ucm.map.PathNode#getLabel()
     * @see #getPathNode()
     * @generated
     */
    EReference getPathNode_Label();

    /**
     * Returns the meta object for class '{@link ucm.map.EndPoint <em>End Point</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>End Point</em>'.
     * @see ucm.map.EndPoint
     * @generated
     */
    EClass getEndPoint();

    /**
     * Returns the meta object for the reference list '{@link ucm.map.EndPoint#getOutBindings <em>Out Bindings</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Out Bindings</em>'.
     * @see ucm.map.EndPoint#getOutBindings()
     * @see #getEndPoint()
     * @generated
     */
    EReference getEndPoint_OutBindings();

    /**
     * Returns the meta object for the containment reference '{@link ucm.map.EndPoint#getPostcondition <em>Postcondition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Postcondition</em>'.
     * @see ucm.map.EndPoint#getPostcondition()
     * @see #getEndPoint()
     * @generated
     */
    EReference getEndPoint_Postcondition();

    /**
     * Returns the meta object for class '{@link ucm.map.StartPoint <em>Start Point</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Start Point</em>'.
     * @see ucm.map.StartPoint
     * @generated
     */
    EClass getStartPoint();

    /**
     * Returns the meta object for the containment reference '{@link ucm.map.StartPoint#getWorkload <em>Workload</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Workload</em>'.
     * @see ucm.map.StartPoint#getWorkload()
     * @see #getStartPoint()
     * @generated
     */
    EReference getStartPoint_Workload();

    /**
     * Returns the meta object for the reference list '{@link ucm.map.StartPoint#getInBindings <em>In Bindings</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>In Bindings</em>'.
     * @see ucm.map.StartPoint#getInBindings()
     * @see #getStartPoint()
     * @generated
     */
    EReference getStartPoint_InBindings();

    /**
     * Returns the meta object for the containment reference '{@link ucm.map.StartPoint#getPrecondition <em>Precondition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Precondition</em>'.
     * @see ucm.map.StartPoint#getPrecondition()
     * @see #getStartPoint()
     * @generated
     */
    EReference getStartPoint_Precondition();

    /**
     * Returns the meta object for class '{@link ucm.map.Map <em>Map</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Map</em>'.
     * @see ucm.map.Map
     * @generated
     */
    EClass getMap();

    /**
     * Returns the meta object for the containment reference '{@link ucm.map.Map#getPathGraph <em>Path Graph</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Path Graph</em>'.
     * @see ucm.map.Map#getPathGraph()
     * @see #getMap()
     * @generated
     */
    EReference getMap_PathGraph();

    /**
     * Returns the meta object for the containment reference list '{@link ucm.map.Map#getCompRefs <em>Comp Refs</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Comp Refs</em>'.
     * @see ucm.map.Map#getCompRefs()
     * @see #getMap()
     * @generated
     */
    EReference getMap_CompRefs();

    /**
     * Returns the meta object for the reference list '{@link ucm.map.Map#getParentStub <em>Parent Stub</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Parent Stub</em>'.
     * @see ucm.map.Map#getParentStub()
     * @see #getMap()
     * @generated
     */
    EReference getMap_ParentStub();

    /**
     * Returns the meta object for class '{@link ucm.map.PathGraph <em>Path Graph</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Path Graph</em>'.
     * @see ucm.map.PathGraph
     * @generated
     */
    EClass getPathGraph();

    /**
     * Returns the meta object for the containment reference list '{@link ucm.map.PathGraph#getPathNodes <em>Path Nodes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Path Nodes</em>'.
     * @see ucm.map.PathGraph#getPathNodes()
     * @see #getPathGraph()
     * @generated
     */
    EReference getPathGraph_PathNodes();

    /**
     * Returns the meta object for the containment reference list '{@link ucm.map.PathGraph#getNodeConnections <em>Node Connections</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Node Connections</em>'.
     * @see ucm.map.PathGraph#getNodeConnections()
     * @see #getPathGraph()
     * @generated
     */
    EReference getPathGraph_NodeConnections();

    /**
     * Returns the meta object for class '{@link ucm.map.Abort <em>Abort</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Abort</em>'.
     * @see ucm.map.Abort
     * @generated
     */
    EClass getAbort();

    /**
     * Returns the meta object for the reference list '{@link ucm.map.Abort#getCondition <em>Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Condition</em>'.
     * @see ucm.map.Abort#getCondition()
     * @see #getAbort()
     * @generated
     */
    EReference getAbort_Condition();

    /**
     * Returns the meta object for class '{@link ucm.map.PluginBinding <em>Plugin Binding</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Plugin Binding</em>'.
     * @see ucm.map.PluginBinding
     * @generated
     */
    EClass getPluginBinding();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.PluginBinding#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see ucm.map.PluginBinding#getId()
     * @see #getPluginBinding()
     * @generated
     */
    EAttribute getPluginBinding_Id();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.PluginBinding#getRepetitionCount <em>Repetition Count</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Repetition Count</em>'.
     * @see ucm.map.PluginBinding#getRepetitionCount()
     * @see #getPluginBinding()
     * @generated
     */
    EAttribute getPluginBinding_RepetitionCount();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.PluginBinding#getProbability <em>Probability</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Probability</em>'.
     * @see ucm.map.PluginBinding#getProbability()
     * @see #getPluginBinding()
     * @generated
     */
    EAttribute getPluginBinding_Probability();

    /**
     * Returns the meta object for the containment reference list '{@link ucm.map.PluginBinding#getIn <em>In</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>In</em>'.
     * @see ucm.map.PluginBinding#getIn()
     * @see #getPluginBinding()
     * @generated
     */
    EReference getPluginBinding_In();

    /**
     * Returns the meta object for the containment reference list '{@link ucm.map.PluginBinding#getOut <em>Out</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Out</em>'.
     * @see ucm.map.PluginBinding#getOut()
     * @see #getPluginBinding()
     * @generated
     */
    EReference getPluginBinding_Out();

    /**
     * Returns the meta object for the reference '{@link ucm.map.PluginBinding#getPlugin <em>Plugin</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Plugin</em>'.
     * @see ucm.map.PluginBinding#getPlugin()
     * @see #getPluginBinding()
     * @generated
     */
    EReference getPluginBinding_Plugin();

    /**
     * Returns the meta object for the containment reference '{@link ucm.map.PluginBinding#getPrecondition <em>Precondition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Precondition</em>'.
     * @see ucm.map.PluginBinding#getPrecondition()
     * @see #getPluginBinding()
     * @generated
     */
    EReference getPluginBinding_Precondition();

    /**
     * Returns the meta object for class '{@link ucm.map.ComponentRef <em>Component Ref</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Component Ref</em>'.
     * @see ucm.map.ComponentRef
     * @generated
     */
    EClass getComponentRef();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.ComponentRef#getRole <em>Role</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Role</em>'.
     * @see ucm.map.ComponentRef#getRole()
     * @see #getComponentRef()
     * @generated
     */
    EAttribute getComponentRef_Role();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.ComponentRef#getReplicationFactor <em>Replication Factor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Replication Factor</em>'.
     * @see ucm.map.ComponentRef#getReplicationFactor()
     * @see #getComponentRef()
     * @generated
     */
    EAttribute getComponentRef_ReplicationFactor();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.ComponentRef#isAnchored <em>Anchored</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Anchored</em>'.
     * @see ucm.map.ComponentRef#isAnchored()
     * @see #getComponentRef()
     * @generated
     */
    EAttribute getComponentRef_Anchored();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.ComponentRef#isFixed <em>Fixed</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Fixed</em>'.
     * @see ucm.map.ComponentRef#isFixed()
     * @see #getComponentRef()
     * @generated
     */
    EAttribute getComponentRef_Fixed();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.ComponentRef#getX <em>X</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>X</em>'.
     * @see ucm.map.ComponentRef#getX()
     * @see #getComponentRef()
     * @generated
     */
    EAttribute getComponentRef_X();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.ComponentRef#getY <em>Y</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Y</em>'.
     * @see ucm.map.ComponentRef#getY()
     * @see #getComponentRef()
     * @generated
     */
    EAttribute getComponentRef_Y();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.ComponentRef#getWidth <em>Width</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Width</em>'.
     * @see ucm.map.ComponentRef#getWidth()
     * @see #getComponentRef()
     * @generated
     */
    EAttribute getComponentRef_Width();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.ComponentRef#getHeight <em>Height</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Height</em>'.
     * @see ucm.map.ComponentRef#getHeight()
     * @see #getComponentRef()
     * @generated
     */
    EAttribute getComponentRef_Height();

    /**
     * Returns the meta object for the reference '{@link ucm.map.ComponentRef#getCompDef <em>Comp Def</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Comp Def</em>'.
     * @see ucm.map.ComponentRef#getCompDef()
     * @see #getComponentRef()
     * @generated
     */
    EReference getComponentRef_CompDef();

    /**
     * Returns the meta object for the reference list '{@link ucm.map.ComponentRef#getChildren <em>Children</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Children</em>'.
     * @see ucm.map.ComponentRef#getChildren()
     * @see #getComponentRef()
     * @generated
     */
    EReference getComponentRef_Children();

    /**
     * Returns the meta object for the reference '{@link ucm.map.ComponentRef#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Parent</em>'.
     * @see ucm.map.ComponentRef#getParent()
     * @see #getComponentRef()
     * @generated
     */
    EReference getComponentRef_Parent();

    /**
     * Returns the meta object for the reference list '{@link ucm.map.ComponentRef#getPathNodes <em>Path Nodes</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Path Nodes</em>'.
     * @see ucm.map.ComponentRef#getPathNodes()
     * @see #getComponentRef()
     * @generated
     */
    EReference getComponentRef_PathNodes();

    /**
     * Returns the meta object for the containment reference '{@link ucm.map.ComponentRef#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Label</em>'.
     * @see ucm.map.ComponentRef#getLabel()
     * @see #getComponentRef()
     * @generated
     */
    EReference getComponentRef_Label();

    /**
     * Returns the meta object for class '{@link ucm.map.Timer <em>Timer</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Timer</em>'.
     * @see ucm.map.Timer
     * @generated
     */
    EClass getTimer();

    /**
     * Returns the meta object for the containment reference '{@link ucm.map.Timer#getTimeoutCondition <em>Timeout Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Timeout Condition</em>'.
     * @see ucm.map.Timer#getTimeoutCondition()
     * @see #getTimer()
     * @generated
     */
    EReference getTimer_TimeoutCondition();

    /**
     * Returns the meta object for the reference '{@link ucm.map.Timer#getTimeoutPath <em>Timeout Path</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Timeout Path</em>'.
     * @see ucm.map.Timer#getTimeoutPath()
     * @see #getTimer()
     * @generated
     */
    EReference getTimer_TimeoutPath();

    /**
     * Returns the meta object for the reference '{@link ucm.map.Timer#getTimerVar <em>Timer Var</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Timer Var</em>'.
     * @see ucm.map.Timer#getTimerVar()
     * @see #getTimer()
     * @generated
     */
    EReference getTimer_TimerVar();

    /**
     * Returns the meta object for class '{@link ucm.map.AndFork <em>And Fork</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>And Fork</em>'.
     * @see ucm.map.AndFork
     * @generated
     */
    EClass getAndFork();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.AndFork#getOrientation <em>Orientation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Orientation</em>'.
     * @see ucm.map.AndFork#getOrientation()
     * @see #getAndFork()
     * @generated
     */
    EAttribute getAndFork_Orientation();

    /**
     * Returns the meta object for class '{@link ucm.map.EmptyPoint <em>Empty Point</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Empty Point</em>'.
     * @see ucm.map.EmptyPoint
     * @generated
     */
    EClass getEmptyPoint();

    /**
     * Returns the meta object for class '{@link ucm.map.FailurePoint <em>Failure Point</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Failure Point</em>'.
     * @see ucm.map.FailurePoint
     * @generated
     */
    EClass getFailurePoint();

    /**
     * Returns the meta object for class '{@link ucm.map.Condition <em>Condition</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Condition</em>'.
     * @see ucm.map.Condition
     * @generated
     */
    EClass getCondition();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.Condition#getLabel <em>Label</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label</em>'.
     * @see ucm.map.Condition#getLabel()
     * @see #getCondition()
     * @generated
     */
    EAttribute getCondition_Label();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.Condition#getExpression <em>Expression</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Expression</em>'.
     * @see ucm.map.Condition#getExpression()
     * @see #getCondition()
     * @generated
     */
    EAttribute getCondition_Expression();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.Condition#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see ucm.map.Condition#getDescription()
     * @see #getCondition()
     * @generated
     */
    EAttribute getCondition_Description();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.Condition#getLabelX <em>Label X</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label X</em>'.
     * @see ucm.map.Condition#getLabelX()
     * @see #getCondition()
     * @generated
     */
    EAttribute getCondition_LabelX();

    /**
     * Returns the meta object for the attribute '{@link ucm.map.Condition#getLabelY <em>Label Y</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Label Y</em>'.
     * @see ucm.map.Condition#getLabelY()
     * @see #getCondition()
     * @generated
     */
    EAttribute getCondition_LabelY();

    /**
     * Returns the meta object for the reference list '{@link ucm.map.Condition#getVariables <em>Variables</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Variables</em>'.
     * @see ucm.map.Condition#getVariables()
     * @see #getCondition()
     * @generated
     */
    EReference getCondition_Variables();

    /**
     * Returns the meta object for class '{@link ucm.map.DirectionArrow <em>Direction Arrow</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Direction Arrow</em>'.
     * @see ucm.map.DirectionArrow
     * @generated
     */
    EClass getDirectionArrow();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    MapFactory getMapFactory();

} //MapPackage
