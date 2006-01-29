/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dynamic Responsibility</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * TBD.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.DynamicResponsibility#getKind <em>Kind</em>}</li>
 *   <li>{@link urncore.DynamicResponsibility#isToPath <em>To Path</em>}</li>
 *   <li>{@link urncore.DynamicResponsibility#getArrowLength <em>Arrow Length</em>}</li>
 *   <li>{@link urncore.DynamicResponsibility#getPool <em>Pool</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getDynamicResponsibility()
 * @model
 * @generated
 */
public interface DynamicResponsibility extends Responsibility{
    /**
     * Returns the value of the '<em><b>Kind</b></em>' attribute.
     * The literals are from the enumeration {@link urncore.DynamicRespKind}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Kind</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Kind</em>' attribute.
     * @see urncore.DynamicRespKind
     * @see #setKind(DynamicRespKind)
     * @see urncore.UrncorePackage#getDynamicResponsibility_Kind()
     * @model
     * @generated
     */
    DynamicRespKind getKind();

    /**
     * Sets the value of the '{@link urncore.DynamicResponsibility#getKind <em>Kind</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Kind</em>' attribute.
     * @see urncore.DynamicRespKind
     * @see #getKind()
     * @generated
     */
    void setKind(DynamicRespKind value);

    /**
     * Returns the value of the '<em><b>To Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>To Path</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>To Path</em>' attribute.
     * @see #setToPath(boolean)
     * @see urncore.UrncorePackage#getDynamicResponsibility_ToPath()
     * @model
     * @generated
     */
    boolean isToPath();

    /**
     * Sets the value of the '{@link urncore.DynamicResponsibility#isToPath <em>To Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>To Path</em>' attribute.
     * @see #isToPath()
     * @generated
     */
    void setToPath(boolean value);

    /**
     * Returns the value of the '<em><b>Arrow Length</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Arrow Length</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Arrow Length</em>' attribute.
     * @see #setArrowLength(int)
     * @see urncore.UrncorePackage#getDynamicResponsibility_ArrowLength()
     * @model
     * @generated
     */
    int getArrowLength();

    /**
     * Sets the value of the '{@link urncore.DynamicResponsibility#getArrowLength <em>Arrow Length</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Arrow Length</em>' attribute.
     * @see #getArrowLength()
     * @generated
     */
    void setArrowLength(int value);

    /**
     * Returns the value of the '<em><b>Pool</b></em>' reference.
     * It is bidirectional and its opposite is '{@link urncore.Pool#getDynResponsibilities <em>Dyn Responsibilities</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pool</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Pool</em>' reference.
     * @see #setPool(Pool)
     * @see urncore.UrncorePackage#getDynamicResponsibility_Pool()
     * @see urncore.Pool#getDynResponsibilities
     * @model opposite="dynResponsibilities"
     * @generated
     */
    Pool getPool();

    /**
     * Sets the value of the '{@link urncore.DynamicResponsibility#getPool <em>Pool</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Pool</em>' reference.
     * @see #getPool()
     * @generated
     */
    void setPool(Pool value);

} // DynamicResponsibility
