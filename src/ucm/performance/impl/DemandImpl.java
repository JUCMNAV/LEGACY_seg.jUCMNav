/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.performance.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import ucm.performance.Demand;
import ucm.performance.ExternalOperation;
import ucm.performance.PerformancePackage;
import urncore.Responsibility;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Demand</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.performance.impl.DemandImpl#getQuantity <em>Quantity</em>}</li>
 *   <li>{@link ucm.performance.impl.DemandImpl#getResponsibility <em>Responsibility</em>}</li>
 *   <li>{@link ucm.performance.impl.DemandImpl#getResource <em>Resource</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DemandImpl extends MinimalEObjectImpl.Container implements Demand {
    /**
	 * The default value of the '{@link #getQuantity() <em>Quantity</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getQuantity()
	 * @generated
	 * @ordered
	 */
    protected static final String QUANTITY_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getQuantity() <em>Quantity</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getQuantity()
	 * @generated
	 * @ordered
	 */
    protected String quantity = QUANTITY_EDEFAULT;

    /**
	 * The cached value of the '{@link #getResource() <em>Resource</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getResource()
	 * @generated
	 * @ordered
	 */
    protected ExternalOperation resource;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected DemandImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return PerformancePackage.Literals.DEMAND;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getQuantity() {
		return quantity;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQuantity(String newQuantity) {
		String oldQuantity = quantity;
		quantity = newQuantity;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.DEMAND__QUANTITY, oldQuantity, quantity));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Responsibility getResponsibility() {
		if (eContainerFeatureID() != PerformancePackage.DEMAND__RESPONSIBILITY) return null;
		return (Responsibility)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResponsibility(Responsibility newResponsibility, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newResponsibility, PerformancePackage.DEMAND__RESPONSIBILITY, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setResponsibility(Responsibility newResponsibility) {
		if (newResponsibility != eInternalContainer() || (eContainerFeatureID() != PerformancePackage.DEMAND__RESPONSIBILITY && newResponsibility != null)) {
			if (EcoreUtil.isAncestor(this, newResponsibility))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newResponsibility != null)
				msgs = ((InternalEObject)newResponsibility).eInverseAdd(this, UrncorePackage.RESPONSIBILITY__DEMANDS, Responsibility.class, msgs);
			msgs = basicSetResponsibility(newResponsibility, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.DEMAND__RESPONSIBILITY, newResponsibility, newResponsibility));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ExternalOperation getResource() {
		if (resource != null && resource.eIsProxy()) {
			InternalEObject oldResource = (InternalEObject)resource;
			resource = (ExternalOperation)eResolveProxy(oldResource);
			if (resource != oldResource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PerformancePackage.DEMAND__RESOURCE, oldResource, resource));
			}
		}
		return resource;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ExternalOperation basicGetResource() {
		return resource;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResource(ExternalOperation newResource, NotificationChain msgs) {
		ExternalOperation oldResource = resource;
		resource = newResource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PerformancePackage.DEMAND__RESOURCE, oldResource, newResource);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResource(ExternalOperation newResource) {
		if (newResource != resource) {
			NotificationChain msgs = null;
			if (resource != null)
				msgs = ((InternalEObject)resource).eInverseRemove(this, PerformancePackage.EXTERNAL_OPERATION__DEMANDS, ExternalOperation.class, msgs);
			if (newResource != null)
				msgs = ((InternalEObject)newResource).eInverseAdd(this, PerformancePackage.EXTERNAL_OPERATION__DEMANDS, ExternalOperation.class, msgs);
			msgs = basicSetResource(newResource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PerformancePackage.DEMAND__RESOURCE, newResource, newResource));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PerformancePackage.DEMAND__RESPONSIBILITY:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetResponsibility((Responsibility)otherEnd, msgs);
			case PerformancePackage.DEMAND__RESOURCE:
				if (resource != null)
					msgs = ((InternalEObject)resource).eInverseRemove(this, PerformancePackage.EXTERNAL_OPERATION__DEMANDS, ExternalOperation.class, msgs);
				return basicSetResource((ExternalOperation)otherEnd, msgs);
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
			case PerformancePackage.DEMAND__RESPONSIBILITY:
				return basicSetResponsibility(null, msgs);
			case PerformancePackage.DEMAND__RESOURCE:
				return basicSetResource(null, msgs);
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
			case PerformancePackage.DEMAND__RESPONSIBILITY:
				return eInternalContainer().eInverseRemove(this, UrncorePackage.RESPONSIBILITY__DEMANDS, Responsibility.class, msgs);
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
			case PerformancePackage.DEMAND__QUANTITY:
				return getQuantity();
			case PerformancePackage.DEMAND__RESPONSIBILITY:
				return getResponsibility();
			case PerformancePackage.DEMAND__RESOURCE:
				if (resolve) return getResource();
				return basicGetResource();
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
			case PerformancePackage.DEMAND__QUANTITY:
				setQuantity((String)newValue);
				return;
			case PerformancePackage.DEMAND__RESPONSIBILITY:
				setResponsibility((Responsibility)newValue);
				return;
			case PerformancePackage.DEMAND__RESOURCE:
				setResource((ExternalOperation)newValue);
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
			case PerformancePackage.DEMAND__QUANTITY:
				setQuantity(QUANTITY_EDEFAULT);
				return;
			case PerformancePackage.DEMAND__RESPONSIBILITY:
				setResponsibility((Responsibility)null);
				return;
			case PerformancePackage.DEMAND__RESOURCE:
				setResource((ExternalOperation)null);
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
			case PerformancePackage.DEMAND__QUANTITY:
				return QUANTITY_EDEFAULT == null ? quantity != null : !QUANTITY_EDEFAULT.equals(quantity);
			case PerformancePackage.DEMAND__RESPONSIBILITY:
				return getResponsibility() != null;
			case PerformancePackage.DEMAND__RESOURCE:
				return resource != null;
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
		result.append(" (quantity: ");
		result.append(quantity);
		result.append(')');
		return result.toString();
	}

} //DemandImpl
