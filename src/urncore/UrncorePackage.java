/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import ca.mcgill.sel.core.CorePackage;
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
 * @see urncore.UrncoreFactory
 * @model kind="package"
 * @generated
 */
public interface UrncorePackage extends EPackage {
    /**
	 * The package name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNAME = "urncore";

    /**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_URI = "http:///urncore.ecore";

    /**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_PREFIX = "urncore";

    /**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    UrncorePackage eINSTANCE = urncore.impl.UrncorePackageImpl.init();

    /**
	 * The meta object id for the '{@link urncore.impl.URNdefinitionImpl <em>UR Ndefinition</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see urncore.impl.URNdefinitionImpl
	 * @see urncore.impl.UrncorePackageImpl#getURNdefinition()
	 * @generated
	 */
    int UR_NDEFINITION = 0;

    /**
	 * The feature id for the '<em><b>Urnspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UR_NDEFINITION__URNSPEC = 0;

    /**
	 * The feature id for the '<em><b>Responsibilities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UR_NDEFINITION__RESPONSIBILITIES = 1;

    /**
	 * The feature id for the '<em><b>Spec Diagrams</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UR_NDEFINITION__SPEC_DIAGRAMS = 2;

    /**
	 * The feature id for the '<em><b>Concerns</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UR_NDEFINITION__CONCERNS = 3;

				/**
	 * The feature id for the '<em><b>Components</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UR_NDEFINITION__COMPONENTS = 4;

				/**
	 * The feature id for the '<em><b>Component Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UR_NDEFINITION__COMPONENT_TYPES = 5;

    /**
	 * The number of structural features of the '<em>UR Ndefinition</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UR_NDEFINITION_FEATURE_COUNT = 6;

    /**
	 * The meta object id for the '{@link urncore.impl.URNmodelElementImpl <em>UR Nmodel Element</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see urncore.impl.URNmodelElementImpl
	 * @see urncore.impl.UrncorePackageImpl#getURNmodelElement()
	 * @generated
	 */
    int UR_NMODEL_ELEMENT = 11;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UR_NMODEL_ELEMENT__NAME = CorePackage.CORE_NAMED_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UR_NMODEL_ELEMENT__FROM_LINKS = CorePackage.CORE_NAMED_ELEMENT_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UR_NMODEL_ELEMENT__TO_LINKS = CorePackage.CORE_NAMED_ELEMENT_FEATURE_COUNT + 1;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UR_NMODEL_ELEMENT__ID = CorePackage.CORE_NAMED_ELEMENT_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UR_NMODEL_ELEMENT__DESCRIPTION = CorePackage.CORE_NAMED_ELEMENT_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UR_NMODEL_ELEMENT__METADATA = CorePackage.CORE_NAMED_ELEMENT_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UR_NMODEL_ELEMENT__INCONCERN = CorePackage.CORE_NAMED_ELEMENT_FEATURE_COUNT + 5;

    /**
	 * The number of structural features of the '<em>UR Nmodel Element</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UR_NMODEL_ELEMENT_FEATURE_COUNT = CorePackage.CORE_NAMED_ELEMENT_FEATURE_COUNT + 6;

    /**
	 * The meta object id for the '{@link urncore.impl.UCMmodelElementImpl <em>UC Mmodel Element</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see urncore.impl.UCMmodelElementImpl
	 * @see urncore.impl.UrncorePackageImpl#getUCMmodelElement()
	 * @generated
	 */
    int UC_MMODEL_ELEMENT = 4;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UC_MMODEL_ELEMENT__NAME = UR_NMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UC_MMODEL_ELEMENT__FROM_LINKS = UR_NMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UC_MMODEL_ELEMENT__TO_LINKS = UR_NMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UC_MMODEL_ELEMENT__ID = UR_NMODEL_ELEMENT__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UC_MMODEL_ELEMENT__DESCRIPTION = UR_NMODEL_ELEMENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UC_MMODEL_ELEMENT__METADATA = UR_NMODEL_ELEMENT__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UC_MMODEL_ELEMENT__INCONCERN = UR_NMODEL_ELEMENT__INCONCERN;

    /**
	 * The number of structural features of the '<em>UC Mmodel Element</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UC_MMODEL_ELEMENT_FEATURE_COUNT = UR_NMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
	 * The meta object id for the '{@link urncore.impl.ResponsibilityImpl <em>Responsibility</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see urncore.impl.ResponsibilityImpl
	 * @see urncore.impl.UrncorePackageImpl#getResponsibility()
	 * @generated
	 */
    int RESPONSIBILITY = 1;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESPONSIBILITY__NAME = UC_MMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESPONSIBILITY__FROM_LINKS = UC_MMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESPONSIBILITY__TO_LINKS = UC_MMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESPONSIBILITY__ID = UC_MMODEL_ELEMENT__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESPONSIBILITY__DESCRIPTION = UC_MMODEL_ELEMENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY__METADATA = UC_MMODEL_ELEMENT__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESPONSIBILITY__INCONCERN = UC_MMODEL_ELEMENT__INCONCERN;

    /**
	 * The feature id for the '<em><b>Empty</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESPONSIBILITY__EMPTY = UC_MMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBILITY__EXPRESSION = UC_MMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESPONSIBILITY__CONTEXT = UC_MMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Urndefinition</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESPONSIBILITY__URNDEFINITION = UC_MMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Demands</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESPONSIBILITY__DEMANDS = UC_MMODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>Resp Refs</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESPONSIBILITY__RESP_REFS = UC_MMODEL_ELEMENT_FEATURE_COUNT + 5;

    /**
	 * The feature id for the '<em><b>Parent Bindings</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESPONSIBILITY__PARENT_BINDINGS = UC_MMODEL_ELEMENT_FEATURE_COUNT + 6;

    /**
	 * The number of structural features of the '<em>Responsibility</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RESPONSIBILITY_FEATURE_COUNT = UC_MMODEL_ELEMENT_FEATURE_COUNT + 7;

    /**
	 * The meta object id for the '{@link urncore.impl.ComponentImpl <em>Component</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see urncore.impl.ComponentImpl
	 * @see urncore.impl.UrncorePackageImpl#getComponent()
	 * @generated
	 */
    int COMPONENT = 2;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT__NAME = UC_MMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT__FROM_LINKS = UC_MMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT__TO_LINKS = UC_MMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT__ID = UC_MMODEL_ELEMENT__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT__DESCRIPTION = UC_MMODEL_ELEMENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__METADATA = UC_MMODEL_ELEMENT__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT__INCONCERN = UC_MMODEL_ELEMENT__INCONCERN;

    /**
	 * The feature id for the '<em><b>Line Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__LINE_COLOR = UC_MMODEL_ELEMENT_FEATURE_COUNT + 0;

				/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__FILL_COLOR = UC_MMODEL_ELEMENT_FEATURE_COUNT + 1;

				/**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__FILLED = UC_MMODEL_ELEMENT_FEATURE_COUNT + 2;

				/**
	 * The feature id for the '<em><b>Cont Refs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__CONT_REFS = UC_MMODEL_ELEMENT_FEATURE_COUNT + 3;

				/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT__KIND = UC_MMODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>Protected</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT__PROTECTED = UC_MMODEL_ELEMENT_FEATURE_COUNT + 5;

    /**
	 * The feature id for the '<em><b>Slot</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT__SLOT = UC_MMODEL_ELEMENT_FEATURE_COUNT + 6;

    /**
	 * The feature id for the '<em><b>Context</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__CONTEXT = UC_MMODEL_ELEMENT_FEATURE_COUNT + 7;

				/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT__TYPE = UC_MMODEL_ELEMENT_FEATURE_COUNT + 8;

    /**
	 * The feature id for the '<em><b>Urndefinition</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__URNDEFINITION = UC_MMODEL_ELEMENT_FEATURE_COUNT + 9;

				/**
	 * The feature id for the '<em><b>Included Component</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT__INCLUDED_COMPONENT = UC_MMODEL_ELEMENT_FEATURE_COUNT + 10;

				/**
	 * The feature id for the '<em><b>Including Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__INCLUDING_COMPONENT = UC_MMODEL_ELEMENT_FEATURE_COUNT + 11;

				/**
	 * The feature id for the '<em><b>Resource</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT__RESOURCE = UC_MMODEL_ELEMENT_FEATURE_COUNT + 12;

				/**
	 * The feature id for the '<em><b>Host</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT__HOST = UC_MMODEL_ELEMENT_FEATURE_COUNT + 13;

				/**
	 * The number of structural features of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT_FEATURE_COUNT = UC_MMODEL_ELEMENT_FEATURE_COUNT + 14;

    /**
	 * The meta object id for the '{@link urncore.impl.ComponentTypeImpl <em>Component Type</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see urncore.impl.ComponentTypeImpl
	 * @see urncore.impl.UrncorePackageImpl#getComponentType()
	 * @generated
	 */
    int COMPONENT_TYPE = 3;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT_TYPE__NAME = UC_MMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT_TYPE__FROM_LINKS = UC_MMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT_TYPE__TO_LINKS = UC_MMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT_TYPE__ID = UC_MMODEL_ELEMENT__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT_TYPE__DESCRIPTION = UC_MMODEL_ELEMENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__METADATA = UC_MMODEL_ELEMENT__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT_TYPE__INCONCERN = UC_MMODEL_ELEMENT__INCONCERN;

