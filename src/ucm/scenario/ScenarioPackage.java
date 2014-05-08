/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.scenario;

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
 * @see ucm.scenario.ScenarioFactory
 * @model kind="package"
 * @generated
 */
public interface ScenarioPackage extends EPackage {
    /**
	 * The package name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNAME = "scenario";

    /**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_URI = "http:///ucm/scenario.ecore";

    /**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_PREFIX = "ucm.scenario";

    /**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    ScenarioPackage eINSTANCE = ucm.scenario.impl.ScenarioPackageImpl.init();

    /**
	 * The meta object id for the '{@link ucm.scenario.impl.ScenarioGroupImpl <em>Group</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see ucm.scenario.impl.ScenarioGroupImpl
	 * @see ucm.scenario.impl.ScenarioPackageImpl#getScenarioGroup()
	 * @generated
	 */
    int SCENARIO_GROUP = 2;

    /**
	 * The meta object id for the '{@link ucm.scenario.impl.ScenarioDefImpl <em>Def</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see ucm.scenario.impl.ScenarioDefImpl
	 * @see ucm.scenario.impl.ScenarioPackageImpl#getScenarioDef()
	 * @generated
	 */
    int SCENARIO_DEF = 1;

    /**
	 * The meta object id for the '{@link ucm.scenario.impl.VariableImpl <em>Variable</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see ucm.scenario.impl.VariableImpl
	 * @see ucm.scenario.impl.ScenarioPackageImpl#getVariable()
	 * @generated
	 */
    int VARIABLE = 0;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int VARIABLE__NAME = UrncorePackage.UC_MMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int VARIABLE__FROM_LINKS = UrncorePackage.UC_MMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int VARIABLE__TO_LINKS = UrncorePackage.UC_MMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int VARIABLE__ID = UrncorePackage.UC_MMODEL_ELEMENT__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int VARIABLE__DESCRIPTION = UrncorePackage.UC_MMODEL_ELEMENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__METADATA = UrncorePackage.UC_MMODEL_ELEMENT__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int VARIABLE__INCONCERN = UrncorePackage.UC_MMODEL_ELEMENT__INCONCERN;

    /**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int VARIABLE__TYPE = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Ucmspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int VARIABLE__UCMSPEC = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Enumeration Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VARIABLE__ENUMERATION_TYPE = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
	 * The number of structural features of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int VARIABLE_FEATURE_COUNT = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 3;


    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SCENARIO_DEF__NAME = UrncorePackage.UC_MMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SCENARIO_DEF__FROM_LINKS = UrncorePackage.UC_MMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SCENARIO_DEF__TO_LINKS = UrncorePackage.UC_MMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SCENARIO_DEF__ID = UrncorePackage.UC_MMODEL_ELEMENT__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SCENARIO_DEF__DESCRIPTION = UrncorePackage.UC_MMODEL_ELEMENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_DEF__METADATA = UrncorePackage.UC_MMODEL_ELEMENT__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SCENARIO_DEF__INCONCERN = UrncorePackage.UC_MMODEL_ELEMENT__INCONCERN;

    /**
	 * The feature id for the '<em><b>Group</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_DEF__GROUP = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Parent Scenarios</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_DEF__PARENT_SCENARIOS = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Included Scenarios</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_DEF__INCLUDED_SCENARIOS = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Preconditions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_DEF__PRECONDITIONS = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Postconditions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_DEF__POSTCONDITIONS = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
	 * The feature id for the '<em><b>Initializations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_DEF__INITIALIZATIONS = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 5;

    /**
	 * The feature id for the '<em><b>Start Points</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SCENARIO_DEF__START_POINTS = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 6;

    /**
	 * The feature id for the '<em><b>End Points</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_DEF__END_POINTS = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 7;

    /**
	 * The number of structural features of the '<em>Def</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SCENARIO_DEF_FEATURE_COUNT = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 8;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SCENARIO_GROUP__NAME = UrncorePackage.UC_MMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SCENARIO_GROUP__FROM_LINKS = UrncorePackage.UC_MMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SCENARIO_GROUP__TO_LINKS = UrncorePackage.UC_MMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SCENARIO_GROUP__ID = UrncorePackage.UC_MMODEL_ELEMENT__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SCENARIO_GROUP__DESCRIPTION = UrncorePackage.UC_MMODEL_ELEMENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_GROUP__METADATA = UrncorePackage.UC_MMODEL_ELEMENT__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SCENARIO_GROUP__INCONCERN = UrncorePackage.UC_MMODEL_ELEMENT__INCONCERN;

    /**
	 * The feature id for the '<em><b>Ucmspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SCENARIO_GROUP__UCMSPEC = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Scenarios</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SCENARIO_GROUP__SCENARIOS = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Group</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SCENARIO_GROUP_FEATURE_COUNT = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
	 * The meta object id for the '{@link ucm.scenario.impl.EnumerationTypeImpl <em>Enumeration Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ucm.scenario.impl.EnumerationTypeImpl
	 * @see ucm.scenario.impl.ScenarioPackageImpl#getEnumerationType()
	 * @generated
	 */
	int ENUMERATION_TYPE = 3;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_TYPE__NAME = UrncorePackage.UC_MMODEL_ELEMENT__NAME;

