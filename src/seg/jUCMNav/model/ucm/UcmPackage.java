/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package seg.jUCMNav.model.ucm;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see seg.jUCMNav.model.ucm.UcmFactory
 * @generated
 */
public interface UcmPackage extends EPackage{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ucm";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///seg/jUCMNav/model/ucm.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "seg.jUCMNav.model.ucm";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UcmPackage eINSTANCE = seg.jUCMNav.model.ucm.impl.UcmPackageImpl.init();

	/**
	 * The meta object id for the '{@link seg.jUCMNav.model.ucm.impl.PathImpl <em>Path</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see seg.jUCMNav.model.ucm.impl.PathImpl
	 * @see seg.jUCMNav.model.ucm.impl.UcmPackageImpl#getPath()
	 * @generated
	 */
	int PATH = 0;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH__NODES = 0;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH__DIAGRAM = 1;

	/**
	 * The feature id for the '<em><b>In Fork</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH__IN_FORK = 2;

	/**
	 * The feature id for the '<em><b>Out Fork</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH__OUT_FORK = 3;

	/**
	 * The feature id for the '<em><b>Endpoint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH__ENDPOINT = 4;

	/**
	 * The feature id for the '<em><b>Startpoint</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH__STARTPOINT = 5;

	/**
	 * The number of structural features of the the '<em>Path</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link seg.jUCMNav.model.ucm.impl.XYElementImpl <em>XY Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see seg.jUCMNav.model.ucm.impl.XYElementImpl
	 * @see seg.jUCMNav.model.ucm.impl.UcmPackageImpl#getXYElement()
	 * @generated
	 */
	int XY_ELEMENT = 2;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XY_ELEMENT__X = 0;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XY_ELEMENT__Y = 1;

	/**
	 * The feature id for the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XY_ELEMENT__COMPONENT = 2;

	/**
	 * The number of structural features of the the '<em>XY Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XY_ELEMENT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link seg.jUCMNav.model.ucm.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see seg.jUCMNav.model.ucm.impl.NodeImpl
	 * @see seg.jUCMNav.model.ucm.impl.UcmPackageImpl#getNode()
	 * @generated
	 */
	int NODE = 1;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__X = XY_ELEMENT__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__Y = XY_ELEMENT__Y;

	/**
	 * The feature id for the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__COMPONENT = XY_ELEMENT__COMPONENT;

	/**
	 * The feature id for the '<em><b>Path</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__PATH = XY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Next</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__NEXT = XY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Previous</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__PREVIOUS = XY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = XY_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link seg.jUCMNav.model.ucm.impl.SizedElementImpl <em>Sized Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see seg.jUCMNav.model.ucm.impl.SizedElementImpl
	 * @see seg.jUCMNav.model.ucm.impl.UcmPackageImpl#getSizedElement()
	 * @generated
	 */
	int SIZED_ELEMENT = 4;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIZED_ELEMENT__X = XY_ELEMENT__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIZED_ELEMENT__Y = XY_ELEMENT__Y;

	/**
	 * The feature id for the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIZED_ELEMENT__COMPONENT = XY_ELEMENT__COMPONENT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIZED_ELEMENT__WIDTH = XY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIZED_ELEMENT__HEIGHT = XY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>Sized Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIZED_ELEMENT_FEATURE_COUNT = XY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link seg.jUCMNav.model.ucm.impl.ComponentImpl <em>Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see seg.jUCMNav.model.ucm.impl.ComponentImpl
	 * @see seg.jUCMNav.model.ucm.impl.UcmPackageImpl#getComponent()
	 * @generated
	 */
	int COMPONENT = 3;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__X = SIZED_ELEMENT__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__Y = SIZED_ELEMENT__Y;

	/**
	 * The feature id for the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__COMPONENT = SIZED_ELEMENT__COMPONENT;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__WIDTH = SIZED_ELEMENT__WIDTH;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__HEIGHT = SIZED_ELEMENT__HEIGHT;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__ELEMENTS = SIZED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__DIAGRAM = SIZED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_FEATURE_COUNT = SIZED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link seg.jUCMNav.model.ucm.impl.UcmDiagramImpl <em>Diagram</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see seg.jUCMNav.model.ucm.impl.UcmDiagramImpl
	 * @see seg.jUCMNav.model.ucm.impl.UcmPackageImpl#getUcmDiagram()
	 * @generated
	 */
	int UCM_DIAGRAM = 5;

