/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Label</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.Label#getDeltaX <em>Delta X</em>}</li>
 *   <li>{@link urncore.Label#getDeltaY <em>Delta Y</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getLabel()
 * @model abstract="true"
 * @generated
 */
public interface Label extends EObject {
    /**
	 * Returns the value of the '<em><b>Delta X</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Delta X</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Delta X</em>' attribute.
	 * @see #setDeltaX(int)
	 * @see urncore.UrncorePackage#getLabel_DeltaX()
	 * @model
	 * @generated
	 */
    int getDeltaX();

    /**
	 * Sets the value of the '{@link urncore.Label#getDeltaX <em>Delta X</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delta X</em>' attribute.
	 * @see #getDeltaX()
	 * @generated
	 */
    void setDeltaX(int value);

    /**
	 * Returns the value of the '<em><b>Delta Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Delta Y</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Delta Y</em>' attribute.
	 * @see #setDeltaY(int)
	 * @see urncore.UrncorePackage#getLabel_DeltaY()
	 * @model
	 * @generated
	 */
    int getDeltaY();

    /**
	 * Sets the value of the '{@link urncore.Label#getDeltaY <em>Delta Y</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Delta Y</em>' attribute.
	 * @see #getDeltaY()
	 * @generated
	 */
    void setDeltaY(int value);

} // Label
