/**
 */
package urn.dyncontext;

import java.util.Date;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Timepoint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link urn.dyncontext.Timepoint#getTimepoint <em>Timepoint</em>}</li>
 *   <li>{@link urn.dyncontext.Timepoint#getGroup <em>Group</em>}</li>
 * </ul>
 *
 * @see urn.dyncontext.DyncontextPackage#getTimepoint()
 * @model
 * @generated
 */
public interface Timepoint extends EObject {
	/**
	 * Returns the value of the '<em><b>Timepoint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timepoint</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timepoint</em>' attribute.
	 * @see #setTimepoint(Date)
	 * @see urn.dyncontext.DyncontextPackage#getTimepoint_Timepoint()
	 * @model
	 * @generated
	 */
	Date getTimepoint();

	/**
	 * Sets the value of the '{@link urn.dyncontext.Timepoint#getTimepoint <em>Timepoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timepoint</em>' attribute.
	 * @see #getTimepoint()
	 * @generated
	 */
	void setTimepoint(Date value);

	/**
	 * Returns the value of the '<em><b>Group</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link urn.dyncontext.TimepointGroup#getTimepoints <em>Timepoints</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' container reference.
	 * @see #setGroup(TimepointGroup)
	 * @see urn.dyncontext.DyncontextPackage#getTimepoint_Group()
	 * @see urn.dyncontext.TimepointGroup#getTimepoints
	 * @model opposite="timepoints" required="true"
	 * @generated
	 */
	TimepointGroup getGroup();

	/**
	 * Sets the value of the '{@link urn.dyncontext.Timepoint#getGroup <em>Group</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group</em>' container reference.
	 * @see #getGroup()
	 * @generated
	 */
	void setGroup(TimepointGroup value);

} // Timepoint
