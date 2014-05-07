/**
 */
package org.etsi.mts.tdl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

import org.etsi.mts.tdl.TdlPackage;
import org.etsi.mts.tdl.TestObjective;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Objective</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.impl.TestObjectiveImpl#getObjectiveURIs <em>Objective URI</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.impl.TestObjectiveImpl#getDescription <em>Description</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TestObjectiveImpl extends PackageableElementImpl implements TestObjective {
	/**
	 * The cached value of the '{@link #getObjectiveURIs() <em>Objective URI</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObjectiveURIs()
	 * @generated
	 * @ordered
	 */
	protected EList<String> objectiveURIs;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestObjectiveImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TdlPackage.Literals.TEST_OBJECTIVE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getObjectiveURIs() {
		if (objectiveURIs == null) {
			objectiveURIs = new EDataTypeEList<String>(String.class, this, TdlPackage.TEST_OBJECTIVE__OBJECTIVE_URI);
		}
		return objectiveURIs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TdlPackage.TEST_OBJECTIVE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TdlPackage.TEST_OBJECTIVE__OBJECTIVE_URI:
				return getObjectiveURIs();
			case TdlPackage.TEST_OBJECTIVE__DESCRIPTION:
				return getDescription();
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
			case TdlPackage.TEST_OBJECTIVE__OBJECTIVE_URI:
				getObjectiveURIs().clear();
				getObjectiveURIs().addAll((Collection<? extends String>)newValue);
				return;
			case TdlPackage.TEST_OBJECTIVE__DESCRIPTION:
				setDescription((String)newValue);
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
			case TdlPackage.TEST_OBJECTIVE__OBJECTIVE_URI:
				getObjectiveURIs().clear();
				return;
			case TdlPackage.TEST_OBJECTIVE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
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
			case TdlPackage.TEST_OBJECTIVE__OBJECTIVE_URI:
				return objectiveURIs != null && !objectiveURIs.isEmpty();
			case TdlPackage.TEST_OBJECTIVE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (objectiveURI: ");
		result.append(objectiveURIs);
		result.append(", description: ");
		result.append(description);
		result.append(')');
		return result.toString();
	}

} //TestObjectiveImpl
