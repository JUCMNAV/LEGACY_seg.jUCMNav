/**
 */
package fm.impl;

import fm.FmPackage;
import fm.ReuseLink;

import grl.impl.ContributionImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reuse Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fm.impl.ReuseLinkImpl#getReuseLinkInFM <em>Reuse Link In FM</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReuseLinkImpl extends ContributionImpl implements ReuseLink {
	/**
	 * The cached value of the '{@link #getReuseLinkInFM() <em>Reuse Link In FM</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReuseLinkInFM()
	 * @generated
	 * @ordered
	 */
	protected ReuseLink reuseLinkInFM;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReuseLinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FmPackage.Literals.REUSE_LINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReuseLink getReuseLinkInFM() {
		if (reuseLinkInFM != null && reuseLinkInFM.eIsProxy()) {
			InternalEObject oldReuseLinkInFM = (InternalEObject)reuseLinkInFM;
			reuseLinkInFM = (ReuseLink)eResolveProxy(oldReuseLinkInFM);
			if (reuseLinkInFM != oldReuseLinkInFM) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FmPackage.REUSE_LINK__REUSE_LINK_IN_FM, oldReuseLinkInFM, reuseLinkInFM));
			}
		}
		return reuseLinkInFM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReuseLink basicGetReuseLinkInFM() {
		return reuseLinkInFM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReuseLinkInFM(ReuseLink newReuseLinkInFM) {
		ReuseLink oldReuseLinkInFM = reuseLinkInFM;
		reuseLinkInFM = newReuseLinkInFM;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FmPackage.REUSE_LINK__REUSE_LINK_IN_FM, oldReuseLinkInFM, reuseLinkInFM));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FmPackage.REUSE_LINK__REUSE_LINK_IN_FM:
				if (resolve) return getReuseLinkInFM();
				return basicGetReuseLinkInFM();
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
			case FmPackage.REUSE_LINK__REUSE_LINK_IN_FM:
				setReuseLinkInFM((ReuseLink)newValue);
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
			case FmPackage.REUSE_LINK__REUSE_LINK_IN_FM:
				setReuseLinkInFM((ReuseLink)null);
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
			case FmPackage.REUSE_LINK__REUSE_LINK_IN_FM:
				return reuseLinkInFM != null;
		}
		return super.eIsSet(featureID);
	}

} //ReuseLinkImpl
