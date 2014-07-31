/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.GRLGraph;
import grl.GrlPackage;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import urncore.Comment;
import urncore.Concern;
import urncore.IURNConnection;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.URNdefinition;
import urncore.UrncorePackage;
import urncore.impl.GRLmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>GRL Graph</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.GRLGraphImpl#getUrndefinition <em>Urndefinition</em>}</li>
 *   <li>{@link grl.impl.GRLGraphImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link grl.impl.GRLGraphImpl#getContRefs <em>Cont Refs</em>}</li>
 *   <li>{@link grl.impl.GRLGraphImpl#getConnections <em>Connections</em>}</li>
 *   <li>{@link grl.impl.GRLGraphImpl#getConcern <em>Concern</em>}</li>
 *   <li>{@link grl.impl.GRLGraphImpl#getComments <em>Comments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GRLGraphImpl extends GRLmodelElementImpl implements GRLGraph {
    /**
	 * The cached value of the '{@link #getNodes() <em>Nodes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getNodes()
	 * @generated
	 * @ordered
	 */
    protected EList nodes;

    /**
	 * The cached value of the '{@link #getContRefs() <em>Cont Refs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getContRefs()
	 * @generated
	 * @ordered
	 */
    protected EList contRefs;

    /**
	 * The cached value of the '{@link #getConnections() <em>Connections</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getConnections()
	 * @generated
	 * @ordered
	 */
    protected EList connections;

    /**
	 * The cached value of the '{@link #getConcern() <em>Concern</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConcern()
	 * @generated
	 * @ordered
	 */
	protected Concern concern;

    /**
	 * The cached value of the '{@link #getComments() <em>Comments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComments()
	 * @generated
	 * @ordered
	 */
	protected EList comments;

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected GRLGraphImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return GrlPackage.Literals.GRL_GRAPH;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public URNdefinition getUrndefinition() {
		if (eContainerFeatureID() != GrlPackage.GRL_GRAPH__URNDEFINITION) return null;
		return (URNdefinition)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUrndefinition(URNdefinition newUrndefinition, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newUrndefinition, GrlPackage.GRL_GRAPH__URNDEFINITION, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUrndefinition(URNdefinition newUrndefinition) {
		if (newUrndefinition != eInternalContainer() || (eContainerFeatureID() != GrlPackage.GRL_GRAPH__URNDEFINITION && newUrndefinition != null)) {
			if (EcoreUtil.isAncestor(this, newUrndefinition))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUrndefinition != null)
				msgs = ((InternalEObject)newUrndefinition).eInverseAdd(this, UrncorePackage.UR_NDEFINITION__SPEC_DIAGRAMS, URNdefinition.class, msgs);
			msgs = basicSetUrndefinition(newUrndefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.GRL_GRAPH__URNDEFINITION, newUrndefinition, newUrndefinition));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getNodes() {
		if (nodes == null) {
			nodes = new EObjectContainmentWithInverseEList(IURNNode.class, this, GrlPackage.GRL_GRAPH__NODES, UrncorePackage.IURN_NODE__DIAGRAM);
		}
		return nodes;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getContRefs() {
		if (contRefs == null) {
			contRefs = new EObjectContainmentWithInverseEList(IURNContainerRef.class, this, GrlPackage.GRL_GRAPH__CONT_REFS, UrncorePackage.IURN_CONTAINER_REF__DIAGRAM);
		}
		return contRefs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getConnections() {
		if (connections == null) {
			connections = new EObjectContainmentWithInverseEList(IURNConnection.class, this, GrlPackage.GRL_GRAPH__CONNECTIONS, UrncorePackage.IURN_CONNECTION__DIAGRAM);
		}
		return connections;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Concern getConcern() {
		if (concern != null && concern.eIsProxy()) {
			InternalEObject oldConcern = (InternalEObject)concern;
			concern = (Concern)eResolveProxy(oldConcern);
			if (concern != oldConcern) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.GRL_GRAPH__CONCERN, oldConcern, concern));
			}
		}
		return concern;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Concern basicGetConcern() {
		return concern;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConcern(Concern newConcern, NotificationChain msgs) {
		Concern oldConcern = concern;
		concern = newConcern;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.GRL_GRAPH__CONCERN, oldConcern, newConcern);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConcern(Concern newConcern) {
		if (newConcern != concern) {
			NotificationChain msgs = null;
			if (concern != null)
				msgs = ((InternalEObject)concern).eInverseRemove(this, UrncorePackage.CONCERN__SPEC_DIAGRAMS, Concern.class, msgs);
			if (newConcern != null)
				msgs = ((InternalEObject)newConcern).eInverseAdd(this, UrncorePackage.CONCERN__SPEC_DIAGRAMS, Concern.class, msgs);
			msgs = basicSetConcern(newConcern, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.GRL_GRAPH__CONCERN, newConcern, newConcern));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getComments() {
		if (comments == null) {
			comments = new EObjectContainmentWithInverseEList(Comment.class, this, GrlPackage.GRL_GRAPH__COMMENTS, UrncorePackage.COMMENT__DIAGRAM);
		}
		return comments;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GrlPackage.GRL_GRAPH__URNDEFINITION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetUrndefinition((URNdefinition)otherEnd, msgs);
			case GrlPackage.GRL_GRAPH__NODES:
				return ((InternalEList)getNodes()).basicAdd(otherEnd, msgs);
			case GrlPackage.GRL_GRAPH__CONT_REFS:
				return ((InternalEList)getContRefs()).basicAdd(otherEnd, msgs);
			case GrlPackage.GRL_GRAPH__CONNECTIONS:
				return ((InternalEList)getConnections()).basicAdd(otherEnd, msgs);
			case GrlPackage.GRL_GRAPH__CONCERN:
				if (concern != null)
					msgs = ((InternalEObject)concern).eInverseRemove(this, UrncorePackage.CONCERN__SPEC_DIAGRAMS, Concern.class, msgs);
				return basicSetConcern((Concern)otherEnd, msgs);
			case GrlPackage.GRL_GRAPH__COMMENTS:
				return ((InternalEList)getComments()).basicAdd(otherEnd, msgs);
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
			case GrlPackage.GRL_GRAPH__URNDEFINITION:
				return basicSetUrndefinition(null, msgs);
			case GrlPackage.GRL_GRAPH__NODES:
				return ((InternalEList)getNodes()).basicRemove(otherEnd, msgs);
			case GrlPackage.GRL_GRAPH__CONT_REFS:
				return ((InternalEList)getContRefs()).basicRemove(otherEnd, msgs);
			case GrlPackage.GRL_GRAPH__CONNECTIONS:
				return ((InternalEList)getConnections()).basicRemove(otherEnd, msgs);
			case GrlPackage.GRL_GRAPH__CONCERN:
				return basicSetConcern(null, msgs);
			case GrlPackage.GRL_GRAPH__COMMENTS:
				return ((InternalEList)getComments()).basicRemove(otherEnd, msgs);
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
			case GrlPackage.GRL_GRAPH__URNDEFINITION:
				return eInternalContainer().eInverseRemove(this, UrncorePackage.UR_NDEFINITION__SPEC_DIAGRAMS, URNdefinition.class, msgs);
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
			case GrlPackage.GRL_GRAPH__URNDEFINITION:
				return getUrndefinition();
			case GrlPackage.GRL_GRAPH__NODES:
				return getNodes();
			case GrlPackage.GRL_GRAPH__CONT_REFS:
				return getContRefs();
			case GrlPackage.GRL_GRAPH__CONNECTIONS:
				return getConnections();
			case GrlPackage.GRL_GRAPH__CONCERN:
				if (resolve) return getConcern();
				return basicGetConcern();
			case GrlPackage.GRL_GRAPH__COMMENTS:
				return getComments();
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
			case GrlPackage.GRL_GRAPH__URNDEFINITION:
				setUrndefinition((URNdefinition)newValue);
				return;
			case GrlPackage.GRL_GRAPH__NODES:
				getNodes().clear();
				getNodes().addAll((Collection)newValue);
				return;
			case GrlPackage.GRL_GRAPH__CONT_REFS:
				getContRefs().clear();
				getContRefs().addAll((Collection)newValue);
				return;
			case GrlPackage.GRL_GRAPH__CONNECTIONS:
				getConnections().clear();
				getConnections().addAll((Collection)newValue);
				return;
			case GrlPackage.GRL_GRAPH__CONCERN:
				setConcern((Concern)newValue);
				return;
			case GrlPackage.GRL_GRAPH__COMMENTS:
				getComments().clear();
				getComments().addAll((Collection)newValue);
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
			case GrlPackage.GRL_GRAPH__URNDEFINITION:
				setUrndefinition((URNdefinition)null);
				return;
			case GrlPackage.GRL_GRAPH__NODES:
				getNodes().clear();
				return;
			case GrlPackage.GRL_GRAPH__CONT_REFS:
				getContRefs().clear();
				return;
			case GrlPackage.GRL_GRAPH__CONNECTIONS:
				getConnections().clear();
				return;
			case GrlPackage.GRL_GRAPH__CONCERN:
				setConcern((Concern)null);
				return;
			case GrlPackage.GRL_GRAPH__COMMENTS:
				getComments().clear();
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
			case GrlPackage.GRL_GRAPH__URNDEFINITION:
				return getUrndefinition() != null;
			case GrlPackage.GRL_GRAPH__NODES:
				return nodes != null && !nodes.isEmpty();
			case GrlPackage.GRL_GRAPH__CONT_REFS:
				return contRefs != null && !contRefs.isEmpty();
			case GrlPackage.GRL_GRAPH__CONNECTIONS:
				return connections != null && !connections.isEmpty();
			case GrlPackage.GRL_GRAPH__CONCERN:
				return concern != null;
			case GrlPackage.GRL_GRAPH__COMMENTS:
				return comments != null && !comments.isEmpty();
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == IURNDiagram.class) {
			switch (derivedFeatureID) {
				case GrlPackage.GRL_GRAPH__URNDEFINITION: return UrncorePackage.IURN_DIAGRAM__URNDEFINITION;
				case GrlPackage.GRL_GRAPH__NODES: return UrncorePackage.IURN_DIAGRAM__NODES;
				case GrlPackage.GRL_GRAPH__CONT_REFS: return UrncorePackage.IURN_DIAGRAM__CONT_REFS;
				case GrlPackage.GRL_GRAPH__CONNECTIONS: return UrncorePackage.IURN_DIAGRAM__CONNECTIONS;
				case GrlPackage.GRL_GRAPH__CONCERN: return UrncorePackage.IURN_DIAGRAM__CONCERN;
				case GrlPackage.GRL_GRAPH__COMMENTS: return UrncorePackage.IURN_DIAGRAM__COMMENTS;
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
		if (baseClass == IURNDiagram.class) {
			switch (baseFeatureID) {
				case UrncorePackage.IURN_DIAGRAM__URNDEFINITION: return GrlPackage.GRL_GRAPH__URNDEFINITION;
				case UrncorePackage.IURN_DIAGRAM__NODES: return GrlPackage.GRL_GRAPH__NODES;
				case UrncorePackage.IURN_DIAGRAM__CONT_REFS: return GrlPackage.GRL_GRAPH__CONT_REFS;
				case UrncorePackage.IURN_DIAGRAM__CONNECTIONS: return GrlPackage.GRL_GRAPH__CONNECTIONS;
				case UrncorePackage.IURN_DIAGRAM__CONCERN: return GrlPackage.GRL_GRAPH__CONCERN;
				case UrncorePackage.IURN_DIAGRAM__COMMENTS: return GrlPackage.GRL_GRAPH__COMMENTS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //GRLGraphImpl
