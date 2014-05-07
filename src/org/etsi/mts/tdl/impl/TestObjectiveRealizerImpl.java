/**
 */
package org.etsi.mts.tdl.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.etsi.mts.tdl.TdlPackage;
import org.etsi.mts.tdl.TestObjective;
import org.etsi.mts.tdl.TestObjectiveRealizer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Objective Realizer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.impl.TestObjectiveRealizerImpl#getTestObjectives <em>Test Objective</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class TestObjectiveRealizerImpl extends ElementImpl implements TestObjectiveRealizer {
	/**
	 * The cached value of the '{@link #getTestObjectives() <em>Test Objective</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestObjectives()
	 * @generated
	 * @ordered
	 */
	protected EList<TestObjective> testObjectives;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestObjectiveRealizerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TdlPackage.Literals.TEST_OBJECTIVE_REALIZER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestObjective> getTestObjectives() {
		if (testObjectives == null) {
			testObjectives = new EObjectResolvingEList<TestObjective>(TestObjective.class, this, TdlPackage.TEST_OBJECTIVE_REALIZER__TEST_OBJECTIVE);
		}
		return testObjectives;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TdlPackage.TEST_OBJECTIVE_REALIZER__TEST_OBJECTIVE:
				return getTestObjectives();
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
			case TdlPackage.TEST_OBJECTIVE_REALIZER__TEST_OBJECTIVE:
				getTestObjectives().clear();
				getTestObjectives().addAll((Collection<? extends TestObjective>)newValue);
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
			case TdlPackage.TEST_OBJECTIVE_REALIZER__TEST_OBJECTIVE:
				getTestObjectives().clear();
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
			case TdlPackage.TEST_OBJECTIVE_REALIZER__TEST_OBJECTIVE:
				return testObjectives != null && !testObjectives.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TestObjectiveRealizerImpl
