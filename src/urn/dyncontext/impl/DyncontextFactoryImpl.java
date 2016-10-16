/**
 */
package urn.dyncontext.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import urn.dyncontext.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DyncontextFactoryImpl extends EFactoryImpl implements DyncontextFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DyncontextFactory init() {
		try {
			DyncontextFactory theDyncontextFactory = (DyncontextFactory)EPackage.Registry.INSTANCE.getEFactory(DyncontextPackage.eNS_URI);
			if (theDyncontextFactory != null) {
				return theDyncontextFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DyncontextFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DyncontextFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case DyncontextPackage.QUADRATIC_CHANGE: return createQuadraticChange();
			case DyncontextPackage.TIMEPOINT_GROUP: return createTimepointGroup();
			case DyncontextPackage.TIMEPOINT: return createTimepoint();
			case DyncontextPackage.FORMULA_CHANGE: return createFormulaChange();
			case DyncontextPackage.LINEAR_CHANGE: return createLinearChange();
			case DyncontextPackage.ENUM_CHANGE: return createEnumChange();
			case DyncontextPackage.DYNAMIC_CONTEXT: return createDynamicContext();
			case DyncontextPackage.DYNAMIC_CONTEXT_GROUP: return createDynamicContextGroup();
			case DyncontextPackage.DEACTIVATION_CHANGE: return createDeactivationChange();
			case DyncontextPackage.CONSTANT_CHANGE: return createConstantChange();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QuadraticChange createQuadraticChange() {
		QuadraticChangeImpl quadraticChange = new QuadraticChangeImpl();
		return quadraticChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimepointGroup createTimepointGroup() {
		TimepointGroupImpl timepointGroup = new TimepointGroupImpl();
		return timepointGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Timepoint createTimepoint() {
		TimepointImpl timepoint = new TimepointImpl();
		return timepoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormulaChange createFormulaChange() {
		FormulaChangeImpl formulaChange = new FormulaChangeImpl();
		return formulaChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinearChange createLinearChange() {
		LinearChangeImpl linearChange = new LinearChangeImpl();
		return linearChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumChange createEnumChange() {
		EnumChangeImpl enumChange = new EnumChangeImpl();
		return enumChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DynamicContext createDynamicContext() {
		DynamicContextImpl dynamicContext = new DynamicContextImpl();
		return dynamicContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DynamicContextGroup createDynamicContextGroup() {
		DynamicContextGroupImpl dynamicContextGroup = new DynamicContextGroupImpl();
		return dynamicContextGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeactivationChange createDeactivationChange() {
		DeactivationChangeImpl deactivationChange = new DeactivationChangeImpl();
		return deactivationChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstantChange createConstantChange() {
		ConstantChangeImpl constantChange = new ConstantChangeImpl();
		return constantChange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DyncontextPackage getDyncontextPackage() {
		return (DyncontextPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static DyncontextPackage getPackage() {
		return DyncontextPackage.eINSTANCE;
	}

} //DyncontextFactoryImpl
