/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import ucm.UCMspec;
import ucm.UcmPackage;
import ucm.performance.GeneralResource;
import ucm.performance.PerformancePackage;
import urncore.impl.UCMmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>General Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.performance.impl.GeneralResourceImpl#getMultiplicity <em>Multiplicity</em>}</li>
 *   <li>{@link ucm.performance.impl.GeneralResourceImpl#getSchedPolicy <em>Sched Policy</em>}</li>
 *   <li>{@link ucm.performance.impl.GeneralResourceImpl#getUcmspec <em>Ucmspec</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class GeneralResourceImpl extends UCMmodelElementImpl implements GeneralResource {
    /**
	 * The default value of the '{@link #getMultiplicity() <em>Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMultiplicity()
	 * @generated
	 * @ordered
	 */
	protected static final String MULTIPLICITY_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getMultiplicity() <em>Multiplicity</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMultiplicity()
	 * @generated
	 * @ordered
	 */
	protected String multiplicity = MULTIPLICITY_EDEFAULT;

    /**
	 * The default value of the '{@link #getSchedPolicy() <em>Sched Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchedPolicy()
	 * @generated
	 * @ordered
	 */
	protected static final String SCHED_POLICY_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getSchedPolicy() <em>Sched Policy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchedPolicy()
	 * @generated
	 * @ordered
	 */
	protected String schedPolicy = SCHED_POLICY_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected GeneralResourceImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return PerformancePackage.Literals.GENERAL_RESOURCE;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMultiplicity() {
		return multiplicity;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMultiplicity(String newMultiplicity) {
		String oldMultiplicity = multiplicity;
		multiplicity = newMultiplicity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.GENERAL_RESOURCE__MULTIPLICITY, oldMultiplicity, multiplicity));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSchedPolicy() {
		return schedPolicy;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchedPolicy(String newSchedPolicy) {
		String oldSchedPolicy = schedPolicy;
		schedPolicy = newSchedPolicy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.GENERAL_RESOURCE__SCHED_POLICY, oldSchedPolicy, schedPolicy));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public UCMspec getUcmspec() {
		if (eContainerFeatureID() != PerformancePackage.GENERAL_RESOURCE__UCMSPEC) return null;
		return (UCMspec)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUcmspec(UCMspec newUcmspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newUcmspec, PerformancePackage.GENERAL_RESOURCE__UCMSPEC, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUcmspec(UCMspec newUcmspec) {
		if (newUcmspec != eInternalContainer() || (eContainerFeatureID() != PerformancePackage.GENERAL_RESOURCE__UCMSPEC && newUcmspec != null)) {
			if (EcoreUtil.isAncestor(this, newUcmspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUcmspec != null)
				msgs = ((InternalEObject)newUcmspec).eInverseAdd(this, UcmPackage.UC_MSPEC__RESOURCES, UCMspec.class, msgs);
			msgs = basicSetUcmspec(newUcmspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.GENERAL_RESOURCE__UCMSPEC, newUcmspec, newUcmspec));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PerformancePackage.GENERAL_RESOURCE__UCMSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetUcmspec((UCMspec)otherEnd, msgs);
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
			case PerformancePackage.GENERAL_RESOURCE__UCMSPEC:
				return basicSetUcmspec(null, msgs);
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
			case PerformancePackage.GENERAL_RESOURCE__UCMSPEC:
				return eInternalContainer().eInverseRemove(this, UcmPackage.UC_MSPEC__RESOURCES, UCMspec.class, msgs);
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
			case PerformancePackage.GENERAL_RESOURCE__MULTIPLICITY:
				return getMultiplicity();
			case PerformancePackage.GENERAL_RESOURCE__SCHED_POLICY:
				return getSchedPolicy();
			case PerformancePackage.GENERAL_RESOURCE__UCMSPEC:
				return getUcmspec();
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
			case PerformancePackage.GENERAL_RESOURCE__MULTIPLICITY:
				setMultiplicity((String)newValue);
				return;
			case PerformancePackage.GENERAL_RESOURCE__SCHED_POLICY:
				setSchedPolicy((String)newValue);
				return;
			case PerformancePackage.GENERAL_RESOURCE__UCMSPEC:
				setUcmspec((UCMspec)newValue);
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
			case PerformancePackage.GENERAL_RESOURCE__MULTIPLICITY:
				setMultiplicity(MULTIPLICITY_EDEFAULT);
				return;
			case PerformancePackage.GENERAL_RESOURCE__SCHED_POLICY:
				setSchedPolicy(SCHED_POLICY_EDEFAULT);
				return;
			case PerformancePackage.GENERAL_RESOURCE__UCMSPEC:
				setUcmspec((UCMspec)null);
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
			case PerformancePackage.GENERAL_RESOURCE__MULTIPLICITY:
				return MULTIPLICITY_EDEFAULT == null ? multiplicity != null : !MULTIPLICITY_EDEFAULT.equals(multiplicity);
			case PerformancePackage.GENERAL_RESOURCE__SCHED_POLICY:
				return SCHED_POLICY_EDEFAULT == null ? schedPolicy != null : !SCHED_POLICY_EDEFAULT.equals(schedPolicy);
			case PerformancePackage.GENERAL_RESOURCE__UCMSPEC:
				return getUcmspec() != null;
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
		result.append(" (multiplicity: ");
		result.append(multiplicity);
		result.append(", schedPolicy: ");
		result.append(schedPolicy);
		result.append(')');
		return result.toString();
	}

} //GeneralResourceImpl
