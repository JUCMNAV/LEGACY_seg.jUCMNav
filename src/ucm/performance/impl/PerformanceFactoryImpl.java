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

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import ucm.performance.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PerformanceFactoryImpl extends EFactoryImpl implements PerformanceFactory {
    /**
     * Creates and instance of the factory.
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
            case PerformancePackage.TIMESTAMP: return createTimestamp();
            case PerformancePackage.RESPONSE_TIME_REQ: return createResponseTimeReq();
            case PerformancePackage.OPEN_WORKLOAD: return createOpenWorkload();
            case PerformancePackage.CLOSED_WORKLOAD: return createClosedWorkload();
            case PerformancePackage.GENERAL_RESOURCE: return createGeneralResource();
            case PerformancePackage.PERF_MEASURE: return createPerfMeasure();
            case PerformancePackage.PERF_VALUE: return createPerfValue();
            case PerformancePackage.ACTIVE_RESOURCE: return createActiveResource();
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
            case PerformancePackage.DEVICE_KIND: {
                DeviceKind result = DeviceKind.get(initialValue);
                if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
                return result;
            }
            case PerformancePackage.PERF_VALUE_KIND: {
                PerfValueKind result = PerfValueKind.get(initialValue);
                if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
                return result;
            }
            case PerformancePackage.PERF_VALUE_SOURCE: {
                PerfValueSource result = PerfValueSource.get(initialValue);
                if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
                return result;
            }
            case PerformancePackage.ARRIVAL_PROCESS: {
                ArrivalProcess result = ArrivalProcess.get(initialValue);
                if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
                return result;
            }
            case PerformancePackage.PERF_ATTRIBUTE: {
                PerfAttribute result = PerfAttribute.get(initialValue);
                if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
                return result;
            }
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
                return instanceValue == null ? null : instanceValue.toString();
            case PerformancePackage.PERF_VALUE_KIND:
                return instanceValue == null ? null : instanceValue.toString();
            case PerformancePackage.PERF_VALUE_SOURCE:
                return instanceValue == null ? null : instanceValue.toString();
            case PerformancePackage.ARRIVAL_PROCESS:
                return instanceValue == null ? null : instanceValue.toString();
            case PerformancePackage.PERF_ATTRIBUTE:
                return instanceValue == null ? null : instanceValue.toString();
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Timestamp createTimestamp() {
        TimestampImpl timestamp = new TimestampImpl();
        return timestamp;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ResponseTimeReq createResponseTimeReq() {
        ResponseTimeReqImpl responseTimeReq = new ResponseTimeReqImpl();
        return responseTimeReq;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OpenWorkload createOpenWorkload() {
        OpenWorkloadImpl openWorkload = new OpenWorkloadImpl();
        return openWorkload;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ClosedWorkload createClosedWorkload() {
        ClosedWorkloadImpl closedWorkload = new ClosedWorkloadImpl();
        return closedWorkload;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GeneralResource createGeneralResource() {
        GeneralResourceImpl generalResource = new GeneralResourceImpl();
        return generalResource;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PerfMeasure createPerfMeasure() {
        PerfMeasureImpl perfMeasure = new PerfMeasureImpl();
        return perfMeasure;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PerfValue createPerfValue() {
        PerfValueImpl perfValue = new PerfValueImpl();
        return perfValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ActiveResource createActiveResource() {
        ActiveResourceImpl activeResource = new ActiveResourceImpl();
        return activeResource;
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
