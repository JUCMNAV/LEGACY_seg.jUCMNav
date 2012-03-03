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
 * A representation of the model object '<em><b>In Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.InBinding#getBinding <em>Binding</em>}</li>
 *   <li>{@link ucm.map.InBinding#getStartPoint <em>Start Point</em>}</li>
 *   <li>{@link ucm.map.InBinding#getStubEntry <em>Stub Entry</em>}</li>
 *   <li>{@link ucm.map.InBinding#getPointcutExit <em>Pointcut Exit</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getInBinding()
 * @model
 * @generated
 */
public interface InBinding extends EObject {
    /**
	 * Returns the value of the '<em><b>Binding</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link ucm.map.PluginBinding#getIn <em>In</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Binding</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' container reference.
	 * @see #setBinding(PluginBinding)
	 * @see ucm.map.MapPackage#getInBinding_Binding()
	 * @see ucm.map.PluginBinding#getIn
	 * @model opposite="in" required="true"
	 * @generated
	 */
    PluginBinding getBinding();

    /**
	 * Sets the value of the '{@link ucm.map.InBinding#getBinding <em>Binding</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' container reference.
	 * @see #getBinding()
	 * @generated
	 */
    void setBinding(PluginBinding value);

    /**
	 * Returns the value of the '<em><b>Start Point</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ucm.map.StartPoint#getInBindings <em>In Bindings</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Start Point</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Point</em>' reference.
	 * @see #setStartPoint(StartPoint)
	 * @see ucm.map.MapPackage#getInBinding_StartPoint()
	 * @see ucm.map.StartPoint#getInBindings
	 * @model opposite="inBindings"
	 * @generated
	 */
    StartPoint getStartPoint();

    /**
	 * Sets the value of the '{@link ucm.map.InBinding#getStartPoint <em>Start Point</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Point</em>' reference.
	 * @see #getStartPoint()
	 * @generated
	 */
    void setStartPoint(StartPoint value);

    /**
	 * Returns the value of the '<em><b>Stub Entry</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ucm.map.NodeConnection#getInBindings <em>In Bindings</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Stub Entry</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Stub Entry</em>' reference.
	 * @see #setStubEntry(NodeConnection)
	 * @see ucm.map.MapPackage#getInBinding_StubEntry()
	 * @see ucm.map.NodeConnection#getInBindings
	 * @model opposite="inBindings" required="true"
	 * @generated
	 */
    NodeConnection getStubEntry();

    /**
	 * Sets the value of the '{@link ucm.map.InBinding#getStubEntry <em>Stub Entry</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Stub Entry</em>' reference.
	 * @see #getStubEntry()
	 * @generated
	 */
    void setStubEntry(NodeConnection value);

    /**
	 * Returns the value of the '<em><b>Pointcut Exit</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link ucm.map.NodeConnection#getInBindingsPlugin <em>In Bindings Plugin</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pointcut Exit</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Pointcut Exit</em>' reference.
	 * @see #setPointcutExit(NodeConnection)
	 * @see ucm.map.MapPackage#getInBinding_PointcutExit()
	 * @see ucm.map.NodeConnection#getInBindingsPlugin
	 * @model opposite="inBindingsPlugin"
	 * @generated
	 */
    NodeConnection getPointcutExit();

    /**
	 * Sets the value of the '{@link ucm.map.InBinding#getPointcutExit <em>Pointcut Exit</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pointcut Exit</em>' reference.
	 * @see #getPointcutExit()
	 * @generated
	 */
    void setPointcutExit(NodeConnection value);

} // InBinding
