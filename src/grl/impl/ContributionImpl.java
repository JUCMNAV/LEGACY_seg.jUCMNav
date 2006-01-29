/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Contribution;
import grl.ContributionType;
import grl.GRLspec;
import grl.GrlPackage;
import grl.IntentionalElement;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Contribution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.ContributionImpl#getContribution <em>Contribution</em>}</li>
 *   <li>{@link grl.impl.ContributionImpl#isCorrelation <em>Correlation</em>}</li>
 * </ul>
 * </p>
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
    protected static final ContributionType CONTRIBUTION_EDEFAULT = ContributionType.UNKNOWN_LITERAL;

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
        return GrlPackage.eINSTANCE.getContribution();
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
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.CONTRIBUTION__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.CONTRIBUTION__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.CONTRIBUTION__REFS:
                    return ((InternalEList)getRefs()).basicAdd(otherEnd, msgs);
                case GrlPackage.CONTRIBUTION__GRLSPEC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, GrlPackage.CONTRIBUTION__GRLSPEC, msgs);
                case GrlPackage.CONTRIBUTION__SRC:
                    if (src != null)
                        msgs = ((InternalEObject)src).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__LINKS_SRC, IntentionalElement.class, msgs);
                    return basicSetSrc((IntentionalElement)otherEnd, msgs);
                case GrlPackage.CONTRIBUTION__DEST:
                    if (dest != null)
                        msgs = ((InternalEObject)dest).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__LINKS_DEST, IntentionalElement.class, msgs);
                    return basicSetDest((IntentionalElement)otherEnd, msgs);
                default:
                    return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
            }
        }
        if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
        return eBasicSetContainer(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.CONTRIBUTION__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.CONTRIBUTION__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.CONTRIBUTION__REFS:
                    return ((InternalEList)getRefs()).basicRemove(otherEnd, msgs);
                case GrlPackage.CONTRIBUTION__GRLSPEC:
                    return eBasicSetContainer(null, GrlPackage.CONTRIBUTION__GRLSPEC, msgs);
                case GrlPackage.CONTRIBUTION__SRC:
                    return basicSetSrc(null, msgs);
                case GrlPackage.CONTRIBUTION__DEST:
                    return basicSetDest(null, msgs);
                default:
                    return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
            }
        }
        return eBasicSetContainer(null, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
        if (eContainerFeatureID >= 0) {
            switch (eContainerFeatureID) {
                case GrlPackage.CONTRIBUTION__GRLSPEC:
                    return eContainer.eInverseRemove(this, GrlPackage.GR_LSPEC__LINKS, GRLspec.class, msgs);
                default:
                    return eDynamicBasicRemoveFromContainer(msgs);
            }
        }
        return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case GrlPackage.CONTRIBUTION__FROM_LINKS:
                return getFromLinks();
            case GrlPackage.CONTRIBUTION__TO_LINKS:
                return getToLinks();
            case GrlPackage.CONTRIBUTION__ID:
                return getId();
            case GrlPackage.CONTRIBUTION__NAME:
                return getName();
            case GrlPackage.CONTRIBUTION__DESCRIPTION:
                return getDescription();
            case GrlPackage.CONTRIBUTION__REFS:
                return getRefs();
            case GrlPackage.CONTRIBUTION__GRLSPEC:
                return getGrlspec();
            case GrlPackage.CONTRIBUTION__SRC:
                if (resolve) return getSrc();
                return basicGetSrc();
            case GrlPackage.CONTRIBUTION__DEST:
                if (resolve) return getDest();
                return basicGetDest();
            case GrlPackage.CONTRIBUTION__CONTRIBUTION:
                return getContribution();
            case GrlPackage.CONTRIBUTION__CORRELATION:
                return isCorrelation() ? Boolean.TRUE : Boolean.FALSE;
        }
        return eDynamicGet(eFeature, resolve);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eSet(EStructuralFeature eFeature, Object newValue) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case GrlPackage.CONTRIBUTION__FROM_LINKS:
                getFromLinks().clear();
                getFromLinks().addAll((Collection)newValue);
                return;
            case GrlPackage.CONTRIBUTION__TO_LINKS:
                getToLinks().clear();
                getToLinks().addAll((Collection)newValue);
                return;
            case GrlPackage.CONTRIBUTION__ID:
                setId((String)newValue);
                return;
            case GrlPackage.CONTRIBUTION__NAME:
                setName((String)newValue);
                return;
            case GrlPackage.CONTRIBUTION__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case GrlPackage.CONTRIBUTION__REFS:
                getRefs().clear();
                getRefs().addAll((Collection)newValue);
                return;
            case GrlPackage.CONTRIBUTION__GRLSPEC:
                setGrlspec((GRLspec)newValue);
                return;
            case GrlPackage.CONTRIBUTION__SRC:
                setSrc((IntentionalElement)newValue);
                return;
            case GrlPackage.CONTRIBUTION__DEST:
                setDest((IntentionalElement)newValue);
                return;
            case GrlPackage.CONTRIBUTION__CONTRIBUTION:
                setContribution((ContributionType)newValue);
                return;
            case GrlPackage.CONTRIBUTION__CORRELATION:
                setCorrelation(((Boolean)newValue).booleanValue());
                return;
        }
        eDynamicSet(eFeature, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void eUnset(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case GrlPackage.CONTRIBUTION__FROM_LINKS:
                getFromLinks().clear();
                return;
            case GrlPackage.CONTRIBUTION__TO_LINKS:
                getToLinks().clear();
                return;
            case GrlPackage.CONTRIBUTION__ID:
                setId(ID_EDEFAULT);
                return;
            case GrlPackage.CONTRIBUTION__NAME:
                setName(NAME_EDEFAULT);
                return;
            case GrlPackage.CONTRIBUTION__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case GrlPackage.CONTRIBUTION__REFS:
                getRefs().clear();
                return;
            case GrlPackage.CONTRIBUTION__GRLSPEC:
                setGrlspec((GRLspec)null);
                return;
            case GrlPackage.CONTRIBUTION__SRC:
                setSrc((IntentionalElement)null);
                return;
            case GrlPackage.CONTRIBUTION__DEST:
                setDest((IntentionalElement)null);
                return;
            case GrlPackage.CONTRIBUTION__CONTRIBUTION:
                setContribution(CONTRIBUTION_EDEFAULT);
                return;
            case GrlPackage.CONTRIBUTION__CORRELATION:
                setCorrelation(CORRELATION_EDEFAULT);
                return;
        }
        eDynamicUnset(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean eIsSet(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case GrlPackage.CONTRIBUTION__FROM_LINKS:
                return fromLinks != null && !fromLinks.isEmpty();
            case GrlPackage.CONTRIBUTION__TO_LINKS:
                return toLinks != null && !toLinks.isEmpty();
            case GrlPackage.CONTRIBUTION__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case GrlPackage.CONTRIBUTION__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case GrlPackage.CONTRIBUTION__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case GrlPackage.CONTRIBUTION__REFS:
                return refs != null && !refs.isEmpty();
            case GrlPackage.CONTRIBUTION__GRLSPEC:
                return getGrlspec() != null;
            case GrlPackage.CONTRIBUTION__SRC:
                return src != null;
            case GrlPackage.CONTRIBUTION__DEST:
                return dest != null;
            case GrlPackage.CONTRIBUTION__CONTRIBUTION:
                return contribution != CONTRIBUTION_EDEFAULT;
            case GrlPackage.CONTRIBUTION__CORRELATION:
                return correlation != CORRELATION_EDEFAULT;
        }
        return eDynamicIsSet(eFeature);
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
        result.append(", correlation: ");
        result.append(correlation);
        result.append(')');
        return result.toString();
    }

} //ContributionImpl
