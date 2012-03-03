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
public interface UcmPackage extends EPackage {
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
	 * The feature id for the '<em><b>Resources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UC_MSPEC__RESOURCES = 1;

    /**
	 * The feature id for the '<em><b>Scenario Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UC_MSPEC__SCENARIO_GROUPS = 2;

    /**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UC_MSPEC__VARIABLES = 3;

    /**
	 * The feature id for the '<em><b>Enumeration Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UC_MSPEC__ENUMERATION_TYPES = 4;

    /**
	 * The number of structural features of the '<em>UC Mspec</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int UC_MSPEC_FEATURE_COUNT = 5;


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
	 * Returns the meta object for the containment reference list '{@link ucm.UCMspec#getEnumerationTypes <em>Enumeration Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Enumeration Types</em>'.
	 * @see ucm.UCMspec#getEnumerationTypes()
	 * @see #getUCMspec()
	 * @generated
	 */
	EReference getUCMspec_EnumerationTypes();

    /**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
    UcmFactory getUcmFactory();

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
		 * The meta object literal for the '{@link ucm.impl.UCMspecImpl <em>UC Mspec</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see ucm.impl.UCMspecImpl
		 * @see ucm.impl.UcmPackageImpl#getUCMspec()
		 * @generated
		 */
		EClass UC_MSPEC = eINSTANCE.getUCMspec();

        /**
		 * The meta object literal for the '<em><b>Urnspec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UC_MSPEC__URNSPEC = eINSTANCE.getUCMspec_Urnspec();

        /**
		 * The meta object literal for the '<em><b>Resources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UC_MSPEC__RESOURCES = eINSTANCE.getUCMspec_Resources();

        /**
		 * The meta object literal for the '<em><b>Scenario Groups</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UC_MSPEC__SCENARIO_GROUPS = eINSTANCE.getUCMspec_ScenarioGroups();

        /**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UC_MSPEC__VARIABLES = eINSTANCE.getUCMspec_Variables();

        /**
		 * The meta object literal for the '<em><b>Enumeration Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UC_MSPEC__ENUMERATION_TYPES = eINSTANCE.getUCMspec_EnumerationTypes();

	}

} //UcmPackage
