/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Belief;
import grl.Dependency;
import grl.ElementLink;
import grl.GrlPackage;
import grl.LinkRef;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import urncore.SpecificationDiagram;
import urncore.SpecificationNode;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.LinkRefImpl#getSource <em>Source</em>}</li>
 *   <li>{@link grl.impl.LinkRefImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link grl.impl.LinkRefImpl#getSpecDiagram <em>Spec Diagram</em>}</li>
 *   <li>{@link grl.impl.LinkRefImpl#getBeliefs <em>Beliefs</em>}</li>
 *   <li>{@link grl.impl.LinkRefImpl#getLink <em>Link</em>}</li>
 *   <li>{@link grl.impl.LinkRefImpl#getDependency <em>Dependency</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LinkRefImpl extends EObjectImpl implements LinkRef {
    /**
     * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSource()
     * @generated
     * @ordered
     */
    protected SpecificationNode source = null;

    /**
     * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getTarget()
     * @generated
     * @ordered
     */
    protected SpecificationNode target = null;

    /**
     * The cached value of the '{@link #getBeliefs() <em>Beliefs</em>}' reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBeliefs()
     * @generated
     * @ordered
     */
    protected EList beliefs = null;

    /**
     * The cached value of the '{@link #getLink() <em>Link</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLink()
     * @generated
     * @ordered
     */
    protected ElementLink link = null;

    /**
     * The cached value of the '{@link #getDependency() <em>Dependency</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDependency()
     * @generated
     * @ordered
     */
    protected Dependency dependency = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected LinkRefImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return GrlPackage.eINSTANCE.getLinkRef();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SpecificationNode getSource() {
        if (source != null && source.eIsProxy()) {
            SpecificationNode oldSource = source;
            source = (SpecificationNode)eResolveProxy((InternalEObject)source);
            if (source != oldSource) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.LINK_REF__SOURCE, oldSource, source));
            }
        }
        return source;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SpecificationNode basicGetSource() {
        return source;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSource(SpecificationNode newSource, NotificationChain msgs) {
        SpecificationNode oldSource = source;
        source = newSource;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.LINK_REF__SOURCE, oldSource, newSource);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSource(SpecificationNode newSource) {
        if (newSource != source) {
            NotificationChain msgs = null;
            if (source != null)
                msgs = ((InternalEObject)source).eInverseRemove(this, UrncorePackage.SPECIFICATION_NODE__SUCC, SpecificationNode.class, msgs);
            if (newSource != null)
                msgs = ((InternalEObject)newSource).eInverseAdd(this, UrncorePackage.SPECIFICATION_NODE__SUCC, SpecificationNode.class, msgs);
            msgs = basicSetSource(newSource, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.LINK_REF__SOURCE, newSource, newSource));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SpecificationNode getTarget() {
        if (target != null && target.eIsProxy()) {
            SpecificationNode oldTarget = target;
            target = (SpecificationNode)eResolveProxy((InternalEObject)target);
            if (target != oldTarget) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.LINK_REF__TARGET, oldTarget, target));
            }
        }
        return target;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SpecificationNode basicGetTarget() {
        return target;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetTarget(SpecificationNode newTarget, NotificationChain msgs) {
        SpecificationNode oldTarget = target;
        target = newTarget;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.LINK_REF__TARGET, oldTarget, newTarget);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTarget(SpecificationNode newTarget) {
        if (newTarget != target) {
            NotificationChain msgs = null;
            if (target != null)
                msgs = ((InternalEObject)target).eInverseRemove(this, UrncorePackage.SPECIFICATION_NODE__PRED, SpecificationNode.class, msgs);
            if (newTarget != null)
                msgs = ((InternalEObject)newTarget).eInverseAdd(this, UrncorePackage.SPECIFICATION_NODE__PRED, SpecificationNode.class, msgs);
            msgs = basicSetTarget(newTarget, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.LINK_REF__TARGET, newTarget, newTarget));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SpecificationDiagram getSpecDiagram() {
        if (eContainerFeatureID != GrlPackage.LINK_REF__SPEC_DIAGRAM) return null;
        return (SpecificationDiagram)eContainer;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSpecDiagram(SpecificationDiagram newSpecDiagram) {
        if (newSpecDiagram != eContainer || (eContainerFeatureID != GrlPackage.LINK_REF__SPEC_DIAGRAM && newSpecDiagram != null)) {
            if (EcoreUtil.isAncestor(this, newSpecDiagram))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eContainer != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newSpecDiagram != null)
                msgs = ((InternalEObject)newSpecDiagram).eInverseAdd(this, UrncorePackage.SPECIFICATION_DIAGRAM__CONNECTIONS, SpecificationDiagram.class, msgs);
            msgs = eBasicSetContainer((InternalEObject)newSpecDiagram, GrlPackage.LINK_REF__SPEC_DIAGRAM, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.LINK_REF__SPEC_DIAGRAM, newSpecDiagram, newSpecDiagram));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getBeliefs() {
        if (beliefs == null) {
            beliefs = new EObjectWithInverseResolvingEList(Belief.class, this, GrlPackage.LINK_REF__BELIEFS, GrlPackage.BELIEF__CONNECTION);
        }
        return beliefs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ElementLink getLink() {
        if (link != null && link.eIsProxy()) {
            ElementLink oldLink = link;
            link = (ElementLink)eResolveProxy((InternalEObject)link);
            if (link != oldLink) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.LINK_REF__LINK, oldLink, link));
            }
        }
        return link;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ElementLink basicGetLink() {
        return link;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLink(ElementLink newLink, NotificationChain msgs) {
        ElementLink oldLink = link;
        link = newLink;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.LINK_REF__LINK, oldLink, newLink);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLink(ElementLink newLink) {
        if (newLink != link) {
            NotificationChain msgs = null;
            if (link != null)
                msgs = ((InternalEObject)link).eInverseRemove(this, GrlPackage.ELEMENT_LINK__REFS, ElementLink.class, msgs);
            if (newLink != null)
                msgs = ((InternalEObject)newLink).eInverseAdd(this, GrlPackage.ELEMENT_LINK__REFS, ElementLink.class, msgs);
            msgs = basicSetLink(newLink, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.LINK_REF__LINK, newLink, newLink));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Dependency getDependency() {
        if (dependency != null && dependency.eIsProxy()) {
            Dependency oldDependency = dependency;
            dependency = (Dependency)eResolveProxy((InternalEObject)dependency);
            if (dependency != oldDependency) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.LINK_REF__DEPENDENCY, oldDependency, dependency));
            }
        }
        return dependency;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Dependency basicGetDependency() {
        return dependency;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDependency(Dependency newDependency, NotificationChain msgs) {
        Dependency oldDependency = dependency;
        dependency = newDependency;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.LINK_REF__DEPENDENCY, oldDependency, newDependency);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDependency(Dependency newDependency) {
        if (newDependency != dependency) {
            NotificationChain msgs = null;
            if (dependency != null)
                msgs = ((InternalEObject)dependency).eInverseRemove(this, GrlPackage.DEPENDENCY__SECOND_REFS, Dependency.class, msgs);
            if (newDependency != null)
                msgs = ((InternalEObject)newDependency).eInverseAdd(this, GrlPackage.DEPENDENCY__SECOND_REFS, Dependency.class, msgs);
            msgs = basicSetDependency(newDependency, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.LINK_REF__DEPENDENCY, newDependency, newDependency));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
        if (featureID >= 0) {
            switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
                case GrlPackage.LINK_REF__SOURCE:
                    if (source != null)
                        msgs = ((InternalEObject)source).eInverseRemove(this, UrncorePackage.SPECIFICATION_NODE__SUCC, SpecificationNode.class, msgs);
                    return basicSetSource((SpecificationNode)otherEnd, msgs);
                case GrlPackage.LINK_REF__TARGET:
                    if (target != null)
                        msgs = ((InternalEObject)target).eInverseRemove(this, UrncorePackage.SPECIFICATION_NODE__PRED, SpecificationNode.class, msgs);
                    return basicSetTarget((SpecificationNode)otherEnd, msgs);
                case GrlPackage.LINK_REF__SPEC_DIAGRAM:
                    if (eContainer != null)
                        msgs = eBasicRemoveFromContainer(msgs);
                    return eBasicSetContainer(otherEnd, GrlPackage.LINK_REF__SPEC_DIAGRAM, msgs);
                case GrlPackage.LINK_REF__BELIEFS:
                    return ((InternalEList)getBeliefs()).basicAdd(otherEnd, msgs);
                case GrlPackage.LINK_REF__LINK:
                    if (link != null)
                        msgs = ((InternalEObject)link).eInverseRemove(this, GrlPackage.ELEMENT_LINK__REFS, ElementLink.class, msgs);
                    return basicSetLink((ElementLink)otherEnd, msgs);
                case GrlPackage.LINK_REF__DEPENDENCY:
                    if (dependency != null)
                        msgs = ((InternalEObject)dependency).eInverseRemove(this, GrlPackage.DEPENDENCY__SECOND_REFS, Dependency.class, msgs);
                    return basicSetDependency((Dependency)otherEnd, msgs);
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
                case GrlPackage.LINK_REF__SOURCE:
                    return basicSetSource(null, msgs);
                case GrlPackage.LINK_REF__TARGET:
                    return basicSetTarget(null, msgs);
                case GrlPackage.LINK_REF__SPEC_DIAGRAM:
                    return eBasicSetContainer(null, GrlPackage.LINK_REF__SPEC_DIAGRAM, msgs);
                case GrlPackage.LINK_REF__BELIEFS:
                    return ((InternalEList)getBeliefs()).basicRemove(otherEnd, msgs);
                case GrlPackage.LINK_REF__LINK:
                    return basicSetLink(null, msgs);
                case GrlPackage.LINK_REF__DEPENDENCY:
                    return basicSetDependency(null, msgs);
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
                case GrlPackage.LINK_REF__SPEC_DIAGRAM:
                    return ((InternalEObject)eContainer).eInverseRemove(this, UrncorePackage.SPECIFICATION_DIAGRAM__CONNECTIONS, SpecificationDiagram.class, msgs);
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
            case GrlPackage.LINK_REF__SOURCE:
                if (resolve) return getSource();
                return basicGetSource();
            case GrlPackage.LINK_REF__TARGET:
                if (resolve) return getTarget();
                return basicGetTarget();
            case GrlPackage.LINK_REF__SPEC_DIAGRAM:
                return getSpecDiagram();
            case GrlPackage.LINK_REF__BELIEFS:
                return getBeliefs();
            case GrlPackage.LINK_REF__LINK:
                if (resolve) return getLink();
                return basicGetLink();
            case GrlPackage.LINK_REF__DEPENDENCY:
                if (resolve) return getDependency();
                return basicGetDependency();
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
            case GrlPackage.LINK_REF__SOURCE:
                setSource((SpecificationNode)newValue);
                return;
            case GrlPackage.LINK_REF__TARGET:
                setTarget((SpecificationNode)newValue);
                return;
            case GrlPackage.LINK_REF__SPEC_DIAGRAM:
                setSpecDiagram((SpecificationDiagram)newValue);
                return;
            case GrlPackage.LINK_REF__BELIEFS:
                getBeliefs().clear();
                getBeliefs().addAll((Collection)newValue);
                return;
            case GrlPackage.LINK_REF__LINK:
                setLink((ElementLink)newValue);
                return;
            case GrlPackage.LINK_REF__DEPENDENCY:
                setDependency((Dependency)newValue);
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
            case GrlPackage.LINK_REF__SOURCE:
                setSource((SpecificationNode)null);
                return;
            case GrlPackage.LINK_REF__TARGET:
                setTarget((SpecificationNode)null);
                return;
            case GrlPackage.LINK_REF__SPEC_DIAGRAM:
                setSpecDiagram((SpecificationDiagram)null);
                return;
            case GrlPackage.LINK_REF__BELIEFS:
                getBeliefs().clear();
                return;
            case GrlPackage.LINK_REF__LINK:
                setLink((ElementLink)null);
                return;
            case GrlPackage.LINK_REF__DEPENDENCY:
                setDependency((Dependency)null);
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
            case GrlPackage.LINK_REF__SOURCE:
                return source != null;
            case GrlPackage.LINK_REF__TARGET:
                return target != null;
            case GrlPackage.LINK_REF__SPEC_DIAGRAM:
                return getSpecDiagram() != null;
            case GrlPackage.LINK_REF__BELIEFS:
                return beliefs != null && !beliefs.isEmpty();
            case GrlPackage.LINK_REF__LINK:
                return link != null;
            case GrlPackage.LINK_REF__DEPENDENCY:
                return dependency != null;
        }
        return eDynamicIsSet(eFeature);
    }

} //LinkRefImpl
