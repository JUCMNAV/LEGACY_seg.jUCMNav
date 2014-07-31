/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.impl;

import ca.mcgill.sel.core.COREConcern;
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
import urncore.Concern;
import urncore.Condition;
import urncore.IURNDiagram;
import urncore.URNdefinition;
import urncore.URNmodelElement;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Concern</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urncore.impl.ConcernImpl#getUrndefinition <em>Urndefinition</em>}</li>
 *   <li>{@link urncore.impl.ConcernImpl#getSpecDiagrams <em>Spec Diagrams</em>}</li>
 *   <li>{@link urncore.impl.ConcernImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link urncore.impl.ConcernImpl#getCoreConcern <em>Core Concern</em>}</li>
 *   <li>{@link urncore.impl.ConcernImpl#getCondition <em>Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConcernImpl extends URNmodelElementImpl implements Concern {
    /**
	 * The cached value of the '{@link #getSpecDiagrams() <em>Spec Diagrams</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecDiagrams()
	 * @generated
	 * @ordered
	 */
	protected EList specDiagrams;

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
	 * The cached value of the '{@link #getCoreConcern() <em>Core Concern</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoreConcern()
	 * @generated
	 * @ordered
	 */
	protected COREConcern coreConcern;

				/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected Condition condition;

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConcernImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return UrncorePackage.Literals.CONCERN;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public URNdefinition getUrndefinition() {
		if (eContainerFeatureID() != UrncorePackage.CONCERN__URNDEFINITION) return null;
		return (URNdefinition)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUrndefinition(URNdefinition newUrndefinition, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newUrndefinition, UrncorePackage.CONCERN__URNDEFINITION, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUrndefinition(URNdefinition newUrndefinition) {
		if (newUrndefinition != eInternalContainer() || (eContainerFeatureID() != UrncorePackage.CONCERN__URNDEFINITION && newUrndefinition != null)) {
			if (EcoreUtil.isAncestor(this, newUrndefinition))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newUrndefinition != null)
				msgs = ((InternalEObject)newUrndefinition).eInverseAdd(this, UrncorePackage.UR_NDEFINITION__CONCERNS, URNdefinition.class, msgs);
			msgs = basicSetUrndefinition(newUrndefinition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.CONCERN__URNDEFINITION, newUrndefinition, newUrndefinition));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getSpecDiagrams() {
		if (specDiagrams == null) {
			specDiagrams = new EObjectWithInverseResolvingEList(IURNDiagram.class, this, UrncorePackage.CONCERN__SPEC_DIAGRAMS, UrncorePackage.IURN_DIAGRAM__CONCERN);
		}
		return specDiagrams;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getElements() {
		if (elements == null) {
			elements = new EObjectWithInverseResolvingEList(URNmodelElement.class, this, UrncorePackage.CONCERN__ELEMENTS, UrncorePackage.UR_NMODEL_ELEMENT__INCONCERN);
		}
		return elements;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public COREConcern getCoreConcern() {
		if (coreConcern != null && coreConcern.eIsProxy()) {
			InternalEObject oldCoreConcern = (InternalEObject)coreConcern;
			coreConcern = (COREConcern)eResolveProxy(oldCoreConcern);
			if (coreConcern != oldCoreConcern) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UrncorePackage.CONCERN__CORE_CONCERN, oldCoreConcern, coreConcern));
			}
		}
		return coreConcern;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public COREConcern basicGetCoreConcern() {
		return coreConcern;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCoreConcern(COREConcern newCoreConcern) {
		COREConcern oldCoreConcern = coreConcern;
		coreConcern = newCoreConcern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.CONCERN__CORE_CONCERN, oldCoreConcern, coreConcern));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Condition getCondition() {
		return condition;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCondition(Condition newCondition, NotificationChain msgs) {
		Condition oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UrncorePackage.CONCERN__CONDITION, oldCondition, newCondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCondition(Condition newCondition) {
		if (newCondition != condition) {
			NotificationChain msgs = null;
			if (condition != null)
				msgs = ((InternalEObject)condition).eInverseRemove(this, UrncorePackage.CONDITION__CONCERN, Condition.class, msgs);
			if (newCondition != null)
				msgs = ((InternalEObject)newCondition).eInverseAdd(this, UrncorePackage.CONDITION__CONCERN, Condition.class, msgs);
			msgs = basicSetCondition(newCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.CONCERN__CONDITION, newCondition, newCondition));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UrncorePackage.CONCERN__URNDEFINITION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetUrndefinition((URNdefinition)otherEnd, msgs);
			case UrncorePackage.CONCERN__SPEC_DIAGRAMS:
				return ((InternalEList)getSpecDiagrams()).basicAdd(otherEnd, msgs);
			case UrncorePackage.CONCERN__ELEMENTS:
				return ((InternalEList)getElements()).basicAdd(otherEnd, msgs);
			case UrncorePackage.CONCERN__CONDITION:
				if (condition != null)
					msgs = ((InternalEObject)condition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - UrncorePackage.CONCERN__CONDITION, null, msgs);
				return basicSetCondition((Condition)otherEnd, msgs);
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
			case UrncorePackage.CONCERN__URNDEFINITION:
				return basicSetUrndefinition(null, msgs);
			case UrncorePackage.CONCERN__SPEC_DIAGRAMS:
				return ((InternalEList)getSpecDiagrams()).basicRemove(otherEnd, msgs);
			case UrncorePackage.CONCERN__ELEMENTS:
				return ((InternalEList)getElements()).basicRemove(otherEnd, msgs);
			case UrncorePackage.CONCERN__CONDITION:
				return basicSetCondition(null, msgs);
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
			case UrncorePackage.CONCERN__URNDEFINITION:
				return eInternalContainer().eInverseRemove(this, UrncorePackage.UR_NDEFINITION__CONCERNS, URNdefinition.class, msgs);
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
			case UrncorePackage.CONCERN__URNDEFINITION:
				return getUrndefinition();
			case UrncorePackage.CONCERN__SPEC_DIAGRAMS:
				return getSpecDiagrams();
			case UrncorePackage.CONCERN__ELEMENTS:
				return getElements();
			case UrncorePackage.CONCERN__CORE_CONCERN:
				if (resolve) return getCoreConcern();
				return basicGetCoreConcern();
			case UrncorePackage.CONCERN__CONDITION:
				return getCondition();
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
			case UrncorePackage.CONCERN__URNDEFINITION:
				setUrndefinition((URNdefinition)newValue);
				return;
			case UrncorePackage.CONCERN__SPEC_DIAGRAMS:
				getSpecDiagrams().clear();
				getSpecDiagrams().addAll((Collection)newValue);
				return;
			case UrncorePackage.CONCERN__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection)newValue);
				return;
			case UrncorePackage.CONCERN__CORE_CONCERN:
				setCoreConcern((COREConcern)newValue);
				return;
			case UrncorePackage.CONCERN__CONDITION:
				setCondition((Condition)newValue);
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
			case UrncorePackage.CONCERN__URNDEFINITION:
				setUrndefinition((URNdefinition)null);
				return;
			case UrncorePackage.CONCERN__SPEC_DIAGRAMS:
				getSpecDiagrams().clear();
				return;
			case UrncorePackage.CONCERN__ELEMENTS:
				getElements().clear();
				return;
			case UrncorePackage.CONCERN__CORE_CONCERN:
				setCoreConcern((COREConcern)null);
				return;
			case UrncorePackage.CONCERN__CONDITION:
				setCondition((Condition)null);
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
			case UrncorePackage.CONCERN__URNDEFINITION:
				return getUrndefinition() != null;
			case UrncorePackage.CONCERN__SPEC_DIAGRAMS:
				return specDiagrams != null && !specDiagrams.isEmpty();
			case UrncorePackage.CONCERN__ELEMENTS:
				return elements != null && !elements.isEmpty();
			case UrncorePackage.CONCERN__CORE_CONCERN:
				return coreConcern != null;
			case UrncorePackage.CONCERN__CONDITION:
				return condition != null;
		}
		return super.eIsSet(featureID);
	}

} //ConcernImpl