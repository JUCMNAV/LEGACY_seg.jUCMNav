/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.kpimodel.impl;

import grl.GRLspec;
import grl.GrlPackage;

import grl.kpimodel.KPIConversion;
import grl.kpimodel.KPIEvalValueSet;
import grl.kpimodel.KpimodelPackage;

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

import urncore.impl.GRLmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KPI Conversion</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.kpimodel.impl.KPIConversionImpl#getKpiEvalValueSet <em>Kpi Eval Value Set</em>}</li>
 *   <li>{@link grl.kpimodel.impl.KPIConversionImpl#getGrlspec <em>Grlspec</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class KPIConversionImpl extends GRLmodelElementImpl implements KPIConversion {
	/**
	 * The cached value of the '{@link #getKpiEvalValueSet() <em>Kpi Eval Value Set</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKpiEvalValueSet()
	 * @generated
	 * @ordered
	 */
	protected EList kpiEvalValueSet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected KPIConversionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return KpimodelPackage.Literals.KPI_CONVERSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getKpiEvalValueSet() {
		if (kpiEvalValueSet == null) {
			kpiEvalValueSet = new EObjectWithInverseResolvingEList(KPIEvalValueSet.class, this, KpimodelPackage.KPI_CONVERSION__KPI_EVAL_VALUE_SET, KpimodelPackage.KPI_EVAL_VALUE_SET__KPI_CONV);
		}
		return kpiEvalValueSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GRLspec getGrlspec() {
		if (eContainerFeatureID() != KpimodelPackage.KPI_CONVERSION__GRLSPEC) return null;
		return (GRLspec)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGrlspec(GRLspec newGrlspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGrlspec, KpimodelPackage.KPI_CONVERSION__GRLSPEC, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGrlspec(GRLspec newGrlspec) {
		if (newGrlspec != eInternalContainer() || (eContainerFeatureID() != KpimodelPackage.KPI_CONVERSION__GRLSPEC && newGrlspec != null)) {
			if (EcoreUtil.isAncestor(this, newGrlspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGrlspec != null)
				msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__KPI_CONVERSION, GRLspec.class, msgs);
			msgs = basicSetGrlspec(newGrlspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.KPI_CONVERSION__GRLSPEC, newGrlspec, newGrlspec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KpimodelPackage.KPI_CONVERSION__KPI_EVAL_VALUE_SET:
				return ((InternalEList)getKpiEvalValueSet()).basicAdd(otherEnd, msgs);
			case KpimodelPackage.KPI_CONVERSION__GRLSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGrlspec((GRLspec)otherEnd, msgs);
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
			case KpimodelPackage.KPI_CONVERSION__KPI_EVAL_VALUE_SET:
				return ((InternalEList)getKpiEvalValueSet()).basicRemove(otherEnd, msgs);
			case KpimodelPackage.KPI_CONVERSION__GRLSPEC:
				return basicSetGrlspec(null, msgs);
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
			case KpimodelPackage.KPI_CONVERSION__GRLSPEC:
				return eInternalContainer().eInverseRemove(this, GrlPackage.GR_LSPEC__KPI_CONVERSION, GRLspec.class, msgs);
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
			case KpimodelPackage.KPI_CONVERSION__KPI_EVAL_VALUE_SET:
				return getKpiEvalValueSet();
			case KpimodelPackage.KPI_CONVERSION__GRLSPEC:
				return getGrlspec();
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
			case KpimodelPackage.KPI_CONVERSION__KPI_EVAL_VALUE_SET:
				getKpiEvalValueSet().clear();
				getKpiEvalValueSet().addAll((Collection)newValue);
				return;
			case KpimodelPackage.KPI_CONVERSION__GRLSPEC:
				setGrlspec((GRLspec)newValue);
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
			case KpimodelPackage.KPI_CONVERSION__KPI_EVAL_VALUE_SET:
				getKpiEvalValueSet().clear();
				return;
			case KpimodelPackage.KPI_CONVERSION__GRLSPEC:
				setGrlspec((GRLspec)null);
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
			case KpimodelPackage.KPI_CONVERSION__KPI_EVAL_VALUE_SET:
				return kpiEvalValueSet != null && !kpiEvalValueSet.isEmpty();
			case KpimodelPackage.KPI_CONVERSION__GRLSPEC:
				return getGrlspec() != null;
		}
		return super.eIsSet(featureID);
	}

} //KPIConversionImpl
