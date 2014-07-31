/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.scenario.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import ucm.map.EndPoint;
import ucm.map.MapPackage;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>End Point</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.scenario.impl.ScenarioEndPointImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioEndPointImpl#isMandatory <em>Mandatory</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioEndPointImpl#getScenarioDef <em>Scenario Def</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioEndPointImpl#getEndPoint <em>End Point</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScenarioEndPointImpl extends MinimalEObjectImpl.Container implements ScenarioEndPoint {
    /**
	 * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLED_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected boolean enabled = ENABLED_EDEFAULT;

    /**
	 * The default value of the '{@link #isMandatory() <em>Mandatory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMandatory()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MANDATORY_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isMandatory() <em>Mandatory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMandatory()
	 * @generated
	 * @ordered
	 */
	protected boolean mandatory = MANDATORY_EDEFAULT;

    /**
	 * The cached value of the '{@link #getEndPoint() <em>End Point</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndPoint()
	 * @generated
	 * @ordered
	 */
	protected EndPoint endPoint;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScenarioEndPointImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ScenarioPackage.Literals.SCENARIO_END_POINT;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnabled() {
		return enabled;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnabled(boolean newEnabled) {
		boolean oldEnabled = enabled;
		enabled = newEnabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.SCENARIO_END_POINT__ENABLED, oldEnabled, enabled));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMandatory() {
		return mandatory;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMandatory(boolean newMandatory) {
		boolean oldMandatory = mandatory;
		mandatory = newMandatory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.SCENARIO_END_POINT__MANDATORY, oldMandatory, mandatory));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScenarioDef getScenarioDef() {
		if (eContainerFeatureID() != ScenarioPackage.SCENARIO_END_POINT__SCENARIO_DEF) return null;
		return (ScenarioDef)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScenarioDef(ScenarioDef newScenarioDef, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newScenarioDef, ScenarioPackage.SCENARIO_END_POINT__SCENARIO_DEF, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScenarioDef(ScenarioDef newScenarioDef) {
		if (newScenarioDef != eInternalContainer() || (eContainerFeatureID() != ScenarioPackage.SCENARIO_END_POINT__SCENARIO_DEF && newScenarioDef != null)) {
			if (EcoreUtil.isAncestor(this, newScenarioDef))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newScenarioDef != null)
				msgs = ((InternalEObject)newScenarioDef).eInverseAdd(this, ScenarioPackage.SCENARIO_DEF__END_POINTS, ScenarioDef.class, msgs);
			msgs = basicSetScenarioDef(newScenarioDef, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.SCENARIO_END_POINT__SCENARIO_DEF, newScenarioDef, newScenarioDef));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EndPoint getEndPoint() {
		if (endPoint != null && endPoint.eIsProxy()) {
			InternalEObject oldEndPoint = (InternalEObject)endPoint;
			endPoint = (EndPoint)eResolveProxy(oldEndPoint);
			if (endPoint != oldEndPoint) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScenarioPackage.SCENARIO_END_POINT__END_POINT, oldEndPoint, endPoint));
			}
		}
		return endPoint;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EndPoint basicGetEndPoint() {
		return endPoint;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEndPoint(EndPoint newEndPoint, NotificationChain msgs) {
		EndPoint oldEndPoint = endPoint;
		endPoint = newEndPoint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScenarioPackage.SCENARIO_END_POINT__END_POINT, oldEndPoint, newEndPoint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndPoint(EndPoint newEndPoint) {
		if (newEndPoint != endPoint) {
			NotificationChain msgs = null;
			if (endPoint != null)
				msgs = ((InternalEObject)endPoint).eInverseRemove(this, MapPackage.END_POINT__SCENARIO_END_POINTS, EndPoint.class, msgs);
			if (newEndPoint != null)
				msgs = ((InternalEObject)newEndPoint).eInverseAdd(this, MapPackage.END_POINT__SCENARIO_END_POINTS, EndPoint.class, msgs);
			msgs = basicSetEndPoint(newEndPoint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.SCENARIO_END_POINT__END_POINT, newEndPoint, newEndPoint));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ScenarioPackage.SCENARIO_END_POINT__SCENARIO_DEF:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetScenarioDef((ScenarioDef)otherEnd, msgs);
			case ScenarioPackage.SCENARIO_END_POINT__END_POINT:
				if (endPoint != null)
					msgs = ((InternalEObject)endPoint).eInverseRemove(this, MapPackage.END_POINT__SCENARIO_END_POINTS, EndPoint.class, msgs);
				return basicSetEndPoint((EndPoint)otherEnd, msgs);
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
			case ScenarioPackage.SCENARIO_END_POINT__SCENARIO_DEF:
				return basicSetScenarioDef(null, msgs);
			case ScenarioPackage.SCENARIO_END_POINT__END_POINT:
				return basicSetEndPoint(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case ScenarioPackage.SCENARIO_END_POINT__SCENARIO_DEF:
				return eInternalContainer().eInverseRemove(this, ScenarioPackage.SCENARIO_DEF__END_POINTS, ScenarioDef.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ScenarioPackage.SCENARIO_END_POINT__ENABLED:
				return isEnabled() ? Boolean.TRUE : Boolean.FALSE;
			case ScenarioPackage.SCENARIO_END_POINT__MANDATORY:
				return isMandatory() ? Boolean.TRUE : Boolean.FALSE;
			case ScenarioPackage.SCENARIO_END_POINT__SCENARIO_DEF:
				return getScenarioDef();
			case ScenarioPackage.SCENARIO_END_POINT__END_POINT:
				if (resolve) return getEndPoint();
				return basicGetEndPoint();
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
			case ScenarioPackage.SCENARIO_END_POINT__ENABLED:
				setEnabled(((Boolean)newValue).booleanValue());
				return;
			case ScenarioPackage.SCENARIO_END_POINT__MANDATORY:
				setMandatory(((Boolean)newValue).booleanValue());
				return;
			case ScenarioPackage.SCENARIO_END_POINT__SCENARIO_DEF:
				setScenarioDef((ScenarioDef)newValue);
				return;
			case ScenarioPackage.SCENARIO_END_POINT__END_POINT:
				setEndPoint((EndPoint)newValue);
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
			case ScenarioPackage.SCENARIO_END_POINT__ENABLED:
				setEnabled(ENABLED_EDEFAULT);
				return;
			case ScenarioPackage.SCENARIO_END_POINT__MANDATORY:
				setMandatory(MANDATORY_EDEFAULT);
				return;
			case ScenarioPackage.SCENARIO_END_POINT__SCENARIO_DEF:
				setScenarioDef((ScenarioDef)null);
				return;
			case ScenarioPackage.SCENARIO_END_POINT__END_POINT:
				setEndPoint((EndPoint)null);
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
			case ScenarioPackage.SCENARIO_END_POINT__ENABLED:
				return enabled != ENABLED_EDEFAULT;
			case ScenarioPackage.SCENARIO_END_POINT__MANDATORY:
				return mandatory != MANDATORY_EDEFAULT;
			case ScenarioPackage.SCENARIO_END_POINT__SCENARIO_DEF:
				return getScenarioDef() != null;
			case ScenarioPackage.SCENARIO_END_POINT__END_POINT:
				return endPoint != null;
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
		result.append(" (enabled: ");
		result.append(enabled);
		result.append(", mandatory: ");
		result.append(mandatory);
		result.append(')');
		return result.toString();
	}

} //ScenarioEndPointImpl