    /**
	 * The feature id for the '<em><b>Instances</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT_TYPE__INSTANCES = UC_MMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Urndefinition</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_TYPE__URNDEFINITION = UC_MMODEL_ELEMENT_FEATURE_COUNT + 1;

				/**
	 * The number of structural features of the '<em>Component Type</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT_TYPE_FEATURE_COUNT = UC_MMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
	 * The meta object id for the '{@link urncore.impl.GRLmodelElementImpl <em>GR Lmodel Element</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see urncore.impl.GRLmodelElementImpl
	 * @see urncore.impl.UrncorePackageImpl#getGRLmodelElement()
	 * @generated
	 */
    int GR_LMODEL_ELEMENT = 5;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GR_LMODEL_ELEMENT__NAME = UR_NMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GR_LMODEL_ELEMENT__FROM_LINKS = UR_NMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GR_LMODEL_ELEMENT__TO_LINKS = UR_NMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GR_LMODEL_ELEMENT__ID = UR_NMODEL_ELEMENT__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GR_LMODEL_ELEMENT__DESCRIPTION = UR_NMODEL_ELEMENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GR_LMODEL_ELEMENT__METADATA = UR_NMODEL_ELEMENT__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GR_LMODEL_ELEMENT__INCONCERN = UR_NMODEL_ELEMENT__INCONCERN;

    /**
	 * The number of structural features of the '<em>GR Lmodel Element</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GR_LMODEL_ELEMENT_FEATURE_COUNT = UR_NMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
	 * The meta object id for the '{@link urncore.impl.LabelImpl <em>Label</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see urncore.impl.LabelImpl
	 * @see urncore.impl.UrncorePackageImpl#getLabel()
	 * @generated
	 */
    int LABEL = 7;

    /**
	 * The feature id for the '<em><b>Delta X</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LABEL__DELTA_X = 0;

    /**
	 * The feature id for the '<em><b>Delta Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LABEL__DELTA_Y = 1;

    /**
	 * The number of structural features of the '<em>Label</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LABEL_FEATURE_COUNT = 2;

    /**
	 * The meta object id for the '{@link urncore.impl.NodeLabelImpl <em>Node Label</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see urncore.impl.NodeLabelImpl
	 * @see urncore.impl.UrncorePackageImpl#getNodeLabel()
	 * @generated
	 */
    int NODE_LABEL = 6;

    /**
	 * The feature id for the '<em><b>Delta X</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int NODE_LABEL__DELTA_X = LABEL__DELTA_X;

    /**
	 * The feature id for the '<em><b>Delta Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int NODE_LABEL__DELTA_Y = LABEL__DELTA_Y;

    /**
	 * The feature id for the '<em><b>Node</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int NODE_LABEL__NODE = LABEL_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Node Label</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int NODE_LABEL_FEATURE_COUNT = LABEL_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link urncore.impl.ComponentLabelImpl <em>Component Label</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see urncore.impl.ComponentLabelImpl
	 * @see urncore.impl.UrncorePackageImpl#getComponentLabel()
	 * @generated
	 */
    int COMPONENT_LABEL = 8;

    /**
	 * The feature id for the '<em><b>Delta X</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT_LABEL__DELTA_X = LABEL__DELTA_X;

    /**
	 * The feature id for the '<em><b>Delta Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT_LABEL__DELTA_Y = LABEL__DELTA_Y;

    /**
	 * The feature id for the '<em><b>Cont Ref</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT_LABEL__CONT_REF = LABEL_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Component Label</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT_LABEL_FEATURE_COUNT = LABEL_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link urncore.impl.ConditionImpl <em>Condition</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see urncore.impl.ConditionImpl
	 * @see urncore.impl.UrncorePackageImpl#getCondition()
	 * @generated
	 */
    int CONDITION = 9;

    /**
	 * The feature id for the '<em><b>Delta X</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONDITION__DELTA_X = LABEL__DELTA_X;

    /**
	 * The feature id for the '<em><b>Delta Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONDITION__DELTA_Y = LABEL__DELTA_Y;

    /**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONDITION__LABEL = LABEL_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONDITION__EXPRESSION = LABEL_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONDITION__DESCRIPTION = LABEL_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Start Point</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONDITION__START_POINT = LABEL_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>End Point</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONDITION__END_POINT = LABEL_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>Plugin Binding</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONDITION__PLUGIN_BINDING = LABEL_FEATURE_COUNT + 5;

    /**
	 * The feature id for the '<em><b>Node Connection</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONDITION__NODE_CONNECTION = LABEL_FEATURE_COUNT + 6;

    /**
	 * The feature id for the '<em><b>Concern</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION__CONCERN = LABEL_FEATURE_COUNT + 7;

				/**
	 * The feature id for the '<em><b>Scenario Def Pre</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION__SCENARIO_DEF_PRE = LABEL_FEATURE_COUNT + 8;

    /**
	 * The feature id for the '<em><b>Scenario Def Post</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION__SCENARIO_DEF_POST = LABEL_FEATURE_COUNT + 9;

    /**
	 * The number of structural features of the '<em>Condition</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONDITION_FEATURE_COUNT = LABEL_FEATURE_COUNT + 10;

    /**
	 * The meta object id for the '{@link urncore.IURNDiagram <em>IURN Diagram</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see urncore.IURNDiagram
	 * @see urncore.impl.UrncorePackageImpl#getIURNDiagram()
	 * @generated
	 */
    int IURN_DIAGRAM = 10;

    /**
	 * The feature id for the '<em><b>Urndefinition</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_DIAGRAM__URNDEFINITION = 0;

    /**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_DIAGRAM__NODES = 1;

    /**
	 * The feature id for the '<em><b>Cont Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_DIAGRAM__CONT_REFS = 2;

    /**
	 * The feature id for the '<em><b>Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_DIAGRAM__CONNECTIONS = 3;

    /**
	 * The feature id for the '<em><b>Concern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IURN_DIAGRAM__CONCERN = 4;

    /**
	 * The feature id for the '<em><b>Comments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IURN_DIAGRAM__COMMENTS = 5;

				/**
	 * The number of structural features of the '<em>IURN Diagram</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_DIAGRAM_FEATURE_COUNT = 6;

    /**
	 * The meta object id for the '{@link urncore.IURNNode <em>IURN Node</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see urncore.IURNNode
	 * @see urncore.impl.UrncorePackageImpl#getIURNNode()
	 * @generated
	 */
    int IURN_NODE = 12;

    /**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_NODE__X = 0;

    /**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_NODE__Y = 1;

    /**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_NODE__DIAGRAM = 2;

    /**
	 * The feature id for the '<em><b>Cont Ref</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_NODE__CONT_REF = 3;

    /**
	 * The feature id for the '<em><b>Succ</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_NODE__SUCC = 4;

    /**
	 * The feature id for the '<em><b>Pred</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_NODE__PRED = 5;

    /**
	 * The feature id for the '<em><b>Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_NODE__LABEL = 6;

    /**
	 * The number of structural features of the '<em>IURN Node</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_NODE_FEATURE_COUNT = 7;

    /**
	 * The meta object id for the '{@link urncore.IURNContainerRef <em>IURN Container Ref</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see urncore.IURNContainerRef
	 * @see urncore.impl.UrncorePackageImpl#getIURNContainerRef()
	 * @generated
	 */
    int IURN_CONTAINER_REF = 13;

    /**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_CONTAINER_REF__X = 0;

    /**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_CONTAINER_REF__Y = 1;

    /**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_CONTAINER_REF__WIDTH = 2;

    /**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_CONTAINER_REF__HEIGHT = 3;

    /**
	 * The feature id for the '<em><b>Fixed</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_CONTAINER_REF__FIXED = 4;

    /**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_CONTAINER_REF__DIAGRAM = 5;

    /**
	 * The feature id for the '<em><b>Cont Def</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_CONTAINER_REF__CONT_DEF = 6;

    /**
	 * The feature id for the '<em><b>Nodes</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_CONTAINER_REF__NODES = 7;

    /**
	 * The feature id for the '<em><b>Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_CONTAINER_REF__LABEL = 8;

    /**
	 * The feature id for the '<em><b>Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_CONTAINER_REF__PARENT = 9;

    /**
	 * The feature id for the '<em><b>Children</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_CONTAINER_REF__CHILDREN = 10;

    /**
	 * The number of structural features of the '<em>IURN Container Ref</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_CONTAINER_REF_FEATURE_COUNT = 11;

    /**
	 * The meta object id for the '{@link urncore.IURNContainer <em>IURN Container</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see urncore.IURNContainer
	 * @see urncore.impl.UrncorePackageImpl#getIURNContainer()
	 * @generated
	 */
    int IURN_CONTAINER = 14;

