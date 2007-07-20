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
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import urn.URNspec;
import urn.UrnPackage;
import urncore.ComponentElement;
import urncore.Concern;
import urncore.IURNDiagram;
import urncore.Responsibility;
import urncore.URNdefinition;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>UR Ndefinition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urncore.impl.URNdefinitionImpl#getUrnspec <em>Urnspec</em>}</li>
 *   <li>{@link urncore.impl.URNdefinitionImpl#getResponsibilities <em>Responsibilities</em>}</li>
 *   <li>{@link urncore.impl.URNdefinitionImpl#getComponents <em>Components</em>}</li>
 *   <li>{@link urncore.impl.URNdefinitionImpl#getSpecDiagrams <em>Spec Diagrams</em>}</li>
 *   <li>{@link urncore.impl.URNdefinitionImpl#getConcerns <em>Concerns</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class URNdefinitionImpl extends EObjectImpl implements URNdefinition {
    /**
     * The cached value of the '{@link #getResponsibilities() <em>Responsibilities</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getResponsibilities()
     * @generated
     * @ordered
     */
    protected EList responsibilities = null;

    /**
     * The cached value of the '{@link #getComponents() <em>Components</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getComponents()
     * @generated
     * @ordered
     */
    protected EList components = null;

    /**
     * The cached value of the '{@link #getSpecDiagrams() <em>Spec Diagrams</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSpecDiagrams()
     * @generated
     * @ordered
     */
    protected EList specDiagrams = null;

    /**
     * The cached value of the '{@link #getConcerns() <em>Concerns</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getConcerns()
     * @generated
     * @ordered
     */
	protected EList concerns = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected URNdefinitionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return UrncorePackage.Literals.UR_NDEFINITION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public URNspec getUrnspec() {
        if (eContainerFeatureID != UrncorePackage.UR_NDEFINITION__URNSPEC) return null;
        return (URNspec)eContainer();
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetUrnspec(URNspec newUrnspec, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newUrnspec, UrncorePackage.UR_NDEFINITION__URNSPEC, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setUrnspec(URNspec newUrnspec) {
        if (newUrnspec != eInternalContainer() || (eContainerFeatureID != UrncorePackage.UR_NDEFINITION__URNSPEC && newUrnspec != null)) {
            if (EcoreUtil.isAncestor(this, newUrnspec))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newUrnspec != null)
                msgs = ((InternalEObject)newUrnspec).eInverseAdd(this, UrnPackage.UR_NSPEC__URNDEF, URNspec.class, msgs);
            msgs = basicSetUrnspec(newUrnspec, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.UR_NDEFINITION__URNSPEC, newUrnspec, newUrnspec));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getResponsibilities() {
        if (responsibilities == null) {
            responsibilities = new EObjectContainmentWithInverseEList(Responsibility.class, this, UrncorePackage.UR_NDEFINITION__RESPONSIBILITIES, UrncorePackage.RESPONSIBILITY__URNDEFINITION);
        }
        return responsibilities;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getComponents() {
        if (components == null) {
            components = new EObjectContainmentWithInverseEList(ComponentElement.class, this, UrncorePackage.UR_NDEFINITION__COMPONENTS, UrncorePackage.COMPONENT_ELEMENT__URNDEFINITION);
        }
        return components;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList getSpecDiagrams() {
        if (specDiagrams == null) {
            specDiagrams = new EObjectContainmentWithInverseEList(IURNDiagram.class, this, UrncorePackage.UR_NDEFINITION__SPEC_DIAGRAMS, UrncorePackage.IURN_DIAGRAM__URNDEFINITION);
        }
        return specDiagrams;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList getConcerns() {
        if (concerns == null) {
            concerns = new EObjectContainmentWithInverseEList(Concern.class, this, UrncorePackage.UR_NDEFINITION__CONCERNS, UrncorePackage.CONCERN__URNDEFINITION);
        }
        return concerns;
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case UrncorePackage.UR_NDEFINITION__URNSPEC:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetUrnspec((URNspec)otherEnd, msgs);
            case UrncorePackage.UR_NDEFINITION__RESPONSIBILITIES:
                return ((InternalEList)getResponsibilities()).basicAdd(otherEnd, msgs);
            case UrncorePackage.UR_NDEFINITION__COMPONENTS:
                return ((InternalEList)getComponents()).basicAdd(otherEnd, msgs);
            case UrncorePackage.UR_NDEFINITION__SPEC_DIAGRAMS:
                return ((InternalEList)getSpecDiagrams()).basicAdd(otherEnd, msgs);
            case UrncorePackage.UR_NDEFINITION__CONCERNS:
                return ((InternalEList)getConcerns()).basicAdd(otherEnd, msgs);
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
            case UrncorePackage.UR_NDEFINITION__URNSPEC:
                return basicSetUrnspec(null, msgs);
            case UrncorePackage.UR_NDEFINITION__RESPONSIBILITIES:
                return ((InternalEList)getResponsibilities()).basicRemove(otherEnd, msgs);
            case UrncorePackage.UR_NDEFINITION__COMPONENTS:
                return ((InternalEList)getComponents()).basicRemove(otherEnd, msgs);
            case UrncorePackage.UR_NDEFINITION__SPEC_DIAGRAMS:
                return ((InternalEList)getSpecDiagrams()).basicRemove(otherEnd, msgs);
            case UrncorePackage.UR_NDEFINITION__CONCERNS:
                return ((InternalEList)getConcerns()).basicRemove(otherEnd, msgs);
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
            case UrncorePackage.UR_NDEFINITION__URNSPEC:
                return eInternalContainer().eInverseRemove(this, UrnPackage.UR_NSPEC__URNDEF, URNspec.class, msgs);
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
            case UrncorePackage.UR_NDEFINITION__URNSPEC:
                return getUrnspec();
            case UrncorePackage.UR_NDEFINITION__RESPONSIBILITIES:
                return getResponsibilities();
            case UrncorePackage.UR_NDEFINITION__COMPONENTS:
                return getComponents();
            case UrncorePackage.UR_NDEFINITION__SPEC_DIAGRAMS:
                return getSpecDiagrams();
            case UrncorePackage.UR_NDEFINITION__CONCERNS:
                return getConcerns();
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
            case UrncorePackage.UR_NDEFINITION__URNSPEC:
                setUrnspec((URNspec)newValue);
                return;
            case UrncorePackage.UR_NDEFINITION__RESPONSIBILITIES:
                getResponsibilities().clear();
                getResponsibilities().addAll((Collection)newValue);
                return;
            case UrncorePackage.UR_NDEFINITION__COMPONENTS:
                getComponents().clear();
                getComponents().addAll((Collection)newValue);
                return;
            case UrncorePackage.UR_NDEFINITION__SPEC_DIAGRAMS:
                getSpecDiagrams().clear();
                getSpecDiagrams().addAll((Collection)newValue);
                return;
            case UrncorePackage.UR_NDEFINITION__CONCERNS:
                getConcerns().clear();
                getConcerns().addAll((Collection)newValue);
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
            case UrncorePackage.UR_NDEFINITION__URNSPEC:
                setUrnspec((URNspec)null);
                return;
            case UrncorePackage.UR_NDEFINITION__RESPONSIBILITIES:
                getResponsibilities().clear();
                return;
            case UrncorePackage.UR_NDEFINITION__COMPONENTS:
                getComponents().clear();
                return;
            case UrncorePackage.UR_NDEFINITION__SPEC_DIAGRAMS:
                getSpecDiagrams().clear();
                return;
            case UrncorePackage.UR_NDEFINITION__CONCERNS:
                getConcerns().clear();
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
            case UrncorePackage.UR_NDEFINITION__URNSPEC:
                return getUrnspec() != null;
            case UrncorePackage.UR_NDEFINITION__RESPONSIBILITIES:
                return responsibilities != null && !responsibilities.isEmpty();
            case UrncorePackage.UR_NDEFINITION__COMPONENTS:
                return components != null && !components.isEmpty();
            case UrncorePackage.UR_NDEFINITION__SPEC_DIAGRAMS:
                return specDiagrams != null && !specDiagrams.isEmpty();
            case UrncorePackage.UR_NDEFINITION__CONCERNS:
                return concerns != null && !concerns.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //URNdefinitionImpl
