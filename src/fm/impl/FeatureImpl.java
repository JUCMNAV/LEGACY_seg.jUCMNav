/**
 */
package fm.impl;

import fm.Feature;
import fm.FeatureDiagram;
import fm.FmPackage;
import grl.ElementLink;
import grl.GRLNode;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.LinkRef;
import grl.impl.IntentionalElementImpl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
import seg.jUCMNav.model.commands.delete.DeleteIntentionalElementCommand;
import seg.jUCMNav.model.commands.transformations.ChangeGrlNodeNameCommand;
import seg.jUCMNav.model.commands.transformations.ChangeLinkCommand;
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
		
		String strPosition = null;
		String strRelationship = convertCOREFeatureRelationshipTypeToString(relationship);
		
		if (position > 0)
			strPosition = String.valueOf(position);
	
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
							
							ChangeLinkCommand changeLinkCmd = new ChangeLinkCommand(strRelationship, (IntentionalElementRef)this.getRefs().get(0));
							if ( ! changeLinkCmd.createNewLink((IntentionalElement)this, (IntentionalElement) ref.getDef(), urn, strPosition, strRelationship, null) )
								return (Boolean) COREFactory4URN.returnResult(false);
							
						}else
							return (Boolean) COREFactory4URN.returnResult(false);
					}
				}
			}
			return (Boolean) COREFactory4URN.returnResult(true);
		}
		else {
			return (Boolean) COREFactory4URN.returnResult(false);
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
			return (Boolean) COREFactory4URN.returnResult(false);
		}
		return (Boolean) COREFactory4URN.returnResult(true);
	}

	// TODO only experimental code at the moment
	public void reorder(int newPosition) {
	    
	    ElementLink link = (ElementLink) getLinksSrc().get(0);
	    IntentionalElement element = (IntentionalElement) link.getDest();
	    element.getLinksDest().remove(link);
	    element.getLinksDest().add(newPosition, link);
	   
	}
	
	@SuppressWarnings("unchecked")
	public boolean changeLink(COREFeatureRelationshipType relationship) {
		
		COREFactory4URN.setCOREInterfaceActive(true);
		
		ChangeLinkCommand changeLinkCmd = null;
		
		/* A String representation of the relationship for use in jUCMNav */
		String strRelationship = null;
		
		if( relationship == COREFeatureRelationshipType.MANDATORY){
			strRelationship = ChangeLinkCommand.FEATURE_MANDATORY_RELATIONSHIP;
		}else if (relationship == COREFeatureRelationshipType.OPTIONAL){
			strRelationship = ChangeLinkCommand.FEATURE_OPTIONAL_RELATIONSHIP;
		}else if (relationship == COREFeatureRelationshipType.OR){
			strRelationship = ChangeLinkCommand.FEATURE_OR_RELATIONSHIP;
		}else if (relationship == COREFeatureRelationshipType.XOR){
			strRelationship = ChangeLinkCommand.FEATURE_XOR_RELATIONSHIP;
		}
		
		for( IntentionalElementRef intElemRef : (List<IntentionalElementRef>)getRefs()){
			changeLinkCmd = new ChangeLinkCommand(strRelationship, intElemRef);
			if( changeLinkCmd.canExecute())
				changeLinkCmd.execute();
			else 
				return (Boolean) COREFactory4URN.returnResult(false);
		}
		
		return (Boolean) COREFactory4URN.returnResult(true);
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
    
    /**
	 * 
	 *
	 * @author pboul037
	 *
	 */
	public boolean changeParent(COREFeature feature, COREFeatureRelationshipType new_association) {
	
		COREFactory4URN.setCOREInterfaceActive(true);
		
		String strRelationship = convertCOREFeatureRelationshipTypeToString(new_association);
		
		if (feature != null && 
				(new_association == COREFeatureRelationshipType.MANDATORY || 
					new_association == COREFeatureRelationshipType.OPTIONAL ||
						new_association == COREFeatureRelationshipType.XOR ||
							new_association == COREFeatureRelationshipType.OR)) {
			
			URNspec urn = this.getGrlspec().getUrnspec();
			
			if( this.getLinksSrc().size() == 1){
				LinkRef linkRef = null;
				
				// find and delete the parent link
				ElementLink oldLink = (ElementLink)this.getLinksSrc().get(0);
				
				ChangeLinkCommand changeLinkCmd = new ChangeLinkCommand(strRelationship, (IntentionalElementRef)this.getRefs().get(0));
				// add new link with desired parent
				if ( ! changeLinkCmd.createNewLink((IntentionalElement)feature, (IntentionalElement) this, urn, null, strRelationship, oldLink) )
					return (Boolean) COREFactory4URN.returnResult(false);
				
				return (Boolean) COREFactory4URN.returnResult(true);
				
			}else{
				// has more than 1 parent ?
				return (Boolean) COREFactory4URN.returnResult(false);
			}
			
		} else {
			return (Boolean) COREFactory4URN.returnResult(false);
		}
	}
	
	/**
	 * Renames <b>this</b> Feature.
	 * 
	 * @param core_feature_name
	 * 		new name for <b>this</b> Feature.
	 * 
	 * @author pboul037
	 */
	public void rename(String core_feature_name) {
		
		COREFactory4URN.setCOREInterfaceActive(true);
	
		for( Object obj : getRefs()){
			
			IntentionalElementRef intElemRef = null;
			if( obj instanceof IntentionalElementRef)
				intElemRef = (IntentionalElementRef) obj;
			
			ChangeGrlNodeNameCommand changeNameCmd = new ChangeGrlNodeNameCommand(intElemRef, core_feature_name);		
			if ( changeNameCmd.canExecute()){
				changeNameCmd.execute();
			}else{
				COREFactory4URN.returnResult(false);
			}
		}
		
		COREFactory4URN.returnResult(true);
	}
	
	private String convertCOREFeatureRelationshipTypeToString(COREFeatureRelationshipType relationship){
		
		/* A String representation of the relationship for use in jUCMNav */
		String strRelationship = null;
		
		if( relationship == COREFeatureRelationshipType.MANDATORY){
			strRelationship = ChangeLinkCommand.FEATURE_MANDATORY_RELATIONSHIP;
		}else if (relationship == COREFeatureRelationshipType.OPTIONAL){
			strRelationship = ChangeLinkCommand.FEATURE_OPTIONAL_RELATIONSHIP;
		}else if (relationship == COREFeatureRelationshipType.OR){
			strRelationship = ChangeLinkCommand.FEATURE_OR_RELATIONSHIP;
		}else if (relationship == COREFeatureRelationshipType.XOR){
			strRelationship = ChangeLinkCommand.FEATURE_XOR_RELATIONSHIP;
		}
		return strRelationship;
	}
	
} //FeatureImpl