    /**
	 * The feature id for the '<em><b>Line Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_CONTAINER__LINE_COLOR = 0;

    /**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_CONTAINER__FILL_COLOR = 1;

    /**
	 * The feature id for the '<em><b>Filled</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_CONTAINER__FILLED = 2;

    /**
	 * The feature id for the '<em><b>Cont Refs</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_CONTAINER__CONT_REFS = 3;

    /**
	 * The number of structural features of the '<em>IURN Container</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_CONTAINER_FEATURE_COUNT = 4;

    /**
	 * The meta object id for the '{@link urncore.IURNConnection <em>IURN Connection</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see urncore.IURNConnection
	 * @see urncore.impl.UrncorePackageImpl#getIURNConnection()
	 * @generated
	 */
    int IURN_CONNECTION = 15;

    /**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_CONNECTION__SOURCE = 0;

    /**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_CONNECTION__TARGET = 1;

    /**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_CONNECTION__DIAGRAM = 2;

    /**
	 * The feature id for the '<em><b>Label</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IURN_CONNECTION__LABEL = 3;

				/**
	 * The number of structural features of the '<em>IURN Connection</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int IURN_CONNECTION_FEATURE_COUNT = 4;

    /**
	 * The meta object id for the '{@link urncore.impl.MetadataImpl <em>Metadata</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see urncore.impl.MetadataImpl
	 * @see urncore.impl.UrncorePackageImpl#getMetadata()
	 * @generated
	 */
	int METADATA = 16;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA__NAME = 0;

    /**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA__VALUE = 1;

    /**
	 * The number of structural features of the '<em>Metadata</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METADATA_FEATURE_COUNT = 2;

    /**
	 * The meta object id for the '{@link urncore.impl.ConcernImpl <em>Concern</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see urncore.impl.ConcernImpl
	 * @see urncore.impl.UrncorePackageImpl#getConcern()
	 * @generated
	 */
	int CONCERN = 17;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCERN__NAME = UR_NMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCERN__FROM_LINKS = UR_NMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCERN__TO_LINKS = UR_NMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCERN__ID = UR_NMODEL_ELEMENT__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCERN__DESCRIPTION = UR_NMODEL_ELEMENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCERN__METADATA = UR_NMODEL_ELEMENT__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONCERN__INCONCERN = UR_NMODEL_ELEMENT__INCONCERN;

    /**
	 * The feature id for the '<em><b>Urndefinition</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCERN__URNDEFINITION = UR_NMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Spec Diagrams</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCERN__SPEC_DIAGRAMS = UR_NMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONCERN__ELEMENTS = UR_NMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Core Concern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCERN__CORE_CONCERN = UR_NMODEL_ELEMENT_FEATURE_COUNT + 3;

				/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCERN__CONDITION = UR_NMODEL_ELEMENT_FEATURE_COUNT + 4;

				/**
	 * The number of structural features of the '<em>Concern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCERN_FEATURE_COUNT = UR_NMODEL_ELEMENT_FEATURE_COUNT + 5;

    /**
	 * The meta object id for the '{@link urncore.impl.ConnectionLabelImpl <em>Connection Label</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see urncore.impl.ConnectionLabelImpl
	 * @see urncore.impl.UrncorePackageImpl#getConnectionLabel()
	 * @generated
	 */
	int CONNECTION_LABEL = 18;

				/**
	 * The feature id for the '<em><b>Delta X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_LABEL__DELTA_X = LABEL__DELTA_X;

				/**
	 * The feature id for the '<em><b>Delta Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_LABEL__DELTA_Y = LABEL__DELTA_Y;

				/**
	 * The feature id for the '<em><b>Connection</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_LABEL__CONNECTION = LABEL_FEATURE_COUNT + 0;

				/**
	 * The number of structural features of the '<em>Connection Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_LABEL_FEATURE_COUNT = LABEL_FEATURE_COUNT + 1;

				/**
	 * The meta object id for the '{@link urncore.impl.CommentImpl <em>Comment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see urncore.impl.CommentImpl
	 * @see urncore.impl.UrncorePackageImpl#getComment()
	 * @generated
	 */
	int COMMENT = 19;

				/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__DESCRIPTION = 0;

				/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__X = 1;

				/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__Y = 2;

				/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__WIDTH = 3;

				/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__HEIGHT = 4;

				/**
	 * The feature id for the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__FILL_COLOR = 5;

				/**
	 * The feature id for the '<em><b>Diagram</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT__DIAGRAM = 6;

				/**
	 * The number of structural features of the '<em>Comment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMMENT_FEATURE_COUNT = 7;

				/**
	 * The meta object id for the '{@link urncore.ComponentKind <em>Component Kind</em>}' enum.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see urncore.ComponentKind
	 * @see urncore.impl.UrncorePackageImpl#getComponentKind()
	 * @generated
	 */
    int COMPONENT_KIND = 20;

    /**
	 * Returns the meta object for class '{@link urncore.URNdefinition <em>UR Ndefinition</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>UR Ndefinition</em>'.
	 * @see urncore.URNdefinition
	 * @generated
	 */
    EClass getURNdefinition();

    /**
	 * Returns the meta object for the container reference '{@link urncore.URNdefinition#getUrnspec <em>Urnspec</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Urnspec</em>'.
	 * @see urncore.URNdefinition#getUrnspec()
	 * @see #getURNdefinition()
	 * @generated
	 */
    EReference getURNdefinition_Urnspec();

    /**
	 * Returns the meta object for the containment reference list '{@link urncore.URNdefinition#getResponsibilities <em>Responsibilities</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Responsibilities</em>'.
	 * @see urncore.URNdefinition#getResponsibilities()
	 * @see #getURNdefinition()
	 * @generated
	 */
    EReference getURNdefinition_Responsibilities();

    /**
	 * Returns the meta object for the containment reference list '{@link urncore.URNdefinition#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Components</em>'.
	 * @see urncore.URNdefinition#getComponents()
	 * @see #getURNdefinition()
	 * @generated
	 */
    EReference getURNdefinition_Components();

    /**
	 * Returns the meta object for the containment reference list '{@link urncore.URNdefinition#getComponentTypes <em>Component Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Component Types</em>'.
	 * @see urncore.URNdefinition#getComponentTypes()
	 * @see #getURNdefinition()
	 * @generated
	 */
	EReference getURNdefinition_ComponentTypes();

				/**
	 * Returns the meta object for the containment reference list '{@link urncore.URNdefinition#getSpecDiagrams <em>Spec Diagrams</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Spec Diagrams</em>'.
	 * @see urncore.URNdefinition#getSpecDiagrams()
	 * @see #getURNdefinition()
	 * @generated
	 */
    EReference getURNdefinition_SpecDiagrams();

    /**
	 * Returns the meta object for the containment reference list '{@link urncore.URNdefinition#getConcerns <em>Concerns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Concerns</em>'.
	 * @see urncore.URNdefinition#getConcerns()
	 * @see #getURNdefinition()
	 * @generated
	 */
	EReference getURNdefinition_Concerns();

    /**
	 * Returns the meta object for class '{@link urncore.Responsibility <em>Responsibility</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Responsibility</em>'.
	 * @see urncore.Responsibility
	 * @generated
	 */
    EClass getResponsibility();

    /**
	 * Returns the meta object for the attribute '{@link urncore.Responsibility#isEmpty <em>Empty</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Empty</em>'.
	 * @see urncore.Responsibility#isEmpty()
	 * @see #getResponsibility()
	 * @generated
	 */
    EAttribute getResponsibility_Empty();

    /**
	 * Returns the meta object for the attribute '{@link urncore.Responsibility#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expression</em>'.
	 * @see urncore.Responsibility#getExpression()
	 * @see #getResponsibility()
	 * @generated
	 */
	EAttribute getResponsibility_Expression();

    /**
	 * Returns the meta object for the attribute '{@link urncore.Responsibility#isContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Context</em>'.
	 * @see urncore.Responsibility#isContext()
	 * @see #getResponsibility()
	 * @generated
	 */
    EAttribute getResponsibility_Context();

    /**
	 * Returns the meta object for the container reference '{@link urncore.Responsibility#getUrndefinition <em>Urndefinition</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Urndefinition</em>'.
	 * @see urncore.Responsibility#getUrndefinition()
	 * @see #getResponsibility()
	 * @generated
	 */
    EReference getResponsibility_Urndefinition();

    /**
	 * Returns the meta object for the containment reference list '{@link urncore.Responsibility#getDemands <em>Demands</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Demands</em>'.
	 * @see urncore.Responsibility#getDemands()
	 * @see #getResponsibility()
	 * @generated
	 */
    EReference getResponsibility_Demands();

    /**
	 * Returns the meta object for the reference list '{@link urncore.Responsibility#getRespRefs <em>Resp Refs</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Resp Refs</em>'.
	 * @see urncore.Responsibility#getRespRefs()
	 * @see #getResponsibility()
	 * @generated
	 */
    EReference getResponsibility_RespRefs();

