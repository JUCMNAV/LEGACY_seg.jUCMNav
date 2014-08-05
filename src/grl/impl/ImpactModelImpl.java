/**
 */
package grl.impl;

import ca.mcgill.sel.core.impl.COREImpactModelImpl;

import grl.GRLspec;
import grl.GrlPackage;
import grl.ImpactModel;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Impact Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.ImpactModelImpl#getGrlspec <em>Grlspec</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ImpactModelImpl extends COREImpactModelImpl implements ImpactModel {
	/**
	 * The cached value of the '{@link #getGrlspec() <em>Grlspec</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGrlspec()
	 * @generated
	 * @ordered
	 */
	protected GRLspec grlspec;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ImpactModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return GrlPackage.Literals.IMPACT_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GRLspec getGrlspec() {
		if (grlspec != null && grlspec.eIsProxy()) {
			InternalEObject oldGrlspec = (InternalEObject)grlspec;
			grlspec = (GRLspec)eResolveProxy(oldGrlspec);
			if (grlspec != oldGrlspec) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.IMPACT_MODEL__GRLSPEC, oldGrlspec, grlspec));
			}
		}
		return grlspec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GRLspec basicGetGrlspec() {
		return grlspec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGrlspec(GRLspec newGrlspec, NotificationChain msgs) {
		GRLspec oldGrlspec = grlspec;
		grlspec = newGrlspec;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.IMPACT_MODEL__GRLSPEC, oldGrlspec, newGrlspec);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGrlspec(GRLspec newGrlspec) {
		if (newGrlspec != grlspec) {
			NotificationChain msgs = null;
			if (grlspec != null)
				msgs = ((InternalEObject)grlspec).eInverseRemove(this, GrlPackage.GR_LSPEC__IMPACT_MODEL, GRLspec.class, msgs);
			if (newGrlspec != null)
				msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__IMPACT_MODEL, GRLspec.class, msgs);
			msgs = basicSetGrlspec(newGrlspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.IMPACT_MODEL__GRLSPEC, newGrlspec, newGrlspec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GrlPackage.IMPACT_MODEL__GRLSPEC:
				if (grlspec != null)
					msgs = ((InternalEObject)grlspec).eInverseRemove(this, GrlPackage.GR_LSPEC__IMPACT_MODEL, GRLspec.class, msgs);
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
			case GrlPackage.IMPACT_MODEL__GRLSPEC:
				return basicSetGrlspec(null, msgs);
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
			case GrlPackage.IMPACT_MODEL__GRLSPEC:
				if (resolve) return getGrlspec();
				return basicGetGrlspec();
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
			case GrlPackage.IMPACT_MODEL__GRLSPEC:
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
			case GrlPackage.IMPACT_MODEL__GRLSPEC:
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
			case GrlPackage.IMPACT_MODEL__GRLSPEC:
				return grlspec != null;
		}
		return super.eIsSet(featureID);
	}

} //ImpactModelImpl
