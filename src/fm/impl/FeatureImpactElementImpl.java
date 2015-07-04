/**
 */
package fm.impl;

import ca.mcgill.sel.core.COREFeatureImpactNode;

import fm.FeatureImpactElement;
import fm.FmPackage;

import grl.impl.IntentionalElementImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Impact Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fm.impl.FeatureImpactElementImpl#getCoreFeatureImpactNode <em>Core Feature Impact Node</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FeatureImpactElementImpl extends IntentionalElementImpl implements FeatureImpactElement {
	/**
	 * The cached value of the '{@link #getCoreFeatureImpactNode() <em>Core Feature Impact Node</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoreFeatureImpactNode()
	 * @generated
	 * @ordered
	 */
	protected COREFeatureImpactNode coreFeatureImpactNode;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureImpactElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FmPackage.Literals.FEATURE_IMPACT_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public COREFeatureImpactNode getCoreFeatureImpactNode() {
		if (coreFeatureImpactNode != null && coreFeatureImpactNode.eIsProxy()) {
			InternalEObject oldCoreFeatureImpactNode = (InternalEObject)coreFeatureImpactNode;
			coreFeatureImpactNode = (COREFeatureImpactNode)eResolveProxy(oldCoreFeatureImpactNode);
			if (coreFeatureImpactNode != oldCoreFeatureImpactNode) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FmPackage.FEATURE_IMPACT_ELEMENT__CORE_FEATURE_IMPACT_NODE, oldCoreFeatureImpactNode, coreFeatureImpactNode));
			}
		}
		return coreFeatureImpactNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public COREFeatureImpactNode basicGetCoreFeatureImpactNode() {
		return coreFeatureImpactNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCoreFeatureImpactNode(COREFeatureImpactNode newCoreFeatureImpactNode) {
		COREFeatureImpactNode oldCoreFeatureImpactNode = coreFeatureImpactNode;
		coreFeatureImpactNode = newCoreFeatureImpactNode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FmPackage.FEATURE_IMPACT_ELEMENT__CORE_FEATURE_IMPACT_NODE, oldCoreFeatureImpactNode, coreFeatureImpactNode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FmPackage.FEATURE_IMPACT_ELEMENT__CORE_FEATURE_IMPACT_NODE:
				if (resolve) return getCoreFeatureImpactNode();
				return basicGetCoreFeatureImpactNode();
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
			case FmPackage.FEATURE_IMPACT_ELEMENT__CORE_FEATURE_IMPACT_NODE:
				setCoreFeatureImpactNode((COREFeatureImpactNode)newValue);
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
			case FmPackage.FEATURE_IMPACT_ELEMENT__CORE_FEATURE_IMPACT_NODE:
				setCoreFeatureImpactNode((COREFeatureImpactNode)null);
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
			case FmPackage.FEATURE_IMPACT_ELEMENT__CORE_FEATURE_IMPACT_NODE:
				return coreFeatureImpactNode != null;
		}
		return super.eIsSet(featureID);
	}

} //FeatureImpactElementImpl
