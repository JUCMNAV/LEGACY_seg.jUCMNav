/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Belief;
import grl.GrlPackage;

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
 * An implementation of the model object '<em><b>Belief</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.BeliefImpl#getAuthor <em>Author</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BeliefImpl extends GRLNodeImpl implements Belief {
    /**
     * The default value of the '{@link #getAuthor() <em>Author</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAuthor()
     * @generated
     * @ordered
     */
    protected static final String AUTHOR_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getAuthor() <em>Author</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAuthor()
     * @generated
     * @ordered
     */
    protected String author = AUTHOR_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected BeliefImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return GrlPackage.eINSTANCE.getBelief();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getAuthor() {
        return author;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setAuthor(String newAuthor) {
        String oldAuthor = author;
        author = newAuthor;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.BELIEF__AUTHOR, oldAuthor, author));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.BELIEF__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.BELIEF__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.BELIEF__DIAGRAM:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, GrlPackage.BELIEF__DIAGRAM, msgs);
                case GrlPackage.BELIEF__CONT_REF:
                    if (contRef != null)
                        msgs = ((InternalEObject)contRef).eInverseRemove(this, UrncorePackage.IURN_CONTAINER_REF__NODES, IURNContainerRef.class, msgs);
                    return basicSetContRef((IURNContainerRef)otherEnd, msgs);
                case GrlPackage.BELIEF__SUCC:
                    return ((InternalEList)getSucc()).basicAdd(otherEnd, msgs);
                case GrlPackage.BELIEF__PRED:
                    return ((InternalEList)getPred()).basicAdd(otherEnd, msgs);
                case GrlPackage.BELIEF__LABEL:
                    if (label != null)
                        msgs = ((InternalEObject)label).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GrlPackage.BELIEF__LABEL, null, msgs);
                    return basicSetLabel((NodeLabel)otherEnd, msgs);
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
                case GrlPackage.BELIEF__FROM_LINKS:
                    return ((InternalEList)getFromLinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.BELIEF__TO_LINKS:
                    return ((InternalEList)getToLinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.BELIEF__DIAGRAM:
                    return eBasicSetContainer(null, GrlPackage.BELIEF__DIAGRAM, msgs);
                case GrlPackage.BELIEF__CONT_REF:
                    return basicSetContRef(null, msgs);
                case GrlPackage.BELIEF__SUCC:
                    return ((InternalEList)getSucc()).basicRemove(otherEnd, msgs);
                case GrlPackage.BELIEF__PRED:
                    return ((InternalEList)getPred()).basicRemove(otherEnd, msgs);
                case GrlPackage.BELIEF__LABEL:
                    return basicSetLabel(null, msgs);
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
                case GrlPackage.BELIEF__DIAGRAM:
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
            case GrlPackage.BELIEF__FROM_LINKS:
                return getFromLinks();
            case GrlPackage.BELIEF__TO_LINKS:
                return getToLinks();
            case GrlPackage.BELIEF__ID:
                return getId();
            case GrlPackage.BELIEF__NAME:
                return getName();
            case GrlPackage.BELIEF__DESCRIPTION:
                return getDescription();
            case GrlPackage.BELIEF__X:
                return new Integer(getX());
            case GrlPackage.BELIEF__Y:
                return new Integer(getY());
            case GrlPackage.BELIEF__DIAGRAM:
                return getDiagram();
            case GrlPackage.BELIEF__CONT_REF:
                if (resolve) return getContRef();
                return basicGetContRef();
            case GrlPackage.BELIEF__SUCC:
                return getSucc();
            case GrlPackage.BELIEF__PRED:
                return getPred();
            case GrlPackage.BELIEF__LABEL:
                return getLabel();
            case GrlPackage.BELIEF__AUTHOR:
                return getAuthor();
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
            case GrlPackage.BELIEF__FROM_LINKS:
                getFromLinks().clear();
                getFromLinks().addAll((Collection)newValue);
                return;
            case GrlPackage.BELIEF__TO_LINKS:
                getToLinks().clear();
                getToLinks().addAll((Collection)newValue);
                return;
            case GrlPackage.BELIEF__ID:
                setId((String)newValue);
                return;
            case GrlPackage.BELIEF__NAME:
                setName((String)newValue);
                return;
            case GrlPackage.BELIEF__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case GrlPackage.BELIEF__X:
                setX(((Integer)newValue).intValue());
                return;
            case GrlPackage.BELIEF__Y:
                setY(((Integer)newValue).intValue());
                return;
            case GrlPackage.BELIEF__DIAGRAM:
                setDiagram((IURNDiagram)newValue);
                return;
            case GrlPackage.BELIEF__CONT_REF:
                setContRef((IURNContainerRef)newValue);
                return;
            case GrlPackage.BELIEF__SUCC:
                getSucc().clear();
                getSucc().addAll((Collection)newValue);
                return;
            case GrlPackage.BELIEF__PRED:
                getPred().clear();
                getPred().addAll((Collection)newValue);
                return;
            case GrlPackage.BELIEF__LABEL:
                setLabel((NodeLabel)newValue);
                return;
            case GrlPackage.BELIEF__AUTHOR:
                setAuthor((String)newValue);
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
            case GrlPackage.BELIEF__FROM_LINKS:
                getFromLinks().clear();
                return;
            case GrlPackage.BELIEF__TO_LINKS:
                getToLinks().clear();
                return;
            case GrlPackage.BELIEF__ID:
                setId(ID_EDEFAULT);
                return;
            case GrlPackage.BELIEF__NAME:
                setName(NAME_EDEFAULT);
                return;
            case GrlPackage.BELIEF__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case GrlPackage.BELIEF__X:
                setX(X_EDEFAULT);
                return;
            case GrlPackage.BELIEF__Y:
                setY(Y_EDEFAULT);
                return;
            case GrlPackage.BELIEF__DIAGRAM:
                setDiagram((IURNDiagram)null);
                return;
            case GrlPackage.BELIEF__CONT_REF:
                setContRef((IURNContainerRef)null);
                return;
            case GrlPackage.BELIEF__SUCC:
                getSucc().clear();
                return;
            case GrlPackage.BELIEF__PRED:
                getPred().clear();
                return;
            case GrlPackage.BELIEF__LABEL:
                setLabel((NodeLabel)null);
                return;
            case GrlPackage.BELIEF__AUTHOR:
                setAuthor(AUTHOR_EDEFAULT);
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
            case GrlPackage.BELIEF__FROM_LINKS:
                return fromLinks != null && !fromLinks.isEmpty();
            case GrlPackage.BELIEF__TO_LINKS:
                return toLinks != null && !toLinks.isEmpty();
            case GrlPackage.BELIEF__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case GrlPackage.BELIEF__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case GrlPackage.BELIEF__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case GrlPackage.BELIEF__X:
                return x != X_EDEFAULT;
            case GrlPackage.BELIEF__Y:
                return y != Y_EDEFAULT;
            case GrlPackage.BELIEF__DIAGRAM:
                return getDiagram() != null;
            case GrlPackage.BELIEF__CONT_REF:
                return contRef != null;
            case GrlPackage.BELIEF__SUCC:
                return succ != null && !succ.isEmpty();
            case GrlPackage.BELIEF__PRED:
                return pred != null && !pred.isEmpty();
            case GrlPackage.BELIEF__LABEL:
                return label != null;
            case GrlPackage.BELIEF__AUTHOR:
                return AUTHOR_EDEFAULT == null ? author != null : !AUTHOR_EDEFAULT.equals(author);
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
        result.append(" (author: ");
        result.append(author);
        result.append(')');
        return result.toString();
    }

} //BeliefImpl
