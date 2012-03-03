/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Responsibility</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.Responsibility#isEmpty <em>Empty</em>}</li>
 *   <li>{@link urncore.Responsibility#getExpression <em>Expression</em>}</li>
 *   <li>{@link urncore.Responsibility#isContext <em>Context</em>}</li>
 *   <li>{@link urncore.Responsibility#getUrndefinition <em>Urndefinition</em>}</li>
 *   <li>{@link urncore.Responsibility#getDemands <em>Demands</em>}</li>
 *   <li>{@link urncore.Responsibility#getRespRefs <em>Resp Refs</em>}</li>
 *   <li>{@link urncore.Responsibility#getParentBindings <em>Parent Bindings</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getResponsibility()
 * @model
 * @generated
 */
public interface Responsibility extends UCMmodelElement {
    /**
	 * Returns the value of the '<em><b>Empty</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Empty</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Empty</em>' attribute.
	 * @see #setEmpty(boolean)
	 * @see urncore.UrncorePackage#getResponsibility_Empty()
	 * @model default="false"
	 * @generated
	 */
    boolean isEmpty();

    /**
	 * Sets the value of the '{@link urncore.Responsibility#isEmpty <em>Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Empty</em>' attribute.
	 * @see #isEmpty()
	 * @generated
	 */
    void setEmpty(boolean value);

    /**
	 * Returns the value of the '<em><b>Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' attribute.
	 * @see #setExpression(String)
	 * @see urncore.UrncorePackage#getResponsibility_Expression()
	 * @model
	 * @generated
	 */
	String getExpression();

    /**
	 * Sets the value of the '{@link urncore.Responsibility#getExpression <em>Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' attribute.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(String value);

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
	 * @see urncore.UrncorePackage#getResponsibility_Context()
	 * @model default="false"
	 * @generated
	 */
    boolean isContext();

    /**
	 * Sets the value of the '{@link urncore.Responsibility#isContext <em>Context</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context</em>' attribute.
	 * @see #isContext()
	 * @generated
	 */
    void setContext(boolean value);

    /**
	 * Returns the value of the '<em><b>Urndefinition</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link urncore.URNdefinition#getResponsibilities <em>Responsibilities</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Urndefinition</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Urndefinition</em>' container reference.
	 * @see #setUrndefinition(URNdefinition)
	 * @see urncore.UrncorePackage#getResponsibility_Urndefinition()
	 * @see urncore.URNdefinition#getResponsibilities
	 * @model opposite="responsibilities" required="true"
	 * @generated
	 */
    URNdefinition getUrndefinition();

    /**
	 * Sets the value of the '{@link urncore.Responsibility#getUrndefinition <em>Urndefinition</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Urndefinition</em>' container reference.
	 * @see #getUrndefinition()
	 * @generated
	 */
    void setUrndefinition(URNdefinition value);

    /**
	 * Returns the value of the '<em><b>Demands</b></em>' containment reference list.
	 * The list contents are of type {@link ucm.performance.Demand}.
	 * It is bidirectional and its opposite is '{@link ucm.performance.Demand#getResponsibility <em>Responsibility</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Demands</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Demands</em>' containment reference list.
	 * @see urncore.UrncorePackage#getResponsibility_Demands()
	 * @see ucm.performance.Demand#getResponsibility
	 * @model type="ucm.performance.Demand" opposite="responsibility" containment="true"
	 * @generated
	 */
    EList getDemands();

    /**
	 * Returns the value of the '<em><b>Resp Refs</b></em>' reference list.
	 * The list contents are of type {@link ucm.map.RespRef}.
	 * It is bidirectional and its opposite is '{@link ucm.map.RespRef#getRespDef <em>Resp Def</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resp Refs</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Resp Refs</em>' reference list.
	 * @see urncore.UrncorePackage#getResponsibility_RespRefs()
	 * @see ucm.map.RespRef#getRespDef
	 * @model type="ucm.map.RespRef" opposite="respDef" required="true"
	 * @generated
	 */
    EList getRespRefs();

    /**
	 * Returns the value of the '<em><b>Parent Bindings</b></em>' reference list.
	 * The list contents are of type {@link ucm.map.ResponsibilityBinding}.
	 * It is bidirectional and its opposite is '{@link ucm.map.ResponsibilityBinding#getParentResp <em>Parent Resp</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parent Bindings</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Bindings</em>' reference list.
	 * @see urncore.UrncorePackage#getResponsibility_ParentBindings()
	 * @see ucm.map.ResponsibilityBinding#getParentResp
	 * @model type="ucm.map.ResponsibilityBinding" opposite="parentResp"
	 * @generated
	 */
    EList getParentBindings();

} // Responsibility
