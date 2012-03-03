/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import ucm.performance.ActiveResource;
import ucm.performance.PerformancePackage;
import ucm.performance.TimeUnit;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Active Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.performance.impl.ActiveResourceImpl#getOpTime <em>Op Time</em>}</li>
 *   <li>{@link ucm.performance.impl.ActiveResourceImpl#getUnit <em>Unit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ActiveResourceImpl extends GeneralResourceImpl implements ActiveResource {
    /**
	 * The default value of the '{@link #getOpTime() <em>Op Time</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getOpTime()
	 * @generated
	 * @ordered
	 */
    protected static final String OP_TIME_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getOpTime() <em>Op Time</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getOpTime()
	 * @generated
	 * @ordered
	 */
    protected String opTime = OP_TIME_EDEFAULT;

    /**
	 * The default value of the '{@link #getUnit() <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getUnit()
	 * @generated
	 * @ordered
	 */
    protected static final TimeUnit UNIT_EDEFAULT = TimeUnit.MS_LITERAL;

    /**
	 * The cached value of the '{@link #getUnit() <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getUnit()
	 * @generated
	 * @ordered
	 */
    protected TimeUnit unit = UNIT_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ActiveResourceImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return PerformancePackage.Literals.ACTIVE_RESOURCE;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getOpTime() {
		return opTime;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOpTime(String newOpTime) {
		String oldOpTime = opTime;
		opTime = newOpTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.ACTIVE_RESOURCE__OP_TIME, oldOpTime, opTime));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public TimeUnit getUnit() {
		return unit;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUnit(TimeUnit newUnit) {
		TimeUnit oldUnit = unit;
		unit = newUnit == null ? UNIT_EDEFAULT : newUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.ACTIVE_RESOURCE__UNIT, oldUnit, unit));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PerformancePackage.ACTIVE_RESOURCE__OP_TIME:
				return getOpTime();
			case PerformancePackage.ACTIVE_RESOURCE__UNIT:
				return getUnit();
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
			case PerformancePackage.ACTIVE_RESOURCE__OP_TIME:
				setOpTime((String)newValue);
				return;
			case PerformancePackage.ACTIVE_RESOURCE__UNIT:
				setUnit((TimeUnit)newValue);
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
			case PerformancePackage.ACTIVE_RESOURCE__OP_TIME:
				setOpTime(OP_TIME_EDEFAULT);
				return;
			case PerformancePackage.ACTIVE_RESOURCE__UNIT:
				setUnit(UNIT_EDEFAULT);
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
			case PerformancePackage.ACTIVE_RESOURCE__OP_TIME:
				return OP_TIME_EDEFAULT == null ? opTime != null : !OP_TIME_EDEFAULT.equals(opTime);
			case PerformancePackage.ACTIVE_RESOURCE__UNIT:
				return unit != UNIT_EDEFAULT;
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
		result.append(" (opTime: ");
		result.append(opTime);
		result.append(", unit: ");
		result.append(unit);
		result.append(')');
		return result.toString();
	}

} //ActiveResourceImpl
