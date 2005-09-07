/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm;

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
 * @see ucm.UcmFactory
 * @model kind="package"
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
	String eNS_URI = "http:///ucm.ecore";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "ucm";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	UcmPackage eINSTANCE = ucm.impl.UcmPackageImpl.init();

    /**
     * The meta object id for the '{@link ucm.impl.UCMspecImpl <em>UC Mspec</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see ucm.impl.UCMspecImpl
     * @see ucm.impl.UcmPackageImpl#getUCMspec()
     * @generated
     */
	int UC_MSPEC = 0;

    /**
     * The feature id for the '<em><b>Urnspec</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UC_MSPEC__URNSPEC = 0;

    /**
     * The feature id for the '<em><b>Resptimereq</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UC_MSPEC__RESPTIMEREQ = 1;

    /**
     * The feature id for the '<em><b>Perf Measures</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UC_MSPEC__PERF_MEASURES = 2;

    /**
     * The feature id for the '<em><b>Resources</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UC_MSPEC__RESOURCES = 3;

    /**
     * The feature id for the '<em><b>Maps</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UC_MSPEC__MAPS = 4;

    /**
     * The feature id for the '<em><b>Root</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UC_MSPEC__ROOT = 5;

    /**
     * The feature id for the '<em><b>Scenario Groups</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UC_MSPEC__SCENARIO_GROUPS = 6;

    /**
     * The feature id for the '<em><b>Variables</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UC_MSPEC__VARIABLES = 7;

    /**
     * The feature id for the '<em><b>Scenario Defs</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UC_MSPEC__SCENARIO_DEFS = 8;

    /**
     * The number of structural features of the the '<em>UC Mspec</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int UC_MSPEC_FEATURE_COUNT = 9;


    /**
     * Returns the meta object for class '{@link ucm.UCMspec <em>UC Mspec</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>UC Mspec</em>'.
     * @see ucm.UCMspec
     * @generated
     */
	EClass getUCMspec();

    /**
     * Returns the meta object for the container reference '{@link ucm.UCMspec#getUrnspec <em>Urnspec</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Urnspec</em>'.
     * @see ucm.UCMspec#getUrnspec()
     * @see #getUCMspec()
     * @generated
     */
	EReference getUCMspec_Urnspec();

    /**
     * Returns the meta object for the containment reference list '{@link ucm.UCMspec#getResptimereq <em>Resptimereq</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Resptimereq</em>'.
     * @see ucm.UCMspec#getResptimereq()
     * @see #getUCMspec()
     * @generated
     */
	EReference getUCMspec_Resptimereq();

    /**
     * Returns the meta object for the containment reference list '{@link ucm.UCMspec#getPerfMeasures <em>Perf Measures</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Perf Measures</em>'.
     * @see ucm.UCMspec#getPerfMeasures()
     * @see #getUCMspec()
     * @generated
     */
	EReference getUCMspec_PerfMeasures();

    /**
     * Returns the meta object for the containment reference list '{@link ucm.UCMspec#getResources <em>Resources</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Resources</em>'.
     * @see ucm.UCMspec#getResources()
     * @see #getUCMspec()
     * @generated
     */
	EReference getUCMspec_Resources();

    /**
     * Returns the meta object for the containment reference list '{@link ucm.UCMspec#getMaps <em>Maps</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Maps</em>'.
     * @see ucm.UCMspec#getMaps()
     * @see #getUCMspec()
     * @generated
     */
	EReference getUCMspec_Maps();

    /**
     * Returns the meta object for the reference list '{@link ucm.UCMspec#getRoot <em>Root</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Root</em>'.
     * @see ucm.UCMspec#getRoot()
     * @see #getUCMspec()
     * @generated
     */
	EReference getUCMspec_Root();

    /**
     * Returns the meta object for the containment reference list '{@link ucm.UCMspec#getScenarioGroups <em>Scenario Groups</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Scenario Groups</em>'.
     * @see ucm.UCMspec#getScenarioGroups()
     * @see #getUCMspec()
     * @generated
     */
	EReference getUCMspec_ScenarioGroups();

    /**
     * Returns the meta object for the containment reference list '{@link ucm.UCMspec#getVariables <em>Variables</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Variables</em>'.
     * @see ucm.UCMspec#getVariables()
     * @see #getUCMspec()
     * @generated
     */
	EReference getUCMspec_Variables();

    /**
     * Returns the meta object for the containment reference list '{@link ucm.UCMspec#getScenarioDefs <em>Scenario Defs</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Scenario Defs</em>'.
     * @see ucm.UCMspec#getScenarioDefs()
     * @see #getUCMspec()
     * @generated
     */
	EReference getUCMspec_ScenarioDefs();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	UcmFactory getUcmFactory();

} //UcmPackage
