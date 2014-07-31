/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.ElementLink;
import grl.GrlPackage;
import grl.LinkRef;
import grl.LinkRefBendpoint;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import urncore.ConnectionLabel;
import urncore.IURNDiagram;
import urncore.IURNNode;
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
 *   <li>{@link grl.impl.LinkRefImpl#getDiagram <em>Diagram</em>}</li>
 *   <li>{@link grl.impl.LinkRefImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link grl.impl.LinkRefImpl#getLink <em>Link</em>}</li>
 *   <li>{@link grl.impl.LinkRefImpl#getBendpoints <em>Bendpoints</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LinkRefImpl extends MinimalEObjectImpl.Container implements LinkRef {
    /**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
    protected IURNNode source;

    /**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
    protected IURNNode target;

    /**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected ConnectionLabel label;

				/**
	 * The cached value of the '{@link #getLink() <em>Link</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLink()
	 * @generated
	 * @ordered
	 */
    protected ElementLink link;

    /**
	 * The cached value of the '{@link #getBendpoints() <em>Bendpoints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getBendpoints()
	 * @generated
	 * @ordered
	 */
    protected EList bendpoints;

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
		return GrlPackage.Literals.LINK_REF;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public IURNNode getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (IURNNode)eResolveProxy(oldSource);
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
    public IURNNode basicGetSource() {
		return source;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetSource(IURNNode newSource, NotificationChain msgs) {
		IURNNode oldSource = source;
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
    public void setSource(IURNNode newSource) {
		if (newSource != source) {
			NotificationChain msgs = null;
			if (source != null)
				msgs = ((InternalEObject)source).eInverseRemove(this, UrncorePackage.IURN_NODE__SUCC, IURNNode.class, msgs);
			if (newSource != null)
				msgs = ((InternalEObject)newSource).eInverseAdd(this, UrncorePackage.IURN_NODE__SUCC, IURNNode.class, msgs);
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
    public IURNNode getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (IURNNode)eResolveProxy(oldTarget);
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
    public IURNNode basicGetTarget() {
		return target;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetTarget(IURNNode newTarget, NotificationChain msgs) {
		IURNNode oldTarget = target;
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
    public void setTarget(IURNNode newTarget) {
		if (newTarget != target) {
			NotificationChain msgs = null;
			if (target != null)
				msgs = ((InternalEObject)target).eInverseRemove(this, UrncorePackage.IURN_NODE__PRED, IURNNode.class, msgs);
			if (newTarget != null)
				msgs = ((InternalEObject)newTarget).eInverseAdd(this, UrncorePackage.IURN_NODE__PRED, IURNNode.class, msgs);
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
    public IURNDiagram getDiagram() {
		if (eContainerFeatureID() != GrlPackage.LINK_REF__DIAGRAM) return null;
		return (IURNDiagram)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDiagram(IURNDiagram newDiagram, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newDiagram, GrlPackage.LINK_REF__DIAGRAM, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDiagram(IURNDiagram newDiagram) {
		if (newDiagram != eInternalContainer() || (eContainerFeatureID() != GrlPackage.LINK_REF__DIAGRAM && newDiagram != null)) {
			if (EcoreUtil.isAncestor(this, newDiagram))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDiagram != null)
				msgs = ((InternalEObject)newDiagram).eInverseAdd(this, UrncorePackage.IURN_DIAGRAM__CONNECTIONS, IURNDiagram.class, msgs);
			msgs = basicSetDiagram(newDiagram, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.LINK_REF__DIAGRAM, newDiagram, newDiagram));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConnectionLabel getLabel() {
		return label;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLabel(ConnectionLabel newLabel, NotificationChain msgs) {
		ConnectionLabel oldLabel = label;
		label = newLabel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.LINK_REF__LABEL, oldLabel, newLabel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabel(ConnectionLabel newLabel) {
		if (newLabel != label) {
			NotificationChain msgs = null;
			if (label != null)
				msgs = ((InternalEObject)label).eInverseRemove(this, UrncorePackage.CONNECTION_LABEL__CONNECTION, ConnectionLabel.class, msgs);
			if (newLabel != null)
				msgs = ((InternalEObject)newLabel).eInverseAdd(this, UrncorePackage.CONNECTION_LABEL__CONNECTION, ConnectionLabel.class, msgs);
			msgs = basicSetLabel(newLabel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.LINK_REF__LABEL, newLabel, newLabel));
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ElementLink getLink() {
		if (link != null && link.eIsProxy()) {
			InternalEObject oldLink = (InternalEObject)link;
			link = (ElementLink)eResolveProxy(oldLink);
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
    public EList getBendpoints() {
		if (bendpoints == null) {
			bendpoints = new EObjectContainmentWithInverseEList(LinkRefBendpoint.class, this, GrlPackage.LINK_REF__BENDPOINTS, GrlPackage.LINK_REF_BENDPOINT__LINKREF);
		}
		return bendpoints;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GrlPackage.LINK_REF__SOURCE:
				if (source != null)
					msgs = ((InternalEObject)source).eInverseRemove(this, UrncorePackage.IURN_NODE__SUCC, IURNNode.class, msgs);
				return basicSetSource((IURNNode)otherEnd, msgs);
			case GrlPackage.LINK_REF__TARGET:
				if (target != null)
					msgs = ((InternalEObject)target).eInverseRemove(this, UrncorePackage.IURN_NODE__PRED, IURNNode.class, msgs);
				return basicSetTarget((IURNNode)otherEnd, msgs);
			case GrlPackage.LINK_REF__DIAGRAM:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetDiagram((IURNDiagram)otherEnd, msgs);
			case GrlPackage.LINK_REF__LABEL:
				if (label != null)
					msgs = ((InternalEObject)label).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - GrlPackage.LINK_REF__LABEL, null, msgs);
				return basicSetLabel((ConnectionLabel)otherEnd, msgs);
			case GrlPackage.LINK_REF__LINK:
				if (link != null)
					msgs = ((InternalEObject)link).eInverseRemove(this, GrlPackage.ELEMENT_LINK__REFS, ElementLink.class, msgs);
				return basicSetLink((ElementLink)otherEnd, msgs);
			case GrlPackage.LINK_REF__BENDPOINTS:
				return ((InternalEList)getBendpoints()).basicAdd(otherEnd, msgs);
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
			case GrlPackage.LINK_REF__SOURCE:
				return basicSetSource(null, msgs);
			case GrlPackage.LINK_REF__TARGET:
				return basicSetTarget(null, msgs);
			case GrlPackage.LINK_REF__DIAGRAM:
				return basicSetDiagram(null, msgs);
			case GrlPackage.LINK_REF__LABEL:
				return basicSetLabel(null, msgs);
			case GrlPackage.LINK_REF__LINK:
				return basicSetLink(null, msgs);
			case GrlPackage.LINK_REF__BENDPOINTS:
				return ((InternalEList)getBendpoints()).basicRemove(otherEnd, msgs);
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
			case GrlPackage.LINK_REF__DIAGRAM:
				return eInternalContainer().eInverseRemove(this, UrncorePackage.IURN_DIAGRAM__CONNECTIONS, IURNDiagram.class, msgs);
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
			case GrlPackage.LINK_REF__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case GrlPackage.LINK_REF__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case GrlPackage.LINK_REF__DIAGRAM:
				return getDiagram();
			case GrlPackage.LINK_REF__LABEL:
				return getLabel();
			case GrlPackage.LINK_REF__LINK:
				if (resolve) return getLink();
				return basicGetLink();
			case GrlPackage.LINK_REF__BENDPOINTS:
				return getBendpoints();
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
			case GrlPackage.LINK_REF__SOURCE:
				setSource((IURNNode)newValue);
				return;
			case GrlPackage.LINK_REF__TARGET:
				setTarget((IURNNode)newValue);
				return;
			case GrlPackage.LINK_REF__DIAGRAM:
				setDiagram((IURNDiagram)newValue);
				return;
			case GrlPackage.LINK_REF__LABEL:
				setLabel((ConnectionLabel)newValue);
				return;
			case GrlPackage.LINK_REF__LINK:
				setLink((ElementLink)newValue);
				return;
			case GrlPackage.LINK_REF__BENDPOINTS:
				getBendpoints().clear();
				getBendpoints().addAll((Collection)newValue);
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
			case GrlPackage.LINK_REF__SOURCE:
				setSource((IURNNode)null);
				return;
			case GrlPackage.LINK_REF__TARGET:
				setTarget((IURNNode)null);
				return;
			case GrlPackage.LINK_REF__DIAGRAM:
				setDiagram((IURNDiagram)null);
				return;
			case GrlPackage.LINK_REF__LABEL:
				setLabel((ConnectionLabel)null);
				return;
			case GrlPackage.LINK_REF__LINK:
				setLink((ElementLink)null);
				return;
			case GrlPackage.LINK_REF__BENDPOINTS:
				getBendpoints().clear();
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
			case GrlPackage.LINK_REF__SOURCE:
				return source != null;
			case GrlPackage.LINK_REF__TARGET:
				return target != null;
			case GrlPackage.LINK_REF__DIAGRAM:
				return getDiagram() != null;
			case GrlPackage.LINK_REF__LABEL:
				return label != null;
			case GrlPackage.LINK_REF__LINK:
				return link != null;
			case GrlPackage.LINK_REF__BENDPOINTS:
				return bendpoints != null && !bendpoints.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //LinkRefImpl
