/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Contribution;
import grl.Criticality;
import grl.Decomposition;
import grl.DecompositionType;
import grl.Dependency;
import grl.Evaluation;
import grl.GRLspec;
import grl.GrlPackage;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.IntentionalElementType;
import grl.Priority;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import urncore.impl.GRLmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Intentional Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.IntentionalElementImpl#getType <em>Type</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getCriticality <em>Criticality</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getPriority <em>Priority</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getDecompositionType <em>Decomposition Type</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getGrlspec <em>Grlspec</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getRefs <em>Refs</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getIsDepender <em>Is Depender</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getDecompositionSrc <em>Decomposition Src</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getDecompositionDest <em>Decomposition Dest</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getContributionSrc <em>Contribution Src</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getContributionDest <em>Contribution Dest</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getIsDependum <em>Is Dependum</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getIsDependee <em>Is Dependee</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getEvals <em>Evals</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IntentionalElementImpl extends GRLmodelElementImpl implements IntentionalElement {
    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final IntentionalElementType TYPE_EDEFAULT = IntentionalElementType.GOAL_LITERAL;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected IntentionalElementType type = TYPE_EDEFAULT;

    /**
     * The default value of the '{@link #getCriticality() <em>Criticality</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCriticality()
     * @generated
     * @ordered
     */
    protected static final Criticality CRITICALITY_EDEFAULT = Criticality.HIGH_LITERAL;

    /**
     * The cached value of the '{@link #getCriticality() <em>Criticality</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCriticality()
     * @generated
     * @ordered
     */
    protected Criticality criticality = CRITICALITY_EDEFAULT;

    /**
     * The default value of the '{@link #getPriority() <em>Priority</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPriority()
     * @generated
     * @ordered
     */
    protected static final Priority PRIORITY_EDEFAULT = Priority.HIGH_LITERAL;

    /**
     * The cached value of the '{@link #getPriority() <em>Priority</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPriority()
     * @generated
     * @ordered
     */
    protected Priority priority = PRIORITY_EDEFAULT;

    /**
     * The default value of the '{@link #getDecompositionType() <em>Decomposition Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDecompositionType()
     * @generated
     * @ordered
     */
    protected static final DecompositionType DECOMPOSITION_TYPE_EDEFAULT = DecompositionType.AND_LITERAL;

    /**
     * The cached value of the '{@link #getDecompositionType() <em>Decomposition Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDecompositionType()
     * @generated
     * @ordered
     */
    protected DecompositionType decompositionType = DECOMPOSITION_TYPE_EDEFAULT;

    /**
     * The cached value of the '{@link #getRefs() <em>Refs</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRefs()
     * @generated
     * @ordered
     */
    protected EList refs = null;

    /**
     * The cached value of the '{@link #getIsDepender() <em>Is Depender</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIsDepender()
     * @generated
     * @ordered
     */
    protected EList isDepender = null;

    /**
     * The cached value of the '{@link #getDecompositionSrc() <em>Decomposition Src</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDecompositionSrc()
     * @generated
     * @ordered
     */
    protected EList decompositionSrc = null;

    /**
     * The cached value of the '{@link #getDecompositionDest() <em>Decomposition Dest</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDecompositionDest()
     * @generated
     * @ordered
     */
    protected EList decompositionDest = null;

    /**
     * The cached value of the '{@link #getContributionSrc() <em>Contribution Src</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContributionSrc()
     * @generated
     * @ordered
     */
    protected EList contributionSrc = null;

    /**
     * The cached value of the '{@link #getContributionDest() <em>Contribution Dest</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContributionDest()
     * @generated
     * @ordered
     */
    protected EList contributionDest = null;

    /**
     * The cached value of the '{@link #getIsDependum() <em>Is Dependum</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIsDependum()
     * @generated
     * @ordered
     */
    protected EList isDependum = null;

    /**
     * The cached value of the '{@link #getIsDependee() <em>Is Dependee</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIsDependee()
     * @generated
     * @ordered
     */
    protected EList isDependee = null;

    /**
     * The cached value of the '{@link #getEvals() <em>Evals</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEvals()
     * @generated
     * @ordered
     */
    protected EList evals = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IntentionalElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return GrlPackage.eINSTANCE.getIntentionalElement();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElementType getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setType(IntentionalElementType newType) {
        IntentionalElementType oldType = type;
        type = newType == null ? TYPE_EDEFAULT : newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT__TYPE, oldType, type));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Criticality getCriticality() {
        return criticality;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCriticality(Criticality newCriticality) {
        Criticality oldCriticality = criticality;
        criticality = newCriticality == null ? CRITICALITY_EDEFAULT : newCriticality;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT__CRITICALITY, oldCriticality, criticality));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPriority(Priority newPriority) {
        Priority oldPriority = priority;
        priority = newPriority == null ? PRIORITY_EDEFAULT : newPriority;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT__PRIORITY, oldPriority, priority));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DecompositionType getDecompositionType() {
        return decompositionType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDecompositionType(DecompositionType newDecompositionType) {
        DecompositionType oldDecompositionType = decompositionType;
        decompositionType = newDecompositionType == null ? DECOMPOSITION_TYPE_EDEFAULT : newDecompositionType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE, oldDecompositionType, decompositionType));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GRLspec getGrlspec() {
        if (eContainerFeatureID != GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC) return null;
        return (GRLspec)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGrlspec(GRLspec newGrlspec) {
        if (newGrlspec != eContainer || (eContainerFeatureID != GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC && newGrlspec != null)) {
            if (EcoreUtil.isAncestor(this, newGrlspec))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newGrlspec != null)
                msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__INT_ELEMENTS, GRLspec.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newGrlspec, GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC, newGrlspec, newGrlspec));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getRefs() {
        if (refs == null) {
            refs = new EObjectWithInverseResolvingEList(IntentionalElementRef.class, this, GrlPackage.INTENTIONAL_ELEMENT__REFS, GrlPackage.INTENTIONAL_ELEMENT_REF__DEF);
        }
        return refs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getIsDepender() {
        if (isDepender == null) {
            isDepender = new EObjectWithInverseResolvingEList(Dependency.class, this, GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDER, GrlPackage.DEPENDENCY__DEPENDER);
        }
        return isDepender;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getDecompositionSrc() {
        if (decompositionSrc == null) {
            decompositionSrc = new EObjectWithInverseResolvingEList(Decomposition.class, this, GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_SRC, GrlPackage.DECOMPOSITION__SRC);
        }
        return decompositionSrc;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getDecompositionDest() {
        if (decompositionDest == null) {
            decompositionDest = new EObjectWithInverseResolvingEList(Decomposition.class, this, GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_DEST, GrlPackage.DECOMPOSITION__DEST);
        }
        return decompositionDest;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getContributionSrc() {
        if (contributionSrc == null) {
            contributionSrc = new EObjectWithInverseResolvingEList(Contribution.class, this, GrlPackage.INTENTIONAL_ELEMENT__CONTRIBUTION_SRC, GrlPackage.CONTRIBUTION__SRC);
        }
        return contributionSrc;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getContributionDest() {
        if (contributionDest == null) {
            contributionDest = new EObjectWithInverseResolvingEList(Contribution.class, this, GrlPackage.INTENTIONAL_ELEMENT__CONTRIBUTION_DEST, GrlPackage.CONTRIBUTION__DEST);
        }
        return contributionDest;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getIsDependum() {
        if (isDependum == null) {
            isDependum = new EObjectWithInverseResolvingEList(Dependency.class, this, GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDUM, GrlPackage.DEPENDENCY__DEPENDUM);
        }
        return isDependum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getIsDependee() {
        if (isDependee == null) {
            isDependee = new EObjectWithInverseResolvingEList(Dependency.class, this, GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDEE, GrlPackage.DEPENDENCY__DEPENDEE);
        }
        return isDependee;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getEvals() {
        if (evals == null) {
            evals = new EObjectWithInverseResolvingEList(Evaluation.class, this, GrlPackage.INTENTIONAL_ELEMENT__EVALS, GrlPackage.EVALUATION__INT_ELEMENT);
        }
        return evals;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.INTENTIONAL_ELEMENT__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__REFS:
                    return ((InternalEList)getRefs()).basicAdd(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDER:
                    return ((InternalEList)getIsDepender()).basicAdd(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_SRC:
                    return ((InternalEList)getDecompositionSrc()).basicAdd(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_DEST:
                    return ((InternalEList)getDecompositionDest()).basicAdd(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__CONTRIBUTION_SRC:
                    return ((InternalEList)getContributionSrc()).basicAdd(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__CONTRIBUTION_DEST:
                    return ((InternalEList)getContributionDest()).basicAdd(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDUM:
                    return ((InternalEList)getIsDependum()).basicAdd(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDEE:
                    return ((InternalEList)getIsDependee()).basicAdd(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__EVALS:
                    return ((InternalEList)getEvals()).basicAdd(otherEnd, msgs);
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
                case GrlPackage.INTENTIONAL_ELEMENT__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC:
                    return eBasicSetContainer(null, GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__REFS:
                    return ((InternalEList)getRefs()).basicRemove(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDER:
                    return ((InternalEList)getIsDepender()).basicRemove(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_SRC:
                    return ((InternalEList)getDecompositionSrc()).basicRemove(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_DEST:
                    return ((InternalEList)getDecompositionDest()).basicRemove(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__CONTRIBUTION_SRC:
                    return ((InternalEList)getContributionSrc()).basicRemove(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__CONTRIBUTION_DEST:
                    return ((InternalEList)getContributionDest()).basicRemove(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDUM:
                    return ((InternalEList)getIsDependum()).basicRemove(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDEE:
                    return ((InternalEList)getIsDependee()).basicRemove(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__EVALS:
                    return ((InternalEList)getEvals()).basicRemove(otherEnd, msgs);
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
                case GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC:
                    return ((InternalEObject)eContainer).eInverseRemove(this, GrlPackage.GR_LSPEC__INT_ELEMENTS, GRLspec.class, msgs);
                default:
                    return eDynamicBasicRemoveFromContainer(msgs);
            }
        }
        return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case GrlPackage.INTENTIONAL_ELEMENT__ID:
                return getId();
            case GrlPackage.INTENTIONAL_ELEMENT__NAME:
                return getName();
            case GrlPackage.INTENTIONAL_ELEMENT__DESCRIPTION:
                return getDescription();
            case GrlPackage.INTENTIONAL_ELEMENT__URN_LINKS:
                return getUrnLinks();
            case GrlPackage.INTENTIONAL_ELEMENT__TYPE:
                return getType();
            case GrlPackage.INTENTIONAL_ELEMENT__CRITICALITY:
                return getCriticality();
            case GrlPackage.INTENTIONAL_ELEMENT__PRIORITY:
                return getPriority();
            case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE:
                return getDecompositionType();
            case GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC:
                return getGrlspec();
            case GrlPackage.INTENTIONAL_ELEMENT__REFS:
                return getRefs();
            case GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDER:
                return getIsDepender();
            case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_SRC:
                return getDecompositionSrc();
            case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_DEST:
                return getDecompositionDest();
            case GrlPackage.INTENTIONAL_ELEMENT__CONTRIBUTION_SRC:
                return getContributionSrc();
            case GrlPackage.INTENTIONAL_ELEMENT__CONTRIBUTION_DEST:
                return getContributionDest();
            case GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDUM:
                return getIsDependum();
            case GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDEE:
                return getIsDependee();
            case GrlPackage.INTENTIONAL_ELEMENT__EVALS:
                return getEvals();
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
            case GrlPackage.INTENTIONAL_ELEMENT__ID:
                setId((String)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__NAME:
                setName((String)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__URN_LINKS:
                getUrnLinks().clear();
                getUrnLinks().addAll((Collection)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__TYPE:
                setType((IntentionalElementType)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__CRITICALITY:
                setCriticality((Criticality)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__PRIORITY:
                setPriority((Priority)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE:
                setDecompositionType((DecompositionType)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC:
                setGrlspec((GRLspec)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__REFS:
                getRefs().clear();
                getRefs().addAll((Collection)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDER:
                getIsDepender().clear();
                getIsDepender().addAll((Collection)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_SRC:
                getDecompositionSrc().clear();
                getDecompositionSrc().addAll((Collection)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_DEST:
                getDecompositionDest().clear();
                getDecompositionDest().addAll((Collection)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__CONTRIBUTION_SRC:
                getContributionSrc().clear();
                getContributionSrc().addAll((Collection)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__CONTRIBUTION_DEST:
                getContributionDest().clear();
                getContributionDest().addAll((Collection)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDUM:
                getIsDependum().clear();
                getIsDependum().addAll((Collection)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDEE:
                getIsDependee().clear();
                getIsDependee().addAll((Collection)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__EVALS:
                getEvals().clear();
                getEvals().addAll((Collection)newValue);
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
            case GrlPackage.INTENTIONAL_ELEMENT__ID:
                setId(ID_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__NAME:
                setName(NAME_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__URN_LINKS:
                getUrnLinks().clear();
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__TYPE:
                setType(TYPE_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__CRITICALITY:
                setCriticality(CRITICALITY_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__PRIORITY:
                setPriority(PRIORITY_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE:
                setDecompositionType(DECOMPOSITION_TYPE_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC:
                setGrlspec((GRLspec)null);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__REFS:
                getRefs().clear();
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDER:
                getIsDepender().clear();
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_SRC:
                getDecompositionSrc().clear();
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_DEST:
                getDecompositionDest().clear();
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__CONTRIBUTION_SRC:
                getContributionSrc().clear();
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__CONTRIBUTION_DEST:
                getContributionDest().clear();
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDUM:
                getIsDependum().clear();
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDEE:
                getIsDependee().clear();
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__EVALS:
                getEvals().clear();
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
            case GrlPackage.INTENTIONAL_ELEMENT__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case GrlPackage.INTENTIONAL_ELEMENT__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case GrlPackage.INTENTIONAL_ELEMENT__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case GrlPackage.INTENTIONAL_ELEMENT__URN_LINKS:
                return urnLinks != null && !urnLinks.isEmpty();
            case GrlPackage.INTENTIONAL_ELEMENT__TYPE:
                return type != TYPE_EDEFAULT;
            case GrlPackage.INTENTIONAL_ELEMENT__CRITICALITY:
                return criticality != CRITICALITY_EDEFAULT;
            case GrlPackage.INTENTIONAL_ELEMENT__PRIORITY:
                return priority != PRIORITY_EDEFAULT;
            case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE:
                return decompositionType != DECOMPOSITION_TYPE_EDEFAULT;
            case GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC:
                return getGrlspec() != null;
            case GrlPackage.INTENTIONAL_ELEMENT__REFS:
                return refs != null && !refs.isEmpty();
            case GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDER:
                return isDepender != null && !isDepender.isEmpty();
            case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_SRC:
                return decompositionSrc != null && !decompositionSrc.isEmpty();
            case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_DEST:
                return decompositionDest != null && !decompositionDest.isEmpty();
            case GrlPackage.INTENTIONAL_ELEMENT__CONTRIBUTION_SRC:
                return contributionSrc != null && !contributionSrc.isEmpty();
            case GrlPackage.INTENTIONAL_ELEMENT__CONTRIBUTION_DEST:
                return contributionDest != null && !contributionDest.isEmpty();
            case GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDUM:
                return isDependum != null && !isDependum.isEmpty();
            case GrlPackage.INTENTIONAL_ELEMENT__IS_DEPENDEE:
                return isDependee != null && !isDependee.isEmpty();
            case GrlPackage.INTENTIONAL_ELEMENT__EVALS:
                return evals != null && !evals.isEmpty();
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
        result.append(" (type: ");
        result.append(type);
        result.append(", criticality: ");
        result.append(criticality);
        result.append(", priority: ");
        result.append(priority);
        result.append(", decompositionType: ");
        result.append(decompositionType);
        result.append(')');
        return result.toString();
    }

} //IntentionalElementImpl
