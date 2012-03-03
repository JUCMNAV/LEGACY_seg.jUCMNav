/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.kpimodel.impl;

import grl.impl.IntentionalElementImpl;
import grl.kpimodel.Indicator;
import grl.kpimodel.IndicatorGroup;
import grl.kpimodel.KPIModelLink;
import grl.kpimodel.KpimodelPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Indicator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.kpimodel.impl.IndicatorImpl#getKpiModelLinksDest <em>Kpi Model Links Dest</em>}</li>
 *   <li>{@link grl.kpimodel.impl.IndicatorImpl#getGroups <em>Groups</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IndicatorImpl extends IntentionalElementImpl implements Indicator {
    /**
	 * The cached value of the '{@link #getKpiModelLinksDest() <em>Kpi Model Links Dest</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getKpiModelLinksDest()
	 * @generated
	 * @ordered
	 */
    protected EList kpiModelLinksDest;

    /**
	 * The cached value of the '{@link #getGroups() <em>Groups</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getGroups()
	 * @generated
	 * @ordered
	 */
    protected EList groups;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected IndicatorImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return KpimodelPackage.Literals.INDICATOR;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getKpiModelLinksDest() {
		if (kpiModelLinksDest == null) {
			kpiModelLinksDest = new EObjectWithInverseResolvingEList(KPIModelLink.class, this, KpimodelPackage.INDICATOR__KPI_MODEL_LINKS_DEST, KpimodelPackage.KPI_MODEL_LINK__IND_DEST);
		}
		return kpiModelLinksDest;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getGroups() {
		if (groups == null) {
			groups = new EObjectWithInverseResolvingEList.ManyInverse(IndicatorGroup.class, this, KpimodelPackage.INDICATOR__GROUPS, KpimodelPackage.INDICATOR_GROUP__INDICATORS);
		}
		return groups;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KpimodelPackage.INDICATOR__KPI_MODEL_LINKS_DEST:
				return ((InternalEList)getKpiModelLinksDest()).basicAdd(otherEnd, msgs);
			case KpimodelPackage.INDICATOR__GROUPS:
				return ((InternalEList)getGroups()).basicAdd(otherEnd, msgs);
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
			case KpimodelPackage.INDICATOR__KPI_MODEL_LINKS_DEST:
				return ((InternalEList)getKpiModelLinksDest()).basicRemove(otherEnd, msgs);
			case KpimodelPackage.INDICATOR__GROUPS:
				return ((InternalEList)getGroups()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case KpimodelPackage.INDICATOR__KPI_MODEL_LINKS_DEST:
				return getKpiModelLinksDest();
			case KpimodelPackage.INDICATOR__GROUPS:
				return getGroups();
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
			case KpimodelPackage.INDICATOR__KPI_MODEL_LINKS_DEST:
				getKpiModelLinksDest().clear();
				getKpiModelLinksDest().addAll((Collection)newValue);
				return;
			case KpimodelPackage.INDICATOR__GROUPS:
				getGroups().clear();
				getGroups().addAll((Collection)newValue);
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
			case KpimodelPackage.INDICATOR__KPI_MODEL_LINKS_DEST:
				getKpiModelLinksDest().clear();
				return;
			case KpimodelPackage.INDICATOR__GROUPS:
				getGroups().clear();
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
			case KpimodelPackage.INDICATOR__KPI_MODEL_LINKS_DEST:
				return kpiModelLinksDest != null && !kpiModelLinksDest.isEmpty();
			case KpimodelPackage.INDICATOR__GROUPS:
				return groups != null && !groups.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //IndicatorImpl