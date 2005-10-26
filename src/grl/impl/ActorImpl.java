/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Actor;
import grl.GRLspec;
import grl.GrlPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import urncore.SpecificationComponent;
import urncore.SpecificationComponentRef;
import urncore.UrncorePackage;

import urncore.impl.GRLmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Actor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.ActorImpl#getLineColor <em>Line Color</em>}</li>
 *   <li>{@link grl.impl.ActorImpl#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link grl.impl.ActorImpl#isFilled <em>Filled</em>}</li>
 *   <li>{@link grl.impl.ActorImpl#getCompRefs <em>Comp Refs</em>}</li>
 *   <li>{@link grl.impl.ActorImpl#getGrlspec <em>Grlspec</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActorImpl extends GRLmodelElementImpl implements Actor {
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
     * The cached value of the '{@link #getCompRefs() <em>Comp Refs</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCompRefs()
     * @generated
     * @ordered
     */
    protected EList compRefs = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ActorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return GrlPackage.eINSTANCE.getActor();
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
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR__LINE_COLOR, oldLineColor, lineColor));
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
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR__FILL_COLOR, oldFillColor, fillColor));
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
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR__FILLED, oldFilled, filled));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getCompRefs() {
        if (compRefs == null) {
            compRefs = new EObjectWithInverseResolvingEList(SpecificationComponentRef.class, this, GrlPackage.ACTOR__COMP_REFS, UrncorePackage.SPECIFICATION_COMPONENT_REF__COMP_DEF);
        }
        return compRefs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GRLspec getGrlspec() {
        if (eContainerFeatureID != GrlPackage.ACTOR__GRLSPEC) return null;
        return (GRLspec)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGrlspec(GRLspec newGrlspec) {
        if (newGrlspec != eContainer || (eContainerFeatureID != GrlPackage.ACTOR__GRLSPEC && newGrlspec != null)) {
            if (EcoreUtil.isAncestor(this, newGrlspec))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newGrlspec != null)
                msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__ACTORS, GRLspec.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newGrlspec, GrlPackage.ACTOR__GRLSPEC, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR__GRLSPEC, newGrlspec, newGrlspec));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.ACTOR__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.ACTOR__COMP_REFS:
                    return ((InternalEList)getCompRefs()).basicAdd(otherEnd, msgs);
                case GrlPackage.ACTOR__GRLSPEC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, GrlPackage.ACTOR__GRLSPEC, msgs);
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
                case GrlPackage.ACTOR__URN_LINKS:
                    return ((InternalEList)getUrnLinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.ACTOR__COMP_REFS:
                    return ((InternalEList)getCompRefs()).basicRemove(otherEnd, msgs);
                case GrlPackage.ACTOR__GRLSPEC:
                    return eBasicSetContainer(null, GrlPackage.ACTOR__GRLSPEC, msgs);
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
                case GrlPackage.ACTOR__GRLSPEC:
                    return ((InternalEObject)eContainer).eInverseRemove(this, GrlPackage.GR_LSPEC__ACTORS, GRLspec.class, msgs);
                default:
                    return eDynamicBasicRemoveFromContainer(msgs);
            }
        }
        return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case GrlPackage.ACTOR__ID:
                return getId();
            case GrlPackage.ACTOR__NAME:
                return getName();
            case GrlPackage.ACTOR__DESCRIPTION:
                return getDescription();
            case GrlPackage.ACTOR__URN_LINKS:
                return getUrnLinks();
            case GrlPackage.ACTOR__LINE_COLOR:
                return getLineColor();
            case GrlPackage.ACTOR__FILL_COLOR:
                return getFillColor();
            case GrlPackage.ACTOR__FILLED:
                return isFilled() ? Boolean.TRUE : Boolean.FALSE;
            case GrlPackage.ACTOR__COMP_REFS:
                return getCompRefs();
            case GrlPackage.ACTOR__GRLSPEC:
                return getGrlspec();
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
            case GrlPackage.ACTOR__ID:
                setId((String)newValue);
                return;
            case GrlPackage.ACTOR__NAME:
                setName((String)newValue);
                return;
            case GrlPackage.ACTOR__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case GrlPackage.ACTOR__URN_LINKS:
                getUrnLinks().clear();
                getUrnLinks().addAll((Collection)newValue);
                return;
            case GrlPackage.ACTOR__LINE_COLOR:
                setLineColor((String)newValue);
                return;
            case GrlPackage.ACTOR__FILL_COLOR:
                setFillColor((String)newValue);
                return;
            case GrlPackage.ACTOR__FILLED:
                setFilled(((Boolean)newValue).booleanValue());
                return;
            case GrlPackage.ACTOR__COMP_REFS:
                getCompRefs().clear();
                getCompRefs().addAll((Collection)newValue);
                return;
            case GrlPackage.ACTOR__GRLSPEC:
                setGrlspec((GRLspec)newValue);
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
            case GrlPackage.ACTOR__ID:
                setId(ID_EDEFAULT);
                return;
            case GrlPackage.ACTOR__NAME:
                setName(NAME_EDEFAULT);
                return;
            case GrlPackage.ACTOR__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case GrlPackage.ACTOR__URN_LINKS:
                getUrnLinks().clear();
                return;
            case GrlPackage.ACTOR__LINE_COLOR:
                setLineColor(LINE_COLOR_EDEFAULT);
                return;
            case GrlPackage.ACTOR__FILL_COLOR:
                setFillColor(FILL_COLOR_EDEFAULT);
                return;
            case GrlPackage.ACTOR__FILLED:
                setFilled(FILLED_EDEFAULT);
                return;
            case GrlPackage.ACTOR__COMP_REFS:
                getCompRefs().clear();
                return;
            case GrlPackage.ACTOR__GRLSPEC:
                setGrlspec((GRLspec)null);
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
            case GrlPackage.ACTOR__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case GrlPackage.ACTOR__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case GrlPackage.ACTOR__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case GrlPackage.ACTOR__URN_LINKS:
                return urnLinks != null && !urnLinks.isEmpty();
            case GrlPackage.ACTOR__LINE_COLOR:
                return LINE_COLOR_EDEFAULT == null ? lineColor != null : !LINE_COLOR_EDEFAULT.equals(lineColor);
            case GrlPackage.ACTOR__FILL_COLOR:
                return FILL_COLOR_EDEFAULT == null ? fillColor != null : !FILL_COLOR_EDEFAULT.equals(fillColor);
            case GrlPackage.ACTOR__FILLED:
                return filled != FILLED_EDEFAULT;
            case GrlPackage.ACTOR__COMP_REFS:
                return compRefs != null && !compRefs.isEmpty();
            case GrlPackage.ACTOR__GRLSPEC:
                return getGrlspec() != null;
        }
        return eDynamicIsSet(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
        if (baseClass == SpecificationComponent.class) {
            switch (derivedFeatureID) {
                case GrlPackage.ACTOR__LINE_COLOR: return UrncorePackage.SPECIFICATION_COMPONENT__LINE_COLOR;
                case GrlPackage.ACTOR__FILL_COLOR: return UrncorePackage.SPECIFICATION_COMPONENT__FILL_COLOR;
                case GrlPackage.ACTOR__FILLED: return UrncorePackage.SPECIFICATION_COMPONENT__FILLED;
                case GrlPackage.ACTOR__COMP_REFS: return UrncorePackage.SPECIFICATION_COMPONENT__COMP_REFS;
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
        if (baseClass == SpecificationComponent.class) {
            switch (baseFeatureID) {
                case UrncorePackage.SPECIFICATION_COMPONENT__LINE_COLOR: return GrlPackage.ACTOR__LINE_COLOR;
                case UrncorePackage.SPECIFICATION_COMPONENT__FILL_COLOR: return GrlPackage.ACTOR__FILL_COLOR;
                case UrncorePackage.SPECIFICATION_COMPONENT__FILLED: return GrlPackage.ACTOR__FILLED;
                case UrncorePackage.SPECIFICATION_COMPONENT__COMP_REFS: return GrlPackage.ACTOR__COMP_REFS;
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

} //ActorImpl
