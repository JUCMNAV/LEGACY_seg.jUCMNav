/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stub</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ucm.map.Stub#isDynamic <em>Dynamic</em>}</li>
 *   <li>{@link ucm.map.Stub#isShared <em>Shared</em>}</li>
 *   <li>{@link ucm.map.Stub#getRepetitionCount <em>Repetition Count</em>}</li>
 *   <li>{@link ucm.map.Stub#isPointcut <em>Pointcut</em>}</li>
 *   <li>{@link ucm.map.Stub#isSynchronization <em>Synchronization</em>}</li>
 *   <li>{@link ucm.map.Stub#isBlocking <em>Blocking</em>}</li>
 *   <li>{@link ucm.map.Stub#getAopointcut <em>Aopointcut</em>}</li>
 *   <li>{@link ucm.map.Stub#getAspect <em>Aspect</em>}</li>
 *   <li>{@link ucm.map.Stub#getBindings <em>Bindings</em>}</li>
 * </ul>
 * </p>
 *
 * @see ucm.map.MapPackage#getStub()
 * @model
 * @generated
 */
public interface Stub extends PathNode {
    /**
	 * Returns the value of the '<em><b>Dynamic</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Dynamic</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Dynamic</em>' attribute.
	 * @see #setDynamic(boolean)
	 * @see ucm.map.MapPackage#getStub_Dynamic()
	 * @model default="false"
	 * @generated
	 */
    boolean isDynamic();

    /**
	 * Sets the value of the '{@link ucm.map.Stub#isDynamic <em>Dynamic</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dynamic</em>' attribute.
	 * @see #isDynamic()
	 * @generated
	 */
    void setDynamic(boolean value);

    /**
	 * Returns the value of the '<em><b>Shared</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Shared</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Shared</em>' attribute.
	 * @see #setShared(boolean)
	 * @see ucm.map.MapPackage#getStub_Shared()
	 * @model default="false"
	 * @generated
	 */
    boolean isShared();

    /**
	 * Sets the value of the '{@link ucm.map.Stub#isShared <em>Shared</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Shared</em>' attribute.
	 * @see #isShared()
	 * @generated
	 */
    void setShared(boolean value);

    /**
	 * Returns the value of the '<em><b>Repetition Count</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Repetition Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Repetition Count</em>' attribute.
	 * @see #setRepetitionCount(String)
	 * @see ucm.map.MapPackage#getStub_RepetitionCount()
	 * @model default="1"
	 * @generated
	 */
	String getRepetitionCount();

    /**
	 * Sets the value of the '{@link ucm.map.Stub#getRepetitionCount <em>Repetition Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Repetition Count</em>' attribute.
	 * @see #getRepetitionCount()
	 * @generated
	 */
	void setRepetitionCount(String value);

    /**
	 * Returns the value of the '<em><b>Bindings</b></em>' containment reference list.
	 * The list contents are of type {@link ucm.map.PluginBinding}.
	 * It is bidirectional and its opposite is '{@link ucm.map.PluginBinding#getStub <em>Stub</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Bindings</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Bindings</em>' containment reference list.
	 * @see ucm.map.MapPackage#getStub_Bindings()
	 * @see ucm.map.PluginBinding#getStub
	 * @model type="ucm.map.PluginBinding" opposite="stub" containment="true"
	 * @generated
	 */
    EList getBindings();

    /**
	 * Returns the value of the '<em><b>Pointcut</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pointcut</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pointcut</em>' attribute.
	 * @see #setPointcut(boolean)
	 * @see ucm.map.MapPackage#getStub_Pointcut()
	 * @model default="false"
	 * @generated
	 */
	boolean isPointcut();

    /**
	 * Sets the value of the '{@link ucm.map.Stub#isPointcut <em>Pointcut</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pointcut</em>' attribute.
	 * @see #isPointcut()
	 * @generated
	 */
	void setPointcut(boolean value);

				/**
	 * Returns the value of the '<em><b>Synchronization</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Synchronization</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Synchronization</em>' attribute.
	 * @see #setSynchronization(boolean)
	 * @see ucm.map.MapPackage#getStub_Synchronization()
	 * @model default="false"
	 * @generated
	 */
	boolean isSynchronization();

				/**
	 * Sets the value of the '{@link ucm.map.Stub#isSynchronization <em>Synchronization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Synchronization</em>' attribute.
	 * @see #isSynchronization()
	 * @generated
	 */
	void setSynchronization(boolean value);

				/**
	 * Returns the value of the '<em><b>Blocking</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Blocking</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Blocking</em>' attribute.
	 * @see #setBlocking(boolean)
	 * @see ucm.map.MapPackage#getStub_Blocking()
	 * @model default="false"
	 * @generated
	 */
	boolean isBlocking();

				/**
	 * Sets the value of the '{@link ucm.map.Stub#isBlocking <em>Blocking</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Blocking</em>' attribute.
	 * @see #isBlocking()
	 * @generated
	 */
	void setBlocking(boolean value);

                /**
	 * Returns the value of the '<em><b>Aopointcut</b></em>' attribute.
	 * The default value is <code>"None"</code>.
	 * The literals are from the enumeration {@link ucm.map.PointcutKind}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Aopointcut</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Aopointcut</em>' attribute.
	 * @see ucm.map.PointcutKind
	 * @see #setAopointcut(PointcutKind)
	 * @see ucm.map.MapPackage#getStub_Aopointcut()
	 * @model default="None"
	 * @generated
	 */
    PointcutKind getAopointcut();

                /**
	 * Sets the value of the '{@link ucm.map.Stub#getAopointcut <em>Aopointcut</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Aopointcut</em>' attribute.
	 * @see ucm.map.PointcutKind
	 * @see #getAopointcut()
	 * @generated
	 */
    void setAopointcut(PointcutKind value);

                /**
	 * Returns the value of the '<em><b>Aspect</b></em>' attribute.
	 * The default value is <code>"None"</code>.
	 * The literals are from the enumeration {@link ucm.map.AspectKind}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Aspect</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Aspect</em>' attribute.
	 * @see ucm.map.AspectKind
	 * @see #setAspect(AspectKind)
	 * @see ucm.map.MapPackage#getStub_Aspect()
	 * @model default="None"
	 * @generated
	 */
    AspectKind getAspect();

                /**
	 * Sets the value of the '{@link ucm.map.Stub#getAspect <em>Aspect</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Aspect</em>' attribute.
	 * @see ucm.map.AspectKind
	 * @see #getAspect()
	 * @generated
	 */
    void setAspect(AspectKind value);

} // Stub
