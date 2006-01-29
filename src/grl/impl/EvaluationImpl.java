/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Evaluation;
import grl.EvaluationScenario;
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
 *   <li>{@link grl.impl.EvaluationImpl#getScenario <em>Scenario</em>}</li>
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
    protected static final int EVALUATION_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getEvaluation() <em>Evaluation</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEvaluation()
     * @generated
     * @ordered
     */
    protected int evaluation = EVALUATION_EDEFAULT;

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
     * The cached value of the '{@link #getScenario() <em>Scenario</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getScenario()
     * @generated
     * @ordered
     */
    protected EvaluationScenario scenario = null;

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
    public int getEvaluation() {
        return evaluation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setEvaluation(int newEvaluation) {
        int oldEvaluation = evaluation;
        evaluation = newEvaluation;
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
    public void setIntElement(IntentionalElement newIntElement) {
        IntentionalElement oldIntElement = intElement;
        intElement = newIntElement;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION__INT_ELEMENT, oldIntElement, intElement));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EvaluationScenario getScenario() {
        if (scenario != null && scenario.eIsProxy()) {
            EvaluationScenario oldScenario = scenario;
            scenario = (EvaluationScenario)eResolveProxy((InternalEObject)scenario);
            if (scenario != oldScenario) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.EVALUATION__SCENARIO, oldScenario, scenario));
            }
        }
        return scenario;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EvaluationScenario basicGetScenario() {
        return scenario;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetScenario(EvaluationScenario newScenario, NotificationChain msgs) {
        EvaluationScenario oldScenario = scenario;
        scenario = newScenario;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION__SCENARIO, oldScenario, newScenario);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setScenario(EvaluationScenario newScenario) {
        if (newScenario != scenario) {
            NotificationChain msgs = null;
            if (scenario != null)
                msgs = ((InternalEObject)scenario).eInverseRemove(this, GrlPackage.EVALUATION_SCENARIO__EVALUATIONS, EvaluationScenario.class, msgs);
            if (newScenario != null)
                msgs = ((InternalEObject)newScenario).eInverseAdd(this, GrlPackage.EVALUATION_SCENARIO__EVALUATIONS, EvaluationScenario.class, msgs);
            msgs = basicSetScenario(newScenario, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION__SCENARIO, newScenario, newScenario));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.EVALUATION__SCENARIO:
                    if (scenario != null)
                        msgs = ((InternalEObject)scenario).eInverseRemove(this, GrlPackage.EVALUATION_SCENARIO__EVALUATIONS, EvaluationScenario.class, msgs);
                    return basicSetScenario((EvaluationScenario)otherEnd, msgs);
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
                case GrlPackage.EVALUATION__SCENARIO:
                    return basicSetScenario(null, msgs);
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
                return new Integer(getEvaluation());
            case GrlPackage.EVALUATION__INT_ELEMENT:
                if (resolve) return getIntElement();
                return basicGetIntElement();
            case GrlPackage.EVALUATION__SCENARIO:
                if (resolve) return getScenario();
                return basicGetScenario();
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
                setEvaluation(((Integer)newValue).intValue());
                return;
            case GrlPackage.EVALUATION__INT_ELEMENT:
                setIntElement((IntentionalElement)newValue);
                return;
            case GrlPackage.EVALUATION__SCENARIO:
                setScenario((EvaluationScenario)newValue);
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
            case GrlPackage.EVALUATION__SCENARIO:
                setScenario((EvaluationScenario)null);
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
            case GrlPackage.EVALUATION__SCENARIO:
                return scenario != null;
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
