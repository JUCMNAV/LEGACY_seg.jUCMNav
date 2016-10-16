/**
 */
package urn.dyncontext;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see urn.dyncontext.DyncontextPackage
 * @generated
 */
public interface DyncontextFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DyncontextFactory eINSTANCE = urn.dyncontext.impl.DyncontextFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Quadratic Change</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Quadratic Change</em>'.
	 * @generated
	 */
	QuadraticChange createQuadraticChange();

	/**
	 * Returns a new object of class '<em>Timepoint Group</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Timepoint Group</em>'.
	 * @generated
	 */
	TimepointGroup createTimepointGroup();

	/**
	 * Returns a new object of class '<em>Timepoint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Timepoint</em>'.
	 * @generated
	 */
	Timepoint createTimepoint();

	/**
	 * Returns a new object of class '<em>Formula Change</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Formula Change</em>'.
	 * @generated
	 */
	FormulaChange createFormulaChange();

	/**
	 * Returns a new object of class '<em>Linear Change</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Linear Change</em>'.
	 * @generated
	 */
	LinearChange createLinearChange();

	/**
	 * Returns a new object of class '<em>Enum Change</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Enum Change</em>'.
	 * @generated
	 */
	EnumChange createEnumChange();

	/**
	 * Returns a new object of class '<em>Dynamic Context</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dynamic Context</em>'.
	 * @generated
	 */
	DynamicContext createDynamicContext();

	/**
	 * Returns a new object of class '<em>Dynamic Context Group</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Dynamic Context Group</em>'.
	 * @generated
	 */
	DynamicContextGroup createDynamicContextGroup();

	/**
	 * Returns a new object of class '<em>Deactivation Change</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Deactivation Change</em>'.
	 * @generated
	 */
	DeactivationChange createDeactivationChange();

	/**
	 * Returns a new object of class '<em>Constant Change</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Constant Change</em>'.
	 * @generated
	 */
	ConstantChange createConstantChange();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	DyncontextPackage getDyncontextPackage();

} //DyncontextFactory
