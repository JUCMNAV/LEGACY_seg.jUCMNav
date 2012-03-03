/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import org.eclipse.emf.common.util.EList;

import urncore.Condition;
import urncore.IURNConnection;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.NodeConnection#getProbability <em>Probability</em>}</li>
 *   <li>{@link ucm.map.NodeConnection#getThreshold <em>Threshold</em>}</li>
 *   <li>{@link ucm.map.NodeConnection#getInBindings <em>In Bindings</em>}</li>
 *   <li>{@link ucm.map.NodeConnection#getOutBindings <em>Out Bindings</em>}</li>
 *   <li>{@link ucm.map.NodeConnection#getCondition <em>Condition</em>}</li>
 *   <li>{@link ucm.map.NodeConnection#getOutBindingsPlugin <em>Out Bindings Plugin</em>}</li>
 *   <li>{@link ucm.map.NodeConnection#getInBindingsPlugin <em>In Bindings Plugin</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getNodeConnection()
 * @model
 * @generated
 */
public interface NodeConnection extends IURNConnection {
    /**
	 * Returns the value of the '<em><b>Probability</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Probability</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Probability</em>' attribute.
	 * @see #setProbability(double)
	 * @see ucm.map.MapPackage#getNodeConnection_Probability()
	 * @model default="1.0"
	 * @generated
	 */
    double getProbability();

    /**
	 * Sets the value of the '{@link ucm.map.NodeConnection#getProbability <em>Probability</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Probability</em>' attribute.
	 * @see #getProbability()
	 * @generated
	 */
    void setProbability(double value);

    /**
	 * Returns the value of the '<em><b>Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Threshold</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Threshold</em>' attribute.
	 * @see #setThreshold(String)
	 * @see ucm.map.MapPackage#getNodeConnection_Threshold()
	 * @model
	 * @generated
	 */
    String getThreshold();

    /**
	 * Sets the value of the '{@link ucm.map.NodeConnection#getThreshold <em>Threshold</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Threshold</em>' attribute.
	 * @see #getThreshold()
	 * @generated
	 */
    void setThreshold(String value);

    /**
	 * Returns the value of the '<em><b>In Bindings</b></em>' reference list.
	 * The list contents are of type {@link ucm.map.InBinding}.
	 * It is bidirectional and its opposite is '{@link ucm.map.InBinding#getStubEntry <em>Stub Entry</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>In Bindings</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>In Bindings</em>' reference list.
	 * @see ucm.map.MapPackage#getNodeConnection_InBindings()
	 * @see ucm.map.InBinding#getStubEntry
	 * @model type="ucm.map.InBinding" opposite="stubEntry"
	 * @generated
	 */
    EList getInBindings();

    /**
	 * Returns the value of the '<em><b>Out Bindings</b></em>' reference list.
	 * The list contents are of type {@link ucm.map.OutBinding}.
	 * It is bidirectional and its opposite is '{@link ucm.map.OutBinding#getStubExit <em>Stub Exit</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Out Bindings</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Bindings</em>' reference list.
	 * @see ucm.map.MapPackage#getNodeConnection_OutBindings()
	 * @see ucm.map.OutBinding#getStubExit
	 * @model type="ucm.map.OutBinding" opposite="stubExit"
	 * @generated
	 */
    EList getOutBindings();

    /**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link urncore.Condition#getNodeConnection <em>Node Connection</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference.
	 * @see #setCondition(Condition)
	 * @see ucm.map.MapPackage#getNodeConnection_Condition()
	 * @see urncore.Condition#getNodeConnection
	 * @model opposite="nodeConnection" containment="true"
	 * @generated
	 */
    Condition getCondition();

    /**
	 * Sets the value of the '{@link ucm.map.NodeConnection#getCondition <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' containment reference.
	 * @see #getCondition()
	 * @generated
	 */
    void setCondition(Condition value);

    /**
	 * Returns the value of the '<em><b>Out Bindings Plugin</b></em>' reference list.
	 * The list contents are of type {@link ucm.map.OutBinding}.
	 * It is bidirectional and its opposite is '{@link ucm.map.OutBinding#getPointcutEntry <em>Pointcut Entry</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Out Bindings Plugin</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Out Bindings Plugin</em>' reference list.
	 * @see ucm.map.MapPackage#getNodeConnection_OutBindingsPlugin()
	 * @see ucm.map.OutBinding#getPointcutEntry
	 * @model type="ucm.map.OutBinding" opposite="pointcutEntry"
	 * @generated
	 */
    EList getOutBindingsPlugin();

    /**
	 * Returns the value of the '<em><b>In Bindings Plugin</b></em>' reference list.
	 * The list contents are of type {@link ucm.map.InBinding}.
	 * It is bidirectional and its opposite is '{@link ucm.map.InBinding#getPointcutExit <em>Pointcut Exit</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>In Bindings Plugin</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>In Bindings Plugin</em>' reference list.
	 * @see ucm.map.MapPackage#getNodeConnection_InBindingsPlugin()
	 * @see ucm.map.InBinding#getPointcutExit
	 * @model type="ucm.map.InBinding" opposite="pointcutExit"
	 * @generated
	 */
    EList getInBindingsPlugin();

} // NodeConnection
