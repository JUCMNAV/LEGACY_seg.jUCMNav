/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import urncore.ComponentLabel;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component Label</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class ComponentLabelImpl extends LabelImpl implements ComponentLabel {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ComponentLabelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected EClass eStaticClass() {
        return UrncorePackage.eINSTANCE.getComponentLabel();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object eGet(EStructuralFeature eFeature, boolean resolve) {
        switch (eDerivedStructuralFeatureID(eFeature)) {
            case UrncorePackage.COMPONENT_LABEL__DELTA_X:
                return new Integer(getDeltaX());
            case UrncorePackage.COMPONENT_LABEL__DELTA_Y:
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
            case UrncorePackage.COMPONENT_LABEL__DELTA_X:
                setDeltaX(((Integer)newValue).intValue());
                return;
            case UrncorePackage.COMPONENT_LABEL__DELTA_Y:
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
            case UrncorePackage.COMPONENT_LABEL__DELTA_X:
                setDeltaX(DELTA_X_EDEFAULT);
                return;
            case UrncorePackage.COMPONENT_LABEL__DELTA_Y:
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
            case UrncorePackage.COMPONENT_LABEL__DELTA_X:
                return deltaX != DELTA_X_EDEFAULT;
            case UrncorePackage.COMPONENT_LABEL__DELTA_Y:
                return deltaY != DELTA_Y_EDEFAULT;
        }
        return eDynamicIsSet(eFeature);
    }

} //ComponentLabelImpl
