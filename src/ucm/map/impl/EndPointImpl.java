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
import ucm.map.EndPoint;
import ucm.map.MapPackage;
import ucm.map.OutBinding;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>End Point</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.EndPointImpl#getOutBindings <em>Out Bindings</em>}</li>
 *   <li>{@link ucm.map.impl.EndPointImpl#getPostcondition <em>Postcondition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EndPointImpl extends PathNodeImpl implements EndPoint {
	/**
	 * The cached value of the '{@link #getOutBindings() <em>Out Bindings</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutBindings()
	 * @generated
	 * @ordered
	 */
	protected EList outBindings = null;

	/**
	 * The cached value of the '{@link #getPostcondition() <em>Postcondition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPostcondition()
	 * @generated
	 * @ordered
	 */
	protected Condition postcondition = null;

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
		return MapPackage.eINSTANCE.getEndPoint();
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
				msgs = ((InternalEObject)postcondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.END_POINT__POSTCONDITION, null, msgs);
			if (newPostcondition != null)
				msgs = ((InternalEObject)newPostcondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MapPackage.END_POINT__POSTCONDITION, null, msgs);
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case MapPackage.END_POINT__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
				case MapPackage.END_POINT__SUCC:
					return ((InternalEList)getSucc()).basicAdd(otherEnd, msgs);
				case MapPackage.END_POINT__PRED:
					return ((InternalEList)getPred()).basicAdd(otherEnd, msgs);
				case MapPackage.END_POINT__COMP_REF:
					if (compRef != null)
						msgs = ((InternalEObject)compRef).eInverseRemove(this, MapPackage.COMPONENT_REF__PATH_NODES, ComponentRef.class, msgs);
					return basicSetCompRef((ComponentRef)otherEnd, msgs);
				case MapPackage.END_POINT__OUT_BINDINGS:
					return ((InternalEList)getOutBindings()).basicAdd(otherEnd, msgs);
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
				case MapPackage.END_POINT__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
				case MapPackage.END_POINT__SUCC:
					return ((InternalEList)getSucc()).basicRemove(otherEnd, msgs);
				case MapPackage.END_POINT__PRED:
					return ((InternalEList)getPred()).basicRemove(otherEnd, msgs);
				case MapPackage.END_POINT__COMP_REF:
					return basicSetCompRef(null, msgs);
				case MapPackage.END_POINT__OUT_BINDINGS:
					return ((InternalEList)getOutBindings()).basicRemove(otherEnd, msgs);
				case MapPackage.END_POINT__POSTCONDITION:
					return basicSetPostcondition(null, msgs);
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
			case MapPackage.END_POINT__URN_LINKS:
				return getUrnLinks();
			case MapPackage.END_POINT__ID:
				return getId();
			case MapPackage.END_POINT__NAME:
				return getName();
			case MapPackage.END_POINT__DESCRIPTION:
				return getDescription();
			case MapPackage.END_POINT__X:
				return new Integer(getX());
			case MapPackage.END_POINT__Y:
				return new Integer(getY());
			case MapPackage.END_POINT__LABEL_X:
				return new Integer(getLabelX());
			case MapPackage.END_POINT__LABEL_Y:
				return new Integer(getLabelY());
			case MapPackage.END_POINT__SUCC:
				return getSucc();
			case MapPackage.END_POINT__PRED:
				return getPred();
			case MapPackage.END_POINT__COMP_REF:
				if (resolve) return getCompRef();
				return basicGetCompRef();
			case MapPackage.END_POINT__OUT_BINDINGS:
				return getOutBindings();
			case MapPackage.END_POINT__POSTCONDITION:
				return getPostcondition();
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
			case MapPackage.END_POINT__URN_LINKS:
				getUrnLinks().clear();
				getUrnLinks().addAll((Collection)newValue);
				return;
			case MapPackage.END_POINT__ID:
				setId((String)newValue);
				return;
			case MapPackage.END_POINT__NAME:
				setName((String)newValue);
				return;
			case MapPackage.END_POINT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case MapPackage.END_POINT__X:
				setX(((Integer)newValue).intValue());
				return;
			case MapPackage.END_POINT__Y:
				setY(((Integer)newValue).intValue());
				return;
			case MapPackage.END_POINT__LABEL_X:
				setLabelX(((Integer)newValue).intValue());
				return;
			case MapPackage.END_POINT__LABEL_Y:
				setLabelY(((Integer)newValue).intValue());
				return;
			case MapPackage.END_POINT__SUCC:
				getSucc().clear();
				getSucc().addAll((Collection)newValue);
				return;
			case MapPackage.END_POINT__PRED:
				getPred().clear();
				getPred().addAll((Collection)newValue);
				return;
			case MapPackage.END_POINT__COMP_REF:
				setCompRef((ComponentRef)newValue);
				return;
			case MapPackage.END_POINT__OUT_BINDINGS:
				getOutBindings().clear();
				getOutBindings().addAll((Collection)newValue);
				return;
			case MapPackage.END_POINT__POSTCONDITION:
				setPostcondition((Condition)newValue);
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
			case MapPackage.END_POINT__URN_LINKS:
				getUrnLinks().clear();
				return;
			case MapPackage.END_POINT__ID:
				setId(ID_EDEFAULT);
				return;
			case MapPackage.END_POINT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case MapPackage.END_POINT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case MapPackage.END_POINT__X:
				setX(X_EDEFAULT);
				return;
			case MapPackage.END_POINT__Y:
				setY(Y_EDEFAULT);
				return;
			case MapPackage.END_POINT__LABEL_X:
				setLabelX(LABEL_X_EDEFAULT);
				return;
			case MapPackage.END_POINT__LABEL_Y:
				setLabelY(LABEL_Y_EDEFAULT);
				return;
			case MapPackage.END_POINT__SUCC:
				getSucc().clear();
				return;
			case MapPackage.END_POINT__PRED:
				getPred().clear();
				return;
			case MapPackage.END_POINT__COMP_REF:
				setCompRef((ComponentRef)null);
				return;
			case MapPackage.END_POINT__OUT_BINDINGS:
				getOutBindings().clear();
				return;
			case MapPackage.END_POINT__POSTCONDITION:
				setPostcondition((Condition)null);
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
			case MapPackage.END_POINT__URN_LINKS:
				return urnLinks != null && !urnLinks.isEmpty();
			case MapPackage.END_POINT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case MapPackage.END_POINT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case MapPackage.END_POINT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case MapPackage.END_POINT__X:
				return x != X_EDEFAULT;
			case MapPackage.END_POINT__Y:
				return y != Y_EDEFAULT;
			case MapPackage.END_POINT__LABEL_X:
				return labelX != LABEL_X_EDEFAULT;
			case MapPackage.END_POINT__LABEL_Y:
				return labelY != LABEL_Y_EDEFAULT;
			case MapPackage.END_POINT__SUCC:
				return succ != null && !succ.isEmpty();
			case MapPackage.END_POINT__PRED:
				return pred != null && !pred.isEmpty();
			case MapPackage.END_POINT__COMP_REF:
				return compRef != null;
			case MapPackage.END_POINT__OUT_BINDINGS:
				return outBindings != null && !outBindings.isEmpty();
			case MapPackage.END_POINT__POSTCONDITION:
				return postcondition != null;
		}
		return eDynamicIsSet(eFeature);
	}

} //EndPointImpl
