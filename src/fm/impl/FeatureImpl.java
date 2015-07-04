/**
 */
package fm.impl;

import java.util.List;

import ca.mcgill.sel.core.COREFeature;
import fm.Feature;
import fm.FmPackage;
import fm.MandatoryFMLink;
import grl.ElementLink;
import grl.impl.IntentionalElementImpl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link fm.impl.FeatureImpl#isSelectable <em>Selectable</em>}</li>
 *   <li>{@link fm.impl.FeatureImpl#getCoreFeature <em>Core Feature</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FeatureImpl extends IntentionalElementImpl implements Feature {
	/**
	 * The default value of the '{@link #isSelectable() <em>Selectable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSelectable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SELECTABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSelectable() <em>Selectable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSelectable()
	 * @generated
	 * @ordered
	 */
	protected boolean selectable = SELECTABLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCoreFeature() <em>Core Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoreFeature()
	 * @generated
	 * @ordered
	 */
	protected COREFeature coreFeature;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FmPackage.Literals.FEATURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSelectable(boolean newSelectable) {
		boolean oldSelectable = selectable;
		selectable = newSelectable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FmPackage.FEATURE__SELECTABLE, oldSelectable, selectable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public COREFeature getCoreFeature() {
		if (coreFeature != null && coreFeature.eIsProxy()) {
			InternalEObject oldCoreFeature = (InternalEObject)coreFeature;
			coreFeature = (COREFeature)eResolveProxy(oldCoreFeature);
			if (coreFeature != oldCoreFeature) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, FmPackage.FEATURE__CORE_FEATURE, oldCoreFeature, coreFeature));
			}
		}
		return coreFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public COREFeature basicGetCoreFeature() {
		return coreFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCoreFeature(COREFeature newCoreFeature) {
		COREFeature oldCoreFeature = coreFeature;
		coreFeature = newCoreFeature;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FmPackage.FEATURE__CORE_FEATURE, oldCoreFeature, coreFeature));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FmPackage.FEATURE__SELECTABLE:
				return isSelectable() ? Boolean.TRUE : Boolean.FALSE;
			case FmPackage.FEATURE__CORE_FEATURE:
				if (resolve) return getCoreFeature();
				return basicGetCoreFeature();
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
			case FmPackage.FEATURE__SELECTABLE:
				setSelectable(((Boolean)newValue).booleanValue());
				return;
			case FmPackage.FEATURE__CORE_FEATURE:
				setCoreFeature((COREFeature)newValue);
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
			case FmPackage.FEATURE__SELECTABLE:
				setSelectable(SELECTABLE_EDEFAULT);
				return;
			case FmPackage.FEATURE__CORE_FEATURE:
				setCoreFeature((COREFeature)null);
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
			case FmPackage.FEATURE__SELECTABLE:
				return selectable != SELECTABLE_EDEFAULT;
			case FmPackage.FEATURE__CORE_FEATURE:
				return coreFeature != null;
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
		result.append(" (selectable: ");
		result.append(selectable);
		result.append(')');
		return result.toString();
	}
	
	/**
	 * Tells if <b>this</b> feature is
	 * selectable, i.e. if it has only optional/OR/XOR
	 * links with all this parent features.
	 * 
	 * @return
	 * 		true if <b>this</b> feature is selectable, false otherwise.
	 * 
	 * @author pboul037
	 * 
	 */
	@SuppressWarnings("unchecked")
	public boolean isSelectable() {
		
		if (getLinksSrc() != null ){
			
			if( getLinksSrc().size() == 0){ // if this is a root 
				setSelectable(false);
				return false;	
			}
			else
				for ( ElementLink currentParentLink : (List<ElementLink>)getLinksSrc()){
					if ( currentParentLink instanceof MandatoryFMLink ){
						setSelectable(false);
						return false;	
					}
				}
		}
		setSelectable(true);
		return true;	
	}

} //FeatureImpl
