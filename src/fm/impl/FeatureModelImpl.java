/**
 */
package fm.impl;

import ca.mcgill.sel.core.COREFeatureModel;

import fm.FeatureModel;
import fm.FmPackage;

import grl.GRLspec;
import grl.GrlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fm.impl.FeatureModelImpl#getGrlspec <em>Grlspec</em>}</li>
 *   <li>{@link fm.impl.FeatureModelImpl#getCoreFeatureModel <em>Core Feature Model</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FeatureModelImpl extends MinimalEObjectImpl.Container implements FeatureModel {
	/**
	 * The cached value of the '{@link #getCoreFeatureModel() <em>Core Feature Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoreFeatureModel()
	 * @generated
	 * @ordered
	 */
	protected COREFeatureModel coreFeatureModel;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FmPackage.Literals.FEATURE_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GRLspec getGrlspec() {
		if (eContainerFeatureID() != FmPackage.FEATURE_MODEL__GRLSPEC) return null;
		return (GRLspec)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGrlspec(GRLspec newGrlspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGrlspec, FmPackage.FEATURE_MODEL__GRLSPEC, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGrlspec(GRLspec newGrlspec) {
		if (newGrlspec != eInternalContainer() || (eContainerFeatureID() != FmPackage.FEATURE_MODEL__GRLSPEC && newGrlspec != null)) {
			if (EcoreUtil.isAncestor(this, newGrlspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGrlspec != null)
				msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__FEATURE_MODEL, GRLspec.class, msgs);
			msgs = basicSetGrlspec(newGrlspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FmPackage.FEATURE_MODEL__GRLSPEC, newGrlspec, newGrlspec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public COREFeatureModel getCoreFeatureModel() {
		if (coreFeatureModel != null && coreFeatureModel.eIsProxy()) {
			InternalEObject oldCoreFeatureModel = (InternalEObject)coreFeatureModel;
			coreFeatureModel = (COREFeatureModel)eResolveProxy(oldCoreFeatureModel);
			if (coreFeatureModel != oldCoreFeatureModel) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FmPackage.FEATURE_MODEL__CORE_FEATURE_MODEL, oldCoreFeatureModel, coreFeatureModel));
			}
		}
		return coreFeatureModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public COREFeatureModel basicGetCoreFeatureModel() {
		return coreFeatureModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCoreFeatureModel(COREFeatureModel newCoreFeatureModel) {
		COREFeatureModel oldCoreFeatureModel = coreFeatureModel;
		coreFeatureModel = newCoreFeatureModel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FmPackage.FEATURE_MODEL__CORE_FEATURE_MODEL, oldCoreFeatureModel, coreFeatureModel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FmPackage.FEATURE_MODEL__GRLSPEC:
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
			case FmPackage.FEATURE_MODEL__GRLSPEC:
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
			case FmPackage.FEATURE_MODEL__GRLSPEC:
				return eInternalContainer().eInverseRemove(this, GrlPackage.GR_LSPEC__FEATURE_MODEL, GRLspec.class, msgs);
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
			case FmPackage.FEATURE_MODEL__GRLSPEC:
				return getGrlspec();
			case FmPackage.FEATURE_MODEL__CORE_FEATURE_MODEL:
				if (resolve) return getCoreFeatureModel();
				return basicGetCoreFeatureModel();
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
			case FmPackage.FEATURE_MODEL__GRLSPEC:
				setGrlspec((GRLspec)newValue);
				return;
			case FmPackage.FEATURE_MODEL__CORE_FEATURE_MODEL:
				setCoreFeatureModel((COREFeatureModel)newValue);
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
			case FmPackage.FEATURE_MODEL__GRLSPEC:
				setGrlspec((GRLspec)null);
				return;
			case FmPackage.FEATURE_MODEL__CORE_FEATURE_MODEL:
				setCoreFeatureModel((COREFeatureModel)null);
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
			case FmPackage.FEATURE_MODEL__GRLSPEC:
				return getGrlspec() != null;
			case FmPackage.FEATURE_MODEL__CORE_FEATURE_MODEL:
				return coreFeatureModel != null;
		}
		return super.eIsSet(featureID);
	}

} //FeatureModelImpl
