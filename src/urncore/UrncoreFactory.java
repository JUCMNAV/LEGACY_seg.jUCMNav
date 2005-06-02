/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see urncore.UrncorePackage
 * @generated
 */
public interface UrncoreFactory extends EFactory{
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	UrncoreFactory eINSTANCE = new urncore.impl.UrncoreFactoryImpl();

    /**
     * Returns a new object of class '<em>UR Ndefinition</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>UR Ndefinition</em>'.
     * @generated
     */
	URNdefinition createURNdefinition();

    /**
     * Returns a new object of class '<em>Responsibility</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Responsibility</em>'.
     * @generated
     */
	Responsibility createResponsibility();

    /**
     * Returns a new object of class '<em>Pool</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Pool</em>'.
     * @generated
     */
	Pool createPool();

    /**
     * Returns a new object of class '<em>Component</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Component</em>'.
     * @generated
     */
	Component createComponent();

    /**
     * Returns a new object of class '<em>Component Type</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Component Type</em>'.
     * @generated
     */
	ComponentType createComponentType();

    /**
     * Returns a new object of class '<em>Dynamic Responsibility</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Dynamic Responsibility</em>'.
     * @generated
     */
	DynamicResponsibility createDynamicResponsibility();

    /**
     * Returns a new object of class '<em>Node Label</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Node Label</em>'.
     * @generated
     */
	NodeLabel createNodeLabel();

    /**
     * Returns a new object of class '<em>Component Label</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Component Label</em>'.
     * @generated
     */
	ComponentLabel createComponentLabel();

    /**
     * Returns a new object of class '<em>New Class</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>New Class</em>'.
     * @generated
     */
	NewClass createNewClass();

    /**
     * Returns a new object of class '<em>Condition</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Condition</em>'.
     * @generated
     */
	Condition createCondition();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	UrncorePackage getUrncorePackage();

} //UrncoreFactory
