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
 * A representation of the model object '<em><b>Specification Component Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.IURNContainerRef#getX <em>X</em>}</li>
 *   <li>{@link urncore.IURNContainerRef#getY <em>Y</em>}</li>
 *   <li>{@link urncore.IURNContainerRef#getWidth <em>Width</em>}</li>
 *   <li>{@link urncore.IURNContainerRef#getHeight <em>Height</em>}</li>
 *   <li>{@link urncore.IURNContainerRef#isFixed <em>Fixed</em>}</li>
 *   <li>{@link urncore.IURNContainerRef#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link urncore.IURNContainerRef#getContDef <em>Cont Def</em>}</li>
 *   <li>{@link urncore.IURNContainerRef#getNodes <em>Nodes</em>}</li>
 *   <li>{@link urncore.IURNContainerRef#getLabel <em>Label</em>}</li>
 *   <li>{@link urncore.IURNContainerRef#getParent <em>Parent</em>}</li>
 *   <li>{@link urncore.IURNContainerRef#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getIURNContainerRef()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IURNContainerRef extends EObject {
    /**
	 * Returns the value of the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>X</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>X</em>' attribute.
	 * @see #setX(int)
	 * @see urncore.UrncorePackage#getIURNContainerRef_X()
	 * @model
	 * @generated
	 */
    int getX();

    /**
	 * Sets the value of the '{@link urncore.IURNContainerRef#getX <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>X</em>' attribute.
	 * @see #getX()
	 * @generated
	 */
    void setX(int value);

    /**
	 * Returns the value of the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Y</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Y</em>' attribute.
	 * @see #setY(int)
	 * @see urncore.UrncorePackage#getIURNContainerRef_Y()
	 * @model
	 * @generated
	 */
    int getY();

    /**
	 * Sets the value of the '{@link urncore.IURNContainerRef#getY <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Y</em>' attribute.
	 * @see #getY()
	 * @generated
	 */
    void setY(int value);

    /**
	 * Returns the value of the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Width</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Width</em>' attribute.
	 * @see #setWidth(int)
	 * @see urncore.UrncorePackage#getIURNContainerRef_Width()
	 * @model
	 * @generated
	 */
    int getWidth();

    /**
	 * Sets the value of the '{@link urncore.IURNContainerRef#getWidth <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Width</em>' attribute.
	 * @see #getWidth()
	 * @generated
	 */
    void setWidth(int value);

    /**
	 * Returns the value of the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Height</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Height</em>' attribute.
	 * @see #setHeight(int)
	 * @see urncore.UrncorePackage#getIURNContainerRef_Height()
	 * @model
	 * @generated
	 */
    int getHeight();

    /**
	 * Sets the value of the '{@link urncore.IURNContainerRef#getHeight <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Height</em>' attribute.
	 * @see #getHeight()
	 * @generated
	 */
    void setHeight(int value);

    /**
	 * Returns the value of the '<em><b>Fixed</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Fixed</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Fixed</em>' attribute.
	 * @see #setFixed(boolean)
	 * @see urncore.UrncorePackage#getIURNContainerRef_Fixed()
	 * @model default="false"
	 * @generated
	 */
    boolean isFixed();

    /**
	 * Sets the value of the '{@link urncore.IURNContainerRef#isFixed <em>Fixed</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fixed</em>' attribute.
	 * @see #isFixed()
	 * @generated
	 */
    void setFixed(boolean value);

    /**
	 * Returns the value of the '<em><b>Diagram</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link urncore.IURNDiagram#getContRefs <em>Cont Refs</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Spec Diagram</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Diagram</em>' container reference.
	 * @see #setDiagram(IURNDiagram)
	 * @see urncore.UrncorePackage#getIURNContainerRef_Diagram()
	 * @see urncore.IURNDiagram#getContRefs
	 * @model opposite="contRefs" required="true"
	 * @generated
	 */
    IURNDiagram getDiagram();

    /**
	 * Sets the value of the '{@link urncore.IURNContainerRef#getDiagram <em>Diagram</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Diagram</em>' container reference.
	 * @see #getDiagram()
	 * @generated
	 */
    void setDiagram(IURNDiagram value);

    /**
	 * Returns the value of the '<em><b>Cont Def</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link urncore.IURNContainer#getContRefs <em>Cont Refs</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Cont Def</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Cont Def</em>' reference.
	 * @see #setContDef(IURNContainer)
	 * @see urncore.UrncorePackage#getIURNContainerRef_ContDef()
	 * @see urncore.IURNContainer#getContRefs
	 * @model opposite="contRefs" required="true"
	 * @generated
	 */
    IURNContainer getContDef();

    /**
	 * Sets the value of the '{@link urncore.IURNContainerRef#getContDef <em>Cont Def</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cont Def</em>' reference.
	 * @see #getContDef()
	 * @generated
	 */
    void setContDef(IURNContainer value);

    /**
	 * Returns the value of the '<em><b>Nodes</b></em>' reference list.
	 * The list contents are of type {@link urncore.IURNNode}.
	 * It is bidirectional and its opposite is '{@link urncore.IURNNode#getContRef <em>Cont Ref</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Nodes</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodes</em>' reference list.
	 * @see urncore.UrncorePackage#getIURNContainerRef_Nodes()
	 * @see urncore.IURNNode#getContRef
	 * @model type="urncore.IURNNode" opposite="contRef"
	 * @generated
	 */
    EList getNodes();

    /**
	 * Returns the value of the '<em><b>Label</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link urncore.ComponentLabel#getContRef <em>Cont Ref</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' containment reference.
	 * @see #setLabel(ComponentLabel)
	 * @see urncore.UrncorePackage#getIURNContainerRef_Label()
	 * @see urncore.ComponentLabel#getContRef
	 * @model opposite="contRef" containment="true" required="true"
	 * @generated
	 */
    ComponentLabel getLabel();

    /**
	 * Sets the value of the '{@link urncore.IURNContainerRef#getLabel <em>Label</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' containment reference.
	 * @see #getLabel()
	 * @generated
	 */
    void setLabel(ComponentLabel value);

    /**
	 * Returns the value of the '<em><b>Parent</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link urncore.IURNContainerRef#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parent</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent</em>' reference.
	 * @see #setParent(IURNContainerRef)
	 * @see urncore.UrncorePackage#getIURNContainerRef_Parent()
	 * @see urncore.IURNContainerRef#getChildren
	 * @model opposite="children"
	 * @generated
	 */
    IURNContainerRef getParent();

    /**
	 * Sets the value of the '{@link urncore.IURNContainerRef#getParent <em>Parent</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' reference.
	 * @see #getParent()
	 * @generated
	 */
    void setParent(IURNContainerRef value);

    /**
	 * Returns the value of the '<em><b>Children</b></em>' reference list.
	 * The list contents are of type {@link urncore.IURNContainerRef}.
	 * It is bidirectional and its opposite is '{@link urncore.IURNContainerRef#getParent <em>Parent</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Children</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' reference list.
	 * @see urncore.UrncorePackage#getIURNContainerRef_Children()
	 * @see urncore.IURNContainerRef#getParent
	 * @model type="urncore.IURNContainerRef" opposite="parent"
	 * @generated
	 */
    EList getChildren();

} // SpecificationComponentRef
