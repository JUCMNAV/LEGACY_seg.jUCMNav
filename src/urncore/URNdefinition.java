/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>UR Ndefinition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.URNdefinition#getResponsibilities <em>Responsibilities</em>}</li>
 *   <li>{@link urncore.URNdefinition#getComponents <em>Components</em>}</li>
 *   <li>{@link urncore.URNdefinition#getGRLelements <em>GR Lelements</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getURNdefinition()
 * @model 
 * @generated
 */
public interface URNdefinition extends EObject {
    /**
     * Returns the value of the '<em><b>Responsibilities</b></em>' containment reference list.
     * The list contents are of type {@link urncore.Responsibility}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Responsibilities</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Responsibilities</em>' containment reference list.
     * @see urncore.UrncorePackage#getURNdefinition_Responsibilities()
     * @model type="urncore.Responsibility" containment="true"
     * @generated
     */
    EList getResponsibilities();

    /**
     * Returns the value of the '<em><b>Components</b></em>' containment reference list.
     * The list contents are of type {@link urncore.ComponentElement}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Components</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Components</em>' containment reference list.
     * @see urncore.UrncorePackage#getURNdefinition_Components()
     * @model type="urncore.ComponentElement" containment="true"
     * @generated
     */
    EList getComponents();

    /**
     * Returns the value of the '<em><b>GR Lelements</b></em>' containment reference list.
     * The list contents are of type {@link urncore.GRLmodelElement}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>GR Lelements</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>GR Lelements</em>' containment reference list.
     * @see urncore.UrncorePackage#getURNdefinition_GRLelements()
     * @model type="urncore.GRLmodelElement" containment="true"
     * @generated
     */
    EList getGRLelements();

} // URNdefinition
