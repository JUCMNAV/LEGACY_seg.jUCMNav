/**
 */
package fm.impl;

import ca.mcgill.sel.core.COREFeatureRelationshipType;
import fm.Feature;
import fm.FeatureModel;
import fm.FmPackage;
import fm.MandatoryFMLink;
import fm.OptionalFMLink;
import grl.ContributionType;
import grl.Dependency;
import grl.ElementLink;
import grl.GRLNode;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.impl.IntentionalElementImpl;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddIntentionalElementRefCommand;
import seg.jUCMNav.model.commands.create.CreateElementLinkCommand;
import seg.jUCMNav.model.commands.delete.DeleteGRLNodeCommand;
import seg.jUCMNav.model.commands.delete.DeleteIntentionalElementCommand;
import seg.jUCMNav.model.commands.transformations.ChangeGrlNodeNameCommand;
import seg.jUCMNav.views.property.LinkRefPropertySource;
import urn.URNspec;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fm.impl.FeatureImpl#getRealizedBy <em>Realized By</em>}</li>
 *   <li>{@link fm.impl.FeatureImpl#getStrategies <em>Strategies</em>}</li>
 *   <li>{@link fm.impl.FeatureImpl#getConfigurations <em>Configurations</em>}</li>
 *   <li>{@link fm.impl.FeatureImpl#isSelectable <em>Selectable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureImpl extends IntentionalElementImpl implements Feature {
	/**
	 * The cached value of the '{@link #getRealizedBy() <em>Realized By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRealizedBy()
	 * @generated
	 * @ordered
	 */
	protected EList<ca.mcgill.sel.core.COREModel> realizedBy;

	/**
	 * The cached value of the '{@link #getStrategies() <em>Strategies</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrategies()
	 * @generated
	 * @ordered
	 */
	protected EList<ca.mcgill.sel.core.COREStrategy> strategies;

	/**
	 * The cached value of the '{@link #getConfigurations() <em>Configurations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfigurations()
	 * @generated
	 * @ordered
	 */
	protected EList<ca.mcgill.sel.core.COREConfiguration> configurations;

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
	public EList<ca.mcgill.sel.core.COREModel> getRealizedBy() {
		if (realizedBy == null) {
			realizedBy = new EObjectWithInverseResolvingEList.ManyInverse(ca.mcgill.sel.core.COREModel.class, this, FmPackage.FEATURE__REALIZED_BY, ca.mcgill.sel.core.CorePackage.CORE_MODEL__REALIZES);
		}
		return realizedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ca.mcgill.sel.core.COREStrategy> getStrategies() {
		if (strategies == null) {
			strategies = new EObjectContainmentEList(ca.mcgill.sel.core.COREStrategy.class, this, FmPackage.FEATURE__STRATEGIES);
		}
		return strategies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ca.mcgill.sel.core.COREConfiguration> getConfigurations() {
		if (configurations == null) {
			configurations = new EObjectContainmentEList(ca.mcgill.sel.core.COREConfiguration.class, this, FmPackage.FEATURE__CONFIGURATIONS);
		}
		return configurations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSelectable() {
		return selectable;
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
	 */
	public void addFeature(String childName, COREFeatureRelationshipType relationship) {
		// TODO add support for other relationships - only mandatory working right now!
		if (relationship == COREFeatureRelationshipType.MANDATORY || relationship == COREFeatureRelationshipType.OPTIONAL) {
			// find the feature model of this feature, do not add new feature if feature model does not exist 
			// TODO this assumes that there is only one feature model where this feature is used; if there are more than one, then use the one where the feature is not a leaf
			FeatureModel fm = null;
			if (this.getRefs() != null) {
				Iterator it = this.getRefs().iterator();
				while (it.hasNext()) {
					GRLNode node = (GRLNode) it.next();
					if (node.getDiagram() instanceof FeatureModel) {
						fm = (FeatureModel) node.getDiagram();						
						break;
					}
				}
				if (fm != null) {
					// add new feature with childName and add to feature model
					URNspec urn = this.getGrlspec().getUrnspec();
					IntentionalElementRef ref = (IntentionalElementRef) ModelCreationFactory.getNewObject(urn, IntentionalElementRef.class, ModelCreationFactory.FEATURE);
					AddIntentionalElementRefCommand aierCmd = new AddIntentionalElementRefCommand(fm, ref);
					if (aierCmd.canExecute()) {
						aierCmd.execute();
						ChangeGrlNodeNameCommand cgnnCmd = new ChangeGrlNodeNameCommand(ref, childName);
						if (cgnnCmd.canExecute()) {
							cgnnCmd.execute();

							ElementLink link = null;
							if (relationship == COREFeatureRelationshipType.MANDATORY) {
								// add mandatory link between this feature and the new child feature
								link = (ElementLink) ModelCreationFactory.getNewObject(urn, MandatoryFMLink.class);								
							} else {
								// add optional link between this feature and the new child feature
								link = (ElementLink) ModelCreationFactory.getNewObject(urn, OptionalFMLink.class);
							}
							CreateElementLinkCommand celCmd = new CreateElementLinkCommand(urn, (IntentionalElement) ref.getDef(), link);
							celCmd.setTarget(this);
							if (celCmd.canExecute())
								celCmd.execute();
						}
					}
				}
			}
		}
		else 
			throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public void delete() {
		DeleteIntentionalElementCommand dieCmd = new DeleteIntentionalElementCommand(this);
		if (dieCmd.canExecute())
			dieCmd.execute();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void rename(String core_feature_name) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void changeLink(COREFeatureRelationshipType new_association) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void changeParent(ca.mcgill.sel.core.COREFeature feature, COREFeatureRelationshipType new_association) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void requires(ca.mcgill.sel.core.COREFeature feature) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void excludes(ca.mcgill.sel.core.COREFeature feature) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeConstraint(ca.mcgill.sel.core.COREFeature feature) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addRealizedBy(ca.mcgill.sel.core.COREModel model) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FmPackage.FEATURE__REALIZED_BY:
				return ((InternalEList)getRealizedBy()).basicAdd(otherEnd, msgs);
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
			case FmPackage.FEATURE__REALIZED_BY:
				return ((InternalEList)getRealizedBy()).basicRemove(otherEnd, msgs);
			case FmPackage.FEATURE__STRATEGIES:
				return ((InternalEList)getStrategies()).basicRemove(otherEnd, msgs);
			case FmPackage.FEATURE__CONFIGURATIONS:
				return ((InternalEList)getConfigurations()).basicRemove(otherEnd, msgs);
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
			case FmPackage.FEATURE__REALIZED_BY:
				return getRealizedBy();
			case FmPackage.FEATURE__STRATEGIES:
				return getStrategies();
			case FmPackage.FEATURE__CONFIGURATIONS:
				return getConfigurations();
			case FmPackage.FEATURE__SELECTABLE:
				return isSelectable() ? Boolean.TRUE : Boolean.FALSE;
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
			case FmPackage.FEATURE__REALIZED_BY:
				getRealizedBy().clear();
				getRealizedBy().addAll((Collection)newValue);
				return;
			case FmPackage.FEATURE__STRATEGIES:
				getStrategies().clear();
				getStrategies().addAll((Collection)newValue);
				return;
			case FmPackage.FEATURE__CONFIGURATIONS:
				getConfigurations().clear();
				getConfigurations().addAll((Collection)newValue);
				return;
			case FmPackage.FEATURE__SELECTABLE:
				setSelectable(((Boolean)newValue).booleanValue());
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
			case FmPackage.FEATURE__REALIZED_BY:
				getRealizedBy().clear();
				return;
			case FmPackage.FEATURE__STRATEGIES:
				getStrategies().clear();
				return;
			case FmPackage.FEATURE__CONFIGURATIONS:
				getConfigurations().clear();
				return;
			case FmPackage.FEATURE__SELECTABLE:
				setSelectable(SELECTABLE_EDEFAULT);
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
			case FmPackage.FEATURE__REALIZED_BY:
				return realizedBy != null && !realizedBy.isEmpty();
			case FmPackage.FEATURE__STRATEGIES:
				return strategies != null && !strategies.isEmpty();
			case FmPackage.FEATURE__CONFIGURATIONS:
				return configurations != null && !configurations.isEmpty();
			case FmPackage.FEATURE__SELECTABLE:
				return selectable != SELECTABLE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == ca.mcgill.sel.core.COREModelElement.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == ca.mcgill.sel.core.COREFeature.class) {
			switch (derivedFeatureID) {
				case FmPackage.FEATURE__REALIZED_BY: return ca.mcgill.sel.core.CorePackage.CORE_FEATURE__REALIZED_BY;
				case FmPackage.FEATURE__STRATEGIES: return ca.mcgill.sel.core.CorePackage.CORE_FEATURE__STRATEGIES;
				case FmPackage.FEATURE__CONFIGURATIONS: return ca.mcgill.sel.core.CorePackage.CORE_FEATURE__CONFIGURATIONS;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
		if (baseClass == ca.mcgill.sel.core.COREModelElement.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == ca.mcgill.sel.core.COREFeature.class) {
			switch (baseFeatureID) {
				case ca.mcgill.sel.core.CorePackage.CORE_FEATURE__REALIZED_BY: return FmPackage.FEATURE__REALIZED_BY;
				case ca.mcgill.sel.core.CorePackage.CORE_FEATURE__STRATEGIES: return FmPackage.FEATURE__STRATEGIES;
				case ca.mcgill.sel.core.CorePackage.CORE_FEATURE__CONFIGURATIONS: return FmPackage.FEATURE__CONFIGURATIONS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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

} //FeatureImpl
