/**
 */
package core.impl;

import core.COREFeature;
import core.COREImpactModelElement;
import core.COREInterface;
import core.COREModelElement;
import core.CorePackage;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CORE Interface</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link core.impl.COREInterfaceImpl#getSelectable <em>Selectable</em>}</li>
 *   <li>{@link core.impl.COREInterfaceImpl#getCustomizable <em>Customizable</em>}</li>
 *   <li>{@link core.impl.COREInterfaceImpl#getUsable <em>Usable</em>}</li>
 *   <li>{@link core.impl.COREInterfaceImpl#getImpacted <em>Impacted</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class COREInterfaceImpl extends EObjectImpl implements COREInterface {
	/**
	 * The cached value of the '{@link #getSelectable() <em>Selectable</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelectable()
	 * @generated
	 * @ordered
	 */
	protected EList selectable;

	/**
	 * The cached value of the '{@link #getCustomizable() <em>Customizable</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCustomizable()
	 * @generated
	 * @ordered
	 */
	protected EList customizable;

	/**
	 * The cached value of the '{@link #getUsable() <em>Usable</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsable()
	 * @generated
	 * @ordered
	 */
	protected EList usable;

	/**
	 * The cached value of the '{@link #getImpacted() <em>Impacted</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImpacted()
	 * @generated
	 * @ordered
	 */
	protected EList impacted;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected COREInterfaceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CorePackage.Literals.CORE_INTERFACE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getSelectable() {
		if (selectable == null) {
			selectable = new EObjectResolvingEList(COREFeature.class, this, CorePackage.CORE_INTERFACE__SELECTABLE);
		}
		return selectable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getCustomizable() {
		if (customizable == null) {
			customizable = new EObjectResolvingEList(COREModelElement.class, this, CorePackage.CORE_INTERFACE__CUSTOMIZABLE);
		}
		return customizable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getUsable() {
		if (usable == null) {
			usable = new EObjectResolvingEList(COREModelElement.class, this, CorePackage.CORE_INTERFACE__USABLE);
		}
		return usable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getImpacted() {
		if (impacted == null) {
			impacted = new EObjectResolvingEList(COREImpactModelElement.class, this, CorePackage.CORE_INTERFACE__IMPACTED);
		}
		return impacted;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CorePackage.CORE_INTERFACE__SELECTABLE:
				return getSelectable();
			case CorePackage.CORE_INTERFACE__CUSTOMIZABLE:
				return getCustomizable();
			case CorePackage.CORE_INTERFACE__USABLE:
				return getUsable();
			case CorePackage.CORE_INTERFACE__IMPACTED:
				return getImpacted();
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
			case CorePackage.CORE_INTERFACE__SELECTABLE:
				getSelectable().clear();
				getSelectable().addAll((Collection)newValue);
				return;
			case CorePackage.CORE_INTERFACE__CUSTOMIZABLE:
				getCustomizable().clear();
				getCustomizable().addAll((Collection)newValue);
				return;
			case CorePackage.CORE_INTERFACE__USABLE:
				getUsable().clear();
				getUsable().addAll((Collection)newValue);
				return;
			case CorePackage.CORE_INTERFACE__IMPACTED:
				getImpacted().clear();
				getImpacted().addAll((Collection)newValue);
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
			case CorePackage.CORE_INTERFACE__SELECTABLE:
				getSelectable().clear();
				return;
			case CorePackage.CORE_INTERFACE__CUSTOMIZABLE:
				getCustomizable().clear();
				return;
			case CorePackage.CORE_INTERFACE__USABLE:
				getUsable().clear();
				return;
			case CorePackage.CORE_INTERFACE__IMPACTED:
				getImpacted().clear();
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
			case CorePackage.CORE_INTERFACE__SELECTABLE:
				return selectable != null && !selectable.isEmpty();
			case CorePackage.CORE_INTERFACE__CUSTOMIZABLE:
				return customizable != null && !customizable.isEmpty();
			case CorePackage.CORE_INTERFACE__USABLE:
				return usable != null && !usable.isEmpty();
			case CorePackage.CORE_INTERFACE__IMPACTED:
				return impacted != null && !impacted.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //COREInterfaceImpl
