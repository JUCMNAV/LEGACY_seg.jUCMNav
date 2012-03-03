/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import org.eclipse.emf.common.util.EList;

import ucm.performance.PassiveResource;
import ucm.performance.ProcessingResource;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Component instance, which can be of a specific ComponentType.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.Component#getKind <em>Kind</em>}</li>
 *   <li>{@link urncore.Component#isProtected <em>Protected</em>}</li>
 *   <li>{@link urncore.Component#isSlot <em>Slot</em>}</li>
 *   <li>{@link urncore.Component#isContext <em>Context</em>}</li>
 *   <li>{@link urncore.Component#getType <em>Type</em>}</li>
 *   <li>{@link urncore.Component#getUrndefinition <em>Urndefinition</em>}</li>
 *   <li>{@link urncore.Component#getIncludedComponent <em>Included Component</em>}</li>
 *   <li>{@link urncore.Component#getIncludingComponent <em>Including Component</em>}</li>
 *   <li>{@link urncore.Component#getResource <em>Resource</em>}</li>
 *   <li>{@link urncore.Component#getHost <em>Host</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getComponent()
 * @model
 * @generated
 */
public interface Component extends UCMmodelElement, IURNContainer {
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
	 * @see urncore.UrncorePackage#getComponent_Kind()
	 * @model
	 * @generated
	 */
	ComponentKind getKind();

	/**
	 * Sets the value of the '{@link urncore.Component#getKind <em>Kind</em>}' attribute.
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
	 * @see urncore.UrncorePackage#getComponent_Protected()
	 * @model default="false"
	 * @generated
	 */
	boolean isProtected();

	/**
	 * Sets the value of the '{@link urncore.Component#isProtected <em>Protected</em>}' attribute.
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
	 * @see urncore.UrncorePackage#getComponent_Slot()
	 * @model default="false"
	 * @generated
	 */
	boolean isSlot();

	/**
	 * Sets the value of the '{@link urncore.Component#isSlot <em>Slot</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Slot</em>' attribute.
	 * @see #isSlot()
	 * @generated
	 */
	void setSlot(boolean value);

				/**
	 * Returns the value of the '<em><b>Context</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context</em>' attribute.
	 * @see #setContext(boolean)
	 * @see urncore.UrncorePackage#getComponent_Context()
	 * @model default="false"
	 * @generated
	 */
	boolean isContext();

	/**
	 * Sets the value of the '{@link urncore.Component#isContext <em>Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context</em>' attribute.
	 * @see #isContext()
	 * @generated
	 */
	void setContext(boolean value);

				/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link urncore.ComponentType#getInstances <em>Instances</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(ComponentType)
	 * @see urncore.UrncorePackage#getComponent_Type()
	 * @see urncore.ComponentType#getInstances
	 * @model opposite="instances"
	 * @generated
	 */
    ComponentType getType();

    /**
	 * Sets the value of the '{@link urncore.Component#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
    void setType(ComponentType value);

				/**
	 * Returns the value of the '<em><b>Urndefinition</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link urncore.URNdefinition#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Urndefinition</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Urndefinition</em>' container reference.
	 * @see #setUrndefinition(URNdefinition)
	 * @see urncore.UrncorePackage#getComponent_Urndefinition()
	 * @see urncore.URNdefinition#getComponents
	 * @model opposite="components" required="true"
	 * @generated
	 */
	URNdefinition getUrndefinition();

				/**
	 * Sets the value of the '{@link urncore.Component#getUrndefinition <em>Urndefinition</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Urndefinition</em>' container reference.
	 * @see #getUrndefinition()
	 * @generated
	 */
	void setUrndefinition(URNdefinition value);

				/**
	 * Returns the value of the '<em><b>Included Component</b></em>' reference list.
	 * The list contents are of type {@link urncore.Component}.
	 * It is bidirectional and its opposite is '{@link urncore.Component#getIncludingComponent <em>Including Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Included Component</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Included Component</em>' reference list.
	 * @see urncore.UrncorePackage#getComponent_IncludedComponent()
	 * @see urncore.Component#getIncludingComponent
	 * @model type="urncore.Component" opposite="includingComponent"
	 * @generated
	 */
	EList getIncludedComponent();

				/**
	 * Returns the value of the '<em><b>Including Component</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link urncore.Component#getIncludedComponent <em>Included Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Including Component</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Including Component</em>' reference.
	 * @see #setIncludingComponent(Component)
	 * @see urncore.UrncorePackage#getComponent_IncludingComponent()
	 * @see urncore.Component#getIncludedComponent
	 * @model opposite="includedComponent"
	 * @generated
	 */
	Component getIncludingComponent();

				/**
	 * Sets the value of the '{@link urncore.Component#getIncludingComponent <em>Including Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Including Component</em>' reference.
	 * @see #getIncludingComponent()
	 * @generated
	 */
	void setIncludingComponent(Component value);

				/**
	 * Returns the value of the '<em><b>Resource</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ucm.performance.PassiveResource#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource</em>' reference.
	 * @see #setResource(PassiveResource)
	 * @see urncore.UrncorePackage#getComponent_Resource()
	 * @see ucm.performance.PassiveResource#getComponent
	 * @model opposite="component"
	 * @generated
	 */
	PassiveResource getResource();

				/**
	 * Sets the value of the '{@link urncore.Component#getResource <em>Resource</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource</em>' reference.
	 * @see #getResource()
	 * @generated
	 */
	void setResource(PassiveResource value);

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
	 * @see urncore.UrncorePackage#getComponent_Host()
	 * @see ucm.performance.ProcessingResource#getComponents
	 * @model opposite="components"
	 * @generated
	 */
	ProcessingResource getHost();

				/**
	 * Sets the value of the '{@link urncore.Component#getHost <em>Host</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Host</em>' reference.
	 * @see #getHost()
	 * @generated
	 */
	void setHost(ProcessingResource value);

} // Component
