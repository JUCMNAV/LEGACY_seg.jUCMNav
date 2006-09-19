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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.UCMspec;
import ucm.UcmPackage;
import ucm.performance.PerfAttribute;
import ucm.performance.PerfMeasure;
import ucm.performance.PerfValue;
import ucm.performance.PerformancePackage;
import ucm.performance.Workload;
import urncore.impl.UCMmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Perf Measure</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.performance.impl.PerfMeasureImpl#getMeasure <em>Measure</em>}</li>
 *   <li>{@link ucm.performance.impl.PerfMeasureImpl#getUcmspec <em>Ucmspec</em>}</li>
 *   <li>{@link ucm.performance.impl.PerfMeasureImpl#getPerfValues <em>Perf Values</em>}</li>
 *   <li>{@link ucm.performance.impl.PerfMeasureImpl#getDuration <em>Duration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PerfMeasureImpl extends UCMmodelElementImpl implements PerfMeasure {
	/**
	 * The default value of the '{@link #getMeasure() <em>Measure</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMeasure()
	 * @generated
	 * @ordered
	 */
    protected static final PerfAttribute MEASURE_EDEFAULT = PerfAttribute.DELAY_LITERAL;

	/**
	 * The cached value of the '{@link #getMeasure() <em>Measure</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getMeasure()
	 * @generated
	 * @ordered
	 */
    protected PerfAttribute measure = MEASURE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getPerfValues() <em>Perf Values</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPerfValues()
	 * @generated
	 * @ordered
	 */
    protected EList perfValues = null;

	/**
	 * The cached value of the '{@link #getDuration() <em>Duration</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDuration()
	 * @generated
	 * @ordered
	 */
    protected Workload duration = null;

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected PerfMeasureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return PerformancePackage.Literals.PERF_MEASURE;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public PerfAttribute getMeasure() {
		return measure;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setMeasure(PerfAttribute newMeasure) {
		PerfAttribute oldMeasure = measure;
		measure = newMeasure == null ? MEASURE_EDEFAULT : newMeasure;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.PERF_MEASURE__MEASURE, oldMeasure, measure));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public UCMspec getUcmspec() {
		if (eContainerFeatureID != PerformancePackage.PERF_MEASURE__UCMSPEC) return null;
		return (UCMspec)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUcmspec(UCMspec newUcmspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newUcmspec, PerformancePackage.PERF_MEASURE__UCMSPEC, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUcmspec(UCMspec newUcmspec) {
		if (newUcmspec != eInternalContainer() || (eContainerFeatureID != PerformancePackage.PERF_MEASURE__UCMSPEC && newUcmspec != null)) {
			if (EcoreUtil.isAncestor(this, newUcmspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUcmspec != null)
				msgs = ((InternalEObject)newUcmspec).eInverseAdd(this, UcmPackage.UC_MSPEC__PERF_MEASURES, UCMspec.class, msgs);
			msgs = basicSetUcmspec(newUcmspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.PERF_MEASURE__UCMSPEC, newUcmspec, newUcmspec));
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getPerfValues() {
		if (perfValues == null) {
			perfValues = new EObjectContainmentWithInverseEList(PerfValue.class, this, PerformancePackage.PERF_MEASURE__PERF_VALUES, PerformancePackage.PERF_VALUE__PERF_MEASURE);
		}
		return perfValues;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Workload getDuration() {
		if (duration != null && duration.eIsProxy()) {
			InternalEObject oldDuration = (InternalEObject)duration;
			duration = (Workload)eResolveProxy(oldDuration);
			if (duration != oldDuration) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.PERF_MEASURE__DURATION, oldDuration, duration));
			}
		}
		return duration;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Workload basicGetDuration() {
		return duration;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetDuration(Workload newDuration, NotificationChain msgs) {
		Workload oldDuration = duration;
		duration = newDuration;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerformancePackage.PERF_MEASURE__DURATION, oldDuration, newDuration);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDuration(Workload newDuration) {
		if (newDuration != duration) {
			NotificationChain msgs = null;
			if (duration != null)
				msgs = ((InternalEObject)duration).eInverseRemove(this, PerformancePackage.WORKLOAD__RESP_TIME, Workload.class, msgs);
			if (newDuration != null)
				msgs = ((InternalEObject)newDuration).eInverseAdd(this, PerformancePackage.WORKLOAD__RESP_TIME, Workload.class, msgs);
			msgs = basicSetDuration(newDuration, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.PERF_MEASURE__DURATION, newDuration, newDuration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PerformancePackage.PERF_MEASURE__UCMSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetUcmspec((UCMspec)otherEnd, msgs);
			case PerformancePackage.PERF_MEASURE__PERF_VALUES:
				return ((InternalEList)getPerfValues()).basicAdd(otherEnd, msgs);
			case PerformancePackage.PERF_MEASURE__DURATION:
				if (duration != null)
					msgs = ((InternalEObject)duration).eInverseRemove(this, PerformancePackage.WORKLOAD__RESP_TIME, Workload.class, msgs);
				return basicSetDuration((Workload)otherEnd, msgs);
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
			case PerformancePackage.PERF_MEASURE__UCMSPEC:
				return basicSetUcmspec(null, msgs);
			case PerformancePackage.PERF_MEASURE__PERF_VALUES:
				return ((InternalEList)getPerfValues()).basicRemove(otherEnd, msgs);
			case PerformancePackage.PERF_MEASURE__DURATION:
				return basicSetDuration(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID) {
			case PerformancePackage.PERF_MEASURE__UCMSPEC:
				return eInternalContainer().eInverseRemove(this, UcmPackage.UC_MSPEC__PERF_MEASURES, UCMspec.class, msgs);
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
			case PerformancePackage.PERF_MEASURE__MEASURE:
				return getMeasure();
			case PerformancePackage.PERF_MEASURE__UCMSPEC:
				return getUcmspec();
			case PerformancePackage.PERF_MEASURE__PERF_VALUES:
				return getPerfValues();
			case PerformancePackage.PERF_MEASURE__DURATION:
				if (resolve) return getDuration();
				return basicGetDuration();
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
			case PerformancePackage.PERF_MEASURE__MEASURE:
				setMeasure((PerfAttribute)newValue);
				return;
			case PerformancePackage.PERF_MEASURE__UCMSPEC:
				setUcmspec((UCMspec)newValue);
				return;
			case PerformancePackage.PERF_MEASURE__PERF_VALUES:
				getPerfValues().clear();
				getPerfValues().addAll((Collection)newValue);
				return;
			case PerformancePackage.PERF_MEASURE__DURATION:
				setDuration((Workload)newValue);
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
			case PerformancePackage.PERF_MEASURE__MEASURE:
				setMeasure(MEASURE_EDEFAULT);
				return;
			case PerformancePackage.PERF_MEASURE__UCMSPEC:
				setUcmspec((UCMspec)null);
				return;
			case PerformancePackage.PERF_MEASURE__PERF_VALUES:
				getPerfValues().clear();
				return;
			case PerformancePackage.PERF_MEASURE__DURATION:
				setDuration((Workload)null);
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
			case PerformancePackage.PERF_MEASURE__MEASURE:
				return measure != MEASURE_EDEFAULT;
			case PerformancePackage.PERF_MEASURE__UCMSPEC:
				return getUcmspec() != null;
			case PerformancePackage.PERF_MEASURE__PERF_VALUES:
				return perfValues != null && !perfValues.isEmpty();
			case PerformancePackage.PERF_MEASURE__DURATION:
				return duration != null;
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
		result.append(" (measure: ");
		result.append(measure);
		result.append(')');
		return result.toString();
	}

} //PerfMeasureImpl
