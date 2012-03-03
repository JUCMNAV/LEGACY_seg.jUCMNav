/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import urncore.Condition;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Plugin Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A plugin refers to a Map that can be substituted  to the parent stub. The binding between the plugin and its  parent stub is defined by the in-connections, the  out-connections, and the instance-values. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.PluginBinding#getId <em>Id</em>}</li>
 *   <li>{@link ucm.map.PluginBinding#getProbability <em>Probability</em>}</li>
 *   <li>{@link ucm.map.PluginBinding#isTransaction <em>Transaction</em>}</li>
 *   <li>{@link ucm.map.PluginBinding#getReplicationFactor <em>Replication Factor</em>}</li>
 *   <li>{@link ucm.map.PluginBinding#getIn <em>In</em>}</li>
 *   <li>{@link ucm.map.PluginBinding#getOut <em>Out</em>}</li>
 *   <li>{@link ucm.map.PluginBinding#getStub <em>Stub</em>}</li>
 *   <li>{@link ucm.map.PluginBinding#getPlugin <em>Plugin</em>}</li>
 *   <li>{@link ucm.map.PluginBinding#getPrecondition <em>Precondition</em>}</li>
 *   <li>{@link ucm.map.PluginBinding#getComponents <em>Components</em>}</li>
 *   <li>{@link ucm.map.PluginBinding#getResponsibilities <em>Responsibilities</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getPluginBinding()
 * @model
 * @generated
 */
public interface PluginBinding extends EObject {
    /**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see ucm.map.MapPackage#getPluginBinding_Id()
	 * @model
	 * @generated
	 */
    String getId();

    /**
	 * Sets the value of the '{@link ucm.map.PluginBinding#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
    void setId(String value);

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
	 * @see ucm.map.MapPackage#getPluginBinding_Probability()
	 * @model default="1.0"
	 * @generated
	 */
    double getProbability();

    /**
	 * Sets the value of the '{@link ucm.map.PluginBinding#getProbability <em>Probability</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Probability</em>' attribute.
	 * @see #getProbability()
	 * @generated
	 */
    void setProbability(double value);

    /**
	 * Returns the value of the '<em><b>Transaction</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transaction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transaction</em>' attribute.
	 * @see #setTransaction(boolean)
	 * @see ucm.map.MapPackage#getPluginBinding_Transaction()
	 * @model default="false"
	 * @generated
	 */
	boolean isTransaction();

    /**
	 * Sets the value of the '{@link ucm.map.PluginBinding#isTransaction <em>Transaction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transaction</em>' attribute.
	 * @see #isTransaction()
	 * @generated
	 */
	void setTransaction(boolean value);

    /**
	 * Returns the value of the '<em><b>Replication Factor</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Replication Factor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Replication Factor</em>' attribute.
	 * @see #setReplicationFactor(int)
	 * @see ucm.map.MapPackage#getPluginBinding_ReplicationFactor()
	 * @model default="1"
	 * @generated
	 */
	int getReplicationFactor();

				/**
	 * Sets the value of the '{@link ucm.map.PluginBinding#getReplicationFactor <em>Replication Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Replication Factor</em>' attribute.
	 * @see #getReplicationFactor()
	 * @generated
	 */
	void setReplicationFactor(int value);

				/**
	 * Returns the value of the '<em><b>In</b></em>' containment reference list.
	 * The list contents are of type {@link ucm.map.InBinding}.
	 * It is bidirectional and its opposite is '{@link ucm.map.InBinding#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>In</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>In</em>' containment reference list.
	 * @see ucm.map.MapPackage#getPluginBinding_In()
	 * @see ucm.map.InBinding#getBinding
	 * @model type="ucm.map.InBinding" opposite="binding" containment="true" required="true"
	 * @generated
	 */
    EList getIn();

    /**
	 * Returns the value of the '<em><b>Out</b></em>' containment reference list.
	 * The list contents are of type {@link ucm.map.OutBinding}.
	 * It is bidirectional and its opposite is '{@link ucm.map.OutBinding#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Out</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Out</em>' containment reference list.
	 * @see ucm.map.MapPackage#getPluginBinding_Out()
	 * @see ucm.map.OutBinding#getBinding
	 * @model type="ucm.map.OutBinding" opposite="binding" containment="true" required="true"
	 * @generated
	 */
    EList getOut();

    /**
	 * Returns the value of the '<em><b>Stub</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucm.map.Stub#getBindings <em>Bindings</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Stub</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Stub</em>' container reference.
	 * @see #setStub(Stub)
	 * @see ucm.map.MapPackage#getPluginBinding_Stub()
	 * @see ucm.map.Stub#getBindings
	 * @model opposite="bindings"
	 * @generated
	 */
    Stub getStub();

    /**
	 * Sets the value of the '{@link ucm.map.PluginBinding#getStub <em>Stub</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stub</em>' container reference.
	 * @see #getStub()
	 * @generated
	 */
    void setStub(Stub value);

    /**
	 * Returns the value of the '<em><b>Plugin</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ucm.map.UCMmap#getParentStub <em>Parent Stub</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Plugin</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Plugin</em>' reference.
	 * @see #setPlugin(UCMmap)
	 * @see ucm.map.MapPackage#getPluginBinding_Plugin()
	 * @see ucm.map.UCMmap#getParentStub
	 * @model opposite="parentStub" required="true"
	 * @generated
	 */
    UCMmap getPlugin();

    /**
	 * Sets the value of the '{@link ucm.map.PluginBinding#getPlugin <em>Plugin</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Plugin</em>' reference.
	 * @see #getPlugin()
	 * @generated
	 */
    void setPlugin(UCMmap value);

    /**
	 * Returns the value of the '<em><b>Precondition</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link urncore.Condition#getPluginBinding <em>Plugin Binding</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Precondition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Precondition</em>' containment reference.
	 * @see #setPrecondition(Condition)
	 * @see ucm.map.MapPackage#getPluginBinding_Precondition()
	 * @see urncore.Condition#getPluginBinding
	 * @model opposite="pluginBinding" containment="true"
	 * @generated
	 */
    Condition getPrecondition();

    /**
	 * Sets the value of the '{@link ucm.map.PluginBinding#getPrecondition <em>Precondition</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precondition</em>' containment reference.
	 * @see #getPrecondition()
	 * @generated
	 */
    void setPrecondition(Condition value);

				/**
	 * Returns the value of the '<em><b>Components</b></em>' containment reference list.
	 * The list contents are of type {@link ucm.map.ComponentBinding}.
	 * It is bidirectional and its opposite is '{@link ucm.map.ComponentBinding#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Components</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Components</em>' containment reference list.
	 * @see ucm.map.MapPackage#getPluginBinding_Components()
	 * @see ucm.map.ComponentBinding#getBinding
	 * @model type="ucm.map.ComponentBinding" opposite="binding" containment="true"
	 * @generated
	 */
	EList getComponents();

                /**
	 * Returns the value of the '<em><b>Responsibilities</b></em>' containment reference list.
	 * The list contents are of type {@link ucm.map.ResponsibilityBinding}.
	 * It is bidirectional and its opposite is '{@link ucm.map.ResponsibilityBinding#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Responsibilities</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Responsibilities</em>' containment reference list.
	 * @see ucm.map.MapPackage#getPluginBinding_Responsibilities()
	 * @see ucm.map.ResponsibilityBinding#getBinding
	 * @model type="ucm.map.ResponsibilityBinding" opposite="binding" containment="true"
	 * @generated
	 */
    EList getResponsibilities();

} // PluginBinding
