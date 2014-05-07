/**
 */
package org.etsi.mts.tdl;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.etsi.mts.tdl.TdlPackage
 * @generated
 */
public interface TdlFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TdlFactory eINSTANCE = org.etsi.mts.tdl.impl.TdlFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Comment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Comment</em>'.
	 * @generated
	 */
	Comment createComment();

	/**
	 * Returns a new object of class '<em>Annotation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Annotation</em>'.
	 * @generated
	 */
	Annotation createAnnotation();

	/**
	 * Returns a new object of class '<em>Annotation Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Annotation Type</em>'.
	 * @generated
	 */
	AnnotationType createAnnotationType();

	/**
	 * Returns a new object of class '<em>Package</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Package</em>'.
	 * @generated
	 */
	Package createPackage();

	/**
	 * Returns a new object of class '<em>Element Import</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Element Import</em>'.
	 * @generated
	 */
	ElementImport createElementImport();

	/**
	 * Returns a new object of class '<em>Test Objective</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Objective</em>'.
	 * @generated
	 */
	TestObjective createTestObjective();

	/**
	 * Returns a new object of class '<em>Data Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Instance</em>'.
	 * @generated
	 */
	DataInstance createDataInstance();

	/**
	 * Returns a new object of class '<em>Data Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Set</em>'.
	 * @generated
	 */
	DataSet createDataSet();

	/**
	 * Returns a new object of class '<em>Data Resource Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Resource Mapping</em>'.
	 * @generated
	 */
	DataResourceMapping createDataResourceMapping();

	/**
	 * Returns a new object of class '<em>Data Element Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Element Mapping</em>'.
	 * @generated
	 */
	DataElementMapping createDataElementMapping();

	/**
	 * Returns a new object of class '<em>Data Proxy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Proxy</em>'.
	 * @generated
	 */
	DataProxy createDataProxy();

	/**
	 * Returns a new object of class '<em>Timer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Timer</em>'.
	 * @generated
	 */
	Timer createTimer();

	/**
	 * Returns a new object of class '<em>Component Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component Type</em>'.
	 * @generated
	 */
	ComponentType createComponentType();

	/**
	 * Returns a new object of class '<em>Gate Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Gate Type</em>'.
	 * @generated
	 */
	GateType createGateType();

	/**
	 * Returns a new object of class '<em>Time Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Time Constraint</em>'.
	 * @generated
	 */
	TimeConstraint createTimeConstraint();

	/**
	 * Returns a new object of class '<em>Timer Start</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Timer Start</em>'.
	 * @generated
	 */
	TimerStart createTimerStart();

	/**
	 * Returns a new object of class '<em>Time</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Time</em>'.
	 * @generated
	 */
	Time createTime();

	/**
	 * Returns a new object of class '<em>Time Unit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Time Unit</em>'.
	 * @generated
	 */
	TimeUnit createTimeUnit();

	/**
	 * Returns a new object of class '<em>Timer Stop</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Timer Stop</em>'.
	 * @generated
	 */
	TimerStop createTimerStop();

	/**
	 * Returns a new object of class '<em>Time Out</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Time Out</em>'.
	 * @generated
	 */
	TimeOut createTimeOut();

	/**
	 * Returns a new object of class '<em>Gate Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Gate Instance</em>'.
	 * @generated
	 */
	GateInstance createGateInstance();

	/**
	 * Returns a new object of class '<em>Component Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component Instance</em>'.
	 * @generated
	 */
	ComponentInstance createComponentInstance();

	/**
	 * Returns a new object of class '<em>Wait</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Wait</em>'.
	 * @generated
	 */
	Wait createWait();

	/**
	 * Returns a new object of class '<em>Quiescence</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Quiescence</em>'.
	 * @generated
	 */
	Quiescence createQuiescence();

	/**
	 * Returns a new object of class '<em>Test Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Configuration</em>'.
	 * @generated
	 */
	TestConfiguration createTestConfiguration();

	/**
	 * Returns a new object of class '<em>Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connection</em>'.
	 * @generated
	 */
	Connection createConnection();

	/**
	 * Returns a new object of class '<em>Test Description</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Description</em>'.
	 * @generated
	 */
	TestDescription createTestDescription();

	/**
	 * Returns a new object of class '<em>Compound Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Compound Behaviour</em>'.
	 * @generated
	 */
	CompoundBehaviour createCompoundBehaviour();

	/**
	 * Returns a new object of class '<em>Periodic Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Periodic Behaviour</em>'.
	 * @generated
	 */
	PeriodicBehaviour createPeriodicBehaviour();

	/**
	 * Returns a new object of class '<em>Block</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Block</em>'.
	 * @generated
	 */
	Block createBlock();

	/**
	 * Returns a new object of class '<em>Alternative Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Alternative Behaviour</em>'.
	 * @generated
	 */
	AlternativeBehaviour createAlternativeBehaviour();

	/**
	 * Returns a new object of class '<em>Parallel Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Parallel Behaviour</em>'.
	 * @generated
	 */
	ParallelBehaviour createParallelBehaviour();

	/**
	 * Returns a new object of class '<em>Bounded Loop Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Bounded Loop Behaviour</em>'.
	 * @generated
	 */
	BoundedLoopBehaviour createBoundedLoopBehaviour();

	/**
	 * Returns a new object of class '<em>Unbounded Loop Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Unbounded Loop Behaviour</em>'.
	 * @generated
	 */
	UnboundedLoopBehaviour createUnboundedLoopBehaviour();

	/**
	 * Returns a new object of class '<em>Conditional Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Conditional Behaviour</em>'.
	 * @generated
	 */
	ConditionalBehaviour createConditionalBehaviour();

	/**
	 * Returns a new object of class '<em>Stop</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stop</em>'.
	 * @generated
	 */
	Stop createStop();

	/**
	 * Returns a new object of class '<em>Test Description Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Description Reference</em>'.
	 * @generated
	 */
	TestDescriptionReference createTestDescriptionReference();

	/**
	 * Returns a new object of class '<em>Verdict Assignment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Verdict Assignment</em>'.
	 * @generated
	 */
	VerdictAssignment createVerdictAssignment();

	/**
	 * Returns a new object of class '<em>Verdict Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Verdict Type</em>'.
	 * @generated
	 */
	VerdictType createVerdictType();

	/**
	 * Returns a new object of class '<em>Action Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Action Reference</em>'.
	 * @generated
	 */
	ActionReference createActionReference();

	/**
	 * Returns a new object of class '<em>Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Action</em>'.
	 * @generated
	 */
	Action createAction();

	/**
	 * Returns a new object of class '<em>Interaction</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Interaction</em>'.
	 * @generated
	 */
	Interaction createInteraction();

	/**
	 * Returns a new object of class '<em>Optional Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Optional Behaviour</em>'.
	 * @generated
	 */
	OptionalBehaviour createOptionalBehaviour();

	/**
	 * Returns a new object of class '<em>Default Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Default Behaviour</em>'.
	 * @generated
	 */
	DefaultBehaviour createDefaultBehaviour();

	/**
	 * Returns a new object of class '<em>Interrupt Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Interrupt Behaviour</em>'.
	 * @generated
	 */
	InterruptBehaviour createInterruptBehaviour();

	/**
	 * Returns a new object of class '<em>Break</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Break</em>'.
	 * @generated
	 */
	Break createBreak();

	/**
	 * Returns a new object of class '<em>Data Instance Argument Specification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Instance Argument Specification</em>'.
	 * @generated
	 */
	DataInstanceArgumentSpecification createDataInstanceArgumentSpecification();

	/**
	 * Returns a new object of class '<em>Data Proxy Argument Specification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Proxy Argument Specification</em>'.
	 * @generated
	 */
	DataProxyArgumentSpecification createDataProxyArgumentSpecification();

	/**
	 * Returns a new object of class '<em>Data Set Argument Specification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Set Argument Specification</em>'.
	 * @generated
	 */
	DataSetArgumentSpecification createDataSetArgumentSpecification();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TdlPackage getTdlPackage();

} //TdlFactory
