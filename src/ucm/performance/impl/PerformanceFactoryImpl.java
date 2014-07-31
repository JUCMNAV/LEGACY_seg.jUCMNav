/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import ucm.performance.ArrivalProcess;
import ucm.performance.Demand;
import ucm.performance.DeviceKind;
import ucm.performance.ExternalOperation;
import ucm.performance.PassiveResource;
import ucm.performance.PerformanceFactory;
import ucm.performance.PerformancePackage;
import ucm.performance.ProcessingResource;
import ucm.performance.TimeUnit;
import ucm.performance.Workload;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PerformanceFactoryImpl extends EFactoryImpl implements PerformanceFactory {
    /**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static PerformanceFactory init() {
		try {
			PerformanceFactory thePerformanceFactory = (PerformanceFactory)EPackage.Registry.INSTANCE.getEFactory(PerformancePackage.eNS_URI);
			if (thePerformanceFactory != null) {
				return thePerformanceFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new PerformanceFactoryImpl();
	}

    /**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PerformanceFactoryImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case PerformancePackage.WORKLOAD: return createWorkload();
			case PerformancePackage.PASSIVE_RESOURCE: return createPassiveResource();
			case PerformancePackage.EXTERNAL_OPERATION: return createExternalOperation();
			case PerformancePackage.PROCESSING_RESOURCE: return createProcessingResource();
			case PerformancePackage.DEMAND: return createDemand();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case PerformancePackage.DEVICE_KIND:
				return createDeviceKindFromString(eDataType, initialValue);
			case PerformancePackage.ARRIVAL_PROCESS:
				return createArrivalProcessFromString(eDataType, initialValue);
			case PerformancePackage.TIME_UNIT:
				return createTimeUnitFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case PerformancePackage.DEVICE_KIND:
				return convertDeviceKindToString(eDataType, instanceValue);
			case PerformancePackage.ARRIVAL_PROCESS:
				return convertArrivalProcessToString(eDataType, instanceValue);
			case PerformancePackage.TIME_UNIT:
				return convertTimeUnitToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Workload createWorkload() {
		WorkloadImpl workload = new WorkloadImpl();
		return workload;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PassiveResource createPassiveResource() {
		PassiveResourceImpl passiveResource = new PassiveResourceImpl();
		return passiveResource;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ExternalOperation createExternalOperation() {
		ExternalOperationImpl externalOperation = new ExternalOperationImpl();
		return externalOperation;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ProcessingResource createProcessingResource() {
		ProcessingResourceImpl processingResource = new ProcessingResourceImpl();
		return processingResource;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Demand createDemand() {
		DemandImpl demand = new DemandImpl();
		return demand;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeviceKind createDeviceKindFromString(EDataType eDataType, String initialValue) {
		DeviceKind result = DeviceKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDeviceKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArrivalProcess createArrivalProcessFromString(EDataType eDataType, String initialValue) {
		ArrivalProcess result = ArrivalProcess.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertArrivalProcessToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public TimeUnit createTimeUnitFromString(EDataType eDataType, String initialValue) {
		TimeUnit result = TimeUnit.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertTimeUnitToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PerformancePackage getPerformancePackage() {
		return (PerformancePackage)getEPackage();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
    public static PerformancePackage getPackage() {
		return PerformancePackage.eINSTANCE;
	}

} //PerformanceFactoryImpl
