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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.ComponentRef;
import ucm.map.MapPackage;

import ucm.map.impl.PathNodeImpl;

import ucm.performance.PerformancePackage;
import ucm.performance.Timestamp;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Timestamp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.performance.impl.TimestampImpl#getOrientation <em>Orientation</em>}</li>
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
		return PerformancePackage.eINSTANCE.getTimestamp();
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case PerformancePackage.TIMESTAMP__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
				case PerformancePackage.TIMESTAMP__SUCC:
					return ((InternalEList)getSucc()).basicAdd(otherEnd, msgs);
				case PerformancePackage.TIMESTAMP__PRED:
					return ((InternalEList)getPred()).basicAdd(otherEnd, msgs);
				case PerformancePackage.TIMESTAMP__COMP_REF:
					if (compRef != null)
						msgs = ((InternalEObject)compRef).eInverseRemove(this, MapPackage.COMPONENT_REF__PATH_NODES, ComponentRef.class, msgs);
					return basicSetCompRef((ComponentRef)otherEnd, msgs);
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
				case PerformancePackage.TIMESTAMP__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
				case PerformancePackage.TIMESTAMP__SUCC:
					return ((InternalEList)getSucc()).basicRemove(otherEnd, msgs);
				case PerformancePackage.TIMESTAMP__PRED:
					return ((InternalEList)getPred()).basicRemove(otherEnd, msgs);
				case PerformancePackage.TIMESTAMP__COMP_REF:
					return basicSetCompRef(null, msgs);
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
			case PerformancePackage.TIMESTAMP__URN_LINKS:
				return getUrnLinks();
			case PerformancePackage.TIMESTAMP__ID:
				return getId();
			case PerformancePackage.TIMESTAMP__NAME:
				return getName();
			case PerformancePackage.TIMESTAMP__DESCRIPTION:
				return getDescription();
			case PerformancePackage.TIMESTAMP__X:
				return new Integer(getX());
			case PerformancePackage.TIMESTAMP__Y:
				return new Integer(getY());
			case PerformancePackage.TIMESTAMP__LABEL_X:
				return new Integer(getLabelX());
			case PerformancePackage.TIMESTAMP__LABEL_Y:
				return new Integer(getLabelY());
			case PerformancePackage.TIMESTAMP__SUCC:
				return getSucc();
			case PerformancePackage.TIMESTAMP__PRED:
				return getPred();
			case PerformancePackage.TIMESTAMP__COMP_REF:
				if (resolve) return getCompRef();
				return basicGetCompRef();
			case PerformancePackage.TIMESTAMP__ORIENTATION:
				return getOrientation();
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
			case PerformancePackage.TIMESTAMP__URN_LINKS:
				getUrnLinks().clear();
				getUrnLinks().addAll((Collection)newValue);
				return;
			case PerformancePackage.TIMESTAMP__ID:
				setId((String)newValue);
				return;
			case PerformancePackage.TIMESTAMP__NAME:
				setName((String)newValue);
				return;
			case PerformancePackage.TIMESTAMP__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case PerformancePackage.TIMESTAMP__X:
				setX(((Integer)newValue).intValue());
				return;
			case PerformancePackage.TIMESTAMP__Y:
				setY(((Integer)newValue).intValue());
				return;
			case PerformancePackage.TIMESTAMP__LABEL_X:
				setLabelX(((Integer)newValue).intValue());
				return;
			case PerformancePackage.TIMESTAMP__LABEL_Y:
				setLabelY(((Integer)newValue).intValue());
				return;
			case PerformancePackage.TIMESTAMP__SUCC:
				getSucc().clear();
				getSucc().addAll((Collection)newValue);
				return;
			case PerformancePackage.TIMESTAMP__PRED:
				getPred().clear();
				getPred().addAll((Collection)newValue);
				return;
			case PerformancePackage.TIMESTAMP__COMP_REF:
				setCompRef((ComponentRef)newValue);
				return;
			case PerformancePackage.TIMESTAMP__ORIENTATION:
				setOrientation((String)newValue);
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
			case PerformancePackage.TIMESTAMP__URN_LINKS:
				getUrnLinks().clear();
				return;
			case PerformancePackage.TIMESTAMP__ID:
				setId(ID_EDEFAULT);
				return;
			case PerformancePackage.TIMESTAMP__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PerformancePackage.TIMESTAMP__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case PerformancePackage.TIMESTAMP__X:
				setX(X_EDEFAULT);
				return;
			case PerformancePackage.TIMESTAMP__Y:
				setY(Y_EDEFAULT);
				return;
			case PerformancePackage.TIMESTAMP__LABEL_X:
				setLabelX(LABEL_X_EDEFAULT);
				return;
			case PerformancePackage.TIMESTAMP__LABEL_Y:
				setLabelY(LABEL_Y_EDEFAULT);
				return;
			case PerformancePackage.TIMESTAMP__SUCC:
				getSucc().clear();
				return;
			case PerformancePackage.TIMESTAMP__PRED:
				getPred().clear();
				return;
			case PerformancePackage.TIMESTAMP__COMP_REF:
				setCompRef((ComponentRef)null);
				return;
			case PerformancePackage.TIMESTAMP__ORIENTATION:
				setOrientation(ORIENTATION_EDEFAULT);
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
			case PerformancePackage.TIMESTAMP__URN_LINKS:
				return urnLinks != null && !urnLinks.isEmpty();
			case PerformancePackage.TIMESTAMP__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case PerformancePackage.TIMESTAMP__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PerformancePackage.TIMESTAMP__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case PerformancePackage.TIMESTAMP__X:
				return x != X_EDEFAULT;
			case PerformancePackage.TIMESTAMP__Y:
				return y != Y_EDEFAULT;
			case PerformancePackage.TIMESTAMP__LABEL_X:
				return labelX != LABEL_X_EDEFAULT;
			case PerformancePackage.TIMESTAMP__LABEL_Y:
				return labelY != LABEL_Y_EDEFAULT;
			case PerformancePackage.TIMESTAMP__SUCC:
				return succ != null && !succ.isEmpty();
			case PerformancePackage.TIMESTAMP__PRED:
				return pred != null && !pred.isEmpty();
			case PerformancePackage.TIMESTAMP__COMP_REF:
				return compRef != null;
			case PerformancePackage.TIMESTAMP__ORIENTATION:
				return ORIENTATION_EDEFAULT == null ? orientation != null : !ORIENTATION_EDEFAULT.equals(orientation);
		}
		return eDynamicIsSet(eFeature);
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
