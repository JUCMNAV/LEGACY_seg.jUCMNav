/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import org.eclipse.emf.common.util.EList;

import ucm.performance.ProcessingResource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Regular</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * ComponentRegular can be protected (e.g., by a semaphone) or not. Dynamic components have the slot attribute set to true. ComponentRegular can include sub-components.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.ComponentRegular#getKind <em>Kind</em>}</li>
 *   <li>{@link urncore.ComponentRegular#isProtected <em>Protected</em>}</li>
 *   <li>{@link urncore.ComponentRegular#isSlot <em>Slot</em>}</li>
 *   <li>{@link urncore.ComponentRegular#getIncludedComponent <em>Included Component</em>}</li>
 *   <li>{@link urncore.ComponentRegular#getHost <em>Host</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getComponentRegular()
 * @model abstract="true"
 * @generated
 */
public interface ComponentRegular extends ComponentElement {
    /**
     * Returns the value of the '<em><b>Kind</b></em>' attribute.
     * The literals are from the enumeration {@link urncore.ComponentKind}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Kind</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Kind</em>' attribute.
     * @see urncore.ComponentKind
     * @see #setKind(ComponentKind)
     * @see urncore.UrncorePackage#getComponentRegular_Kind()
     * @model 
     * @generated
     */
    ComponentKind getKind();

    /**
     * Sets the value of the '{@link urncore.ComponentRegular#getKind <em>Kind</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Kind</em>' attribute.
     * @see urncore.ComponentKind
     * @see #getKind()
     * @generated
     */
    void setKind(ComponentKind value);

    /**
     * Returns the value of the '<em><b>Protected</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Protected</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Protected</em>' attribute.
     * @see #setProtected(boolean)
     * @see urncore.UrncorePackage#getComponentRegular_Protected()
     * @model default="false"
     * @generated
     */
    boolean isProtected();

    /**
     * Sets the value of the '{@link urncore.ComponentRegular#isProtected <em>Protected</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Protected</em>' attribute.
     * @see #isProtected()
     * @generated
     */
    void setProtected(boolean value);

    /**
     * Returns the value of the '<em><b>Slot</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Slot</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Slot</em>' attribute.
     * @see #setSlot(boolean)
     * @see urncore.UrncorePackage#getComponentRegular_Slot()
     * @model default="false"
     * @generated
     */
    boolean isSlot();

    /**
     * Sets the value of the '{@link urncore.ComponentRegular#isSlot <em>Slot</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Slot</em>' attribute.
     * @see #isSlot()
     * @generated
     */
    void setSlot(boolean value);

    /**
     * Returns the value of the '<em><b>Included Component</b></em>' reference list.
     * The list contents are of type {@link urncore.ComponentElement}.
     * It is bidirectional and its opposite is '{@link urncore.ComponentElement#getIncludingComponent <em>Including Component</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Included Component</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Included Component</em>' reference list.
     * @see urncore.UrncorePackage#getComponentRegular_IncludedComponent()
     * @see urncore.ComponentElement#getIncludingComponent
     * @model type="urncore.ComponentElement" opposite="includingComponent"
     * @generated
     */
    EList getIncludedComponent();

    /**
     * Returns the value of the '<em><b>Host</b></em>' reference.
     * It is bidirectional and its opposite is '{@link ucm.performance.ProcessingResource#getComponents <em>Components</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Host</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Host</em>' reference.
     * @see #setHost(ProcessingResource)
     * @see urncore.UrncorePackage#getComponentRegular_Host()
     * @see ucm.performance.ProcessingResource#getComponents
     * @model opposite="components"
     * @generated
     */
    ProcessingResource getHost();

    /**
     * Sets the value of the '{@link urncore.ComponentRegular#getHost <em>Host</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Host</em>' reference.
     * @see #getHost()
     * @generated
     */
    void setHost(ProcessingResource value);

} // ComponentRegular
