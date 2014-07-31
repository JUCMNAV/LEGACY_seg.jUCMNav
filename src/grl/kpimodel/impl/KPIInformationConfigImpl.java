/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.kpimodel.impl;

import grl.EvaluationStrategy;
import grl.GrlPackage;
import grl.kpimodel.KPIInformationConfig;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KpimodelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>KPI Information Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.kpimodel.impl.KPIInformationConfigImpl#getLevelOfDimension <em>Level Of Dimension</em>}</li>
 *   <li>{@link grl.kpimodel.impl.KPIInformationConfigImpl#getValueOfDimension <em>Value Of Dimension</em>}</li>
 *   <li>{@link grl.kpimodel.impl.KPIInformationConfigImpl#getStrategies <em>Strategies</em>}</li>
 *   <li>{@link grl.kpimodel.impl.KPIInformationConfigImpl#getKpiInfoElement <em>Kpi Info Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class KPIInformationConfigImpl extends MinimalEObjectImpl.Container implements KPIInformationConfig {
    /**
	 * The default value of the '{@link #getLevelOfDimension() <em>Level Of Dimension</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLevelOfDimension()
	 * @generated
	 * @ordered
	 */
    protected static final String LEVEL_OF_DIMENSION_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getLevelOfDimension() <em>Level Of Dimension</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLevelOfDimension()
	 * @generated
	 * @ordered
	 */
    protected String levelOfDimension = LEVEL_OF_DIMENSION_EDEFAULT;

    /**
	 * The default value of the '{@link #getValueOfDimension() <em>Value Of Dimension</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getValueOfDimension()
	 * @generated
	 * @ordered
	 */
    protected static final String VALUE_OF_DIMENSION_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getValueOfDimension() <em>Value Of Dimension</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getValueOfDimension()
	 * @generated
	 * @ordered
	 */
    protected String valueOfDimension = VALUE_OF_DIMENSION_EDEFAULT;

    /**
	 * The cached value of the '{@link #getKpiInfoElement() <em>Kpi Info Element</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getKpiInfoElement()
	 * @generated
	 * @ordered
	 */
    protected KPIInformationElement kpiInfoElement;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected KPIInformationConfigImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return KpimodelPackage.Literals.KPI_INFORMATION_CONFIG;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getLevelOfDimension() {
		return levelOfDimension;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setLevelOfDimension(String newLevelOfDimension) {
		String oldLevelOfDimension = levelOfDimension;
		levelOfDimension = newLevelOfDimension;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.KPI_INFORMATION_CONFIG__LEVEL_OF_DIMENSION, oldLevelOfDimension, levelOfDimension));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getValueOfDimension() {
		return valueOfDimension;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setValueOfDimension(String newValueOfDimension) {
		String oldValueOfDimension = valueOfDimension;
		valueOfDimension = newValueOfDimension;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.KPI_INFORMATION_CONFIG__VALUE_OF_DIMENSION, oldValueOfDimension, valueOfDimension));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EvaluationStrategy getStrategies() {
		if (eContainerFeatureID() != KpimodelPackage.KPI_INFORMATION_CONFIG__STRATEGIES) return null;
		return (EvaluationStrategy)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetStrategies(EvaluationStrategy newStrategies, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newStrategies, KpimodelPackage.KPI_INFORMATION_CONFIG__STRATEGIES, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setStrategies(EvaluationStrategy newStrategies) {
		if (newStrategies != eInternalContainer() || (eContainerFeatureID() != KpimodelPackage.KPI_INFORMATION_CONFIG__STRATEGIES && newStrategies != null)) {
			if (EcoreUtil.isAncestor(this, newStrategies))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newStrategies != null)
				msgs = ((InternalEObject)newStrategies).eInverseAdd(this, GrlPackage.EVALUATION_STRATEGY__KPI_INFO_CONFIG, EvaluationStrategy.class, msgs);
			msgs = basicSetStrategies(newStrategies, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.KPI_INFORMATION_CONFIG__STRATEGIES, newStrategies, newStrategies));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public KPIInformationElement getKpiInfoElement() {
		if (kpiInfoElement != null && kpiInfoElement.eIsProxy()) {
			InternalEObject oldKpiInfoElement = (InternalEObject)kpiInfoElement;
			kpiInfoElement = (KPIInformationElement)eResolveProxy(oldKpiInfoElement);
			if (kpiInfoElement != oldKpiInfoElement) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, KpimodelPackage.KPI_INFORMATION_CONFIG__KPI_INFO_ELEMENT, oldKpiInfoElement, kpiInfoElement));
			}
		}
		return kpiInfoElement;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public KPIInformationElement basicGetKpiInfoElement() {
		return kpiInfoElement;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setKpiInfoElement(KPIInformationElement newKpiInfoElement) {
		KPIInformationElement oldKpiInfoElement = kpiInfoElement;
		kpiInfoElement = newKpiInfoElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KpimodelPackage.KPI_INFORMATION_CONFIG__KPI_INFO_ELEMENT, oldKpiInfoElement, kpiInfoElement));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case KpimodelPackage.KPI_INFORMATION_CONFIG__STRATEGIES:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetStrategies((EvaluationStrategy)otherEnd, msgs);
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
			case KpimodelPackage.KPI_INFORMATION_CONFIG__STRATEGIES:
				return basicSetStrategies(null, msgs);
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
			case KpimodelPackage.KPI_INFORMATION_CONFIG__STRATEGIES:
				return eInternalContainer().eInverseRemove(this, GrlPackage.EVALUATION_STRATEGY__KPI_INFO_CONFIG, EvaluationStrategy.class, msgs);
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
			case KpimodelPackage.KPI_INFORMATION_CONFIG__LEVEL_OF_DIMENSION:
				return getLevelOfDimension();
			case KpimodelPackage.KPI_INFORMATION_CONFIG__VALUE_OF_DIMENSION:
				return getValueOfDimension();
			case KpimodelPackage.KPI_INFORMATION_CONFIG__STRATEGIES:
				return getStrategies();
			case KpimodelPackage.KPI_INFORMATION_CONFIG__KPI_INFO_ELEMENT:
				if (resolve) return getKpiInfoElement();
				return basicGetKpiInfoElement();
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
			case KpimodelPackage.KPI_INFORMATION_CONFIG__LEVEL_OF_DIMENSION:
				setLevelOfDimension((String)newValue);
				return;
			case KpimodelPackage.KPI_INFORMATION_CONFIG__VALUE_OF_DIMENSION:
				setValueOfDimension((String)newValue);
				return;
			case KpimodelPackage.KPI_INFORMATION_CONFIG__STRATEGIES:
				setStrategies((EvaluationStrategy)newValue);
				return;
			case KpimodelPackage.KPI_INFORMATION_CONFIG__KPI_INFO_ELEMENT:
				setKpiInfoElement((KPIInformationElement)newValue);
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
			case KpimodelPackage.KPI_INFORMATION_CONFIG__LEVEL_OF_DIMENSION:
				setLevelOfDimension(LEVEL_OF_DIMENSION_EDEFAULT);
				return;
			case KpimodelPackage.KPI_INFORMATION_CONFIG__VALUE_OF_DIMENSION:
				setValueOfDimension(VALUE_OF_DIMENSION_EDEFAULT);
				return;
			case KpimodelPackage.KPI_INFORMATION_CONFIG__STRATEGIES:
				setStrategies((EvaluationStrategy)null);
				return;
			case KpimodelPackage.KPI_INFORMATION_CONFIG__KPI_INFO_ELEMENT:
				setKpiInfoElement((KPIInformationElement)null);
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
			case KpimodelPackage.KPI_INFORMATION_CONFIG__LEVEL_OF_DIMENSION:
				return LEVEL_OF_DIMENSION_EDEFAULT == null ? levelOfDimension != null : !LEVEL_OF_DIMENSION_EDEFAULT.equals(levelOfDimension);
			case KpimodelPackage.KPI_INFORMATION_CONFIG__VALUE_OF_DIMENSION:
				return VALUE_OF_DIMENSION_EDEFAULT == null ? valueOfDimension != null : !VALUE_OF_DIMENSION_EDEFAULT.equals(valueOfDimension);
			case KpimodelPackage.KPI_INFORMATION_CONFIG__STRATEGIES:
				return getStrategies() != null;
			case KpimodelPackage.KPI_INFORMATION_CONFIG__KPI_INFO_ELEMENT:
				return kpiInfoElement != null;
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
		result.append(" (levelOfDimension: ");
		result.append(levelOfDimension);
		result.append(", valueOfDimension: ");
		result.append(valueOfDimension);
		result.append(')');
		return result.toString();
	}

} //KPIInformationConfigImpl