				/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_TYPE__FROM_LINKS = UrncorePackage.UC_MMODEL_ELEMENT__FROM_LINKS;

				/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_TYPE__TO_LINKS = UrncorePackage.UC_MMODEL_ELEMENT__TO_LINKS;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_TYPE__ID = UrncorePackage.UC_MMODEL_ELEMENT__ID;

    /**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_TYPE__DESCRIPTION = UrncorePackage.UC_MMODEL_ELEMENT__DESCRIPTION;

    /**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_TYPE__METADATA = UrncorePackage.UC_MMODEL_ELEMENT__METADATA;

    /**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ENUMERATION_TYPE__INCONCERN = UrncorePackage.UC_MMODEL_ELEMENT__INCONCERN;

    /**
	 * The feature id for the '<em><b>Values</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_TYPE__VALUES = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Ucmspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_TYPE__UCMSPEC = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Instances</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_TYPE__INSTANCES = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
	 * The number of structural features of the '<em>Enumeration Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUMERATION_TYPE_FEATURE_COUNT = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
	 * The meta object id for the '{@link ucm.scenario.impl.InitializationImpl <em>Initialization</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ucm.scenario.impl.InitializationImpl
	 * @see ucm.scenario.impl.ScenarioPackageImpl#getInitialization()
	 * @generated
	 */
	int INITIALIZATION = 4;

    /**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIALIZATION__VALUE = 0;

    /**
	 * The feature id for the '<em><b>Scenario Def</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIALIZATION__SCENARIO_DEF = 1;

    /**
	 * The feature id for the '<em><b>Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIALIZATION__VARIABLE = 2;

    /**
	 * The number of structural features of the '<em>Initialization</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIALIZATION_FEATURE_COUNT = 3;


    /**
	 * The meta object id for the '{@link ucm.scenario.impl.ScenarioStartPointImpl <em>Start Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ucm.scenario.impl.ScenarioStartPointImpl
	 * @see ucm.scenario.impl.ScenarioPackageImpl#getScenarioStartPoint()
	 * @generated
	 */
	int SCENARIO_START_POINT = 5;

    /**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_START_POINT__ENABLED = 0;

    /**
	 * The feature id for the '<em><b>Scenario Def</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_START_POINT__SCENARIO_DEF = 1;

    /**
	 * The feature id for the '<em><b>Start Point</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_START_POINT__START_POINT = 2;

    /**
	 * The number of structural features of the '<em>Start Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_START_POINT_FEATURE_COUNT = 3;

    /**
	 * The meta object id for the '{@link ucm.scenario.impl.ScenarioEndPointImpl <em>End Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ucm.scenario.impl.ScenarioEndPointImpl
	 * @see ucm.scenario.impl.ScenarioPackageImpl#getScenarioEndPoint()
	 * @generated
	 */
	int SCENARIO_END_POINT = 6;

    /**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_END_POINT__ENABLED = 0;

    /**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_END_POINT__MANDATORY = 1;

    /**
	 * The feature id for the '<em><b>Scenario Def</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_END_POINT__SCENARIO_DEF = 2;

    /**
	 * The feature id for the '<em><b>End Point</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_END_POINT__END_POINT = 3;

    /**
	 * The number of structural features of the '<em>End Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCENARIO_END_POINT_FEATURE_COUNT = 4;


    /**
	 * Returns the meta object for class '{@link ucm.scenario.ScenarioGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Group</em>'.
	 * @see ucm.scenario.ScenarioGroup
	 * @generated
	 */
    EClass getScenarioGroup();

