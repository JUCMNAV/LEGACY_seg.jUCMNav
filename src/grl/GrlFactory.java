/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see grl.GrlPackage
 * @generated
 */
public interface GrlFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    GrlFactory eINSTANCE = new grl.impl.GrlFactoryImpl();

    /**
     * Returns a new object of class '<em>GR Lspec</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>GR Lspec</em>'.
     * @generated
     */
    GRLspec createGRLspec();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    GrlPackage getGrlPackage();

} //GrlFactory
