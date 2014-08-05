/**
 */
package fm.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import seg.jUCMNav.core.COREFactory4URN;
import seg.jUCMNav.editparts.IntentionalElementEditPart;
import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.CreateStrategiesGroupCommand;
import seg.jUCMNav.model.commands.create.CreateStrategyCommand;
import seg.jUCMNav.model.commands.transformations.ChangeNumericalEvaluationCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.strategies.util.FeatureUtil;
import urn.URNspec;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREFeatureSelectionStatus;
import ca.mcgill.sel.core.COREImpactModelElement;
import ca.mcgill.sel.core.impl.COREFeatureModelImpl;
import fm.Feature;
import fm.FeatureModel;
import fm.FmPackage;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GRLspec;
import grl.GrlPackage;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.StrategiesGroup;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link fm.impl.FeatureModelImpl#getGrlspec <em>Grlspec</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FeatureModelImpl extends COREFeatureModelImpl implements FeatureModel {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return FmPackage.Literals.FEATURE_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GRLspec getGrlspec() {
		if (eContainerFeatureID() != FmPackage.FEATURE_MODEL__GRLSPEC) return null;
		return (GRLspec)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGrlspec(GRLspec newGrlspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGrlspec, FmPackage.FEATURE_MODEL__GRLSPEC, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGrlspec(GRLspec newGrlspec) {
		if (newGrlspec != eInternalContainer() || (eContainerFeatureID() != FmPackage.FEATURE_MODEL__GRLSPEC && newGrlspec != null)) {
			if (EcoreUtil.isAncestor(this, newGrlspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGrlspec != null)
				msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__FEATURE_MODEL, GRLspec.class, msgs);
			msgs = basicSetGrlspec(newGrlspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, FmPackage.FEATURE_MODEL__GRLSPEC, newGrlspec, newGrlspec));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FmPackage.FEATURE_MODEL__GRLSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGrlspec((GRLspec)otherEnd, msgs);
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
			case FmPackage.FEATURE_MODEL__GRLSPEC:
				return basicSetGrlspec(null, msgs);
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
			case FmPackage.FEATURE_MODEL__GRLSPEC:
				return eInternalContainer().eInverseRemove(this, GrlPackage.GR_LSPEC__FEATURE_MODEL, GRLspec.class, msgs);
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
			case FmPackage.FEATURE_MODEL__GRLSPEC:
				return getGrlspec();
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
			case FmPackage.FEATURE_MODEL__GRLSPEC:
				setGrlspec((GRLspec)newValue);
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
			case FmPackage.FEATURE_MODEL__GRLSPEC:
				setGrlspec((GRLspec)null);
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
			case FmPackage.FEATURE_MODEL__GRLSPEC:
				return getGrlspec() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public COREFeature getGlobalRoot() {
		COREFactory4URN.setCOREInterfaceActive(true);
		List<Feature> roots = FeatureUtil.getRootFeatures(this.getGrlspec());
		// only returns the first of possible many roots (URN does not constrain feature models to one root)
		if (roots.isEmpty())
			return (COREFeature) COREFactory4URN.returnResult(null);
		else
			return (COREFeature) COREFactory4URN.returnResult(roots.get(0)); 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public EvaluationResult select(List<COREFeature> features) {
		COREFactory4URN.setCOREInterfaceActive(true);
		if (features.size() == 0)
			return (EvaluationResult) COREFactory4URN.returnResult(null);
		Iterator<COREFeature> it = features.iterator();
		URNspec urn = null;
		Vector<IntentionalElementRef> featureRefs = new Vector<IntentionalElementRef>();
		HashMap<COREFeature, String> featuresHash = new HashMap<COREFeature, String>();
		while (it.hasNext()) {
			COREFeature feature = it.next();
			if (feature instanceof Feature) {
				if (urn == null)
					urn = ((Feature) feature).getGrlspec().getUrnspec();
				if (!((Feature) feature).getRefs().isEmpty()) {
					featureRefs.add((IntentionalElementRef) ((Feature) feature).getRefs().get(0));
					featuresHash.put(feature, "");
				}
			}
		}
		if (urn == null)
			return (EvaluationResult) COREFactory4URN.returnResult(null);

		// create a new strategy based on the list of selected features
		// TODO this creates a new strategy group each time, there should be a dedicated group for CORE
		StrategiesGroup group = (StrategiesGroup) ModelCreationFactory.getNewObject(urn, StrategiesGroup.class);
		CreateStrategiesGroupCommand csgCmd = new CreateStrategiesGroupCommand(urn, group);
		if (csgCmd.canExecute())
			csgCmd.execute();
		else
			return (EvaluationResult) COREFactory4URN.returnResult(null);
		CreateStrategyCommand csCmd = new CreateStrategyCommand(urn, group);
		EvaluationStrategy strategy = csCmd.getStrategy();
		if (csCmd.canExecute())
			csCmd.execute();
		else 
			return (EvaluationResult) COREFactory4URN.returnResult(null);
		// select the new strategy and set the values of the selected features
        EvaluationStrategyManager.getInstance().setStrategy(strategy);
		ChangeNumericalEvaluationCommand cneCmd = new ChangeNumericalEvaluationCommand(featureRefs, ChangeNumericalEvaluationCommand.USER_ENTRY, 100, null);
		if (cneCmd.canExecute())
			cneCmd.execute();
		else
			return (EvaluationResult) COREFactory4URN.returnResult(null);

		// execute the strategy by calling setStrategy again
		EvaluationStrategyManager.getInstance().setStrategy(strategy);

		// collect the results and prepare the EvaluationResult
		// TODO only features are done so far, impact model results still need to be done
		EvaluationResult er = new EvaluationResult();
		Iterator it2 = urn.getGrlspec().getIntElements().iterator();
		while (it2.hasNext()) {
			IntentionalElement ie = (IntentionalElement) it2.next();
			Evaluation evaluation = EvaluationStrategyManager.getInstance().getEvaluationObject(ie);
			String color = IntentionalElementEditPart.determineColor(urn, ie, evaluation, false, IGRLStrategyAlgorithm.EVAL_FEATURE_MODEL);
			boolean warning = IntentionalElementEditPart.determineOverriddenWarning(ie, IGRLStrategyAlgorithm.EVAL_FEATURE_MODEL) || 
					IntentionalElementEditPart.determineOrXorWarning(ie, IGRLStrategyAlgorithm.EVAL_FEATURE_MODEL);
			if (ie instanceof COREFeature) {
				// color 96,255,96 = SELECTED unless in featuresHash, then USER_SELECTED
				// color 169,169,169 = NOT_SELECTED_NO_ACTION
				// color anything else but the above two = NOT_SELECTED_ACTION_REQUIRED
				// warning = WARNING
				COREFeatureSelectionStatus selectionStatus = COREFeatureSelectionStatus.NOT_SELECTED_ACTION_REQUIRED;
				if (warning)
					// TODO needs to differentiate between user selected and auto selected
					selectionStatus = COREFeatureSelectionStatus.WARNING_USER_SELECTED;
				else if (color.equals("169,169,169"))
					selectionStatus = COREFeatureSelectionStatus.NOT_SELECTED_NO_ACTION;
				else if (color.equals("96,255,96")) {
					if (featuresHash.containsKey(ie))
						selectionStatus = COREFeatureSelectionStatus.USER_SELECTED;
					else
						selectionStatus = COREFeatureSelectionStatus.AUTO_SELECTED;
				}
				er.featureResult.put((COREFeature) ie, selectionStatus);
			}			
		}

		// return the evaluation result
		return (EvaluationResult) COREFactory4URN.returnResult(er);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	public class EvaluationResult {
		public Map<COREFeature, COREFeatureSelectionStatus> featureResult = new HashMap<COREFeature, COREFeatureSelectionStatus>();
		public Map<COREImpactModelElement, Integer> impactResult = new HashMap<COREImpactModelElement, Integer>();	
	}
	
} //FeatureModelImpl
