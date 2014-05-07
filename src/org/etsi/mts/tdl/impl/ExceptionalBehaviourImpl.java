/**
 */
package org.etsi.mts.tdl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.etsi.mts.tdl.Block;
import org.etsi.mts.tdl.ComponentInstance;
import org.etsi.mts.tdl.ExceptionalBehaviour;
import org.etsi.mts.tdl.TdlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Exceptional Behaviour</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.etsi.mts.tdl.impl.ExceptionalBehaviourImpl#getBlock <em>Block</em>}</li>
 *   <li>{@link org.etsi.mts.tdl.impl.ExceptionalBehaviourImpl#getGuardedComponent <em>Guarded Component</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ExceptionalBehaviourImpl extends BehaviourImpl implements ExceptionalBehaviour {
	/**
	 * The cached value of the '{@link #getBlock() <em>Block</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBlock()
	 * @generated
	 * @ordered
	 */
	protected Block block;

	/**
	 * The cached value of the '{@link #getGuardedComponent() <em>Guarded Component</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGuardedComponent()
	 * @generated
	 * @ordered
	 */
	protected ComponentInstance guardedComponent;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExceptionalBehaviourImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TdlPackage.Literals.EXCEPTIONAL_BEHAVIOUR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block getBlock() {
		return block;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBlock(Block newBlock, NotificationChain msgs) {
		Block oldBlock = block;
		block = newBlock;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TdlPackage.EXCEPTIONAL_BEHAVIOUR__BLOCK, oldBlock, newBlock);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBlock(Block newBlock) {
		if (newBlock != block) {
			NotificationChain msgs = null;
			if (block != null)
				msgs = ((InternalEObject)block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TdlPackage.EXCEPTIONAL_BEHAVIOUR__BLOCK, null, msgs);
			if (newBlock != null)
				msgs = ((InternalEObject)newBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TdlPackage.EXCEPTIONAL_BEHAVIOUR__BLOCK, null, msgs);
			msgs = basicSetBlock(newBlock, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TdlPackage.EXCEPTIONAL_BEHAVIOUR__BLOCK, newBlock, newBlock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block createBlock() {
		Block newBlock = (Block) create(TdlPackage.Literals.BLOCK);
		setBlock(newBlock);
		return newBlock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentInstance getGuardedComponent() {
		if (guardedComponent != null && guardedComponent.eIsProxy()) {
			InternalEObject oldGuardedComponent = (InternalEObject)guardedComponent;
			guardedComponent = (ComponentInstance)eResolveProxy(oldGuardedComponent);
			if (guardedComponent != oldGuardedComponent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TdlPackage.EXCEPTIONAL_BEHAVIOUR__GUARDED_COMPONENT, oldGuardedComponent, guardedComponent));
			}
		}
		return guardedComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComponentInstance basicGetGuardedComponent() {
		return guardedComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGuardedComponent(ComponentInstance newGuardedComponent) {
		ComponentInstance oldGuardedComponent = guardedComponent;
		guardedComponent = newGuardedComponent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TdlPackage.EXCEPTIONAL_BEHAVIOUR__GUARDED_COMPONENT, oldGuardedComponent, guardedComponent));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TdlPackage.EXCEPTIONAL_BEHAVIOUR__BLOCK:
				return basicSetBlock(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TdlPackage.EXCEPTIONAL_BEHAVIOUR__BLOCK:
				return getBlock();
			case TdlPackage.EXCEPTIONAL_BEHAVIOUR__GUARDED_COMPONENT:
				if (resolve) return getGuardedComponent();
				return basicGetGuardedComponent();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TdlPackage.EXCEPTIONAL_BEHAVIOUR__BLOCK:
				setBlock((Block)newValue);
				return;
			case TdlPackage.EXCEPTIONAL_BEHAVIOUR__GUARDED_COMPONENT:
				setGuardedComponent((ComponentInstance)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TdlPackage.EXCEPTIONAL_BEHAVIOUR__BLOCK:
				setBlock((Block)null);
				return;
			case TdlPackage.EXCEPTIONAL_BEHAVIOUR__GUARDED_COMPONENT:
				setGuardedComponent((ComponentInstance)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TdlPackage.EXCEPTIONAL_BEHAVIOUR__BLOCK:
				return block != null;
			case TdlPackage.EXCEPTIONAL_BEHAVIOUR__GUARDED_COMPONENT:
				return guardedComponent != null;
		}
		return super.eIsSet(featureID);
	}

} //ExceptionalBehaviourImpl
