/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.InternalEList;

import ucm.performance.ArrivalProcess;
import ucm.performance.ClosedWorkload;
import ucm.performance.PerformancePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Closed Workload</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.performance.impl.ClosedWorkloadImpl#getPopulation <em>Population</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ClosedWorkloadImpl extends WorkloadImpl implements ClosedWorkload {
	/**
	 * The default value of the '{@link #getPopulation() <em>Population</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPopulation()
	 * @generated
	 * @ordered
	 */
	protected static final int POPULATION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPopulation() <em>Population</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPopulation()
	 * @generated
	 * @ordered
	 */
	protected int population = POPULATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ClosedWorkloadImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return PerformancePackage.eINSTANCE.getClosedWorkload();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPopulation() {
		return population;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPopulation(int newPopulation) {
		int oldPopulation = population;
		population = newPopulation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.CLOSED_WORKLOAD__POPULATION, oldPopulation, population));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case PerformancePackage.CLOSED_WORKLOAD__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
				case PerformancePackage.CLOSED_WORKLOAD__RESP_TIME:
					return ((InternalEList)getRespTime()).basicAdd(otherEnd, msgs);
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
				case PerformancePackage.CLOSED_WORKLOAD__URN_LINKS:
					return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
				case PerformancePackage.CLOSED_WORKLOAD__RESP_TIME:
					return ((InternalEList)getRespTime()).basicRemove(otherEnd, msgs);
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
			case PerformancePackage.CLOSED_WORKLOAD__URN_LINKS:
				return getUrnLinks();
			case PerformancePackage.CLOSED_WORKLOAD__ID:
				return getId();
			case PerformancePackage.CLOSED_WORKLOAD__NAME:
				return getName();
			case PerformancePackage.CLOSED_WORKLOAD__DESCRIPTION:
				return getDescription();
			case PerformancePackage.CLOSED_WORKLOAD__ARRIVAL_PATTERN:
				return getArrivalPattern();
			case PerformancePackage.CLOSED_WORKLOAD__ARRIVAL_PARAM1:
				return new Double(getArrivalParam1());
			case PerformancePackage.CLOSED_WORKLOAD__ARRIVAL_PARAM2:
				return new Double(getArrivalParam2());
			case PerformancePackage.CLOSED_WORKLOAD__EXTERNAL_DELAY:
				return new Double(getExternalDelay());
			case PerformancePackage.CLOSED_WORKLOAD__VALUE:
				return new Double(getValue());
			case PerformancePackage.CLOSED_WORKLOAD__COEFF_VAR_SEQ:
				return new Double(getCoeffVarSeq());
			case PerformancePackage.CLOSED_WORKLOAD__RESP_TIME:
				return getRespTime();
			case PerformancePackage.CLOSED_WORKLOAD__POPULATION:
				return new Integer(getPopulation());
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
			case PerformancePackage.CLOSED_WORKLOAD__URN_LINKS:
				getUrnLinks().clear();
				getUrnLinks().addAll((Collection)newValue);
				return;
			case PerformancePackage.CLOSED_WORKLOAD__ID:
				setId((String)newValue);
				return;
			case PerformancePackage.CLOSED_WORKLOAD__NAME:
				setName((String)newValue);
				return;
			case PerformancePackage.CLOSED_WORKLOAD__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case PerformancePackage.CLOSED_WORKLOAD__ARRIVAL_PATTERN:
				setArrivalPattern((ArrivalProcess)newValue);
				return;
			case PerformancePackage.CLOSED_WORKLOAD__ARRIVAL_PARAM1:
				setArrivalParam1(((Double)newValue).doubleValue());
				return;
			case PerformancePackage.CLOSED_WORKLOAD__ARRIVAL_PARAM2:
				setArrivalParam2(((Double)newValue).doubleValue());
				return;
			case PerformancePackage.CLOSED_WORKLOAD__EXTERNAL_DELAY:
				setExternalDelay(((Double)newValue).doubleValue());
				return;
			case PerformancePackage.CLOSED_WORKLOAD__VALUE:
				setValue(((Double)newValue).doubleValue());
				return;
			case PerformancePackage.CLOSED_WORKLOAD__COEFF_VAR_SEQ:
				setCoeffVarSeq(((Double)newValue).doubleValue());
				return;
			case PerformancePackage.CLOSED_WORKLOAD__RESP_TIME:
				getRespTime().clear();
				getRespTime().addAll((Collection)newValue);
				return;
			case PerformancePackage.CLOSED_WORKLOAD__POPULATION:
				setPopulation(((Integer)newValue).intValue());
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
			case PerformancePackage.CLOSED_WORKLOAD__URN_LINKS:
				getUrnLinks().clear();
				return;
			case PerformancePackage.CLOSED_WORKLOAD__ID:
				setId(ID_EDEFAULT);
				return;
			case PerformancePackage.CLOSED_WORKLOAD__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PerformancePackage.CLOSED_WORKLOAD__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case PerformancePackage.CLOSED_WORKLOAD__ARRIVAL_PATTERN:
				setArrivalPattern(ARRIVAL_PATTERN_EDEFAULT);
				return;
			case PerformancePackage.CLOSED_WORKLOAD__ARRIVAL_PARAM1:
				setArrivalParam1(ARRIVAL_PARAM1_EDEFAULT);
				return;
			case PerformancePackage.CLOSED_WORKLOAD__ARRIVAL_PARAM2:
				setArrivalParam2(ARRIVAL_PARAM2_EDEFAULT);
				return;
			case PerformancePackage.CLOSED_WORKLOAD__EXTERNAL_DELAY:
				setExternalDelay(EXTERNAL_DELAY_EDEFAULT);
				return;
			case PerformancePackage.CLOSED_WORKLOAD__VALUE:
				setValue(VALUE_EDEFAULT);
				return;
			case PerformancePackage.CLOSED_WORKLOAD__COEFF_VAR_SEQ:
				setCoeffVarSeq(COEFF_VAR_SEQ_EDEFAULT);
				return;
			case PerformancePackage.CLOSED_WORKLOAD__RESP_TIME:
				getRespTime().clear();
				return;
			case PerformancePackage.CLOSED_WORKLOAD__POPULATION:
				setPopulation(POPULATION_EDEFAULT);
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
			case PerformancePackage.CLOSED_WORKLOAD__URN_LINKS:
				return urnLinks != null && !urnLinks.isEmpty();
			case PerformancePackage.CLOSED_WORKLOAD__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case PerformancePackage.CLOSED_WORKLOAD__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PerformancePackage.CLOSED_WORKLOAD__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case PerformancePackage.CLOSED_WORKLOAD__ARRIVAL_PATTERN:
				return arrivalPattern != ARRIVAL_PATTERN_EDEFAULT;
			case PerformancePackage.CLOSED_WORKLOAD__ARRIVAL_PARAM1:
				return arrivalParam1 != ARRIVAL_PARAM1_EDEFAULT;
			case PerformancePackage.CLOSED_WORKLOAD__ARRIVAL_PARAM2:
				return arrivalParam2 != ARRIVAL_PARAM2_EDEFAULT;
			case PerformancePackage.CLOSED_WORKLOAD__EXTERNAL_DELAY:
				return externalDelay != EXTERNAL_DELAY_EDEFAULT;
			case PerformancePackage.CLOSED_WORKLOAD__VALUE:
				return value != VALUE_EDEFAULT;
			case PerformancePackage.CLOSED_WORKLOAD__COEFF_VAR_SEQ:
				return coeffVarSeq != COEFF_VAR_SEQ_EDEFAULT;
			case PerformancePackage.CLOSED_WORKLOAD__RESP_TIME:
				return respTime != null && !respTime.isEmpty();
			case PerformancePackage.CLOSED_WORKLOAD__POPULATION:
				return population != POPULATION_EDEFAULT;
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
		result.append(" (population: ");
		result.append(population);
		result.append(')');
		return result.toString();
	}

} //ClosedWorkloadImpl
