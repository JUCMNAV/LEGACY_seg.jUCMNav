/**
 */
package core;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see core.CoreFactory
 * @model kind="package"
 * @generated
 */
public interface CorePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "core";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://cs.mcgill.ca/sel/core/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "core";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CorePackage eINSTANCE = core.impl.CorePackageImpl.init();

	/**
	 * The meta object id for the '{@link core.impl.CORENamedElementImpl <em>CORE Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see core.impl.CORENamedElementImpl
	 * @see core.impl.CorePackageImpl#getCORENamedElement()
	 * @generated
	 */
	int CORE_NAMED_ELEMENT = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_NAMED_ELEMENT__NAME = 0;

	/**
	 * The number of structural features of the '<em>CORE Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_NAMED_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link core.impl.COREModelImpl <em>CORE Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see core.impl.COREModelImpl
	 * @see core.impl.CorePackageImpl#getCOREModel()
	 * @generated
	 */
	int CORE_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_MODEL__NAME = CORE_NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Reuses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_MODEL__REUSES = CORE_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Model Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_MODEL__MODEL_ELEMENTS = CORE_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Realizes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_MODEL__REALIZES = CORE_NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>CORE Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_MODEL_FEATURE_COUNT = CORE_NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link core.impl.COREImpactModelImpl <em>CORE Impact Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see core.impl.COREImpactModelImpl
	 * @see core.impl.CorePackageImpl#getCOREImpactModel()
	 * @generated
	 */
	int CORE_IMPACT_MODEL = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_IMPACT_MODEL__NAME = CORE_MODEL__NAME;

	/**
	 * The feature id for the '<em><b>Reuses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_IMPACT_MODEL__REUSES = CORE_MODEL__REUSES;

	/**
	 * The feature id for the '<em><b>Model Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_IMPACT_MODEL__MODEL_ELEMENTS = CORE_MODEL__MODEL_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Realizes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_IMPACT_MODEL__REALIZES = CORE_MODEL__REALIZES;

	/**
	 * The number of structural features of the '<em>CORE Impact Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_IMPACT_MODEL_FEATURE_COUNT = CORE_MODEL_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link core.impl.COREConcernImpl <em>CORE Concern</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see core.impl.COREConcernImpl
	 * @see core.impl.CorePackageImpl#getCOREConcern()
	 * @generated
	 */
	int CORE_CONCERN = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_CONCERN__NAME = CORE_NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Models</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_CONCERN__MODELS = CORE_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Interface</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_CONCERN__INTERFACE = CORE_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>CORE Concern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_CONCERN_FEATURE_COUNT = CORE_NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link core.impl.COREModelElementImpl <em>CORE Model Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see core.impl.COREModelElementImpl
	 * @see core.impl.CorePackageImpl#getCOREModelElement()
	 * @generated
	 */
	int CORE_MODEL_ELEMENT = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_MODEL_ELEMENT__NAME = CORE_NAMED_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>CORE Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_MODEL_ELEMENT_FEATURE_COUNT = CORE_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link core.impl.COREFeatureImpl <em>CORE Feature</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see core.impl.COREFeatureImpl
	 * @see core.impl.CorePackageImpl#getCOREFeature()
	 * @generated
	 */
	int CORE_FEATURE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_FEATURE__NAME = CORE_MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Realized By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_FEATURE__REALIZED_BY = CORE_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Strategies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_FEATURE__STRATEGIES = CORE_MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Configurations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_FEATURE__CONFIGURATIONS = CORE_MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>CORE Feature</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_FEATURE_FEATURE_COUNT = CORE_MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link core.impl.CORECompositionSpecificationImpl <em>CORE Composition Specification</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see core.impl.CORECompositionSpecificationImpl
	 * @see core.impl.CorePackageImpl#getCORECompositionSpecification()
	 * @generated
	 */
	int CORE_COMPOSITION_SPECIFICATION = 6;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_COMPOSITION_SPECIFICATION__SOURCE = 0;

	/**
	 * The number of structural features of the '<em>CORE Composition Specification</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_COMPOSITION_SPECIFICATION_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link core.impl.COREBindingImpl <em>CORE Binding</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see core.impl.COREBindingImpl
	 * @see core.impl.CorePackageImpl#getCOREBinding()
	 * @generated
	 */
	int CORE_BINDING = 4;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_BINDING__SOURCE = CORE_COMPOSITION_SPECIFICATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Core Mappings</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_BINDING__CORE_MAPPINGS = CORE_COMPOSITION_SPECIFICATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>CORE Binding</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_BINDING_FEATURE_COUNT = CORE_COMPOSITION_SPECIFICATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link core.impl.COREMappingImpl <em>CORE Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see core.impl.COREMappingImpl
	 * @see core.impl.CorePackageImpl#getCOREMapping()
	 * @generated
	 */
	int CORE_MAPPING = 7;

	/**
	 * The feature id for the '<em><b>Mapped To</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_MAPPING__MAPPED_TO = 0;

	/**
	 * The feature id for the '<em><b>Mapped From</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_MAPPING__MAPPED_FROM = 1;

	/**
	 * The number of structural features of the '<em>CORE Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_MAPPING_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link core.impl.COREStrategyImpl <em>CORE Strategy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see core.impl.COREStrategyImpl
	 * @see core.impl.CorePackageImpl#getCOREStrategy()
	 * @generated
	 */
	int CORE_STRATEGY = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_STRATEGY__NAME = CORE_NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Configurations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_STRATEGY__CONFIGURATIONS = CORE_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>CORE Strategy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_STRATEGY_FEATURE_COUNT = CORE_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link core.impl.COREInterfaceImpl <em>CORE Interface</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see core.impl.COREInterfaceImpl
	 * @see core.impl.CorePackageImpl#getCOREInterface()
	 * @generated
	 */
	int CORE_INTERFACE = 10;

	/**
	 * The feature id for the '<em><b>Selectable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_INTERFACE__SELECTABLE = 0;

	/**
	 * The feature id for the '<em><b>Customizable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_INTERFACE__CUSTOMIZABLE = 1;

	/**
	 * The feature id for the '<em><b>Usable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_INTERFACE__USABLE = 2;

	/**
	 * The feature id for the '<em><b>Impacted</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_INTERFACE__IMPACTED = 3;

	/**
	 * The number of structural features of the '<em>CORE Interface</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_INTERFACE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link core.impl.COREReuseImpl <em>CORE Reuse</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see core.impl.COREReuseImpl
	 * @see core.impl.CorePackageImpl#getCOREReuse()
	 * @generated
	 */
	int CORE_REUSE = 11;

	/**
	 * The feature id for the '<em><b>Reused Concern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_REUSE__REUSED_CONCERN = 0;

	/**
	 * The feature id for the '<em><b>Compositions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_REUSE__COMPOSITIONS = 1;

	/**
	 * The feature id for the '<em><b>Selected</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_REUSE__SELECTED = 2;

	/**
	 * The number of structural features of the '<em>CORE Reuse</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_REUSE_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link core.impl.COREPatternImpl <em>CORE Pattern</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see core.impl.COREPatternImpl
	 * @see core.impl.CorePackageImpl#getCOREPattern()
	 * @generated
	 */
	int CORE_PATTERN = 12;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_PATTERN__SOURCE = CORE_COMPOSITION_SPECIFICATION__SOURCE;

	/**
	 * The number of structural features of the '<em>CORE Pattern</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_PATTERN_FEATURE_COUNT = CORE_COMPOSITION_SPECIFICATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link core.impl.COREImpactModelElementImpl <em>CORE Impact Model Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see core.impl.COREImpactModelElementImpl
	 * @see core.impl.CorePackageImpl#getCOREImpactModelElement()
	 * @generated
	 */
	int CORE_IMPACT_MODEL_ELEMENT = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_IMPACT_MODEL_ELEMENT__NAME = CORE_MODEL_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>CORE Impact Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_IMPACT_MODEL_ELEMENT_FEATURE_COUNT = CORE_MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link core.impl.COREConfigurationImpl <em>CORE Configuration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see core.impl.COREConfigurationImpl
	 * @see core.impl.CorePackageImpl#getCOREConfiguration()
	 * @generated
	 */
	int CORE_CONFIGURATION = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_CONFIGURATION__NAME = CORE_NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Reused Concern</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_CONFIGURATION__REUSED_CONCERN = CORE_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Selected</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_CONFIGURATION__SELECTED = CORE_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>CORE Configuration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_CONFIGURATION_FEATURE_COUNT = CORE_NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link core.impl.COREFeatureModelImpl <em>CORE Feature Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see core.impl.COREFeatureModelImpl
	 * @see core.impl.CorePackageImpl#getCOREFeatureModel()
	 * @generated
	 */
	int CORE_FEATURE_MODEL = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_FEATURE_MODEL__NAME = CORE_MODEL__NAME;

	/**
	 * The feature id for the '<em><b>Reuses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_FEATURE_MODEL__REUSES = CORE_MODEL__REUSES;

	/**
	 * The feature id for the '<em><b>Model Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_FEATURE_MODEL__MODEL_ELEMENTS = CORE_MODEL__MODEL_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Realizes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_FEATURE_MODEL__REALIZES = CORE_MODEL__REALIZES;

	/**
	 * The number of structural features of the '<em>CORE Feature Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CORE_FEATURE_MODEL_FEATURE_COUNT = CORE_MODEL_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link core.COREModel <em>CORE Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CORE Model</em>'.
	 * @see core.COREModel
	 * @generated
	 */
	EClass getCOREModel();

	/**
	 * Returns the meta object for the containment reference list '{@link core.COREModel#getReuses <em>Reuses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Reuses</em>'.
	 * @see core.COREModel#getReuses()
	 * @see #getCOREModel()
	 * @generated
	 */
	EReference getCOREModel_Reuses();

	/**
	 * Returns the meta object for the reference list '{@link core.COREModel#getModelElements <em>Model Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Model Elements</em>'.
	 * @see core.COREModel#getModelElements()
	 * @see #getCOREModel()
	 * @generated
	 */
	EReference getCOREModel_ModelElements();

	/**
	 * Returns the meta object for the reference list '{@link core.COREModel#getRealizes <em>Realizes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Realizes</em>'.
	 * @see core.COREModel#getRealizes()
	 * @see #getCOREModel()
	 * @generated
	 */
	EReference getCOREModel_Realizes();

	/**
	 * Returns the meta object for class '{@link core.COREImpactModel <em>CORE Impact Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CORE Impact Model</em>'.
	 * @see core.COREImpactModel
	 * @generated
	 */
	EClass getCOREImpactModel();

	/**
	 * Returns the meta object for class '{@link core.COREConcern <em>CORE Concern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CORE Concern</em>'.
	 * @see core.COREConcern
	 * @generated
	 */
	EClass getCOREConcern();

	/**
	 * Returns the meta object for the reference list '{@link core.COREConcern#getModels <em>Models</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Models</em>'.
	 * @see core.COREConcern#getModels()
	 * @see #getCOREConcern()
	 * @generated
	 */
	EReference getCOREConcern_Models();

	/**
	 * Returns the meta object for the containment reference '{@link core.COREConcern#getInterface <em>Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Interface</em>'.
	 * @see core.COREConcern#getInterface()
	 * @see #getCOREConcern()
	 * @generated
	 */
	EReference getCOREConcern_Interface();

	/**
	 * Returns the meta object for class '{@link core.COREFeature <em>CORE Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CORE Feature</em>'.
	 * @see core.COREFeature
	 * @generated
	 */
	EClass getCOREFeature();

	/**
	 * Returns the meta object for the reference list '{@link core.COREFeature#getRealizedBy <em>Realized By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Realized By</em>'.
	 * @see core.COREFeature#getRealizedBy()
	 * @see #getCOREFeature()
	 * @generated
	 */
	EReference getCOREFeature_RealizedBy();

	/**
	 * Returns the meta object for the containment reference list '{@link core.COREFeature#getStrategies <em>Strategies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Strategies</em>'.
	 * @see core.COREFeature#getStrategies()
	 * @see #getCOREFeature()
	 * @generated
	 */
	EReference getCOREFeature_Strategies();

	/**
	 * Returns the meta object for the containment reference list '{@link core.COREFeature#getConfigurations <em>Configurations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Configurations</em>'.
	 * @see core.COREFeature#getConfigurations()
	 * @see #getCOREFeature()
	 * @generated
	 */
	EReference getCOREFeature_Configurations();

	/**
	 * Returns the meta object for class '{@link core.COREBinding <em>CORE Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CORE Binding</em>'.
	 * @see core.COREBinding
	 * @generated
	 */
	EClass getCOREBinding();

	/**
	 * Returns the meta object for the reference list '{@link core.COREBinding#getCoreMappings <em>Core Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Core Mappings</em>'.
	 * @see core.COREBinding#getCoreMappings()
	 * @see #getCOREBinding()
	 * @generated
	 */
	EReference getCOREBinding_CoreMappings();

	/**
	 * Returns the meta object for class '{@link core.COREModelElement <em>CORE Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CORE Model Element</em>'.
	 * @see core.COREModelElement
	 * @generated
	 */
	EClass getCOREModelElement();

	/**
	 * Returns the meta object for class '{@link core.CORECompositionSpecification <em>CORE Composition Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CORE Composition Specification</em>'.
	 * @see core.CORECompositionSpecification
	 * @generated
	 */
	EClass getCORECompositionSpecification();

	/**
	 * Returns the meta object for the reference '{@link core.CORECompositionSpecification#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see core.CORECompositionSpecification#getSource()
	 * @see #getCORECompositionSpecification()
	 * @generated
	 */
	EReference getCORECompositionSpecification_Source();

	/**
	 * Returns the meta object for class '{@link core.COREMapping <em>CORE Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CORE Mapping</em>'.
	 * @see core.COREMapping
	 * @generated
	 */
	EClass getCOREMapping();

	/**
	 * Returns the meta object for the reference '{@link core.COREMapping#getMappedTo <em>Mapped To</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Mapped To</em>'.
	 * @see core.COREMapping#getMappedTo()
	 * @see #getCOREMapping()
	 * @generated
	 */
	EReference getCOREMapping_MappedTo();

	/**
	 * Returns the meta object for the reference '{@link core.COREMapping#getMappedFrom <em>Mapped From</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Mapped From</em>'.
	 * @see core.COREMapping#getMappedFrom()
	 * @see #getCOREMapping()
	 * @generated
	 */
	EReference getCOREMapping_MappedFrom();

	/**
	 * Returns the meta object for class '{@link core.CORENamedElement <em>CORE Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CORE Named Element</em>'.
	 * @see core.CORENamedElement
	 * @generated
	 */
	EClass getCORENamedElement();

	/**
	 * Returns the meta object for the attribute '{@link core.CORENamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see core.CORENamedElement#getName()
	 * @see #getCORENamedElement()
	 * @generated
	 */
	EAttribute getCORENamedElement_Name();

	/**
	 * Returns the meta object for class '{@link core.COREStrategy <em>CORE Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CORE Strategy</em>'.
	 * @see core.COREStrategy
	 * @generated
	 */
	EClass getCOREStrategy();

	/**
	 * Returns the meta object for the reference list '{@link core.COREStrategy#getConfigurations <em>Configurations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Configurations</em>'.
	 * @see core.COREStrategy#getConfigurations()
	 * @see #getCOREStrategy()
	 * @generated
	 */
	EReference getCOREStrategy_Configurations();

	/**
	 * Returns the meta object for class '{@link core.COREInterface <em>CORE Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CORE Interface</em>'.
	 * @see core.COREInterface
	 * @generated
	 */
	EClass getCOREInterface();

	/**
	 * Returns the meta object for the reference list '{@link core.COREInterface#getSelectable <em>Selectable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Selectable</em>'.
	 * @see core.COREInterface#getSelectable()
	 * @see #getCOREInterface()
	 * @generated
	 */
	EReference getCOREInterface_Selectable();

	/**
	 * Returns the meta object for the reference list '{@link core.COREInterface#getCustomizable <em>Customizable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Customizable</em>'.
	 * @see core.COREInterface#getCustomizable()
	 * @see #getCOREInterface()
	 * @generated
	 */
	EReference getCOREInterface_Customizable();

	/**
	 * Returns the meta object for the reference list '{@link core.COREInterface#getUsable <em>Usable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Usable</em>'.
	 * @see core.COREInterface#getUsable()
	 * @see #getCOREInterface()
	 * @generated
	 */
	EReference getCOREInterface_Usable();

	/**
	 * Returns the meta object for the reference list '{@link core.COREInterface#getImpacted <em>Impacted</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Impacted</em>'.
	 * @see core.COREInterface#getImpacted()
	 * @see #getCOREInterface()
	 * @generated
	 */
	EReference getCOREInterface_Impacted();

	/**
	 * Returns the meta object for class '{@link core.COREReuse <em>CORE Reuse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CORE Reuse</em>'.
	 * @see core.COREReuse
	 * @generated
	 */
	EClass getCOREReuse();

	/**
	 * Returns the meta object for the reference '{@link core.COREReuse#getReusedConcern <em>Reused Concern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Reused Concern</em>'.
	 * @see core.COREReuse#getReusedConcern()
	 * @see #getCOREReuse()
	 * @generated
	 */
	EReference getCOREReuse_ReusedConcern();

	/**
	 * Returns the meta object for the reference list '{@link core.COREReuse#getCompositions <em>Compositions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Compositions</em>'.
	 * @see core.COREReuse#getCompositions()
	 * @see #getCOREReuse()
	 * @generated
	 */
	EReference getCOREReuse_Compositions();

	/**
	 * Returns the meta object for the reference list '{@link core.COREReuse#getSelected <em>Selected</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Selected</em>'.
	 * @see core.COREReuse#getSelected()
	 * @see #getCOREReuse()
	 * @generated
	 */
	EReference getCOREReuse_Selected();

	/**
	 * Returns the meta object for class '{@link core.COREPattern <em>CORE Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CORE Pattern</em>'.
	 * @see core.COREPattern
	 * @generated
	 */
	EClass getCOREPattern();

	/**
	 * Returns the meta object for class '{@link core.COREImpactModelElement <em>CORE Impact Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CORE Impact Model Element</em>'.
	 * @see core.COREImpactModelElement
	 * @generated
	 */
	EClass getCOREImpactModelElement();

	/**
	 * Returns the meta object for class '{@link core.COREConfiguration <em>CORE Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CORE Configuration</em>'.
	 * @see core.COREConfiguration
	 * @generated
	 */
	EClass getCOREConfiguration();

	/**
	 * Returns the meta object for the reference list '{@link core.COREConfiguration#getReusedConcern <em>Reused Concern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Reused Concern</em>'.
	 * @see core.COREConfiguration#getReusedConcern()
	 * @see #getCOREConfiguration()
	 * @generated
	 */
	EReference getCOREConfiguration_ReusedConcern();

	/**
	 * Returns the meta object for the reference list '{@link core.COREConfiguration#getSelected <em>Selected</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Selected</em>'.
	 * @see core.COREConfiguration#getSelected()
	 * @see #getCOREConfiguration()
	 * @generated
	 */
	EReference getCOREConfiguration_Selected();

	/**
	 * Returns the meta object for class '{@link core.COREFeatureModel <em>CORE Feature Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>CORE Feature Model</em>'.
	 * @see core.COREFeatureModel
	 * @generated
	 */
	EClass getCOREFeatureModel();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CoreFactory getCoreFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link core.impl.COREModelImpl <em>CORE Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see core.impl.COREModelImpl
		 * @see core.impl.CorePackageImpl#getCOREModel()
		 * @generated
		 */
		EClass CORE_MODEL = eINSTANCE.getCOREModel();

		/**
		 * The meta object literal for the '<em><b>Reuses</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_MODEL__REUSES = eINSTANCE.getCOREModel_Reuses();

		/**
		 * The meta object literal for the '<em><b>Model Elements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_MODEL__MODEL_ELEMENTS = eINSTANCE.getCOREModel_ModelElements();

		/**
		 * The meta object literal for the '<em><b>Realizes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_MODEL__REALIZES = eINSTANCE.getCOREModel_Realizes();

		/**
		 * The meta object literal for the '{@link core.impl.COREImpactModelImpl <em>CORE Impact Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see core.impl.COREImpactModelImpl
		 * @see core.impl.CorePackageImpl#getCOREImpactModel()
		 * @generated
		 */
		EClass CORE_IMPACT_MODEL = eINSTANCE.getCOREImpactModel();

		/**
		 * The meta object literal for the '{@link core.impl.COREConcernImpl <em>CORE Concern</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see core.impl.COREConcernImpl
		 * @see core.impl.CorePackageImpl#getCOREConcern()
		 * @generated
		 */
		EClass CORE_CONCERN = eINSTANCE.getCOREConcern();

		/**
		 * The meta object literal for the '<em><b>Models</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_CONCERN__MODELS = eINSTANCE.getCOREConcern_Models();

		/**
		 * The meta object literal for the '<em><b>Interface</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_CONCERN__INTERFACE = eINSTANCE.getCOREConcern_Interface();

		/**
		 * The meta object literal for the '{@link core.impl.COREFeatureImpl <em>CORE Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see core.impl.COREFeatureImpl
		 * @see core.impl.CorePackageImpl#getCOREFeature()
		 * @generated
		 */
		EClass CORE_FEATURE = eINSTANCE.getCOREFeature();

		/**
		 * The meta object literal for the '<em><b>Realized By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_FEATURE__REALIZED_BY = eINSTANCE.getCOREFeature_RealizedBy();

		/**
		 * The meta object literal for the '<em><b>Strategies</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_FEATURE__STRATEGIES = eINSTANCE.getCOREFeature_Strategies();

		/**
		 * The meta object literal for the '<em><b>Configurations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_FEATURE__CONFIGURATIONS = eINSTANCE.getCOREFeature_Configurations();

		/**
		 * The meta object literal for the '{@link core.impl.COREBindingImpl <em>CORE Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see core.impl.COREBindingImpl
		 * @see core.impl.CorePackageImpl#getCOREBinding()
		 * @generated
		 */
		EClass CORE_BINDING = eINSTANCE.getCOREBinding();

		/**
		 * The meta object literal for the '<em><b>Core Mappings</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_BINDING__CORE_MAPPINGS = eINSTANCE.getCOREBinding_CoreMappings();

		/**
		 * The meta object literal for the '{@link core.impl.COREModelElementImpl <em>CORE Model Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see core.impl.COREModelElementImpl
		 * @see core.impl.CorePackageImpl#getCOREModelElement()
		 * @generated
		 */
		EClass CORE_MODEL_ELEMENT = eINSTANCE.getCOREModelElement();

		/**
		 * The meta object literal for the '{@link core.impl.CORECompositionSpecificationImpl <em>CORE Composition Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see core.impl.CORECompositionSpecificationImpl
		 * @see core.impl.CorePackageImpl#getCORECompositionSpecification()
		 * @generated
		 */
		EClass CORE_COMPOSITION_SPECIFICATION = eINSTANCE.getCORECompositionSpecification();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_COMPOSITION_SPECIFICATION__SOURCE = eINSTANCE.getCORECompositionSpecification_Source();

		/**
		 * The meta object literal for the '{@link core.impl.COREMappingImpl <em>CORE Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see core.impl.COREMappingImpl
		 * @see core.impl.CorePackageImpl#getCOREMapping()
		 * @generated
		 */
		EClass CORE_MAPPING = eINSTANCE.getCOREMapping();

		/**
		 * The meta object literal for the '<em><b>Mapped To</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_MAPPING__MAPPED_TO = eINSTANCE.getCOREMapping_MappedTo();

		/**
		 * The meta object literal for the '<em><b>Mapped From</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_MAPPING__MAPPED_FROM = eINSTANCE.getCOREMapping_MappedFrom();

		/**
		 * The meta object literal for the '{@link core.impl.CORENamedElementImpl <em>CORE Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see core.impl.CORENamedElementImpl
		 * @see core.impl.CorePackageImpl#getCORENamedElement()
		 * @generated
		 */
		EClass CORE_NAMED_ELEMENT = eINSTANCE.getCORENamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CORE_NAMED_ELEMENT__NAME = eINSTANCE.getCORENamedElement_Name();

		/**
		 * The meta object literal for the '{@link core.impl.COREStrategyImpl <em>CORE Strategy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see core.impl.COREStrategyImpl
		 * @see core.impl.CorePackageImpl#getCOREStrategy()
		 * @generated
		 */
		EClass CORE_STRATEGY = eINSTANCE.getCOREStrategy();

		/**
		 * The meta object literal for the '<em><b>Configurations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_STRATEGY__CONFIGURATIONS = eINSTANCE.getCOREStrategy_Configurations();

		/**
		 * The meta object literal for the '{@link core.impl.COREInterfaceImpl <em>CORE Interface</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see core.impl.COREInterfaceImpl
		 * @see core.impl.CorePackageImpl#getCOREInterface()
		 * @generated
		 */
		EClass CORE_INTERFACE = eINSTANCE.getCOREInterface();

		/**
		 * The meta object literal for the '<em><b>Selectable</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_INTERFACE__SELECTABLE = eINSTANCE.getCOREInterface_Selectable();

		/**
		 * The meta object literal for the '<em><b>Customizable</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_INTERFACE__CUSTOMIZABLE = eINSTANCE.getCOREInterface_Customizable();

		/**
		 * The meta object literal for the '<em><b>Usable</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_INTERFACE__USABLE = eINSTANCE.getCOREInterface_Usable();

		/**
		 * The meta object literal for the '<em><b>Impacted</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_INTERFACE__IMPACTED = eINSTANCE.getCOREInterface_Impacted();

		/**
		 * The meta object literal for the '{@link core.impl.COREReuseImpl <em>CORE Reuse</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see core.impl.COREReuseImpl
		 * @see core.impl.CorePackageImpl#getCOREReuse()
		 * @generated
		 */
		EClass CORE_REUSE = eINSTANCE.getCOREReuse();

		/**
		 * The meta object literal for the '<em><b>Reused Concern</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_REUSE__REUSED_CONCERN = eINSTANCE.getCOREReuse_ReusedConcern();

		/**
		 * The meta object literal for the '<em><b>Compositions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_REUSE__COMPOSITIONS = eINSTANCE.getCOREReuse_Compositions();

		/**
		 * The meta object literal for the '<em><b>Selected</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_REUSE__SELECTED = eINSTANCE.getCOREReuse_Selected();

		/**
		 * The meta object literal for the '{@link core.impl.COREPatternImpl <em>CORE Pattern</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see core.impl.COREPatternImpl
		 * @see core.impl.CorePackageImpl#getCOREPattern()
		 * @generated
		 */
		EClass CORE_PATTERN = eINSTANCE.getCOREPattern();

		/**
		 * The meta object literal for the '{@link core.impl.COREImpactModelElementImpl <em>CORE Impact Model Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see core.impl.COREImpactModelElementImpl
		 * @see core.impl.CorePackageImpl#getCOREImpactModelElement()
		 * @generated
		 */
		EClass CORE_IMPACT_MODEL_ELEMENT = eINSTANCE.getCOREImpactModelElement();

		/**
		 * The meta object literal for the '{@link core.impl.COREConfigurationImpl <em>CORE Configuration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see core.impl.COREConfigurationImpl
		 * @see core.impl.CorePackageImpl#getCOREConfiguration()
		 * @generated
		 */
		EClass CORE_CONFIGURATION = eINSTANCE.getCOREConfiguration();

		/**
		 * The meta object literal for the '<em><b>Reused Concern</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_CONFIGURATION__REUSED_CONCERN = eINSTANCE.getCOREConfiguration_ReusedConcern();

		/**
		 * The meta object literal for the '<em><b>Selected</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CORE_CONFIGURATION__SELECTED = eINSTANCE.getCOREConfiguration_Selected();

		/**
		 * The meta object literal for the '{@link core.impl.COREFeatureModelImpl <em>CORE Feature Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see core.impl.COREFeatureModelImpl
		 * @see core.impl.CorePackageImpl#getCOREFeatureModel()
		 * @generated
		 */
		EClass CORE_FEATURE_MODEL = eINSTANCE.getCOREFeatureModel();

	}

} //CorePackage
