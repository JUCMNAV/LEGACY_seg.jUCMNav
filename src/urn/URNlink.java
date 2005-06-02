/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urn;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>UR Nlink</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urn.URNlink#getId <em>Id</em>}</li>
 *   <li>{@link urn.URNlink#getName <em>Name</em>}</li>
 *   <li>{@link urn.URNlink#getDescription <em>Description</em>}</li>
 *   <li>{@link urn.URNlink#getKind <em>Kind</em>}</li>
 *   <li>{@link urn.URNlink#getUrnspec <em>Urnspec</em>}</li>
 *   <li>{@link urn.URNlink#getGrlElems <em>Grl Elems</em>}</li>
 *   <li>{@link urn.URNlink#getUcmElems <em>Ucm Elems</em>}</li>
 * </ul>
 * </p>
 *
 * @see urn.UrnPackage#getURNlink()
 * @model 
 * @generated
 */
public interface URNlink extends EObject{
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
     * @see urn.UrnPackage#getURNlink_Id()
     * @model 
     * @generated
     */
	String getId();

    /**
     * Sets the value of the '{@link urn.URNlink#getId <em>Id</em>}' attribute.
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
     * @see urn.UrnPackage#getURNlink_Name()
     * @model 
     * @generated
     */
	String getName();

    /**
     * Sets the value of the '{@link urn.URNlink#getName <em>Name</em>}' attribute.
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
     * @see urn.UrnPackage#getURNlink_Description()
     * @model 
     * @generated
     */
	String getDescription();

    /**
     * Sets the value of the '{@link urn.URNlink#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
	void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Kind</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Kind</em>' attribute.
     * @see #setKind(String)
     * @see urn.UrnPackage#getURNlink_Kind()
     * @model 
     * @generated
     */
	String getKind();

    /**
     * Sets the value of the '{@link urn.URNlink#getKind <em>Kind</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Kind</em>' attribute.
     * @see #getKind()
     * @generated
     */
	void setKind(String value);

    /**
     * Returns the value of the '<em><b>Urnspec</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link urn.URNspec#getUrnLinks <em>Urn Links</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Urnspec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Urnspec</em>' container reference.
     * @see #setUrnspec(URNspec)
     * @see urn.UrnPackage#getURNlink_Urnspec()
     * @see urn.URNspec#getUrnLinks
     * @model opposite="urnLinks" required="true"
     * @generated
     */
	URNspec getUrnspec();

    /**
     * Sets the value of the '{@link urn.URNlink#getUrnspec <em>Urnspec</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Urnspec</em>' container reference.
     * @see #getUrnspec()
     * @generated
     */
	void setUrnspec(URNspec value);

    /**
     * Returns the value of the '<em><b>Grl Elems</b></em>' reference list.
     * The list contents are of type {@link urncore.GRLmodelElement}.
     * It is bidirectional and its opposite is '{@link urncore.GRLmodelElement#getUrnLinks <em>Urn Links</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grl Elems</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Grl Elems</em>' reference list.
     * @see urn.UrnPackage#getURNlink_GrlElems()
     * @see urncore.GRLmodelElement#getUrnLinks
     * @model type="urncore.GRLmodelElement" opposite="urnLinks"
     * @generated
     */
	EList getGrlElems();

    /**
     * Returns the value of the '<em><b>Ucm Elems</b></em>' reference list.
     * The list contents are of type {@link urncore.UCMmodelElement}.
     * It is bidirectional and its opposite is '{@link urncore.UCMmodelElement#getUrnLinks <em>Urn Links</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ucm Elems</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Ucm Elems</em>' reference list.
     * @see urn.UrnPackage#getURNlink_UcmElems()
     * @see urncore.UCMmodelElement#getUrnLinks
     * @model type="urncore.UCMmodelElement" opposite="urnLinks"
     * @generated
     */
	EList getUcmElems();

} // URNlink
