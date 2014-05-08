/**
 */
package core.impl;

import core.CORECompositionSpecification;
import core.COREConcern;
import core.COREFeature;
import core.COREReuse;
import core.CorePackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CORE Reuse</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link core.impl.COREReuseImpl#getReusedConcern <em>Reused Concern</em>}</li>
 *   <li>{@link core.impl.COREReuseImpl#getCompositions <em>Compositions</em>}</li>
 *   <li>{@link core.impl.COREReuseImpl#getSelected <em>Selected</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class COREReuseImpl extends EObjectImpl implements COREReuse {
	/**
	 * The cached value of the '{@link #getReusedConcern() <em>Reused Concern</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReusedConcern()
	 * @generated
	 * @ordered
	 */
	protected COREConcern reusedConcern;

	/**
	 * The cached value of the '{@link #getCompositions() <em>Compositions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompositions()
	 * @generated
	 * @ordered
	 */
	protected EList compositions;

	/**
	 * The cached value of the '{@link #getSelected() <em>Selected</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSelected()
	 * @generated
	 * @ordered
	 */
	protected EList selected;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected COREReuseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CorePackage.Literals.CORE_REUSE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public COREConcern getReusedConcern() {
		if (reusedConcern != null && reusedConcern.eIsProxy()) {
			InternalEObject oldReusedConcern = (InternalEObject)reusedConcern;
			reusedConcern = (COREConcern)eResolveProxy(oldReusedConcern);
			if (reusedConcern != oldReusedConcern) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.CORE_REUSE__REUSED_CONCERN, oldReusedConcern, reusedConcern));
			}
		}
		return reusedConcern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public COREConcern basicGetReusedConcern() {
		return reusedConcern;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReusedConcern(COREConcern newReusedConcern) {
		COREConcern oldReusedConcern = reusedConcern;
		reusedConcern = newReusedConcern;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CORE_REUSE__REUSED_CONCERN, oldReusedConcern, reusedConcern));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getCompositions() {
		if (compositions == null) {
			compositions = new EObjectResolvingEList(CORECompositionSpecification.class, this, CorePackage.CORE_REUSE__COMPOSITIONS);
		}
		return compositions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getSelected() {
		if (selected == null) {
			selected = new EObjectResolvingEList(COREFeature.class, this, CorePackage.CORE_REUSE__SELECTED);
		}
		return selected;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CorePackage.CORE_REUSE__REUSED_CONCERN:
				if (resolve) return getReusedConcern();
				return basicGetReusedConcern();
			case CorePackage.CORE_REUSE__COMPOSITIONS:
				return getCompositions();
			case CorePackage.CORE_REUSE__SELECTED:
				return getSelected();
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
			case CorePackage.CORE_REUSE__REUSED_CONCERN:
				setReusedConcern((COREConcern)newValue);
				return;
			case CorePackage.CORE_REUSE__COMPOSITIONS:
				getCompositions().clear();
				getCompositions().addAll((Collection)newValue);
				return;
			case CorePackage.CORE_REUSE__SELECTED:
				getSelected().clear();
				getSelected().addAll((Collection)newValue);
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
			case CorePackage.CORE_REUSE__REUSED_CONCERN:
				setReusedConcern((COREConcern)null);
				return;
			case CorePackage.CORE_REUSE__COMPOSITIONS:
				getCompositions().clear();
				return;
			case CorePackage.CORE_REUSE__SELECTED:
				getSelected().clear();
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
			case CorePackage.CORE_REUSE__REUSED_CONCERN:
				return reusedConcern != null;
			case CorePackage.CORE_REUSE__COMPOSITIONS:
				return compositions != null && !compositions.isEmpty();
			case CorePackage.CORE_REUSE__SELECTED:
				return selected != null && !selected.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //COREReuseImpl