    /**
	 * Returns the meta object for the reference list '{@link urncore.Responsibility#getParentBindings <em>Parent Bindings</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parent Bindings</em>'.
	 * @see urncore.Responsibility#getParentBindings()
	 * @see #getResponsibility()
	 * @generated
	 */
    EReference getResponsibility_ParentBindings();

    /**
	 * Returns the meta object for class '{@link urncore.Component <em>Component</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component</em>'.
	 * @see urncore.Component
	 * @generated
	 */
    EClass getComponent();

    /**
	 * Returns the meta object for the attribute '{@link urncore.Component#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see urncore.Component#getKind()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Kind();

				/**
	 * Returns the meta object for the attribute '{@link urncore.Component#isProtected <em>Protected</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Protected</em>'.
	 * @see urncore.Component#isProtected()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Protected();

				/**
	 * Returns the meta object for the attribute '{@link urncore.Component#isSlot <em>Slot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Slot</em>'.
	 * @see urncore.Component#isSlot()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Slot();

				/**
	 * Returns the meta object for the attribute '{@link urncore.Component#isContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Context</em>'.
	 * @see urncore.Component#isContext()
	 * @see #getComponent()
	 * @generated
	 */
	EAttribute getComponent_Context();

				/**
	 * Returns the meta object for the reference '{@link urncore.Component#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see urncore.Component#getType()
	 * @see #getComponent()
	 * @generated
	 */
    EReference getComponent_Type();

    /**
	 * Returns the meta object for the container reference '{@link urncore.Component#getUrndefinition <em>Urndefinition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Urndefinition</em>'.
	 * @see urncore.Component#getUrndefinition()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Urndefinition();

				/**
	 * Returns the meta object for the reference list '{@link urncore.Component#getIncludedComponent <em>Included Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Included Component</em>'.
	 * @see urncore.Component#getIncludedComponent()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_IncludedComponent();

				/**
	 * Returns the meta object for the reference '{@link urncore.Component#getIncludingComponent <em>Including Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Including Component</em>'.
	 * @see urncore.Component#getIncludingComponent()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_IncludingComponent();

				/**
	 * Returns the meta object for the reference '{@link urncore.Component#getResource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Resource</em>'.
	 * @see urncore.Component#getResource()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Resource();

				/**
	 * Returns the meta object for the reference '{@link urncore.Component#getHost <em>Host</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Host</em>'.
	 * @see urncore.Component#getHost()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Host();

				/**
	 * Returns the meta object for class '{@link urncore.ComponentType <em>Component Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Type</em>'.
	 * @see urncore.ComponentType
	 * @generated
	 */
    EClass getComponentType();

    /**
	 * Returns the meta object for the reference list '{@link urncore.ComponentType#getInstances <em>Instances</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Instances</em>'.
	 * @see urncore.ComponentType#getInstances()
	 * @see #getComponentType()
	 * @generated
	 */
    EReference getComponentType_Instances();

    /**
	 * Returns the meta object for the container reference '{@link urncore.ComponentType#getUrndefinition <em>Urndefinition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Urndefinition</em>'.
	 * @see urncore.ComponentType#getUrndefinition()
	 * @see #getComponentType()
	 * @generated
	 */
	EReference getComponentType_Urndefinition();

				/**
	 * Returns the meta object for class '{@link urncore.UCMmodelElement <em>UC Mmodel Element</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>UC Mmodel Element</em>'.
	 * @see urncore.UCMmodelElement
	 * @generated
	 */
    EClass getUCMmodelElement();

    /**
	 * Returns the meta object for class '{@link urncore.GRLmodelElement <em>GR Lmodel Element</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>GR Lmodel Element</em>'.
	 * @see urncore.GRLmodelElement
	 * @generated
	 */
    EClass getGRLmodelElement();

    /**
	 * Returns the meta object for class '{@link urncore.NodeLabel <em>Node Label</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node Label</em>'.
	 * @see urncore.NodeLabel
	 * @generated
	 */
    EClass getNodeLabel();

    /**
	 * Returns the meta object for the container reference '{@link urncore.NodeLabel#getNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Node</em>'.
	 * @see urncore.NodeLabel#getNode()
	 * @see #getNodeLabel()
	 * @generated
	 */
    EReference getNodeLabel_Node();

    /**
	 * Returns the meta object for class '{@link urncore.Label <em>Label</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Label</em>'.
	 * @see urncore.Label
	 * @generated
	 */
    EClass getLabel();

    /**
	 * Returns the meta object for the attribute '{@link urncore.Label#getDeltaX <em>Delta X</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Delta X</em>'.
	 * @see urncore.Label#getDeltaX()
	 * @see #getLabel()
	 * @generated
	 */
    EAttribute getLabel_DeltaX();

    /**
	 * Returns the meta object for the attribute '{@link urncore.Label#getDeltaY <em>Delta Y</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Delta Y</em>'.
	 * @see urncore.Label#getDeltaY()
	 * @see #getLabel()
	 * @generated
	 */
    EAttribute getLabel_DeltaY();

    /**
	 * Returns the meta object for class '{@link urncore.ComponentLabel <em>Component Label</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component Label</em>'.
	 * @see urncore.ComponentLabel
	 * @generated
	 */
    EClass getComponentLabel();

    /**
	 * Returns the meta object for the container reference '{@link urncore.ComponentLabel#getContRef <em>Cont Ref</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Cont Ref</em>'.
	 * @see urncore.ComponentLabel#getContRef()
	 * @see #getComponentLabel()
	 * @generated
	 */
    EReference getComponentLabel_ContRef();

    /**
	 * Returns the meta object for class '{@link urncore.Condition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Condition</em>'.
	 * @see urncore.Condition
	 * @generated
	 */
    EClass getCondition();

    /**
	 * Returns the meta object for the attribute '{@link urncore.Condition#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see urncore.Condition#getLabel()
	 * @see #getCondition()
	 * @generated
	 */
    EAttribute getCondition_Label();

    /**
	 * Returns the meta object for the attribute '{@link urncore.Condition#getExpression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expression</em>'.
	 * @see urncore.Condition#getExpression()
	 * @see #getCondition()
	 * @generated
	 */
    EAttribute getCondition_Expression();

    /**
	 * Returns the meta object for the attribute '{@link urncore.Condition#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see urncore.Condition#getDescription()
	 * @see #getCondition()
	 * @generated
	 */
    EAttribute getCondition_Description();

    /**
	 * Returns the meta object for the container reference '{@link urncore.Condition#getStartPoint <em>Start Point</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Start Point</em>'.
	 * @see urncore.Condition#getStartPoint()
	 * @see #getCondition()
	 * @generated
	 */
    EReference getCondition_StartPoint();

    /**
	 * Returns the meta object for the container reference '{@link urncore.Condition#getEndPoint <em>End Point</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>End Point</em>'.
	 * @see urncore.Condition#getEndPoint()
	 * @see #getCondition()
	 * @generated
	 */
    EReference getCondition_EndPoint();

    /**
	 * Returns the meta object for the container reference '{@link urncore.Condition#getPluginBinding <em>Plugin Binding</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Plugin Binding</em>'.
	 * @see urncore.Condition#getPluginBinding()
	 * @see #getCondition()
	 * @generated
	 */
    EReference getCondition_PluginBinding();

    /**
	 * Returns the meta object for the container reference '{@link urncore.Condition#getNodeConnection <em>Node Connection</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Node Connection</em>'.
	 * @see urncore.Condition#getNodeConnection()
	 * @see #getCondition()
	 * @generated
	 */
    EReference getCondition_NodeConnection();

    /**
	 * Returns the meta object for the container reference '{@link urncore.Condition#getConcern <em>Concern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Concern</em>'.
	 * @see urncore.Condition#getConcern()
	 * @see #getCondition()
	 * @generated
	 */
	EReference getCondition_Concern();

				/**
	 * Returns the meta object for the container reference '{@link urncore.Condition#getScenarioDefPre <em>Scenario Def Pre</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Scenario Def Pre</em>'.
	 * @see urncore.Condition#getScenarioDefPre()
	 * @see #getCondition()
	 * @generated
	 */
	EReference getCondition_ScenarioDefPre();

    /**
	 * Returns the meta object for the container reference '{@link urncore.Condition#getScenarioDefPost <em>Scenario Def Post</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Scenario Def Post</em>'.
	 * @see urncore.Condition#getScenarioDefPost()
	 * @see #getCondition()
	 * @generated
	 */
	EReference getCondition_ScenarioDefPost();

    /**
	 * Returns the meta object for class '{@link urncore.IURNDiagram <em>IURN Diagram</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IURN Diagram</em>'.
	 * @see urncore.IURNDiagram
	 * @generated
	 */
    EClass getIURNDiagram();

