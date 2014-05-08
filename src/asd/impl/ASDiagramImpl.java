/**
 */
package asd.impl;

import asd.ASDelement;
import asd.ASDiagram;
import asd.ASDlayout;
import asd.AsdPackage;
import asd.DivisionOfLabour;
import asd.Mediation;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
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

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AS Diagram</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link asd.impl.ASDiagramImpl#getUrndefinition <em>Urndefinition</em>}</li>
 *   <li>{@link asd.impl.ASDiagramImpl#getNodes <em>Nodes</em>}</li>
 *   <li>{@link asd.impl.ASDiagramImpl#getContRefs <em>Cont Refs</em>}</li>
 *   <li>{@link asd.impl.ASDiagramImpl#getConnections <em>Connections</em>}</li>
 *   <li>{@link asd.impl.ASDiagramImpl#getConcern <em>Concern</em>}</li>
 *   <li>{@link asd.impl.ASDiagramImpl#getComments <em>Comments</em>}</li>
 *   <li>{@link asd.impl.ASDiagramImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link asd.impl.ASDiagramImpl#getASD <em>ASD</em>}</li>
 *   <li>{@link asd.impl.ASDiagramImpl#getRelatedASD <em>Related ASD</em>}</li>
 *   <li>{@link asd.impl.ASDiagramImpl#getMediations <em>Mediations</em>}</li>
 *   <li>{@link asd.impl.ASDiagramImpl#getAsdLayouts <em>Asd Layouts</em>}</li>
 *   <li>{@link asd.impl.ASDiagramImpl#getParentDoLs <em>Parent Do Ls</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ASDiagramImpl extends ASDmodelElementImpl implements ASDiagram {
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
	 * The cached value of the '{@link #getElements() <em>Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElements()
	 * @generated
	 * @ordered
	 */
	protected EList elements;

	/**
	 * The cached value of the '{@link #getASD() <em>ASD</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getASD()
	 * @generated
	 * @ordered
	 */
	protected EList asd;

	/**
	 * The cached value of the '{@link #getRelatedASD() <em>Related ASD</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelatedASD()
	 * @generated
	 * @ordered
	 */
	protected EList relatedASD;

	/**
	 * The cached value of the '{@link #getMediations() <em>Mediations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMediations()
	 * @generated
	 * @ordered
	 */
	protected EList mediations;

	/**
	 * The cached value of the '{@link #getAsdLayouts() <em>Asd Layouts</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAsdLayouts()
	 * @generated
	 * @ordered
	 */
	protected EList asdLayouts;

	/**
	 * The cached value of the '{@link #getParentDoLs() <em>Parent Do Ls</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentDoLs()
	 * @generated
	 * @ordered
	 */
	protected EList parentDoLs;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ASDiagramImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return AsdPackage.Literals.AS_DIAGRAM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public URNdefinition getUrndefinition() {
		if (eContainerFeatureID() != AsdPackage.AS_DIAGRAM__URNDEFINITION) return null;
		return (URNdefinition)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUrndefinition(URNdefinition newUrndefinition, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newUrndefinition, AsdPackage.AS_DIAGRAM__URNDEFINITION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUrndefinition(URNdefinition newUrndefinition) {
		if (newUrndefinition != eInternalContainer() || (eContainerFeatureID() != AsdPackage.AS_DIAGRAM__URNDEFINITION && newUrndefinition != null)) {
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsdPackage.AS_DIAGRAM__URNDEFINITION, newUrndefinition, newUrndefinition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getNodes() {
		if (nodes == null) {
			nodes = new EObjectContainmentWithInverseEList(IURNNode.class, this, AsdPackage.AS_DIAGRAM__NODES, UrncorePackage.IURN_NODE__DIAGRAM);
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
			contRefs = new EObjectContainmentWithInverseEList(IURNContainerRef.class, this, AsdPackage.AS_DIAGRAM__CONT_REFS, UrncorePackage.IURN_CONTAINER_REF__DIAGRAM);
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
			connections = new EObjectContainmentWithInverseEList(IURNConnection.class, this, AsdPackage.AS_DIAGRAM__CONNECTIONS, UrncorePackage.IURN_CONNECTION__DIAGRAM);
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AsdPackage.AS_DIAGRAM__CONCERN, oldConcern, concern));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AsdPackage.AS_DIAGRAM__CONCERN, oldConcern, newConcern);
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsdPackage.AS_DIAGRAM__CONCERN, newConcern, newConcern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getComments() {
		if (comments == null) {
			comments = new EObjectContainmentWithInverseEList(Comment.class, this, AsdPackage.AS_DIAGRAM__COMMENTS, UrncorePackage.COMMENT__DIAGRAM);
		}
		return comments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getElements() {
		if (elements == null) {
			elements = new EObjectWithInverseResolvingEList.ManyInverse(ASDelement.class, this, AsdPackage.AS_DIAGRAM__ELEMENTS, AsdPackage.AS_DELEMENT__DIAGRAMS);
		}
		return elements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getASD() {
		if (asd == null) {
			asd = new EObjectWithInverseResolvingEList.ManyInverse(ASDiagram.class, this, AsdPackage.AS_DIAGRAM__ASD, AsdPackage.AS_DIAGRAM__RELATED_ASD);
		}
		return asd;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRelatedASD() {
		if (relatedASD == null) {
			relatedASD = new EObjectWithInverseResolvingEList.ManyInverse(ASDiagram.class, this, AsdPackage.AS_DIAGRAM__RELATED_ASD, AsdPackage.AS_DIAGRAM__ASD);
		}
		return relatedASD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getMediations() {
		if (mediations == null) {
			mediations = new EObjectContainmentWithInverseEList(Mediation.class, this, AsdPackage.AS_DIAGRAM__MEDIATIONS, AsdPackage.MEDIATION__RELEVANT_ASD);
		}
		return mediations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getAsdLayouts() {
		if (asdLayouts == null) {
			asdLayouts = new EObjectWithInverseResolvingEList(ASDlayout.class, this, AsdPackage.AS_DIAGRAM__ASD_LAYOUTS, AsdPackage.AS_DLAYOUT__AS_DIAGRAM);
		}
		return asdLayouts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getParentDoLs() {
		if (parentDoLs == null) {
			parentDoLs = new EObjectWithInverseResolvingEList.ManyInverse(DivisionOfLabour.class, this, AsdPackage.AS_DIAGRAM__PARENT_DO_LS, AsdPackage.DIVISION_OF_LABOUR__REFINED_DIAGRAMS);
		}
		return parentDoLs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AsdPackage.AS_DIAGRAM__URNDEFINITION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetUrndefinition((URNdefinition)otherEnd, msgs);
			case AsdPackage.AS_DIAGRAM__NODES:
				return ((InternalEList)getNodes()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_DIAGRAM__CONT_REFS:
				return ((InternalEList)getContRefs()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_DIAGRAM__CONNECTIONS:
				return ((InternalEList)getConnections()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_DIAGRAM__CONCERN:
				if (concern != null)
					msgs = ((InternalEObject)concern).eInverseRemove(this, UrncorePackage.CONCERN__SPEC_DIAGRAMS, Concern.class, msgs);
				return basicSetConcern((Concern)otherEnd, msgs);
			case AsdPackage.AS_DIAGRAM__COMMENTS:
				return ((InternalEList)getComments()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_DIAGRAM__ELEMENTS:
				return ((InternalEList)getElements()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_DIAGRAM__ASD:
				return ((InternalEList)getASD()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_DIAGRAM__RELATED_ASD:
				return ((InternalEList)getRelatedASD()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_DIAGRAM__MEDIATIONS:
				return ((InternalEList)getMediations()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_DIAGRAM__ASD_LAYOUTS:
				return ((InternalEList)getAsdLayouts()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_DIAGRAM__PARENT_DO_LS:
				return ((InternalEList)getParentDoLs()).basicAdd(otherEnd, msgs);
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
			case AsdPackage.AS_DIAGRAM__URNDEFINITION:
				return basicSetUrndefinition(null, msgs);
			case AsdPackage.AS_DIAGRAM__NODES:
				return ((InternalEList)getNodes()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_DIAGRAM__CONT_REFS:
				return ((InternalEList)getContRefs()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_DIAGRAM__CONNECTIONS:
				return ((InternalEList)getConnections()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_DIAGRAM__CONCERN:
				return basicSetConcern(null, msgs);
			case AsdPackage.AS_DIAGRAM__COMMENTS:
				return ((InternalEList)getComments()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_DIAGRAM__ELEMENTS:
				return ((InternalEList)getElements()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_DIAGRAM__ASD:
				return ((InternalEList)getASD()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_DIAGRAM__RELATED_ASD:
				return ((InternalEList)getRelatedASD()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_DIAGRAM__MEDIATIONS:
				return ((InternalEList)getMediations()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_DIAGRAM__ASD_LAYOUTS:
				return ((InternalEList)getAsdLayouts()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_DIAGRAM__PARENT_DO_LS:
				return ((InternalEList)getParentDoLs()).basicRemove(otherEnd, msgs);
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
			case AsdPackage.AS_DIAGRAM__URNDEFINITION:
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
			case AsdPackage.AS_DIAGRAM__URNDEFINITION:
				return getUrndefinition();
			case AsdPackage.AS_DIAGRAM__NODES:
				return getNodes();
			case AsdPackage.AS_DIAGRAM__CONT_REFS:
				return getContRefs();
			case AsdPackage.AS_DIAGRAM__CONNECTIONS:
				return getConnections();
			case AsdPackage.AS_DIAGRAM__CONCERN:
				if (resolve) return getConcern();
				return basicGetConcern();
			case AsdPackage.AS_DIAGRAM__COMMENTS:
				return getComments();
			case AsdPackage.AS_DIAGRAM__ELEMENTS:
				return getElements();
			case AsdPackage.AS_DIAGRAM__ASD:
				return getASD();
			case AsdPackage.AS_DIAGRAM__RELATED_ASD:
				return getRelatedASD();
			case AsdPackage.AS_DIAGRAM__MEDIATIONS:
				return getMediations();
			case AsdPackage.AS_DIAGRAM__ASD_LAYOUTS:
				return getAsdLayouts();
			case AsdPackage.AS_DIAGRAM__PARENT_DO_LS:
				return getParentDoLs();
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
			case AsdPackage.AS_DIAGRAM__URNDEFINITION:
				setUrndefinition((URNdefinition)newValue);
				return;
			case AsdPackage.AS_DIAGRAM__NODES:
				getNodes().clear();
				getNodes().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_DIAGRAM__CONT_REFS:
				getContRefs().clear();
				getContRefs().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_DIAGRAM__CONNECTIONS:
				getConnections().clear();
				getConnections().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_DIAGRAM__CONCERN:
				setConcern((Concern)newValue);
				return;
			case AsdPackage.AS_DIAGRAM__COMMENTS:
				getComments().clear();
				getComments().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_DIAGRAM__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_DIAGRAM__ASD:
				getASD().clear();
				getASD().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_DIAGRAM__RELATED_ASD:
				getRelatedASD().clear();
				getRelatedASD().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_DIAGRAM__MEDIATIONS:
				getMediations().clear();
				getMediations().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_DIAGRAM__ASD_LAYOUTS:
				getAsdLayouts().clear();
				getAsdLayouts().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_DIAGRAM__PARENT_DO_LS:
				getParentDoLs().clear();
				getParentDoLs().addAll((Collection)newValue);
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
			case AsdPackage.AS_DIAGRAM__URNDEFINITION:
				setUrndefinition((URNdefinition)null);
				return;
			case AsdPackage.AS_DIAGRAM__NODES:
				getNodes().clear();
				return;
			case AsdPackage.AS_DIAGRAM__CONT_REFS:
				getContRefs().clear();
				return;
			case AsdPackage.AS_DIAGRAM__CONNECTIONS:
				getConnections().clear();
				return;
			case AsdPackage.AS_DIAGRAM__CONCERN:
				setConcern((Concern)null);
				return;
			case AsdPackage.AS_DIAGRAM__COMMENTS:
				getComments().clear();
				return;
			case AsdPackage.AS_DIAGRAM__ELEMENTS:
				getElements().clear();
				return;
			case AsdPackage.AS_DIAGRAM__ASD:
				getASD().clear();
				return;
			case AsdPackage.AS_DIAGRAM__RELATED_ASD:
				getRelatedASD().clear();
				return;
			case AsdPackage.AS_DIAGRAM__MEDIATIONS:
				getMediations().clear();
				return;
			case AsdPackage.AS_DIAGRAM__ASD_LAYOUTS:
				getAsdLayouts().clear();
				return;
			case AsdPackage.AS_DIAGRAM__PARENT_DO_LS:
				getParentDoLs().clear();
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
			case AsdPackage.AS_DIAGRAM__URNDEFINITION:
				return getUrndefinition() != null;
			case AsdPackage.AS_DIAGRAM__NODES:
				return nodes != null && !nodes.isEmpty();
			case AsdPackage.AS_DIAGRAM__CONT_REFS:
				return contRefs != null && !contRefs.isEmpty();
			case AsdPackage.AS_DIAGRAM__CONNECTIONS:
				return connections != null && !connections.isEmpty();
			case AsdPackage.AS_DIAGRAM__CONCERN:
				return concern != null;
			case AsdPackage.AS_DIAGRAM__COMMENTS:
				return comments != null && !comments.isEmpty();
			case AsdPackage.AS_DIAGRAM__ELEMENTS:
				return elements != null && !elements.isEmpty();
			case AsdPackage.AS_DIAGRAM__ASD:
				return asd != null && !asd.isEmpty();
			case AsdPackage.AS_DIAGRAM__RELATED_ASD:
				return relatedASD != null && !relatedASD.isEmpty();
			case AsdPackage.AS_DIAGRAM__MEDIATIONS:
				return mediations != null && !mediations.isEmpty();
			case AsdPackage.AS_DIAGRAM__ASD_LAYOUTS:
				return asdLayouts != null && !asdLayouts.isEmpty();
			case AsdPackage.AS_DIAGRAM__PARENT_DO_LS:
				return parentDoLs != null && !parentDoLs.isEmpty();
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
				case AsdPackage.AS_DIAGRAM__URNDEFINITION: return UrncorePackage.IURN_DIAGRAM__URNDEFINITION;
				case AsdPackage.AS_DIAGRAM__NODES: return UrncorePackage.IURN_DIAGRAM__NODES;
				case AsdPackage.AS_DIAGRAM__CONT_REFS: return UrncorePackage.IURN_DIAGRAM__CONT_REFS;
				case AsdPackage.AS_DIAGRAM__CONNECTIONS: return UrncorePackage.IURN_DIAGRAM__CONNECTIONS;
				case AsdPackage.AS_DIAGRAM__CONCERN: return UrncorePackage.IURN_DIAGRAM__CONCERN;
				case AsdPackage.AS_DIAGRAM__COMMENTS: return UrncorePackage.IURN_DIAGRAM__COMMENTS;
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
				case UrncorePackage.IURN_DIAGRAM__URNDEFINITION: return AsdPackage.AS_DIAGRAM__URNDEFINITION;
				case UrncorePackage.IURN_DIAGRAM__NODES: return AsdPackage.AS_DIAGRAM__NODES;
				case UrncorePackage.IURN_DIAGRAM__CONT_REFS: return AsdPackage.AS_DIAGRAM__CONT_REFS;
				case UrncorePackage.IURN_DIAGRAM__CONNECTIONS: return AsdPackage.AS_DIAGRAM__CONNECTIONS;
				case UrncorePackage.IURN_DIAGRAM__CONCERN: return AsdPackage.AS_DIAGRAM__CONCERN;
				case UrncorePackage.IURN_DIAGRAM__COMMENTS: return AsdPackage.AS_DIAGRAM__COMMENTS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //ASDiagramImpl
