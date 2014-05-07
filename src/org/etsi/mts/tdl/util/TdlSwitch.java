/**
 */
package org.etsi.mts.tdl.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

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
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.etsi.mts.tdl.TdlPackage
 * @generated
 */
public class TdlSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TdlPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TdlSwitch() {
		if (modelPackage == null) {
			modelPackage = TdlPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case TdlPackage.ELEMENT: {
				Element element = (Element)theEObject;
				T result = caseElement(element);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.COMMENT: {
				Comment comment = (Comment)theEObject;
				T result = caseComment(comment);
				if (result == null) result = caseElement(comment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.ANNOTATION: {
				Annotation annotation = (Annotation)theEObject;
				T result = caseAnnotation(annotation);
				if (result == null) result = caseElement(annotation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.ANNOTATION_TYPE: {
				AnnotationType annotationType = (AnnotationType)theEObject;
				T result = caseAnnotationType(annotationType);
				if (result == null) result = casePackageableElement(annotationType);
				if (result == null) result = caseElement(annotationType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.PACKAGEABLE_ELEMENT: {
				PackageableElement packageableElement = (PackageableElement)theEObject;
				T result = casePackageableElement(packageableElement);
				if (result == null) result = caseElement(packageableElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.PACKAGE: {
				org.etsi.mts.tdl.Package package_ = (org.etsi.mts.tdl.Package)theEObject;
				T result = casePackage(package_);
				if (result == null) result = casePackageableElement(package_);
				if (result == null) result = caseElement(package_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.ELEMENT_IMPORT: {
				ElementImport elementImport = (ElementImport)theEObject;
				T result = caseElementImport(elementImport);
				if (result == null) result = caseElement(elementImport);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.TEST_OBJECTIVE: {
				TestObjective testObjective = (TestObjective)theEObject;
				T result = caseTestObjective(testObjective);
				if (result == null) result = casePackageableElement(testObjective);
				if (result == null) result = caseElement(testObjective);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.TEST_OBJECTIVE_REALIZER: {
				TestObjectiveRealizer testObjectiveRealizer = (TestObjectiveRealizer)theEObject;
				T result = caseTestObjectiveRealizer(testObjectiveRealizer);
				if (result == null) result = caseElement(testObjectiveRealizer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.DATA_INSTANCE: {
				DataInstance dataInstance = (DataInstance)theEObject;
				T result = caseDataInstance(dataInstance);
				if (result == null) result = caseMappableDataElement(dataInstance);
				if (result == null) result = caseDataElement(dataInstance);
				if (result == null) result = caseElement(dataInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.MAPPABLE_DATA_ELEMENT: {
				MappableDataElement mappableDataElement = (MappableDataElement)theEObject;
				T result = caseMappableDataElement(mappableDataElement);
				if (result == null) result = caseDataElement(mappableDataElement);
				if (result == null) result = caseElement(mappableDataElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.DATA_ELEMENT: {
				DataElement dataElement = (DataElement)theEObject;
				T result = caseDataElement(dataElement);
				if (result == null) result = caseElement(dataElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.DATA_SET: {
				DataSet dataSet = (DataSet)theEObject;
				T result = caseDataSet(dataSet);
				if (result == null) result = casePackageableElement(dataSet);
				if (result == null) result = caseMappableDataElement(dataSet);
				if (result == null) result = caseDataElement(dataSet);
				if (result == null) result = caseElement(dataSet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.DATA_RESOURCE_MAPPING: {
				DataResourceMapping dataResourceMapping = (DataResourceMapping)theEObject;
				T result = caseDataResourceMapping(dataResourceMapping);
				if (result == null) result = casePackageableElement(dataResourceMapping);
				if (result == null) result = caseElement(dataResourceMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.DATA_ELEMENT_MAPPING: {
				DataElementMapping dataElementMapping = (DataElementMapping)theEObject;
				T result = caseDataElementMapping(dataElementMapping);
				if (result == null) result = casePackageableElement(dataElementMapping);
				if (result == null) result = caseElement(dataElementMapping);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.DATA_PROXY: {
				DataProxy dataProxy = (DataProxy)theEObject;
				T result = caseDataProxy(dataProxy);
				if (result == null) result = caseDataElement(dataProxy);
				if (result == null) result = caseElement(dataProxy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.TIMER: {
				Timer timer = (Timer)theEObject;
				T result = caseTimer(timer);
				if (result == null) result = caseElement(timer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.COMPONENT_TYPE: {
				ComponentType componentType = (ComponentType)theEObject;
				T result = caseComponentType(componentType);
				if (result == null) result = casePackageableElement(componentType);
				if (result == null) result = caseElement(componentType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.GATE_TYPE: {
				GateType gateType = (GateType)theEObject;
				T result = caseGateType(gateType);
				if (result == null) result = casePackageableElement(gateType);
				if (result == null) result = caseElement(gateType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.TIMER_OPERATION: {
				TimerOperation timerOperation = (TimerOperation)theEObject;
				T result = caseTimerOperation(timerOperation);
				if (result == null) result = caseAtomicBehaviour(timerOperation);
				if (result == null) result = caseBehaviour(timerOperation);
				if (result == null) result = caseTestObjectiveRealizer(timerOperation);
				if (result == null) result = caseElement(timerOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.ATOMIC_BEHAVIOUR: {
				AtomicBehaviour atomicBehaviour = (AtomicBehaviour)theEObject;
				T result = caseAtomicBehaviour(atomicBehaviour);
				if (result == null) result = caseBehaviour(atomicBehaviour);
				if (result == null) result = caseTestObjectiveRealizer(atomicBehaviour);
				if (result == null) result = caseElement(atomicBehaviour);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.BEHAVIOUR: {
				Behaviour behaviour = (Behaviour)theEObject;
				T result = caseBehaviour(behaviour);
				if (result == null) result = caseTestObjectiveRealizer(behaviour);
				if (result == null) result = caseElement(behaviour);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.TIME_CONSTRAINT: {
				TimeConstraint timeConstraint = (TimeConstraint)theEObject;
				T result = caseTimeConstraint(timeConstraint);
				if (result == null) result = caseElement(timeConstraint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.TIMER_START: {
				TimerStart timerStart = (TimerStart)theEObject;
				T result = caseTimerStart(timerStart);
				if (result == null) result = caseTimerOperation(timerStart);
				if (result == null) result = caseAtomicBehaviour(timerStart);
				if (result == null) result = caseBehaviour(timerStart);
				if (result == null) result = caseTestObjectiveRealizer(timerStart);
				if (result == null) result = caseElement(timerStart);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.TIME: {
				Time time = (Time)theEObject;
				T result = caseTime(time);
				if (result == null) result = caseElement(time);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.TIME_UNIT: {
				TimeUnit timeUnit = (TimeUnit)theEObject;
				T result = caseTimeUnit(timeUnit);
				if (result == null) result = casePackageableElement(timeUnit);
				if (result == null) result = caseElement(timeUnit);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.TIMER_STOP: {
				TimerStop timerStop = (TimerStop)theEObject;
				T result = caseTimerStop(timerStop);
				if (result == null) result = caseTimerOperation(timerStop);
				if (result == null) result = caseAtomicBehaviour(timerStop);
				if (result == null) result = caseBehaviour(timerStop);
				if (result == null) result = caseTestObjectiveRealizer(timerStop);
				if (result == null) result = caseElement(timerStop);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.TIME_OUT: {
				TimeOut timeOut = (TimeOut)theEObject;
				T result = caseTimeOut(timeOut);
				if (result == null) result = caseTimerOperation(timeOut);
				if (result == null) result = caseAtomicBehaviour(timeOut);
				if (result == null) result = caseBehaviour(timeOut);
				if (result == null) result = caseTestObjectiveRealizer(timeOut);
				if (result == null) result = caseElement(timeOut);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.TIME_OPERATION: {
				TimeOperation timeOperation = (TimeOperation)theEObject;
				T result = caseTimeOperation(timeOperation);
				if (result == null) result = caseAtomicBehaviour(timeOperation);
				if (result == null) result = caseBehaviour(timeOperation);
				if (result == null) result = caseTestObjectiveRealizer(timeOperation);
				if (result == null) result = caseElement(timeOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.GATE_INSTANCE: {
				GateInstance gateInstance = (GateInstance)theEObject;
				T result = caseGateInstance(gateInstance);
				if (result == null) result = caseElement(gateInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.COMPONENT_INSTANCE: {
				ComponentInstance componentInstance = (ComponentInstance)theEObject;
				T result = caseComponentInstance(componentInstance);
				if (result == null) result = caseElement(componentInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.WAIT: {
				Wait wait = (Wait)theEObject;
				T result = caseWait(wait);
				if (result == null) result = caseTimeOperation(wait);
				if (result == null) result = caseAtomicBehaviour(wait);
				if (result == null) result = caseBehaviour(wait);
				if (result == null) result = caseTestObjectiveRealizer(wait);
				if (result == null) result = caseElement(wait);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.QUIESCENCE: {
				Quiescence quiescence = (Quiescence)theEObject;
				T result = caseQuiescence(quiescence);
				if (result == null) result = caseTimeOperation(quiescence);
				if (result == null) result = caseAtomicBehaviour(quiescence);
				if (result == null) result = caseBehaviour(quiescence);
				if (result == null) result = caseTestObjectiveRealizer(quiescence);
				if (result == null) result = caseElement(quiescence);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.TEST_CONFIGURATION: {
				TestConfiguration testConfiguration = (TestConfiguration)theEObject;
				T result = caseTestConfiguration(testConfiguration);
				if (result == null) result = casePackageableElement(testConfiguration);
				if (result == null) result = caseElement(testConfiguration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.CONNECTION: {
				Connection connection = (Connection)theEObject;
				T result = caseConnection(connection);
				if (result == null) result = caseElement(connection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.TEST_DESCRIPTION: {
				TestDescription testDescription = (TestDescription)theEObject;
				T result = caseTestDescription(testDescription);
				if (result == null) result = casePackageableElement(testDescription);
				if (result == null) result = caseTestObjectiveRealizer(testDescription);
				if (result == null) result = caseElement(testDescription);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.COMPOUND_BEHAVIOUR: {
				CompoundBehaviour compoundBehaviour = (CompoundBehaviour)theEObject;
				T result = caseCompoundBehaviour(compoundBehaviour);
				if (result == null) result = caseSingleCombinedBehaviour(compoundBehaviour);
				if (result == null) result = caseCombinedBehaviour(compoundBehaviour);
				if (result == null) result = caseBehaviour(compoundBehaviour);
				if (result == null) result = caseTestObjectiveRealizer(compoundBehaviour);
				if (result == null) result = caseElement(compoundBehaviour);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.SINGLE_COMBINED_BEHAVIOUR: {
				SingleCombinedBehaviour singleCombinedBehaviour = (SingleCombinedBehaviour)theEObject;
				T result = caseSingleCombinedBehaviour(singleCombinedBehaviour);
				if (result == null) result = caseCombinedBehaviour(singleCombinedBehaviour);
				if (result == null) result = caseBehaviour(singleCombinedBehaviour);
				if (result == null) result = caseTestObjectiveRealizer(singleCombinedBehaviour);
				if (result == null) result = caseElement(singleCombinedBehaviour);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.COMBINED_BEHAVIOUR: {
				CombinedBehaviour combinedBehaviour = (CombinedBehaviour)theEObject;
				T result = caseCombinedBehaviour(combinedBehaviour);
				if (result == null) result = caseBehaviour(combinedBehaviour);
				if (result == null) result = caseTestObjectiveRealizer(combinedBehaviour);
				if (result == null) result = caseElement(combinedBehaviour);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.PERIODIC_BEHAVIOUR: {
				PeriodicBehaviour periodicBehaviour = (PeriodicBehaviour)theEObject;
				T result = casePeriodicBehaviour(periodicBehaviour);
				if (result == null) result = caseBehaviour(periodicBehaviour);
				if (result == null) result = caseTestObjectiveRealizer(periodicBehaviour);
				if (result == null) result = caseElement(periodicBehaviour);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.BLOCK: {
				Block block = (Block)theEObject;
				T result = caseBlock(block);
				if (result == null) result = caseElement(block);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.EXCEPTIONAL_BEHAVIOUR: {
				ExceptionalBehaviour exceptionalBehaviour = (ExceptionalBehaviour)theEObject;
				T result = caseExceptionalBehaviour(exceptionalBehaviour);
				if (result == null) result = caseBehaviour(exceptionalBehaviour);
				if (result == null) result = caseTestObjectiveRealizer(exceptionalBehaviour);
				if (result == null) result = caseElement(exceptionalBehaviour);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.ALTERNATIVE_BEHAVIOUR: {
				AlternativeBehaviour alternativeBehaviour = (AlternativeBehaviour)theEObject;
				T result = caseAlternativeBehaviour(alternativeBehaviour);
				if (result == null) result = caseMultipleCombinedBehaviour(alternativeBehaviour);
				if (result == null) result = caseCombinedBehaviour(alternativeBehaviour);
				if (result == null) result = caseBehaviour(alternativeBehaviour);
				if (result == null) result = caseTestObjectiveRealizer(alternativeBehaviour);
				if (result == null) result = caseElement(alternativeBehaviour);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.MULTIPLE_COMBINED_BEHAVIOUR: {
				MultipleCombinedBehaviour multipleCombinedBehaviour = (MultipleCombinedBehaviour)theEObject;
				T result = caseMultipleCombinedBehaviour(multipleCombinedBehaviour);
				if (result == null) result = caseCombinedBehaviour(multipleCombinedBehaviour);
				if (result == null) result = caseBehaviour(multipleCombinedBehaviour);
				if (result == null) result = caseTestObjectiveRealizer(multipleCombinedBehaviour);
				if (result == null) result = caseElement(multipleCombinedBehaviour);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.PARALLEL_BEHAVIOUR: {
				ParallelBehaviour parallelBehaviour = (ParallelBehaviour)theEObject;
				T result = caseParallelBehaviour(parallelBehaviour);
				if (result == null) result = caseMultipleCombinedBehaviour(parallelBehaviour);
				if (result == null) result = caseCombinedBehaviour(parallelBehaviour);
				if (result == null) result = caseBehaviour(parallelBehaviour);
				if (result == null) result = caseTestObjectiveRealizer(parallelBehaviour);
				if (result == null) result = caseElement(parallelBehaviour);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.BOUNDED_LOOP_BEHAVIOUR: {
				BoundedLoopBehaviour boundedLoopBehaviour = (BoundedLoopBehaviour)theEObject;
				T result = caseBoundedLoopBehaviour(boundedLoopBehaviour);
				if (result == null) result = caseSingleCombinedBehaviour(boundedLoopBehaviour);
				if (result == null) result = caseCombinedBehaviour(boundedLoopBehaviour);
				if (result == null) result = caseBehaviour(boundedLoopBehaviour);
				if (result == null) result = caseTestObjectiveRealizer(boundedLoopBehaviour);
				if (result == null) result = caseElement(boundedLoopBehaviour);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.UNBOUNDED_LOOP_BEHAVIOUR: {
				UnboundedLoopBehaviour unboundedLoopBehaviour = (UnboundedLoopBehaviour)theEObject;
				T result = caseUnboundedLoopBehaviour(unboundedLoopBehaviour);
				if (result == null) result = caseSingleCombinedBehaviour(unboundedLoopBehaviour);
				if (result == null) result = caseCombinedBehaviour(unboundedLoopBehaviour);
				if (result == null) result = caseBehaviour(unboundedLoopBehaviour);
				if (result == null) result = caseTestObjectiveRealizer(unboundedLoopBehaviour);
				if (result == null) result = caseElement(unboundedLoopBehaviour);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.CONDITIONAL_BEHAVIOUR: {
				ConditionalBehaviour conditionalBehaviour = (ConditionalBehaviour)theEObject;
				T result = caseConditionalBehaviour(conditionalBehaviour);
				if (result == null) result = caseMultipleCombinedBehaviour(conditionalBehaviour);
				if (result == null) result = caseCombinedBehaviour(conditionalBehaviour);
				if (result == null) result = caseBehaviour(conditionalBehaviour);
				if (result == null) result = caseTestObjectiveRealizer(conditionalBehaviour);
				if (result == null) result = caseElement(conditionalBehaviour);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.STOP: {
				Stop stop = (Stop)theEObject;
				T result = caseStop(stop);
				if (result == null) result = caseAtomicBehaviour(stop);
				if (result == null) result = caseBehaviour(stop);
				if (result == null) result = caseTestObjectiveRealizer(stop);
				if (result == null) result = caseElement(stop);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.TEST_DESCRIPTION_REFERENCE: {
				TestDescriptionReference testDescriptionReference = (TestDescriptionReference)theEObject;
				T result = caseTestDescriptionReference(testDescriptionReference);
				if (result == null) result = caseAtomicBehaviour(testDescriptionReference);
				if (result == null) result = caseBehaviour(testDescriptionReference);
				if (result == null) result = caseTestObjectiveRealizer(testDescriptionReference);
				if (result == null) result = caseElement(testDescriptionReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.ARGUMENT_SPECIFICATION: {
				ArgumentSpecification argumentSpecification = (ArgumentSpecification)theEObject;
				T result = caseArgumentSpecification(argumentSpecification);
				if (result == null) result = caseElement(argumentSpecification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.VERDICT_ASSIGNMENT: {
				VerdictAssignment verdictAssignment = (VerdictAssignment)theEObject;
				T result = caseVerdictAssignment(verdictAssignment);
				if (result == null) result = caseAtomicBehaviour(verdictAssignment);
				if (result == null) result = caseBehaviour(verdictAssignment);
				if (result == null) result = caseTestObjectiveRealizer(verdictAssignment);
				if (result == null) result = caseElement(verdictAssignment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.VERDICT_TYPE: {
				VerdictType verdictType = (VerdictType)theEObject;
				T result = caseVerdictType(verdictType);
				if (result == null) result = casePackageableElement(verdictType);
				if (result == null) result = caseElement(verdictType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.ACTION_REFERENCE: {
				ActionReference actionReference = (ActionReference)theEObject;
				T result = caseActionReference(actionReference);
				if (result == null) result = caseAtomicBehaviour(actionReference);
				if (result == null) result = caseBehaviour(actionReference);
				if (result == null) result = caseTestObjectiveRealizer(actionReference);
				if (result == null) result = caseElement(actionReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.ACTION: {
				Action action = (Action)theEObject;
				T result = caseAction(action);
				if (result == null) result = casePackageableElement(action);
				if (result == null) result = caseElement(action);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.INTERACTION: {
				Interaction interaction = (Interaction)theEObject;
				T result = caseInteraction(interaction);
				if (result == null) result = caseAtomicBehaviour(interaction);
				if (result == null) result = caseBehaviour(interaction);
				if (result == null) result = caseTestObjectiveRealizer(interaction);
				if (result == null) result = caseElement(interaction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.OPTIONAL_BEHAVIOUR: {
				OptionalBehaviour optionalBehaviour = (OptionalBehaviour)theEObject;
				T result = caseOptionalBehaviour(optionalBehaviour);
				if (result == null) result = caseSingleCombinedBehaviour(optionalBehaviour);
				if (result == null) result = caseCombinedBehaviour(optionalBehaviour);
				if (result == null) result = caseBehaviour(optionalBehaviour);
				if (result == null) result = caseTestObjectiveRealizer(optionalBehaviour);
				if (result == null) result = caseElement(optionalBehaviour);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.DEFAULT_BEHAVIOUR: {
				DefaultBehaviour defaultBehaviour = (DefaultBehaviour)theEObject;
				T result = caseDefaultBehaviour(defaultBehaviour);
				if (result == null) result = caseExceptionalBehaviour(defaultBehaviour);
				if (result == null) result = caseBehaviour(defaultBehaviour);
				if (result == null) result = caseTestObjectiveRealizer(defaultBehaviour);
				if (result == null) result = caseElement(defaultBehaviour);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.INTERRUPT_BEHAVIOUR: {
				InterruptBehaviour interruptBehaviour = (InterruptBehaviour)theEObject;
				T result = caseInterruptBehaviour(interruptBehaviour);
				if (result == null) result = caseExceptionalBehaviour(interruptBehaviour);
				if (result == null) result = caseBehaviour(interruptBehaviour);
				if (result == null) result = caseTestObjectiveRealizer(interruptBehaviour);
				if (result == null) result = caseElement(interruptBehaviour);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.BREAK: {
				Break break_ = (Break)theEObject;
				T result = caseBreak(break_);
				if (result == null) result = caseAtomicBehaviour(break_);
				if (result == null) result = caseBehaviour(break_);
				if (result == null) result = caseTestObjectiveRealizer(break_);
				if (result == null) result = caseElement(break_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.DATA_INSTANCE_ARGUMENT_SPECIFICATION: {
				DataInstanceArgumentSpecification dataInstanceArgumentSpecification = (DataInstanceArgumentSpecification)theEObject;
				T result = caseDataInstanceArgumentSpecification(dataInstanceArgumentSpecification);
				if (result == null) result = caseArgumentSpecification(dataInstanceArgumentSpecification);
				if (result == null) result = caseElement(dataInstanceArgumentSpecification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.DATA_PROXY_ARGUMENT_SPECIFICATION: {
				DataProxyArgumentSpecification dataProxyArgumentSpecification = (DataProxyArgumentSpecification)theEObject;
				T result = caseDataProxyArgumentSpecification(dataProxyArgumentSpecification);
				if (result == null) result = caseArgumentSpecification(dataProxyArgumentSpecification);
				if (result == null) result = caseElement(dataProxyArgumentSpecification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case TdlPackage.DATA_SET_ARGUMENT_SPECIFICATION: {
				DataSetArgumentSpecification dataSetArgumentSpecification = (DataSetArgumentSpecification)theEObject;
				T result = caseDataSetArgumentSpecification(dataSetArgumentSpecification);
				if (result == null) result = caseArgumentSpecification(dataSetArgumentSpecification);
				if (result == null) result = caseElement(dataSetArgumentSpecification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElement(Element object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Comment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Comment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComment(Comment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Annotation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Annotation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnnotation(Annotation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Annotation Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Annotation Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnnotationType(AnnotationType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Packageable Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Packageable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackageableElement(PackageableElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackage(org.etsi.mts.tdl.Package object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element Import</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element Import</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElementImport(ElementImport object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Objective</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Objective</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestObjective(TestObjective object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Objective Realizer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Objective Realizer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestObjectiveRealizer(TestObjectiveRealizer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataInstance(DataInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Mappable Data Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Mappable Data Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMappableDataElement(MappableDataElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataElement(DataElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataSet(DataSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Resource Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Resource Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataResourceMapping(DataResourceMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Element Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Element Mapping</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataElementMapping(DataElementMapping object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Proxy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Proxy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataProxy(DataProxy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Timer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Timer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTimer(Timer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentType(ComponentType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Gate Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Gate Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGateType(GateType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Timer Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Timer Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTimerOperation(TimerOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Atomic Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Atomic Behaviour</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAtomicBehaviour(AtomicBehaviour object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Behaviour</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBehaviour(Behaviour object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Time Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Time Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTimeConstraint(TimeConstraint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Timer Start</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Timer Start</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTimerStart(TimerStart object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Time</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Time</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTime(Time object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Time Unit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Time Unit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTimeUnit(TimeUnit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Timer Stop</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Timer Stop</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTimerStop(TimerStop object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Time Out</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Time Out</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTimeOut(TimeOut object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Time Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Time Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTimeOperation(TimeOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Gate Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Gate Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGateInstance(GateInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponentInstance(ComponentInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wait</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wait</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWait(Wait object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Quiescence</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Quiescence</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQuiescence(Quiescence object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Configuration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestConfiguration(TestConfiguration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnection(Connection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Description</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Description</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestDescription(TestDescription object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Compound Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Compound Behaviour</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCompoundBehaviour(CompoundBehaviour object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Single Combined Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Single Combined Behaviour</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSingleCombinedBehaviour(SingleCombinedBehaviour object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Combined Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Combined Behaviour</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCombinedBehaviour(CombinedBehaviour object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Periodic Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Periodic Behaviour</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePeriodicBehaviour(PeriodicBehaviour object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Block</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Block</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBlock(Block object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exceptional Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exceptional Behaviour</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExceptionalBehaviour(ExceptionalBehaviour object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Alternative Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Alternative Behaviour</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAlternativeBehaviour(AlternativeBehaviour object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Multiple Combined Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Multiple Combined Behaviour</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMultipleCombinedBehaviour(MultipleCombinedBehaviour object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parallel Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parallel Behaviour</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParallelBehaviour(ParallelBehaviour object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bounded Loop Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bounded Loop Behaviour</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBoundedLoopBehaviour(BoundedLoopBehaviour object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unbounded Loop Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unbounded Loop Behaviour</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnboundedLoopBehaviour(UnboundedLoopBehaviour object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Conditional Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Conditional Behaviour</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConditionalBehaviour(ConditionalBehaviour object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Stop</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stop</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStop(Stop object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Test Description Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Test Description Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTestDescriptionReference(TestDescriptionReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Argument Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Argument Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArgumentSpecification(ArgumentSpecification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Verdict Assignment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Verdict Assignment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVerdictAssignment(VerdictAssignment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Verdict Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Verdict Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVerdictType(VerdictType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionReference(ActionReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAction(Action object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Interaction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Interaction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInteraction(Interaction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Optional Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Optional Behaviour</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOptionalBehaviour(OptionalBehaviour object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Default Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Default Behaviour</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDefaultBehaviour(DefaultBehaviour object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Interrupt Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Interrupt Behaviour</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInterruptBehaviour(InterruptBehaviour object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Break</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Break</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBreak(Break object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Instance Argument Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Instance Argument Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataInstanceArgumentSpecification(DataInstanceArgumentSpecification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Proxy Argument Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Proxy Argument Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataProxyArgumentSpecification(DataProxyArgumentSpecification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Set Argument Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Set Argument Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataSetArgumentSpecification(DataSetArgumentSpecification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //TdlSwitch
