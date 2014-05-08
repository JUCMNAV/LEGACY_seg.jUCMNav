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
import org.eclipse.emf.ecore.util.EcoreUtil;

import ucm.UCMspec;
import ucm.UcmPackage;
import ucm.scenario.EnumerationType;
import ucm.scenario.ScenarioPackage;
import ucm.scenario.Variable;
import urncore.impl.UCMmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.scenario.impl.VariableImpl#getType <em>Type</em>}</li>
 *   <li>{@link ucm.scenario.impl.VariableImpl#getUcmspec <em>Ucmspec</em>}</li>
 *   <li>{@link ucm.scenario.impl.VariableImpl#getEnumerationType <em>Enumeration Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VariableImpl extends UCMmodelElementImpl implements Variable {
    /**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
    protected static final String TYPE_EDEFAULT = "boolean";

    /**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
    protected String type = TYPE_EDEFAULT;

    /**
	 * The cached value of the '{@link #getEnumerationType() <em>Enumeration Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnumerationType()
	 * @generated
	 * @ordered
	 */
	protected EnumerationType enumerationType;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected VariableImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return ScenarioPackage.Literals.VARIABLE;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getType() {
		return type;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.VARIABLE__TYPE, oldType, type));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public UCMspec getUcmspec() {
		if (eContainerFeatureID() != ScenarioPackage.VARIABLE__UCMSPEC) return null;
		return (UCMspec)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUcmspec(UCMspec newUcmspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newUcmspec, ScenarioPackage.VARIABLE__UCMSPEC, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUcmspec(UCMspec newUcmspec) {
		if (newUcmspec != eInternalContainer() || (eContainerFeatureID() != ScenarioPackage.VARIABLE__UCMSPEC && newUcmspec != null)) {
			if (EcoreUtil.isAncestor(this, newUcmspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUcmspec != null)
				msgs = ((InternalEObject)newUcmspec).eInverseAdd(this, UcmPackage.UC_MSPEC__VARIABLES, UCMspec.class, msgs);
			msgs = basicSetUcmspec(newUcmspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.VARIABLE__UCMSPEC, newUcmspec, newUcmspec));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumerationType getEnumerationType() {
		if (enumerationType != null && enumerationType.eIsProxy()) {
			InternalEObject oldEnumerationType = (InternalEObject)enumerationType;
			enumerationType = (EnumerationType)eResolveProxy(oldEnumerationType);
			if (enumerationType != oldEnumerationType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScenarioPackage.VARIABLE__ENUMERATION_TYPE, oldEnumerationType, enumerationType));
			}
		}
		return enumerationType;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumerationType basicGetEnumerationType() {
		return enumerationType;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEnumerationType(EnumerationType newEnumerationType, NotificationChain msgs) {
		EnumerationType oldEnumerationType = enumerationType;
		enumerationType = newEnumerationType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScenarioPackage.VARIABLE__ENUMERATION_TYPE, oldEnumerationType, newEnumerationType);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnumerationType(EnumerationType newEnumerationType) {
		if (newEnumerationType != enumerationType) {
			NotificationChain msgs = null;
			if (enumerationType != null)
				msgs = ((InternalEObject)enumerationType).eInverseRemove(this, ScenarioPackage.ENUMERATION_TYPE__INSTANCES, EnumerationType.class, msgs);
			if (newEnumerationType != null)
				msgs = ((InternalEObject)newEnumerationType).eInverseAdd(this, ScenarioPackage.ENUMERATION_TYPE__INSTANCES, EnumerationType.class, msgs);
			msgs = basicSetEnumerationType(newEnumerationType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScenarioPackage.VARIABLE__ENUMERATION_TYPE, newEnumerationType, newEnumerationType));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ScenarioPackage.VARIABLE__UCMSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetUcmspec((UCMspec)otherEnd, msgs);
			case ScenarioPackage.VARIABLE__ENUMERATION_TYPE:
				if (enumerationType != null)
					msgs = ((InternalEObject)enumerationType).eInverseRemove(this, ScenarioPackage.ENUMERATION_TYPE__INSTANCES, EnumerationType.class, msgs);
				return basicSetEnumerationType((EnumerationType)otherEnd, msgs);
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
			case ScenarioPackage.VARIABLE__UCMSPEC:
				return basicSetUcmspec(null, msgs);
			case ScenarioPackage.VARIABLE__ENUMERATION_TYPE:
				return basicSetEnumerationType(null, msgs);
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
			case ScenarioPackage.VARIABLE__UCMSPEC:
				return eInternalContainer().eInverseRemove(this, UcmPackage.UC_MSPEC__VARIABLES, UCMspec.class, msgs);
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
			case ScenarioPackage.VARIABLE__TYPE:
				return getType();
			case ScenarioPackage.VARIABLE__UCMSPEC:
				return getUcmspec();
			case ScenarioPackage.VARIABLE__ENUMERATION_TYPE:
				if (resolve) return getEnumerationType();
				return basicGetEnumerationType();
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
			case ScenarioPackage.VARIABLE__TYPE:
				setType((String)newValue);
				return;
			case ScenarioPackage.VARIABLE__UCMSPEC:
				setUcmspec((UCMspec)newValue);
				return;
			case ScenarioPackage.VARIABLE__ENUMERATION_TYPE:
				setEnumerationType((EnumerationType)newValue);
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
			case ScenarioPackage.VARIABLE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case ScenarioPackage.VARIABLE__UCMSPEC:
				setUcmspec((UCMspec)null);
				return;
			case ScenarioPackage.VARIABLE__ENUMERATION_TYPE:
				setEnumerationType((EnumerationType)null);
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
			case ScenarioPackage.VARIABLE__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case ScenarioPackage.VARIABLE__UCMSPEC:
				return getUcmspec() != null;
			case ScenarioPackage.VARIABLE__ENUMERATION_TYPE:
				return enumerationType != null;
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
		result.append(" (type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //VariableImpl