    /**
	 * Returns the meta object for the container reference '{@link urncore.IURNDiagram#getUrndefinition <em>Urndefinition</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Urndefinition</em>'.
	 * @see urncore.IURNDiagram#getUrndefinition()
	 * @see #getIURNDiagram()
	 * @generated
	 */
    EReference getIURNDiagram_Urndefinition();

    /**
	 * Returns the meta object for the containment reference list '{@link urncore.IURNDiagram#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see urncore.IURNDiagram#getNodes()
	 * @see #getIURNDiagram()
	 * @generated
	 */
    EReference getIURNDiagram_Nodes();

    /**
	 * Returns the meta object for the containment reference list '{@link urncore.IURNDiagram#getContRefs <em>Cont Refs</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cont Refs</em>'.
	 * @see urncore.IURNDiagram#getContRefs()
	 * @see #getIURNDiagram()
	 * @generated
	 */
    EReference getIURNDiagram_ContRefs();

    /**
	 * Returns the meta object for the containment reference list '{@link urncore.IURNDiagram#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Connections</em>'.
	 * @see urncore.IURNDiagram#getConnections()
	 * @see #getIURNDiagram()
	 * @generated
	 */
    EReference getIURNDiagram_Connections();

    /**
	 * Returns the meta object for the reference '{@link urncore.IURNDiagram#getConcern <em>Concern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Concern</em>'.
	 * @see urncore.IURNDiagram#getConcern()
	 * @see #getIURNDiagram()
	 * @generated
	 */
	EReference getIURNDiagram_Concern();

    /**
	 * Returns the meta object for the containment reference list '{@link urncore.IURNDiagram#getComments <em>Comments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Comments</em>'.
	 * @see urncore.IURNDiagram#getComments()
	 * @see #getIURNDiagram()
	 * @generated
	 */
	EReference getIURNDiagram_Comments();

				/**
	 * Returns the meta object for class '{@link urncore.URNmodelElement <em>UR Nmodel Element</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>UR Nmodel Element</em>'.
	 * @see urncore.URNmodelElement
	 * @generated
	 */
    EClass getURNmodelElement();

    /**
	 * Returns the meta object for the reference list '{@link urncore.URNmodelElement#getFromLinks <em>From Links</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>From Links</em>'.
	 * @see urncore.URNmodelElement#getFromLinks()
	 * @see #getURNmodelElement()
	 * @generated
	 */
    EReference getURNmodelElement_FromLinks();

    /**
	 * Returns the meta object for the reference list '{@link urncore.URNmodelElement#getToLinks <em>To Links</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>To Links</em>'.
	 * @see urncore.URNmodelElement#getToLinks()
	 * @see #getURNmodelElement()
	 * @generated
	 */
    EReference getURNmodelElement_ToLinks();

    /**
	 * Returns the meta object for the attribute '{@link urncore.URNmodelElement#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see urncore.URNmodelElement#getId()
	 * @see #getURNmodelElement()
	 * @generated
	 */
    EAttribute getURNmodelElement_Id();

    /**
	 * Returns the meta object for the attribute '{@link urncore.URNmodelElement#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see urncore.URNmodelElement#getDescription()
	 * @see #getURNmodelElement()
	 * @generated
	 */
    EAttribute getURNmodelElement_Description();

    /**
	 * Returns the meta object for the containment reference list '{@link urncore.URNmodelElement#getMetadata <em>Metadata</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Metadata</em>'.
	 * @see urncore.URNmodelElement#getMetadata()
	 * @see #getURNmodelElement()
	 * @generated
	 */
	EReference getURNmodelElement_Metadata();

    /**
	 * Returns the meta object for the reference '{@link urncore.URNmodelElement#getInconcern <em>Inconcern</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Inconcern</em>'.
	 * @see urncore.URNmodelElement#getInconcern()
	 * @see #getURNmodelElement()
	 * @generated
	 */
    EReference getURNmodelElement_Inconcern();

    /**
	 * Returns the meta object for class '{@link urncore.IURNNode <em>IURN Node</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IURN Node</em>'.
	 * @see urncore.IURNNode
	 * @generated
	 */
    EClass getIURNNode();

    /**
	 * Returns the meta object for the attribute '{@link urncore.IURNNode#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see urncore.IURNNode#getX()
	 * @see #getIURNNode()
	 * @generated
	 */
    EAttribute getIURNNode_X();

    /**
	 * Returns the meta object for the attribute '{@link urncore.IURNNode#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see urncore.IURNNode#getY()
	 * @see #getIURNNode()
	 * @generated
	 */
    EAttribute getIURNNode_Y();

    /**
	 * Returns the meta object for the container reference '{@link urncore.IURNNode#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Diagram</em>'.
	 * @see urncore.IURNNode#getDiagram()
	 * @see #getIURNNode()
	 * @generated
	 */
    EReference getIURNNode_Diagram();

    /**
	 * Returns the meta object for the reference '{@link urncore.IURNNode#getContRef <em>Cont Ref</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Cont Ref</em>'.
	 * @see urncore.IURNNode#getContRef()
	 * @see #getIURNNode()
	 * @generated
	 */
    EReference getIURNNode_ContRef();

    /**
	 * Returns the meta object for the reference list '{@link urncore.IURNNode#getSucc <em>Succ</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Succ</em>'.
	 * @see urncore.IURNNode#getSucc()
	 * @see #getIURNNode()
	 * @generated
	 */
    EReference getIURNNode_Succ();

    /**
	 * Returns the meta object for the reference list '{@link urncore.IURNNode#getPred <em>Pred</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Pred</em>'.
	 * @see urncore.IURNNode#getPred()
	 * @see #getIURNNode()
	 * @generated
	 */
    EReference getIURNNode_Pred();

    /**
	 * Returns the meta object for the containment reference '{@link urncore.IURNNode#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Label</em>'.
	 * @see urncore.IURNNode#getLabel()
	 * @see #getIURNNode()
	 * @generated
	 */
    EReference getIURNNode_Label();

    /**
	 * Returns the meta object for class '{@link urncore.IURNContainerRef <em>IURN Container Ref</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IURN Container Ref</em>'.
	 * @see urncore.IURNContainerRef
	 * @generated
	 */
    EClass getIURNContainerRef();

    /**
	 * Returns the meta object for the attribute '{@link urncore.IURNContainerRef#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see urncore.IURNContainerRef#getX()
	 * @see #getIURNContainerRef()
	 * @generated
	 */
    EAttribute getIURNContainerRef_X();

    /**
	 * Returns the meta object for the attribute '{@link urncore.IURNContainerRef#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see urncore.IURNContainerRef#getY()
	 * @see #getIURNContainerRef()
	 * @generated
	 */
    EAttribute getIURNContainerRef_Y();

    /**
	 * Returns the meta object for the attribute '{@link urncore.IURNContainerRef#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see urncore.IURNContainerRef#getWidth()
	 * @see #getIURNContainerRef()
	 * @generated
	 */
    EAttribute getIURNContainerRef_Width();

    /**
	 * Returns the meta object for the attribute '{@link urncore.IURNContainerRef#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see urncore.IURNContainerRef#getHeight()
	 * @see #getIURNContainerRef()
	 * @generated
	 */
    EAttribute getIURNContainerRef_Height();

    /**
	 * Returns the meta object for the attribute '{@link urncore.IURNContainerRef#isFixed <em>Fixed</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fixed</em>'.
	 * @see urncore.IURNContainerRef#isFixed()
	 * @see #getIURNContainerRef()
	 * @generated
	 */
    EAttribute getIURNContainerRef_Fixed();

    /**
	 * Returns the meta object for the container reference '{@link urncore.IURNContainerRef#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Diagram</em>'.
	 * @see urncore.IURNContainerRef#getDiagram()
	 * @see #getIURNContainerRef()
	 * @generated
	 */
    EReference getIURNContainerRef_Diagram();

    /**
	 * Returns the meta object for the reference '{@link urncore.IURNContainerRef#getContDef <em>Cont Def</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Cont Def</em>'.
	 * @see urncore.IURNContainerRef#getContDef()
	 * @see #getIURNContainerRef()
	 * @generated
	 */
    EReference getIURNContainerRef_ContDef();

    /**
	 * Returns the meta object for the reference list '{@link urncore.IURNContainerRef#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Nodes</em>'.
	 * @see urncore.IURNContainerRef#getNodes()
	 * @see #getIURNContainerRef()
	 * @generated
	 */
    EReference getIURNContainerRef_Nodes();

    /**
	 * Returns the meta object for the containment reference '{@link urncore.IURNContainerRef#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Label</em>'.
	 * @see urncore.IURNContainerRef#getLabel()
	 * @see #getIURNContainerRef()
	 * @generated
	 */
    EReference getIURNContainerRef_Label();

    /**
	 * Returns the meta object for the reference '{@link urncore.IURNContainerRef#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Parent</em>'.
	 * @see urncore.IURNContainerRef#getParent()
	 * @see #getIURNContainerRef()
	 * @generated
	 */
    EReference getIURNContainerRef_Parent();

