/**
 */
package asd;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>AS Dlayout</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link asd.ASDlayout#getX <em>X</em>}</li>
 *   <li>{@link asd.ASDlayout#getY <em>Y</em>}</li>
 *   <li>{@link asd.ASDlayout#getWidth <em>Width</em>}</li>
 *   <li>{@link asd.ASDlayout#getHeight <em>Height</em>}</li>
 *   <li>{@link asd.ASDlayout#isCollapsed <em>Collapsed</em>}</li>
 *   <li>{@link asd.ASDlayout#getAsNetwork <em>As Network</em>}</li>
 *   <li>{@link asd.ASDlayout#getAsDiagram <em>As Diagram</em>}</li>
 * </ul>
 * </p>
 *
 * @see asd.AsdPackage#getASDlayout()
 * @model
 * @generated
 */
public interface ASDlayout extends EObject {
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
	 * @see asd.AsdPackage#getASDlayout_X()
	 * @model
	 * @generated
	 */
	int getX();

	/**
	 * Sets the value of the '{@link asd.ASDlayout#getX <em>X</em>}' attribute.
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
	 * @see asd.AsdPackage#getASDlayout_Y()
	 * @model
	 * @generated
	 */
	int getY();

	/**
	 * Sets the value of the '{@link asd.ASDlayout#getY <em>Y</em>}' attribute.
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
	 * @see asd.AsdPackage#getASDlayout_Width()
	 * @model
	 * @generated
	 */
	int getWidth();

	/**
	 * Sets the value of the '{@link asd.ASDlayout#getWidth <em>Width</em>}' attribute.
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
	 * @see asd.AsdPackage#getASDlayout_Height()
	 * @model
	 * @generated
	 */
	int getHeight();

	/**
	 * Sets the value of the '{@link asd.ASDlayout#getHeight <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Height</em>' attribute.
	 * @see #getHeight()
	 * @generated
	 */
	void setHeight(int value);

	/**
	 * Returns the value of the '<em><b>Collapsed</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collapsed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Collapsed</em>' attribute.
	 * @see #setCollapsed(boolean)
	 * @see asd.AsdPackage#getASDlayout_Collapsed()
	 * @model default="false"
	 * @generated
	 */
	boolean isCollapsed();

	/**
	 * Sets the value of the '{@link asd.ASDlayout#isCollapsed <em>Collapsed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Collapsed</em>' attribute.
	 * @see #isCollapsed()
	 * @generated
	 */
	void setCollapsed(boolean value);

	/**
	 * Returns the value of the '<em><b>As Network</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link asd.ASNetwork#getAsdLayouts <em>Asd Layouts</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>As Network</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>As Network</em>' container reference.
	 * @see #setAsNetwork(ASNetwork)
	 * @see asd.AsdPackage#getASDlayout_AsNetwork()
	 * @see asd.ASNetwork#getAsdLayouts
	 * @model opposite="asdLayouts" required="true"
	 * @generated
	 */
	ASNetwork getAsNetwork();

	/**
	 * Sets the value of the '{@link asd.ASDlayout#getAsNetwork <em>As Network</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>As Network</em>' container reference.
	 * @see #getAsNetwork()
	 * @generated
	 */
	void setAsNetwork(ASNetwork value);

	/**
	 * Returns the value of the '<em><b>As Diagram</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link asd.ASDiagram#getAsdLayouts <em>Asd Layouts</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>As Diagram</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>As Diagram</em>' reference.
	 * @see #setAsDiagram(ASDiagram)
	 * @see asd.AsdPackage#getASDlayout_AsDiagram()
	 * @see asd.ASDiagram#getAsdLayouts
	 * @model opposite="asdLayouts" required="true"
	 * @generated
	 */
	ASDiagram getAsDiagram();

	/**
	 * Sets the value of the '{@link asd.ASDlayout#getAsDiagram <em>As Diagram</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>As Diagram</em>' reference.
	 * @see #getAsDiagram()
	 * @generated
	 */
	void setAsDiagram(ASDiagram value);

} // ASDlayout
