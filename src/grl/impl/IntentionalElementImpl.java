/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.DecompositionType;
import grl.ElementLink;
import grl.Evaluation;
import grl.GRLspec;
import grl.GrlPackage;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.IntentionalElementType;

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

import urncore.impl.GRLmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Intentional Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.IntentionalElementImpl#getType <em>Type</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getDecompositionType <em>Decomposition Type</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getLineColor <em>Line Color</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#isFilled <em>Filled</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getGrlspec <em>Grlspec</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getRefs <em>Refs</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getEvals <em>Evals</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getLinksSrc <em>Links Src</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getLinksDest <em>Links Dest</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IntentionalElementImpl extends GRLmodelElementImpl implements IntentionalElement {
    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected static final IntentionalElementType TYPE_EDEFAULT = IntentionalElementType.SOFTGOAL_LITERAL;

    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
    protected IntentionalElementType type = TYPE_EDEFAULT;

    /**
     * The default value of the '{@link #getDecompositionType() <em>Decomposition Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDecompositionType()
     * @generated
     * @ordered
     */
    protected static final DecompositionType DECOMPOSITION_TYPE_EDEFAULT = DecompositionType.AND_LITERAL;

    /**
     * The cached value of the '{@link #getDecompositionType() <em>Decomposition Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDecompositionType()
     * @generated
     * @ordered
     */
    protected DecompositionType decompositionType = DECOMPOSITION_TYPE_EDEFAULT;

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
     * The cached value of the '{@link #getRefs() <em>Refs</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRefs()
     * @generated
     * @ordered
     */
    protected EList refs = null;

    /**
     * The cached value of the '{@link #getEvals() <em>Evals</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getEvals()
     * @generated
     * @ordered
     */
    protected EList evals = null;

    /**
     * The cached value of the '{@link #getLinksSrc() <em>Links Src</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLinksSrc()
     * @generated
     * @ordered
     */
    protected EList linksSrc = null;

    /**
     * The cached value of the '{@link #getLinksDest() <em>Links Dest</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLinksDest()
     * @generated
     * @ordered
     */
    protected EList linksDest = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IntentionalElementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return GrlPackage.eINSTANCE.getIntentionalElement();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IntentionalElementType getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setType(IntentionalElementType newType) {
        IntentionalElementType oldType = type;
        type = newType == null ? TYPE_EDEFAULT : newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT__TYPE, oldType, type));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public DecompositionType getDecompositionType() {
        return decompositionType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDecompositionType(DecompositionType newDecompositionType) {
        DecompositionType oldDecompositionType = decompositionType;
        decompositionType = newDecompositionType == null ? DECOMPOSITION_TYPE_EDEFAULT : newDecompositionType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE, oldDecompositionType, decompositionType));
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
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT__LINE_COLOR, oldLineColor, lineColor));
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
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT__FILL_COLOR, oldFillColor, fillColor));
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
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT__FILLED, oldFilled, filled));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public GRLspec getGrlspec() {
        if (eContainerFeatureID != GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC) return null;
        return (GRLspec)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setGrlspec(GRLspec newGrlspec) {
        if (newGrlspec != eContainer || (eContainerFeatureID != GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC && newGrlspec != null)) {
            if (EcoreUtil.isAncestor(this, newGrlspec))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newGrlspec != null)
                msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__INT_ELEMENTS, GRLspec.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newGrlspec, GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC, newGrlspec, newGrlspec));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getRefs() {
        if (refs == null) {
            refs = new EObjectWithInverseResolvingEList(IntentionalElementRef.class, this, GrlPackage.INTENTIONAL_ELEMENT__REFS, GrlPackage.INTENTIONAL_ELEMENT_REF__DEF);
        }
        return refs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getEvals() {
        if (evals == null) {
            evals = new EObjectWithInverseResolvingEList(Evaluation.class, this, GrlPackage.INTENTIONAL_ELEMENT__EVALS, GrlPackage.EVALUATION__INT_ELEMENT);
        }
        return evals;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getLinksSrc() {
        if (linksSrc == null) {
            linksSrc = new EObjectWithInverseResolvingEList(ElementLink.class, this, GrlPackage.INTENTIONAL_ELEMENT__LINKS_SRC, GrlPackage.ELEMENT_LINK__SRC);
        }
        return linksSrc;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getLinksDest() {
        if (linksDest == null) {
            linksDest = new EObjectWithInverseResolvingEList(ElementLink.class, this, GrlPackage.INTENTIONAL_ELEMENT__LINKS_DEST, GrlPackage.ELEMENT_LINK__DEST);
        }
        return linksDest;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.INTENTIONAL_ELEMENT__URNLINKS:
                    return ((InternalEList)getUrnlinks()).basicAdd(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__REFS:
                    return ((InternalEList)getRefs()).basicAdd(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__EVALS:
                    return ((InternalEList)getEvals()).basicAdd(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__LINKS_SRC:
                    return ((InternalEList)getLinksSrc()).basicAdd(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__LINKS_DEST:
                    return ((InternalEList)getLinksDest()).basicAdd(otherEnd, msgs);
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
                case GrlPackage.INTENTIONAL_ELEMENT__URNLINKS:
                    return ((InternalEList)getUrnlinks()).basicRemove(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC:
                    return eBasicSetContainer(null, GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__REFS:
                    return ((InternalEList)getRefs()).basicRemove(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__EVALS:
                    return ((InternalEList)getEvals()).basicRemove(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__LINKS_SRC:
                    return ((InternalEList)getLinksSrc()).basicRemove(otherEnd, msgs);
                case GrlPackage.INTENTIONAL_ELEMENT__LINKS_DEST:
                    return ((InternalEList)getLinksDest()).basicRemove(otherEnd, msgs);
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
                case GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC:
                    return eContainer.eInverseRemove(this, GrlPackage.GR_LSPEC__INT_ELEMENTS, GRLspec.class, msgs);
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
            case GrlPackage.INTENTIONAL_ELEMENT__ID:
                return getId();
            case GrlPackage.INTENTIONAL_ELEMENT__NAME:
                return getName();
            case GrlPackage.INTENTIONAL_ELEMENT__DESCRIPTION:
                return getDescription();
            case GrlPackage.INTENTIONAL_ELEMENT__URNLINKS:
                return getUrnlinks();
            case GrlPackage.INTENTIONAL_ELEMENT__TYPE:
                return getType();
            case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE:
                return getDecompositionType();
            case GrlPackage.INTENTIONAL_ELEMENT__LINE_COLOR:
                return getLineColor();
            case GrlPackage.INTENTIONAL_ELEMENT__FILL_COLOR:
                return getFillColor();
            case GrlPackage.INTENTIONAL_ELEMENT__FILLED:
                return isFilled() ? Boolean.TRUE : Boolean.FALSE;
            case GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC:
                return getGrlspec();
            case GrlPackage.INTENTIONAL_ELEMENT__REFS:
                return getRefs();
            case GrlPackage.INTENTIONAL_ELEMENT__EVALS:
                return getEvals();
            case GrlPackage.INTENTIONAL_ELEMENT__LINKS_SRC:
                return getLinksSrc();
            case GrlPackage.INTENTIONAL_ELEMENT__LINKS_DEST:
                return getLinksDest();
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
            case GrlPackage.INTENTIONAL_ELEMENT__ID:
                setId((String)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__NAME:
                setName((String)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__DESCRIPTION:
                setDescription((String)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__URNLINKS:
                getUrnlinks().clear();
                getUrnlinks().addAll((Collection)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__TYPE:
                setType((IntentionalElementType)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE:
                setDecompositionType((DecompositionType)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__LINE_COLOR:
                setLineColor((String)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__FILL_COLOR:
                setFillColor((String)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__FILLED:
                setFilled(((Boolean)newValue).booleanValue());
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC:
                setGrlspec((GRLspec)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__REFS:
                getRefs().clear();
                getRefs().addAll((Collection)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__EVALS:
                getEvals().clear();
                getEvals().addAll((Collection)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__LINKS_SRC:
                getLinksSrc().clear();
                getLinksSrc().addAll((Collection)newValue);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__LINKS_DEST:
                getLinksDest().clear();
                getLinksDest().addAll((Collection)newValue);
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
            case GrlPackage.INTENTIONAL_ELEMENT__ID:
                setId(ID_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__NAME:
                setName(NAME_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__DESCRIPTION:
                setDescription(DESCRIPTION_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__URNLINKS:
                getUrnlinks().clear();
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__TYPE:
                setType(TYPE_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE:
                setDecompositionType(DECOMPOSITION_TYPE_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__LINE_COLOR:
                setLineColor(LINE_COLOR_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__FILL_COLOR:
                setFillColor(FILL_COLOR_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__FILLED:
                setFilled(FILLED_EDEFAULT);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC:
                setGrlspec((GRLspec)null);
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__REFS:
                getRefs().clear();
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__EVALS:
                getEvals().clear();
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__LINKS_SRC:
                getLinksSrc().clear();
                return;
            case GrlPackage.INTENTIONAL_ELEMENT__LINKS_DEST:
                getLinksDest().clear();
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
            case GrlPackage.INTENTIONAL_ELEMENT__ID:
                return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
            case GrlPackage.INTENTIONAL_ELEMENT__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case GrlPackage.INTENTIONAL_ELEMENT__DESCRIPTION:
                return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
            case GrlPackage.INTENTIONAL_ELEMENT__URNLINKS:
                return urnlinks != null && !urnlinks.isEmpty();
            case GrlPackage.INTENTIONAL_ELEMENT__TYPE:
                return type != TYPE_EDEFAULT;
            case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE:
                return decompositionType != DECOMPOSITION_TYPE_EDEFAULT;
            case GrlPackage.INTENTIONAL_ELEMENT__LINE_COLOR:
                return LINE_COLOR_EDEFAULT == null ? lineColor != null : !LINE_COLOR_EDEFAULT.equals(lineColor);
            case GrlPackage.INTENTIONAL_ELEMENT__FILL_COLOR:
                return FILL_COLOR_EDEFAULT == null ? fillColor != null : !FILL_COLOR_EDEFAULT.equals(fillColor);
            case GrlPackage.INTENTIONAL_ELEMENT__FILLED:
                return filled != FILLED_EDEFAULT;
            case GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC:
                return getGrlspec() != null;
            case GrlPackage.INTENTIONAL_ELEMENT__REFS:
                return refs != null && !refs.isEmpty();
            case GrlPackage.INTENTIONAL_ELEMENT__EVALS:
                return evals != null && !evals.isEmpty();
            case GrlPackage.INTENTIONAL_ELEMENT__LINKS_SRC:
                return linksSrc != null && !linksSrc.isEmpty();
            case GrlPackage.INTENTIONAL_ELEMENT__LINKS_DEST:
                return linksDest != null && !linksDest.isEmpty();
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
        result.append(" (type: ");
        result.append(type);
        result.append(", decompositionType: ");
        result.append(decompositionType);
        result.append(", lineColor: ");
        result.append(lineColor);
        result.append(", fillColor: ");
        result.append(fillColor);
        result.append(", filled: ");
        result.append(filled);
        result.append(')');
        return result.toString();
    }

} //IntentionalElementImpl