    /**
	 * Returns the meta object for the reference list '{@link urncore.IURNContainerRef#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Children</em>'.
	 * @see urncore.IURNContainerRef#getChildren()
	 * @see #getIURNContainerRef()
	 * @generated
	 */
    EReference getIURNContainerRef_Children();

    /**
	 * Returns the meta object for class '{@link urncore.IURNContainer <em>IURN Container</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IURN Container</em>'.
	 * @see urncore.IURNContainer
	 * @generated
	 */
    EClass getIURNContainer();

    /**
	 * Returns the meta object for the attribute '{@link urncore.IURNContainer#getLineColor <em>Line Color</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Color</em>'.
	 * @see urncore.IURNContainer#getLineColor()
	 * @see #getIURNContainer()
	 * @generated
	 */
    EAttribute getIURNContainer_LineColor();

    /**
	 * Returns the meta object for the attribute '{@link urncore.IURNContainer#getFillColor <em>Fill Color</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fill Color</em>'.
	 * @see urncore.IURNContainer#getFillColor()
	 * @see #getIURNContainer()
	 * @generated
	 */
    EAttribute getIURNContainer_FillColor();

    /**
	 * Returns the meta object for the attribute '{@link urncore.IURNContainer#isFilled <em>Filled</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Filled</em>'.
	 * @see urncore.IURNContainer#isFilled()
	 * @see #getIURNContainer()
	 * @generated
	 */
    EAttribute getIURNContainer_Filled();

    /**
	 * Returns the meta object for the reference list '{@link urncore.IURNContainer#getContRefs <em>Cont Refs</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Cont Refs</em>'.
	 * @see urncore.IURNContainer#getContRefs()
	 * @see #getIURNContainer()
	 * @generated
	 */
    EReference getIURNContainer_ContRefs();

    /**
	 * Returns the meta object for class '{@link urncore.IURNConnection <em>IURN Connection</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IURN Connection</em>'.
	 * @see urncore.IURNConnection
	 * @generated
	 */
    EClass getIURNConnection();

    /**
	 * Returns the meta object for the reference '{@link urncore.IURNConnection#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see urncore.IURNConnection#getSource()
	 * @see #getIURNConnection()
	 * @generated
	 */
    EReference getIURNConnection_Source();

    /**
	 * Returns the meta object for the reference '{@link urncore.IURNConnection#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see urncore.IURNConnection#getTarget()
	 * @see #getIURNConnection()
	 * @generated
	 */
    EReference getIURNConnection_Target();

    /**
	 * Returns the meta object for the container reference '{@link urncore.IURNConnection#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Diagram</em>'.
	 * @see urncore.IURNConnection#getDiagram()
	 * @see #getIURNConnection()
	 * @generated
	 */
    EReference getIURNConnection_Diagram();

    /**
	 * Returns the meta object for the containment reference '{@link urncore.IURNConnection#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Label</em>'.
	 * @see urncore.IURNConnection#getLabel()
	 * @see #getIURNConnection()
	 * @generated
	 */
	EReference getIURNConnection_Label();

				/**
	 * Returns the meta object for class '{@link urncore.Metadata <em>Metadata</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Metadata</em>'.
	 * @see urncore.Metadata
	 * @generated
	 */
	EClass getMetadata();

    /**
	 * Returns the meta object for the attribute '{@link urncore.Metadata#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see urncore.Metadata#getName()
	 * @see #getMetadata()
	 * @generated
	 */
	EAttribute getMetadata_Name();

    /**
	 * Returns the meta object for the attribute '{@link urncore.Metadata#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see urncore.Metadata#getValue()
	 * @see #getMetadata()
	 * @generated
	 */
	EAttribute getMetadata_Value();

    /**
	 * Returns the meta object for class '{@link urncore.Concern <em>Concern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Concern</em>'.
	 * @see urncore.Concern
	 * @generated
	 */
	EClass getConcern();

    /**
	 * Returns the meta object for the container reference '{@link urncore.Concern#getUrndefinition <em>Urndefinition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Urndefinition</em>'.
	 * @see urncore.Concern#getUrndefinition()
	 * @see #getConcern()
	 * @generated
	 */
	EReference getConcern_Urndefinition();

    /**
	 * Returns the meta object for the reference list '{@link urncore.Concern#getSpecDiagrams <em>Spec Diagrams</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Spec Diagrams</em>'.
	 * @see urncore.Concern#getSpecDiagrams()
	 * @see #getConcern()
	 * @generated
	 */
	EReference getConcern_SpecDiagrams();

    /**
	 * Returns the meta object for the reference list '{@link urncore.Concern#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Elements</em>'.
	 * @see urncore.Concern#getElements()
	 * @see #getConcern()
	 * @generated
	 */
    EReference getConcern_Elements();

    /**
	 * Returns the meta object for the reference '{@link urncore.Concern#getCoreConcern <em>Core Concern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Core Concern</em>'.
	 * @see urncore.Concern#getCoreConcern()
	 * @see #getConcern()
	 * @generated
	 */
	EReference getConcern_CoreConcern();

				/**
	 * Returns the meta object for the containment reference '{@link urncore.Concern#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see urncore.Concern#getCondition()
	 * @see #getConcern()
	 * @generated
	 */
	EReference getConcern_Condition();

				/**
	 * Returns the meta object for class '{@link urncore.ConnectionLabel <em>Connection Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection Label</em>'.
	 * @see urncore.ConnectionLabel
	 * @generated
	 */
	EClass getConnectionLabel();

				/**
	 * Returns the meta object for the container reference '{@link urncore.ConnectionLabel#getConnection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Connection</em>'.
	 * @see urncore.ConnectionLabel#getConnection()
	 * @see #getConnectionLabel()
	 * @generated
	 */
	EReference getConnectionLabel_Connection();

				/**
	 * Returns the meta object for class '{@link urncore.Comment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comment</em>'.
	 * @see urncore.Comment
	 * @generated
	 */
	EClass getComment();

				/**
	 * Returns the meta object for the attribute '{@link urncore.Comment#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see urncore.Comment#getDescription()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_Description();

				/**
	 * Returns the meta object for the attribute '{@link urncore.Comment#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see urncore.Comment#getX()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_X();

				/**
	 * Returns the meta object for the attribute '{@link urncore.Comment#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see urncore.Comment#getY()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_Y();

				/**
	 * Returns the meta object for the attribute '{@link urncore.Comment#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see urncore.Comment#getWidth()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_Width();

				/**
	 * Returns the meta object for the attribute '{@link urncore.Comment#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see urncore.Comment#getHeight()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_Height();

				/**
	 * Returns the meta object for the attribute '{@link urncore.Comment#getFillColor <em>Fill Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fill Color</em>'.
	 * @see urncore.Comment#getFillColor()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_FillColor();

				/**
	 * Returns the meta object for the container reference '{@link urncore.Comment#getDiagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Diagram</em>'.
	 * @see urncore.Comment#getDiagram()
	 * @see #getComment()
	 * @generated
	 */
	EReference getComment_Diagram();

				/**
	 * Returns the meta object for enum '{@link urncore.ComponentKind <em>Component Kind</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Component Kind</em>'.
	 * @see urncore.ComponentKind
	 * @generated
	 */
    EEnum getComponentKind();

    /**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
    UrncoreFactory getUrncoreFactory();

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
		 * The meta object literal for the '{@link urncore.impl.URNdefinitionImpl <em>UR Ndefinition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urncore.impl.URNdefinitionImpl
		 * @see urncore.impl.UrncorePackageImpl#getURNdefinition()
		 * @generated
		 */
		EClass UR_NDEFINITION = eINSTANCE.getURNdefinition();

        /**
		 * The meta object literal for the '<em><b>Urnspec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UR_NDEFINITION__URNSPEC = eINSTANCE.getURNdefinition_Urnspec();

        /**
		 * The meta object literal for the '<em><b>Responsibilities</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UR_NDEFINITION__RESPONSIBILITIES = eINSTANCE.getURNdefinition_Responsibilities();

        /**
		 * The meta object literal for the '<em><b>Components</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UR_NDEFINITION__COMPONENTS = eINSTANCE.getURNdefinition_Components();

        /**
		 * The meta object literal for the '<em><b>Component Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UR_NDEFINITION__COMPONENT_TYPES = eINSTANCE.getURNdefinition_ComponentTypes();

								/**
		 * The meta object literal for the '<em><b>Spec Diagrams</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UR_NDEFINITION__SPEC_DIAGRAMS = eINSTANCE.getURNdefinition_SpecDiagrams();

        /**
		 * The meta object literal for the '<em><b>Concerns</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UR_NDEFINITION__CONCERNS = eINSTANCE.getURNdefinition_Concerns();

        /**
		 * The meta object literal for the '{@link urncore.impl.ResponsibilityImpl <em>Responsibility</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urncore.impl.ResponsibilityImpl
		 * @see urncore.impl.UrncorePackageImpl#getResponsibility()
		 * @generated
		 */
		EClass RESPONSIBILITY = eINSTANCE.getResponsibility();

