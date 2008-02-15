/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link Ref Bendpoint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.LinkRefBendpoint#getX <em>X</em>}</li>
 *   <li>{@link grl.LinkRefBendpoint#getY <em>Y</em>}</li>
 *   <li>{@link grl.LinkRefBendpoint#getLinkref <em>Linkref</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getLinkRefBendpoint()
 * @model
 * @generated
 */
public interface LinkRefBendpoint extends EObject {
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
	 * @see grl.GrlPackage#getLinkRefBendpoint_X()
	 * @model
	 * @generated
	 */
    int getX();

    /**
	 * Sets the value of the '{@link grl.LinkRefBendpoint#getX <em>X</em>}' attribute.
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
	 * @see grl.GrlPackage#getLinkRefBendpoint_Y()
	 * @model
	 * @generated
	 */
    int getY();

    /**
	 * Sets the value of the '{@link grl.LinkRefBendpoint#getY <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Y</em>' attribute.
	 * @see #getY()
	 * @generated
	 */
    void setY(int value);

    /**
	 * Returns the value of the '<em><b>Linkref</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link grl.LinkRef#getBendpoints <em>Bendpoints</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Linkref</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Linkref</em>' container reference.
	 * @see #setLinkref(LinkRef)
	 * @see grl.GrlPackage#getLinkRefBendpoint_Linkref()
	 * @see grl.LinkRef#getBendpoints
	 * @model opposite="bendpoints" required="true"
	 * @generated
	 */
    LinkRef getLinkref();

    /**
	 * Sets the value of the '{@link grl.LinkRefBendpoint#getLinkref <em>Linkref</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Linkref</em>' container reference.
	 * @see #getLinkref()
	 * @generated
	 */
    void setLinkref(LinkRef value);

} // LinkRefBendpoint
