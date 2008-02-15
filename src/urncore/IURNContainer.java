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
 * A representation of the model object '<em><b>Specification Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.IURNContainer#getLineColor <em>Line Color</em>}</li>
 *   <li>{@link urncore.IURNContainer#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link urncore.IURNContainer#isFilled <em>Filled</em>}</li>
 *   <li>{@link urncore.IURNContainer#getContRefs <em>Cont Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getIURNContainer()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface IURNContainer extends EObject {
    /**
	 * Returns the value of the '<em><b>Line Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Line Color</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Line Color</em>' attribute.
	 * @see #setLineColor(String)
	 * @see urncore.UrncorePackage#getIURNContainer_LineColor()
	 * @model
	 * @generated
	 */
    String getLineColor();

    /**
	 * Sets the value of the '{@link urncore.IURNContainer#getLineColor <em>Line Color</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line Color</em>' attribute.
	 * @see #getLineColor()
	 * @generated
	 */
    void setLineColor(String value);

    /**
	 * Returns the value of the '<em><b>Fill Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Fill Color</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Fill Color</em>' attribute.
	 * @see #setFillColor(String)
	 * @see urncore.UrncorePackage#getIURNContainer_FillColor()
	 * @model
	 * @generated
	 */
    String getFillColor();

    /**
	 * Sets the value of the '{@link urncore.IURNContainer#getFillColor <em>Fill Color</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fill Color</em>' attribute.
	 * @see #getFillColor()
	 * @generated
	 */
    void setFillColor(String value);

    /**
	 * Returns the value of the '<em><b>Filled</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filled</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Filled</em>' attribute.
	 * @see #setFilled(boolean)
	 * @see urncore.UrncorePackage#getIURNContainer_Filled()
	 * @model default="false"
	 * @generated
	 */
    boolean isFilled();

    /**
	 * Sets the value of the '{@link urncore.IURNContainer#isFilled <em>Filled</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filled</em>' attribute.
	 * @see #isFilled()
	 * @generated
	 */
    void setFilled(boolean value);

    /**
	 * Returns the value of the '<em><b>Cont Refs</b></em>' reference list.
	 * The list contents are of type {@link urncore.IURNContainerRef}.
	 * It is bidirectional and its opposite is '{@link urncore.IURNContainerRef#getContDef <em>Cont Def</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Comp Refs</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Cont Refs</em>' reference list.
	 * @see urncore.UrncorePackage#getIURNContainer_ContRefs()
	 * @see urncore.IURNContainerRef#getContDef
	 * @model type="urncore.IURNContainerRef" opposite="contDef"
	 * @generated
	 */
    EList getContRefs();

} // SpecificationComponent
