/**
 */
package org.etsi.mts.tdl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.etsi.mts.tdl.ArgumentSpecification;
import org.etsi.mts.tdl.TdlPackage;
import org.etsi.mts.tdl.TestDescription;
import org.etsi.mts.tdl.TestDescriptionReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Description Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.impl.TestDescriptionReferenceImpl#getReferencedTestDescription <em>Referenced Test Description</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.impl.TestDescriptionReferenceImpl#getActualParameters <em>Actual Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestDescriptionReferenceImpl extends AtomicBehaviourImpl implements TestDescriptionReference {
	/**
	 * The cached value of the '{@link #getReferencedTestDescription() <em>Referenced Test Description</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferencedTestDescription()
	 * @generated
	 * @ordered
	 */
	protected TestDescription referencedTestDescription;

	/**
	 * The cached value of the '{@link #getActualParameters() <em>Actual Parameter</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActualParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<ArgumentSpecification> actualParameters;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestDescriptionReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TdlPackage.Literals.TEST_DESCRIPTION_REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestDescription getReferencedTestDescription() {
		if (referencedTestDescription != null && referencedTestDescription.eIsProxy()) {
			InternalEObject oldReferencedTestDescription = (InternalEObject)referencedTestDescription;
			referencedTestDescription = (TestDescription)eResolveProxy(oldReferencedTestDescription);
			if (referencedTestDescription != oldReferencedTestDescription) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TdlPackage.TEST_DESCRIPTION_REFERENCE__REFERENCED_TEST_DESCRIPTION, oldReferencedTestDescription, referencedTestDescription));
			}
		}
		return referencedTestDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestDescription basicGetReferencedTestDescription() {
		return referencedTestDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferencedTestDescription(TestDescription newReferencedTestDescription) {
		TestDescription oldReferencedTestDescription = referencedTestDescription;
		referencedTestDescription = newReferencedTestDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TdlPackage.TEST_DESCRIPTION_REFERENCE__REFERENCED_TEST_DESCRIPTION, oldReferencedTestDescription, referencedTestDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ArgumentSpecification> getActualParameters() {
		if (actualParameters == null) {
			actualParameters = new EObjectContainmentEList<ArgumentSpecification>(ArgumentSpecification.class, this, TdlPackage.TEST_DESCRIPTION_REFERENCE__ACTUAL_PARAMETER);
		}
		return actualParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArgumentSpecification createActualParameter(EClass eClass) {
		ArgumentSpecification newActualParameter = (ArgumentSpecification) create(eClass);
		getActualParameters().add(newActualParameter);
		return newActualParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TdlPackage.TEST_DESCRIPTION_REFERENCE__ACTUAL_PARAMETER:
				return ((InternalEList<?>)getActualParameters()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TdlPackage.TEST_DESCRIPTION_REFERENCE__REFERENCED_TEST_DESCRIPTION:
				if (resolve) return getReferencedTestDescription();
				return basicGetReferencedTestDescription();
			case TdlPackage.TEST_DESCRIPTION_REFERENCE__ACTUAL_PARAMETER:
				return getActualParameters();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TdlPackage.TEST_DESCRIPTION_REFERENCE__REFERENCED_TEST_DESCRIPTION:
				setReferencedTestDescription((TestDescription)newValue);
				return;
			case TdlPackage.TEST_DESCRIPTION_REFERENCE__ACTUAL_PARAMETER:
				getActualParameters().clear();
				getActualParameters().addAll((Collection<? extends ArgumentSpecification>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TdlPackage.TEST_DESCRIPTION_REFERENCE__REFERENCED_TEST_DESCRIPTION:
				setReferencedTestDescription((TestDescription)null);
				return;
			case TdlPackage.TEST_DESCRIPTION_REFERENCE__ACTUAL_PARAMETER:
				getActualParameters().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TdlPackage.TEST_DESCRIPTION_REFERENCE__REFERENCED_TEST_DESCRIPTION:
				return referencedTestDescription != null;
			case TdlPackage.TEST_DESCRIPTION_REFERENCE__ACTUAL_PARAMETER:
				return actualParameters != null && !actualParameters.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TestDescriptionReferenceImpl
