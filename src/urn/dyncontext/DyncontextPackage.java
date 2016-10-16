/**
 */
package urn.dyncontext;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * @see urn.dyncontext.DyncontextFactory
 * @model kind="package"
 * @generated
 */
public interface DyncontextPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "dyncontext";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///urn/dyncontext.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "urn.dyncontext";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DyncontextPackage eINSTANCE = urn.dyncontext.impl.DyncontextPackageImpl.init();

	/**
	 * The meta object id for the '{@link urn.dyncontext.impl.ChangeImpl <em>Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see urn.dyncontext.impl.ChangeImpl
	 * @see urn.dyncontext.impl.DyncontextPackageImpl#getChange()
	 * @generated
	 */
	int CHANGE = 12;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE__START = 0;

	/**
	 * The feature id for the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE__END = 1;

	/**
	 * The feature id for the '<em><b>Context</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE__CONTEXT = 2;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE__ELEMENT = 3;

	/**
	 * The number of structural features of the '<em>Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHANGE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link urn.dyncontext.impl.PropertyChangeImpl <em>Property Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see urn.dyncontext.impl.PropertyChangeImpl
	 * @see urn.dyncontext.impl.DyncontextPackageImpl#getPropertyChange()
	 * @generated
	 */
	int PROPERTY_CHANGE = 3;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CHANGE__START = CHANGE__START;

	/**
	 * The feature id for the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CHANGE__END = CHANGE__END;

	/**
	 * The feature id for the '<em><b>Context</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CHANGE__CONTEXT = CHANGE__CONTEXT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CHANGE__ELEMENT = CHANGE__ELEMENT;

	/**
	 * The feature id for the '<em><b>Affected Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CHANGE__AFFECTED_PROPERTY = CHANGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Property Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_CHANGE_FEATURE_COUNT = CHANGE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link urn.dyncontext.impl.NumericChangeImpl <em>Numeric Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see urn.dyncontext.impl.NumericChangeImpl
	 * @see urn.dyncontext.impl.DyncontextPackageImpl#getNumericChange()
	 * @generated
	 */
	int NUMERIC_CHANGE = 4;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_CHANGE__START = PROPERTY_CHANGE__START;

	/**
	 * The feature id for the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_CHANGE__END = PROPERTY_CHANGE__END;

	/**
	 * The feature id for the '<em><b>Context</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_CHANGE__CONTEXT = PROPERTY_CHANGE__CONTEXT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_CHANGE__ELEMENT = PROPERTY_CHANGE__ELEMENT;

	/**
	 * The feature id for the '<em><b>Affected Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_CHANGE__AFFECTED_PROPERTY = PROPERTY_CHANGE__AFFECTED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Sufficient Once Achieved</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_CHANGE__SUFFICIENT_ONCE_ACHIEVED = PROPERTY_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Numeric Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_CHANGE_FEATURE_COUNT = PROPERTY_CHANGE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link urn.dyncontext.impl.QuadraticChangeImpl <em>Quadratic Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see urn.dyncontext.impl.QuadraticChangeImpl
	 * @see urn.dyncontext.impl.DyncontextPackageImpl#getQuadraticChange()
	 * @generated
	 */
	int QUADRATIC_CHANGE = 0;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUADRATIC_CHANGE__START = NUMERIC_CHANGE__START;

	/**
	 * The feature id for the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUADRATIC_CHANGE__END = NUMERIC_CHANGE__END;

	/**
	 * The feature id for the '<em><b>Context</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUADRATIC_CHANGE__CONTEXT = NUMERIC_CHANGE__CONTEXT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUADRATIC_CHANGE__ELEMENT = NUMERIC_CHANGE__ELEMENT;

	/**
	 * The feature id for the '<em><b>Affected Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUADRATIC_CHANGE__AFFECTED_PROPERTY = NUMERIC_CHANGE__AFFECTED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Sufficient Once Achieved</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUADRATIC_CHANGE__SUFFICIENT_ONCE_ACHIEVED = NUMERIC_CHANGE__SUFFICIENT_ONCE_ACHIEVED;

	/**
	 * The feature id for the '<em><b>Quadratic Coefficient</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUADRATIC_CHANGE__QUADRATIC_COEFFICIENT = NUMERIC_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Linear Coefficient</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUADRATIC_CHANGE__LINEAR_COEFFICIENT = NUMERIC_CHANGE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Constant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUADRATIC_CHANGE__CONSTANT = NUMERIC_CHANGE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Quadratic Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUADRATIC_CHANGE_FEATURE_COUNT = NUMERIC_CHANGE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link urn.dyncontext.impl.TimepointGroupImpl <em>Timepoint Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see urn.dyncontext.impl.TimepointGroupImpl
	 * @see urn.dyncontext.impl.DyncontextPackageImpl#getTimepointGroup()
	 * @generated
	 */
	int TIMEPOINT_GROUP = 1;

	/**
	 * The feature id for the '<em><b>Timepoints</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMEPOINT_GROUP__TIMEPOINTS = 0;

	/**
	 * The feature id for the '<em><b>Urnspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMEPOINT_GROUP__URNSPEC = 1;

	/**
	 * The number of structural features of the '<em>Timepoint Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMEPOINT_GROUP_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link urn.dyncontext.impl.TimepointImpl <em>Timepoint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see urn.dyncontext.impl.TimepointImpl
	 * @see urn.dyncontext.impl.DyncontextPackageImpl#getTimepoint()
	 * @generated
	 */
	int TIMEPOINT = 2;

	/**
	 * The feature id for the '<em><b>Timepoint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMEPOINT__TIMEPOINT = 0;

	/**
	 * The feature id for the '<em><b>Group</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMEPOINT__GROUP = 1;

	/**
	 * The number of structural features of the '<em>Timepoint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TIMEPOINT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link urn.dyncontext.impl.FormulaChangeImpl <em>Formula Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see urn.dyncontext.impl.FormulaChangeImpl
	 * @see urn.dyncontext.impl.DyncontextPackageImpl#getFormulaChange()
	 * @generated
	 */
	int FORMULA_CHANGE = 5;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMULA_CHANGE__START = NUMERIC_CHANGE__START;

	/**
	 * The feature id for the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMULA_CHANGE__END = NUMERIC_CHANGE__END;

	/**
	 * The feature id for the '<em><b>Context</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMULA_CHANGE__CONTEXT = NUMERIC_CHANGE__CONTEXT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMULA_CHANGE__ELEMENT = NUMERIC_CHANGE__ELEMENT;

	/**
	 * The feature id for the '<em><b>Affected Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMULA_CHANGE__AFFECTED_PROPERTY = NUMERIC_CHANGE__AFFECTED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Sufficient Once Achieved</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMULA_CHANGE__SUFFICIENT_ONCE_ACHIEVED = NUMERIC_CHANGE__SUFFICIENT_ONCE_ACHIEVED;

	/**
	 * The feature id for the '<em><b>Formula</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMULA_CHANGE__FORMULA = NUMERIC_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Formula Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMULA_CHANGE_FEATURE_COUNT = NUMERIC_CHANGE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link urn.dyncontext.impl.LinearChangeImpl <em>Linear Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see urn.dyncontext.impl.LinearChangeImpl
	 * @see urn.dyncontext.impl.DyncontextPackageImpl#getLinearChange()
	 * @generated
	 */
	int LINEAR_CHANGE = 6;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_CHANGE__START = NUMERIC_CHANGE__START;

	/**
	 * The feature id for the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_CHANGE__END = NUMERIC_CHANGE__END;

	/**
	 * The feature id for the '<em><b>Context</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_CHANGE__CONTEXT = NUMERIC_CHANGE__CONTEXT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_CHANGE__ELEMENT = NUMERIC_CHANGE__ELEMENT;

	/**
	 * The feature id for the '<em><b>Affected Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_CHANGE__AFFECTED_PROPERTY = NUMERIC_CHANGE__AFFECTED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Sufficient Once Achieved</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_CHANGE__SUFFICIENT_ONCE_ACHIEVED = NUMERIC_CHANGE__SUFFICIENT_ONCE_ACHIEVED;

	/**
	 * The feature id for the '<em><b>New Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_CHANGE__NEW_VALUE = NUMERIC_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Linear Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINEAR_CHANGE_FEATURE_COUNT = NUMERIC_CHANGE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link urn.dyncontext.impl.EnumChangeImpl <em>Enum Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see urn.dyncontext.impl.EnumChangeImpl
	 * @see urn.dyncontext.impl.DyncontextPackageImpl#getEnumChange()
	 * @generated
	 */
	int ENUM_CHANGE = 7;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_CHANGE__START = PROPERTY_CHANGE__START;

	/**
	 * The feature id for the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_CHANGE__END = PROPERTY_CHANGE__END;

	/**
	 * The feature id for the '<em><b>Context</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_CHANGE__CONTEXT = PROPERTY_CHANGE__CONTEXT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_CHANGE__ELEMENT = PROPERTY_CHANGE__ELEMENT;

	/**
	 * The feature id for the '<em><b>Affected Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_CHANGE__AFFECTED_PROPERTY = PROPERTY_CHANGE__AFFECTED_PROPERTY;

	/**
	 * The feature id for the '<em><b>New Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_CHANGE__NEW_VALUE = PROPERTY_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Enum Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENUM_CHANGE_FEATURE_COUNT = PROPERTY_CHANGE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link urn.dyncontext.impl.DynamicContextImpl <em>Dynamic Context</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see urn.dyncontext.impl.DynamicContextImpl
	 * @see urn.dyncontext.impl.DyncontextPackageImpl#getDynamicContext()
	 * @generated
	 */
	int DYNAMIC_CONTEXT = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT__NAME = UrncorePackage.UR_NMODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT__FROM_LINKS = UrncorePackage.UR_NMODEL_ELEMENT__FROM_LINKS;

	/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT__TO_LINKS = UrncorePackage.UR_NMODEL_ELEMENT__TO_LINKS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT__ID = UrncorePackage.UR_NMODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT__DESCRIPTION = UrncorePackage.UR_NMODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT__METADATA = UrncorePackage.UR_NMODEL_ELEMENT__METADATA;

	/**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT__INCONCERN = UrncorePackage.UR_NMODEL_ELEMENT__INCONCERN;

	/**
	 * The feature id for the '<em><b>Parent Contexts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT__PARENT_CONTEXTS = UrncorePackage.UR_NMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Included Contexts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT__INCLUDED_CONTEXTS = UrncorePackage.UR_NMODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Strategy</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT__STRATEGY = UrncorePackage.UR_NMODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Contribution Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT__CONTRIBUTION_CONTEXT = UrncorePackage.UR_NMODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Scenario</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT__SCENARIO = UrncorePackage.UR_NMODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Urnspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT__URNSPEC = UrncorePackage.UR_NMODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Groups</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT__GROUPS = UrncorePackage.UR_NMODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Changes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT__CHANGES = UrncorePackage.UR_NMODEL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Dynamic Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT_FEATURE_COUNT = UrncorePackage.UR_NMODEL_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link urn.dyncontext.impl.DynamicContextGroupImpl <em>Dynamic Context Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see urn.dyncontext.impl.DynamicContextGroupImpl
	 * @see urn.dyncontext.impl.DyncontextPackageImpl#getDynamicContextGroup()
	 * @generated
	 */
	int DYNAMIC_CONTEXT_GROUP = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT_GROUP__NAME = UrncorePackage.UR_NMODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>From Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT_GROUP__FROM_LINKS = UrncorePackage.UR_NMODEL_ELEMENT__FROM_LINKS;

	/**
	 * The feature id for the '<em><b>To Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT_GROUP__TO_LINKS = UrncorePackage.UR_NMODEL_ELEMENT__TO_LINKS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT_GROUP__ID = UrncorePackage.UR_NMODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT_GROUP__DESCRIPTION = UrncorePackage.UR_NMODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Metadata</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT_GROUP__METADATA = UrncorePackage.UR_NMODEL_ELEMENT__METADATA;

	/**
	 * The feature id for the '<em><b>Inconcern</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT_GROUP__INCONCERN = UrncorePackage.UR_NMODEL_ELEMENT__INCONCERN;

	/**
	 * The feature id for the '<em><b>Urnspec</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT_GROUP__URNSPEC = UrncorePackage.UR_NMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Contexts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT_GROUP__CONTEXTS = UrncorePackage.UR_NMODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Dynamic Context Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DYNAMIC_CONTEXT_GROUP_FEATURE_COUNT = UrncorePackage.UR_NMODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link urn.dyncontext.impl.DeactivationChangeImpl <em>Deactivation Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see urn.dyncontext.impl.DeactivationChangeImpl
	 * @see urn.dyncontext.impl.DyncontextPackageImpl#getDeactivationChange()
	 * @generated
	 */
	int DEACTIVATION_CHANGE = 10;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEACTIVATION_CHANGE__START = CHANGE__START;

	/**
	 * The feature id for the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEACTIVATION_CHANGE__END = CHANGE__END;

	/**
	 * The feature id for the '<em><b>Context</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEACTIVATION_CHANGE__CONTEXT = CHANGE__CONTEXT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEACTIVATION_CHANGE__ELEMENT = CHANGE__ELEMENT;

	/**
	 * The number of structural features of the '<em>Deactivation Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEACTIVATION_CHANGE_FEATURE_COUNT = CHANGE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link urn.dyncontext.impl.ConstantChangeImpl <em>Constant Change</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see urn.dyncontext.impl.ConstantChangeImpl
	 * @see urn.dyncontext.impl.DyncontextPackageImpl#getConstantChange()
	 * @generated
	 */
	int CONSTANT_CHANGE = 11;

	/**
	 * The feature id for the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_CHANGE__START = NUMERIC_CHANGE__START;

	/**
	 * The feature id for the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_CHANGE__END = NUMERIC_CHANGE__END;

	/**
	 * The feature id for the '<em><b>Context</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_CHANGE__CONTEXT = NUMERIC_CHANGE__CONTEXT;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_CHANGE__ELEMENT = NUMERIC_CHANGE__ELEMENT;

	/**
	 * The feature id for the '<em><b>Affected Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_CHANGE__AFFECTED_PROPERTY = NUMERIC_CHANGE__AFFECTED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Sufficient Once Achieved</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_CHANGE__SUFFICIENT_ONCE_ACHIEVED = NUMERIC_CHANGE__SUFFICIENT_ONCE_ACHIEVED;

	/**
	 * The feature id for the '<em><b>New Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_CHANGE__NEW_VALUE = NUMERIC_CHANGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Constant Change</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTANT_CHANGE_FEATURE_COUNT = NUMERIC_CHANGE_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link urn.dyncontext.QuadraticChange <em>Quadratic Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Quadratic Change</em>'.
	 * @see urn.dyncontext.QuadraticChange
	 * @generated
	 */
	EClass getQuadraticChange();

	/**
	 * Returns the meta object for the attribute '{@link urn.dyncontext.QuadraticChange#getQuadraticCoefficient <em>Quadratic Coefficient</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Quadratic Coefficient</em>'.
	 * @see urn.dyncontext.QuadraticChange#getQuadraticCoefficient()
	 * @see #getQuadraticChange()
	 * @generated
	 */
	EAttribute getQuadraticChange_QuadraticCoefficient();

	/**
	 * Returns the meta object for the attribute '{@link urn.dyncontext.QuadraticChange#getLinearCoefficient <em>Linear Coefficient</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Linear Coefficient</em>'.
	 * @see urn.dyncontext.QuadraticChange#getLinearCoefficient()
	 * @see #getQuadraticChange()
	 * @generated
	 */
	EAttribute getQuadraticChange_LinearCoefficient();

	/**
	 * Returns the meta object for the attribute '{@link urn.dyncontext.QuadraticChange#getConstant <em>Constant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Constant</em>'.
	 * @see urn.dyncontext.QuadraticChange#getConstant()
	 * @see #getQuadraticChange()
	 * @generated
	 */
	EAttribute getQuadraticChange_Constant();

	/**
	 * Returns the meta object for class '{@link urn.dyncontext.TimepointGroup <em>Timepoint Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Timepoint Group</em>'.
	 * @see urn.dyncontext.TimepointGroup
	 * @generated
	 */
	EClass getTimepointGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link urn.dyncontext.TimepointGroup#getTimepoints <em>Timepoints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Timepoints</em>'.
	 * @see urn.dyncontext.TimepointGroup#getTimepoints()
	 * @see #getTimepointGroup()
	 * @generated
	 */
	EReference getTimepointGroup_Timepoints();

	/**
	 * Returns the meta object for the container reference '{@link urn.dyncontext.TimepointGroup#getUrnspec <em>Urnspec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Urnspec</em>'.
	 * @see urn.dyncontext.TimepointGroup#getUrnspec()
	 * @see #getTimepointGroup()
	 * @generated
	 */
	EReference getTimepointGroup_Urnspec();

	/**
	 * Returns the meta object for class '{@link urn.dyncontext.Timepoint <em>Timepoint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Timepoint</em>'.
	 * @see urn.dyncontext.Timepoint
	 * @generated
	 */
	EClass getTimepoint();

	/**
	 * Returns the meta object for the attribute '{@link urn.dyncontext.Timepoint#getTimepoint <em>Timepoint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Timepoint</em>'.
	 * @see urn.dyncontext.Timepoint#getTimepoint()
	 * @see #getTimepoint()
	 * @generated
	 */
	EAttribute getTimepoint_Timepoint();

	/**
	 * Returns the meta object for the container reference '{@link urn.dyncontext.Timepoint#getGroup <em>Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Group</em>'.
	 * @see urn.dyncontext.Timepoint#getGroup()
	 * @see #getTimepoint()
	 * @generated
	 */
	EReference getTimepoint_Group();

	/**
	 * Returns the meta object for class '{@link urn.dyncontext.PropertyChange <em>Property Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Change</em>'.
	 * @see urn.dyncontext.PropertyChange
	 * @generated
	 */
	EClass getPropertyChange();

	/**
	 * Returns the meta object for the attribute '{@link urn.dyncontext.PropertyChange#getAffectedProperty <em>Affected Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Affected Property</em>'.
	 * @see urn.dyncontext.PropertyChange#getAffectedProperty()
	 * @see #getPropertyChange()
	 * @generated
	 */
	EAttribute getPropertyChange_AffectedProperty();

	/**
	 * Returns the meta object for class '{@link urn.dyncontext.NumericChange <em>Numeric Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Numeric Change</em>'.
	 * @see urn.dyncontext.NumericChange
	 * @generated
	 */
	EClass getNumericChange();

	/**
	 * Returns the meta object for the attribute '{@link urn.dyncontext.NumericChange#isSufficientOnceAchieved <em>Sufficient Once Achieved</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sufficient Once Achieved</em>'.
	 * @see urn.dyncontext.NumericChange#isSufficientOnceAchieved()
	 * @see #getNumericChange()
	 * @generated
	 */
	EAttribute getNumericChange_SufficientOnceAchieved();

	/**
	 * Returns the meta object for class '{@link urn.dyncontext.FormulaChange <em>Formula Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Formula Change</em>'.
	 * @see urn.dyncontext.FormulaChange
	 * @generated
	 */
	EClass getFormulaChange();

	/**
	 * Returns the meta object for the attribute '{@link urn.dyncontext.FormulaChange#getFormula <em>Formula</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Formula</em>'.
	 * @see urn.dyncontext.FormulaChange#getFormula()
	 * @see #getFormulaChange()
	 * @generated
	 */
	EAttribute getFormulaChange_Formula();

	/**
	 * Returns the meta object for class '{@link urn.dyncontext.LinearChange <em>Linear Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Linear Change</em>'.
	 * @see urn.dyncontext.LinearChange
	 * @generated
	 */
	EClass getLinearChange();

	/**
	 * Returns the meta object for the attribute '{@link urn.dyncontext.LinearChange#getNewValue <em>New Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>New Value</em>'.
	 * @see urn.dyncontext.LinearChange#getNewValue()
	 * @see #getLinearChange()
	 * @generated
	 */
	EAttribute getLinearChange_NewValue();

	/**
	 * Returns the meta object for class '{@link urn.dyncontext.EnumChange <em>Enum Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enum Change</em>'.
	 * @see urn.dyncontext.EnumChange
	 * @generated
	 */
	EClass getEnumChange();

	/**
	 * Returns the meta object for the attribute '{@link urn.dyncontext.EnumChange#getNewValue <em>New Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>New Value</em>'.
	 * @see urn.dyncontext.EnumChange#getNewValue()
	 * @see #getEnumChange()
	 * @generated
	 */
	EAttribute getEnumChange_NewValue();

	/**
	 * Returns the meta object for class '{@link urn.dyncontext.DynamicContext <em>Dynamic Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dynamic Context</em>'.
	 * @see urn.dyncontext.DynamicContext
	 * @generated
	 */
	EClass getDynamicContext();

	/**
	 * Returns the meta object for the reference list '{@link urn.dyncontext.DynamicContext#getParentContexts <em>Parent Contexts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parent Contexts</em>'.
	 * @see urn.dyncontext.DynamicContext#getParentContexts()
	 * @see #getDynamicContext()
	 * @generated
	 */
	EReference getDynamicContext_ParentContexts();

	/**
	 * Returns the meta object for the reference list '{@link urn.dyncontext.DynamicContext#getIncludedContexts <em>Included Contexts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Included Contexts</em>'.
	 * @see urn.dyncontext.DynamicContext#getIncludedContexts()
	 * @see #getDynamicContext()
	 * @generated
	 */
	EReference getDynamicContext_IncludedContexts();

	/**
	 * Returns the meta object for the reference '{@link urn.dyncontext.DynamicContext#getStrategy <em>Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Strategy</em>'.
	 * @see urn.dyncontext.DynamicContext#getStrategy()
	 * @see #getDynamicContext()
	 * @generated
	 */
	EReference getDynamicContext_Strategy();

	/**
	 * Returns the meta object for the reference '{@link urn.dyncontext.DynamicContext#getContributionContext <em>Contribution Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Contribution Context</em>'.
	 * @see urn.dyncontext.DynamicContext#getContributionContext()
	 * @see #getDynamicContext()
	 * @generated
	 */
	EReference getDynamicContext_ContributionContext();

	/**
	 * Returns the meta object for the reference '{@link urn.dyncontext.DynamicContext#getScenario <em>Scenario</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Scenario</em>'.
	 * @see urn.dyncontext.DynamicContext#getScenario()
	 * @see #getDynamicContext()
	 * @generated
	 */
	EReference getDynamicContext_Scenario();

	/**
	 * Returns the meta object for the container reference '{@link urn.dyncontext.DynamicContext#getUrnspec <em>Urnspec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Urnspec</em>'.
	 * @see urn.dyncontext.DynamicContext#getUrnspec()
	 * @see #getDynamicContext()
	 * @generated
	 */
	EReference getDynamicContext_Urnspec();

	/**
	 * Returns the meta object for the reference list '{@link urn.dyncontext.DynamicContext#getGroups <em>Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Groups</em>'.
	 * @see urn.dyncontext.DynamicContext#getGroups()
	 * @see #getDynamicContext()
	 * @generated
	 */
	EReference getDynamicContext_Groups();

	/**
	 * Returns the meta object for the containment reference list '{@link urn.dyncontext.DynamicContext#getChanges <em>Changes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Changes</em>'.
	 * @see urn.dyncontext.DynamicContext#getChanges()
	 * @see #getDynamicContext()
	 * @generated
	 */
	EReference getDynamicContext_Changes();

	/**
	 * Returns the meta object for class '{@link urn.dyncontext.DynamicContextGroup <em>Dynamic Context Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dynamic Context Group</em>'.
	 * @see urn.dyncontext.DynamicContextGroup
	 * @generated
	 */
	EClass getDynamicContextGroup();

	/**
	 * Returns the meta object for the container reference '{@link urn.dyncontext.DynamicContextGroup#getUrnspec <em>Urnspec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Urnspec</em>'.
	 * @see urn.dyncontext.DynamicContextGroup#getUrnspec()
	 * @see #getDynamicContextGroup()
	 * @generated
	 */
	EReference getDynamicContextGroup_Urnspec();

	/**
	 * Returns the meta object for the reference list '{@link urn.dyncontext.DynamicContextGroup#getContexts <em>Contexts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Contexts</em>'.
	 * @see urn.dyncontext.DynamicContextGroup#getContexts()
	 * @see #getDynamicContextGroup()
	 * @generated
	 */
	EReference getDynamicContextGroup_Contexts();

	/**
	 * Returns the meta object for class '{@link urn.dyncontext.DeactivationChange <em>Deactivation Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deactivation Change</em>'.
	 * @see urn.dyncontext.DeactivationChange
	 * @generated
	 */
	EClass getDeactivationChange();

	/**
	 * Returns the meta object for class '{@link urn.dyncontext.ConstantChange <em>Constant Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constant Change</em>'.
	 * @see urn.dyncontext.ConstantChange
	 * @generated
	 */
	EClass getConstantChange();

	/**
	 * Returns the meta object for the attribute '{@link urn.dyncontext.ConstantChange#getNewValue <em>New Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>New Value</em>'.
	 * @see urn.dyncontext.ConstantChange#getNewValue()
	 * @see #getConstantChange()
	 * @generated
	 */
	EAttribute getConstantChange_NewValue();

	/**
	 * Returns the meta object for class '{@link urn.dyncontext.Change <em>Change</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Change</em>'.
	 * @see urn.dyncontext.Change
	 * @generated
	 */
	EClass getChange();

	/**
	 * Returns the meta object for the attribute '{@link urn.dyncontext.Change#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Start</em>'.
	 * @see urn.dyncontext.Change#getStart()
	 * @see #getChange()
	 * @generated
	 */
	EAttribute getChange_Start();

	/**
	 * Returns the meta object for the attribute '{@link urn.dyncontext.Change#getEnd <em>End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>End</em>'.
	 * @see urn.dyncontext.Change#getEnd()
	 * @see #getChange()
	 * @generated
	 */
	EAttribute getChange_End();

	/**
	 * Returns the meta object for the container reference '{@link urn.dyncontext.Change#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Context</em>'.
	 * @see urn.dyncontext.Change#getContext()
	 * @see #getChange()
	 * @generated
	 */
	EReference getChange_Context();

	/**
	 * Returns the meta object for the reference '{@link urn.dyncontext.Change#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Element</em>'.
	 * @see urn.dyncontext.Change#getElement()
	 * @see #getChange()
	 * @generated
	 */
	EReference getChange_Element();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DyncontextFactory getDyncontextFactory();

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
		 * The meta object literal for the '{@link urn.dyncontext.impl.QuadraticChangeImpl <em>Quadratic Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urn.dyncontext.impl.QuadraticChangeImpl
		 * @see urn.dyncontext.impl.DyncontextPackageImpl#getQuadraticChange()
		 * @generated
		 */
		EClass QUADRATIC_CHANGE = eINSTANCE.getQuadraticChange();

		/**
		 * The meta object literal for the '<em><b>Quadratic Coefficient</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUADRATIC_CHANGE__QUADRATIC_COEFFICIENT = eINSTANCE.getQuadraticChange_QuadraticCoefficient();

		/**
		 * The meta object literal for the '<em><b>Linear Coefficient</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUADRATIC_CHANGE__LINEAR_COEFFICIENT = eINSTANCE.getQuadraticChange_LinearCoefficient();

		/**
		 * The meta object literal for the '<em><b>Constant</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUADRATIC_CHANGE__CONSTANT = eINSTANCE.getQuadraticChange_Constant();

		/**
		 * The meta object literal for the '{@link urn.dyncontext.impl.TimepointGroupImpl <em>Timepoint Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urn.dyncontext.impl.TimepointGroupImpl
		 * @see urn.dyncontext.impl.DyncontextPackageImpl#getTimepointGroup()
		 * @generated
		 */
		EClass TIMEPOINT_GROUP = eINSTANCE.getTimepointGroup();

		/**
		 * The meta object literal for the '<em><b>Timepoints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIMEPOINT_GROUP__TIMEPOINTS = eINSTANCE.getTimepointGroup_Timepoints();

		/**
		 * The meta object literal for the '<em><b>Urnspec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIMEPOINT_GROUP__URNSPEC = eINSTANCE.getTimepointGroup_Urnspec();

		/**
		 * The meta object literal for the '{@link urn.dyncontext.impl.TimepointImpl <em>Timepoint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urn.dyncontext.impl.TimepointImpl
		 * @see urn.dyncontext.impl.DyncontextPackageImpl#getTimepoint()
		 * @generated
		 */
		EClass TIMEPOINT = eINSTANCE.getTimepoint();

		/**
		 * The meta object literal for the '<em><b>Timepoint</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TIMEPOINT__TIMEPOINT = eINSTANCE.getTimepoint_Timepoint();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TIMEPOINT__GROUP = eINSTANCE.getTimepoint_Group();

		/**
		 * The meta object literal for the '{@link urn.dyncontext.impl.PropertyChangeImpl <em>Property Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urn.dyncontext.impl.PropertyChangeImpl
		 * @see urn.dyncontext.impl.DyncontextPackageImpl#getPropertyChange()
		 * @generated
		 */
		EClass PROPERTY_CHANGE = eINSTANCE.getPropertyChange();

		/**
		 * The meta object literal for the '<em><b>Affected Property</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY_CHANGE__AFFECTED_PROPERTY = eINSTANCE.getPropertyChange_AffectedProperty();

		/**
		 * The meta object literal for the '{@link urn.dyncontext.impl.NumericChangeImpl <em>Numeric Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urn.dyncontext.impl.NumericChangeImpl
		 * @see urn.dyncontext.impl.DyncontextPackageImpl#getNumericChange()
		 * @generated
		 */
		EClass NUMERIC_CHANGE = eINSTANCE.getNumericChange();

		/**
		 * The meta object literal for the '<em><b>Sufficient Once Achieved</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NUMERIC_CHANGE__SUFFICIENT_ONCE_ACHIEVED = eINSTANCE.getNumericChange_SufficientOnceAchieved();

		/**
		 * The meta object literal for the '{@link urn.dyncontext.impl.FormulaChangeImpl <em>Formula Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urn.dyncontext.impl.FormulaChangeImpl
		 * @see urn.dyncontext.impl.DyncontextPackageImpl#getFormulaChange()
		 * @generated
		 */
		EClass FORMULA_CHANGE = eINSTANCE.getFormulaChange();

		/**
		 * The meta object literal for the '<em><b>Formula</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORMULA_CHANGE__FORMULA = eINSTANCE.getFormulaChange_Formula();

		/**
		 * The meta object literal for the '{@link urn.dyncontext.impl.LinearChangeImpl <em>Linear Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urn.dyncontext.impl.LinearChangeImpl
		 * @see urn.dyncontext.impl.DyncontextPackageImpl#getLinearChange()
		 * @generated
		 */
		EClass LINEAR_CHANGE = eINSTANCE.getLinearChange();

		/**
		 * The meta object literal for the '<em><b>New Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINEAR_CHANGE__NEW_VALUE = eINSTANCE.getLinearChange_NewValue();

		/**
		 * The meta object literal for the '{@link urn.dyncontext.impl.EnumChangeImpl <em>Enum Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urn.dyncontext.impl.EnumChangeImpl
		 * @see urn.dyncontext.impl.DyncontextPackageImpl#getEnumChange()
		 * @generated
		 */
		EClass ENUM_CHANGE = eINSTANCE.getEnumChange();

		/**
		 * The meta object literal for the '<em><b>New Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENUM_CHANGE__NEW_VALUE = eINSTANCE.getEnumChange_NewValue();

		/**
		 * The meta object literal for the '{@link urn.dyncontext.impl.DynamicContextImpl <em>Dynamic Context</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urn.dyncontext.impl.DynamicContextImpl
		 * @see urn.dyncontext.impl.DyncontextPackageImpl#getDynamicContext()
		 * @generated
		 */
		EClass DYNAMIC_CONTEXT = eINSTANCE.getDynamicContext();

		/**
		 * The meta object literal for the '<em><b>Parent Contexts</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_CONTEXT__PARENT_CONTEXTS = eINSTANCE.getDynamicContext_ParentContexts();

		/**
		 * The meta object literal for the '<em><b>Included Contexts</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_CONTEXT__INCLUDED_CONTEXTS = eINSTANCE.getDynamicContext_IncludedContexts();

		/**
		 * The meta object literal for the '<em><b>Strategy</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_CONTEXT__STRATEGY = eINSTANCE.getDynamicContext_Strategy();

		/**
		 * The meta object literal for the '<em><b>Contribution Context</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_CONTEXT__CONTRIBUTION_CONTEXT = eINSTANCE.getDynamicContext_ContributionContext();

		/**
		 * The meta object literal for the '<em><b>Scenario</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_CONTEXT__SCENARIO = eINSTANCE.getDynamicContext_Scenario();

		/**
		 * The meta object literal for the '<em><b>Urnspec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_CONTEXT__URNSPEC = eINSTANCE.getDynamicContext_Urnspec();

		/**
		 * The meta object literal for the '<em><b>Groups</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_CONTEXT__GROUPS = eINSTANCE.getDynamicContext_Groups();

		/**
		 * The meta object literal for the '<em><b>Changes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_CONTEXT__CHANGES = eINSTANCE.getDynamicContext_Changes();

		/**
		 * The meta object literal for the '{@link urn.dyncontext.impl.DynamicContextGroupImpl <em>Dynamic Context Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urn.dyncontext.impl.DynamicContextGroupImpl
		 * @see urn.dyncontext.impl.DyncontextPackageImpl#getDynamicContextGroup()
		 * @generated
		 */
		EClass DYNAMIC_CONTEXT_GROUP = eINSTANCE.getDynamicContextGroup();

		/**
		 * The meta object literal for the '<em><b>Urnspec</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_CONTEXT_GROUP__URNSPEC = eINSTANCE.getDynamicContextGroup_Urnspec();

		/**
		 * The meta object literal for the '<em><b>Contexts</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_CONTEXT_GROUP__CONTEXTS = eINSTANCE.getDynamicContextGroup_Contexts();

		/**
		 * The meta object literal for the '{@link urn.dyncontext.impl.DeactivationChangeImpl <em>Deactivation Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urn.dyncontext.impl.DeactivationChangeImpl
		 * @see urn.dyncontext.impl.DyncontextPackageImpl#getDeactivationChange()
		 * @generated
		 */
		EClass DEACTIVATION_CHANGE = eINSTANCE.getDeactivationChange();

		/**
		 * The meta object literal for the '{@link urn.dyncontext.impl.ConstantChangeImpl <em>Constant Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urn.dyncontext.impl.ConstantChangeImpl
		 * @see urn.dyncontext.impl.DyncontextPackageImpl#getConstantChange()
		 * @generated
		 */
		EClass CONSTANT_CHANGE = eINSTANCE.getConstantChange();

		/**
		 * The meta object literal for the '<em><b>New Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTANT_CHANGE__NEW_VALUE = eINSTANCE.getConstantChange_NewValue();

		/**
		 * The meta object literal for the '{@link urn.dyncontext.impl.ChangeImpl <em>Change</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see urn.dyncontext.impl.ChangeImpl
		 * @see urn.dyncontext.impl.DyncontextPackageImpl#getChange()
		 * @generated
		 */
		EClass CHANGE = eINSTANCE.getChange();

		/**
		 * The meta object literal for the '<em><b>Start</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHANGE__START = eINSTANCE.getChange_Start();

		/**
		 * The meta object literal for the '<em><b>End</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHANGE__END = eINSTANCE.getChange_End();

		/**
		 * The meta object literal for the '<em><b>Context</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHANGE__CONTEXT = eINSTANCE.getChange_Context();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CHANGE__ELEMENT = eINSTANCE.getChange_Element();

	}

} //DyncontextPackage
