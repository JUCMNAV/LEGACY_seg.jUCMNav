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
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.EndPoint;
import ucm.map.MapPackage;
import ucm.map.OutBinding;
import ucm.scenario.ScenarioEndPoint;
import ucm.scenario.ScenarioPackage;
import urncore.Condition;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>End Point</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.EndPointImpl#isLocal <em>Local</em>}</li>
 *   <li>{@link ucm.map.impl.EndPointImpl#getOutBindings <em>Out Bindings</em>}</li>
 *   <li>{@link ucm.map.impl.EndPointImpl#getPostcondition <em>Postcondition</em>}</li>
 *   <li>{@link ucm.map.impl.EndPointImpl#getScenarioEndPoints <em>Scenario End Points</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EndPointImpl extends PathNodeImpl implements EndPoint {
    /**
	 * The default value of the '{@link #isLocal() <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isLocal()
	 * @generated
	 * @ordered
	 */
    protected static final boolean LOCAL_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isLocal() <em>Local</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isLocal()
	 * @generated
	 * @ordered
	 */
    protected boolean local = LOCAL_EDEFAULT;

    /**
	 * The cached value of the '{@link #getOutBindings() <em>Out Bindings</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getOutBindings()
	 * @generated
	 * @ordered
	 */
    protected EList outBindings;

    /**
	 * The cached value of the '{@link #getPostcondition() <em>Postcondition</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPostcondition()
	 * @generated
	 * @ordered
	 */
    protected Condition postcondition;

    /**
	 * The cached value of the '{@link #getScenarioEndPoints() <em>Scenario End Points</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScenarioEndPoints()
	 * @generated
	 * @ordered
	 */
	protected EList scenarioEndPoints;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EndPointImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return MapPackage.Literals.END_POINT;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isLocal() {
		return local;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setLocal(boolean newLocal) {
		boolean oldLocal = local;
		local = newLocal;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.END_POINT__LOCAL, oldLocal, local));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getOutBindings() {
		if (outBindings == null) {
			outBindings = new EObjectWithInverseResolvingEList(OutBinding.class, this, MapPackage.END_POINT__OUT_BINDINGS, MapPackage.OUT_BINDING__END_POINT);
		}
		return outBindings;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Condition getPostcondition() {
		return postcondition;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetPostcondition(Condition newPostcondition, NotificationChain msgs) {
		Condition oldPostcondition = postcondition;
		postcondition = newPostcondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.END_POINT__POSTCONDITION, oldPostcondition, newPostcondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPostcondition(Condition newPostcondition) {
		if (newPostcondition != postcondition) {
			NotificationChain msgs = null;
			if (postcondition != null)
				msgs = ((InternalEObject)postcondition).eInverseRemove(this, UrncorePackage.CONDITION__END_POINT, Condition.class, msgs);
			if (newPostcondition != null)
				msgs = ((InternalEObject)newPostcondition).eInverseAdd(this, UrncorePackage.CONDITION__END_POINT, Condition.class, msgs);
			msgs = basicSetPostcondition(newPostcondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.END_POINT__POSTCONDITION, newPostcondition, newPostcondition));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getScenarioEndPoints() {
		if (scenarioEndPoints == null) {
			scenarioEndPoints = new EObjectWithInverseResolvingEList(ScenarioEndPoint.class, this, MapPackage.END_POINT__SCENARIO_END_POINTS, ScenarioPackage.SCENARIO_END_POINT__END_POINT);
		}
		return scenarioEndPoints;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MapPackage.END_POINT__OUT_BINDINGS:
				return ((InternalEList)getOutBindings()).basicAdd(otherEnd, msgs);
			case MapPackage.END_POINT__POSTCONDITION:
				if (postcondition != null)
					msgs = ((InternalEObject)postcondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.END_POINT__POSTCONDITION, null, msgs);
				return basicSetPostcondition((Condition)otherEnd, msgs);
			case MapPackage.END_POINT__SCENARIO_END_POINTS:
				return ((InternalEList)getScenarioEndPoints()).basicAdd(otherEnd, msgs);
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
			case MapPackage.END_POINT__OUT_BINDINGS:
				return ((InternalEList)getOutBindings()).basicRemove(otherEnd, msgs);
			case MapPackage.END_POINT__POSTCONDITION:
				return basicSetPostcondition(null, msgs);
			case MapPackage.END_POINT__SCENARIO_END_POINTS:
				return ((InternalEList)getScenarioEndPoints()).basicRemove(otherEnd, msgs);
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
			case MapPackage.END_POINT__LOCAL:
				return isLocal() ? Boolean.TRUE : Boolean.FALSE;
			case MapPackage.END_POINT__OUT_BINDINGS:
				return getOutBindings();
			case MapPackage.END_POINT__POSTCONDITION:
				return getPostcondition();
			case MapPackage.END_POINT__SCENARIO_END_POINTS:
				return getScenarioEndPoints();
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
			case MapPackage.END_POINT__LOCAL:
				setLocal(((Boolean)newValue).booleanValue());
				return;
			case MapPackage.END_POINT__OUT_BINDINGS:
				getOutBindings().clear();
				getOutBindings().addAll((Collection)newValue);
				return;
			case MapPackage.END_POINT__POSTCONDITION:
				setPostcondition((Condition)newValue);
				return;
			case MapPackage.END_POINT__SCENARIO_END_POINTS:
				getScenarioEndPoints().clear();
				getScenarioEndPoints().addAll((Collection)newValue);
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
			case MapPackage.END_POINT__LOCAL:
				setLocal(LOCAL_EDEFAULT);
				return;
			case MapPackage.END_POINT__OUT_BINDINGS:
				getOutBindings().clear();
				return;
			case MapPackage.END_POINT__POSTCONDITION:
				setPostcondition((Condition)null);
				return;
			case MapPackage.END_POINT__SCENARIO_END_POINTS:
				getScenarioEndPoints().clear();
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
			case MapPackage.END_POINT__LOCAL:
				return local != LOCAL_EDEFAULT;
			case MapPackage.END_POINT__OUT_BINDINGS:
				return outBindings != null && !outBindings.isEmpty();
			case MapPackage.END_POINT__POSTCONDITION:
				return postcondition != null;
			case MapPackage.END_POINT__SCENARIO_END_POINTS:
				return scenarioEndPoints != null && !scenarioEndPoints.isEmpty();
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
		result.append(" (local: ");
		result.append(local);
		result.append(')');
		return result.toString();
	}

} //EndPointImpl
