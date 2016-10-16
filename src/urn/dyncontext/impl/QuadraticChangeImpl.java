/**
 */
package urn.dyncontext.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import urn.dyncontext.DyncontextPackage;
import urn.dyncontext.QuadraticChange;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Quadratic Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link urn.dyncontext.impl.QuadraticChangeImpl#getQuadraticCoefficient <em>Quadratic Coefficient</em>}</li>
 *   <li>{@link urn.dyncontext.impl.QuadraticChangeImpl#getLinearCoefficient <em>Linear Coefficient</em>}</li>
 *   <li>{@link urn.dyncontext.impl.QuadraticChangeImpl#getConstant <em>Constant</em>}</li>
 * </ul>
 *
 * @generated
 */
public class QuadraticChangeImpl extends NumericChangeImpl implements QuadraticChange {
	/**
	 * The default value of the '{@link #getQuadraticCoefficient() <em>Quadratic Coefficient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuadraticCoefficient()
	 * @generated
	 * @ordered
	 */
	protected static final float QUADRATIC_COEFFICIENT_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getQuadraticCoefficient() <em>Quadratic Coefficient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQuadraticCoefficient()
	 * @generated
	 * @ordered
	 */
	protected float quadraticCoefficient = QUADRATIC_COEFFICIENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getLinearCoefficient() <em>Linear Coefficient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinearCoefficient()
	 * @generated
	 * @ordered
	 */
	protected static final float LINEAR_COEFFICIENT_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getLinearCoefficient() <em>Linear Coefficient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinearCoefficient()
	 * @generated
	 * @ordered
	 */
	protected float linearCoefficient = LINEAR_COEFFICIENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getConstant() <em>Constant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstant()
	 * @generated
	 * @ordered
	 */
	protected static final float CONSTANT_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getConstant() <em>Constant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstant()
	 * @generated
	 * @ordered
	 */
	protected float constant = CONSTANT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QuadraticChangeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return DyncontextPackage.Literals.QUADRATIC_CHANGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getQuadraticCoefficient() {
		return quadraticCoefficient;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQuadraticCoefficient(float newQuadraticCoefficient) {
		float oldQuadraticCoefficient = quadraticCoefficient;
		quadraticCoefficient = newQuadraticCoefficient;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DyncontextPackage.QUADRATIC_CHANGE__QUADRATIC_COEFFICIENT, oldQuadraticCoefficient, quadraticCoefficient));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getLinearCoefficient() {
		return linearCoefficient;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLinearCoefficient(float newLinearCoefficient) {
		float oldLinearCoefficient = linearCoefficient;
		linearCoefficient = newLinearCoefficient;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DyncontextPackage.QUADRATIC_CHANGE__LINEAR_COEFFICIENT, oldLinearCoefficient, linearCoefficient));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getConstant() {
		return constant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstant(float newConstant) {
		float oldConstant = constant;
		constant = newConstant;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DyncontextPackage.QUADRATIC_CHANGE__CONSTANT, oldConstant, constant));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DyncontextPackage.QUADRATIC_CHANGE__QUADRATIC_COEFFICIENT:
				return new Float(getQuadraticCoefficient());
			case DyncontextPackage.QUADRATIC_CHANGE__LINEAR_COEFFICIENT:
				return new Float(getLinearCoefficient());
			case DyncontextPackage.QUADRATIC_CHANGE__CONSTANT:
				return new Float(getConstant());
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
			case DyncontextPackage.QUADRATIC_CHANGE__QUADRATIC_COEFFICIENT:
				setQuadraticCoefficient(((Float)newValue).floatValue());
				return;
			case DyncontextPackage.QUADRATIC_CHANGE__LINEAR_COEFFICIENT:
				setLinearCoefficient(((Float)newValue).floatValue());
				return;
			case DyncontextPackage.QUADRATIC_CHANGE__CONSTANT:
				setConstant(((Float)newValue).floatValue());
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
			case DyncontextPackage.QUADRATIC_CHANGE__QUADRATIC_COEFFICIENT:
				setQuadraticCoefficient(QUADRATIC_COEFFICIENT_EDEFAULT);
				return;
			case DyncontextPackage.QUADRATIC_CHANGE__LINEAR_COEFFICIENT:
				setLinearCoefficient(LINEAR_COEFFICIENT_EDEFAULT);
				return;
			case DyncontextPackage.QUADRATIC_CHANGE__CONSTANT:
				setConstant(CONSTANT_EDEFAULT);
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
			case DyncontextPackage.QUADRATIC_CHANGE__QUADRATIC_COEFFICIENT:
				return quadraticCoefficient != QUADRATIC_COEFFICIENT_EDEFAULT;
			case DyncontextPackage.QUADRATIC_CHANGE__LINEAR_COEFFICIENT:
				return linearCoefficient != LINEAR_COEFFICIENT_EDEFAULT;
			case DyncontextPackage.QUADRATIC_CHANGE__CONSTANT:
				return constant != CONSTANT_EDEFAULT;
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
		result.append(" (quadraticCoefficient: ");
		result.append(quadraticCoefficient);
		result.append(", linearCoefficient: ");
		result.append(linearCoefficient);
		result.append(", constant: ");
		result.append(constant);
		result.append(')');
		return result.toString();
	}

} //QuadraticChangeImpl
