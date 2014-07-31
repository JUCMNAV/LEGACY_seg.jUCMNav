/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.ContributionChange;
import grl.ContributionRange;
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
 * An implementation of the model object '<em><b>Contribution Range</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.ContributionRangeImpl#getStart <em>Start</em>}</li>
 *   <li>{@link grl.impl.ContributionRangeImpl#getEnd <em>End</em>}</li>
 *   <li>{@link grl.impl.ContributionRangeImpl#getStep <em>Step</em>}</li>
 *   <li>{@link grl.impl.ContributionRangeImpl#getChange <em>Change</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContributionRangeImpl extends MinimalEObjectImpl.Container implements ContributionRange {
	/**
	 * The default value of the '{@link #getStart() <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected static final int START_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getStart() <em>Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected int start = START_EDEFAULT;

	/**
	 * The default value of the '{@link #getEnd() <em>End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnd()
	 * @generated
	 * @ordered
	 */
	protected static final int END_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getEnd() <em>End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnd()
	 * @generated
	 * @ordered
	 */
	protected int end = END_EDEFAULT;

	/**
	 * The default value of the '{@link #getStep() <em>Step</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStep()
	 * @generated
	 * @ordered
	 */
	protected static final int STEP_EDEFAULT = 1;

	/**
	 * The cached value of the '{@link #getStep() <em>Step</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStep()
	 * @generated
	 * @ordered
	 */
	protected int step = STEP_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContributionRangeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return GrlPackage.Literals.CONTRIBUTION_RANGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getStart() {
		return start;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStart(int newStart) {
		int oldStart = start;
		start = newStart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.CONTRIBUTION_RANGE__START, oldStart, start));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnd(int newEnd) {
		int oldEnd = end;
		end = newEnd;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.CONTRIBUTION_RANGE__END, oldEnd, end));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getStep() {
		return step;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStep(int newStep) {
		int oldStep = step;
		step = newStep;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.CONTRIBUTION_RANGE__STEP, oldStep, step));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContributionChange getChange() {
		if (eContainerFeatureID() != GrlPackage.CONTRIBUTION_RANGE__CHANGE) return null;
		return (ContributionChange)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetChange(ContributionChange newChange, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newChange, GrlPackage.CONTRIBUTION_RANGE__CHANGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChange(ContributionChange newChange) {
		if (newChange != eInternalContainer() || (eContainerFeatureID() != GrlPackage.CONTRIBUTION_RANGE__CHANGE && newChange != null)) {
			if (EcoreUtil.isAncestor(this, newChange))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newChange != null)
				msgs = ((InternalEObject)newChange).eInverseAdd(this, GrlPackage.CONTRIBUTION_CHANGE__CONTRIB_RANGE, ContributionChange.class, msgs);
			msgs = basicSetChange(newChange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.CONTRIBUTION_RANGE__CHANGE, newChange, newChange));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GrlPackage.CONTRIBUTION_RANGE__CHANGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetChange((ContributionChange)otherEnd, msgs);
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
			case GrlPackage.CONTRIBUTION_RANGE__CHANGE:
				return basicSetChange(null, msgs);
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
			case GrlPackage.CONTRIBUTION_RANGE__CHANGE:
				return eInternalContainer().eInverseRemove(this, GrlPackage.CONTRIBUTION_CHANGE__CONTRIB_RANGE, ContributionChange.class, msgs);
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
			case GrlPackage.CONTRIBUTION_RANGE__START:
				return new Integer(getStart());
			case GrlPackage.CONTRIBUTION_RANGE__END:
				return new Integer(getEnd());
			case GrlPackage.CONTRIBUTION_RANGE__STEP:
				return new Integer(getStep());
			case GrlPackage.CONTRIBUTION_RANGE__CHANGE:
				return getChange();
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
			case GrlPackage.CONTRIBUTION_RANGE__START:
				setStart(((Integer)newValue).intValue());
				return;
			case GrlPackage.CONTRIBUTION_RANGE__END:
				setEnd(((Integer)newValue).intValue());
				return;
			case GrlPackage.CONTRIBUTION_RANGE__STEP:
				setStep(((Integer)newValue).intValue());
				return;
			case GrlPackage.CONTRIBUTION_RANGE__CHANGE:
				setChange((ContributionChange)newValue);
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
			case GrlPackage.CONTRIBUTION_RANGE__START:
				setStart(START_EDEFAULT);
				return;
			case GrlPackage.CONTRIBUTION_RANGE__END:
				setEnd(END_EDEFAULT);
				return;
			case GrlPackage.CONTRIBUTION_RANGE__STEP:
				setStep(STEP_EDEFAULT);
				return;
			case GrlPackage.CONTRIBUTION_RANGE__CHANGE:
				setChange((ContributionChange)null);
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
			case GrlPackage.CONTRIBUTION_RANGE__START:
				return start != START_EDEFAULT;
			case GrlPackage.CONTRIBUTION_RANGE__END:
				return end != END_EDEFAULT;
			case GrlPackage.CONTRIBUTION_RANGE__STEP:
				return step != STEP_EDEFAULT;
			case GrlPackage.CONTRIBUTION_RANGE__CHANGE:
				return getChange() != null;
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
		result.append(" (start: ");
		result.append(start);
		result.append(", end: ");
		result.append(end);
		result.append(", step: ");
		result.append(step);
		result.append(')');
		return result.toString();
	}

} //ContributionRangeImpl