    /**
	 * Returns the meta object for the container reference '{@link ucm.scenario.ScenarioGroup#getUcmspec <em>Ucmspec</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Ucmspec</em>'.
	 * @see ucm.scenario.ScenarioGroup#getUcmspec()
	 * @see #getScenarioGroup()
	 * @generated
	 */
    EReference getScenarioGroup_Ucmspec();

    /**
	 * Returns the meta object for the containment reference list '{@link ucm.scenario.ScenarioGroup#getScenarios <em>Scenarios</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Scenarios</em>'.
	 * @see ucm.scenario.ScenarioGroup#getScenarios()
	 * @see #getScenarioGroup()
	 * @generated
	 */
    EReference getScenarioGroup_Scenarios();

    /**
	 * Returns the meta object for class '{@link ucm.scenario.EnumerationType <em>Enumeration Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enumeration Type</em>'.
	 * @see ucm.scenario.EnumerationType
	 * @generated
	 */
	EClass getEnumerationType();

    /**
	 * Returns the meta object for the attribute '{@link ucm.scenario.EnumerationType#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Values</em>'.
	 * @see ucm.scenario.EnumerationType#getValues()
	 * @see #getEnumerationType()
	 * @generated
	 */
	EAttribute getEnumerationType_Values();

    /**
	 * Returns the meta object for the container reference '{@link ucm.scenario.EnumerationType#getUcmspec <em>Ucmspec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Ucmspec</em>'.
	 * @see ucm.scenario.EnumerationType#getUcmspec()
	 * @see #getEnumerationType()
	 * @generated
	 */
	EReference getEnumerationType_Ucmspec();

    /**
	 * Returns the meta object for the reference list '{@link ucm.scenario.EnumerationType#getInstances <em>Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Instances</em>'.
	 * @see ucm.scenario.EnumerationType#getInstances()
	 * @see #getEnumerationType()
	 * @generated
	 */
	EReference getEnumerationType_Instances();

    /**
	 * Returns the meta object for class '{@link ucm.scenario.Initialization <em>Initialization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Initialization</em>'.
	 * @see ucm.scenario.Initialization
	 * @generated
	 */
	EClass getInitialization();

    /**
	 * Returns the meta object for the attribute '{@link ucm.scenario.Initialization#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see ucm.scenario.Initialization#getValue()
	 * @see #getInitialization()
	 * @generated
	 */
	EAttribute getInitialization_Value();

    /**
	 * Returns the meta object for the container reference '{@link ucm.scenario.Initialization#getScenarioDef <em>Scenario Def</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Scenario Def</em>'.
	 * @see ucm.scenario.Initialization#getScenarioDef()
	 * @see #getInitialization()
	 * @generated
	 */
	EReference getInitialization_ScenarioDef();

    /**
	 * Returns the meta object for the reference '{@link ucm.scenario.Initialization#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Variable</em>'.
	 * @see ucm.scenario.Initialization#getVariable()
	 * @see #getInitialization()
	 * @generated
	 */
	EReference getInitialization_Variable();

    /**
	 * Returns the meta object for class '{@link ucm.scenario.ScenarioStartPoint <em>Start Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Start Point</em>'.
	 * @see ucm.scenario.ScenarioStartPoint
	 * @generated
	 */
	EClass getScenarioStartPoint();

    /**
	 * Returns the meta object for the attribute '{@link ucm.scenario.ScenarioStartPoint#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see ucm.scenario.ScenarioStartPoint#isEnabled()
	 * @see #getScenarioStartPoint()
	 * @generated
	 */
	EAttribute getScenarioStartPoint_Enabled();

    /**
	 * Returns the meta object for the container reference '{@link ucm.scenario.ScenarioStartPoint#getScenarioDef <em>Scenario Def</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Scenario Def</em>'.
	 * @see ucm.scenario.ScenarioStartPoint#getScenarioDef()
	 * @see #getScenarioStartPoint()
	 * @generated
	 */
	EReference getScenarioStartPoint_ScenarioDef();

