/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import ucm.UCMspec;
import ucm.UcmPackage;
import ucm.performance.GeneralResource;
import ucm.performance.PerformancePackage;
import ucm.scenario.EnumerationType;
import ucm.scenario.ScenarioGroup;
import ucm.scenario.ScenarioPackage;
import ucm.scenario.Variable;
import urn.URNspec;
import urn.UrnPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>UC Mspec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.impl.UCMspecImpl#getUrnspec <em>Urnspec</em>}</li>
 *   <li>{@link ucm.impl.UCMspecImpl#getResources <em>Resources</em>}</li>
 *   <li>{@link ucm.impl.UCMspecImpl#getScenarioGroups <em>Scenario Groups</em>}</li>
 *   <li>{@link ucm.impl.UCMspecImpl#getVariables <em>Variables</em>}</li>
 *   <li>{@link ucm.impl.UCMspecImpl#getEnumerationTypes <em>Enumeration Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UCMspecImpl extends MinimalEObjectImpl.Container implements UCMspec {
    /**
	 * The cached value of the '{@link #getResources() <em>Resources</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getResources()
	 * @generated
	 * @ordered
	 */
    protected EList resources;

    /**
	 * The cached value of the '{@link #getScenarioGroups() <em>Scenario Groups</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getScenarioGroups()
	 * @generated
	 * @ordered
	 */
    protected EList scenarioGroups;

    /**
	 * The cached value of the '{@link #getVariables() <em>Variables</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getVariables()
	 * @generated
	 * @ordered
	 */
    protected EList variables;

    /**
	 * The cached value of the '{@link #getEnumerationTypes() <em>Enumeration Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnumerationTypes()
	 * @generated
	 * @ordered
	 */
	protected EList enumerationTypes;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected UCMspecImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return UcmPackage.Literals.UC_MSPEC;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public URNspec getUrnspec() {
		if (eContainerFeatureID() != UcmPackage.UC_MSPEC__URNSPEC) return null;
		return (URNspec)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUrnspec(URNspec newUrnspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newUrnspec, UcmPackage.UC_MSPEC__URNSPEC, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUrnspec(URNspec newUrnspec) {
		if (newUrnspec != eInternalContainer() || (eContainerFeatureID() != UcmPackage.UC_MSPEC__URNSPEC && newUrnspec != null)) {
			if (EcoreUtil.isAncestor(this, newUrnspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUrnspec != null)
				msgs = ((InternalEObject)newUrnspec).eInverseAdd(this, UrnPackage.UR_NSPEC__UCMSPEC, URNspec.class, msgs);
			msgs = basicSetUrnspec(newUrnspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UcmPackage.UC_MSPEC__URNSPEC, newUrnspec, newUrnspec));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getResources() {
		if (resources == null) {
			resources = new EObjectContainmentWithInverseEList(GeneralResource.class, this, UcmPackage.UC_MSPEC__RESOURCES, PerformancePackage.GENERAL_RESOURCE__UCMSPEC);
		}
		return resources;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getScenarioGroups() {
		if (scenarioGroups == null) {
			scenarioGroups = new EObjectContainmentWithInverseEList(ScenarioGroup.class, this, UcmPackage.UC_MSPEC__SCENARIO_GROUPS, ScenarioPackage.SCENARIO_GROUP__UCMSPEC);
		}
		return scenarioGroups;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getVariables() {
		if (variables == null) {
			variables = new EObjectContainmentWithInverseEList(Variable.class, this, UcmPackage.UC_MSPEC__VARIABLES, ScenarioPackage.VARIABLE__UCMSPEC);
		}
		return variables;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEnumerationTypes() {
		if (enumerationTypes == null) {
			enumerationTypes = new EObjectContainmentWithInverseEList(EnumerationType.class, this, UcmPackage.UC_MSPEC__ENUMERATION_TYPES, ScenarioPackage.ENUMERATION_TYPE__UCMSPEC);
		}
		return enumerationTypes;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UcmPackage.UC_MSPEC__URNSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetUrnspec((URNspec)otherEnd, msgs);
			case UcmPackage.UC_MSPEC__RESOURCES:
				return ((InternalEList)getResources()).basicAdd(otherEnd, msgs);
			case UcmPackage.UC_MSPEC__SCENARIO_GROUPS:
				return ((InternalEList)getScenarioGroups()).basicAdd(otherEnd, msgs);
			case UcmPackage.UC_MSPEC__VARIABLES:
				return ((InternalEList)getVariables()).basicAdd(otherEnd, msgs);
			case UcmPackage.UC_MSPEC__ENUMERATION_TYPES:
				return ((InternalEList)getEnumerationTypes()).basicAdd(otherEnd, msgs);
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
			case UcmPackage.UC_MSPEC__URNSPEC:
				return basicSetUrnspec(null, msgs);
			case UcmPackage.UC_MSPEC__RESOURCES:
				return ((InternalEList)getResources()).basicRemove(otherEnd, msgs);
			case UcmPackage.UC_MSPEC__SCENARIO_GROUPS:
				return ((InternalEList)getScenarioGroups()).basicRemove(otherEnd, msgs);
			case UcmPackage.UC_MSPEC__VARIABLES:
				return ((InternalEList)getVariables()).basicRemove(otherEnd, msgs);
			case UcmPackage.UC_MSPEC__ENUMERATION_TYPES:
				return ((InternalEList)getEnumerationTypes()).basicRemove(otherEnd, msgs);
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
			case UcmPackage.UC_MSPEC__URNSPEC:
				return eInternalContainer().eInverseRemove(this, UrnPackage.UR_NSPEC__UCMSPEC, URNspec.class, msgs);
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
			case UcmPackage.UC_MSPEC__URNSPEC:
				return getUrnspec();
			case UcmPackage.UC_MSPEC__RESOURCES:
				return getResources();
			case UcmPackage.UC_MSPEC__SCENARIO_GROUPS:
				return getScenarioGroups();
			case UcmPackage.UC_MSPEC__VARIABLES:
				return getVariables();
			case UcmPackage.UC_MSPEC__ENUMERATION_TYPES:
				return getEnumerationTypes();
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
			case UcmPackage.UC_MSPEC__URNSPEC:
				setUrnspec((URNspec)newValue);
				return;
			case UcmPackage.UC_MSPEC__RESOURCES:
				getResources().clear();
				getResources().addAll((Collection)newValue);
				return;
			case UcmPackage.UC_MSPEC__SCENARIO_GROUPS:
				getScenarioGroups().clear();
				getScenarioGroups().addAll((Collection)newValue);
				return;
			case UcmPackage.UC_MSPEC__VARIABLES:
				getVariables().clear();
				getVariables().addAll((Collection)newValue);
				return;
			case UcmPackage.UC_MSPEC__ENUMERATION_TYPES:
				getEnumerationTypes().clear();
				getEnumerationTypes().addAll((Collection)newValue);
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
			case UcmPackage.UC_MSPEC__URNSPEC:
				setUrnspec((URNspec)null);
				return;
			case UcmPackage.UC_MSPEC__RESOURCES:
				getResources().clear();
				return;
			case UcmPackage.UC_MSPEC__SCENARIO_GROUPS:
				getScenarioGroups().clear();
				return;
			case UcmPackage.UC_MSPEC__VARIABLES:
				getVariables().clear();
				return;
			case UcmPackage.UC_MSPEC__ENUMERATION_TYPES:
				getEnumerationTypes().clear();
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
			case UcmPackage.UC_MSPEC__URNSPEC:
				return getUrnspec() != null;
			case UcmPackage.UC_MSPEC__RESOURCES:
				return resources != null && !resources.isEmpty();
			case UcmPackage.UC_MSPEC__SCENARIO_GROUPS:
				return scenarioGroups != null && !scenarioGroups.isEmpty();
			case UcmPackage.UC_MSPEC__VARIABLES:
				return variables != null && !variables.isEmpty();
			case UcmPackage.UC_MSPEC__ENUMERATION_TYPES:
				return enumerationTypes != null && !enumerationTypes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //UCMspecImpl
