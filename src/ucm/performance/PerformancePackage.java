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
 * @generated
 */
public interface PerformancePackage extends EPackage {
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
	 * The meta object id for the '{@link ucm.performance.impl.DeviceImpl <em>Device</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ucm.performance.impl.DeviceImpl
	 * @see ucm.performance.impl.PerformancePackageImpl#getDevice()
	 * @generated
	 */
	int DEVICE = 0;

	/**
	 * The feature id for the '<em><b>Urn Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEVICE__URN_LINKS = UrncorePackage.UC_MMODEL_ELEMENT__URN_LINKS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEVICE__ID = UrncorePackage.UC_MMODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEVICE__NAME = UrncorePackage.UC_MMODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEVICE__DESCRIPTION = UrncorePackage.UC_MMODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Device Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEVICE__DEVICE_KIND = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Optime</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEVICE__OPTIME = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the the '<em>Device</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEVICE_FEATURE_COUNT = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link ucm.performance.impl.TimestampImpl <em>Timestamp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ucm.performance.impl.TimestampImpl
	 * @see ucm.performance.impl.PerformancePackageImpl#getTimestamp()
	 * @generated
	 */
	int TIMESTAMP = 1;

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
	 * The feature id for the '<em><b>Label X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMESTAMP__LABEL_X = MapPackage.PATH_NODE__LABEL_X;

	/**
	 * The feature id for the '<em><b>Label Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMESTAMP__LABEL_Y = MapPackage.PATH_NODE__LABEL_Y;

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
	 * The feature id for the '<em><b>Orientation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMESTAMP__ORIENTATION = MapPackage.PATH_NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the the '<em>Timestamp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMESTAMP_FEATURE_COUNT = MapPackage.PATH_NODE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link ucm.performance.impl.ResponseTimeReqImpl <em>Response Time Req</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ucm.performance.impl.ResponseTimeReqImpl
	 * @see ucm.performance.impl.PerformancePackageImpl#getResponseTimeReq()
	 * @generated
	 */
	int RESPONSE_TIME_REQ = 2;

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
	 * The feature id for the '<em><b>Ts1</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSE_TIME_REQ__TS1 = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Ts2</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSE_TIME_REQ__TS2 = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the the '<em>Response Time Req</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSE_TIME_REQ_FEATURE_COUNT = UrncorePackage.UC_MMODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link ucm.performance.DeviceKind <em>Device Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see ucm.performance.DeviceKind
	 * @see ucm.performance.impl.PerformancePackageImpl#getDeviceKind()
	 * @generated
	 */
	int DEVICE_KIND = 3;


	/**
	 * Returns the meta object for class '{@link ucm.performance.Device <em>Device</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Device</em>'.
	 * @see ucm.performance.Device
	 * @generated
	 */
	EClass getDevice();

	/**
	 * Returns the meta object for the attribute '{@link ucm.performance.Device#getDeviceKind <em>Device Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Device Kind</em>'.
	 * @see ucm.performance.Device#getDeviceKind()
	 * @see #getDevice()
	 * @generated
	 */
	EAttribute getDevice_DeviceKind();

	/**
	 * Returns the meta object for the attribute '{@link ucm.performance.Device#getOptime <em>Optime</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Optime</em>'.
	 * @see ucm.performance.Device#getOptime()
	 * @see #getDevice()
	 * @generated
	 */
	EAttribute getDevice_Optime();

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
	 * Returns the meta object for enum '{@link ucm.performance.DeviceKind <em>Device Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Device Kind</em>'.
	 * @see ucm.performance.DeviceKind
	 * @generated
	 */
	EEnum getDeviceKind();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	PerformanceFactory getPerformanceFactory();

} //PerformancePackage
