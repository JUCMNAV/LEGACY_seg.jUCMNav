/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package seg.jUCMNav.model.ucm.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import seg.jUCMNav.model.ucm.Component;
import seg.jUCMNav.model.ucm.Fork;
import seg.jUCMNav.model.ucm.Path;
import seg.jUCMNav.model.ucm.UcmDiagram;
import seg.jUCMNav.model.ucm.UcmPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Diagram</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link seg.jUCMNav.model.ucm.impl.UcmDiagramImpl#getPaths <em>Paths</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.impl.UcmDiagramImpl#getForks <em>Forks</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.impl.UcmDiagramImpl#getComponents <em>Components</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UcmDiagramImpl extends EObjectImpl implements UcmDiagram {
	/**
	 * The cached value of the '{@link #getPaths() <em>Paths</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPaths()
	 * @generated
	 * @ordered
	 */
	protected EList paths = null;

	/**
	 * The cached value of the '{@link #getForks() <em>Forks</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getForks()
	 * @generated
	 * @ordered
	 */
	protected EList forks = null;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UcmDiagramImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return UcmPackage.eINSTANCE.getUcmDiagram();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getPaths() {
		if (paths == null) {
			paths = new EObjectContainmentWithInverseEList(Path.class, this, UcmPackage.UCM_DIAGRAM__PATHS, UcmPackage.PATH__DIAGRAM);
		}
		return paths;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getForks() {
		if (forks == null) {
			forks = new EObjectContainmentEList(Fork.class, this, UcmPackage.UCM_DIAGRAM__FORKS);
		}
		return forks;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getComponents() {
		if (components == null) {
			components = new EObjectContainmentWithInverseEList(Component.class, this, UcmPackage.UCM_DIAGRAM__COMPONENTS, UcmPackage.COMPONENT__DIAGRAM);
		}
		return components;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case UcmPackage.UCM_DIAGRAM__PATHS:
					return ((InternalEList)getPaths()).basicAdd(otherEnd, msgs);
				case UcmPackage.UCM_DIAGRAM__COMPONENTS:
					return ((InternalEList)getComponents()).basicAdd(otherEnd, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case UcmPackage.UCM_DIAGRAM__PATHS:
					return ((InternalEList)getPaths()).basicRemove(otherEnd, msgs);
				case UcmPackage.UCM_DIAGRAM__FORKS:
					return ((InternalEList)getForks()).basicRemove(otherEnd, msgs);
				case UcmPackage.UCM_DIAGRAM__COMPONENTS:
					return ((InternalEList)getComponents()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case UcmPackage.UCM_DIAGRAM__PATHS:
				return getPaths();
			case UcmPackage.UCM_DIAGRAM__FORKS:
				return getForks();
			case UcmPackage.UCM_DIAGRAM__COMPONENTS:
				return getComponents();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case UcmPackage.UCM_DIAGRAM__PATHS:
				getPaths().clear();
				getPaths().addAll((Collection)newValue);
				return;
			case UcmPackage.UCM_DIAGRAM__FORKS:
				getForks().clear();
				getForks().addAll((Collection)newValue);
				return;
			case UcmPackage.UCM_DIAGRAM__COMPONENTS:
				getComponents().clear();
				getComponents().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case UcmPackage.UCM_DIAGRAM__PATHS:
				getPaths().clear();
				return;
			case UcmPackage.UCM_DIAGRAM__FORKS:
				getForks().clear();
				return;
			case UcmPackage.UCM_DIAGRAM__COMPONENTS:
				getComponents().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case UcmPackage.UCM_DIAGRAM__PATHS:
				return paths != null && !paths.isEmpty();
			case UcmPackage.UCM_DIAGRAM__FORKS:
				return forks != null && !forks.isEmpty();
			case UcmPackage.UCM_DIAGRAM__COMPONENTS:
				return components != null && !components.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //UcmDiagramImpl
