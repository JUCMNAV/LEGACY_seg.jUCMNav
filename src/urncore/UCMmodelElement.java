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
 * A representation of the model object '<em><b>UC Mmodel Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.UCMmodelElement#getUrnLinks <em>Urn Links</em>}</li>
 *   <li>{@link urncore.UCMmodelElement#getId <em>Id</em>}</li>
 *   <li>{@link urncore.UCMmodelElement#getName <em>Name</em>}</li>
 *   <li>{@link urncore.UCMmodelElement#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getUCMmodelElement()
 * @model abstract="true"
 * @generated
 */
public interface UCMmodelElement extends EObject{
    /**
     * Returns the value of the '<em><b>Urn Links</b></em>' reference list.
     * The list contents are of type {@link urn.URNlink}.
     * It is bidirectional and its opposite is '{@link urn.URNlink#getUcmElems <em>Ucm Elems</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Urn Links</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Urn Links</em>' reference list.
     * @see urncore.UrncorePackage#getUCMmodelElement_UrnLinks()
     * @see urn.URNlink#getUcmElems
     * @model type="urn.URNlink" opposite="ucmElems"
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
     * @see urncore.UrncorePackage#getUCMmodelElement_Id()
     * @model
     * @generated
     */
	String getId();

    /**
     * Sets the value of the '{@link urncore.UCMmodelElement#getId <em>Id</em>}' attribute.
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
     * @see urncore.UrncorePackage#getUCMmodelElement_Name()
     * @model
     * @generated
     */
	String getName();

    /**
     * Sets the value of the '{@link urncore.UCMmodelElement#getName <em>Name</em>}' attribute.
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
     * @see urncore.UrncorePackage#getUCMmodelElement_Description()
     * @model
     * @generated
     */
	String getDescription();

    /**
     * Sets the value of the '{@link urncore.UCMmodelElement#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
	void setDescription(String value);

} // UCMmodelElement
