/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class GrlFactoryImpl extends EFactoryImpl implements GrlFactory {
    /**
     * Creates and instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GrlFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case GrlPackage.GR_LSPEC: return createGRLspec();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GRLspec createGRLspec() {
        GRLspecImpl grLspec = new GRLspecImpl();
        return grLspec;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GrlPackage getGrlPackage() {
        return (GrlPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    public static GrlPackage getPackage() {
        return GrlPackage.eINSTANCE;
    }

} //GrlFactoryImpl
