/**
 */
package fm.impl;

import fm.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FmFactoryImpl extends EFactoryImpl implements FmFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static FmFactory init() {
		try {
			FmFactory theFmFactory = (FmFactory)EPackage.Registry.INSTANCE.getEFactory(FmPackage.eNS_URI);
			if (theFmFactory != null) {
				return theFmFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new FmFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FmFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case FmPackage.FEATURE_DIAGRAM: return createFeatureDiagram();
			case FmPackage.FEATURE: return createFeature();
			case FmPackage.MANDATORY_FM_LINK: return createMandatoryFMLink();
			case FmPackage.OPTIONAL_FM_LINK: return createOptionalFMLink();
			case FmPackage.FEATURE_MODEL: return createFeatureModel();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureDiagram createFeatureDiagram() {
		FeatureDiagramImpl featureDiagram = new FeatureDiagramImpl();
		return featureDiagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Feature createFeature() {
		FeatureImpl feature = new FeatureImpl();
		return feature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MandatoryFMLink createMandatoryFMLink() {
		MandatoryFMLinkImpl mandatoryFMLink = new MandatoryFMLinkImpl();
		return mandatoryFMLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OptionalFMLink createOptionalFMLink() {
		OptionalFMLinkImpl optionalFMLink = new OptionalFMLinkImpl();
		return optionalFMLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureModel createFeatureModel() {
		FeatureModelImpl featureModel = new FeatureModelImpl();
		return featureModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FmPackage getFmPackage() {
		return (FmPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static FmPackage getPackage() {
		return FmPackage.eINSTANCE;
	}

} //FmFactoryImpl
