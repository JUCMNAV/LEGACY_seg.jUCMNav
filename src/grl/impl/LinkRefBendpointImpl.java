/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.GrlPackage;
import grl.LinkRef;
import grl.LinkRefBendpoint;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link Ref Bendpoint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.LinkRefBendpointImpl#getX <em>X</em>}</li>
 *   <li>{@link grl.impl.LinkRefBendpointImpl#getY <em>Y</em>}</li>
 *   <li>{@link grl.impl.LinkRefBendpointImpl#getLinkref <em>Linkref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LinkRefBendpointImpl extends MinimalEObjectImpl.Container implements LinkRefBendpoint {
    /**
	 * The default value of the '{@link #getX() <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getX()
	 * @generated
	 * @ordered
	 */
    protected static final int X_EDEFAULT = 0;

    /**
	 * The cached value of the '{@link #getX() <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getX()
	 * @generated
	 * @ordered
	 */
    protected int x = X_EDEFAULT;

    /**
	 * The default value of the '{@link #getY() <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getY()
	 * @generated
	 * @ordered
	 */
    protected static final int Y_EDEFAULT = 0;

    /**
	 * The cached value of the '{@link #getY() <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getY()
	 * @generated
	 * @ordered
	 */
    protected int y = Y_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected LinkRefBendpointImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return GrlPackage.Literals.LINK_REF_BENDPOINT;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getX() {
		return x;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setX(int newX) {
		int oldX = x;
		x = newX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.LINK_REF_BENDPOINT__X, oldX, x));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int getY() {
		return y;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setY(int newY) {
		int oldY = y;
		y = newY;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.LINK_REF_BENDPOINT__Y, oldY, y));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public LinkRef getLinkref() {
		if (eContainerFeatureID() != GrlPackage.LINK_REF_BENDPOINT__LINKREF) return null;
		return (LinkRef)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLinkref(LinkRef newLinkref, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newLinkref, GrlPackage.LINK_REF_BENDPOINT__LINKREF, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setLinkref(LinkRef newLinkref) {
		if (newLinkref != eInternalContainer() || (eContainerFeatureID() != GrlPackage.LINK_REF_BENDPOINT__LINKREF && newLinkref != null)) {
			if (EcoreUtil.isAncestor(this, newLinkref))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLinkref != null)
				msgs = ((InternalEObject)newLinkref).eInverseAdd(this, GrlPackage.LINK_REF__BENDPOINTS, LinkRef.class, msgs);
			msgs = basicSetLinkref(newLinkref, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.LINK_REF_BENDPOINT__LINKREF, newLinkref, newLinkref));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GrlPackage.LINK_REF_BENDPOINT__LINKREF:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetLinkref((LinkRef)otherEnd, msgs);
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
			case GrlPackage.LINK_REF_BENDPOINT__LINKREF:
				return basicSetLinkref(null, msgs);
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
			case GrlPackage.LINK_REF_BENDPOINT__LINKREF:
				return eInternalContainer().eInverseRemove(this, GrlPackage.LINK_REF__BENDPOINTS, LinkRef.class, msgs);
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
			case GrlPackage.LINK_REF_BENDPOINT__X:
				return new Integer(getX());
			case GrlPackage.LINK_REF_BENDPOINT__Y:
				return new Integer(getY());
			case GrlPackage.LINK_REF_BENDPOINT__LINKREF:
				return getLinkref();
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
			case GrlPackage.LINK_REF_BENDPOINT__X:
				setX(((Integer)newValue).intValue());
				return;
			case GrlPackage.LINK_REF_BENDPOINT__Y:
				setY(((Integer)newValue).intValue());
				return;
			case GrlPackage.LINK_REF_BENDPOINT__LINKREF:
				setLinkref((LinkRef)newValue);
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
			case GrlPackage.LINK_REF_BENDPOINT__X:
				setX(X_EDEFAULT);
				return;
			case GrlPackage.LINK_REF_BENDPOINT__Y:
				setY(Y_EDEFAULT);
				return;
			case GrlPackage.LINK_REF_BENDPOINT__LINKREF:
				setLinkref((LinkRef)null);
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
			case GrlPackage.LINK_REF_BENDPOINT__X:
				return x != X_EDEFAULT;
			case GrlPackage.LINK_REF_BENDPOINT__Y:
				return y != Y_EDEFAULT;
			case GrlPackage.LINK_REF_BENDPOINT__LINKREF:
				return getLinkref() != null;
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
		result.append(" (x: ");
		result.append(x);
		result.append(", y: ");
		result.append(y);
		result.append(')');
		return result.toString();
	}

} //LinkRefBendpointImpl