        /**
		 * The meta object literal for the '<em><b>Empty</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESPONSIBILITY__EMPTY = eINSTANCE.getResponsibility_Empty();

        /**
		 * The meta object literal for the '<em><b>Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESPONSIBILITY__EXPRESSION = eINSTANCE.getResponsibility_Expression();

        /**
		 * The meta object literal for the '<em><b>Context</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute RESPONSIBILITY__CONTEXT = eINSTANCE.getResponsibility_Context();

        /**
		 * The meta object literal for the '<em><b>Urndefinition</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESPONSIBILITY__URNDEFINITION = eINSTANCE.getResponsibility_Urndefinition();

        /**
		 * The meta object literal for the '<em><b>Demands</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESPONSIBILITY__DEMANDS = eINSTANCE.getResponsibility_Demands();

        /**
		 * The meta object literal for the '<em><b>Resp Refs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESPONSIBILITY__RESP_REFS = eINSTANCE.getResponsibility_RespRefs();

        /**
		 * The meta object literal for the '<em><b>Parent Bindings</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference RESPONSIBILITY__PARENT_BINDINGS = eINSTANCE.getResponsibility_ParentBindings();

        /**
		 * The meta object literal for the '{@link urncore.impl.ComponentImpl <em>Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urncore.impl.ComponentImpl
		 * @see urncore.impl.UrncorePackageImpl#getComponent()
		 * @generated
		 */
		EClass COMPONENT = eINSTANCE.getComponent();

        /**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__KIND = eINSTANCE.getComponent_Kind();

								/**
		 * The meta object literal for the '<em><b>Protected</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__PROTECTED = eINSTANCE.getComponent_Protected();

								/**
		 * The meta object literal for the '<em><b>Slot</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__SLOT = eINSTANCE.getComponent_Slot();

								/**
		 * The meta object literal for the '<em><b>Context</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPONENT__CONTEXT = eINSTANCE.getComponent_Context();

								/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__TYPE = eINSTANCE.getComponent_Type();

        /**
		 * The meta object literal for the '<em><b>Urndefinition</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__URNDEFINITION = eINSTANCE.getComponent_Urndefinition();

								/**
		 * The meta object literal for the '<em><b>Included Component</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__INCLUDED_COMPONENT = eINSTANCE.getComponent_IncludedComponent();

								/**
		 * The meta object literal for the '<em><b>Including Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__INCLUDING_COMPONENT = eINSTANCE.getComponent_IncludingComponent();

								/**
		 * The meta object literal for the '<em><b>Resource</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__RESOURCE = eINSTANCE.getComponent_Resource();

								/**
		 * The meta object literal for the '<em><b>Host</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__HOST = eINSTANCE.getComponent_Host();

								/**
		 * The meta object literal for the '{@link urncore.impl.ComponentTypeImpl <em>Component Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urncore.impl.ComponentTypeImpl
		 * @see urncore.impl.UrncorePackageImpl#getComponentType()
		 * @generated
		 */
		EClass COMPONENT_TYPE = eINSTANCE.getComponentType();

        /**
		 * The meta object literal for the '<em><b>Instances</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_TYPE__INSTANCES = eINSTANCE.getComponentType_Instances();

        /**
		 * The meta object literal for the '<em><b>Urndefinition</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_TYPE__URNDEFINITION = eINSTANCE.getComponentType_Urndefinition();

								/**
		 * The meta object literal for the '{@link urncore.impl.UCMmodelElementImpl <em>UC Mmodel Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urncore.impl.UCMmodelElementImpl
		 * @see urncore.impl.UrncorePackageImpl#getUCMmodelElement()
		 * @generated
		 */
		EClass UC_MMODEL_ELEMENT = eINSTANCE.getUCMmodelElement();

        /**
		 * The meta object literal for the '{@link urncore.impl.GRLmodelElementImpl <em>GR Lmodel Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urncore.impl.GRLmodelElementImpl
		 * @see urncore.impl.UrncorePackageImpl#getGRLmodelElement()
		 * @generated
		 */
		EClass GR_LMODEL_ELEMENT = eINSTANCE.getGRLmodelElement();

        /**
		 * The meta object literal for the '{@link urncore.impl.NodeLabelImpl <em>Node Label</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urncore.impl.NodeLabelImpl
		 * @see urncore.impl.UrncorePackageImpl#getNodeLabel()
		 * @generated
		 */
		EClass NODE_LABEL = eINSTANCE.getNodeLabel();

        /**
		 * The meta object literal for the '<em><b>Node</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_LABEL__NODE = eINSTANCE.getNodeLabel_Node();

        /**
		 * The meta object literal for the '{@link urncore.impl.LabelImpl <em>Label</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urncore.impl.LabelImpl
		 * @see urncore.impl.UrncorePackageImpl#getLabel()
		 * @generated
		 */
		EClass LABEL = eINSTANCE.getLabel();

        /**
		 * The meta object literal for the '<em><b>Delta X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL__DELTA_X = eINSTANCE.getLabel_DeltaX();

        /**
		 * The meta object literal for the '<em><b>Delta Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL__DELTA_Y = eINSTANCE.getLabel_DeltaY();

        /**
		 * The meta object literal for the '{@link urncore.impl.ComponentLabelImpl <em>Component Label</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urncore.impl.ComponentLabelImpl
		 * @see urncore.impl.UrncorePackageImpl#getComponentLabel()
		 * @generated
		 */
		EClass COMPONENT_LABEL = eINSTANCE.getComponentLabel();

        /**
		 * The meta object literal for the '<em><b>Cont Ref</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT_LABEL__CONT_REF = eINSTANCE.getComponentLabel_ContRef();

        /**
		 * The meta object literal for the '{@link urncore.impl.ConditionImpl <em>Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urncore.impl.ConditionImpl
		 * @see urncore.impl.UrncorePackageImpl#getCondition()
		 * @generated
		 */
		EClass CONDITION = eINSTANCE.getCondition();

        /**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDITION__LABEL = eINSTANCE.getCondition_Label();

        /**
		 * The meta object literal for the '<em><b>Expression</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDITION__EXPRESSION = eINSTANCE.getCondition_Expression();

        /**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDITION__DESCRIPTION = eINSTANCE.getCondition_Description();

        /**
		 * The meta object literal for the '<em><b>Start Point</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITION__START_POINT = eINSTANCE.getCondition_StartPoint();

        /**
		 * The meta object literal for the '<em><b>End Point</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITION__END_POINT = eINSTANCE.getCondition_EndPoint();

        /**
		 * The meta object literal for the '<em><b>Plugin Binding</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITION__PLUGIN_BINDING = eINSTANCE.getCondition_PluginBinding();

        /**
		 * The meta object literal for the '<em><b>Node Connection</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITION__NODE_CONNECTION = eINSTANCE.getCondition_NodeConnection();

        /**
		 * The meta object literal for the '<em><b>Concern</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITION__CONCERN = eINSTANCE.getCondition_Concern();

								/**
		 * The meta object literal for the '<em><b>Scenario Def Pre</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITION__SCENARIO_DEF_PRE = eINSTANCE.getCondition_ScenarioDefPre();

        /**
		 * The meta object literal for the '<em><b>Scenario Def Post</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITION__SCENARIO_DEF_POST = eINSTANCE.getCondition_ScenarioDefPost();

        /**
		 * The meta object literal for the '{@link urncore.IURNDiagram <em>IURN Diagram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urncore.IURNDiagram
		 * @see urncore.impl.UrncorePackageImpl#getIURNDiagram()
		 * @generated
		 */
		EClass IURN_DIAGRAM = eINSTANCE.getIURNDiagram();

        /**
		 * The meta object literal for the '<em><b>Urndefinition</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IURN_DIAGRAM__URNDEFINITION = eINSTANCE.getIURNDiagram_Urndefinition();

        /**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IURN_DIAGRAM__NODES = eINSTANCE.getIURNDiagram_Nodes();

        /**
		 * The meta object literal for the '<em><b>Cont Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IURN_DIAGRAM__CONT_REFS = eINSTANCE.getIURNDiagram_ContRefs();

        /**
		 * The meta object literal for the '<em><b>Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IURN_DIAGRAM__CONNECTIONS = eINSTANCE.getIURNDiagram_Connections();

        /**
		 * The meta object literal for the '<em><b>Concern</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IURN_DIAGRAM__CONCERN = eINSTANCE.getIURNDiagram_Concern();

        /**
		 * The meta object literal for the '<em><b>Comments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IURN_DIAGRAM__COMMENTS = eINSTANCE.getIURNDiagram_Comments();

								/**
		 * The meta object literal for the '{@link urncore.impl.URNmodelElementImpl <em>UR Nmodel Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urncore.impl.URNmodelElementImpl
		 * @see urncore.impl.UrncorePackageImpl#getURNmodelElement()
		 * @generated
		 */
		EClass UR_NMODEL_ELEMENT = eINSTANCE.getURNmodelElement();

