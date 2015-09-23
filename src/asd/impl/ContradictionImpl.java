/**
 */
package asd.impl;

import asd.ASDelement;
import asd.ASDiagram;
import asd.AsdPackage;
import asd.Contradiction;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Contradiction</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link asd.impl.ContradictionImpl#getContradictingAEs <em>Contradicting AEs</em>}</li>
 *   <li>{@link asd.impl.ContradictionImpl#getConsideredAE <em>Considered AE</em>}</li>
 *   <li>{@link asd.impl.ContradictionImpl#getDiagram <em>Diagram</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContradictionImpl extends ASDmodelElementImpl implements Contradiction {
	/**
	 * The cached value of the '{@link #getContradictingAEs() <em>Contradicting AEs</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContradictingAEs()
	 * @generated
	 * @ordered
	 */
	protected EList contradictingAEs;

	/**
	 * The cached value of the '{@link #getConsideredAE() <em>Considered AE</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConsideredAE()
	 * @generated
	 * @ordered
	 */
	protected ASDelement consideredAE;

	/**
	 * The cached value of the '{@link #getDiagram() <em>Diagram</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiagram()
	 * @generated
	 * @ordered
	 */
	protected ASDiagram diagram;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContradictionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return AsdPackage.Literals.CONTRADICTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getContradictingAEs() {
		if (contradictingAEs == null) {
			contradictingAEs = new EObjectResolvingEList(ASDelement.class, this, AsdPackage.CONTRADICTION__CONTRADICTING_AES);
		}
		return contradictingAEs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASDelement getConsideredAE() {
		if (consideredAE != null && consideredAE.eIsProxy()) {
			InternalEObject oldConsideredAE = (InternalEObject)consideredAE;
			consideredAE = (ASDelement)eResolveProxy(oldConsideredAE);
			if (consideredAE != oldConsideredAE) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AsdPackage.CONTRADICTION__CONSIDERED_AE, oldConsideredAE, consideredAE));
			}
		}
		return consideredAE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASDelement basicGetConsideredAE() {
		return consideredAE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConsideredAE(ASDelement newConsideredAE, NotificationChain msgs) {
		ASDelement oldConsideredAE = consideredAE;
		consideredAE = newConsideredAE;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AsdPackage.CONTRADICTION__CONSIDERED_AE, oldConsideredAE, newConsideredAE);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConsideredAE(ASDelement newConsideredAE) {
		if (newConsideredAE != consideredAE) {
			NotificationChain msgs = null;
			if (consideredAE != null)
				msgs = ((InternalEObject)consideredAE).eInverseRemove(this, AsdPackage.AS_DELEMENT__CONTRADICTIONS, ASDelement.class, msgs);
			if (newConsideredAE != null)
				msgs = ((InternalEObject)newConsideredAE).eInverseAdd(this, AsdPackage.AS_DELEMENT__CONTRADICTIONS, ASDelement.class, msgs);
			msgs = basicSetConsideredAE(newConsideredAE, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsdPackage.CONTRADICTION__CONSIDERED_AE, newConsideredAE, newConsideredAE));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASDiagram getDiagram() {
		if (diagram != null && diagram.eIsProxy()) {
			InternalEObject oldDiagram = (InternalEObject)diagram;
			diagram = (ASDiagram)eResolveProxy(oldDiagram);
			if (diagram != oldDiagram) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AsdPackage.CONTRADICTION__DIAGRAM, oldDiagram, diagram));
			}
		}
		return diagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASDiagram basicGetDiagram() {
		return diagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDiagram(ASDiagram newDiagram, NotificationChain msgs) {
		ASDiagram oldDiagram = diagram;
		diagram = newDiagram;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AsdPackage.CONTRADICTION__DIAGRAM, oldDiagram, newDiagram);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDiagram(ASDiagram newDiagram) {
		if (newDiagram != diagram) {
			NotificationChain msgs = null;
			if (diagram != null)
				msgs = ((InternalEObject)diagram).eInverseRemove(this, AsdPackage.AS_DIAGRAM__CONTRADICTIONS, ASDiagram.class, msgs);
			if (newDiagram != null)
				msgs = ((InternalEObject)newDiagram).eInverseAdd(this, AsdPackage.AS_DIAGRAM__CONTRADICTIONS, ASDiagram.class, msgs);
			msgs = basicSetDiagram(newDiagram, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsdPackage.CONTRADICTION__DIAGRAM, newDiagram, newDiagram));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AsdPackage.CONTRADICTION__CONSIDERED_AE:
				if (consideredAE != null)
					msgs = ((InternalEObject)consideredAE).eInverseRemove(this, AsdPackage.AS_DELEMENT__CONTRADICTIONS, ASDelement.class, msgs);
				return basicSetConsideredAE((ASDelement)otherEnd, msgs);
			case AsdPackage.CONTRADICTION__DIAGRAM:
				if (diagram != null)
					msgs = ((InternalEObject)diagram).eInverseRemove(this, AsdPackage.AS_DIAGRAM__CONTRADICTIONS, ASDiagram.class, msgs);
				return basicSetDiagram((ASDiagram)otherEnd, msgs);
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
			case AsdPackage.CONTRADICTION__CONSIDERED_AE:
				return basicSetConsideredAE(null, msgs);
			case AsdPackage.CONTRADICTION__DIAGRAM:
				return basicSetDiagram(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AsdPackage.CONTRADICTION__CONTRADICTING_AES:
				return getContradictingAEs();
			case AsdPackage.CONTRADICTION__CONSIDERED_AE:
				if (resolve) return getConsideredAE();
				return basicGetConsideredAE();
			case AsdPackage.CONTRADICTION__DIAGRAM:
				if (resolve) return getDiagram();
				return basicGetDiagram();
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
			case AsdPackage.CONTRADICTION__CONTRADICTING_AES:
				getContradictingAEs().clear();
				getContradictingAEs().addAll((Collection)newValue);
				return;
			case AsdPackage.CONTRADICTION__CONSIDERED_AE:
				setConsideredAE((ASDelement)newValue);
				return;
			case AsdPackage.CONTRADICTION__DIAGRAM:
				setDiagram((ASDiagram)newValue);
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
			case AsdPackage.CONTRADICTION__CONTRADICTING_AES:
				getContradictingAEs().clear();
				return;
			case AsdPackage.CONTRADICTION__CONSIDERED_AE:
				setConsideredAE((ASDelement)null);
				return;
			case AsdPackage.CONTRADICTION__DIAGRAM:
				setDiagram((ASDiagram)null);
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
			case AsdPackage.CONTRADICTION__CONTRADICTING_AES:
				return contradictingAEs != null && !contradictingAEs.isEmpty();
			case AsdPackage.CONTRADICTION__CONSIDERED_AE:
				return consideredAE != null;
			case AsdPackage.CONTRADICTION__DIAGRAM:
				return diagram != null;
		}
		return super.eIsSet(featureID);
	}

} //ContradictionImpl
