/**
 */
package core;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see core.CorePackage
 * @generated
 */
public interface CoreFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CoreFactory eINSTANCE = core.impl.CoreFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>CORE Concern</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>CORE Concern</em>'.
	 * @generated
	 */
	COREConcern createCOREConcern();

	/**
	 * Returns a new object of class '<em>CORE Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>CORE Mapping</em>'.
	 * @generated
	 */
	COREMapping createCOREMapping();

	/**
	 * Returns a new object of class '<em>CORE Interface</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>CORE Interface</em>'.
	 * @generated
	 */
	COREInterface createCOREInterface();

	/**
	 * Returns a new object of class '<em>CORE Reuse</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>CORE Reuse</em>'.
	 * @generated
	 */
	COREReuse createCOREReuse();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	CorePackage getCorePackage();

} //CoreFactory