	/**
	 * The feature id for the '<em><b>Paths</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UCM_DIAGRAM__PATHS = 0;

	/**
	 * The feature id for the '<em><b>Forks</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UCM_DIAGRAM__FORKS = 1;

	/**
	 * The feature id for the '<em><b>Components</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UCM_DIAGRAM__COMPONENTS = 2;

	/**
	 * The number of structural features of the the '<em>Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UCM_DIAGRAM_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link seg.jUCMNav.model.ucm.impl.ForkImpl <em>Fork</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see seg.jUCMNav.model.ucm.impl.ForkImpl
	 * @see seg.jUCMNav.model.ucm.impl.UcmPackageImpl#getFork()
	 * @generated
	 */
	int FORK = 6;

	/**
	 * The feature id for the '<em><b>In Paths</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__IN_PATHS = 0;

	/**
	 * The feature id for the '<em><b>Out Path</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__OUT_PATH = 1;

	/**
	 * The number of structural features of the the '<em>Fork</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link seg.jUCMNav.model.ucm.impl.EndPointImpl <em>End Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see seg.jUCMNav.model.ucm.impl.EndPointImpl
	 * @see seg.jUCMNav.model.ucm.impl.UcmPackageImpl#getEndPoint()
	 * @generated
	 */
	int END_POINT = 7;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_POINT__X = NODE__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_POINT__Y = NODE__Y;

	/**
	 * The feature id for the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_POINT__COMPONENT = NODE__COMPONENT;

	/**
	 * The feature id for the '<em><b>Path</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_POINT__PATH = NODE__PATH;

	/**
	 * The feature id for the '<em><b>Next</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_POINT__NEXT = NODE__NEXT;

	/**
	 * The feature id for the '<em><b>Previous</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_POINT__PREVIOUS = NODE__PREVIOUS;

	/**
	 * The number of structural features of the the '<em>End Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_POINT_FEATURE_COUNT = NODE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link seg.jUCMNav.model.ucm.impl.StartPointImpl <em>Start Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see seg.jUCMNav.model.ucm.impl.StartPointImpl
	 * @see seg.jUCMNav.model.ucm.impl.UcmPackageImpl#getStartPoint()
	 * @generated
	 */
	int START_POINT = 8;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_POINT__X = NODE__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_POINT__Y = NODE__Y;

	/**
	 * The feature id for the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_POINT__COMPONENT = NODE__COMPONENT;

	/**
	 * The feature id for the '<em><b>Path</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_POINT__PATH = NODE__PATH;

	/**
	 * The feature id for the '<em><b>Next</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_POINT__NEXT = NODE__NEXT;

	/**
	 * The feature id for the '<em><b>Previous</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_POINT__PREVIOUS = NODE__PREVIOUS;

	/**
	 * The number of structural features of the the '<em>Start Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_POINT_FEATURE_COUNT = NODE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link seg.jUCMNav.model.ucm.impl.ResponsibilityImpl <em>Responsibility</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see seg.jUCMNav.model.ucm.impl.ResponsibilityImpl
	 * @see seg.jUCMNav.model.ucm.impl.UcmPackageImpl#getResponsibility()
	 * @generated
	 */
	int RESPONSIBILITY = 9;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY__X = NODE__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY__Y = NODE__Y;

	/**
	 * The feature id for the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY__COMPONENT = NODE__COMPONENT;

	/**
	 * The feature id for the '<em><b>Path</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY__PATH = NODE__PATH;

	/**
	 * The feature id for the '<em><b>Next</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY__NEXT = NODE__NEXT;

	/**
	 * The feature id for the '<em><b>Previous</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY__PREVIOUS = NODE__PREVIOUS;

	/**
	 * The number of structural features of the the '<em>Responsibility</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY_FEATURE_COUNT = NODE_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link seg.jUCMNav.model.ucm.Path <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Path</em>'.
	 * @see seg.jUCMNav.model.ucm.Path
	 * @generated
	 */
	EClass getPath();

