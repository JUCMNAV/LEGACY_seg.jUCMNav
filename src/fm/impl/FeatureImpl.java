/**
 */
package fm.impl;

import fm.Feature;
import fm.FeatureDiagram;
import fm.FmPackage;
import fm.MandatoryFMLink;
import fm.OptionalFMLink;
import grl.Decomposition;
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

import seg.jUCMNav.core.COREFactory4URN;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddIntentionalElementRefCommand;
import seg.jUCMNav.model.commands.create.CreateElementLinkCommand;
import seg.jUCMNav.model.commands.delete.DeleteIntentionalElementCommand;
import seg.jUCMNav.model.commands.transformations.ChangeDecompositionTypeCommand;
import seg.jUCMNav.model.commands.transformations.ChangeGrlNodeNameCommand;
import urn.URNspec;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREFeatureRelationshipType;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.COREModelElement;
import ca.mcgill.sel.core.COREReuse;
import ca.mcgill.sel.core.CorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fm.impl.FeatureImpl#getRealizedBy <em>Realized By</em>}</li>
 *   <li>{@link fm.impl.FeatureImpl#getReuses <em>Reuses</em>}</li>
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
	protected EList<COREModel> realizedBy;

	/**
	 * The cached value of the '{@link #getReuses() <em>Reuses</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReuses()
	 * @generated
	 * @ordered
	 */
	protected EList<COREReuse> reuses;

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
	public EList<COREModel> getRealizedBy() {
		if (realizedBy == null) {
			realizedBy = new EObjectWithInverseResolvingEList.ManyInverse(COREModel.class, this, FmPackage.FEATURE__REALIZED_BY, CorePackage.CORE_MODEL__REALIZES);
		}
		return realizedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<COREReuse> getReuses() {
		if (reuses == null) {
			reuses = new EObjectContainmentEList(COREReuse.class, this, FmPackage.FEATURE__REUSES);
		}
		return reuses;
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
	public boolean changeParent(COREFeature feature, COREFeatureRelationshipType new_association) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean requires(COREFeature feature) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean excludes(COREFeature feature) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean removeConstraint(COREFeature feature) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean AssociateReuse(COREReuse reuse, EList<?> selected, EList<?> reexposed) {
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
			case FmPackage.FEATURE__REUSES:
				return ((InternalEList)getReuses()).basicRemove(otherEnd, msgs);
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
			case FmPackage.FEATURE__REUSES:
				return getReuses();
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
			case FmPackage.FEATURE__REUSES:
				getReuses().clear();
				getReuses().addAll((Collection)newValue);
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
			case FmPackage.FEATURE__REUSES:
				getReuses().clear();
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
			case FmPackage.FEATURE__REUSES:
				return reuses != null && !reuses.isEmpty();
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
		if (baseClass == COREModelElement.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == COREFeature.class) {
			switch (derivedFeatureID) {
				case FmPackage.FEATURE__REALIZED_BY: return CorePackage.CORE_FEATURE__REALIZED_BY;
				case FmPackage.FEATURE__REUSES: return CorePackage.CORE_FEATURE__REUSES;
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
		if (baseClass == COREModelElement.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == COREFeature.class) {
			switch (baseFeatureID) {
				case CorePackage.CORE_FEATURE__REALIZED_BY: return FmPackage.FEATURE__REALIZED_BY;
				case CorePackage.CORE_FEATURE__REUSES: return FmPackage.FEATURE__REUSES;
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public boolean addFeature(String childName, COREFeatureRelationshipType relationship) {
		return addFeature(-1, childName, relationship);
	}

	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public boolean addFeature(int position, String childName, COREFeatureRelationshipType relationship) {
		COREFactory4URN.setCOREInterfaceActive(true);
		if (relationship == COREFeatureRelationshipType.MANDATORY || relationship == COREFeatureRelationshipType.OPTIONAL ||
				relationship == COREFeatureRelationshipType.XOR || relationship == COREFeatureRelationshipType.OR) {
			// find the feature diagram of this feature, do not add new feature if feature diagram does not exist 
			// TODO this assumes that there is only one feature diagram where this feature is used; if there are more than one, then use the one where the feature is not a leaf
			FeatureDiagram fd = null;
			if (this.getRefs() != null) {
				Iterator it = this.getRefs().iterator();
				while (it.hasNext()) {
					GRLNode node = (GRLNode) it.next();
					if (node.getDiagram() instanceof FeatureDiagram) {
						fd = (FeatureDiagram) node.getDiagram();						
						break;
					}
				}
				if (fd != null) {
					// add new feature with childName and add to feature diagram
					URNspec urn = this.getGrlspec().getUrnspec();
					IntentionalElementRef ref = (IntentionalElementRef) ModelCreationFactory.getNewObject(urn, IntentionalElementRef.class, ModelCreationFactory.FEATURE);
					AddIntentionalElementRefCommand aierCmd = new AddIntentionalElementRefCommand(fd, ref);
					if (aierCmd.canExecute()) {
						aierCmd.execute();
						ChangeGrlNodeNameCommand cgnnCmd = new ChangeGrlNodeNameCommand(ref, childName);
						if (cgnnCmd.canExecute()) {
							cgnnCmd.execute();

							ElementLink link = null;
							int type = 0;
							if (relationship == COREFeatureRelationshipType.MANDATORY) {
								// add mandatory link between this feature and the new child feature
								link = (ElementLink) ModelCreationFactory.getNewObject(urn, MandatoryFMLink.class);								
							} else if (relationship == COREFeatureRelationshipType.OPTIONAL) {
								// add optional link between this feature and the new child feature
								link = (ElementLink) ModelCreationFactory.getNewObject(urn, OptionalFMLink.class);
							} else if (relationship == COREFeatureRelationshipType.XOR) {
								// add XOR decomposition link between this feature and the new child feature
								link = (ElementLink) ModelCreationFactory.getNewObject(urn, Decomposition.class);
								type = 2;
							} else if (relationship == COREFeatureRelationshipType.OR) {
								// add OR decomposition link between this feature and the new child feature
								link = (ElementLink) ModelCreationFactory.getNewObject(urn, Decomposition.class);
								type = 1;
							}
							// TODO add position to createelementlinkcommand
							CreateElementLinkCommand celCmd = new CreateElementLinkCommand(urn, (IntentionalElement) ref.getDef(), link);
							celCmd.setTarget(this);
							if (celCmd.canExecute())
								celCmd.execute();
							if (relationship == COREFeatureRelationshipType.XOR || relationship == COREFeatureRelationshipType.OR) {
								ChangeDecompositionTypeCommand cdtCmd = new ChangeDecompositionTypeCommand((IntentionalElementRef) this.getRefs().get(0), type);
								if (cdtCmd.canExecute())
									cdtCmd.execute();
							}
						}
					}
				}
			}
			COREFactory4URN.setCOREInterfaceActive(false);
			return true;
		}
		else {
			COREFactory4URN.setCOREInterfaceActive(false);
			return false;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public boolean delete() {
		COREFactory4URN.setCOREInterfaceActive(true);
		DeleteIntentionalElementCommand dieCmd = new DeleteIntentionalElementCommand(this);
		if (dieCmd.canExecute())
			dieCmd.execute();
		else {
			COREFactory4URN.setCOREInterfaceActive(false);
			return false;
		}
		COREFactory4URN.setCOREInterfaceActive(false);
		return true;
	}

	// TODO only experimental code at the moment
	public void reorder(int newPosition) {
	    
	    ElementLink link = (ElementLink) getLinksSrc().get(0);
	    IntentionalElement element = (IntentionalElement) link.getDest();
	    element.getLinksDest().remove(link);
	    element.getLinksDest().add(newPosition, link);
	   
	}
	
	// TODO only experimental code at the moment
	public boolean changeLink(COREFeatureRelationshipType relationship) {
		COREFactory4URN.setCOREInterfaceActive(true);
		int type;

		ElementLink link = (ElementLink) getLinksSrc().get(0);
		IntentionalElement element = (IntentionalElement) link.getDest();
		int position = element.getLinksDest().indexOf(link);
		element.getLinksDest().remove(link);
		getLinksSrc().clear();
		URNspec urn = this.getGrlspec().getUrnspec();
		urn.getGrlspec().getLinks().remove(link);

		ElementLink newLink = null;
		if (relationship == COREFeatureRelationshipType.MANDATORY) {
			// add mandatory link between this feature and the new child feature
			newLink = (ElementLink) ModelCreationFactory.getNewObject(urn, MandatoryFMLink.class);                             
		} else if (relationship == COREFeatureRelationshipType.OPTIONAL) {
			// add optional link between this feature and the new child feature
			newLink = (ElementLink) ModelCreationFactory.getNewObject(urn, OptionalFMLink.class);
		} else if (relationship == COREFeatureRelationshipType.XOR) {
			// add XOR decomposition link between this feature and the new child feature
			newLink = (ElementLink) ModelCreationFactory.getNewObject(urn, Decomposition.class);
			type = 2;
		} else if (relationship == COREFeatureRelationshipType.OR) {
			// add OR decomposition link between this feature and the new child feature
			newLink = (ElementLink) ModelCreationFactory.getNewObject(urn, Decomposition.class);
			type = 1;
		}


		if(relationship == COREFeatureRelationshipType.OPTIONAL || relationship == COREFeatureRelationshipType.MANDATORY ) {
			element.setDecompositionType(decompositionType.AND_LITERAL);

		} else if (relationship == COREFeatureRelationshipType.XOR) {
			element.setDecompositionType(decompositionType.XOR_LITERAL);

		} else if (relationship == COREFeatureRelationshipType.OR) {
			element.setDecompositionType(decompositionType.OR_LITERAL);
		}

		try {

			// TODO use the new command with position
			CreateElementLinkCommand celCmd = new CreateElementLinkCommand(urn, (IntentionalElement) this, newLink);
			celCmd.setTarget(element);
			if (celCmd.canExecute())
				celCmd.execute();

			// newLink.setDest(element);
			// newLink.setSrc(this);
		}
		catch(Exception e) {

		}
		COREFactory4URN.setCOREInterfaceActive(false);
		return true;
	}

	// TODO only experimental code at the moment
    public boolean addRealizedBy(COREModel model) {
        // TODO: implement this method
        // Ensure that you remove @generated or mark it @generated NOT
            getRealizedBy().add(0,model);
            return true;
    }

	// TODO only experimental code at the moment
//    public FeatureModel getFeatureModel() {
//        FeatureModel fm = null;
//        if (this.getRefs() != null) {
//            Iterator it = this.getRefs().iterator();
//            while (it.hasNext()) {
//                GRLNode node = (GRLNode) it.next();
//                if (node.getDiagram() instanceof FeatureModel) {
//                    fm = (FeatureModel) node.getDiagram();                      
//                    break;
//                }
//            }
//        }
//        return fm;
//    }


} //FeatureImpl
