/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import ucm.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UcmFactoryImpl extends EFactoryImpl implements UcmFactory {
    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public UcmFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case UcmPackage.UC_MSPEC: return createUCMspec();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public UCMspec createUCMspec() {
        UCMspecImpl ucMspec = new UCMspecImpl();
        return ucMspec;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public UcmPackage getUcmPackage() {
        return (UcmPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
	public static UcmPackage getPackage() {
        return UcmPackage.eINSTANCE;
    }

} //UcmFactoryImpl
