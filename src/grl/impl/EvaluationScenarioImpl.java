/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Evaluation;
import grl.EvaluationGroup;
import grl.EvaluationScenario;
import grl.GRLspec;
import grl.GrlPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import urncore.impl.GRLmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Evaluation Scenario</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.EvaluationScenarioImpl#getAuthor <em>Author</em>}</li>
 *   <li>{@link grl.impl.EvaluationScenarioImpl#getEvaluations <em>Evaluations</em>}</li>
 *   <li>{@link grl.impl.EvaluationScenarioImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link grl.impl.EvaluationScenarioImpl#getGrlspec <em>Grlspec</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EvaluationScenarioImpl extends GRLmodelElementImpl implements EvaluationScenario {
    /**
     * The default value of the '{@link #getAuthor() <em>Author</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAuthor()
     * @generated
     * @ordered
     */
    protected static final String AUTHOR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getAuthor() <em>Author</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAuthor()
     * @generated
     * @ordered
     */
    protected String author = AUTHOR_EDEFAULT;

    /**
     * The cached value of the '{@link #getEvaluations() <em>Evaluations</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEvaluations()
     * @generated
     * @ordered
     */
    protected EList evaluations = null;

    /**
     * The cached value of the '{@link #getGroup() <em>Group</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getGroup()
     * @generated
     * @ordered
     */
    protected EvaluationGroup group = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EvaluationScenarioImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return GrlPackage.eINSTANCE.getEvaluationScenario();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getAuthor() {
        return author;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAuthor(String newAuthor) {
        String oldAuthor = author;
        author = newAuthor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION_SCENARIO__AUTHOR, oldAuthor, author));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getEvaluations() {
        if (evaluations == null) {
            evaluations = new EObjectContainmentWithInverseEList(Evaluation.class, this, GrlPackage.EVALUATION_SCENARIO__EVALUATIONS, GrlPackage.EVALUATION__SCENARIO);
        }
        return evaluations;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EvaluationGroup getGroup() {
        if (group != null && group.eIsProxy()) {
            EvaluationGroup oldGroup = group;
            group = (EvaluationGroup)eResolveProxy((InternalEObject)group);
            if (group != oldGroup) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.EVALUATION_SCENARIO__GROUP, oldGroup, group));
            }
        }
        return group;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EvaluationGroup basicGetGroup() {
        return group;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetGroup(EvaluationGroup newGroup, NotificationChain msgs) {
        EvaluationGroup oldGroup = group;
        group = newGroup;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION_SCENARIO__GROUP, oldGroup, newGroup);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGroup(EvaluationGroup newGroup) {
        if (newGroup != group) {
            NotificationChain msgs = null;
            if (group != null)
                msgs = ((InternalEObject)group).eInverseRemove(this, GrlPackage.EVALUATION_GROUP__SCENARIOS, EvaluationGroup.class, msgs);
            if (newGroup != null)
                msgs = ((InternalEObject)newGroup).eInverseAdd(this, GrlPackage.EVALUATION_GROUP__SCENARIOS, EvaluationGroup.class, msgs);
            msgs = basicSetGroup(newGroup, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION_SCENARIO__GROUP, newGroup, newGroup));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GRLspec getGrlspec() {
        if (eContainerFeatureID != GrlPackage.EVALUATION_SCENARIO__GRLSPEC) return null;
        return (GRLspec)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGrlspec(GRLspec newGrlspec) {
        if (newGrlspec != eContainer || (eContainerFeatureID != GrlPackage.EVALUATION_SCENARIO__GRLSPEC && newGrlspec != null)) {
            if (EcoreUtil.isAncestor(this, newGrlspec))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newGrlspec != null)
                msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__SCENARIOS, GRLspec.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newGrlspec, GrlPackage.EVALUATION_SCENARIO__GRLSPEC, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION_SCENARIO__GRLSPEC, newGrlspec, newGrlspec));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.EVALUATION_SCENARIO__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.EVALUATION_SCENARIO__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.EVALUATION_SCENARIO__EVALUATIONS:
                    return ((InternalEList)getEvaluations()).basicAdd(otherEnd, msgs);
                case GrlPackage.EVALUATION_SCENARIO__GROUP:
                    if (group != null)
                        msgs = ((InternalEObject)group).eInverseRemove(this, GrlPackage.EVALUATION_GROUP__SCENARIOS, EvaluationGroup.class, msgs);
                    return basicSetGroup((EvaluationGroup)otherEnd, msgs);
                case GrlPackage.EVALUATION_SCENARIO__GRLSPEC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, GrlPackage.EVALUATION_SCENARIO__GRLSPEC, msgs);
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
                case GrlPackage.EVALUATION_SCENARIO__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.EVALUATION_SCENARIO__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.EVALUATION_SCENARIO__EVALUATIONS:
                    return ((InternalEList)getEvaluations()).basicRemove(otherEnd, msgs);
                case GrlPackage.EVALUATION_SCENARIO__GROUP:
                    return basicSetGroup(null, msgs);
                case GrlPackage.EVALUATION_SCENARIO__GRLSPEC:
                    return eBasicSetContainer(null, GrlPackage.EVALUATION_SCENARIO__GRLSPEC, msgs);
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
                case GrlPackage.EVALUATION_SCENARIO__GRLSPEC:
                    return eContainer.eInverseRemove(this, GrlPackage.GR_LSPEC__SCENARIOS, GRLspec.class, msgs);
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
            case GrlPackage.EVALUATION_SCENARIO__FROM_LINKS:
                return getFromLinks();
            case GrlPackage.EVALUATION_SCENARIO__TO_LINKS:
                return getToLinks();
            case GrlPackage.EVALUATION_SCENARIO__ID:
                return getId();
            case GrlPackage.EVALUATION_SCENARIO__NAME:
                return getName();
            case GrlPackage.EVALUATION_SCENARIO__DESCRIPTION:
                return getDescription();
            case GrlPackage.EVALUATION_SCENARIO__AUTHOR:
                return getAuthor();
            case GrlPackage.EVALUATION_SCENARIO__EVALUATIONS:
                return getEvaluations();
            case GrlPackage.EVALUATION_SCENARIO__GROUP:
                if (resolve) return getGroup();
                return basicGetGroup();
            case GrlPackage.EVALUATION_SCENARIO__GRLSPEC:
                return getGrlspec();
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
            case GrlPackage.EVALUATION_SCENARIO__FROM_LINKS:
                getFromLinks().clear();
                getFromLinks().addAll((Collection)newValue);
                return;
            case GrlPackage.EVALUATION_SCENARIO__TO_LINKS:
                getToLinks().clear();
                getToLinks().addAll((Collection)newValue);
                return;
            case GrlPackage.EVALUATION_SCENARIO__ID:
                setId((String)newValue);
                return;
            case GrlPackage.EVALUATION_SCENARIO__NAME:
                setName((String)newValue);
                return;
            case GrlPackage.EVALUATION_SCENARIO__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case GrlPackage.EVALUATION_SCENARIO__AUTHOR:
                setAuthor((String)newValue);
                return;
            case GrlPackage.EVALUATION_SCENARIO__EVALUATIONS:
                getEvaluations().clear();
                getEvaluations().addAll((Collection)newValue);
                return;
            case GrlPackage.EVALUATION_SCENARIO__GROUP:
                setGroup((EvaluationGroup)newValue);
                return;
            case GrlPackage.EVALUATION_SCENARIO__GRLSPEC:
                setGrlspec((GRLspec)newValue);
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
            case GrlPackage.EVALUATION_SCENARIO__FROM_LINKS:
                getFromLinks().clear();
                return;
            case GrlPackage.EVALUATION_SCENARIO__TO_LINKS:
                getToLinks().clear();
                return;
            case GrlPackage.EVALUATION_SCENARIO__ID:
                setId(ID_EDEFAULT);
                return;
            case GrlPackage.EVALUATION_SCENARIO__NAME:
                setName(NAME_EDEFAULT);
                return;
            case GrlPackage.EVALUATION_SCENARIO__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case GrlPackage.EVALUATION_SCENARIO__AUTHOR:
                setAuthor(AUTHOR_EDEFAULT);
                return;
            case GrlPackage.EVALUATION_SCENARIO__EVALUATIONS:
                getEvaluations().clear();
                return;
            case GrlPackage.EVALUATION_SCENARIO__GROUP:
                setGroup((EvaluationGroup)null);
                return;
            case GrlPackage.EVALUATION_SCENARIO__GRLSPEC:
                setGrlspec((GRLspec)null);
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
            case GrlPackage.EVALUATION_SCENARIO__FROM_LINKS:
                return fromLinks != null && !fromLinks.isEmpty();
            case GrlPackage.EVALUATION_SCENARIO__TO_LINKS:
                return toLinks != null && !toLinks.isEmpty();
            case GrlPackage.EVALUATION_SCENARIO__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case GrlPackage.EVALUATION_SCENARIO__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case GrlPackage.EVALUATION_SCENARIO__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case GrlPackage.EVALUATION_SCENARIO__AUTHOR:
                return AUTHOR_EDEFAULT == null ? author != null : !AUTHOR_EDEFAULT.equals(author);
            case GrlPackage.EVALUATION_SCENARIO__EVALUATIONS:
                return evaluations != null && !evaluations.isEmpty();
            case GrlPackage.EVALUATION_SCENARIO__GROUP:
                return group != null;
            case GrlPackage.EVALUATION_SCENARIO__GRLSPEC:
                return getGrlspec() != null;
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
        result.append(" (author: ");
        result.append(author);
        result.append(')');
        return result.toString();
    }

} //EvaluationScenarioImpl
