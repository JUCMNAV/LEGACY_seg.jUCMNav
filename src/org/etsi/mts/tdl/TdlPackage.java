/**
 */
package org.etsi.mts.tdl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.etsi.mts.tdl.TdlFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore validationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL'"
 * @generated
 */
public interface TdlPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "tdl";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.etsi.org/spec/TDL/20130606";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "tdl";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TdlPackage eINSTANCE = org.etsi.mts.tdl.impl.TdlPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.ElementImpl <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.ElementImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getElement()
	 * @generated
	 */
	int ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__COMMENT = 0;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__ANNOTATION = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__NAME = 2;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.CommentImpl <em>Comment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.CommentImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getComment()
	 * @generated
	 */
	int COMMENT = 1;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__COMMENT = ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__ANNOTATION = ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__NAME = ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Body</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__BODY = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Commented Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__COMMENTED_ELEMENT = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.AnnotationImpl <em>Annotation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.AnnotationImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getAnnotation()
	 * @generated
	 */
	int ANNOTATION = 2;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__COMMENT = ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__ANNOTATION = ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__NAME = ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__VALUE = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Key</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__KEY = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Annotated Element</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION__ANNOTATED_ELEMENT = ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Annotation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.PackageableElementImpl <em>Packageable Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.PackageableElementImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getPackageableElement()
	 * @generated
	 */
	int PACKAGEABLE_ELEMENT = 4;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGEABLE_ELEMENT__COMMENT = ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGEABLE_ELEMENT__ANNOTATION = ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGEABLE_ELEMENT__NAME = ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGEABLE_ELEMENT__QUALIFIED_NAME = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Owning Package</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGEABLE_ELEMENT__OWNING_PACKAGE = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Packageable Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGEABLE_ELEMENT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.AnnotationTypeImpl <em>Annotation Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.AnnotationTypeImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getAnnotationType()
	 * @generated
	 */
	int ANNOTATION_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_TYPE__COMMENT = PACKAGEABLE_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_TYPE__ANNOTATION = PACKAGEABLE_ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_TYPE__NAME = PACKAGEABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_TYPE__QUALIFIED_NAME = PACKAGEABLE_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owning Package</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_TYPE__OWNING_PACKAGE = PACKAGEABLE_ELEMENT__OWNING_PACKAGE;

	/**
	 * The number of structural features of the '<em>Annotation Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ANNOTATION_TYPE_FEATURE_COUNT = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.PackageImpl <em>Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.PackageImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getPackage()
	 * @generated
	 */
	int PACKAGE = 5;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__COMMENT = PACKAGEABLE_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__ANNOTATION = PACKAGEABLE_ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__NAME = PACKAGEABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__QUALIFIED_NAME = PACKAGEABLE_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owning Package</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__OWNING_PACKAGE = PACKAGEABLE_ELEMENT__OWNING_PACKAGE;

	/**
	 * The feature id for the '<em><b>Import</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__IMPORT = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Packaged Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__PACKAGED_ELEMENTS = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_FEATURE_COUNT = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.ElementImportImpl <em>Element Import</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.ElementImportImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getElementImport()
	 * @generated
	 */
	int ELEMENT_IMPORT = 6;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_IMPORT__COMMENT = ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_IMPORT__ANNOTATION = ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_IMPORT__NAME = ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Imported Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_IMPORT__IMPORTED_ELEMENT = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Element Import</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_IMPORT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.TestObjectiveImpl <em>Test Objective</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.TestObjectiveImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTestObjective()
	 * @generated
	 */
	int TEST_OBJECTIVE = 7;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECTIVE__COMMENT = PACKAGEABLE_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECTIVE__ANNOTATION = PACKAGEABLE_ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECTIVE__NAME = PACKAGEABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECTIVE__QUALIFIED_NAME = PACKAGEABLE_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owning Package</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECTIVE__OWNING_PACKAGE = PACKAGEABLE_ELEMENT__OWNING_PACKAGE;

	/**
	 * The feature id for the '<em><b>Objective URI</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECTIVE__OBJECTIVE_URI = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECTIVE__DESCRIPTION = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Test Objective</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECTIVE_FEATURE_COUNT = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.TestObjectiveRealizerImpl <em>Test Objective Realizer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.TestObjectiveRealizerImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTestObjectiveRealizer()
	 * @generated
	 */
	int TEST_OBJECTIVE_REALIZER = 8;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECTIVE_REALIZER__COMMENT = ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECTIVE_REALIZER__ANNOTATION = ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECTIVE_REALIZER__NAME = ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECTIVE_REALIZER__TEST_OBJECTIVE = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Test Objective Realizer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_OBJECTIVE_REALIZER_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.DataElementImpl <em>Data Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.DataElementImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getDataElement()
	 * @generated
	 */
	int DATA_ELEMENT = 11;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_ELEMENT__COMMENT = ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_ELEMENT__ANNOTATION = ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_ELEMENT__NAME = ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>Data Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_ELEMENT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.MappableDataElementImpl <em>Mappable Data Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.MappableDataElementImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getMappableDataElement()
	 * @generated
	 */
	int MAPPABLE_DATA_ELEMENT = 10;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPABLE_DATA_ELEMENT__COMMENT = DATA_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPABLE_DATA_ELEMENT__ANNOTATION = DATA_ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPABLE_DATA_ELEMENT__NAME = DATA_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>Mappable Data Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAPPABLE_DATA_ELEMENT_FEATURE_COUNT = DATA_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.DataInstanceImpl <em>Data Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.DataInstanceImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getDataInstance()
	 * @generated
	 */
	int DATA_INSTANCE = 9;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_INSTANCE__COMMENT = MAPPABLE_DATA_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_INSTANCE__ANNOTATION = MAPPABLE_DATA_ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_INSTANCE__NAME = MAPPABLE_DATA_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_INSTANCE__PARAMETER = MAPPABLE_DATA_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Data Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_INSTANCE_FEATURE_COUNT = MAPPABLE_DATA_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.DataSetImpl <em>Data Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.DataSetImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getDataSet()
	 * @generated
	 */
	int DATA_SET = 12;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SET__COMMENT = PACKAGEABLE_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SET__ANNOTATION = PACKAGEABLE_ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SET__NAME = PACKAGEABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SET__QUALIFIED_NAME = PACKAGEABLE_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owning Package</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SET__OWNING_PACKAGE = PACKAGEABLE_ELEMENT__OWNING_PACKAGE;

	/**
	 * The feature id for the '<em><b>Data Instance</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SET__DATA_INSTANCE = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Data Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SET_FEATURE_COUNT = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.DataResourceMappingImpl <em>Data Resource Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.DataResourceMappingImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getDataResourceMapping()
	 * @generated
	 */
	int DATA_RESOURCE_MAPPING = 13;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_RESOURCE_MAPPING__COMMENT = PACKAGEABLE_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_RESOURCE_MAPPING__ANNOTATION = PACKAGEABLE_ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_RESOURCE_MAPPING__NAME = PACKAGEABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_RESOURCE_MAPPING__QUALIFIED_NAME = PACKAGEABLE_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owning Package</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_RESOURCE_MAPPING__OWNING_PACKAGE = PACKAGEABLE_ELEMENT__OWNING_PACKAGE;

	/**
	 * The feature id for the '<em><b>Resource URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_RESOURCE_MAPPING__RESOURCE_URI = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Data Resource Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_RESOURCE_MAPPING_FEATURE_COUNT = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.DataElementMappingImpl <em>Data Element Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.DataElementMappingImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getDataElementMapping()
	 * @generated
	 */
	int DATA_ELEMENT_MAPPING = 14;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_ELEMENT_MAPPING__COMMENT = PACKAGEABLE_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_ELEMENT_MAPPING__ANNOTATION = PACKAGEABLE_ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_ELEMENT_MAPPING__NAME = PACKAGEABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_ELEMENT_MAPPING__QUALIFIED_NAME = PACKAGEABLE_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owning Package</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_ELEMENT_MAPPING__OWNING_PACKAGE = PACKAGEABLE_ELEMENT__OWNING_PACKAGE;

	/**
	 * The feature id for the '<em><b>Mappable Data Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_ELEMENT_MAPPING__MAPPABLE_DATA_ELEMENT = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Element URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_ELEMENT_MAPPING__ELEMENT_URI = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Data Resource Mapping</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_ELEMENT_MAPPING__DATA_RESOURCE_MAPPING = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Data Element Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_ELEMENT_MAPPING_FEATURE_COUNT = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.DataProxyImpl <em>Data Proxy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.DataProxyImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getDataProxy()
	 * @generated
	 */
	int DATA_PROXY = 15;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROXY__COMMENT = DATA_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROXY__ANNOTATION = DATA_ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROXY__NAME = DATA_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Data Set</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROXY__DATA_SET = DATA_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Data Proxy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROXY_FEATURE_COUNT = DATA_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.TimerImpl <em>Timer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.TimerImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTimer()
	 * @generated
	 */
	int TIMER = 16;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER__COMMENT = ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER__ANNOTATION = ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER__NAME = ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Component Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER__COMPONENT_TYPE = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Timer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.ComponentTypeImpl <em>Component Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.ComponentTypeImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getComponentType()
	 * @generated
	 */
	int COMPONENT_TYPE = 17;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__COMMENT = PACKAGEABLE_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__ANNOTATION = PACKAGEABLE_ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__NAME = PACKAGEABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__QUALIFIED_NAME = PACKAGEABLE_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owning Package</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__OWNING_PACKAGE = PACKAGEABLE_ELEMENT__OWNING_PACKAGE;

	/**
	 * The feature id for the '<em><b>Gate Type</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__GATE_TYPE = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Timer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__TIMER = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Component Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE_FEATURE_COUNT = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.GateTypeImpl <em>Gate Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.GateTypeImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getGateType()
	 * @generated
	 */
	int GATE_TYPE = 18;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE_TYPE__COMMENT = PACKAGEABLE_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE_TYPE__ANNOTATION = PACKAGEABLE_ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE_TYPE__NAME = PACKAGEABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE_TYPE__QUALIFIED_NAME = PACKAGEABLE_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owning Package</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE_TYPE__OWNING_PACKAGE = PACKAGEABLE_ELEMENT__OWNING_PACKAGE;

	/**
	 * The feature id for the '<em><b>Data Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE_TYPE__DATA_SET = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Gate Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE_TYPE_FEATURE_COUNT = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.BehaviourImpl <em>Behaviour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.BehaviourImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getBehaviour()
	 * @generated
	 */
	int BEHAVIOUR = 21;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOUR__COMMENT = TEST_OBJECTIVE_REALIZER__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOUR__ANNOTATION = TEST_OBJECTIVE_REALIZER__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOUR__NAME = TEST_OBJECTIVE_REALIZER__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOUR__TEST_OBJECTIVE = TEST_OBJECTIVE_REALIZER__TEST_OBJECTIVE;

	/**
	 * The number of structural features of the '<em>Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOUR_FEATURE_COUNT = TEST_OBJECTIVE_REALIZER_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.AtomicBehaviourImpl <em>Atomic Behaviour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.AtomicBehaviourImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getAtomicBehaviour()
	 * @generated
	 */
	int ATOMIC_BEHAVIOUR = 20;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOMIC_BEHAVIOUR__COMMENT = BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOMIC_BEHAVIOUR__ANNOTATION = BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOMIC_BEHAVIOUR__NAME = BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOMIC_BEHAVIOUR__TEST_OBJECTIVE = BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Time Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOMIC_BEHAVIOUR__TIME_CONSTRAINT = BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Atomic Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATOMIC_BEHAVIOUR_FEATURE_COUNT = BEHAVIOUR_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.TimerOperationImpl <em>Timer Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.TimerOperationImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTimerOperation()
	 * @generated
	 */
	int TIMER_OPERATION = 19;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_OPERATION__COMMENT = ATOMIC_BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_OPERATION__ANNOTATION = ATOMIC_BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_OPERATION__NAME = ATOMIC_BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_OPERATION__TEST_OBJECTIVE = ATOMIC_BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Time Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_OPERATION__TIME_CONSTRAINT = ATOMIC_BEHAVIOUR__TIME_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Timer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_OPERATION__TIMER = ATOMIC_BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Timer Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_OPERATION_FEATURE_COUNT = ATOMIC_BEHAVIOUR_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.TimeConstraintImpl <em>Time Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.TimeConstraintImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTimeConstraint()
	 * @generated
	 */
	int TIME_CONSTRAINT = 22;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_CONSTRAINT__COMMENT = ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_CONSTRAINT__ANNOTATION = ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_CONSTRAINT__NAME = ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_CONSTRAINT__EXPRESSION = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Atomic Behaviour</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_CONSTRAINT__ATOMIC_BEHAVIOUR = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Time Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_CONSTRAINT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.TimerStartImpl <em>Timer Start</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.TimerStartImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTimerStart()
	 * @generated
	 */
	int TIMER_START = 23;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_START__COMMENT = TIMER_OPERATION__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_START__ANNOTATION = TIMER_OPERATION__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_START__NAME = TIMER_OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_START__TEST_OBJECTIVE = TIMER_OPERATION__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Time Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_START__TIME_CONSTRAINT = TIMER_OPERATION__TIME_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Timer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_START__TIMER = TIMER_OPERATION__TIMER;

	/**
	 * The feature id for the '<em><b>Period</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_START__PERIOD = TIMER_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Timer Start</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_START_FEATURE_COUNT = TIMER_OPERATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.TimeImpl <em>Time</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.TimeImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTime()
	 * @generated
	 */
	int TIME = 24;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME__COMMENT = ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME__ANNOTATION = ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME__NAME = ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME__VALUE = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Unit</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME__UNIT = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Time</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.TimeUnitImpl <em>Time Unit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.TimeUnitImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTimeUnit()
	 * @generated
	 */
	int TIME_UNIT = 25;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_UNIT__COMMENT = PACKAGEABLE_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_UNIT__ANNOTATION = PACKAGEABLE_ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_UNIT__NAME = PACKAGEABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_UNIT__QUALIFIED_NAME = PACKAGEABLE_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owning Package</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_UNIT__OWNING_PACKAGE = PACKAGEABLE_ELEMENT__OWNING_PACKAGE;

	/**
	 * The number of structural features of the '<em>Time Unit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_UNIT_FEATURE_COUNT = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.TimerStopImpl <em>Timer Stop</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.TimerStopImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTimerStop()
	 * @generated
	 */
	int TIMER_STOP = 26;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_STOP__COMMENT = TIMER_OPERATION__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_STOP__ANNOTATION = TIMER_OPERATION__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_STOP__NAME = TIMER_OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_STOP__TEST_OBJECTIVE = TIMER_OPERATION__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Time Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_STOP__TIME_CONSTRAINT = TIMER_OPERATION__TIME_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Timer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_STOP__TIMER = TIMER_OPERATION__TIMER;

	/**
	 * The number of structural features of the '<em>Timer Stop</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMER_STOP_FEATURE_COUNT = TIMER_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.TimeOutImpl <em>Time Out</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.TimeOutImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTimeOut()
	 * @generated
	 */
	int TIME_OUT = 27;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_OUT__COMMENT = TIMER_OPERATION__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_OUT__ANNOTATION = TIMER_OPERATION__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_OUT__NAME = TIMER_OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_OUT__TEST_OBJECTIVE = TIMER_OPERATION__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Time Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_OUT__TIME_CONSTRAINT = TIMER_OPERATION__TIME_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Timer</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_OUT__TIMER = TIMER_OPERATION__TIMER;

	/**
	 * The number of structural features of the '<em>Time Out</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_OUT_FEATURE_COUNT = TIMER_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.TimeOperationImpl <em>Time Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.TimeOperationImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTimeOperation()
	 * @generated
	 */
	int TIME_OPERATION = 28;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_OPERATION__COMMENT = ATOMIC_BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_OPERATION__ANNOTATION = ATOMIC_BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_OPERATION__NAME = ATOMIC_BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_OPERATION__TEST_OBJECTIVE = ATOMIC_BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Time Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_OPERATION__TIME_CONSTRAINT = ATOMIC_BEHAVIOUR__TIME_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Gate Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_OPERATION__GATE_INSTANCE = ATOMIC_BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Period</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_OPERATION__PERIOD = ATOMIC_BEHAVIOUR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Component Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_OPERATION__COMPONENT_INSTANCE = ATOMIC_BEHAVIOUR_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Time Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIME_OPERATION_FEATURE_COUNT = ATOMIC_BEHAVIOUR_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.GateInstanceImpl <em>Gate Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.GateInstanceImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getGateInstance()
	 * @generated
	 */
	int GATE_INSTANCE = 29;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE_INSTANCE__COMMENT = ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE_INSTANCE__ANNOTATION = ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE_INSTANCE__NAME = ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE_INSTANCE__TYPE = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Component Instance</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE_INSTANCE__COMPONENT_INSTANCE = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Gate Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GATE_INSTANCE_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.ComponentInstanceImpl <em>Component Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.ComponentInstanceImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getComponentInstance()
	 * @generated
	 */
	int COMPONENT_INSTANCE = 30;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INSTANCE__COMMENT = ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INSTANCE__ANNOTATION = ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INSTANCE__NAME = ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INSTANCE__TYPE = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INSTANCE__ROLE = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Timer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INSTANCE__TIMER = ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Gate Instance</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INSTANCE__GATE_INSTANCE = ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Component Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_INSTANCE_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.WaitImpl <em>Wait</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.WaitImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getWait()
	 * @generated
	 */
	int WAIT = 31;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAIT__COMMENT = TIME_OPERATION__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAIT__ANNOTATION = TIME_OPERATION__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAIT__NAME = TIME_OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAIT__TEST_OBJECTIVE = TIME_OPERATION__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Time Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAIT__TIME_CONSTRAINT = TIME_OPERATION__TIME_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Gate Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAIT__GATE_INSTANCE = TIME_OPERATION__GATE_INSTANCE;

	/**
	 * The feature id for the '<em><b>Period</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAIT__PERIOD = TIME_OPERATION__PERIOD;

	/**
	 * The feature id for the '<em><b>Component Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAIT__COMPONENT_INSTANCE = TIME_OPERATION__COMPONENT_INSTANCE;

	/**
	 * The number of structural features of the '<em>Wait</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAIT_FEATURE_COUNT = TIME_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.QuiescenceImpl <em>Quiescence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.QuiescenceImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getQuiescence()
	 * @generated
	 */
	int QUIESCENCE = 32;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUIESCENCE__COMMENT = TIME_OPERATION__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUIESCENCE__ANNOTATION = TIME_OPERATION__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUIESCENCE__NAME = TIME_OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUIESCENCE__TEST_OBJECTIVE = TIME_OPERATION__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Time Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUIESCENCE__TIME_CONSTRAINT = TIME_OPERATION__TIME_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Gate Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUIESCENCE__GATE_INSTANCE = TIME_OPERATION__GATE_INSTANCE;

	/**
	 * The feature id for the '<em><b>Period</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUIESCENCE__PERIOD = TIME_OPERATION__PERIOD;

	/**
	 * The feature id for the '<em><b>Component Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUIESCENCE__COMPONENT_INSTANCE = TIME_OPERATION__COMPONENT_INSTANCE;

	/**
	 * The number of structural features of the '<em>Quiescence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUIESCENCE_FEATURE_COUNT = TIME_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.TestConfigurationImpl <em>Test Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.TestConfigurationImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTestConfiguration()
	 * @generated
	 */
	int TEST_CONFIGURATION = 33;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONFIGURATION__COMMENT = PACKAGEABLE_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONFIGURATION__ANNOTATION = PACKAGEABLE_ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONFIGURATION__NAME = PACKAGEABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONFIGURATION__QUALIFIED_NAME = PACKAGEABLE_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owning Package</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONFIGURATION__OWNING_PACKAGE = PACKAGEABLE_ELEMENT__OWNING_PACKAGE;

	/**
	 * The feature id for the '<em><b>Component Instance</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONFIGURATION__COMPONENT_INSTANCE = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONFIGURATION__CONNECTION = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Test Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_CONFIGURATION_FEATURE_COUNT = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.ConnectionImpl <em>Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.ConnectionImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getConnection()
	 * @generated
	 */
	int CONNECTION = 34;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__COMMENT = ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__ANNOTATION = ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__NAME = ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>End Point</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__END_POINT = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.TestDescriptionImpl <em>Test Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.TestDescriptionImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTestDescription()
	 * @generated
	 */
	int TEST_DESCRIPTION = 35;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_DESCRIPTION__COMMENT = PACKAGEABLE_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_DESCRIPTION__ANNOTATION = PACKAGEABLE_ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_DESCRIPTION__NAME = PACKAGEABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_DESCRIPTION__QUALIFIED_NAME = PACKAGEABLE_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owning Package</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_DESCRIPTION__OWNING_PACKAGE = PACKAGEABLE_ELEMENT__OWNING_PACKAGE;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_DESCRIPTION__TEST_OBJECTIVE = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Test Configuration</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_DESCRIPTION__TEST_CONFIGURATION = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Behaviour</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_DESCRIPTION__BEHAVIOUR = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Time Constraint</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_DESCRIPTION__TIME_CONSTRAINT = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Formal Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_DESCRIPTION__FORMAL_PARAMETER = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Test Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_DESCRIPTION_FEATURE_COUNT = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.CombinedBehaviourImpl <em>Combined Behaviour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.CombinedBehaviourImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getCombinedBehaviour()
	 * @generated
	 */
	int COMBINED_BEHAVIOUR = 38;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBINED_BEHAVIOUR__COMMENT = BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBINED_BEHAVIOUR__ANNOTATION = BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBINED_BEHAVIOUR__NAME = BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBINED_BEHAVIOUR__TEST_OBJECTIVE = BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Periodic</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBINED_BEHAVIOUR__PERIODIC = BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Exceptional</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBINED_BEHAVIOUR__EXCEPTIONAL = BEHAVIOUR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Combined Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMBINED_BEHAVIOUR_FEATURE_COUNT = BEHAVIOUR_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.SingleCombinedBehaviourImpl <em>Single Combined Behaviour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.SingleCombinedBehaviourImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getSingleCombinedBehaviour()
	 * @generated
	 */
	int SINGLE_COMBINED_BEHAVIOUR = 37;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_COMBINED_BEHAVIOUR__COMMENT = COMBINED_BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_COMBINED_BEHAVIOUR__ANNOTATION = COMBINED_BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_COMBINED_BEHAVIOUR__NAME = COMBINED_BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_COMBINED_BEHAVIOUR__TEST_OBJECTIVE = COMBINED_BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Periodic</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_COMBINED_BEHAVIOUR__PERIODIC = COMBINED_BEHAVIOUR__PERIODIC;

	/**
	 * The feature id for the '<em><b>Exceptional</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_COMBINED_BEHAVIOUR__EXCEPTIONAL = COMBINED_BEHAVIOUR__EXCEPTIONAL;

	/**
	 * The feature id for the '<em><b>Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_COMBINED_BEHAVIOUR__BLOCK = COMBINED_BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Single Combined Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINGLE_COMBINED_BEHAVIOUR_FEATURE_COUNT = COMBINED_BEHAVIOUR_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.CompoundBehaviourImpl <em>Compound Behaviour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.CompoundBehaviourImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getCompoundBehaviour()
	 * @generated
	 */
	int COMPOUND_BEHAVIOUR = 36;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOUND_BEHAVIOUR__COMMENT = SINGLE_COMBINED_BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOUND_BEHAVIOUR__ANNOTATION = SINGLE_COMBINED_BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOUND_BEHAVIOUR__NAME = SINGLE_COMBINED_BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOUND_BEHAVIOUR__TEST_OBJECTIVE = SINGLE_COMBINED_BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Periodic</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOUND_BEHAVIOUR__PERIODIC = SINGLE_COMBINED_BEHAVIOUR__PERIODIC;

	/**
	 * The feature id for the '<em><b>Exceptional</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOUND_BEHAVIOUR__EXCEPTIONAL = SINGLE_COMBINED_BEHAVIOUR__EXCEPTIONAL;

	/**
	 * The feature id for the '<em><b>Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOUND_BEHAVIOUR__BLOCK = SINGLE_COMBINED_BEHAVIOUR__BLOCK;

	/**
	 * The number of structural features of the '<em>Compound Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOUND_BEHAVIOUR_FEATURE_COUNT = SINGLE_COMBINED_BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.PeriodicBehaviourImpl <em>Periodic Behaviour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.PeriodicBehaviourImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getPeriodicBehaviour()
	 * @generated
	 */
	int PERIODIC_BEHAVIOUR = 39;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIODIC_BEHAVIOUR__COMMENT = BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIODIC_BEHAVIOUR__ANNOTATION = BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIODIC_BEHAVIOUR__NAME = BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIODIC_BEHAVIOUR__TEST_OBJECTIVE = BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIODIC_BEHAVIOUR__BLOCK = BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Period</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIODIC_BEHAVIOUR__PERIOD = BEHAVIOUR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Periodic Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIODIC_BEHAVIOUR_FEATURE_COUNT = BEHAVIOUR_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.BlockImpl <em>Block</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.BlockImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getBlock()
	 * @generated
	 */
	int BLOCK = 40;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK__COMMENT = ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK__ANNOTATION = ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK__NAME = ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Behaviour</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK__BEHAVIOUR = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Guard</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK__GUARD = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Block</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLOCK_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.ExceptionalBehaviourImpl <em>Exceptional Behaviour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.ExceptionalBehaviourImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getExceptionalBehaviour()
	 * @generated
	 */
	int EXCEPTIONAL_BEHAVIOUR = 41;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTIONAL_BEHAVIOUR__COMMENT = BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTIONAL_BEHAVIOUR__ANNOTATION = BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTIONAL_BEHAVIOUR__NAME = BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTIONAL_BEHAVIOUR__TEST_OBJECTIVE = BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTIONAL_BEHAVIOUR__BLOCK = BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Guarded Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTIONAL_BEHAVIOUR__GUARDED_COMPONENT = BEHAVIOUR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Exceptional Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXCEPTIONAL_BEHAVIOUR_FEATURE_COUNT = BEHAVIOUR_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.MultipleCombinedBehaviourImpl <em>Multiple Combined Behaviour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.MultipleCombinedBehaviourImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getMultipleCombinedBehaviour()
	 * @generated
	 */
	int MULTIPLE_COMBINED_BEHAVIOUR = 43;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLE_COMBINED_BEHAVIOUR__COMMENT = COMBINED_BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLE_COMBINED_BEHAVIOUR__ANNOTATION = COMBINED_BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLE_COMBINED_BEHAVIOUR__NAME = COMBINED_BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLE_COMBINED_BEHAVIOUR__TEST_OBJECTIVE = COMBINED_BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Periodic</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLE_COMBINED_BEHAVIOUR__PERIODIC = COMBINED_BEHAVIOUR__PERIODIC;

	/**
	 * The feature id for the '<em><b>Exceptional</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLE_COMBINED_BEHAVIOUR__EXCEPTIONAL = COMBINED_BEHAVIOUR__EXCEPTIONAL;

	/**
	 * The feature id for the '<em><b>Block</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLE_COMBINED_BEHAVIOUR__BLOCK = COMBINED_BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Multiple Combined Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLE_COMBINED_BEHAVIOUR_FEATURE_COUNT = COMBINED_BEHAVIOUR_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.AlternativeBehaviourImpl <em>Alternative Behaviour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.AlternativeBehaviourImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getAlternativeBehaviour()
	 * @generated
	 */
	int ALTERNATIVE_BEHAVIOUR = 42;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVE_BEHAVIOUR__COMMENT = MULTIPLE_COMBINED_BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVE_BEHAVIOUR__ANNOTATION = MULTIPLE_COMBINED_BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVE_BEHAVIOUR__NAME = MULTIPLE_COMBINED_BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVE_BEHAVIOUR__TEST_OBJECTIVE = MULTIPLE_COMBINED_BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Periodic</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVE_BEHAVIOUR__PERIODIC = MULTIPLE_COMBINED_BEHAVIOUR__PERIODIC;

	/**
	 * The feature id for the '<em><b>Exceptional</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVE_BEHAVIOUR__EXCEPTIONAL = MULTIPLE_COMBINED_BEHAVIOUR__EXCEPTIONAL;

	/**
	 * The feature id for the '<em><b>Block</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVE_BEHAVIOUR__BLOCK = MULTIPLE_COMBINED_BEHAVIOUR__BLOCK;

	/**
	 * The number of structural features of the '<em>Alternative Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ALTERNATIVE_BEHAVIOUR_FEATURE_COUNT = MULTIPLE_COMBINED_BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.ParallelBehaviourImpl <em>Parallel Behaviour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.ParallelBehaviourImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getParallelBehaviour()
	 * @generated
	 */
	int PARALLEL_BEHAVIOUR = 44;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL_BEHAVIOUR__COMMENT = MULTIPLE_COMBINED_BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL_BEHAVIOUR__ANNOTATION = MULTIPLE_COMBINED_BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL_BEHAVIOUR__NAME = MULTIPLE_COMBINED_BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL_BEHAVIOUR__TEST_OBJECTIVE = MULTIPLE_COMBINED_BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Periodic</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL_BEHAVIOUR__PERIODIC = MULTIPLE_COMBINED_BEHAVIOUR__PERIODIC;

	/**
	 * The feature id for the '<em><b>Exceptional</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL_BEHAVIOUR__EXCEPTIONAL = MULTIPLE_COMBINED_BEHAVIOUR__EXCEPTIONAL;

	/**
	 * The feature id for the '<em><b>Block</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL_BEHAVIOUR__BLOCK = MULTIPLE_COMBINED_BEHAVIOUR__BLOCK;

	/**
	 * The number of structural features of the '<em>Parallel Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARALLEL_BEHAVIOUR_FEATURE_COUNT = MULTIPLE_COMBINED_BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.BoundedLoopBehaviourImpl <em>Bounded Loop Behaviour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.BoundedLoopBehaviourImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getBoundedLoopBehaviour()
	 * @generated
	 */
	int BOUNDED_LOOP_BEHAVIOUR = 45;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOUNDED_LOOP_BEHAVIOUR__COMMENT = SINGLE_COMBINED_BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOUNDED_LOOP_BEHAVIOUR__ANNOTATION = SINGLE_COMBINED_BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOUNDED_LOOP_BEHAVIOUR__NAME = SINGLE_COMBINED_BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOUNDED_LOOP_BEHAVIOUR__TEST_OBJECTIVE = SINGLE_COMBINED_BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Periodic</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOUNDED_LOOP_BEHAVIOUR__PERIODIC = SINGLE_COMBINED_BEHAVIOUR__PERIODIC;

	/**
	 * The feature id for the '<em><b>Exceptional</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOUNDED_LOOP_BEHAVIOUR__EXCEPTIONAL = SINGLE_COMBINED_BEHAVIOUR__EXCEPTIONAL;

	/**
	 * The feature id for the '<em><b>Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOUNDED_LOOP_BEHAVIOUR__BLOCK = SINGLE_COMBINED_BEHAVIOUR__BLOCK;

	/**
	 * The feature id for the '<em><b>Num Iteration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOUNDED_LOOP_BEHAVIOUR__NUM_ITERATION = SINGLE_COMBINED_BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Bounded Loop Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOUNDED_LOOP_BEHAVIOUR_FEATURE_COUNT = SINGLE_COMBINED_BEHAVIOUR_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.UnboundedLoopBehaviourImpl <em>Unbounded Loop Behaviour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.UnboundedLoopBehaviourImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getUnboundedLoopBehaviour()
	 * @generated
	 */
	int UNBOUNDED_LOOP_BEHAVIOUR = 46;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNBOUNDED_LOOP_BEHAVIOUR__COMMENT = SINGLE_COMBINED_BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNBOUNDED_LOOP_BEHAVIOUR__ANNOTATION = SINGLE_COMBINED_BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNBOUNDED_LOOP_BEHAVIOUR__NAME = SINGLE_COMBINED_BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNBOUNDED_LOOP_BEHAVIOUR__TEST_OBJECTIVE = SINGLE_COMBINED_BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Periodic</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNBOUNDED_LOOP_BEHAVIOUR__PERIODIC = SINGLE_COMBINED_BEHAVIOUR__PERIODIC;

	/**
	 * The feature id for the '<em><b>Exceptional</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNBOUNDED_LOOP_BEHAVIOUR__EXCEPTIONAL = SINGLE_COMBINED_BEHAVIOUR__EXCEPTIONAL;

	/**
	 * The feature id for the '<em><b>Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNBOUNDED_LOOP_BEHAVIOUR__BLOCK = SINGLE_COMBINED_BEHAVIOUR__BLOCK;

	/**
	 * The number of structural features of the '<em>Unbounded Loop Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNBOUNDED_LOOP_BEHAVIOUR_FEATURE_COUNT = SINGLE_COMBINED_BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.ConditionalBehaviourImpl <em>Conditional Behaviour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.ConditionalBehaviourImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getConditionalBehaviour()
	 * @generated
	 */
	int CONDITIONAL_BEHAVIOUR = 47;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_BEHAVIOUR__COMMENT = MULTIPLE_COMBINED_BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_BEHAVIOUR__ANNOTATION = MULTIPLE_COMBINED_BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_BEHAVIOUR__NAME = MULTIPLE_COMBINED_BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_BEHAVIOUR__TEST_OBJECTIVE = MULTIPLE_COMBINED_BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Periodic</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_BEHAVIOUR__PERIODIC = MULTIPLE_COMBINED_BEHAVIOUR__PERIODIC;

	/**
	 * The feature id for the '<em><b>Exceptional</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_BEHAVIOUR__EXCEPTIONAL = MULTIPLE_COMBINED_BEHAVIOUR__EXCEPTIONAL;

	/**
	 * The feature id for the '<em><b>Block</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_BEHAVIOUR__BLOCK = MULTIPLE_COMBINED_BEHAVIOUR__BLOCK;

	/**
	 * The number of structural features of the '<em>Conditional Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITIONAL_BEHAVIOUR_FEATURE_COUNT = MULTIPLE_COMBINED_BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.StopImpl <em>Stop</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.StopImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getStop()
	 * @generated
	 */
	int STOP = 48;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOP__COMMENT = ATOMIC_BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOP__ANNOTATION = ATOMIC_BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOP__NAME = ATOMIC_BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOP__TEST_OBJECTIVE = ATOMIC_BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Time Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOP__TIME_CONSTRAINT = ATOMIC_BEHAVIOUR__TIME_CONSTRAINT;

	/**
	 * The number of structural features of the '<em>Stop</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STOP_FEATURE_COUNT = ATOMIC_BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.TestDescriptionReferenceImpl <em>Test Description Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.TestDescriptionReferenceImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTestDescriptionReference()
	 * @generated
	 */
	int TEST_DESCRIPTION_REFERENCE = 49;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_DESCRIPTION_REFERENCE__COMMENT = ATOMIC_BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_DESCRIPTION_REFERENCE__ANNOTATION = ATOMIC_BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_DESCRIPTION_REFERENCE__NAME = ATOMIC_BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_DESCRIPTION_REFERENCE__TEST_OBJECTIVE = ATOMIC_BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Time Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_DESCRIPTION_REFERENCE__TIME_CONSTRAINT = ATOMIC_BEHAVIOUR__TIME_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Referenced Test Description</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_DESCRIPTION_REFERENCE__REFERENCED_TEST_DESCRIPTION = ATOMIC_BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Actual Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_DESCRIPTION_REFERENCE__ACTUAL_PARAMETER = ATOMIC_BEHAVIOUR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Test Description Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_DESCRIPTION_REFERENCE_FEATURE_COUNT = ATOMIC_BEHAVIOUR_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.ArgumentSpecificationImpl <em>Argument Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.ArgumentSpecificationImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getArgumentSpecification()
	 * @generated
	 */
	int ARGUMENT_SPECIFICATION = 50;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARGUMENT_SPECIFICATION__COMMENT = ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARGUMENT_SPECIFICATION__ANNOTATION = ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARGUMENT_SPECIFICATION__NAME = ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>Argument Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARGUMENT_SPECIFICATION_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.VerdictAssignmentImpl <em>Verdict Assignment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.VerdictAssignmentImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getVerdictAssignment()
	 * @generated
	 */
	int VERDICT_ASSIGNMENT = 51;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERDICT_ASSIGNMENT__COMMENT = ATOMIC_BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERDICT_ASSIGNMENT__ANNOTATION = ATOMIC_BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERDICT_ASSIGNMENT__NAME = ATOMIC_BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERDICT_ASSIGNMENT__TEST_OBJECTIVE = ATOMIC_BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Time Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERDICT_ASSIGNMENT__TIME_CONSTRAINT = ATOMIC_BEHAVIOUR__TIME_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Verdict Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERDICT_ASSIGNMENT__VERDICT_TYPE = ATOMIC_BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Verdict Assignment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERDICT_ASSIGNMENT_FEATURE_COUNT = ATOMIC_BEHAVIOUR_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.VerdictTypeImpl <em>Verdict Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.VerdictTypeImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getVerdictType()
	 * @generated
	 */
	int VERDICT_TYPE = 52;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERDICT_TYPE__COMMENT = PACKAGEABLE_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERDICT_TYPE__ANNOTATION = PACKAGEABLE_ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERDICT_TYPE__NAME = PACKAGEABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERDICT_TYPE__QUALIFIED_NAME = PACKAGEABLE_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owning Package</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERDICT_TYPE__OWNING_PACKAGE = PACKAGEABLE_ELEMENT__OWNING_PACKAGE;

	/**
	 * The number of structural features of the '<em>Verdict Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VERDICT_TYPE_FEATURE_COUNT = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.ActionReferenceImpl <em>Action Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.ActionReferenceImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getActionReference()
	 * @generated
	 */
	int ACTION_REFERENCE = 53;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_REFERENCE__COMMENT = ATOMIC_BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_REFERENCE__ANNOTATION = ATOMIC_BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_REFERENCE__NAME = ATOMIC_BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_REFERENCE__TEST_OBJECTIVE = ATOMIC_BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Time Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_REFERENCE__TIME_CONSTRAINT = ATOMIC_BEHAVIOUR__TIME_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Component Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_REFERENCE__COMPONENT_INSTANCE = ATOMIC_BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Action</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_REFERENCE__ACTION = ATOMIC_BEHAVIOUR_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Action Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_REFERENCE_FEATURE_COUNT = ATOMIC_BEHAVIOUR_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.ActionImpl <em>Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.ActionImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getAction()
	 * @generated
	 */
	int ACTION = 54;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__COMMENT = PACKAGEABLE_ELEMENT__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ANNOTATION = PACKAGEABLE_ELEMENT__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__NAME = PACKAGEABLE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__QUALIFIED_NAME = PACKAGEABLE_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owning Package</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__OWNING_PACKAGE = PACKAGEABLE_ELEMENT__OWNING_PACKAGE;

	/**
	 * The feature id for the '<em><b>Body</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__BODY = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FEATURE_COUNT = PACKAGEABLE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.InteractionImpl <em>Interaction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.InteractionImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getInteraction()
	 * @generated
	 */
	int INTERACTION = 55;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION__COMMENT = ATOMIC_BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION__ANNOTATION = ATOMIC_BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION__NAME = ATOMIC_BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION__TEST_OBJECTIVE = ATOMIC_BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Time Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION__TIME_CONSTRAINT = ATOMIC_BEHAVIOUR__TIME_CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Argument</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION__ARGUMENT = ATOMIC_BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION__SOURCE = ATOMIC_BEHAVIOUR_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION__TARGET = ATOMIC_BEHAVIOUR_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Interaction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERACTION_FEATURE_COUNT = ATOMIC_BEHAVIOUR_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.OptionalBehaviourImpl <em>Optional Behaviour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.OptionalBehaviourImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getOptionalBehaviour()
	 * @generated
	 */
	int OPTIONAL_BEHAVIOUR = 56;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPTIONAL_BEHAVIOUR__COMMENT = SINGLE_COMBINED_BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPTIONAL_BEHAVIOUR__ANNOTATION = SINGLE_COMBINED_BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPTIONAL_BEHAVIOUR__NAME = SINGLE_COMBINED_BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPTIONAL_BEHAVIOUR__TEST_OBJECTIVE = SINGLE_COMBINED_BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Periodic</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPTIONAL_BEHAVIOUR__PERIODIC = SINGLE_COMBINED_BEHAVIOUR__PERIODIC;

	/**
	 * The feature id for the '<em><b>Exceptional</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPTIONAL_BEHAVIOUR__EXCEPTIONAL = SINGLE_COMBINED_BEHAVIOUR__EXCEPTIONAL;

	/**
	 * The feature id for the '<em><b>Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPTIONAL_BEHAVIOUR__BLOCK = SINGLE_COMBINED_BEHAVIOUR__BLOCK;

	/**
	 * The number of structural features of the '<em>Optional Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPTIONAL_BEHAVIOUR_FEATURE_COUNT = SINGLE_COMBINED_BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.DefaultBehaviourImpl <em>Default Behaviour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.DefaultBehaviourImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getDefaultBehaviour()
	 * @generated
	 */
	int DEFAULT_BEHAVIOUR = 57;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_BEHAVIOUR__COMMENT = EXCEPTIONAL_BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_BEHAVIOUR__ANNOTATION = EXCEPTIONAL_BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_BEHAVIOUR__NAME = EXCEPTIONAL_BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_BEHAVIOUR__TEST_OBJECTIVE = EXCEPTIONAL_BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_BEHAVIOUR__BLOCK = EXCEPTIONAL_BEHAVIOUR__BLOCK;

	/**
	 * The feature id for the '<em><b>Guarded Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_BEHAVIOUR__GUARDED_COMPONENT = EXCEPTIONAL_BEHAVIOUR__GUARDED_COMPONENT;

	/**
	 * The number of structural features of the '<em>Default Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEFAULT_BEHAVIOUR_FEATURE_COUNT = EXCEPTIONAL_BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.InterruptBehaviourImpl <em>Interrupt Behaviour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.InterruptBehaviourImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getInterruptBehaviour()
	 * @generated
	 */
	int INTERRUPT_BEHAVIOUR = 58;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERRUPT_BEHAVIOUR__COMMENT = EXCEPTIONAL_BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERRUPT_BEHAVIOUR__ANNOTATION = EXCEPTIONAL_BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERRUPT_BEHAVIOUR__NAME = EXCEPTIONAL_BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERRUPT_BEHAVIOUR__TEST_OBJECTIVE = EXCEPTIONAL_BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Block</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERRUPT_BEHAVIOUR__BLOCK = EXCEPTIONAL_BEHAVIOUR__BLOCK;

	/**
	 * The feature id for the '<em><b>Guarded Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERRUPT_BEHAVIOUR__GUARDED_COMPONENT = EXCEPTIONAL_BEHAVIOUR__GUARDED_COMPONENT;

	/**
	 * The number of structural features of the '<em>Interrupt Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERRUPT_BEHAVIOUR_FEATURE_COUNT = EXCEPTIONAL_BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.BreakImpl <em>Break</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.BreakImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getBreak()
	 * @generated
	 */
	int BREAK = 59;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAK__COMMENT = ATOMIC_BEHAVIOUR__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAK__ANNOTATION = ATOMIC_BEHAVIOUR__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAK__NAME = ATOMIC_BEHAVIOUR__NAME;

	/**
	 * The feature id for the '<em><b>Test Objective</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAK__TEST_OBJECTIVE = ATOMIC_BEHAVIOUR__TEST_OBJECTIVE;

	/**
	 * The feature id for the '<em><b>Time Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAK__TIME_CONSTRAINT = ATOMIC_BEHAVIOUR__TIME_CONSTRAINT;

	/**
	 * The number of structural features of the '<em>Break</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREAK_FEATURE_COUNT = ATOMIC_BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.DataInstanceArgumentSpecificationImpl <em>Data Instance Argument Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.DataInstanceArgumentSpecificationImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getDataInstanceArgumentSpecification()
	 * @generated
	 */
	int DATA_INSTANCE_ARGUMENT_SPECIFICATION = 60;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_INSTANCE_ARGUMENT_SPECIFICATION__COMMENT = ARGUMENT_SPECIFICATION__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_INSTANCE_ARGUMENT_SPECIFICATION__ANNOTATION = ARGUMENT_SPECIFICATION__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_INSTANCE_ARGUMENT_SPECIFICATION__NAME = ARGUMENT_SPECIFICATION__NAME;

	/**
	 * The feature id for the '<em><b>Data Instance</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_INSTANCE_ARGUMENT_SPECIFICATION__DATA_INSTANCE = ARGUMENT_SPECIFICATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Actual Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_INSTANCE_ARGUMENT_SPECIFICATION__ACTUAL_PARAMETER = ARGUMENT_SPECIFICATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Data Instance Argument Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_INSTANCE_ARGUMENT_SPECIFICATION_FEATURE_COUNT = ARGUMENT_SPECIFICATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.DataProxyArgumentSpecificationImpl <em>Data Proxy Argument Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.DataProxyArgumentSpecificationImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getDataProxyArgumentSpecification()
	 * @generated
	 */
	int DATA_PROXY_ARGUMENT_SPECIFICATION = 61;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROXY_ARGUMENT_SPECIFICATION__COMMENT = ARGUMENT_SPECIFICATION__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROXY_ARGUMENT_SPECIFICATION__ANNOTATION = ARGUMENT_SPECIFICATION__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROXY_ARGUMENT_SPECIFICATION__NAME = ARGUMENT_SPECIFICATION__NAME;

	/**
	 * The feature id for the '<em><b>Data Proxy</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROXY_ARGUMENT_SPECIFICATION__DATA_PROXY = ARGUMENT_SPECIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Data Proxy Argument Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROXY_ARGUMENT_SPECIFICATION_FEATURE_COUNT = ARGUMENT_SPECIFICATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.impl.DataSetArgumentSpecificationImpl <em>Data Set Argument Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.impl.DataSetArgumentSpecificationImpl
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getDataSetArgumentSpecification()
	 * @generated
	 */
	int DATA_SET_ARGUMENT_SPECIFICATION = 62;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SET_ARGUMENT_SPECIFICATION__COMMENT = ARGUMENT_SPECIFICATION__COMMENT;

	/**
	 * The feature id for the '<em><b>Annotation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SET_ARGUMENT_SPECIFICATION__ANNOTATION = ARGUMENT_SPECIFICATION__ANNOTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SET_ARGUMENT_SPECIFICATION__NAME = ARGUMENT_SPECIFICATION__NAME;

	/**
	 * The feature id for the '<em><b>Data Set</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SET_ARGUMENT_SPECIFICATION__DATA_SET = ARGUMENT_SPECIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Data Set Argument Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_SET_ARGUMENT_SPECIFICATION_FEATURE_COUNT = ARGUMENT_SPECIFICATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.etsi.mts.tdl.ComponentInstanceRole <em>Component Instance Role</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.etsi.mts.tdl.ComponentInstanceRole
	 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getComponentInstanceRole()
	 * @generated
	 */
	int COMPONENT_INSTANCE_ROLE = 63;


	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see org.etsi.mts.tdl.Element
	 * @generated
	 */
	EClass getElement();

	/**
	 * Returns the meta object for the containment reference list '{@link org.etsi.mts.tdl.Element#getComments <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Comment</em>'.
	 * @see org.etsi.mts.tdl.Element#getComments()
	 * @see #getElement()
	 * @generated
	 */
	EReference getElement_Comment();

	/**
	 * Returns the meta object for the containment reference list '{@link org.etsi.mts.tdl.Element#getAnnotations <em>Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Annotation</em>'.
	 * @see org.etsi.mts.tdl.Element#getAnnotations()
	 * @see #getElement()
	 * @generated
	 */
	EReference getElement_Annotation();

	/**
	 * Returns the meta object for the attribute '{@link org.etsi.mts.tdl.Element#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.etsi.mts.tdl.Element#getName()
	 * @see #getElement()
	 * @generated
	 */
	EAttribute getElement_Name();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.Comment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comment</em>'.
	 * @see org.etsi.mts.tdl.Comment
	 * @generated
	 */
	EClass getComment();

	/**
	 * Returns the meta object for the attribute '{@link org.etsi.mts.tdl.Comment#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Body</em>'.
	 * @see org.etsi.mts.tdl.Comment#getBody()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_Body();

	/**
	 * Returns the meta object for the container reference '{@link org.etsi.mts.tdl.Comment#getCommentedElement <em>Commented Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Commented Element</em>'.
	 * @see org.etsi.mts.tdl.Comment#getCommentedElement()
	 * @see #getComment()
	 * @generated
	 */
	EReference getComment_CommentedElement();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.Annotation <em>Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Annotation</em>'.
	 * @see org.etsi.mts.tdl.Annotation
	 * @generated
	 */
	EClass getAnnotation();

	/**
	 * Returns the meta object for the attribute '{@link org.etsi.mts.tdl.Annotation#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.etsi.mts.tdl.Annotation#getValue()
	 * @see #getAnnotation()
	 * @generated
	 */
	EAttribute getAnnotation_Value();

	/**
	 * Returns the meta object for the reference '{@link org.etsi.mts.tdl.Annotation#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Key</em>'.
	 * @see org.etsi.mts.tdl.Annotation#getKey()
	 * @see #getAnnotation()
	 * @generated
	 */
	EReference getAnnotation_Key();

	/**
	 * Returns the meta object for the container reference '{@link org.etsi.mts.tdl.Annotation#getAnnotatedElement <em>Annotated Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Annotated Element</em>'.
	 * @see org.etsi.mts.tdl.Annotation#getAnnotatedElement()
	 * @see #getAnnotation()
	 * @generated
	 */
	EReference getAnnotation_AnnotatedElement();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.AnnotationType <em>Annotation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Annotation Type</em>'.
	 * @see org.etsi.mts.tdl.AnnotationType
	 * @generated
	 */
	EClass getAnnotationType();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.PackageableElement <em>Packageable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Packageable Element</em>'.
	 * @see org.etsi.mts.tdl.PackageableElement
	 * @generated
	 */
	EClass getPackageableElement();

	/**
	 * Returns the meta object for the attribute '{@link org.etsi.mts.tdl.PackageableElement#getQualifiedName <em>Qualified Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Qualified Name</em>'.
	 * @see org.etsi.mts.tdl.PackageableElement#getQualifiedName()
	 * @see #getPackageableElement()
	 * @generated
	 */
	EAttribute getPackageableElement_QualifiedName();

	/**
	 * Returns the meta object for the container reference '{@link org.etsi.mts.tdl.PackageableElement#getOwningPackage <em>Owning Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Package</em>'.
	 * @see org.etsi.mts.tdl.PackageableElement#getOwningPackage()
	 * @see #getPackageableElement()
	 * @generated
	 */
	EReference getPackageableElement_OwningPackage();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.Package <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package</em>'.
	 * @see org.etsi.mts.tdl.Package
	 * @generated
	 */
	EClass getPackage();

	/**
	 * Returns the meta object for the containment reference list '{@link org.etsi.mts.tdl.Package#getImports <em>Import</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Import</em>'.
	 * @see org.etsi.mts.tdl.Package#getImports()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_Import();

	/**
	 * Returns the meta object for the containment reference list '{@link org.etsi.mts.tdl.Package#getPackagedElements <em>Packaged Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Packaged Elements</em>'.
	 * @see org.etsi.mts.tdl.Package#getPackagedElements()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_PackagedElements();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.ElementImport <em>Element Import</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element Import</em>'.
	 * @see org.etsi.mts.tdl.ElementImport
	 * @generated
	 */
	EClass getElementImport();

	/**
	 * Returns the meta object for the reference list '{@link org.etsi.mts.tdl.ElementImport#getImportedElements <em>Imported Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Imported Element</em>'.
	 * @see org.etsi.mts.tdl.ElementImport#getImportedElements()
	 * @see #getElementImport()
	 * @generated
	 */
	EReference getElementImport_ImportedElement();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.TestObjective <em>Test Objective</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Objective</em>'.
	 * @see org.etsi.mts.tdl.TestObjective
	 * @generated
	 */
	EClass getTestObjective();

	/**
	 * Returns the meta object for the attribute list '{@link org.etsi.mts.tdl.TestObjective#getObjectiveURIs <em>Objective URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Objective URI</em>'.
	 * @see org.etsi.mts.tdl.TestObjective#getObjectiveURIs()
	 * @see #getTestObjective()
	 * @generated
	 */
	EAttribute getTestObjective_ObjectiveURI();

	/**
	 * Returns the meta object for the attribute '{@link org.etsi.mts.tdl.TestObjective#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.etsi.mts.tdl.TestObjective#getDescription()
	 * @see #getTestObjective()
	 * @generated
	 */
	EAttribute getTestObjective_Description();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.TestObjectiveRealizer <em>Test Objective Realizer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Objective Realizer</em>'.
	 * @see org.etsi.mts.tdl.TestObjectiveRealizer
	 * @generated
	 */
	EClass getTestObjectiveRealizer();

	/**
	 * Returns the meta object for the reference list '{@link org.etsi.mts.tdl.TestObjectiveRealizer#getTestObjectives <em>Test Objective</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Test Objective</em>'.
	 * @see org.etsi.mts.tdl.TestObjectiveRealizer#getTestObjectives()
	 * @see #getTestObjectiveRealizer()
	 * @generated
	 */
	EReference getTestObjectiveRealizer_TestObjective();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.DataInstance <em>Data Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Instance</em>'.
	 * @see org.etsi.mts.tdl.DataInstance
	 * @generated
	 */
	EClass getDataInstance();

	/**
	 * Returns the meta object for the reference list '{@link org.etsi.mts.tdl.DataInstance#getParameters <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parameter</em>'.
	 * @see org.etsi.mts.tdl.DataInstance#getParameters()
	 * @see #getDataInstance()
	 * @generated
	 */
	EReference getDataInstance_Parameter();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.MappableDataElement <em>Mappable Data Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Mappable Data Element</em>'.
	 * @see org.etsi.mts.tdl.MappableDataElement
	 * @generated
	 */
	EClass getMappableDataElement();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.DataElement <em>Data Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Element</em>'.
	 * @see org.etsi.mts.tdl.DataElement
	 * @generated
	 */
	EClass getDataElement();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.DataSet <em>Data Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Set</em>'.
	 * @see org.etsi.mts.tdl.DataSet
	 * @generated
	 */
	EClass getDataSet();

	/**
	 * Returns the meta object for the containment reference list '{@link org.etsi.mts.tdl.DataSet#getDataInstances <em>Data Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Data Instance</em>'.
	 * @see org.etsi.mts.tdl.DataSet#getDataInstances()
	 * @see #getDataSet()
	 * @generated
	 */
	EReference getDataSet_DataInstance();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.DataResourceMapping <em>Data Resource Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Resource Mapping</em>'.
	 * @see org.etsi.mts.tdl.DataResourceMapping
	 * @generated
	 */
	EClass getDataResourceMapping();

	/**
	 * Returns the meta object for the attribute '{@link org.etsi.mts.tdl.DataResourceMapping#getResourceURI <em>Resource URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Resource URI</em>'.
	 * @see org.etsi.mts.tdl.DataResourceMapping#getResourceURI()
	 * @see #getDataResourceMapping()
	 * @generated
	 */
	EAttribute getDataResourceMapping_ResourceURI();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.DataElementMapping <em>Data Element Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Element Mapping</em>'.
	 * @see org.etsi.mts.tdl.DataElementMapping
	 * @generated
	 */
	EClass getDataElementMapping();

	/**
	 * Returns the meta object for the reference '{@link org.etsi.mts.tdl.DataElementMapping#getMappableDataElement <em>Mappable Data Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Mappable Data Element</em>'.
	 * @see org.etsi.mts.tdl.DataElementMapping#getMappableDataElement()
	 * @see #getDataElementMapping()
	 * @generated
	 */
	EReference getDataElementMapping_MappableDataElement();

	/**
	 * Returns the meta object for the attribute '{@link org.etsi.mts.tdl.DataElementMapping#getElementURI <em>Element URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Element URI</em>'.
	 * @see org.etsi.mts.tdl.DataElementMapping#getElementURI()
	 * @see #getDataElementMapping()
	 * @generated
	 */
	EAttribute getDataElementMapping_ElementURI();

	/**
	 * Returns the meta object for the reference '{@link org.etsi.mts.tdl.DataElementMapping#getDataResourceMapping <em>Data Resource Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Data Resource Mapping</em>'.
	 * @see org.etsi.mts.tdl.DataElementMapping#getDataResourceMapping()
	 * @see #getDataElementMapping()
	 * @generated
	 */
	EReference getDataElementMapping_DataResourceMapping();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.DataProxy <em>Data Proxy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Proxy</em>'.
	 * @see org.etsi.mts.tdl.DataProxy
	 * @generated
	 */
	EClass getDataProxy();

	/**
	 * Returns the meta object for the reference '{@link org.etsi.mts.tdl.DataProxy#getDataSet <em>Data Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Data Set</em>'.
	 * @see org.etsi.mts.tdl.DataProxy#getDataSet()
	 * @see #getDataProxy()
	 * @generated
	 */
	EReference getDataProxy_DataSet();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.Timer <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Timer</em>'.
	 * @see org.etsi.mts.tdl.Timer
	 * @generated
	 */
	EClass getTimer();

	/**
	 * Returns the meta object for the container reference '{@link org.etsi.mts.tdl.Timer#getComponentType <em>Component Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Component Type</em>'.
	 * @see org.etsi.mts.tdl.Timer#getComponentType()
	 * @see #getTimer()
	 * @generated
	 */
	EReference getTimer_ComponentType();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.ComponentType <em>Component Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Type</em>'.
	 * @see org.etsi.mts.tdl.ComponentType
	 * @generated
	 */
	EClass getComponentType();

	/**
	 * Returns the meta object for the reference list '{@link org.etsi.mts.tdl.ComponentType#getGateTypes <em>Gate Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Gate Type</em>'.
	 * @see org.etsi.mts.tdl.ComponentType#getGateTypes()
	 * @see #getComponentType()
	 * @generated
	 */
	EReference getComponentType_GateType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.etsi.mts.tdl.ComponentType#getTimers <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timer</em>'.
	 * @see org.etsi.mts.tdl.ComponentType#getTimers()
	 * @see #getComponentType()
	 * @generated
	 */
	EReference getComponentType_Timer();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.GateType <em>Gate Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gate Type</em>'.
	 * @see org.etsi.mts.tdl.GateType
	 * @generated
	 */
	EClass getGateType();

	/**
	 * Returns the meta object for the reference list '{@link org.etsi.mts.tdl.GateType#getDataSets <em>Data Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Data Set</em>'.
	 * @see org.etsi.mts.tdl.GateType#getDataSets()
	 * @see #getGateType()
	 * @generated
	 */
	EReference getGateType_DataSet();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.TimerOperation <em>Timer Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Timer Operation</em>'.
	 * @see org.etsi.mts.tdl.TimerOperation
	 * @generated
	 */
	EClass getTimerOperation();

	/**
	 * Returns the meta object for the reference '{@link org.etsi.mts.tdl.TimerOperation#getTimer <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Timer</em>'.
	 * @see org.etsi.mts.tdl.TimerOperation#getTimer()
	 * @see #getTimerOperation()
	 * @generated
	 */
	EReference getTimerOperation_Timer();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.AtomicBehaviour <em>Atomic Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Atomic Behaviour</em>'.
	 * @see org.etsi.mts.tdl.AtomicBehaviour
	 * @generated
	 */
	EClass getAtomicBehaviour();

	/**
	 * Returns the meta object for the reference list '{@link org.etsi.mts.tdl.AtomicBehaviour#getTimeConstraints <em>Time Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Time Constraint</em>'.
	 * @see org.etsi.mts.tdl.AtomicBehaviour#getTimeConstraints()
	 * @see #getAtomicBehaviour()
	 * @generated
	 */
	EReference getAtomicBehaviour_TimeConstraint();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.Behaviour <em>Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Behaviour</em>'.
	 * @see org.etsi.mts.tdl.Behaviour
	 * @generated
	 */
	EClass getBehaviour();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.TimeConstraint <em>Time Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Time Constraint</em>'.
	 * @see org.etsi.mts.tdl.TimeConstraint
	 * @generated
	 */
	EClass getTimeConstraint();

	/**
	 * Returns the meta object for the attribute '{@link org.etsi.mts.tdl.TimeConstraint#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expression</em>'.
	 * @see org.etsi.mts.tdl.TimeConstraint#getExpression()
	 * @see #getTimeConstraint()
	 * @generated
	 */
	EAttribute getTimeConstraint_Expression();

	/**
	 * Returns the meta object for the reference list '{@link org.etsi.mts.tdl.TimeConstraint#getAtomicBehaviours <em>Atomic Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Atomic Behaviour</em>'.
	 * @see org.etsi.mts.tdl.TimeConstraint#getAtomicBehaviours()
	 * @see #getTimeConstraint()
	 * @generated
	 */
	EReference getTimeConstraint_AtomicBehaviour();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.TimerStart <em>Timer Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Timer Start</em>'.
	 * @see org.etsi.mts.tdl.TimerStart
	 * @generated
	 */
	EClass getTimerStart();

	/**
	 * Returns the meta object for the containment reference '{@link org.etsi.mts.tdl.TimerStart#getPeriod <em>Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Period</em>'.
	 * @see org.etsi.mts.tdl.TimerStart#getPeriod()
	 * @see #getTimerStart()
	 * @generated
	 */
	EReference getTimerStart_Period();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.Time <em>Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Time</em>'.
	 * @see org.etsi.mts.tdl.Time
	 * @generated
	 */
	EClass getTime();

	/**
	 * Returns the meta object for the attribute '{@link org.etsi.mts.tdl.Time#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.etsi.mts.tdl.Time#getValue()
	 * @see #getTime()
	 * @generated
	 */
	EAttribute getTime_Value();

	/**
	 * Returns the meta object for the reference '{@link org.etsi.mts.tdl.Time#getUnit <em>Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Unit</em>'.
	 * @see org.etsi.mts.tdl.Time#getUnit()
	 * @see #getTime()
	 * @generated
	 */
	EReference getTime_Unit();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.TimeUnit <em>Time Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Time Unit</em>'.
	 * @see org.etsi.mts.tdl.TimeUnit
	 * @generated
	 */
	EClass getTimeUnit();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.TimerStop <em>Timer Stop</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Timer Stop</em>'.
	 * @see org.etsi.mts.tdl.TimerStop
	 * @generated
	 */
	EClass getTimerStop();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.TimeOut <em>Time Out</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Time Out</em>'.
	 * @see org.etsi.mts.tdl.TimeOut
	 * @generated
	 */
	EClass getTimeOut();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.TimeOperation <em>Time Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Time Operation</em>'.
	 * @see org.etsi.mts.tdl.TimeOperation
	 * @generated
	 */
	EClass getTimeOperation();

	/**
	 * Returns the meta object for the reference '{@link org.etsi.mts.tdl.TimeOperation#getGateInstance <em>Gate Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Gate Instance</em>'.
	 * @see org.etsi.mts.tdl.TimeOperation#getGateInstance()
	 * @see #getTimeOperation()
	 * @generated
	 */
	EReference getTimeOperation_GateInstance();

	/**
	 * Returns the meta object for the containment reference '{@link org.etsi.mts.tdl.TimeOperation#getPeriod <em>Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Period</em>'.
	 * @see org.etsi.mts.tdl.TimeOperation#getPeriod()
	 * @see #getTimeOperation()
	 * @generated
	 */
	EReference getTimeOperation_Period();

	/**
	 * Returns the meta object for the reference '{@link org.etsi.mts.tdl.TimeOperation#getComponentInstance <em>Component Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Component Instance</em>'.
	 * @see org.etsi.mts.tdl.TimeOperation#getComponentInstance()
	 * @see #getTimeOperation()
	 * @generated
	 */
	EReference getTimeOperation_ComponentInstance();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.GateInstance <em>Gate Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Gate Instance</em>'.
	 * @see org.etsi.mts.tdl.GateInstance
	 * @generated
	 */
	EClass getGateInstance();

	/**
	 * Returns the meta object for the reference '{@link org.etsi.mts.tdl.GateInstance#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.etsi.mts.tdl.GateInstance#getType()
	 * @see #getGateInstance()
	 * @generated
	 */
	EReference getGateInstance_Type();

	/**
	 * Returns the meta object for the container reference '{@link org.etsi.mts.tdl.GateInstance#getComponentInstance <em>Component Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Component Instance</em>'.
	 * @see org.etsi.mts.tdl.GateInstance#getComponentInstance()
	 * @see #getGateInstance()
	 * @generated
	 */
	EReference getGateInstance_ComponentInstance();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.ComponentInstance <em>Component Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Instance</em>'.
	 * @see org.etsi.mts.tdl.ComponentInstance
	 * @generated
	 */
	EClass getComponentInstance();

	/**
	 * Returns the meta object for the reference '{@link org.etsi.mts.tdl.ComponentInstance#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.etsi.mts.tdl.ComponentInstance#getType()
	 * @see #getComponentInstance()
	 * @generated
	 */
	EReference getComponentInstance_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.etsi.mts.tdl.ComponentInstance#getRole <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Role</em>'.
	 * @see org.etsi.mts.tdl.ComponentInstance#getRole()
	 * @see #getComponentInstance()
	 * @generated
	 */
	EAttribute getComponentInstance_Role();

	/**
	 * Returns the meta object for the reference list '{@link org.etsi.mts.tdl.ComponentInstance#getTimers <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Timer</em>'.
	 * @see org.etsi.mts.tdl.ComponentInstance#getTimers()
	 * @see #getComponentInstance()
	 * @generated
	 */
	EReference getComponentInstance_Timer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.etsi.mts.tdl.ComponentInstance#getGateInstances <em>Gate Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Gate Instance</em>'.
	 * @see org.etsi.mts.tdl.ComponentInstance#getGateInstances()
	 * @see #getComponentInstance()
	 * @generated
	 */
	EReference getComponentInstance_GateInstance();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.Wait <em>Wait</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wait</em>'.
	 * @see org.etsi.mts.tdl.Wait
	 * @generated
	 */
	EClass getWait();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.Quiescence <em>Quiescence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Quiescence</em>'.
	 * @see org.etsi.mts.tdl.Quiescence
	 * @generated
	 */
	EClass getQuiescence();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.TestConfiguration <em>Test Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Configuration</em>'.
	 * @see org.etsi.mts.tdl.TestConfiguration
	 * @generated
	 */
	EClass getTestConfiguration();

	/**
	 * Returns the meta object for the containment reference list '{@link org.etsi.mts.tdl.TestConfiguration#getComponentInstances <em>Component Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Component Instance</em>'.
	 * @see org.etsi.mts.tdl.TestConfiguration#getComponentInstances()
	 * @see #getTestConfiguration()
	 * @generated
	 */
	EReference getTestConfiguration_ComponentInstance();

	/**
	 * Returns the meta object for the containment reference list '{@link org.etsi.mts.tdl.TestConfiguration#getConnections <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connection</em>'.
	 * @see org.etsi.mts.tdl.TestConfiguration#getConnections()
	 * @see #getTestConfiguration()
	 * @generated
	 */
	EReference getTestConfiguration_Connection();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection</em>'.
	 * @see org.etsi.mts.tdl.Connection
	 * @generated
	 */
	EClass getConnection();

	/**
	 * Returns the meta object for the reference list '{@link org.etsi.mts.tdl.Connection#getEndPoints <em>End Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>End Point</em>'.
	 * @see org.etsi.mts.tdl.Connection#getEndPoints()
	 * @see #getConnection()
	 * @generated
	 */
	EReference getConnection_EndPoint();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.TestDescription <em>Test Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Description</em>'.
	 * @see org.etsi.mts.tdl.TestDescription
	 * @generated
	 */
	EClass getTestDescription();

	/**
	 * Returns the meta object for the reference '{@link org.etsi.mts.tdl.TestDescription#getTestConfiguration <em>Test Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Test Configuration</em>'.
	 * @see org.etsi.mts.tdl.TestDescription#getTestConfiguration()
	 * @see #getTestDescription()
	 * @generated
	 */
	EReference getTestDescription_TestConfiguration();

	/**
	 * Returns the meta object for the containment reference '{@link org.etsi.mts.tdl.TestDescription#getBehaviour <em>Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Behaviour</em>'.
	 * @see org.etsi.mts.tdl.TestDescription#getBehaviour()
	 * @see #getTestDescription()
	 * @generated
	 */
	EReference getTestDescription_Behaviour();

	/**
	 * Returns the meta object for the containment reference list '{@link org.etsi.mts.tdl.TestDescription#getTimeConstraints <em>Time Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Time Constraint</em>'.
	 * @see org.etsi.mts.tdl.TestDescription#getTimeConstraints()
	 * @see #getTestDescription()
	 * @generated
	 */
	EReference getTestDescription_TimeConstraint();

	/**
	 * Returns the meta object for the containment reference list '{@link org.etsi.mts.tdl.TestDescription#getFormalParameters <em>Formal Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Formal Parameter</em>'.
	 * @see org.etsi.mts.tdl.TestDescription#getFormalParameters()
	 * @see #getTestDescription()
	 * @generated
	 */
	EReference getTestDescription_FormalParameter();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.CompoundBehaviour <em>Compound Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Compound Behaviour</em>'.
	 * @see org.etsi.mts.tdl.CompoundBehaviour
	 * @generated
	 */
	EClass getCompoundBehaviour();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.SingleCombinedBehaviour <em>Single Combined Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Single Combined Behaviour</em>'.
	 * @see org.etsi.mts.tdl.SingleCombinedBehaviour
	 * @generated
	 */
	EClass getSingleCombinedBehaviour();

	/**
	 * Returns the meta object for the containment reference '{@link org.etsi.mts.tdl.SingleCombinedBehaviour#getBlock <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Block</em>'.
	 * @see org.etsi.mts.tdl.SingleCombinedBehaviour#getBlock()
	 * @see #getSingleCombinedBehaviour()
	 * @generated
	 */
	EReference getSingleCombinedBehaviour_Block();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.CombinedBehaviour <em>Combined Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Combined Behaviour</em>'.
	 * @see org.etsi.mts.tdl.CombinedBehaviour
	 * @generated
	 */
	EClass getCombinedBehaviour();

	/**
	 * Returns the meta object for the containment reference list '{@link org.etsi.mts.tdl.CombinedBehaviour#getPeriodics <em>Periodic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Periodic</em>'.
	 * @see org.etsi.mts.tdl.CombinedBehaviour#getPeriodics()
	 * @see #getCombinedBehaviour()
	 * @generated
	 */
	EReference getCombinedBehaviour_Periodic();

	/**
	 * Returns the meta object for the containment reference list '{@link org.etsi.mts.tdl.CombinedBehaviour#getExceptionals <em>Exceptional</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exceptional</em>'.
	 * @see org.etsi.mts.tdl.CombinedBehaviour#getExceptionals()
	 * @see #getCombinedBehaviour()
	 * @generated
	 */
	EReference getCombinedBehaviour_Exceptional();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.PeriodicBehaviour <em>Periodic Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Periodic Behaviour</em>'.
	 * @see org.etsi.mts.tdl.PeriodicBehaviour
	 * @generated
	 */
	EClass getPeriodicBehaviour();

	/**
	 * Returns the meta object for the containment reference '{@link org.etsi.mts.tdl.PeriodicBehaviour#getBlock <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Block</em>'.
	 * @see org.etsi.mts.tdl.PeriodicBehaviour#getBlock()
	 * @see #getPeriodicBehaviour()
	 * @generated
	 */
	EReference getPeriodicBehaviour_Block();

	/**
	 * Returns the meta object for the containment reference '{@link org.etsi.mts.tdl.PeriodicBehaviour#getPeriod <em>Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Period</em>'.
	 * @see org.etsi.mts.tdl.PeriodicBehaviour#getPeriod()
	 * @see #getPeriodicBehaviour()
	 * @generated
	 */
	EReference getPeriodicBehaviour_Period();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.Block <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Block</em>'.
	 * @see org.etsi.mts.tdl.Block
	 * @generated
	 */
	EClass getBlock();

	/**
	 * Returns the meta object for the containment reference list '{@link org.etsi.mts.tdl.Block#getBehaviours <em>Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Behaviour</em>'.
	 * @see org.etsi.mts.tdl.Block#getBehaviours()
	 * @see #getBlock()
	 * @generated
	 */
	EReference getBlock_Behaviour();

	/**
	 * Returns the meta object for the attribute '{@link org.etsi.mts.tdl.Block#getGuard <em>Guard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Guard</em>'.
	 * @see org.etsi.mts.tdl.Block#getGuard()
	 * @see #getBlock()
	 * @generated
	 */
	EAttribute getBlock_Guard();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.ExceptionalBehaviour <em>Exceptional Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exceptional Behaviour</em>'.
	 * @see org.etsi.mts.tdl.ExceptionalBehaviour
	 * @generated
	 */
	EClass getExceptionalBehaviour();

	/**
	 * Returns the meta object for the containment reference '{@link org.etsi.mts.tdl.ExceptionalBehaviour#getBlock <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Block</em>'.
	 * @see org.etsi.mts.tdl.ExceptionalBehaviour#getBlock()
	 * @see #getExceptionalBehaviour()
	 * @generated
	 */
	EReference getExceptionalBehaviour_Block();

	/**
	 * Returns the meta object for the reference '{@link org.etsi.mts.tdl.ExceptionalBehaviour#getGuardedComponent <em>Guarded Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Guarded Component</em>'.
	 * @see org.etsi.mts.tdl.ExceptionalBehaviour#getGuardedComponent()
	 * @see #getExceptionalBehaviour()
	 * @generated
	 */
	EReference getExceptionalBehaviour_GuardedComponent();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.AlternativeBehaviour <em>Alternative Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Alternative Behaviour</em>'.
	 * @see org.etsi.mts.tdl.AlternativeBehaviour
	 * @generated
	 */
	EClass getAlternativeBehaviour();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.MultipleCombinedBehaviour <em>Multiple Combined Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Multiple Combined Behaviour</em>'.
	 * @see org.etsi.mts.tdl.MultipleCombinedBehaviour
	 * @generated
	 */
	EClass getMultipleCombinedBehaviour();

	/**
	 * Returns the meta object for the containment reference list '{@link org.etsi.mts.tdl.MultipleCombinedBehaviour#getBlocks <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Block</em>'.
	 * @see org.etsi.mts.tdl.MultipleCombinedBehaviour#getBlocks()
	 * @see #getMultipleCombinedBehaviour()
	 * @generated
	 */
	EReference getMultipleCombinedBehaviour_Block();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.ParallelBehaviour <em>Parallel Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parallel Behaviour</em>'.
	 * @see org.etsi.mts.tdl.ParallelBehaviour
	 * @generated
	 */
	EClass getParallelBehaviour();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.BoundedLoopBehaviour <em>Bounded Loop Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bounded Loop Behaviour</em>'.
	 * @see org.etsi.mts.tdl.BoundedLoopBehaviour
	 * @generated
	 */
	EClass getBoundedLoopBehaviour();

	/**
	 * Returns the meta object for the attribute '{@link org.etsi.mts.tdl.BoundedLoopBehaviour#getNumIteration <em>Num Iteration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Num Iteration</em>'.
	 * @see org.etsi.mts.tdl.BoundedLoopBehaviour#getNumIteration()
	 * @see #getBoundedLoopBehaviour()
	 * @generated
	 */
	EAttribute getBoundedLoopBehaviour_NumIteration();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.UnboundedLoopBehaviour <em>Unbounded Loop Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unbounded Loop Behaviour</em>'.
	 * @see org.etsi.mts.tdl.UnboundedLoopBehaviour
	 * @generated
	 */
	EClass getUnboundedLoopBehaviour();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.ConditionalBehaviour <em>Conditional Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conditional Behaviour</em>'.
	 * @see org.etsi.mts.tdl.ConditionalBehaviour
	 * @generated
	 */
	EClass getConditionalBehaviour();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.Stop <em>Stop</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stop</em>'.
	 * @see org.etsi.mts.tdl.Stop
	 * @generated
	 */
	EClass getStop();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.TestDescriptionReference <em>Test Description Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Description Reference</em>'.
	 * @see org.etsi.mts.tdl.TestDescriptionReference
	 * @generated
	 */
	EClass getTestDescriptionReference();

	/**
	 * Returns the meta object for the reference '{@link org.etsi.mts.tdl.TestDescriptionReference#getReferencedTestDescription <em>Referenced Test Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referenced Test Description</em>'.
	 * @see org.etsi.mts.tdl.TestDescriptionReference#getReferencedTestDescription()
	 * @see #getTestDescriptionReference()
	 * @generated
	 */
	EReference getTestDescriptionReference_ReferencedTestDescription();

	/**
	 * Returns the meta object for the containment reference list '{@link org.etsi.mts.tdl.TestDescriptionReference#getActualParameters <em>Actual Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Actual Parameter</em>'.
	 * @see org.etsi.mts.tdl.TestDescriptionReference#getActualParameters()
	 * @see #getTestDescriptionReference()
	 * @generated
	 */
	EReference getTestDescriptionReference_ActualParameter();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.ArgumentSpecification <em>Argument Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Argument Specification</em>'.
	 * @see org.etsi.mts.tdl.ArgumentSpecification
	 * @generated
	 */
	EClass getArgumentSpecification();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.VerdictAssignment <em>Verdict Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Verdict Assignment</em>'.
	 * @see org.etsi.mts.tdl.VerdictAssignment
	 * @generated
	 */
	EClass getVerdictAssignment();

	/**
	 * Returns the meta object for the reference '{@link org.etsi.mts.tdl.VerdictAssignment#getVerdictType <em>Verdict Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Verdict Type</em>'.
	 * @see org.etsi.mts.tdl.VerdictAssignment#getVerdictType()
	 * @see #getVerdictAssignment()
	 * @generated
	 */
	EReference getVerdictAssignment_VerdictType();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.VerdictType <em>Verdict Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Verdict Type</em>'.
	 * @see org.etsi.mts.tdl.VerdictType
	 * @generated
	 */
	EClass getVerdictType();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.ActionReference <em>Action Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Reference</em>'.
	 * @see org.etsi.mts.tdl.ActionReference
	 * @generated
	 */
	EClass getActionReference();

	/**
	 * Returns the meta object for the reference '{@link org.etsi.mts.tdl.ActionReference#getComponentInstance <em>Component Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Component Instance</em>'.
	 * @see org.etsi.mts.tdl.ActionReference#getComponentInstance()
	 * @see #getActionReference()
	 * @generated
	 */
	EReference getActionReference_ComponentInstance();

	/**
	 * Returns the meta object for the reference '{@link org.etsi.mts.tdl.ActionReference#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Action</em>'.
	 * @see org.etsi.mts.tdl.ActionReference#getAction()
	 * @see #getActionReference()
	 * @generated
	 */
	EReference getActionReference_Action();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.Action <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action</em>'.
	 * @see org.etsi.mts.tdl.Action
	 * @generated
	 */
	EClass getAction();

	/**
	 * Returns the meta object for the attribute '{@link org.etsi.mts.tdl.Action#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Body</em>'.
	 * @see org.etsi.mts.tdl.Action#getBody()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Body();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.Interaction <em>Interaction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interaction</em>'.
	 * @see org.etsi.mts.tdl.Interaction
	 * @generated
	 */
	EClass getInteraction();

	/**
	 * Returns the meta object for the containment reference '{@link org.etsi.mts.tdl.Interaction#getArgument <em>Argument</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Argument</em>'.
	 * @see org.etsi.mts.tdl.Interaction#getArgument()
	 * @see #getInteraction()
	 * @generated
	 */
	EReference getInteraction_Argument();

	/**
	 * Returns the meta object for the reference '{@link org.etsi.mts.tdl.Interaction#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see org.etsi.mts.tdl.Interaction#getSource()
	 * @see #getInteraction()
	 * @generated
	 */
	EReference getInteraction_Source();

	/**
	 * Returns the meta object for the reference list '{@link org.etsi.mts.tdl.Interaction#getTargets <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Target</em>'.
	 * @see org.etsi.mts.tdl.Interaction#getTargets()
	 * @see #getInteraction()
	 * @generated
	 */
	EReference getInteraction_Target();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.OptionalBehaviour <em>Optional Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Optional Behaviour</em>'.
	 * @see org.etsi.mts.tdl.OptionalBehaviour
	 * @generated
	 */
	EClass getOptionalBehaviour();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.DefaultBehaviour <em>Default Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Default Behaviour</em>'.
	 * @see org.etsi.mts.tdl.DefaultBehaviour
	 * @generated
	 */
	EClass getDefaultBehaviour();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.InterruptBehaviour <em>Interrupt Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interrupt Behaviour</em>'.
	 * @see org.etsi.mts.tdl.InterruptBehaviour
	 * @generated
	 */
	EClass getInterruptBehaviour();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.Break <em>Break</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Break</em>'.
	 * @see org.etsi.mts.tdl.Break
	 * @generated
	 */
	EClass getBreak();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.DataInstanceArgumentSpecification <em>Data Instance Argument Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Instance Argument Specification</em>'.
	 * @see org.etsi.mts.tdl.DataInstanceArgumentSpecification
	 * @generated
	 */
	EClass getDataInstanceArgumentSpecification();

	/**
	 * Returns the meta object for the reference '{@link org.etsi.mts.tdl.DataInstanceArgumentSpecification#getDataInstance <em>Data Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Data Instance</em>'.
	 * @see org.etsi.mts.tdl.DataInstanceArgumentSpecification#getDataInstance()
	 * @see #getDataInstanceArgumentSpecification()
	 * @generated
	 */
	EReference getDataInstanceArgumentSpecification_DataInstance();

	/**
	 * Returns the meta object for the reference list '{@link org.etsi.mts.tdl.DataInstanceArgumentSpecification#getActualParameters <em>Actual Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Actual Parameter</em>'.
	 * @see org.etsi.mts.tdl.DataInstanceArgumentSpecification#getActualParameters()
	 * @see #getDataInstanceArgumentSpecification()
	 * @generated
	 */
	EReference getDataInstanceArgumentSpecification_ActualParameter();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.DataProxyArgumentSpecification <em>Data Proxy Argument Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Proxy Argument Specification</em>'.
	 * @see org.etsi.mts.tdl.DataProxyArgumentSpecification
	 * @generated
	 */
	EClass getDataProxyArgumentSpecification();

	/**
	 * Returns the meta object for the reference '{@link org.etsi.mts.tdl.DataProxyArgumentSpecification#getDataProxy <em>Data Proxy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Data Proxy</em>'.
	 * @see org.etsi.mts.tdl.DataProxyArgumentSpecification#getDataProxy()
	 * @see #getDataProxyArgumentSpecification()
	 * @generated
	 */
	EReference getDataProxyArgumentSpecification_DataProxy();

	/**
	 * Returns the meta object for class '{@link org.etsi.mts.tdl.DataSetArgumentSpecification <em>Data Set Argument Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Set Argument Specification</em>'.
	 * @see org.etsi.mts.tdl.DataSetArgumentSpecification
	 * @generated
	 */
	EClass getDataSetArgumentSpecification();

	/**
	 * Returns the meta object for the reference '{@link org.etsi.mts.tdl.DataSetArgumentSpecification#getDataSet <em>Data Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Data Set</em>'.
	 * @see org.etsi.mts.tdl.DataSetArgumentSpecification#getDataSet()
	 * @see #getDataSetArgumentSpecification()
	 * @generated
	 */
	EReference getDataSetArgumentSpecification_DataSet();

	/**
	 * Returns the meta object for enum '{@link org.etsi.mts.tdl.ComponentInstanceRole <em>Component Instance Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Component Instance Role</em>'.
	 * @see org.etsi.mts.tdl.ComponentInstanceRole
	 * @generated
	 */
	EEnum getComponentInstanceRole();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TdlFactory getTdlFactory();

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
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.ElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.ElementImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getElement()
		 * @generated
		 */
		EClass ELEMENT = eINSTANCE.getElement();

		/**
		 * The meta object literal for the '<em><b>Comment</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT__COMMENT = eINSTANCE.getElement_Comment();

		/**
		 * The meta object literal for the '<em><b>Annotation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT__ANNOTATION = eINSTANCE.getElement_Annotation();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ELEMENT__NAME = eINSTANCE.getElement_Name();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.CommentImpl <em>Comment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.CommentImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getComment()
		 * @generated
		 */
		EClass COMMENT = eINSTANCE.getComment();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__BODY = eINSTANCE.getComment_Body();

		/**
		 * The meta object literal for the '<em><b>Commented Element</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMMENT__COMMENTED_ELEMENT = eINSTANCE.getComment_CommentedElement();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.AnnotationImpl <em>Annotation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.AnnotationImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getAnnotation()
		 * @generated
		 */
		EClass ANNOTATION = eINSTANCE.getAnnotation();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ANNOTATION__VALUE = eINSTANCE.getAnnotation_Value();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANNOTATION__KEY = eINSTANCE.getAnnotation_Key();

		/**
		 * The meta object literal for the '<em><b>Annotated Element</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANNOTATION__ANNOTATED_ELEMENT = eINSTANCE.getAnnotation_AnnotatedElement();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.AnnotationTypeImpl <em>Annotation Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.AnnotationTypeImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getAnnotationType()
		 * @generated
		 */
		EClass ANNOTATION_TYPE = eINSTANCE.getAnnotationType();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.PackageableElementImpl <em>Packageable Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.PackageableElementImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getPackageableElement()
		 * @generated
		 */
		EClass PACKAGEABLE_ELEMENT = eINSTANCE.getPackageableElement();

		/**
		 * The meta object literal for the '<em><b>Qualified Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGEABLE_ELEMENT__QUALIFIED_NAME = eINSTANCE.getPackageableElement_QualifiedName();

		/**
		 * The meta object literal for the '<em><b>Owning Package</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGEABLE_ELEMENT__OWNING_PACKAGE = eINSTANCE.getPackageableElement_OwningPackage();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.PackageImpl <em>Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.PackageImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getPackage()
		 * @generated
		 */
		EClass PACKAGE = eINSTANCE.getPackage();

		/**
		 * The meta object literal for the '<em><b>Import</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__IMPORT = eINSTANCE.getPackage_Import();

		/**
		 * The meta object literal for the '<em><b>Packaged Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__PACKAGED_ELEMENTS = eINSTANCE.getPackage_PackagedElements();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.ElementImportImpl <em>Element Import</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.ElementImportImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getElementImport()
		 * @generated
		 */
		EClass ELEMENT_IMPORT = eINSTANCE.getElementImport();

		/**
		 * The meta object literal for the '<em><b>Imported Element</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT_IMPORT__IMPORTED_ELEMENT = eINSTANCE.getElementImport_ImportedElement();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.TestObjectiveImpl <em>Test Objective</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.TestObjectiveImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTestObjective()
		 * @generated
		 */
		EClass TEST_OBJECTIVE = eINSTANCE.getTestObjective();

		/**
		 * The meta object literal for the '<em><b>Objective URI</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_OBJECTIVE__OBJECTIVE_URI = eINSTANCE.getTestObjective_ObjectiveURI();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_OBJECTIVE__DESCRIPTION = eINSTANCE.getTestObjective_Description();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.TestObjectiveRealizerImpl <em>Test Objective Realizer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.TestObjectiveRealizerImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTestObjectiveRealizer()
		 * @generated
		 */
		EClass TEST_OBJECTIVE_REALIZER = eINSTANCE.getTestObjectiveRealizer();

		/**
		 * The meta object literal for the '<em><b>Test Objective</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_OBJECTIVE_REALIZER__TEST_OBJECTIVE = eINSTANCE.getTestObjectiveRealizer_TestObjective();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.DataInstanceImpl <em>Data Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.DataInstanceImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getDataInstance()
		 * @generated
		 */
		EClass DATA_INSTANCE = eINSTANCE.getDataInstance();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_INSTANCE__PARAMETER = eINSTANCE.getDataInstance_Parameter();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.MappableDataElementImpl <em>Mappable Data Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.MappableDataElementImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getMappableDataElement()
		 * @generated
		 */
		EClass MAPPABLE_DATA_ELEMENT = eINSTANCE.getMappableDataElement();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.DataElementImpl <em>Data Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.DataElementImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getDataElement()
		 * @generated
		 */
		EClass DATA_ELEMENT = eINSTANCE.getDataElement();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.DataSetImpl <em>Data Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.DataSetImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getDataSet()
		 * @generated
		 */
		EClass DATA_SET = eINSTANCE.getDataSet();

		/**
		 * The meta object literal for the '<em><b>Data Instance</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_SET__DATA_INSTANCE = eINSTANCE.getDataSet_DataInstance();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.DataResourceMappingImpl <em>Data Resource Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.DataResourceMappingImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getDataResourceMapping()
		 * @generated
		 */
		EClass DATA_RESOURCE_MAPPING = eINSTANCE.getDataResourceMapping();

		/**
		 * The meta object literal for the '<em><b>Resource URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_RESOURCE_MAPPING__RESOURCE_URI = eINSTANCE.getDataResourceMapping_ResourceURI();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.DataElementMappingImpl <em>Data Element Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.DataElementMappingImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getDataElementMapping()
		 * @generated
		 */
		EClass DATA_ELEMENT_MAPPING = eINSTANCE.getDataElementMapping();

		/**
		 * The meta object literal for the '<em><b>Mappable Data Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_ELEMENT_MAPPING__MAPPABLE_DATA_ELEMENT = eINSTANCE.getDataElementMapping_MappableDataElement();

		/**
		 * The meta object literal for the '<em><b>Element URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_ELEMENT_MAPPING__ELEMENT_URI = eINSTANCE.getDataElementMapping_ElementURI();

		/**
		 * The meta object literal for the '<em><b>Data Resource Mapping</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_ELEMENT_MAPPING__DATA_RESOURCE_MAPPING = eINSTANCE.getDataElementMapping_DataResourceMapping();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.DataProxyImpl <em>Data Proxy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.DataProxyImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getDataProxy()
		 * @generated
		 */
		EClass DATA_PROXY = eINSTANCE.getDataProxy();

		/**
		 * The meta object literal for the '<em><b>Data Set</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_PROXY__DATA_SET = eINSTANCE.getDataProxy_DataSet();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.TimerImpl <em>Timer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.TimerImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTimer()
		 * @generated
		 */
		EClass TIMER = eINSTANCE.getTimer();

		/**
		 * The meta object literal for the '<em><b>Component Type</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIMER__COMPONENT_TYPE = eINSTANCE.getTimer_ComponentType();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.ComponentTypeImpl <em>Component Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.ComponentTypeImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getComponentType()
		 * @generated
		 */
		EClass COMPONENT_TYPE = eINSTANCE.getComponentType();

		/**
		 * The meta object literal for the '<em><b>Gate Type</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_TYPE__GATE_TYPE = eINSTANCE.getComponentType_GateType();

		/**
		 * The meta object literal for the '<em><b>Timer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_TYPE__TIMER = eINSTANCE.getComponentType_Timer();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.GateTypeImpl <em>Gate Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.GateTypeImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getGateType()
		 * @generated
		 */
		EClass GATE_TYPE = eINSTANCE.getGateType();

		/**
		 * The meta object literal for the '<em><b>Data Set</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GATE_TYPE__DATA_SET = eINSTANCE.getGateType_DataSet();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.TimerOperationImpl <em>Timer Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.TimerOperationImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTimerOperation()
		 * @generated
		 */
		EClass TIMER_OPERATION = eINSTANCE.getTimerOperation();

		/**
		 * The meta object literal for the '<em><b>Timer</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIMER_OPERATION__TIMER = eINSTANCE.getTimerOperation_Timer();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.AtomicBehaviourImpl <em>Atomic Behaviour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.AtomicBehaviourImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getAtomicBehaviour()
		 * @generated
		 */
		EClass ATOMIC_BEHAVIOUR = eINSTANCE.getAtomicBehaviour();

		/**
		 * The meta object literal for the '<em><b>Time Constraint</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATOMIC_BEHAVIOUR__TIME_CONSTRAINT = eINSTANCE.getAtomicBehaviour_TimeConstraint();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.BehaviourImpl <em>Behaviour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.BehaviourImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getBehaviour()
		 * @generated
		 */
		EClass BEHAVIOUR = eINSTANCE.getBehaviour();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.TimeConstraintImpl <em>Time Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.TimeConstraintImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTimeConstraint()
		 * @generated
		 */
		EClass TIME_CONSTRAINT = eINSTANCE.getTimeConstraint();

		/**
		 * The meta object literal for the '<em><b>Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME_CONSTRAINT__EXPRESSION = eINSTANCE.getTimeConstraint_Expression();

		/**
		 * The meta object literal for the '<em><b>Atomic Behaviour</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIME_CONSTRAINT__ATOMIC_BEHAVIOUR = eINSTANCE.getTimeConstraint_AtomicBehaviour();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.TimerStartImpl <em>Timer Start</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.TimerStartImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTimerStart()
		 * @generated
		 */
		EClass TIMER_START = eINSTANCE.getTimerStart();

		/**
		 * The meta object literal for the '<em><b>Period</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIMER_START__PERIOD = eINSTANCE.getTimerStart_Period();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.TimeImpl <em>Time</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.TimeImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTime()
		 * @generated
		 */
		EClass TIME = eINSTANCE.getTime();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIME__VALUE = eINSTANCE.getTime_Value();

		/**
		 * The meta object literal for the '<em><b>Unit</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIME__UNIT = eINSTANCE.getTime_Unit();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.TimeUnitImpl <em>Time Unit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.TimeUnitImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTimeUnit()
		 * @generated
		 */
		EClass TIME_UNIT = eINSTANCE.getTimeUnit();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.TimerStopImpl <em>Timer Stop</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.TimerStopImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTimerStop()
		 * @generated
		 */
		EClass TIMER_STOP = eINSTANCE.getTimerStop();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.TimeOutImpl <em>Time Out</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.TimeOutImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTimeOut()
		 * @generated
		 */
		EClass TIME_OUT = eINSTANCE.getTimeOut();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.TimeOperationImpl <em>Time Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.TimeOperationImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTimeOperation()
		 * @generated
		 */
		EClass TIME_OPERATION = eINSTANCE.getTimeOperation();

		/**
		 * The meta object literal for the '<em><b>Gate Instance</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIME_OPERATION__GATE_INSTANCE = eINSTANCE.getTimeOperation_GateInstance();

		/**
		 * The meta object literal for the '<em><b>Period</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIME_OPERATION__PERIOD = eINSTANCE.getTimeOperation_Period();

		/**
		 * The meta object literal for the '<em><b>Component Instance</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIME_OPERATION__COMPONENT_INSTANCE = eINSTANCE.getTimeOperation_ComponentInstance();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.GateInstanceImpl <em>Gate Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.GateInstanceImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getGateInstance()
		 * @generated
		 */
		EClass GATE_INSTANCE = eINSTANCE.getGateInstance();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GATE_INSTANCE__TYPE = eINSTANCE.getGateInstance_Type();

		/**
		 * The meta object literal for the '<em><b>Component Instance</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GATE_INSTANCE__COMPONENT_INSTANCE = eINSTANCE.getGateInstance_ComponentInstance();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.ComponentInstanceImpl <em>Component Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.ComponentInstanceImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getComponentInstance()
		 * @generated
		 */
		EClass COMPONENT_INSTANCE = eINSTANCE.getComponentInstance();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_INSTANCE__TYPE = eINSTANCE.getComponentInstance_Type();

		/**
		 * The meta object literal for the '<em><b>Role</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT_INSTANCE__ROLE = eINSTANCE.getComponentInstance_Role();

		/**
		 * The meta object literal for the '<em><b>Timer</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_INSTANCE__TIMER = eINSTANCE.getComponentInstance_Timer();

		/**
		 * The meta object literal for the '<em><b>Gate Instance</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_INSTANCE__GATE_INSTANCE = eINSTANCE.getComponentInstance_GateInstance();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.WaitImpl <em>Wait</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.WaitImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getWait()
		 * @generated
		 */
		EClass WAIT = eINSTANCE.getWait();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.QuiescenceImpl <em>Quiescence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.QuiescenceImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getQuiescence()
		 * @generated
		 */
		EClass QUIESCENCE = eINSTANCE.getQuiescence();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.TestConfigurationImpl <em>Test Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.TestConfigurationImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTestConfiguration()
		 * @generated
		 */
		EClass TEST_CONFIGURATION = eINSTANCE.getTestConfiguration();

		/**
		 * The meta object literal for the '<em><b>Component Instance</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CONFIGURATION__COMPONENT_INSTANCE = eINSTANCE.getTestConfiguration_ComponentInstance();

		/**
		 * The meta object literal for the '<em><b>Connection</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_CONFIGURATION__CONNECTION = eINSTANCE.getTestConfiguration_Connection();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.ConnectionImpl <em>Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.ConnectionImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getConnection()
		 * @generated
		 */
		EClass CONNECTION = eINSTANCE.getConnection();

		/**
		 * The meta object literal for the '<em><b>End Point</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION__END_POINT = eINSTANCE.getConnection_EndPoint();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.TestDescriptionImpl <em>Test Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.TestDescriptionImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTestDescription()
		 * @generated
		 */
		EClass TEST_DESCRIPTION = eINSTANCE.getTestDescription();

		/**
		 * The meta object literal for the '<em><b>Test Configuration</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_DESCRIPTION__TEST_CONFIGURATION = eINSTANCE.getTestDescription_TestConfiguration();

		/**
		 * The meta object literal for the '<em><b>Behaviour</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_DESCRIPTION__BEHAVIOUR = eINSTANCE.getTestDescription_Behaviour();

		/**
		 * The meta object literal for the '<em><b>Time Constraint</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_DESCRIPTION__TIME_CONSTRAINT = eINSTANCE.getTestDescription_TimeConstraint();

		/**
		 * The meta object literal for the '<em><b>Formal Parameter</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_DESCRIPTION__FORMAL_PARAMETER = eINSTANCE.getTestDescription_FormalParameter();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.CompoundBehaviourImpl <em>Compound Behaviour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.CompoundBehaviourImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getCompoundBehaviour()
		 * @generated
		 */
		EClass COMPOUND_BEHAVIOUR = eINSTANCE.getCompoundBehaviour();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.SingleCombinedBehaviourImpl <em>Single Combined Behaviour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.SingleCombinedBehaviourImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getSingleCombinedBehaviour()
		 * @generated
		 */
		EClass SINGLE_COMBINED_BEHAVIOUR = eINSTANCE.getSingleCombinedBehaviour();

		/**
		 * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SINGLE_COMBINED_BEHAVIOUR__BLOCK = eINSTANCE.getSingleCombinedBehaviour_Block();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.CombinedBehaviourImpl <em>Combined Behaviour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.CombinedBehaviourImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getCombinedBehaviour()
		 * @generated
		 */
		EClass COMBINED_BEHAVIOUR = eINSTANCE.getCombinedBehaviour();

		/**
		 * The meta object literal for the '<em><b>Periodic</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMBINED_BEHAVIOUR__PERIODIC = eINSTANCE.getCombinedBehaviour_Periodic();

		/**
		 * The meta object literal for the '<em><b>Exceptional</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMBINED_BEHAVIOUR__EXCEPTIONAL = eINSTANCE.getCombinedBehaviour_Exceptional();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.PeriodicBehaviourImpl <em>Periodic Behaviour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.PeriodicBehaviourImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getPeriodicBehaviour()
		 * @generated
		 */
		EClass PERIODIC_BEHAVIOUR = eINSTANCE.getPeriodicBehaviour();

		/**
		 * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERIODIC_BEHAVIOUR__BLOCK = eINSTANCE.getPeriodicBehaviour_Block();

		/**
		 * The meta object literal for the '<em><b>Period</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERIODIC_BEHAVIOUR__PERIOD = eINSTANCE.getPeriodicBehaviour_Period();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.BlockImpl <em>Block</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.BlockImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getBlock()
		 * @generated
		 */
		EClass BLOCK = eINSTANCE.getBlock();

		/**
		 * The meta object literal for the '<em><b>Behaviour</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BLOCK__BEHAVIOUR = eINSTANCE.getBlock_Behaviour();

		/**
		 * The meta object literal for the '<em><b>Guard</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLOCK__GUARD = eINSTANCE.getBlock_Guard();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.ExceptionalBehaviourImpl <em>Exceptional Behaviour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.ExceptionalBehaviourImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getExceptionalBehaviour()
		 * @generated
		 */
		EClass EXCEPTIONAL_BEHAVIOUR = eINSTANCE.getExceptionalBehaviour();

		/**
		 * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXCEPTIONAL_BEHAVIOUR__BLOCK = eINSTANCE.getExceptionalBehaviour_Block();

		/**
		 * The meta object literal for the '<em><b>Guarded Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXCEPTIONAL_BEHAVIOUR__GUARDED_COMPONENT = eINSTANCE.getExceptionalBehaviour_GuardedComponent();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.AlternativeBehaviourImpl <em>Alternative Behaviour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.AlternativeBehaviourImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getAlternativeBehaviour()
		 * @generated
		 */
		EClass ALTERNATIVE_BEHAVIOUR = eINSTANCE.getAlternativeBehaviour();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.MultipleCombinedBehaviourImpl <em>Multiple Combined Behaviour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.MultipleCombinedBehaviourImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getMultipleCombinedBehaviour()
		 * @generated
		 */
		EClass MULTIPLE_COMBINED_BEHAVIOUR = eINSTANCE.getMultipleCombinedBehaviour();

		/**
		 * The meta object literal for the '<em><b>Block</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MULTIPLE_COMBINED_BEHAVIOUR__BLOCK = eINSTANCE.getMultipleCombinedBehaviour_Block();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.ParallelBehaviourImpl <em>Parallel Behaviour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.ParallelBehaviourImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getParallelBehaviour()
		 * @generated
		 */
		EClass PARALLEL_BEHAVIOUR = eINSTANCE.getParallelBehaviour();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.BoundedLoopBehaviourImpl <em>Bounded Loop Behaviour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.BoundedLoopBehaviourImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getBoundedLoopBehaviour()
		 * @generated
		 */
		EClass BOUNDED_LOOP_BEHAVIOUR = eINSTANCE.getBoundedLoopBehaviour();

		/**
		 * The meta object literal for the '<em><b>Num Iteration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOUNDED_LOOP_BEHAVIOUR__NUM_ITERATION = eINSTANCE.getBoundedLoopBehaviour_NumIteration();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.UnboundedLoopBehaviourImpl <em>Unbounded Loop Behaviour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.UnboundedLoopBehaviourImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getUnboundedLoopBehaviour()
		 * @generated
		 */
		EClass UNBOUNDED_LOOP_BEHAVIOUR = eINSTANCE.getUnboundedLoopBehaviour();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.ConditionalBehaviourImpl <em>Conditional Behaviour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.ConditionalBehaviourImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getConditionalBehaviour()
		 * @generated
		 */
		EClass CONDITIONAL_BEHAVIOUR = eINSTANCE.getConditionalBehaviour();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.StopImpl <em>Stop</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.StopImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getStop()
		 * @generated
		 */
		EClass STOP = eINSTANCE.getStop();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.TestDescriptionReferenceImpl <em>Test Description Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.TestDescriptionReferenceImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getTestDescriptionReference()
		 * @generated
		 */
		EClass TEST_DESCRIPTION_REFERENCE = eINSTANCE.getTestDescriptionReference();

		/**
		 * The meta object literal for the '<em><b>Referenced Test Description</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_DESCRIPTION_REFERENCE__REFERENCED_TEST_DESCRIPTION = eINSTANCE.getTestDescriptionReference_ReferencedTestDescription();

		/**
		 * The meta object literal for the '<em><b>Actual Parameter</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_DESCRIPTION_REFERENCE__ACTUAL_PARAMETER = eINSTANCE.getTestDescriptionReference_ActualParameter();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.ArgumentSpecificationImpl <em>Argument Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.ArgumentSpecificationImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getArgumentSpecification()
		 * @generated
		 */
		EClass ARGUMENT_SPECIFICATION = eINSTANCE.getArgumentSpecification();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.VerdictAssignmentImpl <em>Verdict Assignment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.VerdictAssignmentImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getVerdictAssignment()
		 * @generated
		 */
		EClass VERDICT_ASSIGNMENT = eINSTANCE.getVerdictAssignment();

		/**
		 * The meta object literal for the '<em><b>Verdict Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VERDICT_ASSIGNMENT__VERDICT_TYPE = eINSTANCE.getVerdictAssignment_VerdictType();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.VerdictTypeImpl <em>Verdict Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.VerdictTypeImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getVerdictType()
		 * @generated
		 */
		EClass VERDICT_TYPE = eINSTANCE.getVerdictType();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.ActionReferenceImpl <em>Action Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.ActionReferenceImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getActionReference()
		 * @generated
		 */
		EClass ACTION_REFERENCE = eINSTANCE.getActionReference();

		/**
		 * The meta object literal for the '<em><b>Component Instance</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_REFERENCE__COMPONENT_INSTANCE = eINSTANCE.getActionReference_ComponentInstance();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION_REFERENCE__ACTION = eINSTANCE.getActionReference_Action();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.ActionImpl <em>Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.ActionImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getAction()
		 * @generated
		 */
		EClass ACTION = eINSTANCE.getAction();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__BODY = eINSTANCE.getAction_Body();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.InteractionImpl <em>Interaction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.InteractionImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getInteraction()
		 * @generated
		 */
		EClass INTERACTION = eINSTANCE.getInteraction();

		/**
		 * The meta object literal for the '<em><b>Argument</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERACTION__ARGUMENT = eINSTANCE.getInteraction_Argument();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERACTION__SOURCE = eINSTANCE.getInteraction_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERACTION__TARGET = eINSTANCE.getInteraction_Target();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.OptionalBehaviourImpl <em>Optional Behaviour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.OptionalBehaviourImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getOptionalBehaviour()
		 * @generated
		 */
		EClass OPTIONAL_BEHAVIOUR = eINSTANCE.getOptionalBehaviour();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.DefaultBehaviourImpl <em>Default Behaviour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.DefaultBehaviourImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getDefaultBehaviour()
		 * @generated
		 */
		EClass DEFAULT_BEHAVIOUR = eINSTANCE.getDefaultBehaviour();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.InterruptBehaviourImpl <em>Interrupt Behaviour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.InterruptBehaviourImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getInterruptBehaviour()
		 * @generated
		 */
		EClass INTERRUPT_BEHAVIOUR = eINSTANCE.getInterruptBehaviour();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.BreakImpl <em>Break</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.BreakImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getBreak()
		 * @generated
		 */
		EClass BREAK = eINSTANCE.getBreak();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.DataInstanceArgumentSpecificationImpl <em>Data Instance Argument Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.DataInstanceArgumentSpecificationImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getDataInstanceArgumentSpecification()
		 * @generated
		 */
		EClass DATA_INSTANCE_ARGUMENT_SPECIFICATION = eINSTANCE.getDataInstanceArgumentSpecification();

		/**
		 * The meta object literal for the '<em><b>Data Instance</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_INSTANCE_ARGUMENT_SPECIFICATION__DATA_INSTANCE = eINSTANCE.getDataInstanceArgumentSpecification_DataInstance();

		/**
		 * The meta object literal for the '<em><b>Actual Parameter</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_INSTANCE_ARGUMENT_SPECIFICATION__ACTUAL_PARAMETER = eINSTANCE.getDataInstanceArgumentSpecification_ActualParameter();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.DataProxyArgumentSpecificationImpl <em>Data Proxy Argument Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.DataProxyArgumentSpecificationImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getDataProxyArgumentSpecification()
		 * @generated
		 */
		EClass DATA_PROXY_ARGUMENT_SPECIFICATION = eINSTANCE.getDataProxyArgumentSpecification();

		/**
		 * The meta object literal for the '<em><b>Data Proxy</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_PROXY_ARGUMENT_SPECIFICATION__DATA_PROXY = eINSTANCE.getDataProxyArgumentSpecification_DataProxy();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.impl.DataSetArgumentSpecificationImpl <em>Data Set Argument Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.impl.DataSetArgumentSpecificationImpl
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getDataSetArgumentSpecification()
		 * @generated
		 */
		EClass DATA_SET_ARGUMENT_SPECIFICATION = eINSTANCE.getDataSetArgumentSpecification();

		/**
		 * The meta object literal for the '<em><b>Data Set</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_SET_ARGUMENT_SPECIFICATION__DATA_SET = eINSTANCE.getDataSetArgumentSpecification_DataSet();

		/**
		 * The meta object literal for the '{@link org.etsi.mts.tdl.ComponentInstanceRole <em>Component Instance Role</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.etsi.mts.tdl.ComponentInstanceRole
		 * @see org.etsi.mts.tdl.impl.TdlPackageImpl#getComponentInstanceRole()
		 * @generated
		 */
		EEnum COMPONENT_INSTANCE_ROLE = eINSTANCE.getComponentInstanceRole();

	}

} //TdlPackage
