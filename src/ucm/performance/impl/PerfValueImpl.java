/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import ucm.performance.PerfValue;
import ucm.performance.PerfValueKind;
import ucm.performance.PerfValueSource;
import ucm.performance.PerformancePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Perf Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.performance.impl.PerfValueImpl#getValue <em>Value</em>}</li>
 *   <li>{@link ucm.performance.impl.PerfValueImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link ucm.performance.impl.PerfValueImpl#getSource <em>Source</em>}</li>
 *   <li>{@link ucm.performance.impl.PerfValueImpl#getPercentile <em>Percentile</em>}</li>
 *   <li>{@link ucm.performance.impl.PerfValueImpl#getKthMoment <em>Kth Moment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PerfValueImpl extends EObjectImpl implements PerfValue {
	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final String VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected String value = VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final PerfValueKind KIND_EDEFAULT = PerfValueKind.UNKNOWN_LITERAL;

	/**
	 * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected PerfValueKind kind = KIND_EDEFAULT;

	/**
	 * The default value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected static final PerfValueSource SOURCE_EDEFAULT = PerfValueSource.UNKNOWN_LITERAL;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected PerfValueSource source = SOURCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getPercentile() <em>Percentile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPercentile()
	 * @generated
	 * @ordered
	 */
	protected static final String PERCENTILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPercentile() <em>Percentile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPercentile()
	 * @generated
	 * @ordered
	 */
	protected String percentile = PERCENTILE_EDEFAULT;

	/**
	 * The default value of the '{@link #getKthMoment() <em>Kth Moment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKthMoment()
	 * @generated
	 * @ordered
	 */
	protected static final String KTH_MOMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getKthMoment() <em>Kth Moment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKthMoment()
	 * @generated
	 * @ordered
	 */
	protected String kthMoment = KTH_MOMENT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PerfValueImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return PerformancePackage.eINSTANCE.getPerfValue();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(String newValue) {
		String oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.PERF_VALUE__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PerfValueKind getKind() {
		return kind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKind(PerfValueKind newKind) {
		PerfValueKind oldKind = kind;
		kind = newKind == null ? KIND_EDEFAULT : newKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.PERF_VALUE__KIND, oldKind, kind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PerfValueSource getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(PerfValueSource newSource) {
		PerfValueSource oldSource = source;
		source = newSource == null ? SOURCE_EDEFAULT : newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.PERF_VALUE__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPercentile() {
		return percentile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPercentile(String newPercentile) {
		String oldPercentile = percentile;
		percentile = newPercentile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.PERF_VALUE__PERCENTILE, oldPercentile, percentile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getKthMoment() {
		return kthMoment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKthMoment(String newKthMoment) {
		String oldKthMoment = kthMoment;
		kthMoment = newKthMoment;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.PERF_VALUE__KTH_MOMENT, oldKthMoment, kthMoment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case PerformancePackage.PERF_VALUE__VALUE:
				return getValue();
			case PerformancePackage.PERF_VALUE__KIND:
				return getKind();
			case PerformancePackage.PERF_VALUE__SOURCE:
				return getSource();
			case PerformancePackage.PERF_VALUE__PERCENTILE:
				return getPercentile();
			case PerformancePackage.PERF_VALUE__KTH_MOMENT:
				return getKthMoment();
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
			case PerformancePackage.PERF_VALUE__VALUE:
				setValue((String)newValue);
				return;
			case PerformancePackage.PERF_VALUE__KIND:
				setKind((PerfValueKind)newValue);
				return;
			case PerformancePackage.PERF_VALUE__SOURCE:
				setSource((PerfValueSource)newValue);
				return;
			case PerformancePackage.PERF_VALUE__PERCENTILE:
				setPercentile((String)newValue);
				return;
			case PerformancePackage.PERF_VALUE__KTH_MOMENT:
				setKthMoment((String)newValue);
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
			case PerformancePackage.PERF_VALUE__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case PerformancePackage.PERF_VALUE__KIND:
				setKind(KIND_EDEFAULT);
				return;
			case PerformancePackage.PERF_VALUE__SOURCE:
				setSource(SOURCE_EDEFAULT);
				return;
			case PerformancePackage.PERF_VALUE__PERCENTILE:
				setPercentile(PERCENTILE_EDEFAULT);
				return;
			case PerformancePackage.PERF_VALUE__KTH_MOMENT:
				setKthMoment(KTH_MOMENT_EDEFAULT);
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
			case PerformancePackage.PERF_VALUE__VALUE:
				return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
			case PerformancePackage.PERF_VALUE__KIND:
				return kind != KIND_EDEFAULT;
			case PerformancePackage.PERF_VALUE__SOURCE:
				return source != SOURCE_EDEFAULT;
			case PerformancePackage.PERF_VALUE__PERCENTILE:
				return PERCENTILE_EDEFAULT == null ? percentile != null : !PERCENTILE_EDEFAULT.equals(percentile);
			case PerformancePackage.PERF_VALUE__KTH_MOMENT:
				return KTH_MOMENT_EDEFAULT == null ? kthMoment != null : !KTH_MOMENT_EDEFAULT.equals(kthMoment);
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
		result.append(" (value: ");
		result.append(value);
		result.append(", kind: ");
		result.append(kind);
		result.append(", source: ");
		result.append(source);
		result.append(", percentile: ");
		result.append(percentile);
		result.append(", kthMoment: ");
		result.append(kthMoment);
		result.append(')');
		return result.toString();
	}

} //PerfValueImpl
