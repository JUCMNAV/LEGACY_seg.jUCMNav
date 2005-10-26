/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Evaluation;
import grl.EvaluationSet;
import grl.GrlPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import urncore.impl.GRLmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Evaluation Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.EvaluationSetImpl#getEvaluations <em>Evaluations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EvaluationSetImpl extends GRLmodelElementImpl implements EvaluationSet {
    /**
     * The cached value of the '{@link #getEvaluations() <em>Evaluations</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEvaluations()
     * @generated
     * @ordered
     */
    protected EList evaluations = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EvaluationSetImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return GrlPackage.eINSTANCE.getEvaluationSet();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getEvaluations() {
        if (evaluations == null) {
            evaluations = new EObjectWithInverseResolvingEList(Evaluation.class, this, GrlPackage.EVALUATION_SET__EVALUATIONS, GrlPackage.EVALUATION__SET);
        }
        return evaluations;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.EVALUATION_SET__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.EVALUATION_SET__EVALUATIONS:
                    return ((InternalEList)getEvaluations()).basicAdd(otherEnd, msgs);
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
                case GrlPackage.EVALUATION_SET__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.EVALUATION_SET__EVALUATIONS:
                    return ((InternalEList)getEvaluations()).basicRemove(otherEnd, msgs);
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
            case GrlPackage.EVALUATION_SET__ID:
                return getId();
            case GrlPackage.EVALUATION_SET__NAME:
                return getName();
            case GrlPackage.EVALUATION_SET__DESCRIPTION:
                return getDescription();
            case GrlPackage.EVALUATION_SET__URN_LINKS:
                return getUrnLinks();
            case GrlPackage.EVALUATION_SET__EVALUATIONS:
                return getEvaluations();
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
            case GrlPackage.EVALUATION_SET__ID:
                setId((String)newValue);
                return;
            case GrlPackage.EVALUATION_SET__NAME:
                setName((String)newValue);
                return;
            case GrlPackage.EVALUATION_SET__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case GrlPackage.EVALUATION_SET__URN_LINKS:
                getUrnLinks().clear();
                getUrnLinks().addAll((Collection)newValue);
                return;
            case GrlPackage.EVALUATION_SET__EVALUATIONS:
                getEvaluations().clear();
                getEvaluations().addAll((Collection)newValue);
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
            case GrlPackage.EVALUATION_SET__ID:
                setId(ID_EDEFAULT);
                return;
            case GrlPackage.EVALUATION_SET__NAME:
                setName(NAME_EDEFAULT);
                return;
            case GrlPackage.EVALUATION_SET__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case GrlPackage.EVALUATION_SET__URN_LINKS:
                getUrnLinks().clear();
                return;
            case GrlPackage.EVALUATION_SET__EVALUATIONS:
                getEvaluations().clear();
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
            case GrlPackage.EVALUATION_SET__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case GrlPackage.EVALUATION_SET__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case GrlPackage.EVALUATION_SET__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case GrlPackage.EVALUATION_SET__URN_LINKS:
                return urnLinks != null && !urnLinks.isEmpty();
            case GrlPackage.EVALUATION_SET__EVALUATIONS:
                return evaluations != null && !evaluations.isEmpty();
        }
        return eDynamicIsSet(eFeature);
    }

} //EvaluationSetImpl
