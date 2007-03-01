/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GrlPackage;
import grl.IntentionalElement;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Evaluation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.EvaluationImpl#getEvaluation <em>Evaluation</em>}</li>
 *   <li>{@link grl.impl.EvaluationImpl#getIntElement <em>Int Element</em>}</li>
 *   <li>{@link grl.impl.EvaluationImpl#getStrategies <em>Strategies</em>}</li>
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
        return GrlPackage.Literals.EVALUATION;
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
            InternalEObject oldIntElement = (InternalEObject)intElement;
            intElement = (IntentionalElement)eResolveProxy(oldIntElement);
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
    public EvaluationStrategy getStrategies() {
        if (eContainerFeatureID != GrlPackage.EVALUATION__STRATEGIES) return null;
        return (EvaluationStrategy)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetStrategies(EvaluationStrategy newStrategies, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newStrategies, GrlPackage.EVALUATION__STRATEGIES, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setStrategies(EvaluationStrategy newStrategies) {
        if (newStrategies != eInternalContainer() || (eContainerFeatureID != GrlPackage.EVALUATION__STRATEGIES && newStrategies != null)) {
            if (EcoreUtil.isAncestor(this, newStrategies))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newStrategies != null)
                msgs = ((InternalEObject)newStrategies).eInverseAdd(this, GrlPackage.EVALUATION_STRATEGY__EVALUATIONS, EvaluationStrategy.class, msgs);
            msgs = basicSetStrategies(newStrategies, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION__STRATEGIES, newStrategies, newStrategies));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case GrlPackage.EVALUATION__STRATEGIES:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetStrategies((EvaluationStrategy)otherEnd, msgs);
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
            case GrlPackage.EVALUATION__STRATEGIES:
                return basicSetStrategies(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID) {
            case GrlPackage.EVALUATION__STRATEGIES:
                return eInternalContainer().eInverseRemove(this, GrlPackage.EVALUATION_STRATEGY__EVALUATIONS, EvaluationStrategy.class, msgs);
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
            case GrlPackage.EVALUATION__EVALUATION:
                return new Integer(getEvaluation());
            case GrlPackage.EVALUATION__INT_ELEMENT:
                if (resolve) return getIntElement();
                return basicGetIntElement();
            case GrlPackage.EVALUATION__STRATEGIES:
                return getStrategies();
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
            case GrlPackage.EVALUATION__EVALUATION:
                setEvaluation(((Integer)newValue).intValue());
                return;
            case GrlPackage.EVALUATION__INT_ELEMENT:
                setIntElement((IntentionalElement)newValue);
                return;
            case GrlPackage.EVALUATION__STRATEGIES:
                setStrategies((EvaluationStrategy)newValue);
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
            case GrlPackage.EVALUATION__EVALUATION:
                setEvaluation(EVALUATION_EDEFAULT);
                return;
            case GrlPackage.EVALUATION__INT_ELEMENT:
                setIntElement((IntentionalElement)null);
                return;
            case GrlPackage.EVALUATION__STRATEGIES:
                setStrategies((EvaluationStrategy)null);
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
            case GrlPackage.EVALUATION__EVALUATION:
                return evaluation != EVALUATION_EDEFAULT;
            case GrlPackage.EVALUATION__INT_ELEMENT:
                return intElement != null;
            case GrlPackage.EVALUATION__STRATEGIES:
                return getStrategies() != null;
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
        result.append(" (evaluation: ");
        result.append(evaluation);
        result.append(')');
        return result.toString();
    }

} //EvaluationImpl
