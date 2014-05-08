/**
 */
package asd.impl;

import asd.ASDelement;
import asd.ASDspec;
import asd.AsdPackage;
import asd.Outcome;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Outcome</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link asd.impl.OutcomeImpl#getEnabledElements <em>Enabled Elements</em>}</li>
 *   <li>{@link asd.impl.OutcomeImpl#getAsdSpec <em>Asd Spec</em>}</li>
 *   <li>{@link asd.impl.OutcomeImpl#getObjects <em>Objects</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OutcomeImpl extends ASDelementImpl implements Outcome {
	/**
	 * The cached value of the '{@link #getEnabledElements() <em>Enabled Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnabledElements()
	 * @generated
	 * @ordered
	 */
	protected EList enabledElements;

	/**
	 * The cached value of the '{@link #getObjects() <em>Objects</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObjects()
	 * @generated
	 * @ordered
	 */
	protected EList objects;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OutcomeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return AsdPackage.Literals.OUTCOME;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getEnabledElements() {
		if (enabledElements == null) {
			enabledElements = new EObjectWithInverseResolvingEList.ManyInverse(ASDelement.class, this, AsdPackage.OUTCOME__ENABLED_ELEMENTS, AsdPackage.AS_DELEMENT__REQUIRED_OUTCOMES);
		}
		return enabledElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASDspec getAsdSpec() {
		if (eContainerFeatureID() != AsdPackage.OUTCOME__ASD_SPEC) return null;
		return (ASDspec)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAsdSpec(ASDspec newAsdSpec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newAsdSpec, AsdPackage.OUTCOME__ASD_SPEC, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAsdSpec(ASDspec newAsdSpec) {
		if (newAsdSpec != eInternalContainer() || (eContainerFeatureID() != AsdPackage.OUTCOME__ASD_SPEC && newAsdSpec != null)) {
			if (EcoreUtil.isAncestor(this, newAsdSpec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newAsdSpec != null)
				msgs = ((InternalEObject)newAsdSpec).eInverseAdd(this, AsdPackage.AS_DSPEC__OUTCOMES, ASDspec.class, msgs);
			msgs = basicSetAsdSpec(newAsdSpec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsdPackage.OUTCOME__ASD_SPEC, newAsdSpec, newAsdSpec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getObjects() {
		if (objects == null) {
			objects = new EObjectWithInverseResolvingEList.ManyInverse(asd.Object.class, this, AsdPackage.OUTCOME__OBJECTS, AsdPackage.OBJECT__OUTCOMES);
		}
		return objects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AsdPackage.OUTCOME__ENABLED_ELEMENTS:
				return ((InternalEList)getEnabledElements()).basicAdd(otherEnd, msgs);
			case AsdPackage.OUTCOME__ASD_SPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetAsdSpec((ASDspec)otherEnd, msgs);
			case AsdPackage.OUTCOME__OBJECTS:
				return ((InternalEList)getObjects()).basicAdd(otherEnd, msgs);
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
			case AsdPackage.OUTCOME__ENABLED_ELEMENTS:
				return ((InternalEList)getEnabledElements()).basicRemove(otherEnd, msgs);
			case AsdPackage.OUTCOME__ASD_SPEC:
				return basicSetAsdSpec(null, msgs);
			case AsdPackage.OUTCOME__OBJECTS:
				return ((InternalEList)getObjects()).basicRemove(otherEnd, msgs);
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
			case AsdPackage.OUTCOME__ASD_SPEC:
				return eInternalContainer().eInverseRemove(this, AsdPackage.AS_DSPEC__OUTCOMES, ASDspec.class, msgs);
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
			case AsdPackage.OUTCOME__ENABLED_ELEMENTS:
				return getEnabledElements();
			case AsdPackage.OUTCOME__ASD_SPEC:
				return getAsdSpec();
			case AsdPackage.OUTCOME__OBJECTS:
				return getObjects();
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
			case AsdPackage.OUTCOME__ENABLED_ELEMENTS:
				getEnabledElements().clear();
				getEnabledElements().addAll((Collection)newValue);
				return;
			case AsdPackage.OUTCOME__ASD_SPEC:
				setAsdSpec((ASDspec)newValue);
				return;
			case AsdPackage.OUTCOME__OBJECTS:
				getObjects().clear();
				getObjects().addAll((Collection)newValue);
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
			case AsdPackage.OUTCOME__ENABLED_ELEMENTS:
				getEnabledElements().clear();
				return;
			case AsdPackage.OUTCOME__ASD_SPEC:
				setAsdSpec((ASDspec)null);
				return;
			case AsdPackage.OUTCOME__OBJECTS:
				getObjects().clear();
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
			case AsdPackage.OUTCOME__ENABLED_ELEMENTS:
				return enabledElements != null && !enabledElements.isEmpty();
			case AsdPackage.OUTCOME__ASD_SPEC:
				return getAsdSpec() != null;
			case AsdPackage.OUTCOME__OBJECTS:
				return objects != null && !objects.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //OutcomeImpl
