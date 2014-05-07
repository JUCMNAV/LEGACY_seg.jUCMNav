/**
 */
package org.etsi.mts.tdl.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.etsi.mts.tdl.Action;
import org.etsi.mts.tdl.ActionReference;
import org.etsi.mts.tdl.AlternativeBehaviour;
import org.etsi.mts.tdl.Annotation;
import org.etsi.mts.tdl.AnnotationType;
import org.etsi.mts.tdl.ArgumentSpecification;
import org.etsi.mts.tdl.AtomicBehaviour;
import org.etsi.mts.tdl.Behaviour;
import org.etsi.mts.tdl.Block;
import org.etsi.mts.tdl.BoundedLoopBehaviour;
import org.etsi.mts.tdl.Break;
import org.etsi.mts.tdl.CombinedBehaviour;
import org.etsi.mts.tdl.Comment;
import org.etsi.mts.tdl.ComponentInstance;
import org.etsi.mts.tdl.ComponentType;
import org.etsi.mts.tdl.CompoundBehaviour;
import org.etsi.mts.tdl.ConditionalBehaviour;
import org.etsi.mts.tdl.Connection;
import org.etsi.mts.tdl.DataElement;
import org.etsi.mts.tdl.DataElementMapping;
import org.etsi.mts.tdl.DataInstance;
import org.etsi.mts.tdl.DataInstanceArgumentSpecification;
import org.etsi.mts.tdl.DataProxy;
import org.etsi.mts.tdl.DataProxyArgumentSpecification;
import org.etsi.mts.tdl.DataResourceMapping;
import org.etsi.mts.tdl.DataSet;
import org.etsi.mts.tdl.DataSetArgumentSpecification;
import org.etsi.mts.tdl.DefaultBehaviour;
import org.etsi.mts.tdl.Element;
import org.etsi.mts.tdl.ElementImport;
import org.etsi.mts.tdl.ExceptionalBehaviour;
import org.etsi.mts.tdl.GateInstance;
import org.etsi.mts.tdl.GateType;
import org.etsi.mts.tdl.Interaction;
import org.etsi.mts.tdl.InterruptBehaviour;
import org.etsi.mts.tdl.MappableDataElement;
import org.etsi.mts.tdl.MultipleCombinedBehaviour;
import org.etsi.mts.tdl.OptionalBehaviour;
import org.etsi.mts.tdl.PackageableElement;
import org.etsi.mts.tdl.ParallelBehaviour;
import org.etsi.mts.tdl.PeriodicBehaviour;
import org.etsi.mts.tdl.Quiescence;
import org.etsi.mts.tdl.SingleCombinedBehaviour;
import org.etsi.mts.tdl.Stop;
import org.etsi.mts.tdl.TdlPackage;
import org.etsi.mts.tdl.TestConfiguration;
import org.etsi.mts.tdl.TestDescription;
import org.etsi.mts.tdl.TestDescriptionReference;
import org.etsi.mts.tdl.TestObjective;
import org.etsi.mts.tdl.TestObjectiveRealizer;
import org.etsi.mts.tdl.Time;
import org.etsi.mts.tdl.TimeConstraint;
import org.etsi.mts.tdl.TimeOperation;
import org.etsi.mts.tdl.TimeOut;
import org.etsi.mts.tdl.TimeUnit;
import org.etsi.mts.tdl.Timer;
import org.etsi.mts.tdl.TimerOperation;
import org.etsi.mts.tdl.TimerStart;
import org.etsi.mts.tdl.TimerStop;
import org.etsi.mts.tdl.UnboundedLoopBehaviour;
import org.etsi.mts.tdl.VerdictAssignment;
import org.etsi.mts.tdl.VerdictType;
import org.etsi.mts.tdl.Wait;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.etsi.mts.tdl.TdlPackage
 * @generated
 */
