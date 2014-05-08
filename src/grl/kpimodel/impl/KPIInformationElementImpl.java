/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.kpimodel.impl;

import grl.GRLspec;
import grl.GrlPackage;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;
import grl.kpimodel.KPIModelLink;
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
 * An implementation of the model object '<em><b>KPI Information Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.kpimodel.impl.KPIInformationElementImpl#getRefs <em>Refs</em>}</li>
 *   <li>{@link grl.kpimodel.impl.KPIInformationElementImpl#getGrlspec <em>Grlspec</em>}</li>
 *   <li>{@link grl.kpimodel.impl.KPIInformationElementImpl#getKpiModelLinksSrc <em>Kpi Model Links Src</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KPIInformationElementImpl extends GRLmodelElementImpl implements KPIInformationElement {
    /**
	 * The cached value of the '{@link #getRefs() <em>Refs</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRefs()
	 * @generated
	 * @ordered
	 */
    protected EList refs;

    /**
	 * The cached value of the '{@link #getKpiModelLinksSrc() <em>Kpi Model Links Src</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getKpiModelLinksSrc()
	 * @generated
	 * @ordered
	 */
    protected EList kpiModelLinksSrc;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected KPIInformationElementImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return KpimodelPackage.Literals.KPI_INFORMATION_ELEMENT;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getRefs() {
		if (refs == null) {
			refs = new EObjectWithInverseResolvingEList(KPIInformationElementRef.class, this, KpimodelPackage.KPI_INFORMATION_ELEMENT__REFS, KpimodelPackage.KPI_INFORMATION_ELEMENT_REF__DEF);
		}
		return refs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public GRLspec getGrlspec() {
		if (eContainerFeatureID() != KpimodelPackage.KPI_INFORMATION_ELEMENT__GRLSPEC) return null;
		return (GRLspec)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetGrlspec(GRLspec newGrlspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGrlspec, KpimodelPackage.KPI_INFORMATION_ELEMENT__GRLSPEC, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGrlspec(GRLspec newGrlspec) {
		if (newGrlspec != eInternalContainer() || (eContainerFeatureID() != KpimodelPackage.KPI_INFORMATION_ELEMENT__GRLSPEC && newGrlspec != null)) {
			if (EcoreUtil.isAncestor(this, newGrlspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGrlspec != null)
				msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__KPI_INFORMATION_ELEMENTS, GRLspec.class, msgs);
			msgs = basicSetGrlspec(newGrlspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.KPI_INFORMATION_ELEMENT__GRLSPEC, newGrlspec, newGrlspec));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getKpiModelLinksSrc() {
		if (kpiModelLinksSrc == null) {
			kpiModelLinksSrc = new EObjectWithInverseResolvingEList(KPIModelLink.class, this, KpimodelPackage.KPI_INFORMATION_ELEMENT__KPI_MODEL_LINKS_SRC, KpimodelPackage.KPI_MODEL_LINK__KPI_INFORMATION_ELEMENT_SRC);
		}
		return kpiModelLinksSrc;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KpimodelPackage.KPI_INFORMATION_ELEMENT__REFS:
				return ((InternalEList)getRefs()).basicAdd(otherEnd, msgs);
			case KpimodelPackage.KPI_INFORMATION_ELEMENT__GRLSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGrlspec((GRLspec)otherEnd, msgs);
			case KpimodelPackage.KPI_INFORMATION_ELEMENT__KPI_MODEL_LINKS_SRC:
				return ((InternalEList)getKpiModelLinksSrc()).basicAdd(otherEnd, msgs);
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
			case KpimodelPackage.KPI_INFORMATION_ELEMENT__REFS:
				return ((InternalEList)getRefs()).basicRemove(otherEnd, msgs);
			case KpimodelPackage.KPI_INFORMATION_ELEMENT__GRLSPEC:
				return basicSetGrlspec(null, msgs);
			case KpimodelPackage.KPI_INFORMATION_ELEMENT__KPI_MODEL_LINKS_SRC:
				return ((InternalEList)getKpiModelLinksSrc()).basicRemove(otherEnd, msgs);
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
			case KpimodelPackage.KPI_INFORMATION_ELEMENT__GRLSPEC:
				return eInternalContainer().eInverseRemove(this, GrlPackage.GR_LSPEC__KPI_INFORMATION_ELEMENTS, GRLspec.class, msgs);
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
			case KpimodelPackage.KPI_INFORMATION_ELEMENT__REFS:
				return getRefs();
			case KpimodelPackage.KPI_INFORMATION_ELEMENT__GRLSPEC:
				return getGrlspec();
			case KpimodelPackage.KPI_INFORMATION_ELEMENT__KPI_MODEL_LINKS_SRC:
				return getKpiModelLinksSrc();
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
			case KpimodelPackage.KPI_INFORMATION_ELEMENT__REFS:
				getRefs().clear();
				getRefs().addAll((Collection)newValue);
				return;
			case KpimodelPackage.KPI_INFORMATION_ELEMENT__GRLSPEC:
				setGrlspec((GRLspec)newValue);
				return;
			case KpimodelPackage.KPI_INFORMATION_ELEMENT__KPI_MODEL_LINKS_SRC:
				getKpiModelLinksSrc().clear();
				getKpiModelLinksSrc().addAll((Collection)newValue);
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
			case KpimodelPackage.KPI_INFORMATION_ELEMENT__REFS:
				getRefs().clear();
				return;
			case KpimodelPackage.KPI_INFORMATION_ELEMENT__GRLSPEC:
				setGrlspec((GRLspec)null);
				return;
			case KpimodelPackage.KPI_INFORMATION_ELEMENT__KPI_MODEL_LINKS_SRC:
				getKpiModelLinksSrc().clear();
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
			case KpimodelPackage.KPI_INFORMATION_ELEMENT__REFS:
				return refs != null && !refs.isEmpty();
			case KpimodelPackage.KPI_INFORMATION_ELEMENT__GRLSPEC:
				return getGrlspec() != null;
			case KpimodelPackage.KPI_INFORMATION_ELEMENT__KPI_MODEL_LINKS_SRC:
				return kpiModelLinksSrc != null && !kpiModelLinksSrc.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //KPIInformationElementImpl