	/**
	 * Returns the meta object for the containment reference list '{@link seg.jUCMNav.model.ucm.Path#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see seg.jUCMNav.model.ucm.Path#getNodes()
	 * @see #getPath()
	 * @generated
	 */
	EReference getPath_Nodes();

	/**
	 * Returns the meta object for the container reference '{@link seg.jUCMNav.model.ucm.Path#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Diagram</em>'.
	 * @see seg.jUCMNav.model.ucm.Path#getDiagram()
	 * @see #getPath()
	 * @generated
	 */
	EReference getPath_Diagram();

	/**
	 * Returns the meta object for the container reference '{@link seg.jUCMNav.model.ucm.Path#getInFork <em>In Fork</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>In Fork</em>'.
	 * @see seg.jUCMNav.model.ucm.Path#getInFork()
	 * @see #getPath()
	 * @generated
	 */
	EReference getPath_InFork();

	/**
	 * Returns the meta object for the container reference '{@link seg.jUCMNav.model.ucm.Path#getOutFork <em>Out Fork</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Out Fork</em>'.
	 * @see seg.jUCMNav.model.ucm.Path#getOutFork()
	 * @see #getPath()
	 * @generated
	 */
	EReference getPath_OutFork();

	/**
	 * Returns the meta object for the containment reference '{@link seg.jUCMNav.model.ucm.Path#getEndpoint <em>Endpoint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Endpoint</em>'.
	 * @see seg.jUCMNav.model.ucm.Path#getEndpoint()
	 * @see #getPath()
	 * @generated
	 */
	EReference getPath_Endpoint();

	/**
	 * Returns the meta object for the containment reference '{@link seg.jUCMNav.model.ucm.Path#getStartpoint <em>Startpoint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Startpoint</em>'.
	 * @see seg.jUCMNav.model.ucm.Path#getStartpoint()
	 * @see #getPath()
	 * @generated
	 */
	EReference getPath_Startpoint();

	/**
	 * Returns the meta object for class '{@link seg.jUCMNav.model.ucm.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see seg.jUCMNav.model.ucm.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for the container reference '{@link seg.jUCMNav.model.ucm.Node#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Path</em>'.
	 * @see seg.jUCMNav.model.ucm.Node#getPath()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Path();

	/**
	 * Returns the meta object for the reference '{@link seg.jUCMNav.model.ucm.Node#getNext <em>Next</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Next</em>'.
	 * @see seg.jUCMNav.model.ucm.Node#getNext()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Next();

	/**
	 * Returns the meta object for the reference '{@link seg.jUCMNav.model.ucm.Node#getPrevious <em>Previous</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Previous</em>'.
	 * @see seg.jUCMNav.model.ucm.Node#getPrevious()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Previous();

	/**
	 * Returns the meta object for class '{@link seg.jUCMNav.model.ucm.XYElement <em>XY Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>XY Element</em>'.
	 * @see seg.jUCMNav.model.ucm.XYElement
	 * @generated
	 */
	EClass getXYElement();

	/**
	 * Returns the meta object for the attribute '{@link seg.jUCMNav.model.ucm.XYElement#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see seg.jUCMNav.model.ucm.XYElement#getX()
	 * @see #getXYElement()
	 * @generated
	 */
	EAttribute getXYElement_X();

	/**
	 * Returns the meta object for the attribute '{@link seg.jUCMNav.model.ucm.XYElement#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see seg.jUCMNav.model.ucm.XYElement#getY()
	 * @see #getXYElement()
	 * @generated
	 */
	EAttribute getXYElement_Y();

	/**
	 * Returns the meta object for the reference '{@link seg.jUCMNav.model.ucm.XYElement#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Component</em>'.
	 * @see seg.jUCMNav.model.ucm.XYElement#getComponent()
	 * @see #getXYElement()
	 * @generated
	 */
	EReference getXYElement_Component();

	/**
	 * Returns the meta object for class '{@link seg.jUCMNav.model.ucm.Component <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component</em>'.
	 * @see seg.jUCMNav.model.ucm.Component
	 * @generated
	 */
	EClass getComponent();

