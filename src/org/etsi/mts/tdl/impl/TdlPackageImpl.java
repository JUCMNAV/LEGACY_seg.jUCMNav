/**
 */
package org.etsi.mts.tdl.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.uml2.types.TypesPackage;

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
import org.etsi.mts.tdl.TdlFactory;
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

import org.etsi.mts.tdl.util.TdlValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TdlPackageImpl extends EPackageImpl implements TdlPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass commentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass annotationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass annotationTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageableElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementImportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testObjectiveEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testObjectiveRealizerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mappableDataElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataResourceMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataElementMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataProxyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gateTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timerOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass atomicBehaviourEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass behaviourEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timeConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timerStartEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timeUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timerStopEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timeOutEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass timeOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gateInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass componentInstanceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass waitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass quiescenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testConfigurationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testDescriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compoundBehaviourEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass singleCombinedBehaviourEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass combinedBehaviourEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass periodicBehaviourEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass blockEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exceptionalBehaviourEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass alternativeBehaviourEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass multipleCombinedBehaviourEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parallelBehaviourEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass boundedLoopBehaviourEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unboundedLoopBehaviourEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conditionalBehaviourEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stopEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass testDescriptionReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass argumentSpecificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass verdictAssignmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass verdictTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interactionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass optionalBehaviourEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass defaultBehaviourEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interruptBehaviourEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass breakEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataInstanceArgumentSpecificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataProxyArgumentSpecificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataSetArgumentSpecificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum componentInstanceRoleEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.etsi.mts.tdl.TdlPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TdlPackageImpl() {
		super(eNS_URI, TdlFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link TdlPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TdlPackage init() {
		if (isInited) return (TdlPackage)EPackage.Registry.INSTANCE.getEPackage(TdlPackage.eNS_URI);

		// Obtain or create and register package
		TdlPackageImpl theTdlPackage = (TdlPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof TdlPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new TdlPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		TypesPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theTdlPackage.createPackageContents();

		// Initialize created meta-data
		theTdlPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theTdlPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return TdlValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theTdlPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TdlPackage.eNS_URI, theTdlPackage);
		return theTdlPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getElement() {
		return elementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElement_Comment() {
		return (EReference)elementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElement_Annotation() {
		return (EReference)elementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getElement_Name() {
		return (EAttribute)elementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComment() {
		return commentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComment_Body() {
		return (EAttribute)commentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComment_CommentedElement() {
		return (EReference)commentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnnotation() {
		return annotationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAnnotation_Value() {
		return (EAttribute)annotationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnnotation_Key() {
		return (EReference)annotationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAnnotation_AnnotatedElement() {
		return (EReference)annotationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnnotationType() {
		return annotationTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPackageableElement() {
		return packageableElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPackageableElement_QualifiedName() {
		return (EAttribute)packageableElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackageableElement_OwningPackage() {
		return (EReference)packageableElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPackage() {
		return packageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_Import() {
		return (EReference)packageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPackage_PackagedElements() {
		return (EReference)packageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getElementImport() {
		return elementImportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getElementImport_ImportedElement() {
		return (EReference)elementImportEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestObjective() {
		return testObjectiveEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestObjective_ObjectiveURI() {
		return (EAttribute)testObjectiveEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTestObjective_Description() {
		return (EAttribute)testObjectiveEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestObjectiveRealizer() {
		return testObjectiveRealizerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestObjectiveRealizer_TestObjective() {
		return (EReference)testObjectiveRealizerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataInstance() {
		return dataInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataInstance_Parameter() {
		return (EReference)dataInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMappableDataElement() {
		return mappableDataElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataElement() {
		return dataElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataSet() {
		return dataSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataSet_DataInstance() {
		return (EReference)dataSetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataResourceMapping() {
		return dataResourceMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataResourceMapping_ResourceURI() {
		return (EAttribute)dataResourceMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataElementMapping() {
		return dataElementMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataElementMapping_MappableDataElement() {
		return (EReference)dataElementMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDataElementMapping_ElementURI() {
		return (EAttribute)dataElementMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataElementMapping_DataResourceMapping() {
		return (EReference)dataElementMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataProxy() {
		return dataProxyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataProxy_DataSet() {
		return (EReference)dataProxyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimer() {
		return timerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimer_ComponentType() {
		return (EReference)timerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComponentType() {
		return componentTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentType_GateType() {
		return (EReference)componentTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentType_Timer() {
		return (EReference)componentTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGateType() {
		return gateTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGateType_DataSet() {
		return (EReference)gateTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimerOperation() {
		return timerOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimerOperation_Timer() {
		return (EReference)timerOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAtomicBehaviour() {
		return atomicBehaviourEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAtomicBehaviour_TimeConstraint() {
		return (EReference)atomicBehaviourEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBehaviour() {
		return behaviourEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimeConstraint() {
		return timeConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTimeConstraint_Expression() {
		return (EAttribute)timeConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimeConstraint_AtomicBehaviour() {
		return (EReference)timeConstraintEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimerStart() {
		return timerStartEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimerStart_Period() {
		return (EReference)timerStartEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTime() {
		return timeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTime_Value() {
		return (EAttribute)timeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTime_Unit() {
		return (EReference)timeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimeUnit() {
		return timeUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimerStop() {
		return timerStopEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimeOut() {
		return timeOutEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTimeOperation() {
		return timeOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimeOperation_GateInstance() {
		return (EReference)timeOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimeOperation_Period() {
		return (EReference)timeOperationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTimeOperation_ComponentInstance() {
		return (EReference)timeOperationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGateInstance() {
		return gateInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGateInstance_Type() {
		return (EReference)gateInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGateInstance_ComponentInstance() {
		return (EReference)gateInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getComponentInstance() {
		return componentInstanceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentInstance_Type() {
		return (EReference)componentInstanceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getComponentInstance_Role() {
		return (EAttribute)componentInstanceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentInstance_Timer() {
		return (EReference)componentInstanceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getComponentInstance_GateInstance() {
		return (EReference)componentInstanceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWait() {
		return waitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQuiescence() {
		return quiescenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestConfiguration() {
		return testConfigurationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestConfiguration_ComponentInstance() {
		return (EReference)testConfigurationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestConfiguration_Connection() {
		return (EReference)testConfigurationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConnection() {
		return connectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConnection_EndPoint() {
		return (EReference)connectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestDescription() {
		return testDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestDescription_TestConfiguration() {
		return (EReference)testDescriptionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestDescription_Behaviour() {
		return (EReference)testDescriptionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestDescription_TimeConstraint() {
		return (EReference)testDescriptionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestDescription_FormalParameter() {
		return (EReference)testDescriptionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCompoundBehaviour() {
		return compoundBehaviourEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSingleCombinedBehaviour() {
		return singleCombinedBehaviourEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSingleCombinedBehaviour_Block() {
		return (EReference)singleCombinedBehaviourEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCombinedBehaviour() {
		return combinedBehaviourEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCombinedBehaviour_Periodic() {
		return (EReference)combinedBehaviourEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCombinedBehaviour_Exceptional() {
		return (EReference)combinedBehaviourEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPeriodicBehaviour() {
		return periodicBehaviourEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPeriodicBehaviour_Block() {
		return (EReference)periodicBehaviourEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getPeriodicBehaviour_Period() {
		return (EReference)periodicBehaviourEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBlock() {
		return blockEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBlock_Behaviour() {
		return (EReference)blockEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBlock_Guard() {
		return (EAttribute)blockEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExceptionalBehaviour() {
		return exceptionalBehaviourEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExceptionalBehaviour_Block() {
		return (EReference)exceptionalBehaviourEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getExceptionalBehaviour_GuardedComponent() {
		return (EReference)exceptionalBehaviourEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAlternativeBehaviour() {
		return alternativeBehaviourEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMultipleCombinedBehaviour() {
		return multipleCombinedBehaviourEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMultipleCombinedBehaviour_Block() {
		return (EReference)multipleCombinedBehaviourEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParallelBehaviour() {
		return parallelBehaviourEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBoundedLoopBehaviour() {
		return boundedLoopBehaviourEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getBoundedLoopBehaviour_NumIteration() {
		return (EAttribute)boundedLoopBehaviourEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnboundedLoopBehaviour() {
		return unboundedLoopBehaviourEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConditionalBehaviour() {
		return conditionalBehaviourEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStop() {
		return stopEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTestDescriptionReference() {
		return testDescriptionReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestDescriptionReference_ReferencedTestDescription() {
		return (EReference)testDescriptionReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTestDescriptionReference_ActualParameter() {
		return (EReference)testDescriptionReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getArgumentSpecification() {
		return argumentSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVerdictAssignment() {
		return verdictAssignmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVerdictAssignment_VerdictType() {
		return (EReference)verdictAssignmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVerdictType() {
		return verdictTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getActionReference() {
		return actionReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActionReference_ComponentInstance() {
		return (EReference)actionReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getActionReference_Action() {
		return (EReference)actionReferenceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAction() {
		return actionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAction_Body() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInteraction() {
		return interactionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInteraction_Argument() {
		return (EReference)interactionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInteraction_Source() {
		return (EReference)interactionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInteraction_Target() {
		return (EReference)interactionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOptionalBehaviour() {
		return optionalBehaviourEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDefaultBehaviour() {
		return defaultBehaviourEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInterruptBehaviour() {
		return interruptBehaviourEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBreak() {
		return breakEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataInstanceArgumentSpecification() {
		return dataInstanceArgumentSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataInstanceArgumentSpecification_DataInstance() {
		return (EReference)dataInstanceArgumentSpecificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataInstanceArgumentSpecification_ActualParameter() {
		return (EReference)dataInstanceArgumentSpecificationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataProxyArgumentSpecification() {
		return dataProxyArgumentSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataProxyArgumentSpecification_DataProxy() {
		return (EReference)dataProxyArgumentSpecificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDataSetArgumentSpecification() {
		return dataSetArgumentSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDataSetArgumentSpecification_DataSet() {
		return (EReference)dataSetArgumentSpecificationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getComponentInstanceRole() {
		return componentInstanceRoleEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TdlFactory getTdlFactory() {
		return (TdlFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		elementEClass = createEClass(ELEMENT);
		createEReference(elementEClass, ELEMENT__COMMENT);
		createEReference(elementEClass, ELEMENT__ANNOTATION);
		createEAttribute(elementEClass, ELEMENT__NAME);

		commentEClass = createEClass(COMMENT);
		createEAttribute(commentEClass, COMMENT__BODY);
		createEReference(commentEClass, COMMENT__COMMENTED_ELEMENT);

		annotationEClass = createEClass(ANNOTATION);
		createEAttribute(annotationEClass, ANNOTATION__VALUE);
		createEReference(annotationEClass, ANNOTATION__KEY);
		createEReference(annotationEClass, ANNOTATION__ANNOTATED_ELEMENT);

		annotationTypeEClass = createEClass(ANNOTATION_TYPE);

		packageableElementEClass = createEClass(PACKAGEABLE_ELEMENT);
		createEAttribute(packageableElementEClass, PACKAGEABLE_ELEMENT__QUALIFIED_NAME);
		createEReference(packageableElementEClass, PACKAGEABLE_ELEMENT__OWNING_PACKAGE);

		packageEClass = createEClass(PACKAGE);
		createEReference(packageEClass, PACKAGE__IMPORT);
		createEReference(packageEClass, PACKAGE__PACKAGED_ELEMENTS);

		elementImportEClass = createEClass(ELEMENT_IMPORT);
		createEReference(elementImportEClass, ELEMENT_IMPORT__IMPORTED_ELEMENT);

		testObjectiveEClass = createEClass(TEST_OBJECTIVE);
		createEAttribute(testObjectiveEClass, TEST_OBJECTIVE__OBJECTIVE_URI);
		createEAttribute(testObjectiveEClass, TEST_OBJECTIVE__DESCRIPTION);

		testObjectiveRealizerEClass = createEClass(TEST_OBJECTIVE_REALIZER);
		createEReference(testObjectiveRealizerEClass, TEST_OBJECTIVE_REALIZER__TEST_OBJECTIVE);

		dataInstanceEClass = createEClass(DATA_INSTANCE);
		createEReference(dataInstanceEClass, DATA_INSTANCE__PARAMETER);

		mappableDataElementEClass = createEClass(MAPPABLE_DATA_ELEMENT);

		dataElementEClass = createEClass(DATA_ELEMENT);

		dataSetEClass = createEClass(DATA_SET);
		createEReference(dataSetEClass, DATA_SET__DATA_INSTANCE);

		dataResourceMappingEClass = createEClass(DATA_RESOURCE_MAPPING);
		createEAttribute(dataResourceMappingEClass, DATA_RESOURCE_MAPPING__RESOURCE_URI);

		dataElementMappingEClass = createEClass(DATA_ELEMENT_MAPPING);
		createEReference(dataElementMappingEClass, DATA_ELEMENT_MAPPING__MAPPABLE_DATA_ELEMENT);
		createEAttribute(dataElementMappingEClass, DATA_ELEMENT_MAPPING__ELEMENT_URI);
		createEReference(dataElementMappingEClass, DATA_ELEMENT_MAPPING__DATA_RESOURCE_MAPPING);

		dataProxyEClass = createEClass(DATA_PROXY);
		createEReference(dataProxyEClass, DATA_PROXY__DATA_SET);

		timerEClass = createEClass(TIMER);
		createEReference(timerEClass, TIMER__COMPONENT_TYPE);

		componentTypeEClass = createEClass(COMPONENT_TYPE);
		createEReference(componentTypeEClass, COMPONENT_TYPE__GATE_TYPE);
		createEReference(componentTypeEClass, COMPONENT_TYPE__TIMER);

		gateTypeEClass = createEClass(GATE_TYPE);
		createEReference(gateTypeEClass, GATE_TYPE__DATA_SET);

		timerOperationEClass = createEClass(TIMER_OPERATION);
		createEReference(timerOperationEClass, TIMER_OPERATION__TIMER);

		atomicBehaviourEClass = createEClass(ATOMIC_BEHAVIOUR);
		createEReference(atomicBehaviourEClass, ATOMIC_BEHAVIOUR__TIME_CONSTRAINT);

		behaviourEClass = createEClass(BEHAVIOUR);

		timeConstraintEClass = createEClass(TIME_CONSTRAINT);
		createEAttribute(timeConstraintEClass, TIME_CONSTRAINT__EXPRESSION);
		createEReference(timeConstraintEClass, TIME_CONSTRAINT__ATOMIC_BEHAVIOUR);

		timerStartEClass = createEClass(TIMER_START);
		createEReference(timerStartEClass, TIMER_START__PERIOD);

		timeEClass = createEClass(TIME);
		createEAttribute(timeEClass, TIME__VALUE);
		createEReference(timeEClass, TIME__UNIT);

		timeUnitEClass = createEClass(TIME_UNIT);

		timerStopEClass = createEClass(TIMER_STOP);

		timeOutEClass = createEClass(TIME_OUT);

		timeOperationEClass = createEClass(TIME_OPERATION);
		createEReference(timeOperationEClass, TIME_OPERATION__GATE_INSTANCE);
		createEReference(timeOperationEClass, TIME_OPERATION__PERIOD);
		createEReference(timeOperationEClass, TIME_OPERATION__COMPONENT_INSTANCE);

		gateInstanceEClass = createEClass(GATE_INSTANCE);
		createEReference(gateInstanceEClass, GATE_INSTANCE__TYPE);
		createEReference(gateInstanceEClass, GATE_INSTANCE__COMPONENT_INSTANCE);

		componentInstanceEClass = createEClass(COMPONENT_INSTANCE);
		createEReference(componentInstanceEClass, COMPONENT_INSTANCE__TYPE);
		createEAttribute(componentInstanceEClass, COMPONENT_INSTANCE__ROLE);
		createEReference(componentInstanceEClass, COMPONENT_INSTANCE__TIMER);
		createEReference(componentInstanceEClass, COMPONENT_INSTANCE__GATE_INSTANCE);

		waitEClass = createEClass(WAIT);

		quiescenceEClass = createEClass(QUIESCENCE);

		testConfigurationEClass = createEClass(TEST_CONFIGURATION);
		createEReference(testConfigurationEClass, TEST_CONFIGURATION__COMPONENT_INSTANCE);
		createEReference(testConfigurationEClass, TEST_CONFIGURATION__CONNECTION);

		connectionEClass = createEClass(CONNECTION);
		createEReference(connectionEClass, CONNECTION__END_POINT);

		testDescriptionEClass = createEClass(TEST_DESCRIPTION);
		createEReference(testDescriptionEClass, TEST_DESCRIPTION__TEST_CONFIGURATION);
		createEReference(testDescriptionEClass, TEST_DESCRIPTION__BEHAVIOUR);
		createEReference(testDescriptionEClass, TEST_DESCRIPTION__TIME_CONSTRAINT);
		createEReference(testDescriptionEClass, TEST_DESCRIPTION__FORMAL_PARAMETER);

		compoundBehaviourEClass = createEClass(COMPOUND_BEHAVIOUR);

		singleCombinedBehaviourEClass = createEClass(SINGLE_COMBINED_BEHAVIOUR);
		createEReference(singleCombinedBehaviourEClass, SINGLE_COMBINED_BEHAVIOUR__BLOCK);

		combinedBehaviourEClass = createEClass(COMBINED_BEHAVIOUR);
		createEReference(combinedBehaviourEClass, COMBINED_BEHAVIOUR__PERIODIC);
		createEReference(combinedBehaviourEClass, COMBINED_BEHAVIOUR__EXCEPTIONAL);

		periodicBehaviourEClass = createEClass(PERIODIC_BEHAVIOUR);
		createEReference(periodicBehaviourEClass, PERIODIC_BEHAVIOUR__BLOCK);
		createEReference(periodicBehaviourEClass, PERIODIC_BEHAVIOUR__PERIOD);

		blockEClass = createEClass(BLOCK);
		createEReference(blockEClass, BLOCK__BEHAVIOUR);
		createEAttribute(blockEClass, BLOCK__GUARD);

		exceptionalBehaviourEClass = createEClass(EXCEPTIONAL_BEHAVIOUR);
		createEReference(exceptionalBehaviourEClass, EXCEPTIONAL_BEHAVIOUR__BLOCK);
		createEReference(exceptionalBehaviourEClass, EXCEPTIONAL_BEHAVIOUR__GUARDED_COMPONENT);

		alternativeBehaviourEClass = createEClass(ALTERNATIVE_BEHAVIOUR);

		multipleCombinedBehaviourEClass = createEClass(MULTIPLE_COMBINED_BEHAVIOUR);
		createEReference(multipleCombinedBehaviourEClass, MULTIPLE_COMBINED_BEHAVIOUR__BLOCK);

		parallelBehaviourEClass = createEClass(PARALLEL_BEHAVIOUR);

		boundedLoopBehaviourEClass = createEClass(BOUNDED_LOOP_BEHAVIOUR);
		createEAttribute(boundedLoopBehaviourEClass, BOUNDED_LOOP_BEHAVIOUR__NUM_ITERATION);

		unboundedLoopBehaviourEClass = createEClass(UNBOUNDED_LOOP_BEHAVIOUR);

		conditionalBehaviourEClass = createEClass(CONDITIONAL_BEHAVIOUR);

		stopEClass = createEClass(STOP);

		testDescriptionReferenceEClass = createEClass(TEST_DESCRIPTION_REFERENCE);
		createEReference(testDescriptionReferenceEClass, TEST_DESCRIPTION_REFERENCE__REFERENCED_TEST_DESCRIPTION);
		createEReference(testDescriptionReferenceEClass, TEST_DESCRIPTION_REFERENCE__ACTUAL_PARAMETER);

		argumentSpecificationEClass = createEClass(ARGUMENT_SPECIFICATION);

		verdictAssignmentEClass = createEClass(VERDICT_ASSIGNMENT);
		createEReference(verdictAssignmentEClass, VERDICT_ASSIGNMENT__VERDICT_TYPE);

		verdictTypeEClass = createEClass(VERDICT_TYPE);

		actionReferenceEClass = createEClass(ACTION_REFERENCE);
		createEReference(actionReferenceEClass, ACTION_REFERENCE__COMPONENT_INSTANCE);
		createEReference(actionReferenceEClass, ACTION_REFERENCE__ACTION);

		actionEClass = createEClass(ACTION);
		createEAttribute(actionEClass, ACTION__BODY);

		interactionEClass = createEClass(INTERACTION);
		createEReference(interactionEClass, INTERACTION__ARGUMENT);
		createEReference(interactionEClass, INTERACTION__SOURCE);
		createEReference(interactionEClass, INTERACTION__TARGET);

		optionalBehaviourEClass = createEClass(OPTIONAL_BEHAVIOUR);

		defaultBehaviourEClass = createEClass(DEFAULT_BEHAVIOUR);

		interruptBehaviourEClass = createEClass(INTERRUPT_BEHAVIOUR);

		breakEClass = createEClass(BREAK);

		dataInstanceArgumentSpecificationEClass = createEClass(DATA_INSTANCE_ARGUMENT_SPECIFICATION);
		createEReference(dataInstanceArgumentSpecificationEClass, DATA_INSTANCE_ARGUMENT_SPECIFICATION__DATA_INSTANCE);
		createEReference(dataInstanceArgumentSpecificationEClass, DATA_INSTANCE_ARGUMENT_SPECIFICATION__ACTUAL_PARAMETER);

		dataProxyArgumentSpecificationEClass = createEClass(DATA_PROXY_ARGUMENT_SPECIFICATION);
		createEReference(dataProxyArgumentSpecificationEClass, DATA_PROXY_ARGUMENT_SPECIFICATION__DATA_PROXY);

		dataSetArgumentSpecificationEClass = createEClass(DATA_SET_ARGUMENT_SPECIFICATION);
		createEReference(dataSetArgumentSpecificationEClass, DATA_SET_ARGUMENT_SPECIFICATION__DATA_SET);

		// Create enums
		componentInstanceRoleEEnum = createEEnum(COMPONENT_INSTANCE_ROLE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		TypesPackage theTypesPackage = (TypesPackage)EPackage.Registry.INSTANCE.getEPackage(TypesPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		commentEClass.getESuperTypes().add(this.getElement());
		annotationEClass.getESuperTypes().add(this.getElement());
		annotationTypeEClass.getESuperTypes().add(this.getPackageableElement());
		packageableElementEClass.getESuperTypes().add(this.getElement());
		packageEClass.getESuperTypes().add(this.getPackageableElement());
		elementImportEClass.getESuperTypes().add(this.getElement());
		testObjectiveEClass.getESuperTypes().add(this.getPackageableElement());
		testObjectiveRealizerEClass.getESuperTypes().add(this.getElement());
		dataInstanceEClass.getESuperTypes().add(this.getMappableDataElement());
		mappableDataElementEClass.getESuperTypes().add(this.getDataElement());
		dataElementEClass.getESuperTypes().add(this.getElement());
		dataSetEClass.getESuperTypes().add(this.getPackageableElement());
		dataSetEClass.getESuperTypes().add(this.getMappableDataElement());
		dataResourceMappingEClass.getESuperTypes().add(this.getPackageableElement());
		dataElementMappingEClass.getESuperTypes().add(this.getPackageableElement());
		dataProxyEClass.getESuperTypes().add(this.getDataElement());
		timerEClass.getESuperTypes().add(this.getElement());
		componentTypeEClass.getESuperTypes().add(this.getPackageableElement());
		gateTypeEClass.getESuperTypes().add(this.getPackageableElement());
		timerOperationEClass.getESuperTypes().add(this.getAtomicBehaviour());
		atomicBehaviourEClass.getESuperTypes().add(this.getBehaviour());
		behaviourEClass.getESuperTypes().add(this.getTestObjectiveRealizer());
		timeConstraintEClass.getESuperTypes().add(this.getElement());
		timerStartEClass.getESuperTypes().add(this.getTimerOperation());
		timeEClass.getESuperTypes().add(this.getElement());
		timeUnitEClass.getESuperTypes().add(this.getPackageableElement());
		timerStopEClass.getESuperTypes().add(this.getTimerOperation());
		timeOutEClass.getESuperTypes().add(this.getTimerOperation());
		timeOperationEClass.getESuperTypes().add(this.getAtomicBehaviour());
		gateInstanceEClass.getESuperTypes().add(this.getElement());
		componentInstanceEClass.getESuperTypes().add(this.getElement());
		waitEClass.getESuperTypes().add(this.getTimeOperation());
		quiescenceEClass.getESuperTypes().add(this.getTimeOperation());
		testConfigurationEClass.getESuperTypes().add(this.getPackageableElement());
		connectionEClass.getESuperTypes().add(this.getElement());
		testDescriptionEClass.getESuperTypes().add(this.getPackageableElement());
		testDescriptionEClass.getESuperTypes().add(this.getTestObjectiveRealizer());
		compoundBehaviourEClass.getESuperTypes().add(this.getSingleCombinedBehaviour());
		singleCombinedBehaviourEClass.getESuperTypes().add(this.getCombinedBehaviour());
		combinedBehaviourEClass.getESuperTypes().add(this.getBehaviour());
		periodicBehaviourEClass.getESuperTypes().add(this.getBehaviour());
		blockEClass.getESuperTypes().add(this.getElement());
		exceptionalBehaviourEClass.getESuperTypes().add(this.getBehaviour());
		alternativeBehaviourEClass.getESuperTypes().add(this.getMultipleCombinedBehaviour());
		multipleCombinedBehaviourEClass.getESuperTypes().add(this.getCombinedBehaviour());
		parallelBehaviourEClass.getESuperTypes().add(this.getMultipleCombinedBehaviour());
		boundedLoopBehaviourEClass.getESuperTypes().add(this.getSingleCombinedBehaviour());
		unboundedLoopBehaviourEClass.getESuperTypes().add(this.getSingleCombinedBehaviour());
		conditionalBehaviourEClass.getESuperTypes().add(this.getMultipleCombinedBehaviour());
		stopEClass.getESuperTypes().add(this.getAtomicBehaviour());
		testDescriptionReferenceEClass.getESuperTypes().add(this.getAtomicBehaviour());
		argumentSpecificationEClass.getESuperTypes().add(this.getElement());
		verdictAssignmentEClass.getESuperTypes().add(this.getAtomicBehaviour());
		verdictTypeEClass.getESuperTypes().add(this.getPackageableElement());
		actionReferenceEClass.getESuperTypes().add(this.getAtomicBehaviour());
		actionEClass.getESuperTypes().add(this.getPackageableElement());
		interactionEClass.getESuperTypes().add(this.getAtomicBehaviour());
		optionalBehaviourEClass.getESuperTypes().add(this.getSingleCombinedBehaviour());
		defaultBehaviourEClass.getESuperTypes().add(this.getExceptionalBehaviour());
		interruptBehaviourEClass.getESuperTypes().add(this.getExceptionalBehaviour());
		breakEClass.getESuperTypes().add(this.getAtomicBehaviour());
		dataInstanceArgumentSpecificationEClass.getESuperTypes().add(this.getArgumentSpecification());
		dataProxyArgumentSpecificationEClass.getESuperTypes().add(this.getArgumentSpecification());
		dataSetArgumentSpecificationEClass.getESuperTypes().add(this.getArgumentSpecification());

		// Initialize classes and features; add operations and parameters
		initEClass(elementEClass, Element.class, "Element", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getElement_Comment(), this.getComment(), this.getComment_CommentedElement(), "comment", null, 0, -1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getElement_Annotation(), this.getAnnotation(), this.getAnnotation_AnnotatedElement(), "annotation", null, 0, -1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getElement_Name(), theTypesPackage.getString(), "name", null, 0, 1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(commentEClass, Comment.class, "Comment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComment_Body(), theTypesPackage.getString(), "body", null, 1, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getComment_CommentedElement(), this.getElement(), this.getElement_Comment(), "commentedElement", null, 1, 1, Comment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(annotationEClass, Annotation.class, "Annotation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAnnotation_Value(), theTypesPackage.getString(), "value", null, 0, 1, Annotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAnnotation_Key(), this.getAnnotationType(), null, "key", null, 1, 1, Annotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getAnnotation_AnnotatedElement(), this.getElement(), this.getElement_Annotation(), "annotatedElement", null, 1, 1, Annotation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(annotationTypeEClass, AnnotationType.class, "AnnotationType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(packageableElementEClass, PackageableElement.class, "PackageableElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPackageableElement_QualifiedName(), theTypesPackage.getString(), "qualifiedName", null, 1, 1, PackageableElement.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, !IS_ORDERED);
		initEReference(getPackageableElement_OwningPackage(), this.getPackage(), this.getPackage_PackagedElements(), "owningPackage", null, 0, 1, PackageableElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		EOperation op = addEOperation(packageableElementEClass, ecorePackage.getEBoolean(), "invQualifiednamesmustbedistinguishable", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED);
		EGenericType g1 = createEGenericType(ecorePackage.getEMap());
		EGenericType g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(packageEClass, org.etsi.mts.tdl.Package.class, "Package", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPackage_Import(), this.getElementImport(), null, "import", null, 0, -1, org.etsi.mts.tdl.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getPackage_PackagedElements(), this.getPackageableElement(), this.getPackageableElement_OwningPackage(), "packagedElements", null, 0, -1, org.etsi.mts.tdl.Package.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		op = addEOperation(packageEClass, ecorePackage.getEBoolean(), "invCyclicImportsNotAllowed", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(elementImportEClass, ElementImport.class, "ElementImport", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getElementImport_ImportedElement(), this.getPackageableElement(), null, "importedElement", null, 1, -1, ElementImport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(testObjectiveEClass, TestObjective.class, "TestObjective", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTestObjective_ObjectiveURI(), theTypesPackage.getString(), "objectiveURI", null, 0, -1, TestObjective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getTestObjective_Description(), theTypesPackage.getString(), "description", null, 0, 1, TestObjective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(testObjectiveRealizerEClass, TestObjectiveRealizer.class, "TestObjectiveRealizer", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestObjectiveRealizer_TestObjective(), this.getTestObjective(), null, "testObjective", null, 0, -1, TestObjectiveRealizer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(dataInstanceEClass, DataInstance.class, "DataInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDataInstance_Parameter(), this.getMappableDataElement(), null, "parameter", null, 0, -1, DataInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mappableDataElementEClass, MappableDataElement.class, "MappableDataElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dataElementEClass, DataElement.class, "DataElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dataSetEClass, DataSet.class, "DataSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDataSet_DataInstance(), this.getDataInstance(), null, "dataInstance", null, 0, -1, DataSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(dataResourceMappingEClass, DataResourceMapping.class, "DataResourceMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDataResourceMapping_ResourceURI(), theTypesPackage.getString(), "resourceURI", null, 0, 1, DataResourceMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(dataElementMappingEClass, DataElementMapping.class, "DataElementMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDataElementMapping_MappableDataElement(), this.getMappableDataElement(), null, "mappableDataElement", null, 1, 1, DataElementMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getDataElementMapping_ElementURI(), theTypesPackage.getString(), "elementURI", null, 0, 1, DataElementMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getDataElementMapping_DataResourceMapping(), this.getDataResourceMapping(), null, "dataResourceMapping", null, 1, 1, DataElementMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(dataProxyEClass, DataProxy.class, "DataProxy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDataProxy_DataSet(), this.getDataSet(), null, "dataSet", null, 1, 1, DataProxy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(timerEClass, Timer.class, "Timer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTimer_ComponentType(), this.getComponentType(), this.getComponentType_Timer(), "componentType", null, 1, 1, Timer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(componentTypeEClass, ComponentType.class, "ComponentType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponentType_GateType(), this.getGateType(), null, "gateType", null, 1, -1, ComponentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponentType_Timer(), this.getTimer(), this.getTimer_ComponentType(), "timer", null, 0, -1, ComponentType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		op = addEOperation(componentTypeEClass, ecorePackage.getEBoolean(), "invTestConfigurationandComponentsRoles", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(gateTypeEClass, GateType.class, "GateType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGateType_DataSet(), this.getDataSet(), null, "dataSet", null, 1, -1, GateType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(timerOperationEClass, TimerOperation.class, "TimerOperation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTimerOperation_Timer(), this.getTimer(), null, "timer", null, 1, 1, TimerOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(atomicBehaviourEClass, AtomicBehaviour.class, "AtomicBehaviour", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAtomicBehaviour_TimeConstraint(), this.getTimeConstraint(), this.getTimeConstraint_AtomicBehaviour(), "timeConstraint", null, 0, -1, AtomicBehaviour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(behaviourEClass, Behaviour.class, "Behaviour", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(timeConstraintEClass, TimeConstraint.class, "TimeConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTimeConstraint_Expression(), theTypesPackage.getString(), "expression", null, 1, 1, TimeConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getTimeConstraint_AtomicBehaviour(), this.getAtomicBehaviour(), this.getAtomicBehaviour_TimeConstraint(), "atomicBehaviour", null, 1, -1, TimeConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(timerStartEClass, TimerStart.class, "TimerStart", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTimerStart_Period(), this.getTime(), null, "period", null, 1, 1, TimerStart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(timeEClass, Time.class, "Time", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTime_Value(), theTypesPackage.getReal(), "value", null, 1, 1, Time.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getTime_Unit(), this.getTimeUnit(), null, "unit", null, 1, 1, Time.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(timeUnitEClass, TimeUnit.class, "TimeUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(timerStopEClass, TimerStop.class, "TimerStop", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(timeOutEClass, TimeOut.class, "TimeOut", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(timeOperationEClass, TimeOperation.class, "TimeOperation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTimeOperation_GateInstance(), this.getGateInstance(), null, "gateInstance", null, 0, 1, TimeOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getTimeOperation_Period(), this.getTime(), null, "period", null, 1, 1, TimeOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getTimeOperation_ComponentInstance(), this.getComponentInstance(), null, "componentInstance", null, 0, 1, TimeOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(gateInstanceEClass, GateInstance.class, "GateInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGateInstance_Type(), this.getGateType(), null, "type", null, 1, 1, GateInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getGateInstance_ComponentInstance(), this.getComponentInstance(), this.getComponentInstance_GateInstance(), "componentInstance", null, 1, 1, GateInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(componentInstanceEClass, ComponentInstance.class, "ComponentInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponentInstance_Type(), this.getComponentType(), null, "type", null, 1, 1, ComponentInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEAttribute(getComponentInstance_Role(), this.getComponentInstanceRole(), "role", null, 1, 1, ComponentInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getComponentInstance_Timer(), this.getTimer(), null, "timer", null, 0, -1, ComponentInstance.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED);
		initEReference(getComponentInstance_GateInstance(), this.getGateInstance(), this.getGateInstance_ComponentInstance(), "gateInstance", null, 1, -1, ComponentInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(componentInstanceEClass, ecorePackage.getEBoolean(), "invNumberofGateInstances", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(componentInstanceEClass, ecorePackage.getEBoolean(), "invTimersofComponentInstances", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(waitEClass, Wait.class, "Wait", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(quiescenceEClass, Quiescence.class, "Quiescence", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(testConfigurationEClass, TestConfiguration.class, "TestConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestConfiguration_ComponentInstance(), this.getComponentInstance(), null, "componentInstance", null, 2, -1, TestConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getTestConfiguration_Connection(), this.getConnection(), null, "connection", null, 1, -1, TestConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(connectionEClass, Connection.class, "Connection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConnection_EndPoint(), this.getGateInstance(), null, "endPoint", null, 2, 2, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		op = addEOperation(connectionEClass, ecorePackage.getEBoolean(), "invSelfloopedConnections", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(connectionEClass, ecorePackage.getEBoolean(), "invOnlyoneconnectionallowed", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(testDescriptionEClass, TestDescription.class, "TestDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestDescription_TestConfiguration(), this.getTestConfiguration(), null, "testConfiguration", null, 1, 1, TestDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getTestDescription_Behaviour(), this.getCompoundBehaviour(), null, "behaviour", null, 0, 1, TestDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getTestDescription_TimeConstraint(), this.getTimeConstraint(), null, "timeConstraint", null, 0, -1, TestDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getTestDescription_FormalParameter(), this.getDataProxy(), null, "formalParameter", null, 0, -1, TestDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(compoundBehaviourEClass, CompoundBehaviour.class, "CompoundBehaviour", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(singleCombinedBehaviourEClass, SingleCombinedBehaviour.class, "SingleCombinedBehaviour", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSingleCombinedBehaviour_Block(), this.getBlock(), null, "block", null, 1, 1, SingleCombinedBehaviour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(combinedBehaviourEClass, CombinedBehaviour.class, "CombinedBehaviour", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCombinedBehaviour_Periodic(), this.getPeriodicBehaviour(), null, "periodic", null, 0, -1, CombinedBehaviour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCombinedBehaviour_Exceptional(), this.getExceptionalBehaviour(), null, "exceptional", null, 0, -1, CombinedBehaviour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(periodicBehaviourEClass, PeriodicBehaviour.class, "PeriodicBehaviour", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPeriodicBehaviour_Block(), this.getBlock(), null, "block", null, 1, 1, PeriodicBehaviour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getPeriodicBehaviour_Period(), this.getTime(), null, "period", null, 1, 1, PeriodicBehaviour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(blockEClass, Block.class, "Block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBlock_Behaviour(), this.getBehaviour(), null, "behaviour", null, 1, -1, Block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBlock_Guard(), theTypesPackage.getString(), "guard", null, 0, 1, Block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(exceptionalBehaviourEClass, ExceptionalBehaviour.class, "ExceptionalBehaviour", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getExceptionalBehaviour_Block(), this.getBlock(), null, "block", null, 1, 1, ExceptionalBehaviour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getExceptionalBehaviour_GuardedComponent(), this.getComponentInstance(), null, "guardedComponent", null, 0, 1, ExceptionalBehaviour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(alternativeBehaviourEClass, AlternativeBehaviour.class, "AlternativeBehaviour", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(multipleCombinedBehaviourEClass, MultipleCombinedBehaviour.class, "MultipleCombinedBehaviour", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMultipleCombinedBehaviour_Block(), this.getBlock(), null, "block", null, 1, -1, MultipleCombinedBehaviour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parallelBehaviourEClass, ParallelBehaviour.class, "ParallelBehaviour", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(boundedLoopBehaviourEClass, BoundedLoopBehaviour.class, "BoundedLoopBehaviour", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBoundedLoopBehaviour_NumIteration(), theTypesPackage.getInteger(), "numIteration", null, 1, 1, BoundedLoopBehaviour.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		op = addEOperation(boundedLoopBehaviourEClass, ecorePackage.getEBoolean(), "invBoundedLoopmustnothaveaguard", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(unboundedLoopBehaviourEClass, UnboundedLoopBehaviour.class, "UnboundedLoopBehaviour", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(conditionalBehaviourEClass, ConditionalBehaviour.class, "ConditionalBehaviour", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stopEClass, Stop.class, "Stop", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(testDescriptionReferenceEClass, TestDescriptionReference.class, "TestDescriptionReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTestDescriptionReference_ReferencedTestDescription(), this.getTestDescription(), null, "referencedTestDescription", null, 1, 1, TestDescriptionReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getTestDescriptionReference_ActualParameter(), this.getArgumentSpecification(), null, "actualParameter", null, 0, -1, TestDescriptionReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(argumentSpecificationEClass, ArgumentSpecification.class, "ArgumentSpecification", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(verdictAssignmentEClass, VerdictAssignment.class, "VerdictAssignment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVerdictAssignment_VerdictType(), this.getVerdictType(), null, "verdictType", null, 1, 1, VerdictAssignment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(verdictTypeEClass, VerdictType.class, "VerdictType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(actionReferenceEClass, ActionReference.class, "ActionReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActionReference_ComponentInstance(), this.getComponentInstance(), null, "componentInstance", null, 0, 1, ActionReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getActionReference_Action(), this.getAction(), null, "action", null, 1, 1, ActionReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(actionEClass, Action.class, "Action", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAction_Body(), theTypesPackage.getString(), "body", null, 1, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(interactionEClass, Interaction.class, "Interaction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInteraction_Argument(), this.getArgumentSpecification(), null, "argument", null, 1, 1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getInteraction_Source(), this.getGateInstance(), null, "source", null, 1, 1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getInteraction_Target(), this.getGateInstance(), null, "target", null, 1, -1, Interaction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(interactionEClass, ecorePackage.getEBoolean(), "invConstraint1", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(interactionEClass, ecorePackage.getEBoolean(), "invConstraint2", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(interactionEClass, ecorePackage.getEBoolean(), "invConstraint3", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(optionalBehaviourEClass, OptionalBehaviour.class, "OptionalBehaviour", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(defaultBehaviourEClass, DefaultBehaviour.class, "DefaultBehaviour", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(interruptBehaviourEClass, InterruptBehaviour.class, "InterruptBehaviour", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(breakEClass, Break.class, "Break", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dataInstanceArgumentSpecificationEClass, DataInstanceArgumentSpecification.class, "DataInstanceArgumentSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDataInstanceArgumentSpecification_DataInstance(), this.getDataInstance(), null, "dataInstance", null, 1, 1, DataInstanceArgumentSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);
		initEReference(getDataInstanceArgumentSpecification_ActualParameter(), this.getDataElement(), null, "actualParameter", null, 0, -1, DataInstanceArgumentSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dataProxyArgumentSpecificationEClass, DataProxyArgumentSpecification.class, "DataProxyArgumentSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDataProxyArgumentSpecification_DataProxy(), this.getDataProxy(), null, "dataProxy", null, 1, 1, DataProxyArgumentSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		initEClass(dataSetArgumentSpecificationEClass, DataSetArgumentSpecification.class, "DataSetArgumentSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDataSetArgumentSpecification_DataSet(), this.getDataSet(), null, "dataSet", null, 1, 1, DataSetArgumentSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, !IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(componentInstanceRoleEEnum, ComponentInstanceRole.class, "ComponentInstanceRole");
		addEEnumLiteral(componentInstanceRoleEEnum, ComponentInstanceRole.SUT);
		addEEnumLiteral(componentInstanceRoleEEnum, ComponentInstanceRole.TESTER);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
		// http://www.eclipse.org/emf/2002/Ecore/OCL
		createOCLAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";		
		addAnnotation
		  (this, 
		   source, 
		   new String[] {
			 "validationDelegates", "http://www.eclipse.org/emf/2002/Ecore/OCL"
		   });																									
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore/OCL</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createOCLAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore/OCL";																		
		addAnnotation
		  (boundedLoopBehaviourEClass.getEOperations().get(0), 
		   source, 
		   new String[] {
			 "body", "self.block.guard.oclIsUndefined()"
		   });									
	}

} //TdlPackageImpl
