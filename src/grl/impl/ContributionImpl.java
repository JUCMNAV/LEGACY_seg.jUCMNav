/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import ca.mcgill.sel.core.COREContribution;
import grl.Contribution;
import grl.ContributionType;
import grl.GrlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Contribution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link grl.impl.ContributionImpl#getContribution <em>Contribution</em>}</li>
 *   <li>{@link grl.impl.ContributionImpl#getQuantitativeContribution <em>Quantitative Contribution</em>}</li>
 *   <li>{@link grl.impl.ContributionImpl#isCorrelation <em>Correlation</em>}</li>
 *   <li>{@link grl.impl.ContributionImpl#getCoreContribution <em>Core Contribution</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContributionImpl extends ElementLinkImpl implements Contribution {
    /**
	 * The default value of the '{@link #getContribution() <em>Contribution</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getContribution()
	 * @generated
	 * @ordered
	 */
    protected static final ContributionType CONTRIBUTION_EDEFAULT = ContributionType.HELP_LITERAL;

    /**
	 * The cached value of the '{@link #getContribution() <em>Contribution</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getContribution()
	 * @generated
	 * @ordered
	 */
    protected ContributionType contribution = CONTRIBUTION_EDEFAULT;

    /**
	 * The default value of the '{@link #getQuantitativeContribution() <em>Quantitative Contribution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantitativeContribution()
	 * @generated
	 * @ordered
	 */
	protected static final int QUANTITATIVE_CONTRIBUTION_EDEFAULT = 25;

				/**
	 * The cached value of the '{@link #getQuantitativeContribution() <em>Quantitative Contribution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuantitativeContribution()
	 * @generated
	 * @ordered
	 */
	protected int quantitativeContribution = QUANTITATIVE_CONTRIBUTION_EDEFAULT;

				/**
	 * The default value of the '{@link #isCorrelation() <em>Correlation</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isCorrelation()
	 * @generated
	 * @ordered
	 */
    protected static final boolean CORRELATION_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isCorrelation() <em>Correlation</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isCorrelation()
	 * @generated
	 * @ordered
	 */
    protected boolean correlation = CORRELATION_EDEFAULT;

    /**
	 * The cached value of the '{@link #getCoreContribution() <em>Core Contribution</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoreContribution()
	 * @generated
	 * @ordered
	 */
	protected COREContribution coreContribution;

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ContributionImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return GrlPackage.Literals.CONTRIBUTION;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ContributionType getContribution() {
		return contribution;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setContribution(ContributionType newContribution) {
		ContributionType oldContribution = contribution;
		contribution = newContribution == null ? CONTRIBUTION_EDEFAULT : newContribution;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.CONTRIBUTION__CONTRIBUTION, oldContribution, contribution));
	}		
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getQuantitativeContribution() {
		return quantitativeContribution;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQuantitativeContribution(int newQuantitativeContribution) {
		int oldQuantitativeContribution = quantitativeContribution;
		quantitativeContribution = newQuantitativeContribution;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.CONTRIBUTION__QUANTITATIVE_CONTRIBUTION, oldQuantitativeContribution, quantitativeContribution));
	}
	
				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isCorrelation() {
		return correlation;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCorrelation(boolean newCorrelation) {
		boolean oldCorrelation = correlation;
		correlation = newCorrelation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.CONTRIBUTION__CORRELATION, oldCorrelation, correlation));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public COREContribution getCoreContribution() {
		if (coreContribution != null && coreContribution.eIsProxy()) {
			InternalEObject oldCoreContribution = (InternalEObject)coreContribution;
			coreContribution = (COREContribution)eResolveProxy(oldCoreContribution);
			if (coreContribution != oldCoreContribution) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.CONTRIBUTION__CORE_CONTRIBUTION, oldCoreContribution, coreContribution));
			}
		}
		return coreContribution;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public COREContribution basicGetCoreContribution() {
		return coreContribution;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCoreContribution(COREContribution newCoreContribution) {
		COREContribution oldCoreContribution = coreContribution;
		coreContribution = newCoreContribution;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.CONTRIBUTION__CORE_CONTRIBUTION, oldCoreContribution, coreContribution));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GrlPackage.CONTRIBUTION__CONTRIBUTION:
				return getContribution();
			case GrlPackage.CONTRIBUTION__QUANTITATIVE_CONTRIBUTION:
				return new Integer(getQuantitativeContribution());
			case GrlPackage.CONTRIBUTION__CORRELATION:
				return isCorrelation() ? Boolean.TRUE : Boolean.FALSE;
			case GrlPackage.CONTRIBUTION__CORE_CONTRIBUTION:
				if (resolve) return getCoreContribution();
				return basicGetCoreContribution();
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
			case GrlPackage.CONTRIBUTION__CONTRIBUTION:
				setContribution((ContributionType)newValue);
				return;
			case GrlPackage.CONTRIBUTION__QUANTITATIVE_CONTRIBUTION:
				setQuantitativeContribution(((Integer)newValue).intValue());
				return;
			case GrlPackage.CONTRIBUTION__CORRELATION:
				setCorrelation(((Boolean)newValue).booleanValue());
				return;
			case GrlPackage.CONTRIBUTION__CORE_CONTRIBUTION:
				setCoreContribution((COREContribution)newValue);
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
			case GrlPackage.CONTRIBUTION__CONTRIBUTION:
				setContribution(CONTRIBUTION_EDEFAULT);
				return;
			case GrlPackage.CONTRIBUTION__QUANTITATIVE_CONTRIBUTION:
				setQuantitativeContribution(QUANTITATIVE_CONTRIBUTION_EDEFAULT);
				return;
			case GrlPackage.CONTRIBUTION__CORRELATION:
				setCorrelation(CORRELATION_EDEFAULT);
				return;
			case GrlPackage.CONTRIBUTION__CORE_CONTRIBUTION:
				setCoreContribution((COREContribution)null);
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
			case GrlPackage.CONTRIBUTION__CONTRIBUTION:
				return contribution != CONTRIBUTION_EDEFAULT;
			case GrlPackage.CONTRIBUTION__QUANTITATIVE_CONTRIBUTION:
				return quantitativeContribution != QUANTITATIVE_CONTRIBUTION_EDEFAULT;
			case GrlPackage.CONTRIBUTION__CORRELATION:
				return correlation != CORRELATION_EDEFAULT;
			case GrlPackage.CONTRIBUTION__CORE_CONTRIBUTION:
				return coreContribution != null;
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
		result.append(" (contribution: ");
		result.append(contribution);
		result.append(", quantitativeContribution: ");
		result.append(quantitativeContribution);
		result.append(", correlation: ");
		result.append(correlation);
		result.append(')');
		return result.toString();
	}

} //ContributionImpl