    /**
	 * Returns the meta object for the reference '{@link ucm.scenario.ScenarioStartPoint#getStartPoint <em>Start Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Start Point</em>'.
	 * @see ucm.scenario.ScenarioStartPoint#getStartPoint()
	 * @see #getScenarioStartPoint()
	 * @generated
	 */
	EReference getScenarioStartPoint_StartPoint();

    /**
	 * Returns the meta object for class '{@link ucm.scenario.ScenarioEndPoint <em>End Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>End Point</em>'.
	 * @see ucm.scenario.ScenarioEndPoint
	 * @generated
	 */
	EClass getScenarioEndPoint();

    /**
	 * Returns the meta object for the attribute '{@link ucm.scenario.ScenarioEndPoint#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see ucm.scenario.ScenarioEndPoint#isEnabled()
	 * @see #getScenarioEndPoint()
	 * @generated
	 */
	EAttribute getScenarioEndPoint_Enabled();

    /**
	 * Returns the meta object for the attribute '{@link ucm.scenario.ScenarioEndPoint#isMandatory <em>Mandatory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mandatory</em>'.
	 * @see ucm.scenario.ScenarioEndPoint#isMandatory()
	 * @see #getScenarioEndPoint()
	 * @generated
	 */
	EAttribute getScenarioEndPoint_Mandatory();

    /**
	 * Returns the meta object for the container reference '{@link ucm.scenario.ScenarioEndPoint#getScenarioDef <em>Scenario Def</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Scenario Def</em>'.
	 * @see ucm.scenario.ScenarioEndPoint#getScenarioDef()
	 * @see #getScenarioEndPoint()
	 * @generated
	 */
	EReference getScenarioEndPoint_ScenarioDef();

    /**
	 * Returns the meta object for the reference '{@link ucm.scenario.ScenarioEndPoint#getEndPoint <em>End Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>End Point</em>'.
	 * @see ucm.scenario.ScenarioEndPoint#getEndPoint()
	 * @see #getScenarioEndPoint()
	 * @generated
	 */
	EReference getScenarioEndPoint_EndPoint();

    /**
	 * Returns the meta object for class '{@link ucm.scenario.ScenarioDef <em>Def</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Def</em>'.
	 * @see ucm.scenario.ScenarioDef
	 * @generated
	 */
    EClass getScenarioDef();

    /**
	 * Returns the meta object for the containment reference list '{@link ucm.scenario.ScenarioDef#getStartPoints <em>Start Points</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Start Points</em>'.
	 * @see ucm.scenario.ScenarioDef#getStartPoints()
	 * @see #getScenarioDef()
	 * @generated
	 */
    EReference getScenarioDef_StartPoints();

    /**
	 * Returns the meta object for the container reference '{@link ucm.scenario.ScenarioDef#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Group</em>'.
	 * @see ucm.scenario.ScenarioDef#getGroup()
	 * @see #getScenarioDef()
	 * @generated
	 */
	EReference getScenarioDef_Group();

    /**
	 * Returns the meta object for the reference list '{@link ucm.scenario.ScenarioDef#getParentScenarios <em>Parent Scenarios</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parent Scenarios</em>'.
	 * @see ucm.scenario.ScenarioDef#getParentScenarios()
	 * @see #getScenarioDef()
	 * @generated
	 */
	EReference getScenarioDef_ParentScenarios();

    /**
	 * Returns the meta object for the reference list '{@link ucm.scenario.ScenarioDef#getIncludedScenarios <em>Included Scenarios</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Included Scenarios</em>'.
	 * @see ucm.scenario.ScenarioDef#getIncludedScenarios()
	 * @see #getScenarioDef()
	 * @generated
	 */
	EReference getScenarioDef_IncludedScenarios();

    /**
	 * Returns the meta object for the containment reference list '{@link ucm.scenario.ScenarioDef#getEndPoints <em>End Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>End Points</em>'.
	 * @see ucm.scenario.ScenarioDef#getEndPoints()
	 * @see #getScenarioDef()
	 * @generated
	 */
	EReference getScenarioDef_EndPoints();

    /**
	 * Returns the meta object for the containment reference list '{@link ucm.scenario.ScenarioDef#getPreconditions <em>Preconditions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Preconditions</em>'.
	 * @see ucm.scenario.ScenarioDef#getPreconditions()
	 * @see #getScenarioDef()
	 * @generated
	 */
	EReference getScenarioDef_Preconditions();

