/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucmscenarios.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import ucmscenarios.Component;
import ucmscenarios.ScenarioGroup;
import ucmscenarios.ScenarioSpec;
import ucmscenarios.UcmscenariosPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scenario Spec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucmscenarios.impl.ScenarioSpecImpl#getFilename <em>Filename</em>}</li>
 *   <li>{@link ucmscenarios.impl.ScenarioSpecImpl#getCreated <em>Created</em>}</li>
 *   <li>{@link ucmscenarios.impl.ScenarioSpecImpl#getModified <em>Modified</em>}</li>
 *   <li>{@link ucmscenarios.impl.ScenarioSpecImpl#getSpecVersion <em>Spec Version</em>}</li>
 *   <li>{@link ucmscenarios.impl.ScenarioSpecImpl#getComponents <em>Components</em>}</li>
 *   <li>{@link ucmscenarios.impl.ScenarioSpecImpl#getGroups <em>Groups</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScenarioSpecImpl extends ModelElementImpl implements ScenarioSpec {
	/**
	 * The default value of the '{@link #getFilename() <em>Filename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilename()
	 * @generated
	 * @ordered
	 */
	protected static final String FILENAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFilename() <em>Filename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFilename()
	 * @generated
	 * @ordered
	 */
	protected String filename = FILENAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getCreated() <em>Created</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreated()
	 * @generated
	 * @ordered
	 */
	protected static final String CREATED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCreated() <em>Created</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreated()
	 * @generated
	 * @ordered
	 */
	protected String created = CREATED_EDEFAULT;

	/**
	 * The default value of the '{@link #getModified() <em>Modified</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModified()
	 * @generated
	 * @ordered
	 */
	protected static final String MODIFIED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModified() <em>Modified</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModified()
	 * @generated
	 * @ordered
	 */
	protected String modified = MODIFIED_EDEFAULT;

	/**
	 * The default value of the '{@link #getSpecVersion() <em>Spec Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String SPEC_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSpecVersion() <em>Spec Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecVersion()
	 * @generated
	 * @ordered
	 */
	protected String specVersion = SPEC_VERSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getComponents() <em>Components</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponents()
	 * @generated
	 * @ordered
	 */
	protected EList components;

	/**
	 * The cached value of the '{@link #getGroups() <em>Groups</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroups()
	 * @generated
	 * @ordered
	 */
	protected EList groups;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScenarioSpecImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return UcmscenariosPackage.Literals.SCENARIO_SPEC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFilename(String newFilename) {
		String oldFilename = filename;
		filename = newFilename;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmscenariosPackage.SCENARIO_SPEC__FILENAME, oldFilename, filename));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCreated() {
		return created;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreated(String newCreated) {
		String oldCreated = created;
		created = newCreated;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmscenariosPackage.SCENARIO_SPEC__CREATED, oldCreated, created));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getModified() {
		return modified;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModified(String newModified) {
		String oldModified = modified;
		modified = newModified;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmscenariosPackage.SCENARIO_SPEC__MODIFIED, oldModified, modified));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSpecVersion() {
		return specVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpecVersion(String newSpecVersion) {
		String oldSpecVersion = specVersion;
		specVersion = newSpecVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmscenariosPackage.SCENARIO_SPEC__SPEC_VERSION, oldSpecVersion, specVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getComponents() {
		if (components == null) {
			components = new EObjectContainmentWithInverseEList(Component.class, this, UcmscenariosPackage.SCENARIO_SPEC__COMPONENTS, UcmscenariosPackage.COMPONENT__SCENARIO_SPEC);
		}
		return components;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getGroups() {
		if (groups == null) {
			groups = new EObjectContainmentWithInverseEList(ScenarioGroup.class, this, UcmscenariosPackage.SCENARIO_SPEC__GROUPS, UcmscenariosPackage.SCENARIO_GROUP__SCENARIO_SPEC);
		}
		return groups;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UcmscenariosPackage.SCENARIO_SPEC__COMPONENTS:
				return ((InternalEList)getComponents()).basicAdd(otherEnd, msgs);
			case UcmscenariosPackage.SCENARIO_SPEC__GROUPS:
				return ((InternalEList)getGroups()).basicAdd(otherEnd, msgs);
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
			case UcmscenariosPackage.SCENARIO_SPEC__COMPONENTS:
				return ((InternalEList)getComponents()).basicRemove(otherEnd, msgs);
			case UcmscenariosPackage.SCENARIO_SPEC__GROUPS:
				return ((InternalEList)getGroups()).basicRemove(otherEnd, msgs);
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
			case UcmscenariosPackage.SCENARIO_SPEC__FILENAME:
				return getFilename();
			case UcmscenariosPackage.SCENARIO_SPEC__CREATED:
				return getCreated();
			case UcmscenariosPackage.SCENARIO_SPEC__MODIFIED:
				return getModified();
			case UcmscenariosPackage.SCENARIO_SPEC__SPEC_VERSION:
				return getSpecVersion();
			case UcmscenariosPackage.SCENARIO_SPEC__COMPONENTS:
				return getComponents();
			case UcmscenariosPackage.SCENARIO_SPEC__GROUPS:
				return getGroups();
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
			case UcmscenariosPackage.SCENARIO_SPEC__FILENAME:
				setFilename((String)newValue);
				return;
			case UcmscenariosPackage.SCENARIO_SPEC__CREATED:
				setCreated((String)newValue);
				return;
			case UcmscenariosPackage.SCENARIO_SPEC__MODIFIED:
				setModified((String)newValue);
				return;
			case UcmscenariosPackage.SCENARIO_SPEC__SPEC_VERSION:
				setSpecVersion((String)newValue);
				return;
			case UcmscenariosPackage.SCENARIO_SPEC__COMPONENTS:
				getComponents().clear();
				getComponents().addAll((Collection)newValue);
				return;
			case UcmscenariosPackage.SCENARIO_SPEC__GROUPS:
				getGroups().clear();
				getGroups().addAll((Collection)newValue);
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
			case UcmscenariosPackage.SCENARIO_SPEC__FILENAME:
				setFilename(FILENAME_EDEFAULT);
				return;
			case UcmscenariosPackage.SCENARIO_SPEC__CREATED:
				setCreated(CREATED_EDEFAULT);
				return;
			case UcmscenariosPackage.SCENARIO_SPEC__MODIFIED:
				setModified(MODIFIED_EDEFAULT);
				return;
			case UcmscenariosPackage.SCENARIO_SPEC__SPEC_VERSION:
				setSpecVersion(SPEC_VERSION_EDEFAULT);
				return;
			case UcmscenariosPackage.SCENARIO_SPEC__COMPONENTS:
				getComponents().clear();
				return;
			case UcmscenariosPackage.SCENARIO_SPEC__GROUPS:
				getGroups().clear();
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
			case UcmscenariosPackage.SCENARIO_SPEC__FILENAME:
				return FILENAME_EDEFAULT == null ? filename != null : !FILENAME_EDEFAULT.equals(filename);
			case UcmscenariosPackage.SCENARIO_SPEC__CREATED:
				return CREATED_EDEFAULT == null ? created != null : !CREATED_EDEFAULT.equals(created);
			case UcmscenariosPackage.SCENARIO_SPEC__MODIFIED:
				return MODIFIED_EDEFAULT == null ? modified != null : !MODIFIED_EDEFAULT.equals(modified);
			case UcmscenariosPackage.SCENARIO_SPEC__SPEC_VERSION:
				return SPEC_VERSION_EDEFAULT == null ? specVersion != null : !SPEC_VERSION_EDEFAULT.equals(specVersion);
			case UcmscenariosPackage.SCENARIO_SPEC__COMPONENTS:
				return components != null && !components.isEmpty();
			case UcmscenariosPackage.SCENARIO_SPEC__GROUPS:
				return groups != null && !groups.isEmpty();
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
		result.append(" (filename: ");
		result.append(filename);
		result.append(", created: ");
		result.append(created);
		result.append(", modified: ");
		result.append(modified);
		result.append(", specVersion: ");
		result.append(specVersion);
		result.append(')');
		return result.toString();
	}

} //ScenarioSpecImpl