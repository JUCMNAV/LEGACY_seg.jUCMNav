/**
 */
package asd.impl;

import asd.ASDspec;
import asd.AsdPackage;
import asd.Tool;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tool</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link asd.impl.ToolImpl#getAsdSpec <em>Asd Spec</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ToolImpl extends MediatingElementImpl implements Tool {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ToolImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return AsdPackage.Literals.TOOL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASDspec getAsdSpec() {
		if (eContainerFeatureID() != AsdPackage.TOOL__ASD_SPEC) return null;
		return (ASDspec)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAsdSpec(ASDspec newAsdSpec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newAsdSpec, AsdPackage.TOOL__ASD_SPEC, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAsdSpec(ASDspec newAsdSpec) {
		if (newAsdSpec != eInternalContainer() || (eContainerFeatureID() != AsdPackage.TOOL__ASD_SPEC && newAsdSpec != null)) {
			if (EcoreUtil.isAncestor(this, newAsdSpec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newAsdSpec != null)
				msgs = ((InternalEObject)newAsdSpec).eInverseAdd(this, AsdPackage.AS_DSPEC__TOOLS, ASDspec.class, msgs);
			msgs = basicSetAsdSpec(newAsdSpec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsdPackage.TOOL__ASD_SPEC, newAsdSpec, newAsdSpec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AsdPackage.TOOL__ASD_SPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetAsdSpec((ASDspec)otherEnd, msgs);
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
			case AsdPackage.TOOL__ASD_SPEC:
				return basicSetAsdSpec(null, msgs);
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
			case AsdPackage.TOOL__ASD_SPEC:
				return eInternalContainer().eInverseRemove(this, AsdPackage.AS_DSPEC__TOOLS, ASDspec.class, msgs);
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
			case AsdPackage.TOOL__ASD_SPEC:
				return getAsdSpec();
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
			case AsdPackage.TOOL__ASD_SPEC:
				setAsdSpec((ASDspec)newValue);
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
			case AsdPackage.TOOL__ASD_SPEC:
				setAsdSpec((ASDspec)null);
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
			case AsdPackage.TOOL__ASD_SPEC:
				return getAsdSpec() != null;
		}
		return super.eIsSet(featureID);
	}

} //ToolImpl
