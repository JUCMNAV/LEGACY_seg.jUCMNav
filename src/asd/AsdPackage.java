/**
 */
package asd;

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
 * @see asd.AsdFactory
 * @model kind="package"
 * @generated
 */
public interface AsdPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "asd";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///asd.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "asd";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AsdPackage eINSTANCE = asd.impl.AsdPackageImpl.init();

	/**
	 * The meta object id for the '{@link asd.impl.ASDspecImpl <em>AS Dspec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asd.impl.ASDspecImpl
	 * @see asd.impl.AsdPackageImpl#getASDspec()
	 * @generated
	 */
	int AS_DSPEC = 0;

	/**
	 * The feature id for the '<em><b>AS Network</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DSPEC__AS_NETWORK = 0;

	/**
	 * The feature id for the '<em><b>Motivation</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DSPEC__MOTIVATION = 1;

	/**
	 * The feature id for the '<em><b>Outcome</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DSPEC__OUTCOME = 2;

	/**
	 * The feature id for the '<em><b>Dols</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DSPEC__DOLS = 3;

	/**
	 * The feature id for the '<em><b>Subjects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DSPEC__SUBJECTS = 4;

	/**
	 * The feature id for the '<em><b>Communities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DSPEC__COMMUNITIES = 5;

	/**
	 * The feature id for the '<em><b>Objects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DSPEC__OBJECTS = 6;

	/**
	 * The feature id for the '<em><b>Outcomes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DSPEC__OUTCOMES = 7;

	/**
	 * The feature id for the '<em><b>Motivations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DSPEC__MOTIVATIONS = 8;

	/**
	 * The feature id for the '<em><b>Tools</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DSPEC__TOOLS = 9;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DSPEC__RULES = 10;

	/**
	 * The number of structural features of the '<em>AS Dspec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DSPEC_FEATURE_COUNT = 11;

	/**
	 * The meta object id for the '{@link asd.impl.ASDmodelElementImpl <em>AS Dmodel Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asd.impl.ASDmodelElementImpl
	 * @see asd.impl.AsdPackageImpl#getASDmodelElement()
	 * @generated
	 */
	int AS_DMODEL_ELEMENT = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DMODEL_ELEMENT__NAME = UrncorePackage.UR_NMODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DMODEL_ELEMENT__FROM_LINKS = UrncorePackage.UR_NMODEL_ELEMENT__FROM_LINKS;

	/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DMODEL_ELEMENT__TO_LINKS = UrncorePackage.UR_NMODEL_ELEMENT__TO_LINKS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DMODEL_ELEMENT__ID = UrncorePackage.UR_NMODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DMODEL_ELEMENT__DESCRIPTION = UrncorePackage.UR_NMODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DMODEL_ELEMENT__METADATA = UrncorePackage.UR_NMODEL_ELEMENT__METADATA;

	/**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DMODEL_ELEMENT__INCONCERN = UrncorePackage.UR_NMODEL_ELEMENT__INCONCERN;

	/**
	 * The number of structural features of the '<em>AS Dmodel Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DMODEL_ELEMENT_FEATURE_COUNT = UrncorePackage.UR_NMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link asd.impl.ASDelementImpl <em>AS Delement</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asd.impl.ASDelementImpl
	 * @see asd.impl.AsdPackageImpl#getASDelement()
	 * @generated
	 */
	int AS_DELEMENT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DELEMENT__NAME = AS_DMODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DELEMENT__FROM_LINKS = AS_DMODEL_ELEMENT__FROM_LINKS;

	/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DELEMENT__TO_LINKS = AS_DMODEL_ELEMENT__TO_LINKS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DELEMENT__ID = AS_DMODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DELEMENT__DESCRIPTION = AS_DMODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DELEMENT__METADATA = AS_DMODEL_ELEMENT__METADATA;

	/**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DELEMENT__INCONCERN = AS_DMODEL_ELEMENT__INCONCERN;

	/**
	 * The feature id for the '<em><b>Diagrams</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DELEMENT__DIAGRAMS = AS_DMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Parent Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DELEMENT__PARENT_ELEMENTS = AS_DMODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Refined Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DELEMENT__REFINED_ELEMENTS = AS_DMODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Required Outcomes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DELEMENT__REQUIRED_OUTCOMES = AS_DMODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>AS Delement</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DELEMENT_FEATURE_COUNT = AS_DMODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link asd.impl.ASDiagramImpl <em>AS Diagram</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asd.impl.ASDiagramImpl
	 * @see asd.impl.AsdPackageImpl#getASDiagram()
	 * @generated
	 */
	int AS_DIAGRAM = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DIAGRAM__NAME = AS_DMODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DIAGRAM__FROM_LINKS = AS_DMODEL_ELEMENT__FROM_LINKS;

	/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DIAGRAM__TO_LINKS = AS_DMODEL_ELEMENT__TO_LINKS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DIAGRAM__ID = AS_DMODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DIAGRAM__DESCRIPTION = AS_DMODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DIAGRAM__METADATA = AS_DMODEL_ELEMENT__METADATA;

	/**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DIAGRAM__INCONCERN = AS_DMODEL_ELEMENT__INCONCERN;

	/**
	 * The feature id for the '<em><b>Urndefinition</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DIAGRAM__URNDEFINITION = AS_DMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DIAGRAM__NODES = AS_DMODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Cont Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DIAGRAM__CONT_REFS = AS_DMODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DIAGRAM__CONNECTIONS = AS_DMODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Concern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DIAGRAM__CONCERN = AS_DMODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DIAGRAM__COMMENTS = AS_DMODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DIAGRAM__ELEMENTS = AS_DMODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>ASD</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DIAGRAM__ASD = AS_DMODEL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Related ASD</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DIAGRAM__RELATED_ASD = AS_DMODEL_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Mediations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DIAGRAM__MEDIATIONS = AS_DMODEL_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Asd Layouts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DIAGRAM__ASD_LAYOUTS = AS_DMODEL_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Parent Do Ls</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DIAGRAM__PARENT_DO_LS = AS_DMODEL_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>AS Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DIAGRAM_FEATURE_COUNT = AS_DMODEL_ELEMENT_FEATURE_COUNT + 12;

	/**
	 * The meta object id for the '{@link asd.impl.MediatingElementImpl <em>Mediating Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asd.impl.MediatingElementImpl
	 * @see asd.impl.AsdPackageImpl#getMediatingElement()
	 * @generated
	 */
	int MEDIATING_ELEMENT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATING_ELEMENT__NAME = AS_DELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATING_ELEMENT__FROM_LINKS = AS_DELEMENT__FROM_LINKS;

	/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATING_ELEMENT__TO_LINKS = AS_DELEMENT__TO_LINKS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATING_ELEMENT__ID = AS_DELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATING_ELEMENT__DESCRIPTION = AS_DELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATING_ELEMENT__METADATA = AS_DELEMENT__METADATA;

	/**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATING_ELEMENT__INCONCERN = AS_DELEMENT__INCONCERN;

	/**
	 * The feature id for the '<em><b>Diagrams</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATING_ELEMENT__DIAGRAMS = AS_DELEMENT__DIAGRAMS;

	/**
	 * The feature id for the '<em><b>Parent Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATING_ELEMENT__PARENT_ELEMENTS = AS_DELEMENT__PARENT_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Refined Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATING_ELEMENT__REFINED_ELEMENTS = AS_DELEMENT__REFINED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Required Outcomes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATING_ELEMENT__REQUIRED_OUTCOMES = AS_DELEMENT__REQUIRED_OUTCOMES;

	/**
	 * The feature id for the '<em><b>Mediations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATING_ELEMENT__MEDIATIONS = AS_DELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Mediating Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATING_ELEMENT_FEATURE_COUNT = AS_DELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link asd.impl.MediationImpl <em>Mediation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asd.impl.MediationImpl
	 * @see asd.impl.AsdPackageImpl#getMediation()
	 * @generated
	 */
	int MEDIATION = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATION__NAME = AS_DMODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATION__FROM_LINKS = AS_DMODEL_ELEMENT__FROM_LINKS;

	/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATION__TO_LINKS = AS_DMODEL_ELEMENT__TO_LINKS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATION__ID = AS_DMODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATION__DESCRIPTION = AS_DMODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATION__METADATA = AS_DMODEL_ELEMENT__METADATA;

	/**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATION__INCONCERN = AS_DMODEL_ELEMENT__INCONCERN;

	/**
	 * The feature id for the '<em><b>Mediated By</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATION__MEDIATED_BY = AS_DMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Mediates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATION__MEDIATES = AS_DMODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Relevant ASD</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATION__RELEVANT_ASD = AS_DMODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Mediation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATION_FEATURE_COUNT = AS_DMODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link asd.impl.MediatedElementImpl <em>Mediated Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asd.impl.MediatedElementImpl
	 * @see asd.impl.AsdPackageImpl#getMediatedElement()
	 * @generated
	 */
	int MEDIATED_ELEMENT = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATED_ELEMENT__NAME = AS_DELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATED_ELEMENT__FROM_LINKS = AS_DELEMENT__FROM_LINKS;

	/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATED_ELEMENT__TO_LINKS = AS_DELEMENT__TO_LINKS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATED_ELEMENT__ID = AS_DELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATED_ELEMENT__DESCRIPTION = AS_DELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATED_ELEMENT__METADATA = AS_DELEMENT__METADATA;

	/**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATED_ELEMENT__INCONCERN = AS_DELEMENT__INCONCERN;

	/**
	 * The feature id for the '<em><b>Diagrams</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATED_ELEMENT__DIAGRAMS = AS_DELEMENT__DIAGRAMS;

	/**
	 * The feature id for the '<em><b>Parent Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATED_ELEMENT__PARENT_ELEMENTS = AS_DELEMENT__PARENT_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Refined Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATED_ELEMENT__REFINED_ELEMENTS = AS_DELEMENT__REFINED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Required Outcomes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATED_ELEMENT__REQUIRED_OUTCOMES = AS_DELEMENT__REQUIRED_OUTCOMES;

	/**
	 * The feature id for the '<em><b>Mediations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATED_ELEMENT__MEDIATIONS = AS_DELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Mediated Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEDIATED_ELEMENT_FEATURE_COUNT = AS_DELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link asd.impl.ASNetworkImpl <em>AS Network</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asd.impl.ASNetworkImpl
	 * @see asd.impl.AsdPackageImpl#getASNetwork()
	 * @generated
	 */
	int AS_NETWORK = 7;

	/**
	 * The feature id for the '<em><b>Urndefinition</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_NETWORK__URNDEFINITION = UrncorePackage.IURN_DIAGRAM__URNDEFINITION;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_NETWORK__NODES = UrncorePackage.IURN_DIAGRAM__NODES;

	/**
	 * The feature id for the '<em><b>Cont Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_NETWORK__CONT_REFS = UrncorePackage.IURN_DIAGRAM__CONT_REFS;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_NETWORK__CONNECTIONS = UrncorePackage.IURN_DIAGRAM__CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Concern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_NETWORK__CONCERN = UrncorePackage.IURN_DIAGRAM__CONCERN;

	/**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_NETWORK__COMMENTS = UrncorePackage.IURN_DIAGRAM__COMMENTS;

	/**
	 * The feature id for the '<em><b>AS Dspec</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_NETWORK__AS_DSPEC = UrncorePackage.IURN_DIAGRAM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Asd Layouts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_NETWORK__ASD_LAYOUTS = UrncorePackage.IURN_DIAGRAM_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>AS Network</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_NETWORK_FEATURE_COUNT = UrncorePackage.IURN_DIAGRAM_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link asd.impl.ASDlayoutImpl <em>AS Dlayout</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asd.impl.ASDlayoutImpl
	 * @see asd.impl.AsdPackageImpl#getASDlayout()
	 * @generated
	 */
	int AS_DLAYOUT = 8;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DLAYOUT__X = 0;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DLAYOUT__Y = 1;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DLAYOUT__WIDTH = 2;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DLAYOUT__HEIGHT = 3;

	/**
	 * The feature id for the '<em><b>Collapsed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DLAYOUT__COLLAPSED = 4;

	/**
	 * The feature id for the '<em><b>As Network</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DLAYOUT__AS_NETWORK = 5;

	/**
	 * The feature id for the '<em><b>As Diagram</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DLAYOUT__AS_DIAGRAM = 6;

	/**
	 * The number of structural features of the '<em>AS Dlayout</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AS_DLAYOUT_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link asd.impl.ToolImpl <em>Tool</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asd.impl.ToolImpl
	 * @see asd.impl.AsdPackageImpl#getTool()
	 * @generated
	 */
	int TOOL = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__NAME = MEDIATING_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__FROM_LINKS = MEDIATING_ELEMENT__FROM_LINKS;

	/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__TO_LINKS = MEDIATING_ELEMENT__TO_LINKS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__ID = MEDIATING_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__DESCRIPTION = MEDIATING_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__METADATA = MEDIATING_ELEMENT__METADATA;

	/**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__INCONCERN = MEDIATING_ELEMENT__INCONCERN;

	/**
	 * The feature id for the '<em><b>Diagrams</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__DIAGRAMS = MEDIATING_ELEMENT__DIAGRAMS;

	/**
	 * The feature id for the '<em><b>Parent Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__PARENT_ELEMENTS = MEDIATING_ELEMENT__PARENT_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Refined Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__REFINED_ELEMENTS = MEDIATING_ELEMENT__REFINED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Required Outcomes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__REQUIRED_OUTCOMES = MEDIATING_ELEMENT__REQUIRED_OUTCOMES;

	/**
	 * The feature id for the '<em><b>Mediations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__MEDIATIONS = MEDIATING_ELEMENT__MEDIATIONS;

	/**
	 * The feature id for the '<em><b>Asd Spec</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL__ASD_SPEC = MEDIATING_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tool</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOOL_FEATURE_COUNT = MEDIATING_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link asd.impl.RuleImpl <em>Rule</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asd.impl.RuleImpl
	 * @see asd.impl.AsdPackageImpl#getRule()
	 * @generated
	 */
	int RULE = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__NAME = MEDIATING_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__FROM_LINKS = MEDIATING_ELEMENT__FROM_LINKS;

	/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__TO_LINKS = MEDIATING_ELEMENT__TO_LINKS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__ID = MEDIATING_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__DESCRIPTION = MEDIATING_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__METADATA = MEDIATING_ELEMENT__METADATA;

	/**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__INCONCERN = MEDIATING_ELEMENT__INCONCERN;

	/**
	 * The feature id for the '<em><b>Diagrams</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__DIAGRAMS = MEDIATING_ELEMENT__DIAGRAMS;

	/**
	 * The feature id for the '<em><b>Parent Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__PARENT_ELEMENTS = MEDIATING_ELEMENT__PARENT_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Refined Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__REFINED_ELEMENTS = MEDIATING_ELEMENT__REFINED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Required Outcomes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__REQUIRED_OUTCOMES = MEDIATING_ELEMENT__REQUIRED_OUTCOMES;

	/**
	 * The feature id for the '<em><b>Mediations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__MEDIATIONS = MEDIATING_ELEMENT__MEDIATIONS;

	/**
	 * The feature id for the '<em><b>Dols</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__DOLS = MEDIATING_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Asd Spec</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE__ASD_SPEC = MEDIATING_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Rule</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RULE_FEATURE_COUNT = MEDIATING_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link asd.impl.DivisionOfLabourImpl <em>Division Of Labour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asd.impl.DivisionOfLabourImpl
	 * @see asd.impl.AsdPackageImpl#getDivisionOfLabour()
	 * @generated
	 */
	int DIVISION_OF_LABOUR = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIVISION_OF_LABOUR__NAME = MEDIATING_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIVISION_OF_LABOUR__FROM_LINKS = MEDIATING_ELEMENT__FROM_LINKS;

	/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIVISION_OF_LABOUR__TO_LINKS = MEDIATING_ELEMENT__TO_LINKS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIVISION_OF_LABOUR__ID = MEDIATING_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIVISION_OF_LABOUR__DESCRIPTION = MEDIATING_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIVISION_OF_LABOUR__METADATA = MEDIATING_ELEMENT__METADATA;

	/**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIVISION_OF_LABOUR__INCONCERN = MEDIATING_ELEMENT__INCONCERN;

	/**
	 * The feature id for the '<em><b>Diagrams</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIVISION_OF_LABOUR__DIAGRAMS = MEDIATING_ELEMENT__DIAGRAMS;

	/**
	 * The feature id for the '<em><b>Parent Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIVISION_OF_LABOUR__PARENT_ELEMENTS = MEDIATING_ELEMENT__PARENT_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Refined Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIVISION_OF_LABOUR__REFINED_ELEMENTS = MEDIATING_ELEMENT__REFINED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Required Outcomes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIVISION_OF_LABOUR__REQUIRED_OUTCOMES = MEDIATING_ELEMENT__REQUIRED_OUTCOMES;

	/**
	 * The feature id for the '<em><b>Mediations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIVISION_OF_LABOUR__MEDIATIONS = MEDIATING_ELEMENT__MEDIATIONS;

	/**
	 * The feature id for the '<em><b>Rules</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIVISION_OF_LABOUR__RULES = MEDIATING_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Refined Diagrams</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIVISION_OF_LABOUR__REFINED_DIAGRAMS = MEDIATING_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Performed By</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIVISION_OF_LABOUR__PERFORMED_BY = MEDIATING_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Asd Spec</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIVISION_OF_LABOUR__ASD_SPEC = MEDIATING_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Division Of Labour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIVISION_OF_LABOUR_FEATURE_COUNT = MEDIATING_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link asd.impl.CommunityImpl <em>Community</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asd.impl.CommunityImpl
	 * @see asd.impl.AsdPackageImpl#getCommunity()
	 * @generated
	 */
	int COMMUNITY = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUNITY__NAME = MEDIATED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUNITY__FROM_LINKS = MEDIATED_ELEMENT__FROM_LINKS;

	/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUNITY__TO_LINKS = MEDIATED_ELEMENT__TO_LINKS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUNITY__ID = MEDIATED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUNITY__DESCRIPTION = MEDIATED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUNITY__METADATA = MEDIATED_ELEMENT__METADATA;

	/**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUNITY__INCONCERN = MEDIATED_ELEMENT__INCONCERN;

	/**
	 * The feature id for the '<em><b>Diagrams</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUNITY__DIAGRAMS = MEDIATED_ELEMENT__DIAGRAMS;

	/**
	 * The feature id for the '<em><b>Parent Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUNITY__PARENT_ELEMENTS = MEDIATED_ELEMENT__PARENT_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Refined Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUNITY__REFINED_ELEMENTS = MEDIATED_ELEMENT__REFINED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Required Outcomes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUNITY__REQUIRED_OUTCOMES = MEDIATED_ELEMENT__REQUIRED_OUTCOMES;

	/**
	 * The feature id for the '<em><b>Mediations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUNITY__MEDIATIONS = MEDIATED_ELEMENT__MEDIATIONS;

	/**
	 * The feature id for the '<em><b>Performs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUNITY__PERFORMS = MEDIATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Asd Spec</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUNITY__ASD_SPEC = MEDIATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Subject</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUNITY__SUBJECT = MEDIATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Community</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMUNITY_FEATURE_COUNT = MEDIATED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link asd.impl.SubjectImpl <em>Subject</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asd.impl.SubjectImpl
	 * @see asd.impl.AsdPackageImpl#getSubject()
	 * @generated
	 */
	int SUBJECT = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT__NAME = MEDIATED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT__FROM_LINKS = MEDIATED_ELEMENT__FROM_LINKS;

	/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT__TO_LINKS = MEDIATED_ELEMENT__TO_LINKS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT__ID = MEDIATED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT__DESCRIPTION = MEDIATED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT__METADATA = MEDIATED_ELEMENT__METADATA;

	/**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT__INCONCERN = MEDIATED_ELEMENT__INCONCERN;

	/**
	 * The feature id for the '<em><b>Diagrams</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT__DIAGRAMS = MEDIATED_ELEMENT__DIAGRAMS;

	/**
	 * The feature id for the '<em><b>Parent Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT__PARENT_ELEMENTS = MEDIATED_ELEMENT__PARENT_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Refined Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT__REFINED_ELEMENTS = MEDIATED_ELEMENT__REFINED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Required Outcomes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT__REQUIRED_OUTCOMES = MEDIATED_ELEMENT__REQUIRED_OUTCOMES;

	/**
	 * The feature id for the '<em><b>Mediations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT__MEDIATIONS = MEDIATED_ELEMENT__MEDIATIONS;

	/**
	 * The feature id for the '<em><b>Asd Spec</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT__ASD_SPEC = MEDIATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Member Of</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT__MEMBER_OF = MEDIATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Objects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT__OBJECTS = MEDIATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Subject</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBJECT_FEATURE_COUNT = MEDIATED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link asd.impl.ObjectImpl <em>Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asd.impl.ObjectImpl
	 * @see asd.impl.AsdPackageImpl#getObject()
	 * @generated
	 */
	int OBJECT = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__NAME = MEDIATED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__FROM_LINKS = MEDIATED_ELEMENT__FROM_LINKS;

	/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__TO_LINKS = MEDIATED_ELEMENT__TO_LINKS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__ID = MEDIATED_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__DESCRIPTION = MEDIATED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__METADATA = MEDIATED_ELEMENT__METADATA;

	/**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__INCONCERN = MEDIATED_ELEMENT__INCONCERN;

	/**
	 * The feature id for the '<em><b>Diagrams</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__DIAGRAMS = MEDIATED_ELEMENT__DIAGRAMS;

	/**
	 * The feature id for the '<em><b>Parent Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__PARENT_ELEMENTS = MEDIATED_ELEMENT__PARENT_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Refined Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__REFINED_ELEMENTS = MEDIATED_ELEMENT__REFINED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Required Outcomes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__REQUIRED_OUTCOMES = MEDIATED_ELEMENT__REQUIRED_OUTCOMES;

	/**
	 * The feature id for the '<em><b>Mediations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__MEDIATIONS = MEDIATED_ELEMENT__MEDIATIONS;

	/**
	 * The feature id for the '<em><b>Asd Spec</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__ASD_SPEC = MEDIATED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Outcomes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__OUTCOMES = MEDIATED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Subjects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__SUBJECTS = MEDIATED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_FEATURE_COUNT = MEDIATED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link asd.impl.MotivationImpl <em>Motivation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asd.impl.MotivationImpl
	 * @see asd.impl.AsdPackageImpl#getMotivation()
	 * @generated
	 */
	int MOTIVATION = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOTIVATION__NAME = AS_DELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOTIVATION__FROM_LINKS = AS_DELEMENT__FROM_LINKS;

	/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOTIVATION__TO_LINKS = AS_DELEMENT__TO_LINKS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOTIVATION__ID = AS_DELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOTIVATION__DESCRIPTION = AS_DELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOTIVATION__METADATA = AS_DELEMENT__METADATA;

	/**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOTIVATION__INCONCERN = AS_DELEMENT__INCONCERN;

	/**
	 * The feature id for the '<em><b>Diagrams</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOTIVATION__DIAGRAMS = AS_DELEMENT__DIAGRAMS;

	/**
	 * The feature id for the '<em><b>Parent Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOTIVATION__PARENT_ELEMENTS = AS_DELEMENT__PARENT_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Refined Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOTIVATION__REFINED_ELEMENTS = AS_DELEMENT__REFINED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Required Outcomes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOTIVATION__REQUIRED_OUTCOMES = AS_DELEMENT__REQUIRED_OUTCOMES;

	/**
	 * The feature id for the '<em><b>Asd Spec</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOTIVATION__ASD_SPEC = AS_DELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Motivation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MOTIVATION_FEATURE_COUNT = AS_DELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link asd.impl.OutcomeImpl <em>Outcome</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see asd.impl.OutcomeImpl
	 * @see asd.impl.AsdPackageImpl#getOutcome()
	 * @generated
	 */
	int OUTCOME = 16;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__NAME = AS_DELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__FROM_LINKS = AS_DELEMENT__FROM_LINKS;

	/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__TO_LINKS = AS_DELEMENT__TO_LINKS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__ID = AS_DELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__DESCRIPTION = AS_DELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__METADATA = AS_DELEMENT__METADATA;

	/**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__INCONCERN = AS_DELEMENT__INCONCERN;

	/**
	 * The feature id for the '<em><b>Diagrams</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__DIAGRAMS = AS_DELEMENT__DIAGRAMS;

	/**
	 * The feature id for the '<em><b>Parent Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__PARENT_ELEMENTS = AS_DELEMENT__PARENT_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Refined Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__REFINED_ELEMENTS = AS_DELEMENT__REFINED_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Required Outcomes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__REQUIRED_OUTCOMES = AS_DELEMENT__REQUIRED_OUTCOMES;

	/**
	 * The feature id for the '<em><b>Enabled Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__ENABLED_ELEMENTS = AS_DELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Asd Spec</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__ASD_SPEC = AS_DELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Objects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME__OBJECTS = AS_DELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Outcome</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTCOME_FEATURE_COUNT = AS_DELEMENT_FEATURE_COUNT + 3;


	/**
	 * Returns the meta object for class '{@link asd.ASDspec <em>AS Dspec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AS Dspec</em>'.
	 * @see asd.ASDspec
	 * @generated
	 */
	EClass getASDspec();

	/**
	 * Returns the meta object for the reference list '{@link asd.ASDspec#getASNetwork <em>AS Network</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>AS Network</em>'.
	 * @see asd.ASDspec#getASNetwork()
	 * @see #getASDspec()
	 * @generated
	 */
	EReference getASDspec_ASNetwork();

	/**
	 * Returns the meta object for the reference list '{@link asd.ASDspec#getMotivation <em>Motivation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Motivation</em>'.
	 * @see asd.ASDspec#getMotivation()
	 * @see #getASDspec()
	 * @generated
	 */
	EReference getASDspec_Motivation();

	/**
	 * Returns the meta object for the reference list '{@link asd.ASDspec#getOutcome <em>Outcome</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Outcome</em>'.
	 * @see asd.ASDspec#getOutcome()
	 * @see #getASDspec()
	 * @generated
	 */
	EReference getASDspec_Outcome();

	/**
	 * Returns the meta object for the containment reference list '{@link asd.ASDspec#getDols <em>Dols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dols</em>'.
	 * @see asd.ASDspec#getDols()
	 * @see #getASDspec()
	 * @generated
	 */
	EReference getASDspec_Dols();

	/**
	 * Returns the meta object for the containment reference list '{@link asd.ASDspec#getSubjects <em>Subjects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Subjects</em>'.
	 * @see asd.ASDspec#getSubjects()
	 * @see #getASDspec()
	 * @generated
	 */
	EReference getASDspec_Subjects();

	/**
	 * Returns the meta object for the containment reference list '{@link asd.ASDspec#getCommunities <em>Communities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Communities</em>'.
	 * @see asd.ASDspec#getCommunities()
	 * @see #getASDspec()
	 * @generated
	 */
	EReference getASDspec_Communities();

	/**
	 * Returns the meta object for the containment reference list '{@link asd.ASDspec#getObjects <em>Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Objects</em>'.
	 * @see asd.ASDspec#getObjects()
	 * @see #getASDspec()
	 * @generated
	 */
	EReference getASDspec_Objects();

	/**
	 * Returns the meta object for the containment reference list '{@link asd.ASDspec#getOutcomes <em>Outcomes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outcomes</em>'.
	 * @see asd.ASDspec#getOutcomes()
	 * @see #getASDspec()
	 * @generated
	 */
	EReference getASDspec_Outcomes();

	/**
	 * Returns the meta object for the containment reference list '{@link asd.ASDspec#getMotivations <em>Motivations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Motivations</em>'.
	 * @see asd.ASDspec#getMotivations()
	 * @see #getASDspec()
	 * @generated
	 */
	EReference getASDspec_Motivations();

	/**
	 * Returns the meta object for the containment reference list '{@link asd.ASDspec#getTools <em>Tools</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tools</em>'.
	 * @see asd.ASDspec#getTools()
	 * @see #getASDspec()
	 * @generated
	 */
	EReference getASDspec_Tools();

	/**
	 * Returns the meta object for the containment reference list '{@link asd.ASDspec#getRules <em>Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rules</em>'.
	 * @see asd.ASDspec#getRules()
	 * @see #getASDspec()
	 * @generated
	 */
	EReference getASDspec_Rules();

	/**
	 * Returns the meta object for class '{@link asd.ASDelement <em>AS Delement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AS Delement</em>'.
	 * @see asd.ASDelement
	 * @generated
	 */
	EClass getASDelement();

	/**
	 * Returns the meta object for the reference list '{@link asd.ASDelement#getDiagrams <em>Diagrams</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Diagrams</em>'.
	 * @see asd.ASDelement#getDiagrams()
	 * @see #getASDelement()
	 * @generated
	 */
	EReference getASDelement_Diagrams();

	/**
	 * Returns the meta object for the reference list '{@link asd.ASDelement#getParentElements <em>Parent Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parent Elements</em>'.
	 * @see asd.ASDelement#getParentElements()
	 * @see #getASDelement()
	 * @generated
	 */
	EReference getASDelement_ParentElements();

	/**
	 * Returns the meta object for the reference list '{@link asd.ASDelement#getRefinedElements <em>Refined Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Refined Elements</em>'.
	 * @see asd.ASDelement#getRefinedElements()
	 * @see #getASDelement()
	 * @generated
	 */
	EReference getASDelement_RefinedElements();

	/**
	 * Returns the meta object for the reference list '{@link asd.ASDelement#getRequiredOutcomes <em>Required Outcomes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Required Outcomes</em>'.
	 * @see asd.ASDelement#getRequiredOutcomes()
	 * @see #getASDelement()
	 * @generated
	 */
	EReference getASDelement_RequiredOutcomes();

	/**
	 * Returns the meta object for class '{@link asd.ASDiagram <em>AS Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AS Diagram</em>'.
	 * @see asd.ASDiagram
	 * @generated
	 */
	EClass getASDiagram();

	/**
	 * Returns the meta object for the reference list '{@link asd.ASDiagram#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Elements</em>'.
	 * @see asd.ASDiagram#getElements()
	 * @see #getASDiagram()
	 * @generated
	 */
	EReference getASDiagram_Elements();

	/**
	 * Returns the meta object for the reference list '{@link asd.ASDiagram#getASD <em>ASD</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>ASD</em>'.
	 * @see asd.ASDiagram#getASD()
	 * @see #getASDiagram()
	 * @generated
	 */
	EReference getASDiagram_ASD();

	/**
	 * Returns the meta object for the reference list '{@link asd.ASDiagram#getRelatedASD <em>Related ASD</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Related ASD</em>'.
	 * @see asd.ASDiagram#getRelatedASD()
	 * @see #getASDiagram()
	 * @generated
	 */
	EReference getASDiagram_RelatedASD();

	/**
	 * Returns the meta object for the containment reference list '{@link asd.ASDiagram#getMediations <em>Mediations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Mediations</em>'.
	 * @see asd.ASDiagram#getMediations()
	 * @see #getASDiagram()
	 * @generated
	 */
	EReference getASDiagram_Mediations();

	/**
	 * Returns the meta object for the reference list '{@link asd.ASDiagram#getAsdLayouts <em>Asd Layouts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Asd Layouts</em>'.
	 * @see asd.ASDiagram#getAsdLayouts()
	 * @see #getASDiagram()
	 * @generated
	 */
	EReference getASDiagram_AsdLayouts();

	/**
	 * Returns the meta object for the reference list '{@link asd.ASDiagram#getParentDoLs <em>Parent Do Ls</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parent Do Ls</em>'.
	 * @see asd.ASDiagram#getParentDoLs()
	 * @see #getASDiagram()
	 * @generated
	 */
	EReference getASDiagram_ParentDoLs();

	/**
	 * Returns the meta object for class '{@link asd.MediatingElement <em>Mediating Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mediating Element</em>'.
	 * @see asd.MediatingElement
	 * @generated
	 */
	EClass getMediatingElement();

	/**
	 * Returns the meta object for the reference list '{@link asd.MediatingElement#getMediations <em>Mediations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Mediations</em>'.
	 * @see asd.MediatingElement#getMediations()
	 * @see #getMediatingElement()
	 * @generated
	 */
	EReference getMediatingElement_Mediations();

	/**
	 * Returns the meta object for class '{@link asd.Mediation <em>Mediation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mediation</em>'.
	 * @see asd.Mediation
	 * @generated
	 */
	EClass getMediation();

	/**
	 * Returns the meta object for the reference '{@link asd.Mediation#getMediatedBy <em>Mediated By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Mediated By</em>'.
	 * @see asd.Mediation#getMediatedBy()
	 * @see #getMediation()
	 * @generated
	 */
	EReference getMediation_MediatedBy();

	/**
	 * Returns the meta object for the reference list '{@link asd.Mediation#getMediates <em>Mediates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Mediates</em>'.
	 * @see asd.Mediation#getMediates()
	 * @see #getMediation()
	 * @generated
	 */
	EReference getMediation_Mediates();

	/**
	 * Returns the meta object for the container reference '{@link asd.Mediation#getRelevantASD <em>Relevant ASD</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Relevant ASD</em>'.
	 * @see asd.Mediation#getRelevantASD()
	 * @see #getMediation()
	 * @generated
	 */
	EReference getMediation_RelevantASD();

	/**
	 * Returns the meta object for class '{@link asd.MediatedElement <em>Mediated Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mediated Element</em>'.
	 * @see asd.MediatedElement
	 * @generated
	 */
	EClass getMediatedElement();

	/**
	 * Returns the meta object for the reference list '{@link asd.MediatedElement#getMediations <em>Mediations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Mediations</em>'.
	 * @see asd.MediatedElement#getMediations()
	 * @see #getMediatedElement()
	 * @generated
	 */
	EReference getMediatedElement_Mediations();

	/**
	 * Returns the meta object for class '{@link asd.ASDmodelElement <em>AS Dmodel Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AS Dmodel Element</em>'.
	 * @see asd.ASDmodelElement
	 * @generated
	 */
	EClass getASDmodelElement();

	/**
	 * Returns the meta object for class '{@link asd.ASNetwork <em>AS Network</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AS Network</em>'.
	 * @see asd.ASNetwork
	 * @generated
	 */
	EClass getASNetwork();

	/**
	 * Returns the meta object for the reference list '{@link asd.ASNetwork#getASDspec <em>AS Dspec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>AS Dspec</em>'.
	 * @see asd.ASNetwork#getASDspec()
	 * @see #getASNetwork()
	 * @generated
	 */
	EReference getASNetwork_ASDspec();

	/**
	 * Returns the meta object for the containment reference list '{@link asd.ASNetwork#getAsdLayouts <em>Asd Layouts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Asd Layouts</em>'.
	 * @see asd.ASNetwork#getAsdLayouts()
	 * @see #getASNetwork()
	 * @generated
	 */
	EReference getASNetwork_AsdLayouts();

	/**
	 * Returns the meta object for class '{@link asd.ASDlayout <em>AS Dlayout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>AS Dlayout</em>'.
	 * @see asd.ASDlayout
	 * @generated
	 */
	EClass getASDlayout();

	/**
	 * Returns the meta object for the attribute '{@link asd.ASDlayout#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see asd.ASDlayout#getX()
	 * @see #getASDlayout()
	 * @generated
	 */
	EAttribute getASDlayout_X();

	/**
	 * Returns the meta object for the attribute '{@link asd.ASDlayout#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see asd.ASDlayout#getY()
	 * @see #getASDlayout()
	 * @generated
	 */
	EAttribute getASDlayout_Y();

	/**
	 * Returns the meta object for the attribute '{@link asd.ASDlayout#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see asd.ASDlayout#getWidth()
	 * @see #getASDlayout()
	 * @generated
	 */
	EAttribute getASDlayout_Width();

	/**
	 * Returns the meta object for the attribute '{@link asd.ASDlayout#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see asd.ASDlayout#getHeight()
	 * @see #getASDlayout()
	 * @generated
	 */
	EAttribute getASDlayout_Height();

	/**
	 * Returns the meta object for the attribute '{@link asd.ASDlayout#isCollapsed <em>Collapsed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Collapsed</em>'.
	 * @see asd.ASDlayout#isCollapsed()
	 * @see #getASDlayout()
	 * @generated
	 */
	EAttribute getASDlayout_Collapsed();

	/**
	 * Returns the meta object for the container reference '{@link asd.ASDlayout#getAsNetwork <em>As Network</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>As Network</em>'.
	 * @see asd.ASDlayout#getAsNetwork()
	 * @see #getASDlayout()
	 * @generated
	 */
	EReference getASDlayout_AsNetwork();

	/**
	 * Returns the meta object for the reference '{@link asd.ASDlayout#getAsDiagram <em>As Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>As Diagram</em>'.
	 * @see asd.ASDlayout#getAsDiagram()
	 * @see #getASDlayout()
	 * @generated
	 */
	EReference getASDlayout_AsDiagram();

	/**
	 * Returns the meta object for class '{@link asd.Tool <em>Tool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tool</em>'.
	 * @see asd.Tool
	 * @generated
	 */
	EClass getTool();

	/**
	 * Returns the meta object for the container reference '{@link asd.Tool#getAsdSpec <em>Asd Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Asd Spec</em>'.
	 * @see asd.Tool#getAsdSpec()
	 * @see #getTool()
	 * @generated
	 */
	EReference getTool_AsdSpec();

	/**
	 * Returns the meta object for class '{@link asd.Rule <em>Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rule</em>'.
	 * @see asd.Rule
	 * @generated
	 */
	EClass getRule();

	/**
	 * Returns the meta object for the reference list '{@link asd.Rule#getDols <em>Dols</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Dols</em>'.
	 * @see asd.Rule#getDols()
	 * @see #getRule()
	 * @generated
	 */
	EReference getRule_Dols();

	/**
	 * Returns the meta object for the container reference '{@link asd.Rule#getAsdSpec <em>Asd Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Asd Spec</em>'.
	 * @see asd.Rule#getAsdSpec()
	 * @see #getRule()
	 * @generated
	 */
	EReference getRule_AsdSpec();

	/**
	 * Returns the meta object for class '{@link asd.DivisionOfLabour <em>Division Of Labour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Division Of Labour</em>'.
	 * @see asd.DivisionOfLabour
	 * @generated
	 */
	EClass getDivisionOfLabour();

	/**
	 * Returns the meta object for the reference list '{@link asd.DivisionOfLabour#getRules <em>Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Rules</em>'.
	 * @see asd.DivisionOfLabour#getRules()
	 * @see #getDivisionOfLabour()
	 * @generated
	 */
	EReference getDivisionOfLabour_Rules();

	/**
	 * Returns the meta object for the reference list '{@link asd.DivisionOfLabour#getRefinedDiagrams <em>Refined Diagrams</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Refined Diagrams</em>'.
	 * @see asd.DivisionOfLabour#getRefinedDiagrams()
	 * @see #getDivisionOfLabour()
	 * @generated
	 */
	EReference getDivisionOfLabour_RefinedDiagrams();

	/**
	 * Returns the meta object for the reference '{@link asd.DivisionOfLabour#getPerformedBy <em>Performed By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Performed By</em>'.
	 * @see asd.DivisionOfLabour#getPerformedBy()
	 * @see #getDivisionOfLabour()
	 * @generated
	 */
	EReference getDivisionOfLabour_PerformedBy();

	/**
	 * Returns the meta object for the container reference '{@link asd.DivisionOfLabour#getAsdSpec <em>Asd Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Asd Spec</em>'.
	 * @see asd.DivisionOfLabour#getAsdSpec()
	 * @see #getDivisionOfLabour()
	 * @generated
	 */
	EReference getDivisionOfLabour_AsdSpec();

	/**
	 * Returns the meta object for class '{@link asd.Community <em>Community</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Community</em>'.
	 * @see asd.Community
	 * @generated
	 */
	EClass getCommunity();

	/**
	 * Returns the meta object for the reference list '{@link asd.Community#getPerforms <em>Performs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Performs</em>'.
	 * @see asd.Community#getPerforms()
	 * @see #getCommunity()
	 * @generated
	 */
	EReference getCommunity_Performs();

	/**
	 * Returns the meta object for the container reference '{@link asd.Community#getAsdSpec <em>Asd Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Asd Spec</em>'.
	 * @see asd.Community#getAsdSpec()
	 * @see #getCommunity()
	 * @generated
	 */
	EReference getCommunity_AsdSpec();

	/**
	 * Returns the meta object for the reference '{@link asd.Community#getSubject <em>Subject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Subject</em>'.
	 * @see asd.Community#getSubject()
	 * @see #getCommunity()
	 * @generated
	 */
	EReference getCommunity_Subject();

	/**
	 * Returns the meta object for class '{@link asd.Subject <em>Subject</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Subject</em>'.
	 * @see asd.Subject
	 * @generated
	 */
	EClass getSubject();

	/**
	 * Returns the meta object for the container reference '{@link asd.Subject#getAsdSpec <em>Asd Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Asd Spec</em>'.
	 * @see asd.Subject#getAsdSpec()
	 * @see #getSubject()
	 * @generated
	 */
	EReference getSubject_AsdSpec();

	/**
	 * Returns the meta object for the reference '{@link asd.Subject#getMemberOf <em>Member Of</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Member Of</em>'.
	 * @see asd.Subject#getMemberOf()
	 * @see #getSubject()
	 * @generated
	 */
	EReference getSubject_MemberOf();

	/**
	 * Returns the meta object for the reference list '{@link asd.Subject#getObjects <em>Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Objects</em>'.
	 * @see asd.Subject#getObjects()
	 * @see #getSubject()
	 * @generated
	 */
	EReference getSubject_Objects();

	/**
	 * Returns the meta object for class '{@link asd.Object <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Object</em>'.
	 * @see asd.Object
	 * @generated
	 */
	EClass getObject();

	/**
	 * Returns the meta object for the container reference '{@link asd.Object#getAsdSpec <em>Asd Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Asd Spec</em>'.
	 * @see asd.Object#getAsdSpec()
	 * @see #getObject()
	 * @generated
	 */
	EReference getObject_AsdSpec();

	/**
	 * Returns the meta object for the reference list '{@link asd.Object#getOutcomes <em>Outcomes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Outcomes</em>'.
	 * @see asd.Object#getOutcomes()
	 * @see #getObject()
	 * @generated
	 */
	EReference getObject_Outcomes();

	/**
	 * Returns the meta object for the reference list '{@link asd.Object#getSubjects <em>Subjects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Subjects</em>'.
	 * @see asd.Object#getSubjects()
	 * @see #getObject()
	 * @generated
	 */
	EReference getObject_Subjects();

	/**
	 * Returns the meta object for class '{@link asd.Motivation <em>Motivation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Motivation</em>'.
	 * @see asd.Motivation
	 * @generated
	 */
	EClass getMotivation();

	/**
	 * Returns the meta object for the container reference '{@link asd.Motivation#getAsdSpec <em>Asd Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Asd Spec</em>'.
	 * @see asd.Motivation#getAsdSpec()
	 * @see #getMotivation()
	 * @generated
	 */
	EReference getMotivation_AsdSpec();

	/**
	 * Returns the meta object for class '{@link asd.Outcome <em>Outcome</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Outcome</em>'.
	 * @see asd.Outcome
	 * @generated
	 */
	EClass getOutcome();

	/**
	 * Returns the meta object for the reference list '{@link asd.Outcome#getEnabledElements <em>Enabled Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Enabled Elements</em>'.
	 * @see asd.Outcome#getEnabledElements()
	 * @see #getOutcome()
	 * @generated
	 */
	EReference getOutcome_EnabledElements();

	/**
	 * Returns the meta object for the container reference '{@link asd.Outcome#getAsdSpec <em>Asd Spec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Asd Spec</em>'.
	 * @see asd.Outcome#getAsdSpec()
	 * @see #getOutcome()
	 * @generated
	 */
	EReference getOutcome_AsdSpec();

	/**
	 * Returns the meta object for the reference list '{@link asd.Outcome#getObjects <em>Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Objects</em>'.
	 * @see asd.Outcome#getObjects()
	 * @see #getOutcome()
	 * @generated
	 */
	EReference getOutcome_Objects();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AsdFactory getAsdFactory();

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
		 * The meta object literal for the '{@link asd.impl.ASDspecImpl <em>AS Dspec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asd.impl.ASDspecImpl
		 * @see asd.impl.AsdPackageImpl#getASDspec()
		 * @generated
		 */
		EClass AS_DSPEC = eINSTANCE.getASDspec();

		/**
		 * The meta object literal for the '<em><b>AS Network</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DSPEC__AS_NETWORK = eINSTANCE.getASDspec_ASNetwork();

		/**
		 * The meta object literal for the '<em><b>Motivation</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DSPEC__MOTIVATION = eINSTANCE.getASDspec_Motivation();

		/**
		 * The meta object literal for the '<em><b>Outcome</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DSPEC__OUTCOME = eINSTANCE.getASDspec_Outcome();

		/**
		 * The meta object literal for the '<em><b>Dols</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DSPEC__DOLS = eINSTANCE.getASDspec_Dols();

		/**
		 * The meta object literal for the '<em><b>Subjects</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DSPEC__SUBJECTS = eINSTANCE.getASDspec_Subjects();

		/**
		 * The meta object literal for the '<em><b>Communities</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DSPEC__COMMUNITIES = eINSTANCE.getASDspec_Communities();

		/**
		 * The meta object literal for the '<em><b>Objects</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DSPEC__OBJECTS = eINSTANCE.getASDspec_Objects();

		/**
		 * The meta object literal for the '<em><b>Outcomes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DSPEC__OUTCOMES = eINSTANCE.getASDspec_Outcomes();

		/**
		 * The meta object literal for the '<em><b>Motivations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DSPEC__MOTIVATIONS = eINSTANCE.getASDspec_Motivations();

		/**
		 * The meta object literal for the '<em><b>Tools</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DSPEC__TOOLS = eINSTANCE.getASDspec_Tools();

		/**
		 * The meta object literal for the '<em><b>Rules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DSPEC__RULES = eINSTANCE.getASDspec_Rules();

		/**
		 * The meta object literal for the '{@link asd.impl.ASDelementImpl <em>AS Delement</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asd.impl.ASDelementImpl
		 * @see asd.impl.AsdPackageImpl#getASDelement()
		 * @generated
		 */
		EClass AS_DELEMENT = eINSTANCE.getASDelement();

		/**
		 * The meta object literal for the '<em><b>Diagrams</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DELEMENT__DIAGRAMS = eINSTANCE.getASDelement_Diagrams();

		/**
		 * The meta object literal for the '<em><b>Parent Elements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DELEMENT__PARENT_ELEMENTS = eINSTANCE.getASDelement_ParentElements();

		/**
		 * The meta object literal for the '<em><b>Refined Elements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DELEMENT__REFINED_ELEMENTS = eINSTANCE.getASDelement_RefinedElements();

		/**
		 * The meta object literal for the '<em><b>Required Outcomes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DELEMENT__REQUIRED_OUTCOMES = eINSTANCE.getASDelement_RequiredOutcomes();

		/**
		 * The meta object literal for the '{@link asd.impl.ASDiagramImpl <em>AS Diagram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asd.impl.ASDiagramImpl
		 * @see asd.impl.AsdPackageImpl#getASDiagram()
		 * @generated
		 */
		EClass AS_DIAGRAM = eINSTANCE.getASDiagram();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DIAGRAM__ELEMENTS = eINSTANCE.getASDiagram_Elements();

		/**
		 * The meta object literal for the '<em><b>ASD</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DIAGRAM__ASD = eINSTANCE.getASDiagram_ASD();

		/**
		 * The meta object literal for the '<em><b>Related ASD</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DIAGRAM__RELATED_ASD = eINSTANCE.getASDiagram_RelatedASD();

		/**
		 * The meta object literal for the '<em><b>Mediations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DIAGRAM__MEDIATIONS = eINSTANCE.getASDiagram_Mediations();

		/**
		 * The meta object literal for the '<em><b>Asd Layouts</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DIAGRAM__ASD_LAYOUTS = eINSTANCE.getASDiagram_AsdLayouts();

		/**
		 * The meta object literal for the '<em><b>Parent Do Ls</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DIAGRAM__PARENT_DO_LS = eINSTANCE.getASDiagram_ParentDoLs();

		/**
		 * The meta object literal for the '{@link asd.impl.MediatingElementImpl <em>Mediating Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asd.impl.MediatingElementImpl
		 * @see asd.impl.AsdPackageImpl#getMediatingElement()
		 * @generated
		 */
		EClass MEDIATING_ELEMENT = eINSTANCE.getMediatingElement();

		/**
		 * The meta object literal for the '<em><b>Mediations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEDIATING_ELEMENT__MEDIATIONS = eINSTANCE.getMediatingElement_Mediations();

		/**
		 * The meta object literal for the '{@link asd.impl.MediationImpl <em>Mediation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asd.impl.MediationImpl
		 * @see asd.impl.AsdPackageImpl#getMediation()
		 * @generated
		 */
		EClass MEDIATION = eINSTANCE.getMediation();

		/**
		 * The meta object literal for the '<em><b>Mediated By</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEDIATION__MEDIATED_BY = eINSTANCE.getMediation_MediatedBy();

		/**
		 * The meta object literal for the '<em><b>Mediates</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEDIATION__MEDIATES = eINSTANCE.getMediation_Mediates();

		/**
		 * The meta object literal for the '<em><b>Relevant ASD</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEDIATION__RELEVANT_ASD = eINSTANCE.getMediation_RelevantASD();

		/**
		 * The meta object literal for the '{@link asd.impl.MediatedElementImpl <em>Mediated Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asd.impl.MediatedElementImpl
		 * @see asd.impl.AsdPackageImpl#getMediatedElement()
		 * @generated
		 */
		EClass MEDIATED_ELEMENT = eINSTANCE.getMediatedElement();

		/**
		 * The meta object literal for the '<em><b>Mediations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEDIATED_ELEMENT__MEDIATIONS = eINSTANCE.getMediatedElement_Mediations();

		/**
		 * The meta object literal for the '{@link asd.impl.ASDmodelElementImpl <em>AS Dmodel Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asd.impl.ASDmodelElementImpl
		 * @see asd.impl.AsdPackageImpl#getASDmodelElement()
		 * @generated
		 */
		EClass AS_DMODEL_ELEMENT = eINSTANCE.getASDmodelElement();

		/**
		 * The meta object literal for the '{@link asd.impl.ASNetworkImpl <em>AS Network</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asd.impl.ASNetworkImpl
		 * @see asd.impl.AsdPackageImpl#getASNetwork()
		 * @generated
		 */
		EClass AS_NETWORK = eINSTANCE.getASNetwork();

		/**
		 * The meta object literal for the '<em><b>AS Dspec</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_NETWORK__AS_DSPEC = eINSTANCE.getASNetwork_ASDspec();

		/**
		 * The meta object literal for the '<em><b>Asd Layouts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_NETWORK__ASD_LAYOUTS = eINSTANCE.getASNetwork_AsdLayouts();

		/**
		 * The meta object literal for the '{@link asd.impl.ASDlayoutImpl <em>AS Dlayout</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asd.impl.ASDlayoutImpl
		 * @see asd.impl.AsdPackageImpl#getASDlayout()
		 * @generated
		 */
		EClass AS_DLAYOUT = eINSTANCE.getASDlayout();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AS_DLAYOUT__X = eINSTANCE.getASDlayout_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AS_DLAYOUT__Y = eINSTANCE.getASDlayout_Y();

		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AS_DLAYOUT__WIDTH = eINSTANCE.getASDlayout_Width();

		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AS_DLAYOUT__HEIGHT = eINSTANCE.getASDlayout_Height();

		/**
		 * The meta object literal for the '<em><b>Collapsed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AS_DLAYOUT__COLLAPSED = eINSTANCE.getASDlayout_Collapsed();

		/**
		 * The meta object literal for the '<em><b>As Network</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DLAYOUT__AS_NETWORK = eINSTANCE.getASDlayout_AsNetwork();

		/**
		 * The meta object literal for the '<em><b>As Diagram</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AS_DLAYOUT__AS_DIAGRAM = eINSTANCE.getASDlayout_AsDiagram();

		/**
		 * The meta object literal for the '{@link asd.impl.ToolImpl <em>Tool</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asd.impl.ToolImpl
		 * @see asd.impl.AsdPackageImpl#getTool()
		 * @generated
		 */
		EClass TOOL = eINSTANCE.getTool();

		/**
		 * The meta object literal for the '<em><b>Asd Spec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOOL__ASD_SPEC = eINSTANCE.getTool_AsdSpec();

		/**
		 * The meta object literal for the '{@link asd.impl.RuleImpl <em>Rule</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asd.impl.RuleImpl
		 * @see asd.impl.AsdPackageImpl#getRule()
		 * @generated
		 */
		EClass RULE = eINSTANCE.getRule();

		/**
		 * The meta object literal for the '<em><b>Dols</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE__DOLS = eINSTANCE.getRule_Dols();

		/**
		 * The meta object literal for the '<em><b>Asd Spec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RULE__ASD_SPEC = eINSTANCE.getRule_AsdSpec();

		/**
		 * The meta object literal for the '{@link asd.impl.DivisionOfLabourImpl <em>Division Of Labour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asd.impl.DivisionOfLabourImpl
		 * @see asd.impl.AsdPackageImpl#getDivisionOfLabour()
		 * @generated
		 */
		EClass DIVISION_OF_LABOUR = eINSTANCE.getDivisionOfLabour();

		/**
		 * The meta object literal for the '<em><b>Rules</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIVISION_OF_LABOUR__RULES = eINSTANCE.getDivisionOfLabour_Rules();

		/**
		 * The meta object literal for the '<em><b>Refined Diagrams</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIVISION_OF_LABOUR__REFINED_DIAGRAMS = eINSTANCE.getDivisionOfLabour_RefinedDiagrams();

		/**
		 * The meta object literal for the '<em><b>Performed By</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIVISION_OF_LABOUR__PERFORMED_BY = eINSTANCE.getDivisionOfLabour_PerformedBy();

		/**
		 * The meta object literal for the '<em><b>Asd Spec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIVISION_OF_LABOUR__ASD_SPEC = eINSTANCE.getDivisionOfLabour_AsdSpec();

		/**
		 * The meta object literal for the '{@link asd.impl.CommunityImpl <em>Community</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asd.impl.CommunityImpl
		 * @see asd.impl.AsdPackageImpl#getCommunity()
		 * @generated
		 */
		EClass COMMUNITY = eINSTANCE.getCommunity();

		/**
		 * The meta object literal for the '<em><b>Performs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMMUNITY__PERFORMS = eINSTANCE.getCommunity_Performs();

		/**
		 * The meta object literal for the '<em><b>Asd Spec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMMUNITY__ASD_SPEC = eINSTANCE.getCommunity_AsdSpec();

		/**
		 * The meta object literal for the '<em><b>Subject</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMMUNITY__SUBJECT = eINSTANCE.getCommunity_Subject();

		/**
		 * The meta object literal for the '{@link asd.impl.SubjectImpl <em>Subject</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asd.impl.SubjectImpl
		 * @see asd.impl.AsdPackageImpl#getSubject()
		 * @generated
		 */
		EClass SUBJECT = eINSTANCE.getSubject();

		/**
		 * The meta object literal for the '<em><b>Asd Spec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUBJECT__ASD_SPEC = eINSTANCE.getSubject_AsdSpec();

		/**
		 * The meta object literal for the '<em><b>Member Of</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUBJECT__MEMBER_OF = eINSTANCE.getSubject_MemberOf();

		/**
		 * The meta object literal for the '<em><b>Objects</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUBJECT__OBJECTS = eINSTANCE.getSubject_Objects();

		/**
		 * The meta object literal for the '{@link asd.impl.ObjectImpl <em>Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asd.impl.ObjectImpl
		 * @see asd.impl.AsdPackageImpl#getObject()
		 * @generated
		 */
		EClass OBJECT = eINSTANCE.getObject();

		/**
		 * The meta object literal for the '<em><b>Asd Spec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OBJECT__ASD_SPEC = eINSTANCE.getObject_AsdSpec();

		/**
		 * The meta object literal for the '<em><b>Outcomes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OBJECT__OUTCOMES = eINSTANCE.getObject_Outcomes();

		/**
		 * The meta object literal for the '<em><b>Subjects</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OBJECT__SUBJECTS = eINSTANCE.getObject_Subjects();

		/**
		 * The meta object literal for the '{@link asd.impl.MotivationImpl <em>Motivation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asd.impl.MotivationImpl
		 * @see asd.impl.AsdPackageImpl#getMotivation()
		 * @generated
		 */
		EClass MOTIVATION = eINSTANCE.getMotivation();

		/**
		 * The meta object literal for the '<em><b>Asd Spec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MOTIVATION__ASD_SPEC = eINSTANCE.getMotivation_AsdSpec();

		/**
		 * The meta object literal for the '{@link asd.impl.OutcomeImpl <em>Outcome</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see asd.impl.OutcomeImpl
		 * @see asd.impl.AsdPackageImpl#getOutcome()
		 * @generated
		 */
		EClass OUTCOME = eINSTANCE.getOutcome();

		/**
		 * The meta object literal for the '<em><b>Enabled Elements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUTCOME__ENABLED_ELEMENTS = eINSTANCE.getOutcome_EnabledElements();

		/**
		 * The meta object literal for the '<em><b>Asd Spec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUTCOME__ASD_SPEC = eINSTANCE.getOutcome_AsdSpec();

		/**
		 * The meta object literal for the '<em><b>Objects</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OUTCOME__OBJECTS = eINSTANCE.getOutcome_Objects();

	}

} //AsdPackage
