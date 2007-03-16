/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.impl.PathNodeImpl;
import ucm.performance.PerformancePackage;
import ucm.performance.ResponseTimeReq;
import ucm.performance.Timestamp;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Timestamp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.performance.impl.TimestampImpl#getOrientation <em>Orientation</em>}</li>
 *   <li>{@link ucm.performance.impl.TimestampImpl#getTargets <em>Targets</em>}</li>
 *   <li>{@link ucm.performance.impl.TimestampImpl#getSources <em>Sources</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TimestampImpl extends PathNodeImpl implements Timestamp {
	/**
	 * The default value of the '{@link #getOrientation() <em>Orientation</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getOrientation()
	 * @generated
	 * @ordered
	 */
    protected static final String ORIENTATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOrientation() <em>Orientation</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getOrientation()
	 * @generated
	 * @ordered
	 */
    protected String orientation = ORIENTATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTargets() <em>Targets</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getTargets()
	 * @generated
	 * @ordered
	 */
    protected EList targets = null;

	/**
	 * The cached value of the '{@link #getSources() <em>Sources</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSources()
	 * @generated
	 * @ordered
	 */
    protected EList sources = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected TimestampImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return PerformancePackage.Literals.TIMESTAMP;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getOrientation() {
		return orientation;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setOrientation(String newOrientation) {
		String oldOrientation = orientation;
		orientation = newOrientation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.TIMESTAMP__ORIENTATION, oldOrientation, orientation));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getTargets() {
		if (targets == null) {
			targets = new EObjectWithInverseResolvingEList(ResponseTimeReq.class, this, PerformancePackage.TIMESTAMP__TARGETS, PerformancePackage.RESPONSE_TIME_REQ__TS1);
		}
		return targets;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getSources() {
		if (sources == null) {
			sources = new EObjectWithInverseResolvingEList(ResponseTimeReq.class, this, PerformancePackage.TIMESTAMP__SOURCES, PerformancePackage.RESPONSE_TIME_REQ__TS2);
		}
		return sources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PerformancePackage.TIMESTAMP__TARGETS:
				return ((InternalEList)getTargets()).basicAdd(otherEnd, msgs);
			case PerformancePackage.TIMESTAMP__SOURCES:
				return ((InternalEList)getSources()).basicAdd(otherEnd, msgs);
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
			case PerformancePackage.TIMESTAMP__TARGETS:
				return ((InternalEList)getTargets()).basicRemove(otherEnd, msgs);
			case PerformancePackage.TIMESTAMP__SOURCES:
				return ((InternalEList)getSources()).basicRemove(otherEnd, msgs);
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
			case PerformancePackage.TIMESTAMP__ORIENTATION:
				return getOrientation();
			case PerformancePackage.TIMESTAMP__TARGETS:
				return getTargets();
			case PerformancePackage.TIMESTAMP__SOURCES:
				return getSources();
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
			case PerformancePackage.TIMESTAMP__ORIENTATION:
				setOrientation((String)newValue);
				return;
			case PerformancePackage.TIMESTAMP__TARGETS:
				getTargets().clear();
				getTargets().addAll((Collection)newValue);
				return;
			case PerformancePackage.TIMESTAMP__SOURCES:
				getSources().clear();
				getSources().addAll((Collection)newValue);
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
			case PerformancePackage.TIMESTAMP__ORIENTATION:
				setOrientation(ORIENTATION_EDEFAULT);
				return;
			case PerformancePackage.TIMESTAMP__TARGETS:
				getTargets().clear();
				return;
			case PerformancePackage.TIMESTAMP__SOURCES:
				getSources().clear();
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
			case PerformancePackage.TIMESTAMP__ORIENTATION:
				return ORIENTATION_EDEFAULT == null ? orientation != null : !ORIENTATION_EDEFAULT.equals(orientation);
			case PerformancePackage.TIMESTAMP__TARGETS:
				return targets != null && !targets.isEmpty();
			case PerformancePackage.TIMESTAMP__SOURCES:
				return sources != null && !sources.isEmpty();
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
		result.append(" (orientation: ");
		result.append(orientation);
		result.append(')');
		return result.toString();
	}

} //TimestampImpl
