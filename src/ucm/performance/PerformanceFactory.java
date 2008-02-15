/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see ucm.performance.PerformancePackage
 * @generated
 */
public interface PerformanceFactory extends EFactory {
    /**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    PerformanceFactory eINSTANCE = ucm.performance.impl.PerformanceFactoryImpl.init();

    /**
	 * Returns a new object of class '<em>Workload</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Workload</em>'.
	 * @generated
	 */
    Workload createWorkload();

    /**
	 * Returns a new object of class '<em>Passive Resource</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Passive Resource</em>'.
	 * @generated
	 */
    PassiveResource createPassiveResource();

    /**
	 * Returns a new object of class '<em>External Operation</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>External Operation</em>'.
	 * @generated
	 */
    ExternalOperation createExternalOperation();

    /**
	 * Returns a new object of class '<em>Processing Resource</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Processing Resource</em>'.
	 * @generated
	 */
    ProcessingResource createProcessingResource();

    /**
	 * Returns a new object of class '<em>Demand</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Demand</em>'.
	 * @generated
	 */
    Demand createDemand();

    /**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
    PerformancePackage getPerformancePackage();

} //PerformanceFactory
