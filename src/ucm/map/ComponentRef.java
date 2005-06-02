/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import org.eclipse.emf.common.util.EList;

import urncore.ComponentElement;
import urncore.ComponentLabel;
import urncore.UCMmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Component Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A reference to a component or pool in a particular map.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.ComponentRef#getRole <em>Role</em>}</li>
 *   <li>{@link ucm.map.ComponentRef#getReplicationFactor <em>Replication Factor</em>}</li>
 *   <li>{@link ucm.map.ComponentRef#isAnchored <em>Anchored</em>}</li>
 *   <li>{@link ucm.map.ComponentRef#isFixed <em>Fixed</em>}</li>
 *   <li>{@link ucm.map.ComponentRef#getX <em>X</em>}</li>
 *   <li>{@link ucm.map.ComponentRef#getY <em>Y</em>}</li>
 *   <li>{@link ucm.map.ComponentRef#getWidth <em>Width</em>}</li>
 *   <li>{@link ucm.map.ComponentRef#getHeight <em>Height</em>}</li>
 *   <li>{@link ucm.map.ComponentRef#getMap <em>Map</em>}</li>
 *   <li>{@link ucm.map.ComponentRef#getCompDef <em>Comp Def</em>}</li>
 *   <li>{@link ucm.map.ComponentRef#getChildren <em>Children</em>}</li>
 *   <li>{@link ucm.map.ComponentRef#getParent <em>Parent</em>}</li>
 *   <li>{@link ucm.map.ComponentRef#getPathNodes <em>Path Nodes</em>}</li>
 *   <li>{@link ucm.map.ComponentRef#getLabel <em>Label</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getComponentRef()
 * @model 
 * @generated
 */
public interface ComponentRef extends UCMmodelElement{
    /**
     * Returns the value of the '<em><b>Role</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Role</em>' attribute.
     * @see #setRole(String)
     * @see ucm.map.MapPackage#getComponentRef_Role()
     * @model 
     * @generated
     */
	String getRole();

    /**
     * Sets the value of the '{@link ucm.map.ComponentRef#getRole <em>Role</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Role</em>' attribute.
     * @see #getRole()
     * @generated
     */
	void setRole(String value);

    /**
     * Returns the value of the '<em><b>Replication Factor</b></em>' attribute.
     * The default value is <code>"1"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Replication Factor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Replication Factor</em>' attribute.
     * @see #setReplicationFactor(int)
     * @see ucm.map.MapPackage#getComponentRef_ReplicationFactor()
     * @model default="1"
     * @generated
     */
	int getReplicationFactor();

    /**
     * Sets the value of the '{@link ucm.map.ComponentRef#getReplicationFactor <em>Replication Factor</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Replication Factor</em>' attribute.
     * @see #getReplicationFactor()
     * @generated
     */
	void setReplicationFactor(int value);

    /**
     * Returns the value of the '<em><b>Anchored</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Anchored</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Anchored</em>' attribute.
     * @see #setAnchored(boolean)
     * @see ucm.map.MapPackage#getComponentRef_Anchored()
     * @model default="false"
     * @generated
     */
	boolean isAnchored();

    /**
     * Sets the value of the '{@link ucm.map.ComponentRef#isAnchored <em>Anchored</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Anchored</em>' attribute.
     * @see #isAnchored()
     * @generated
     */
	void setAnchored(boolean value);

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
     * @see ucm.map.MapPackage#getComponentRef_Fixed()
     * @model default="false"
     * @generated
     */
	boolean isFixed();

    /**
     * Sets the value of the '{@link ucm.map.ComponentRef#isFixed <em>Fixed</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Fixed</em>' attribute.
     * @see #isFixed()
     * @generated
     */
	void setFixed(boolean value);

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
     * @see ucm.map.MapPackage#getComponentRef_X()
     * @model 
     * @generated
     */
	int getX();

    /**
     * Sets the value of the '{@link ucm.map.ComponentRef#getX <em>X</em>}' attribute.
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
     * @see ucm.map.MapPackage#getComponentRef_Y()
     * @model 
     * @generated
     */
	int getY();

    /**
     * Sets the value of the '{@link ucm.map.ComponentRef#getY <em>Y</em>}' attribute.
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
     * @see ucm.map.MapPackage#getComponentRef_Width()
     * @model 
     * @generated
     */
	int getWidth();

    /**
     * Sets the value of the '{@link ucm.map.ComponentRef#getWidth <em>Width</em>}' attribute.
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
     * @see ucm.map.MapPackage#getComponentRef_Height()
     * @model 
     * @generated
     */
	int getHeight();

    /**
     * Sets the value of the '{@link ucm.map.ComponentRef#getHeight <em>Height</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Height</em>' attribute.
     * @see #getHeight()
     * @generated
     */
	void setHeight(int value);

    /**
     * Returns the value of the '<em><b>Map</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link ucm.map.Map#getCompRefs <em>Comp Refs</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Map</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Map</em>' container reference.
     * @see #setMap(Map)
     * @see ucm.map.MapPackage#getComponentRef_Map()
     * @see ucm.map.Map#getCompRefs
     * @model opposite="compRefs" required="true"
     * @generated
     */
	Map getMap();

    /**
     * Sets the value of the '{@link ucm.map.ComponentRef#getMap <em>Map</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Map</em>' container reference.
     * @see #getMap()
     * @generated
     */
	void setMap(Map value);

    /**
     * Returns the value of the '<em><b>Comp Def</b></em>' reference.
     * It is bidirectional and its opposite is '{@link urncore.ComponentElement#getCompRefs <em>Comp Refs</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comp Def</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Comp Def</em>' reference.
     * @see #setCompDef(ComponentElement)
     * @see ucm.map.MapPackage#getComponentRef_CompDef()
     * @see urncore.ComponentElement#getCompRefs
     * @model opposite="compRefs" required="true"
     * @generated
     */
	ComponentElement getCompDef();

    /**
     * Sets the value of the '{@link ucm.map.ComponentRef#getCompDef <em>Comp Def</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Comp Def</em>' reference.
     * @see #getCompDef()
     * @generated
     */
	void setCompDef(ComponentElement value);

    /**
     * Returns the value of the '<em><b>Children</b></em>' reference list.
     * The list contents are of type {@link ucm.map.ComponentRef}.
     * It is bidirectional and its opposite is '{@link ucm.map.ComponentRef#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Children</em>' reference list.
     * @see ucm.map.MapPackage#getComponentRef_Children()
     * @see ucm.map.ComponentRef#getParent
     * @model type="ucm.map.ComponentRef" opposite="parent"
     * @generated
     */
	EList getChildren();

    /**
     * Returns the value of the '<em><b>Parent</b></em>' reference.
     * It is bidirectional and its opposite is '{@link ucm.map.ComponentRef#getChildren <em>Children</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Parent</em>' reference.
     * @see #setParent(ComponentRef)
     * @see ucm.map.MapPackage#getComponentRef_Parent()
     * @see ucm.map.ComponentRef#getChildren
     * @model opposite="children"
     * @generated
     */
	ComponentRef getParent();

    /**
     * Sets the value of the '{@link ucm.map.ComponentRef#getParent <em>Parent</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent</em>' reference.
     * @see #getParent()
     * @generated
     */
	void setParent(ComponentRef value);

    /**
     * Returns the value of the '<em><b>Path Nodes</b></em>' reference list.
     * The list contents are of type {@link ucm.map.PathNode}.
     * It is bidirectional and its opposite is '{@link ucm.map.PathNode#getCompRef <em>Comp Ref</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path Nodes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Path Nodes</em>' reference list.
     * @see ucm.map.MapPackage#getComponentRef_PathNodes()
     * @see ucm.map.PathNode#getCompRef
     * @model type="ucm.map.PathNode" opposite="compRef"
     * @generated
     */
	EList getPathNodes();

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
     * @see ucm.map.MapPackage#getComponentRef_Label()
     * @see urncore.ComponentLabel#getCompRef
     * @model opposite="compRef" containment="true" required="true"
     * @generated
     */
	ComponentLabel getLabel();

    /**
     * Sets the value of the '{@link ucm.map.ComponentRef#getLabel <em>Label</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Label</em>' containment reference.
     * @see #getLabel()
     * @generated
     */
	void setLabel(ComponentLabel value);

} // ComponentRef
