/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import urncore.ComponentLabel;
import urncore.IURNContainerRef;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Label</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urncore.impl.ComponentLabelImpl#getContRef <em>Cont Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ComponentLabelImpl extends LabelImpl implements ComponentLabel {
    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ComponentLabelImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return UrncorePackage.Literals.COMPONENT_LABEL;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public IURNContainerRef getContRef() {
		if (eContainerFeatureID() != UrncorePackage.COMPONENT_LABEL__CONT_REF) return null;
		return (IURNContainerRef)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContRef(IURNContainerRef newContRef, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newContRef, UrncorePackage.COMPONENT_LABEL__CONT_REF, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setContRef(IURNContainerRef newContRef) {
		if (newContRef != eInternalContainer() || (eContainerFeatureID() != UrncorePackage.COMPONENT_LABEL__CONT_REF && newContRef != null)) {
			if (EcoreUtil.isAncestor(this, newContRef))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newContRef != null)
				msgs = ((InternalEObject)newContRef).eInverseAdd(this, UrncorePackage.IURN_CONTAINER_REF__LABEL, IURNContainerRef.class, msgs);
			msgs = basicSetContRef(newContRef, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT_LABEL__CONT_REF, newContRef, newContRef));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UrncorePackage.COMPONENT_LABEL__CONT_REF:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetContRef((IURNContainerRef)otherEnd, msgs);
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
			case UrncorePackage.COMPONENT_LABEL__CONT_REF:
				return basicSetContRef(null, msgs);
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
			case UrncorePackage.COMPONENT_LABEL__CONT_REF:
				return eInternalContainer().eInverseRemove(this, UrncorePackage.IURN_CONTAINER_REF__LABEL, IURNContainerRef.class, msgs);
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
			case UrncorePackage.COMPONENT_LABEL__CONT_REF:
				return getContRef();
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
			case UrncorePackage.COMPONENT_LABEL__CONT_REF:
				setContRef((IURNContainerRef)newValue);
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
			case UrncorePackage.COMPONENT_LABEL__CONT_REF:
				setContRef((IURNContainerRef)null);
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
			case UrncorePackage.COMPONENT_LABEL__CONT_REF:
				return getContRef() != null;
		}
		return super.eIsSet(featureID);
	}

} //ComponentLabelImpl
