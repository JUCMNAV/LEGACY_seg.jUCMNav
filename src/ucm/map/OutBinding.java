/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Out Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.OutBinding#getBinding <em>Binding</em>}</li>
 *   <li>{@link ucm.map.OutBinding#getEndPoint <em>End Point</em>}</li>
 *   <li>{@link ucm.map.OutBinding#getStubExit <em>Stub Exit</em>}</li>
 *   <li>{@link ucm.map.OutBinding#getPointcutEntry <em>Pointcut Entry</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getOutBinding()
 * @model
 * @generated
 */
public interface OutBinding extends EObject {
    /**
	 * Returns the value of the '<em><b>Binding</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucm.map.PluginBinding#getOut <em>Out</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Binding</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' container reference.
	 * @see #setBinding(PluginBinding)
	 * @see ucm.map.MapPackage#getOutBinding_Binding()
	 * @see ucm.map.PluginBinding#getOut
	 * @model opposite="out" required="true"
	 * @generated
	 */
    PluginBinding getBinding();

    /**
	 * Sets the value of the '{@link ucm.map.OutBinding#getBinding <em>Binding</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' container reference.
	 * @see #getBinding()
	 * @generated
	 */
    void setBinding(PluginBinding value);

    /**
	 * Returns the value of the '<em><b>End Point</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ucm.map.EndPoint#getOutBindings <em>Out Bindings</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>End Point</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>End Point</em>' reference.
	 * @see #setEndPoint(EndPoint)
	 * @see ucm.map.MapPackage#getOutBinding_EndPoint()
	 * @see ucm.map.EndPoint#getOutBindings
	 * @model opposite="outBindings"
	 * @generated
	 */
    EndPoint getEndPoint();

    /**
	 * Sets the value of the '{@link ucm.map.OutBinding#getEndPoint <em>End Point</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End Point</em>' reference.
	 * @see #getEndPoint()
	 * @generated
	 */
    void setEndPoint(EndPoint value);

    /**
	 * Returns the value of the '<em><b>Stub Exit</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ucm.map.NodeConnection#getOutBindings <em>Out Bindings</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Stub Exit</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Stub Exit</em>' reference.
	 * @see #setStubExit(NodeConnection)
	 * @see ucm.map.MapPackage#getOutBinding_StubExit()
	 * @see ucm.map.NodeConnection#getOutBindings
	 * @model opposite="outBindings" required="true"
	 * @generated
	 */
    NodeConnection getStubExit();

    /**
	 * Sets the value of the '{@link ucm.map.OutBinding#getStubExit <em>Stub Exit</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stub Exit</em>' reference.
	 * @see #getStubExit()
	 * @generated
	 */
    void setStubExit(NodeConnection value);

    /**
	 * Returns the value of the '<em><b>Pointcut Entry</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ucm.map.NodeConnection#getOutBindingsPlugin <em>Out Bindings Plugin</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pointcut Entry</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Pointcut Entry</em>' reference.
	 * @see #setPointcutEntry(NodeConnection)
	 * @see ucm.map.MapPackage#getOutBinding_PointcutEntry()
	 * @see ucm.map.NodeConnection#getOutBindingsPlugin
	 * @model opposite="outBindingsPlugin"
	 * @generated
	 */
    NodeConnection getPointcutEntry();

    /**
	 * Sets the value of the '{@link ucm.map.OutBinding#getPointcutEntry <em>Pointcut Entry</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pointcut Entry</em>' reference.
	 * @see #getPointcutEntry()
	 * @generated
	 */
    void setPointcutEntry(NodeConnection value);

} // OutBinding