    /**
	 * Returns the meta object for the containment reference list '{@link ucm.scenario.ScenarioDef#getPostconditions <em>Postconditions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Postconditions</em>'.
	 * @see ucm.scenario.ScenarioDef#getPostconditions()
	 * @see #getScenarioDef()
	 * @generated
	 */
	EReference getScenarioDef_Postconditions();

    /**
	 * Returns the meta object for the containment reference list '{@link ucm.scenario.ScenarioDef#getInitializations <em>Initializations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Initializations</em>'.
	 * @see ucm.scenario.ScenarioDef#getInitializations()
	 * @see #getScenarioDef()
	 * @generated
	 */
	EReference getScenarioDef_Initializations();

    /**
	 * Returns the meta object for class '{@link ucm.scenario.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable</em>'.
	 * @see ucm.scenario.Variable
	 * @generated
	 */
    EClass getVariable();

    /**
	 * Returns the meta object for the attribute '{@link ucm.scenario.Variable#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see ucm.scenario.Variable#getType()
	 * @see #getVariable()
	 * @generated
	 */
    EAttribute getVariable_Type();

    /**
	 * Returns the meta object for the container reference '{@link ucm.scenario.Variable#getUcmspec <em>Ucmspec</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Ucmspec</em>'.
	 * @see ucm.scenario.Variable#getUcmspec()
	 * @see #getVariable()
	 * @generated
	 */
    EReference getVariable_Ucmspec();

    /**
	 * Returns the meta object for the reference '{@link ucm.scenario.Variable#getEnumerationType <em>Enumeration Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Enumeration Type</em>'.
	 * @see ucm.scenario.Variable#getEnumerationType()
	 * @see #getVariable()
	 * @generated
	 */
	EReference getVariable_EnumerationType();

    /**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
    ScenarioFactory getScenarioFactory();

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
		 * The meta object literal for the '{@link ucm.scenario.impl.VariableImpl <em>Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ucm.scenario.impl.VariableImpl
		 * @see ucm.scenario.impl.ScenarioPackageImpl#getVariable()
		 * @generated
		 */
		EClass VARIABLE = eINSTANCE.getVariable();

        /**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__TYPE = eINSTANCE.getVariable_Type();

        /**
		 * The meta object literal for the '<em><b>Ucmspec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE__UCMSPEC = eINSTANCE.getVariable_Ucmspec();

        /**
		 * The meta object literal for the '<em><b>Enumeration Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE__ENUMERATION_TYPE = eINSTANCE.getVariable_EnumerationType();

        /**
		 * The meta object literal for the '{@link ucm.scenario.impl.ScenarioDefImpl <em>Def</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ucm.scenario.impl.ScenarioDefImpl
		 * @see ucm.scenario.impl.ScenarioPackageImpl#getScenarioDef()
		 * @generated
		 */
		EClass SCENARIO_DEF = eINSTANCE.getScenarioDef();

        /**
		 * The meta object literal for the '<em><b>Start Points</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_DEF__START_POINTS = eINSTANCE.getScenarioDef_StartPoints();

        /**
		 * The meta object literal for the '<em><b>Group</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_DEF__GROUP = eINSTANCE.getScenarioDef_Group();

        /**
		 * The meta object literal for the '<em><b>Parent Scenarios</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_DEF__PARENT_SCENARIOS = eINSTANCE.getScenarioDef_ParentScenarios();

        /**
		 * The meta object literal for the '<em><b>Included Scenarios</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_DEF__INCLUDED_SCENARIOS = eINSTANCE.getScenarioDef_IncludedScenarios();

        /**
		 * The meta object literal for the '<em><b>End Points</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_DEF__END_POINTS = eINSTANCE.getScenarioDef_EndPoints();

        /**
		 * The meta object literal for the '<em><b>Preconditions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_DEF__PRECONDITIONS = eINSTANCE.getScenarioDef_Preconditions();

        /**
		 * The meta object literal for the '<em><b>Postconditions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_DEF__POSTCONDITIONS = eINSTANCE.getScenarioDef_Postconditions();

        /**
		 * The meta object literal for the '<em><b>Initializations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_DEF__INITIALIZATIONS = eINSTANCE.getScenarioDef_Initializations();

        /**
		 * The meta object literal for the '{@link ucm.scenario.impl.ScenarioGroupImpl <em>Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ucm.scenario.impl.ScenarioGroupImpl
		 * @see ucm.scenario.impl.ScenarioPackageImpl#getScenarioGroup()
		 * @generated
		 */
		EClass SCENARIO_GROUP = eINSTANCE.getScenarioGroup();

