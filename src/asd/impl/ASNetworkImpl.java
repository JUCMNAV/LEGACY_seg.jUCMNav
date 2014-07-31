/**
 */
package asd.impl;

import asd.ASDlayout;
import asd.ASDspec;
import asd.ASNetwork;
import asd.AsdPackage;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import urncore.Comment;
import urncore.Concern;
import urncore.IURNConnection;
import urncore.IURNContainerRef;
import urncore.IURNNode;
import urncore.URNdefinition;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AS Network</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link asd.impl.ASNetworkImpl#getUrndefinition <em>Urndefinition</em>}</li>
 *   <li>{@link asd.impl.ASNetworkImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link asd.impl.ASNetworkImpl#getContRefs <em>Cont Refs</em>}</li>
 *   <li>{@link asd.impl.ASNetworkImpl#getConnections <em>Connections</em>}</li>
 *   <li>{@link asd.impl.ASNetworkImpl#getConcern <em>Concern</em>}</li>
 *   <li>{@link asd.impl.ASNetworkImpl#getComments <em>Comments</em>}</li>
 *   <li>{@link asd.impl.ASNetworkImpl#getASDspec <em>AS Dspec</em>}</li>
 *   <li>{@link asd.impl.ASNetworkImpl#getAsdLayouts <em>Asd Layouts</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ASNetworkImpl extends MinimalEObjectImpl.Container implements ASNetwork {
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
	 * The cached value of the '{@link #getASDspec() <em>AS Dspec</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getASDspec()
	 * @generated
	 * @ordered
	 */
	protected EList asDspec;

	/**
	 * The cached value of the '{@link #getAsdLayouts() <em>Asd Layouts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAsdLayouts()
	 * @generated
	 * @ordered
	 */
	protected EList asdLayouts;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ASNetworkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return AsdPackage.Literals.AS_NETWORK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public URNdefinition getUrndefinition() {
		if (eContainerFeatureID() != AsdPackage.AS_NETWORK__URNDEFINITION) return null;
		return (URNdefinition)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUrndefinition(URNdefinition newUrndefinition, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newUrndefinition, AsdPackage.AS_NETWORK__URNDEFINITION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUrndefinition(URNdefinition newUrndefinition) {
		if (newUrndefinition != eInternalContainer() || (eContainerFeatureID() != AsdPackage.AS_NETWORK__URNDEFINITION && newUrndefinition != null)) {
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsdPackage.AS_NETWORK__URNDEFINITION, newUrndefinition, newUrndefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getNodes() {
		if (nodes == null) {
			nodes = new EObjectContainmentWithInverseEList(IURNNode.class, this, AsdPackage.AS_NETWORK__NODES, UrncorePackage.IURN_NODE__DIAGRAM);
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
			contRefs = new EObjectContainmentWithInverseEList(IURNContainerRef.class, this, AsdPackage.AS_NETWORK__CONT_REFS, UrncorePackage.IURN_CONTAINER_REF__DIAGRAM);
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
			connections = new EObjectContainmentWithInverseEList(IURNConnection.class, this, AsdPackage.AS_NETWORK__CONNECTIONS, UrncorePackage.IURN_CONNECTION__DIAGRAM);
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AsdPackage.AS_NETWORK__CONCERN, oldConcern, concern));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AsdPackage.AS_NETWORK__CONCERN, oldConcern, newConcern);
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsdPackage.AS_NETWORK__CONCERN, newConcern, newConcern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getComments() {
		if (comments == null) {
			comments = new EObjectContainmentWithInverseEList(Comment.class, this, AsdPackage.AS_NETWORK__COMMENTS, UrncorePackage.COMMENT__DIAGRAM);
		}
		return comments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getASDspec() {
		if (asDspec == null) {
			asDspec = new EObjectWithInverseResolvingEList.ManyInverse(ASDspec.class, this, AsdPackage.AS_NETWORK__AS_DSPEC, AsdPackage.AS_DSPEC__AS_NETWORK);
		}
		return asDspec;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getAsdLayouts() {
		if (asdLayouts == null) {
			asdLayouts = new EObjectContainmentWithInverseEList(ASDlayout.class, this, AsdPackage.AS_NETWORK__ASD_LAYOUTS, AsdPackage.AS_DLAYOUT__AS_NETWORK);
		}
		return asdLayouts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AsdPackage.AS_NETWORK__URNDEFINITION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetUrndefinition((URNdefinition)otherEnd, msgs);
			case AsdPackage.AS_NETWORK__NODES:
				return ((InternalEList)getNodes()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_NETWORK__CONT_REFS:
				return ((InternalEList)getContRefs()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_NETWORK__CONNECTIONS:
				return ((InternalEList)getConnections()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_NETWORK__CONCERN:
				if (concern != null)
					msgs = ((InternalEObject)concern).eInverseRemove(this, UrncorePackage.CONCERN__SPEC_DIAGRAMS, Concern.class, msgs);
				return basicSetConcern((Concern)otherEnd, msgs);
			case AsdPackage.AS_NETWORK__COMMENTS:
				return ((InternalEList)getComments()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_NETWORK__AS_DSPEC:
				return ((InternalEList)getASDspec()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_NETWORK__ASD_LAYOUTS:
				return ((InternalEList)getAsdLayouts()).basicAdd(otherEnd, msgs);
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
			case AsdPackage.AS_NETWORK__URNDEFINITION:
				return basicSetUrndefinition(null, msgs);
			case AsdPackage.AS_NETWORK__NODES:
				return ((InternalEList)getNodes()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_NETWORK__CONT_REFS:
				return ((InternalEList)getContRefs()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_NETWORK__CONNECTIONS:
				return ((InternalEList)getConnections()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_NETWORK__CONCERN:
				return basicSetConcern(null, msgs);
			case AsdPackage.AS_NETWORK__COMMENTS:
				return ((InternalEList)getComments()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_NETWORK__AS_DSPEC:
				return ((InternalEList)getASDspec()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_NETWORK__ASD_LAYOUTS:
				return ((InternalEList)getAsdLayouts()).basicRemove(otherEnd, msgs);
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
			case AsdPackage.AS_NETWORK__URNDEFINITION:
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
			case AsdPackage.AS_NETWORK__URNDEFINITION:
				return getUrndefinition();
			case AsdPackage.AS_NETWORK__NODES:
				return getNodes();
			case AsdPackage.AS_NETWORK__CONT_REFS:
				return getContRefs();
			case AsdPackage.AS_NETWORK__CONNECTIONS:
				return getConnections();
			case AsdPackage.AS_NETWORK__CONCERN:
				if (resolve) return getConcern();
				return basicGetConcern();
			case AsdPackage.AS_NETWORK__COMMENTS:
				return getComments();
			case AsdPackage.AS_NETWORK__AS_DSPEC:
				return getASDspec();
			case AsdPackage.AS_NETWORK__ASD_LAYOUTS:
				return getAsdLayouts();
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
			case AsdPackage.AS_NETWORK__URNDEFINITION:
				setUrndefinition((URNdefinition)newValue);
				return;
			case AsdPackage.AS_NETWORK__NODES:
				getNodes().clear();
				getNodes().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_NETWORK__CONT_REFS:
				getContRefs().clear();
				getContRefs().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_NETWORK__CONNECTIONS:
				getConnections().clear();
				getConnections().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_NETWORK__CONCERN:
				setConcern((Concern)newValue);
				return;
			case AsdPackage.AS_NETWORK__COMMENTS:
				getComments().clear();
				getComments().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_NETWORK__AS_DSPEC:
				getASDspec().clear();
				getASDspec().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_NETWORK__ASD_LAYOUTS:
				getAsdLayouts().clear();
				getAsdLayouts().addAll((Collection)newValue);
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
			case AsdPackage.AS_NETWORK__URNDEFINITION:
				setUrndefinition((URNdefinition)null);
				return;
			case AsdPackage.AS_NETWORK__NODES:
				getNodes().clear();
				return;
			case AsdPackage.AS_NETWORK__CONT_REFS:
				getContRefs().clear();
				return;
			case AsdPackage.AS_NETWORK__CONNECTIONS:
				getConnections().clear();
				return;
			case AsdPackage.AS_NETWORK__CONCERN:
				setConcern((Concern)null);
				return;
			case AsdPackage.AS_NETWORK__COMMENTS:
				getComments().clear();
				return;
			case AsdPackage.AS_NETWORK__AS_DSPEC:
				getASDspec().clear();
				return;
			case AsdPackage.AS_NETWORK__ASD_LAYOUTS:
				getAsdLayouts().clear();
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
			case AsdPackage.AS_NETWORK__URNDEFINITION:
				return getUrndefinition() != null;
			case AsdPackage.AS_NETWORK__NODES:
				return nodes != null && !nodes.isEmpty();
			case AsdPackage.AS_NETWORK__CONT_REFS:
				return contRefs != null && !contRefs.isEmpty();
			case AsdPackage.AS_NETWORK__CONNECTIONS:
				return connections != null && !connections.isEmpty();
			case AsdPackage.AS_NETWORK__CONCERN:
				return concern != null;
			case AsdPackage.AS_NETWORK__COMMENTS:
				return comments != null && !comments.isEmpty();
			case AsdPackage.AS_NETWORK__AS_DSPEC:
				return asDspec != null && !asDspec.isEmpty();
			case AsdPackage.AS_NETWORK__ASD_LAYOUTS:
				return asdLayouts != null && !asdLayouts.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ASNetworkImpl
