/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Contribution;
import grl.ContributionType;
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
 *   <li>{@link grl.impl.ContributionImpl#getContibution <em>Contibution</em>}</li>
 *   <li>{@link grl.impl.ContributionImpl#isCorrelation <em>Correlation</em>}</li>
 *   <li>{@link grl.impl.ContributionImpl#getSrc <em>Src</em>}</li>
 *   <li>{@link grl.impl.ContributionImpl#getDest <em>Dest</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContributionImpl extends ElementLinkImpl implements Contribution {
    /**
     * The default value of the '{@link #getContibution() <em>Contibution</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContibution()
     * @generated
     * @ordered
     */
    protected static final ContributionType CONTIBUTION_EDEFAULT = ContributionType.BREAK_LITERAL;

    /**
     * The cached value of the '{@link #getContibution() <em>Contibution</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContibution()
     * @generated
     * @ordered
     */
    protected ContributionType contibution = CONTIBUTION_EDEFAULT;

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
     * The cached value of the '{@link #getSrc() <em>Src</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSrc()
     * @generated
     * @ordered
     */
    protected IntentionalElement src = null;

    /**
     * The cached value of the '{@link #getDest() <em>Dest</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDest()
     * @generated
     * @ordered
     */
    protected IntentionalElement dest = null;

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
    public ContributionType getContibution() {
        return contibution;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setContibution(ContributionType newContibution) {
        ContributionType oldContibution = contibution;
        contibution = newContibution == null ? CONTIBUTION_EDEFAULT : newContibution;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.CONTRIBUTION__CONTIBUTION, oldContibution, contibution));
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
    public IntentionalElement getSrc() {
        if (src != null && src.eIsProxy()) {
            IntentionalElement oldSrc = src;
            src = (IntentionalElement)eResolveProxy((InternalEObject)src);
            if (src != oldSrc) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.CONTRIBUTION__SRC, oldSrc, src));
            }
        }
        return src;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElement basicGetSrc() {
        return src;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSrc(IntentionalElement newSrc, NotificationChain msgs) {
        IntentionalElement oldSrc = src;
        src = newSrc;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.CONTRIBUTION__SRC, oldSrc, newSrc);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSrc(IntentionalElement newSrc) {
        if (newSrc != src) {
            NotificationChain msgs = null;
            if (src != null)
                msgs = ((InternalEObject)src).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__CONTRIBUTION_SRC, IntentionalElement.class, msgs);
            if (newSrc != null)
                msgs = ((InternalEObject)newSrc).eInverseAdd(this, GrlPackage.INTENTIONAL_ELEMENT__CONTRIBUTION_SRC, IntentionalElement.class, msgs);
            msgs = basicSetSrc(newSrc, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.CONTRIBUTION__SRC, newSrc, newSrc));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElement getDest() {
        if (dest != null && dest.eIsProxy()) {
            IntentionalElement oldDest = dest;
            dest = (IntentionalElement)eResolveProxy((InternalEObject)dest);
            if (dest != oldDest) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.CONTRIBUTION__DEST, oldDest, dest));
            }
        }
        return dest;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElement basicGetDest() {
        return dest;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDest(IntentionalElement newDest, NotificationChain msgs) {
        IntentionalElement oldDest = dest;
        dest = newDest;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.CONTRIBUTION__DEST, oldDest, newDest);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDest(IntentionalElement newDest) {
        if (newDest != dest) {
            NotificationChain msgs = null;
            if (dest != null)
                msgs = ((InternalEObject)dest).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__CONTRIBUTION_DEST, IntentionalElement.class, msgs);
            if (newDest != null)
                msgs = ((InternalEObject)newDest).eInverseAdd(this, GrlPackage.INTENTIONAL_ELEMENT__CONTRIBUTION_DEST, IntentionalElement.class, msgs);
            msgs = basicSetDest(newDest, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.CONTRIBUTION__DEST, newDest, newDest));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.CONTRIBUTION__REFS:
                    return ((InternalEList)getRefs()).basicAdd(otherEnd, msgs);
                case GrlPackage.CONTRIBUTION__SRC:
                    if (src != null)
                        msgs = ((InternalEObject)src).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__CONTRIBUTION_SRC, IntentionalElement.class, msgs);
                    return basicSetSrc((IntentionalElement)otherEnd, msgs);
                case GrlPackage.CONTRIBUTION__DEST:
                    if (dest != null)
                        msgs = ((InternalEObject)dest).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__CONTRIBUTION_DEST, IntentionalElement.class, msgs);
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
                case GrlPackage.CONTRIBUTION__REFS:
                    return ((InternalEList)getRefs()).basicRemove(otherEnd, msgs);
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
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case GrlPackage.CONTRIBUTION__REFS:
                return getRefs();
            case GrlPackage.CONTRIBUTION__CONTIBUTION:
                return getContibution();
            case GrlPackage.CONTRIBUTION__CORRELATION:
                return isCorrelation() ? Boolean.TRUE : Boolean.FALSE;
            case GrlPackage.CONTRIBUTION__SRC:
                if (resolve) return getSrc();
                return basicGetSrc();
            case GrlPackage.CONTRIBUTION__DEST:
                if (resolve) return getDest();
                return basicGetDest();
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
            case GrlPackage.CONTRIBUTION__REFS:
                getRefs().clear();
                getRefs().addAll((Collection)newValue);
                return;
            case GrlPackage.CONTRIBUTION__CONTIBUTION:
                setContibution((ContributionType)newValue);
                return;
            case GrlPackage.CONTRIBUTION__CORRELATION:
                setCorrelation(((Boolean)newValue).booleanValue());
                return;
            case GrlPackage.CONTRIBUTION__SRC:
                setSrc((IntentionalElement)newValue);
                return;
            case GrlPackage.CONTRIBUTION__DEST:
                setDest((IntentionalElement)newValue);
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
            case GrlPackage.CONTRIBUTION__REFS:
                getRefs().clear();
                return;
            case GrlPackage.CONTRIBUTION__CONTIBUTION:
                setContibution(CONTIBUTION_EDEFAULT);
                return;
            case GrlPackage.CONTRIBUTION__CORRELATION:
                setCorrelation(CORRELATION_EDEFAULT);
                return;
            case GrlPackage.CONTRIBUTION__SRC:
                setSrc((IntentionalElement)null);
                return;
            case GrlPackage.CONTRIBUTION__DEST:
                setDest((IntentionalElement)null);
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
            case GrlPackage.CONTRIBUTION__REFS:
                return refs != null && !refs.isEmpty();
            case GrlPackage.CONTRIBUTION__CONTIBUTION:
                return contibution != CONTIBUTION_EDEFAULT;
            case GrlPackage.CONTRIBUTION__CORRELATION:
                return correlation != CORRELATION_EDEFAULT;
            case GrlPackage.CONTRIBUTION__SRC:
                return src != null;
            case GrlPackage.CONTRIBUTION__DEST:
                return dest != null;
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
        result.append(" (contibution: ");
        result.append(contibution);
        result.append(", correlation: ");
        result.append(correlation);
        result.append(')');
        return result.toString();
    }

} //ContributionImpl
