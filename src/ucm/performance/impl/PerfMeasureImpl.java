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
import org.eclipse.emf.ecore.EStructuralFeature;
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
        return PerformancePackage.eINSTANCE.getPerfMeasure();
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
        return (UCMspec)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setUcmspec(UCMspec newUcmspec) {
        if (newUcmspec != eContainer || (eContainerFeatureID != PerformancePackage.PERF_MEASURE__UCMSPEC && newUcmspec != null)) {
            if (EcoreUtil.isAncestor(this, newUcmspec))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUcmspec != null)
                msgs = ((InternalEObject)newUcmspec).eInverseAdd(this, UcmPackage.UC_MSPEC__PERF_MEASURES, UCMspec.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newUcmspec, PerformancePackage.PERF_MEASURE__UCMSPEC, msgs);
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
            Workload oldDuration = duration;
            duration = (Workload)eResolveProxy((InternalEObject)duration);
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case PerformancePackage.PERF_MEASURE__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
                case PerformancePackage.PERF_MEASURE__UCMSPEC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, PerformancePackage.PERF_MEASURE__UCMSPEC, msgs);
                case PerformancePackage.PERF_MEASURE__PERF_VALUES:
                    return ((InternalEList)getPerfValues()).basicAdd(otherEnd, msgs);
                case PerformancePackage.PERF_MEASURE__DURATION:
                    if (duration != null)
                        msgs = ((InternalEObject)duration).eInverseRemove(this, PerformancePackage.WORKLOAD__RESP_TIME, Workload.class, msgs);
                    return basicSetDuration((Workload)otherEnd, msgs);
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
                case PerformancePackage.PERF_MEASURE__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
                case PerformancePackage.PERF_MEASURE__UCMSPEC:
                    return eBasicSetContainer(null, PerformancePackage.PERF_MEASURE__UCMSPEC, msgs);
                case PerformancePackage.PERF_MEASURE__PERF_VALUES:
                    return ((InternalEList)getPerfValues()).basicRemove(otherEnd, msgs);
                case PerformancePackage.PERF_MEASURE__DURATION:
                    return basicSetDuration(null, msgs);
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
                case PerformancePackage.PERF_MEASURE__UCMSPEC:
                    return eContainer.eInverseRemove(this, UcmPackage.UC_MSPEC__PERF_MEASURES, UCMspec.class, msgs);
                default:
                    return eDynamicBasicRemoveFromContainer(msgs);
            }
        }
        return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case PerformancePackage.PERF_MEASURE__URN_LINKS:
                return getUrnLinks();
            case PerformancePackage.PERF_MEASURE__ID:
                return getId();
            case PerformancePackage.PERF_MEASURE__NAME:
                return getName();
            case PerformancePackage.PERF_MEASURE__DESCRIPTION:
                return getDescription();
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
        return eDynamicGet(eFeature, resolve);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case PerformancePackage.PERF_MEASURE__URN_LINKS:
                getUrnLinks().clear();
                getUrnLinks().addAll((Collection)newValue);
                return;
            case PerformancePackage.PERF_MEASURE__ID:
                setId((String)newValue);
                return;
            case PerformancePackage.PERF_MEASURE__NAME:
                setName((String)newValue);
                return;
            case PerformancePackage.PERF_MEASURE__DESCRIPTION:
                setDescription((String)newValue);
                return;
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
        eDynamicSet(eFeature, newValue);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eUnset(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case PerformancePackage.PERF_MEASURE__URN_LINKS:
                getUrnLinks().clear();
                return;
            case PerformancePackage.PERF_MEASURE__ID:
                setId(ID_EDEFAULT);
                return;
            case PerformancePackage.PERF_MEASURE__NAME:
                setName(NAME_EDEFAULT);
                return;
            case PerformancePackage.PERF_MEASURE__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
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
        eDynamicUnset(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean eIsSet(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case PerformancePackage.PERF_MEASURE__URN_LINKS:
                return urnLinks != null && !urnLinks.isEmpty();
            case PerformancePackage.PERF_MEASURE__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case PerformancePackage.PERF_MEASURE__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case PerformancePackage.PERF_MEASURE__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case PerformancePackage.PERF_MEASURE__MEASURE:
                return measure != MEASURE_EDEFAULT;
            case PerformancePackage.PERF_MEASURE__UCMSPEC:
                return getUcmspec() != null;
            case PerformancePackage.PERF_MEASURE__PERF_VALUES:
                return perfValues != null && !perfValues.isEmpty();
            case PerformancePackage.PERF_MEASURE__DURATION:
                return duration != null;
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
        result.append(" (measure: ");
        result.append(measure);
        result.append(')');
        return result.toString();
    }

} //PerfMeasureImpl
