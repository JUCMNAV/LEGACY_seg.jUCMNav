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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import ucm.performance.Demand;
import ucm.performance.GeneralResource;
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
public class DemandImpl extends EObjectImpl implements Demand {
    /**
     * The default value of the '{@link #getQuantity() <em>Quantity</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getQuantity()
     * @generated
     * @ordered
     */
	protected static final double QUANTITY_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getQuantity() <em>Quantity</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getQuantity()
     * @generated
     * @ordered
     */
	protected double quantity = QUANTITY_EDEFAULT;

    /**
     * The cached value of the '{@link #getResource() <em>Resource</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getResource()
     * @generated
     * @ordered
     */
	protected GeneralResource resource = null;

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
        return PerformancePackage.eINSTANCE.getDemand();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public double getQuantity() {
        return quantity;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setQuantity(double newQuantity) {
        double oldQuantity = quantity;
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
        if (eContainerFeatureID != PerformancePackage.DEMAND__RESPONSIBILITY) return null;
        return (Responsibility)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setResponsibility(Responsibility newResponsibility) {
        if (newResponsibility != eContainer || (eContainerFeatureID != PerformancePackage.DEMAND__RESPONSIBILITY && newResponsibility != null)) {
            if (EcoreUtil.isAncestor(this, newResponsibility))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newResponsibility != null)
                msgs = ((InternalEObject)newResponsibility).eInverseAdd(this, UrncorePackage.RESPONSIBILITY__DEMANDS, Responsibility.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newResponsibility, PerformancePackage.DEMAND__RESPONSIBILITY, msgs);
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
	public GeneralResource getResource() {
        if (resource != null && resource.eIsProxy()) {
            GeneralResource oldResource = resource;
            resource = (GeneralResource)eResolveProxy((InternalEObject)resource);
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
	public GeneralResource basicGetResource() {
        return resource;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetResource(GeneralResource newResource, NotificationChain msgs) {
        GeneralResource oldResource = resource;
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
	public void setResource(GeneralResource newResource) {
        if (newResource != resource) {
            NotificationChain msgs = null;
            if (resource != null)
                msgs = ((InternalEObject)resource).eInverseRemove(this, PerformancePackage.GENERAL_RESOURCE__DEMANDS, GeneralResource.class, msgs);
            if (newResource != null)
                msgs = ((InternalEObject)newResource).eInverseAdd(this, PerformancePackage.GENERAL_RESOURCE__DEMANDS, GeneralResource.class, msgs);
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case PerformancePackage.DEMAND__RESPONSIBILITY:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, PerformancePackage.DEMAND__RESPONSIBILITY, msgs);
                case PerformancePackage.DEMAND__RESOURCE:
                    if (resource != null)
                        msgs = ((InternalEObject)resource).eInverseRemove(this, PerformancePackage.GENERAL_RESOURCE__DEMANDS, GeneralResource.class, msgs);
                    return basicSetResource((GeneralResource)otherEnd, msgs);
                default:
                    return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
            }
        }
        if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
        return eBasicSetContainer(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case PerformancePackage.DEMAND__RESPONSIBILITY:
                    return eBasicSetContainer(null, PerformancePackage.DEMAND__RESPONSIBILITY, msgs);
                case PerformancePackage.DEMAND__RESOURCE:
                    return basicSetResource(null, msgs);
                default:
                    return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
            }
        }
        return eBasicSetContainer(null, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
        if (eContainerFeatureID >= 0) {
            switch (eContainerFeatureID) {
                case PerformancePackage.DEMAND__RESPONSIBILITY:
                    return eContainer.eInverseRemove(this, UrncorePackage.RESPONSIBILITY__DEMANDS, Responsibility.class, msgs);
                default:
                    return eDynamicBasicRemoveFromContainer(msgs);
            }
        }
        return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case PerformancePackage.DEMAND__QUANTITY:
                return new Double(getQuantity());
            case PerformancePackage.DEMAND__RESPONSIBILITY:
                return getResponsibility();
            case PerformancePackage.DEMAND__RESOURCE:
                if (resolve) return getResource();
                return basicGetResource();
        }
        return eDynamicGet(eFeature, resolve);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case PerformancePackage.DEMAND__QUANTITY:
                setQuantity(((Double)newValue).doubleValue());
                return;
            case PerformancePackage.DEMAND__RESPONSIBILITY:
                setResponsibility((Responsibility)newValue);
                return;
            case PerformancePackage.DEMAND__RESOURCE:
                setResource((GeneralResource)newValue);
                return;
        }
        eDynamicSet(eFeature, newValue);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void eUnset(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case PerformancePackage.DEMAND__QUANTITY:
                setQuantity(QUANTITY_EDEFAULT);
                return;
            case PerformancePackage.DEMAND__RESPONSIBILITY:
                setResponsibility((Responsibility)null);
                return;
            case PerformancePackage.DEMAND__RESOURCE:
                setResource((GeneralResource)null);
                return;
        }
        eDynamicUnset(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean eIsSet(EStructuralFeature eFeature) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case PerformancePackage.DEMAND__QUANTITY:
                return quantity != QUANTITY_EDEFAULT;
            case PerformancePackage.DEMAND__RESPONSIBILITY:
                return getResponsibility() != null;
            case PerformancePackage.DEMAND__RESOURCE:
                return resource != null;
        }
        return eDynamicIsSet(eFeature);
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
