/**
 */
package asd.impl;

import asd.ASDiagram;
import asd.AsdPackage;
import asd.MediatedElement;
import asd.MediatingElement;
import asd.Mediation;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mediation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link asd.impl.MediationImpl#getMediatedBy <em>Mediated By</em>}</li>
 *   <li>{@link asd.impl.MediationImpl#getMediates <em>Mediates</em>}</li>
 *   <li>{@link asd.impl.MediationImpl#getRelevantASD <em>Relevant ASD</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MediationImpl extends ASDmodelElementImpl implements Mediation {
	/**
	 * The cached value of the '{@link #getMediatedBy() <em>Mediated By</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMediatedBy()
	 * @generated
	 * @ordered
	 */
	protected MediatingElement mediatedBy;

	/**
	 * The cached value of the '{@link #getMediates() <em>Mediates</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMediates()
	 * @generated
	 * @ordered
	 */
	protected EList mediates;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MediationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return AsdPackage.Literals.MEDIATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MediatingElement getMediatedBy() {
		if (mediatedBy != null && mediatedBy.eIsProxy()) {
			InternalEObject oldMediatedBy = (InternalEObject)mediatedBy;
			mediatedBy = (MediatingElement)eResolveProxy(oldMediatedBy);
			if (mediatedBy != oldMediatedBy) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AsdPackage.MEDIATION__MEDIATED_BY, oldMediatedBy, mediatedBy));
			}
		}
		return mediatedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MediatingElement basicGetMediatedBy() {
		return mediatedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMediatedBy(MediatingElement newMediatedBy, NotificationChain msgs) {
		MediatingElement oldMediatedBy = mediatedBy;
		mediatedBy = newMediatedBy;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AsdPackage.MEDIATION__MEDIATED_BY, oldMediatedBy, newMediatedBy);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMediatedBy(MediatingElement newMediatedBy) {
		if (newMediatedBy != mediatedBy) {
			NotificationChain msgs = null;
			if (mediatedBy != null)
				msgs = ((InternalEObject)mediatedBy).eInverseRemove(this, AsdPackage.MEDIATING_ELEMENT__MEDIATIONS, MediatingElement.class, msgs);
			if (newMediatedBy != null)
				msgs = ((InternalEObject)newMediatedBy).eInverseAdd(this, AsdPackage.MEDIATING_ELEMENT__MEDIATIONS, MediatingElement.class, msgs);
			msgs = basicSetMediatedBy(newMediatedBy, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsdPackage.MEDIATION__MEDIATED_BY, newMediatedBy, newMediatedBy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getMediates() {
		if (mediates == null) {
			mediates = new EObjectWithInverseResolvingEList.ManyInverse(MediatedElement.class, this, AsdPackage.MEDIATION__MEDIATES, AsdPackage.MEDIATED_ELEMENT__MEDIATIONS);
		}
		return mediates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASDiagram getRelevantASD() {
		if (eContainerFeatureID() != AsdPackage.MEDIATION__RELEVANT_ASD) return null;
		return (ASDiagram)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRelevantASD(ASDiagram newRelevantASD, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRelevantASD, AsdPackage.MEDIATION__RELEVANT_ASD, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRelevantASD(ASDiagram newRelevantASD) {
		if (newRelevantASD != eInternalContainer() || (eContainerFeatureID() != AsdPackage.MEDIATION__RELEVANT_ASD && newRelevantASD != null)) {
			if (EcoreUtil.isAncestor(this, newRelevantASD))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRelevantASD != null)
				msgs = ((InternalEObject)newRelevantASD).eInverseAdd(this, AsdPackage.AS_DIAGRAM__MEDIATIONS, ASDiagram.class, msgs);
			msgs = basicSetRelevantASD(newRelevantASD, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsdPackage.MEDIATION__RELEVANT_ASD, newRelevantASD, newRelevantASD));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AsdPackage.MEDIATION__MEDIATED_BY:
				if (mediatedBy != null)
					msgs = ((InternalEObject)mediatedBy).eInverseRemove(this, AsdPackage.MEDIATING_ELEMENT__MEDIATIONS, MediatingElement.class, msgs);
				return basicSetMediatedBy((MediatingElement)otherEnd, msgs);
			case AsdPackage.MEDIATION__MEDIATES:
				return ((InternalEList)getMediates()).basicAdd(otherEnd, msgs);
			case AsdPackage.MEDIATION__RELEVANT_ASD:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRelevantASD((ASDiagram)otherEnd, msgs);
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
			case AsdPackage.MEDIATION__MEDIATED_BY:
				return basicSetMediatedBy(null, msgs);
			case AsdPackage.MEDIATION__MEDIATES:
				return ((InternalEList)getMediates()).basicRemove(otherEnd, msgs);
			case AsdPackage.MEDIATION__RELEVANT_ASD:
				return basicSetRelevantASD(null, msgs);
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
			case AsdPackage.MEDIATION__RELEVANT_ASD:
				return eInternalContainer().eInverseRemove(this, AsdPackage.AS_DIAGRAM__MEDIATIONS, ASDiagram.class, msgs);
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
			case AsdPackage.MEDIATION__MEDIATED_BY:
				if (resolve) return getMediatedBy();
				return basicGetMediatedBy();
			case AsdPackage.MEDIATION__MEDIATES:
				return getMediates();
			case AsdPackage.MEDIATION__RELEVANT_ASD:
				return getRelevantASD();
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
			case AsdPackage.MEDIATION__MEDIATED_BY:
				setMediatedBy((MediatingElement)newValue);
				return;
			case AsdPackage.MEDIATION__MEDIATES:
				getMediates().clear();
				getMediates().addAll((Collection)newValue);
				return;
			case AsdPackage.MEDIATION__RELEVANT_ASD:
				setRelevantASD((ASDiagram)newValue);
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
			case AsdPackage.MEDIATION__MEDIATED_BY:
				setMediatedBy((MediatingElement)null);
				return;
			case AsdPackage.MEDIATION__MEDIATES:
				getMediates().clear();
				return;
			case AsdPackage.MEDIATION__RELEVANT_ASD:
				setRelevantASD((ASDiagram)null);
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
			case AsdPackage.MEDIATION__MEDIATED_BY:
				return mediatedBy != null;
			case AsdPackage.MEDIATION__MEDIATES:
				return mediates != null && !mediates.isEmpty();
			case AsdPackage.MEDIATION__RELEVANT_ASD:
				return getRelevantASD() != null;
		}
		return super.eIsSet(featureID);
	}

} //MediationImpl
