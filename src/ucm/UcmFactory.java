/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see ucm.UcmPackage
 * @generated
 */
public interface UcmFactory extends EFactory {
    /**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    UcmFactory eINSTANCE = ucm.impl.UcmFactoryImpl.init();

    /**
	 * Returns a new object of class '<em>UC Mspec</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>UC Mspec</em>'.
	 * @generated
	 */
    UCMspec createUCMspec();

    /**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
    UcmPackage getUcmPackage();

} //UcmFactory
