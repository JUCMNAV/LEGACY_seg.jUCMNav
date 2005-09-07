/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import ucm.map.MapPackage;

import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see ucm.performance.PerformanceFactory
 * @model kind="package"
 * @generated
 */
public interface PerformancePackage extends EPackage{
    /**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "performance";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http:///ucm/performance.ecore";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "ucm.performance";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	PerformancePackage eINSTANCE = ucm.performance.impl.PerformancePackageImpl.init();

    /**
     * The meta object id for the '{@link ucm.performance.impl.TimestampImpl <em>Timestamp</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see ucm.performance.impl.TimestampImpl
     * @see ucm.performance.impl.PerformancePackageImpl#getTimestamp()
     * @generated
     */
	int TIMESTAMP = 0;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMESTAMP__URN_LINKS = MapPackage.PATH_NODE__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMESTAMP__ID = MapPackage.PATH_NODE__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMESTAMP__NAME = MapPackage.PATH_NODE__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMESTAMP__DESCRIPTION = MapPackage.PATH_NODE__DESCRIPTION;

    /**
     * The feature id for the '<em><b>X</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMESTAMP__X = MapPackage.PATH_NODE__X;

    /**
     * The feature id for the '<em><b>Y</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMESTAMP__Y = MapPackage.PATH_NODE__Y;

    /**
     * The feature id for the '<em><b>Path Graph</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMESTAMP__PATH_GRAPH = MapPackage.PATH_NODE__PATH_GRAPH;

    /**
     * The feature id for the '<em><b>Succ</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMESTAMP__SUCC = MapPackage.PATH_NODE__SUCC;

    /**
     * The feature id for the '<em><b>Pred</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMESTAMP__PRED = MapPackage.PATH_NODE__PRED;

    /**
     * The feature id for the '<em><b>Comp Ref</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMESTAMP__COMP_REF = MapPackage.PATH_NODE__COMP_REF;

    /**
     * The feature id for the '<em><b>Label</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMESTAMP__LABEL = MapPackage.PATH_NODE__LABEL;

    /**
     * The feature id for the '<em><b>Orientation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMESTAMP__ORIENTATION = MapPackage.PATH_NODE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Targets</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMESTAMP__TARGETS = MapPackage.PATH_NODE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Sources</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMESTAMP__SOURCES = MapPackage.PATH_NODE_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the the '<em>Timestamp</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int TIMESTAMP_FEATURE_COUNT = MapPackage.PATH_NODE_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '{@link ucm.performance.impl.ResponseTimeReqImpl <em>Response Time Req</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see ucm.performance.impl.ResponseTimeReqImpl
     * @see ucm.performance.impl.PerformancePackageImpl#getResponseTimeReq()
     * @generated
     */
	int RESPONSE_TIME_REQ = 1;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESPONSE_TIME_REQ__URN_LINKS = UrncorePackage.UC_MMODEL_ELEMENT__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESPONSE_TIME_REQ__ID = UrncorePackage.UC_MMODEL_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESPONSE_TIME_REQ__NAME = UrncorePackage.UC_MMODEL_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESPONSE_TIME_REQ__DESCRIPTION = UrncorePackage.UC_MMODEL_ELEMENT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Response Time</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESPONSE_TIME_REQ__RESPONSE_TIME = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Percentage</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESPONSE_TIME_REQ__PERCENTAGE = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Ucmspec</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESPONSE_TIME_REQ__UCMSPEC = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Ts1</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESPONSE_TIME_REQ__TS1 = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Ts2</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESPONSE_TIME_REQ__TS2 = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The number of structural features of the the '<em>Response Time Req</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESPONSE_TIME_REQ_FEATURE_COUNT = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The meta object id for the '{@link ucm.performance.impl.WorkloadImpl <em>Workload</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see ucm.performance.impl.WorkloadImpl
     * @see ucm.performance.impl.PerformancePackageImpl#getWorkload()
     * @generated
     */
	int WORKLOAD = 2;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WORKLOAD__URN_LINKS = UrncorePackage.UC_MMODEL_ELEMENT__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WORKLOAD__ID = UrncorePackage.UC_MMODEL_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WORKLOAD__NAME = UrncorePackage.UC_MMODEL_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WORKLOAD__DESCRIPTION = UrncorePackage.UC_MMODEL_ELEMENT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Closed</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WORKLOAD__CLOSED = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Arrival Pattern</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WORKLOAD__ARRIVAL_PATTERN = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Arrival Param1</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WORKLOAD__ARRIVAL_PARAM1 = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Arrival Param2</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WORKLOAD__ARRIVAL_PARAM2 = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>External Delay</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WORKLOAD__EXTERNAL_DELAY = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WORKLOAD__VALUE = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Coeff Var Seq</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WORKLOAD__COEFF_VAR_SEQ = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Population</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WORKLOAD__POPULATION = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Start Point</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WORKLOAD__START_POINT = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Resp Time</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WORKLOAD__RESP_TIME = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 9;

    /**
     * The number of structural features of the the '<em>Workload</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int WORKLOAD_FEATURE_COUNT = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 10;

    /**
     * The meta object id for the '{@link ucm.performance.impl.GeneralResourceImpl <em>General Resource</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see ucm.performance.impl.GeneralResourceImpl
     * @see ucm.performance.impl.PerformancePackageImpl#getGeneralResource()
     * @generated
     */
	int GENERAL_RESOURCE = 3;

    /**
     * The feature id for the '<em><b>Ucmspec</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GENERAL_RESOURCE__UCMSPEC = 0;

    /**
     * The feature id for the '<em><b>Demands</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GENERAL_RESOURCE__DEMANDS = 1;

    /**
     * The number of structural features of the the '<em>General Resource</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int GENERAL_RESOURCE_FEATURE_COUNT = 2;

    /**
     * The meta object id for the '{@link ucm.performance.impl.PerfMeasureImpl <em>Perf Measure</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see ucm.performance.impl.PerfMeasureImpl
     * @see ucm.performance.impl.PerformancePackageImpl#getPerfMeasure()
     * @generated
     */
	int PERF_MEASURE = 4;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PERF_MEASURE__URN_LINKS = UrncorePackage.UC_MMODEL_ELEMENT__URN_LINKS;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PERF_MEASURE__ID = UrncorePackage.UC_MMODEL_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PERF_MEASURE__NAME = UrncorePackage.UC_MMODEL_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PERF_MEASURE__DESCRIPTION = UrncorePackage.UC_MMODEL_ELEMENT__DESCRIPTION;

    /**
     * The feature id for the '<em><b>Measure</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PERF_MEASURE__MEASURE = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Ucmspec</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PERF_MEASURE__UCMSPEC = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Perf Values</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PERF_MEASURE__PERF_VALUES = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Duration</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PERF_MEASURE__DURATION = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the the '<em>Perf Measure</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PERF_MEASURE_FEATURE_COUNT = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '{@link ucm.performance.impl.PerfValueImpl <em>Perf Value</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see ucm.performance.impl.PerfValueImpl
     * @see ucm.performance.impl.PerformancePackageImpl#getPerfValue()
     * @generated
     */
	int PERF_VALUE = 5;

    /**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PERF_VALUE__VALUE = 0;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PERF_VALUE__KIND = 1;

    /**
     * The feature id for the '<em><b>Source</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PERF_VALUE__SOURCE = 2;

    /**
     * The feature id for the '<em><b>Percentile</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PERF_VALUE__PERCENTILE = 3;

    /**
     * The feature id for the '<em><b>Kth Moment</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PERF_VALUE__KTH_MOMENT = 4;

    /**
     * The feature id for the '<em><b>Perf Measure</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PERF_VALUE__PERF_MEASURE = 5;

    /**
     * The number of structural features of the the '<em>Perf Value</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PERF_VALUE_FEATURE_COUNT = 6;

    /**
     * The meta object id for the '{@link ucm.performance.impl.ActiveResourceImpl <em>Active Resource</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see ucm.performance.impl.ActiveResourceImpl
     * @see ucm.performance.impl.PerformancePackageImpl#getActiveResource()
     * @generated
     */
	int ACTIVE_RESOURCE = 6;

    /**
     * The feature id for the '<em><b>Ucmspec</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVE_RESOURCE__UCMSPEC = GENERAL_RESOURCE__UCMSPEC;

    /**
     * The feature id for the '<em><b>Demands</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVE_RESOURCE__DEMANDS = GENERAL_RESOURCE__DEMANDS;

    /**
     * The feature id for the '<em><b>Op Time</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVE_RESOURCE__OP_TIME = GENERAL_RESOURCE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the the '<em>Active Resource</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTIVE_RESOURCE_FEATURE_COUNT = GENERAL_RESOURCE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ucm.performance.impl.PassiveResourceImpl <em>Passive Resource</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see ucm.performance.impl.PassiveResourceImpl
     * @see ucm.performance.impl.PerformancePackageImpl#getPassiveResource()
     * @generated
     */
	int PASSIVE_RESOURCE = 7;

    /**
     * The feature id for the '<em><b>Ucmspec</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSIVE_RESOURCE__UCMSPEC = GENERAL_RESOURCE__UCMSPEC;

    /**
     * The feature id for the '<em><b>Demands</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSIVE_RESOURCE__DEMANDS = GENERAL_RESOURCE__DEMANDS;

    /**
     * The feature id for the '<em><b>Component</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSIVE_RESOURCE__COMPONENT = GENERAL_RESOURCE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the the '<em>Passive Resource</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PASSIVE_RESOURCE_FEATURE_COUNT = GENERAL_RESOURCE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ucm.performance.impl.ExternalOperationImpl <em>External Operation</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see ucm.performance.impl.ExternalOperationImpl
     * @see ucm.performance.impl.PerformancePackageImpl#getExternalOperation()
     * @generated
     */
	int EXTERNAL_OPERATION = 8;

    /**
     * The feature id for the '<em><b>Ucmspec</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EXTERNAL_OPERATION__UCMSPEC = ACTIVE_RESOURCE__UCMSPEC;

    /**
     * The feature id for the '<em><b>Demands</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EXTERNAL_OPERATION__DEMANDS = ACTIVE_RESOURCE__DEMANDS;

    /**
     * The feature id for the '<em><b>Op Time</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EXTERNAL_OPERATION__OP_TIME = ACTIVE_RESOURCE__OP_TIME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EXTERNAL_OPERATION__DESCRIPTION = ACTIVE_RESOURCE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the the '<em>External Operation</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int EXTERNAL_OPERATION_FEATURE_COUNT = ACTIVE_RESOURCE_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '{@link ucm.performance.impl.ProcessingResourceImpl <em>Processing Resource</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see ucm.performance.impl.ProcessingResourceImpl
     * @see ucm.performance.impl.PerformancePackageImpl#getProcessingResource()
     * @generated
     */
	int PROCESSING_RESOURCE = 9;

    /**
     * The feature id for the '<em><b>Ucmspec</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROCESSING_RESOURCE__UCMSPEC = ACTIVE_RESOURCE__UCMSPEC;

    /**
     * The feature id for the '<em><b>Demands</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROCESSING_RESOURCE__DEMANDS = ACTIVE_RESOURCE__DEMANDS;

    /**
     * The feature id for the '<em><b>Op Time</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROCESSING_RESOURCE__OP_TIME = ACTIVE_RESOURCE__OP_TIME;

    /**
     * The feature id for the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROCESSING_RESOURCE__KIND = ACTIVE_RESOURCE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Components</b></em>' reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROCESSING_RESOURCE__COMPONENTS = ACTIVE_RESOURCE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the the '<em>Processing Resource</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int PROCESSING_RESOURCE_FEATURE_COUNT = ACTIVE_RESOURCE_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '{@link ucm.performance.impl.DemandImpl <em>Demand</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see ucm.performance.impl.DemandImpl
     * @see ucm.performance.impl.PerformancePackageImpl#getDemand()
     * @generated
     */
	int DEMAND = 10;

    /**
     * The feature id for the '<em><b>Quantity</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DEMAND__QUANTITY = 0;

    /**
     * The feature id for the '<em><b>Responsibility</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DEMAND__RESPONSIBILITY = 1;

    /**
     * The feature id for the '<em><b>Resource</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DEMAND__RESOURCE = 2;

    /**
     * The number of structural features of the the '<em>Demand</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DEMAND_FEATURE_COUNT = 3;

    /**
     * The meta object id for the '{@link ucm.performance.DeviceKind <em>Device Kind</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see ucm.performance.DeviceKind
     * @see ucm.performance.impl.PerformancePackageImpl#getDeviceKind()
     * @generated
     */
	int DEVICE_KIND = 11;

    /**
     * The meta object id for the '{@link ucm.performance.PerfValueKind <em>Perf Value Kind</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see ucm.performance.PerfValueKind
     * @see ucm.performance.impl.PerformancePackageImpl#getPerfValueKind()
     * @generated
     */
	int PERF_VALUE_KIND = 12;

    /**
     * The meta object id for the '{@link ucm.performance.PerfValueSource <em>Perf Value Source</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see ucm.performance.PerfValueSource
     * @see ucm.performance.impl.PerformancePackageImpl#getPerfValueSource()
     * @generated
     */
	int PERF_VALUE_SOURCE = 13;

    /**
     * The meta object id for the '{@link ucm.performance.ArrivalProcess <em>Arrival Process</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see ucm.performance.ArrivalProcess
     * @see ucm.performance.impl.PerformancePackageImpl#getArrivalProcess()
     * @generated
     */
	int ARRIVAL_PROCESS = 14;

    /**
     * The meta object id for the '{@link ucm.performance.PerfAttribute <em>Perf Attribute</em>}' enum.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see ucm.performance.PerfAttribute
     * @see ucm.performance.impl.PerformancePackageImpl#getPerfAttribute()
     * @generated
     */
	int PERF_ATTRIBUTE = 15;


    /**
     * Returns the meta object for class '{@link ucm.performance.Timestamp <em>Timestamp</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Timestamp</em>'.
     * @see ucm.performance.Timestamp
     * @generated
     */
	EClass getTimestamp();

    /**
     * Returns the meta object for the attribute '{@link ucm.performance.Timestamp#getOrientation <em>Orientation</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Orientation</em>'.
     * @see ucm.performance.Timestamp#getOrientation()
     * @see #getTimestamp()
     * @generated
     */
	EAttribute getTimestamp_Orientation();

    /**
     * Returns the meta object for the reference list '{@link ucm.performance.Timestamp#getTargets <em>Targets</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Targets</em>'.
     * @see ucm.performance.Timestamp#getTargets()
     * @see #getTimestamp()
     * @generated
     */
	EReference getTimestamp_Targets();

    /**
     * Returns the meta object for the reference list '{@link ucm.performance.Timestamp#getSources <em>Sources</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Sources</em>'.
     * @see ucm.performance.Timestamp#getSources()
     * @see #getTimestamp()
     * @generated
     */
	EReference getTimestamp_Sources();

    /**
     * Returns the meta object for class '{@link ucm.performance.ResponseTimeReq <em>Response Time Req</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Response Time Req</em>'.
     * @see ucm.performance.ResponseTimeReq
     * @generated
     */
	EClass getResponseTimeReq();

    /**
     * Returns the meta object for the attribute '{@link ucm.performance.ResponseTimeReq#getResponseTime <em>Response Time</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Response Time</em>'.
     * @see ucm.performance.ResponseTimeReq#getResponseTime()
     * @see #getResponseTimeReq()
     * @generated
     */
	EAttribute getResponseTimeReq_ResponseTime();

    /**
     * Returns the meta object for the attribute '{@link ucm.performance.ResponseTimeReq#getPercentage <em>Percentage</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Percentage</em>'.
     * @see ucm.performance.ResponseTimeReq#getPercentage()
     * @see #getResponseTimeReq()
     * @generated
     */
	EAttribute getResponseTimeReq_Percentage();

    /**
     * Returns the meta object for the container reference '{@link ucm.performance.ResponseTimeReq#getUcmspec <em>Ucmspec</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Ucmspec</em>'.
     * @see ucm.performance.ResponseTimeReq#getUcmspec()
     * @see #getResponseTimeReq()
     * @generated
     */
	EReference getResponseTimeReq_Ucmspec();

    /**
     * Returns the meta object for the reference '{@link ucm.performance.ResponseTimeReq#getTs1 <em>Ts1</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Ts1</em>'.
     * @see ucm.performance.ResponseTimeReq#getTs1()
     * @see #getResponseTimeReq()
     * @generated
     */
	EReference getResponseTimeReq_Ts1();

    /**
     * Returns the meta object for the reference '{@link ucm.performance.ResponseTimeReq#getTs2 <em>Ts2</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Ts2</em>'.
     * @see ucm.performance.ResponseTimeReq#getTs2()
     * @see #getResponseTimeReq()
     * @generated
     */
	EReference getResponseTimeReq_Ts2();

    /**
     * Returns the meta object for class '{@link ucm.performance.Workload <em>Workload</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Workload</em>'.
     * @see ucm.performance.Workload
     * @generated
     */
	EClass getWorkload();

    /**
     * Returns the meta object for the attribute '{@link ucm.performance.Workload#isClosed <em>Closed</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Closed</em>'.
     * @see ucm.performance.Workload#isClosed()
     * @see #getWorkload()
     * @generated
     */
	EAttribute getWorkload_Closed();

    /**
     * Returns the meta object for the attribute '{@link ucm.performance.Workload#getArrivalPattern <em>Arrival Pattern</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Arrival Pattern</em>'.
     * @see ucm.performance.Workload#getArrivalPattern()
     * @see #getWorkload()
     * @generated
     */
	EAttribute getWorkload_ArrivalPattern();

    /**
     * Returns the meta object for the attribute '{@link ucm.performance.Workload#getArrivalParam1 <em>Arrival Param1</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Arrival Param1</em>'.
     * @see ucm.performance.Workload#getArrivalParam1()
     * @see #getWorkload()
     * @generated
     */
	EAttribute getWorkload_ArrivalParam1();

    /**
     * Returns the meta object for the attribute '{@link ucm.performance.Workload#getArrivalParam2 <em>Arrival Param2</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Arrival Param2</em>'.
     * @see ucm.performance.Workload#getArrivalParam2()
     * @see #getWorkload()
     * @generated
     */
	EAttribute getWorkload_ArrivalParam2();

    /**
     * Returns the meta object for the attribute '{@link ucm.performance.Workload#getExternalDelay <em>External Delay</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>External Delay</em>'.
     * @see ucm.performance.Workload#getExternalDelay()
     * @see #getWorkload()
     * @generated
     */
	EAttribute getWorkload_ExternalDelay();

    /**
     * Returns the meta object for the attribute '{@link ucm.performance.Workload#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see ucm.performance.Workload#getValue()
     * @see #getWorkload()
     * @generated
     */
	EAttribute getWorkload_Value();

    /**
     * Returns the meta object for the attribute '{@link ucm.performance.Workload#getCoeffVarSeq <em>Coeff Var Seq</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Coeff Var Seq</em>'.
     * @see ucm.performance.Workload#getCoeffVarSeq()
     * @see #getWorkload()
     * @generated
     */
	EAttribute getWorkload_CoeffVarSeq();

    /**
     * Returns the meta object for the attribute '{@link ucm.performance.Workload#getPopulation <em>Population</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Population</em>'.
     * @see ucm.performance.Workload#getPopulation()
     * @see #getWorkload()
     * @generated
     */
	EAttribute getWorkload_Population();

    /**
     * Returns the meta object for the container reference '{@link ucm.performance.Workload#getStartPoint <em>Start Point</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Start Point</em>'.
     * @see ucm.performance.Workload#getStartPoint()
     * @see #getWorkload()
     * @generated
     */
	EReference getWorkload_StartPoint();

    /**
     * Returns the meta object for the reference list '{@link ucm.performance.Workload#getRespTime <em>Resp Time</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Resp Time</em>'.
     * @see ucm.performance.Workload#getRespTime()
     * @see #getWorkload()
     * @generated
     */
	EReference getWorkload_RespTime();

    /**
     * Returns the meta object for class '{@link ucm.performance.GeneralResource <em>General Resource</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>General Resource</em>'.
     * @see ucm.performance.GeneralResource
     * @generated
     */
	EClass getGeneralResource();

    /**
     * Returns the meta object for the container reference '{@link ucm.performance.GeneralResource#getUcmspec <em>Ucmspec</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Ucmspec</em>'.
     * @see ucm.performance.GeneralResource#getUcmspec()
     * @see #getGeneralResource()
     * @generated
     */
	EReference getGeneralResource_Ucmspec();

    /**
     * Returns the meta object for the reference list '{@link ucm.performance.GeneralResource#getDemands <em>Demands</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Demands</em>'.
     * @see ucm.performance.GeneralResource#getDemands()
     * @see #getGeneralResource()
     * @generated
     */
	EReference getGeneralResource_Demands();

    /**
     * Returns the meta object for class '{@link ucm.performance.PerfMeasure <em>Perf Measure</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Perf Measure</em>'.
     * @see ucm.performance.PerfMeasure
     * @generated
     */
	EClass getPerfMeasure();

    /**
     * Returns the meta object for the attribute '{@link ucm.performance.PerfMeasure#getMeasure <em>Measure</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Measure</em>'.
     * @see ucm.performance.PerfMeasure#getMeasure()
     * @see #getPerfMeasure()
     * @generated
     */
	EAttribute getPerfMeasure_Measure();

    /**
     * Returns the meta object for the container reference '{@link ucm.performance.PerfMeasure#getUcmspec <em>Ucmspec</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Ucmspec</em>'.
     * @see ucm.performance.PerfMeasure#getUcmspec()
     * @see #getPerfMeasure()
     * @generated
     */
	EReference getPerfMeasure_Ucmspec();

    /**
     * Returns the meta object for the containment reference list '{@link ucm.performance.PerfMeasure#getPerfValues <em>Perf Values</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Perf Values</em>'.
     * @see ucm.performance.PerfMeasure#getPerfValues()
     * @see #getPerfMeasure()
     * @generated
     */
	EReference getPerfMeasure_PerfValues();

    /**
     * Returns the meta object for the reference '{@link ucm.performance.PerfMeasure#getDuration <em>Duration</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Duration</em>'.
     * @see ucm.performance.PerfMeasure#getDuration()
     * @see #getPerfMeasure()
     * @generated
     */
	EReference getPerfMeasure_Duration();

    /**
     * Returns the meta object for class '{@link ucm.performance.PerfValue <em>Perf Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Perf Value</em>'.
     * @see ucm.performance.PerfValue
     * @generated
     */
	EClass getPerfValue();

    /**
     * Returns the meta object for the attribute '{@link ucm.performance.PerfValue#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see ucm.performance.PerfValue#getValue()
     * @see #getPerfValue()
     * @generated
     */
	EAttribute getPerfValue_Value();

    /**
     * Returns the meta object for the attribute '{@link ucm.performance.PerfValue#getKind <em>Kind</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Kind</em>'.
     * @see ucm.performance.PerfValue#getKind()
     * @see #getPerfValue()
     * @generated
     */
	EAttribute getPerfValue_Kind();

    /**
     * Returns the meta object for the attribute '{@link ucm.performance.PerfValue#getSource <em>Source</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Source</em>'.
     * @see ucm.performance.PerfValue#getSource()
     * @see #getPerfValue()
     * @generated
     */
	EAttribute getPerfValue_Source();

    /**
     * Returns the meta object for the attribute '{@link ucm.performance.PerfValue#getPercentile <em>Percentile</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Percentile</em>'.
     * @see ucm.performance.PerfValue#getPercentile()
     * @see #getPerfValue()
     * @generated
     */
	EAttribute getPerfValue_Percentile();

    /**
     * Returns the meta object for the attribute '{@link ucm.performance.PerfValue#getKthMoment <em>Kth Moment</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Kth Moment</em>'.
     * @see ucm.performance.PerfValue#getKthMoment()
     * @see #getPerfValue()
     * @generated
     */
	EAttribute getPerfValue_KthMoment();

    /**
     * Returns the meta object for the container reference '{@link ucm.performance.PerfValue#getPerfMeasure <em>Perf Measure</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Perf Measure</em>'.
     * @see ucm.performance.PerfValue#getPerfMeasure()
     * @see #getPerfValue()
     * @generated
     */
	EReference getPerfValue_PerfMeasure();

    /**
     * Returns the meta object for class '{@link ucm.performance.ActiveResource <em>Active Resource</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Active Resource</em>'.
     * @see ucm.performance.ActiveResource
     * @generated
     */
	EClass getActiveResource();

    /**
     * Returns the meta object for the attribute '{@link ucm.performance.ActiveResource#getOpTime <em>Op Time</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Op Time</em>'.
     * @see ucm.performance.ActiveResource#getOpTime()
     * @see #getActiveResource()
     * @generated
     */
	EAttribute getActiveResource_OpTime();

    /**
     * Returns the meta object for class '{@link ucm.performance.PassiveResource <em>Passive Resource</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Passive Resource</em>'.
     * @see ucm.performance.PassiveResource
     * @generated
     */
	EClass getPassiveResource();

    /**
     * Returns the meta object for the reference '{@link ucm.performance.PassiveResource#getComponent <em>Component</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Component</em>'.
     * @see ucm.performance.PassiveResource#getComponent()
     * @see #getPassiveResource()
     * @generated
     */
	EReference getPassiveResource_Component();

    /**
     * Returns the meta object for class '{@link ucm.performance.ExternalOperation <em>External Operation</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>External Operation</em>'.
     * @see ucm.performance.ExternalOperation
     * @generated
     */
	EClass getExternalOperation();

    /**
     * Returns the meta object for the attribute '{@link ucm.performance.ExternalOperation#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see ucm.performance.ExternalOperation#getDescription()
     * @see #getExternalOperation()
     * @generated
     */
	EAttribute getExternalOperation_Description();

    /**
     * Returns the meta object for class '{@link ucm.performance.ProcessingResource <em>Processing Resource</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Processing Resource</em>'.
     * @see ucm.performance.ProcessingResource
     * @generated
     */
	EClass getProcessingResource();

    /**
     * Returns the meta object for the attribute '{@link ucm.performance.ProcessingResource#getKind <em>Kind</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Kind</em>'.
     * @see ucm.performance.ProcessingResource#getKind()
     * @see #getProcessingResource()
     * @generated
     */
	EAttribute getProcessingResource_Kind();

    /**
     * Returns the meta object for the reference list '{@link ucm.performance.ProcessingResource#getComponents <em>Components</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference list '<em>Components</em>'.
     * @see ucm.performance.ProcessingResource#getComponents()
     * @see #getProcessingResource()
     * @generated
     */
	EReference getProcessingResource_Components();

    /**
     * Returns the meta object for class '{@link ucm.performance.Demand <em>Demand</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Demand</em>'.
     * @see ucm.performance.Demand
     * @generated
     */
	EClass getDemand();

    /**
     * Returns the meta object for the attribute '{@link ucm.performance.Demand#getQuantity <em>Quantity</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Quantity</em>'.
     * @see ucm.performance.Demand#getQuantity()
     * @see #getDemand()
     * @generated
     */
	EAttribute getDemand_Quantity();

    /**
     * Returns the meta object for the container reference '{@link ucm.performance.Demand#getResponsibility <em>Responsibility</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Responsibility</em>'.
     * @see ucm.performance.Demand#getResponsibility()
     * @see #getDemand()
     * @generated
     */
	EReference getDemand_Responsibility();

    /**
     * Returns the meta object for the reference '{@link ucm.performance.Demand#getResource <em>Resource</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Resource</em>'.
     * @see ucm.performance.Demand#getResource()
     * @see #getDemand()
     * @generated
     */
	EReference getDemand_Resource();

    /**
     * Returns the meta object for enum '{@link ucm.performance.DeviceKind <em>Device Kind</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Device Kind</em>'.
     * @see ucm.performance.DeviceKind
     * @generated
     */
	EEnum getDeviceKind();

    /**
     * Returns the meta object for enum '{@link ucm.performance.PerfValueKind <em>Perf Value Kind</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Perf Value Kind</em>'.
     * @see ucm.performance.PerfValueKind
     * @generated
     */
	EEnum getPerfValueKind();

    /**
     * Returns the meta object for enum '{@link ucm.performance.PerfValueSource <em>Perf Value Source</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Perf Value Source</em>'.
     * @see ucm.performance.PerfValueSource
     * @generated
     */
	EEnum getPerfValueSource();

    /**
     * Returns the meta object for enum '{@link ucm.performance.ArrivalProcess <em>Arrival Process</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Arrival Process</em>'.
     * @see ucm.performance.ArrivalProcess
     * @generated
     */
	EEnum getArrivalProcess();

    /**
     * Returns the meta object for enum '{@link ucm.performance.PerfAttribute <em>Perf Attribute</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Perf Attribute</em>'.
     * @see ucm.performance.PerfAttribute
     * @generated
     */
	EEnum getPerfAttribute();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	PerformanceFactory getPerformanceFactory();

} //PerformancePackage
