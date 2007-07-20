/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.impl;

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
import urncore.IURNDiagram;
import urncore.URNdefinition;
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
	protected EList specDiagrams = null;

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
        if (eContainerFeatureID != UrncorePackage.CONCERN__URNDEFINITION) return null;
        return (URNdefinition)eContainer();
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
        if (newUrndefinition != eInternalContainer() || (eContainerFeatureID != UrncorePackage.CONCERN__URNDEFINITION && newUrndefinition != null)) {
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
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case UrncorePackage.CONCERN__URNDEFINITION:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetUrndefinition((URNdefinition)otherEnd, msgs);
            case UrncorePackage.CONCERN__SPEC_DIAGRAMS:
                return ((InternalEList)getSpecDiagrams()).basicAdd(otherEnd, msgs);
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
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID) {
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
        }
        return super.eIsSet(featureID);
    }

} //ConcernImpl