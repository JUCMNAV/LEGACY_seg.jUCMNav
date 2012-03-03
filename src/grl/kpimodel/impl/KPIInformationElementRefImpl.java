/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.kpimodel.impl;

import grl.impl.GRLNodeImpl;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;
import grl.kpimodel.KpimodelPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KPI Information Element Ref</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.kpimodel.impl.KPIInformationElementRefImpl#getDef <em>Def</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KPIInformationElementRefImpl extends GRLNodeImpl implements KPIInformationElementRef {
    /**
	 * The cached value of the '{@link #getDef() <em>Def</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDef()
	 * @generated
	 * @ordered
	 */
    protected KPIInformationElement def;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected KPIInformationElementRefImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return KpimodelPackage.Literals.KPI_INFORMATION_ELEMENT_REF;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public KPIInformationElement getDef() {
		if (def != null && def.eIsProxy()) {
			InternalEObject oldDef = (InternalEObject)def;
			def = (KPIInformationElement)eResolveProxy(oldDef);
			if (def != oldDef) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, KpimodelPackage.KPI_INFORMATION_ELEMENT_REF__DEF, oldDef, def));
			}
		}
		return def;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public KPIInformationElement basicGetDef() {
		return def;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetDef(KPIInformationElement newDef, NotificationChain msgs) {
		KPIInformationElement oldDef = def;
		def = newDef;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, KpimodelPackage.KPI_INFORMATION_ELEMENT_REF__DEF, oldDef, newDef);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDef(KPIInformationElement newDef) {
		if (newDef != def) {
			NotificationChain msgs = null;
			if (def != null)
				msgs = ((InternalEObject)def).eInverseRemove(this, KpimodelPackage.KPI_INFORMATION_ELEMENT__REFS, KPIInformationElement.class, msgs);
			if (newDef != null)
				msgs = ((InternalEObject)newDef).eInverseAdd(this, KpimodelPackage.KPI_INFORMATION_ELEMENT__REFS, KPIInformationElement.class, msgs);
			msgs = basicSetDef(newDef, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.KPI_INFORMATION_ELEMENT_REF__DEF, newDef, newDef));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KpimodelPackage.KPI_INFORMATION_ELEMENT_REF__DEF:
				if (def != null)
					msgs = ((InternalEObject)def).eInverseRemove(this, KpimodelPackage.KPI_INFORMATION_ELEMENT__REFS, KPIInformationElement.class, msgs);
				return basicSetDef((KPIInformationElement)otherEnd, msgs);
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
			case KpimodelPackage.KPI_INFORMATION_ELEMENT_REF__DEF:
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
			case KpimodelPackage.KPI_INFORMATION_ELEMENT_REF__DEF:
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
			case KpimodelPackage.KPI_INFORMATION_ELEMENT_REF__DEF:
				setDef((KPIInformationElement)newValue);
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
			case KpimodelPackage.KPI_INFORMATION_ELEMENT_REF__DEF:
				setDef((KPIInformationElement)null);
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
			case KpimodelPackage.KPI_INFORMATION_ELEMENT_REF__DEF:
				return def != null;
		}
		return super.eIsSet(featureID);
	}

} //KPIInformationElementRefImpl