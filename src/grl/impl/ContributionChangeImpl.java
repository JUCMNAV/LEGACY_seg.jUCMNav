/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Contribution;
import grl.ContributionChange;
import grl.ContributionContext;
import grl.ContributionRange;
import grl.ContributionType;
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
 * An implementation of the model object '<em><b>Contribution Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.ContributionChangeImpl#getNewContribution <em>New Contribution</em>}</li>
 *   <li>{@link grl.impl.ContributionChangeImpl#getNewQuantitativeContribution <em>New Quantitative Contribution</em>}</li>
 *   <li>{@link grl.impl.ContributionChangeImpl#getContext <em>Context</em>}</li>
 *   <li>{@link grl.impl.ContributionChangeImpl#getContribution <em>Contribution</em>}</li>
 *   <li>{@link grl.impl.ContributionChangeImpl#getContribRange <em>Contrib Range</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContributionChangeImpl extends MinimalEObjectImpl.Container implements ContributionChange {
	/**
	 * The default value of the '{@link #getNewContribution() <em>New Contribution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewContribution()
	 * @generated
	 * @ordered
	 */
	protected static final ContributionType NEW_CONTRIBUTION_EDEFAULT = ContributionType.UNKNOWN_LITERAL;

	/**
	 * The cached value of the '{@link #getNewContribution() <em>New Contribution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewContribution()
	 * @generated
	 * @ordered
	 */
	protected ContributionType newContribution = NEW_CONTRIBUTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getNewQuantitativeContribution() <em>New Quantitative Contribution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewQuantitativeContribution()
	 * @generated
	 * @ordered
	 */
	protected static final int NEW_QUANTITATIVE_CONTRIBUTION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNewQuantitativeContribution() <em>New Quantitative Contribution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNewQuantitativeContribution()
	 * @generated
	 * @ordered
	 */
	protected int newQuantitativeContribution = NEW_QUANTITATIVE_CONTRIBUTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getContribution() <em>Contribution</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContribution()
	 * @generated
	 * @ordered
	 */
	protected Contribution contribution;

	/**
	 * The cached value of the '{@link #getContribRange() <em>Contrib Range</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContribRange()
	 * @generated
	 * @ordered
	 */
	protected ContributionRange contribRange;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContributionChangeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return GrlPackage.Literals.CONTRIBUTION_CHANGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContributionType getNewContribution() {
		return newContribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNewContribution(ContributionType newNewContribution) {
		ContributionType oldNewContribution = newContribution;
		newContribution = newNewContribution == null ? NEW_CONTRIBUTION_EDEFAULT : newNewContribution;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.CONTRIBUTION_CHANGE__NEW_CONTRIBUTION, oldNewContribution, newContribution));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNewQuantitativeContribution() {
		return newQuantitativeContribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNewQuantitativeContribution(int newNewQuantitativeContribution) {
		int oldNewQuantitativeContribution = newQuantitativeContribution;
		newQuantitativeContribution = newNewQuantitativeContribution;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.CONTRIBUTION_CHANGE__NEW_QUANTITATIVE_CONTRIBUTION, oldNewQuantitativeContribution, newQuantitativeContribution));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContributionContext getContext() {
		if (eContainerFeatureID() != GrlPackage.CONTRIBUTION_CHANGE__CONTEXT) return null;
		return (ContributionContext)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContext(ContributionContext newContext, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newContext, GrlPackage.CONTRIBUTION_CHANGE__CONTEXT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContext(ContributionContext newContext) {
		if (newContext != eInternalContainer() || (eContainerFeatureID() != GrlPackage.CONTRIBUTION_CHANGE__CONTEXT && newContext != null)) {
			if (EcoreUtil.isAncestor(this, newContext))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newContext != null)
				msgs = ((InternalEObject)newContext).eInverseAdd(this, GrlPackage.CONTRIBUTION_CONTEXT__CHANGES, ContributionContext.class, msgs);
			msgs = basicSetContext(newContext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.CONTRIBUTION_CHANGE__CONTEXT, newContext, newContext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Contribution getContribution() {
		if (contribution != null && contribution.eIsProxy()) {
			InternalEObject oldContribution = (InternalEObject)contribution;
			contribution = (Contribution)eResolveProxy(oldContribution);
			if (contribution != oldContribution) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.CONTRIBUTION_CHANGE__CONTRIBUTION, oldContribution, contribution));
			}
		}
		return contribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Contribution basicGetContribution() {
		return contribution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContribution(Contribution newContribution) {
		Contribution oldContribution = contribution;
		contribution = newContribution;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.CONTRIBUTION_CHANGE__CONTRIBUTION, oldContribution, contribution));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContributionRange getContribRange() {
		return contribRange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContribRange(ContributionRange newContribRange, NotificationChain msgs) {
		ContributionRange oldContribRange = contribRange;
		contribRange = newContribRange;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.CONTRIBUTION_CHANGE__CONTRIB_RANGE, oldContribRange, newContribRange);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContribRange(ContributionRange newContribRange) {
		if (newContribRange != contribRange) {
			NotificationChain msgs = null;
			if (contribRange != null)
				msgs = ((InternalEObject)contribRange).eInverseRemove(this, GrlPackage.CONTRIBUTION_RANGE__CHANGE, ContributionRange.class, msgs);
			if (newContribRange != null)
				msgs = ((InternalEObject)newContribRange).eInverseAdd(this, GrlPackage.CONTRIBUTION_RANGE__CHANGE, ContributionRange.class, msgs);
			msgs = basicSetContribRange(newContribRange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.CONTRIBUTION_CHANGE__CONTRIB_RANGE, newContribRange, newContribRange));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GrlPackage.CONTRIBUTION_CHANGE__CONTEXT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetContext((ContributionContext)otherEnd, msgs);
			case GrlPackage.CONTRIBUTION_CHANGE__CONTRIB_RANGE:
				if (contribRange != null)
					msgs = ((InternalEObject)contribRange).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GrlPackage.CONTRIBUTION_CHANGE__CONTRIB_RANGE, null, msgs);
				return basicSetContribRange((ContributionRange)otherEnd, msgs);
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
			case GrlPackage.CONTRIBUTION_CHANGE__CONTEXT:
				return basicSetContext(null, msgs);
			case GrlPackage.CONTRIBUTION_CHANGE__CONTRIB_RANGE:
				return basicSetContribRange(null, msgs);
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
			case GrlPackage.CONTRIBUTION_CHANGE__CONTEXT:
				return eInternalContainer().eInverseRemove(this, GrlPackage.CONTRIBUTION_CONTEXT__CHANGES, ContributionContext.class, msgs);
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
			case GrlPackage.CONTRIBUTION_CHANGE__NEW_CONTRIBUTION:
				return getNewContribution();
			case GrlPackage.CONTRIBUTION_CHANGE__NEW_QUANTITATIVE_CONTRIBUTION:
				return new Integer(getNewQuantitativeContribution());
			case GrlPackage.CONTRIBUTION_CHANGE__CONTEXT:
				return getContext();
			case GrlPackage.CONTRIBUTION_CHANGE__CONTRIBUTION:
				if (resolve) return getContribution();
				return basicGetContribution();
			case GrlPackage.CONTRIBUTION_CHANGE__CONTRIB_RANGE:
				return getContribRange();
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
			case GrlPackage.CONTRIBUTION_CHANGE__NEW_CONTRIBUTION:
				setNewContribution((ContributionType)newValue);
				return;
			case GrlPackage.CONTRIBUTION_CHANGE__NEW_QUANTITATIVE_CONTRIBUTION:
				setNewQuantitativeContribution(((Integer)newValue).intValue());
				return;
			case GrlPackage.CONTRIBUTION_CHANGE__CONTEXT:
				setContext((ContributionContext)newValue);
				return;
			case GrlPackage.CONTRIBUTION_CHANGE__CONTRIBUTION:
				setContribution((Contribution)newValue);
				return;
			case GrlPackage.CONTRIBUTION_CHANGE__CONTRIB_RANGE:
				setContribRange((ContributionRange)newValue);
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
			case GrlPackage.CONTRIBUTION_CHANGE__NEW_CONTRIBUTION:
				setNewContribution(NEW_CONTRIBUTION_EDEFAULT);
				return;
			case GrlPackage.CONTRIBUTION_CHANGE__NEW_QUANTITATIVE_CONTRIBUTION:
				setNewQuantitativeContribution(NEW_QUANTITATIVE_CONTRIBUTION_EDEFAULT);
				return;
			case GrlPackage.CONTRIBUTION_CHANGE__CONTEXT:
				setContext((ContributionContext)null);
				return;
			case GrlPackage.CONTRIBUTION_CHANGE__CONTRIBUTION:
				setContribution((Contribution)null);
				return;
			case GrlPackage.CONTRIBUTION_CHANGE__CONTRIB_RANGE:
				setContribRange((ContributionRange)null);
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
			case GrlPackage.CONTRIBUTION_CHANGE__NEW_CONTRIBUTION:
				return newContribution != NEW_CONTRIBUTION_EDEFAULT;
			case GrlPackage.CONTRIBUTION_CHANGE__NEW_QUANTITATIVE_CONTRIBUTION:
				return newQuantitativeContribution != NEW_QUANTITATIVE_CONTRIBUTION_EDEFAULT;
			case GrlPackage.CONTRIBUTION_CHANGE__CONTEXT:
				return getContext() != null;
			case GrlPackage.CONTRIBUTION_CHANGE__CONTRIBUTION:
				return contribution != null;
			case GrlPackage.CONTRIBUTION_CHANGE__CONTRIB_RANGE:
				return contribRange != null;
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
		result.append(" (newContribution: ");
		result.append(newContribution);
		result.append(", newQuantitativeContribution: ");
		result.append(newQuantitativeContribution);
		result.append(')');
		return result.toString();
	}

} //ContributionChangeImpl
