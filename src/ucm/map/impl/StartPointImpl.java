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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.ComponentRef;
import ucm.map.Condition;
import ucm.map.InBinding;
import ucm.map.MapPackage;
import ucm.map.StartPoint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Start Point</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.StartPointImpl#getInBindings <em>In Bindings</em>}</li>
 *   <li>{@link ucm.map.impl.StartPointImpl#getPrecondition <em>Precondition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StartPointImpl extends PathNodeImpl implements StartPoint {
	/**
	 * The cached value of the '{@link #getInBindings() <em>In Bindings</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInBindings()
	 * @generated
	 * @ordered
	 */
	protected EList inBindings = null;

	/**
	 * The cached value of the '{@link #getPrecondition() <em>Precondition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecondition()
	 * @generated
	 * @ordered
	 */
	protected Condition precondition = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StartPointImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MapPackage.eINSTANCE.getStartPoint();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getInBindings() {
		if (inBindings == null) {
			inBindings = new EObjectWithInverseResolvingEList(InBinding.class, this, MapPackage.START_POINT__IN_BINDINGS, MapPackage.IN_BINDING__START_POINT);
		}
		return inBindings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Condition getPrecondition() {
		return precondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPrecondition(Condition newPrecondition, NotificationChain msgs) {
		Condition oldPrecondition = precondition;
		precondition = newPrecondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.START_POINT__PRECONDITION, oldPrecondition, newPrecondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrecondition(Condition newPrecondition) {
		if (newPrecondition != precondition) {
			NotificationChain msgs = null;
			if (precondition != null)
				msgs = ((InternalEObject)precondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.START_POINT__PRECONDITION, null, msgs);
			if (newPrecondition != null)
				msgs = ((InternalEObject)newPrecondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MapPackage.START_POINT__PRECONDITION, null, msgs);
			msgs = basicSetPrecondition(newPrecondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.START_POINT__PRECONDITION, newPrecondition, newPrecondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case MapPackage.START_POINT__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
				case MapPackage.START_POINT__SUCC:
					return ((InternalEList)getSucc()).basicAdd(otherEnd, msgs);
				case MapPackage.START_POINT__PRED:
					return ((InternalEList)getPred()).basicAdd(otherEnd, msgs);
				case MapPackage.START_POINT__COMP_REF:
					if (compRef != null)
						msgs = ((InternalEObject)compRef).eInverseRemove(this, MapPackage.COMPONENT_REF__PATH_NODES, ComponentRef.class, msgs);
					return basicSetCompRef((ComponentRef)otherEnd, msgs);
				case MapPackage.START_POINT__IN_BINDINGS:
					return ((InternalEList)getInBindings()).basicAdd(otherEnd, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case MapPackage.START_POINT__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
				case MapPackage.START_POINT__SUCC:
					return ((InternalEList)getSucc()).basicRemove(otherEnd, msgs);
				case MapPackage.START_POINT__PRED:
					return ((InternalEList)getPred()).basicRemove(otherEnd, msgs);
				case MapPackage.START_POINT__COMP_REF:
					return basicSetCompRef(null, msgs);
				case MapPackage.START_POINT__IN_BINDINGS:
					return ((InternalEList)getInBindings()).basicRemove(otherEnd, msgs);
				case MapPackage.START_POINT__PRECONDITION:
					return basicSetPrecondition(null, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MapPackage.START_POINT__URN_LINKS:
				return getUrnLinks();
			case MapPackage.START_POINT__ID:
				return getId();
			case MapPackage.START_POINT__NAME:
				return getName();
			case MapPackage.START_POINT__DESCRIPTION:
				return getDescription();
			case MapPackage.START_POINT__X:
				return new Integer(getX());
			case MapPackage.START_POINT__Y:
				return new Integer(getY());
			case MapPackage.START_POINT__LABEL_X:
				return new Integer(getLabelX());
			case MapPackage.START_POINT__LABEL_Y:
				return new Integer(getLabelY());
			case MapPackage.START_POINT__SUCC:
				return getSucc();
			case MapPackage.START_POINT__PRED:
				return getPred();
			case MapPackage.START_POINT__COMP_REF:
				if (resolve) return getCompRef();
				return basicGetCompRef();
			case MapPackage.START_POINT__IN_BINDINGS:
				return getInBindings();
			case MapPackage.START_POINT__PRECONDITION:
				return getPrecondition();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MapPackage.START_POINT__URN_LINKS:
				getUrnLinks().clear();
				getUrnLinks().addAll((Collection)newValue);
				return;
			case MapPackage.START_POINT__ID:
				setId((String)newValue);
				return;
			case MapPackage.START_POINT__NAME:
				setName((String)newValue);
				return;
			case MapPackage.START_POINT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case MapPackage.START_POINT__X:
				setX(((Integer)newValue).intValue());
				return;
			case MapPackage.START_POINT__Y:
				setY(((Integer)newValue).intValue());
				return;
			case MapPackage.START_POINT__LABEL_X:
				setLabelX(((Integer)newValue).intValue());
				return;
			case MapPackage.START_POINT__LABEL_Y:
				setLabelY(((Integer)newValue).intValue());
				return;
			case MapPackage.START_POINT__SUCC:
				getSucc().clear();
				getSucc().addAll((Collection)newValue);
				return;
			case MapPackage.START_POINT__PRED:
				getPred().clear();
				getPred().addAll((Collection)newValue);
				return;
			case MapPackage.START_POINT__COMP_REF:
				setCompRef((ComponentRef)newValue);
				return;
			case MapPackage.START_POINT__IN_BINDINGS:
				getInBindings().clear();
				getInBindings().addAll((Collection)newValue);
				return;
			case MapPackage.START_POINT__PRECONDITION:
				setPrecondition((Condition)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MapPackage.START_POINT__URN_LINKS:
				getUrnLinks().clear();
				return;
			case MapPackage.START_POINT__ID:
				setId(ID_EDEFAULT);
				return;
			case MapPackage.START_POINT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case MapPackage.START_POINT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case MapPackage.START_POINT__X:
				setX(X_EDEFAULT);
				return;
			case MapPackage.START_POINT__Y:
				setY(Y_EDEFAULT);
				return;
			case MapPackage.START_POINT__LABEL_X:
				setLabelX(LABEL_X_EDEFAULT);
				return;
			case MapPackage.START_POINT__LABEL_Y:
				setLabelY(LABEL_Y_EDEFAULT);
				return;
			case MapPackage.START_POINT__SUCC:
				getSucc().clear();
				return;
			case MapPackage.START_POINT__PRED:
				getPred().clear();
				return;
			case MapPackage.START_POINT__COMP_REF:
				setCompRef((ComponentRef)null);
				return;
			case MapPackage.START_POINT__IN_BINDINGS:
				getInBindings().clear();
				return;
			case MapPackage.START_POINT__PRECONDITION:
				setPrecondition((Condition)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MapPackage.START_POINT__URN_LINKS:
				return urnLinks != null && !urnLinks.isEmpty();
			case MapPackage.START_POINT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case MapPackage.START_POINT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case MapPackage.START_POINT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case MapPackage.START_POINT__X:
				return x != X_EDEFAULT;
			case MapPackage.START_POINT__Y:
				return y != Y_EDEFAULT;
			case MapPackage.START_POINT__LABEL_X:
				return labelX != LABEL_X_EDEFAULT;
			case MapPackage.START_POINT__LABEL_Y:
				return labelY != LABEL_Y_EDEFAULT;
			case MapPackage.START_POINT__SUCC:
				return succ != null && !succ.isEmpty();
			case MapPackage.START_POINT__PRED:
				return pred != null && !pred.isEmpty();
			case MapPackage.START_POINT__COMP_REF:
				return compRef != null;
			case MapPackage.START_POINT__IN_BINDINGS:
				return inBindings != null && !inBindings.isEmpty();
			case MapPackage.START_POINT__PRECONDITION:
				return precondition != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //StartPointImpl
