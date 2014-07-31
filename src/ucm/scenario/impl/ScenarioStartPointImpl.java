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
import ucm.map.MapPackage;
import ucm.map.StartPoint;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioPackage;
import ucm.scenario.ScenarioStartPoint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Start Point</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.scenario.impl.ScenarioStartPointImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioStartPointImpl#getScenarioDef <em>Scenario Def</em>}</li>
 *   <li>{@link ucm.scenario.impl.ScenarioStartPointImpl#getStartPoint <em>Start Point</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScenarioStartPointImpl extends MinimalEObjectImpl.Container implements ScenarioStartPoint {
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
	 * The cached value of the '{@link #getStartPoint() <em>Start Point</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartPoint()
	 * @generated
	 * @ordered
	 */
	protected StartPoint startPoint;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScenarioStartPointImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ScenarioPackage.Literals.SCENARIO_START_POINT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.SCENARIO_START_POINT__ENABLED, oldEnabled, enabled));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScenarioDef getScenarioDef() {
		if (eContainerFeatureID() != ScenarioPackage.SCENARIO_START_POINT__SCENARIO_DEF) return null;
		return (ScenarioDef)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScenarioDef(ScenarioDef newScenarioDef, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newScenarioDef, ScenarioPackage.SCENARIO_START_POINT__SCENARIO_DEF, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScenarioDef(ScenarioDef newScenarioDef) {
		if (newScenarioDef != eInternalContainer() || (eContainerFeatureID() != ScenarioPackage.SCENARIO_START_POINT__SCENARIO_DEF && newScenarioDef != null)) {
			if (EcoreUtil.isAncestor(this, newScenarioDef))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newScenarioDef != null)
				msgs = ((InternalEObject)newScenarioDef).eInverseAdd(this, ScenarioPackage.SCENARIO_DEF__START_POINTS, ScenarioDef.class, msgs);
			msgs = basicSetScenarioDef(newScenarioDef, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.SCENARIO_START_POINT__SCENARIO_DEF, newScenarioDef, newScenarioDef));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartPoint getStartPoint() {
		if (startPoint != null && startPoint.eIsProxy()) {
			InternalEObject oldStartPoint = (InternalEObject)startPoint;
			startPoint = (StartPoint)eResolveProxy(oldStartPoint);
			if (startPoint != oldStartPoint) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScenarioPackage.SCENARIO_START_POINT__START_POINT, oldStartPoint, startPoint));
			}
		}
		return startPoint;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartPoint basicGetStartPoint() {
		return startPoint;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStartPoint(StartPoint newStartPoint, NotificationChain msgs) {
		StartPoint oldStartPoint = startPoint;
		startPoint = newStartPoint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScenarioPackage.SCENARIO_START_POINT__START_POINT, oldStartPoint, newStartPoint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartPoint(StartPoint newStartPoint) {
		if (newStartPoint != startPoint) {
			NotificationChain msgs = null;
			if (startPoint != null)
				msgs = ((InternalEObject)startPoint).eInverseRemove(this, MapPackage.START_POINT__SCENARIO_START_POINTS, StartPoint.class, msgs);
			if (newStartPoint != null)
				msgs = ((InternalEObject)newStartPoint).eInverseAdd(this, MapPackage.START_POINT__SCENARIO_START_POINTS, StartPoint.class, msgs);
			msgs = basicSetStartPoint(newStartPoint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.SCENARIO_START_POINT__START_POINT, newStartPoint, newStartPoint));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ScenarioPackage.SCENARIO_START_POINT__SCENARIO_DEF:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetScenarioDef((ScenarioDef)otherEnd, msgs);
			case ScenarioPackage.SCENARIO_START_POINT__START_POINT:
				if (startPoint != null)
					msgs = ((InternalEObject)startPoint).eInverseRemove(this, MapPackage.START_POINT__SCENARIO_START_POINTS, StartPoint.class, msgs);
				return basicSetStartPoint((StartPoint)otherEnd, msgs);
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
			case ScenarioPackage.SCENARIO_START_POINT__SCENARIO_DEF:
				return basicSetScenarioDef(null, msgs);
			case ScenarioPackage.SCENARIO_START_POINT__START_POINT:
				return basicSetStartPoint(null, msgs);
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
			case ScenarioPackage.SCENARIO_START_POINT__SCENARIO_DEF:
				return eInternalContainer().eInverseRemove(this, ScenarioPackage.SCENARIO_DEF__START_POINTS, ScenarioDef.class, msgs);
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
			case ScenarioPackage.SCENARIO_START_POINT__ENABLED:
				return isEnabled() ? Boolean.TRUE : Boolean.FALSE;
			case ScenarioPackage.SCENARIO_START_POINT__SCENARIO_DEF:
				return getScenarioDef();
			case ScenarioPackage.SCENARIO_START_POINT__START_POINT:
				if (resolve) return getStartPoint();
				return basicGetStartPoint();
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
			case ScenarioPackage.SCENARIO_START_POINT__ENABLED:
				setEnabled(((Boolean)newValue).booleanValue());
				return;
			case ScenarioPackage.SCENARIO_START_POINT__SCENARIO_DEF:
				setScenarioDef((ScenarioDef)newValue);
				return;
			case ScenarioPackage.SCENARIO_START_POINT__START_POINT:
				setStartPoint((StartPoint)newValue);
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
			case ScenarioPackage.SCENARIO_START_POINT__ENABLED:
				setEnabled(ENABLED_EDEFAULT);
				return;
			case ScenarioPackage.SCENARIO_START_POINT__SCENARIO_DEF:
				setScenarioDef((ScenarioDef)null);
				return;
			case ScenarioPackage.SCENARIO_START_POINT__START_POINT:
				setStartPoint((StartPoint)null);
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
			case ScenarioPackage.SCENARIO_START_POINT__ENABLED:
				return enabled != ENABLED_EDEFAULT;
			case ScenarioPackage.SCENARIO_START_POINT__SCENARIO_DEF:
				return getScenarioDef() != null;
			case ScenarioPackage.SCENARIO_START_POINT__START_POINT:
				return startPoint != null;
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
		result.append(')');
		return result.toString();
	}

} //ScenarioStartPointImpl