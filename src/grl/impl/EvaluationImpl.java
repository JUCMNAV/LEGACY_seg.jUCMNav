/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Evaluation;
import grl.EvaluationLevel;
import grl.EvaluationSet;
import grl.GrlPackage;
import grl.IntentionalElement;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Evaluation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.EvaluationImpl#getEvaluation <em>Evaluation</em>}</li>
 *   <li>{@link grl.impl.EvaluationImpl#getIntElement <em>Int Element</em>}</li>
 *   <li>{@link grl.impl.EvaluationImpl#getSet <em>Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EvaluationImpl extends EObjectImpl implements Evaluation {
    /**
     * The default value of the '{@link #getEvaluation() <em>Evaluation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEvaluation()
     * @generated
     * @ordered
     */
    protected static final EvaluationLevel EVALUATION_EDEFAULT = EvaluationLevel.SATISFICED_LITERAL;

    /**
     * The cached value of the '{@link #getEvaluation() <em>Evaluation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEvaluation()
     * @generated
     * @ordered
     */
    protected EvaluationLevel evaluation = EVALUATION_EDEFAULT;

    /**
     * The cached value of the '{@link #getIntElement() <em>Int Element</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIntElement()
     * @generated
     * @ordered
     */
    protected IntentionalElement intElement = null;

    /**
     * The cached value of the '{@link #getSet() <em>Set</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSet()
     * @generated
     * @ordered
     */
    protected EvaluationSet set = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EvaluationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return GrlPackage.eINSTANCE.getEvaluation();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EvaluationLevel getEvaluation() {
        return evaluation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEvaluation(EvaluationLevel newEvaluation) {
        EvaluationLevel oldEvaluation = evaluation;
        evaluation = newEvaluation == null ? EVALUATION_EDEFAULT : newEvaluation;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION__EVALUATION, oldEvaluation, evaluation));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElement getIntElement() {
        if (intElement != null && intElement.eIsProxy()) {
            IntentionalElement oldIntElement = intElement;
            intElement = (IntentionalElement)eResolveProxy((InternalEObject)intElement);
            if (intElement != oldIntElement) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.EVALUATION__INT_ELEMENT, oldIntElement, intElement));
            }
        }
        return intElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElement basicGetIntElement() {
        return intElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetIntElement(IntentionalElement newIntElement, NotificationChain msgs) {
        IntentionalElement oldIntElement = intElement;
        intElement = newIntElement;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION__INT_ELEMENT, oldIntElement, newIntElement);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIntElement(IntentionalElement newIntElement) {
        if (newIntElement != intElement) {
            NotificationChain msgs = null;
            if (intElement != null)
                msgs = ((InternalEObject)intElement).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__EVALS, IntentionalElement.class, msgs);
            if (newIntElement != null)
                msgs = ((InternalEObject)newIntElement).eInverseAdd(this, GrlPackage.INTENTIONAL_ELEMENT__EVALS, IntentionalElement.class, msgs);
            msgs = basicSetIntElement(newIntElement, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION__INT_ELEMENT, newIntElement, newIntElement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EvaluationSet getSet() {
        if (set != null && set.eIsProxy()) {
            EvaluationSet oldSet = set;
            set = (EvaluationSet)eResolveProxy((InternalEObject)set);
            if (set != oldSet) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.EVALUATION__SET, oldSet, set));
            }
        }
        return set;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EvaluationSet basicGetSet() {
        return set;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSet(EvaluationSet newSet, NotificationChain msgs) {
        EvaluationSet oldSet = set;
        set = newSet;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION__SET, oldSet, newSet);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSet(EvaluationSet newSet) {
        if (newSet != set) {
            NotificationChain msgs = null;
            if (set != null)
                msgs = ((InternalEObject)set).eInverseRemove(this, GrlPackage.EVALUATION_SET__EVALUATIONS, EvaluationSet.class, msgs);
            if (newSet != null)
                msgs = ((InternalEObject)newSet).eInverseAdd(this, GrlPackage.EVALUATION_SET__EVALUATIONS, EvaluationSet.class, msgs);
            msgs = basicSetSet(newSet, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION__SET, newSet, newSet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.EVALUATION__INT_ELEMENT:
                    if (intElement != null)
                        msgs = ((InternalEObject)intElement).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__EVALS, IntentionalElement.class, msgs);
                    return basicSetIntElement((IntentionalElement)otherEnd, msgs);
                case GrlPackage.EVALUATION__SET:
                    if (set != null)
                        msgs = ((InternalEObject)set).eInverseRemove(this, GrlPackage.EVALUATION_SET__EVALUATIONS, EvaluationSet.class, msgs);
                    return basicSetSet((EvaluationSet)otherEnd, msgs);
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
                case GrlPackage.EVALUATION__INT_ELEMENT:
                    return basicSetIntElement(null, msgs);
                case GrlPackage.EVALUATION__SET:
                    return basicSetSet(null, msgs);
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
            case GrlPackage.EVALUATION__EVALUATION:
                return getEvaluation();
            case GrlPackage.EVALUATION__INT_ELEMENT:
                if (resolve) return getIntElement();
                return basicGetIntElement();
            case GrlPackage.EVALUATION__SET:
                if (resolve) return getSet();
                return basicGetSet();
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
            case GrlPackage.EVALUATION__EVALUATION:
                setEvaluation((EvaluationLevel)newValue);
                return;
            case GrlPackage.EVALUATION__INT_ELEMENT:
                setIntElement((IntentionalElement)newValue);
                return;
            case GrlPackage.EVALUATION__SET:
                setSet((EvaluationSet)newValue);
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
            case GrlPackage.EVALUATION__EVALUATION:
                setEvaluation(EVALUATION_EDEFAULT);
                return;
            case GrlPackage.EVALUATION__INT_ELEMENT:
                setIntElement((IntentionalElement)null);
                return;
            case GrlPackage.EVALUATION__SET:
                setSet((EvaluationSet)null);
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
            case GrlPackage.EVALUATION__EVALUATION:
                return evaluation != EVALUATION_EDEFAULT;
            case GrlPackage.EVALUATION__INT_ELEMENT:
                return intElement != null;
            case GrlPackage.EVALUATION__SET:
                return set != null;
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
        result.append(" (evaluation: ");
        result.append(evaluation);
        result.append(')');
        return result.toString();
    }

} //EvaluationImpl
