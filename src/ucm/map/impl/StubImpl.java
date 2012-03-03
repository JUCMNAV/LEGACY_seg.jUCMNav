/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.AspectKind;
import ucm.map.MapPackage;
import ucm.map.PluginBinding;
import ucm.map.PointcutKind;
import ucm.map.Stub;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stub</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.StubImpl#isDynamic <em>Dynamic</em>}</li>
 *   <li>{@link ucm.map.impl.StubImpl#isShared <em>Shared</em>}</li>
 *   <li>{@link ucm.map.impl.StubImpl#getRepetitionCount <em>Repetition Count</em>}</li>
 *   <li>{@link ucm.map.impl.StubImpl#isPointcut <em>Pointcut</em>}</li>
 *   <li>{@link ucm.map.impl.StubImpl#isSynchronization <em>Synchronization</em>}</li>
 *   <li>{@link ucm.map.impl.StubImpl#isBlocking <em>Blocking</em>}</li>
 *   <li>{@link ucm.map.impl.StubImpl#getAopointcut <em>Aopointcut</em>}</li>
 *   <li>{@link ucm.map.impl.StubImpl#getAspect <em>Aspect</em>}</li>
 *   <li>{@link ucm.map.impl.StubImpl#getBindings <em>Bindings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StubImpl extends PathNodeImpl implements Stub {
    /**
	 * The default value of the '{@link #isDynamic() <em>Dynamic</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isDynamic()
	 * @generated
	 * @ordered
	 */
    protected static final boolean DYNAMIC_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isDynamic() <em>Dynamic</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isDynamic()
	 * @generated
	 * @ordered
	 */
    protected boolean dynamic = DYNAMIC_EDEFAULT;

    /**
	 * The default value of the '{@link #isShared() <em>Shared</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isShared()
	 * @generated
	 * @ordered
	 */
    protected static final boolean SHARED_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isShared() <em>Shared</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isShared()
	 * @generated
	 * @ordered
	 */
    protected boolean shared = SHARED_EDEFAULT;

    /**
	 * The default value of the '{@link #getRepetitionCount() <em>Repetition Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepetitionCount()
	 * @generated
	 * @ordered
	 */
	protected static final String REPETITION_COUNT_EDEFAULT = "1";

    /**
	 * The cached value of the '{@link #getRepetitionCount() <em>Repetition Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepetitionCount()
	 * @generated
	 * @ordered
	 */
	protected String repetitionCount = REPETITION_COUNT_EDEFAULT;

    /**
	 * The default value of the '{@link #isPointcut() <em>Pointcut</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPointcut()
	 * @generated
	 * @ordered
	 */
	protected static final boolean POINTCUT_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isPointcut() <em>Pointcut</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPointcut()
	 * @generated
	 * @ordered
	 */
	protected boolean pointcut = POINTCUT_EDEFAULT;

    /**
	 * The default value of the '{@link #isSynchronization() <em>Synchronization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSynchronization()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SYNCHRONIZATION_EDEFAULT = false;

				/**
	 * The cached value of the '{@link #isSynchronization() <em>Synchronization</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSynchronization()
	 * @generated
	 * @ordered
	 */
	protected boolean synchronization = SYNCHRONIZATION_EDEFAULT;

				/**
	 * The default value of the '{@link #isBlocking() <em>Blocking</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBlocking()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BLOCKING_EDEFAULT = false;

				/**
	 * The cached value of the '{@link #isBlocking() <em>Blocking</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBlocking()
	 * @generated
	 * @ordered
	 */
	protected boolean blocking = BLOCKING_EDEFAULT;

				/**
	 * The default value of the '{@link #getAopointcut() <em>Aopointcut</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getAopointcut()
	 * @generated
	 * @ordered
	 */
    protected static final PointcutKind AOPOINTCUT_EDEFAULT = PointcutKind.NONE_LITERAL;

                /**
	 * The cached value of the '{@link #getAopointcut() <em>Aopointcut</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getAopointcut()
	 * @generated
	 * @ordered
	 */
    protected PointcutKind aopointcut = AOPOINTCUT_EDEFAULT;

                /**
	 * The default value of the '{@link #getAspect() <em>Aspect</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getAspect()
	 * @generated
	 * @ordered
	 */
    protected static final AspectKind ASPECT_EDEFAULT = AspectKind.NONE_LITERAL;

                /**
	 * The cached value of the '{@link #getAspect() <em>Aspect</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getAspect()
	 * @generated
	 * @ordered
	 */
    protected AspectKind aspect = ASPECT_EDEFAULT;

                /**
	 * The cached value of the '{@link #getBindings() <em>Bindings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getBindings()
	 * @generated
	 * @ordered
	 */
    protected EList bindings;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected StubImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return MapPackage.Literals.STUB;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isDynamic() {
		return dynamic;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDynamic(boolean newDynamic) {
		boolean oldDynamic = dynamic;
		dynamic = newDynamic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.STUB__DYNAMIC, oldDynamic, dynamic));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isShared() {
		return shared;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setShared(boolean newShared) {
		boolean oldShared = shared;
		shared = newShared;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.STUB__SHARED, oldShared, shared));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRepetitionCount() {
		return repetitionCount;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRepetitionCount(String newRepetitionCount) {
		String oldRepetitionCount = repetitionCount;
		repetitionCount = newRepetitionCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.STUB__REPETITION_COUNT, oldRepetitionCount, repetitionCount));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getBindings() {
		if (bindings == null) {
			bindings = new EObjectContainmentWithInverseEList(PluginBinding.class, this, MapPackage.STUB__BINDINGS, MapPackage.PLUGIN_BINDING__STUB);
		}
		return bindings;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isPointcut() {
		return pointcut;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPointcut(boolean newPointcut) {
		boolean oldPointcut = pointcut;
		pointcut = newPointcut;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.STUB__POINTCUT, oldPointcut, pointcut));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSynchronization() {
		return synchronization;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSynchronization(boolean newSynchronization) {
		boolean oldSynchronization = synchronization;
		synchronization = newSynchronization;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.STUB__SYNCHRONIZATION, oldSynchronization, synchronization));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBlocking() {
		return blocking;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBlocking(boolean newBlocking) {
		boolean oldBlocking = blocking;
		blocking = newBlocking;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.STUB__BLOCKING, oldBlocking, blocking));
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PointcutKind getAopointcut() {
		return aopointcut;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setAopointcut(PointcutKind newAopointcut) {
		PointcutKind oldAopointcut = aopointcut;
		aopointcut = newAopointcut == null ? AOPOINTCUT_EDEFAULT : newAopointcut;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.STUB__AOPOINTCUT, oldAopointcut, aopointcut));
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public AspectKind getAspect() {
		return aspect;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setAspect(AspectKind newAspect) {
		AspectKind oldAspect = aspect;
		aspect = newAspect == null ? ASPECT_EDEFAULT : newAspect;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.STUB__ASPECT, oldAspect, aspect));
	}

                /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MapPackage.STUB__BINDINGS:
				return ((InternalEList)getBindings()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MapPackage.STUB__BINDINGS:
				return ((InternalEList)getBindings()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MapPackage.STUB__DYNAMIC:
				return isDynamic() ? Boolean.TRUE : Boolean.FALSE;
			case MapPackage.STUB__SHARED:
				return isShared() ? Boolean.TRUE : Boolean.FALSE;
			case MapPackage.STUB__REPETITION_COUNT:
				return getRepetitionCount();
			case MapPackage.STUB__POINTCUT:
				return isPointcut() ? Boolean.TRUE : Boolean.FALSE;
			case MapPackage.STUB__SYNCHRONIZATION:
				return isSynchronization() ? Boolean.TRUE : Boolean.FALSE;
			case MapPackage.STUB__BLOCKING:
				return isBlocking() ? Boolean.TRUE : Boolean.FALSE;
			case MapPackage.STUB__AOPOINTCUT:
				return getAopointcut();
			case MapPackage.STUB__ASPECT:
				return getAspect();
			case MapPackage.STUB__BINDINGS:
				return getBindings();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MapPackage.STUB__DYNAMIC:
				setDynamic(((Boolean)newValue).booleanValue());
				return;
			case MapPackage.STUB__SHARED:
				setShared(((Boolean)newValue).booleanValue());
				return;
			case MapPackage.STUB__REPETITION_COUNT:
				setRepetitionCount((String)newValue);
				return;
			case MapPackage.STUB__POINTCUT:
				setPointcut(((Boolean)newValue).booleanValue());
				return;
			case MapPackage.STUB__SYNCHRONIZATION:
				setSynchronization(((Boolean)newValue).booleanValue());
				return;
			case MapPackage.STUB__BLOCKING:
				setBlocking(((Boolean)newValue).booleanValue());
				return;
			case MapPackage.STUB__AOPOINTCUT:
				setAopointcut((PointcutKind)newValue);
				return;
			case MapPackage.STUB__ASPECT:
				setAspect((AspectKind)newValue);
				return;
			case MapPackage.STUB__BINDINGS:
				getBindings().clear();
				getBindings().addAll((Collection)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case MapPackage.STUB__DYNAMIC:
				setDynamic(DYNAMIC_EDEFAULT);
				return;
			case MapPackage.STUB__SHARED:
				setShared(SHARED_EDEFAULT);
				return;
			case MapPackage.STUB__REPETITION_COUNT:
				setRepetitionCount(REPETITION_COUNT_EDEFAULT);
				return;
			case MapPackage.STUB__POINTCUT:
				setPointcut(POINTCUT_EDEFAULT);
				return;
			case MapPackage.STUB__SYNCHRONIZATION:
				setSynchronization(SYNCHRONIZATION_EDEFAULT);
				return;
			case MapPackage.STUB__BLOCKING:
				setBlocking(BLOCKING_EDEFAULT);
				return;
			case MapPackage.STUB__AOPOINTCUT:
				setAopointcut(AOPOINTCUT_EDEFAULT);
				return;
			case MapPackage.STUB__ASPECT:
				setAspect(ASPECT_EDEFAULT);
				return;
			case MapPackage.STUB__BINDINGS:
				getBindings().clear();
				return;
		}
		super.eUnset(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MapPackage.STUB__DYNAMIC:
				return dynamic != DYNAMIC_EDEFAULT;
			case MapPackage.STUB__SHARED:
				return shared != SHARED_EDEFAULT;
			case MapPackage.STUB__REPETITION_COUNT:
				return REPETITION_COUNT_EDEFAULT == null ? repetitionCount != null : !REPETITION_COUNT_EDEFAULT.equals(repetitionCount);
			case MapPackage.STUB__POINTCUT:
				return pointcut != POINTCUT_EDEFAULT;
			case MapPackage.STUB__SYNCHRONIZATION:
				return synchronization != SYNCHRONIZATION_EDEFAULT;
			case MapPackage.STUB__BLOCKING:
				return blocking != BLOCKING_EDEFAULT;
			case MapPackage.STUB__AOPOINTCUT:
				return aopointcut != AOPOINTCUT_EDEFAULT;
			case MapPackage.STUB__ASPECT:
				return aspect != ASPECT_EDEFAULT;
			case MapPackage.STUB__BINDINGS:
				return bindings != null && !bindings.isEmpty();
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (dynamic: ");
		result.append(dynamic);
		result.append(", shared: ");
		result.append(shared);
		result.append(", repetitionCount: ");
		result.append(repetitionCount);
		result.append(", pointcut: ");
		result.append(pointcut);
		result.append(", synchronization: ");
		result.append(synchronization);
		result.append(", blocking: ");
		result.append(blocking);
		result.append(", aopointcut: ");
		result.append(aopointcut);
		result.append(", aspect: ");
		result.append(aspect);
		result.append(')');
		return result.toString();
	}

} //StubImpl
