/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.kpimodel.impl;

import grl.GRLspec;
import grl.GrlPackage;
import grl.kpimodel.Indicator;
import grl.kpimodel.IndicatorGroup;
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
 * An implementation of the model object '<em><b>Indicator Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.kpimodel.impl.IndicatorGroupImpl#isIsRedesignCategory <em>Is Redesign Category</em>}</li>
 *   <li>{@link grl.kpimodel.impl.IndicatorGroupImpl#getGrlspec <em>Grlspec</em>}</li>
 *   <li>{@link grl.kpimodel.impl.IndicatorGroupImpl#getIndicators <em>Indicators</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IndicatorGroupImpl extends GRLmodelElementImpl implements IndicatorGroup {
    /**
	 * The default value of the '{@link #isIsRedesignCategory() <em>Is Redesign Category</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isIsRedesignCategory()
	 * @generated
	 * @ordered
	 */
    protected static final boolean IS_REDESIGN_CATEGORY_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isIsRedesignCategory() <em>Is Redesign Category</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isIsRedesignCategory()
	 * @generated
	 * @ordered
	 */
    protected boolean isRedesignCategory = IS_REDESIGN_CATEGORY_EDEFAULT;

    /**
	 * The cached value of the '{@link #getIndicators() <em>Indicators</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getIndicators()
	 * @generated
	 * @ordered
	 */
    protected EList indicators;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected IndicatorGroupImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return KpimodelPackage.Literals.INDICATOR_GROUP;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isIsRedesignCategory() {
		return isRedesignCategory;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setIsRedesignCategory(boolean newIsRedesignCategory) {
		boolean oldIsRedesignCategory = isRedesignCategory;
		isRedesignCategory = newIsRedesignCategory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.INDICATOR_GROUP__IS_REDESIGN_CATEGORY, oldIsRedesignCategory, isRedesignCategory));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public GRLspec getGrlspec() {
		if (eContainerFeatureID() != KpimodelPackage.INDICATOR_GROUP__GRLSPEC) return null;
		return (GRLspec)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetGrlspec(GRLspec newGrlspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGrlspec, KpimodelPackage.INDICATOR_GROUP__GRLSPEC, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGrlspec(GRLspec newGrlspec) {
		if (newGrlspec != eInternalContainer() || (eContainerFeatureID() != KpimodelPackage.INDICATOR_GROUP__GRLSPEC && newGrlspec != null)) {
			if (EcoreUtil.isAncestor(this, newGrlspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGrlspec != null)
				msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__INDICATOR_GROUP, GRLspec.class, msgs);
			msgs = basicSetGrlspec(newGrlspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.INDICATOR_GROUP__GRLSPEC, newGrlspec, newGrlspec));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getIndicators() {
		if (indicators == null) {
			indicators = new EObjectWithInverseResolvingEList.ManyInverse(Indicator.class, this, KpimodelPackage.INDICATOR_GROUP__INDICATORS, KpimodelPackage.INDICATOR__GROUPS);
		}
		return indicators;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KpimodelPackage.INDICATOR_GROUP__GRLSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGrlspec((GRLspec)otherEnd, msgs);
			case KpimodelPackage.INDICATOR_GROUP__INDICATORS:
				return ((InternalEList)getIndicators()).basicAdd(otherEnd, msgs);
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
			case KpimodelPackage.INDICATOR_GROUP__GRLSPEC:
				return basicSetGrlspec(null, msgs);
			case KpimodelPackage.INDICATOR_GROUP__INDICATORS:
				return ((InternalEList)getIndicators()).basicRemove(otherEnd, msgs);
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
			case KpimodelPackage.INDICATOR_GROUP__GRLSPEC:
				return eInternalContainer().eInverseRemove(this, GrlPackage.GR_LSPEC__INDICATOR_GROUP, GRLspec.class, msgs);
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
			case KpimodelPackage.INDICATOR_GROUP__IS_REDESIGN_CATEGORY:
				return isIsRedesignCategory() ? Boolean.TRUE : Boolean.FALSE;
			case KpimodelPackage.INDICATOR_GROUP__GRLSPEC:
				return getGrlspec();
			case KpimodelPackage.INDICATOR_GROUP__INDICATORS:
				return getIndicators();
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
			case KpimodelPackage.INDICATOR_GROUP__IS_REDESIGN_CATEGORY:
				setIsRedesignCategory(((Boolean)newValue).booleanValue());
				return;
			case KpimodelPackage.INDICATOR_GROUP__GRLSPEC:
				setGrlspec((GRLspec)newValue);
				return;
			case KpimodelPackage.INDICATOR_GROUP__INDICATORS:
				getIndicators().clear();
				getIndicators().addAll((Collection)newValue);
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
			case KpimodelPackage.INDICATOR_GROUP__IS_REDESIGN_CATEGORY:
				setIsRedesignCategory(IS_REDESIGN_CATEGORY_EDEFAULT);
				return;
			case KpimodelPackage.INDICATOR_GROUP__GRLSPEC:
				setGrlspec((GRLspec)null);
				return;
			case KpimodelPackage.INDICATOR_GROUP__INDICATORS:
				getIndicators().clear();
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
			case KpimodelPackage.INDICATOR_GROUP__IS_REDESIGN_CATEGORY:
				return isRedesignCategory != IS_REDESIGN_CATEGORY_EDEFAULT;
			case KpimodelPackage.INDICATOR_GROUP__GRLSPEC:
				return getGrlspec() != null;
			case KpimodelPackage.INDICATOR_GROUP__INDICATORS:
				return indicators != null && !indicators.isEmpty();
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
		result.append(" (isRedesignCategory: ");
		result.append(isRedesignCategory);
		result.append(')');
		return result.toString();
	}

} //IndicatorGroupImpl