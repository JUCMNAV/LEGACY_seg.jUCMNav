/**
 */
package asd.impl;

import asd.ASDelement;
import asd.ASDiagram;
import asd.AsdPackage;
import asd.Contradiction;
import asd.Outcome;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AS Delement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link asd.impl.ASDelementImpl#getDiagrams <em>Diagrams</em>}</li>
 *   <li>{@link asd.impl.ASDelementImpl#getParentElement <em>Parent Element</em>}</li>
 *   <li>{@link asd.impl.ASDelementImpl#getRefinedElements <em>Refined Elements</em>}</li>
 *   <li>{@link asd.impl.ASDelementImpl#getRequiredOutcomes <em>Required Outcomes</em>}</li>
 *   <li>{@link asd.impl.ASDelementImpl#getContradictions <em>Contradictions</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ASDelementImpl extends ASDmodelElementImpl implements ASDelement {
	/**
	 * The cached value of the '{@link #getDiagrams() <em>Diagrams</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiagrams()
	 * @generated
	 * @ordered
	 */
	protected EList diagrams;

	/**
	 * The cached value of the '{@link #getParentElement() <em>Parent Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentElement()
	 * @generated
	 * @ordered
	 */
	protected ASDelement parentElement;

	/**
	 * The cached value of the '{@link #getRefinedElements() <em>Refined Elements</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRefinedElements()
	 * @generated
	 * @ordered
	 */
	protected EList refinedElements;

	/**
	 * The cached value of the '{@link #getRequiredOutcomes() <em>Required Outcomes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRequiredOutcomes()
	 * @generated
	 * @ordered
	 */
	protected EList requiredOutcomes;

	/**
	 * The cached value of the '{@link #getContradictions() <em>Contradictions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContradictions()
	 * @generated
	 * @ordered
	 */
	protected EList contradictions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ASDelementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return AsdPackage.Literals.AS_DELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDiagrams() {
		if (diagrams == null) {
			diagrams = new EObjectWithInverseResolvingEList.ManyInverse(ASDiagram.class, this, AsdPackage.AS_DELEMENT__DIAGRAMS, AsdPackage.AS_DIAGRAM__ELEMENTS);
		}
		return diagrams;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASDelement getParentElement() {
		if (parentElement != null && parentElement.eIsProxy()) {
			InternalEObject oldParentElement = (InternalEObject)parentElement;
			parentElement = (ASDelement)eResolveProxy(oldParentElement);
			if (parentElement != oldParentElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AsdPackage.AS_DELEMENT__PARENT_ELEMENT, oldParentElement, parentElement));
			}
		}
		return parentElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASDelement basicGetParentElement() {
		return parentElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParentElement(ASDelement newParentElement, NotificationChain msgs) {
		ASDelement oldParentElement = parentElement;
		parentElement = newParentElement;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AsdPackage.AS_DELEMENT__PARENT_ELEMENT, oldParentElement, newParentElement);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentElement(ASDelement newParentElement) {
		if (newParentElement != parentElement) {
			NotificationChain msgs = null;
			if (parentElement != null)
				msgs = ((InternalEObject)parentElement).eInverseRemove(this, AsdPackage.AS_DELEMENT__REFINED_ELEMENTS, ASDelement.class, msgs);
			if (newParentElement != null)
				msgs = ((InternalEObject)newParentElement).eInverseAdd(this, AsdPackage.AS_DELEMENT__REFINED_ELEMENTS, ASDelement.class, msgs);
			msgs = basicSetParentElement(newParentElement, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsdPackage.AS_DELEMENT__PARENT_ELEMENT, newParentElement, newParentElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRefinedElements() {
		if (refinedElements == null) {
			refinedElements = new EObjectWithInverseResolvingEList(ASDelement.class, this, AsdPackage.AS_DELEMENT__REFINED_ELEMENTS, AsdPackage.AS_DELEMENT__PARENT_ELEMENT);
		}
		return refinedElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRequiredOutcomes() {
		if (requiredOutcomes == null) {
			requiredOutcomes = new EObjectWithInverseResolvingEList.ManyInverse(Outcome.class, this, AsdPackage.AS_DELEMENT__REQUIRED_OUTCOMES, AsdPackage.OUTCOME__ENABLED_ELEMENTS);
		}
		return requiredOutcomes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getContradictions() {
		if (contradictions == null) {
			contradictions = new EObjectWithInverseResolvingEList(Contradiction.class, this, AsdPackage.AS_DELEMENT__CONTRADICTIONS, AsdPackage.CONTRADICTION__CONSIDERED_AE);
		}
		return contradictions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AsdPackage.AS_DELEMENT__DIAGRAMS:
				return ((InternalEList)getDiagrams()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_DELEMENT__PARENT_ELEMENT:
				if (parentElement != null)
					msgs = ((InternalEObject)parentElement).eInverseRemove(this, AsdPackage.AS_DELEMENT__REFINED_ELEMENTS, ASDelement.class, msgs);
				return basicSetParentElement((ASDelement)otherEnd, msgs);
			case AsdPackage.AS_DELEMENT__REFINED_ELEMENTS:
				return ((InternalEList)getRefinedElements()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_DELEMENT__REQUIRED_OUTCOMES:
				return ((InternalEList)getRequiredOutcomes()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_DELEMENT__CONTRADICTIONS:
				return ((InternalEList)getContradictions()).basicAdd(otherEnd, msgs);
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
			case AsdPackage.AS_DELEMENT__DIAGRAMS:
				return ((InternalEList)getDiagrams()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_DELEMENT__PARENT_ELEMENT:
				return basicSetParentElement(null, msgs);
			case AsdPackage.AS_DELEMENT__REFINED_ELEMENTS:
				return ((InternalEList)getRefinedElements()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_DELEMENT__REQUIRED_OUTCOMES:
				return ((InternalEList)getRequiredOutcomes()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_DELEMENT__CONTRADICTIONS:
				return ((InternalEList)getContradictions()).basicRemove(otherEnd, msgs);
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
			case AsdPackage.AS_DELEMENT__DIAGRAMS:
				return getDiagrams();
			case AsdPackage.AS_DELEMENT__PARENT_ELEMENT:
				if (resolve) return getParentElement();
				return basicGetParentElement();
			case AsdPackage.AS_DELEMENT__REFINED_ELEMENTS:
				return getRefinedElements();
			case AsdPackage.AS_DELEMENT__REQUIRED_OUTCOMES:
				return getRequiredOutcomes();
			case AsdPackage.AS_DELEMENT__CONTRADICTIONS:
				return getContradictions();
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
			case AsdPackage.AS_DELEMENT__DIAGRAMS:
				getDiagrams().clear();
				getDiagrams().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_DELEMENT__PARENT_ELEMENT:
				setParentElement((ASDelement)newValue);
				return;
			case AsdPackage.AS_DELEMENT__REFINED_ELEMENTS:
				getRefinedElements().clear();
				getRefinedElements().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_DELEMENT__REQUIRED_OUTCOMES:
				getRequiredOutcomes().clear();
				getRequiredOutcomes().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_DELEMENT__CONTRADICTIONS:
				getContradictions().clear();
				getContradictions().addAll((Collection)newValue);
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
			case AsdPackage.AS_DELEMENT__DIAGRAMS:
				getDiagrams().clear();
				return;
			case AsdPackage.AS_DELEMENT__PARENT_ELEMENT:
				setParentElement((ASDelement)null);
				return;
			case AsdPackage.AS_DELEMENT__REFINED_ELEMENTS:
				getRefinedElements().clear();
				return;
			case AsdPackage.AS_DELEMENT__REQUIRED_OUTCOMES:
				getRequiredOutcomes().clear();
				return;
			case AsdPackage.AS_DELEMENT__CONTRADICTIONS:
				getContradictions().clear();
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
			case AsdPackage.AS_DELEMENT__DIAGRAMS:
				return diagrams != null && !diagrams.isEmpty();
			case AsdPackage.AS_DELEMENT__PARENT_ELEMENT:
				return parentElement != null;
			case AsdPackage.AS_DELEMENT__REFINED_ELEMENTS:
				return refinedElements != null && !refinedElements.isEmpty();
			case AsdPackage.AS_DELEMENT__REQUIRED_OUTCOMES:
				return requiredOutcomes != null && !requiredOutcomes.isEmpty();
			case AsdPackage.AS_DELEMENT__CONTRADICTIONS:
				return contradictions != null && !contradictions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ASDelementImpl
