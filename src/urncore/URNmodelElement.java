/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import ca.mcgill.sel.core.CORENamedElement;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>UR Nmodel Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.URNmodelElement#getFromLinks <em>From Links</em>}</li>
 *   <li>{@link urncore.URNmodelElement#getToLinks <em>To Links</em>}</li>
 *   <li>{@link urncore.URNmodelElement#getId <em>Id</em>}</li>
 *   <li>{@link urncore.URNmodelElement#getDescription <em>Description</em>}</li>
 *   <li>{@link urncore.URNmodelElement#getMetadata <em>Metadata</em>}</li>
 *   <li>{@link urncore.URNmodelElement#getInconcern <em>Inconcern</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getURNmodelElement()
 * @model abstract="true"
 * @generated
 */
public interface URNmodelElement extends CORENamedElement {
    /**
	 * Returns the value of the '<em><b>From Links</b></em>' reference list.
	 * The list contents are of type {@link urn.URNlink}.
	 * It is bidirectional and its opposite is '{@link urn.URNlink#getFromElem <em>From Elem</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>From Links</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>From Links</em>' reference list.
	 * @see urncore.UrncorePackage#getURNmodelElement_FromLinks()
	 * @see urn.URNlink#getFromElem
	 * @model type="urn.URNlink" opposite="fromElem"
	 * @generated
	 */
    EList getFromLinks();

    /**
	 * Returns the value of the '<em><b>To Links</b></em>' reference list.
	 * The list contents are of type {@link urn.URNlink}.
	 * It is bidirectional and its opposite is '{@link urn.URNlink#getToElem <em>To Elem</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>To Links</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>To Links</em>' reference list.
	 * @see urncore.UrncorePackage#getURNmodelElement_ToLinks()
	 * @see urn.URNlink#getToElem
	 * @model type="urn.URNlink" opposite="toElem"
	 * @generated
	 */
    EList getToLinks();

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
	 * @see urncore.UrncorePackage#getURNmodelElement_Id()
	 * @model id="true"
	 * @generated
	 */
    String getId();

    /**
	 * Sets the value of the '{@link urncore.URNmodelElement#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
    void setId(String value);

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
	 * @see urncore.UrncorePackage#getURNmodelElement_Description()
	 * @model
	 * @generated
	 */
    String getDescription();

    /**
	 * Sets the value of the '{@link urncore.URNmodelElement#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
    void setDescription(String value);

    /**
	 * Returns the value of the '<em><b>Metadata</b></em>' containment reference list.
	 * The list contents are of type {@link urncore.Metadata}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metadata</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metadata</em>' containment reference list.
	 * @see urncore.UrncorePackage#getURNmodelElement_Metadata()
	 * @model type="urncore.Metadata" containment="true"
	 * @generated
	 */
	EList getMetadata();

    /**
	 * Returns the value of the '<em><b>Inconcern</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link urncore.Concern#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Inconcern</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Inconcern</em>' reference.
	 * @see #setInconcern(Concern)
	 * @see urncore.UrncorePackage#getURNmodelElement_Inconcern()
	 * @see urncore.Concern#getElements
	 * @model opposite="elements"
	 * @generated
	 */
    Concern getInconcern();

    /**
	 * Sets the value of the '{@link urncore.URNmodelElement#getInconcern <em>Inconcern</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inconcern</em>' reference.
	 * @see #getInconcern()
	 * @generated
	 */
    void setInconcern(Concern value);

} // URNmodelElement
