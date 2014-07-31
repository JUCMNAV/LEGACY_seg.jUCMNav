/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Evaluation;
import grl.EvaluationRange;
import grl.EvaluationStrategy;
import grl.GrlPackage;
import grl.IntentionalElement;
import grl.QualitativeLabel;
import grl.kpimodel.KPIEvalValueSet;
import grl.kpimodel.KPINewEvalValue;
import grl.kpimodel.KpimodelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Evaluation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.EvaluationImpl#getEvaluation <em>Evaluation</em>}</li>
 *   <li>{@link grl.impl.EvaluationImpl#getQualitativeEvaluation <em>Qualitative Evaluation</em>}</li>
 *   <li>{@link grl.impl.EvaluationImpl#isExceeds <em>Exceeds</em>}</li>
 *   <li>{@link grl.impl.EvaluationImpl#getIntElement <em>Int Element</em>}</li>
 *   <li>{@link grl.impl.EvaluationImpl#getStrategies <em>Strategies</em>}</li>
 *   <li>{@link grl.impl.EvaluationImpl#getKpiEvalValueSet <em>Kpi Eval Value Set</em>}</li>
 *   <li>{@link grl.impl.EvaluationImpl#getEvalRange <em>Eval Range</em>}</li>
 *   <li>{@link grl.impl.EvaluationImpl#getKpiNewEvalValue <em>Kpi New Eval Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EvaluationImpl extends MinimalEObjectImpl.Container implements Evaluation {
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
	 * The default value of the '{@link #getQualitativeEvaluation() <em>Qualitative Evaluation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualitativeEvaluation()
	 * @generated
	 * @ordered
	 */
	protected static final QualitativeLabel QUALITATIVE_EVALUATION_EDEFAULT = QualitativeLabel.NONE_LITERAL;

				/**
	 * The cached value of the '{@link #getQualitativeEvaluation() <em>Qualitative Evaluation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQualitativeEvaluation()
	 * @generated
	 * @ordered
	 */
	protected QualitativeLabel qualitativeEvaluation = QUALITATIVE_EVALUATION_EDEFAULT;

				/**
	 * The default value of the '{@link #isExceeds() <em>Exceeds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExceeds()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXCEEDS_EDEFAULT = false;

				/**
	 * The cached value of the '{@link #isExceeds() <em>Exceeds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isExceeds()
	 * @generated
	 * @ordered
	 */
	protected boolean exceeds = EXCEEDS_EDEFAULT;

				/**
	 * The cached value of the '{@link #getIntElement() <em>Int Element</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getIntElement()
	 * @generated
	 * @ordered
	 */
    protected IntentionalElement intElement;

    /**
	 * The cached value of the '{@link #getKpiEvalValueSet() <em>Kpi Eval Value Set</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getKpiEvalValueSet()
	 * @generated
	 * @ordered
	 */
    protected KPIEvalValueSet kpiEvalValueSet;

    /**
	 * The cached value of the '{@link #getEvalRange() <em>Eval Range</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEvalRange()
	 * @generated
	 * @ordered
	 */
	protected EvaluationRange evalRange;

				/**
	 * The cached value of the '{@link #getKpiNewEvalValue() <em>Kpi New Eval Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKpiNewEvalValue()
	 * @generated
	 * @ordered
	 */
	protected KPINewEvalValue kpiNewEvalValue;

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
	public QualitativeLabel getQualitativeEvaluation() {
		return qualitativeEvaluation;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQualitativeEvaluation(QualitativeLabel newQualitativeEvaluation) {
		QualitativeLabel oldQualitativeEvaluation = qualitativeEvaluation;
		qualitativeEvaluation = newQualitativeEvaluation == null ? QUALITATIVE_EVALUATION_EDEFAULT : newQualitativeEvaluation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION__QUALITATIVE_EVALUATION, oldQualitativeEvaluation, qualitativeEvaluation));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isExceeds() {
		return exceeds;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExceeds(boolean newExceeds) {
		boolean oldExceeds = exceeds;
		exceeds = newExceeds;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION__EXCEEDS, oldExceeds, exceeds));
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
		if (eContainerFeatureID() != GrlPackage.EVALUATION__STRATEGIES) return null;
		return (EvaluationStrategy)eInternalContainer();
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
		if (newStrategies != eInternalContainer() || (eContainerFeatureID() != GrlPackage.EVALUATION__STRATEGIES && newStrategies != null)) {
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
    public KPIEvalValueSet getKpiEvalValueSet() {
		return kpiEvalValueSet;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetKpiEvalValueSet(KPIEvalValueSet newKpiEvalValueSet, NotificationChain msgs) {
		KPIEvalValueSet oldKpiEvalValueSet = kpiEvalValueSet;
		kpiEvalValueSet = newKpiEvalValueSet;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION__KPI_EVAL_VALUE_SET, oldKpiEvalValueSet, newKpiEvalValueSet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setKpiEvalValueSet(KPIEvalValueSet newKpiEvalValueSet) {
		if (newKpiEvalValueSet != kpiEvalValueSet) {
			NotificationChain msgs = null;
			if (kpiEvalValueSet != null)
				msgs = ((InternalEObject)kpiEvalValueSet).eInverseRemove(this, KpimodelPackage.KPI_EVAL_VALUE_SET__EVAL, KPIEvalValueSet.class, msgs);
			if (newKpiEvalValueSet != null)
				msgs = ((InternalEObject)newKpiEvalValueSet).eInverseAdd(this, KpimodelPackage.KPI_EVAL_VALUE_SET__EVAL, KPIEvalValueSet.class, msgs);
			msgs = basicSetKpiEvalValueSet(newKpiEvalValueSet, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION__KPI_EVAL_VALUE_SET, newKpiEvalValueSet, newKpiEvalValueSet));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EvaluationRange getEvalRange() {
		return evalRange;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEvalRange(EvaluationRange newEvalRange, NotificationChain msgs) {
		EvaluationRange oldEvalRange = evalRange;
		evalRange = newEvalRange;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION__EVAL_RANGE, oldEvalRange, newEvalRange);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEvalRange(EvaluationRange newEvalRange) {
		if (newEvalRange != evalRange) {
			NotificationChain msgs = null;
			if (evalRange != null)
				msgs = ((InternalEObject)evalRange).eInverseRemove(this, GrlPackage.EVALUATION_RANGE__EVAL, EvaluationRange.class, msgs);
			if (newEvalRange != null)
				msgs = ((InternalEObject)newEvalRange).eInverseAdd(this, GrlPackage.EVALUATION_RANGE__EVAL, EvaluationRange.class, msgs);
			msgs = basicSetEvalRange(newEvalRange, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION__EVAL_RANGE, newEvalRange, newEvalRange));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KPINewEvalValue getKpiNewEvalValue() {
		return kpiNewEvalValue;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetKpiNewEvalValue(KPINewEvalValue newKpiNewEvalValue, NotificationChain msgs) {
		KPINewEvalValue oldKpiNewEvalValue = kpiNewEvalValue;
		kpiNewEvalValue = newKpiNewEvalValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION__KPI_NEW_EVAL_VALUE, oldKpiNewEvalValue, newKpiNewEvalValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKpiNewEvalValue(KPINewEvalValue newKpiNewEvalValue) {
		if (newKpiNewEvalValue != kpiNewEvalValue) {
			NotificationChain msgs = null;
			if (kpiNewEvalValue != null)
				msgs = ((InternalEObject)kpiNewEvalValue).eInverseRemove(this, KpimodelPackage.KPI_NEW_EVAL_VALUE__EVAL, KPINewEvalValue.class, msgs);
			if (newKpiNewEvalValue != null)
				msgs = ((InternalEObject)newKpiNewEvalValue).eInverseAdd(this, KpimodelPackage.KPI_NEW_EVAL_VALUE__EVAL, KPINewEvalValue.class, msgs);
			msgs = basicSetKpiNewEvalValue(newKpiNewEvalValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.EVALUATION__KPI_NEW_EVAL_VALUE, newKpiNewEvalValue, newKpiNewEvalValue));
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
			case GrlPackage.EVALUATION__KPI_EVAL_VALUE_SET:
				if (kpiEvalValueSet != null)
					msgs = ((InternalEObject)kpiEvalValueSet).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GrlPackage.EVALUATION__KPI_EVAL_VALUE_SET, null, msgs);
				return basicSetKpiEvalValueSet((KPIEvalValueSet)otherEnd, msgs);
			case GrlPackage.EVALUATION__EVAL_RANGE:
				if (evalRange != null)
					msgs = ((InternalEObject)evalRange).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GrlPackage.EVALUATION__EVAL_RANGE, null, msgs);
				return basicSetEvalRange((EvaluationRange)otherEnd, msgs);
			case GrlPackage.EVALUATION__KPI_NEW_EVAL_VALUE:
				if (kpiNewEvalValue != null)
					msgs = ((InternalEObject)kpiNewEvalValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GrlPackage.EVALUATION__KPI_NEW_EVAL_VALUE, null, msgs);
				return basicSetKpiNewEvalValue((KPINewEvalValue)otherEnd, msgs);
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
			case GrlPackage.EVALUATION__KPI_EVAL_VALUE_SET:
				return basicSetKpiEvalValueSet(null, msgs);
			case GrlPackage.EVALUATION__EVAL_RANGE:
				return basicSetEvalRange(null, msgs);
			case GrlPackage.EVALUATION__KPI_NEW_EVAL_VALUE:
				return basicSetKpiNewEvalValue(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
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
			case GrlPackage.EVALUATION__QUALITATIVE_EVALUATION:
				return getQualitativeEvaluation();
			case GrlPackage.EVALUATION__EXCEEDS:
				return isExceeds() ? Boolean.TRUE : Boolean.FALSE;
			case GrlPackage.EVALUATION__INT_ELEMENT:
				if (resolve) return getIntElement();
				return basicGetIntElement();
			case GrlPackage.EVALUATION__STRATEGIES:
				return getStrategies();
			case GrlPackage.EVALUATION__KPI_EVAL_VALUE_SET:
				return getKpiEvalValueSet();
			case GrlPackage.EVALUATION__EVAL_RANGE:
				return getEvalRange();
			case GrlPackage.EVALUATION__KPI_NEW_EVAL_VALUE:
				return getKpiNewEvalValue();
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
			case GrlPackage.EVALUATION__QUALITATIVE_EVALUATION:
				setQualitativeEvaluation((QualitativeLabel)newValue);
				return;
			case GrlPackage.EVALUATION__EXCEEDS:
				setExceeds(((Boolean)newValue).booleanValue());
				return;
			case GrlPackage.EVALUATION__INT_ELEMENT:
				setIntElement((IntentionalElement)newValue);
				return;
			case GrlPackage.EVALUATION__STRATEGIES:
				setStrategies((EvaluationStrategy)newValue);
				return;
			case GrlPackage.EVALUATION__KPI_EVAL_VALUE_SET:
				setKpiEvalValueSet((KPIEvalValueSet)newValue);
				return;
			case GrlPackage.EVALUATION__EVAL_RANGE:
				setEvalRange((EvaluationRange)newValue);
				return;
			case GrlPackage.EVALUATION__KPI_NEW_EVAL_VALUE:
				setKpiNewEvalValue((KPINewEvalValue)newValue);
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
			case GrlPackage.EVALUATION__QUALITATIVE_EVALUATION:
				setQualitativeEvaluation(QUALITATIVE_EVALUATION_EDEFAULT);
				return;
			case GrlPackage.EVALUATION__EXCEEDS:
				setExceeds(EXCEEDS_EDEFAULT);
				return;
			case GrlPackage.EVALUATION__INT_ELEMENT:
				setIntElement((IntentionalElement)null);
				return;
			case GrlPackage.EVALUATION__STRATEGIES:
				setStrategies((EvaluationStrategy)null);
				return;
			case GrlPackage.EVALUATION__KPI_EVAL_VALUE_SET:
				setKpiEvalValueSet((KPIEvalValueSet)null);
				return;
			case GrlPackage.EVALUATION__EVAL_RANGE:
				setEvalRange((EvaluationRange)null);
				return;
			case GrlPackage.EVALUATION__KPI_NEW_EVAL_VALUE:
				setKpiNewEvalValue((KPINewEvalValue)null);
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
			case GrlPackage.EVALUATION__QUALITATIVE_EVALUATION:
				return qualitativeEvaluation != QUALITATIVE_EVALUATION_EDEFAULT;
			case GrlPackage.EVALUATION__EXCEEDS:
				return exceeds != EXCEEDS_EDEFAULT;
			case GrlPackage.EVALUATION__INT_ELEMENT:
				return intElement != null;
			case GrlPackage.EVALUATION__STRATEGIES:
				return getStrategies() != null;
			case GrlPackage.EVALUATION__KPI_EVAL_VALUE_SET:
				return kpiEvalValueSet != null;
			case GrlPackage.EVALUATION__EVAL_RANGE:
				return evalRange != null;
			case GrlPackage.EVALUATION__KPI_NEW_EVAL_VALUE:
				return kpiNewEvalValue != null;
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
		result.append(", qualitativeEvaluation: ");
		result.append(qualitativeEvaluation);
		result.append(", exceeds: ");
		result.append(exceeds);
		result.append(')');
		return result.toString();
	}

} //EvaluationImpl
