/**
 */
package org.etsi.mts.tdl.util;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

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
import org.etsi.mts.tdl.ComponentInstanceRole;
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
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.etsi.mts.tdl.TdlPackage
 * @generated
 */
public class TdlValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final TdlValidator INSTANCE = new TdlValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.etsi.mts.tdl";

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Inv Qualifiednamesmustbedistinguishable' of 'Packageable Element'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int PACKAGEABLE_ELEMENT__INV_QUALIFIEDNAMESMUSTBEDISTINGUISHABLE = 1;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Inv Cyclic Imports Not Allowed' of 'Package'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int PACKAGE__INV_CYCLIC_IMPORTS_NOT_ALLOWED = 2;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Inv Test Configurationand Components Roles' of 'Component Type'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int COMPONENT_TYPE__INV_TEST_CONFIGURATIONAND_COMPONENTS_ROLES = 3;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Inv Numberof Gate Instances' of 'Component Instance'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int COMPONENT_INSTANCE__INV_NUMBEROF_GATE_INSTANCES = 4;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Inv Timersof Component Instances' of 'Component Instance'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int COMPONENT_INSTANCE__INV_TIMERSOF_COMPONENT_INSTANCES = 5;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Inv Selflooped Connections' of 'Connection'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int CONNECTION__INV_SELFLOOPED_CONNECTIONS = 6;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Inv Onlyoneconnectionallowed' of 'Connection'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int CONNECTION__INV_ONLYONECONNECTIONALLOWED = 7;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Inv Bounded Loopmustnothaveaguard' of 'Bounded Loop Behaviour'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int BOUNDED_LOOP_BEHAVIOUR__INV_BOUNDED_LOOPMUSTNOTHAVEAGUARD = 8;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Inv Constraint1' of 'Interaction'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int INTERACTION__INV_CONSTRAINT1 = 9;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Inv Constraint2' of 'Interaction'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int INTERACTION__INV_CONSTRAINT2 = 10;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Inv Constraint3' of 'Interaction'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int INTERACTION__INV_CONSTRAINT3 = 11;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 11;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Delegates evaluation of the given invariant expression against the object in the given context.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context, String validationDelegate, EOperation invariant, String expression, int severity, String source, int code) {
		return EObjectValidator.validate(eClass, eObject, diagnostics, context, validationDelegate, invariant, expression, severity, source, code);
	}

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TdlValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return TdlPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case TdlPackage.ELEMENT:
				return validateElement((Element)value, diagnostics, context);
			case TdlPackage.COMMENT:
				return validateComment((Comment)value, diagnostics, context);
			case TdlPackage.ANNOTATION:
				return validateAnnotation((Annotation)value, diagnostics, context);
			case TdlPackage.ANNOTATION_TYPE:
				return validateAnnotationType((AnnotationType)value, diagnostics, context);
			case TdlPackage.PACKAGEABLE_ELEMENT:
				return validatePackageableElement((PackageableElement)value, diagnostics, context);
			case TdlPackage.PACKAGE:
				return validatePackage((org.etsi.mts.tdl.Package)value, diagnostics, context);
			case TdlPackage.ELEMENT_IMPORT:
				return validateElementImport((ElementImport)value, diagnostics, context);
			case TdlPackage.TEST_OBJECTIVE:
				return validateTestObjective((TestObjective)value, diagnostics, context);
			case TdlPackage.TEST_OBJECTIVE_REALIZER:
				return validateTestObjectiveRealizer((TestObjectiveRealizer)value, diagnostics, context);
			case TdlPackage.DATA_INSTANCE:
				return validateDataInstance((DataInstance)value, diagnostics, context);
			case TdlPackage.MAPPABLE_DATA_ELEMENT:
				return validateMappableDataElement((MappableDataElement)value, diagnostics, context);
			case TdlPackage.DATA_ELEMENT:
				return validateDataElement((DataElement)value, diagnostics, context);
			case TdlPackage.DATA_SET:
				return validateDataSet((DataSet)value, diagnostics, context);
			case TdlPackage.DATA_RESOURCE_MAPPING:
				return validateDataResourceMapping((DataResourceMapping)value, diagnostics, context);
			case TdlPackage.DATA_ELEMENT_MAPPING:
				return validateDataElementMapping((DataElementMapping)value, diagnostics, context);
			case TdlPackage.DATA_PROXY:
				return validateDataProxy((DataProxy)value, diagnostics, context);
			case TdlPackage.TIMER:
				return validateTimer((Timer)value, diagnostics, context);
			case TdlPackage.COMPONENT_TYPE:
				return validateComponentType((ComponentType)value, diagnostics, context);
			case TdlPackage.GATE_TYPE:
				return validateGateType((GateType)value, diagnostics, context);
			case TdlPackage.TIMER_OPERATION:
				return validateTimerOperation((TimerOperation)value, diagnostics, context);
			case TdlPackage.ATOMIC_BEHAVIOUR:
				return validateAtomicBehaviour((AtomicBehaviour)value, diagnostics, context);
			case TdlPackage.BEHAVIOUR:
				return validateBehaviour((Behaviour)value, diagnostics, context);
			case TdlPackage.TIME_CONSTRAINT:
				return validateTimeConstraint((TimeConstraint)value, diagnostics, context);
			case TdlPackage.TIMER_START:
				return validateTimerStart((TimerStart)value, diagnostics, context);
			case TdlPackage.TIME:
				return validateTime((Time)value, diagnostics, context);
			case TdlPackage.TIME_UNIT:
				return validateTimeUnit((TimeUnit)value, diagnostics, context);
			case TdlPackage.TIMER_STOP:
				return validateTimerStop((TimerStop)value, diagnostics, context);
			case TdlPackage.TIME_OUT:
				return validateTimeOut((TimeOut)value, diagnostics, context);
			case TdlPackage.TIME_OPERATION:
				return validateTimeOperation((TimeOperation)value, diagnostics, context);
			case TdlPackage.GATE_INSTANCE:
				return validateGateInstance((GateInstance)value, diagnostics, context);
			case TdlPackage.COMPONENT_INSTANCE:
				return validateComponentInstance((ComponentInstance)value, diagnostics, context);
			case TdlPackage.WAIT:
				return validateWait((Wait)value, diagnostics, context);
			case TdlPackage.QUIESCENCE:
				return validateQuiescence((Quiescence)value, diagnostics, context);
			case TdlPackage.TEST_CONFIGURATION:
				return validateTestConfiguration((TestConfiguration)value, diagnostics, context);
			case TdlPackage.CONNECTION:
				return validateConnection((Connection)value, diagnostics, context);
			case TdlPackage.TEST_DESCRIPTION:
				return validateTestDescription((TestDescription)value, diagnostics, context);
			case TdlPackage.COMPOUND_BEHAVIOUR:
				return validateCompoundBehaviour((CompoundBehaviour)value, diagnostics, context);
			case TdlPackage.SINGLE_COMBINED_BEHAVIOUR:
				return validateSingleCombinedBehaviour((SingleCombinedBehaviour)value, diagnostics, context);
			case TdlPackage.COMBINED_BEHAVIOUR:
				return validateCombinedBehaviour((CombinedBehaviour)value, diagnostics, context);
			case TdlPackage.PERIODIC_BEHAVIOUR:
				return validatePeriodicBehaviour((PeriodicBehaviour)value, diagnostics, context);
			case TdlPackage.BLOCK:
				return validateBlock((Block)value, diagnostics, context);
			case TdlPackage.EXCEPTIONAL_BEHAVIOUR:
				return validateExceptionalBehaviour((ExceptionalBehaviour)value, diagnostics, context);
			case TdlPackage.ALTERNATIVE_BEHAVIOUR:
				return validateAlternativeBehaviour((AlternativeBehaviour)value, diagnostics, context);
			case TdlPackage.MULTIPLE_COMBINED_BEHAVIOUR:
				return validateMultipleCombinedBehaviour((MultipleCombinedBehaviour)value, diagnostics, context);
			case TdlPackage.PARALLEL_BEHAVIOUR:
				return validateParallelBehaviour((ParallelBehaviour)value, diagnostics, context);
			case TdlPackage.BOUNDED_LOOP_BEHAVIOUR:
				return validateBoundedLoopBehaviour((BoundedLoopBehaviour)value, diagnostics, context);
			case TdlPackage.UNBOUNDED_LOOP_BEHAVIOUR:
				return validateUnboundedLoopBehaviour((UnboundedLoopBehaviour)value, diagnostics, context);
			case TdlPackage.CONDITIONAL_BEHAVIOUR:
				return validateConditionalBehaviour((ConditionalBehaviour)value, diagnostics, context);
			case TdlPackage.STOP:
				return validateStop((Stop)value, diagnostics, context);
			case TdlPackage.TEST_DESCRIPTION_REFERENCE:
				return validateTestDescriptionReference((TestDescriptionReference)value, diagnostics, context);
			case TdlPackage.ARGUMENT_SPECIFICATION:
				return validateArgumentSpecification((ArgumentSpecification)value, diagnostics, context);
			case TdlPackage.VERDICT_ASSIGNMENT:
				return validateVerdictAssignment((VerdictAssignment)value, diagnostics, context);
			case TdlPackage.VERDICT_TYPE:
				return validateVerdictType((VerdictType)value, diagnostics, context);
			case TdlPackage.ACTION_REFERENCE:
				return validateActionReference((ActionReference)value, diagnostics, context);
			case TdlPackage.ACTION:
				return validateAction((Action)value, diagnostics, context);
			case TdlPackage.INTERACTION:
				return validateInteraction((Interaction)value, diagnostics, context);
			case TdlPackage.OPTIONAL_BEHAVIOUR:
				return validateOptionalBehaviour((OptionalBehaviour)value, diagnostics, context);
			case TdlPackage.DEFAULT_BEHAVIOUR:
				return validateDefaultBehaviour((DefaultBehaviour)value, diagnostics, context);
			case TdlPackage.INTERRUPT_BEHAVIOUR:
				return validateInterruptBehaviour((InterruptBehaviour)value, diagnostics, context);
			case TdlPackage.BREAK:
				return validateBreak((Break)value, diagnostics, context);
			case TdlPackage.DATA_INSTANCE_ARGUMENT_SPECIFICATION:
				return validateDataInstanceArgumentSpecification((DataInstanceArgumentSpecification)value, diagnostics, context);
			case TdlPackage.DATA_PROXY_ARGUMENT_SPECIFICATION:
				return validateDataProxyArgumentSpecification((DataProxyArgumentSpecification)value, diagnostics, context);
			case TdlPackage.DATA_SET_ARGUMENT_SPECIFICATION:
				return validateDataSetArgumentSpecification((DataSetArgumentSpecification)value, diagnostics, context);
			case TdlPackage.COMPONENT_INSTANCE_ROLE:
				return validateComponentInstanceRole((ComponentInstanceRole)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateElement(Element element, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(element, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateComment(Comment comment, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(comment, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAnnotation(Annotation annotation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(annotation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAnnotationType(AnnotationType annotationType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(annotationType, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(annotationType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(annotationType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(annotationType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(annotationType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(annotationType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(annotationType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(annotationType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(annotationType, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageableElement_invQualifiednamesmustbedistinguishable(annotationType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePackageableElement(PackageableElement packageableElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(packageableElement, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(packageableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(packageableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(packageableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(packageableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(packageableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(packageableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(packageableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(packageableElement, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageableElement_invQualifiednamesmustbedistinguishable(packageableElement, diagnostics, context);
		return result;
	}

	/**
	 * Validates the invQualifiednamesmustbedistinguishable constraint of '<em>Packageable Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePackageableElement_invQualifiednamesmustbedistinguishable(PackageableElement packageableElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return packageableElement.invQualifiednamesmustbedistinguishable(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePackage(org.etsi.mts.tdl.Package package_, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(package_, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageableElement_invQualifiednamesmustbedistinguishable(package_, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackage_invCyclicImportsNotAllowed(package_, diagnostics, context);
		return result;
	}

	/**
	 * Validates the invCyclicImportsNotAllowed constraint of '<em>Package</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePackage_invCyclicImportsNotAllowed(org.etsi.mts.tdl.Package package_, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return package_.invCyclicImportsNotAllowed(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateElementImport(ElementImport elementImport, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(elementImport, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTestObjective(TestObjective testObjective, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(testObjective, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(testObjective, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(testObjective, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(testObjective, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(testObjective, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(testObjective, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(testObjective, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(testObjective, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(testObjective, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageableElement_invQualifiednamesmustbedistinguishable(testObjective, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTestObjectiveRealizer(TestObjectiveRealizer testObjectiveRealizer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(testObjectiveRealizer, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDataInstance(DataInstance dataInstance, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(dataInstance, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMappableDataElement(MappableDataElement mappableDataElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(mappableDataElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDataElement(DataElement dataElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(dataElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDataSet(DataSet dataSet, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(dataSet, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(dataSet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(dataSet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(dataSet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(dataSet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(dataSet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(dataSet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(dataSet, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(dataSet, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageableElement_invQualifiednamesmustbedistinguishable(dataSet, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDataResourceMapping(DataResourceMapping dataResourceMapping, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(dataResourceMapping, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(dataResourceMapping, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(dataResourceMapping, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(dataResourceMapping, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(dataResourceMapping, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(dataResourceMapping, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(dataResourceMapping, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(dataResourceMapping, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(dataResourceMapping, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageableElement_invQualifiednamesmustbedistinguishable(dataResourceMapping, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDataElementMapping(DataElementMapping dataElementMapping, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(dataElementMapping, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(dataElementMapping, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(dataElementMapping, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(dataElementMapping, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(dataElementMapping, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(dataElementMapping, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(dataElementMapping, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(dataElementMapping, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(dataElementMapping, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageableElement_invQualifiednamesmustbedistinguishable(dataElementMapping, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDataProxy(DataProxy dataProxy, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(dataProxy, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTimer(Timer timer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(timer, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateComponentType(ComponentType componentType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(componentType, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(componentType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(componentType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(componentType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(componentType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(componentType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(componentType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(componentType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(componentType, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageableElement_invQualifiednamesmustbedistinguishable(componentType, diagnostics, context);
		if (result || diagnostics != null) result &= validateComponentType_invTestConfigurationandComponentsRoles(componentType, diagnostics, context);
		return result;
	}

	/**
	 * Validates the invTestConfigurationandComponentsRoles constraint of '<em>Component Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateComponentType_invTestConfigurationandComponentsRoles(ComponentType componentType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return componentType.invTestConfigurationandComponentsRoles(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGateType(GateType gateType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(gateType, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(gateType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(gateType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(gateType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(gateType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(gateType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(gateType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(gateType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(gateType, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageableElement_invQualifiednamesmustbedistinguishable(gateType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTimerOperation(TimerOperation timerOperation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(timerOperation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAtomicBehaviour(AtomicBehaviour atomicBehaviour, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(atomicBehaviour, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBehaviour(Behaviour behaviour, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(behaviour, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTimeConstraint(TimeConstraint timeConstraint, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(timeConstraint, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTimerStart(TimerStart timerStart, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(timerStart, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTime(Time time, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(time, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTimeUnit(TimeUnit timeUnit, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(timeUnit, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(timeUnit, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(timeUnit, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(timeUnit, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(timeUnit, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(timeUnit, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(timeUnit, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(timeUnit, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(timeUnit, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageableElement_invQualifiednamesmustbedistinguishable(timeUnit, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTimerStop(TimerStop timerStop, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(timerStop, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTimeOut(TimeOut timeOut, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(timeOut, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTimeOperation(TimeOperation timeOperation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(timeOperation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateGateInstance(GateInstance gateInstance, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(gateInstance, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateComponentInstance(ComponentInstance componentInstance, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(componentInstance, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(componentInstance, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(componentInstance, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(componentInstance, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(componentInstance, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(componentInstance, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(componentInstance, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(componentInstance, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(componentInstance, diagnostics, context);
		if (result || diagnostics != null) result &= validateComponentInstance_invNumberofGateInstances(componentInstance, diagnostics, context);
		if (result || diagnostics != null) result &= validateComponentInstance_invTimersofComponentInstances(componentInstance, diagnostics, context);
		return result;
	}

	/**
	 * Validates the invNumberofGateInstances constraint of '<em>Component Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateComponentInstance_invNumberofGateInstances(ComponentInstance componentInstance, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return componentInstance.invNumberofGateInstances(diagnostics, context);
	}

	/**
	 * Validates the invTimersofComponentInstances constraint of '<em>Component Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateComponentInstance_invTimersofComponentInstances(ComponentInstance componentInstance, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return componentInstance.invTimersofComponentInstances(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWait(Wait wait, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(wait, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateQuiescence(Quiescence quiescence, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(quiescence, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTestConfiguration(TestConfiguration testConfiguration, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(testConfiguration, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(testConfiguration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(testConfiguration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(testConfiguration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(testConfiguration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(testConfiguration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(testConfiguration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(testConfiguration, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(testConfiguration, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageableElement_invQualifiednamesmustbedistinguishable(testConfiguration, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConnection(Connection connection, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(connection, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(connection, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(connection, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(connection, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(connection, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(connection, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(connection, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(connection, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(connection, diagnostics, context);
		if (result || diagnostics != null) result &= validateConnection_invSelfloopedConnections(connection, diagnostics, context);
		if (result || diagnostics != null) result &= validateConnection_invOnlyoneconnectionallowed(connection, diagnostics, context);
		return result;
	}

	/**
	 * Validates the invSelfloopedConnections constraint of '<em>Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConnection_invSelfloopedConnections(Connection connection, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return connection.invSelfloopedConnections(diagnostics, context);
	}

	/**
	 * Validates the invOnlyoneconnectionallowed constraint of '<em>Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConnection_invOnlyoneconnectionallowed(Connection connection, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return connection.invOnlyoneconnectionallowed(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTestDescription(TestDescription testDescription, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(testDescription, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(testDescription, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(testDescription, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(testDescription, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(testDescription, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(testDescription, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(testDescription, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(testDescription, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(testDescription, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageableElement_invQualifiednamesmustbedistinguishable(testDescription, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCompoundBehaviour(CompoundBehaviour compoundBehaviour, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(compoundBehaviour, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSingleCombinedBehaviour(SingleCombinedBehaviour singleCombinedBehaviour, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(singleCombinedBehaviour, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCombinedBehaviour(CombinedBehaviour combinedBehaviour, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(combinedBehaviour, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePeriodicBehaviour(PeriodicBehaviour periodicBehaviour, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(periodicBehaviour, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBlock(Block block, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(block, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExceptionalBehaviour(ExceptionalBehaviour exceptionalBehaviour, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(exceptionalBehaviour, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAlternativeBehaviour(AlternativeBehaviour alternativeBehaviour, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(alternativeBehaviour, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMultipleCombinedBehaviour(MultipleCombinedBehaviour multipleCombinedBehaviour, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(multipleCombinedBehaviour, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateParallelBehaviour(ParallelBehaviour parallelBehaviour, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(parallelBehaviour, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBoundedLoopBehaviour(BoundedLoopBehaviour boundedLoopBehaviour, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(boundedLoopBehaviour, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(boundedLoopBehaviour, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(boundedLoopBehaviour, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(boundedLoopBehaviour, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(boundedLoopBehaviour, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(boundedLoopBehaviour, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(boundedLoopBehaviour, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(boundedLoopBehaviour, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(boundedLoopBehaviour, diagnostics, context);
		if (result || diagnostics != null) result &= validateBoundedLoopBehaviour_invBoundedLoopmustnothaveaguard(boundedLoopBehaviour, diagnostics, context);
		return result;
	}

	/**
	 * Validates the invBoundedLoopmustnothaveaguard constraint of '<em>Bounded Loop Behaviour</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBoundedLoopBehaviour_invBoundedLoopmustnothaveaguard(BoundedLoopBehaviour boundedLoopBehaviour, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return boundedLoopBehaviour.invBoundedLoopmustnothaveaguard(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateUnboundedLoopBehaviour(UnboundedLoopBehaviour unboundedLoopBehaviour, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(unboundedLoopBehaviour, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateConditionalBehaviour(ConditionalBehaviour conditionalBehaviour, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(conditionalBehaviour, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStop(Stop stop, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(stop, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTestDescriptionReference(TestDescriptionReference testDescriptionReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(testDescriptionReference, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateArgumentSpecification(ArgumentSpecification argumentSpecification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(argumentSpecification, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateVerdictAssignment(VerdictAssignment verdictAssignment, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(verdictAssignment, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateVerdictType(VerdictType verdictType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(verdictType, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(verdictType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(verdictType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(verdictType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(verdictType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(verdictType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(verdictType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(verdictType, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(verdictType, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageableElement_invQualifiednamesmustbedistinguishable(verdictType, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateActionReference(ActionReference actionReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(actionReference, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAction(Action action, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(action, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(action, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(action, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(action, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(action, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(action, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(action, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(action, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(action, diagnostics, context);
		if (result || diagnostics != null) result &= validatePackageableElement_invQualifiednamesmustbedistinguishable(action, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInteraction(Interaction interaction, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(interaction, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(interaction, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(interaction, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(interaction, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(interaction, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(interaction, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(interaction, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(interaction, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(interaction, diagnostics, context);
		if (result || diagnostics != null) result &= validateInteraction_invConstraint1(interaction, diagnostics, context);
		if (result || diagnostics != null) result &= validateInteraction_invConstraint2(interaction, diagnostics, context);
		if (result || diagnostics != null) result &= validateInteraction_invConstraint3(interaction, diagnostics, context);
		return result;
	}

	/**
	 * Validates the invConstraint1 constraint of '<em>Interaction</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInteraction_invConstraint1(Interaction interaction, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return interaction.invConstraint1(diagnostics, context);
	}

	/**
	 * Validates the invConstraint2 constraint of '<em>Interaction</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInteraction_invConstraint2(Interaction interaction, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return interaction.invConstraint2(diagnostics, context);
	}

	/**
	 * Validates the invConstraint3 constraint of '<em>Interaction</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInteraction_invConstraint3(Interaction interaction, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return interaction.invConstraint3(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOptionalBehaviour(OptionalBehaviour optionalBehaviour, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(optionalBehaviour, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDefaultBehaviour(DefaultBehaviour defaultBehaviour, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(defaultBehaviour, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInterruptBehaviour(InterruptBehaviour interruptBehaviour, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(interruptBehaviour, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBreak(Break break_, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(break_, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDataInstanceArgumentSpecification(DataInstanceArgumentSpecification dataInstanceArgumentSpecification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(dataInstanceArgumentSpecification, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDataProxyArgumentSpecification(DataProxyArgumentSpecification dataProxyArgumentSpecification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(dataProxyArgumentSpecification, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDataSetArgumentSpecification(DataSetArgumentSpecification dataSetArgumentSpecification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(dataSetArgumentSpecification, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateComponentInstanceRole(ComponentInstanceRole componentInstanceRole, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //TdlValidator
