/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.scenario;

import org.eclipse.emf.common.util.EList;

import ucm.UCMspec;
import urncore.UCMmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enumeration Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.scenario.EnumerationType#getValues <em>Values</em>}</li>
 *   <li>{@link ucm.scenario.EnumerationType#getUcmspec <em>Ucmspec</em>}</li>
 *   <li>{@link ucm.scenario.EnumerationType#getInstances <em>Instances</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.scenario.ScenarioPackage#getEnumerationType()
 * @model
 * @generated
 */
public interface EnumerationType extends UCMmodelElement {
    /**
	 * Returns the value of the '<em><b>Values</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Values</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Values</em>' attribute.
	 * @see #setValues(String)
	 * @see ucm.scenario.ScenarioPackage#getEnumerationType_Values()
	 * @model
	 * @generated
	 */
	String getValues();

    /**
	 * Sets the value of the '{@link ucm.scenario.EnumerationType#getValues <em>Values</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Values</em>' attribute.
	 * @see #getValues()
	 * @generated
	 */
	void setValues(String value);

    /**
	 * Returns the value of the '<em><b>Ucmspec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucm.UCMspec#getEnumerationTypes <em>Enumeration Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ucmspec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ucmspec</em>' container reference.
	 * @see #setUcmspec(UCMspec)
	 * @see ucm.scenario.ScenarioPackage#getEnumerationType_Ucmspec()
	 * @see ucm.UCMspec#getEnumerationTypes
	 * @model opposite="enumerationTypes" required="true"
	 * @generated
	 */
	UCMspec getUcmspec();

    /**
	 * Sets the value of the '{@link ucm.scenario.EnumerationType#getUcmspec <em>Ucmspec</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ucmspec</em>' container reference.
	 * @see #getUcmspec()
	 * @generated
	 */
	void setUcmspec(UCMspec value);

    /**
	 * Returns the value of the '<em><b>Instances</b></em>' reference list.
	 * The list contents are of type {@link ucm.scenario.Variable}.
	 * It is bidirectional and its opposite is '{@link ucm.scenario.Variable#getEnumerationType <em>Enumeration Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instances</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instances</em>' reference list.
	 * @see ucm.scenario.ScenarioPackage#getEnumerationType_Instances()
	 * @see ucm.scenario.Variable#getEnumerationType
	 * @model type="ucm.scenario.Variable" opposite="enumerationType"
	 * @generated
	 */
	EList getInstances();

} // EnumerationType