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
public interface ScenarioPackage extends EPackage{
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
	int SCENARIO_GROUP = 0;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCENARIO_GROUP__URN_LINKS = UrncorePackage.UC_MMODEL_ELEMENT__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCENARIO_GROUP__ID = UrncorePackage.UC_MMODEL_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCENARIO_GROUP__NAME = UrncorePackage.UC_MMODEL_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCENARIO_GROUP__DESCRIPTION = UrncorePackage.UC_MMODEL_ELEMENT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Urnspec</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCENARIO_GROUP__URNSPEC = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Scenario Def</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCENARIO_GROUP__SCENARIO_DEF = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the the '<em>Group</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCENARIO_GROUP_FEATURE_COUNT = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 2;

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
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCENARIO_DEF__URN_LINKS = UrncorePackage.UC_MMODEL_ELEMENT__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCENARIO_DEF__ID = UrncorePackage.UC_MMODEL_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCENARIO_DEF__NAME = UrncorePackage.UC_MMODEL_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCENARIO_DEF__DESCRIPTION = UrncorePackage.UC_MMODEL_ELEMENT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Start Points</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCENARIO_DEF__START_POINTS = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Urnspec</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCENARIO_DEF__URNSPEC = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the the '<em>Def</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int SCENARIO_DEF_FEATURE_COUNT = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link ucm.scenario.impl.VariableImpl <em>Variable</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see ucm.scenario.impl.VariableImpl
     * @see ucm.scenario.impl.ScenarioPackageImpl#getVariable()
     * @generated
     */
	int VARIABLE = 2;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VARIABLE__URN_LINKS = UrncorePackage.UC_MMODEL_ELEMENT__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VARIABLE__ID = UrncorePackage.UC_MMODEL_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VARIABLE__NAME = UrncorePackage.UC_MMODEL_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VARIABLE__DESCRIPTION = UrncorePackage.UC_MMODEL_ELEMENT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VARIABLE__TYPE = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Urnspec</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VARIABLE__URNSPEC = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Usages</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VARIABLE__USAGES = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the the '<em>Variable</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int VARIABLE_FEATURE_COUNT = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 3;


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
     * Returns the meta object for the container reference '{@link ucm.scenario.ScenarioGroup#getUrnspec <em>Urnspec</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Urnspec</em>'.
     * @see ucm.scenario.ScenarioGroup#getUrnspec()
     * @see #getScenarioGroup()
     * @generated
     */
	EReference getScenarioGroup_Urnspec();

    /**
     * Returns the meta object for the containment reference list '{@link ucm.scenario.ScenarioGroup#getScenarioDef <em>Scenario Def</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Scenario Def</em>'.
     * @see ucm.scenario.ScenarioGroup#getScenarioDef()
     * @see #getScenarioGroup()
     * @generated
     */
	EReference getScenarioGroup_ScenarioDef();

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
     * Returns the meta object for the reference list '{@link ucm.scenario.ScenarioDef#getStartPoints <em>Start Points</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Start Points</em>'.
     * @see ucm.scenario.ScenarioDef#getStartPoints()
     * @see #getScenarioDef()
     * @generated
     */
	EReference getScenarioDef_StartPoints();

    /**
     * Returns the meta object for the container reference '{@link ucm.scenario.ScenarioDef#getUrnspec <em>Urnspec</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Urnspec</em>'.
     * @see ucm.scenario.ScenarioDef#getUrnspec()
     * @see #getScenarioDef()
     * @generated
     */
	EReference getScenarioDef_Urnspec();

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
     * Returns the meta object for the container reference '{@link ucm.scenario.Variable#getUrnspec <em>Urnspec</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Urnspec</em>'.
     * @see ucm.scenario.Variable#getUrnspec()
     * @see #getVariable()
     * @generated
     */
	EReference getVariable_Urnspec();

    /**
     * Returns the meta object for the reference list '{@link ucm.scenario.Variable#getUsages <em>Usages</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Usages</em>'.
     * @see ucm.scenario.Variable#getUsages()
     * @see #getVariable()
     * @generated
     */
	EReference getVariable_Usages();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	ScenarioFactory getScenarioFactory();

} //ScenarioPackage
