/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import ucm.UCMspec;
import ucm.UcmFactory;
import ucm.UcmPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UcmFactoryImpl extends EFactoryImpl implements UcmFactory {
    /**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UcmFactory init() {
		try {
			UcmFactory theUcmFactory = (UcmFactory)EPackage.Registry.INSTANCE.getEFactory(UcmPackage.eNS_URI);
			if (theUcmFactory != null) {
				return theUcmFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new UcmFactoryImpl();
	}

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
