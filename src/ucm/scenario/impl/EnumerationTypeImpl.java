/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.scenario.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.UCMspec;
import ucm.UcmPackage;
import ucm.scenario.EnumerationType;
import ucm.scenario.ScenarioPackage;
import ucm.scenario.Variable;
import urncore.impl.UCMmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enumeration Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.scenario.impl.EnumerationTypeImpl#getValues <em>Values</em>}</li>
 *   <li>{@link ucm.scenario.impl.EnumerationTypeImpl#getUcmspec <em>Ucmspec</em>}</li>
 *   <li>{@link ucm.scenario.impl.EnumerationTypeImpl#getInstances <em>Instances</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnumerationTypeImpl extends UCMmodelElementImpl implements EnumerationType {
    /**
	 * The default value of the '{@link #getValues() <em>Values</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValues()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUES_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getValues() <em>Values</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValues()
	 * @generated
	 * @ordered
	 */
	protected String values = VALUES_EDEFAULT;

    /**
	 * The cached value of the '{@link #getInstances() <em>Instances</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstances()
	 * @generated
	 * @ordered
	 */
	protected EList instances;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnumerationTypeImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ScenarioPackage.Literals.ENUMERATION_TYPE;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValues() {
		return values;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValues(String newValues) {
		String oldValues = values;
		values = newValues;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.ENUMERATION_TYPE__VALUES, oldValues, values));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UCMspec getUcmspec() {
		if (eContainerFeatureID() != ScenarioPackage.ENUMERATION_TYPE__UCMSPEC) return null;
		return (UCMspec)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUcmspec(UCMspec newUcmspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newUcmspec, ScenarioPackage.ENUMERATION_TYPE__UCMSPEC, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUcmspec(UCMspec newUcmspec) {
		if (newUcmspec != eInternalContainer() || (eContainerFeatureID() != ScenarioPackage.ENUMERATION_TYPE__UCMSPEC && newUcmspec != null)) {
			if (EcoreUtil.isAncestor(this, newUcmspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUcmspec != null)
				msgs = ((InternalEObject)newUcmspec).eInverseAdd(this, UcmPackage.UC_MSPEC__ENUMERATION_TYPES, UCMspec.class, msgs);
			msgs = basicSetUcmspec(newUcmspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.ENUMERATION_TYPE__UCMSPEC, newUcmspec, newUcmspec));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getInstances() {
		if (instances == null) {
			instances = new EObjectWithInverseResolvingEList(Variable.class, this, ScenarioPackage.ENUMERATION_TYPE__INSTANCES, ScenarioPackage.VARIABLE__ENUMERATION_TYPE);
		}
		return instances;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ScenarioPackage.ENUMERATION_TYPE__UCMSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetUcmspec((UCMspec)otherEnd, msgs);
			case ScenarioPackage.ENUMERATION_TYPE__INSTANCES:
				return ((InternalEList)getInstances()).basicAdd(otherEnd, msgs);
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
			case ScenarioPackage.ENUMERATION_TYPE__UCMSPEC:
				return basicSetUcmspec(null, msgs);
			case ScenarioPackage.ENUMERATION_TYPE__INSTANCES:
				return ((InternalEList)getInstances()).basicRemove(otherEnd, msgs);
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
			case ScenarioPackage.ENUMERATION_TYPE__UCMSPEC:
				return eInternalContainer().eInverseRemove(this, UcmPackage.UC_MSPEC__ENUMERATION_TYPES, UCMspec.class, msgs);
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
			case ScenarioPackage.ENUMERATION_TYPE__VALUES:
				return getValues();
			case ScenarioPackage.ENUMERATION_TYPE__UCMSPEC:
				return getUcmspec();
			case ScenarioPackage.ENUMERATION_TYPE__INSTANCES:
				return getInstances();
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
			case ScenarioPackage.ENUMERATION_TYPE__VALUES:
				setValues((String)newValue);
				return;
			case ScenarioPackage.ENUMERATION_TYPE__UCMSPEC:
				setUcmspec((UCMspec)newValue);
				return;
			case ScenarioPackage.ENUMERATION_TYPE__INSTANCES:
				getInstances().clear();
				getInstances().addAll((Collection)newValue);
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
			case ScenarioPackage.ENUMERATION_TYPE__VALUES:
				setValues(VALUES_EDEFAULT);
				return;
			case ScenarioPackage.ENUMERATION_TYPE__UCMSPEC:
				setUcmspec((UCMspec)null);
				return;
			case ScenarioPackage.ENUMERATION_TYPE__INSTANCES:
				getInstances().clear();
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
			case ScenarioPackage.ENUMERATION_TYPE__VALUES:
				return VALUES_EDEFAULT == null ? values != null : !VALUES_EDEFAULT.equals(values);
			case ScenarioPackage.ENUMERATION_TYPE__UCMSPEC:
				return getUcmspec() != null;
			case ScenarioPackage.ENUMERATION_TYPE__INSTANCES:
				return instances != null && !instances.isEmpty();
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
		result.append(" (values: ");
		result.append(values);
		result.append(')');
		return result.toString();
	}

} //EnumerationTypeImpl