	/**
	 * Returns the meta object for the reference list '{@link seg.jUCMNav.model.ucm.Component#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Elements</em>'.
	 * @see seg.jUCMNav.model.ucm.Component#getElements()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Elements();

	/**
	 * Returns the meta object for the container reference '{@link seg.jUCMNav.model.ucm.Component#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Diagram</em>'.
	 * @see seg.jUCMNav.model.ucm.Component#getDiagram()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Diagram();

	/**
	 * Returns the meta object for class '{@link seg.jUCMNav.model.ucm.SizedElement <em>Sized Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sized Element</em>'.
	 * @see seg.jUCMNav.model.ucm.SizedElement
	 * @generated
	 */
	EClass getSizedElement();

	/**
	 * Returns the meta object for the attribute '{@link seg.jUCMNav.model.ucm.SizedElement#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see seg.jUCMNav.model.ucm.SizedElement#getWidth()
	 * @see #getSizedElement()
	 * @generated
	 */
	EAttribute getSizedElement_Width();

	/**
	 * Returns the meta object for the attribute '{@link seg.jUCMNav.model.ucm.SizedElement#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see seg.jUCMNav.model.ucm.SizedElement#getHeight()
	 * @see #getSizedElement()
	 * @generated
	 */
	EAttribute getSizedElement_Height();

	/**
	 * Returns the meta object for class '{@link seg.jUCMNav.model.ucm.UcmDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagram</em>'.
	 * @see seg.jUCMNav.model.ucm.UcmDiagram
	 * @generated
	 */
	EClass getUcmDiagram();

	/**
	 * Returns the meta object for the containment reference list '{@link seg.jUCMNav.model.ucm.UcmDiagram#getPaths <em>Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Paths</em>'.
	 * @see seg.jUCMNav.model.ucm.UcmDiagram#getPaths()
	 * @see #getUcmDiagram()
	 * @generated
	 */
	EReference getUcmDiagram_Paths();

	/**
	 * Returns the meta object for the containment reference list '{@link seg.jUCMNav.model.ucm.UcmDiagram#getForks <em>Forks</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Forks</em>'.
	 * @see seg.jUCMNav.model.ucm.UcmDiagram#getForks()
	 * @see #getUcmDiagram()
	 * @generated
	 */
	EReference getUcmDiagram_Forks();

	/**
	 * Returns the meta object for the containment reference list '{@link seg.jUCMNav.model.ucm.UcmDiagram#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Components</em>'.
	 * @see seg.jUCMNav.model.ucm.UcmDiagram#getComponents()
	 * @see #getUcmDiagram()
	 * @generated
	 */
	EReference getUcmDiagram_Components();

	/**
	 * Returns the meta object for class '{@link seg.jUCMNav.model.ucm.Fork <em>Fork</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fork</em>'.
	 * @see seg.jUCMNav.model.ucm.Fork
	 * @generated
	 */
	EClass getFork();

	/**
	 * Returns the meta object for the containment reference list '{@link seg.jUCMNav.model.ucm.Fork#getInPaths <em>In Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>In Paths</em>'.
	 * @see seg.jUCMNav.model.ucm.Fork#getInPaths()
	 * @see #getFork()
	 * @generated
	 */
	EReference getFork_InPaths();

	/**
	 * Returns the meta object for the containment reference list '{@link seg.jUCMNav.model.ucm.Fork#getOutPath <em>Out Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Out Path</em>'.
	 * @see seg.jUCMNav.model.ucm.Fork#getOutPath()
	 * @see #getFork()
	 * @generated
	 */
	EReference getFork_OutPath();

	/**
	 * Returns the meta object for class '{@link seg.jUCMNav.model.ucm.EndPoint <em>End Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>End Point</em>'.
	 * @see seg.jUCMNav.model.ucm.EndPoint
	 * @generated
	 */
	EClass getEndPoint();

	/**
	 * Returns the meta object for class '{@link seg.jUCMNav.model.ucm.StartPoint <em>Start Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Start Point</em>'.
	 * @see seg.jUCMNav.model.ucm.StartPoint
	 * @generated
	 */
	EClass getStartPoint();

	/**
	 * Returns the meta object for class '{@link seg.jUCMNav.model.ucm.Responsibility <em>Responsibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Responsibility</em>'.
	 * @see seg.jUCMNav.model.ucm.Responsibility
	 * @generated
	 */
	EClass getResponsibility();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	UcmFactory getUcmFactory();

} //UcmPackage
