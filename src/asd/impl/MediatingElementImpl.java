/**
 */
package asd.impl;

import asd.AsdPackage;
import asd.MediatingElement;
import asd.Mediation;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mediating Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link asd.impl.MediatingElementImpl#getMediations <em>Mediations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class MediatingElementImpl extends ASDelementImpl implements MediatingElement {
	/**
	 * The cached value of the '{@link #getMediations() <em>Mediations</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMediations()
	 * @generated
	 * @ordered
	 */
	protected EList mediations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MediatingElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return AsdPackage.Literals.MEDIATING_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getMediations() {
		if (mediations == null) {
			mediations = new EObjectWithInverseResolvingEList(Mediation.class, this, AsdPackage.MEDIATING_ELEMENT__MEDIATIONS, AsdPackage.MEDIATION__MEDIATED_BY);
		}
		return mediations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AsdPackage.MEDIATING_ELEMENT__MEDIATIONS:
				return ((InternalEList)getMediations()).basicAdd(otherEnd, msgs);
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
			case AsdPackage.MEDIATING_ELEMENT__MEDIATIONS:
				return ((InternalEList)getMediations()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AsdPackage.MEDIATING_ELEMENT__MEDIATIONS:
				return getMediations();
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
			case AsdPackage.MEDIATING_ELEMENT__MEDIATIONS:
				getMediations().clear();
				getMediations().addAll((Collection)newValue);
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
			case AsdPackage.MEDIATING_ELEMENT__MEDIATIONS:
				getMediations().clear();
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
			case AsdPackage.MEDIATING_ELEMENT__MEDIATIONS:
				return mediations != null && !mediations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //MediatingElementImpl
