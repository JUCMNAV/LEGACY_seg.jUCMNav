/**
 */
package asd.impl;

import asd.ASDiagram;
import asd.ASDspec;
import asd.AsdPackage;
import asd.Community;
import asd.DivisionOfLabour;
import asd.Rule;
import asd.Tool;

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
 * An implementation of the model object '<em><b>Division Of Labour</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link asd.impl.DivisionOfLabourImpl#getRules <em>Rules</em>}</li>
 *   <li>{@link asd.impl.DivisionOfLabourImpl#getRefinedDiagrams <em>Refined Diagrams</em>}</li>
 *   <li>{@link asd.impl.DivisionOfLabourImpl#getPerformedBy <em>Performed By</em>}</li>
 *   <li>{@link asd.impl.DivisionOfLabourImpl#getAsdSpec <em>Asd Spec</em>}</li>
 *   <li>{@link asd.impl.DivisionOfLabourImpl#getTools <em>Tools</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DivisionOfLabourImpl extends MediatingElementImpl implements DivisionOfLabour {
	/**
	 * The cached value of the '{@link #getRules() <em>Rules</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRules()
	 * @generated
	 * @ordered
	 */
	protected EList rules;

	/**
	 * The cached value of the '{@link #getRefinedDiagrams() <em>Refined Diagrams</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRefinedDiagrams()
	 * @generated
	 * @ordered
	 */
	protected EList refinedDiagrams;

	/**
	 * The cached value of the '{@link #getPerformedBy() <em>Performed By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerformedBy()
	 * @generated
	 * @ordered
	 */
	protected EList performedBy;

	/**
	 * The cached value of the '{@link #getTools() <em>Tools</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTools()
	 * @generated
	 * @ordered
	 */
	protected EList tools;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DivisionOfLabourImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return AsdPackage.Literals.DIVISION_OF_LABOUR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRules() {
		if (rules == null) {
			rules = new EObjectWithInverseResolvingEList.ManyInverse(Rule.class, this, AsdPackage.DIVISION_OF_LABOUR__RULES, AsdPackage.RULE__DOLS);
		}
		return rules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRefinedDiagrams() {
		if (refinedDiagrams == null) {
			refinedDiagrams = new EObjectWithInverseResolvingEList.ManyInverse(ASDiagram.class, this, AsdPackage.DIVISION_OF_LABOUR__REFINED_DIAGRAMS, AsdPackage.AS_DIAGRAM__PARENT_DO_LS);
		}
		return refinedDiagrams;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPerformedBy() {
		if (performedBy == null) {
			performedBy = new EObjectWithInverseResolvingEList.ManyInverse(Community.class, this, AsdPackage.DIVISION_OF_LABOUR__PERFORMED_BY, AsdPackage.COMMUNITY__PERFORMS);
		}
		return performedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASDspec getAsdSpec() {
		if (eContainerFeatureID() != AsdPackage.DIVISION_OF_LABOUR__ASD_SPEC) return null;
		return (ASDspec)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAsdSpec(ASDspec newAsdSpec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newAsdSpec, AsdPackage.DIVISION_OF_LABOUR__ASD_SPEC, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAsdSpec(ASDspec newAsdSpec) {
		if (newAsdSpec != eInternalContainer() || (eContainerFeatureID() != AsdPackage.DIVISION_OF_LABOUR__ASD_SPEC && newAsdSpec != null)) {
			if (EcoreUtil.isAncestor(this, newAsdSpec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newAsdSpec != null)
				msgs = ((InternalEObject)newAsdSpec).eInverseAdd(this, AsdPackage.AS_DSPEC__DOLS, ASDspec.class, msgs);
			msgs = basicSetAsdSpec(newAsdSpec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsdPackage.DIVISION_OF_LABOUR__ASD_SPEC, newAsdSpec, newAsdSpec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTools() {
		if (tools == null) {
			tools = new EObjectWithInverseResolvingEList.ManyInverse(Tool.class, this, AsdPackage.DIVISION_OF_LABOUR__TOOLS, AsdPackage.TOOL__DOLS);
		}
		return tools;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AsdPackage.DIVISION_OF_LABOUR__RULES:
				return ((InternalEList)getRules()).basicAdd(otherEnd, msgs);
			case AsdPackage.DIVISION_OF_LABOUR__REFINED_DIAGRAMS:
				return ((InternalEList)getRefinedDiagrams()).basicAdd(otherEnd, msgs);
			case AsdPackage.DIVISION_OF_LABOUR__PERFORMED_BY:
				return ((InternalEList)getPerformedBy()).basicAdd(otherEnd, msgs);
			case AsdPackage.DIVISION_OF_LABOUR__ASD_SPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetAsdSpec((ASDspec)otherEnd, msgs);
			case AsdPackage.DIVISION_OF_LABOUR__TOOLS:
				return ((InternalEList)getTools()).basicAdd(otherEnd, msgs);
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
			case AsdPackage.DIVISION_OF_LABOUR__RULES:
				return ((InternalEList)getRules()).basicRemove(otherEnd, msgs);
			case AsdPackage.DIVISION_OF_LABOUR__REFINED_DIAGRAMS:
				return ((InternalEList)getRefinedDiagrams()).basicRemove(otherEnd, msgs);
			case AsdPackage.DIVISION_OF_LABOUR__PERFORMED_BY:
				return ((InternalEList)getPerformedBy()).basicRemove(otherEnd, msgs);
			case AsdPackage.DIVISION_OF_LABOUR__ASD_SPEC:
				return basicSetAsdSpec(null, msgs);
			case AsdPackage.DIVISION_OF_LABOUR__TOOLS:
				return ((InternalEList)getTools()).basicRemove(otherEnd, msgs);
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
			case AsdPackage.DIVISION_OF_LABOUR__ASD_SPEC:
				return eInternalContainer().eInverseRemove(this, AsdPackage.AS_DSPEC__DOLS, ASDspec.class, msgs);
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
			case AsdPackage.DIVISION_OF_LABOUR__RULES:
				return getRules();
			case AsdPackage.DIVISION_OF_LABOUR__REFINED_DIAGRAMS:
				return getRefinedDiagrams();
			case AsdPackage.DIVISION_OF_LABOUR__PERFORMED_BY:
				return getPerformedBy();
			case AsdPackage.DIVISION_OF_LABOUR__ASD_SPEC:
				return getAsdSpec();
			case AsdPackage.DIVISION_OF_LABOUR__TOOLS:
				return getTools();
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
			case AsdPackage.DIVISION_OF_LABOUR__RULES:
				getRules().clear();
				getRules().addAll((Collection)newValue);
				return;
			case AsdPackage.DIVISION_OF_LABOUR__REFINED_DIAGRAMS:
				getRefinedDiagrams().clear();
				getRefinedDiagrams().addAll((Collection)newValue);
				return;
			case AsdPackage.DIVISION_OF_LABOUR__PERFORMED_BY:
				getPerformedBy().clear();
				getPerformedBy().addAll((Collection)newValue);
				return;
			case AsdPackage.DIVISION_OF_LABOUR__ASD_SPEC:
				setAsdSpec((ASDspec)newValue);
				return;
			case AsdPackage.DIVISION_OF_LABOUR__TOOLS:
				getTools().clear();
				getTools().addAll((Collection)newValue);
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
			case AsdPackage.DIVISION_OF_LABOUR__RULES:
				getRules().clear();
				return;
			case AsdPackage.DIVISION_OF_LABOUR__REFINED_DIAGRAMS:
				getRefinedDiagrams().clear();
				return;
			case AsdPackage.DIVISION_OF_LABOUR__PERFORMED_BY:
				getPerformedBy().clear();
				return;
			case AsdPackage.DIVISION_OF_LABOUR__ASD_SPEC:
				setAsdSpec((ASDspec)null);
				return;
			case AsdPackage.DIVISION_OF_LABOUR__TOOLS:
				getTools().clear();
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
			case AsdPackage.DIVISION_OF_LABOUR__RULES:
				return rules != null && !rules.isEmpty();
			case AsdPackage.DIVISION_OF_LABOUR__REFINED_DIAGRAMS:
				return refinedDiagrams != null && !refinedDiagrams.isEmpty();
			case AsdPackage.DIVISION_OF_LABOUR__PERFORMED_BY:
				return performedBy != null && !performedBy.isEmpty();
			case AsdPackage.DIVISION_OF_LABOUR__ASD_SPEC:
				return getAsdSpec() != null;
			case AsdPackage.DIVISION_OF_LABOUR__TOOLS:
				return tools != null && !tools.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DivisionOfLabourImpl
