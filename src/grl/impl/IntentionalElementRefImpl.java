/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Criticality;
import grl.GrlPackage;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.Priority;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Intentional Element Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.IntentionalElementRefImpl#getCriticality <em>Criticality</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementRefImpl#getPriority <em>Priority</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementRefImpl#getDef <em>Def</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IntentionalElementRefImpl extends GRLNodeImpl implements IntentionalElementRef {
    /**
	 * The default value of the '{@link #getCriticality() <em>Criticality</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCriticality()
	 * @generated
	 * @ordered
	 */
    protected static final Criticality CRITICALITY_EDEFAULT = Criticality.NONE_LITERAL;

    /**
	 * The cached value of the '{@link #getCriticality() <em>Criticality</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getCriticality()
	 * @generated
	 * @ordered
	 */
    protected Criticality criticality = CRITICALITY_EDEFAULT;

    /**
	 * The default value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
    protected static final Priority PRIORITY_EDEFAULT = Priority.NONE_LITERAL;

    /**
	 * The cached value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
    protected Priority priority = PRIORITY_EDEFAULT;

    /**
	 * The cached value of the '{@link #getDef() <em>Def</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDef()
	 * @generated
	 * @ordered
	 */
    protected IntentionalElement def;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected IntentionalElementRefImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return GrlPackage.Literals.INTENTIONAL_ELEMENT_REF;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Criticality getCriticality() {
		return criticality;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setCriticality(Criticality newCriticality) {
		Criticality oldCriticality = criticality;
		criticality = newCriticality == null ? CRITICALITY_EDEFAULT : newCriticality;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT_REF__CRITICALITY, oldCriticality, criticality));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Priority getPriority() {
		return priority;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setPriority(Priority newPriority) {
		Priority oldPriority = priority;
		priority = newPriority == null ? PRIORITY_EDEFAULT : newPriority;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT_REF__PRIORITY, oldPriority, priority));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public IntentionalElement getDef() {
		if (def != null && def.eIsProxy()) {
			InternalEObject oldDef = (InternalEObject)def;
			def = (IntentionalElement)eResolveProxy(oldDef);
			if (def != oldDef) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.INTENTIONAL_ELEMENT_REF__DEF, oldDef, def));
			}
		}
		return def;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public IntentionalElement basicGetDef() {
		return def;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetDef(IntentionalElement newDef, NotificationChain msgs) {
		IntentionalElement oldDef = def;
		def = newDef;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT_REF__DEF, oldDef, newDef);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDef(IntentionalElement newDef) {
		if (newDef != def) {
			NotificationChain msgs = null;
			if (def != null)
				msgs = ((InternalEObject)def).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__REFS, IntentionalElement.class, msgs);
			if (newDef != null)
				msgs = ((InternalEObject)newDef).eInverseAdd(this, GrlPackage.INTENTIONAL_ELEMENT__REFS, IntentionalElement.class, msgs);
			msgs = basicSetDef(newDef, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT_REF__DEF, newDef, newDef));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GrlPackage.INTENTIONAL_ELEMENT_REF__DEF:
				if (def != null)
					msgs = ((InternalEObject)def).eInverseRemove(this, GrlPackage.INTENTIONAL_ELEMENT__REFS, IntentionalElement.class, msgs);
				return basicSetDef((IntentionalElement)otherEnd, msgs);
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
			case GrlPackage.INTENTIONAL_ELEMENT_REF__DEF:
				return basicSetDef(null, msgs);
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
			case GrlPackage.INTENTIONAL_ELEMENT_REF__CRITICALITY:
				return getCriticality();
			case GrlPackage.INTENTIONAL_ELEMENT_REF__PRIORITY:
				return getPriority();
			case GrlPackage.INTENTIONAL_ELEMENT_REF__DEF:
				if (resolve) return getDef();
				return basicGetDef();
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
			case GrlPackage.INTENTIONAL_ELEMENT_REF__CRITICALITY:
				setCriticality((Criticality)newValue);
				return;
			case GrlPackage.INTENTIONAL_ELEMENT_REF__PRIORITY:
				setPriority((Priority)newValue);
				return;
			case GrlPackage.INTENTIONAL_ELEMENT_REF__DEF:
				setDef((IntentionalElement)newValue);
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
			case GrlPackage.INTENTIONAL_ELEMENT_REF__CRITICALITY:
				setCriticality(CRITICALITY_EDEFAULT);
				return;
			case GrlPackage.INTENTIONAL_ELEMENT_REF__PRIORITY:
				setPriority(PRIORITY_EDEFAULT);
				return;
			case GrlPackage.INTENTIONAL_ELEMENT_REF__DEF:
				setDef((IntentionalElement)null);
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
			case GrlPackage.INTENTIONAL_ELEMENT_REF__CRITICALITY:
				return criticality != CRITICALITY_EDEFAULT;
			case GrlPackage.INTENTIONAL_ELEMENT_REF__PRIORITY:
				return priority != PRIORITY_EDEFAULT;
			case GrlPackage.INTENTIONAL_ELEMENT_REF__DEF:
				return def != null;
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (criticality: ");
		result.append(criticality);
		result.append(", priority: ");
		result.append(priority);
		result.append(')');
		return result.toString();
	}

} //IntentionalElementRefImpl
