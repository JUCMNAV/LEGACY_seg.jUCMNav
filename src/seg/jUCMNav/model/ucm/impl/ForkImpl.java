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

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import seg.jUCMNav.model.ucm.Fork;
import seg.jUCMNav.model.ucm.Path;
import seg.jUCMNav.model.ucm.UcmPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fork</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link seg.jUCMNav.model.ucm.impl.ForkImpl#getInPaths <em>In Paths</em>}</li>
 *   <li>{@link seg.jUCMNav.model.ucm.impl.ForkImpl#getOutPath <em>Out Path</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ForkImpl extends EObjectImpl implements Fork {
	/**
	 * The cached value of the '{@link #getInPaths() <em>In Paths</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInPaths()
	 * @generated
	 * @ordered
	 */
	protected EList inPaths = null;

	/**
	 * The cached value of the '{@link #getOutPath() <em>Out Path</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutPath()
	 * @generated
	 * @ordered
	 */
	protected EList outPath = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ForkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return UcmPackage.eINSTANCE.getFork();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getInPaths() {
		if (inPaths == null) {
			inPaths = new EObjectContainmentWithInverseEList(Path.class, this, UcmPackage.FORK__IN_PATHS, UcmPackage.PATH__IN_FORK);
		}
		return inPaths;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getOutPath() {
		if (outPath == null) {
			outPath = new EObjectContainmentWithInverseEList(Path.class, this, UcmPackage.FORK__OUT_PATH, UcmPackage.PATH__OUT_FORK);
		}
		return outPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case UcmPackage.FORK__IN_PATHS:
					return ((InternalEList)getInPaths()).basicAdd(otherEnd, msgs);
				case UcmPackage.FORK__OUT_PATH:
					return ((InternalEList)getOutPath()).basicAdd(otherEnd, msgs);
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
				case UcmPackage.FORK__IN_PATHS:
					return ((InternalEList)getInPaths()).basicRemove(otherEnd, msgs);
				case UcmPackage.FORK__OUT_PATH:
					return ((InternalEList)getOutPath()).basicRemove(otherEnd, msgs);
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
			case UcmPackage.FORK__IN_PATHS:
				return getInPaths();
			case UcmPackage.FORK__OUT_PATH:
				return getOutPath();
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
			case UcmPackage.FORK__IN_PATHS:
				getInPaths().clear();
				getInPaths().addAll((Collection)newValue);
				return;
			case UcmPackage.FORK__OUT_PATH:
				getOutPath().clear();
				getOutPath().addAll((Collection)newValue);
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
			case UcmPackage.FORK__IN_PATHS:
				getInPaths().clear();
				return;
			case UcmPackage.FORK__OUT_PATH:
				getOutPath().clear();
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
			case UcmPackage.FORK__IN_PATHS:
				return inPaths != null && !inPaths.isEmpty();
			case UcmPackage.FORK__OUT_PATH:
				return outPath != null && !outPath.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

} //ForkImpl
