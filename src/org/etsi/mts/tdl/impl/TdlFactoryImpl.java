/**
 */
package org.etsi.mts.tdl.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.etsi.mts.tdl.Action;
import org.etsi.mts.tdl.ActionReference;
import org.etsi.mts.tdl.AlternativeBehaviour;
import org.etsi.mts.tdl.Annotation;
import org.etsi.mts.tdl.AnnotationType;
import org.etsi.mts.tdl.Block;
import org.etsi.mts.tdl.BoundedLoopBehaviour;
import org.etsi.mts.tdl.Break;
import org.etsi.mts.tdl.Comment;
import org.etsi.mts.tdl.ComponentInstance;
import org.etsi.mts.tdl.ComponentInstanceRole;
import org.etsi.mts.tdl.ComponentType;
import org.etsi.mts.tdl.CompoundBehaviour;
import org.etsi.mts.tdl.ConditionalBehaviour;
import org.etsi.mts.tdl.Connection;
import org.etsi.mts.tdl.DataElementMapping;
import org.etsi.mts.tdl.DataInstance;
import org.etsi.mts.tdl.DataInstanceArgumentSpecification;
import org.etsi.mts.tdl.DataProxy;
import org.etsi.mts.tdl.DataProxyArgumentSpecification;
import org.etsi.mts.tdl.DataResourceMapping;
import org.etsi.mts.tdl.DataSet;
import org.etsi.mts.tdl.DataSetArgumentSpecification;
import org.etsi.mts.tdl.DefaultBehaviour;
import org.etsi.mts.tdl.ElementImport;
import org.etsi.mts.tdl.GateInstance;
import org.etsi.mts.tdl.GateType;
import org.etsi.mts.tdl.Interaction;
import org.etsi.mts.tdl.InterruptBehaviour;
import org.etsi.mts.tdl.OptionalBehaviour;
import org.etsi.mts.tdl.ParallelBehaviour;
import org.etsi.mts.tdl.PeriodicBehaviour;
import org.etsi.mts.tdl.Quiescence;
import org.etsi.mts.tdl.Stop;
import org.etsi.mts.tdl.TdlFactory;
import org.etsi.mts.tdl.TdlPackage;
import org.etsi.mts.tdl.TestConfiguration;
import org.etsi.mts.tdl.TestDescription;
import org.etsi.mts.tdl.TestDescriptionReference;
import org.etsi.mts.tdl.TestObjective;
import org.etsi.mts.tdl.Time;
import org.etsi.mts.tdl.TimeConstraint;
import org.etsi.mts.tdl.TimeOut;
import org.etsi.mts.tdl.TimeUnit;
import org.etsi.mts.tdl.Timer;
import org.etsi.mts.tdl.TimerStart;
import org.etsi.mts.tdl.TimerStop;
import org.etsi.mts.tdl.UnboundedLoopBehaviour;
import org.etsi.mts.tdl.VerdictAssignment;
import org.etsi.mts.tdl.VerdictType;
import org.etsi.mts.tdl.Wait;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TdlFactoryImpl extends EFactoryImpl implements TdlFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TdlFactory init() {
		try {
			TdlFactory theTdlFactory = (TdlFactory)EPackage.Registry.INSTANCE.getEFactory(TdlPackage.eNS_URI);
			if (theTdlFactory != null) {
				return theTdlFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TdlFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TdlFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case TdlPackage.COMMENT: return createComment();
			case TdlPackage.ANNOTATION: return createAnnotation();
			case TdlPackage.ANNOTATION_TYPE: return createAnnotationType();
			case TdlPackage.PACKAGE: return createPackage();
			case TdlPackage.ELEMENT_IMPORT: return createElementImport();
			case TdlPackage.TEST_OBJECTIVE: return createTestObjective();
			case TdlPackage.DATA_INSTANCE: return createDataInstance();
			case TdlPackage.DATA_SET: return createDataSet();
			case TdlPackage.DATA_RESOURCE_MAPPING: return createDataResourceMapping();
			case TdlPackage.DATA_ELEMENT_MAPPING: return createDataElementMapping();
			case TdlPackage.DATA_PROXY: return createDataProxy();
			case TdlPackage.TIMER: return createTimer();
			case TdlPackage.COMPONENT_TYPE: return createComponentType();
			case TdlPackage.GATE_TYPE: return createGateType();
			case TdlPackage.TIME_CONSTRAINT: return createTimeConstraint();
			case TdlPackage.TIMER_START: return createTimerStart();
			case TdlPackage.TIME: return createTime();
			case TdlPackage.TIME_UNIT: return createTimeUnit();
			case TdlPackage.TIMER_STOP: return createTimerStop();
			case TdlPackage.TIME_OUT: return createTimeOut();
			case TdlPackage.GATE_INSTANCE: return createGateInstance();
			case TdlPackage.COMPONENT_INSTANCE: return createComponentInstance();
			case TdlPackage.WAIT: return createWait();
			case TdlPackage.QUIESCENCE: return createQuiescence();
			case TdlPackage.TEST_CONFIGURATION: return createTestConfiguration();
			case TdlPackage.CONNECTION: return createConnection();
			case TdlPackage.TEST_DESCRIPTION: return createTestDescription();
			case TdlPackage.COMPOUND_BEHAVIOUR: return createCompoundBehaviour();
			case TdlPackage.PERIODIC_BEHAVIOUR: return createPeriodicBehaviour();
			case TdlPackage.BLOCK: return createBlock();
			case TdlPackage.ALTERNATIVE_BEHAVIOUR: return createAlternativeBehaviour();
			case TdlPackage.PARALLEL_BEHAVIOUR: return createParallelBehaviour();
			case TdlPackage.BOUNDED_LOOP_BEHAVIOUR: return createBoundedLoopBehaviour();
			case TdlPackage.UNBOUNDED_LOOP_BEHAVIOUR: return createUnboundedLoopBehaviour();
			case TdlPackage.CONDITIONAL_BEHAVIOUR: return createConditionalBehaviour();
			case TdlPackage.STOP: return createStop();
			case TdlPackage.TEST_DESCRIPTION_REFERENCE: return createTestDescriptionReference();
			case TdlPackage.VERDICT_ASSIGNMENT: return createVerdictAssignment();
			case TdlPackage.VERDICT_TYPE: return createVerdictType();
			case TdlPackage.ACTION_REFERENCE: return createActionReference();
			case TdlPackage.ACTION: return createAction();
			case TdlPackage.INTERACTION: return createInteraction();
			case TdlPackage.OPTIONAL_BEHAVIOUR: return createOptionalBehaviour();
			case TdlPackage.DEFAULT_BEHAVIOUR: return createDefaultBehaviour();
			case TdlPackage.INTERRUPT_BEHAVIOUR: return createInterruptBehaviour();
			case TdlPackage.BREAK: return createBreak();
			case TdlPackage.DATA_INSTANCE_ARGUMENT_SPECIFICATION: return createDataInstanceArgumentSpecification();
			case TdlPackage.DATA_PROXY_ARGUMENT_SPECIFICATION: return createDataProxyArgumentSpecification();
			case TdlPackage.DATA_SET_ARGUMENT_SPECIFICATION: return createDataSetArgumentSpecification();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case TdlPackage.COMPONENT_INSTANCE_ROLE:
				return createComponentInstanceRoleFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case TdlPackage.COMPONENT_INSTANCE_ROLE:
				return convertComponentInstanceRoleToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Comment createComment() {
		CommentImpl comment = new CommentImpl();
		return comment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Annotation createAnnotation() {
		AnnotationImpl annotation = new AnnotationImpl();
		return annotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AnnotationType createAnnotationType() {
		AnnotationTypeImpl annotationType = new AnnotationTypeImpl();
		return annotationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public org.etsi.mts.tdl.Package createPackage() {
		PackageImpl package_ = new PackageImpl();
		return package_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ElementImport createElementImport() {
		ElementImportImpl elementImport = new ElementImportImpl();
		return elementImport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestObjective createTestObjective() {
		TestObjectiveImpl testObjective = new TestObjectiveImpl();
		return testObjective;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataInstance createDataInstance() {
		DataInstanceImpl dataInstance = new DataInstanceImpl();
		return dataInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataSet createDataSet() {
		DataSetImpl dataSet = new DataSetImpl();
		return dataSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataResourceMapping createDataResourceMapping() {
		DataResourceMappingImpl dataResourceMapping = new DataResourceMappingImpl();
		return dataResourceMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataElementMapping createDataElementMapping() {
		DataElementMappingImpl dataElementMapping = new DataElementMappingImpl();
		return dataElementMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataProxy createDataProxy() {
		DataProxyImpl dataProxy = new DataProxyImpl();
		return dataProxy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Timer createTimer() {
		TimerImpl timer = new TimerImpl();
		return timer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentType createComponentType() {
		ComponentTypeImpl componentType = new ComponentTypeImpl();
		return componentType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GateType createGateType() {
		GateTypeImpl gateType = new GateTypeImpl();
		return gateType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeConstraint createTimeConstraint() {
		TimeConstraintImpl timeConstraint = new TimeConstraintImpl();
		return timeConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimerStart createTimerStart() {
		TimerStartImpl timerStart = new TimerStartImpl();
		return timerStart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Time createTime() {
		TimeImpl time = new TimeImpl();
		return time;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeUnit createTimeUnit() {
		TimeUnitImpl timeUnit = new TimeUnitImpl();
		return timeUnit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimerStop createTimerStop() {
		TimerStopImpl timerStop = new TimerStopImpl();
		return timerStop;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeOut createTimeOut() {
		TimeOutImpl timeOut = new TimeOutImpl();
		return timeOut;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GateInstance createGateInstance() {
		GateInstanceImpl gateInstance = new GateInstanceImpl();
		return gateInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentInstance createComponentInstance() {
		ComponentInstanceImpl componentInstance = new ComponentInstanceImpl();
		return componentInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Wait createWait() {
		WaitImpl wait = new WaitImpl();
		return wait;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Quiescence createQuiescence() {
		QuiescenceImpl quiescence = new QuiescenceImpl();
		return quiescence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestConfiguration createTestConfiguration() {
		TestConfigurationImpl testConfiguration = new TestConfigurationImpl();
		return testConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Connection createConnection() {
		ConnectionImpl connection = new ConnectionImpl();
		return connection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestDescription createTestDescription() {
		TestDescriptionImpl testDescription = new TestDescriptionImpl();
		return testDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompoundBehaviour createCompoundBehaviour() {
		CompoundBehaviourImpl compoundBehaviour = new CompoundBehaviourImpl();
		return compoundBehaviour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PeriodicBehaviour createPeriodicBehaviour() {
		PeriodicBehaviourImpl periodicBehaviour = new PeriodicBehaviourImpl();
		return periodicBehaviour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block createBlock() {
		BlockImpl block = new BlockImpl();
		return block;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlternativeBehaviour createAlternativeBehaviour() {
		AlternativeBehaviourImpl alternativeBehaviour = new AlternativeBehaviourImpl();
		return alternativeBehaviour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParallelBehaviour createParallelBehaviour() {
		ParallelBehaviourImpl parallelBehaviour = new ParallelBehaviourImpl();
		return parallelBehaviour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BoundedLoopBehaviour createBoundedLoopBehaviour() {
		BoundedLoopBehaviourImpl boundedLoopBehaviour = new BoundedLoopBehaviourImpl();
		return boundedLoopBehaviour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnboundedLoopBehaviour createUnboundedLoopBehaviour() {
		UnboundedLoopBehaviourImpl unboundedLoopBehaviour = new UnboundedLoopBehaviourImpl();
		return unboundedLoopBehaviour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConditionalBehaviour createConditionalBehaviour() {
		ConditionalBehaviourImpl conditionalBehaviour = new ConditionalBehaviourImpl();
		return conditionalBehaviour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Stop createStop() {
		StopImpl stop = new StopImpl();
		return stop;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestDescriptionReference createTestDescriptionReference() {
		TestDescriptionReferenceImpl testDescriptionReference = new TestDescriptionReferenceImpl();
		return testDescriptionReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VerdictAssignment createVerdictAssignment() {
		VerdictAssignmentImpl verdictAssignment = new VerdictAssignmentImpl();
		return verdictAssignment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VerdictType createVerdictType() {
		VerdictTypeImpl verdictType = new VerdictTypeImpl();
		return verdictType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionReference createActionReference() {
		ActionReferenceImpl actionReference = new ActionReferenceImpl();
		return actionReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Action createAction() {
		ActionImpl action = new ActionImpl();
		return action;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Interaction createInteraction() {
		InteractionImpl interaction = new InteractionImpl();
		return interaction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OptionalBehaviour createOptionalBehaviour() {
		OptionalBehaviourImpl optionalBehaviour = new OptionalBehaviourImpl();
		return optionalBehaviour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DefaultBehaviour createDefaultBehaviour() {
		DefaultBehaviourImpl defaultBehaviour = new DefaultBehaviourImpl();
		return defaultBehaviour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InterruptBehaviour createInterruptBehaviour() {
		InterruptBehaviourImpl interruptBehaviour = new InterruptBehaviourImpl();
		return interruptBehaviour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Break createBreak() {
		BreakImpl break_ = new BreakImpl();
		return break_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataInstanceArgumentSpecification createDataInstanceArgumentSpecification() {
		DataInstanceArgumentSpecificationImpl dataInstanceArgumentSpecification = new DataInstanceArgumentSpecificationImpl();
		return dataInstanceArgumentSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataProxyArgumentSpecification createDataProxyArgumentSpecification() {
		DataProxyArgumentSpecificationImpl dataProxyArgumentSpecification = new DataProxyArgumentSpecificationImpl();
		return dataProxyArgumentSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataSetArgumentSpecification createDataSetArgumentSpecification() {
		DataSetArgumentSpecificationImpl dataSetArgumentSpecification = new DataSetArgumentSpecificationImpl();
		return dataSetArgumentSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentInstanceRole createComponentInstanceRoleFromString(EDataType eDataType, String initialValue) {
		ComponentInstanceRole result = ComponentInstanceRole.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertComponentInstanceRoleToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TdlPackage getTdlPackage() {
		return (TdlPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TdlPackage getPackage() {
		return TdlPackage.eINSTANCE;
	}

} //TdlFactoryImpl
