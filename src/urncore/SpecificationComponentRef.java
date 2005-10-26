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
 *   <li>{@link urncore.SpecificationComponentRef#getX <em>X</em>}</li>
 *   <li>{@link urncore.SpecificationComponentRef#getY <em>Y</em>}</li>
 *   <li>{@link urncore.SpecificationComponentRef#getWidth <em>Width</em>}</li>
 *   <li>{@link urncore.SpecificationComponentRef#getHeight <em>Height</em>}</li>
 *   <li>{@link urncore.SpecificationComponentRef#isFixed <em>Fixed</em>}</li>
 *   <li>{@link urncore.SpecificationComponentRef#getSpecDiagram <em>Spec Diagram</em>}</li>
 *   <li>{@link urncore.SpecificationComponentRef#getCompDef <em>Comp Def</em>}</li>
 *   <li>{@link urncore.SpecificationComponentRef#getNodes <em>Nodes</em>}</li>
 *   <li>{@link urncore.SpecificationComponentRef#getLabel <em>Label</em>}</li>
 *   <li>{@link urncore.SpecificationComponentRef#getParent <em>Parent</em>}</li>
 *   <li>{@link urncore.SpecificationComponentRef#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getSpecificationComponentRef()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface SpecificationComponentRef extends EObject {
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
     * @see urncore.UrncorePackage#getSpecificationComponentRef_X()
     * @model 
     * @generated
     */
    int getX();

    /**
     * Sets the value of the '{@link urncore.SpecificationComponentRef#getX <em>X</em>}' attribute.
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
     * @see urncore.UrncorePackage#getSpecificationComponentRef_Y()
     * @model 
     * @generated
     */
    int getY();

    /**
     * Sets the value of the '{@link urncore.SpecificationComponentRef#getY <em>Y</em>}' attribute.
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
     * @see urncore.UrncorePackage#getSpecificationComponentRef_Width()
     * @model 
     * @generated
     */
    int getWidth();

    /**
     * Sets the value of the '{@link urncore.SpecificationComponentRef#getWidth <em>Width</em>}' attribute.
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
     * @see urncore.UrncorePackage#getSpecificationComponentRef_Height()
     * @model 
     * @generated
     */
    int getHeight();

    /**
     * Sets the value of the '{@link urncore.SpecificationComponentRef#getHeight <em>Height</em>}' attribute.
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
     * @see urncore.UrncorePackage#getSpecificationComponentRef_Fixed()
     * @model default="false"
     * @generated
     */
    boolean isFixed();

    /**
     * Sets the value of the '{@link urncore.SpecificationComponentRef#isFixed <em>Fixed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Fixed</em>' attribute.
     * @see #isFixed()
     * @generated
     */
    void setFixed(boolean value);

    /**
     * Returns the value of the '<em><b>Spec Diagram</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link urncore.SpecificationDiagram#getCompRefs <em>Comp Refs</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Spec Diagram</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Spec Diagram</em>' container reference.
     * @see #setSpecDiagram(SpecificationDiagram)
     * @see urncore.UrncorePackage#getSpecificationComponentRef_SpecDiagram()
     * @see urncore.SpecificationDiagram#getCompRefs
     * @model opposite="compRefs" required="true"
     * @generated
     */
    SpecificationDiagram getSpecDiagram();

    /**
     * Sets the value of the '{@link urncore.SpecificationComponentRef#getSpecDiagram <em>Spec Diagram</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Spec Diagram</em>' container reference.
     * @see #getSpecDiagram()
     * @generated
     */
    void setSpecDiagram(SpecificationDiagram value);

    /**
     * Returns the value of the '<em><b>Comp Def</b></em>' reference.
     * It is bidirectional and its opposite is '{@link urncore.SpecificationComponent#getCompRefs <em>Comp Refs</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Comp Def</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Comp Def</em>' reference.
     * @see #setCompDef(SpecificationComponent)
     * @see urncore.UrncorePackage#getSpecificationComponentRef_CompDef()
     * @see urncore.SpecificationComponent#getCompRefs
     * @model opposite="compRefs" required="true"
     * @generated
     */
    SpecificationComponent getCompDef();

    /**
     * Sets the value of the '{@link urncore.SpecificationComponentRef#getCompDef <em>Comp Def</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Comp Def</em>' reference.
     * @see #getCompDef()
     * @generated
     */
    void setCompDef(SpecificationComponent value);

    /**
     * Returns the value of the '<em><b>Nodes</b></em>' reference list.
     * The list contents are of type {@link urncore.SpecificationNode}.
     * It is bidirectional and its opposite is '{@link urncore.SpecificationNode#getCompRef <em>Comp Ref</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Nodes</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Nodes</em>' reference list.
     * @see urncore.UrncorePackage#getSpecificationComponentRef_Nodes()
     * @see urncore.SpecificationNode#getCompRef
     * @model type="urncore.SpecificationNode" opposite="compRef"
     * @generated
     */
    EList getNodes();

    /**
     * Returns the value of the '<em><b>Label</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link urncore.ComponentLabel#getCompRef <em>Comp Ref</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Label</em>' containment reference.
     * @see #setLabel(ComponentLabel)
     * @see urncore.UrncorePackage#getSpecificationComponentRef_Label()
     * @see urncore.ComponentLabel#getCompRef
     * @model opposite="compRef" containment="true" required="true"
     * @generated
     */
    ComponentLabel getLabel();

    /**
     * Sets the value of the '{@link urncore.SpecificationComponentRef#getLabel <em>Label</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label</em>' containment reference.
     * @see #getLabel()
     * @generated
     */
    void setLabel(ComponentLabel value);

    /**
     * Returns the value of the '<em><b>Parent</b></em>' reference.
     * It is bidirectional and its opposite is '{@link urncore.SpecificationComponentRef#getChildren <em>Children</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parent</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parent</em>' reference.
     * @see #setParent(SpecificationComponentRef)
     * @see urncore.UrncorePackage#getSpecificationComponentRef_Parent()
     * @see urncore.SpecificationComponentRef#getChildren
     * @model opposite="children"
     * @generated
     */
    SpecificationComponentRef getParent();

    /**
     * Sets the value of the '{@link urncore.SpecificationComponentRef#getParent <em>Parent</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent</em>' reference.
     * @see #getParent()
     * @generated
     */
    void setParent(SpecificationComponentRef value);

    /**
     * Returns the value of the '<em><b>Children</b></em>' reference list.
     * The list contents are of type {@link urncore.SpecificationComponentRef}.
     * It is bidirectional and its opposite is '{@link urncore.SpecificationComponentRef#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Children</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Children</em>' reference list.
     * @see urncore.UrncorePackage#getSpecificationComponentRef_Children()
     * @see urncore.SpecificationComponentRef#getParent
     * @model type="urncore.SpecificationComponentRef" opposite="parent"
     * @generated
     */
    EList getChildren();

} // SpecificationComponentRef
