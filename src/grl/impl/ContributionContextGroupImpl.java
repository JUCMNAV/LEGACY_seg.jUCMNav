/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.ContributionContext;
import grl.ContributionContextGroup;
import grl.GRLspec;
import grl.GrlPackage;

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
 * An implementation of the model object '<em><b>Contribution Context Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.ContributionContextGroupImpl#getGrlspec <em>Grlspec</em>}</li>
 *   <li>{@link grl.impl.ContributionContextGroupImpl#getContribs <em>Contribs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContributionContextGroupImpl extends GRLmodelElementImpl implements ContributionContextGroup {
	/**
	 * The cached value of the '{@link #getContribs() <em>Contribs</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContribs()
	 * @generated
	 * @ordered
	 */
	protected EList contribs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContributionContextGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return GrlPackage.Literals.CONTRIBUTION_CONTEXT_GROUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GRLspec getGrlspec() {
		if (eContainerFeatureID() != GrlPackage.CONTRIBUTION_CONTEXT_GROUP__GRLSPEC) return null;
		return (GRLspec)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGrlspec(GRLspec newGrlspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGrlspec, GrlPackage.CONTRIBUTION_CONTEXT_GROUP__GRLSPEC, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGrlspec(GRLspec newGrlspec) {
		if (newGrlspec != eInternalContainer() || (eContainerFeatureID() != GrlPackage.CONTRIBUTION_CONTEXT_GROUP__GRLSPEC && newGrlspec != null)) {
			if (EcoreUtil.isAncestor(this, newGrlspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGrlspec != null)
				msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__CONTRIBUTION_GROUPS, GRLspec.class, msgs);
			msgs = basicSetGrlspec(newGrlspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.CONTRIBUTION_CONTEXT_GROUP__GRLSPEC, newGrlspec, newGrlspec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getContribs() {
		if (contribs == null) {
			contribs = new EObjectWithInverseResolvingEList.ManyInverse(ContributionContext.class, this, GrlPackage.CONTRIBUTION_CONTEXT_GROUP__CONTRIBS, GrlPackage.CONTRIBUTION_CONTEXT__GROUPS);
		}
		return contribs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GrlPackage.CONTRIBUTION_CONTEXT_GROUP__GRLSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGrlspec((GRLspec)otherEnd, msgs);
			case GrlPackage.CONTRIBUTION_CONTEXT_GROUP__CONTRIBS:
				return ((InternalEList)getContribs()).basicAdd(otherEnd, msgs);
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
			case GrlPackage.CONTRIBUTION_CONTEXT_GROUP__GRLSPEC:
				return basicSetGrlspec(null, msgs);
			case GrlPackage.CONTRIBUTION_CONTEXT_GROUP__CONTRIBS:
				return ((InternalEList)getContribs()).basicRemove(otherEnd, msgs);
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
			case GrlPackage.CONTRIBUTION_CONTEXT_GROUP__GRLSPEC:
				return eInternalContainer().eInverseRemove(this, GrlPackage.GR_LSPEC__CONTRIBUTION_GROUPS, GRLspec.class, msgs);
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
			case GrlPackage.CONTRIBUTION_CONTEXT_GROUP__GRLSPEC:
				return getGrlspec();
			case GrlPackage.CONTRIBUTION_CONTEXT_GROUP__CONTRIBS:
				return getContribs();
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
			case GrlPackage.CONTRIBUTION_CONTEXT_GROUP__GRLSPEC:
				setGrlspec((GRLspec)newValue);
				return;
			case GrlPackage.CONTRIBUTION_CONTEXT_GROUP__CONTRIBS:
				getContribs().clear();
				getContribs().addAll((Collection)newValue);
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
			case GrlPackage.CONTRIBUTION_CONTEXT_GROUP__GRLSPEC:
				setGrlspec((GRLspec)null);
				return;
			case GrlPackage.CONTRIBUTION_CONTEXT_GROUP__CONTRIBS:
				getContribs().clear();
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
			case GrlPackage.CONTRIBUTION_CONTEXT_GROUP__GRLSPEC:
				return getGrlspec() != null;
			case GrlPackage.CONTRIBUTION_CONTEXT_GROUP__CONTRIBS:
				return contribs != null && !contribs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ContributionContextGroupImpl
