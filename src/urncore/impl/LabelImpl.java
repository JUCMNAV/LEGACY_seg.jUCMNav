/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import urncore.Label;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Label</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link urncore.impl.LabelImpl#getDeltaX <em>Delta X</em>}</li>
 *   <li>{@link urncore.impl.LabelImpl#getDeltaY <em>Delta Y</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class LabelImpl extends EObjectImpl implements Label {
    /**
     * The default value of the '{@link #getDeltaX() <em>Delta X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDeltaX()
     * @generated
     * @ordered
     */
    protected static final int DELTA_X_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getDeltaX() <em>Delta X</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDeltaX()
     * @generated
     * @ordered
     */
    protected int deltaX = DELTA_X_EDEFAULT;

    /**
     * The default value of the '{@link #getDeltaY() <em>Delta Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDeltaY()
     * @generated
     * @ordered
     */
    protected static final int DELTA_Y_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getDeltaY() <em>Delta Y</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDeltaY()
     * @generated
     * @ordered
     */
    protected int deltaY = DELTA_Y_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected LabelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return UrncorePackage.eINSTANCE.getLabel();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getDeltaX() {
        return deltaX;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDeltaX(int newDeltaX) {
        int oldDeltaX = deltaX;
        deltaX = newDeltaX;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.LABEL__DELTA_X, oldDeltaX, deltaX));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getDeltaY() {
        return deltaY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDeltaY(int newDeltaY) {
        int oldDeltaY = deltaY;
        deltaY = newDeltaY;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, UrncorePackage.LABEL__DELTA_Y, oldDeltaY, deltaY));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case UrncorePackage.LABEL__DELTA_X:
                return new Integer(getDeltaX());
            case UrncorePackage.LABEL__DELTA_Y:
                return new Integer(getDeltaY());
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
            case UrncorePackage.LABEL__DELTA_X:
                setDeltaX(((Integer)newValue).intValue());
                return;
            case UrncorePackage.LABEL__DELTA_Y:
                setDeltaY(((Integer)newValue).intValue());
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
            case UrncorePackage.LABEL__DELTA_X:
                setDeltaX(DELTA_X_EDEFAULT);
                return;
            case UrncorePackage.LABEL__DELTA_Y:
                setDeltaY(DELTA_Y_EDEFAULT);
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
            case UrncorePackage.LABEL__DELTA_X:
                return deltaX != DELTA_X_EDEFAULT;
            case UrncorePackage.LABEL__DELTA_Y:
                return deltaY != DELTA_Y_EDEFAULT;
        }
        return eDynamicIsSet(eFeature);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (deltaX: ");
        result.append(deltaX);
        result.append(", deltaY: ");
        result.append(deltaY);
        result.append(')');
        return result.toString();
    }

} //LabelImpl
