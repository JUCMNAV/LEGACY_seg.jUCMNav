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
import ucm.map.MapPackage;
import ucm.map.PathNode;

import ucm.performance.GeneralResource;
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
 *   <li>{@link ucm.performance.impl.PerfMeasureImpl#getResource <em>Resource</em>}</li>
 *   <li>{@link ucm.performance.impl.PerfMeasureImpl#getTrigger <em>Trigger</em>}</li>
 *   <li>{@link ucm.performance.impl.PerfMeasureImpl#getEnd <em>End</em>}</li>
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
	 * The cached value of the '{@link #getResource() <em>Resource</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResource()
	 * @generated
	 * @ordered
	 */
	protected GeneralResource resource = null;

	/**
	 * The cached value of the '{@link #getTrigger() <em>Trigger</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrigger()
	 * @generated
	 * @ordered
	 */
	protected PathNode trigger = null;

	/**
	 * The cached value of the '{@link #getEnd() <em>End</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnd()
	 * @generated
	 * @ordered
	 */
	protected PathNode end = null;

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
				msgs = ((InternalEObject)duration).eInverseRemove(this, PerformancePackage.WORKLOAD__RESPONSE_TIME, Workload.class, msgs);
			if (newDuration != null)
				msgs = ((InternalEObject)newDuration).eInverseAdd(this, PerformancePackage.WORKLOAD__RESPONSE_TIME, Workload.class, msgs);
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
	public GeneralResource getResource() {
		if (resource != null && resource.eIsProxy()) {
			InternalEObject oldResource = (InternalEObject)resource;
			resource = (GeneralResource)eResolveProxy(oldResource);
			if (resource != oldResource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.PERF_MEASURE__RESOURCE, oldResource, resource));
			}
		}
		return resource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GeneralResource basicGetResource() {
		return resource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResource(GeneralResource newResource, NotificationChain msgs) {
		GeneralResource oldResource = resource;
		resource = newResource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerformancePackage.PERF_MEASURE__RESOURCE, oldResource, newResource);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResource(GeneralResource newResource) {
		if (newResource != resource) {
			NotificationChain msgs = null;
			if (resource != null)
				msgs = ((InternalEObject)resource).eInverseRemove(this, PerformancePackage.GENERAL_RESOURCE__PERF_MEASURES, GeneralResource.class, msgs);
			if (newResource != null)
				msgs = ((InternalEObject)newResource).eInverseAdd(this, PerformancePackage.GENERAL_RESOURCE__PERF_MEASURES, GeneralResource.class, msgs);
			msgs = basicSetResource(newResource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.PERF_MEASURE__RESOURCE, newResource, newResource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PathNode getTrigger() {
		if (trigger != null && trigger.eIsProxy()) {
			InternalEObject oldTrigger = (InternalEObject)trigger;
			trigger = (PathNode)eResolveProxy(oldTrigger);
			if (trigger != oldTrigger) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.PERF_MEASURE__TRIGGER, oldTrigger, trigger));
			}
		}
		return trigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PathNode basicGetTrigger() {
		return trigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTrigger(PathNode newTrigger, NotificationChain msgs) {
		PathNode oldTrigger = trigger;
		trigger = newTrigger;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerformancePackage.PERF_MEASURE__TRIGGER, oldTrigger, newTrigger);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrigger(PathNode newTrigger) {
		if (newTrigger != trigger) {
			NotificationChain msgs = null;
			if (trigger != null)
				msgs = ((InternalEObject)trigger).eInverseRemove(this, MapPackage.PATH_NODE__PERF_MTRIG, PathNode.class, msgs);
			if (newTrigger != null)
				msgs = ((InternalEObject)newTrigger).eInverseAdd(this, MapPackage.PATH_NODE__PERF_MTRIG, PathNode.class, msgs);
			msgs = basicSetTrigger(newTrigger, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.PERF_MEASURE__TRIGGER, newTrigger, newTrigger));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PathNode getEnd() {
		if (end != null && end.eIsProxy()) {
			InternalEObject oldEnd = (InternalEObject)end;
			end = (PathNode)eResolveProxy(oldEnd);
			if (end != oldEnd) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.PERF_MEASURE__END, oldEnd, end));
			}
		}
		return end;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PathNode basicGetEnd() {
		return end;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEnd(PathNode newEnd, NotificationChain msgs) {
		PathNode oldEnd = end;
		end = newEnd;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerformancePackage.PERF_MEASURE__END, oldEnd, newEnd);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnd(PathNode newEnd) {
		if (newEnd != end) {
			NotificationChain msgs = null;
			if (end != null)
				msgs = ((InternalEObject)end).eInverseRemove(this, MapPackage.PATH_NODE__PERF_MEND, PathNode.class, msgs);
			if (newEnd != null)
				msgs = ((InternalEObject)newEnd).eInverseAdd(this, MapPackage.PATH_NODE__PERF_MEND, PathNode.class, msgs);
			msgs = basicSetEnd(newEnd, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.PERF_MEASURE__END, newEnd, newEnd));
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
					msgs = ((InternalEObject)duration).eInverseRemove(this, PerformancePackage.WORKLOAD__RESPONSE_TIME, Workload.class, msgs);
				return basicSetDuration((Workload)otherEnd, msgs);
			case PerformancePackage.PERF_MEASURE__RESOURCE:
				if (resource != null)
					msgs = ((InternalEObject)resource).eInverseRemove(this, PerformancePackage.GENERAL_RESOURCE__PERF_MEASURES, GeneralResource.class, msgs);
				return basicSetResource((GeneralResource)otherEnd, msgs);
			case PerformancePackage.PERF_MEASURE__TRIGGER:
				if (trigger != null)
					msgs = ((InternalEObject)trigger).eInverseRemove(this, MapPackage.PATH_NODE__PERF_MTRIG, PathNode.class, msgs);
				return basicSetTrigger((PathNode)otherEnd, msgs);
			case PerformancePackage.PERF_MEASURE__END:
				if (end != null)
					msgs = ((InternalEObject)end).eInverseRemove(this, MapPackage.PATH_NODE__PERF_MEND, PathNode.class, msgs);
				return basicSetEnd((PathNode)otherEnd, msgs);
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
			case PerformancePackage.PERF_MEASURE__RESOURCE:
				return basicSetResource(null, msgs);
			case PerformancePackage.PERF_MEASURE__TRIGGER:
				return basicSetTrigger(null, msgs);
			case PerformancePackage.PERF_MEASURE__END:
				return basicSetEnd(null, msgs);
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
			case PerformancePackage.PERF_MEASURE__RESOURCE:
				if (resolve) return getResource();
				return basicGetResource();
			case PerformancePackage.PERF_MEASURE__TRIGGER:
				if (resolve) return getTrigger();
				return basicGetTrigger();
			case PerformancePackage.PERF_MEASURE__END:
				if (resolve) return getEnd();
				return basicGetEnd();
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
			case PerformancePackage.PERF_MEASURE__RESOURCE:
				setResource((GeneralResource)newValue);
				return;
			case PerformancePackage.PERF_MEASURE__TRIGGER:
				setTrigger((PathNode)newValue);
				return;
			case PerformancePackage.PERF_MEASURE__END:
				setEnd((PathNode)newValue);
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
			case PerformancePackage.PERF_MEASURE__RESOURCE:
				setResource((GeneralResource)null);
				return;
			case PerformancePackage.PERF_MEASURE__TRIGGER:
				setTrigger((PathNode)null);
				return;
			case PerformancePackage.PERF_MEASURE__END:
				setEnd((PathNode)null);
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
			case PerformancePackage.PERF_MEASURE__RESOURCE:
				return resource != null;
			case PerformancePackage.PERF_MEASURE__TRIGGER:
				return trigger != null;
			case PerformancePackage.PERF_MEASURE__END:
				return end != null;
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
