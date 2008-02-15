/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urn;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see urn.UrnPackage
 * @generated
 */
public interface UrnFactory extends EFactory {
    /**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    UrnFactory eINSTANCE = urn.impl.UrnFactoryImpl.init();

    /**
	 * Returns a new object of class '<em>UR Nspec</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>UR Nspec</em>'.
	 * @generated
	 */
    URNspec createURNspec();

    /**
	 * Returns a new object of class '<em>UR Nlink</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>UR Nlink</em>'.
	 * @generated
	 */
    URNlink createURNlink();

    /**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
    UrnPackage getUrnPackage();

} //UrnFactory
