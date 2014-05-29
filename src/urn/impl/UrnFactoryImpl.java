/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urn.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import urn.URNlink;
import urn.URNspec;
import urn.UrnFactory;
import urn.UrnPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class UrnFactoryImpl extends EFactoryImpl implements UrnFactory {
    /**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static UrnFactory init() {
		try {
			UrnFactory theUrnFactory = (UrnFactory)EPackage.Registry.INSTANCE.getEFactory(UrnPackage.eNS_URI);
			if (theUrnFactory != null) {
				return theUrnFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new UrnFactoryImpl();
	}

    /**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public UrnFactoryImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case UrnPackage.UR_NSPEC: return createURNspec();
			case UrnPackage.UR_NLINK: return createURNlink();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public URNspec createURNspec() {
		URNspecImpl urNspec = new URNspecImpl();
		return urNspec;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public URNlink createURNlink() {
		URNlinkImpl urNlink = new URNlinkImpl();
		return urNlink;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public UrnPackage getUrnPackage() {
		return (UrnPackage)getEPackage();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
    public static UrnPackage getPackage() {
		return UrnPackage.eINSTANCE;
	}

} //UrnFactoryImpl