public class TdlAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TdlPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TdlAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = TdlPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TdlSwitch<Adapter> modelSwitch =
		new TdlSwitch<Adapter>() {
			@Override
			public Adapter caseElement(Element object) {
				return createElementAdapter();
			}
			@Override
			public Adapter caseComment(Comment object) {
				return createCommentAdapter();
			}
			@Override
			public Adapter caseAnnotation(Annotation object) {
				return createAnnotationAdapter();
			}
			@Override
			public Adapter caseAnnotationType(AnnotationType object) {
				return createAnnotationTypeAdapter();
			}
			@Override
			public Adapter casePackageableElement(PackageableElement object) {
				return createPackageableElementAdapter();
			}
			@Override
			public Adapter casePackage(org.etsi.mts.tdl.Package object) {
				return createPackageAdapter();
			}
			@Override
			public Adapter caseElementImport(ElementImport object) {
				return createElementImportAdapter();
			}
			@Override
			public Adapter caseTestObjective(TestObjective object) {
				return createTestObjectiveAdapter();
			}
			@Override
			public Adapter caseTestObjectiveRealizer(TestObjectiveRealizer object) {
				return createTestObjectiveRealizerAdapter();
			}
			@Override
			public Adapter caseDataInstance(DataInstance object) {
				return createDataInstanceAdapter();
			}
			@Override
			public Adapter caseMappableDataElement(MappableDataElement object) {
				return createMappableDataElementAdapter();
			}
			@Override
			public Adapter caseDataElement(DataElement object) {
				return createDataElementAdapter();
			}
			@Override
			public Adapter caseDataSet(DataSet object) {
				return createDataSetAdapter();
			}
			@Override
			public Adapter caseDataResourceMapping(DataResourceMapping object) {
				return createDataResourceMappingAdapter();
			}
			@Override
			public Adapter caseDataElementMapping(DataElementMapping object) {
				return createDataElementMappingAdapter();
			}
			@Override
			public Adapter caseDataProxy(DataProxy object) {
				return createDataProxyAdapter();
			}
			@Override
			public Adapter caseTimer(Timer object) {
				return createTimerAdapter();
			}
			@Override
			public Adapter caseComponentType(ComponentType object) {
				return createComponentTypeAdapter();
			}
			@Override
			public Adapter caseGateType(GateType object) {
				return createGateTypeAdapter();
			}
			@Override
			public Adapter caseTimerOperation(TimerOperation object) {
				return createTimerOperationAdapter();
			}
			@Override
			public Adapter caseAtomicBehaviour(AtomicBehaviour object) {
				return createAtomicBehaviourAdapter();
			}
			@Override
			public Adapter caseBehaviour(Behaviour object) {
				return createBehaviourAdapter();
			}
			@Override
			public Adapter caseTimeConstraint(TimeConstraint object) {
				return createTimeConstraintAdapter();
			}
			@Override
			public Adapter caseTimerStart(TimerStart object) {
				return createTimerStartAdapter();
			}
			@Override
			public Adapter caseTime(Time object) {
				return createTimeAdapter();
			}
			@Override
			public Adapter caseTimeUnit(TimeUnit object) {
				return createTimeUnitAdapter();
			}
			@Override
			public Adapter caseTimerStop(TimerStop object) {
				return createTimerStopAdapter();
			}
			@Override
			public Adapter caseTimeOut(TimeOut object) {
				return createTimeOutAdapter();
			}
			@Override
			public Adapter caseTimeOperation(TimeOperation object) {
				return createTimeOperationAdapter();
			}
			@Override
			public Adapter caseGateInstance(GateInstance object) {
				return createGateInstanceAdapter();
			}
			@Override
			public Adapter caseComponentInstance(ComponentInstance object) {
				return createComponentInstanceAdapter();
			}
			@Override
			public Adapter caseWait(Wait object) {
				return createWaitAdapter();
			}
			@Override
			public Adapter caseQuiescence(Quiescence object) {
				return createQuiescenceAdapter();
			}
			@Override
			public Adapter caseTestConfiguration(TestConfiguration object) {
				return createTestConfigurationAdapter();
			}
			@Override
			public Adapter caseConnection(Connection object) {
				return createConnectionAdapter();
			}
			@Override
			public Adapter caseTestDescription(TestDescription object) {
				return createTestDescriptionAdapter();
			}
			@Override
			public Adapter caseCompoundBehaviour(CompoundBehaviour object) {
				return createCompoundBehaviourAdapter();
			}
			@Override
			public Adapter caseSingleCombinedBehaviour(SingleCombinedBehaviour object) {
				return createSingleCombinedBehaviourAdapter();
			}
			@Override
			public Adapter caseCombinedBehaviour(CombinedBehaviour object) {
				return createCombinedBehaviourAdapter();
			}
			@Override
			public Adapter casePeriodicBehaviour(PeriodicBehaviour object) {
				return createPeriodicBehaviourAdapter();
			}
			@Override
			public Adapter caseBlock(Block object) {
				return createBlockAdapter();
			}
			@Override
			public Adapter caseExceptionalBehaviour(ExceptionalBehaviour object) {
				return createExceptionalBehaviourAdapter();
			}
			@Override
			public Adapter caseAlternativeBehaviour(AlternativeBehaviour object) {
				return createAlternativeBehaviourAdapter();
			}
			@Override
			public Adapter caseMultipleCombinedBehaviour(MultipleCombinedBehaviour object) {
				return createMultipleCombinedBehaviourAdapter();
			}
			@Override
			public Adapter caseParallelBehaviour(ParallelBehaviour object) {
				return createParallelBehaviourAdapter();
			}
			@Override
			public Adapter caseBoundedLoopBehaviour(BoundedLoopBehaviour object) {
				return createBoundedLoopBehaviourAdapter();
			}
			@Override
			public Adapter caseUnboundedLoopBehaviour(UnboundedLoopBehaviour object) {
				return createUnboundedLoopBehaviourAdapter();
			}
			@Override
			public Adapter caseConditionalBehaviour(ConditionalBehaviour object) {
				return createConditionalBehaviourAdapter();
			}
			@Override
			public Adapter caseStop(Stop object) {
				return createStopAdapter();
			}
			@Override
			public Adapter caseTestDescriptionReference(TestDescriptionReference object) {
				return createTestDescriptionReferenceAdapter();
			}
			@Override
			public Adapter caseArgumentSpecification(ArgumentSpecification object) {
				return createArgumentSpecificationAdapter();
			}
			@Override
			public Adapter caseVerdictAssignment(VerdictAssignment object) {
				return createVerdictAssignmentAdapter();
			}
			@Override
			public Adapter caseVerdictType(VerdictType object) {
				return createVerdictTypeAdapter();
			}
			@Override
			public Adapter caseActionReference(ActionReference object) {
				return createActionReferenceAdapter();
			}
			@Override
			public Adapter caseAction(Action object) {
				return createActionAdapter();
			}
			@Override
			public Adapter caseInteraction(Interaction object) {
				return createInteractionAdapter();
			}
			@Override
			public Adapter caseOptionalBehaviour(OptionalBehaviour object) {
				return createOptionalBehaviourAdapter();
			}
			@Override
			public Adapter caseDefaultBehaviour(DefaultBehaviour object) {
				return createDefaultBehaviourAdapter();
			}
			@Override
			public Adapter caseInterruptBehaviour(InterruptBehaviour object) {
				return createInterruptBehaviourAdapter();
			}
			@Override
			public Adapter caseBreak(Break object) {
				return createBreakAdapter();
			}
			@Override
			public Adapter caseDataInstanceArgumentSpecification(DataInstanceArgumentSpecification object) {
				return createDataInstanceArgumentSpecificationAdapter();
			}
			@Override
			public Adapter caseDataProxyArgumentSpecification(DataProxyArgumentSpecification object) {
				return createDataProxyArgumentSpecificationAdapter();
			}
			@Override
			public Adapter caseDataSetArgumentSpecification(DataSetArgumentSpecification object) {
				return createDataSetArgumentSpecificationAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.Element
	 * @generated
	 */
	public Adapter createElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.Comment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.Comment
	 * @generated
	 */
	public Adapter createCommentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.Annotation <em>Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.Annotation
	 * @generated
	 */
	public Adapter createAnnotationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.AnnotationType <em>Annotation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.AnnotationType
	 * @generated
	 */
	public Adapter createAnnotationTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.PackageableElement <em>Packageable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.PackageableElement
	 * @generated
	 */
	public Adapter createPackageableElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.Package <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.Package
	 * @generated
	 */
	public Adapter createPackageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.ElementImport <em>Element Import</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.ElementImport
	 * @generated
	 */
	public Adapter createElementImportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.TestObjective <em>Test Objective</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.TestObjective
	 * @generated
	 */
	public Adapter createTestObjectiveAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.TestObjectiveRealizer <em>Test Objective Realizer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.TestObjectiveRealizer
	 * @generated
	 */
	public Adapter createTestObjectiveRealizerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.DataInstance <em>Data Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.DataInstance
	 * @generated
	 */
	public Adapter createDataInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.MappableDataElement <em>Mappable Data Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.MappableDataElement
	 * @generated
	 */
	public Adapter createMappableDataElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.DataElement <em>Data Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.DataElement
	 * @generated
	 */
	public Adapter createDataElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.DataSet <em>Data Set</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.DataSet
	 * @generated
	 */
	public Adapter createDataSetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.DataResourceMapping <em>Data Resource Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.DataResourceMapping
	 * @generated
	 */
	public Adapter createDataResourceMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.DataElementMapping <em>Data Element Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.DataElementMapping
	 * @generated
	 */
	public Adapter createDataElementMappingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.DataProxy <em>Data Proxy</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.DataProxy
	 * @generated
	 */
	public Adapter createDataProxyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.Timer <em>Timer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.Timer
	 * @generated
	 */
	public Adapter createTimerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.ComponentType <em>Component Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.ComponentType
	 * @generated
	 */
	public Adapter createComponentTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.GateType <em>Gate Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.GateType
	 * @generated
	 */
	public Adapter createGateTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.TimerOperation <em>Timer Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.TimerOperation
	 * @generated
	 */
	public Adapter createTimerOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.AtomicBehaviour <em>Atomic Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.AtomicBehaviour
	 * @generated
	 */
	public Adapter createAtomicBehaviourAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.Behaviour <em>Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.Behaviour
	 * @generated
	 */
	public Adapter createBehaviourAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.TimeConstraint <em>Time Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.TimeConstraint
	 * @generated
	 */
	public Adapter createTimeConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.TimerStart <em>Timer Start</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.TimerStart
	 * @generated
	 */
	public Adapter createTimerStartAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.Time <em>Time</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.Time
	 * @generated
	 */
	public Adapter createTimeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.TimeUnit <em>Time Unit</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.TimeUnit
	 * @generated
	 */
	public Adapter createTimeUnitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.TimerStop <em>Timer Stop</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.TimerStop
	 * @generated
	 */
	public Adapter createTimerStopAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.TimeOut <em>Time Out</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.TimeOut
	 * @generated
	 */
	public Adapter createTimeOutAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.TimeOperation <em>Time Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.TimeOperation
	 * @generated
	 */
	public Adapter createTimeOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.GateInstance <em>Gate Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.GateInstance
	 * @generated
	 */
	public Adapter createGateInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.ComponentInstance <em>Component Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.ComponentInstance
	 * @generated
	 */
	public Adapter createComponentInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.Wait <em>Wait</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.Wait
	 * @generated
	 */
	public Adapter createWaitAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.Quiescence <em>Quiescence</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.Quiescence
	 * @generated
	 */
	public Adapter createQuiescenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.TestConfiguration <em>Test Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.TestConfiguration
	 * @generated
	 */
	public Adapter createTestConfigurationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.Connection
	 * @generated
	 */
	public Adapter createConnectionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.TestDescription <em>Test Description</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.TestDescription
	 * @generated
	 */
	public Adapter createTestDescriptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.CompoundBehaviour <em>Compound Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.CompoundBehaviour
	 * @generated
	 */
	public Adapter createCompoundBehaviourAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.SingleCombinedBehaviour <em>Single Combined Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.SingleCombinedBehaviour
	 * @generated
	 */
	public Adapter createSingleCombinedBehaviourAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.CombinedBehaviour <em>Combined Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.CombinedBehaviour
	 * @generated
	 */
	public Adapter createCombinedBehaviourAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.PeriodicBehaviour <em>Periodic Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.PeriodicBehaviour
	 * @generated
	 */
	public Adapter createPeriodicBehaviourAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.Block <em>Block</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.Block
	 * @generated
	 */
	public Adapter createBlockAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.ExceptionalBehaviour <em>Exceptional Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.ExceptionalBehaviour
	 * @generated
	 */
	public Adapter createExceptionalBehaviourAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.AlternativeBehaviour <em>Alternative Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.AlternativeBehaviour
	 * @generated
	 */
	public Adapter createAlternativeBehaviourAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.MultipleCombinedBehaviour <em>Multiple Combined Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.MultipleCombinedBehaviour
	 * @generated
	 */
	public Adapter createMultipleCombinedBehaviourAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.ParallelBehaviour <em>Parallel Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.ParallelBehaviour
	 * @generated
	 */
	public Adapter createParallelBehaviourAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.BoundedLoopBehaviour <em>Bounded Loop Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.BoundedLoopBehaviour
	 * @generated
	 */
	public Adapter createBoundedLoopBehaviourAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.UnboundedLoopBehaviour <em>Unbounded Loop Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.UnboundedLoopBehaviour
	 * @generated
	 */
	public Adapter createUnboundedLoopBehaviourAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.ConditionalBehaviour <em>Conditional Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.ConditionalBehaviour
	 * @generated
	 */
	public Adapter createConditionalBehaviourAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.Stop <em>Stop</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.Stop
	 * @generated
	 */
	public Adapter createStopAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.TestDescriptionReference <em>Test Description Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.TestDescriptionReference
	 * @generated
	 */
	public Adapter createTestDescriptionReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.ArgumentSpecification <em>Argument Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.ArgumentSpecification
	 * @generated
	 */
	public Adapter createArgumentSpecificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.VerdictAssignment <em>Verdict Assignment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.VerdictAssignment
	 * @generated
	 */
	public Adapter createVerdictAssignmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.VerdictType <em>Verdict Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.VerdictType
	 * @generated
	 */
	public Adapter createVerdictTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.ActionReference <em>Action Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.ActionReference
	 * @generated
	 */
	public Adapter createActionReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.Action <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.Action
	 * @generated
	 */
	public Adapter createActionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.Interaction <em>Interaction</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.Interaction
	 * @generated
	 */
	public Adapter createInteractionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.OptionalBehaviour <em>Optional Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.OptionalBehaviour
	 * @generated
	 */
	public Adapter createOptionalBehaviourAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.DefaultBehaviour <em>Default Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.DefaultBehaviour
	 * @generated
	 */
	public Adapter createDefaultBehaviourAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.InterruptBehaviour <em>Interrupt Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.InterruptBehaviour
	 * @generated
	 */
	public Adapter createInterruptBehaviourAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.Break <em>Break</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.Break
	 * @generated
	 */
	public Adapter createBreakAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.DataInstanceArgumentSpecification <em>Data Instance Argument Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.DataInstanceArgumentSpecification
	 * @generated
	 */
	public Adapter createDataInstanceArgumentSpecificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.DataProxyArgumentSpecification <em>Data Proxy Argument Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.DataProxyArgumentSpecification
	 * @generated
	 */
	public Adapter createDataProxyArgumentSpecificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.etsi.mts.tdl.DataSetArgumentSpecification <em>Data Set Argument Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.etsi.mts.tdl.DataSetArgumentSpecification
	 * @generated
	 */
	public Adapter createDataSetArgumentSpecificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //TdlAdapterFactory
