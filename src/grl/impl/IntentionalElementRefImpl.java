/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Criticality;
import grl.GrlPackage;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

import grl.Priority;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.NodeLabel;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Intentional Element Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.IntentionalElementRefImpl#getCriticality <em>Criticality</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementRefImpl#getPriority <em>Priority</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementRefImpl#getDef <em>Def</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IntentionalElementRefImpl extends GRLNodeImpl implements IntentionalElementRef {
    /**
     * The default value of the '{@link #getCriticality() <em>Criticality</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCriticality()
     * @generated
     * @ordered
     */
    protected static final Criticality CRITICALITY_EDEFAULT = Criticality.NONE_LITERAL;

    /**
     * The cached value of the '{@link #getCriticality() <em>Criticality</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getCriticality()
     * @generated
     * @ordered
     */
    protected Criticality criticality = CRITICALITY_EDEFAULT;

    /**
     * The default value of the '{@link #getPriority() <em>Priority</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPriority()
     * @generated
     * @ordered
     */
    protected static final Priority PRIORITY_EDEFAULT = Priority.NONE_LITERAL;

    /**
     * The cached value of the '{@link #getPriority() <em>Priority</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPriority()
     * @generated
     * @ordered
     */
    protected Priority priority = PRIORITY_EDEFAULT;

    /**
     * The cached value of the '{@link #getDef() <em>Def</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDef()
     * @generated
     * @ordered
     */
    protected IntentionalElement def = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IntentionalElementRefImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return GrlPackage.eINSTANCE.getIntentionalElementRef();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Criticality getCriticality() {
        return criticality;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setCriticality(Criticality newCriticality) {
        Criticality oldCriticality = criticality;
        criticality = newCriticality == null ? CRITICALITY_EDEFAULT : newCriticality;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT_REF__CRITICALITY, oldCriticality, criticality));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setPriority(Priority newPriority) {
        Priority oldPriority = priority;
        priority = newPriority == null ? PRIORITY_EDEFAULT : newPriority;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT_REF__PRIORITY, oldPriority, priority));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElement getDef() {
        if (def != null && def.eIsProxy()) {
            IntentionalElement oldDef = def;
            def = (IntentionalElement)eResolveProxy((InternalEObject)def);
            if (def != oldDef) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.INTENTIONAL_ELEMENT_REF__DEF, oldDef, def));
            }
        }
        return def;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElement basicGetDef() {
        return def;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDef(IntentionalElement newDef, NotificationChain msgs) {
        IntentionalElement oldDef = def;
        def = newDef;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT_REF__DEF, oldDef, newDef);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDef(IntentionalElement newDef) {
        if (newDef != def) {
            NotificationChain msgs = null;
            if (def != null)
                msgs = ((InternalEObject)def).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__REFS, IntentionalElement.class, msgs);
            if (newDef != null)
                msgs = ((InternalEObject)newDef).eInverseAdd(this, GrlPackage.INTENTIONAL_ELEMENT__REFS, IntentionalElement.class, msgs);
            msgs = basicSetDef(newDef, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT_REF__DEF, newDef, newDef));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.INTENTIONAL_ELEMENT_REF__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__DIAGRAM:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, GrlPackage.INTENTIONAL_ELEMENT_REF__DIAGRAM, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__CONT_REF:
                    if (contRef != null)
                        msgs = ((InternalEObject)contRef).eInverseRemove(this, UrncorePackage.IURN_CONTAINER_REF__NODES, IURNContainerRef.class, msgs);
                    return basicSetContRef((IURNContainerRef)otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__SUCC:
                    return ((InternalEList)getSucc()).basicAdd(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__PRED:
                    return ((InternalEList)getPred()).basicAdd(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__LABEL:
                    if (label != null)
                        msgs = ((InternalEObject)label).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GrlPackage.INTENTIONAL_ELEMENT_REF__LABEL, null, msgs);
                    return basicSetLabel((NodeLabel)otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__DEF:
                    if (def != null)
                        msgs = ((InternalEObject)def).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__REFS, IntentionalElement.class, msgs);
                    return basicSetDef((IntentionalElement)otherEnd, msgs);
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
                case GrlPackage.INTENTIONAL_ELEMENT_REF__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__DIAGRAM:
                    return eBasicSetContainer(null, GrlPackage.INTENTIONAL_ELEMENT_REF__DIAGRAM, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__CONT_REF:
                    return basicSetContRef(null, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__SUCC:
                    return ((InternalEList)getSucc()).basicRemove(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__PRED:
                    return ((InternalEList)getPred()).basicRemove(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__LABEL:
                    return basicSetLabel(null, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT_REF__DEF:
                    return basicSetDef(null, msgs);
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
                case GrlPackage.INTENTIONAL_ELEMENT_REF__DIAGRAM:
                    return eContainer.eInverseRemove(this, UrncorePackage.IURN_DIAGRAM__NODES, IURNDiagram.class, msgs);
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
            case GrlPackage.INTENTIONAL_ELEMENT_REF__FROM_LINKS:
                return getFromLinks();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__TO_LINKS:
                return getToLinks();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__ID:
                return getId();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__NAME:
                return getName();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__DESCRIPTION:
                return getDescription();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__X:
                return new Integer(getX());
            case GrlPackage.INTENTIONAL_ELEMENT_REF__Y:
                return new Integer(getY());
            case GrlPackage.INTENTIONAL_ELEMENT_REF__DIAGRAM:
                return getDiagram();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__CONT_REF:
                if (resolve) return getContRef();
                return basicGetContRef();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__SUCC:
                return getSucc();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__PRED:
                return getPred();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__LABEL:
                return getLabel();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__CRITICALITY:
                return getCriticality();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__PRIORITY:
                return getPriority();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__DEF:
                if (resolve) return getDef();
                return basicGetDef();
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
            case GrlPackage.INTENTIONAL_ELEMENT_REF__FROM_LINKS:
                getFromLinks().clear();
                getFromLinks().addAll((Collection)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__TO_LINKS:
                getToLinks().clear();
                getToLinks().addAll((Collection)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__ID:
                setId((String)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__NAME:
                setName((String)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__X:
                setX(((Integer)newValue).intValue());
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__Y:
                setY(((Integer)newValue).intValue());
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__DIAGRAM:
                setDiagram((IURNDiagram)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__CONT_REF:
                setContRef((IURNContainerRef)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__SUCC:
                getSucc().clear();
                getSucc().addAll((Collection)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__PRED:
                getPred().clear();
                getPred().addAll((Collection)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__LABEL:
                setLabel((NodeLabel)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__CRITICALITY:
                setCriticality((Criticality)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__PRIORITY:
                setPriority((Priority)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__DEF:
                setDef((IntentionalElement)newValue);
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
            case GrlPackage.INTENTIONAL_ELEMENT_REF__FROM_LINKS:
                getFromLinks().clear();
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__TO_LINKS:
                getToLinks().clear();
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__ID:
                setId(ID_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__NAME:
                setName(NAME_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__X:
                setX(X_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__Y:
                setY(Y_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__DIAGRAM:
                setDiagram((IURNDiagram)null);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__CONT_REF:
                setContRef((IURNContainerRef)null);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__SUCC:
                getSucc().clear();
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__PRED:
                getPred().clear();
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__LABEL:
                setLabel((NodeLabel)null);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__CRITICALITY:
                setCriticality(CRITICALITY_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__PRIORITY:
                setPriority(PRIORITY_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__DEF:
                setDef((IntentionalElement)null);
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
            case GrlPackage.INTENTIONAL_ELEMENT_REF__FROM_LINKS:
                return fromLinks != null && !fromLinks.isEmpty();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__TO_LINKS:
                return toLinks != null && !toLinks.isEmpty();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case GrlPackage.INTENTIONAL_ELEMENT_REF__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case GrlPackage.INTENTIONAL_ELEMENT_REF__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case GrlPackage.INTENTIONAL_ELEMENT_REF__X:
                return x != X_EDEFAULT;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__Y:
                return y != Y_EDEFAULT;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__DIAGRAM:
                return getDiagram() != null;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__CONT_REF:
                return contRef != null;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__SUCC:
                return succ != null && !succ.isEmpty();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__PRED:
                return pred != null && !pred.isEmpty();
            case GrlPackage.INTENTIONAL_ELEMENT_REF__LABEL:
                return label != null;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__CRITICALITY:
                return criticality != CRITICALITY_EDEFAULT;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__PRIORITY:
                return priority != PRIORITY_EDEFAULT;
            case GrlPackage.INTENTIONAL_ELEMENT_REF__DEF:
                return def != null;
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
        result.append(" (criticality: ");
        result.append(criticality);
        result.append(", priority: ");
        result.append(priority);
        result.append(')');
        return result.toString();
    }

} //IntentionalElementRefImpl
