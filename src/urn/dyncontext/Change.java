/**
 */
package urn.dyncontext;

import java.util.Date;
import org.eclipse.emf.ecore.EObject;

import urncore.URNmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link urn.dyncontext.Change#getStart <em>Start</em>}</li>
 *   <li>{@link urn.dyncontext.Change#getEnd <em>End</em>}</li>
 *   <li>{@link urn.dyncontext.Change#getContext <em>Context</em>}</li>
 *   <li>{@link urn.dyncontext.Change#getElement <em>Element</em>}</li>
 * </ul>
 *
 * @see urn.dyncontext.DyncontextPackage#getChange()
 * @model abstract="true"
 * @generated
 */
public interface Change extends EObject {
	/**
	 * Returns the value of the '<em><b>Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start</em>' attribute.
	 * @see #setStart(Date)
	 * @see urn.dyncontext.DyncontextPackage#getChange_Start()
	 * @model
	 * @generated
	 */
	Date getStart();

	/**
	 * Sets the value of the '{@link urn.dyncontext.Change#getStart <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start</em>' attribute.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(Date value);

	/**
	 * Returns the value of the '<em><b>End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End</em>' attribute.
	 * @see #setEnd(Date)
	 * @see urn.dyncontext.DyncontextPackage#getChange_End()
	 * @model
	 * @generated
	 */
	Date getEnd();

	/**
	 * Sets the value of the '{@link urn.dyncontext.Change#getEnd <em>End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End</em>' attribute.
	 * @see #getEnd()
	 * @generated
	 */
	void setEnd(Date value);

	/**
	 * Returns the value of the '<em><b>Context</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link urn.dyncontext.DynamicContext#getChanges <em>Changes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context</em>' container reference.
	 * @see #setContext(DynamicContext)
	 * @see urn.dyncontext.DyncontextPackage#getChange_Context()
	 * @see urn.dyncontext.DynamicContext#getChanges
	 * @model opposite="changes" required="true"
	 * @generated
	 */
	DynamicContext getContext();

	/**
	 * Sets the value of the '{@link urn.dyncontext.Change#getContext <em>Context</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context</em>' container reference.
	 * @see #getContext()
	 * @generated
	 */
	void setContext(DynamicContext value);

	/**
	 * Returns the value of the '<em><b>Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element</em>' reference.
	 * @see #setElement(URNmodelElement)
	 * @see urn.dyncontext.DyncontextPackage#getChange_Element()
	 * @model required="true"
	 * @generated
	 */
	URNmodelElement getElement();

	/**
	 * Sets the value of the '{@link urn.dyncontext.Change#getElement <em>Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element</em>' reference.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(URNmodelElement value);

} // Change