        /**
		 * The meta object literal for the '<em><b>Ucmspec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_GROUP__UCMSPEC = eINSTANCE.getScenarioGroup_Ucmspec();

        /**
		 * The meta object literal for the '<em><b>Scenarios</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_GROUP__SCENARIOS = eINSTANCE.getScenarioGroup_Scenarios();

        /**
		 * The meta object literal for the '{@link ucm.scenario.impl.EnumerationTypeImpl <em>Enumeration Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ucm.scenario.impl.EnumerationTypeImpl
		 * @see ucm.scenario.impl.ScenarioPackageImpl#getEnumerationType()
		 * @generated
		 */
		EClass ENUMERATION_TYPE = eINSTANCE.getEnumerationType();

        /**
		 * The meta object literal for the '<em><b>Values</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENUMERATION_TYPE__VALUES = eINSTANCE.getEnumerationType_Values();

        /**
		 * The meta object literal for the '<em><b>Ucmspec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENUMERATION_TYPE__UCMSPEC = eINSTANCE.getEnumerationType_Ucmspec();

        /**
		 * The meta object literal for the '<em><b>Instances</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENUMERATION_TYPE__INSTANCES = eINSTANCE.getEnumerationType_Instances();

        /**
		 * The meta object literal for the '{@link ucm.scenario.impl.InitializationImpl <em>Initialization</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ucm.scenario.impl.InitializationImpl
		 * @see ucm.scenario.impl.ScenarioPackageImpl#getInitialization()
		 * @generated
		 */
		EClass INITIALIZATION = eINSTANCE.getInitialization();

        /**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INITIALIZATION__VALUE = eINSTANCE.getInitialization_Value();

        /**
		 * The meta object literal for the '<em><b>Scenario Def</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INITIALIZATION__SCENARIO_DEF = eINSTANCE.getInitialization_ScenarioDef();

        /**
		 * The meta object literal for the '<em><b>Variable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INITIALIZATION__VARIABLE = eINSTANCE.getInitialization_Variable();

        /**
		 * The meta object literal for the '{@link ucm.scenario.impl.ScenarioStartPointImpl <em>Start Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ucm.scenario.impl.ScenarioStartPointImpl
		 * @see ucm.scenario.impl.ScenarioPackageImpl#getScenarioStartPoint()
		 * @generated
		 */
		EClass SCENARIO_START_POINT = eINSTANCE.getScenarioStartPoint();

        /**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCENARIO_START_POINT__ENABLED = eINSTANCE.getScenarioStartPoint_Enabled();

        /**
		 * The meta object literal for the '<em><b>Scenario Def</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_START_POINT__SCENARIO_DEF = eINSTANCE.getScenarioStartPoint_ScenarioDef();

        /**
		 * The meta object literal for the '<em><b>Start Point</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_START_POINT__START_POINT = eINSTANCE.getScenarioStartPoint_StartPoint();

        /**
		 * The meta object literal for the '{@link ucm.scenario.impl.ScenarioEndPointImpl <em>End Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ucm.scenario.impl.ScenarioEndPointImpl
		 * @see ucm.scenario.impl.ScenarioPackageImpl#getScenarioEndPoint()
		 * @generated
		 */
		EClass SCENARIO_END_POINT = eINSTANCE.getScenarioEndPoint();

        /**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCENARIO_END_POINT__ENABLED = eINSTANCE.getScenarioEndPoint_Enabled();

        /**
		 * The meta object literal for the '<em><b>Mandatory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCENARIO_END_POINT__MANDATORY = eINSTANCE.getScenarioEndPoint_Mandatory();

        /**
		 * The meta object literal for the '<em><b>Scenario Def</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_END_POINT__SCENARIO_DEF = eINSTANCE.getScenarioEndPoint_ScenarioDef();

        /**
		 * The meta object literal for the '<em><b>End Point</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCENARIO_END_POINT__END_POINT = eINSTANCE.getScenarioEndPoint_EndPoint();

	}

} //ScenarioPackage
