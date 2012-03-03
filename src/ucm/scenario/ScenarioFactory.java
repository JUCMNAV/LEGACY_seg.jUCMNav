/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.scenario;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see ucm.scenario.ScenarioPackage
 * @generated
 */
public interface ScenarioFactory extends EFactory {
    /**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    ScenarioFactory eINSTANCE = ucm.scenario.impl.ScenarioFactoryImpl.init();

    /**
	 * Returns a new object of class '<em>Group</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Group</em>'.
	 * @generated
	 */
    ScenarioGroup createScenarioGroup();

    /**
	 * Returns a new object of class '<em>Enumeration Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Enumeration Type</em>'.
	 * @generated
	 */
	EnumerationType createEnumerationType();

    /**
	 * Returns a new object of class '<em>Initialization</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Initialization</em>'.
	 * @generated
	 */
	Initialization createInitialization();

    /**
	 * Returns a new object of class '<em>Start Point</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Start Point</em>'.
	 * @generated
	 */
	ScenarioStartPoint createScenarioStartPoint();

    /**
	 * Returns a new object of class '<em>End Point</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>End Point</em>'.
	 * @generated
	 */
	ScenarioEndPoint createScenarioEndPoint();

    /**
	 * Returns a new object of class '<em>Variable</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Variable</em>'.
	 * @generated
	 */
    Variable createVariable();

    /**
	 * Returns a new object of class '<em>Def</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Def</em>'.
	 * @generated
	 */
	ScenarioDef createScenarioDef();

    /**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
    ScenarioPackage getScenarioPackage();

} //ScenarioFactory
