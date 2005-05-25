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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.ComponentRef;
import ucm.map.MapPackage;
import ucm.map.PathGraph;
import ucm.map.RespRef;

import urncore.NodeLabel;
import urncore.Responsibility;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resp Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.RespRefImpl#getRepetitionCount <em>Repetition Count</em>}</li>
 *   <li>{@link ucm.map.impl.RespRefImpl#getRespDef <em>Resp Def</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RespRefImpl extends PathNodeImpl implements RespRef {
	/**
	 * The default value of the '{@link #getRepetitionCount() <em>Repetition Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepetitionCount()
	 * @generated
	 * @ordered
	 */
	protected static final int REPETITION_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getRepetitionCount() <em>Repetition Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepetitionCount()
	 * @generated
	 * @ordered
	 */
	protected int repetitionCount = REPETITION_COUNT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRespDef() <em>Resp Def</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRespDef()
	 * @generated
	 * @ordered
	 */
	protected Responsibility respDef = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RespRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MapPackage.eINSTANCE.getRespRef();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getRepetitionCount() {
		return repetitionCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRepetitionCount(int newRepetitionCount) {
		int oldRepetitionCount = repetitionCount;
		repetitionCount = newRepetitionCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.RESP_REF__REPETITION_COUNT, oldRepetitionCount, repetitionCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Responsibility getRespDef() {
		if (respDef != null && respDef.eIsProxy()) {
			Responsibility oldRespDef = respDef;
			respDef = (Responsibility)eResolveProxy((InternalEObject)respDef);
			if (respDef != oldRespDef) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.RESP_REF__RESP_DEF, oldRespDef, respDef));
			}
		}
		return respDef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Responsibility basicGetRespDef() {
		return respDef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRespDef(Responsibility newRespDef, NotificationChain msgs) {
		Responsibility oldRespDef = respDef;
		respDef = newRespDef;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.RESP_REF__RESP_DEF, oldRespDef, newRespDef);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRespDef(Responsibility newRespDef) {
		if (newRespDef != respDef) {
			NotificationChain msgs = null;
			if (respDef != null)
				msgs = ((InternalEObject)respDef).eInverseRemove(this, UrncorePackage.RESPONSIBILITY__RESP_REFS, Responsibility.class, msgs);
			if (newRespDef != null)
				msgs = ((InternalEObject)newRespDef).eInverseAdd(this, UrncorePackage.RESPONSIBILITY__RESP_REFS, Responsibility.class, msgs);
			msgs = basicSetRespDef(newRespDef, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.RESP_REF__RESP_DEF, newRespDef, newRespDef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case MapPackage.RESP_REF__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
				case MapPackage.RESP_REF__PATH_GRAPH:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, MapPackage.RESP_REF__PATH_GRAPH, msgs);
				case MapPackage.RESP_REF__SUCC:
					return ((InternalEList)getSucc()).basicAdd(otherEnd, msgs);
				case MapPackage.RESP_REF__PRED:
					return ((InternalEList)getPred()).basicAdd(otherEnd, msgs);
				case MapPackage.RESP_REF__COMP_REF:
					if (compRef != null)
						msgs = ((InternalEObject)compRef).eInverseRemove(this, MapPackage.COMPONENT_REF__PATH_NODES, ComponentRef.class, msgs);
					return basicSetCompRef((ComponentRef)otherEnd, msgs);
				case MapPackage.RESP_REF__LABEL:
					if (label != null)
						msgs = ((InternalEObject)label).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.RESP_REF__LABEL, null, msgs);
					return basicSetLabel((NodeLabel)otherEnd, msgs);
				case MapPackage.RESP_REF__RESP_DEF:
					if (respDef != null)
						msgs = ((InternalEObject)respDef).eInverseRemove(this, UrncorePackage.RESPONSIBILITY__RESP_REFS, Responsibility.class, msgs);
					return basicSetRespDef((Responsibility)otherEnd, msgs);
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
				case MapPackage.RESP_REF__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
				case MapPackage.RESP_REF__PATH_GRAPH:
					return eBasicSetContainer(null, MapPackage.RESP_REF__PATH_GRAPH, msgs);
				case MapPackage.RESP_REF__SUCC:
					return ((InternalEList)getSucc()).basicRemove(otherEnd, msgs);
				case MapPackage.RESP_REF__PRED:
					return ((InternalEList)getPred()).basicRemove(otherEnd, msgs);
				case MapPackage.RESP_REF__COMP_REF:
					return basicSetCompRef(null, msgs);
				case MapPackage.RESP_REF__LABEL:
					return basicSetLabel(null, msgs);
				case MapPackage.RESP_REF__RESP_DEF:
					return basicSetRespDef(null, msgs);
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
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case MapPackage.RESP_REF__PATH_GRAPH:
					return ((InternalEObject)eContainer).eInverseRemove(this, MapPackage.PATH_GRAPH__PATH_NODES, PathGraph.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MapPackage.RESP_REF__URN_LINKS:
				return getUrnLinks();
			case MapPackage.RESP_REF__ID:
				return getId();
			case MapPackage.RESP_REF__NAME:
				return getName();
			case MapPackage.RESP_REF__DESCRIPTION:
				return getDescription();
			case MapPackage.RESP_REF__X:
				return new Integer(getX());
			case MapPackage.RESP_REF__Y:
				return new Integer(getY());
			case MapPackage.RESP_REF__PATH_GRAPH:
				return getPathGraph();
			case MapPackage.RESP_REF__SUCC:
				return getSucc();
			case MapPackage.RESP_REF__PRED:
				return getPred();
			case MapPackage.RESP_REF__COMP_REF:
				if (resolve) return getCompRef();
				return basicGetCompRef();
			case MapPackage.RESP_REF__LABEL:
				return getLabel();
			case MapPackage.RESP_REF__REPETITION_COUNT:
				return new Integer(getRepetitionCount());
			case MapPackage.RESP_REF__RESP_DEF:
				if (resolve) return getRespDef();
				return basicGetRespDef();
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
			case MapPackage.RESP_REF__URN_LINKS:
				getUrnLinks().clear();
				getUrnLinks().addAll((Collection)newValue);
				return;
			case MapPackage.RESP_REF__ID:
				setId((String)newValue);
				return;
			case MapPackage.RESP_REF__NAME:
				setName((String)newValue);
				return;
			case MapPackage.RESP_REF__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case MapPackage.RESP_REF__X:
				setX(((Integer)newValue).intValue());
				return;
			case MapPackage.RESP_REF__Y:
				setY(((Integer)newValue).intValue());
				return;
			case MapPackage.RESP_REF__PATH_GRAPH:
				setPathGraph((PathGraph)newValue);
				return;
			case MapPackage.RESP_REF__SUCC:
				getSucc().clear();
				getSucc().addAll((Collection)newValue);
				return;
			case MapPackage.RESP_REF__PRED:
				getPred().clear();
				getPred().addAll((Collection)newValue);
				return;
			case MapPackage.RESP_REF__COMP_REF:
				setCompRef((ComponentRef)newValue);
				return;
			case MapPackage.RESP_REF__LABEL:
				setLabel((NodeLabel)newValue);
				return;
			case MapPackage.RESP_REF__REPETITION_COUNT:
				setRepetitionCount(((Integer)newValue).intValue());
				return;
			case MapPackage.RESP_REF__RESP_DEF:
				setRespDef((Responsibility)newValue);
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
			case MapPackage.RESP_REF__URN_LINKS:
				getUrnLinks().clear();
				return;
			case MapPackage.RESP_REF__ID:
				setId(ID_EDEFAULT);
				return;
			case MapPackage.RESP_REF__NAME:
				setName(NAME_EDEFAULT);
				return;
			case MapPackage.RESP_REF__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case MapPackage.RESP_REF__X:
				setX(X_EDEFAULT);
				return;
			case MapPackage.RESP_REF__Y:
				setY(Y_EDEFAULT);
				return;
			case MapPackage.RESP_REF__PATH_GRAPH:
				setPathGraph((PathGraph)null);
				return;
			case MapPackage.RESP_REF__SUCC:
				getSucc().clear();
				return;
			case MapPackage.RESP_REF__PRED:
				getPred().clear();
				return;
			case MapPackage.RESP_REF__COMP_REF:
				setCompRef((ComponentRef)null);
				return;
			case MapPackage.RESP_REF__LABEL:
				setLabel((NodeLabel)null);
				return;
			case MapPackage.RESP_REF__REPETITION_COUNT:
				setRepetitionCount(REPETITION_COUNT_EDEFAULT);
				return;
			case MapPackage.RESP_REF__RESP_DEF:
				setRespDef((Responsibility)null);
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
			case MapPackage.RESP_REF__URN_LINKS:
				return urnLinks != null && !urnLinks.isEmpty();
			case MapPackage.RESP_REF__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case MapPackage.RESP_REF__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case MapPackage.RESP_REF__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case MapPackage.RESP_REF__X:
				return x != X_EDEFAULT;
			case MapPackage.RESP_REF__Y:
				return y != Y_EDEFAULT;
			case MapPackage.RESP_REF__PATH_GRAPH:
				return getPathGraph() != null;
			case MapPackage.RESP_REF__SUCC:
				return succ != null && !succ.isEmpty();
			case MapPackage.RESP_REF__PRED:
				return pred != null && !pred.isEmpty();
			case MapPackage.RESP_REF__COMP_REF:
				return compRef != null;
			case MapPackage.RESP_REF__LABEL:
				return label != null;
			case MapPackage.RESP_REF__REPETITION_COUNT:
				return repetitionCount != REPETITION_COUNT_EDEFAULT;
			case MapPackage.RESP_REF__RESP_DEF:
				return respDef != null;
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
		result.append(" (repetitionCount: ");
		result.append(repetitionCount);
		result.append(')');
		return result.toString();
	}

} //RespRefImpl
