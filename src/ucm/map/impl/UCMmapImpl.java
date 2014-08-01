/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map.impl;

import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.COREModelElement;
import ca.mcgill.sel.core.COREModelReuse;
import ca.mcgill.sel.core.CorePackage;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import ucm.map.MapPackage;
import ucm.map.PluginBinding;
import ucm.map.UCMmap;
import urncore.Comment;
import urncore.Concern;
import urncore.IURNConnection;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.URNdefinition;
import urncore.UrncorePackage;
import urncore.impl.UCMmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>UC Mmap</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.UCMmapImpl#getUrndefinition <em>Urndefinition</em>}</li>
 *   <li>{@link ucm.map.impl.UCMmapImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link ucm.map.impl.UCMmapImpl#getContRefs <em>Cont Refs</em>}</li>
 *   <li>{@link ucm.map.impl.UCMmapImpl#getConnections <em>Connections</em>}</li>
 *   <li>{@link ucm.map.impl.UCMmapImpl#getConcern <em>Concern</em>}</li>
 *   <li>{@link ucm.map.impl.UCMmapImpl#getComments <em>Comments</em>}</li>
 *   <li>{@link ucm.map.impl.UCMmapImpl#getModelReuse <em>Model Reuse</em>}</li>
 *   <li>{@link ucm.map.impl.UCMmapImpl#getModelElements <em>Model Elements</em>}</li>
 *   <li>{@link ucm.map.impl.UCMmapImpl#getRealizes <em>Realizes</em>}</li>
 *   <li>{@link ucm.map.impl.UCMmapImpl#isSingleton <em>Singleton</em>}</li>
 *   <li>{@link ucm.map.impl.UCMmapImpl#getParentStub <em>Parent Stub</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UCMmapImpl extends UCMmodelElementImpl implements UCMmap {
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
	 * The cached value of the '{@link #getModelReuse() <em>Model Reuse</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelReuse()
	 * @generated
	 * @ordered
	 */
	protected EList<COREModelReuse> modelReuse;

				/**
	 * The cached value of the '{@link #getModelElements() <em>Model Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelElements()
	 * @generated
	 * @ordered
	 */
	protected EList<COREModelElement> modelElements;

				/**
	 * The cached value of the '{@link #getRealizes() <em>Realizes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRealizes()
	 * @generated
	 * @ordered
	 */
	protected EList<COREFeature> realizes;

				/**
	 * The default value of the '{@link #isSingleton() <em>Singleton</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSingleton()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SINGLETON_EDEFAULT = true;

				/**
	 * The cached value of the '{@link #isSingleton() <em>Singleton</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSingleton()
	 * @generated
	 * @ordered
	 */
	protected boolean singleton = SINGLETON_EDEFAULT;

				/**
	 * The cached value of the '{@link #getParentStub() <em>Parent Stub</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getParentStub()
	 * @generated
	 * @ordered
	 */
    protected EList parentStub;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected UCMmapImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return MapPackage.Literals.UC_MMAP;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public URNdefinition getUrndefinition() {
		if (eContainerFeatureID() != MapPackage.UC_MMAP__URNDEFINITION) return null;
		return (URNdefinition)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUrndefinition(URNdefinition newUrndefinition, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newUrndefinition, MapPackage.UC_MMAP__URNDEFINITION, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setUrndefinition(URNdefinition newUrndefinition) {
		if (newUrndefinition != eInternalContainer() || (eContainerFeatureID() != MapPackage.UC_MMAP__URNDEFINITION && newUrndefinition != null)) {
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
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.UC_MMAP__URNDEFINITION, newUrndefinition, newUrndefinition));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getNodes() {
		if (nodes == null) {
			nodes = new EObjectContainmentWithInverseEList(IURNNode.class, this, MapPackage.UC_MMAP__NODES, UrncorePackage.IURN_NODE__DIAGRAM);
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
			contRefs = new EObjectContainmentWithInverseEList(IURNContainerRef.class, this, MapPackage.UC_MMAP__CONT_REFS, UrncorePackage.IURN_CONTAINER_REF__DIAGRAM);
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
			connections = new EObjectContainmentWithInverseEList(IURNConnection.class, this, MapPackage.UC_MMAP__CONNECTIONS, UrncorePackage.IURN_CONNECTION__DIAGRAM);
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.UC_MMAP__CONCERN, oldConcern, concern));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.UC_MMAP__CONCERN, oldConcern, newConcern);
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
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.UC_MMAP__CONCERN, newConcern, newConcern));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getComments() {
		if (comments == null) {
			comments = new EObjectContainmentWithInverseEList(Comment.class, this, MapPackage.UC_MMAP__COMMENTS, UrncorePackage.COMMENT__DIAGRAM);
		}
		return comments;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<COREModelReuse> getModelReuse() {
		if (modelReuse == null) {
			modelReuse = new EObjectContainmentEList(COREModelReuse.class, this, MapPackage.UC_MMAP__MODEL_REUSE);
		}
		return modelReuse;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<COREModelElement> getModelElements() {
		if (modelElements == null) {
			modelElements = new EObjectResolvingEList(COREModelElement.class, this, MapPackage.UC_MMAP__MODEL_ELEMENTS);
		}
		return modelElements;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<COREFeature> getRealizes() {
		if (realizes == null) {
			realizes = new EObjectWithInverseResolvingEList.ManyInverse(COREFeature.class, this, MapPackage.UC_MMAP__REALIZES, CorePackage.CORE_FEATURE__REALIZED_BY);
		}
		return realizes;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSingleton() {
		return singleton;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSingleton(boolean newSingleton) {
		boolean oldSingleton = singleton;
		singleton = newSingleton;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.UC_MMAP__SINGLETON, oldSingleton, singleton));
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getParentStub() {
		if (parentStub == null) {
			parentStub = new EObjectWithInverseResolvingEList(PluginBinding.class, this, MapPackage.UC_MMAP__PARENT_STUB, MapPackage.PLUGIN_BINDING__PLUGIN);
		}
		return parentStub;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MapPackage.UC_MMAP__URNDEFINITION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetUrndefinition((URNdefinition)otherEnd, msgs);
			case MapPackage.UC_MMAP__NODES:
				return ((InternalEList)getNodes()).basicAdd(otherEnd, msgs);
			case MapPackage.UC_MMAP__CONT_REFS:
				return ((InternalEList)getContRefs()).basicAdd(otherEnd, msgs);
			case MapPackage.UC_MMAP__CONNECTIONS:
				return ((InternalEList)getConnections()).basicAdd(otherEnd, msgs);
			case MapPackage.UC_MMAP__CONCERN:
				if (concern != null)
					msgs = ((InternalEObject)concern).eInverseRemove(this, UrncorePackage.CONCERN__SPEC_DIAGRAMS, Concern.class, msgs);
				return basicSetConcern((Concern)otherEnd, msgs);
			case MapPackage.UC_MMAP__COMMENTS:
				return ((InternalEList)getComments()).basicAdd(otherEnd, msgs);
			case MapPackage.UC_MMAP__REALIZES:
				return ((InternalEList)getRealizes()).basicAdd(otherEnd, msgs);
			case MapPackage.UC_MMAP__PARENT_STUB:
				return ((InternalEList)getParentStub()).basicAdd(otherEnd, msgs);
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
			case MapPackage.UC_MMAP__URNDEFINITION:
				return basicSetUrndefinition(null, msgs);
			case MapPackage.UC_MMAP__NODES:
				return ((InternalEList)getNodes()).basicRemove(otherEnd, msgs);
			case MapPackage.UC_MMAP__CONT_REFS:
				return ((InternalEList)getContRefs()).basicRemove(otherEnd, msgs);
			case MapPackage.UC_MMAP__CONNECTIONS:
				return ((InternalEList)getConnections()).basicRemove(otherEnd, msgs);
			case MapPackage.UC_MMAP__CONCERN:
				return basicSetConcern(null, msgs);
			case MapPackage.UC_MMAP__COMMENTS:
				return ((InternalEList)getComments()).basicRemove(otherEnd, msgs);
			case MapPackage.UC_MMAP__MODEL_REUSE:
				return ((InternalEList)getModelReuse()).basicRemove(otherEnd, msgs);
			case MapPackage.UC_MMAP__REALIZES:
				return ((InternalEList)getRealizes()).basicRemove(otherEnd, msgs);
			case MapPackage.UC_MMAP__PARENT_STUB:
				return ((InternalEList)getParentStub()).basicRemove(otherEnd, msgs);
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
			case MapPackage.UC_MMAP__URNDEFINITION:
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
			case MapPackage.UC_MMAP__URNDEFINITION:
				return getUrndefinition();
			case MapPackage.UC_MMAP__NODES:
				return getNodes();
			case MapPackage.UC_MMAP__CONT_REFS:
				return getContRefs();
			case MapPackage.UC_MMAP__CONNECTIONS:
				return getConnections();
			case MapPackage.UC_MMAP__CONCERN:
				if (resolve) return getConcern();
				return basicGetConcern();
			case MapPackage.UC_MMAP__COMMENTS:
				return getComments();
			case MapPackage.UC_MMAP__MODEL_REUSE:
				return getModelReuse();
			case MapPackage.UC_MMAP__MODEL_ELEMENTS:
				return getModelElements();
			case MapPackage.UC_MMAP__REALIZES:
				return getRealizes();
			case MapPackage.UC_MMAP__SINGLETON:
				return isSingleton() ? Boolean.TRUE : Boolean.FALSE;
			case MapPackage.UC_MMAP__PARENT_STUB:
				return getParentStub();
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
			case MapPackage.UC_MMAP__URNDEFINITION:
				setUrndefinition((URNdefinition)newValue);
				return;
			case MapPackage.UC_MMAP__NODES:
				getNodes().clear();
				getNodes().addAll((Collection)newValue);
				return;
			case MapPackage.UC_MMAP__CONT_REFS:
				getContRefs().clear();
				getContRefs().addAll((Collection)newValue);
				return;
			case MapPackage.UC_MMAP__CONNECTIONS:
				getConnections().clear();
				getConnections().addAll((Collection)newValue);
				return;
			case MapPackage.UC_MMAP__CONCERN:
				setConcern((Concern)newValue);
				return;
			case MapPackage.UC_MMAP__COMMENTS:
				getComments().clear();
				getComments().addAll((Collection)newValue);
				return;
			case MapPackage.UC_MMAP__MODEL_REUSE:
				getModelReuse().clear();
				getModelReuse().addAll((Collection)newValue);
				return;
			case MapPackage.UC_MMAP__REALIZES:
				getRealizes().clear();
				getRealizes().addAll((Collection)newValue);
				return;
			case MapPackage.UC_MMAP__SINGLETON:
				setSingleton(((Boolean)newValue).booleanValue());
				return;
			case MapPackage.UC_MMAP__PARENT_STUB:
				getParentStub().clear();
				getParentStub().addAll((Collection)newValue);
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
			case MapPackage.UC_MMAP__URNDEFINITION:
				setUrndefinition((URNdefinition)null);
				return;
			case MapPackage.UC_MMAP__NODES:
				getNodes().clear();
				return;
			case MapPackage.UC_MMAP__CONT_REFS:
				getContRefs().clear();
				return;
			case MapPackage.UC_MMAP__CONNECTIONS:
				getConnections().clear();
				return;
			case MapPackage.UC_MMAP__CONCERN:
				setConcern((Concern)null);
				return;
			case MapPackage.UC_MMAP__COMMENTS:
				getComments().clear();
				return;
			case MapPackage.UC_MMAP__MODEL_REUSE:
				getModelReuse().clear();
				return;
			case MapPackage.UC_MMAP__REALIZES:
				getRealizes().clear();
				return;
			case MapPackage.UC_MMAP__SINGLETON:
				setSingleton(SINGLETON_EDEFAULT);
				return;
			case MapPackage.UC_MMAP__PARENT_STUB:
				getParentStub().clear();
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
			case MapPackage.UC_MMAP__URNDEFINITION:
				return getUrndefinition() != null;
			case MapPackage.UC_MMAP__NODES:
				return nodes != null && !nodes.isEmpty();
			case MapPackage.UC_MMAP__CONT_REFS:
				return contRefs != null && !contRefs.isEmpty();
			case MapPackage.UC_MMAP__CONNECTIONS:
				return connections != null && !connections.isEmpty();
			case MapPackage.UC_MMAP__CONCERN:
				return concern != null;
			case MapPackage.UC_MMAP__COMMENTS:
				return comments != null && !comments.isEmpty();
			case MapPackage.UC_MMAP__MODEL_REUSE:
				return modelReuse != null && !modelReuse.isEmpty();
			case MapPackage.UC_MMAP__MODEL_ELEMENTS:
				return modelElements != null && !modelElements.isEmpty();
			case MapPackage.UC_MMAP__REALIZES:
				return realizes != null && !realizes.isEmpty();
			case MapPackage.UC_MMAP__SINGLETON:
				return singleton != SINGLETON_EDEFAULT;
			case MapPackage.UC_MMAP__PARENT_STUB:
				return parentStub != null && !parentStub.isEmpty();
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
				case MapPackage.UC_MMAP__URNDEFINITION: return UrncorePackage.IURN_DIAGRAM__URNDEFINITION;
				case MapPackage.UC_MMAP__NODES: return UrncorePackage.IURN_DIAGRAM__NODES;
				case MapPackage.UC_MMAP__CONT_REFS: return UrncorePackage.IURN_DIAGRAM__CONT_REFS;
				case MapPackage.UC_MMAP__CONNECTIONS: return UrncorePackage.IURN_DIAGRAM__CONNECTIONS;
				case MapPackage.UC_MMAP__CONCERN: return UrncorePackage.IURN_DIAGRAM__CONCERN;
				case MapPackage.UC_MMAP__COMMENTS: return UrncorePackage.IURN_DIAGRAM__COMMENTS;
				default: return -1;
			}
		}
		if (baseClass == COREModel.class) {
			switch (derivedFeatureID) {
				case MapPackage.UC_MMAP__MODEL_REUSE: return CorePackage.CORE_MODEL__MODEL_REUSE;
				case MapPackage.UC_MMAP__MODEL_ELEMENTS: return CorePackage.CORE_MODEL__MODEL_ELEMENTS;
				case MapPackage.UC_MMAP__REALIZES: return CorePackage.CORE_MODEL__REALIZES;
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
				case UrncorePackage.IURN_DIAGRAM__URNDEFINITION: return MapPackage.UC_MMAP__URNDEFINITION;
				case UrncorePackage.IURN_DIAGRAM__NODES: return MapPackage.UC_MMAP__NODES;
				case UrncorePackage.IURN_DIAGRAM__CONT_REFS: return MapPackage.UC_MMAP__CONT_REFS;
				case UrncorePackage.IURN_DIAGRAM__CONNECTIONS: return MapPackage.UC_MMAP__CONNECTIONS;
				case UrncorePackage.IURN_DIAGRAM__CONCERN: return MapPackage.UC_MMAP__CONCERN;
				case UrncorePackage.IURN_DIAGRAM__COMMENTS: return MapPackage.UC_MMAP__COMMENTS;
				default: return -1;
			}
		}
		if (baseClass == COREModel.class) {
			switch (baseFeatureID) {
				case CorePackage.CORE_MODEL__MODEL_REUSE: return MapPackage.UC_MMAP__MODEL_REUSE;
				case CorePackage.CORE_MODEL__MODEL_ELEMENTS: return MapPackage.UC_MMAP__MODEL_ELEMENTS;
				case CorePackage.CORE_MODEL__REALIZES: return MapPackage.UC_MMAP__REALIZES;
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
		result.append(" (singleton: ");
		result.append(singleton);
		result.append(')');
		return result.toString();
	}

} //UCMmapImpl