        /**
		 * The meta object literal for the '<em><b>From Links</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UR_NMODEL_ELEMENT__FROM_LINKS = eINSTANCE.getURNmodelElement_FromLinks();

        /**
		 * The meta object literal for the '<em><b>To Links</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UR_NMODEL_ELEMENT__TO_LINKS = eINSTANCE.getURNmodelElement_ToLinks();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UR_NMODEL_ELEMENT__ID = eINSTANCE.getURNmodelElement_Id();

        /**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UR_NMODEL_ELEMENT__DESCRIPTION = eINSTANCE.getURNmodelElement_Description();

        /**
		 * The meta object literal for the '<em><b>Metadata</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UR_NMODEL_ELEMENT__METADATA = eINSTANCE.getURNmodelElement_Metadata();

        /**
		 * The meta object literal for the '<em><b>Inconcern</b></em>' reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference UR_NMODEL_ELEMENT__INCONCERN = eINSTANCE.getURNmodelElement_Inconcern();

        /**
		 * The meta object literal for the '{@link urncore.IURNNode <em>IURN Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urncore.IURNNode
		 * @see urncore.impl.UrncorePackageImpl#getIURNNode()
		 * @generated
		 */
		EClass IURN_NODE = eINSTANCE.getIURNNode();

        /**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IURN_NODE__X = eINSTANCE.getIURNNode_X();

        /**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IURN_NODE__Y = eINSTANCE.getIURNNode_Y();

        /**
		 * The meta object literal for the '<em><b>Diagram</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IURN_NODE__DIAGRAM = eINSTANCE.getIURNNode_Diagram();

        /**
		 * The meta object literal for the '<em><b>Cont Ref</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IURN_NODE__CONT_REF = eINSTANCE.getIURNNode_ContRef();

        /**
		 * The meta object literal for the '<em><b>Succ</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IURN_NODE__SUCC = eINSTANCE.getIURNNode_Succ();

        /**
		 * The meta object literal for the '<em><b>Pred</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IURN_NODE__PRED = eINSTANCE.getIURNNode_Pred();

        /**
		 * The meta object literal for the '<em><b>Label</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IURN_NODE__LABEL = eINSTANCE.getIURNNode_Label();

        /**
		 * The meta object literal for the '{@link urncore.IURNContainerRef <em>IURN Container Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urncore.IURNContainerRef
		 * @see urncore.impl.UrncorePackageImpl#getIURNContainerRef()
		 * @generated
		 */
		EClass IURN_CONTAINER_REF = eINSTANCE.getIURNContainerRef();

        /**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IURN_CONTAINER_REF__X = eINSTANCE.getIURNContainerRef_X();

        /**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IURN_CONTAINER_REF__Y = eINSTANCE.getIURNContainerRef_Y();

        /**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IURN_CONTAINER_REF__WIDTH = eINSTANCE.getIURNContainerRef_Width();

        /**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IURN_CONTAINER_REF__HEIGHT = eINSTANCE.getIURNContainerRef_Height();

        /**
		 * The meta object literal for the '<em><b>Fixed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IURN_CONTAINER_REF__FIXED = eINSTANCE.getIURNContainerRef_Fixed();

        /**
		 * The meta object literal for the '<em><b>Diagram</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IURN_CONTAINER_REF__DIAGRAM = eINSTANCE.getIURNContainerRef_Diagram();

        /**
		 * The meta object literal for the '<em><b>Cont Def</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IURN_CONTAINER_REF__CONT_DEF = eINSTANCE.getIURNContainerRef_ContDef();

        /**
		 * The meta object literal for the '<em><b>Nodes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IURN_CONTAINER_REF__NODES = eINSTANCE.getIURNContainerRef_Nodes();

        /**
		 * The meta object literal for the '<em><b>Label</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IURN_CONTAINER_REF__LABEL = eINSTANCE.getIURNContainerRef_Label();

        /**
		 * The meta object literal for the '<em><b>Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IURN_CONTAINER_REF__PARENT = eINSTANCE.getIURNContainerRef_Parent();

        /**
		 * The meta object literal for the '<em><b>Children</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IURN_CONTAINER_REF__CHILDREN = eINSTANCE.getIURNContainerRef_Children();

        /**
		 * The meta object literal for the '{@link urncore.IURNContainer <em>IURN Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urncore.IURNContainer
		 * @see urncore.impl.UrncorePackageImpl#getIURNContainer()
		 * @generated
		 */
		EClass IURN_CONTAINER = eINSTANCE.getIURNContainer();

        /**
		 * The meta object literal for the '<em><b>Line Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IURN_CONTAINER__LINE_COLOR = eINSTANCE.getIURNContainer_LineColor();

        /**
		 * The meta object literal for the '<em><b>Fill Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IURN_CONTAINER__FILL_COLOR = eINSTANCE.getIURNContainer_FillColor();

        /**
		 * The meta object literal for the '<em><b>Filled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IURN_CONTAINER__FILLED = eINSTANCE.getIURNContainer_Filled();

        /**
		 * The meta object literal for the '<em><b>Cont Refs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IURN_CONTAINER__CONT_REFS = eINSTANCE.getIURNContainer_ContRefs();

        /**
		 * The meta object literal for the '{@link urncore.IURNConnection <em>IURN Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urncore.IURNConnection
		 * @see urncore.impl.UrncorePackageImpl#getIURNConnection()
		 * @generated
		 */
		EClass IURN_CONNECTION = eINSTANCE.getIURNConnection();

        /**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IURN_CONNECTION__SOURCE = eINSTANCE.getIURNConnection_Source();

        /**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IURN_CONNECTION__TARGET = eINSTANCE.getIURNConnection_Target();

        /**
		 * The meta object literal for the '<em><b>Diagram</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IURN_CONNECTION__DIAGRAM = eINSTANCE.getIURNConnection_Diagram();

        /**
		 * The meta object literal for the '<em><b>Label</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IURN_CONNECTION__LABEL = eINSTANCE.getIURNConnection_Label();

								/**
		 * The meta object literal for the '{@link urncore.impl.MetadataImpl <em>Metadata</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urncore.impl.MetadataImpl
		 * @see urncore.impl.UrncorePackageImpl#getMetadata()
		 * @generated
		 */
		EClass METADATA = eINSTANCE.getMetadata();

        /**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METADATA__NAME = eINSTANCE.getMetadata_Name();

        /**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METADATA__VALUE = eINSTANCE.getMetadata_Value();

        /**
		 * The meta object literal for the '{@link urncore.impl.ConcernImpl <em>Concern</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urncore.impl.ConcernImpl
		 * @see urncore.impl.UrncorePackageImpl#getConcern()
		 * @generated
		 */
		EClass CONCERN = eINSTANCE.getConcern();

        /**
		 * The meta object literal for the '<em><b>Urndefinition</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCERN__URNDEFINITION = eINSTANCE.getConcern_Urndefinition();

        /**
		 * The meta object literal for the '<em><b>Spec Diagrams</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCERN__SPEC_DIAGRAMS = eINSTANCE.getConcern_SpecDiagrams();

        /**
		 * The meta object literal for the '<em><b>Elements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference CONCERN__ELEMENTS = eINSTANCE.getConcern_Elements();

        /**
		 * The meta object literal for the '<em><b>Core Concern</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCERN__CORE_CONCERN = eINSTANCE.getConcern_CoreConcern();

								/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCERN__CONDITION = eINSTANCE.getConcern_Condition();

								/**
		 * The meta object literal for the '{@link urncore.impl.ConnectionLabelImpl <em>Connection Label</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urncore.impl.ConnectionLabelImpl
		 * @see urncore.impl.UrncorePackageImpl#getConnectionLabel()
		 * @generated
		 */
		EClass CONNECTION_LABEL = eINSTANCE.getConnectionLabel();

								/**
		 * The meta object literal for the '<em><b>Connection</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION_LABEL__CONNECTION = eINSTANCE.getConnectionLabel_Connection();

								/**
		 * The meta object literal for the '{@link urncore.impl.CommentImpl <em>Comment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urncore.impl.CommentImpl
		 * @see urncore.impl.UrncorePackageImpl#getComment()
		 * @generated
		 */
		EClass COMMENT = eINSTANCE.getComment();

								/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__DESCRIPTION = eINSTANCE.getComment_Description();

								/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__X = eINSTANCE.getComment_X();

								/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__Y = eINSTANCE.getComment_Y();

								/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__WIDTH = eINSTANCE.getComment_Width();

								/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__HEIGHT = eINSTANCE.getComment_Height();

								/**
		 * The meta object literal for the '<em><b>Fill Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__FILL_COLOR = eINSTANCE.getComment_FillColor();

								/**
		 * The meta object literal for the '<em><b>Diagram</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMMENT__DIAGRAM = eINSTANCE.getComment_Diagram();

								/**
		 * The meta object literal for the '{@link urncore.ComponentKind <em>Component Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urncore.ComponentKind
		 * @see urncore.impl.UrncorePackageImpl#getComponentKind()
		 * @generated
		 */
		EEnum COMPONENT_KIND = eINSTANCE.getComponentKind();

	}

} //UrncorePackage
