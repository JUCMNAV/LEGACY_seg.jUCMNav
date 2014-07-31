/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.kpimodel.impl;

import grl.QualitativeLabel;
import grl.kpimodel.KpimodelPackage;
import grl.kpimodel.QualitativeMapping;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Qualitative Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.kpimodel.impl.QualitativeMappingImpl#getRealWorldLabel <em>Real World Label</em>}</li>
 *   <li>{@link grl.kpimodel.impl.QualitativeMappingImpl#getEvaluation <em>Evaluation</em>}</li>
 *   <li>{@link grl.kpimodel.impl.QualitativeMappingImpl#getQualitativeEvaluation <em>Qualitative Evaluation</em>}</li>
 *   <li>{@link grl.kpimodel.impl.QualitativeMappingImpl#isExceeds <em>Exceeds</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QualitativeMappingImpl extends MinimalEObjectImpl.Container implements QualitativeMapping {
	/**
	 * The default value of the '{@link #getRealWorldLabel() <em>Real World Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRealWorldLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String REAL_WORLD_LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRealWorldLabel() <em>Real World Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRealWorldLabel()
	 * @generated
	 * @ordered
	 */
	protected String realWorldLabel = REAL_WORLD_LABEL_EDEFAULT;

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
	protected static final QualitativeLabel QUALITATIVE_EVALUATION_EDEFAULT = QualitativeLabel.DENIED_LITERAL;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QualitativeMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return KpimodelPackage.Literals.QUALITATIVE_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRealWorldLabel() {
		return realWorldLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRealWorldLabel(String newRealWorldLabel) {
		String oldRealWorldLabel = realWorldLabel;
		realWorldLabel = newRealWorldLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.QUALITATIVE_MAPPING__REAL_WORLD_LABEL, oldRealWorldLabel, realWorldLabel));
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
			eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.QUALITATIVE_MAPPING__EVALUATION, oldEvaluation, evaluation));
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
			eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.QUALITATIVE_MAPPING__QUALITATIVE_EVALUATION, oldQualitativeEvaluation, qualitativeEvaluation));
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
			eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.QUALITATIVE_MAPPING__EXCEEDS, oldExceeds, exceeds));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case KpimodelPackage.QUALITATIVE_MAPPING__REAL_WORLD_LABEL:
				return getRealWorldLabel();
			case KpimodelPackage.QUALITATIVE_MAPPING__EVALUATION:
				return new Integer(getEvaluation());
			case KpimodelPackage.QUALITATIVE_MAPPING__QUALITATIVE_EVALUATION:
				return getQualitativeEvaluation();
			case KpimodelPackage.QUALITATIVE_MAPPING__EXCEEDS:
				return isExceeds() ? Boolean.TRUE : Boolean.FALSE;
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
			case KpimodelPackage.QUALITATIVE_MAPPING__REAL_WORLD_LABEL:
				setRealWorldLabel((String)newValue);
				return;
			case KpimodelPackage.QUALITATIVE_MAPPING__EVALUATION:
				setEvaluation(((Integer)newValue).intValue());
				return;
			case KpimodelPackage.QUALITATIVE_MAPPING__QUALITATIVE_EVALUATION:
				setQualitativeEvaluation((QualitativeLabel)newValue);
				return;
			case KpimodelPackage.QUALITATIVE_MAPPING__EXCEEDS:
				setExceeds(((Boolean)newValue).booleanValue());
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
			case KpimodelPackage.QUALITATIVE_MAPPING__REAL_WORLD_LABEL:
				setRealWorldLabel(REAL_WORLD_LABEL_EDEFAULT);
				return;
			case KpimodelPackage.QUALITATIVE_MAPPING__EVALUATION:
				setEvaluation(EVALUATION_EDEFAULT);
				return;
			case KpimodelPackage.QUALITATIVE_MAPPING__QUALITATIVE_EVALUATION:
				setQualitativeEvaluation(QUALITATIVE_EVALUATION_EDEFAULT);
				return;
			case KpimodelPackage.QUALITATIVE_MAPPING__EXCEEDS:
				setExceeds(EXCEEDS_EDEFAULT);
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
			case KpimodelPackage.QUALITATIVE_MAPPING__REAL_WORLD_LABEL:
				return REAL_WORLD_LABEL_EDEFAULT == null ? realWorldLabel != null : !REAL_WORLD_LABEL_EDEFAULT.equals(realWorldLabel);
			case KpimodelPackage.QUALITATIVE_MAPPING__EVALUATION:
				return evaluation != EVALUATION_EDEFAULT;
			case KpimodelPackage.QUALITATIVE_MAPPING__QUALITATIVE_EVALUATION:
				return qualitativeEvaluation != QUALITATIVE_EVALUATION_EDEFAULT;
			case KpimodelPackage.QUALITATIVE_MAPPING__EXCEEDS:
				return exceeds != EXCEEDS_EDEFAULT;
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
		result.append(" (realWorldLabel: ");
		result.append(realWorldLabel);
		result.append(", evaluation: ");
		result.append(evaluation);
		result.append(", qualitativeEvaluation: ");
		result.append(qualitativeEvaluation);
		result.append(", exceeds: ");
		result.append(exceeds);
		result.append(')');
		return result.toString();
	}

} //QualitativeMappingImpl
