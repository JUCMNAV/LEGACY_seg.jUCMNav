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
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.etsi.mts.tdl.CompoundBehaviour;
import org.etsi.mts.tdl.DataProxy;
import org.etsi.mts.tdl.TdlPackage;
import org.etsi.mts.tdl.TestConfiguration;
import org.etsi.mts.tdl.TestDescription;
import org.etsi.mts.tdl.TestObjective;
import org.etsi.mts.tdl.TestObjectiveRealizer;
import org.etsi.mts.tdl.TimeConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Description</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.impl.TestDescriptionImpl#getTestObjectives <em>Test Objective</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.impl.TestDescriptionImpl#getTestConfiguration <em>Test Configuration</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.impl.TestDescriptionImpl#getBehaviour <em>Behaviour</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.impl.TestDescriptionImpl#getTimeConstraints <em>Time Constraint</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.impl.TestDescriptionImpl#getFormalParameters <em>Formal Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestDescriptionImpl extends PackageableElementImpl implements TestDescription {
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
	 * The cached value of the '{@link #getTestConfiguration() <em>Test Configuration</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestConfiguration()
	 * @generated
	 * @ordered
	 */
	protected TestConfiguration testConfiguration;

	/**
	 * The cached value of the '{@link #getBehaviour() <em>Behaviour</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBehaviour()
	 * @generated
	 * @ordered
	 */
	protected CompoundBehaviour behaviour;

	/**
	 * The cached value of the '{@link #getTimeConstraints() <em>Time Constraint</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<TimeConstraint> timeConstraints;

	/**
	 * The cached value of the '{@link #getFormalParameters() <em>Formal Parameter</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormalParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<DataProxy> formalParameters;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestDescriptionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TdlPackage.Literals.TEST_DESCRIPTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestObjective> getTestObjectives() {
		if (testObjectives == null) {
			testObjectives = new EObjectResolvingEList<TestObjective>(TestObjective.class, this, TdlPackage.TEST_DESCRIPTION__TEST_OBJECTIVE);
		}
		return testObjectives;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestConfiguration getTestConfiguration() {
		if (testConfiguration != null && testConfiguration.eIsProxy()) {
			InternalEObject oldTestConfiguration = (InternalEObject)testConfiguration;
			testConfiguration = (TestConfiguration)eResolveProxy(oldTestConfiguration);
			if (testConfiguration != oldTestConfiguration) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TdlPackage.TEST_DESCRIPTION__TEST_CONFIGURATION, oldTestConfiguration, testConfiguration));
			}
		}
		return testConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestConfiguration basicGetTestConfiguration() {
		return testConfiguration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTestConfiguration(TestConfiguration newTestConfiguration) {
		TestConfiguration oldTestConfiguration = testConfiguration;
		testConfiguration = newTestConfiguration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TdlPackage.TEST_DESCRIPTION__TEST_CONFIGURATION, oldTestConfiguration, testConfiguration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompoundBehaviour getBehaviour() {
		return behaviour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBehaviour(CompoundBehaviour newBehaviour, NotificationChain msgs) {
		CompoundBehaviour oldBehaviour = behaviour;
		behaviour = newBehaviour;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TdlPackage.TEST_DESCRIPTION__BEHAVIOUR, oldBehaviour, newBehaviour);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBehaviour(CompoundBehaviour newBehaviour) {
		if (newBehaviour != behaviour) {
			NotificationChain msgs = null;
			if (behaviour != null)
				msgs = ((InternalEObject)behaviour).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TdlPackage.TEST_DESCRIPTION__BEHAVIOUR, null, msgs);
			if (newBehaviour != null)
				msgs = ((InternalEObject)newBehaviour).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TdlPackage.TEST_DESCRIPTION__BEHAVIOUR, null, msgs);
			msgs = basicSetBehaviour(newBehaviour, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TdlPackage.TEST_DESCRIPTION__BEHAVIOUR, newBehaviour, newBehaviour));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompoundBehaviour createBehaviour() {
		CompoundBehaviour newBehaviour = (CompoundBehaviour) create(TdlPackage.Literals.COMPOUND_BEHAVIOUR);
		setBehaviour(newBehaviour);
		return newBehaviour;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TimeConstraint> getTimeConstraints() {
		if (timeConstraints == null) {
			timeConstraints = new EObjectContainmentEList<TimeConstraint>(TimeConstraint.class, this, TdlPackage.TEST_DESCRIPTION__TIME_CONSTRAINT);
		}
		return timeConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeConstraint createTimeConstraint() {
		TimeConstraint newTimeConstraint = (TimeConstraint) create(TdlPackage.Literals.TIME_CONSTRAINT);
		getTimeConstraints().add(newTimeConstraint);
		return newTimeConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DataProxy> getFormalParameters() {
		if (formalParameters == null) {
			formalParameters = new EObjectContainmentEList<DataProxy>(DataProxy.class, this, TdlPackage.TEST_DESCRIPTION__FORMAL_PARAMETER);
		}
		return formalParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataProxy createFormalParameter() {
		DataProxy newFormalParameter = (DataProxy) create(TdlPackage.Literals.DATA_PROXY);
		getFormalParameters().add(newFormalParameter);
		return newFormalParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TdlPackage.TEST_DESCRIPTION__BEHAVIOUR:
				return basicSetBehaviour(null, msgs);
			case TdlPackage.TEST_DESCRIPTION__TIME_CONSTRAINT:
				return ((InternalEList<?>)getTimeConstraints()).basicRemove(otherEnd, msgs);
			case TdlPackage.TEST_DESCRIPTION__FORMAL_PARAMETER:
				return ((InternalEList<?>)getFormalParameters()).basicRemove(otherEnd, msgs);
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
			case TdlPackage.TEST_DESCRIPTION__TEST_OBJECTIVE:
				return getTestObjectives();
			case TdlPackage.TEST_DESCRIPTION__TEST_CONFIGURATION:
				if (resolve) return getTestConfiguration();
				return basicGetTestConfiguration();
			case TdlPackage.TEST_DESCRIPTION__BEHAVIOUR:
				return getBehaviour();
			case TdlPackage.TEST_DESCRIPTION__TIME_CONSTRAINT:
				return getTimeConstraints();
			case TdlPackage.TEST_DESCRIPTION__FORMAL_PARAMETER:
				return getFormalParameters();
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
			case TdlPackage.TEST_DESCRIPTION__TEST_OBJECTIVE:
				getTestObjectives().clear();
				getTestObjectives().addAll((Collection<? extends TestObjective>)newValue);
				return;
			case TdlPackage.TEST_DESCRIPTION__TEST_CONFIGURATION:
				setTestConfiguration((TestConfiguration)newValue);
				return;
			case TdlPackage.TEST_DESCRIPTION__BEHAVIOUR:
				setBehaviour((CompoundBehaviour)newValue);
				return;
			case TdlPackage.TEST_DESCRIPTION__TIME_CONSTRAINT:
				getTimeConstraints().clear();
				getTimeConstraints().addAll((Collection<? extends TimeConstraint>)newValue);
				return;
			case TdlPackage.TEST_DESCRIPTION__FORMAL_PARAMETER:
				getFormalParameters().clear();
				getFormalParameters().addAll((Collection<? extends DataProxy>)newValue);
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
			case TdlPackage.TEST_DESCRIPTION__TEST_OBJECTIVE:
				getTestObjectives().clear();
				return;
			case TdlPackage.TEST_DESCRIPTION__TEST_CONFIGURATION:
				setTestConfiguration((TestConfiguration)null);
				return;
			case TdlPackage.TEST_DESCRIPTION__BEHAVIOUR:
				setBehaviour((CompoundBehaviour)null);
				return;
			case TdlPackage.TEST_DESCRIPTION__TIME_CONSTRAINT:
				getTimeConstraints().clear();
				return;
			case TdlPackage.TEST_DESCRIPTION__FORMAL_PARAMETER:
				getFormalParameters().clear();
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
			case TdlPackage.TEST_DESCRIPTION__TEST_OBJECTIVE:
				return testObjectives != null && !testObjectives.isEmpty();
			case TdlPackage.TEST_DESCRIPTION__TEST_CONFIGURATION:
				return testConfiguration != null;
			case TdlPackage.TEST_DESCRIPTION__BEHAVIOUR:
				return behaviour != null;
			case TdlPackage.TEST_DESCRIPTION__TIME_CONSTRAINT:
				return timeConstraints != null && !timeConstraints.isEmpty();
			case TdlPackage.TEST_DESCRIPTION__FORMAL_PARAMETER:
				return formalParameters != null && !formalParameters.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == TestObjectiveRealizer.class) {
			switch (derivedFeatureID) {
				case TdlPackage.TEST_DESCRIPTION__TEST_OBJECTIVE: return TdlPackage.TEST_OBJECTIVE_REALIZER__TEST_OBJECTIVE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == TestObjectiveRealizer.class) {
			switch (baseFeatureID) {
				case TdlPackage.TEST_OBJECTIVE_REALIZER__TEST_OBJECTIVE: return TdlPackage.TEST_DESCRIPTION__TEST_OBJECTIVE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //TestDescriptionImpl
