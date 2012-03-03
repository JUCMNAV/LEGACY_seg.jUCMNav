/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.ElementLink;
import grl.GRLLinkableElement;
import grl.GrlPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import urncore.impl.GRLmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>GRL Linkable Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.GRLLinkableElementImpl#getLinksDest <em>Links Dest</em>}</li>
 *   <li>{@link grl.impl.GRLLinkableElementImpl#getLinksSrc <em>Links Src</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class GRLLinkableElementImpl extends GRLmodelElementImpl implements GRLLinkableElement {
	/**
	 * The cached value of the '{@link #getLinksDest() <em>Links Dest</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinksDest()
	 * @generated
	 * @ordered
	 */
	protected EList linksDest;

	/**
	 * The cached value of the '{@link #getLinksSrc() <em>Links Src</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLinksSrc()
	 * @generated
	 * @ordered
	 */
	protected EList linksSrc;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GRLLinkableElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return GrlPackage.Literals.GRL_LINKABLE_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getLinksDest() {
		if (linksDest == null) {
			linksDest = new EObjectWithInverseResolvingEList(ElementLink.class, this, GrlPackage.GRL_LINKABLE_ELEMENT__LINKS_DEST, GrlPackage.ELEMENT_LINK__DEST);
		}
		return linksDest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getLinksSrc() {
		if (linksSrc == null) {
			linksSrc = new EObjectWithInverseResolvingEList(ElementLink.class, this, GrlPackage.GRL_LINKABLE_ELEMENT__LINKS_SRC, GrlPackage.ELEMENT_LINK__SRC);
		}
		return linksSrc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GrlPackage.GRL_LINKABLE_ELEMENT__LINKS_DEST:
				return ((InternalEList)getLinksDest()).basicAdd(otherEnd, msgs);
			case GrlPackage.GRL_LINKABLE_ELEMENT__LINKS_SRC:
				return ((InternalEList)getLinksSrc()).basicAdd(otherEnd, msgs);
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
			case GrlPackage.GRL_LINKABLE_ELEMENT__LINKS_DEST:
				return ((InternalEList)getLinksDest()).basicRemove(otherEnd, msgs);
			case GrlPackage.GRL_LINKABLE_ELEMENT__LINKS_SRC:
				return ((InternalEList)getLinksSrc()).basicRemove(otherEnd, msgs);
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
			case GrlPackage.GRL_LINKABLE_ELEMENT__LINKS_DEST:
				return getLinksDest();
			case GrlPackage.GRL_LINKABLE_ELEMENT__LINKS_SRC:
				return getLinksSrc();
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
			case GrlPackage.GRL_LINKABLE_ELEMENT__LINKS_DEST:
				getLinksDest().clear();
				getLinksDest().addAll((Collection)newValue);
				return;
			case GrlPackage.GRL_LINKABLE_ELEMENT__LINKS_SRC:
				getLinksSrc().clear();
				getLinksSrc().addAll((Collection)newValue);
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
			case GrlPackage.GRL_LINKABLE_ELEMENT__LINKS_DEST:
				getLinksDest().clear();
				return;
			case GrlPackage.GRL_LINKABLE_ELEMENT__LINKS_SRC:
				getLinksSrc().clear();
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
			case GrlPackage.GRL_LINKABLE_ELEMENT__LINKS_DEST:
				return linksDest != null && !linksDest.isEmpty();
			case GrlPackage.GRL_LINKABLE_ELEMENT__LINKS_SRC:
				return linksSrc != null && !linksSrc.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //GRLLinkableElementImpl
