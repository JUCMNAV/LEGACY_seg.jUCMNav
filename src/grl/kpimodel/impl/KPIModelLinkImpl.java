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
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIModelLink;
import grl.kpimodel.KPIModelLinkRef;
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
 * An implementation of the model object '<em><b>KPI Model Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.kpimodel.impl.KPIModelLinkImpl#getKpiInformationElementSrc <em>Kpi Information Element Src</em>}</li>
 *   <li>{@link grl.kpimodel.impl.KPIModelLinkImpl#getRefs <em>Refs</em>}</li>
 *   <li>{@link grl.kpimodel.impl.KPIModelLinkImpl#getGrlspec <em>Grlspec</em>}</li>
 *   <li>{@link grl.kpimodel.impl.KPIModelLinkImpl#getIndDest <em>Ind Dest</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KPIModelLinkImpl extends GRLmodelElementImpl implements KPIModelLink {
    /**
	 * The cached value of the '{@link #getKpiInformationElementSrc() <em>Kpi Information Element Src</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getKpiInformationElementSrc()
	 * @generated
	 * @ordered
	 */
    protected KPIInformationElement kpiInformationElementSrc;

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
	 * The cached value of the '{@link #getIndDest() <em>Ind Dest</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getIndDest()
	 * @generated
	 * @ordered
	 */
    protected Indicator indDest;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected KPIModelLinkImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return KpimodelPackage.Literals.KPI_MODEL_LINK;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public KPIInformationElement getKpiInformationElementSrc() {
		if (kpiInformationElementSrc != null && kpiInformationElementSrc.eIsProxy()) {
			InternalEObject oldKpiInformationElementSrc = (InternalEObject)kpiInformationElementSrc;
			kpiInformationElementSrc = (KPIInformationElement)eResolveProxy(oldKpiInformationElementSrc);
			if (kpiInformationElementSrc != oldKpiInformationElementSrc) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, KpimodelPackage.KPI_MODEL_LINK__KPI_INFORMATION_ELEMENT_SRC, oldKpiInformationElementSrc, kpiInformationElementSrc));
			}
		}
		return kpiInformationElementSrc;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public KPIInformationElement basicGetKpiInformationElementSrc() {
		return kpiInformationElementSrc;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetKpiInformationElementSrc(KPIInformationElement newKpiInformationElementSrc, NotificationChain msgs) {
		KPIInformationElement oldKpiInformationElementSrc = kpiInformationElementSrc;
		kpiInformationElementSrc = newKpiInformationElementSrc;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KpimodelPackage.KPI_MODEL_LINK__KPI_INFORMATION_ELEMENT_SRC, oldKpiInformationElementSrc, newKpiInformationElementSrc);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setKpiInformationElementSrc(KPIInformationElement newKpiInformationElementSrc) {
		if (newKpiInformationElementSrc != kpiInformationElementSrc) {
			NotificationChain msgs = null;
			if (kpiInformationElementSrc != null)
				msgs = ((InternalEObject)kpiInformationElementSrc).eInverseRemove(this, KpimodelPackage.KPI_INFORMATION_ELEMENT__KPI_MODEL_LINKS_SRC, KPIInformationElement.class, msgs);
			if (newKpiInformationElementSrc != null)
				msgs = ((InternalEObject)newKpiInformationElementSrc).eInverseAdd(this, KpimodelPackage.KPI_INFORMATION_ELEMENT__KPI_MODEL_LINKS_SRC, KPIInformationElement.class, msgs);
			msgs = basicSetKpiInformationElementSrc(newKpiInformationElementSrc, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.KPI_MODEL_LINK__KPI_INFORMATION_ELEMENT_SRC, newKpiInformationElementSrc, newKpiInformationElementSrc));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getRefs() {
		if (refs == null) {
			refs = new EObjectWithInverseResolvingEList(KPIModelLinkRef.class, this, KpimodelPackage.KPI_MODEL_LINK__REFS, KpimodelPackage.KPI_MODEL_LINK_REF__LINK);
		}
		return refs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public GRLspec getGrlspec() {
		if (eContainerFeatureID() != KpimodelPackage.KPI_MODEL_LINK__GRLSPEC) return null;
		return (GRLspec)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetGrlspec(GRLspec newGrlspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGrlspec, KpimodelPackage.KPI_MODEL_LINK__GRLSPEC, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGrlspec(GRLspec newGrlspec) {
		if (newGrlspec != eInternalContainer() || (eContainerFeatureID() != KpimodelPackage.KPI_MODEL_LINK__GRLSPEC && newGrlspec != null)) {
			if (EcoreUtil.isAncestor(this, newGrlspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGrlspec != null)
				msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__KPI_MODEL_LINKS, GRLspec.class, msgs);
			msgs = basicSetGrlspec(newGrlspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.KPI_MODEL_LINK__GRLSPEC, newGrlspec, newGrlspec));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Indicator getIndDest() {
		if (indDest != null && indDest.eIsProxy()) {
			InternalEObject oldIndDest = (InternalEObject)indDest;
			indDest = (Indicator)eResolveProxy(oldIndDest);
			if (indDest != oldIndDest) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, KpimodelPackage.KPI_MODEL_LINK__IND_DEST, oldIndDest, indDest));
			}
		}
		return indDest;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Indicator basicGetIndDest() {
		return indDest;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetIndDest(Indicator newIndDest, NotificationChain msgs) {
		Indicator oldIndDest = indDest;
		indDest = newIndDest;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KpimodelPackage.KPI_MODEL_LINK__IND_DEST, oldIndDest, newIndDest);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setIndDest(Indicator newIndDest) {
		if (newIndDest != indDest) {
			NotificationChain msgs = null;
			if (indDest != null)
				msgs = ((InternalEObject)indDest).eInverseRemove(this, KpimodelPackage.INDICATOR__KPI_MODEL_LINKS_DEST, Indicator.class, msgs);
			if (newIndDest != null)
				msgs = ((InternalEObject)newIndDest).eInverseAdd(this, KpimodelPackage.INDICATOR__KPI_MODEL_LINKS_DEST, Indicator.class, msgs);
			msgs = basicSetIndDest(newIndDest, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.KPI_MODEL_LINK__IND_DEST, newIndDest, newIndDest));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KpimodelPackage.KPI_MODEL_LINK__KPI_INFORMATION_ELEMENT_SRC:
				if (kpiInformationElementSrc != null)
					msgs = ((InternalEObject)kpiInformationElementSrc).eInverseRemove(this, KpimodelPackage.KPI_INFORMATION_ELEMENT__KPI_MODEL_LINKS_SRC, KPIInformationElement.class, msgs);
				return basicSetKpiInformationElementSrc((KPIInformationElement)otherEnd, msgs);
			case KpimodelPackage.KPI_MODEL_LINK__REFS:
				return ((InternalEList)getRefs()).basicAdd(otherEnd, msgs);
			case KpimodelPackage.KPI_MODEL_LINK__GRLSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGrlspec((GRLspec)otherEnd, msgs);
			case KpimodelPackage.KPI_MODEL_LINK__IND_DEST:
				if (indDest != null)
					msgs = ((InternalEObject)indDest).eInverseRemove(this, KpimodelPackage.INDICATOR__KPI_MODEL_LINKS_DEST, Indicator.class, msgs);
				return basicSetIndDest((Indicator)otherEnd, msgs);
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
			case KpimodelPackage.KPI_MODEL_LINK__KPI_INFORMATION_ELEMENT_SRC:
				return basicSetKpiInformationElementSrc(null, msgs);
			case KpimodelPackage.KPI_MODEL_LINK__REFS:
				return ((InternalEList)getRefs()).basicRemove(otherEnd, msgs);
			case KpimodelPackage.KPI_MODEL_LINK__GRLSPEC:
				return basicSetGrlspec(null, msgs);
			case KpimodelPackage.KPI_MODEL_LINK__IND_DEST:
				return basicSetIndDest(null, msgs);
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
			case KpimodelPackage.KPI_MODEL_LINK__GRLSPEC:
				return eInternalContainer().eInverseRemove(this, GrlPackage.GR_LSPEC__KPI_MODEL_LINKS, GRLspec.class, msgs);
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
			case KpimodelPackage.KPI_MODEL_LINK__KPI_INFORMATION_ELEMENT_SRC:
				if (resolve) return getKpiInformationElementSrc();
				return basicGetKpiInformationElementSrc();
			case KpimodelPackage.KPI_MODEL_LINK__REFS:
				return getRefs();
			case KpimodelPackage.KPI_MODEL_LINK__GRLSPEC:
				return getGrlspec();
			case KpimodelPackage.KPI_MODEL_LINK__IND_DEST:
				if (resolve) return getIndDest();
				return basicGetIndDest();
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
			case KpimodelPackage.KPI_MODEL_LINK__KPI_INFORMATION_ELEMENT_SRC:
				setKpiInformationElementSrc((KPIInformationElement)newValue);
				return;
			case KpimodelPackage.KPI_MODEL_LINK__REFS:
				getRefs().clear();
				getRefs().addAll((Collection)newValue);
				return;
			case KpimodelPackage.KPI_MODEL_LINK__GRLSPEC:
				setGrlspec((GRLspec)newValue);
				return;
			case KpimodelPackage.KPI_MODEL_LINK__IND_DEST:
				setIndDest((Indicator)newValue);
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
			case KpimodelPackage.KPI_MODEL_LINK__KPI_INFORMATION_ELEMENT_SRC:
				setKpiInformationElementSrc((KPIInformationElement)null);
				return;
			case KpimodelPackage.KPI_MODEL_LINK__REFS:
				getRefs().clear();
				return;
			case KpimodelPackage.KPI_MODEL_LINK__GRLSPEC:
				setGrlspec((GRLspec)null);
				return;
			case KpimodelPackage.KPI_MODEL_LINK__IND_DEST:
				setIndDest((Indicator)null);
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
			case KpimodelPackage.KPI_MODEL_LINK__KPI_INFORMATION_ELEMENT_SRC:
				return kpiInformationElementSrc != null;
			case KpimodelPackage.KPI_MODEL_LINK__REFS:
				return refs != null && !refs.isEmpty();
			case KpimodelPackage.KPI_MODEL_LINK__GRLSPEC:
				return getGrlspec() != null;
			case KpimodelPackage.KPI_MODEL_LINK__IND_DEST:
				return indDest != null;
		}
		return super.eIsSet(featureID);
	}

} //KPIModelLinkImpl