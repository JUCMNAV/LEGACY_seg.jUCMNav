/**
 */
package grl.impl;

import fm.ReuseLink;

import grl.EvaluationStrategy;
import grl.GrlPackage;
import grl.ReusedStrategy;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reused Strategy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link grl.impl.ReusedStrategyImpl#getReusingstrategies <em>Reusingstrategies</em>}</li>
 *   <li>{@link grl.impl.ReusedStrategyImpl#getReuseLinkInFM <em>Reuse Link In FM</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReusedStrategyImpl extends EvaluationStrategyImpl implements ReusedStrategy {
	/**
	 * The cached value of the '{@link #getReusingstrategies() <em>Reusingstrategies</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReusingstrategies()
	 * @generated
	 * @ordered
	 */
	protected EList reusingstrategies;

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
	protected ReusedStrategyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return GrlPackage.Literals.REUSED_STRATEGY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getReusingstrategies() {
		if (reusingstrategies == null) {
			reusingstrategies = new EObjectWithInverseResolvingEList.ManyInverse(EvaluationStrategy.class, this, GrlPackage.REUSED_STRATEGY__REUSINGSTRATEGIES, GrlPackage.EVALUATION_STRATEGY__REUSED_STRATEGIES);
		}
		return reusingstrategies;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.REUSED_STRATEGY__REUSE_LINK_IN_FM, oldReuseLinkInFM, reuseLinkInFM));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.REUSED_STRATEGY__REUSE_LINK_IN_FM, oldReuseLinkInFM, reuseLinkInFM));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GrlPackage.REUSED_STRATEGY__REUSINGSTRATEGIES:
				return ((InternalEList)getReusingstrategies()).basicAdd(otherEnd, msgs);
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
			case GrlPackage.REUSED_STRATEGY__REUSINGSTRATEGIES:
				return ((InternalEList)getReusingstrategies()).basicRemove(otherEnd, msgs);
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
			case GrlPackage.REUSED_STRATEGY__REUSINGSTRATEGIES:
				return getReusingstrategies();
			case GrlPackage.REUSED_STRATEGY__REUSE_LINK_IN_FM:
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
			case GrlPackage.REUSED_STRATEGY__REUSINGSTRATEGIES:
				getReusingstrategies().clear();
				getReusingstrategies().addAll((Collection)newValue);
				return;
			case GrlPackage.REUSED_STRATEGY__REUSE_LINK_IN_FM:
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
			case GrlPackage.REUSED_STRATEGY__REUSINGSTRATEGIES:
				getReusingstrategies().clear();
				return;
			case GrlPackage.REUSED_STRATEGY__REUSE_LINK_IN_FM:
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
			case GrlPackage.REUSED_STRATEGY__REUSINGSTRATEGIES:
				return reusingstrategies != null && !reusingstrategies.isEmpty();
			case GrlPackage.REUSED_STRATEGY__REUSE_LINK_IN_FM:
				return reuseLinkInFM != null;
		}
		return super.eIsSet(featureID);
	}

} //ReusedStrategyImpl
