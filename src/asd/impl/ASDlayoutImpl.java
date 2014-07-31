/**
 */
package asd.impl;

import asd.ASDiagram;
import asd.ASDlayout;
import asd.ASNetwork;
import asd.AsdPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AS Dlayout</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link asd.impl.ASDlayoutImpl#getX <em>X</em>}</li>
 *   <li>{@link asd.impl.ASDlayoutImpl#getY <em>Y</em>}</li>
 *   <li>{@link asd.impl.ASDlayoutImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link asd.impl.ASDlayoutImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link asd.impl.ASDlayoutImpl#isCollapsed <em>Collapsed</em>}</li>
 *   <li>{@link asd.impl.ASDlayoutImpl#getAsNetwork <em>As Network</em>}</li>
 *   <li>{@link asd.impl.ASDlayoutImpl#getAsDiagram <em>As Diagram</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ASDlayoutImpl extends MinimalEObjectImpl.Container implements ASDlayout {
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
	 * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected static final int WIDTH_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected int width = WIDTH_EDEFAULT;

	/**
	 * The default value of the '{@link #getHeight() <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeight()
	 * @generated
	 * @ordered
	 */
	protected static final int HEIGHT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeight()
	 * @generated
	 * @ordered
	 */
	protected int height = HEIGHT_EDEFAULT;

	/**
	 * The default value of the '{@link #isCollapsed() <em>Collapsed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCollapsed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean COLLAPSED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isCollapsed() <em>Collapsed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCollapsed()
	 * @generated
	 * @ordered
	 */
	protected boolean collapsed = COLLAPSED_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAsDiagram() <em>As Diagram</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAsDiagram()
	 * @generated
	 * @ordered
	 */
	protected ASDiagram asDiagram;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ASDlayoutImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return AsdPackage.Literals.AS_DLAYOUT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsdPackage.AS_DLAYOUT__X, oldX, x));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AsdPackage.AS_DLAYOUT__Y, oldY, y));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWidth(int newWidth) {
		int oldWidth = width;
		width = newWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsdPackage.AS_DLAYOUT__WIDTH, oldWidth, width));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeight(int newHeight) {
		int oldHeight = height;
		height = newHeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsdPackage.AS_DLAYOUT__HEIGHT, oldHeight, height));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isCollapsed() {
		return collapsed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCollapsed(boolean newCollapsed) {
		boolean oldCollapsed = collapsed;
		collapsed = newCollapsed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsdPackage.AS_DLAYOUT__COLLAPSED, oldCollapsed, collapsed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASNetwork getAsNetwork() {
		if (eContainerFeatureID() != AsdPackage.AS_DLAYOUT__AS_NETWORK) return null;
		return (ASNetwork)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAsNetwork(ASNetwork newAsNetwork, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newAsNetwork, AsdPackage.AS_DLAYOUT__AS_NETWORK, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAsNetwork(ASNetwork newAsNetwork) {
		if (newAsNetwork != eInternalContainer() || (eContainerFeatureID() != AsdPackage.AS_DLAYOUT__AS_NETWORK && newAsNetwork != null)) {
			if (EcoreUtil.isAncestor(this, newAsNetwork))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newAsNetwork != null)
				msgs = ((InternalEObject)newAsNetwork).eInverseAdd(this, AsdPackage.AS_NETWORK__ASD_LAYOUTS, ASNetwork.class, msgs);
			msgs = basicSetAsNetwork(newAsNetwork, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsdPackage.AS_DLAYOUT__AS_NETWORK, newAsNetwork, newAsNetwork));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASDiagram getAsDiagram() {
		if (asDiagram != null && asDiagram.eIsProxy()) {
			InternalEObject oldAsDiagram = (InternalEObject)asDiagram;
			asDiagram = (ASDiagram)eResolveProxy(oldAsDiagram);
			if (asDiagram != oldAsDiagram) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AsdPackage.AS_DLAYOUT__AS_DIAGRAM, oldAsDiagram, asDiagram));
			}
		}
		return asDiagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ASDiagram basicGetAsDiagram() {
		return asDiagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAsDiagram(ASDiagram newAsDiagram, NotificationChain msgs) {
		ASDiagram oldAsDiagram = asDiagram;
		asDiagram = newAsDiagram;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, AsdPackage.AS_DLAYOUT__AS_DIAGRAM, oldAsDiagram, newAsDiagram);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAsDiagram(ASDiagram newAsDiagram) {
		if (newAsDiagram != asDiagram) {
			NotificationChain msgs = null;
			if (asDiagram != null)
				msgs = ((InternalEObject)asDiagram).eInverseRemove(this, AsdPackage.AS_DIAGRAM__ASD_LAYOUTS, ASDiagram.class, msgs);
			if (newAsDiagram != null)
				msgs = ((InternalEObject)newAsDiagram).eInverseAdd(this, AsdPackage.AS_DIAGRAM__ASD_LAYOUTS, ASDiagram.class, msgs);
			msgs = basicSetAsDiagram(newAsDiagram, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AsdPackage.AS_DLAYOUT__AS_DIAGRAM, newAsDiagram, newAsDiagram));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AsdPackage.AS_DLAYOUT__AS_NETWORK:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetAsNetwork((ASNetwork)otherEnd, msgs);
			case AsdPackage.AS_DLAYOUT__AS_DIAGRAM:
				if (asDiagram != null)
					msgs = ((InternalEObject)asDiagram).eInverseRemove(this, AsdPackage.AS_DIAGRAM__ASD_LAYOUTS, ASDiagram.class, msgs);
				return basicSetAsDiagram((ASDiagram)otherEnd, msgs);
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
			case AsdPackage.AS_DLAYOUT__AS_NETWORK:
				return basicSetAsNetwork(null, msgs);
			case AsdPackage.AS_DLAYOUT__AS_DIAGRAM:
				return basicSetAsDiagram(null, msgs);
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
			case AsdPackage.AS_DLAYOUT__AS_NETWORK:
				return eInternalContainer().eInverseRemove(this, AsdPackage.AS_NETWORK__ASD_LAYOUTS, ASNetwork.class, msgs);
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
			case AsdPackage.AS_DLAYOUT__X:
				return new Integer(getX());
			case AsdPackage.AS_DLAYOUT__Y:
				return new Integer(getY());
			case AsdPackage.AS_DLAYOUT__WIDTH:
				return new Integer(getWidth());
			case AsdPackage.AS_DLAYOUT__HEIGHT:
				return new Integer(getHeight());
			case AsdPackage.AS_DLAYOUT__COLLAPSED:
				return isCollapsed() ? Boolean.TRUE : Boolean.FALSE;
			case AsdPackage.AS_DLAYOUT__AS_NETWORK:
				return getAsNetwork();
			case AsdPackage.AS_DLAYOUT__AS_DIAGRAM:
				if (resolve) return getAsDiagram();
				return basicGetAsDiagram();
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
			case AsdPackage.AS_DLAYOUT__X:
				setX(((Integer)newValue).intValue());
				return;
			case AsdPackage.AS_DLAYOUT__Y:
				setY(((Integer)newValue).intValue());
				return;
			case AsdPackage.AS_DLAYOUT__WIDTH:
				setWidth(((Integer)newValue).intValue());
				return;
			case AsdPackage.AS_DLAYOUT__HEIGHT:
				setHeight(((Integer)newValue).intValue());
				return;
			case AsdPackage.AS_DLAYOUT__COLLAPSED:
				setCollapsed(((Boolean)newValue).booleanValue());
				return;
			case AsdPackage.AS_DLAYOUT__AS_NETWORK:
				setAsNetwork((ASNetwork)newValue);
				return;
			case AsdPackage.AS_DLAYOUT__AS_DIAGRAM:
				setAsDiagram((ASDiagram)newValue);
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
			case AsdPackage.AS_DLAYOUT__X:
				setX(X_EDEFAULT);
				return;
			case AsdPackage.AS_DLAYOUT__Y:
				setY(Y_EDEFAULT);
				return;
			case AsdPackage.AS_DLAYOUT__WIDTH:
				setWidth(WIDTH_EDEFAULT);
				return;
			case AsdPackage.AS_DLAYOUT__HEIGHT:
				setHeight(HEIGHT_EDEFAULT);
				return;
			case AsdPackage.AS_DLAYOUT__COLLAPSED:
				setCollapsed(COLLAPSED_EDEFAULT);
				return;
			case AsdPackage.AS_DLAYOUT__AS_NETWORK:
				setAsNetwork((ASNetwork)null);
				return;
			case AsdPackage.AS_DLAYOUT__AS_DIAGRAM:
				setAsDiagram((ASDiagram)null);
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
			case AsdPackage.AS_DLAYOUT__X:
				return x != X_EDEFAULT;
			case AsdPackage.AS_DLAYOUT__Y:
				return y != Y_EDEFAULT;
			case AsdPackage.AS_DLAYOUT__WIDTH:
				return width != WIDTH_EDEFAULT;
			case AsdPackage.AS_DLAYOUT__HEIGHT:
				return height != HEIGHT_EDEFAULT;
			case AsdPackage.AS_DLAYOUT__COLLAPSED:
				return collapsed != COLLAPSED_EDEFAULT;
			case AsdPackage.AS_DLAYOUT__AS_NETWORK:
				return getAsNetwork() != null;
			case AsdPackage.AS_DLAYOUT__AS_DIAGRAM:
				return asDiagram != null;
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
		result.append(", width: ");
		result.append(width);
		result.append(", height: ");
		result.append(height);
		result.append(", collapsed: ");
		result.append(collapsed);
		result.append(')');
		return result.toString();
	}

} //ASDlayoutImpl
