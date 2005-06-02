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
 * A representation of the model object '<em><b>GR Lmodel Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.GRLmodelElement#getUrnLinks <em>Urn Links</em>}</li>
 *   <li>{@link urncore.GRLmodelElement#getId <em>Id</em>}</li>
 *   <li>{@link urncore.GRLmodelElement#getName <em>Name</em>}</li>
 *   <li>{@link urncore.GRLmodelElement#getDescription <em>Description</em>}</li>
 *   <li>{@link urncore.GRLmodelElement#getUrndefinition <em>Urndefinition</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getGRLmodelElement()
 * @model abstract="true"
 * @generated
 */
public interface GRLmodelElement extends EObject{
    /**
     * Returns the value of the '<em><b>Urn Links</b></em>' reference list.
     * The list contents are of type {@link urn.URNlink}.
     * It is bidirectional and its opposite is '{@link urn.URNlink#getGrlElems <em>Grl Elems</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Urn Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Urn Links</em>' reference list.
     * @see urncore.UrncorePackage#getGRLmodelElement_UrnLinks()
     * @see urn.URNlink#getGrlElems
     * @model type="urn.URNlink" opposite="grlElems"
     * @generated
     */
	EList getUrnLinks();

    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see urncore.UrncorePackage#getGRLmodelElement_Id()
     * @model 
     * @generated
     */
	String getId();

    /**
     * Sets the value of the '{@link urncore.GRLmodelElement#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
	void setId(String value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see urncore.UrncorePackage#getGRLmodelElement_Name()
     * @model 
     * @generated
     */
	String getName();

    /**
     * Sets the value of the '{@link urncore.GRLmodelElement#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
	void setName(String value);

    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see urncore.UrncorePackage#getGRLmodelElement_Description()
     * @model 
     * @generated
     */
	String getDescription();

    /**
     * Sets the value of the '{@link urncore.GRLmodelElement#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
	void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Urndefinition</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link urncore.URNdefinition#getGRLelements <em>GR Lelements</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Urndefinition</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Urndefinition</em>' container reference.
     * @see #setUrndefinition(URNdefinition)
     * @see urncore.UrncorePackage#getGRLmodelElement_Urndefinition()
     * @see urncore.URNdefinition#getGRLelements
     * @model opposite="GRLelements" required="true"
     * @generated
     */
	URNdefinition getUrndefinition();

    /**
     * Sets the value of the '{@link urncore.GRLmodelElement#getUrndefinition <em>Urndefinition</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Urndefinition</em>' container reference.
     * @see #getUrndefinition()
     * @generated
     */
	void setUrndefinition(URNdefinition value);

} // GRLmodelElement
