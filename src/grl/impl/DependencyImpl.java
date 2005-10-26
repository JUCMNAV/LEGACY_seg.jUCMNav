/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Dependency;
import grl.GrlPackage;
import grl.IntentionalElement;
import grl.LinkRef;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dependency</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.DependencyImpl#getDepender <em>Depender</em>}</li>
 *   <li>{@link grl.impl.DependencyImpl#getDependum <em>Dependum</em>}</li>
 *   <li>{@link grl.impl.DependencyImpl#getDependee <em>Dependee</em>}</li>
 *   <li>{@link grl.impl.DependencyImpl#getSecondRefs <em>Second Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DependencyImpl extends ElementLinkImpl implements Dependency {
    /**
     * The cached value of the '{@link #getDepender() <em>Depender</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDepender()
     * @generated
     * @ordered
     */
    protected IntentionalElement depender = null;

    /**
     * The cached value of the '{@link #getDependum() <em>Dependum</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDependum()
     * @generated
     * @ordered
     */
    protected IntentionalElement dependum = null;

    /**
     * The cached value of the '{@link #getDependee() <em>Dependee</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDependee()
     * @generated
     * @ordered
     */
    protected IntentionalElement dependee = null;

    /**
     * The cached value of the '{@link #getSecondRefs() <em>Second Refs</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSecondRefs()
     * @generated
     * @ordered
     */
    protected EList secondRefs = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected DependencyImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return GrlPackage.eINSTANCE.getDependency();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElement getDepender() {
        if (depender != null && depender.eIsProxy()) {
            IntentionalElement oldDepender = depender;
            depender = (IntentionalElement)eResolveProxy((InternalEObject)depender);
            if (depender != oldDepender) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.DEPENDENCY__DEPENDER, oldDepender, depender));
            }
        }
        return depender;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElement basicGetDepender() {
        return depender;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDepender(IntentionalElement newDepender, NotificationChain msgs) {
        IntentionalElement oldDepender = depender;
        depender = newDepender;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.DEPENDENCY__DEPENDER, oldDepender, newDepender);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDepender(IntentionalElement newDepender) {
        if (newDepender != depender) {
            NotificationChain msgs = null;
            if (depender != null)
                msgs = ((InternalEObject)depender).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDER, IntentionalElement.class, msgs);
            if (newDepender != null)
                msgs = ((InternalEObject)newDepender).eInverseAdd(this, GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDER, IntentionalElement.class, msgs);
            msgs = basicSetDepender(newDepender, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.DEPENDENCY__DEPENDER, newDepender, newDepender));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElement getDependum() {
        if (dependum != null && dependum.eIsProxy()) {
            IntentionalElement oldDependum = dependum;
            dependum = (IntentionalElement)eResolveProxy((InternalEObject)dependum);
            if (dependum != oldDependum) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.DEPENDENCY__DEPENDUM, oldDependum, dependum));
            }
        }
        return dependum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElement basicGetDependum() {
        return dependum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDependum(IntentionalElement newDependum, NotificationChain msgs) {
        IntentionalElement oldDependum = dependum;
        dependum = newDependum;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.DEPENDENCY__DEPENDUM, oldDependum, newDependum);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDependum(IntentionalElement newDependum) {
        if (newDependum != dependum) {
            NotificationChain msgs = null;
            if (dependum != null)
                msgs = ((InternalEObject)dependum).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDUM, IntentionalElement.class, msgs);
            if (newDependum != null)
                msgs = ((InternalEObject)newDependum).eInverseAdd(this, GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDUM, IntentionalElement.class, msgs);
            msgs = basicSetDependum(newDependum, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.DEPENDENCY__DEPENDUM, newDependum, newDependum));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElement getDependee() {
        if (dependee != null && dependee.eIsProxy()) {
            IntentionalElement oldDependee = dependee;
            dependee = (IntentionalElement)eResolveProxy((InternalEObject)dependee);
            if (dependee != oldDependee) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.DEPENDENCY__DEPENDEE, oldDependee, dependee));
            }
        }
        return dependee;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElement basicGetDependee() {
        return dependee;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDependee(IntentionalElement newDependee, NotificationChain msgs) {
        IntentionalElement oldDependee = dependee;
        dependee = newDependee;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.DEPENDENCY__DEPENDEE, oldDependee, newDependee);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDependee(IntentionalElement newDependee) {
        if (newDependee != dependee) {
            NotificationChain msgs = null;
            if (dependee != null)
                msgs = ((InternalEObject)dependee).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDEE, IntentionalElement.class, msgs);
            if (newDependee != null)
                msgs = ((InternalEObject)newDependee).eInverseAdd(this, GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDEE, IntentionalElement.class, msgs);
            msgs = basicSetDependee(newDependee, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.DEPENDENCY__DEPENDEE, newDependee, newDependee));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getSecondRefs() {
        if (secondRefs == null) {
            secondRefs = new EObjectWithInverseResolvingEList(LinkRef.class, this, GrlPackage.DEPENDENCY__SECOND_REFS, GrlPackage.LINK_REF__DEPENDENCY);
        }
        return secondRefs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.DEPENDENCY__REFS:
                    return ((InternalEList)getRefs()).basicAdd(otherEnd, msgs);
                case GrlPackage.DEPENDENCY__DEPENDER:
                    if (depender != null)
                        msgs = ((InternalEObject)depender).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDER, IntentionalElement.class, msgs);
                    return basicSetDepender((IntentionalElement)otherEnd, msgs);
                case GrlPackage.DEPENDENCY__DEPENDUM:
                    if (dependum != null)
                        msgs = ((InternalEObject)dependum).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDUM, IntentionalElement.class, msgs);
                    return basicSetDependum((IntentionalElement)otherEnd, msgs);
                case GrlPackage.DEPENDENCY__DEPENDEE:
                    if (dependee != null)
                        msgs = ((InternalEObject)dependee).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDEE, IntentionalElement.class, msgs);
                    return basicSetDependee((IntentionalElement)otherEnd, msgs);
                case GrlPackage.DEPENDENCY__SECOND_REFS:
                    return ((InternalEList)getSecondRefs()).basicAdd(otherEnd, msgs);
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
                case GrlPackage.DEPENDENCY__REFS:
                    return ((InternalEList)getRefs()).basicRemove(otherEnd, msgs);
                case GrlPackage.DEPENDENCY__DEPENDER:
                    return basicSetDepender(null, msgs);
                case GrlPackage.DEPENDENCY__DEPENDUM:
                    return basicSetDependum(null, msgs);
                case GrlPackage.DEPENDENCY__DEPENDEE:
                    return basicSetDependee(null, msgs);
                case GrlPackage.DEPENDENCY__SECOND_REFS:
                    return ((InternalEList)getSecondRefs()).basicRemove(otherEnd, msgs);
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
            case GrlPackage.DEPENDENCY__REFS:
                return getRefs();
            case GrlPackage.DEPENDENCY__DEPENDER:
                if (resolve) return getDepender();
                return basicGetDepender();
            case GrlPackage.DEPENDENCY__DEPENDUM:
                if (resolve) return getDependum();
                return basicGetDependum();
            case GrlPackage.DEPENDENCY__DEPENDEE:
                if (resolve) return getDependee();
                return basicGetDependee();
            case GrlPackage.DEPENDENCY__SECOND_REFS:
                return getSecondRefs();
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
            case GrlPackage.DEPENDENCY__REFS:
                getRefs().clear();
                getRefs().addAll((Collection)newValue);
                return;
            case GrlPackage.DEPENDENCY__DEPENDER:
                setDepender((IntentionalElement)newValue);
                return;
            case GrlPackage.DEPENDENCY__DEPENDUM:
                setDependum((IntentionalElement)newValue);
                return;
            case GrlPackage.DEPENDENCY__DEPENDEE:
                setDependee((IntentionalElement)newValue);
                return;
            case GrlPackage.DEPENDENCY__SECOND_REFS:
                getSecondRefs().clear();
                getSecondRefs().addAll((Collection)newValue);
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
            case GrlPackage.DEPENDENCY__REFS:
                getRefs().clear();
                return;
            case GrlPackage.DEPENDENCY__DEPENDER:
                setDepender((IntentionalElement)null);
                return;
            case GrlPackage.DEPENDENCY__DEPENDUM:
                setDependum((IntentionalElement)null);
                return;
            case GrlPackage.DEPENDENCY__DEPENDEE:
                setDependee((IntentionalElement)null);
                return;
            case GrlPackage.DEPENDENCY__SECOND_REFS:
                getSecondRefs().clear();
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
            case GrlPackage.DEPENDENCY__REFS:
                return refs != null && !refs.isEmpty();
            case GrlPackage.DEPENDENCY__DEPENDER:
                return depender != null;
            case GrlPackage.DEPENDENCY__DEPENDUM:
                return dependum != null;
            case GrlPackage.DEPENDENCY__DEPENDEE:
                return dependee != null;
            case GrlPackage.DEPENDENCY__SECOND_REFS:
                return secondRefs != null && !secondRefs.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

} //DependencyImpl
