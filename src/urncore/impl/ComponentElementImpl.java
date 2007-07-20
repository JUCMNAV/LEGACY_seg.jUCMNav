/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.impl;

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

import ucm.performance.PassiveResource;
import ucm.performance.PerformancePackage;
import urncore.ComponentElement;
import urncore.ComponentRegular;
import urncore.IURNContainer;
import urncore.IURNContainerRef;
import urncore.URNdefinition;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urncore.impl.ComponentElementImpl#getLineColor <em>Line Color</em>}</li>
 *   <li>{@link urncore.impl.ComponentElementImpl#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link urncore.impl.ComponentElementImpl#isFilled <em>Filled</em>}</li>
 *   <li>{@link urncore.impl.ComponentElementImpl#getContRefs <em>Cont Refs</em>}</li>
 *   <li>{@link urncore.impl.ComponentElementImpl#getUrndefinition <em>Urndefinition</em>}</li>
 *   <li>{@link urncore.impl.ComponentElementImpl#getIncludingComponent <em>Including Component</em>}</li>
 *   <li>{@link urncore.impl.ComponentElementImpl#getResource <em>Resource</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ComponentElementImpl extends UCMmodelElementImpl implements ComponentElement {
    /**
     * The default value of the '{@link #getLineColor() <em>Line Color</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLineColor()
     * @generated
     * @ordered
     */
    protected static final String LINE_COLOR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLineColor() <em>Line Color</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLineColor()
     * @generated
     * @ordered
     */
    protected String lineColor = LINE_COLOR_EDEFAULT;

    /**
     * The default value of the '{@link #getFillColor() <em>Fill Color</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFillColor()
     * @generated
     * @ordered
     */
    protected static final String FILL_COLOR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFillColor() <em>Fill Color</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFillColor()
     * @generated
     * @ordered
     */
    protected String fillColor = FILL_COLOR_EDEFAULT;

    /**
     * The default value of the '{@link #isFilled() <em>Filled</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isFilled()
     * @generated
     * @ordered
     */
    protected static final boolean FILLED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isFilled() <em>Filled</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isFilled()
     * @generated
     * @ordered
     */
    protected boolean filled = FILLED_EDEFAULT;

    /**
     * The cached value of the '{@link #getContRefs() <em>Cont Refs</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getContRefs()
     * @generated
     * @ordered
     */
    protected EList contRefs = null;

    /**
     * The cached value of the '{@link #getIncludingComponent() <em>Including Component</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIncludingComponent()
     * @generated
     * @ordered
     */
    protected ComponentRegular includingComponent = null;

    /**
     * The cached value of the '{@link #getResource() <em>Resource</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResource()
     * @generated
     * @ordered
     */
    protected PassiveResource resource = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ComponentElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return UrncorePackage.Literals.COMPONENT_ELEMENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLineColor() {
        return lineColor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLineColor(String newLineColor) {
        String oldLineColor = lineColor;
        lineColor = newLineColor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT_ELEMENT__LINE_COLOR, oldLineColor, lineColor));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getFillColor() {
        return fillColor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFillColor(String newFillColor) {
        String oldFillColor = fillColor;
        fillColor = newFillColor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT_ELEMENT__FILL_COLOR, oldFillColor, fillColor));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isFilled() {
        return filled;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFilled(boolean newFilled) {
        boolean oldFilled = filled;
        filled = newFilled;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT_ELEMENT__FILLED, oldFilled, filled));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getContRefs() {
        if (contRefs == null) {
            contRefs = new EObjectWithInverseResolvingEList(IURNContainerRef.class, this, UrncorePackage.COMPONENT_ELEMENT__CONT_REFS, UrncorePackage.IURN_CONTAINER_REF__CONT_DEF);
        }
        return contRefs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public URNdefinition getUrndefinition() {
        if (eContainerFeatureID != UrncorePackage.COMPONENT_ELEMENT__URNDEFINITION) return null;
        return (URNdefinition)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetUrndefinition(URNdefinition newUrndefinition, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newUrndefinition, UrncorePackage.COMPONENT_ELEMENT__URNDEFINITION, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUrndefinition(URNdefinition newUrndefinition) {
        if (newUrndefinition != eInternalContainer() || (eContainerFeatureID != UrncorePackage.COMPONENT_ELEMENT__URNDEFINITION && newUrndefinition != null)) {
            if (EcoreUtil.isAncestor(this, newUrndefinition))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUrndefinition != null)
                msgs = ((InternalEObject)newUrndefinition).eInverseAdd(this, UrncorePackage.UR_NDEFINITION__COMPONENTS, URNdefinition.class, msgs);
            msgs = basicSetUrndefinition(newUrndefinition, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT_ELEMENT__URNDEFINITION, newUrndefinition, newUrndefinition));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentRegular getIncludingComponent() {
        if (includingComponent != null && includingComponent.eIsProxy()) {
            InternalEObject oldIncludingComponent = (InternalEObject)includingComponent;
            includingComponent = (ComponentRegular)eResolveProxy(oldIncludingComponent);
            if (includingComponent != oldIncludingComponent) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, UrncorePackage.COMPONENT_ELEMENT__INCLUDING_COMPONENT, oldIncludingComponent, includingComponent));
            }
        }
        return includingComponent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentRegular basicGetIncludingComponent() {
        return includingComponent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetIncludingComponent(ComponentRegular newIncludingComponent, NotificationChain msgs) {
        ComponentRegular oldIncludingComponent = includingComponent;
        includingComponent = newIncludingComponent;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT_ELEMENT__INCLUDING_COMPONENT, oldIncludingComponent, newIncludingComponent);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setIncludingComponent(ComponentRegular newIncludingComponent) {
        if (newIncludingComponent != includingComponent) {
            NotificationChain msgs = null;
            if (includingComponent != null)
                msgs = ((InternalEObject)includingComponent).eInverseRemove(this, UrncorePackage.COMPONENT_REGULAR__INCLUDED_COMPONENT, ComponentRegular.class, msgs);
            if (newIncludingComponent != null)
                msgs = ((InternalEObject)newIncludingComponent).eInverseAdd(this, UrncorePackage.COMPONENT_REGULAR__INCLUDED_COMPONENT, ComponentRegular.class, msgs);
            msgs = basicSetIncludingComponent(newIncludingComponent, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT_ELEMENT__INCLUDING_COMPONENT, newIncludingComponent, newIncludingComponent));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PassiveResource getResource() {
        if (resource != null && resource.eIsProxy()) {
            InternalEObject oldResource = (InternalEObject)resource;
            resource = (PassiveResource)eResolveProxy(oldResource);
            if (resource != oldResource) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, UrncorePackage.COMPONENT_ELEMENT__RESOURCE, oldResource, resource));
            }
        }
        return resource;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PassiveResource basicGetResource() {
        return resource;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetResource(PassiveResource newResource, NotificationChain msgs) {
        PassiveResource oldResource = resource;
        resource = newResource;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT_ELEMENT__RESOURCE, oldResource, newResource);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setResource(PassiveResource newResource) {
        if (newResource != resource) {
            NotificationChain msgs = null;
            if (resource != null)
                msgs = ((InternalEObject)resource).eInverseRemove(this, PerformancePackage.PASSIVE_RESOURCE__COMPONENT, PassiveResource.class, msgs);
            if (newResource != null)
                msgs = ((InternalEObject)newResource).eInverseAdd(this, PerformancePackage.PASSIVE_RESOURCE__COMPONENT, PassiveResource.class, msgs);
            msgs = basicSetResource(newResource, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.COMPONENT_ELEMENT__RESOURCE, newResource, newResource));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case UrncorePackage.COMPONENT_ELEMENT__CONT_REFS:
                return ((InternalEList)getContRefs()).basicAdd(otherEnd, msgs);
            case UrncorePackage.COMPONENT_ELEMENT__URNDEFINITION:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetUrndefinition((URNdefinition)otherEnd, msgs);
            case UrncorePackage.COMPONENT_ELEMENT__INCLUDING_COMPONENT:
                if (includingComponent != null)
                    msgs = ((InternalEObject)includingComponent).eInverseRemove(this, UrncorePackage.COMPONENT_REGULAR__INCLUDED_COMPONENT, ComponentRegular.class, msgs);
                return basicSetIncludingComponent((ComponentRegular)otherEnd, msgs);
            case UrncorePackage.COMPONENT_ELEMENT__RESOURCE:
                if (resource != null)
                    msgs = ((InternalEObject)resource).eInverseRemove(this, PerformancePackage.PASSIVE_RESOURCE__COMPONENT, PassiveResource.class, msgs);
                return basicSetResource((PassiveResource)otherEnd, msgs);
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
            case UrncorePackage.COMPONENT_ELEMENT__CONT_REFS:
                return ((InternalEList)getContRefs()).basicRemove(otherEnd, msgs);
            case UrncorePackage.COMPONENT_ELEMENT__URNDEFINITION:
                return basicSetUrndefinition(null, msgs);
            case UrncorePackage.COMPONENT_ELEMENT__INCLUDING_COMPONENT:
                return basicSetIncludingComponent(null, msgs);
            case UrncorePackage.COMPONENT_ELEMENT__RESOURCE:
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
        switch (eContainerFeatureID) {
            case UrncorePackage.COMPONENT_ELEMENT__URNDEFINITION:
                return eInternalContainer().eInverseRemove(this, UrncorePackage.UR_NDEFINITION__COMPONENTS, URNdefinition.class, msgs);
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
            case UrncorePackage.COMPONENT_ELEMENT__LINE_COLOR:
                return getLineColor();
            case UrncorePackage.COMPONENT_ELEMENT__FILL_COLOR:
                return getFillColor();
            case UrncorePackage.COMPONENT_ELEMENT__FILLED:
                return isFilled() ? Boolean.TRUE : Boolean.FALSE;
            case UrncorePackage.COMPONENT_ELEMENT__CONT_REFS:
                return getContRefs();
            case UrncorePackage.COMPONENT_ELEMENT__URNDEFINITION:
                return getUrndefinition();
            case UrncorePackage.COMPONENT_ELEMENT__INCLUDING_COMPONENT:
                if (resolve) return getIncludingComponent();
                return basicGetIncludingComponent();
            case UrncorePackage.COMPONENT_ELEMENT__RESOURCE:
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
            case UrncorePackage.COMPONENT_ELEMENT__LINE_COLOR:
                setLineColor((String)newValue);
                return;
            case UrncorePackage.COMPONENT_ELEMENT__FILL_COLOR:
                setFillColor((String)newValue);
                return;
            case UrncorePackage.COMPONENT_ELEMENT__FILLED:
                setFilled(((Boolean)newValue).booleanValue());
                return;
            case UrncorePackage.COMPONENT_ELEMENT__CONT_REFS:
                getContRefs().clear();
                getContRefs().addAll((Collection)newValue);
                return;
            case UrncorePackage.COMPONENT_ELEMENT__URNDEFINITION:
                setUrndefinition((URNdefinition)newValue);
                return;
            case UrncorePackage.COMPONENT_ELEMENT__INCLUDING_COMPONENT:
                setIncludingComponent((ComponentRegular)newValue);
                return;
            case UrncorePackage.COMPONENT_ELEMENT__RESOURCE:
                setResource((PassiveResource)newValue);
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
            case UrncorePackage.COMPONENT_ELEMENT__LINE_COLOR:
                setLineColor(LINE_COLOR_EDEFAULT);
                return;
            case UrncorePackage.COMPONENT_ELEMENT__FILL_COLOR:
                setFillColor(FILL_COLOR_EDEFAULT);
                return;
            case UrncorePackage.COMPONENT_ELEMENT__FILLED:
                setFilled(FILLED_EDEFAULT);
                return;
            case UrncorePackage.COMPONENT_ELEMENT__CONT_REFS:
                getContRefs().clear();
                return;
            case UrncorePackage.COMPONENT_ELEMENT__URNDEFINITION:
                setUrndefinition((URNdefinition)null);
                return;
            case UrncorePackage.COMPONENT_ELEMENT__INCLUDING_COMPONENT:
                setIncludingComponent((ComponentRegular)null);
                return;
            case UrncorePackage.COMPONENT_ELEMENT__RESOURCE:
                setResource((PassiveResource)null);
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
            case UrncorePackage.COMPONENT_ELEMENT__LINE_COLOR:
                return LINE_COLOR_EDEFAULT == null ? lineColor != null : !LINE_COLOR_EDEFAULT.equals(lineColor);
            case UrncorePackage.COMPONENT_ELEMENT__FILL_COLOR:
                return FILL_COLOR_EDEFAULT == null ? fillColor != null : !FILL_COLOR_EDEFAULT.equals(fillColor);
            case UrncorePackage.COMPONENT_ELEMENT__FILLED:
                return filled != FILLED_EDEFAULT;
            case UrncorePackage.COMPONENT_ELEMENT__CONT_REFS:
                return contRefs != null && !contRefs.isEmpty();
            case UrncorePackage.COMPONENT_ELEMENT__URNDEFINITION:
                return getUrndefinition() != null;
            case UrncorePackage.COMPONENT_ELEMENT__INCLUDING_COMPONENT:
                return includingComponent != null;
            case UrncorePackage.COMPONENT_ELEMENT__RESOURCE:
                return resource != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
        if (baseClass == IURNContainer.class) {
            switch (derivedFeatureID) {
                case UrncorePackage.COMPONENT_ELEMENT__LINE_COLOR: return UrncorePackage.IURN_CONTAINER__LINE_COLOR;
                case UrncorePackage.COMPONENT_ELEMENT__FILL_COLOR: return UrncorePackage.IURN_CONTAINER__FILL_COLOR;
                case UrncorePackage.COMPONENT_ELEMENT__FILLED: return UrncorePackage.IURN_CONTAINER__FILLED;
                case UrncorePackage.COMPONENT_ELEMENT__CONT_REFS: return UrncorePackage.IURN_CONTAINER__CONT_REFS;
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
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
        if (baseClass == IURNContainer.class) {
            switch (baseFeatureID) {
                case UrncorePackage.IURN_CONTAINER__LINE_COLOR: return UrncorePackage.COMPONENT_ELEMENT__LINE_COLOR;
                case UrncorePackage.IURN_CONTAINER__FILL_COLOR: return UrncorePackage.COMPONENT_ELEMENT__FILL_COLOR;
                case UrncorePackage.IURN_CONTAINER__FILLED: return UrncorePackage.COMPONENT_ELEMENT__FILLED;
                case UrncorePackage.IURN_CONTAINER__CONT_REFS: return UrncorePackage.COMPONENT_ELEMENT__CONT_REFS;
                default: return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (lineColor: ");
        result.append(lineColor);
        result.append(", fillColor: ");
        result.append(fillColor);
        result.append(", filled: ");
        result.append(filled);
        result.append(')');
        return result.toString();
    }

} //ComponentElementImpl
